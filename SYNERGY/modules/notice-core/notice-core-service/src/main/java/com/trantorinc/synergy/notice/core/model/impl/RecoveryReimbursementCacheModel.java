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

import com.trantorinc.synergy.notice.core.model.RecoveryReimbursement;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing RecoveryReimbursement in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class RecoveryReimbursementCacheModel
	implements CacheModel<RecoveryReimbursement>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof RecoveryReimbursementCacheModel)) {
			return false;
		}

		RecoveryReimbursementCacheModel recoveryReimbursementCacheModel =
			(RecoveryReimbursementCacheModel)object;

		if (id == recoveryReimbursementCacheModel.id) {
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
		StringBundler sb = new StringBundler(25);

		sb.append("{id=");
		sb.append(id);
		sb.append(", departmentFormId=");
		sb.append(departmentFormId);
		sb.append(", department=");
		sb.append(department);
		sb.append(", recoveryType=");
		sb.append(recoveryType);
		sb.append(", recoveryItem=");
		sb.append(recoveryItem);
		sb.append(", recoveryAmount=");
		sb.append(recoveryAmount);
		sb.append(", recoveryStatus=");
		sb.append(recoveryStatus);
		sb.append(", reimbursementItem=");
		sb.append(reimbursementItem);
		sb.append(", reimbursementAmount=");
		sb.append(reimbursementAmount);
		sb.append(", reimbursementStatus=");
		sb.append(reimbursementStatus);
		sb.append(", approved=");
		sb.append(approved);
		sb.append(", comment=");
		sb.append(comment);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public RecoveryReimbursement toEntityModel() {
		RecoveryReimbursementImpl recoveryReimbursementImpl =
			new RecoveryReimbursementImpl();

		recoveryReimbursementImpl.setId(id);
		recoveryReimbursementImpl.setDepartmentFormId(departmentFormId);
		recoveryReimbursementImpl.setDepartment(department);
		recoveryReimbursementImpl.setRecoveryType(recoveryType);

		if (recoveryItem == null) {
			recoveryReimbursementImpl.setRecoveryItem("");
		}
		else {
			recoveryReimbursementImpl.setRecoveryItem(recoveryItem);
		}

		if (recoveryAmount == null) {
			recoveryReimbursementImpl.setRecoveryAmount("");
		}
		else {
			recoveryReimbursementImpl.setRecoveryAmount(recoveryAmount);
		}

		recoveryReimbursementImpl.setRecoveryStatus(recoveryStatus);

		if (reimbursementItem == null) {
			recoveryReimbursementImpl.setReimbursementItem("");
		}
		else {
			recoveryReimbursementImpl.setReimbursementItem(reimbursementItem);
		}

		if (reimbursementAmount == null) {
			recoveryReimbursementImpl.setReimbursementAmount("");
		}
		else {
			recoveryReimbursementImpl.setReimbursementAmount(
				reimbursementAmount);
		}

		recoveryReimbursementImpl.setReimbursementStatus(reimbursementStatus);
		recoveryReimbursementImpl.setApproved(approved);

		if (comment == null) {
			recoveryReimbursementImpl.setComment("");
		}
		else {
			recoveryReimbursementImpl.setComment(comment);
		}

		recoveryReimbursementImpl.resetOriginalValues();

		return recoveryReimbursementImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		id = objectInput.readLong();

		departmentFormId = objectInput.readLong();

		department = objectInput.readInt();

		recoveryType = objectInput.readBoolean();
		recoveryItem = objectInput.readUTF();
		recoveryAmount = objectInput.readUTF();

		recoveryStatus = objectInput.readInt();
		reimbursementItem = objectInput.readUTF();
		reimbursementAmount = objectInput.readUTF();

		reimbursementStatus = objectInput.readInt();

		approved = objectInput.readBoolean();
		comment = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(id);

		objectOutput.writeLong(departmentFormId);

		objectOutput.writeInt(department);

		objectOutput.writeBoolean(recoveryType);

		if (recoveryItem == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(recoveryItem);
		}

		if (recoveryAmount == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(recoveryAmount);
		}

		objectOutput.writeInt(recoveryStatus);

		if (reimbursementItem == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(reimbursementItem);
		}

		if (reimbursementAmount == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(reimbursementAmount);
		}

		objectOutput.writeInt(reimbursementStatus);

		objectOutput.writeBoolean(approved);

		if (comment == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(comment);
		}
	}

	public long id;
	public long departmentFormId;
	public int department;
	public boolean recoveryType;
	public String recoveryItem;
	public String recoveryAmount;
	public int recoveryStatus;
	public String reimbursementItem;
	public String reimbursementAmount;
	public int reimbursementStatus;
	public boolean approved;
	public String comment;

}