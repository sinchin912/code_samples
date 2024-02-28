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

package com.trantorinc.synergy.skill.core.model.impl;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import com.trantorinc.synergy.skill.core.model.SkillRejectionComment;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing SkillRejectionComment in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class SkillRejectionCommentCacheModel
	implements CacheModel<SkillRejectionComment>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof SkillRejectionCommentCacheModel)) {
			return false;
		}

		SkillRejectionCommentCacheModel skillRejectionCommentCacheModel =
			(SkillRejectionCommentCacheModel)object;

		if (commentId == skillRejectionCommentCacheModel.commentId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, commentId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(11);

		sb.append("{commentId=");
		sb.append(commentId);
		sb.append(", ecode=");
		sb.append(ecode);
		sb.append(", author=");
		sb.append(author);
		sb.append(", description=");
		sb.append(description);
		sb.append(", createdDate=");
		sb.append(createdDate);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public SkillRejectionComment toEntityModel() {
		SkillRejectionCommentImpl skillRejectionCommentImpl =
			new SkillRejectionCommentImpl();

		skillRejectionCommentImpl.setCommentId(commentId);

		if (ecode == null) {
			skillRejectionCommentImpl.setEcode("");
		}
		else {
			skillRejectionCommentImpl.setEcode(ecode);
		}

		if (author == null) {
			skillRejectionCommentImpl.setAuthor("");
		}
		else {
			skillRejectionCommentImpl.setAuthor(author);
		}

		if (description == null) {
			skillRejectionCommentImpl.setDescription("");
		}
		else {
			skillRejectionCommentImpl.setDescription(description);
		}

		if (createdDate == Long.MIN_VALUE) {
			skillRejectionCommentImpl.setCreatedDate(null);
		}
		else {
			skillRejectionCommentImpl.setCreatedDate(new Date(createdDate));
		}

		skillRejectionCommentImpl.resetOriginalValues();

		return skillRejectionCommentImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		commentId = objectInput.readLong();
		ecode = objectInput.readUTF();
		author = objectInput.readUTF();
		description = objectInput.readUTF();
		createdDate = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(commentId);

		if (ecode == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(ecode);
		}

		if (author == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(author);
		}

		if (description == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(description);
		}

		objectOutput.writeLong(createdDate);
	}

	public long commentId;
	public String ecode;
	public String author;
	public String description;
	public long createdDate;

}