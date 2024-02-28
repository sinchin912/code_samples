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

import com.trantorinc.synergy.skill.core.model.Certificate;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Certificate in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class CertificateCacheModel
	implements CacheModel<Certificate>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof CertificateCacheModel)) {
			return false;
		}

		CertificateCacheModel certificateCacheModel =
			(CertificateCacheModel)object;

		if (certificateId == certificateCacheModel.certificateId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, certificateId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(19);

		sb.append("{certificateId=");
		sb.append(certificateId);
		sb.append(", ecode=");
		sb.append(ecode);
		sb.append(", name=");
		sb.append(name);
		sb.append(", category=");
		sb.append(category);
		sb.append(", description=");
		sb.append(description);
		sb.append(", fileId=");
		sb.append(fileId);
		sb.append(", filename=");
		sb.append(filename);
		sb.append(", createdDate=");
		sb.append(createdDate);
		sb.append(", expiryDate=");
		sb.append(expiryDate);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Certificate toEntityModel() {
		CertificateImpl certificateImpl = new CertificateImpl();

		certificateImpl.setCertificateId(certificateId);

		if (ecode == null) {
			certificateImpl.setEcode("");
		}
		else {
			certificateImpl.setEcode(ecode);
		}

		if (name == null) {
			certificateImpl.setName("");
		}
		else {
			certificateImpl.setName(name);
		}

		if (category == null) {
			certificateImpl.setCategory("");
		}
		else {
			certificateImpl.setCategory(category);
		}

		if (description == null) {
			certificateImpl.setDescription("");
		}
		else {
			certificateImpl.setDescription(description);
		}

		if (fileId == null) {
			certificateImpl.setFileId("");
		}
		else {
			certificateImpl.setFileId(fileId);
		}

		if (filename == null) {
			certificateImpl.setFilename("");
		}
		else {
			certificateImpl.setFilename(filename);
		}

		if (createdDate == Long.MIN_VALUE) {
			certificateImpl.setCreatedDate(null);
		}
		else {
			certificateImpl.setCreatedDate(new Date(createdDate));
		}

		if (expiryDate == Long.MIN_VALUE) {
			certificateImpl.setExpiryDate(null);
		}
		else {
			certificateImpl.setExpiryDate(new Date(expiryDate));
		}

		certificateImpl.resetOriginalValues();

		return certificateImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		certificateId = objectInput.readLong();
		ecode = objectInput.readUTF();
		name = objectInput.readUTF();
		category = objectInput.readUTF();
		description = objectInput.readUTF();
		fileId = objectInput.readUTF();
		filename = objectInput.readUTF();
		createdDate = objectInput.readLong();
		expiryDate = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(certificateId);

		if (ecode == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(ecode);
		}

		if (name == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(name);
		}

		if (category == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(category);
		}

		if (description == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(description);
		}

		if (fileId == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(fileId);
		}

		if (filename == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(filename);
		}

		objectOutput.writeLong(createdDate);
		objectOutput.writeLong(expiryDate);
	}

	public long certificateId;
	public String ecode;
	public String name;
	public String category;
	public String description;
	public String fileId;
	public String filename;
	public long createdDate;
	public long expiryDate;

}