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
 * This class is a wrapper for {@link ReviewComment}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ReviewComment
 * @generated
 */
public class ReviewCommentWrapper
	extends BaseModelWrapper<ReviewComment>
	implements ModelWrapper<ReviewComment>, ReviewComment {

	public ReviewCommentWrapper(ReviewComment reviewComment) {
		super(reviewComment);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("commentId", getCommentId());
		attributes.put("reviewId", getReviewId());
		attributes.put("author", getAuthor());
		attributes.put("description", getDescription());
		attributes.put("createdDate", getCreatedDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long commentId = (Long)attributes.get("commentId");

		if (commentId != null) {
			setCommentId(commentId);
		}

		Long reviewId = (Long)attributes.get("reviewId");

		if (reviewId != null) {
			setReviewId(reviewId);
		}

		String author = (String)attributes.get("author");

		if (author != null) {
			setAuthor(author);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		Date createdDate = (Date)attributes.get("createdDate");

		if (createdDate != null) {
			setCreatedDate(createdDate);
		}
	}

	@Override
	public ReviewComment cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the author of this review comment.
	 *
	 * @return the author of this review comment
	 */
	@Override
	public String getAuthor() {
		return model.getAuthor();
	}

	/**
	 * Returns the comment ID of this review comment.
	 *
	 * @return the comment ID of this review comment
	 */
	@Override
	public long getCommentId() {
		return model.getCommentId();
	}

	/**
	 * Returns the created date of this review comment.
	 *
	 * @return the created date of this review comment
	 */
	@Override
	public Date getCreatedDate() {
		return model.getCreatedDate();
	}

	/**
	 * Returns the description of this review comment.
	 *
	 * @return the description of this review comment
	 */
	@Override
	public String getDescription() {
		return model.getDescription();
	}

	/**
	 * Returns the primary key of this review comment.
	 *
	 * @return the primary key of this review comment
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the review ID of this review comment.
	 *
	 * @return the review ID of this review comment
	 */
	@Override
	public long getReviewId() {
		return model.getReviewId();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the author of this review comment.
	 *
	 * @param author the author of this review comment
	 */
	@Override
	public void setAuthor(String author) {
		model.setAuthor(author);
	}

	/**
	 * Sets the comment ID of this review comment.
	 *
	 * @param commentId the comment ID of this review comment
	 */
	@Override
	public void setCommentId(long commentId) {
		model.setCommentId(commentId);
	}

	/**
	 * Sets the created date of this review comment.
	 *
	 * @param createdDate the created date of this review comment
	 */
	@Override
	public void setCreatedDate(Date createdDate) {
		model.setCreatedDate(createdDate);
	}

	/**
	 * Sets the description of this review comment.
	 *
	 * @param description the description of this review comment
	 */
	@Override
	public void setDescription(String description) {
		model.setDescription(description);
	}

	/**
	 * Sets the primary key of this review comment.
	 *
	 * @param primaryKey the primary key of this review comment
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the review ID of this review comment.
	 *
	 * @param reviewId the review ID of this review comment
	 */
	@Override
	public void setReviewId(long reviewId) {
		model.setReviewId(reviewId);
	}

	@Override
	protected ReviewCommentWrapper wrap(ReviewComment reviewComment) {
		return new ReviewCommentWrapper(reviewComment);
	}

}