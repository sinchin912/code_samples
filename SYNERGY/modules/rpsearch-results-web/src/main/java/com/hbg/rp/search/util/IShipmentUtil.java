package com.hbg.rp.search.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hbg.rp.exception.NoSuchDeliveredShipmentException;
import com.hbg.rp.model.ShipmentLine;
import com.hbg.rp.model.OrderLine;
import com.hbg.rp.search.exceptions.ShipmentTrackingException;
import com.liferay.portal.kernel.exception.SystemException;

import java.util.List;
import java.util.Map;

/**
 * The com.hbg.rp.util.IShipmentUtil is an interface for {@link ShipmentUtil}.
 * 
 * @author ravi.kumar
 */
public interface IShipmentUtil {

	/**
	 * Get the shipments.
	 */
	Map<String, Object> getShipments(Map<String, String> shipmentSearchCriteriaMap, String searchPresent, long extRepId,boolean hasAllTerritories)
			throws SystemException;
	
	/**
	 * Get shipment lines.
	 * @param criteria
	 * @return
	 * @throws SystemException
	 */
	List<ShipmentLine> getShipmentLines(Map<String, String> criteria, long extRepId,boolean hasAllTerritories) throws SystemException;
	
	/**
	 * Get the shipment tracking details.
	 * @param shipmentCarrier
	 * @param trackingNumber
	 * @param orderHeaderId
	 * @return
	 * @throws ShipmentTrackingException
	 * @throws NumberFormatException
	 * @throws JsonProcessingException
	 * @throws NoSuchDeliveredShipmentException
	 * @throws SystemException
	 */
	String getShipmentTrackingDetails(String shipmentCarrier, String trackingNumber, String orderHeaderId)
			throws ShipmentTrackingException, NumberFormatException, JsonProcessingException, NoSuchDeliveredShipmentException, SystemException;
	
	/**
	 * Get shipment lines.
	 * @param shipmentHeaderIds
	 * @param itemCode
	 * @return
	 * @throws SystemException
	 */
	public List<ShipmentLine> getShipmentLines(List<Long> shipmentHeaderIds, String itemCode, long extRepId,boolean hasAllTerritories) throws SystemException;

}