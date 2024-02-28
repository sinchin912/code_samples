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

package com.trantorinc.synergy.game.core.model;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link Santa}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Santa
 * @generated
 */
public class SantaWrapper
	extends BaseModelWrapper<Santa> implements ModelWrapper<Santa>, Santa {

	public SantaWrapper(Santa santa) {
		super(santa);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("santaId", getSantaId());
		attributes.put("year", getYear());
		attributes.put("ecode", getEcode());
		attributes.put("mobile", getMobile());
		attributes.put("city", getCity());
		attributes.put("state", getState());
		attributes.put("pincode", getPincode());
		attributes.put("postalAddress", getPostalAddress());
		attributes.put("santaEcode", getSantaEcode());
		attributes.put("guessedEcode", getGuessedEcode());
		attributes.put("giftSent", isGiftSent());
		attributes.put("emailSent", isEmailSent());
		attributes.put("fileId", getFileId());
		attributes.put("createDatetime", getCreateDatetime());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long santaId = (Long)attributes.get("santaId");

		if (santaId != null) {
			setSantaId(santaId);
		}

		Integer year = (Integer)attributes.get("year");

		if (year != null) {
			setYear(year);
		}

		String ecode = (String)attributes.get("ecode");

		if (ecode != null) {
			setEcode(ecode);
		}

		String mobile = (String)attributes.get("mobile");

		if (mobile != null) {
			setMobile(mobile);
		}

		String city = (String)attributes.get("city");

		if (city != null) {
			setCity(city);
		}

		String state = (String)attributes.get("state");

		if (state != null) {
			setState(state);
		}

		String pincode = (String)attributes.get("pincode");

		if (pincode != null) {
			setPincode(pincode);
		}

		String postalAddress = (String)attributes.get("postalAddress");

		if (postalAddress != null) {
			setPostalAddress(postalAddress);
		}

		String santaEcode = (String)attributes.get("santaEcode");

		if (santaEcode != null) {
			setSantaEcode(santaEcode);
		}

		String guessedEcode = (String)attributes.get("guessedEcode");

		if (guessedEcode != null) {
			setGuessedEcode(guessedEcode);
		}

		Boolean giftSent = (Boolean)attributes.get("giftSent");

		if (giftSent != null) {
			setGiftSent(giftSent);
		}

		Boolean emailSent = (Boolean)attributes.get("emailSent");

		if (emailSent != null) {
			setEmailSent(emailSent);
		}

		String fileId = (String)attributes.get("fileId");

		if (fileId != null) {
			setFileId(fileId);
		}

		Date createDatetime = (Date)attributes.get("createDatetime");

		if (createDatetime != null) {
			setCreateDatetime(createDatetime);
		}
	}

	@Override
	public Santa cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the city of this santa.
	 *
	 * @return the city of this santa
	 */
	@Override
	public String getCity() {
		return model.getCity();
	}

	/**
	 * Returns the create datetime of this santa.
	 *
	 * @return the create datetime of this santa
	 */
	@Override
	public Date getCreateDatetime() {
		return model.getCreateDatetime();
	}

	/**
	 * Returns the ecode of this santa.
	 *
	 * @return the ecode of this santa
	 */
	@Override
	public String getEcode() {
		return model.getEcode();
	}

	/**
	 * Returns the email sent of this santa.
	 *
	 * @return the email sent of this santa
	 */
	@Override
	public boolean getEmailSent() {
		return model.getEmailSent();
	}

	/**
	 * Returns the file ID of this santa.
	 *
	 * @return the file ID of this santa
	 */
	@Override
	public String getFileId() {
		return model.getFileId();
	}

	/**
	 * Returns the gift sent of this santa.
	 *
	 * @return the gift sent of this santa
	 */
	@Override
	public boolean getGiftSent() {
		return model.getGiftSent();
	}

	/**
	 * Returns the guessed ecode of this santa.
	 *
	 * @return the guessed ecode of this santa
	 */
	@Override
	public String getGuessedEcode() {
		return model.getGuessedEcode();
	}

	/**
	 * Returns the mobile of this santa.
	 *
	 * @return the mobile of this santa
	 */
	@Override
	public String getMobile() {
		return model.getMobile();
	}

	/**
	 * Returns the pincode of this santa.
	 *
	 * @return the pincode of this santa
	 */
	@Override
	public String getPincode() {
		return model.getPincode();
	}

	/**
	 * Returns the postal address of this santa.
	 *
	 * @return the postal address of this santa
	 */
	@Override
	public String getPostalAddress() {
		return model.getPostalAddress();
	}

	/**
	 * Returns the primary key of this santa.
	 *
	 * @return the primary key of this santa
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the santa ecode of this santa.
	 *
	 * @return the santa ecode of this santa
	 */
	@Override
	public String getSantaEcode() {
		return model.getSantaEcode();
	}

	/**
	 * Returns the santa ID of this santa.
	 *
	 * @return the santa ID of this santa
	 */
	@Override
	public long getSantaId() {
		return model.getSantaId();
	}

	/**
	 * Returns the state of this santa.
	 *
	 * @return the state of this santa
	 */
	@Override
	public String getState() {
		return model.getState();
	}

	/**
	 * Returns the year of this santa.
	 *
	 * @return the year of this santa
	 */
	@Override
	public int getYear() {
		return model.getYear();
	}

	/**
	 * Returns <code>true</code> if this santa is email sent.
	 *
	 * @return <code>true</code> if this santa is email sent; <code>false</code> otherwise
	 */
	@Override
	public boolean isEmailSent() {
		return model.isEmailSent();
	}

	/**
	 * Returns <code>true</code> if this santa is gift sent.
	 *
	 * @return <code>true</code> if this santa is gift sent; <code>false</code> otherwise
	 */
	@Override
	public boolean isGiftSent() {
		return model.isGiftSent();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the city of this santa.
	 *
	 * @param city the city of this santa
	 */
	@Override
	public void setCity(String city) {
		model.setCity(city);
	}

	/**
	 * Sets the create datetime of this santa.
	 *
	 * @param createDatetime the create datetime of this santa
	 */
	@Override
	public void setCreateDatetime(Date createDatetime) {
		model.setCreateDatetime(createDatetime);
	}

	/**
	 * Sets the ecode of this santa.
	 *
	 * @param ecode the ecode of this santa
	 */
	@Override
	public void setEcode(String ecode) {
		model.setEcode(ecode);
	}

	/**
	 * Sets whether this santa is email sent.
	 *
	 * @param emailSent the email sent of this santa
	 */
	@Override
	public void setEmailSent(boolean emailSent) {
		model.setEmailSent(emailSent);
	}

	/**
	 * Sets the file ID of this santa.
	 *
	 * @param fileId the file ID of this santa
	 */
	@Override
	public void setFileId(String fileId) {
		model.setFileId(fileId);
	}

	/**
	 * Sets whether this santa is gift sent.
	 *
	 * @param giftSent the gift sent of this santa
	 */
	@Override
	public void setGiftSent(boolean giftSent) {
		model.setGiftSent(giftSent);
	}

	/**
	 * Sets the guessed ecode of this santa.
	 *
	 * @param guessedEcode the guessed ecode of this santa
	 */
	@Override
	public void setGuessedEcode(String guessedEcode) {
		model.setGuessedEcode(guessedEcode);
	}

	/**
	 * Sets the mobile of this santa.
	 *
	 * @param mobile the mobile of this santa
	 */
	@Override
	public void setMobile(String mobile) {
		model.setMobile(mobile);
	}

	/**
	 * Sets the pincode of this santa.
	 *
	 * @param pincode the pincode of this santa
	 */
	@Override
	public void setPincode(String pincode) {
		model.setPincode(pincode);
	}

	/**
	 * Sets the postal address of this santa.
	 *
	 * @param postalAddress the postal address of this santa
	 */
	@Override
	public void setPostalAddress(String postalAddress) {
		model.setPostalAddress(postalAddress);
	}

	/**
	 * Sets the primary key of this santa.
	 *
	 * @param primaryKey the primary key of this santa
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the santa ecode of this santa.
	 *
	 * @param santaEcode the santa ecode of this santa
	 */
	@Override
	public void setSantaEcode(String santaEcode) {
		model.setSantaEcode(santaEcode);
	}

	/**
	 * Sets the santa ID of this santa.
	 *
	 * @param santaId the santa ID of this santa
	 */
	@Override
	public void setSantaId(long santaId) {
		model.setSantaId(santaId);
	}

	/**
	 * Sets the state of this santa.
	 *
	 * @param state the state of this santa
	 */
	@Override
	public void setState(String state) {
		model.setState(state);
	}

	/**
	 * Sets the year of this santa.
	 *
	 * @param year the year of this santa
	 */
	@Override
	public void setYear(int year) {
		model.setYear(year);
	}

	@Override
	protected SantaWrapper wrap(Santa santa) {
		return new SantaWrapper(santa);
	}

}