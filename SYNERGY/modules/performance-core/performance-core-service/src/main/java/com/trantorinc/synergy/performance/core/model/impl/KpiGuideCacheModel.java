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

import com.trantorinc.synergy.performance.core.model.KpiGuide;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing KpiGuide in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class KpiGuideCacheModel
	implements CacheModel<KpiGuide>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof KpiGuideCacheModel)) {
			return false;
		}

		KpiGuideCacheModel kpiGuideCacheModel = (KpiGuideCacheModel)object;

		if (guideId == kpiGuideCacheModel.guideId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, guideId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(13);

		sb.append("{guideId=");
		sb.append(guideId);
		sb.append(", title=");
		sb.append(title);
		sb.append(", description=");
		sb.append(description);
		sb.append(", attribute=");
		sb.append(attribute);
		sb.append(", other=");
		sb.append(other);
		sb.append(", mandatory=");
		sb.append(mandatory);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public KpiGuide toEntityModel() {
		KpiGuideImpl kpiGuideImpl = new KpiGuideImpl();

		kpiGuideImpl.setGuideId(guideId);

		if (title == null) {
			kpiGuideImpl.setTitle("");
		}
		else {
			kpiGuideImpl.setTitle(title);
		}

		if (description == null) {
			kpiGuideImpl.setDescription("");
		}
		else {
			kpiGuideImpl.setDescription(description);
		}

		kpiGuideImpl.setAttribute(attribute);
		kpiGuideImpl.setOther(other);
		kpiGuideImpl.setMandatory(mandatory);

		kpiGuideImpl.resetOriginalValues();

		return kpiGuideImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		guideId = objectInput.readLong();
		title = objectInput.readUTF();
		description = objectInput.readUTF();

		attribute = objectInput.readBoolean();

		other = objectInput.readBoolean();

		mandatory = objectInput.readBoolean();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
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

		objectOutput.writeBoolean(attribute);

		objectOutput.writeBoolean(other);

		objectOutput.writeBoolean(mandatory);
	}

	public long guideId;
	public String title;
	public String description;
	public boolean attribute;
	public boolean other;
	public boolean mandatory;

}