package com.hbg.rp.search.vendor;

import com.easypost.model.Tracker;
import com.hbg.rp.search.dto.Tracking;

/**
 * 
 * @author ravi.kumar
 */

public interface IEasyPostShippingVendorHelper {
	
	/**
	 * Convert tracker to tracking.
	 * @param tracker
	 * @param tracking
	 * @return
	 */
	Tracking convertTrackerToTracking(Tracker tracker, Tracking tracking);

}