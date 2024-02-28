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

package com.trantorinc.synergy.notice.core.model;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link ExitState}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ExitState
 * @generated
 */
public class ExitStateWrapper
	extends BaseModelWrapper<ExitState>
	implements ExitState, ModelWrapper<ExitState> {

	public ExitStateWrapper(ExitState exitState) {
		super(exitState);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("exitStateId", getExitStateId());
		attributes.put("exitStateKey", getExitStateKey());
		attributes.put("exitStateDescription", getExitStateDescription());
		attributes.put("exitStateDisplay", getExitStateDisplay());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long exitStateId = (Long)attributes.get("exitStateId");

		if (exitStateId != null) {
			setExitStateId(exitStateId);
		}

		Integer exitStateKey = (Integer)attributes.get("exitStateKey");

		if (exitStateKey != null) {
			setExitStateKey(exitStateKey);
		}

		String exitStateDescription = (String)attributes.get(
			"exitStateDescription");

		if (exitStateDescription != null) {
			setExitStateDescription(exitStateDescription);
		}

		String exitStateDisplay = (String)attributes.get("exitStateDisplay");

		if (exitStateDisplay != null) {
			setExitStateDisplay(exitStateDisplay);
		}
	}

	@Override
	public ExitState cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the exit state description of this exit state.
	 *
	 * @return the exit state description of this exit state
	 */
	@Override
	public String getExitStateDescription() {
		return model.getExitStateDescription();
	}

	/**
	 * Returns the exit state display of this exit state.
	 *
	 * @return the exit state display of this exit state
	 */
	@Override
	public String getExitStateDisplay() {
		return model.getExitStateDisplay();
	}

	/**
	 * Returns the exit state ID of this exit state.
	 *
	 * @return the exit state ID of this exit state
	 */
	@Override
	public long getExitStateId() {
		return model.getExitStateId();
	}

	/**
	 * Returns the exit state key of this exit state.
	 *
	 * @return the exit state key of this exit state
	 */
	@Override
	public int getExitStateKey() {
		return model.getExitStateKey();
	}

	/**
	 * Returns the primary key of this exit state.
	 *
	 * @return the primary key of this exit state
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the exit state description of this exit state.
	 *
	 * @param exitStateDescription the exit state description of this exit state
	 */
	@Override
	public void setExitStateDescription(String exitStateDescription) {
		model.setExitStateDescription(exitStateDescription);
	}

	/**
	 * Sets the exit state display of this exit state.
	 *
	 * @param exitStateDisplay the exit state display of this exit state
	 */
	@Override
	public void setExitStateDisplay(String exitStateDisplay) {
		model.setExitStateDisplay(exitStateDisplay);
	}

	/**
	 * Sets the exit state ID of this exit state.
	 *
	 * @param exitStateId the exit state ID of this exit state
	 */
	@Override
	public void setExitStateId(long exitStateId) {
		model.setExitStateId(exitStateId);
	}

	/**
	 * Sets the exit state key of this exit state.
	 *
	 * @param exitStateKey the exit state key of this exit state
	 */
	@Override
	public void setExitStateKey(int exitStateKey) {
		model.setExitStateKey(exitStateKey);
	}

	/**
	 * Sets the primary key of this exit state.
	 *
	 * @param primaryKey the primary key of this exit state
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	@Override
	protected ExitStateWrapper wrap(ExitState exitState) {
		return new ExitStateWrapper(exitState);
	}

}