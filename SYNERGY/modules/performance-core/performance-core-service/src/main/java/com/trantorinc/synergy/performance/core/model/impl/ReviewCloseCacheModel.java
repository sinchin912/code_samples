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

import com.trantorinc.synergy.performance.core.model.ReviewClose;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing ReviewClose in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ReviewCloseCacheModel
	implements CacheModel<ReviewClose>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof ReviewCloseCacheModel)) {
			return false;
		}

		ReviewCloseCacheModel reviewCloseCacheModel =
			(ReviewCloseCacheModel)object;

		if (closeId == reviewCloseCacheModel.closeId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, closeId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(13);

		sb.append("{closeId=");
		sb.append(closeId);
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
	public ReviewClose toEntityModel() {
		ReviewCloseImpl reviewCloseImpl = new ReviewCloseImpl();

		reviewCloseImpl.setCloseId(closeId);
		reviewCloseImpl.setReviewId(reviewId);

		if (requestBy == null) {
			reviewCloseImpl.setRequestBy("");
		}
		else {
			reviewCloseImpl.setRequestBy(requestBy);
		}

		if (requestDate == Long.MIN_VALUE) {
			reviewCloseImpl.setRequestDate(null);
		}
		else {
			reviewCloseImpl.setRequestDate(new Date(requestDate));
		}

		if (actionDate == Long.MIN_VALUE) {
			reviewCloseImpl.setActionDate(null);
		}
		else {
			reviewCloseImpl.setActionDate(new Date(actionDate));
		}

		reviewCloseImpl.setStatus(status);

		reviewCloseImpl.resetOriginalValues();

		return reviewCloseImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		closeId = objectInput.readLong();

		reviewId = objectInput.readLong();
		requestBy = objectInput.readUTF();
		requestDate = objectInput.readLong();
		actionDate = objectInput.readLong();

		status = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(closeId);

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

	public long closeId;
	public long reviewId;
	public String requestBy;
	public long requestDate;
	public long actionDate;
	public int status;

}