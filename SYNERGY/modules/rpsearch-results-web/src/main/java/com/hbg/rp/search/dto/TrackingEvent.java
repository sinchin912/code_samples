package com.hbg.rp.search.dto;

import java.util.Date;

/**
 * DTO for Tracking Event.
 * 
 * @author ravi.kumar
 *
 */
public class TrackingEvent {
	
	private Date eventDate;
	private String location;
	private String message;
	private TrackingStatus status;
	private String eventDateString;
	
	/**
	 * @return the eventDate
	 */
	public Date getEventDate() {
		return eventDate;
	}
	/**
	 * @param eventDate the eventDate to set
	 */
	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}
	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}
	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	/**
	 * @return the status
	 */
	public TrackingStatus getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(TrackingStatus status) {
		this.status = status;
	}
	/**
	 * @return the eventDateString
	 */
	public String getEventDateString() {
		return eventDateString;
	}
	/**
	 * @param eventDateString the eventDateString to set
	 */
	public void setEventDateString(String eventDateString) {
		this.eventDateString = eventDateString;
	}
	
	@Override
	public String toString() {
		return String.format("Status: %s - Location: %s - Date: %s", status, location, eventDate);
	}
	
}
