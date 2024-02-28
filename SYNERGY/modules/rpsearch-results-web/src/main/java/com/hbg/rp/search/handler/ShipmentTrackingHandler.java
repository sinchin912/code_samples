/**
 * 
 */
package com.hbg.rp.search.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.hbg.rp.model.ShipmentCarrier;
import com.hbg.rp.model.ShipmentCarrierVendorMapping;
import com.hbg.rp.search.vendor.ShippingVendor;
import com.hbg.rp.service.ShipmentCarrierLocalServiceUtil;
import com.hbg.rp.service.ShipmentCarrierVendorMappingLocalServiceUtil;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * The com.hbg.rp.shipment.tracking.handler.ShipmentTrackingHandler is the
 * shipment tracking handler.
 * 
 * @author ravi.kumar
 */
@Service
public class ShipmentTrackingHandler implements IShipmentTrackingHandler {

	private List<ShipmentCarrier> allShipmentCarriers = new ArrayList<>();

	private List<ShippingVendor> shippingVendorsList = null;

	private List<ShipmentCarrierVendorMapping> shipmentCarrierVendorMappings = null;

	private ShippingVendor defaultShippingVendor = null;

	@Autowired
	private ApplicationContext applicationContext;

	private static boolean isMappingsPopulated = false;

	/**
	 * Empty Constructor.
	 */
	public ShipmentTrackingHandler() { }

	/**
	 * This is the initializer method for caching the available shippingVendors so
	 * that they can be used for tracking.
	 */
	private void postProcessBeanFactory() throws SystemException {
		shippingVendorsList = new ArrayList<>(applicationContext.getBeansOfType(ShippingVendor.class).values());
		Map<String, ShippingVendor> shippingVendorsMap = applicationContext.getBeansOfType(ShippingVendor.class);
		ShippingVendor shippingVendorBean;
		Set<String> shippingVendorsKeys = shippingVendorsMap.keySet();
		for (String beanName : shippingVendorsKeys) {
			shippingVendorBean = shippingVendorsMap.get(beanName);
			shippingVendorBean.initializeVendorCarrierCodes();
			if (applicationContext.findAnnotationOnBean(beanName, Primary.class) != null) {
				defaultShippingVendor = shippingVendorBean;
			}
		}
		shippingVendorsList = new ArrayList<>(shippingVendorsMap.values());

		/**
		 * We will call DB for getting the mapping information of carriers with shipment
		 * vendor
		 */
		cacheAllShipmentCarriers();
		isMappingsPopulated = true;

	}

	/*
	 * 
	 * @see com.hbg.shipment.tracking.handler.IShipmentTrackingHandler#
	 * cacheAllShipmentCarriers()
	 */
	@Override
	public void cacheAllShipmentCarriers() throws SystemException {
		allShipmentCarriers = ShipmentCarrierLocalServiceUtil.getShipmentCarriers(0,
				ShipmentCarrierLocalServiceUtil.getShipmentCarriersCount());
		shipmentCarrierVendorMappings = ShipmentCarrierVendorMappingLocalServiceUtil.getShipmentCarrierVendorMappings(0,
				ShipmentCarrierVendorMappingLocalServiceUtil.getShipmentCarrierVendorMappingsCount());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hbg.shipment.tracking.handler.IShipmentTrackingHandler#
	 * getShippingVendor(java.lang.String)
	 */
	@Override
	public ShippingVendor getShippingVendor(String carrierCode) {
		if (!isMappingsPopulated) {
			postProcessBeanFactory();
		}
		ShippingVendor vendor = null;
		if (carrierCode != null) {
			ShipmentCarrier shipmentCarrier = findShipmentCarrier(StringUtils.upperCase(carrierCode));
			if (shipmentCarrier != null) {
				vendor = findShippingVendor(shipmentCarrier.getCarrierCode());
			}
		}
		if (vendor != null) {
			return vendor;

		}
		return defaultShippingVendor;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hbg.shipment.tracking.handler.IShipmentTrackingHandler#
	 * findShipmentCarrier(java.lang.String)
	 */
	@Override
	public ShipmentCarrier findShipmentCarrier(final String carrierCode) {
		if (!isMappingsPopulated) {
			postProcessBeanFactory();
		}
		Predicate<Object> predicate = new Predicate<Object>() {
			@Override
			public boolean test(Object object) {
				return ((ShipmentCarrier) object).getCarrierCode().equals(carrierCode);
			}
		};
		List<ShipmentCarrier> shipmentCarrierList = allShipmentCarriers.stream().filter(predicate)
				.collect(Collectors.toList());
		// logger.info("shipmentCarrierList >>>>>>>>>" +shipmentCarrierList.get(0));
		if (!shipmentCarrierList.isEmpty()) {
			return shipmentCarrierList.get(0);
		} else {
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hbg.shipment.tracking.handler.IShipmentTrackingHandler#
	 * findShippingVendor(java.lang.String)
	 */
	@Override
	public ShippingVendor findShippingVendor(final String carrierCode) {
		if (!isMappingsPopulated) {
			postProcessBeanFactory();
		}
		List<ShipmentCarrierVendorMapping> vendorCarrierMappingList = null;
		Predicate<Object> predicate = new Predicate<Object>() {
			@Override
			public boolean test(Object object) {
				return ((ShipmentCarrierVendorMapping) object).getHbgCarrierCode().equals(carrierCode);
			}
		};
		vendorCarrierMappingList = shipmentCarrierVendorMappings.stream().filter(predicate)
				.collect(Collectors.toList());
		if (vendorCarrierMappingList != null) {
			final ShipmentCarrierVendorMapping vendorCarrierMapping = vendorCarrierMappingList.get(0);
			if (vendorCarrierMapping != null) {
				Predicate<Object> predicate2 = new Predicate<Object>() {
					@Override
					public boolean test(Object object) {
						return ((ShippingVendor) object).getVendorName()
								.equals(vendorCarrierMapping.getShippingVendorName());
					}
				};
				List<ShippingVendor> shippingVendorList = shippingVendorsList.stream().filter(predicate2)
						.collect(Collectors.toList());
				return shippingVendorList.get(0);
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hbg.shipment.tracking.handler.IShipmentTrackingHandler#
	 * getCarrierTrackingUrl(java.lang.String, java.lang.String)
	 */
	@Override
	public String getCarrierTrackingUrl(String carrierCode, String trackingNumber) {
		if (!isMappingsPopulated) {
			postProcessBeanFactory();
		}
		String trackingUrl = null;
		ShipmentCarrier shipmentCarrier = findShipmentCarrier(carrierCode);
		if (null != shipmentCarrier)
			trackingUrl = StringUtils.replace(shipmentCarrier.getTrackingUrlTemplate(), ":trackingNo", trackingNumber);
		return trackingUrl;
	}

	/**
	 * Get carrier details of carrier code passed.
	 */
	@Override
	public ShipmentCarrier getCarrierDetails(String carrierCode) {
		if (!isMappingsPopulated) {
			postProcessBeanFactory();
		}
		if (carrierCode == null) {
			return null;
		} else {
			return findShipmentCarrier(carrierCode);
		}
	}

	/**
	 * Get all carrier details.
	 */
	@Override
	public List<ShipmentCarrier> getAllCarrierDetails() {
		if (!isMappingsPopulated) {
			postProcessBeanFactory();
		}
		return allShipmentCarriers;
	}

	private static final Log logger = LogFactoryUtil.getLog(ShipmentTrackingHandler.class);

}
