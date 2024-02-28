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

package com.trantorinc.synergy.performance.core.model;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link ReviewRollback}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ReviewRollback
 * @generated
 */
public class ReviewRollbackWrapper
	extends BaseModelWrapper<ReviewRollback>
	implements ModelWrapper<ReviewRollback>, ReviewRollback {

	public ReviewRollbackWrapper(ReviewRollback reviewRollback) {
		super(reviewRollback);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("rollbackId", getRollbackId());
		attributes.put("reviewId", getReviewId());
		attributes.put("requestBy", getRequestBy());
		attributes.put("requestDate", getRequestDate());
		attributes.put("actionDate", getActionDate());
		attributes.put("status", getStatus());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long rollbackId = (Long)attributes.get("rollbackId");

		if (rollbackId != null) {
			setRollbackId(rollbackId);
		}

		Long reviewId = (Long)attributes.get("reviewId");

		if (reviewId != null) {
			setReviewId(reviewId);
		}

		String requestBy = (String)attributes.get("requestBy");

		if (requestBy != null) {
			setRequestBy(requestBy);
		}

		Date requestDate = (Date)attributes.get("requestDate");

		if (requestDate != null) {
			setRequestDate(requestDate);
		}

		Date actionDate = (Date)attributes.get("actionDate");

		if (actionDate != null) {
			setActionDate(actionDate);
		}

		Integer status = (Integer)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}
	}

	@Override
	public ReviewRollback cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the action date of this review rollback.
	 *
	 * @return the action date of this review rollback
	 */
	@Override
	public Date getActionDate() {
		return model.getActionDate();
	}

	/**
	 * Returns the primary key of this review rollback.
	 *
	 * @return the primary key of this review rollback
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the request by of this review rollback.
	 *
	 * @return the request by of this review rollback
	 */
	@Override
	public String getRequestBy() {
		return model.getRequestBy();
	}

	/**
	 * Returns the request date of this review rollback.
	 *
	 * @return the request date of this review rollback
	 */
	@Override
	public Date getRequestDate() {
		return model.getRequestDate();
	}

	/**
	 * Returns the review ID of this review rollback.
	 *
	 * @return the review ID of this review rollback
	 */
	@Override
	public long getReviewId() {
		return model.getReviewId();
	}

	/**
	 * Returns the rollback ID of this review rollback.
	 *
	 * @return the rollback ID of this review rollback
	 */
	@Override
	public long getRollbackId() {
		return model.getRollbackId();
	}

	/**
	 * Returns the status of this review rollback.
	 *
	 * @return the status of this review rollback
	 */
	@Override
	public int getStatus() {
		return model.getStatus();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the action date of this review rollback.
	 *
	 * @param actionDate the action date of this review rollback
	 */
	@Override
	public void setActionDate(Date actionDate) {
		model.setActionDate(actionDate);
	}

	/**
	 * Sets the primary key of this review rollback.
	 *
	 * @param primaryKey the primary key of this review rollback
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the request by of this review rollback.
	 *
	 * @param requestBy the request by of this review rollback
	 */
	@Override
	public void setRequestBy(String requestBy) {
		model.setRequestBy(requestBy);
	}

	/**
	 * Sets the request date of this review rollback.
	 *
	 * @param requestDate the request date of this review rollback
	 */
	@Override
	public void setRequestDate(Date requestDate) {
		model.setRequestDate(requestDate);
	}

	/**
	 * Sets the review ID of this review rollback.
	 *
	 * @param reviewId the review ID of this review rollback
	 */
	@Override
	public void setReviewId(long reviewId) {
		model.setReviewId(reviewId);
	}

	/**
	 * Sets the rollback ID of this review rollback.
	 *
	 * @param rollbackId the rollback ID of this review rollback
	 */
	@Override
	public void setRollbackId(long rollbackId) {
		model.setRollbackId(rollbackId);
	}

	/**
	 * Sets the status of this review rollback.
	 *
	 * @param status the status of this review rollback
	 */
	@Override
	public void setStatus(int status) {
		model.setStatus(status);
	}

	@Override
	protected ReviewRollbackWrapper wrap(ReviewRollback reviewRollback) {
		return new ReviewRollbackWrapper(reviewRollback);
	}

}