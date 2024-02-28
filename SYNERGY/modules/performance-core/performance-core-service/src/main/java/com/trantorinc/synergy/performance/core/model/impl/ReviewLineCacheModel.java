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

import com.trantorinc.synergy.performance.core.model.ReviewLine;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing ReviewLine in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ReviewLineCacheModel
	implements CacheModel<ReviewLine>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof ReviewLineCacheModel)) {
			return false;
		}

		ReviewLineCacheModel reviewLineCacheModel =
			(ReviewLineCacheModel)object;

		if (lineId == reviewLineCacheModel.lineId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, lineId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(25);

		sb.append("{lineId=");
		sb.append(lineId);
		sb.append(", reviewId=");
		sb.append(reviewId);
		sb.append(", guideId=");
		sb.append(guideId);
		sb.append(", title=");
		sb.append(title);
		sb.append(", description=");
		sb.append(description);
		sb.append(", target=");
		sb.append(target);
		sb.append(", employeeRating=");
		sb.append(employeeRating);
		sb.append(", employeeComment=");
		sb.append(employeeComment);
		sb.append(", managerRating=");
		sb.append(managerRating);
		sb.append(", managerComment=");
		sb.append(managerComment);
		sb.append(", hrRating=");
		sb.append(hrRating);
		sb.append(", hrComment=");
		sb.append(hrComment);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ReviewLine toEntityModel() {
		ReviewLineImpl reviewLineImpl = new ReviewLineImpl();

		reviewLineImpl.setLineId(lineId);
		reviewLineImpl.setReviewId(reviewId);
		reviewLineImpl.setGuideId(guideId);

		if (title == null) {
			reviewLineImpl.setTitle("");
		}
		else {
			reviewLineImpl.setTitle(title);
		}

		if (description == null) {
			reviewLineImpl.setDescription("");
		}
		else {
			reviewLineImpl.setDescription(description);
		}

		if (target == null) {
			reviewLineImpl.setTarget("");
		}
		else {
			reviewLineImpl.setTarget(target);
		}

		if (employeeRating == null) {
			reviewLineImpl.setEmployeeRating("");
		}
		else {
			reviewLineImpl.setEmployeeRating(employeeRating);
		}

		if (employeeComment == null) {
			reviewLineImpl.setEmployeeComment("");
		}
		else {
			reviewLineImpl.setEmployeeComment(employeeComment);
		}

		if (managerRating == null) {
			reviewLineImpl.setManagerRating("");
		}
		else {
			reviewLineImpl.setManagerRating(managerRating);
		}

		if (managerComment == null) {
			reviewLineImpl.setManagerComment("");
		}
		else {
			reviewLineImpl.setManagerComment(managerComment);
		}

		if (hrRating == null) {
			reviewLineImpl.setHrRating("");
		}
		else {
			reviewLineImpl.setHrRating(hrRating);
		}

		if (hrComment == null) {
			reviewLineImpl.setHrComment("");
		}
		else {
			reviewLineImpl.setHrComment(hrComment);
		}

		reviewLineImpl.resetOriginalValues();

		return reviewLineImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		lineId = objectInput.readLong();

		reviewId = objectInput.readLong();

		guideId = objectInput.readLong();
		title = objectInput.readUTF();
		description = objectInput.readUTF();
		target = objectInput.readUTF();
		employeeRating = objectInput.readUTF();
		employeeComment = objectInput.readUTF();
		managerRating = objectInput.readUTF();
		managerComment = objectInput.readUTF();
		hrRating = objectInput.readUTF();
		hrComment = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(lineId);

		objectOutput.writeLong(reviewId);

		objectOutput.writeLong(guideId);

		if (title == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(title);
		}

		if (description == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(description);
		}

		if (target == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(target);
		}

		if (employeeRating == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(employeeRating);
		}

		if (employeeComment == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(employeeComment);
		}

		if (managerRating == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(managerRating);
		}

		if (managerComment == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(managerComment);
		}

		if (hrRating == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(hrRating);
		}

		if (hrComment == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(hrComment);
		}
	}

	public long lineId;
	public long reviewId;
	public long guideId;
	public String title;
	public String description;
	public String target;
	public String employeeRating;
	public String employeeComment;
	public String managerRating;
	public String managerComment;
	public String hrRating;
	public String hrComment;

}