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

import com.trantorinc.synergy.game.core.model.GameTimeline;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing GameTimeline in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class GameTimelineCacheModel
	implements CacheModel<GameTimeline>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof GameTimelineCacheModel)) {
			return false;
		}

		GameTimelineCacheModel gameTimelineCacheModel =
			(GameTimelineCacheModel)object;

		if (name.equals(gameTimelineCacheModel.name)) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, name);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(11);

		sb.append("{name=");
		sb.append(name);
		sb.append(", openDate=");
		sb.append(openDate);
		sb.append(", freezeDate=");
		sb.append(freezeDate);
		sb.append(", actionDate=");
		sb.append(actionDate);
		sb.append(", closeDate=");
		sb.append(closeDate);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public GameTimeline toEntityModel() {
		GameTimelineImpl gameTimelineImpl = new GameTimelineImpl();

		if (name == null) {
			gameTimelineImpl.setName("");
		}
		else {
			gameTimelineImpl.setName(name);
		}

		if (openDate == Long.MIN_VALUE) {
			gameTimelineImpl.setOpenDate(null);
		}
		else {
			gameTimelineImpl.setOpenDate(new Date(openDate));
		}

		if (freezeDate == Long.MIN_VALUE) {
			gameTimelineImpl.setFreezeDate(null);
		}
		else {
			gameTimelineImpl.setFreezeDate(new Date(freezeDate));
		}

		if (actionDate == Long.MIN_VALUE) {
			gameTimelineImpl.setActionDate(null);
		}
		else {
			gameTimelineImpl.setActionDate(new Date(actionDate));
		}

		if (closeDate == Long.MIN_VALUE) {
			gameTimelineImpl.setCloseDate(null);
		}
		else {
			gameTimelineImpl.setCloseDate(new Date(closeDate));
		}

		gameTimelineImpl.resetOriginalValues();

		return gameTimelineImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		name = objectInput.readUTF();
		openDate = objectInput.readLong();
		freezeDate = objectInput.readLong();
		actionDate = objectInput.readLong();
		closeDate = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		if (name == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(name);
		}

		objectOutput.writeLong(openDate);
		objectOutput.writeLong(freezeDate);
		objectOutput.writeLong(actionDate);
		objectOutput.writeLong(closeDate);
	}

	public String name;
	public long openDate;
	public long freezeDate;
	public long actionDate;
	public long closeDate;

}