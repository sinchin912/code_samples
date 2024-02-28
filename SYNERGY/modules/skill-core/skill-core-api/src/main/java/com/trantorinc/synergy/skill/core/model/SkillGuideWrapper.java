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
 * This class is a wrapper for {@link SkillGuide}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SkillGuide
 * @generated
 */
public class SkillGuideWrapper
	extends BaseModelWrapper<SkillGuide>
	implements ModelWrapper<SkillGuide>, SkillGuide {

	public SkillGuideWrapper(SkillGuide skillGuide) {
		super(skillGuide);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("guideId", getGuideId());
		attributes.put("coreSkill", getCoreSkill());
		attributes.put("subSkill", getSubSkill());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long guideId = (Long)attributes.get("guideId");

		if (guideId != null) {
			setGuideId(guideId);
		}

		String coreSkill = (String)attributes.get("coreSkill");

		if (coreSkill != null) {
			setCoreSkill(coreSkill);
		}

		String subSkill = (String)attributes.get("subSkill");

		if (subSkill != null) {
			setSubSkill(subSkill);
		}
	}

	@Override
	public SkillGuide cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the core skill of this skill guide.
	 *
	 * @return the core skill of this skill guide
	 */
	@Override
	public String getCoreSkill() {
		return model.getCoreSkill();
	}

	/**
	 * Returns the guide ID of this skill guide.
	 *
	 * @return the guide ID of this skill guide
	 */
	@Override
	public long getGuideId() {
		return model.getGuideId();
	}

	/**
	 * Returns the primary key of this skill guide.
	 *
	 * @return the primary key of this skill guide
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the sub skill of this skill guide.
	 *
	 * @return the sub skill of this skill guide
	 */
	@Override
	public String getSubSkill() {
		return model.getSubSkill();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the core skill of this skill guide.
	 *
	 * @param coreSkill the core skill of this skill guide
	 */
	@Override
	public void setCoreSkill(String coreSkill) {
		model.setCoreSkill(coreSkill);
	}

	/**
	 * Sets the guide ID of this skill guide.
	 *
	 * @param guideId the guide ID of this skill guide
	 */
	@Override
	public void setGuideId(long guideId) {
		model.setGuideId(guideId);
	}

	/**
	 * Sets the primary key of this skill guide.
	 *
	 * @param primaryKey the primary key of this skill guide
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the sub skill of this skill guide.
	 *
	 * @param subSkill the sub skill of this skill guide
	 */
	@Override
	public void setSubSkill(String subSkill) {
		model.setSubSkill(subSkill);
	}

	@Override
	protected SkillGuideWrapper wrap(SkillGuide skillGuide) {
		return new SkillGuideWrapper(skillGuide);
	}

}