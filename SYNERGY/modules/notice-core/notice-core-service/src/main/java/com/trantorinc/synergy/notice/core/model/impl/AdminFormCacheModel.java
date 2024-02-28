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

package com.trantorinc.synergy.notice.core.model.impl;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import com.trantorinc.synergy.notice.core.model.AdminForm;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing AdminForm in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class AdminFormCacheModel
	implements CacheModel<AdminForm>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof AdminFormCacheModel)) {
			return false;
		}

		AdminFormCacheModel adminFormCacheModel = (AdminFormCacheModel)object;

		if (id == adminFormCacheModel.id) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, id);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(71);

		sb.append("{id=");
		sb.append(id);
		sb.append(", exitId=");
		sb.append(exitId);
		sb.append(", stationaryRecoveryAmt=");
		sb.append(stationaryRecoveryAmt);
		sb.append(", stationaryRecoveryAmtStatus=");
		sb.append(stationaryRecoveryAmtStatus);
		sb.append(", stationaryRecoveryApproved=");
		sb.append(stationaryRecoveryApproved);
		sb.append(", stationaryRecoveryComment=");
		sb.append(stationaryRecoveryComment);
		sb.append(", keysRecoveryAmt=");
		sb.append(keysRecoveryAmt);
		sb.append(", keysRecoveryAmtStatus=");
		sb.append(keysRecoveryAmtStatus);
		sb.append(", keyRecoveryApproved=");
		sb.append(keyRecoveryApproved);
		sb.append(", keyRecoveryComment=");
		sb.append(keyRecoveryComment);
		sb.append(", libraryRecoveryAmt=");
		sb.append(libraryRecoveryAmt);
		sb.append(", libraryRecoveryAmtStatus=");
		sb.append(libraryRecoveryAmtStatus);
		sb.append(", libraryRecoveryApproved=");
		sb.append(libraryRecoveryApproved);
		sb.append(", libraryRecoveryComment=");
		sb.append(libraryRecoveryComment);
		sb.append(", sportsRecoveryAmt=");
		sb.append(sportsRecoveryAmt);
		sb.append(", sportsRecoveryAmtStatus=");
		sb.append(sportsRecoveryAmtStatus);
		sb.append(", sportsRecoveryApproved=");
		sb.append(sportsRecoveryApproved);
		sb.append(", sportsRecoveryComment=");
		sb.append(sportsRecoveryComment);
		sb.append(", infraRecoveryAmt=");
		sb.append(infraRecoveryAmt);
		sb.append(", infraRecoveryAmtStatus=");
		sb.append(infraRecoveryAmtStatus);
		sb.append(", infraRecoveryApproved=");
		sb.append(infraRecoveryApproved);
		sb.append(", infraRecoveryComment=");
		sb.append(infraRecoveryComment);
		sb.append(", lunchRecoveryAmt=");
		sb.append(lunchRecoveryAmt);
		sb.append(", lunchRecoveryAmtStatus=");
		sb.append(lunchRecoveryAmtStatus);
		sb.append(", lunchRecoveryApproved=");
		sb.append(lunchRecoveryApproved);
		sb.append(", lunchRecoveryComment=");
		sb.append(lunchRecoveryComment);
		sb.append(", accessCardRecoveryAmt=");
		sb.append(accessCardRecoveryAmt);
		sb.append(", accessCardRecoveryAmtStatus=");
		sb.append(accessCardRecoveryAmtStatus);
		sb.append(", accessCardRecoveryApproved=");
		sb.append(accessCardRecoveryApproved);
		sb.append(", accessCardRecoveryComment=");
		sb.append(accessCardRecoveryComment);
		sb.append(", identityCardRecoveryAmt=");
		sb.append(identityCardRecoveryAmt);
		sb.append(", identityCardRecoveryStatus=");
		sb.append(identityCardRecoveryStatus);
		sb.append(", identityCardRecoveryApproved=");
		sb.append(identityCardRecoveryApproved);
		sb.append(", identityCardRecoveryComment=");
		sb.append(identityCardRecoveryComment);
		sb.append(", updatedDate=");
		sb.append(updatedDate);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public AdminForm toEntityModel() {
		AdminFormImpl adminFormImpl = new AdminFormImpl();

		adminFormImpl.setId(id);
		adminFormImpl.setExitId(exitId);

		if (stationaryRecoveryAmt == null) {
			adminFormImpl.setStationaryRecoveryAmt("");
		}
		else {
			adminFormImpl.setStationaryRecoveryAmt(stationaryRecoveryAmt);
		}

		adminFormImpl.setStationaryRecoveryAmtStatus(
			stationaryRecoveryAmtStatus);
		adminFormImpl.setStationaryRecoveryApproved(stationaryRecoveryApproved);

		if (stationaryRecoveryComment == null) {
			adminFormImpl.setStationaryRecoveryComment("");
		}
		else {
			adminFormImpl.setStationaryRecoveryComment(
				stationaryRecoveryComment);
		}

		if (keysRecoveryAmt == null) {
			adminFormImpl.setKeysRecoveryAmt("");
		}
		else {
			adminFormImpl.setKeysRecoveryAmt(keysRecoveryAmt);
		}

		adminFormImpl.setKeysRecoveryAmtStatus(keysRecoveryAmtStatus);
		adminFormImpl.setKeyRecoveryApproved(keyRecoveryApproved);

		if (keyRecoveryComment == null) {
			adminFormImpl.setKeyRecoveryComment("");
		}
		else {
			adminFormImpl.setKeyRecoveryComment(keyRecoveryComment);
		}

		if (libraryRecoveryAmt == null) {
			adminFormImpl.setLibraryRecoveryAmt("");
		}
		else {
			adminFormImpl.setLibraryRecoveryAmt(libraryRecoveryAmt);
		}

		adminFormImpl.setLibraryRecoveryAmtStatus(libraryRecoveryAmtStatus);
		adminFormImpl.setLibraryRecoveryApproved(libraryRecoveryApproved);

		if (libraryRecoveryComment == null) {
			adminFormImpl.setLibraryRecoveryComment("");
		}
		else {
			adminFormImpl.setLibraryRecoveryComment(libraryRecoveryComment);
		}

		if (sportsRecoveryAmt == null) {
			adminFormImpl.setSportsRecoveryAmt("");
		}
		else {
			adminFormImpl.setSportsRecoveryAmt(sportsRecoveryAmt);
		}

		adminFormImpl.setSportsRecoveryAmtStatus(sportsRecoveryAmtStatus);
		adminFormImpl.setSportsRecoveryApproved(sportsRecoveryApproved);

		if (sportsRecoveryComment == null) {
			adminFormImpl.setSportsRecoveryComment("");
		}
		else {
			adminFormImpl.setSportsRecoveryComment(sportsRecoveryComment);
		}

		if (infraRecoveryAmt == null) {
			adminFormImpl.setInfraRecoveryAmt("");
		}
		else {
			adminFormImpl.setInfraRecoveryAmt(infraRecoveryAmt);
		}

		adminFormImpl.setInfraRecoveryAmtStatus(infraRecoveryAmtStatus);
		adminFormImpl.setInfraRecoveryApproved(infraRecoveryApproved);

		if (infraRecoveryComment == null) {
			adminFormImpl.setInfraRecoveryComment("");
		}
		else {
			adminFormImpl.setInfraRecoveryComment(infraRecoveryComment);
		}

		if (lunchRecoveryAmt == null) {
			adminFormImpl.setLunchRecoveryAmt("");
		}
		else {
			adminFormImpl.setLunchRecoveryAmt(lunchRecoveryAmt);
		}

		adminFormImpl.setLunchRecoveryAmtStatus(lunchRecoveryAmtStatus);
		adminFormImpl.setLunchRecoveryApproved(lunchRecoveryApproved);

		if (lunchRecoveryComment == null) {
			adminFormImpl.setLunchRecoveryComment("");
		}
		else {
			adminFormImpl.setLunchRecoveryComment(lunchRecoveryComment);
		}

		if (accessCardRecoveryAmt == null) {
			adminFormImpl.setAccessCardRecoveryAmt("");
		}
		else {
			adminFormImpl.setAccessCardRecoveryAmt(accessCardRecoveryAmt);
		}

		adminFormImpl.setAccessCardRecoveryAmtStatus(
			accessCardRecoveryAmtStatus);
		adminFormImpl.setAccessCardRecoveryApproved(accessCardRecoveryApproved);

		if (accessCardRecoveryComment == null) {
			adminFormImpl.setAccessCardRecoveryComment("");
		}
		else {
			adminFormImpl.setAccessCardRecoveryComment(
				accessCardRecoveryComment);
		}

		if (identityCardRecoveryAmt == null) {
			adminFormImpl.setIdentityCardRecoveryAmt("");
		}
		else {
			adminFormImpl.setIdentityCardRecoveryAmt(identityCardRecoveryAmt);
		}

		adminFormImpl.setIdentityCardRecoveryStatus(identityCardRecoveryStatus);
		adminFormImpl.setIdentityCardRecoveryApproved(
			identityCardRecoveryApproved);

		if (identityCardRecoveryComment == null) {
			adminFormImpl.setIdentityCardRecoveryComment("");
		}
		else {
			adminFormImpl.setIdentityCardRecoveryComment(
				identityCardRecoveryComment);
		}

		if (updatedDate == Long.MIN_VALUE) {
			adminFormImpl.setUpdatedDate(null);
		}
		else {
			adminFormImpl.setUpdatedDate(new Date(updatedDate));
		}

		adminFormImpl.resetOriginalValues();

		return adminFormImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		id = objectInput.readLong();

		exitId = objectInput.readLong();
		stationaryRecoveryAmt = objectInput.readUTF();

		stationaryRecoveryAmtStatus = objectInput.readInt();

		stationaryRecoveryApproved = objectInput.readBoolean();
		stationaryRecoveryComment = objectInput.readUTF();
		keysRecoveryAmt = objectInput.readUTF();

		keysRecoveryAmtStatus = objectInput.readInt();

		keyRecoveryApproved = objectInput.readBoolean();
		keyRecoveryComment = objectInput.readUTF();
		libraryRecoveryAmt = objectInput.readUTF();

		libraryRecoveryAmtStatus = objectInput.readInt();

		libraryRecoveryApproved = objectInput.readBoolean();
		libraryRecoveryComment = objectInput.readUTF();
		sportsRecoveryAmt = objectInput.readUTF();

		sportsRecoveryAmtStatus = objectInput.readInt();

		sportsRecoveryApproved = objectInput.readBoolean();
		sportsRecoveryComment = objectInput.readUTF();
		infraRecoveryAmt = objectInput.readUTF();

		infraRecoveryAmtStatus = objectInput.readInt();

		infraRecoveryApproved = objectInput.readBoolean();
		infraRecoveryComment = objectInput.readUTF();
		lunchRecoveryAmt = objectInput.readUTF();

		lunchRecoveryAmtStatus = objectInput.readInt();

		lunchRecoveryApproved = objectInput.readBoolean();
		lunchRecoveryComment = objectInput.readUTF();
		accessCardRecoveryAmt = objectInput.readUTF();

		accessCardRecoveryAmtStatus = objectInput.readInt();

		accessCardRecoveryApproved = objectInput.readBoolean();
		accessCardRecoveryComment = objectInput.readUTF();
		identityCardRecoveryAmt = objectInput.readUTF();

		identityCardRecoveryStatus = objectInput.readInt();

		identityCardRecoveryApproved = objectInput.readBoolean();
		identityCardRecoveryComment = objectInput.readUTF();
		updatedDate = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(id);

		objectOutput.writeLong(exitId);

		if (stationaryRecoveryAmt == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(stationaryRecoveryAmt);
		}

		objectOutput.writeInt(stationaryRecoveryAmtStatus);

		objectOutput.writeBoolean(stationaryRecoveryApproved);

		if (stationaryRecoveryComment == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(stationaryRecoveryComment);
		}

		if (keysRecoveryAmt == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(keysRecoveryAmt);
		}

		objectOutput.writeInt(keysRecoveryAmtStatus);

		objectOutput.writeBoolean(keyRecoveryApproved);

		if (keyRecoveryComment == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(keyRecoveryComment);
		}

		if (libraryRecoveryAmt == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(libraryRecoveryAmt);
		}

		objectOutput.writeInt(libraryRecoveryAmtStatus);

		objectOutput.writeBoolean(libraryRecoveryApproved);

		if (libraryRecoveryComment == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(libraryRecoveryComment);
		}

		if (sportsRecoveryAmt == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(sportsRecoveryAmt);
		}

		objectOutput.writeInt(sportsRecoveryAmtStatus);

		objectOutput.writeBoolean(sportsRecoveryApproved);

		if (sportsRecoveryComment == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(sportsRecoveryComment);
		}

		if (infraRecoveryAmt == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(infraRecoveryAmt);
		}

		objectOutput.writeInt(infraRecoveryAmtStatus);

		objectOutput.writeBoolean(infraRecoveryApproved);

		if (infraRecoveryComment == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(infraRecoveryComment);
		}

		if (lunchRecoveryAmt == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(lunchRecoveryAmt);
		}

		objectOutput.writeInt(lunchRecoveryAmtStatus);

		objectOutput.writeBoolean(lunchRecoveryApproved);

		if (lunchRecoveryComment == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(lunchRecoveryComment);
		}

		if (accessCardRecoveryAmt == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(accessCardRecoveryAmt);
		}

		objectOutput.writeInt(accessCardRecoveryAmtStatus);

		objectOutput.writeBoolean(accessCardRecoveryApproved);

		if (accessCardRecoveryComment == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(accessCardRecoveryComment);
		}

		if (identityCardRecoveryAmt == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(identityCardRecoveryAmt);
		}

		objectOutput.writeInt(identityCardRecoveryStatus);

		objectOutput.writeBoolean(identityCardRecoveryApproved);

		if (identityCardRecoveryComment == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(identityCardRecoveryComment);
		}

		objectOutput.writeLong(updatedDate);
	}

	public long id;
	public long exitId;
	public String stationaryRecoveryAmt;
	public int stationaryRecoveryAmtStatus;
	public boolean stationaryRecoveryApproved;
	public String stationaryRecoveryComment;
	public String keysRecoveryAmt;
	public int keysRecoveryAmtStatus;
	public boolean keyRecoveryApproved;
	public String keyRecoveryComment;
	public String libraryRecoveryAmt;
	public int libraryRecoveryAmtStatus;
	public boolean libraryRecoveryApproved;
	public String libraryRecoveryComment;
	public String sportsRecoveryAmt;
	public int sportsRecoveryAmtStatus;
	public boolean sportsRecoveryApproved;
	public String sportsRecoveryComment;
	public String infraRecoveryAmt;
	public int infraRecoveryAmtStatus;
	public boolean infraRecoveryApproved;
	public String infraRecoveryComment;
	public String lunchRecoveryAmt;
	public int lunchRecoveryAmtStatus;
	public boolean lunchRecoveryApproved;
	public String lunchRecoveryComment;
	public String accessCardRecoveryAmt;
	public int accessCardRecoveryAmtStatus;
	public boolean accessCardRecoveryApproved;
	public String accessCardRecoveryComment;
	public String identityCardRecoveryAmt;
	public int identityCardRecoveryStatus;
	public boolean identityCardRecoveryApproved;
	public String identityCardRecoveryComment;
	public long updatedDate;

}