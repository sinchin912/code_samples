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

package com.trantorinc.synergy.probation.core.model;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link ProbationLine}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ProbationLine
 * @generated
 */
public class ProbationLineWrapper
	extends BaseModelWrapper<ProbationLine>
	implements ModelWrapper<ProbationLine>, ProbationLine {

	public ProbationLineWrapper(ProbationLine probationLine) {
		super(probationLine);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("lineId", getLineId());
		attributes.put("probationId", getProbationId());
		attributes.put("lineNumber", getLineNumber());
		attributes.put("rating", getRating());
		attributes.put("comment", getComment());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long lineId = (Long)attributes.get("lineId");

		if (lineId != null) {
			setLineId(lineId);
		}

		String probationId = (String)attributes.get("probationId");

		if (probationId != null) {
			setProbationId(probationId);
		}

		Integer lineNumber = (Integer)attributes.get("lineNumber");

		if (lineNumber != null) {
			setLineNumber(lineNumber);
		}

		Integer rating = (Integer)attributes.get("rating");

		if (rating != null) {
			setRating(rating);
		}

		String comment = (String)attributes.get("comment");

		if (comment != null) {
			setComment(comment);
		}
	}

	@Override
	public ProbationLine cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the comment of this probation line.
	 *
	 * @return the comment of this probation line
	 */
	@Override
	public String getComment() {
		return model.getComment();
	}

	/**
	 * Returns the line ID of this probation line.
	 *
	 * @return the line ID of this probation line
	 */
	@Override
	public long getLineId() {
		return model.getLineId();
	}

	/**
	 * Returns the line number of this probation line.
	 *
	 * @return the line number of this probation line
	 */
	@Override
	public int getLineNumber() {
		return model.getLineNumber();
	}

	/**
	 * Returns the primary key of this probation line.
	 *
	 * @return the primary key of this probation line
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the probation ID of this probation line.
	 *
	 * @return the probation ID of this probation line
	 */
	@Override
	public String getProbationId() {
		return model.getProbationId();
	}

	/**
	 * Returns the rating of this probation line.
	 *
	 * @return the rating of this probation line
	 */
	@Override
	public int getRating() {
		return model.getRating();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the comment of this probation line.
	 *
	 * @param comment the comment of this probation line
	 */
	@Override
	public void setComment(String comment) {
		model.setComment(comment);
	}

	/**
	 * Sets the line ID of this probation line.
	 *
	 * @param lineId the line ID of this probation line
	 */
	@Override
	public void setLineId(long lineId) {
		model.setLineId(lineId);
	}

	/**
	 * Sets the line number of this probation line.
	 *
	 * @param lineNumber the line number of this probation line
	 */
	@Override
	public void setLineNumber(int lineNumber) {
		model.setLineNumber(lineNumber);
	}

	/**
	 * Sets the primary key of this probation line.
	 *
	 * @param primaryKey the primary key of this probation line
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the probation ID of this probation line.
	 *
	 * @param probationId the probation ID of this probation line
	 */
	@Override
	public void setProbationId(String probationId) {
		model.setProbationId(probationId);
	}

	/**
	 * Sets the rating of this probation line.
	 *
	 * @param rating the rating of this probation line
	 */
	@Override
	public void setRating(int rating) {
		model.setRating(rating);
	}

	@Override
	protected ProbationLineWrapper wrap(ProbationLine probationLine) {
		return new ProbationLineWrapper(probationLine);
	}

}