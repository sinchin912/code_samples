package com.hbg.rp.search.util;

import static java.util.Calendar.MINUTE;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hbg.rp.exception.NoSuchDeliveredShipmentException;
import com.hbg.rp.model.DeliveredShipment;
import com.hbg.rp.model.ShipmentCarrier;
import com.hbg.rp.search.dto.Tracking;
import com.hbg.rp.search.dto.TrackingStatus;
import com.hbg.rp.search.exceptions.ShipmentTrackingException;
import com.hbg.rp.search.handler.IShipmentTrackingHandler;
import com.hbg.rp.search.vendor.ShippingVendor;
import com.hbg.rp.service.DeliveredShipmentLocalServiceUtil;
import com.liferay.portal.kernel.exception.SystemException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.osgi.service.component.annotations.Component;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The com.hbg.rp.shipment.tracking.service.ShipmentTrackingServiceImpl is the implementation of 
 * {@link IShipmentTrackingService}.
 * 
 * @author ravi.kumar
 */
@Service
public class ShipmentTrackingUtil implements IShipmentTrackingUtil {

	private static final String L_L_IS_NOT_SUPPORTED = "Tracking status for L & L Shipping is not supported yet.";
	private static final String TRACKING_DETAILS_NOT_AVAILABLE = "Tracking details are not available.";
	
	@Autowired
	private IShipmentTrackingHandler shipmentTrackingHandler;
	
	@Autowired
	private ShippingVendor shippingVendor;

	/**
	 * Empty Constructor.
	 */
	public ShipmentTrackingUtil() {}


	/**
	 * Get all carrier details.
	 */
	@Override
	public Map<String, ShipmentCarrier> getAllCarrierDetails() {
		Map<String, ShipmentCarrier> allCarriers = new HashMap<>();
		List<ShipmentCarrier> carriers = shipmentTrackingHandler.getAllCarrierDetails();
		for (ShipmentCarrier shipmentCarrier : carriers) {
			allCarriers.put(shipmentCarrier.getCarrierCode(), shipmentCarrier);
		}
		return allCarriers;
	}
	
	/**
	 * Get shipment tracking details.
	 * @param shipmentCarrier
	 * @param trackingNumber
	 * @param orderHeaderId
	 * @param shipDate
	 */
	@Override
	public Tracking getShipmentTrackingDetails(String shipmentCarrier, String trackingNumber, String orderHeaderId,
			String shipDate) throws ShipmentTrackingException, SystemException {
		
		Tracking trackingDetails = null;
		try{
			trackingDetails = getDeliveredShipment(Long.valueOf(orderHeaderId), trackingNumber);
		}catch(Exception e){
			trackingDetails = null;
		}
		boolean noTrackingRequired = false;
		if (null != shipDate && !shipDate.isEmpty()) {
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy");
			try {
				noTrackingRequired = isBeforTime(-172800, sdf.parse(shipDate));
			} catch (ParseException e) {
				logger.info("Error occured while parsing shipped date >>>>>>>>>", e);
			}
		}

		if (trackingDetails == null) {
			// If shipping date is 120 days older, no need to make vendor call
			if (noTrackingRequired) {
				trackingDetails = new Tracking();
				trackingDetails.setMessage("Sorry, tracking information for this shipment is no longer available from the carrier.");	
			} else {
				//Special case L&L : do not call aftership -> once aftership support L&L, use as else
				if ("LLFQ".equals(shipmentCarrier)) {
					trackingDetails = new Tracking();
					trackingDetails.setTrackingNumber(trackingNumber);
					ShipmentCarrier sc = getCarrierDetails(shipmentCarrier);
					if(sc != null) {
						trackingDetails.setMessage("Tracking status for '"+sc.getCarrierName()+"' is not supported yet. "
								+ "Please visit "+sc.getTrackingUrlTemplate()+" and search tracking with Tracking # "+trackingNumber);
						trackingDetails.setUrl(sc.getTrackingUrlTemplate());
					} else {
						//TODO: confirm with Heather the exact message.
						trackingDetails.setMessage(L_L_IS_NOT_SUPPORTED);
						logger.info("The value of shipment carrier is: " + sc);
					} 
					
				} else {
					// to fetch tracking details
					trackingDetails = getTracking(shipmentCarrier, trackingNumber);
					if (trackingDetails == null) {
						trackingDetails = new Tracking();
						trackingDetails.setMessage(TRACKING_DETAILS_NOT_AVAILABLE);
					} else if (trackingDetails.getStatus().equals(TrackingStatus.DELIVERED)) {
						saveDeliveredShipment(Long.valueOf(orderHeaderId), trackingDetails);
					}
				}
			}
		}
		boolean delivered120DaysOldLnL = trackingDetails.getStatus().equals(TrackingStatus.DELIVERED) 
				&& noTrackingRequired && "LLFQ".equals(shipmentCarrier);
		if ( !StringUtils.isEmpty(shipmentCarrier) && !StringUtils.isEmpty(trackingDetails.getTrackingNumber())
				&& !delivered120DaysOldLnL) {
			trackingDetails.setUrl(getCarrierTrackingUrl(shipmentCarrier, trackingDetails.getTrackingNumber()));
		}
		return trackingDetails;
	}
	
