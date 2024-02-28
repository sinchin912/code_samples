/**
 * 
 */
package com.hbg.rp.search.vendor;

import com.easypost.model.TrackingLocation;
import com.hbg.rp.search.dto.TrackingStatus;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import org.springframework.stereotype.Component;

/**
 * The com.hbg.rp.shipment.tracking.vendor.helper.util.EasyPostShippingVendorHelperUtil is the 
 * implementation class for {@link IEasyPostShippingVendorHelperUtil}
 * @author iagoupov
 */
@Component
public class EasyPostShippingVendorHelperUtil implements IEasyPostShippingVendorHelperUtil {
	private final Log logger = LogFactoryUtil.getLog(this.getClass());
	
	/**
	 * Empty constructor.
	 */
	public EasyPostShippingVendorHelperUtil() {
		logger.info("EasyPostShippingVendorHelperUtil() <<");
		logger.info("EasyPostShippingVendorHelperUtil() >>");	
	}
	
	/**
	 * Get Tracking Status enum object from tracking status string.
	 * @param status
	 * @return
	 */
	@Override
	public TrackingStatus convertTrackingStatus(String status) {
		
			if("available_for_pickup".equals(status)) {
				return TrackingStatus.AVAILABLE_FOR_PICKUP;
			}else if("cancelled".equals(status)) {
				return TrackingStatus.CANCELLED;
			}else if("delivered".equals(status)) {
				return TrackingStatus.DELIVERED;
			}else if("error".equals(status)) {
				return TrackingStatus.ERROR;
			}else if("failure".equals(status)) {
				return TrackingStatus.FAILURE;
			}else if("in_transit".equals(status)) {
				return TrackingStatus.IN_TRANSIT;
			}else if("out_for_delivery".equals(status)) {
				return TrackingStatus.OUT_FOR_DELIVERY;
			}else if("pre_transit".equals(status)) {
				return TrackingStatus.PRE_TRANSIT;
			}else if("return_to_sender".equals(status)) {
				return TrackingStatus.RETURN;
			}else{
				return TrackingStatus.UNKNOWN;
			}
		
	}
	
	/**
	 * Convert tracking location to string from {@link TrackingLocation}.
	 * @param location Location to be converted
	 * @return String 
	 */
	@Override
	public String locationToString(TrackingLocation location) {
		if (location.getCity() == null && location.getState() == null)
			return "";
		else
			return String.format("%s, %s", location.getCity(), location.getState());
	}
	

}
