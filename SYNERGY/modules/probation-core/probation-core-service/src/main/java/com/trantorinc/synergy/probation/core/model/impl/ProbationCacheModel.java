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

package com.trantorinc.synergy.probation.core.model.impl;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import com.trantorinc.synergy.probation.core.model.Probation;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Probation in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ProbationCacheModel
	implements CacheModel<Probation>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof ProbationCacheModel)) {
			return false;
		}

		ProbationCacheModel probationCacheModel = (ProbationCacheModel)object;

		if (ecode.equals(probationCacheModel.ecode)) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, ecode);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(23);

		sb.append("{ecode=");
		sb.append(ecode);
		sb.append(", manager=");
		sb.append(manager);
		sb.append(", reviewer=");
		sb.append(reviewer);
		sb.append(", state=");
		sb.append(state);
		sb.append(", stateName=");
		sb.append(stateName);
		sb.append(", alertDate=");
		sb.append(alertDate);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", updateDate=");
		sb.append(updateDate);
		sb.append(", areaOfStrength=");
		sb.append(areaOfStrength);
		sb.append(", areaOfImprovement=");
		sb.append(areaOfImprovement);
		sb.append(", comment=");
		sb.append(comment);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Probation toEntityModel() {
		ProbationImpl probationImpl = new ProbationImpl();

		if (ecode == null) {
			probationImpl.setEcode("");
		}
		else {
			probationImpl.setEcode(ecode);
		}

		if (manager == null) {
			probationImpl.setManager("");
		}
		else {
			probationImpl.setManager(manager);
		}

		if (reviewer == null) {
			probationImpl.setReviewer("");
		}
		else {
			probationImpl.setReviewer(reviewer);
		}

		probationImpl.setState(state);

		if (stateName == null) {
			probationImpl.setStateName("");
		}
		else {
			probationImpl.setStateName(stateName);
		}

		if (alertDate == Long.MIN_VALUE) {
			probationImpl.setAlertDate(null);
		}
		else {
			probationImpl.setAlertDate(new Date(alertDate));
		}

		if (createDate == Long.MIN_VALUE) {
			probationImpl.setCreateDate(null);
		}
		else {
			probationImpl.setCreateDate(new Date(createDate));
		}

		if (updateDate == Long.MIN_VALUE) {
			probationImpl.setUpdateDate(null);
		}
		else {
			probationImpl.setUpdateDate(new Date(updateDate));
		}

		if (areaOfStrength == null) {
			probationImpl.setAreaOfStrength("");
		}
		else {
			probationImpl.setAreaOfStrength(areaOfStrength);
		}

		if (areaOfImprovement == null) {
			probationImpl.setAreaOfImprovement("");
		}
		else {
			probationImpl.setAreaOfImprovement(areaOfImprovement);
		}

		if (comment == null) {
			probationImpl.setComment("");
		}
		else {
			probationImpl.setComment(comment);
		}

		probationImpl.resetOriginalValues();

		return probationImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		ecode = objectInput.readUTF();
		manager = objectInput.readUTF();
		reviewer = objectInput.readUTF();

		state = objectInput.readInt();
		stateName = objectInput.readUTF();
		alertDate = objectInput.readLong();
		createDate = objectInput.readLong();
		updateDate = objectInput.readLong();
		areaOfStrength = objectInput.readUTF();
		areaOfImprovement = objectInput.readUTF();
		comment = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		if (ecode == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(ecode);
		}

		if (manager == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(manager);
		}

		if (reviewer == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(reviewer);
		}

		objectOutput.writeInt(state);

		if (stateName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(stateName);
		}

		objectOutput.writeLong(alertDate);
		objectOutput.writeLong(createDate);
		objectOutput.writeLong(updateDate);

		if (areaOfStrength == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(areaOfStrength);
		}

		if (areaOfImprovement == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(areaOfImprovement);
		}

		if (comment == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(comment);
		}
	}

	public String ecode;
	public String manager;
	public String reviewer;
	public int state;
	public String stateName;
	public long alertDate;
	public long createDate;
	public long updateDate;
	public String areaOfStrength;
	public String areaOfImprovement;
	public String comment;

}