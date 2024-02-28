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

package com.trantorinc.synergy.skill.core.model;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link Skill}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Skill
 * @generated
 */
public class SkillWrapper
	extends BaseModelWrapper<Skill> implements ModelWrapper<Skill>, Skill {

	public SkillWrapper(Skill skill) {
		super(skill);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("skillId", getSkillId());
		attributes.put("ecode", getEcode());
		attributes.put("project", getProject());
		attributes.put("coreSkill", getCoreSkill());
		attributes.put("subSkill", getSubSkill());
		attributes.put("tool", getTool());
		attributes.put("validity", getValidity());
		attributes.put("version", getVersion());
		attributes.put("status", getStatus());
		attributes.put("reviewer", getReviewer());
		attributes.put("manager", getManager());
		attributes.put("primarySkill", isPrimarySkill());
		attributes.put("proficiencyLevel", getProficiencyLevel());
		attributes.put("requiredLevel", getRequiredLevel());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long skillId = (Long)attributes.get("skillId");

		if (skillId != null) {
			setSkillId(skillId);
		}

		String ecode = (String)attributes.get("ecode");

		if (ecode != null) {
			setEcode(ecode);
		}

		String project = (String)attributes.get("project");

		if (project != null) {
			setProject(project);
		}

		String coreSkill = (String)attributes.get("coreSkill");

		if (coreSkill != null) {
			setCoreSkill(coreSkill);
		}

		String subSkill = (String)attributes.get("subSkill");

		if (subSkill != null) {
			setSubSkill(subSkill);
		}

		String tool = (String)attributes.get("tool");

		if (tool != null) {
			setTool(tool);
		}

		String validity = (String)attributes.get("validity");

		if (validity != null) {
			setValidity(validity);
		}

		String version = (String)attributes.get("version");

		if (version != null) {
			setVersion(version);
		}

		Integer status = (Integer)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}

		String reviewer = (String)attributes.get("reviewer");

		if (reviewer != null) {
			setReviewer(reviewer);
		}

		String manager = (String)attributes.get("manager");

		if (manager != null) {
			setManager(manager);
		}

		Boolean primarySkill = (Boolean)attributes.get("primarySkill");

		if (primarySkill != null) {
			setPrimarySkill(primarySkill);
		}

		Integer proficiencyLevel = (Integer)attributes.get("proficiencyLevel");

		if (proficiencyLevel != null) {
			setProficiencyLevel(proficiencyLevel);
		}

		Integer requiredLevel = (Integer)attributes.get("requiredLevel");

		if (requiredLevel != null) {
			setRequiredLevel(requiredLevel);
		}
	}

	@Override
	public Skill cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the core skill of this skill.
	 *
	 * @return the core skill of this skill
	 */
	@Override
	public String getCoreSkill() {
		return model.getCoreSkill();
	}

	/**
	 * Returns the ecode of this skill.
	 *
	 * @return the ecode of this skill
	 */
	@Override
	public String getEcode() {
		return model.getEcode();
	}

	/**
	 * Returns the manager of this skill.
	 *
	 * @return the manager of this skill
	 */
	@Override
	public String getManager() {
		return model.getManager();
	}

	/**
	 * Returns the primary key of this skill.
	 *
	 * @return the primary key of this skill
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the primary skill of this skill.
	 *
	 * @return the primary skill of this skill
	 */
	@Override
	public boolean getPrimarySkill() {
		return model.getPrimarySkill();
	}

	/**
	 * Returns the proficiency level of this skill.
	 *
	 * @return the proficiency level of this skill
	 */
	@Override
	public int getProficiencyLevel() {
		return model.getProficiencyLevel();
	}

	/**
	 * Returns the project of this skill.
	 *
	 * @return the project of this skill
	 */
	@Override
	public String getProject() {
		return model.getProject();
	}

	/**
	 * Returns the required level of this skill.
	 *
	 * @return the required level of this skill
	 */
	@Override
	public int getRequiredLevel() {
		return model.getRequiredLevel();
	}

	/**
	 * Returns the reviewer of this skill.
	 *
	 * @return the reviewer of this skill
	 */
	@Override
	public String getReviewer() {
		return model.getReviewer();
	}

	/**
	 * Returns the skill ID of this skill.
	 *
	 * @return the skill ID of this skill
	 */
	@Override
	public long getSkillId() {
		return model.getSkillId();
	}

	/**
	 * Returns the status of this skill.
	 *
	 * @return the status of this skill
	 */
	@Override
	public int getStatus() {
		return model.getStatus();
	}

	/**
	 * Returns the sub skill of this skill.
	 *
	 * @return the sub skill of this skill
	 */
	@Override
	public String getSubSkill() {
		return model.getSubSkill();
	}

	/**
	 * Returns the tool of this skill.
	 *
	 * @return the tool of this skill
	 */
	@Override
	public String getTool() {
		return model.getTool();
	}

	/**
	 * Returns the validity of this skill.
	 *
	 * @return the validity of this skill
	 */
	@Override
	public String getValidity() {
		return model.getValidity();
	}

	/**
	 * Returns the version of this skill.
	 *
	 * @return the version of this skill
	 */
	@Override
	public String getVersion() {
		return model.getVersion();
	}

	/**
	 * Returns <code>true</code> if this skill is primary skill.
	 *
	 * @return <code>true</code> if this skill is primary skill; <code>false</code> otherwise
	 */
	@Override
	public boolean isPrimarySkill() {
		return model.isPrimarySkill();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the core skill of this skill.
	 *
	 * @param coreSkill the core skill of this skill
	 */
	@Override
	public void setCoreSkill(String coreSkill) {
		model.setCoreSkill(coreSkill);
	}

	/**
	 * Sets the ecode of this skill.
	 *
	 * @param ecode the ecode of this skill
	 */
	@Override
	public void setEcode(String ecode) {
		model.setEcode(ecode);
	}

	/**
	 * Sets the manager of this skill.
	 *
	 * @param manager the manager of this skill
	 */
	@Override
	public void setManager(String manager) {
		model.setManager(manager);
	}

	/**
	 * Sets the primary key of this skill.
	 *
	 * @param primaryKey the primary key of this skill
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets whether this skill is primary skill.
	 *
	 * @param primarySkill the primary skill of this skill
	 */
	@Override
	public void setPrimarySkill(boolean primarySkill) {
		model.setPrimarySkill(primarySkill);
	}

	/**
	 * Sets the proficiency level of this skill.
	 *
	 * @param proficiencyLevel the proficiency level of this skill
	 */
	@Override
	public void setProficiencyLevel(int proficiencyLevel) {
		model.setProficiencyLevel(proficiencyLevel);
	}

	/**
	 * Sets the project of this skill.
	 *
	 * @param project the project of this skill
	 */
	@Override
	public void setProject(String project) {
		model.setProject(project);
	}

	/**
	 * Sets the required level of this skill.
	 *
	 * @param requiredLevel the required level of this skill
	 */
	@Override
	public void setRequiredLevel(int requiredLevel) {
		model.setRequiredLevel(requiredLevel);
	}

	/**
	 * Sets the reviewer of this skill.
	 *
	 * @param reviewer the reviewer of this skill
	 */
	@Override
	public void setReviewer(String reviewer) {
		model.setReviewer(reviewer);
	}

	/**
	 * Sets the skill ID of this skill.
	 *
	 * @param skillId the skill ID of this skill
	 */
	@Override
	public void setSkillId(long skillId) {
		model.setSkillId(skillId);
	}

	/**
	 * Sets the status of this skill.
	 *
	 * @param status the status of this skill
	 */
	@Override
	public void setStatus(int status) {
		model.setStatus(status);
	}

	/**
	 * Sets the sub skill of this skill.
	 *
	 * @param subSkill the sub skill of this skill
	 */
	@Override
	public void setSubSkill(String subSkill) {
		model.setSubSkill(subSkill);
	}

	/**
	 * Sets the tool of this skill.
	 *
	 * @param tool the tool of this skill
	 */
	@Override
	public void setTool(String tool) {
		model.setTool(tool);
	}

	/**
	 * Sets the validity of this skill.
	 *
	 * @param validity the validity of this skill
	 */
	@Override
	public void setValidity(String validity) {
		model.setValidity(validity);
	}

	/**
	 * Sets the version of this skill.
	 *
	 * @param version the version of this skill
	 */
	@Override
	public void setVersion(String version) {
		model.setVersion(version);
	}

	@Override
	protected SkillWrapper wrap(Skill skill) {
		return new SkillWrapper(skill);
	}

}