package com.hbg.rp.search.util;

import com.hbg.rp.model.Product;
import com.hbg.rp.model.ProductPrice;
import com.hbg.rp.search.constants.ApplicationConstant;
import com.hbg.rp.search.model.CatalogDTO;
import com.hbg.rp.service.NrpPubstatusMappingLocalServiceUtil;
import com.hbg.rp.service.ProductLocalServiceUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * <p>
 * The com.hbg.rp.util.CatalogUtil is the implementation of
 * </p>
 * {@link ICatalogUtil}.
 * 
 * @author ravi.kumar
 *
 */
@Component
public class CatalogUtil implements ICatalogUtil {

	private static final String PARAM_CATALOG_DATA = "catalogData";
	private static final String PARAM_PUBSTATUS_DATA = "pubStatus";
	private static final String PARAM_FORMAT_DATA = "formats";

	@Autowired
	private IPortalMappingsUtil portalMappingsUtil;

	@Autowired
	private IGenericCacheHandler genericCacheHandler;

	@Override
	public Map<String, Object> getPrerequisitModel() throws SystemException {
		Map<String, Object> model = new HashMap<>();
		List<String> sortedReportingGroups = genericCacheHandler.getAllReportingGroups();
		Collections.sort(sortedReportingGroups);
		model.put(ApplicationConstant.PARAM_REPORTING_GROUPS, sortedReportingGroups);
		List<String> formats = ProductLocalServiceUtil.getFormats();
		model.put(PARAM_FORMAT_DATA, formats);

		Map<String, String> psMap = portalMappingsUtil.getPubStatusData();
		Map<String, String> messageMap = NrpPubstatusMappingLocalServiceUtil.getAllPubStatusMap();	
		Set<String> pubStatusKeys = psMap.keySet();
		Iterator<String> it = pubStatusKeys.iterator();
		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();
		while (it.hasNext()) {
			String key = it.next();
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
			jsonObject.put("code", key.toUpperCase());
			jsonObject.put("desc", messageMap.containsKey(key.toUpperCase()) ? messageMap.get(key.toUpperCase()) : psMap.get(key));
			jsonArray.put(jsonObject);
		}
		model.put(PARAM_PUBSTATUS_DATA, jsonArray);
		return model;
	}

	@Override
	public Map<String, Object> getProduct(Map<String, String> catalogSearchCriteriaMap) throws SystemException {
		logger.info("getProduct() <<");

		Map<String, Object> catalogsMap = new HashMap<>();
//		for (Map.Entry<String, String> entry : catalogSearchCriteriaMap.entrySet()) {
//			logger.info("search criteria key: " + entry.getKey() + " value: " + entry.getValue());
//		}

		long startTime = System.currentTimeMillis();
		catalogSearchCriteriaMap.put(ApplicationConstant.PARAM_CATALOG_LIMIT, ApplicationConstant.PAGE_SIZE.toString());
		List<Product> catalogsData = ProductLocalServiceUtil.getProduct(catalogSearchCriteriaMap);
		// Fetch prices for the catalogs
		List catalogsPrices = new ArrayList<>();
		if(null != catalogsData && !catalogsData.isEmpty()){
			catalogsPrices = ProductLocalServiceUtil.getCatalogPrices(catalogsData);
		}
				
		Map<String, String> productKeyPriceMap = new HashMap<>();

		if (null != catalogsPrices && !catalogsPrices.isEmpty()) {

			for (Object object : catalogsPrices) {
				ProductPrice productPrice = (ProductPrice) object;
				String finalPrice = "";
				String priceType = "";
				String priceStatus = productPrice.getPriceStatus() == null ? "" : productPrice.getPriceStatus();
				String noChargeFlag = productPrice.getNoChargeFlag() == null ? "" : productPrice.getNoChargeFlag();
				double priceAmount = productPrice.getPriceAmount();
				if ( priceStatus.equals(ApplicationConstant.STATUS_DEFAULT) && priceAmount>0 ) {
					finalPrice = Double.toString(priceAmount);
				} else if (priceStatus.equals(ApplicationConstant.STATUS_DEFAULT) && comparePrice(priceAmount) 
						&& noChargeFlag.equalsIgnoreCase(ApplicationConstant.YES) ) {
					// Price status is DEFAULT.
					finalPrice = ApplicationConstant.DEFAULT_PRICE;
				} else if (priceStatus.equals(ApplicationConstant.STATUS_DEFAULT)
						&& comparePrice(priceAmount) && noChargeFlag.equalsIgnoreCase(ApplicationConstant.NO) ) {
					finalPrice = ApplicationConstant.NOT_APPLICABLE_PRICE;
				} else if (priceStatus.equals(ApplicationConstant.STATUS_TENTATIVE)
						&& comparePrice(priceAmount) && noChargeFlag.equalsIgnoreCase(ApplicationConstant.YES) ) {
					// Price status is TENTATIVE.
					finalPrice = ApplicationConstant.DEFAULT_PRICE;
				} else if (priceStatus.equals(ApplicationConstant.STATUS_TENTATIVE)
						&& comparePrice(priceAmount) && noChargeFlag.equalsIgnoreCase(ApplicationConstant.NO) ) {
					finalPrice = ApplicationConstant.NOT_APPLICABLE_PRICE;
				} else if (priceStatus.equals(ApplicationConstant.STATUS_TENTATIVE) && priceAmount>0) {
					finalPrice = priceAmount + ApplicationConstant.TENTATIVE;
				}

				if (null != productPrice.getPriceCurrency()
						&& productPrice.getPriceCurrency().equalsIgnoreCase(ApplicationConstant.USD_PRICE)) {
					priceType = ApplicationConstant.USD_PRICE + "-";
				} else if (null != productPrice.getPriceCurrency()
						&& productPrice.getPriceCurrency().equalsIgnoreCase(ApplicationConstant.CAD_PRICE)) {
					priceType = ApplicationConstant.CAD_PRICE + "-";
				}

				finalPrice = (finalPrice.equals(ApplicationConstant.EMPTY_STRING)) ? ApplicationConstant.NOT_APPLICABLE_PRICE: finalPrice;
				if (productKeyPriceMap.containsKey(productPrice.getProductKey() + "")) {
					String existingValue = productKeyPriceMap.get(productPrice.getProductKey() + "");
					productKeyPriceMap.put(productPrice.getProductKey() + "",
							existingValue + "=" + priceType + finalPrice);
				} else {
					productKeyPriceMap.put(productPrice.getProductKey() + "", priceType + finalPrice);
				}

			}
		}

		List<CatalogDTO> catalogData = createCatalogData(catalogsData, productKeyPriceMap);

		catalogsMap.put(PARAM_CATALOG_DATA, catalogData);

		long endTime = System.currentTimeMillis();
		long delta = endTime - startTime;
		logger.info("getProduct() >> times: " + delta + " ms");
		return catalogsMap;
	}

