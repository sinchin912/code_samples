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

import com.trantorinc.synergy.email.core.model.Email;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Email in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class EmailCacheModel implements CacheModel<Email>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof EmailCacheModel)) {
			return false;
		}

		EmailCacheModel emailCacheModel = (EmailCacheModel)object;

		if (emailId == emailCacheModel.emailId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, emailId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(21);

		sb.append("{emailId=");
		sb.append(emailId);
		sb.append(", scheduled=");
		sb.append(scheduled);
		sb.append(", module=");
		sb.append(module);
		sb.append(", toAddress=");
		sb.append(toAddress);
		sb.append(", ccAddress=");
		sb.append(ccAddress);
		sb.append(", bccAddress=");
		sb.append(bccAddress);
		sb.append(", subject=");
		sb.append(subject);
		sb.append(", body=");
		sb.append(body);
		sb.append(", createdDate=");
		sb.append(createdDate);
		sb.append(", sent=");
		sb.append(sent);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Email toEntityModel() {
		EmailImpl emailImpl = new EmailImpl();

		emailImpl.setEmailId(emailId);
		emailImpl.setScheduled(scheduled);

		if (module == null) {
			emailImpl.setModule("");
		}
		else {
			emailImpl.setModule(module);
		}

		if (toAddress == null) {
			emailImpl.setToAddress("");
		}
		else {
			emailImpl.setToAddress(toAddress);
		}

		if (ccAddress == null) {
			emailImpl.setCcAddress("");
		}
		else {
			emailImpl.setCcAddress(ccAddress);
		}

		if (bccAddress == null) {
			emailImpl.setBccAddress("");
		}
		else {
			emailImpl.setBccAddress(bccAddress);
		}

		if (subject == null) {
			emailImpl.setSubject("");
		}
		else {
			emailImpl.setSubject(subject);
		}

		if (body == null) {
			emailImpl.setBody("");
		}
		else {
			emailImpl.setBody(body);
		}

		if (createdDate == Long.MIN_VALUE) {
			emailImpl.setCreatedDate(null);
		}
		else {
			emailImpl.setCreatedDate(new Date(createdDate));
		}

		emailImpl.setSent(sent);

		emailImpl.resetOriginalValues();

		return emailImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		emailId = objectInput.readLong();

		scheduled = objectInput.readBoolean();
		module = objectInput.readUTF();
		toAddress = objectInput.readUTF();
		ccAddress = objectInput.readUTF();
		bccAddress = objectInput.readUTF();
		subject = objectInput.readUTF();
		body = objectInput.readUTF();
		createdDate = objectInput.readLong();

		sent = objectInput.readBoolean();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(emailId);

		objectOutput.writeBoolean(scheduled);

		if (module == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(module);
		}

		if (toAddress == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(toAddress);
		}

		if (ccAddress == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(ccAddress);
		}

		if (bccAddress == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(bccAddress);
		}

		if (subject == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(subject);
		}

		if (body == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(body);
		}

		objectOutput.writeLong(createdDate);

		objectOutput.writeBoolean(sent);
	}

	public long emailId;
	public boolean scheduled;
	public String module;
	public String toAddress;
	public String ccAddress;
	public String bccAddress;
	public String subject;
	public String body;
	public long createdDate;
	public boolean sent;

}