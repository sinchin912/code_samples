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
 * This class is a wrapper for {@link ReviewTimeline}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ReviewTimeline
 * @generated
 */
public class ReviewTimelineWrapper
	extends BaseModelWrapper<ReviewTimeline>
	implements ModelWrapper<ReviewTimeline>, ReviewTimeline {

	public ReviewTimelineWrapper(ReviewTimeline reviewTimeline) {
		super(reviewTimeline);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("timelineId", getTimelineId());
		attributes.put("reviewState", getReviewState());
		attributes.put("stateName", getStateName());
		attributes.put("endDate", getEndDate());
		attributes.put("finalYear", isFinalYear());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long timelineId = (Long)attributes.get("timelineId");

		if (timelineId != null) {
			setTimelineId(timelineId);
		}

		Integer reviewState = (Integer)attributes.get("reviewState");

		if (reviewState != null) {
			setReviewState(reviewState);
		}

		String stateName = (String)attributes.get("stateName");

		if (stateName != null) {
			setStateName(stateName);
		}

		Date endDate = (Date)attributes.get("endDate");

		if (endDate != null) {
			setEndDate(endDate);
		}

		Boolean finalYear = (Boolean)attributes.get("finalYear");

		if (finalYear != null) {
			setFinalYear(finalYear);
		}
	}

	@Override
	public ReviewTimeline cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the end date of this review timeline.
	 *
	 * @return the end date of this review timeline
	 */
	@Override
	public Date getEndDate() {
		return model.getEndDate();
	}

	/**
	 * Returns the final year of this review timeline.
	 *
	 * @return the final year of this review timeline
	 */
	@Override
	public boolean getFinalYear() {
		return model.getFinalYear();
	}

	/**
	 * Returns the primary key of this review timeline.
	 *
	 * @return the primary key of this review timeline
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the review state of this review timeline.
	 *
	 * @return the review state of this review timeline
	 */
	@Override
	public int getReviewState() {
		return model.getReviewState();
	}

	/**
	 * Returns the state name of this review timeline.
	 *
	 * @return the state name of this review timeline
	 */
	@Override
	public String getStateName() {
		return model.getStateName();
	}

	/**
	 * Returns the timeline ID of this review timeline.
	 *
	 * @return the timeline ID of this review timeline
	 */
	@Override
	public long getTimelineId() {
		return model.getTimelineId();
	}

	/**
	 * Returns <code>true</code> if this review timeline is final year.
	 *
	 * @return <code>true</code> if this review timeline is final year; <code>false</code> otherwise
	 */
	@Override
	public boolean isFinalYear() {
		return model.isFinalYear();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the end date of this review timeline.
	 *
	 * @param endDate the end date of this review timeline
	 */
	@Override
	public void setEndDate(Date endDate) {
		model.setEndDate(endDate);
	}

	/**
	 * Sets whether this review timeline is final year.
	 *
	 * @param finalYear the final year of this review timeline
	 */
	@Override
	public void setFinalYear(boolean finalYear) {
		model.setFinalYear(finalYear);
	}

	/**
	 * Sets the primary key of this review timeline.
	 *
	 * @param primaryKey the primary key of this review timeline
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the review state of this review timeline.
	 *
	 * @param reviewState the review state of this review timeline
	 */
	@Override
	public void setReviewState(int reviewState) {
		model.setReviewState(reviewState);
	}

	/**
	 * Sets the state name of this review timeline.
	 *
	 * @param stateName the state name of this review timeline
	 */
	@Override
	public void setStateName(String stateName) {
		model.setStateName(stateName);
	}

	/**
	 * Sets the timeline ID of this review timeline.
	 *
	 * @param timelineId the timeline ID of this review timeline
	 */
	@Override
	public void setTimelineId(long timelineId) {
		model.setTimelineId(timelineId);
	}

	@Override
	protected ReviewTimelineWrapper wrap(ReviewTimeline reviewTimeline) {
		return new ReviewTimelineWrapper(reviewTimeline);
	}

}