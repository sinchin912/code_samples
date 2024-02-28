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

package com.trantorinc.synergy.email.core.model.impl;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import com.trantorinc.synergy.email.core.model.EmailAttachment;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing EmailAttachment in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class EmailAttachmentCacheModel
	implements CacheModel<EmailAttachment>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof EmailAttachmentCacheModel)) {
			return false;
		}

		EmailAttachmentCacheModel emailAttachmentCacheModel =
			(EmailAttachmentCacheModel)object;

		if (emailAttachmentId == emailAttachmentCacheModel.emailAttachmentId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, emailAttachmentId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(9);

		sb.append("{emailAttachmentId=");
		sb.append(emailAttachmentId);
		sb.append(", emailId=");
		sb.append(emailId);
		sb.append(", attachmentName=");
		sb.append(attachmentName);
		sb.append(", attachmentFileId=");
		sb.append(attachmentFileId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public EmailAttachment toEntityModel() {
		EmailAttachmentImpl emailAttachmentImpl = new EmailAttachmentImpl();

		emailAttachmentImpl.setEmailAttachmentId(emailAttachmentId);
		emailAttachmentImpl.setEmailId(emailId);

		if (attachmentName == null) {
			emailAttachmentImpl.setAttachmentName("");
		}
		else {
			emailAttachmentImpl.setAttachmentName(attachmentName);
		}

		if (attachmentFileId == null) {
			emailAttachmentImpl.setAttachmentFileId("");
		}
		else {
			emailAttachmentImpl.setAttachmentFileId(attachmentFileId);
		}

		emailAttachmentImpl.resetOriginalValues();

		return emailAttachmentImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		emailAttachmentId = objectInput.readLong();

		emailId = objectInput.readLong();
		attachmentName = objectInput.readUTF();
		attachmentFileId = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(emailAttachmentId);

		objectOutput.writeLong(emailId);

		if (attachmentName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(attachmentName);
		}

		if (attachmentFileId == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(attachmentFileId);
		}
	}

	public long emailAttachmentId;
	public long emailId;
	public String attachmentName;
	public String attachmentFileId;

}