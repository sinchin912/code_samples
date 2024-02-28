package com.hbg.rp.search.vendor;

import com.hbg.rp.model.Shipment;
import com.hbg.rp.model.ShipmentCarrier;
import com.hbg.rp.search.exceptions.ShipmentTrackingException;
import com.hbg.rp.search.handler.IShipmentTrackingHandler;
import com.hbg.rp.search.util.IShipmentTrackingUtil;
import com.hbg.rp.service.ShipmentLocalServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.PropsUtil;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * The com.hbg.rp.cache.AftershipTrackingPost is utility for posting tracking numbers on AfterShip API.
 * 
 * @author Gurminder.Singh
 *
 */
@Configuration
@EnableScheduling
public class AftershipTrackingPost implements IAftershipTrackingPost {
	private static final Log logger = LogFactoryUtil.getLog(AftershipTrackingPost.class);

	private static final String HBG_AFTERSHIP_POST_DELAY = "hbg.aftership.post.delay.ms";
	
	@Autowired
	private IShipmentTrackingHandler shipmentTrackingHandler;

	@Autowired
	private IShipmentTrackingUtil shipmentTrackingUtil;
	
	/**
	 * First gets list of tracking numbers for POST to AfterShip. 
	 * @see 
	 * com.hbg.rp.service.ShipmentLocalServiceUtil#getAllTrackingNumbersForPost()
	 * 
	 * Then for each Shipment tracking number, sends a POST request. 
	 * @see
	 * com.hbg.rp.shipment.tracking.vendor.ShippingVendor#postTrackingNumber(String, String)
	 * 
	 * @see com.hbg.rp.cache.IAftershipTrackingPost#postTrackingNumber()
	 */
	@Override
	@Scheduled(fixedRate = 1800000)
	public void postTrackingNumber() {
		long startTime = System.currentTimeMillis();
		logger.info(" AftershipTrackingPost >>>>>startTime>>>>>>>" + startTime);
		List<Shipment> shipmentList = ShipmentLocalServiceUtil.getAllTrackingNumbersForPost();
		logger.info(" AftershipTrackingPost >>>>>>>>>>>>" + shipmentList.size());
		if (null != shipmentList && !shipmentList.isEmpty()) {
			Map<String, ShipmentCarrier> shipmentCarrierMap = shipmentTrackingUtil.getAllCarrierDetails();
			try {
				for (Shipment shipment : shipmentList) {
					String trackingNumber = getPriorityTrackingNumber(shipment, shipmentCarrierMap);
					String carrierCode = shipment.getCarrierId();
					ShippingVendor shippingVendor = shipmentTrackingHandler.getShippingVendor(carrierCode);
					shippingVendor.postTrackingNumber(carrierCode, trackingNumber);
					Thread.sleep(Long.parseLong(PropsUtil.get(HBG_AFTERSHIP_POST_DELAY))); // a new property is requested to be created: CHG0037442 added hbg.aftership.post.delay.ms=100
				}
			} catch(InterruptedException ie) {
				logger.info(" InterruptedException: " + Arrays.toString(ie.getStackTrace()) + " message: " + ie.getMessage());
				ie.printStackTrace();
			} catch(ShipmentTrackingException ste) {
				logger.info(" ShipmentTrackingException: " + Arrays.toString(ste.getStackTrace()) + " message: " + ste.getMessage());
				ste.printStackTrace();
			}
		}
		long endTime = System.currentTimeMillis();
		logger.info(" AftershipTrackingPost >>>>>endtime>>>>>>>" + endTime);
	}

	/**
	 * Get priority tracking number.
	 * 
	 * @param shipment Shipment
	 * @param shipmentCarrierMap Shipment-Carrier map.
	 * @return
	 */
	private String getPriorityTrackingNumber(Shipment shipment, Map<String, ShipmentCarrier> shipmentCarrierMap) {
		logger.info("getPriorityTrackingNumber() <<");
		
		if (null != shipmentCarrierMap.get(shipment.getCarrierId())
				&& "BOL".equalsIgnoreCase(shipmentCarrierMap.get(shipment.getCarrierId()).getSupportedTrackingType())) {
			logger.info("BOL getPriorityTrackingNumber() >>");
			return shipment.getBillOfLadingNumber();
		} else if (null != shipmentCarrierMap.get(shipment.getCarrierId())
				&& "PRO".equalsIgnoreCase(shipmentCarrierMap.get(shipment.getCarrierId()).getSupportedTrackingType())) {
			logger.info(" PRO getPriorityTrackingNumber() >>");
			return shipment.getProNumber();
		} else {
			logger.info("getPriorityTrackingNumber() >>");
			return isValid(shipment.getTrackingNumber()) ? shipment.getTrackingNumber()
					: (isValid(shipment.getProNumber()) ? shipment.getProNumber() : shipment.getBillOfLadingNumber());
		}
	}

	/** Check if String is empty or null **/
	private boolean isValid(String valueObject) {
		return valueObject != null && !valueObject.trim().isEmpty();
	}

}