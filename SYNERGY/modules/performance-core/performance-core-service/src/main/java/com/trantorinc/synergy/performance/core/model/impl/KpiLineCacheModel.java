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

import com.trantorinc.synergy.performance.core.model.KpiLine;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing KpiLine in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class KpiLineCacheModel implements CacheModel<KpiLine>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof KpiLineCacheModel)) {
			return false;
		}

		KpiLineCacheModel kpiLineCacheModel = (KpiLineCacheModel)object;

		if (lineId == kpiLineCacheModel.lineId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, lineId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(15);

		sb.append("{lineId=");
		sb.append(lineId);
		sb.append(", kpiId=");
		sb.append(kpiId);
		sb.append(", guideId=");
		sb.append(guideId);
		sb.append(", title=");
		sb.append(title);
		sb.append(", description=");
		sb.append(description);
		sb.append(", target=");
		sb.append(target);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public KpiLine toEntityModel() {
		KpiLineImpl kpiLineImpl = new KpiLineImpl();

		kpiLineImpl.setLineId(lineId);
		kpiLineImpl.setKpiId(kpiId);
		kpiLineImpl.setGuideId(guideId);

		if (title == null) {
			kpiLineImpl.setTitle("");
		}
		else {
			kpiLineImpl.setTitle(title);
		}

		if (description == null) {
			kpiLineImpl.setDescription("");
		}
		else {
			kpiLineImpl.setDescription(description);
		}

		if (target == null) {
			kpiLineImpl.setTarget("");
		}
		else {
			kpiLineImpl.setTarget(target);
		}

		if (createDate == Long.MIN_VALUE) {
			kpiLineImpl.setCreateDate(null);
		}
		else {
			kpiLineImpl.setCreateDate(new Date(createDate));
		}

		kpiLineImpl.resetOriginalValues();

		return kpiLineImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		lineId = objectInput.readLong();

		kpiId = objectInput.readLong();

		guideId = objectInput.readLong();
		title = objectInput.readUTF();
		description = objectInput.readUTF();
		target = objectInput.readUTF();
		createDate = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(lineId);

		objectOutput.writeLong(kpiId);

		objectOutput.writeLong(guideId);

		if (title == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(title);
		}

		if (description == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(description);
		}

		if (target == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(target);
		}

		objectOutput.writeLong(createDate);
	}

	public long lineId;
	public long kpiId;
	public long guideId;
	public String title;
	public String description;
	public String target;
	public long createDate;

}