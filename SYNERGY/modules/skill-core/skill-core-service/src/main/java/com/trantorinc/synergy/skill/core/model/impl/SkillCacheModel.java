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

import com.trantorinc.synergy.skill.core.model.Skill;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing Skill in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class SkillCacheModel implements CacheModel<Skill>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof SkillCacheModel)) {
			return false;
		}

		SkillCacheModel skillCacheModel = (SkillCacheModel)object;

		if (skillId == skillCacheModel.skillId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, skillId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(29);

		sb.append("{skillId=");
		sb.append(skillId);
		sb.append(", ecode=");
		sb.append(ecode);
		sb.append(", project=");
		sb.append(project);
		sb.append(", coreSkill=");
		sb.append(coreSkill);
		sb.append(", subSkill=");
		sb.append(subSkill);
		sb.append(", tool=");
		sb.append(tool);
		sb.append(", validity=");
		sb.append(validity);
		sb.append(", version=");
		sb.append(version);
		sb.append(", status=");
		sb.append(status);
		sb.append(", reviewer=");
		sb.append(reviewer);
		sb.append(", manager=");
		sb.append(manager);
		sb.append(", primarySkill=");
		sb.append(primarySkill);
		sb.append(", proficiencyLevel=");
		sb.append(proficiencyLevel);
		sb.append(", requiredLevel=");
		sb.append(requiredLevel);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Skill toEntityModel() {
		SkillImpl skillImpl = new SkillImpl();

		skillImpl.setSkillId(skillId);

		if (ecode == null) {
			skillImpl.setEcode("");
		}
		else {
			skillImpl.setEcode(ecode);
		}

		if (project == null) {
			skillImpl.setProject("");
		}
		else {
			skillImpl.setProject(project);
		}

		if (coreSkill == null) {
			skillImpl.setCoreSkill("");
		}
		else {
			skillImpl.setCoreSkill(coreSkill);
		}

		if (subSkill == null) {
			skillImpl.setSubSkill("");
		}
		else {
			skillImpl.setSubSkill(subSkill);
		}

		if (tool == null) {
			skillImpl.setTool("");
		}
		else {
			skillImpl.setTool(tool);
		}

		if (validity == null) {
			skillImpl.setValidity("");
		}
		else {
			skillImpl.setValidity(validity);
		}

		if (version == null) {
			skillImpl.setVersion("");
		}
		else {
			skillImpl.setVersion(version);
		}

		skillImpl.setStatus(status);

		if (reviewer == null) {
			skillImpl.setReviewer("");
		}
		else {
			skillImpl.setReviewer(reviewer);
		}

		if (manager == null) {
			skillImpl.setManager("");
		}
		else {
			skillImpl.setManager(manager);
		}

		skillImpl.setPrimarySkill(primarySkill);
		skillImpl.setProficiencyLevel(proficiencyLevel);
		skillImpl.setRequiredLevel(requiredLevel);

		skillImpl.resetOriginalValues();

		return skillImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		skillId = objectInput.readLong();
		ecode = objectInput.readUTF();
		project = objectInput.readUTF();
		coreSkill = objectInput.readUTF();
		subSkill = objectInput.readUTF();
		tool = objectInput.readUTF();
		validity = objectInput.readUTF();
		version = objectInput.readUTF();

		status = objectInput.readInt();
		reviewer = objectInput.readUTF();
		manager = objectInput.readUTF();

		primarySkill = objectInput.readBoolean();

		proficiencyLevel = objectInput.readInt();

		requiredLevel = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(skillId);

		if (ecode == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(ecode);
		}

		if (project == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(project);
		}

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

		if (tool == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(tool);
		}

		if (validity == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(validity);
		}

		if (version == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(version);
		}

		objectOutput.writeInt(status);

		if (reviewer == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(reviewer);
		}

		if (manager == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(manager);
		}

		objectOutput.writeBoolean(primarySkill);

		objectOutput.writeInt(proficiencyLevel);

		objectOutput.writeInt(requiredLevel);
	}

	public long skillId;
	public String ecode;
	public String project;
	public String coreSkill;
	public String subSkill;
	public String tool;
	public String validity;
	public String version;
	public int status;
	public String reviewer;
	public String manager;
	public boolean primarySkill;
	public int proficiencyLevel;
	public int requiredLevel;

}