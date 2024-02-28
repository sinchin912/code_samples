package com.hbg.rp.search.vendor;

import com.easypost.model.TrackingLocation;
import com.hbg.rp.search.dto.TrackingStatus;

/**
 * 
 * @author ravi.kumar
 */

public interface IEasyPostShippingVendorHelperUtil {
	
	/**
	 * Get Tracking Status enum object from tracking status string.
	 * @param status
	 * @return
	 */
	TrackingStatus convertTrackingStatus(String status);

	/**
	 * Convert tracking location to string from {@link TrackingLocation}.
	 * @param location Location to be converted
	 * @return String 
	 */
	String locationToString(TrackingLocation location);

}