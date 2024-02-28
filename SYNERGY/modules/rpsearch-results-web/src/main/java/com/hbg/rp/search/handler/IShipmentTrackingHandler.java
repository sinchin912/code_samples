package com.hbg.rp.search.handler;

import com.hbg.rp.model.ShipmentCarrier;
import com.hbg.rp.search.vendor.ShippingVendor;
import com.liferay.portal.kernel.exception.SystemException;

import java.util.List;

/**
 * The com.hbg.rp.shipment.tracking.handler.IShipmentTrackingHandler is an interface for
 * {@link ShipmentTrackingHandler}.
 * 
 * @author ravi.kumar
 */
public interface IShipmentTrackingHandler {
	
	/**
	 * Cache all shipment carriers.
	 * @throws SystemException
	 */
	void cacheAllShipmentCarriers() throws SystemException;

	/**
	 * Returns a ShippingVendor object associated to the carrierCode
	 * @param carrierCode
	 * @return
	 */
	ShippingVendor getShippingVendor(String carrierCode);
	
	/**
	 * Find shipment carrier by carrier code.
	 * @param carrierCode
	 * @return
	 */
	ShipmentCarrier findShipmentCarrier(String carrierCode);

	/**
	 * Find shipping vendor by carrier code.
	 * @param carrierCode
	 * @return
	 */
	ShippingVendor findShippingVendor(String carrierCode);
	
	/**
	 * Get carrier tracking URL.
	 * @param carrierCode
	 * @param trackingNumber
	 * @return
	 */
	String getCarrierTrackingUrl(String carrierCode, String trackingNumber);
	
	/**
	 * Get carrier details.
	 * @param carrierCode
	 * @return
	 */
	ShipmentCarrier getCarrierDetails(String carrierCode);
	
	/**
	 * Get all carrier details.
	 * @return
	 */
	List<ShipmentCarrier> getAllCarrierDetails();

}