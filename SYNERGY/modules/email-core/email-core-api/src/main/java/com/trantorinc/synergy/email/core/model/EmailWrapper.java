/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.trantorinc.synergy.email.core.model;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link Email}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Email
 * @generated
 */
public class EmailWrapper
	extends BaseModelWrapper<Email> implements Email, ModelWrapper<Email> {

	public EmailWrapper(Email email) {
		super(email);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("emailId", getEmailId());
		attributes.put("scheduled", isScheduled());
		attributes.put("module", getModule());
		attributes.put("toAddress", getToAddress());
		attributes.put("ccAddress", getCcAddress());
		attributes.put("bccAddress", getBccAddress());
		attributes.put("subject", getSubject());
		attributes.put("body", getBody());
		attributes.put("createdDate", getCreatedDate());
		attributes.put("sent", isSent());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long emailId = (Long)attributes.get("emailId");

		if (emailId != null) {
			setEmailId(emailId);
		}

		Boolean scheduled = (Boolean)attributes.get("scheduled");

		if (scheduled != null) {
			setScheduled(scheduled);
		}

		String module = (String)attributes.get("module");

		if (module != null) {
			setModule(module);
		}

		String toAddress = (String)attributes.get("toAddress");

		if (toAddress != null) {
			setToAddress(toAddress);
		}

		String ccAddress = (String)attributes.get("ccAddress");

		if (ccAddress != null) {
			setCcAddress(ccAddress);
		}

		String bccAddress = (String)attributes.get("bccAddress");

		if (bccAddress != null) {
			setBccAddress(bccAddress);
		}

		String subject = (String)attributes.get("subject");

		if (subject != null) {
			setSubject(subject);
		}

		String body = (String)attributes.get("body");

		if (body != null) {
			setBody(body);
		}

		Date createdDate = (Date)attributes.get("createdDate");

		if (createdDate != null) {
			setCreatedDate(createdDate);
		}

		Boolean sent = (Boolean)attributes.get("sent");

		if (sent != null) {
			setSent(sent);
		}
	}

	@Override
	public Email cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the bcc address of this email.
	 *
	 * @return the bcc address of this email
	 */
	@Override
	public String getBccAddress() {
		return model.getBccAddress();
	}

	/**
	 * Returns the body of this email.
	 *
	 * @return the body of this email
	 */
	@Override
	public String getBody() {
		return model.getBody();
	}

	/**
	 * Returns the cc address of this email.
	 *
	 * @return the cc address of this email
	 */
	@Override
	public String getCcAddress() {
		return model.getCcAddress();
	}

	/**
	 * Returns the created date of this email.
	 *
	 * @return the created date of this email
	 */
	@Override
	public Date getCreatedDate() {
		return model.getCreatedDate();
	}

	/**
	 * Returns the email ID of this email.
	 *
	 * @return the email ID of this email
	 */
	@Override
	public long getEmailId() {
		return model.getEmailId();
	}

	/**
	 * Returns the module of this email.
	 *
	 * @return the module of this email
	 */
	@Override
	public String getModule() {
		return model.getModule();
	}

	/**
	 * Returns the primary key of this email.
	 *
	 * @return the primary key of this email
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the scheduled of this email.
	 *
	 * @return the scheduled of this email
	 */
	@Override
	public boolean getScheduled() {
		return model.getScheduled();
	}

	/**
	 * Returns the sent of this email.
	 *
	 * @return the sent of this email
	 */
	@Override
	public boolean getSent() {
		return model.getSent();
	}

	/**
	 * Returns the subject of this email.
	 *
	 * @return the subject of this email
	 */
	@Override
	public String getSubject() {
		return model.getSubject();
	}

	/**
	 * Returns the to address of this email.
	 *
	 * @return the to address of this email
	 */
	@Override
	public String getToAddress() {
		return model.getToAddress();
	}

	/**
	 * Returns <code>true</code> if this email is scheduled.
	 *
	 * @return <code>true</code> if this email is scheduled; <code>false</code> otherwise
	 */
	@Override
	public boolean isScheduled() {
		return model.isScheduled();
	}

	/**
	 * Returns <code>true</code> if this email is sent.
	 *
	 * @return <code>true</code> if this email is sent; <code>false</code> otherwise
	 */
	@Override
	public boolean isSent() {
		return model.isSent();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the bcc address of this email.
	 *
	 * @param bccAddress the bcc address of this email
	 */
	@Override
	public void setBccAddress(String bccAddress) {
		model.setBccAddress(bccAddress);
	}

	/**
	 * Sets the body of this email.
	 *
	 * @param body the body of this email
	 */
	@Override
	public void setBody(String body) {
		model.setBody(body);
	}

	/**
	 * Sets the cc address of this email.
	 *
	 * @param ccAddress the cc address of this email
	 */
	@Override
	public void setCcAddress(String ccAddress) {
		model.setCcAddress(ccAddress);
	}

	/**
	 * Sets the created date of this email.
	 *
	 * @param createdDate the created date of this email
	 */
	@Override
	public void setCreatedDate(Date createdDate) {
		model.setCreatedDate(createdDate);
	}

	/**
	 * Sets the email ID of this email.
	 *
	 * @param emailId the email ID of this email
	 */
	@Override
	public void setEmailId(long emailId) {
		model.setEmailId(emailId);
	}

	/**
	 * Sets the module of this email.
	 *
	 * @param module the module of this email
	 */
	@Override
	public void setModule(String module) {
		model.setModule(module);
	}

	/**
	 * Sets the primary key of this email.
	 *
	 * @param primaryKey the primary key of this email
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets whether this email is scheduled.
	 *
	 * @param scheduled the scheduled of this email
	 */
	@Override
	public void setScheduled(boolean scheduled) {
		model.setScheduled(scheduled);
	}

	/**
	 * Sets whether this email is sent.
	 *
	 * @param sent the sent of this email
	 */
	@Override
	public void setSent(boolean sent) {
		model.setSent(sent);
	}

	/**
	 * Sets the subject of this email.
	 *
	 * @param subject the subject of this email
	 */
	@Override
	public void setSubject(String subject) {
		model.setSubject(subject);
	}

	/**
	 * Sets the to address of this email.
	 *
	 * @param toAddress the to address of this email
	 */
	@Override
	public void setToAddress(String toAddress) {
		model.setToAddress(toAddress);
	}

	@Override
	protected EmailWrapper wrap(Email email) {
		return new EmailWrapper(email);
	}

}