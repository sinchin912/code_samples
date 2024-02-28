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

import com.trantorinc.synergy.performance.core.model.ReviewRollback;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing ReviewRollback in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ReviewRollbackCacheModel
	implements CacheModel<ReviewRollback>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof ReviewRollbackCacheModel)) {
			return false;
		}

		ReviewRollbackCacheModel reviewRollbackCacheModel =
			(ReviewRollbackCacheModel)object;

		if (rollbackId == reviewRollbackCacheModel.rollbackId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, rollbackId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(13);

		sb.append("{rollbackId=");
		sb.append(rollbackId);
		sb.append(", reviewId=");
		sb.append(reviewId);
		sb.append(", requestBy=");
		sb.append(requestBy);
		sb.append(", requestDate=");
		sb.append(requestDate);
		sb.append(", actionDate=");
		sb.append(actionDate);
		sb.append(", status=");
		sb.append(status);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ReviewRollback toEntityModel() {
		ReviewRollbackImpl reviewRollbackImpl = new ReviewRollbackImpl();

		reviewRollbackImpl.setRollbackId(rollbackId);
		reviewRollbackImpl.setReviewId(reviewId);

		if (requestBy == null) {
			reviewRollbackImpl.setRequestBy("");
		}
		else {
			reviewRollbackImpl.setRequestBy(requestBy);
		}

		if (requestDate == Long.MIN_VALUE) {
			reviewRollbackImpl.setRequestDate(null);
		}
		else {
			reviewRollbackImpl.setRequestDate(new Date(requestDate));
		}

		if (actionDate == Long.MIN_VALUE) {
			reviewRollbackImpl.setActionDate(null);
		}
		else {
			reviewRollbackImpl.setActionDate(new Date(actionDate));
		}

		reviewRollbackImpl.setStatus(status);

		reviewRollbackImpl.resetOriginalValues();

		return reviewRollbackImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		rollbackId = objectInput.readLong();

		reviewId = objectInput.readLong();
		requestBy = objectInput.readUTF();
		requestDate = objectInput.readLong();
		actionDate = objectInput.readLong();

		status = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(rollbackId);

		objectOutput.writeLong(reviewId);

		if (requestBy == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(requestBy);
		}

		objectOutput.writeLong(requestDate);
		objectOutput.writeLong(actionDate);

		objectOutput.writeInt(status);
	}

	public long rollbackId;
	public long reviewId;
	public String requestBy;
	public long requestDate;
	public long actionDate;
	public int status;

}