package com.hbg.rp.search.vendor;

import com.hbg.rp.search.dto.Tracking;
import com.hbg.rp.search.exceptions.ShipmentTrackingException;

/**
 * The com.hbg.rp.shipment.tracking.vendor.ShippingVendor is the interface for Shipping Vendor.
 * 
 * @author ravi.kumar
 */
public interface ShippingVendor {
	
	/**
	 * Get vendor name.
	 * @return
	 */
	String getVendorName();
	
	/**
	 * Set vendor name.
	 * @param vendorName
	 */
	void setVendorName(String vendorName);

	/** Get tracking **/
	public Tracking getTracking(Tracking tracking) throws ShipmentTrackingException;
	
	/** Get tracking based on carrier code and tracking number **/
	public Tracking getTracking(String carrierCode, String trackingNum) throws ShipmentTrackingException;
	
	/** Convert carrier code **/
	public String convertCarrierCode(String carrierCode);
	
	/** Initialize vendor carrier codes **/
	public void initializeVendorCarrierCodes();
	
	/** Get tracking URL **/
	String getTrackingURL();
	
	/** Set tracking URL **/
	void setTrackingURL(String trackingURL);
	
	/** Post tracking number **/
	void postTrackingNumber(String carrierCode, String trackingNum) throws ShipmentTrackingException;

}
