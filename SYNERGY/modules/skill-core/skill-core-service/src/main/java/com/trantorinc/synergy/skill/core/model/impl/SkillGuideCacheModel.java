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

package com.trantorinc.synergy.skill.core.model.impl;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import com.trantorinc.synergy.skill.core.model.SkillGuide;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing SkillGuide in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class SkillGuideCacheModel
	implements CacheModel<SkillGuide>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof SkillGuideCacheModel)) {
			return false;
		}

		SkillGuideCacheModel skillGuideCacheModel =
			(SkillGuideCacheModel)object;

		if (guideId == skillGuideCacheModel.guideId) {
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
		StringBundler sb = new StringBundler(7);

		sb.append("{guideId=");
		sb.append(guideId);
		sb.append(", coreSkill=");
		sb.append(coreSkill);
		sb.append(", subSkill=");
		sb.append(subSkill);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public SkillGuide toEntityModel() {
		SkillGuideImpl skillGuideImpl = new SkillGuideImpl();

		skillGuideImpl.setGuideId(guideId);

		if (coreSkill == null) {
			skillGuideImpl.setCoreSkill("");
		}
		else {
			skillGuideImpl.setCoreSkill(coreSkill);
		}

		if (subSkill == null) {
			skillGuideImpl.setSubSkill("");
		}
		else {
			skillGuideImpl.setSubSkill(subSkill);
		}

		skillGuideImpl.resetOriginalValues();

		return skillGuideImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		guideId = objectInput.readLong();
		coreSkill = objectInput.readUTF();
		subSkill = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(guideId);

		if (coreSkill == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(coreSkill);
		}

		if (subSkill == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(subSkill);
		}
	}

	public long guideId;
	public String coreSkill;
	public String subSkill;

}