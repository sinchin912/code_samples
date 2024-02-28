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

package com.trantorinc.synergy.probation.core.model.impl;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import com.trantorinc.synergy.probation.core.model.ProbationLine;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing ProbationLine in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ProbationLineCacheModel
	implements CacheModel<ProbationLine>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof ProbationLineCacheModel)) {
			return false;
		}

		ProbationLineCacheModel probationLineCacheModel =
			(ProbationLineCacheModel)object;

		if (lineId == probationLineCacheModel.lineId) {
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
		StringBundler sb = new StringBundler(11);

		sb.append("{lineId=");
		sb.append(lineId);
		sb.append(", probationId=");
		sb.append(probationId);
		sb.append(", lineNumber=");
		sb.append(lineNumber);
		sb.append(", rating=");
		sb.append(rating);
		sb.append(", comment=");
		sb.append(comment);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ProbationLine toEntityModel() {
		ProbationLineImpl probationLineImpl = new ProbationLineImpl();

		probationLineImpl.setLineId(lineId);

		if (probationId == null) {
			probationLineImpl.setProbationId("");
		}
		else {
			probationLineImpl.setProbationId(probationId);
		}

		probationLineImpl.setLineNumber(lineNumber);
		probationLineImpl.setRating(rating);

		if (comment == null) {
			probationLineImpl.setComment("");
		}
		else {
			probationLineImpl.setComment(comment);
		}

		probationLineImpl.resetOriginalValues();

		return probationLineImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		lineId = objectInput.readLong();
		probationId = objectInput.readUTF();

		lineNumber = objectInput.readInt();

		rating = objectInput.readInt();
		comment = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(lineId);

		if (probationId == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(probationId);
		}

		objectOutput.writeInt(lineNumber);

		objectOutput.writeInt(rating);

		if (comment == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(comment);
		}
	}

	public long lineId;
	public String probationId;
	public int lineNumber;
	public int rating;
	public String comment;

}