	public List<CatalogDTO> createCatalogData(List<Product> products, Map<String, String> productPriceMap) {

		List<CatalogDTO> catalogResponse = new ArrayList<>();

		if (null != products && !products.isEmpty()) {

			Iterator<Product> productIterator = products.iterator();
			while (productIterator.hasNext()) {
				Product product = productIterator.next();

				CatalogDTO productDTO = new CatalogDTO();
				productDTO.setIsbn(product.getIsbn());
				productDTO.setIsbn10(product.getIsbn10());
				productDTO.setTitle(product.getTitle());
				productDTO.setByLine(product.getByLine());
				productDTO.setPubStatusDesc(product.getPubStatusDesc());
				productDTO.setUnitHeight(product.getUnitHeight());
				productDTO.setUnitWidth(product.getUnitWidth());
				productDTO.setUnitDepth(product.getUnitDepth());
				String unitWeightStr = product.getUnitWeight();
				String unitWeight = "";
				if (unitWeightStr != null && !unitWeightStr.equalsIgnoreCase("null")
						&& unitWeightStr.trim().length() > 0) {
					unitWeight = String.format("%.2f", Float.parseFloat(unitWeightStr));
				}
				productDTO.setUnitWeight(unitWeight);
				if (null != product.getAvailablityDate()) {
					productDTO.setAvailabilityDate(product.getAvailablityDate());

				} else {
					productDTO.setAvailabilityDate("");
				}
				String usPrice = "";
				String canPrice = "";
				String productPrice = productPriceMap.get(product.getProductKey() + "");

				if (null != productPrice && !productPrice.isEmpty()) {
					if (productPrice.contains("=")) {
						String[] prices = productPrice.split("=");
						if (null != prices && prices.length > 0) {
							for (int i = 0; i < prices.length; i++) {
								if (prices[i].contains(ApplicationConstant.USD_PRICE)) {
									usPrice = prices[i].split("-")[1];
								} else if (prices[i].contains(ApplicationConstant.CAD_PRICE)) {
									canPrice = prices[i].split("-")[1];
								}
							}
						}
					} else {
						if (productPrice.contains(ApplicationConstant.USD_PRICE)) {
							usPrice = productPrice.split("-")[1];
						} else if (productPrice.contains(ApplicationConstant.CAD_PRICE)) {
							canPrice = productPrice.split("-")[1];
						}
					}
				}

				if (usPrice.contains(ApplicationConstant.TENTATIVE)) {
					usPrice = usPrice.split(ApplicationConstant.TENTATIVE)[0];
					productDTO.setIsTentative(ApplicationConstant.YES);
				}
				if (canPrice.contains(ApplicationConstant.TENTATIVE)) {
					canPrice = canPrice.split(ApplicationConstant.TENTATIVE)[0];
					productDTO.setIsTentative(ApplicationConstant.YES);
				}

				productDTO.setUsaNetPrice(usPrice);
				productDTO.setCanadaNetPrice(canPrice);
				productDTO.setUsPrice(usPrice);
				productDTO.setCanPrice(canPrice);
				productDTO.setNbrOfPages(product.getNumberOfPages());
				productDTO.setOnSaleDate(product.getOnSaleDate());
				productDTO.setPublisherDesc(product.getPublisherDesc());
				productDTO.setProductDesc(product.getProductDesc());
				productDTO.setDefaultUseableBalance(product.getDefaultUseableBalance());
				productDTO.setHotTitleFlag(product.getHotTitleFlage());
				productDTO.setPubStatusCode(product.getPubStatusCode());
				productDTO.setOnixSynopsis(product.getOnixSynopsis());
				productDTO.setHideFromOnix(product.getHideFromOnix());
				productDTO.setTeamSize(product.getTeamSize());
				productDTO.setImprint(product.getImprint());

				catalogResponse.add(productDTO);
			}

		}

		return catalogResponse;
	}

	private boolean comparePrice(double price) {
		BigDecimal pr = BigDecimal.valueOf(price);
		BigDecimal v1 = new BigDecimal(ApplicationConstant.DEFAULT_PRICE);
		BigDecimal v2 = new BigDecimal(ApplicationConstant.DEFAULT_PRICE_01);
		return pr.compareTo(v1) == 0 || pr.compareTo(v2) == 0;
	}
	
	private static final Log logger = LogFactoryUtil.getLog(CatalogUtil.class);

}
