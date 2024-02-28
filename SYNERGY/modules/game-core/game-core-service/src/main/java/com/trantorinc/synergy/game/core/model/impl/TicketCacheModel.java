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

import com.trantorinc.synergy.game.core.model.Ticket;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Ticket in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class TicketCacheModel implements CacheModel<Ticket>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof TicketCacheModel)) {
			return false;
		}

		TicketCacheModel ticketCacheModel = (TicketCacheModel)object;

		if (ticketId == ticketCacheModel.ticketId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, ticketId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(13);

		sb.append("{ticketId=");
		sb.append(ticketId);
		sb.append(", year=");
		sb.append(year);
		sb.append(", ecode=");
		sb.append(ecode);
		sb.append(", ticketNumber=");
		sb.append(ticketNumber);
		sb.append(", createDatetime=");
		sb.append(createDatetime);
		sb.append(", draw=");
		sb.append(draw);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Ticket toEntityModel() {
		TicketImpl ticketImpl = new TicketImpl();

		ticketImpl.setTicketId(ticketId);
		ticketImpl.setYear(year);

		if (ecode == null) {
			ticketImpl.setEcode("");
		}
		else {
			ticketImpl.setEcode(ecode);
		}

		if (ticketNumber == null) {
			ticketImpl.setTicketNumber("");
		}
		else {
			ticketImpl.setTicketNumber(ticketNumber);
		}

		if (createDatetime == Long.MIN_VALUE) {
			ticketImpl.setCreateDatetime(null);
		}
		else {
			ticketImpl.setCreateDatetime(new Date(createDatetime));
		}

		ticketImpl.setDraw(draw);

		ticketImpl.resetOriginalValues();

		return ticketImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		ticketId = objectInput.readLong();

		year = objectInput.readInt();
		ecode = objectInput.readUTF();
		ticketNumber = objectInput.readUTF();
		createDatetime = objectInput.readLong();

		draw = objectInput.readBoolean();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(ticketId);

		objectOutput.writeInt(year);

		if (ecode == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(ecode);
		}

		if (ticketNumber == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(ticketNumber);
		}

		objectOutput.writeLong(createDatetime);

		objectOutput.writeBoolean(draw);
	}

	public long ticketId;
	public int year;
	public String ecode;
	public String ticketNumber;
	public long createDatetime;
	public boolean draw;

}