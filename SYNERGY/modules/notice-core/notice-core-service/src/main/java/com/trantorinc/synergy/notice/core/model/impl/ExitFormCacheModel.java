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

import com.trantorinc.synergy.notice.core.model.ExitForm;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing ExitForm in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ExitFormCacheModel
	implements CacheModel<ExitForm>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof ExitFormCacheModel)) {
			return false;
		}

		ExitFormCacheModel exitFormCacheModel = (ExitFormCacheModel)object;

		if (id == exitFormCacheModel.id) {
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
		sb.append(", resignationId=");
		sb.append(resignationId);
		sb.append(", managerFormStatus=");
		sb.append(managerFormStatus);
		sb.append(", hrFormStatus=");
		sb.append(hrFormStatus);
		sb.append(", itFormStatus=");
		sb.append(itFormStatus);
		sb.append(", adminFormStatus=");
		sb.append(adminFormStatus);
		sb.append(", financeFormStatus=");
		sb.append(financeFormStatus);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ExitForm toEntityModel() {
		ExitFormImpl exitFormImpl = new ExitFormImpl();

		exitFormImpl.setId(id);
		exitFormImpl.setResignationId(resignationId);
		exitFormImpl.setManagerFormStatus(managerFormStatus);
		exitFormImpl.setHrFormStatus(hrFormStatus);
		exitFormImpl.setItFormStatus(itFormStatus);
		exitFormImpl.setAdminFormStatus(adminFormStatus);
		exitFormImpl.setFinanceFormStatus(financeFormStatus);

		exitFormImpl.resetOriginalValues();

		return exitFormImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		id = objectInput.readLong();

		resignationId = objectInput.readLong();

		managerFormStatus = objectInput.readBoolean();

		hrFormStatus = objectInput.readBoolean();

		itFormStatus = objectInput.readBoolean();

		adminFormStatus = objectInput.readBoolean();

		financeFormStatus = objectInput.readBoolean();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(id);

		objectOutput.writeLong(resignationId);

		objectOutput.writeBoolean(managerFormStatus);

		objectOutput.writeBoolean(hrFormStatus);

		objectOutput.writeBoolean(itFormStatus);

		objectOutput.writeBoolean(adminFormStatus);

		objectOutput.writeBoolean(financeFormStatus);
	}

	public long id;
	public long resignationId;
	public boolean managerFormStatus;
	public boolean hrFormStatus;
	public boolean itFormStatus;
	public boolean adminFormStatus;
	public boolean financeFormStatus;

}