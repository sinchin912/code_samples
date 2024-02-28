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
 * This class is a wrapper for {@link ReviewUpload}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ReviewUpload
 * @generated
 */
public class ReviewUploadWrapper
	extends BaseModelWrapper<ReviewUpload>
	implements ModelWrapper<ReviewUpload>, ReviewUpload {

	public ReviewUploadWrapper(ReviewUpload reviewUpload) {
		super(reviewUpload);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uploadId", getUploadId());
		attributes.put("reviewId", getReviewId());
		attributes.put("attachmentName", getAttachmentName());
		attributes.put("fileId", getFileId());
		attributes.put("createdDate", getCreatedDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long uploadId = (Long)attributes.get("uploadId");

		if (uploadId != null) {
			setUploadId(uploadId);
		}

		Long reviewId = (Long)attributes.get("reviewId");

		if (reviewId != null) {
			setReviewId(reviewId);
		}

		String attachmentName = (String)attributes.get("attachmentName");

		if (attachmentName != null) {
			setAttachmentName(attachmentName);
		}

		String fileId = (String)attributes.get("fileId");

		if (fileId != null) {
			setFileId(fileId);
		}

		Date createdDate = (Date)attributes.get("createdDate");

		if (createdDate != null) {
			setCreatedDate(createdDate);
		}
	}

	@Override
	public ReviewUpload cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the attachment name of this review upload.
	 *
	 * @return the attachment name of this review upload
	 */
	@Override
	public String getAttachmentName() {
		return model.getAttachmentName();
	}

	/**
	 * Returns the created date of this review upload.
	 *
	 * @return the created date of this review upload
	 */
	@Override
	public Date getCreatedDate() {
		return model.getCreatedDate();
	}

	/**
	 * Returns the file ID of this review upload.
	 *
	 * @return the file ID of this review upload
	 */
	@Override
	public String getFileId() {
		return model.getFileId();
	}

	/**
	 * Returns the primary key of this review upload.
	 *
	 * @return the primary key of this review upload
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the review ID of this review upload.
	 *
	 * @return the review ID of this review upload
	 */
	@Override
	public long getReviewId() {
		return model.getReviewId();
	}

	/**
	 * Returns the upload ID of this review upload.
	 *
	 * @return the upload ID of this review upload
	 */
	@Override
	public long getUploadId() {
		return model.getUploadId();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the attachment name of this review upload.
	 *
	 * @param attachmentName the attachment name of this review upload
	 */
	@Override
	public void setAttachmentName(String attachmentName) {
		model.setAttachmentName(attachmentName);
	}

	/**
	 * Sets the created date of this review upload.
	 *
	 * @param createdDate the created date of this review upload
	 */
	@Override
	public void setCreatedDate(Date createdDate) {
		model.setCreatedDate(createdDate);
	}

	/**
	 * Sets the file ID of this review upload.
	 *
	 * @param fileId the file ID of this review upload
	 */
	@Override
	public void setFileId(String fileId) {
		model.setFileId(fileId);
	}

	/**
	 * Sets the primary key of this review upload.
	 *
	 * @param primaryKey the primary key of this review upload
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the review ID of this review upload.
	 *
	 * @param reviewId the review ID of this review upload
	 */
	@Override
	public void setReviewId(long reviewId) {
		model.setReviewId(reviewId);
	}

	/**
	 * Sets the upload ID of this review upload.
	 *
	 * @param uploadId the upload ID of this review upload
	 */
	@Override
	public void setUploadId(long uploadId) {
		model.setUploadId(uploadId);
	}

	@Override
	protected ReviewUploadWrapper wrap(ReviewUpload reviewUpload) {
		return new ReviewUploadWrapper(reviewUpload);
	}

}