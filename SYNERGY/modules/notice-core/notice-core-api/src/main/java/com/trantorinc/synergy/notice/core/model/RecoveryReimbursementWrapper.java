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
 * This class is a wrapper for {@link RecoveryReimbursement}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see RecoveryReimbursement
 * @generated
 */
public class RecoveryReimbursementWrapper
	extends BaseModelWrapper<RecoveryReimbursement>
	implements ModelWrapper<RecoveryReimbursement>, RecoveryReimbursement {

	public RecoveryReimbursementWrapper(
		RecoveryReimbursement recoveryReimbursement) {

		super(recoveryReimbursement);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("id", getId());
		attributes.put("departmentFormId", getDepartmentFormId());
		attributes.put("department", getDepartment());
		attributes.put("recoveryType", isRecoveryType());
		attributes.put("recoveryItem", getRecoveryItem());
		attributes.put("recoveryAmount", getRecoveryAmount());
		attributes.put("recoveryStatus", getRecoveryStatus());
		attributes.put("reimbursementItem", getReimbursementItem());
		attributes.put("reimbursementAmount", getReimbursementAmount());
		attributes.put("reimbursementStatus", getReimbursementStatus());
		attributes.put("approved", isApproved());
		attributes.put("comment", getComment());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long id = (Long)attributes.get("id");

		if (id != null) {
			setId(id);
		}

		Long departmentFormId = (Long)attributes.get("departmentFormId");

		if (departmentFormId != null) {
			setDepartmentFormId(departmentFormId);
		}

		Integer department = (Integer)attributes.get("department");

		if (department != null) {
			setDepartment(department);
		}

		Boolean recoveryType = (Boolean)attributes.get("recoveryType");

		if (recoveryType != null) {
			setRecoveryType(recoveryType);
		}

		String recoveryItem = (String)attributes.get("recoveryItem");

		if (recoveryItem != null) {
			setRecoveryItem(recoveryItem);
		}

		String recoveryAmount = (String)attributes.get("recoveryAmount");

		if (recoveryAmount != null) {
			setRecoveryAmount(recoveryAmount);
		}

		Integer recoveryStatus = (Integer)attributes.get("recoveryStatus");

		if (recoveryStatus != null) {
			setRecoveryStatus(recoveryStatus);
		}

		String reimbursementItem = (String)attributes.get("reimbursementItem");

		if (reimbursementItem != null) {
			setReimbursementItem(reimbursementItem);
		}

		String reimbursementAmount = (String)attributes.get(
			"reimbursementAmount");

		if (reimbursementAmount != null) {
			setReimbursementAmount(reimbursementAmount);
		}

		Integer reimbursementStatus = (Integer)attributes.get(
			"reimbursementStatus");

		if (reimbursementStatus != null) {
			setReimbursementStatus(reimbursementStatus);
		}

		Boolean approved = (Boolean)attributes.get("approved");

		if (approved != null) {
			setApproved(approved);
		}

		String comment = (String)attributes.get("comment");

		if (comment != null) {
			setComment(comment);
		}
	}

	@Override
	public RecoveryReimbursement cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the approved of this recovery reimbursement.
	 *
	 * @return the approved of this recovery reimbursement
	 */
	@Override
	public boolean getApproved() {
		return model.getApproved();
	}

	/**
	 * Returns the comment of this recovery reimbursement.
	 *
	 * @return the comment of this recovery reimbursement
	 */
	@Override
	public String getComment() {
		return model.getComment();
	}

	/**
	 * Returns the department of this recovery reimbursement.
	 *
	 * @return the department of this recovery reimbursement
	 */
	@Override
	public int getDepartment() {
		return model.getDepartment();
	}

	/**
	 * Returns the department form ID of this recovery reimbursement.
	 *
	 * @return the department form ID of this recovery reimbursement
	 */
	@Override
	public long getDepartmentFormId() {
		return model.getDepartmentFormId();
	}

	/**
	 * Returns the ID of this recovery reimbursement.
	 *
	 * @return the ID of this recovery reimbursement
	 */
	@Override
	public long getId() {
		return model.getId();
	}

	/**
	 * Returns the primary key of this recovery reimbursement.
	 *
	 * @return the primary key of this recovery reimbursement
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the recovery amount of this recovery reimbursement.
	 *
	 * @return the recovery amount of this recovery reimbursement
	 */
	@Override
	public String getRecoveryAmount() {
		return model.getRecoveryAmount();
	}

	/**
	 * Returns the recovery item of this recovery reimbursement.
	 *
	 * @return the recovery item of this recovery reimbursement
	 */
	@Override
	public String getRecoveryItem() {
		return model.getRecoveryItem();
	}

	/**
	 * Returns the recovery status of this recovery reimbursement.
	 *
	 * @return the recovery status of this recovery reimbursement
	 */
	@Override
	public int getRecoveryStatus() {
		return model.getRecoveryStatus();
	}

	/**
	 * Returns the recovery type of this recovery reimbursement.
	 *
	 * @return the recovery type of this recovery reimbursement
	 */
	@Override
	public boolean getRecoveryType() {
		return model.getRecoveryType();
	}

	/**
	 * Returns the reimbursement amount of this recovery reimbursement.
	 *
	 * @return the reimbursement amount of this recovery reimbursement
	 */
	@Override
	public String getReimbursementAmount() {
		return model.getReimbursementAmount();
	}

	/**
	 * Returns the reimbursement item of this recovery reimbursement.
	 *
	 * @return the reimbursement item of this recovery reimbursement
	 */
	@Override
	public String getReimbursementItem() {
		return model.getReimbursementItem();
	}

	/**
	 * Returns the reimbursement status of this recovery reimbursement.
	 *
	 * @return the reimbursement status of this recovery reimbursement
	 */
	@Override
	public int getReimbursementStatus() {
		return model.getReimbursementStatus();
	}

	/**
	 * Returns <code>true</code> if this recovery reimbursement is approved.
	 *
	 * @return <code>true</code> if this recovery reimbursement is approved; <code>false</code> otherwise
	 */
	@Override
	public boolean isApproved() {
		return model.isApproved();
	}

	/**
	 * Returns <code>true</code> if this recovery reimbursement is recovery type.
	 *
	 * @return <code>true</code> if this recovery reimbursement is recovery type; <code>false</code> otherwise
	 */
	@Override
	public boolean isRecoveryType() {
		return model.isRecoveryType();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets whether this recovery reimbursement is approved.
	 *
	 * @param approved the approved of this recovery reimbursement
	 */
	@Override
	public void setApproved(boolean approved) {
		model.setApproved(approved);
	}

	/**
	 * Sets the comment of this recovery reimbursement.
	 *
	 * @param comment the comment of this recovery reimbursement
	 */
	@Override
	public void setComment(String comment) {
		model.setComment(comment);
	}

	/**
	 * Sets the department of this recovery reimbursement.
	 *
	 * @param department the department of this recovery reimbursement
	 */
	@Override
	public void setDepartment(int department) {
		model.setDepartment(department);
	}

	/**
	 * Sets the department form ID of this recovery reimbursement.
	 *
	 * @param departmentFormId the department form ID of this recovery reimbursement
	 */
	@Override
	public void setDepartmentFormId(long departmentFormId) {
		model.setDepartmentFormId(departmentFormId);
	}

	/**
	 * Sets the ID of this recovery reimbursement.
	 *
	 * @param id the ID of this recovery reimbursement
	 */
	@Override
	public void setId(long id) {
		model.setId(id);
	}

	/**
	 * Sets the primary key of this recovery reimbursement.
	 *
	 * @param primaryKey the primary key of this recovery reimbursement
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the recovery amount of this recovery reimbursement.
	 *
	 * @param recoveryAmount the recovery amount of this recovery reimbursement
	 */
	@Override
	public void setRecoveryAmount(String recoveryAmount) {
		model.setRecoveryAmount(recoveryAmount);
	}

	/**
	 * Sets the recovery item of this recovery reimbursement.
	 *
	 * @param recoveryItem the recovery item of this recovery reimbursement
	 */
	@Override
	public void setRecoveryItem(String recoveryItem) {
		model.setRecoveryItem(recoveryItem);
	}

	/**
	 * Sets the recovery status of this recovery reimbursement.
	 *
	 * @param recoveryStatus the recovery status of this recovery reimbursement
	 */
	@Override
	public void setRecoveryStatus(int recoveryStatus) {
		model.setRecoveryStatus(recoveryStatus);
	}

	/**
	 * Sets whether this recovery reimbursement is recovery type.
	 *
	 * @param recoveryType the recovery type of this recovery reimbursement
	 */
	@Override
	public void setRecoveryType(boolean recoveryType) {
		model.setRecoveryType(recoveryType);
	}

	/**
	 * Sets the reimbursement amount of this recovery reimbursement.
	 *
	 * @param reimbursementAmount the reimbursement amount of this recovery reimbursement
	 */
	@Override
	public void setReimbursementAmount(String reimbursementAmount) {
		model.setReimbursementAmount(reimbursementAmount);
	}

	/**
	 * Sets the reimbursement item of this recovery reimbursement.
	 *
	 * @param reimbursementItem the reimbursement item of this recovery reimbursement
	 */
	@Override
	public void setReimbursementItem(String reimbursementItem) {
		model.setReimbursementItem(reimbursementItem);
	}

	/**
	 * Sets the reimbursement status of this recovery reimbursement.
	 *
	 * @param reimbursementStatus the reimbursement status of this recovery reimbursement
	 */
	@Override
	public void setReimbursementStatus(int reimbursementStatus) {
		model.setReimbursementStatus(reimbursementStatus);
	}

	@Override
	protected RecoveryReimbursementWrapper wrap(
		RecoveryReimbursement recoveryReimbursement) {

		return new RecoveryReimbursementWrapper(recoveryReimbursement);
	}

}