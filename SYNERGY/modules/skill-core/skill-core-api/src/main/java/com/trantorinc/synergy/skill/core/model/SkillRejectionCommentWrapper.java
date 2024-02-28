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

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link SkillRejectionComment}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SkillRejectionComment
 * @generated
 */
public class SkillRejectionCommentWrapper
	extends BaseModelWrapper<SkillRejectionComment>
	implements ModelWrapper<SkillRejectionComment>, SkillRejectionComment {

	public SkillRejectionCommentWrapper(
		SkillRejectionComment skillRejectionComment) {

		super(skillRejectionComment);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("commentId", getCommentId());
		attributes.put("ecode", getEcode());
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

		String ecode = (String)attributes.get("ecode");

		if (ecode != null) {
			setEcode(ecode);
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
	public SkillRejectionComment cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the author of this skill rejection comment.
	 *
	 * @return the author of this skill rejection comment
	 */
	@Override
	public String getAuthor() {
		return model.getAuthor();
	}

	/**
	 * Returns the comment ID of this skill rejection comment.
	 *
	 * @return the comment ID of this skill rejection comment
	 */
	@Override
	public long getCommentId() {
		return model.getCommentId();
	}

	/**
	 * Returns the created date of this skill rejection comment.
	 *
	 * @return the created date of this skill rejection comment
	 */
	@Override
	public Date getCreatedDate() {
		return model.getCreatedDate();
	}

	/**
	 * Returns the description of this skill rejection comment.
	 *
	 * @return the description of this skill rejection comment
	 */
	@Override
	public String getDescription() {
		return model.getDescription();
	}

	/**
	 * Returns the ecode of this skill rejection comment.
	 *
	 * @return the ecode of this skill rejection comment
	 */
	@Override
	public String getEcode() {
		return model.getEcode();
	}

	/**
	 * Returns the primary key of this skill rejection comment.
	 *
	 * @return the primary key of this skill rejection comment
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
	 * Sets the author of this skill rejection comment.
	 *
	 * @param author the author of this skill rejection comment
	 */
	@Override
	public void setAuthor(String author) {
		model.setAuthor(author);
	}

	/**
	 * Sets the comment ID of this skill rejection comment.
	 *
	 * @param commentId the comment ID of this skill rejection comment
	 */
	@Override
	public void setCommentId(long commentId) {
		model.setCommentId(commentId);
	}

	/**
	 * Sets the created date of this skill rejection comment.
	 *
	 * @param createdDate the created date of this skill rejection comment
	 */
	@Override
	public void setCreatedDate(Date createdDate) {
		model.setCreatedDate(createdDate);
	}

	/**
	 * Sets the description of this skill rejection comment.
	 *
	 * @param description the description of this skill rejection comment
	 */
	@Override
	public void setDescription(String description) {
		model.setDescription(description);
	}

	/**
	 * Sets the ecode of this skill rejection comment.
	 *
	 * @param ecode the ecode of this skill rejection comment
	 */
	@Override
	public void setEcode(String ecode) {
		model.setEcode(ecode);
	}

	/**
	 * Sets the primary key of this skill rejection comment.
	 *
	 * @param primaryKey the primary key of this skill rejection comment
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	@Override
	protected SkillRejectionCommentWrapper wrap(
		SkillRejectionComment skillRejectionComment) {

		return new SkillRejectionCommentWrapper(skillRejectionComment);
	}

}