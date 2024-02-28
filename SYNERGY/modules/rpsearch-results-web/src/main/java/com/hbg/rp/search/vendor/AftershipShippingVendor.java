package com.hbg.rp.search.vendor;

import com.hbg.rp.search.dto.Tracking;
import com.hbg.rp.search.exceptions.ShipmentTrackingException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.PropsUtil;

import java.io.IOException;
import java.text.ParseException;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import aftership.classes.AftershipAPIException;
import aftership.classes.ConnectionAPI;

/**
 * 
 * @author ravi.kumar
 */

@Service
@Primary
public class AftershipShippingVendor extends BaseShipmentTrackingVendor {
	private final Log logger = LogFactoryUtil.getLog(this.getClass());
	
	private static final String ERROR_PARSE = "ERROR_PARSE";
	private static final String ERROR_IO = "ERROR_IO";
	private static final String ERROR_JSON = "ERROR_JSON";
	private static final String VENDOR_NAME = "AFTERSHIP_SHIPPING_VENDOR";
	private static final String INVALID_SLUG = "INVALID_SLUG";
	private static final String HBG_AFTERSHIP_TRACKING_URI = "hbg.aftership.tracking.uri";
	private static final String ERROR_API = "ERROR_API";
	
	@Autowired
	private IAftershipShipConnectionAPI aftershipShipConnectionAPI;
	
	@Autowired
	private IAftershipShippingVendorHelper aftershipShippingVendorHelper;
	
	/**
	 * Initialize vendor carrier codes.
	 */
	@Override
	public void initializeVendorCarrierCodes() { 
		setVendorName(VENDOR_NAME);
		setTrackingURL(PropsUtil.get(HBG_AFTERSHIP_TRACKING_URI));
		setShipmentCarrierVendorMappings();
	}
	
	/**
	 * Get tracking by carrier code.
	 */
	@Override
	public Tracking getTracking(Tracking tracking) throws ShipmentTrackingException {
		logger.info(" getTracking() >>>>>>>>>> ");
		initializeVendorCarrierCodes();
		aftership.classes.Tracking track = null;
		String carrier = this.convertCarrierCode(tracking.getCarrierCode());
		logger.info("### found slug '"+carrier+"' from getVendorCarrierCodes() map for carrier_code '"+tracking.getCarrierCode()+"' and tracking_num '"+tracking.getTrackingNumber()+"'");
		ConnectionAPI connection = aftershipShipConnectionAPI.getAftershipShipConnectionAPI();
		aftership.classes.Tracking tempTrack = new aftership.classes.Tracking(tracking.getTrackingNumber());
		tempTrack.setSlug(carrier);
		try {
			track = connection.getTrackingByNumber(tempTrack);
			return aftershipShippingVendorHelper.convertAftershipToTracking(track, tracking);
		} catch (AftershipAPIException e) {
			//e.printStackTrace();
			logger.info("AftershipAPIException for Carrier(" + tracking.getCarrierCode()+ ") & Tracking_Number(" + tracking.getTrackingNumber()+")");
			if (e.getMessage().contains("4004")) {
				try {
					return aftershipShippingVendorHelper.postTracking(tracking, connection, tempTrack);
				} catch (ShipmentTrackingException se) {
					//se.printStackTrace();
					logger.info("### received ShipmentTrackingException with message "+se.getMessage());
					if (ERROR_API.equals(se.getMessage()) && se.getCause().getMessage().contains("4003")) {
						logger.info("Tracking already exists with the following data:" + se.getCause().getMessage());
						return getTracking(tracking);
					} else {
						throw se;
					}
				}
			}
			if (e.getMessage().contains("4010")) {
				throw new ShipmentTrackingException(INVALID_SLUG, e);
			}
			logger.info("Error occured while fetching tracking data >>>>>>>>>>>> Carrier: " + tracking.getCarrierCode()
					+ " Tracking Number: " + tracking.getTrackingNumber(), e);
		} catch (JSONException je) {
			//je.printStackTrace();
			throw new ShipmentTrackingException(ERROR_JSON, je);
		} catch (IOException ioe) {
			//ioe.printStackTrace();
			throw new ShipmentTrackingException(ERROR_IO, ioe);
		} catch (ParseException pe) {
			//pe.printStackTrace();
			throw new ShipmentTrackingException(ERROR_PARSE, pe);
		}
		
		logger.info(" getTracking() >>>>>>>>>> ");
		return null;
	}

	/**
	 * Post tracking number via
	 * <p> Create tracking from carrier code and tracking number. </p>
	 * <p> Call {@link #postTrackingNumber(Tracking)} method </p>
	 */
	@Override
	public void postTrackingNumber(String carrierCode, String trackingNum) throws ShipmentTrackingException {
		initializeVendorCarrierCodes();
		Tracking tracking = new Tracking(carrierCode, trackingNum);
		String carrier = this.convertCarrierCode(tracking.getCarrierCode());
		aftershipShippingVendorHelper.postTrackingNumber(tracking, carrier);
	}
	
	
	/**
	 * Get tracking by carrier code & tracking number.
	 */
	@Override
	public Tracking getTracking(String carrierCode, String trackingNum) throws ShipmentTrackingException {
		initializeVendorCarrierCodes();
		Tracking tracking = new Tracking(carrierCode, trackingNum);
		return this.getTracking(tracking);
	}
	
}
