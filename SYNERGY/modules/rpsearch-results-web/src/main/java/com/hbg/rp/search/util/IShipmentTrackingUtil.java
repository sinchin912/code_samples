/**
 * 
 */
package com.hbg.rp.search.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hbg.rp.model.ShipmentCarrier;
import com.hbg.rp.search.dto.Tracking;
import com.hbg.rp.search.exceptions.ShipmentTrackingException;
import com.liferay.portal.kernel.exception.SystemException;

import java.util.Map;

/**
 * @author ravi.kumar
 *
 */
public interface IShipmentTrackingUtil {
	
	/**
	 * Gets tracking information for the tracking number and the corresponding carrier
	 * @param carrier Carrier associated with the tracking number
	 * @param trackingNumber Carrier associated with the tracking number
	 * @return
	 * @throws ShipmentTrackingException
	 */
	public Tracking getTracking(String carrier, String trackingNumber) throws ShipmentTrackingException;

	/**
	 * Saves tracking information for the delivered shipment in the database
	 * @param orderHeaderId Order header identifier associated with the shipment
	 * @param trackingDetails  Tracking details received from the Shipping Vendor for a tracking number
	 * @throws SystemException 
	 * @throws ShipmentTrackingException
	 */
	void saveDeliveredShipment(Long orderHeaderId, Tracking trackingDetails) throws SystemException; 

	Tracking getDeliveredShipment(Long orderHeaderId, String trackingNumber) throws SystemException; 
	
	String getShipmentTrackingDetailsJson(String shipmentCarrier, String trackingNumber, String orderHeaderId) throws ShipmentTrackingException, NumberFormatException, JsonProcessingException, SystemException ;
	
	Tracking getShipmentTrackingDetails(String shipmentCarrier, String trackingNumber, String orderHeaderId, String shipDate) throws ShipmentTrackingException, NumberFormatException, SystemException ;

	String getCarrierTrackingUrl(String carrierCode, String trackingNumber);

	ShipmentCarrier getCarrierDetails(String carrierCode);
	
	Map<String, ShipmentCarrier> getAllCarrierDetails();
	
}
