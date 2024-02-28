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
 * This class is a wrapper for {@link ReviewLine}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ReviewLine
 * @generated
 */
public class ReviewLineWrapper
	extends BaseModelWrapper<ReviewLine>
	implements ModelWrapper<ReviewLine>, ReviewLine {

	public ReviewLineWrapper(ReviewLine reviewLine) {
		super(reviewLine);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("lineId", getLineId());
		attributes.put("reviewId", getReviewId());
		attributes.put("guideId", getGuideId());
		attributes.put("title", getTitle());
		attributes.put("description", getDescription());
		attributes.put("target", getTarget());
		attributes.put("employeeRating", getEmployeeRating());
		attributes.put("employeeComment", getEmployeeComment());
		attributes.put("managerRating", getManagerRating());
		attributes.put("managerComment", getManagerComment());
		attributes.put("hrRating", getHrRating());
		attributes.put("hrComment", getHrComment());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long lineId = (Long)attributes.get("lineId");

		if (lineId != null) {
			setLineId(lineId);
		}

		Long reviewId = (Long)attributes.get("reviewId");

		if (reviewId != null) {
			setReviewId(reviewId);
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

		String employeeRating = (String)attributes.get("employeeRating");

		if (employeeRating != null) {
			setEmployeeRating(employeeRating);
		}

		String employeeComment = (String)attributes.get("employeeComment");

		if (employeeComment != null) {
			setEmployeeComment(employeeComment);
		}

		String managerRating = (String)attributes.get("managerRating");

		if (managerRating != null) {
			setManagerRating(managerRating);
		}

		String managerComment = (String)attributes.get("managerComment");

		if (managerComment != null) {
			setManagerComment(managerComment);
		}

		String hrRating = (String)attributes.get("hrRating");

		if (hrRating != null) {
			setHrRating(hrRating);
		}

		String hrComment = (String)attributes.get("hrComment");

		if (hrComment != null) {
			setHrComment(hrComment);
		}
	}

	@Override
	public ReviewLine cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the description of this review line.
	 *
	 * @return the description of this review line
	 */
	@Override
	public String getDescription() {
		return model.getDescription();
	}

	/**
	 * Returns the employee comment of this review line.
	 *
	 * @return the employee comment of this review line
	 */
	@Override
	public String getEmployeeComment() {
		return model.getEmployeeComment();
	}

	/**
	 * Returns the employee rating of this review line.
	 *
	 * @return the employee rating of this review line
	 */
	@Override
	public String getEmployeeRating() {
		return model.getEmployeeRating();
	}

	/**
	 * Returns the guide ID of this review line.
	 *
	 * @return the guide ID of this review line
	 */
	@Override
	public long getGuideId() {
		return model.getGuideId();
	}

	/**
	 * Returns the hr comment of this review line.
	 *
	 * @return the hr comment of this review line
	 */
	@Override
	public String getHrComment() {
		return model.getHrComment();
	}

	/**
	 * Returns the hr rating of this review line.
	 *
	 * @return the hr rating of this review line
	 */
	@Override
	public String getHrRating() {
		return model.getHrRating();
	}

	/**
	 * Returns the line ID of this review line.
	 *
	 * @return the line ID of this review line
	 */
	@Override
	public long getLineId() {
		return model.getLineId();
	}

	/**
	 * Returns the manager comment of this review line.
	 *
	 * @return the manager comment of this review line
	 */
	@Override
	public String getManagerComment() {
		return model.getManagerComment();
	}

	/**
	 * Returns the manager rating of this review line.
	 *
	 * @return the manager rating of this review line
	 */
	@Override
	public String getManagerRating() {
		return model.getManagerRating();
	}

	/**
	 * Returns the primary key of this review line.
	 *
	 * @return the primary key of this review line
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the review ID of this review line.
	 *
	 * @return the review ID of this review line
	 */
	@Override
	public long getReviewId() {
		return model.getReviewId();
	}

	/**
	 * Returns the target of this review line.
	 *
	 * @return the target of this review line
	 */
	@Override
	public String getTarget() {
		return model.getTarget();
	}

	/**
	 * Returns the title of this review line.
	 *
	 * @return the title of this review line
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
	 * Sets the description of this review line.
	 *
	 * @param description the description of this review line
	 */
	@Override
	public void setDescription(String description) {
		model.setDescription(description);
	}

	/**
	 * Sets the employee comment of this review line.
	 *
	 * @param employeeComment the employee comment of this review line
	 */
	@Override
	public void setEmployeeComment(String employeeComment) {
		model.setEmployeeComment(employeeComment);
	}

	/**
	 * Sets the employee rating of this review line.
	 *
	 * @param employeeRating the employee rating of this review line
	 */
	@Override
	public void setEmployeeRating(String employeeRating) {
		model.setEmployeeRating(employeeRating);
	}

	/**
	 * Sets the guide ID of this review line.
	 *
	 * @param guideId the guide ID of this review line
	 */
	@Override
	public void setGuideId(long guideId) {
		model.setGuideId(guideId);
	}

	/**
	 * Sets the hr comment of this review line.
	 *
	 * @param hrComment the hr comment of this review line
	 */
	@Override
	public void setHrComment(String hrComment) {
		model.setHrComment(hrComment);
	}

	/**
	 * Sets the hr rating of this review line.
	 *
	 * @param hrRating the hr rating of this review line
	 */
	@Override
	public void setHrRating(String hrRating) {
		model.setHrRating(hrRating);
	}

	/**
	 * Sets the line ID of this review line.
	 *
	 * @param lineId the line ID of this review line
	 */
	@Override
	public void setLineId(long lineId) {
		model.setLineId(lineId);
	}

	/**
	 * Sets the manager comment of this review line.
	 *
	 * @param managerComment the manager comment of this review line
	 */
	@Override
	public void setManagerComment(String managerComment) {
		model.setManagerComment(managerComment);
	}

	/**
	 * Sets the manager rating of this review line.
	 *
	 * @param managerRating the manager rating of this review line
	 */
	@Override
	public void setManagerRating(String managerRating) {
		model.setManagerRating(managerRating);
	}

	/**
	 * Sets the primary key of this review line.
	 *
	 * @param primaryKey the primary key of this review line
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the review ID of this review line.
	 *
	 * @param reviewId the review ID of this review line
	 */
	@Override
	public void setReviewId(long reviewId) {
		model.setReviewId(reviewId);
	}

	/**
	 * Sets the target of this review line.
	 *
	 * @param target the target of this review line
	 */
	@Override
	public void setTarget(String target) {
		model.setTarget(target);
	}

	/**
	 * Sets the title of this review line.
	 *
	 * @param title the title of this review line
	 */
	@Override
	public void setTitle(String title) {
		model.setTitle(title);
	}

	@Override
	protected ReviewLineWrapper wrap(ReviewLine reviewLine) {
		return new ReviewLineWrapper(reviewLine);
	}

}