	/**
	 * Check is before time the date passed to it after adding minutes passed to current instance.
	 * @param minutes To be added to current instance
	 * @param dateToCompare 
	 * @return
	 */
	private static boolean isBeforTime(int minutes, Date dateToCompare) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(MINUTE, minutes);
		return dateToCompare.compareTo(calendar.getTime()) < 0;
	}
	
	@Override
	public Tracking getTracking(String carrier, String trackingNumber)
			throws ShipmentTrackingException {
		shippingVendor = shipmentTrackingHandler.getShippingVendor(carrier);
		return shippingVendor.getTracking(carrier, trackingNumber);
	}

	@Override
	public void saveDeliveredShipment(Long orderHeaderId, Tracking trackingDetails)
			throws SystemException {
		DeliveredShipmentLocalServiceUtil.createDeliveredShipment(orderHeaderId, trackingDetails.getTrackingNumber(),
				trackingDetails.getDeliveryDate(), new Date());
	}

	@Override
	public Tracking getDeliveredShipment(Long orderHeaderId, String trackingNumber)
			throws SystemException {
		Tracking trackingDetails = null;
		try {
			DeliveredShipment deliveredShipment = DeliveredShipmentLocalServiceUtil
					.findByDeliveredShipment(orderHeaderId, trackingNumber);
			if (deliveredShipment != null) {
				trackingDetails = new Tracking();
				trackingDetails.setStatus(TrackingStatus.DELIVERED);
				trackingDetails.setDeliveryDate(deliveredShipment.getDeliveredDate());
				trackingDetails.setTrackingNumber(deliveredShipment.getTrackingNumber());
			}
		} catch (NoSuchDeliveredShipmentException e) {
			logger.info("No shipment found for provided tracking number >>>>>> " + trackingNumber);//, e);
		}
		return trackingDetails;
	}
	
	@Override
	public ShipmentCarrier getCarrierDetails(String carrierCode) {
		return shipmentTrackingHandler.getCarrierDetails(carrierCode);
	}

	@Override
	public String getCarrierTrackingUrl(String carrierCode, String trackingNumber) {
		return shipmentTrackingHandler.getCarrierTrackingUrl(carrierCode, trackingNumber);
	}

	private static final Log logger = LogFactoryUtil.getLog(ShipmentTrackingUtil.class);

	@Override
	public String getShipmentTrackingDetailsJson(String shipmentCarrier, String trackingNumber, String orderHeaderId)
			throws com.hbg.rp.search.exceptions.ShipmentTrackingException, NumberFormatException,
			JsonProcessingException, SystemException {
		return null;
	}
	
}
