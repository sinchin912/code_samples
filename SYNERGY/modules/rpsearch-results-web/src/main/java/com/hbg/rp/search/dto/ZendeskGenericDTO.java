package com.hbg.rp.search.dto;

import java.io.Serializable;

/**
 * DTO for Zendesk
 * @author robin.sharma
 *
 */
public class ZendeskGenericDTO implements Serializable {

	private static final long serialVersionUID = -5193515699696881715L;

	private String requestorName;
	private String requestorEmail;
	private String subject;
	private String description;
	private long userId;

	public String getRequestorName() {
		return requestorName;
	}

	public void setRequestorName(String requestorName) {
		this.requestorName = requestorName;
	}

	public String getRequestorEmail() {
		return requestorEmail;
	}

	public void setRequestorEmail(String requestorEmail) {
		this.requestorEmail = requestorEmail;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

}
