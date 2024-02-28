/**
 * 
 */
package com.hbg.rp.search.vendor;

import com.hbg.rp.model.ShipmentCarrierVendorMapping;
import com.hbg.rp.service.ShipmentCarrierVendorMappingLocalServiceUtil;
import com.liferay.portal.kernel.exception.SystemException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

/**
 * 
 * @author ravi.kumar
 */

public abstract class BaseShipmentTrackingVendor implements ShippingVendor {
	private final Log logger = LogFactoryUtil.getLog(this.getClass());
	
	private Map<String, String> vendorCarrierCodes = new HashMap<>();
	private String trackingURL;
	private String vendorName;

	/**
	 * @return the vendorName
	 */
	@Override
	public String getVendorName() {
		return vendorName;
	}

	/**
	 * @param vendorName the vendorName to set
	 */
	@Override
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	
	/**
	 * Set shipment carrier vendor mappings.
	 */
	public void setShipmentCarrierVendorMappings() {
		logger.info("*************** AftershipShippingVendor Initilized");
		List<ShipmentCarrierVendorMapping> shipmentCarrierVendorMappings = null;
		try {
			shipmentCarrierVendorMappings = ShipmentCarrierVendorMappingLocalServiceUtil.findByShipmentCarrierVendorMappingsForVendor(getVendorName());
			if(shipmentCarrierVendorMappings!=null) {
				for (ShipmentCarrierVendorMapping shipmentCarrierVendorMapping : shipmentCarrierVendorMappings) {
					getVendorCarrierCodes().put(shipmentCarrierVendorMapping.getHbgCarrierCode(), shipmentCarrierVendorMapping.getVendorCarrierCode());
				}
			}
		} catch (SystemException e) {
			e.printStackTrace();
			logger.info(e.getMessage(), e);
		}
		logger.info("*************** AftershipShippingVendor Initilized");
	}
	
	/**
	 * Convert carrier code
	 */
	@Override
	public String convertCarrierCode(String carrierCode) {
		if(StringUtils.isNotBlank(carrierCode) && 
				StringUtils.isNotBlank(getVendorCarrierCodes().get(StringUtils.upperCase(carrierCode))) ){
			return getVendorCarrierCodes().get(StringUtils.upperCase(carrierCode));
		}
		return carrierCode;
	}
	
	/**
	 * Get vendor carrier codes.
	 * @return
	 */
	public Map<String, String> getVendorCarrierCodes() {
		return vendorCarrierCodes;
	}

	/**
	 * Get the tracking URL.
	 * @return the trackingURL
	 */
	@Override
	public String getTrackingURL() {
		return trackingURL;
	}

	/**
	 * Set the tracking URL.
	 * @param trackingURL the trackingURL to set
	 */
	@Override
	public void setTrackingURL(String trackingURL) {
		this.trackingURL = trackingURL;
	}
	
	

}
