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

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link ExitForm}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ExitForm
 * @generated
 */
public class ExitFormWrapper
	extends BaseModelWrapper<ExitForm>
	implements ExitForm, ModelWrapper<ExitForm> {

	public ExitFormWrapper(ExitForm exitForm) {
		super(exitForm);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("id", getId());
		attributes.put("resignationId", getResignationId());
		attributes.put("managerFormStatus", isManagerFormStatus());
		attributes.put("hrFormStatus", isHrFormStatus());
		attributes.put("itFormStatus", isItFormStatus());
		attributes.put("adminFormStatus", isAdminFormStatus());
		attributes.put("financeFormStatus", isFinanceFormStatus());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long id = (Long)attributes.get("id");

		if (id != null) {
			setId(id);
		}

		Long resignationId = (Long)attributes.get("resignationId");

		if (resignationId != null) {
			setResignationId(resignationId);
		}

		Boolean managerFormStatus = (Boolean)attributes.get(
			"managerFormStatus");

		if (managerFormStatus != null) {
			setManagerFormStatus(managerFormStatus);
		}

		Boolean hrFormStatus = (Boolean)attributes.get("hrFormStatus");

		if (hrFormStatus != null) {
			setHrFormStatus(hrFormStatus);
		}

		Boolean itFormStatus = (Boolean)attributes.get("itFormStatus");

		if (itFormStatus != null) {
			setItFormStatus(itFormStatus);
		}

		Boolean adminFormStatus = (Boolean)attributes.get("adminFormStatus");

		if (adminFormStatus != null) {
			setAdminFormStatus(adminFormStatus);
		}

		Boolean financeFormStatus = (Boolean)attributes.get(
			"financeFormStatus");

		if (financeFormStatus != null) {
			setFinanceFormStatus(financeFormStatus);
		}
	}

	@Override
	public ExitForm cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the admin form status of this exit form.
	 *
	 * @return the admin form status of this exit form
	 */
	@Override
	public boolean getAdminFormStatus() {
		return model.getAdminFormStatus();
	}

	/**
	 * Returns the finance form status of this exit form.
	 *
	 * @return the finance form status of this exit form
	 */
	@Override
	public boolean getFinanceFormStatus() {
		return model.getFinanceFormStatus();
	}

	/**
	 * Returns the hr form status of this exit form.
	 *
	 * @return the hr form status of this exit form
	 */
	@Override
	public boolean getHrFormStatus() {
		return model.getHrFormStatus();
	}

	/**
	 * Returns the ID of this exit form.
	 *
	 * @return the ID of this exit form
	 */
	@Override
	public long getId() {
		return model.getId();
	}

	/**
	 * Returns the it form status of this exit form.
	 *
	 * @return the it form status of this exit form
	 */
	@Override
	public boolean getItFormStatus() {
		return model.getItFormStatus();
	}

	/**
	 * Returns the manager form status of this exit form.
	 *
	 * @return the manager form status of this exit form
	 */
	@Override
	public boolean getManagerFormStatus() {
		return model.getManagerFormStatus();
	}

	/**
	 * Returns the primary key of this exit form.
	 *
	 * @return the primary key of this exit form
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the resignation ID of this exit form.
	 *
	 * @return the resignation ID of this exit form
	 */
	@Override
	public long getResignationId() {
		return model.getResignationId();
	}

	/**
	 * Returns <code>true</code> if this exit form is admin form status.
	 *
	 * @return <code>true</code> if this exit form is admin form status; <code>false</code> otherwise
	 */
	@Override
	public boolean isAdminFormStatus() {
		return model.isAdminFormStatus();
	}

	/**
	 * Returns <code>true</code> if this exit form is finance form status.
	 *
	 * @return <code>true</code> if this exit form is finance form status; <code>false</code> otherwise
	 */
	@Override
	public boolean isFinanceFormStatus() {
		return model.isFinanceFormStatus();
	}

	/**
	 * Returns <code>true</code> if this exit form is hr form status.
	 *
	 * @return <code>true</code> if this exit form is hr form status; <code>false</code> otherwise
	 */
	@Override
	public boolean isHrFormStatus() {
		return model.isHrFormStatus();
	}

	/**
	 * Returns <code>true</code> if this exit form is it form status.
	 *
	 * @return <code>true</code> if this exit form is it form status; <code>false</code> otherwise
	 */
	@Override
	public boolean isItFormStatus() {
		return model.isItFormStatus();
	}

	/**
	 * Returns <code>true</code> if this exit form is manager form status.
	 *
	 * @return <code>true</code> if this exit form is manager form status; <code>false</code> otherwise
	 */
	@Override
	public boolean isManagerFormStatus() {
		return model.isManagerFormStatus();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets whether this exit form is admin form status.
	 *
	 * @param adminFormStatus the admin form status of this exit form
	 */
	@Override
	public void setAdminFormStatus(boolean adminFormStatus) {
		model.setAdminFormStatus(adminFormStatus);
	}

	/**
	 * Sets whether this exit form is finance form status.
	 *
	 * @param financeFormStatus the finance form status of this exit form
	 */
	@Override
	public void setFinanceFormStatus(boolean financeFormStatus) {
		model.setFinanceFormStatus(financeFormStatus);
	}

	/**
	 * Sets whether this exit form is hr form status.
	 *
	 * @param hrFormStatus the hr form status of this exit form
	 */
	@Override
	public void setHrFormStatus(boolean hrFormStatus) {
		model.setHrFormStatus(hrFormStatus);
	}

	/**
	 * Sets the ID of this exit form.
	 *
	 * @param id the ID of this exit form
	 */
	@Override
	public void setId(long id) {
		model.setId(id);
	}

	/**
	 * Sets whether this exit form is it form status.
	 *
	 * @param itFormStatus the it form status of this exit form
	 */
	@Override
	public void setItFormStatus(boolean itFormStatus) {
		model.setItFormStatus(itFormStatus);
	}

	/**
	 * Sets whether this exit form is manager form status.
	 *
	 * @param managerFormStatus the manager form status of this exit form
	 */
	@Override
	public void setManagerFormStatus(boolean managerFormStatus) {
		model.setManagerFormStatus(managerFormStatus);
	}

	/**
	 * Sets the primary key of this exit form.
	 *
	 * @param primaryKey the primary key of this exit form
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the resignation ID of this exit form.
	 *
	 * @param resignationId the resignation ID of this exit form
	 */
	@Override
	public void setResignationId(long resignationId) {
		model.setResignationId(resignationId);
	}

	@Override
	protected ExitFormWrapper wrap(ExitForm exitForm) {
		return new ExitFormWrapper(exitForm);
	}

}