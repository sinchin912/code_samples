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

package com.trantorinc.synergy.lms.core.model;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link Drive}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Drive
 * @generated
 */
public class DriveWrapper
	extends BaseModelWrapper<Drive> implements Drive, ModelWrapper<Drive> {

	public DriveWrapper(Drive drive) {
		super(drive);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("driveId", getDriveId());
		attributes.put("folderName", getFolderName());
		attributes.put("folderId", getFolderId());
		attributes.put("filesCount", getFilesCount());
		attributes.put("updateDate", getUpdateDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long driveId = (Long)attributes.get("driveId");

		if (driveId != null) {
			setDriveId(driveId);
		}

		String folderName = (String)attributes.get("folderName");

		if (folderName != null) {
			setFolderName(folderName);
		}

		String folderId = (String)attributes.get("folderId");

		if (folderId != null) {
			setFolderId(folderId);
		}

		Integer filesCount = (Integer)attributes.get("filesCount");

		if (filesCount != null) {
			setFilesCount(filesCount);
		}

		Date updateDate = (Date)attributes.get("updateDate");

		if (updateDate != null) {
			setUpdateDate(updateDate);
		}
	}

	@Override
	public Drive cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the drive ID of this drive.
	 *
	 * @return the drive ID of this drive
	 */
	@Override
	public long getDriveId() {
		return model.getDriveId();
	}

	/**
	 * Returns the files count of this drive.
	 *
	 * @return the files count of this drive
	 */
	@Override
	public int getFilesCount() {
		return model.getFilesCount();
	}

	/**
	 * Returns the folder ID of this drive.
	 *
	 * @return the folder ID of this drive
	 */
	@Override
	public String getFolderId() {
		return model.getFolderId();
	}

	/**
	 * Returns the folder name of this drive.
	 *
	 * @return the folder name of this drive
	 */
	@Override
	public String getFolderName() {
		return model.getFolderName();
	}

	/**
	 * Returns the primary key of this drive.
	 *
	 * @return the primary key of this drive
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the update date of this drive.
	 *
	 * @return the update date of this drive
	 */
	@Override
	public Date getUpdateDate() {
		return model.getUpdateDate();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the drive ID of this drive.
	 *
	 * @param driveId the drive ID of this drive
	 */
	@Override
	public void setDriveId(long driveId) {
		model.setDriveId(driveId);
	}

	/**
	 * Sets the files count of this drive.
	 *
	 * @param filesCount the files count of this drive
	 */
	@Override
	public void setFilesCount(int filesCount) {
		model.setFilesCount(filesCount);
	}

	/**
	 * Sets the folder ID of this drive.
	 *
	 * @param folderId the folder ID of this drive
	 */
	@Override
	public void setFolderId(String folderId) {
		model.setFolderId(folderId);
	}

	/**
	 * Sets the folder name of this drive.
	 *
	 * @param folderName the folder name of this drive
	 */
	@Override
	public void setFolderName(String folderName) {
		model.setFolderName(folderName);
	}

	/**
	 * Sets the primary key of this drive.
	 *
	 * @param primaryKey the primary key of this drive
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the update date of this drive.
	 *
	 * @param updateDate the update date of this drive
	 */
	@Override
	public void setUpdateDate(Date updateDate) {
		model.setUpdateDate(updateDate);
	}

	@Override
	protected DriveWrapper wrap(Drive drive) {
		return new DriveWrapper(drive);
	}

}