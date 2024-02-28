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

import com.trantorinc.synergy.notice.core.model.ManagerForm;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing ManagerForm in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ManagerFormCacheModel
	implements CacheModel<ManagerForm>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof ManagerFormCacheModel)) {
			return false;
		}

		ManagerFormCacheModel managerFormCacheModel =
			(ManagerFormCacheModel)object;

		if (id == managerFormCacheModel.id) {
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
		StringBundler sb = new StringBundler(15);

		sb.append("{id=");
		sb.append(id);
		sb.append(", exitId=");
		sb.append(exitId);
		sb.append(", ticketNo=");
		sb.append(ticketNo);
		sb.append(", ticketNoRemark=");
		sb.append(ticketNoRemark);
		sb.append(", separationDocument=");
		sb.append(separationDocument);
		sb.append(", separationDocumentRemark=");
		sb.append(separationDocumentRemark);
		sb.append(", updatedDate=");
		sb.append(updatedDate);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ManagerForm toEntityModel() {
		ManagerFormImpl managerFormImpl = new ManagerFormImpl();

		managerFormImpl.setId(id);
		managerFormImpl.setExitId(exitId);
		managerFormImpl.setTicketNo(ticketNo);

		if (ticketNoRemark == null) {
			managerFormImpl.setTicketNoRemark("");
		}
		else {
			managerFormImpl.setTicketNoRemark(ticketNoRemark);
		}

		managerFormImpl.setSeparationDocument(separationDocument);

		if (separationDocumentRemark == null) {
			managerFormImpl.setSeparationDocumentRemark("");
		}
		else {
			managerFormImpl.setSeparationDocumentRemark(
				separationDocumentRemark);
		}

		if (updatedDate == Long.MIN_VALUE) {
			managerFormImpl.setUpdatedDate(null);
		}
		else {
			managerFormImpl.setUpdatedDate(new Date(updatedDate));
		}

		managerFormImpl.resetOriginalValues();

		return managerFormImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		id = objectInput.readLong();

		exitId = objectInput.readLong();

		ticketNo = objectInput.readInt();
		ticketNoRemark = objectInput.readUTF();

		separationDocument = objectInput.readInt();
		separationDocumentRemark = objectInput.readUTF();
		updatedDate = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(id);

		objectOutput.writeLong(exitId);

		objectOutput.writeInt(ticketNo);

		if (ticketNoRemark == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(ticketNoRemark);
		}

		objectOutput.writeInt(separationDocument);

		if (separationDocumentRemark == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(separationDocumentRemark);
		}

		objectOutput.writeLong(updatedDate);
	}

	public long id;
	public long exitId;
	public int ticketNo;
	public String ticketNoRemark;
	public int separationDocument;
	public String separationDocumentRemark;
	public long updatedDate;

}