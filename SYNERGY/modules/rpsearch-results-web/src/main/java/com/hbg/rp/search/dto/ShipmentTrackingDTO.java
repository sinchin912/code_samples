package com.hbg.rp.search.dto;


/**
 * The com.hbg.rp.shipment.tracking.dto.Tracking is DTO for Tracking.
 * 
 * @author ravi.kumar
 */
public class ShipmentTrackingDTO {


	private boolean ZENDESK;
	private Tracking trackingData;

	public boolean isZENDESK() {
		return ZENDESK;
	}

	public void setZENDESK(boolean ZENDESK) {
		this.ZENDESK = ZENDESK;
	}

	public Tracking getTrackingData() {
		return trackingData;
	}

	public void setTrackingData(Tracking trackingData) {
		this.trackingData = trackingData;
	}
}
