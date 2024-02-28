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

import com.trantorinc.synergy.game.core.model.Santa;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Santa in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class SantaCacheModel implements CacheModel<Santa>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof SantaCacheModel)) {
			return false;
		}

		SantaCacheModel santaCacheModel = (SantaCacheModel)object;

		if (santaId == santaCacheModel.santaId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, santaId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(29);

		sb.append("{santaId=");
		sb.append(santaId);
		sb.append(", year=");
		sb.append(year);
		sb.append(", ecode=");
		sb.append(ecode);
		sb.append(", mobile=");
		sb.append(mobile);
		sb.append(", city=");
		sb.append(city);
		sb.append(", state=");
		sb.append(state);
		sb.append(", pincode=");
		sb.append(pincode);
		sb.append(", postalAddress=");
		sb.append(postalAddress);
		sb.append(", santaEcode=");
		sb.append(santaEcode);
		sb.append(", guessedEcode=");
		sb.append(guessedEcode);
		sb.append(", giftSent=");
		sb.append(giftSent);
		sb.append(", emailSent=");
		sb.append(emailSent);
		sb.append(", fileId=");
		sb.append(fileId);
		sb.append(", createDatetime=");
		sb.append(createDatetime);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Santa toEntityModel() {
		SantaImpl santaImpl = new SantaImpl();

		santaImpl.setSantaId(santaId);
		santaImpl.setYear(year);

		if (ecode == null) {
			santaImpl.setEcode("");
		}
		else {
			santaImpl.setEcode(ecode);
		}

		if (mobile == null) {
			santaImpl.setMobile("");
		}
		else {
			santaImpl.setMobile(mobile);
		}

		if (city == null) {
			santaImpl.setCity("");
		}
		else {
			santaImpl.setCity(city);
		}

		if (state == null) {
			santaImpl.setState("");
		}
		else {
			santaImpl.setState(state);
		}

		if (pincode == null) {
			santaImpl.setPincode("");
		}
		else {
			santaImpl.setPincode(pincode);
		}

		if (postalAddress == null) {
			santaImpl.setPostalAddress("");
		}
		else {
			santaImpl.setPostalAddress(postalAddress);
		}

		if (santaEcode == null) {
			santaImpl.setSantaEcode("");
		}
		else {
			santaImpl.setSantaEcode(santaEcode);
		}

		if (guessedEcode == null) {
			santaImpl.setGuessedEcode("");
		}
		else {
			santaImpl.setGuessedEcode(guessedEcode);
		}

		santaImpl.setGiftSent(giftSent);
		santaImpl.setEmailSent(emailSent);

		if (fileId == null) {
			santaImpl.setFileId("");
		}
		else {
			santaImpl.setFileId(fileId);
		}

		if (createDatetime == Long.MIN_VALUE) {
			santaImpl.setCreateDatetime(null);
		}
		else {
			santaImpl.setCreateDatetime(new Date(createDatetime));
		}

		santaImpl.resetOriginalValues();

		return santaImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		santaId = objectInput.readLong();

		year = objectInput.readInt();
		ecode = objectInput.readUTF();
		mobile = objectInput.readUTF();
		city = objectInput.readUTF();
		state = objectInput.readUTF();
		pincode = objectInput.readUTF();
		postalAddress = objectInput.readUTF();
		santaEcode = objectInput.readUTF();
		guessedEcode = objectInput.readUTF();

		giftSent = objectInput.readBoolean();

		emailSent = objectInput.readBoolean();
		fileId = objectInput.readUTF();
		createDatetime = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(santaId);

		objectOutput.writeInt(year);

		if (ecode == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(ecode);
		}

		if (mobile == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(mobile);
		}

		if (city == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(city);
		}

		if (state == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(state);
		}

		if (pincode == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(pincode);
		}

		if (postalAddress == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(postalAddress);
		}

		if (santaEcode == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(santaEcode);
		}

		if (guessedEcode == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(guessedEcode);
		}

		objectOutput.writeBoolean(giftSent);

		objectOutput.writeBoolean(emailSent);

		if (fileId == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(fileId);
		}

		objectOutput.writeLong(createDatetime);
	}

	public long santaId;
	public int year;
	public String ecode;
	public String mobile;
	public String city;
	public String state;
	public String pincode;
	public String postalAddress;
	public String santaEcode;
	public String guessedEcode;
	public boolean giftSent;
	public boolean emailSent;
	public String fileId;
	public long createDatetime;

}