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
 * This class is a wrapper for {@link Kpi}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Kpi
 * @generated
 */
public class KpiWrapper
	extends BaseModelWrapper<Kpi> implements Kpi, ModelWrapper<Kpi> {

	public KpiWrapper(Kpi kpi) {
		super(kpi);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("kpiId", getKpiId());
		attributes.put("ecode", getEcode());
		attributes.put("account", getAccount());
		attributes.put("managerEmail", getManagerEmail());
		attributes.put("reviewerEmail", getReviewerEmail());
		attributes.put("accountPrimary", isAccountPrimary());
		attributes.put("updateDate", getUpdateDate());
		attributes.put("kpiSettingStatus", isKpiSettingStatus());
		attributes.put("rejectionComment", getRejectionComment());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long kpiId = (Long)attributes.get("kpiId");

		if (kpiId != null) {
			setKpiId(kpiId);
		}

		String ecode = (String)attributes.get("ecode");

		if (ecode != null) {
			setEcode(ecode);
		}

		String account = (String)attributes.get("account");

		if (account != null) {
			setAccount(account);
		}

		String managerEmail = (String)attributes.get("managerEmail");

		if (managerEmail != null) {
			setManagerEmail(managerEmail);
		}

		String reviewerEmail = (String)attributes.get("reviewerEmail");

		if (reviewerEmail != null) {
			setReviewerEmail(reviewerEmail);
		}

		Boolean accountPrimary = (Boolean)attributes.get("accountPrimary");

		if (accountPrimary != null) {
			setAccountPrimary(accountPrimary);
		}

		Date updateDate = (Date)attributes.get("updateDate");

		if (updateDate != null) {
			setUpdateDate(updateDate);
		}

		Boolean kpiSettingStatus = (Boolean)attributes.get("kpiSettingStatus");

		if (kpiSettingStatus != null) {
			setKpiSettingStatus(kpiSettingStatus);
		}

		String rejectionComment = (String)attributes.get("rejectionComment");

		if (rejectionComment != null) {
			setRejectionComment(rejectionComment);
		}
	}

	@Override
	public Kpi cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the account of this kpi.
	 *
	 * @return the account of this kpi
	 */
	@Override
	public String getAccount() {
		return model.getAccount();
	}

	/**
	 * Returns the account primary of this kpi.
	 *
	 * @return the account primary of this kpi
	 */
	@Override
	public boolean getAccountPrimary() {
		return model.getAccountPrimary();
	}

	/**
	 * Returns the ecode of this kpi.
	 *
	 * @return the ecode of this kpi
	 */
	@Override
	public String getEcode() {
		return model.getEcode();
	}

	/**
	 * Returns the kpi ID of this kpi.
	 *
	 * @return the kpi ID of this kpi
	 */
	@Override
	public long getKpiId() {
		return model.getKpiId();
	}

	/**
	 * Returns the kpi setting status of this kpi.
	 *
	 * @return the kpi setting status of this kpi
	 */
	@Override
	public boolean getKpiSettingStatus() {
		return model.getKpiSettingStatus();
	}

	/**
	 * Returns the manager email of this kpi.
	 *
	 * @return the manager email of this kpi
	 */
	@Override
	public String getManagerEmail() {
		return model.getManagerEmail();
	}

	/**
	 * Returns the primary key of this kpi.
	 *
	 * @return the primary key of this kpi
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the rejection comment of this kpi.
	 *
	 * @return the rejection comment of this kpi
	 */
	@Override
	public String getRejectionComment() {
		return model.getRejectionComment();
	}

	/**
	 * Returns the reviewer email of this kpi.
	 *
	 * @return the reviewer email of this kpi
	 */
	@Override
	public String getReviewerEmail() {
		return model.getReviewerEmail();
	}

	/**
	 * Returns the update date of this kpi.
	 *
	 * @return the update date of this kpi
	 */
	@Override
	public Date getUpdateDate() {
		return model.getUpdateDate();
	}

	/**
	 * Returns <code>true</code> if this kpi is account primary.
	 *
	 * @return <code>true</code> if this kpi is account primary; <code>false</code> otherwise
	 */
	@Override
	public boolean isAccountPrimary() {
		return model.isAccountPrimary();
	}

	/**
	 * Returns <code>true</code> if this kpi is kpi setting status.
	 *
	 * @return <code>true</code> if this kpi is kpi setting status; <code>false</code> otherwise
	 */
	@Override
	public boolean isKpiSettingStatus() {
		return model.isKpiSettingStatus();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the account of this kpi.
	 *
	 * @param account the account of this kpi
	 */
	@Override
	public void setAccount(String account) {
		model.setAccount(account);
	}

	/**
	 * Sets whether this kpi is account primary.
	 *
	 * @param accountPrimary the account primary of this kpi
	 */
	@Override
	public void setAccountPrimary(boolean accountPrimary) {
		model.setAccountPrimary(accountPrimary);
	}

	/**
	 * Sets the ecode of this kpi.
	 *
	 * @param ecode the ecode of this kpi
	 */
	@Override
	public void setEcode(String ecode) {
		model.setEcode(ecode);
	}

	/**
	 * Sets the kpi ID of this kpi.
	 *
	 * @param kpiId the kpi ID of this kpi
	 */
	@Override
	public void setKpiId(long kpiId) {
		model.setKpiId(kpiId);
	}

	/**
	 * Sets whether this kpi is kpi setting status.
	 *
	 * @param kpiSettingStatus the kpi setting status of this kpi
	 */
	@Override
	public void setKpiSettingStatus(boolean kpiSettingStatus) {
		model.setKpiSettingStatus(kpiSettingStatus);
	}

	/**
	 * Sets the manager email of this kpi.
	 *
	 * @param managerEmail the manager email of this kpi
	 */
	@Override
	public void setManagerEmail(String managerEmail) {
		model.setManagerEmail(managerEmail);
	}

	/**
	 * Sets the primary key of this kpi.
	 *
	 * @param primaryKey the primary key of this kpi
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the rejection comment of this kpi.
	 *
	 * @param rejectionComment the rejection comment of this kpi
	 */
	@Override
	public void setRejectionComment(String rejectionComment) {
		model.setRejectionComment(rejectionComment);
	}

	/**
	 * Sets the reviewer email of this kpi.
	 *
	 * @param reviewerEmail the reviewer email of this kpi
	 */
	@Override
	public void setReviewerEmail(String reviewerEmail) {
		model.setReviewerEmail(reviewerEmail);
	}

	/**
	 * Sets the update date of this kpi.
	 *
	 * @param updateDate the update date of this kpi
	 */
	@Override
	public void setUpdateDate(Date updateDate) {
		model.setUpdateDate(updateDate);
	}

	@Override
	protected KpiWrapper wrap(Kpi kpi) {
		return new KpiWrapper(kpi);
	}

}