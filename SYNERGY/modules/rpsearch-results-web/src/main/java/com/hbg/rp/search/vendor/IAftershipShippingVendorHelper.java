package com.hbg.rp.search.vendor;

import com.hbg.rp.search.exceptions.ShipmentTrackingException;
import com.hbg.rp.search.dto.Tracking;

import aftership.classes.ConnectionAPI;

/**
 * 
 * @author iagoupov
 * IAftershipShippingVendorHelper interface.
 */
public interface IAftershipShippingVendorHelper {

	/**
	 * Post tracking number. 
	 * via {@link ConnectionAPI#postTracking(aftership.classes.Tracking)}
	 * @param tracking
	 * @return
	 * @throws ShipmentTrackingException
	 */
	void postTrackingNumber(Tracking tracking, String carrier) throws ShipmentTrackingException;

	/**
	 * Post tracking.
	 * @param tracking
	 * @param connection
	 * @param tempTrack
	 * @return
	 * @throws ShipmentTrackingException
	 */
	Tracking postTracking(Tracking tracking, ConnectionAPI connection, aftership.classes.Tracking tempTrack)
			throws ShipmentTrackingException;

	/**
	 * Convert aftership to tracking.
	 * @param track
	 * @param tracking
	 * @return
	 */
	Tracking convertAftershipToTracking(aftership.classes.Tracking track, Tracking tracking);

}