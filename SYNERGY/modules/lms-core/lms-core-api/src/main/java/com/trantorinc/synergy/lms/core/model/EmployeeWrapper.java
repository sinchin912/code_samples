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

package com.trantorinc.synergy.lms.core.model;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link Employee}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Employee
 * @generated
 */
public class EmployeeWrapper
	extends BaseModelWrapper<Employee>
	implements Employee, ModelWrapper<Employee> {

	public EmployeeWrapper(Employee employee) {
		super(employee);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("ecode", getEcode());
		attributes.put("status", isStatus());
		attributes.put("employeeType", getEmployeeType());
		attributes.put("name", getName());
		attributes.put("doj", getDoj());
		attributes.put("dob", getDob());
		attributes.put("email", getEmail());
		attributes.put("band", getBand());
		attributes.put("designation", getDesignation());
		attributes.put("manager", getManager());
		attributes.put("reviewer", getReviewer());
		attributes.put("coordinator", getCoordinator());
		attributes.put("account", getAccount());
		attributes.put("project", getProject());
		attributes.put("experience", getExperience());
		attributes.put("skill", getSkill());
		attributes.put("location", getLocation());
		attributes.put("confirmed", isConfirmed());
		attributes.put("mobile", getMobile());
		attributes.put("fileId", getFileId());
		attributes.put("skype", getSkype());
		attributes.put("lmsUser", isLmsUser());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String ecode = (String)attributes.get("ecode");

		if (ecode != null) {
			setEcode(ecode);
		}

		Boolean status = (Boolean)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}

		String employeeType = (String)attributes.get("employeeType");

		if (employeeType != null) {
			setEmployeeType(employeeType);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		Date doj = (Date)attributes.get("doj");

		if (doj != null) {
			setDoj(doj);
		}

		Date dob = (Date)attributes.get("dob");

		if (dob != null) {
			setDob(dob);
		}

		String email = (String)attributes.get("email");

		if (email != null) {
			setEmail(email);
		}

		String band = (String)attributes.get("band");

		if (band != null) {
			setBand(band);
		}

		String designation = (String)attributes.get("designation");

		if (designation != null) {
			setDesignation(designation);
		}

		String manager = (String)attributes.get("manager");

		if (manager != null) {
			setManager(manager);
		}

		String reviewer = (String)attributes.get("reviewer");

		if (reviewer != null) {
			setReviewer(reviewer);
		}

		String coordinator = (String)attributes.get("coordinator");

		if (coordinator != null) {
			setCoordinator(coordinator);
		}

		String account = (String)attributes.get("account");

		if (account != null) {
			setAccount(account);
		}

		String project = (String)attributes.get("project");

		if (project != null) {
			setProject(project);
		}

		String experience = (String)attributes.get("experience");

		if (experience != null) {
			setExperience(experience);
		}

		String skill = (String)attributes.get("skill");

		if (skill != null) {
			setSkill(skill);
		}

		String location = (String)attributes.get("location");

		if (location != null) {
			setLocation(location);
		}

		Boolean confirmed = (Boolean)attributes.get("confirmed");

		if (confirmed != null) {
			setConfirmed(confirmed);
		}

		String mobile = (String)attributes.get("mobile");

		if (mobile != null) {
			setMobile(mobile);
		}

		String fileId = (String)attributes.get("fileId");

		if (fileId != null) {
			setFileId(fileId);
		}

		String skype = (String)attributes.get("skype");

		if (skype != null) {
			setSkype(skype);
		}

		Boolean lmsUser = (Boolean)attributes.get("lmsUser");

		if (lmsUser != null) {
			setLmsUser(lmsUser);
		}
	}

	@Override
	public Employee cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the account of this employee.
	 *
	 * @return the account of this employee
	 */
	@Override
	public String getAccount() {
		return model.getAccount();
	}

	/**
	 * Returns the band of this employee.
	 *
	 * @return the band of this employee
	 */
	@Override
	public String getBand() {
		return model.getBand();
	}

	/**
	 * Returns the confirmed of this employee.
	 *
	 * @return the confirmed of this employee
	 */
	@Override
	public boolean getConfirmed() {
		return model.getConfirmed();
	}

	/**
	 * Returns the coordinator of this employee.
	 *
	 * @return the coordinator of this employee
	 */
	@Override
	public String getCoordinator() {
		return model.getCoordinator();
	}

	/**
	 * Returns the designation of this employee.
	 *
	 * @return the designation of this employee
	 */
	@Override
	public String getDesignation() {
		return model.getDesignation();
	}

	/**
	 * Returns the dob of this employee.
	 *
	 * @return the dob of this employee
	 */
	@Override
	public Date getDob() {
		return model.getDob();
	}

	/**
	 * Returns the doj of this employee.
	 *
	 * @return the doj of this employee
	 */
	@Override
	public Date getDoj() {
		return model.getDoj();
	}

	/**
	 * Returns the ecode of this employee.
	 *
	 * @return the ecode of this employee
	 */
	@Override
	public String getEcode() {
		return model.getEcode();
	}

	/**
	 * Returns the email of this employee.
	 *
	 * @return the email of this employee
	 */
	@Override
	public String getEmail() {
		return model.getEmail();
	}

	/**
	 * Returns the employee type of this employee.
	 *
	 * @return the employee type of this employee
	 */
	@Override
	public String getEmployeeType() {
		return model.getEmployeeType();
	}

	/**
	 * Returns the experience of this employee.
	 *
	 * @return the experience of this employee
	 */
	@Override
	public String getExperience() {
		return model.getExperience();
	}

	/**
	 * Returns the file ID of this employee.
	 *
	 * @return the file ID of this employee
	 */
	@Override
	public String getFileId() {
		return model.getFileId();
	}

	/**
	 * Returns the lms user of this employee.
	 *
	 * @return the lms user of this employee
	 */
	@Override
	public boolean getLmsUser() {
		return model.getLmsUser();
	}

	/**
	 * Returns the location of this employee.
	 *
	 * @return the location of this employee
	 */
	@Override
	public String getLocation() {
		return model.getLocation();
	}

	/**
	 * Returns the manager of this employee.
	 *
	 * @return the manager of this employee
	 */
	@Override
	public String getManager() {
		return model.getManager();
	}

	/**
	 * Returns the mobile of this employee.
	 *
	 * @return the mobile of this employee
	 */
	@Override
	public String getMobile() {
		return model.getMobile();
	}

	/**
	 * Returns the name of this employee.
	 *
	 * @return the name of this employee
	 */
	@Override
	public String getName() {
		return model.getName();
	}

	/**
	 * Returns the primary key of this employee.
	 *
	 * @return the primary key of this employee
	 */
	@Override
	public String getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the project of this employee.
	 *
	 * @return the project of this employee
	 */
	@Override
	public String getProject() {
		return model.getProject();
	}

	/**
	 * Returns the reviewer of this employee.
	 *
	 * @return the reviewer of this employee
	 */
	@Override
	public String getReviewer() {
		return model.getReviewer();
	}

	/**
	 * Returns the skill of this employee.
	 *
	 * @return the skill of this employee
	 */
	@Override
	public String getSkill() {
		return model.getSkill();
	}

	/**
	 * Returns the skype of this employee.
	 *
	 * @return the skype of this employee
	 */
	@Override
	public String getSkype() {
		return model.getSkype();
	}

	/**
	 * Returns the status of this employee.
	 *
	 * @return the status of this employee
	 */
	@Override
	public boolean getStatus() {
		return model.getStatus();
	}

	/**
	 * Returns <code>true</code> if this employee is confirmed.
	 *
	 * @return <code>true</code> if this employee is confirmed; <code>false</code> otherwise
	 */
	@Override
	public boolean isConfirmed() {
		return model.isConfirmed();
	}

	/**
	 * Returns <code>true</code> if this employee is lms user.
	 *
	 * @return <code>true</code> if this employee is lms user; <code>false</code> otherwise
	 */
	@Override
	public boolean isLmsUser() {
		return model.isLmsUser();
	}

	/**
	 * Returns <code>true</code> if this employee is status.
	 *
	 * @return <code>true</code> if this employee is status; <code>false</code> otherwise
	 */
	@Override
	public boolean isStatus() {
		return model.isStatus();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the account of this employee.
	 *
	 * @param account the account of this employee
	 */
	@Override
	public void setAccount(String account) {
		model.setAccount(account);
	}

	/**
	 * Sets the band of this employee.
	 *
	 * @param band the band of this employee
	 */
	@Override
	public void setBand(String band) {
		model.setBand(band);
	}

	/**
	 * Sets whether this employee is confirmed.
	 *
	 * @param confirmed the confirmed of this employee
	 */
	@Override
	public void setConfirmed(boolean confirmed) {
		model.setConfirmed(confirmed);
	}

	/**
	 * Sets the coordinator of this employee.
	 *
	 * @param coordinator the coordinator of this employee
	 */
	@Override
	public void setCoordinator(String coordinator) {
		model.setCoordinator(coordinator);
	}

	/**
	 * Sets the designation of this employee.
	 *
	 * @param designation the designation of this employee
	 */
	@Override
	public void setDesignation(String designation) {
		model.setDesignation(designation);
	}

	/**
	 * Sets the dob of this employee.
	 *
	 * @param dob the dob of this employee
	 */
	@Override
	public void setDob(Date dob) {
		model.setDob(dob);
	}

	/**
	 * Sets the doj of this employee.
	 *
	 * @param doj the doj of this employee
	 */
	@Override
	public void setDoj(Date doj) {
		model.setDoj(doj);
	}

	/**
	 * Sets the ecode of this employee.
	 *
	 * @param ecode the ecode of this employee
	 */
	@Override
	public void setEcode(String ecode) {
		model.setEcode(ecode);
	}

	/**
	 * Sets the email of this employee.
	 *
	 * @param email the email of this employee
	 */
	@Override
	public void setEmail(String email) {
		model.setEmail(email);
	}

	/**
	 * Sets the employee type of this employee.
	 *
	 * @param employeeType the employee type of this employee
	 */
	@Override
	public void setEmployeeType(String employeeType) {
		model.setEmployeeType(employeeType);
	}

	/**
	 * Sets the experience of this employee.
	 *
	 * @param experience the experience of this employee
	 */
	@Override
	public void setExperience(String experience) {
		model.setExperience(experience);
	}

	/**
	 * Sets the file ID of this employee.
	 *
	 * @param fileId the file ID of this employee
	 */
	@Override
	public void setFileId(String fileId) {
		model.setFileId(fileId);
	}

	/**
	 * Sets whether this employee is lms user.
	 *
	 * @param lmsUser the lms user of this employee
	 */
	@Override
	public void setLmsUser(boolean lmsUser) {
		model.setLmsUser(lmsUser);
	}

	/**
	 * Sets the location of this employee.
	 *
	 * @param location the location of this employee
	 */
	@Override
	public void setLocation(String location) {
		model.setLocation(location);
	}

	/**
	 * Sets the manager of this employee.
	 *
	 * @param manager the manager of this employee
	 */
	@Override
	public void setManager(String manager) {
		model.setManager(manager);
	}

	/**
	 * Sets the mobile of this employee.
	 *
	 * @param mobile the mobile of this employee
	 */
	@Override
	public void setMobile(String mobile) {
		model.setMobile(mobile);
	}

	/**
	 * Sets the name of this employee.
	 *
	 * @param name the name of this employee
	 */
	@Override
	public void setName(String name) {
		model.setName(name);
	}

	/**
	 * Sets the primary key of this employee.
	 *
	 * @param primaryKey the primary key of this employee
	 */
	@Override
	public void setPrimaryKey(String primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the project of this employee.
	 *
	 * @param project the project of this employee
	 */
	@Override
	public void setProject(String project) {
		model.setProject(project);
	}

	/**
	 * Sets the reviewer of this employee.
	 *
	 * @param reviewer the reviewer of this employee
	 */
	@Override
	public void setReviewer(String reviewer) {
		model.setReviewer(reviewer);
	}

	/**
	 * Sets the skill of this employee.
	 *
	 * @param skill the skill of this employee
	 */
	@Override
	public void setSkill(String skill) {
		model.setSkill(skill);
	}

	/**
	 * Sets the skype of this employee.
	 *
	 * @param skype the skype of this employee
	 */
	@Override
	public void setSkype(String skype) {
		model.setSkype(skype);
	}

	/**
	 * Sets whether this employee is status.
	 *
	 * @param status the status of this employee
	 */
	@Override
	public void setStatus(boolean status) {
		model.setStatus(status);
	}

	@Override
	protected EmployeeWrapper wrap(Employee employee) {
		return new EmployeeWrapper(employee);
	}

}