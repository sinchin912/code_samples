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

package com.trantorinc.synergy.probation.core.model;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link Probation}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Probation
 * @generated
 */
public class ProbationWrapper
	extends BaseModelWrapper<Probation>
	implements ModelWrapper<Probation>, Probation {

	public ProbationWrapper(Probation probation) {
		super(probation);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("ecode", getEcode());
		attributes.put("manager", getManager());
		attributes.put("reviewer", getReviewer());
		attributes.put("state", getState());
		attributes.put("stateName", getStateName());
		attributes.put("alertDate", getAlertDate());
		attributes.put("createDate", getCreateDate());
		attributes.put("updateDate", getUpdateDate());
		attributes.put("areaOfStrength", getAreaOfStrength());
		attributes.put("areaOfImprovement", getAreaOfImprovement());
		attributes.put("comment", getComment());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String ecode = (String)attributes.get("ecode");

		if (ecode != null) {
			setEcode(ecode);
		}

		String manager = (String)attributes.get("manager");

		if (manager != null) {
			setManager(manager);
		}

		String reviewer = (String)attributes.get("reviewer");

		if (reviewer != null) {
			setReviewer(reviewer);
		}

		Integer state = (Integer)attributes.get("state");

		if (state != null) {
			setState(state);
		}

		String stateName = (String)attributes.get("stateName");

		if (stateName != null) {
			setStateName(stateName);
		}

		Date alertDate = (Date)attributes.get("alertDate");

		if (alertDate != null) {
			setAlertDate(alertDate);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date updateDate = (Date)attributes.get("updateDate");

		if (updateDate != null) {
			setUpdateDate(updateDate);
		}

		String areaOfStrength = (String)attributes.get("areaOfStrength");

		if (areaOfStrength != null) {
			setAreaOfStrength(areaOfStrength);
		}

		String areaOfImprovement = (String)attributes.get("areaOfImprovement");

		if (areaOfImprovement != null) {
			setAreaOfImprovement(areaOfImprovement);
		}

		String comment = (String)attributes.get("comment");

		if (comment != null) {
			setComment(comment);
		}
	}

	@Override
	public Probation cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the alert date of this probation.
	 *
	 * @return the alert date of this probation
	 */
	@Override
	public Date getAlertDate() {
		return model.getAlertDate();
	}

	/**
	 * Returns the area of improvement of this probation.
	 *
	 * @return the area of improvement of this probation
	 */
	@Override
	public String getAreaOfImprovement() {
		return model.getAreaOfImprovement();
	}

	/**
	 * Returns the area of strength of this probation.
	 *
	 * @return the area of strength of this probation
	 */
	@Override
	public String getAreaOfStrength() {
		return model.getAreaOfStrength();
	}

	/**
	 * Returns the comment of this probation.
	 *
	 * @return the comment of this probation
	 */
	@Override
	public String getComment() {
		return model.getComment();
	}

	/**
	 * Returns the create date of this probation.
	 *
	 * @return the create date of this probation
	 */
	@Override
	public Date getCreateDate() {
		return model.getCreateDate();
	}

	/**
	 * Returns the ecode of this probation.
	 *
	 * @return the ecode of this probation
	 */
	@Override
	public String getEcode() {
		return model.getEcode();
	}

	/**
	 * Returns the manager of this probation.
	 *
	 * @return the manager of this probation
	 */
	@Override
	public String getManager() {
		return model.getManager();
	}

	/**
	 * Returns the primary key of this probation.
	 *
	 * @return the primary key of this probation
	 */
	@Override
	public String getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the reviewer of this probation.
	 *
	 * @return the reviewer of this probation
	 */
	@Override
	public String getReviewer() {
		return model.getReviewer();
	}

	/**
	 * Returns the state of this probation.
	 *
	 * @return the state of this probation
	 */
	@Override
	public int getState() {
		return model.getState();
	}

	/**
	 * Returns the state name of this probation.
	 *
	 * @return the state name of this probation
	 */
	@Override
	public String getStateName() {
		return model.getStateName();
	}

	/**
	 * Returns the update date of this probation.
	 *
	 * @return the update date of this probation
	 */
	@Override
	public Date getUpdateDate() {
		return model.getUpdateDate();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the alert date of this probation.
	 *
	 * @param alertDate the alert date of this probation
	 */
	@Override
	public void setAlertDate(Date alertDate) {
		model.setAlertDate(alertDate);
	}

	/**
	 * Sets the area of improvement of this probation.
	 *
	 * @param areaOfImprovement the area of improvement of this probation
	 */
	@Override
	public void setAreaOfImprovement(String areaOfImprovement) {
		model.setAreaOfImprovement(areaOfImprovement);
	}

	/**
	 * Sets the area of strength of this probation.
	 *
	 * @param areaOfStrength the area of strength of this probation
	 */
	@Override
	public void setAreaOfStrength(String areaOfStrength) {
		model.setAreaOfStrength(areaOfStrength);
	}

	/**
	 * Sets the comment of this probation.
	 *
	 * @param comment the comment of this probation
	 */
	@Override
	public void setComment(String comment) {
		model.setComment(comment);
	}

	/**
	 * Sets the create date of this probation.
	 *
	 * @param createDate the create date of this probation
	 */
	@Override
	public void setCreateDate(Date createDate) {
		model.setCreateDate(createDate);
	}

	/**
	 * Sets the ecode of this probation.
	 *
	 * @param ecode the ecode of this probation
	 */
	@Override
	public void setEcode(String ecode) {
		model.setEcode(ecode);
	}

	/**
	 * Sets the manager of this probation.
	 *
	 * @param manager the manager of this probation
	 */
	@Override
	public void setManager(String manager) {
		model.setManager(manager);
	}

	/**
	 * Sets the primary key of this probation.
	 *
	 * @param primaryKey the primary key of this probation
	 */
	@Override
	public void setPrimaryKey(String primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the reviewer of this probation.
	 *
	 * @param reviewer the reviewer of this probation
	 */
	@Override
	public void setReviewer(String reviewer) {
		model.setReviewer(reviewer);
	}

	/**
	 * Sets the state of this probation.
	 *
	 * @param state the state of this probation
	 */
	@Override
	public void setState(int state) {
		model.setState(state);
	}

	/**
	 * Sets the state name of this probation.
	 *
	 * @param stateName the state name of this probation
	 */
	@Override
	public void setStateName(String stateName) {
		model.setStateName(stateName);
	}

	/**
	 * Sets the update date of this probation.
	 *
	 * @param updateDate the update date of this probation
	 */
	@Override
	public void setUpdateDate(Date updateDate) {
		model.setUpdateDate(updateDate);
	}

	@Override
	protected ProbationWrapper wrap(Probation probation) {
		return new ProbationWrapper(probation);
	}

}