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

import com.trantorinc.synergy.performance.core.model.ReviewTimeline;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing ReviewTimeline in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ReviewTimelineCacheModel
	implements CacheModel<ReviewTimeline>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof ReviewTimelineCacheModel)) {
			return false;
		}

		ReviewTimelineCacheModel reviewTimelineCacheModel =
			(ReviewTimelineCacheModel)object;

		if (timelineId == reviewTimelineCacheModel.timelineId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, timelineId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(11);

		sb.append("{timelineId=");
		sb.append(timelineId);
		sb.append(", reviewState=");
		sb.append(reviewState);
		sb.append(", stateName=");
		sb.append(stateName);
		sb.append(", endDate=");
		sb.append(endDate);
		sb.append(", finalYear=");
		sb.append(finalYear);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ReviewTimeline toEntityModel() {
		ReviewTimelineImpl reviewTimelineImpl = new ReviewTimelineImpl();

		reviewTimelineImpl.setTimelineId(timelineId);
		reviewTimelineImpl.setReviewState(reviewState);

		if (stateName == null) {
			reviewTimelineImpl.setStateName("");
		}
		else {
			reviewTimelineImpl.setStateName(stateName);
		}

		if (endDate == Long.MIN_VALUE) {
			reviewTimelineImpl.setEndDate(null);
		}
		else {
			reviewTimelineImpl.setEndDate(new Date(endDate));
		}

		reviewTimelineImpl.setFinalYear(finalYear);

		reviewTimelineImpl.resetOriginalValues();

		return reviewTimelineImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		timelineId = objectInput.readLong();

		reviewState = objectInput.readInt();
		stateName = objectInput.readUTF();
		endDate = objectInput.readLong();

		finalYear = objectInput.readBoolean();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(timelineId);

		objectOutput.writeInt(reviewState);

		if (stateName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(stateName);
		}

		objectOutput.writeLong(endDate);

		objectOutput.writeBoolean(finalYear);
	}

	public long timelineId;
	public int reviewState;
	public String stateName;
	public long endDate;
	public boolean finalYear;

}