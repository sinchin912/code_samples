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

package com.trantorinc.synergy.lms.core.model.impl;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import com.trantorinc.synergy.lms.core.model.Scheduler;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Scheduler in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class SchedulerCacheModel
	implements CacheModel<Scheduler>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof SchedulerCacheModel)) {
			return false;
		}

		SchedulerCacheModel schedulerCacheModel = (SchedulerCacheModel)object;

		if (schedulerId == schedulerCacheModel.schedulerId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, schedulerId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(11);

		sb.append("{schedulerId=");
		sb.append(schedulerId);
		sb.append(", name=");
		sb.append(name);
		sb.append(", status=");
		sb.append(status);
		sb.append(", onDate=");
		sb.append(onDate);
		sb.append(", runDate=");
		sb.append(runDate);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Scheduler toEntityModel() {
		SchedulerImpl schedulerImpl = new SchedulerImpl();

		schedulerImpl.setSchedulerId(schedulerId);

		if (name == null) {
			schedulerImpl.setName("");
		}
		else {
			schedulerImpl.setName(name);
		}

		schedulerImpl.setStatus(status);

		if (onDate == Long.MIN_VALUE) {
			schedulerImpl.setOnDate(null);
		}
		else {
			schedulerImpl.setOnDate(new Date(onDate));
		}

		if (runDate == Long.MIN_VALUE) {
			schedulerImpl.setRunDate(null);
		}
		else {
			schedulerImpl.setRunDate(new Date(runDate));
		}

		schedulerImpl.resetOriginalValues();

		return schedulerImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		schedulerId = objectInput.readLong();
		name = objectInput.readUTF();

		status = objectInput.readBoolean();
		onDate = objectInput.readLong();
		runDate = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(schedulerId);

		if (name == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(name);
		}

		objectOutput.writeBoolean(status);
		objectOutput.writeLong(onDate);
		objectOutput.writeLong(runDate);
	}

	public long schedulerId;
	public String name;
	public boolean status;
	public long onDate;
	public long runDate;

}