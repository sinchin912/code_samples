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

import com.trantorinc.synergy.notice.core.model.FinanceForm;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing FinanceForm in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class FinanceFormCacheModel
	implements CacheModel<FinanceForm>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof FinanceFormCacheModel)) {
			return false;
		}

		FinanceFormCacheModel financeFormCacheModel =
			(FinanceFormCacheModel)object;

		if (id == financeFormCacheModel.id) {
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
		StringBundler sb = new StringBundler(69);

		sb.append("{id=");
		sb.append(id);
		sb.append(", exitId=");
		sb.append(exitId);
		sb.append(", lastSalaryPaidDays=");
		sb.append(lastSalaryPaidDays);
		sb.append(", lastSalaryPaidMonth=");
		sb.append(lastSalaryPaidMonth);
		sb.append(", lastSalaryPaidYear=");
		sb.append(lastSalaryPaidYear);
		sb.append(", taxProofStatus=");
		sb.append(taxProofStatus);
		sb.append(", taxProofRemark=");
		sb.append(taxProofRemark);
		sb.append(", foodReimbursementStatus=");
		sb.append(foodReimbursementStatus);
		sb.append(", foodReimbursementAmt=");
		sb.append(foodReimbursementAmt);
		sb.append(", travelRecoveryAmt=");
		sb.append(travelRecoveryAmt);
		sb.append(", travelRecoveryStatus=");
		sb.append(travelRecoveryStatus);
		sb.append(", travelRecoveryApproved=");
		sb.append(travelRecoveryApproved);
		sb.append(", travelRecoveryComment=");
		sb.append(travelRecoveryComment);
		sb.append(", hotelRecoveryAmt=");
		sb.append(hotelRecoveryAmt);
		sb.append(", hotelRecoveryStatus=");
		sb.append(hotelRecoveryStatus);
		sb.append(", hotelRecoveryApproved=");
		sb.append(hotelRecoveryApproved);
		sb.append(", hotelRecoveryComment=");
		sb.append(hotelRecoveryComment);
		sb.append(", internationalRecoveryAmt=");
		sb.append(internationalRecoveryAmt);
		sb.append(", internationalRecoveryStatus=");
		sb.append(internationalRecoveryStatus);
		sb.append(", internationalRecoveryApproved=");
		sb.append(internationalRecoveryApproved);
		sb.append(", internationalRecoveryComment=");
		sb.append(internationalRecoveryComment);
		sb.append(", educationRecoveryAmt=");
		sb.append(educationRecoveryAmt);
		sb.append(", educationRecoveryStatus=");
		sb.append(educationRecoveryStatus);
		sb.append(", educationRecoveryApproved=");
		sb.append(educationRecoveryApproved);
		sb.append(", educationRecoveryComment=");
		sb.append(educationRecoveryComment);
		sb.append(", joiningBonusRecoveryAmt=");
		sb.append(joiningBonusRecoveryAmt);
		sb.append(", joiningBonusRecoveryStatus=");
		sb.append(joiningBonusRecoveryStatus);
		sb.append(", joiningRecoveryApproved=");
		sb.append(joiningRecoveryApproved);
		sb.append(", joiningRecoveryComment=");
		sb.append(joiningRecoveryComment);
		sb.append(", noticePeriodRecoveryAmt=");
		sb.append(noticePeriodRecoveryAmt);
		sb.append(", noticePeriodRecoveryStatus=");
		sb.append(noticePeriodRecoveryStatus);
		sb.append(", noticePeriodRecoveryApproved=");
		sb.append(noticePeriodRecoveryApproved);
		sb.append(", noticePeriodRecoveryComment=");
		sb.append(noticePeriodRecoveryComment);
		sb.append(", updatedDate=");
		sb.append(updatedDate);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public FinanceForm toEntityModel() {
		FinanceFormImpl financeFormImpl = new FinanceFormImpl();

		financeFormImpl.setId(id);
		financeFormImpl.setExitId(exitId);
		financeFormImpl.setLastSalaryPaidDays(lastSalaryPaidDays);

		if (lastSalaryPaidMonth == null) {
			financeFormImpl.setLastSalaryPaidMonth("");
		}
		else {
			financeFormImpl.setLastSalaryPaidMonth(lastSalaryPaidMonth);
		}

		if (lastSalaryPaidYear == null) {
			financeFormImpl.setLastSalaryPaidYear("");
		}
		else {
			financeFormImpl.setLastSalaryPaidYear(lastSalaryPaidYear);
		}

		financeFormImpl.setTaxProofStatus(taxProofStatus);

		if (taxProofRemark == null) {
			financeFormImpl.setTaxProofRemark("");
		}
		else {
			financeFormImpl.setTaxProofRemark(taxProofRemark);
		}

		financeFormImpl.setFoodReimbursementStatus(foodReimbursementStatus);

		if (foodReimbursementAmt == null) {
			financeFormImpl.setFoodReimbursementAmt("");
		}
		else {
			financeFormImpl.setFoodReimbursementAmt(foodReimbursementAmt);
		}

		if (travelRecoveryAmt == null) {
			financeFormImpl.setTravelRecoveryAmt("");
		}
		else {
			financeFormImpl.setTravelRecoveryAmt(travelRecoveryAmt);
		}

		financeFormImpl.setTravelRecoveryStatus(travelRecoveryStatus);
		financeFormImpl.setTravelRecoveryApproved(travelRecoveryApproved);

		if (travelRecoveryComment == null) {
			financeFormImpl.setTravelRecoveryComment("");
		}
		else {
			financeFormImpl.setTravelRecoveryComment(travelRecoveryComment);
		}

		if (hotelRecoveryAmt == null) {
			financeFormImpl.setHotelRecoveryAmt("");
		}
		else {
			financeFormImpl.setHotelRecoveryAmt(hotelRecoveryAmt);
		}

		financeFormImpl.setHotelRecoveryStatus(hotelRecoveryStatus);
		financeFormImpl.setHotelRecoveryApproved(hotelRecoveryApproved);

		if (hotelRecoveryComment == null) {
			financeFormImpl.setHotelRecoveryComment("");
		}
		else {
			financeFormImpl.setHotelRecoveryComment(hotelRecoveryComment);
		}

		if (internationalRecoveryAmt == null) {
			financeFormImpl.setInternationalRecoveryAmt("");
		}
		else {
			financeFormImpl.setInternationalRecoveryAmt(
				internationalRecoveryAmt);
		}

		financeFormImpl.setInternationalRecoveryStatus(
			internationalRecoveryStatus);
		financeFormImpl.setInternationalRecoveryApproved(
			internationalRecoveryApproved);

		if (internationalRecoveryComment == null) {
			financeFormImpl.setInternationalRecoveryComment("");
		}
		else {
			financeFormImpl.setInternationalRecoveryComment(
				internationalRecoveryComment);
		}

		if (educationRecoveryAmt == null) {
			financeFormImpl.setEducationRecoveryAmt("");
		}
		else {
			financeFormImpl.setEducationRecoveryAmt(educationRecoveryAmt);
		}

		financeFormImpl.setEducationRecoveryStatus(educationRecoveryStatus);
		financeFormImpl.setEducationRecoveryApproved(educationRecoveryApproved);

		if (educationRecoveryComment == null) {
			financeFormImpl.setEducationRecoveryComment("");
		}
		else {
			financeFormImpl.setEducationRecoveryComment(
				educationRecoveryComment);
		}

		if (joiningBonusRecoveryAmt == null) {
			financeFormImpl.setJoiningBonusRecoveryAmt("");
		}
		else {
			financeFormImpl.setJoiningBonusRecoveryAmt(joiningBonusRecoveryAmt);
		}

		financeFormImpl.setJoiningBonusRecoveryStatus(
			joiningBonusRecoveryStatus);
		financeFormImpl.setJoiningRecoveryApproved(joiningRecoveryApproved);

		if (joiningRecoveryComment == null) {
			financeFormImpl.setJoiningRecoveryComment("");
		}
		else {
			financeFormImpl.setJoiningRecoveryComment(joiningRecoveryComment);
		}

		if (noticePeriodRecoveryAmt == null) {
			financeFormImpl.setNoticePeriodRecoveryAmt("");
		}
		else {
			financeFormImpl.setNoticePeriodRecoveryAmt(noticePeriodRecoveryAmt);
		}

		financeFormImpl.setNoticePeriodRecoveryStatus(
			noticePeriodRecoveryStatus);
		financeFormImpl.setNoticePeriodRecoveryApproved(
			noticePeriodRecoveryApproved);

		if (noticePeriodRecoveryComment == null) {
			financeFormImpl.setNoticePeriodRecoveryComment("");
		}
		else {
			financeFormImpl.setNoticePeriodRecoveryComment(
				noticePeriodRecoveryComment);
		}

		if (updatedDate == Long.MIN_VALUE) {
			financeFormImpl.setUpdatedDate(null);
		}
		else {
			financeFormImpl.setUpdatedDate(new Date(updatedDate));
		}

		financeFormImpl.resetOriginalValues();

		return financeFormImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		id = objectInput.readLong();

		exitId = objectInput.readLong();

		lastSalaryPaidDays = objectInput.readLong();
		lastSalaryPaidMonth = objectInput.readUTF();
		lastSalaryPaidYear = objectInput.readUTF();

		taxProofStatus = objectInput.readInt();
		taxProofRemark = objectInput.readUTF();

		foodReimbursementStatus = objectInput.readInt();
		foodReimbursementAmt = objectInput.readUTF();
		travelRecoveryAmt = objectInput.readUTF();

		travelRecoveryStatus = objectInput.readInt();

		travelRecoveryApproved = objectInput.readBoolean();
		travelRecoveryComment = objectInput.readUTF();
		hotelRecoveryAmt = objectInput.readUTF();

		hotelRecoveryStatus = objectInput.readInt();

		hotelRecoveryApproved = objectInput.readBoolean();
		hotelRecoveryComment = objectInput.readUTF();
		internationalRecoveryAmt = objectInput.readUTF();

		internationalRecoveryStatus = objectInput.readInt();

		internationalRecoveryApproved = objectInput.readBoolean();
		internationalRecoveryComment = objectInput.readUTF();
		educationRecoveryAmt = objectInput.readUTF();

		educationRecoveryStatus = objectInput.readInt();

		educationRecoveryApproved = objectInput.readBoolean();
		educationRecoveryComment = objectInput.readUTF();
		joiningBonusRecoveryAmt = objectInput.readUTF();

		joiningBonusRecoveryStatus = objectInput.readInt();

		joiningRecoveryApproved = objectInput.readBoolean();
		joiningRecoveryComment = objectInput.readUTF();
		noticePeriodRecoveryAmt = objectInput.readUTF();

		noticePeriodRecoveryStatus = objectInput.readInt();

		noticePeriodRecoveryApproved = objectInput.readBoolean();
		noticePeriodRecoveryComment = objectInput.readUTF();
		updatedDate = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(id);

		objectOutput.writeLong(exitId);

		objectOutput.writeLong(lastSalaryPaidDays);

		if (lastSalaryPaidMonth == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(lastSalaryPaidMonth);
		}

		if (lastSalaryPaidYear == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(lastSalaryPaidYear);
		}

		objectOutput.writeInt(taxProofStatus);

		if (taxProofRemark == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(taxProofRemark);
		}

		objectOutput.writeInt(foodReimbursementStatus);

		if (foodReimbursementAmt == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(foodReimbursementAmt);
		}

		if (travelRecoveryAmt == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(travelRecoveryAmt);
		}

		objectOutput.writeInt(travelRecoveryStatus);

		objectOutput.writeBoolean(travelRecoveryApproved);

		if (travelRecoveryComment == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(travelRecoveryComment);
		}

		if (hotelRecoveryAmt == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(hotelRecoveryAmt);
		}

		objectOutput.writeInt(hotelRecoveryStatus);

		objectOutput.writeBoolean(hotelRecoveryApproved);

		if (hotelRecoveryComment == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(hotelRecoveryComment);
		}

		if (internationalRecoveryAmt == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(internationalRecoveryAmt);
		}

		objectOutput.writeInt(internationalRecoveryStatus);

		objectOutput.writeBoolean(internationalRecoveryApproved);

		if (internationalRecoveryComment == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(internationalRecoveryComment);
		}

		if (educationRecoveryAmt == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(educationRecoveryAmt);
		}

		objectOutput.writeInt(educationRecoveryStatus);

		objectOutput.writeBoolean(educationRecoveryApproved);

		if (educationRecoveryComment == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(educationRecoveryComment);
		}

		if (joiningBonusRecoveryAmt == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(joiningBonusRecoveryAmt);
		}

		objectOutput.writeInt(joiningBonusRecoveryStatus);

		objectOutput.writeBoolean(joiningRecoveryApproved);

		if (joiningRecoveryComment == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(joiningRecoveryComment);
		}

		if (noticePeriodRecoveryAmt == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(noticePeriodRecoveryAmt);
		}

		objectOutput.writeInt(noticePeriodRecoveryStatus);

		objectOutput.writeBoolean(noticePeriodRecoveryApproved);

		if (noticePeriodRecoveryComment == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(noticePeriodRecoveryComment);
		}

		objectOutput.writeLong(updatedDate);
	}

	public long id;
	public long exitId;
	public long lastSalaryPaidDays;
	public String lastSalaryPaidMonth;
	public String lastSalaryPaidYear;
	public int taxProofStatus;
	public String taxProofRemark;
	public int foodReimbursementStatus;
	public String foodReimbursementAmt;
	public String travelRecoveryAmt;
	public int travelRecoveryStatus;
	public boolean travelRecoveryApproved;
	public String travelRecoveryComment;
	public String hotelRecoveryAmt;
	public int hotelRecoveryStatus;
	public boolean hotelRecoveryApproved;
	public String hotelRecoveryComment;
	public String internationalRecoveryAmt;
	public int internationalRecoveryStatus;
	public boolean internationalRecoveryApproved;
	public String internationalRecoveryComment;
	public String educationRecoveryAmt;
	public int educationRecoveryStatus;
	public boolean educationRecoveryApproved;
	public String educationRecoveryComment;
	public String joiningBonusRecoveryAmt;
	public int joiningBonusRecoveryStatus;
	public boolean joiningRecoveryApproved;
	public String joiningRecoveryComment;
	public String noticePeriodRecoveryAmt;
	public int noticePeriodRecoveryStatus;
	public boolean noticePeriodRecoveryApproved;
	public String noticePeriodRecoveryComment;
	public long updatedDate;

}