package com.hbg.rp.search.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hbg.rp.exception.NoSuchDeliveredShipmentException;
import com.hbg.rp.model.ShipmentLine;
import com.hbg.rp.model.Product;
import com.hbg.rp.model.OrderLine;
import com.hbg.rp.search.constants.ApplicationConstant;
import com.hbg.rp.search.exceptions.ShipmentTrackingException;
import com.hbg.rp.service.ShipmentLineLocalServiceUtil;
import com.hbg.rp.service.ShipmentLocalServiceUtil;
import com.liferay.portal.kernel.exception.SystemException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * The com.hbg.rp.util.ShipmentUtil is the utility for Shipments.
 * 
 * @author ravi.kumar
 */
@Component
public class ShipmentUtil implements IShipmentUtil {

	/**
	 * Empty Constructor.
	 */
	public ShipmentUtil() {}
	
	@Autowired
	private ICommonUtil commonUtil;

	/**
	 * Gets the shipments.
	 * @see com.hbg.rp.util.IShipmentUtil#getShipments(java.util.Map, java.lang.String)
	 */
	@Override
	public Map<String, Object> getShipments(Map<String, String> shipmentSearchCriteriaMap, String searchPresent, long extRepId,boolean hasAllTerritories)
			throws SystemException {
		logger.info("getShipments() <<");
		if ("true".equals(searchPresent)) {
			shipmentSearchCriteriaMap.put(ApplicationConstant.PARAM_SHIP_LIMIT,
					ApplicationConstant.PAGE_SIZE.toString());
		} else {
			shipmentSearchCriteriaMap.put(ApplicationConstant.PARAM_SHIP_LIMIT,
					ApplicationConstant.PARAM_RECENT_RECORDS_LIMIT.toString());
		}

//		for (Map.Entry<String, String> entry : shipmentSearchCriteriaMap.entrySet()) {
//			logger.info("search criteria key: " + entry.getKey() + " value: " + entry.getValue());
//		}

		long startTime = System.currentTimeMillis();
		Map<String, Object> shipmentsMap = ShipmentLocalServiceUtil.getShipments(shipmentSearchCriteriaMap,extRepId,hasAllTerritories);

		long endTime = System.currentTimeMillis();
		long delta = endTime - startTime;
		logger.info("getShipments() >> time: " + delta + " ms");
		return shipmentsMap;
	}
	
	/**
	 * @see IShipmentUtil#getShipmentLines(List, String)
	 */
	@Override
	public List<ShipmentLine> getShipmentLines(List<Long> shipmentHeaderIds, String itemCode,long extRepId,boolean hasAllTerritories) throws SystemException {
		List<ShipmentLine> defaultShipmentLineList = new ArrayList<>();
		List<ShipmentLine> shipmentLineList  = new ArrayList<>();
		if (null != itemCode && !"".equals(itemCode) && null != shipmentHeaderIds && !shipmentHeaderIds.isEmpty()) {
			shipmentLineList = ShipmentLineLocalServiceUtil.getShipmentLinesForShipmentHeaders(shipmentHeaderIds,
					itemCode,extRepId,hasAllTerritories);
			//shipmentLineList = addLongTitle(defaultShipmentLineList); //NRP 2746 Release 1.6: Change Short Title to Title on site and reports
		}
		return shipmentLineList;
	}

	/**
	 * @see com.hbg.rp.util.IShipmentUtil#getShipmentLines(java.util.Map)
	 */
	@Override
	public List<ShipmentLine> getShipmentLines(Map<String, String> criteria,long extRepId,boolean hasAllTerritories) throws SystemException {
		List<ShipmentLine> shipmentLineList = ShipmentLineLocalServiceUtil.getShipmentLines(criteria,extRepId,hasAllTerritories);
		ShipmentLine shipmentLine = null;
		int rowIndex = 0;
		List<ShipmentLine> finalShipmentLines = new ArrayList<>();
		if (null == shipmentLineList || shipmentLineList.isEmpty()) {
			// In case of empty shipmentLineList, return empty finalShipmentLines.
			return finalShipmentLines;
		}
		long orderLineKey = 0;
		for(ShipmentLine currentline: shipmentLineList){
			if(orderLineKey != currentline.getOrderLineId()){
				orderLineKey = currentline.getOrderLineId();
				rowIndex = finalShipmentLines.size();
				shipmentLine = (ShipmentLine) currentline.clone();
				finalShipmentLines.add(currentline);
				
			}else{
				currentline.setShipmentLineId(-11);
				if(null != shipmentLine){
					shipmentLine.setShipmentLineId(-11);
					finalShipmentLines.add((ShipmentLine) shipmentLine.clone());
					shipmentLine = null;
				}
				finalShipmentLines.add(currentline);
				long totalShippedQty = currentline.getShippedQuantity() + finalShipmentLines.get(rowIndex).getShippedQuantity();
				finalShipmentLines.get(rowIndex).setShipmentLineId(-1);
				finalShipmentLines.get(rowIndex).setShippedQuantity(totalShippedQty);
			}
		}
		//List<ShipmentLine> shipmentLineListWithLongTitle = addLongTitle(finalShipmentLines);
//		List<ShipmentLine> shipmentLineListWithLongTitle = new ArrayList<>();
//		if (!shippedOrderLines.isEmpty()) {
//			shipmentLineListWithLongTitle = addLongTitleForShipmentLine(finalShipmentLines, shippedOrderLines);
//		} else {
//			shipmentLineListWithLongTitle = addLongTitle(finalShipmentLines);
//		}
		return finalShipmentLines;
	}
	
	
	private static final Log logger = LogFactoryUtil.getLog(ShipmentUtil.class);

	@Override
	public String getShipmentTrackingDetails(String shipmentCarrier, String trackingNumber, String orderHeaderId)
			throws ShipmentTrackingException, NumberFormatException, JsonProcessingException,
			NoSuchDeliveredShipmentException, SystemException {
		return null;
	}
	
	/**
	 * 
	 * @param invoiceLines
	 * @return
	 * @throws SystemException
	 */
	private List<ShipmentLine> addLongTitle(List<ShipmentLine> shipmentLines) throws SystemException {
		if (!shipmentLines.isEmpty()) {
			List<String> isbnList = new ArrayList<>();
			for (ShipmentLine shipmentLine : shipmentLines) {
				isbnList.add(shipmentLine.getIsbn());
			}
			Map<String, Product> productTitleMap = commonUtil.getProductTitleMap(isbnList);
			for (ShipmentLine shipmentLine : shipmentLines) {
				if(shipmentLine.getIsbn()!= null && productTitleMap.get(shipmentLine.getIsbn())!= null) {
					if(productTitleMap.get(shipmentLine.getIsbn()).getTitle()!= null) {
						shipmentLine.setShortTitle(productTitleMap.get(shipmentLine.getIsbn()).getTitle());
					}
					else {
						shipmentLine.setShortTitle("");
					}
					if(productTitleMap.get(shipmentLine.getIsbn()).getByLine()!= null) {
						shipmentLine.setShortAuthor(productTitleMap.get(shipmentLine.getIsbn()).getByLine()); //NRP-2876 Change Short Author value to original Author in NRP
					}
					else {
						shipmentLine.setShortAuthor("");
					}
				}
				else {
					shipmentLine.setShortTitle("");
					shipmentLine.setShortAuthor("");
				}
			}
		}
		return shipmentLines;
	}
}
