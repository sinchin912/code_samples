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

package com.trantorinc.synergy.lms.core.model.impl;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import com.trantorinc.synergy.lms.core.model.Drive;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Drive in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class DriveCacheModel implements CacheModel<Drive>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof DriveCacheModel)) {
			return false;
		}

		DriveCacheModel driveCacheModel = (DriveCacheModel)object;

		if (driveId == driveCacheModel.driveId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, driveId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(11);

		sb.append("{driveId=");
		sb.append(driveId);
		sb.append(", folderName=");
		sb.append(folderName);
		sb.append(", folderId=");
		sb.append(folderId);
		sb.append(", filesCount=");
		sb.append(filesCount);
		sb.append(", updateDate=");
		sb.append(updateDate);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Drive toEntityModel() {
		DriveImpl driveImpl = new DriveImpl();

		driveImpl.setDriveId(driveId);

		if (folderName == null) {
			driveImpl.setFolderName("");
		}
		else {
			driveImpl.setFolderName(folderName);
		}

		if (folderId == null) {
			driveImpl.setFolderId("");
		}
		else {
			driveImpl.setFolderId(folderId);
		}

		driveImpl.setFilesCount(filesCount);

		if (updateDate == Long.MIN_VALUE) {
			driveImpl.setUpdateDate(null);
		}
		else {
			driveImpl.setUpdateDate(new Date(updateDate));
		}

		driveImpl.resetOriginalValues();

		return driveImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		driveId = objectInput.readLong();
		folderName = objectInput.readUTF();
		folderId = objectInput.readUTF();

		filesCount = objectInput.readInt();
		updateDate = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(driveId);

		if (folderName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(folderName);
		}

		if (folderId == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(folderId);
		}

		objectOutput.writeInt(filesCount);
		objectOutput.writeLong(updateDate);
	}

	public long driveId;
	public String folderName;
	public String folderId;
	public int filesCount;
	public long updateDate;

}