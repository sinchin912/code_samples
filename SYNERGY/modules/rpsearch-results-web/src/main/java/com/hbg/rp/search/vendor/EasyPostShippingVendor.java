/**
 * 
 */
package com.hbg.rp.search.vendor;

import java.util.HashMap;
import java.util.Map;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.easypost.exception.EasyPostException;
import com.easypost.model.Tracker;
import com.hbg.rp.search.dto.Tracking;
import com.hbg.rp.search.exceptions.ShipmentTrackingException;
import com.liferay.portal.kernel.util.PropsUtil;

/**
 * 
 * @author ravi.kumar
 */

@Service
public class EasyPostShippingVendor extends BaseShipmentTrackingVendor {
	private final Log logger = LogFactoryUtil.getLog(this.getClass());
	private static final String VENDOR_NAME = "EASYPOST_SHIPPING_VENDOR";
	private static final String HBG_EASYPOST_API_KEY = "hbg.easypost.api.key";
	private static final String HBG_EASYPOST_TRACKING_URI = "hbg.easypost.tracking.uri";
	
	@Autowired
	private IEasyPostShippingVendorHelper easyPostShippingVendorHelper;
	
	/**
	 * Initialize vendor carrier codes.
	 */
	@Override
	public void initializeVendorCarrierCodes() {
		logger.info("******************* EasyPostShippingVendor Initilized");
		super.setVendorName(VENDOR_NAME);
		super.setTrackingURL(PropsUtil.get(HBG_EASYPOST_TRACKING_URI));
		super.setShipmentCarrierVendorMappings();
	}
	
	/**
	 * @see com.hbg.rp.shipment.tracking.vendor.ShippingVendor#getTracking(com.hbgusa.nrp.tracking.Tracking)
	 */
	@Override
	public Tracking getTracking(Tracking tracking) {
		logger.info("******************* calling Easypost");
		try {
			Map<String, Object> params = new HashMap<>();
		    params.put("tracking_code", tracking.getTrackingNumber());
		    params.put("carrier", super.convertCarrierCode(tracking.getCarrierCode()));
		    Tracker tracker = Tracker.create(params, PropsUtil.get(HBG_EASYPOST_API_KEY));
			return easyPostShippingVendorHelper.convertTrackerToTracking(tracker, tracking);
		} catch (EasyPostException e) {
			logger.info(e.getMessage(), e);
			return null;
		}
	}

	/**
	 * @see com.hbg.rp.shipment.tracking.vendor.ShippingVendor#getTracking(java.lang.String, java.lang.String)
	 */
	@Override
	public Tracking getTracking(String carrierCode, String trackingNum) {
		Tracking tracking = new Tracking(carrierCode, trackingNum);
		return this.getTracking(tracking);
	}
	
	/**
	 * @see com.hbg.rp.shipment.tracking.vendor.ShippingVendor#postTrackingNumber(java.lang.String, java.lang.String)
	 */
	@Override
	public void postTrackingNumber(String carrierCode, String trackingNum) throws ShipmentTrackingException {
		// TODO Auto-generated method stub
	}

}
