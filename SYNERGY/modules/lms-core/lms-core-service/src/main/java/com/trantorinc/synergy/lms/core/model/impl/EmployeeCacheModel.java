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

import com.trantorinc.synergy.lms.core.model.Employee;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Employee in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class EmployeeCacheModel
	implements CacheModel<Employee>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof EmployeeCacheModel)) {
			return false;
		}

		EmployeeCacheModel employeeCacheModel = (EmployeeCacheModel)object;

		if (ecode.equals(employeeCacheModel.ecode)) {
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
		StringBundler sb = new StringBundler(45);

		sb.append("{ecode=");
		sb.append(ecode);
		sb.append(", status=");
		sb.append(status);
		sb.append(", employeeType=");
		sb.append(employeeType);
		sb.append(", name=");
		sb.append(name);
		sb.append(", doj=");
		sb.append(doj);
		sb.append(", dob=");
		sb.append(dob);
		sb.append(", email=");
		sb.append(email);
		sb.append(", band=");
		sb.append(band);
		sb.append(", designation=");
		sb.append(designation);
		sb.append(", manager=");
		sb.append(manager);
		sb.append(", reviewer=");
		sb.append(reviewer);
		sb.append(", coordinator=");
		sb.append(coordinator);
		sb.append(", account=");
		sb.append(account);
		sb.append(", project=");
		sb.append(project);
		sb.append(", experience=");
		sb.append(experience);
		sb.append(", skill=");
		sb.append(skill);
		sb.append(", location=");
		sb.append(location);
		sb.append(", confirmed=");
		sb.append(confirmed);
		sb.append(", mobile=");
		sb.append(mobile);
		sb.append(", fileId=");
		sb.append(fileId);
		sb.append(", skype=");
		sb.append(skype);
		sb.append(", lmsUser=");
		sb.append(lmsUser);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Employee toEntityModel() {
		EmployeeImpl employeeImpl = new EmployeeImpl();

		if (ecode == null) {
			employeeImpl.setEcode("");
		}
		else {
			employeeImpl.setEcode(ecode);
		}

		employeeImpl.setStatus(status);

		if (employeeType == null) {
			employeeImpl.setEmployeeType("");
		}
		else {
			employeeImpl.setEmployeeType(employeeType);
		}

		if (name == null) {
			employeeImpl.setName("");
		}
		else {
			employeeImpl.setName(name);
		}

		if (doj == Long.MIN_VALUE) {
			employeeImpl.setDoj(null);
		}
		else {
			employeeImpl.setDoj(new Date(doj));
		}

		if (dob == Long.MIN_VALUE) {
			employeeImpl.setDob(null);
		}
		else {
			employeeImpl.setDob(new Date(dob));
		}

		if (email == null) {
			employeeImpl.setEmail("");
		}
		else {
			employeeImpl.setEmail(email);
		}

		if (band == null) {
			employeeImpl.setBand("");
		}
		else {
			employeeImpl.setBand(band);
		}

		if (designation == null) {
			employeeImpl.setDesignation("");
		}
		else {
			employeeImpl.setDesignation(designation);
		}

		if (manager == null) {
			employeeImpl.setManager("");
		}
		else {
			employeeImpl.setManager(manager);
		}

		if (reviewer == null) {
			employeeImpl.setReviewer("");
		}
		else {
			employeeImpl.setReviewer(reviewer);
		}

		if (coordinator == null) {
			employeeImpl.setCoordinator("");
		}
		else {
			employeeImpl.setCoordinator(coordinator);
		}

		if (account == null) {
			employeeImpl.setAccount("");
		}
		else {
			employeeImpl.setAccount(account);
		}

		if (project == null) {
			employeeImpl.setProject("");
		}
		else {
			employeeImpl.setProject(project);
		}

		if (experience == null) {
			employeeImpl.setExperience("");
		}
		else {
			employeeImpl.setExperience(experience);
		}

		if (skill == null) {
			employeeImpl.setSkill("");
		}
		else {
			employeeImpl.setSkill(skill);
		}

		if (location == null) {
			employeeImpl.setLocation("");
		}
		else {
			employeeImpl.setLocation(location);
		}

		employeeImpl.setConfirmed(confirmed);

		if (mobile == null) {
			employeeImpl.setMobile("");
		}
		else {
			employeeImpl.setMobile(mobile);
		}

		if (fileId == null) {
			employeeImpl.setFileId("");
		}
		else {
			employeeImpl.setFileId(fileId);
		}

		if (skype == null) {
			employeeImpl.setSkype("");
		}
		else {
			employeeImpl.setSkype(skype);
		}

		employeeImpl.setLmsUser(lmsUser);

		employeeImpl.resetOriginalValues();

		return employeeImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		ecode = objectInput.readUTF();

		status = objectInput.readBoolean();
		employeeType = objectInput.readUTF();
		name = objectInput.readUTF();
		doj = objectInput.readLong();
		dob = objectInput.readLong();
		email = objectInput.readUTF();
		band = objectInput.readUTF();
		designation = objectInput.readUTF();
		manager = objectInput.readUTF();
		reviewer = objectInput.readUTF();
		coordinator = objectInput.readUTF();
		account = objectInput.readUTF();
		project = objectInput.readUTF();
		experience = objectInput.readUTF();
		skill = objectInput.readUTF();
		location = objectInput.readUTF();

		confirmed = objectInput.readBoolean();
		mobile = objectInput.readUTF();
		fileId = objectInput.readUTF();
		skype = objectInput.readUTF();

		lmsUser = objectInput.readBoolean();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		if (ecode == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(ecode);
		}

		objectOutput.writeBoolean(status);

		if (employeeType == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(employeeType);
		}

		if (name == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(name);
		}

		objectOutput.writeLong(doj);
		objectOutput.writeLong(dob);

		if (email == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(email);
		}

		if (band == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(band);
		}

		if (designation == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(designation);
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

		if (coordinator == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(coordinator);
		}

		if (account == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(account);
		}

		if (project == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(project);
		}

		if (experience == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(experience);
		}

		if (skill == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(skill);
		}

		if (location == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(location);
		}

		objectOutput.writeBoolean(confirmed);

		if (mobile == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(mobile);
		}

		if (fileId == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(fileId);
		}

		if (skype == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(skype);
		}

		objectOutput.writeBoolean(lmsUser);
	}

	public String ecode;
	public boolean status;
	public String employeeType;
	public String name;
	public long doj;
	public long dob;
	public String email;
	public String band;
	public String designation;
	public String manager;
	public String reviewer;
	public String coordinator;
	public String account;
	public String project;
	public String experience;
	public String skill;
	public String location;
	public boolean confirmed;
	public String mobile;
	public String fileId;
	public String skype;
	public boolean lmsUser;

}