/**
 * 
 */
package com.hbg.rp.search.vendor;

import com.hbg.rp.search.constants.ApplicationConstant;
import com.hbg.rp.search.dto.Tracking;
import com.hbg.rp.search.dto.TrackingEvent;
import com.hbg.rp.search.dto.TrackingStatus;
import com.hbg.rp.search.exceptions.ShipmentTrackingException;
import com.hbg.rp.search.util.IDateUtil;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.Executors;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import aftership.classes.AftershipAPIException;
import aftership.classes.Checkpoint;
import aftership.classes.ConnectionAPI;
import aftership.enums.StatusTag;

/**
 * @author iagoupov
 * AftershipShippingVendorHelper class.
 */
@Component
public class AftershipShippingVendorHelper implements IAftershipShippingVendorHelper  {
	private final Log logger = LogFactoryUtil.getLog(this.getClass());
	
	private static final String ERROR_PARSE = "ERROR_PARSE";
	private static final String ERROR_IO = "ERROR_IO";
	private static final String ERROR_JSON = "ERROR_JSON";
	private static final String NOT_AVAILABLE = "Not Available";
	
	@Autowired
	private IDateUtil dateUtil;
	
	@Autowired
	private IAftershipShipConnectionAPI aftershipShipConnectionAPI;
	
	/**
	 * Constructor AftershipShippingVendorHelper.
	 */
	public AftershipShippingVendorHelper() { }

	/**
	 * Post tracking number. 
	 * via {@link ConnectionAPI#postTracking(aftership.classes.Tracking)}
	 * @param tracking
	 * @return
	 * @throws ShipmentTrackingException
	 */
	@Override
	public void postTrackingNumber(final Tracking tracking, final String carrier) throws ShipmentTrackingException {
		logger.info(" postTrackingNumber() << ");
		
		final ConnectionAPI connection = aftershipShipConnectionAPI.getAftershipShipConnectionAPI();
		final aftership.classes.Tracking tempTrack = new aftership.classes.Tracking(tracking.getTrackingNumber());
		tempTrack.setSlug(carrier);

		Executors.newSingleThreadExecutor().execute(new Runnable() {
		    @Override 
		    public void run() {
		    	try {
		    	    connection.postTracking(tempTrack);
				} catch (JSONException je) {
					je.printStackTrace();
					logger.info("JSONException: " + Arrays.toString(je.getStackTrace()));
				} catch (AftershipAPIException ae) {
					ae.printStackTrace();
					if (ae.getMessage().contains("4003"))
						logger.info("Tracking already exists with the following data:" + ae.getMessage());
					else
						logger.info("Error occured while posting tracking data >>>>>>>>>>>> Carrier: "
								+ tracking.getCarrierCode() + " Tracking Number: " + tracking.getTrackingNumber(), ae);
				} catch (IOException ioe) {
					ioe.printStackTrace();
					logger.info("IOException: " + Arrays.toString(ioe.getStackTrace()));
				} catch (ParseException pe) {
					pe.printStackTrace();
					logger.info("ParseException: " + Arrays.toString(pe.getStackTrace()));
				}
		    }
		});
			
		logger.info(" postTrackingNumber() >> ");
	}

	/**
	 * Post tracking.
	 * @param tracking
	 * @param connection
	 * @param tempTrack
	 * @return
	 * @throws ShipmentTrackingException
	 */
	@Override
	public Tracking postTracking(Tracking tracking, ConnectionAPI connection, aftership.classes.Tracking tempTrack)
			throws ShipmentTrackingException {
		aftership.classes.Tracking track;
		try {
			connection.postTracking(tempTrack);
			track = connection.getTrackingByNumber(tempTrack);
			return this.convertAftershipToTracking(track, tracking);
		} catch (JSONException je) {
			je.printStackTrace();
			throw new ShipmentTrackingException(ERROR_JSON, je);
		} catch (AftershipAPIException ae) {
			ae.printStackTrace();
			throw new ShipmentTrackingException("ERROR_API", ae);
		} catch (IOException ioe) {
			ioe.printStackTrace();
			throw new ShipmentTrackingException(ERROR_IO, ioe);
		} catch (ParseException pe) {
			pe.printStackTrace();
			throw new ShipmentTrackingException(ERROR_PARSE, pe);
		}
	}
	
	/**
	 * Convert aftership to tracking.
	 * @param track
	 * @param tracking
	 * @return
	 */
	@Override
	public Tracking convertAftershipToTracking(aftership.classes.Tracking track, Tracking tracking) {
		tracking.setStatus(this.convertTrackingStatus(track.getTag()));
		tracking.setScheduledDeliveryDate(dateUtil.stringToDate(track.getExpectedDelivery()));
		ArrayList<TrackingEvent> events = new ArrayList<>();
		if (track.getCheckpoints() != null) {
			for (Checkpoint checkpoint : track.getCheckpoints()) {
				events.add(this.convertCheckpointToTrackingEvent(checkpoint));
				if (checkpoint.getTag().equalsIgnoreCase(ApplicationConstant.TRACKING_STATUS_DELIVERED)) {
					tracking.setDeliveryDate(dateUtil.stringToDate(checkpoint.getCheckpointTime()));
				}
			}
		}
		tracking.setEvents(events);
		tracking.setShipmentCategory(track.getShipmentType());
		tracking.setReceiverName(track.getSignedBy());
		if (!events.isEmpty()) {
			tracking.setLocation(events.get(events.size() - 1).getLocation());
		}
		return tracking;
	}
	
	/**
	 * Convert tracking status.
	 * @param status
	 * @return
	 */
	private TrackingStatus convertTrackingStatus(StatusTag status) {
		switch (status) {
		case AttemptFail:
			return TrackingStatus.FAILURE;
		case Delivered:
			return TrackingStatus.DELIVERED;
		case Exception:
			return TrackingStatus.ERROR;
		case Expired:
			return TrackingStatus.EXPIRED;
		case InfoReceived:
		case Pending:
			return TrackingStatus.PRE_TRANSIT;
		case InTransit:
			return TrackingStatus.IN_TRANSIT;
		case OutForDelivery:
			return TrackingStatus.OUT_FOR_DELIVERY;
		default:
			return TrackingStatus.UNKNOWN;
		}
	}
	
	/**
	 * Convert checkpoint to tracking event.
	 * @param checkpoint
	 * @return
	 */
	private TrackingEvent convertCheckpointToTrackingEvent(Checkpoint checkpoint) {
		TrackingEvent trackingEvent = new TrackingEvent();
		trackingEvent.setEventDate(dateUtil.stringToDate(checkpoint.getCheckpointTime()));
		trackingEvent.setEventDateString(checkpoint.getCheckpointTime());
		trackingEvent.setLocation(
				StringUtils.isNotBlank(checkpoint.getLocation()) ? checkpoint.getLocation() : NOT_AVAILABLE);
		trackingEvent.setMessage(checkpoint.getMessage());
		if(null != checkpoint.getTag()) {
			try {
				trackingEvent.setStatus(this.convertTrackingStatus(StatusTag.valueOf(checkpoint.getTag())));
			} catch (Exception ex) {
				trackingEvent.setStatus(this.convertTrackingStatus(StatusTag.valueOf("InTransit")));
			}
		}else {
			trackingEvent.setStatus(this.convertTrackingStatus(StatusTag.valueOf("InTransit")));
		}
		return trackingEvent;
	}
}
