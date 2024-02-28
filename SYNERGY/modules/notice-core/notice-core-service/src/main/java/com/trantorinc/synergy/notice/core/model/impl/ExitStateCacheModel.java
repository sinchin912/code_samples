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

import com.trantorinc.synergy.notice.core.model.ExitState;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing ExitState in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ExitStateCacheModel
	implements CacheModel<ExitState>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof ExitStateCacheModel)) {
			return false;
		}

		ExitStateCacheModel exitStateCacheModel = (ExitStateCacheModel)object;

		if (exitStateId == exitStateCacheModel.exitStateId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, exitStateId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(9);

		sb.append("{exitStateId=");
		sb.append(exitStateId);
		sb.append(", exitStateKey=");
		sb.append(exitStateKey);
		sb.append(", exitStateDescription=");
		sb.append(exitStateDescription);
		sb.append(", exitStateDisplay=");
		sb.append(exitStateDisplay);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ExitState toEntityModel() {
		ExitStateImpl exitStateImpl = new ExitStateImpl();

		exitStateImpl.setExitStateId(exitStateId);
		exitStateImpl.setExitStateKey(exitStateKey);

		if (exitStateDescription == null) {
			exitStateImpl.setExitStateDescription("");
		}
		else {
			exitStateImpl.setExitStateDescription(exitStateDescription);
		}

		if (exitStateDisplay == null) {
			exitStateImpl.setExitStateDisplay("");
		}
		else {
			exitStateImpl.setExitStateDisplay(exitStateDisplay);
		}

		exitStateImpl.resetOriginalValues();

		return exitStateImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		exitStateId = objectInput.readLong();

		exitStateKey = objectInput.readInt();
		exitStateDescription = objectInput.readUTF();
		exitStateDisplay = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(exitStateId);

		objectOutput.writeInt(exitStateKey);

		if (exitStateDescription == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(exitStateDescription);
		}

		if (exitStateDisplay == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(exitStateDisplay);
		}
	}

	public long exitStateId;
	public int exitStateKey;
	public String exitStateDescription;
	public String exitStateDisplay;

}