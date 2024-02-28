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

package com.trantorinc.synergy.game.core.model.impl;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import com.trantorinc.synergy.game.core.model.Prize;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing Prize in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class PrizeCacheModel implements CacheModel<Prize>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof PrizeCacheModel)) {
			return false;
		}

		PrizeCacheModel prizeCacheModel = (PrizeCacheModel)object;

		if (prizeId == prizeCacheModel.prizeId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, prizeId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(17);

		sb.append("{prizeId=");
		sb.append(prizeId);
		sb.append(", year=");
		sb.append(year);
		sb.append(", sequence=");
		sb.append(sequence);
		sb.append(", description=");
		sb.append(description);
		sb.append(", winner=");
		sb.append(winner);
		sb.append(", ticketId=");
		sb.append(ticketId);
		sb.append(", surprise=");
		sb.append(surprise);
		sb.append(", fileId=");
		sb.append(fileId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Prize toEntityModel() {
		PrizeImpl prizeImpl = new PrizeImpl();

		prizeImpl.setPrizeId(prizeId);
		prizeImpl.setYear(year);
		prizeImpl.setSequence(sequence);

		if (description == null) {
			prizeImpl.setDescription("");
		}
		else {
			prizeImpl.setDescription(description);
		}

		if (winner == null) {
			prizeImpl.setWinner("");
		}
		else {
			prizeImpl.setWinner(winner);
		}

		prizeImpl.setTicketId(ticketId);
		prizeImpl.setSurprise(surprise);

		if (fileId == null) {
			prizeImpl.setFileId("");
		}
		else {
			prizeImpl.setFileId(fileId);
		}

		prizeImpl.resetOriginalValues();

		return prizeImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		prizeId = objectInput.readLong();

		year = objectInput.readInt();

		sequence = objectInput.readInt();
		description = objectInput.readUTF();
		winner = objectInput.readUTF();

		ticketId = objectInput.readLong();

		surprise = objectInput.readBoolean();
		fileId = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(prizeId);

		objectOutput.writeInt(year);

		objectOutput.writeInt(sequence);

		if (description == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(description);
		}

		if (winner == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(winner);
		}

		objectOutput.writeLong(ticketId);

		objectOutput.writeBoolean(surprise);

		if (fileId == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(fileId);
		}
	}

	public long prizeId;
	public int year;
	public int sequence;
	public String description;
	public String winner;
	public long ticketId;
	public boolean surprise;
	public String fileId;

}