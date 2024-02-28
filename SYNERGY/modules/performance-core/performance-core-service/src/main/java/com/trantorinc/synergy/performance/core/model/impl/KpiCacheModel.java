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

package com.trantorinc.synergy.performance.core.model.impl;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import com.trantorinc.synergy.performance.core.model.Kpi;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Kpi in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class KpiCacheModel implements CacheModel<Kpi>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof KpiCacheModel)) {
			return false;
		}

		KpiCacheModel kpiCacheModel = (KpiCacheModel)object;

		if (kpiId == kpiCacheModel.kpiId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, kpiId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(19);

		sb.append("{kpiId=");
		sb.append(kpiId);
		sb.append(", ecode=");
		sb.append(ecode);
		sb.append(", account=");
		sb.append(account);
		sb.append(", managerEmail=");
		sb.append(managerEmail);
		sb.append(", reviewerEmail=");
		sb.append(reviewerEmail);
		sb.append(", accountPrimary=");
		sb.append(accountPrimary);
		sb.append(", updateDate=");
		sb.append(updateDate);
		sb.append(", kpiSettingStatus=");
		sb.append(kpiSettingStatus);
		sb.append(", rejectionComment=");
		sb.append(rejectionComment);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Kpi toEntityModel() {
		KpiImpl kpiImpl = new KpiImpl();

		kpiImpl.setKpiId(kpiId);

		if (ecode == null) {
			kpiImpl.setEcode("");
		}
		else {
			kpiImpl.setEcode(ecode);
		}

		if (account == null) {
			kpiImpl.setAccount("");
		}
		else {
			kpiImpl.setAccount(account);
		}

		if (managerEmail == null) {
			kpiImpl.setManagerEmail("");
		}
		else {
			kpiImpl.setManagerEmail(managerEmail);
		}

		if (reviewerEmail == null) {
			kpiImpl.setReviewerEmail("");
		}
		else {
			kpiImpl.setReviewerEmail(reviewerEmail);
		}

		kpiImpl.setAccountPrimary(accountPrimary);

		if (updateDate == Long.MIN_VALUE) {
			kpiImpl.setUpdateDate(null);
		}
		else {
			kpiImpl.setUpdateDate(new Date(updateDate));
		}

		kpiImpl.setKpiSettingStatus(kpiSettingStatus);

		if (rejectionComment == null) {
			kpiImpl.setRejectionComment("");
		}
		else {
			kpiImpl.setRejectionComment(rejectionComment);
		}

		kpiImpl.resetOriginalValues();

		return kpiImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		kpiId = objectInput.readLong();
		ecode = objectInput.readUTF();
		account = objectInput.readUTF();
		managerEmail = objectInput.readUTF();
		reviewerEmail = objectInput.readUTF();

		accountPrimary = objectInput.readBoolean();
		updateDate = objectInput.readLong();

		kpiSettingStatus = objectInput.readBoolean();
		rejectionComment = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(kpiId);

		if (ecode == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(ecode);
		}

		if (account == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(account);
		}

		if (managerEmail == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(managerEmail);
		}

		if (reviewerEmail == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(reviewerEmail);
		}

		objectOutput.writeBoolean(accountPrimary);
		objectOutput.writeLong(updateDate);

		objectOutput.writeBoolean(kpiSettingStatus);

		if (rejectionComment == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(rejectionComment);
		}
	}

	public long kpiId;
	public String ecode;
	public String account;
	public String managerEmail;
	public String reviewerEmail;
	public boolean accountPrimary;
	public long updateDate;
	public boolean kpiSettingStatus;
	public String rejectionComment;

}