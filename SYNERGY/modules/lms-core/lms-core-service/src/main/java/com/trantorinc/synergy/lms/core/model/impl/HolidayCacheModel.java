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

import com.trantorinc.synergy.lms.core.model.Holiday;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Holiday in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class HolidayCacheModel implements CacheModel<Holiday>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof HolidayCacheModel)) {
			return false;
		}

		HolidayCacheModel holidayCacheModel = (HolidayCacheModel)object;

		if (holidayId == holidayCacheModel.holidayId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, holidayId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(9);

		sb.append("{holidayId=");
		sb.append(holidayId);
		sb.append(", holidayYear=");
		sb.append(holidayYear);
		sb.append(", onDate=");
		sb.append(onDate);
		sb.append(", name=");
		sb.append(name);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Holiday toEntityModel() {
		HolidayImpl holidayImpl = new HolidayImpl();

		holidayImpl.setHolidayId(holidayId);
		holidayImpl.setHolidayYear(holidayYear);

		if (onDate == Long.MIN_VALUE) {
			holidayImpl.setOnDate(null);
		}
		else {
			holidayImpl.setOnDate(new Date(onDate));
		}

		if (name == null) {
			holidayImpl.setName("");
		}
		else {
			holidayImpl.setName(name);
		}

		holidayImpl.resetOriginalValues();

		return holidayImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		holidayId = objectInput.readLong();

		holidayYear = objectInput.readInt();
		onDate = objectInput.readLong();
		name = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(holidayId);

		objectOutput.writeInt(holidayYear);
		objectOutput.writeLong(onDate);

		if (name == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(name);
		}
	}

	public long holidayId;
	public int holidayYear;
	public long onDate;
	public String name;

}