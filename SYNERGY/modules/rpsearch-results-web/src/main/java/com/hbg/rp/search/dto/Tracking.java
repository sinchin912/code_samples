package com.hbg.rp.search.dto;

import java.util.Date;
import java.util.List;

/**
 * The com.hbg.rp.shipment.tracking.dto.Tracking is DTO for Tracking.
 * 
 * @author ravi.kumar
 */
public class Tracking {

	private String carrierCode;
	private String trackingNumber;
	private TrackingStatus status;
	private Date scheduledDeliveryDate;
	private Date deliveryDate;
	private List<TrackingEvent> events;
	private String shipmentCategory;
	private String weight;
	private String receiverName;
	private String url;
	private String location;
	private String message;

	public Tracking() {
		this.status = TrackingStatus.UNKNOWN;
	}

	public Tracking(String carrierCode, String trackingNumber) {
		this.status = TrackingStatus.UNKNOWN;
		this.carrierCode = carrierCode;
		this.trackingNumber = trackingNumber;
	}

	/**
	 * @return the carrierCode
	 */
	public String getCarrierCode() {
		return carrierCode;
	}

	/**
	 * @return the deliveryDate
	 */
	public Date getDeliveryDate() {
		return deliveryDate;
	}

	/**
	 * @return the events
	 */
	public List<TrackingEvent> getEvents() {
		return events;
	}

	/**
	 * @return the receiverName
	 */
	public String getReceiverName() {
		return receiverName;
	}

	/**
	 * @return the scheduledDeliveryDate
	 */
	public Date getScheduledDeliveryDate() {
		return scheduledDeliveryDate;
	}

	/**
	 * @return the shipmentCategory
	 */
	public String getShipmentCategory() {
		return shipmentCategory;
	}

	/**
	 * @return the status
	 */
	public TrackingStatus getStatus() {
		return status;
	}

	/**
	 * @return the trackingNumber
	 */
	public String getTrackingNumber() {
		return trackingNumber;
	}

	/**
	 * @return the weight
	 */
	public String getWeight() {
		return weight;
	}

	/**
	 * @param carrierCode
	 *            the carrierCode to set
	 */
	public void setCarrierCode(String carrierCode) {
		this.carrierCode = carrierCode;
	}

	/**
	 * @param deliveryDate
	 *            the deliveryDate to set
	 */
	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	/**
	 * @param events
	 *            the events to set
	 */
	public void setEvents(List<TrackingEvent> events) {
		this.events = events;
	}

	/**
	 * @param receiverName
	 *            the receiverName to set
	 */
	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	/**
	 * @param scheduledDeliveryDate
	 *            the scheduledDeliveryDate to set
	 */
	public void setScheduledDeliveryDate(Date scheduledDeliveryDate) {
		this.scheduledDeliveryDate = scheduledDeliveryDate;
	}

	/**
	 * @param shipmentCategory
	 *            the shipmentCategory to set
	 */
	public void setShipmentCategory(String shipmentCategory) {
		this.shipmentCategory = shipmentCategory;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(TrackingStatus status) {
		this.status = status;
	}

	/**
	 * @param trackingNumber
	 *            the trackingNumber to set
	 */
	public void setTrackingNumber(String trackingNumber) {
		this.trackingNumber = trackingNumber;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @param weight
	 *            the weight to set
	 */
	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "Carrier: " + carrierCode + " " + "Tracking Number: " + trackingNumber + " - " + "Status: " + status;
	}

}
