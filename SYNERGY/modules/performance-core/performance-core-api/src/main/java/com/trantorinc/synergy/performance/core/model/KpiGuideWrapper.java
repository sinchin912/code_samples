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

package com.trantorinc.synergy.performance.core.model;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link KpiGuide}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see KpiGuide
 * @generated
 */
public class KpiGuideWrapper
	extends BaseModelWrapper<KpiGuide>
	implements KpiGuide, ModelWrapper<KpiGuide> {

	public KpiGuideWrapper(KpiGuide kpiGuide) {
		super(kpiGuide);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("guideId", getGuideId());
		attributes.put("title", getTitle());
		attributes.put("description", getDescription());
		attributes.put("attribute", isAttribute());
		attributes.put("other", isOther());
		attributes.put("mandatory", isMandatory());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long guideId = (Long)attributes.get("guideId");

		if (guideId != null) {
			setGuideId(guideId);
		}

		String title = (String)attributes.get("title");

		if (title != null) {
			setTitle(title);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		Boolean attribute = (Boolean)attributes.get("attribute");

		if (attribute != null) {
			setAttribute(attribute);
		}

		Boolean other = (Boolean)attributes.get("other");

		if (other != null) {
			setOther(other);
		}

		Boolean mandatory = (Boolean)attributes.get("mandatory");

		if (mandatory != null) {
			setMandatory(mandatory);
		}
	}

	@Override
	public KpiGuide cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the attribute of this kpi guide.
	 *
	 * @return the attribute of this kpi guide
	 */
	@Override
	public boolean getAttribute() {
		return model.getAttribute();
	}

	/**
	 * Returns the description of this kpi guide.
	 *
	 * @return the description of this kpi guide
	 */
	@Override
	public String getDescription() {
		return model.getDescription();
	}

	/**
	 * Returns the guide ID of this kpi guide.
	 *
	 * @return the guide ID of this kpi guide
	 */
	@Override
	public long getGuideId() {
		return model.getGuideId();
	}

	/**
	 * Returns the mandatory of this kpi guide.
	 *
	 * @return the mandatory of this kpi guide
	 */
	@Override
	public boolean getMandatory() {
		return model.getMandatory();
	}

	/**
	 * Returns the other of this kpi guide.
	 *
	 * @return the other of this kpi guide
	 */
	@Override
	public boolean getOther() {
		return model.getOther();
	}

	/**
	 * Returns the primary key of this kpi guide.
	 *
	 * @return the primary key of this kpi guide
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the title of this kpi guide.
	 *
	 * @return the title of this kpi guide
	 */
	@Override
	public String getTitle() {
		return model.getTitle();
	}

	/**
	 * Returns <code>true</code> if this kpi guide is attribute.
	 *
	 * @return <code>true</code> if this kpi guide is attribute; <code>false</code> otherwise
	 */
	@Override
	public boolean isAttribute() {
		return model.isAttribute();
	}

	/**
	 * Returns <code>true</code> if this kpi guide is mandatory.
	 *
	 * @return <code>true</code> if this kpi guide is mandatory; <code>false</code> otherwise
	 */
	@Override
	public boolean isMandatory() {
		return model.isMandatory();
	}

	/**
	 * Returns <code>true</code> if this kpi guide is other.
	 *
	 * @return <code>true</code> if this kpi guide is other; <code>false</code> otherwise
	 */
	@Override
	public boolean isOther() {
		return model.isOther();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets whether this kpi guide is attribute.
	 *
	 * @param attribute the attribute of this kpi guide
	 */
	@Override
	public void setAttribute(boolean attribute) {
		model.setAttribute(attribute);
	}

	/**
	 * Sets the description of this kpi guide.
	 *
	 * @param description the description of this kpi guide
	 */
	@Override
	public void setDescription(String description) {
		model.setDescription(description);
	}

	/**
	 * Sets the guide ID of this kpi guide.
	 *
	 * @param guideId the guide ID of this kpi guide
	 */
	@Override
	public void setGuideId(long guideId) {
		model.setGuideId(guideId);
	}

	/**
	 * Sets whether this kpi guide is mandatory.
	 *
	 * @param mandatory the mandatory of this kpi guide
	 */
	@Override
	public void setMandatory(boolean mandatory) {
		model.setMandatory(mandatory);
	}

	/**
	 * Sets whether this kpi guide is other.
	 *
	 * @param other the other of this kpi guide
	 */
	@Override
	public void setOther(boolean other) {
		model.setOther(other);
	}

	/**
	 * Sets the primary key of this kpi guide.
	 *
	 * @param primaryKey the primary key of this kpi guide
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the title of this kpi guide.
	 *
	 * @param title the title of this kpi guide
	 */
	@Override
	public void setTitle(String title) {
		model.setTitle(title);
	}

	@Override
	protected KpiGuideWrapper wrap(KpiGuide kpiGuide) {
		return new KpiGuideWrapper(kpiGuide);
	}

}