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

package com.trantorinc.synergy.game.core.model;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link GameTimeline}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see GameTimeline
 * @generated
 */
public class GameTimelineWrapper
	extends BaseModelWrapper<GameTimeline>
	implements GameTimeline, ModelWrapper<GameTimeline> {

	public GameTimelineWrapper(GameTimeline gameTimeline) {
		super(gameTimeline);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("name", getName());
		attributes.put("openDate", getOpenDate());
		attributes.put("freezeDate", getFreezeDate());
		attributes.put("actionDate", getActionDate());
		attributes.put("closeDate", getCloseDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		Date openDate = (Date)attributes.get("openDate");

		if (openDate != null) {
			setOpenDate(openDate);
		}

		Date freezeDate = (Date)attributes.get("freezeDate");

		if (freezeDate != null) {
			setFreezeDate(freezeDate);
		}

		Date actionDate = (Date)attributes.get("actionDate");

		if (actionDate != null) {
			setActionDate(actionDate);
		}

		Date closeDate = (Date)attributes.get("closeDate");

		if (closeDate != null) {
			setCloseDate(closeDate);
		}
	}

	@Override
	public GameTimeline cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the action date of this game timeline.
	 *
	 * @return the action date of this game timeline
	 */
	@Override
	public Date getActionDate() {
		return model.getActionDate();
	}

	/**
	 * Returns the close date of this game timeline.
	 *
	 * @return the close date of this game timeline
	 */
	@Override
	public Date getCloseDate() {
		return model.getCloseDate();
	}

	/**
	 * Returns the freeze date of this game timeline.
	 *
	 * @return the freeze date of this game timeline
	 */
	@Override
	public Date getFreezeDate() {
		return model.getFreezeDate();
	}

	/**
	 * Returns the name of this game timeline.
	 *
	 * @return the name of this game timeline
	 */
	@Override
	public String getName() {
		return model.getName();
	}

	/**
	 * Returns the open date of this game timeline.
	 *
	 * @return the open date of this game timeline
	 */
	@Override
	public Date getOpenDate() {
		return model.getOpenDate();
	}

	/**
	 * Returns the primary key of this game timeline.
	 *
	 * @return the primary key of this game timeline
	 */
	@Override
	public String getPrimaryKey() {
		return model.getPrimaryKey();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the action date of this game timeline.
	 *
	 * @param actionDate the action date of this game timeline
	 */
	@Override
	public void setActionDate(Date actionDate) {
		model.setActionDate(actionDate);
	}

	/**
	 * Sets the close date of this game timeline.
	 *
	 * @param closeDate the close date of this game timeline
	 */
	@Override
	public void setCloseDate(Date closeDate) {
		model.setCloseDate(closeDate);
	}

	/**
	 * Sets the freeze date of this game timeline.
	 *
	 * @param freezeDate the freeze date of this game timeline
	 */
	@Override
	public void setFreezeDate(Date freezeDate) {
		model.setFreezeDate(freezeDate);
	}

	/**
	 * Sets the name of this game timeline.
	 *
	 * @param name the name of this game timeline
	 */
	@Override
	public void setName(String name) {
		model.setName(name);
	}

	/**
	 * Sets the open date of this game timeline.
	 *
	 * @param openDate the open date of this game timeline
	 */
	@Override
	public void setOpenDate(Date openDate) {
		model.setOpenDate(openDate);
	}

	/**
	 * Sets the primary key of this game timeline.
	 *
	 * @param primaryKey the primary key of this game timeline
	 */
	@Override
	public void setPrimaryKey(String primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	@Override
	protected GameTimelineWrapper wrap(GameTimeline gameTimeline) {
		return new GameTimelineWrapper(gameTimeline);
	}

}