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

package com.trantorinc.synergy.notice.core.model;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link AdminForm}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AdminForm
 * @generated
 */
public class AdminFormWrapper
	extends BaseModelWrapper<AdminForm>
	implements AdminForm, ModelWrapper<AdminForm> {

	public AdminFormWrapper(AdminForm adminForm) {
		super(adminForm);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("id", getId());
		attributes.put("exitId", getExitId());
		attributes.put("stationaryRecoveryAmt", getStationaryRecoveryAmt());
		attributes.put(
			"stationaryRecoveryAmtStatus", getStationaryRecoveryAmtStatus());
		attributes.put(
			"stationaryRecoveryApproved", isStationaryRecoveryApproved());
		attributes.put(
			"stationaryRecoveryComment", getStationaryRecoveryComment());
		attributes.put("keysRecoveryAmt", getKeysRecoveryAmt());
		attributes.put("keysRecoveryAmtStatus", getKeysRecoveryAmtStatus());
		attributes.put("keyRecoveryApproved", isKeyRecoveryApproved());
		attributes.put("keyRecoveryComment", getKeyRecoveryComment());
		attributes.put("libraryRecoveryAmt", getLibraryRecoveryAmt());
		attributes.put(
			"libraryRecoveryAmtStatus", getLibraryRecoveryAmtStatus());
		attributes.put("libraryRecoveryApproved", isLibraryRecoveryApproved());
		attributes.put("libraryRecoveryComment", getLibraryRecoveryComment());
		attributes.put("sportsRecoveryAmt", getSportsRecoveryAmt());
		attributes.put("sportsRecoveryAmtStatus", getSportsRecoveryAmtStatus());
		attributes.put("sportsRecoveryApproved", isSportsRecoveryApproved());
		attributes.put("sportsRecoveryComment", getSportsRecoveryComment());
		attributes.put("infraRecoveryAmt", getInfraRecoveryAmt());
		attributes.put("infraRecoveryAmtStatus", getInfraRecoveryAmtStatus());
		attributes.put("infraRecoveryApproved", isInfraRecoveryApproved());
		attributes.put("infraRecoveryComment", getInfraRecoveryComment());
		attributes.put("lunchRecoveryAmt", getLunchRecoveryAmt());
		attributes.put("lunchRecoveryAmtStatus", getLunchRecoveryAmtStatus());
		attributes.put("lunchRecoveryApproved", isLunchRecoveryApproved());
		attributes.put("lunchRecoveryComment", getLunchRecoveryComment());
		attributes.put("accessCardRecoveryAmt", getAccessCardRecoveryAmt());
		attributes.put(
			"accessCardRecoveryAmtStatus", getAccessCardRecoveryAmtStatus());
		attributes.put(
			"accessCardRecoveryApproved", isAccessCardRecoveryApproved());
		attributes.put(
			"accessCardRecoveryComment", getAccessCardRecoveryComment());
		attributes.put("identityCardRecoveryAmt", getIdentityCardRecoveryAmt());
		attributes.put(
			"identityCardRecoveryStatus", getIdentityCardRecoveryStatus());
		attributes.put(
			"identityCardRecoveryApproved", isIdentityCardRecoveryApproved());
		attributes.put(
			"identityCardRecoveryComment", getIdentityCardRecoveryComment());
		attributes.put("updatedDate", getUpdatedDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long id = (Long)attributes.get("id");

		if (id != null) {
			setId(id);
		}

		Long exitId = (Long)attributes.get("exitId");

		if (exitId != null) {
			setExitId(exitId);
		}

		String stationaryRecoveryAmt = (String)attributes.get(
			"stationaryRecoveryAmt");

		if (stationaryRecoveryAmt != null) {
			setStationaryRecoveryAmt(stationaryRecoveryAmt);
		}

		Integer stationaryRecoveryAmtStatus = (Integer)attributes.get(
			"stationaryRecoveryAmtStatus");

		if (stationaryRecoveryAmtStatus != null) {
			setStationaryRecoveryAmtStatus(stationaryRecoveryAmtStatus);
		}

		Boolean stationaryRecoveryApproved = (Boolean)attributes.get(
			"stationaryRecoveryApproved");

		if (stationaryRecoveryApproved != null) {
			setStationaryRecoveryApproved(stationaryRecoveryApproved);
		}

		String stationaryRecoveryComment = (String)attributes.get(
			"stationaryRecoveryComment");

		if (stationaryRecoveryComment != null) {
			setStationaryRecoveryComment(stationaryRecoveryComment);
		}

		String keysRecoveryAmt = (String)attributes.get("keysRecoveryAmt");

		if (keysRecoveryAmt != null) {
			setKeysRecoveryAmt(keysRecoveryAmt);
		}

		Integer keysRecoveryAmtStatus = (Integer)attributes.get(
			"keysRecoveryAmtStatus");

		if (keysRecoveryAmtStatus != null) {
			setKeysRecoveryAmtStatus(keysRecoveryAmtStatus);
		}

		Boolean keyRecoveryApproved = (Boolean)attributes.get(
			"keyRecoveryApproved");

		if (keyRecoveryApproved != null) {
			setKeyRecoveryApproved(keyRecoveryApproved);
		}

		String keyRecoveryComment = (String)attributes.get(
			"keyRecoveryComment");

		if (keyRecoveryComment != null) {
			setKeyRecoveryComment(keyRecoveryComment);
		}

		String libraryRecoveryAmt = (String)attributes.get(
			"libraryRecoveryAmt");

		if (libraryRecoveryAmt != null) {
			setLibraryRecoveryAmt(libraryRecoveryAmt);
		}

		Integer libraryRecoveryAmtStatus = (Integer)attributes.get(
			"libraryRecoveryAmtStatus");

		if (libraryRecoveryAmtStatus != null) {
			setLibraryRecoveryAmtStatus(libraryRecoveryAmtStatus);
		}

		Boolean libraryRecoveryApproved = (Boolean)attributes.get(
			"libraryRecoveryApproved");

		if (libraryRecoveryApproved != null) {
			setLibraryRecoveryApproved(libraryRecoveryApproved);
		}

		String libraryRecoveryComment = (String)attributes.get(
			"libraryRecoveryComment");

		if (libraryRecoveryComment != null) {
			setLibraryRecoveryComment(libraryRecoveryComment);
		}

		String sportsRecoveryAmt = (String)attributes.get("sportsRecoveryAmt");

		if (sportsRecoveryAmt != null) {
			setSportsRecoveryAmt(sportsRecoveryAmt);
		}

		Integer sportsRecoveryAmtStatus = (Integer)attributes.get(
			"sportsRecoveryAmtStatus");

		if (sportsRecoveryAmtStatus != null) {
			setSportsRecoveryAmtStatus(sportsRecoveryAmtStatus);
		}

		Boolean sportsRecoveryApproved = (Boolean)attributes.get(
			"sportsRecoveryApproved");

		if (sportsRecoveryApproved != null) {
			setSportsRecoveryApproved(sportsRecoveryApproved);
		}

		String sportsRecoveryComment = (String)attributes.get(
			"sportsRecoveryComment");

		if (sportsRecoveryComment != null) {
			setSportsRecoveryComment(sportsRecoveryComment);
		}

		String infraRecoveryAmt = (String)attributes.get("infraRecoveryAmt");

		if (infraRecoveryAmt != null) {
			setInfraRecoveryAmt(infraRecoveryAmt);
		}

		Integer infraRecoveryAmtStatus = (Integer)attributes.get(
			"infraRecoveryAmtStatus");

		if (infraRecoveryAmtStatus != null) {
			setInfraRecoveryAmtStatus(infraRecoveryAmtStatus);
		}

		Boolean infraRecoveryApproved = (Boolean)attributes.get(
			"infraRecoveryApproved");

		if (infraRecoveryApproved != null) {
			setInfraRecoveryApproved(infraRecoveryApproved);
		}

		String infraRecoveryComment = (String)attributes.get(
			"infraRecoveryComment");

		if (infraRecoveryComment != null) {
			setInfraRecoveryComment(infraRecoveryComment);
		}

		String lunchRecoveryAmt = (String)attributes.get("lunchRecoveryAmt");

		if (lunchRecoveryAmt != null) {
			setLunchRecoveryAmt(lunchRecoveryAmt);
		}

		Integer lunchRecoveryAmtStatus = (Integer)attributes.get(
			"lunchRecoveryAmtStatus");

		if (lunchRecoveryAmtStatus != null) {
			setLunchRecoveryAmtStatus(lunchRecoveryAmtStatus);
		}

		Boolean lunchRecoveryApproved = (Boolean)attributes.get(
			"lunchRecoveryApproved");

		if (lunchRecoveryApproved != null) {
			setLunchRecoveryApproved(lunchRecoveryApproved);
		}

		String lunchRecoveryComment = (String)attributes.get(
			"lunchRecoveryComment");

		if (lunchRecoveryComment != null) {
			setLunchRecoveryComment(lunchRecoveryComment);
		}

		String accessCardRecoveryAmt = (String)attributes.get(
			"accessCardRecoveryAmt");

		if (accessCardRecoveryAmt != null) {
			setAccessCardRecoveryAmt(accessCardRecoveryAmt);
		}

		Integer accessCardRecoveryAmtStatus = (Integer)attributes.get(
			"accessCardRecoveryAmtStatus");

		if (accessCardRecoveryAmtStatus != null) {
			setAccessCardRecoveryAmtStatus(accessCardRecoveryAmtStatus);
		}

		Boolean accessCardRecoveryApproved = (Boolean)attributes.get(
			"accessCardRecoveryApproved");

		if (accessCardRecoveryApproved != null) {
			setAccessCardRecoveryApproved(accessCardRecoveryApproved);
		}

		String accessCardRecoveryComment = (String)attributes.get(
			"accessCardRecoveryComment");

		if (accessCardRecoveryComment != null) {
			setAccessCardRecoveryComment(accessCardRecoveryComment);
		}

		String identityCardRecoveryAmt = (String)attributes.get(
			"identityCardRecoveryAmt");

		if (identityCardRecoveryAmt != null) {
			setIdentityCardRecoveryAmt(identityCardRecoveryAmt);
		}

		Integer identityCardRecoveryStatus = (Integer)attributes.get(
			"identityCardRecoveryStatus");

		if (identityCardRecoveryStatus != null) {
			setIdentityCardRecoveryStatus(identityCardRecoveryStatus);
		}

		Boolean identityCardRecoveryApproved = (Boolean)attributes.get(
			"identityCardRecoveryApproved");

		if (identityCardRecoveryApproved != null) {
			setIdentityCardRecoveryApproved(identityCardRecoveryApproved);
		}

		String identityCardRecoveryComment = (String)attributes.get(
			"identityCardRecoveryComment");

		if (identityCardRecoveryComment != null) {
			setIdentityCardRecoveryComment(identityCardRecoveryComment);
		}

		Date updatedDate = (Date)attributes.get("updatedDate");

		if (updatedDate != null) {
			setUpdatedDate(updatedDate);
		}
	}

	@Override
	public AdminForm cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the access card recovery amt of this admin form.
	 *
	 * @return the access card recovery amt of this admin form
	 */
	@Override
	public String getAccessCardRecoveryAmt() {
		return model.getAccessCardRecoveryAmt();
	}

	/**
	 * Returns the access card recovery amt status of this admin form.
	 *
	 * @return the access card recovery amt status of this admin form
	 */
	@Override
	public int getAccessCardRecoveryAmtStatus() {
		return model.getAccessCardRecoveryAmtStatus();
	}

	/**
	 * Returns the access card recovery approved of this admin form.
	 *
	 * @return the access card recovery approved of this admin form
	 */
	@Override
	public boolean getAccessCardRecoveryApproved() {
		return model.getAccessCardRecoveryApproved();
	}

	/**
	 * Returns the access card recovery comment of this admin form.
	 *
	 * @return the access card recovery comment of this admin form
	 */
	@Override
	public String getAccessCardRecoveryComment() {
		return model.getAccessCardRecoveryComment();
	}

	/**
	 * Returns the exit ID of this admin form.
	 *
	 * @return the exit ID of this admin form
	 */
	@Override
	public long getExitId() {
		return model.getExitId();
	}

	/**
	 * Returns the ID of this admin form.
	 *
	 * @return the ID of this admin form
	 */
	@Override
	public long getId() {
		return model.getId();
	}

	/**
	 * Returns the identity card recovery amt of this admin form.
	 *
	 * @return the identity card recovery amt of this admin form
	 */
	@Override
	public String getIdentityCardRecoveryAmt() {
		return model.getIdentityCardRecoveryAmt();
	}

	/**
	 * Returns the identity card recovery approved of this admin form.
	 *
	 * @return the identity card recovery approved of this admin form
	 */
	@Override
	public boolean getIdentityCardRecoveryApproved() {
		return model.getIdentityCardRecoveryApproved();
	}

	/**
	 * Returns the identity card recovery comment of this admin form.
	 *
	 * @return the identity card recovery comment of this admin form
	 */
	@Override
	public String getIdentityCardRecoveryComment() {
		return model.getIdentityCardRecoveryComment();
	}

	/**
	 * Returns the identity card recovery status of this admin form.
	 *
	 * @return the identity card recovery status of this admin form
	 */
	@Override
	public int getIdentityCardRecoveryStatus() {
		return model.getIdentityCardRecoveryStatus();
	}

	/**
	 * Returns the infra recovery amt of this admin form.
	 *
	 * @return the infra recovery amt of this admin form
	 */
	@Override
	public String getInfraRecoveryAmt() {
		return model.getInfraRecoveryAmt();
	}

	/**
	 * Returns the infra recovery amt status of this admin form.
	 *
	 * @return the infra recovery amt status of this admin form
	 */
	@Override
	public int getInfraRecoveryAmtStatus() {
		return model.getInfraRecoveryAmtStatus();
	}

	/**
	 * Returns the infra recovery approved of this admin form.
	 *
	 * @return the infra recovery approved of this admin form
	 */
	@Override
	public boolean getInfraRecoveryApproved() {
		return model.getInfraRecoveryApproved();
	}

	/**
	 * Returns the infra recovery comment of this admin form.
	 *
	 * @return the infra recovery comment of this admin form
	 */
	@Override
	public String getInfraRecoveryComment() {
		return model.getInfraRecoveryComment();
	}

	/**
	 * Returns the key recovery approved of this admin form.
	 *
	 * @return the key recovery approved of this admin form
	 */
	@Override
	public boolean getKeyRecoveryApproved() {
		return model.getKeyRecoveryApproved();
	}

	/**
	 * Returns the key recovery comment of this admin form.
	 *
	 * @return the key recovery comment of this admin form
	 */
	@Override
	public String getKeyRecoveryComment() {
		return model.getKeyRecoveryComment();
	}

	/**
	 * Returns the keys recovery amt of this admin form.
	 *
	 * @return the keys recovery amt of this admin form
	 */
	@Override
	public String getKeysRecoveryAmt() {
		return model.getKeysRecoveryAmt();
	}

	/**
	 * Returns the keys recovery amt status of this admin form.
	 *
	 * @return the keys recovery amt status of this admin form
	 */
	@Override
	public int getKeysRecoveryAmtStatus() {
		return model.getKeysRecoveryAmtStatus();
	}

	/**
	 * Returns the library recovery amt of this admin form.
	 *
	 * @return the library recovery amt of this admin form
	 */
	@Override
	public String getLibraryRecoveryAmt() {
		return model.getLibraryRecoveryAmt();
	}

	/**
	 * Returns the library recovery amt status of this admin form.
	 *
	 * @return the library recovery amt status of this admin form
	 */
	@Override
	public int getLibraryRecoveryAmtStatus() {
		return model.getLibraryRecoveryAmtStatus();
	}

	/**
	 * Returns the library recovery approved of this admin form.
	 *
	 * @return the library recovery approved of this admin form
	 */
	@Override
	public boolean getLibraryRecoveryApproved() {
		return model.getLibraryRecoveryApproved();
	}

	/**
	 * Returns the library recovery comment of this admin form.
	 *
	 * @return the library recovery comment of this admin form
	 */
	@Override
	public String getLibraryRecoveryComment() {
		return model.getLibraryRecoveryComment();
	}

	/**
	 * Returns the lunch recovery amt of this admin form.
	 *
	 * @return the lunch recovery amt of this admin form
	 */
	@Override
	public String getLunchRecoveryAmt() {
		return model.getLunchRecoveryAmt();
	}

	/**
	 * Returns the lunch recovery amt status of this admin form.
	 *
	 * @return the lunch recovery amt status of this admin form
	 */
	@Override
	public int getLunchRecoveryAmtStatus() {
		return model.getLunchRecoveryAmtStatus();
	}

	/**
	 * Returns the lunch recovery approved of this admin form.
	 *
	 * @return the lunch recovery approved of this admin form
	 */
	@Override
	public boolean getLunchRecoveryApproved() {
		return model.getLunchRecoveryApproved();
	}

	/**
	 * Returns the lunch recovery comment of this admin form.
	 *
	 * @return the lunch recovery comment of this admin form
	 */
	@Override
	public String getLunchRecoveryComment() {
		return model.getLunchRecoveryComment();
	}

	/**
	 * Returns the primary key of this admin form.
	 *
	 * @return the primary key of this admin form
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the sports recovery amt of this admin form.
	 *
	 * @return the sports recovery amt of this admin form
	 */
	@Override
	public String getSportsRecoveryAmt() {
		return model.getSportsRecoveryAmt();
	}

	/**
	 * Returns the sports recovery amt status of this admin form.
	 *
	 * @return the sports recovery amt status of this admin form
	 */
	@Override
	public int getSportsRecoveryAmtStatus() {
		return model.getSportsRecoveryAmtStatus();
	}

	/**
	 * Returns the sports recovery approved of this admin form.
	 *
	 * @return the sports recovery approved of this admin form
	 */
	@Override
	public boolean getSportsRecoveryApproved() {
		return model.getSportsRecoveryApproved();
	}

	/**
	 * Returns the sports recovery comment of this admin form.
	 *
	 * @return the sports recovery comment of this admin form
	 */
	@Override
	public String getSportsRecoveryComment() {
		return model.getSportsRecoveryComment();
	}

	/**
	 * Returns the stationary recovery amt of this admin form.
	 *
	 * @return the stationary recovery amt of this admin form
	 */
	@Override
	public String getStationaryRecoveryAmt() {
		return model.getStationaryRecoveryAmt();
	}

	/**
	 * Returns the stationary recovery amt status of this admin form.
	 *
	 * @return the stationary recovery amt status of this admin form
	 */
	@Override
	public int getStationaryRecoveryAmtStatus() {
		return model.getStationaryRecoveryAmtStatus();
	}

	/**
	 * Returns the stationary recovery approved of this admin form.
	 *
	 * @return the stationary recovery approved of this admin form
	 */
	@Override
	public boolean getStationaryRecoveryApproved() {
		return model.getStationaryRecoveryApproved();
	}

	/**
	 * Returns the stationary recovery comment of this admin form.
	 *
	 * @return the stationary recovery comment of this admin form
	 */
	@Override
	public String getStationaryRecoveryComment() {
		return model.getStationaryRecoveryComment();
	}

	/**
	 * Returns the updated date of this admin form.
	 *
	 * @return the updated date of this admin form
	 */
	@Override
	public Date getUpdatedDate() {
		return model.getUpdatedDate();
	}

	/**
	 * Returns <code>true</code> if this admin form is access card recovery approved.
	 *
	 * @return <code>true</code> if this admin form is access card recovery approved; <code>false</code> otherwise
	 */
	@Override
	public boolean isAccessCardRecoveryApproved() {
		return model.isAccessCardRecoveryApproved();
	}

	/**
	 * Returns <code>true</code> if this admin form is identity card recovery approved.
	 *
	 * @return <code>true</code> if this admin form is identity card recovery approved; <code>false</code> otherwise
	 */
	@Override
	public boolean isIdentityCardRecoveryApproved() {
		return model.isIdentityCardRecoveryApproved();
	}

	/**
	 * Returns <code>true</code> if this admin form is infra recovery approved.
	 *
	 * @return <code>true</code> if this admin form is infra recovery approved; <code>false</code> otherwise
	 */
	@Override
	public boolean isInfraRecoveryApproved() {
		return model.isInfraRecoveryApproved();
	}

	/**
	 * Returns <code>true</code> if this admin form is key recovery approved.
	 *
	 * @return <code>true</code> if this admin form is key recovery approved; <code>false</code> otherwise
	 */
	@Override
	public boolean isKeyRecoveryApproved() {
		return model.isKeyRecoveryApproved();
	}

	/**
	 * Returns <code>true</code> if this admin form is library recovery approved.
	 *
	 * @return <code>true</code> if this admin form is library recovery approved; <code>false</code> otherwise
	 */
	@Override
	public boolean isLibraryRecoveryApproved() {
		return model.isLibraryRecoveryApproved();
	}

	/**
	 * Returns <code>true</code> if this admin form is lunch recovery approved.
	 *
	 * @return <code>true</code> if this admin form is lunch recovery approved; <code>false</code> otherwise
	 */
	@Override
	public boolean isLunchRecoveryApproved() {
		return model.isLunchRecoveryApproved();
	}

	/**
	 * Returns <code>true</code> if this admin form is sports recovery approved.
	 *
	 * @return <code>true</code> if this admin form is sports recovery approved; <code>false</code> otherwise
	 */
	@Override
	public boolean isSportsRecoveryApproved() {
		return model.isSportsRecoveryApproved();
	}

	/**
	 * Returns <code>true</code> if this admin form is stationary recovery approved.
	 *
	 * @return <code>true</code> if this admin form is stationary recovery approved; <code>false</code> otherwise
	 */
	@Override
	public boolean isStationaryRecoveryApproved() {
		return model.isStationaryRecoveryApproved();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the access card recovery amt of this admin form.
	 *
	 * @param accessCardRecoveryAmt the access card recovery amt of this admin form
	 */
	@Override
	public void setAccessCardRecoveryAmt(String accessCardRecoveryAmt) {
		model.setAccessCardRecoveryAmt(accessCardRecoveryAmt);
	}

	/**
	 * Sets the access card recovery amt status of this admin form.
	 *
	 * @param accessCardRecoveryAmtStatus the access card recovery amt status of this admin form
	 */
	@Override
	public void setAccessCardRecoveryAmtStatus(
		int accessCardRecoveryAmtStatus) {

		model.setAccessCardRecoveryAmtStatus(accessCardRecoveryAmtStatus);
	}

	/**
	 * Sets whether this admin form is access card recovery approved.
	 *
	 * @param accessCardRecoveryApproved the access card recovery approved of this admin form
	 */
	@Override
	public void setAccessCardRecoveryApproved(
		boolean accessCardRecoveryApproved) {

		model.setAccessCardRecoveryApproved(accessCardRecoveryApproved);
	}

	/**
	 * Sets the access card recovery comment of this admin form.
	 *
	 * @param accessCardRecoveryComment the access card recovery comment of this admin form
	 */
	@Override
	public void setAccessCardRecoveryComment(String accessCardRecoveryComment) {
		model.setAccessCardRecoveryComment(accessCardRecoveryComment);
	}

	/**
	 * Sets the exit ID of this admin form.
	 *
	 * @param exitId the exit ID of this admin form
	 */
	@Override
	public void setExitId(long exitId) {
		model.setExitId(exitId);
	}

	/**
	 * Sets the ID of this admin form.
	 *
	 * @param id the ID of this admin form
	 */
	@Override
	public void setId(long id) {
		model.setId(id);
	}

	/**
	 * Sets the identity card recovery amt of this admin form.
	 *
	 * @param identityCardRecoveryAmt the identity card recovery amt of this admin form
	 */
	@Override
	public void setIdentityCardRecoveryAmt(String identityCardRecoveryAmt) {
		model.setIdentityCardRecoveryAmt(identityCardRecoveryAmt);
	}

	/**
	 * Sets whether this admin form is identity card recovery approved.
	 *
	 * @param identityCardRecoveryApproved the identity card recovery approved of this admin form
	 */
	@Override
	public void setIdentityCardRecoveryApproved(
		boolean identityCardRecoveryApproved) {

		model.setIdentityCardRecoveryApproved(identityCardRecoveryApproved);
	}

	/**
	 * Sets the identity card recovery comment of this admin form.
	 *
	 * @param identityCardRecoveryComment the identity card recovery comment of this admin form
	 */
	@Override
	public void setIdentityCardRecoveryComment(
		String identityCardRecoveryComment) {

		model.setIdentityCardRecoveryComment(identityCardRecoveryComment);
	}

	/**
	 * Sets the identity card recovery status of this admin form.
	 *
	 * @param identityCardRecoveryStatus the identity card recovery status of this admin form
	 */
	@Override
	public void setIdentityCardRecoveryStatus(int identityCardRecoveryStatus) {
		model.setIdentityCardRecoveryStatus(identityCardRecoveryStatus);
	}

	/**
	 * Sets the infra recovery amt of this admin form.
	 *
	 * @param infraRecoveryAmt the infra recovery amt of this admin form
	 */
	@Override
	public void setInfraRecoveryAmt(String infraRecoveryAmt) {
		model.setInfraRecoveryAmt(infraRecoveryAmt);
	}

	/**
	 * Sets the infra recovery amt status of this admin form.
	 *
	 * @param infraRecoveryAmtStatus the infra recovery amt status of this admin form
	 */
	@Override
	public void setInfraRecoveryAmtStatus(int infraRecoveryAmtStatus) {
		model.setInfraRecoveryAmtStatus(infraRecoveryAmtStatus);
	}

	/**
	 * Sets whether this admin form is infra recovery approved.
	 *
	 * @param infraRecoveryApproved the infra recovery approved of this admin form
	 */
	@Override
	public void setInfraRecoveryApproved(boolean infraRecoveryApproved) {
		model.setInfraRecoveryApproved(infraRecoveryApproved);
	}

	/**
	 * Sets the infra recovery comment of this admin form.
	 *
	 * @param infraRecoveryComment the infra recovery comment of this admin form
	 */
	@Override
	public void setInfraRecoveryComment(String infraRecoveryComment) {
		model.setInfraRecoveryComment(infraRecoveryComment);
	}

	/**
	 * Sets whether this admin form is key recovery approved.
	 *
	 * @param keyRecoveryApproved the key recovery approved of this admin form
	 */
	@Override
	public void setKeyRecoveryApproved(boolean keyRecoveryApproved) {
		model.setKeyRecoveryApproved(keyRecoveryApproved);
	}

	/**
	 * Sets the key recovery comment of this admin form.
	 *
	 * @param keyRecoveryComment the key recovery comment of this admin form
	 */
	@Override
	public void setKeyRecoveryComment(String keyRecoveryComment) {
		model.setKeyRecoveryComment(keyRecoveryComment);
	}

	/**
	 * Sets the keys recovery amt of this admin form.
	 *
	 * @param keysRecoveryAmt the keys recovery amt of this admin form
	 */
	@Override
	public void setKeysRecoveryAmt(String keysRecoveryAmt) {
		model.setKeysRecoveryAmt(keysRecoveryAmt);
	}

	/**
	 * Sets the keys recovery amt status of this admin form.
	 *
	 * @param keysRecoveryAmtStatus the keys recovery amt status of this admin form
	 */
	@Override
	public void setKeysRecoveryAmtStatus(int keysRecoveryAmtStatus) {
		model.setKeysRecoveryAmtStatus(keysRecoveryAmtStatus);
	}

	/**
	 * Sets the library recovery amt of this admin form.
	 *
	 * @param libraryRecoveryAmt the library recovery amt of this admin form
	 */
	@Override
	public void setLibraryRecoveryAmt(String libraryRecoveryAmt) {
		model.setLibraryRecoveryAmt(libraryRecoveryAmt);
	}

	/**
	 * Sets the library recovery amt status of this admin form.
	 *
	 * @param libraryRecoveryAmtStatus the library recovery amt status of this admin form
	 */
	@Override
	public void setLibraryRecoveryAmtStatus(int libraryRecoveryAmtStatus) {
		model.setLibraryRecoveryAmtStatus(libraryRecoveryAmtStatus);
	}

	/**
	 * Sets whether this admin form is library recovery approved.
	 *
	 * @param libraryRecoveryApproved the library recovery approved of this admin form
	 */
	@Override
	public void setLibraryRecoveryApproved(boolean libraryRecoveryApproved) {
		model.setLibraryRecoveryApproved(libraryRecoveryApproved);
	}

	/**
	 * Sets the library recovery comment of this admin form.
	 *
	 * @param libraryRecoveryComment the library recovery comment of this admin form
	 */
	@Override
	public void setLibraryRecoveryComment(String libraryRecoveryComment) {
		model.setLibraryRecoveryComment(libraryRecoveryComment);
	}

	/**
	 * Sets the lunch recovery amt of this admin form.
	 *
	 * @param lunchRecoveryAmt the lunch recovery amt of this admin form
	 */
	@Override
	public void setLunchRecoveryAmt(String lunchRecoveryAmt) {
		model.setLunchRecoveryAmt(lunchRecoveryAmt);
	}

	/**
	 * Sets the lunch recovery amt status of this admin form.
	 *
	 * @param lunchRecoveryAmtStatus the lunch recovery amt status of this admin form
	 */
	@Override
	public void setLunchRecoveryAmtStatus(int lunchRecoveryAmtStatus) {
		model.setLunchRecoveryAmtStatus(lunchRecoveryAmtStatus);
	}

	/**
	 * Sets whether this admin form is lunch recovery approved.
	 *
	 * @param lunchRecoveryApproved the lunch recovery approved of this admin form
	 */
	@Override
	public void setLunchRecoveryApproved(boolean lunchRecoveryApproved) {
		model.setLunchRecoveryApproved(lunchRecoveryApproved);
	}

	/**
	 * Sets the lunch recovery comment of this admin form.
	 *
	 * @param lunchRecoveryComment the lunch recovery comment of this admin form
	 */
	@Override
	public void setLunchRecoveryComment(String lunchRecoveryComment) {
		model.setLunchRecoveryComment(lunchRecoveryComment);
	}

	/**
	 * Sets the primary key of this admin form.
	 *
	 * @param primaryKey the primary key of this admin form
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the sports recovery amt of this admin form.
	 *
	 * @param sportsRecoveryAmt the sports recovery amt of this admin form
	 */
	@Override
	public void setSportsRecoveryAmt(String sportsRecoveryAmt) {
		model.setSportsRecoveryAmt(sportsRecoveryAmt);
	}

	/**
	 * Sets the sports recovery amt status of this admin form.
	 *
	 * @param sportsRecoveryAmtStatus the sports recovery amt status of this admin form
	 */
	@Override
	public void setSportsRecoveryAmtStatus(int sportsRecoveryAmtStatus) {
		model.setSportsRecoveryAmtStatus(sportsRecoveryAmtStatus);
	}

	/**
	 * Sets whether this admin form is sports recovery approved.
	 *
	 * @param sportsRecoveryApproved the sports recovery approved of this admin form
	 */
	@Override
	public void setSportsRecoveryApproved(boolean sportsRecoveryApproved) {
		model.setSportsRecoveryApproved(sportsRecoveryApproved);
	}

	/**
	 * Sets the sports recovery comment of this admin form.
	 *
	 * @param sportsRecoveryComment the sports recovery comment of this admin form
	 */
	@Override
	public void setSportsRecoveryComment(String sportsRecoveryComment) {
		model.setSportsRecoveryComment(sportsRecoveryComment);
	}

	/**
	 * Sets the stationary recovery amt of this admin form.
	 *
	 * @param stationaryRecoveryAmt the stationary recovery amt of this admin form
	 */
	@Override
	public void setStationaryRecoveryAmt(String stationaryRecoveryAmt) {
		model.setStationaryRecoveryAmt(stationaryRecoveryAmt);
	}

	/**
	 * Sets the stationary recovery amt status of this admin form.
	 *
	 * @param stationaryRecoveryAmtStatus the stationary recovery amt status of this admin form
	 */
	@Override
	public void setStationaryRecoveryAmtStatus(
		int stationaryRecoveryAmtStatus) {

		model.setStationaryRecoveryAmtStatus(stationaryRecoveryAmtStatus);
	}

	/**
	 * Sets whether this admin form is stationary recovery approved.
	 *
	 * @param stationaryRecoveryApproved the stationary recovery approved of this admin form
	 */
	@Override
	public void setStationaryRecoveryApproved(
		boolean stationaryRecoveryApproved) {

		model.setStationaryRecoveryApproved(stationaryRecoveryApproved);
	}

	/**
	 * Sets the stationary recovery comment of this admin form.
	 *
	 * @param stationaryRecoveryComment the stationary recovery comment of this admin form
	 */
	@Override
	public void setStationaryRecoveryComment(String stationaryRecoveryComment) {
		model.setStationaryRecoveryComment(stationaryRecoveryComment);
	}

	/**
	 * Sets the updated date of this admin form.
	 *
	 * @param updatedDate the updated date of this admin form
	 */
	@Override
	public void setUpdatedDate(Date updatedDate) {
		model.setUpdatedDate(updatedDate);
	}

	@Override
	protected AdminFormWrapper wrap(AdminForm adminForm) {
		return new AdminFormWrapper(adminForm);
	}

}