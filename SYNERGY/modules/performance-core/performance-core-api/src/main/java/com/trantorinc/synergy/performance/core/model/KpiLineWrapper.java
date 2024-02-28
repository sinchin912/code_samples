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

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link KpiLine}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see KpiLine
 * @generated
 */
public class KpiLineWrapper
	extends BaseModelWrapper<KpiLine>
	implements KpiLine, ModelWrapper<KpiLine> {

	public KpiLineWrapper(KpiLine kpiLine) {
		super(kpiLine);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("lineId", getLineId());
		attributes.put("kpiId", getKpiId());
		attributes.put("guideId", getGuideId());
		attributes.put("title", getTitle());
		attributes.put("description", getDescription());
		attributes.put("target", getTarget());
		attributes.put("createDate", getCreateDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long lineId = (Long)attributes.get("lineId");

		if (lineId != null) {
			setLineId(lineId);
		}

		Long kpiId = (Long)attributes.get("kpiId");

		if (kpiId != null) {
			setKpiId(kpiId);
		}

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

		String target = (String)attributes.get("target");

		if (target != null) {
			setTarget(target);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}
	}

	@Override
	public KpiLine cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the create date of this kpi line.
	 *
	 * @return the create date of this kpi line
	 */
	@Override
	public Date getCreateDate() {
		return model.getCreateDate();
	}

	/**
	 * Returns the description of this kpi line.
	 *
	 * @return the description of this kpi line
	 */
	@Override
	public String getDescription() {
		return model.getDescription();
	}

	/**
	 * Returns the guide ID of this kpi line.
	 *
	 * @return the guide ID of this kpi line
	 */
	@Override
	public long getGuideId() {
		return model.getGuideId();
	}

	/**
	 * Returns the kpi ID of this kpi line.
	 *
	 * @return the kpi ID of this kpi line
	 */
	@Override
	public long getKpiId() {
		return model.getKpiId();
	}

	/**
	 * Returns the line ID of this kpi line.
	 *
	 * @return the line ID of this kpi line
	 */
	@Override
	public long getLineId() {
		return model.getLineId();
	}

	/**
	 * Returns the primary key of this kpi line.
	 *
	 * @return the primary key of this kpi line
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the target of this kpi line.
	 *
	 * @return the target of this kpi line
	 */
	@Override
	public String getTarget() {
		return model.getTarget();
	}

	/**
	 * Returns the title of this kpi line.
	 *
	 * @return the title of this kpi line
	 */
	@Override
	public String getTitle() {
		return model.getTitle();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the create date of this kpi line.
	 *
	 * @param createDate the create date of this kpi line
	 */
	@Override
	public void setCreateDate(Date createDate) {
		model.setCreateDate(createDate);
	}

	/**
	 * Sets the description of this kpi line.
	 *
	 * @param description the description of this kpi line
	 */
	@Override
	public void setDescription(String description) {
		model.setDescription(description);
	}

	/**
	 * Sets the guide ID of this kpi line.
	 *
	 * @param guideId the guide ID of this kpi line
	 */
	@Override
	public void setGuideId(long guideId) {
		model.setGuideId(guideId);
	}

	/**
	 * Sets the kpi ID of this kpi line.
	 *
	 * @param kpiId the kpi ID of this kpi line
	 */
	@Override
	public void setKpiId(long kpiId) {
		model.setKpiId(kpiId);
	}

	/**
	 * Sets the line ID of this kpi line.
	 *
	 * @param lineId the line ID of this kpi line
	 */
	@Override
	public void setLineId(long lineId) {
		model.setLineId(lineId);
	}

	/**
	 * Sets the primary key of this kpi line.
	 *
	 * @param primaryKey the primary key of this kpi line
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the target of this kpi line.
	 *
	 * @param target the target of this kpi line
	 */
	@Override
	public void setTarget(String target) {
		model.setTarget(target);
	}

	/**
	 * Sets the title of this kpi line.
	 *
	 * @param title the title of this kpi line
	 */
	@Override
	public void setTitle(String title) {
		model.setTitle(title);
	}

	@Override
	protected KpiLineWrapper wrap(KpiLine kpiLine) {
		return new KpiLineWrapper(kpiLine);
	}

}