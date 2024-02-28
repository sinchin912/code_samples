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

package com.trantorinc.synergy.lms.core.model;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link Scheduler}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Scheduler
 * @generated
 */
public class SchedulerWrapper
	extends BaseModelWrapper<Scheduler>
	implements ModelWrapper<Scheduler>, Scheduler {

	public SchedulerWrapper(Scheduler scheduler) {
		super(scheduler);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("schedulerId", getSchedulerId());
		attributes.put("name", getName());
		attributes.put("status", isStatus());
		attributes.put("onDate", getOnDate());
		attributes.put("runDate", getRunDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long schedulerId = (Long)attributes.get("schedulerId");

		if (schedulerId != null) {
			setSchedulerId(schedulerId);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		Boolean status = (Boolean)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}

		Date onDate = (Date)attributes.get("onDate");

		if (onDate != null) {
			setOnDate(onDate);
		}

		Date runDate = (Date)attributes.get("runDate");

		if (runDate != null) {
			setRunDate(runDate);
		}
	}

	@Override
	public Scheduler cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the name of this scheduler.
	 *
	 * @return the name of this scheduler
	 */
	@Override
	public String getName() {
		return model.getName();
	}

	/**
	 * Returns the on date of this scheduler.
	 *
	 * @return the on date of this scheduler
	 */
	@Override
	public Date getOnDate() {
		return model.getOnDate();
	}

	/**
	 * Returns the primary key of this scheduler.
	 *
	 * @return the primary key of this scheduler
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the run date of this scheduler.
	 *
	 * @return the run date of this scheduler
	 */
	@Override
	public Date getRunDate() {
		return model.getRunDate();
	}

	/**
	 * Returns the scheduler ID of this scheduler.
	 *
	 * @return the scheduler ID of this scheduler
	 */
	@Override
	public long getSchedulerId() {
		return model.getSchedulerId();
	}

	/**
	 * Returns the status of this scheduler.
	 *
	 * @return the status of this scheduler
	 */
	@Override
	public boolean getStatus() {
		return model.getStatus();
	}

	/**
	 * Returns <code>true</code> if this scheduler is status.
	 *
	 * @return <code>true</code> if this scheduler is status; <code>false</code> otherwise
	 */
	@Override
	public boolean isStatus() {
		return model.isStatus();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the name of this scheduler.
	 *
	 * @param name the name of this scheduler
	 */
	@Override
	public void setName(String name) {
		model.setName(name);
	}

	/**
	 * Sets the on date of this scheduler.
	 *
	 * @param onDate the on date of this scheduler
	 */
	@Override
	public void setOnDate(Date onDate) {
		model.setOnDate(onDate);
	}

	/**
	 * Sets the primary key of this scheduler.
	 *
	 * @param primaryKey the primary key of this scheduler
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the run date of this scheduler.
	 *
	 * @param runDate the run date of this scheduler
	 */
	@Override
	public void setRunDate(Date runDate) {
		model.setRunDate(runDate);
	}

	/**
	 * Sets the scheduler ID of this scheduler.
	 *
	 * @param schedulerId the scheduler ID of this scheduler
	 */
	@Override
	public void setSchedulerId(long schedulerId) {
		model.setSchedulerId(schedulerId);
	}

	/**
	 * Sets whether this scheduler is status.
	 *
	 * @param status the status of this scheduler
	 */
	@Override
	public void setStatus(boolean status) {
		model.setStatus(status);
	}

	@Override
	protected SchedulerWrapper wrap(Scheduler scheduler) {
		return new SchedulerWrapper(scheduler);
	}

}