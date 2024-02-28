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

package com.trantorinc.synergy.performance.core.model.impl;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import com.trantorinc.synergy.performance.core.model.ReviewUpload;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing ReviewUpload in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ReviewUploadCacheModel
	implements CacheModel<ReviewUpload>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof ReviewUploadCacheModel)) {
			return false;
		}

		ReviewUploadCacheModel reviewUploadCacheModel =
			(ReviewUploadCacheModel)object;

		if (uploadId == reviewUploadCacheModel.uploadId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, uploadId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(11);

		sb.append("{uploadId=");
		sb.append(uploadId);
		sb.append(", reviewId=");
		sb.append(reviewId);
		sb.append(", attachmentName=");
		sb.append(attachmentName);
		sb.append(", fileId=");
		sb.append(fileId);
		sb.append(", createdDate=");
		sb.append(createdDate);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ReviewUpload toEntityModel() {
		ReviewUploadImpl reviewUploadImpl = new ReviewUploadImpl();

		reviewUploadImpl.setUploadId(uploadId);
		reviewUploadImpl.setReviewId(reviewId);

		if (attachmentName == null) {
			reviewUploadImpl.setAttachmentName("");
		}
		else {
			reviewUploadImpl.setAttachmentName(attachmentName);
		}

		if (fileId == null) {
			reviewUploadImpl.setFileId("");
		}
		else {
			reviewUploadImpl.setFileId(fileId);
		}

		if (createdDate == Long.MIN_VALUE) {
			reviewUploadImpl.setCreatedDate(null);
		}
		else {
			reviewUploadImpl.setCreatedDate(new Date(createdDate));
		}

		reviewUploadImpl.resetOriginalValues();

		return reviewUploadImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uploadId = objectInput.readLong();

		reviewId = objectInput.readLong();
		attachmentName = objectInput.readUTF();
		fileId = objectInput.readUTF();
		createdDate = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(uploadId);

		objectOutput.writeLong(reviewId);

		if (attachmentName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(attachmentName);
		}

		if (fileId == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(fileId);
		}

		objectOutput.writeLong(createdDate);
	}

	public long uploadId;
	public long reviewId;
	public String attachmentName;
	public String fileId;
	public long createdDate;

}