package com.hbg.rp.search.dto;

/**
 * DTO of Tracking Status
 * 
 * @author ravi.kumar
 *
 */
public enum TrackingStatus {
	//"unknown", "pre_transit", "in_transit", "out_for_delivery", "delivered", "available_for_pickup", "return_to_sender", "failure", "cancelled" or "error"
	UNKNOWN ("Unknown"),
	PRE_TRANSIT ("Pre-transit"),
	IN_TRANSIT ("In transit"),
	OUT_FOR_DELIVERY ("Out for delivery"),
	DELIVERED ("Delivered"),
	AVAILABLE_FOR_PICKUP ("Available for pickup"),
	RETURN ("Return to sender"),
	FAILURE ("Failure"),
	CANCELLED ("Cancelled"),
	ERROR ("Error"),
	EXPIRED ("Expired");
	
	private final String description;
	
	private TrackingStatus(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	@Override
	public String toString() {
		return this.description;
	}
}
