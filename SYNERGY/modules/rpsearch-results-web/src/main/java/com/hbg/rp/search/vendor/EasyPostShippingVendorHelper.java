package com.hbg.rp.search.vendor;

import com.easypost.model.Tracker;
import com.easypost.model.TrackingDetail;
import com.hbg.rp.search.dto.Tracking;
import com.hbg.rp.search.dto.TrackingEvent;

import java.util.ArrayList;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * The com.hbg.rp.shipment.tracking.vendor.helper.EasyPostShippingVendorHelper is the 
 * implementation class for {@link IEasyPostShippingVendorHelper}.
 * @author iagoupov
 */
@Component
public class EasyPostShippingVendorHelper implements IEasyPostShippingVendorHelper {
	private final Log logger = LogFactoryUtil.getLog(this.getClass());
	
	@Autowired
	private IEasyPostShippingVendorHelperUtil easyPostShippingVendorHelperUtil; 
	
	/**
	 * Empty constructor.
	 */
	public EasyPostShippingVendorHelper() {
		logger.info("EasyPostShippingVendorHelper() <<");
		logger.info("EasyPostShippingVendorHelper() >>");			
	}
	
	/**
	 * Convert tracker to tracking.
	 * @param tracker
	 * @param tracking
	 * @return
	 */
	@Override
	public Tracking convertTrackerToTracking(Tracker tracker, Tracking tracking) {
		tracking.setStatus(easyPostShippingVendorHelperUtil.convertTrackingStatus(tracker.getStatus()));
		tracking.setScheduledDeliveryDate(tracker.getEstDeliveryDate());
		
		ArrayList<TrackingEvent> events = new ArrayList<>();
		for (TrackingDetail trackingDetail : tracker.getTrackingDetails()) {
			TrackingEvent trackingEvent = new TrackingEvent();
			trackingEvent.setEventDate(trackingDetail.getDatetime());
			trackingEvent.setLocation(easyPostShippingVendorHelperUtil.locationToString(trackingDetail.getTrackingLocation()));
			trackingEvent.setMessage(trackingDetail.getMessage());
			trackingEvent.setStatus(easyPostShippingVendorHelperUtil.convertTrackingStatus(trackingDetail.getStatus()));
			events.add(trackingEvent);
			
			if ("delivered".equals(trackingDetail.getStatus())) {
				tracking.setDeliveryDate(trackingDetail.getDatetime());
			}
		}
		
		tracking.setEvents(events);
		
		tracking.setWeight(String.valueOf(tracker.getWeight()));
		tracking.setShipmentCategory(tracker.getCarrierDetail().getContainerType());
		tracking.setReceiverName(tracker.getSignedBy());
		tracking.setUrl(tracker.getPublicUrl());
		
		return tracking;
	}
	
	
}
