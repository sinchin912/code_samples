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

import com.trantorinc.synergy.notice.core.model.HrForm;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing HrForm in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class HrFormCacheModel implements CacheModel<HrForm>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof HrFormCacheModel)) {
			return false;
		}

		HrFormCacheModel hrFormCacheModel = (HrFormCacheModel)object;

		if (id == hrFormCacheModel.id) {
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
		StringBundler sb = new StringBundler(107);

		sb.append("{id=");
		sb.append(id);
		sb.append(", exitId=");
		sb.append(exitId);
		sb.append(", foodOption=");
		sb.append(foodOption);
		sb.append(", foodOptionRemark=");
		sb.append(foodOptionRemark);
		sb.append(", inductionFeedbackStatus=");
		sb.append(inductionFeedbackStatus);
		sb.append(", inductionFeedbackRemark=");
		sb.append(inductionFeedbackRemark);
		sb.append(", inductionQuizStatus=");
		sb.append(inductionQuizStatus);
		sb.append(", inductionQuizRemark=");
		sb.append(inductionQuizRemark);
		sb.append(", trainingFeedbackStatus=");
		sb.append(trainingFeedbackStatus);
		sb.append(", trainingFeedbackRemark=");
		sb.append(trainingFeedbackRemark);
		sb.append(", exitInterviewStatus=");
		sb.append(exitInterviewStatus);
		sb.append(", exitInterviewRemark=");
		sb.append(exitInterviewRemark);
		sb.append(", employeeDirectoryStatus=");
		sb.append(employeeDirectoryStatus);
		sb.append(", employeeDirectoryRemark=");
		sb.append(employeeDirectoryRemark);
		sb.append(", lmsStatus=");
		sb.append(lmsStatus);
		sb.append(", lmsRemark=");
		sb.append(lmsRemark);
		sb.append(", vantageCircleStatus=");
		sb.append(vantageCircleStatus);
		sb.append(", vantageCircleRemark=");
		sb.append(vantageCircleRemark);
		sb.append(", birthdaySynergyStatus=");
		sb.append(birthdaySynergyStatus);
		sb.append(", birthdaySynergyRemark=");
		sb.append(birthdaySynergyRemark);
		sb.append(", experienceLetterStatus=");
		sb.append(experienceLetterStatus);
		sb.append(", experienceLetterRemark=");
		sb.append(experienceLetterRemark);
		sb.append(", ndaFormStatus=");
		sb.append(ndaFormStatus);
		sb.append(", ndaFormRemark=");
		sb.append(ndaFormRemark);
		sb.append(", separationDocumentStatus=");
		sb.append(separationDocumentStatus);
		sb.append(", separationDocumentRemark=");
		sb.append(separationDocumentRemark);
		sb.append(", trainingAgreementAmt=");
		sb.append(trainingAgreementAmt);
		sb.append(", trainingAgreementStatus=");
		sb.append(trainingAgreementStatus);
		sb.append(", recoverableBonusAmt=");
		sb.append(recoverableBonusAmt);
		sb.append(", recoverableBonusStatus=");
		sb.append(recoverableBonusStatus);
		sb.append(", noticePeriodRecoveryAmt=");
		sb.append(noticePeriodRecoveryAmt);
		sb.append(", noticePeriodRecoveryStatus=");
		sb.append(noticePeriodRecoveryStatus);
		sb.append(", leavesMonth1=");
		sb.append(leavesMonth1);
		sb.append(", leaveDaysMonth1=");
		sb.append(leaveDaysMonth1);
		sb.append(", leaveDateMonth1=");
		sb.append(leaveDateMonth1);
		sb.append(", leavesMonth2=");
		sb.append(leavesMonth2);
		sb.append(", leaveDaysMonth2=");
		sb.append(leaveDaysMonth2);
		sb.append(", leaveDateMonth2=");
		sb.append(leaveDateMonth2);
		sb.append(", leavesMonth3=");
		sb.append(leavesMonth3);
		sb.append(", leaveDaysMonth3=");
		sb.append(leaveDaysMonth3);
		sb.append(", leaveDateMonth3=");
		sb.append(leaveDateMonth3);
		sb.append(", lopMonth1=");
		sb.append(lopMonth1);
		sb.append(", lopDaysMonth1=");
		sb.append(lopDaysMonth1);
		sb.append(", lopDateMonth1=");
		sb.append(lopDateMonth1);
		sb.append(", lopMonth2=");
		sb.append(lopMonth2);
		sb.append(", lopDaysMonth2=");
		sb.append(lopDaysMonth2);
		sb.append(", lopDateMonth2=");
		sb.append(lopDateMonth2);
		sb.append(", lopMonth3=");
		sb.append(lopMonth3);
		sb.append(", lopDaysMonth3=");
		sb.append(lopDaysMonth3);
		sb.append(", lopDateMonth3=");
		sb.append(lopDateMonth3);
		sb.append(", earnedLeaveBalance=");
		sb.append(earnedLeaveBalance);
		sb.append(", hrRemark=");
		sb.append(hrRemark);
		sb.append(", updatedDate=");
		sb.append(updatedDate);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public HrForm toEntityModel() {
		HrFormImpl hrFormImpl = new HrFormImpl();

		hrFormImpl.setId(id);
		hrFormImpl.setExitId(exitId);
		hrFormImpl.setFoodOption(foodOption);

		if (foodOptionRemark == null) {
			hrFormImpl.setFoodOptionRemark("");
		}
		else {
			hrFormImpl.setFoodOptionRemark(foodOptionRemark);
		}

		hrFormImpl.setInductionFeedbackStatus(inductionFeedbackStatus);

		if (inductionFeedbackRemark == null) {
			hrFormImpl.setInductionFeedbackRemark("");
		}
		else {
			hrFormImpl.setInductionFeedbackRemark(inductionFeedbackRemark);
		}

		hrFormImpl.setInductionQuizStatus(inductionQuizStatus);

		if (inductionQuizRemark == null) {
			hrFormImpl.setInductionQuizRemark("");
		}
		else {
			hrFormImpl.setInductionQuizRemark(inductionQuizRemark);
		}

		hrFormImpl.setTrainingFeedbackStatus(trainingFeedbackStatus);

		if (trainingFeedbackRemark == null) {
			hrFormImpl.setTrainingFeedbackRemark("");
		}
		else {
			hrFormImpl.setTrainingFeedbackRemark(trainingFeedbackRemark);
		}

		hrFormImpl.setExitInterviewStatus(exitInterviewStatus);

		if (exitInterviewRemark == null) {
			hrFormImpl.setExitInterviewRemark("");
		}
		else {
			hrFormImpl.setExitInterviewRemark(exitInterviewRemark);
		}

		hrFormImpl.setEmployeeDirectoryStatus(employeeDirectoryStatus);

		if (employeeDirectoryRemark == null) {
			hrFormImpl.setEmployeeDirectoryRemark("");
		}
		else {
			hrFormImpl.setEmployeeDirectoryRemark(employeeDirectoryRemark);
		}

		hrFormImpl.setLmsStatus(lmsStatus);

		if (lmsRemark == null) {
			hrFormImpl.setLmsRemark("");
		}
		else {
			hrFormImpl.setLmsRemark(lmsRemark);
		}

		hrFormImpl.setVantageCircleStatus(vantageCircleStatus);

		if (vantageCircleRemark == null) {
			hrFormImpl.setVantageCircleRemark("");
		}
		else {
			hrFormImpl.setVantageCircleRemark(vantageCircleRemark);
		}

		hrFormImpl.setBirthdaySynergyStatus(birthdaySynergyStatus);

		if (birthdaySynergyRemark == null) {
			hrFormImpl.setBirthdaySynergyRemark("");
		}
		else {
			hrFormImpl.setBirthdaySynergyRemark(birthdaySynergyRemark);
		}

		hrFormImpl.setExperienceLetterStatus(experienceLetterStatus);

		if (experienceLetterRemark == null) {
			hrFormImpl.setExperienceLetterRemark("");
		}
		else {
			hrFormImpl.setExperienceLetterRemark(experienceLetterRemark);
		}

		hrFormImpl.setNdaFormStatus(ndaFormStatus);

		if (ndaFormRemark == null) {
			hrFormImpl.setNdaFormRemark("");
		}
		else {
			hrFormImpl.setNdaFormRemark(ndaFormRemark);
		}

		hrFormImpl.setSeparationDocumentStatus(separationDocumentStatus);

		if (separationDocumentRemark == null) {
			hrFormImpl.setSeparationDocumentRemark("");
		}
		else {
			hrFormImpl.setSeparationDocumentRemark(separationDocumentRemark);
		}

		if (trainingAgreementAmt == null) {
			hrFormImpl.setTrainingAgreementAmt("");
		}
		else {
			hrFormImpl.setTrainingAgreementAmt(trainingAgreementAmt);
		}

		hrFormImpl.setTrainingAgreementStatus(trainingAgreementStatus);

		if (recoverableBonusAmt == null) {
			hrFormImpl.setRecoverableBonusAmt("");
		}
		else {
			hrFormImpl.setRecoverableBonusAmt(recoverableBonusAmt);
		}

		hrFormImpl.setRecoverableBonusStatus(recoverableBonusStatus);

		if (noticePeriodRecoveryAmt == null) {
			hrFormImpl.setNoticePeriodRecoveryAmt("");
		}
		else {
			hrFormImpl.setNoticePeriodRecoveryAmt(noticePeriodRecoveryAmt);
		}

		hrFormImpl.setNoticePeriodRecoveryStatus(noticePeriodRecoveryStatus);

		if (leavesMonth1 == null) {
			hrFormImpl.setLeavesMonth1("");
		}
		else {
			hrFormImpl.setLeavesMonth1(leavesMonth1);
		}

		if (leaveDaysMonth1 == null) {
			hrFormImpl.setLeaveDaysMonth1("");
		}
		else {
			hrFormImpl.setLeaveDaysMonth1(leaveDaysMonth1);
		}

		if (leaveDateMonth1 == null) {
			hrFormImpl.setLeaveDateMonth1("");
		}
		else {
			hrFormImpl.setLeaveDateMonth1(leaveDateMonth1);
		}

		if (leavesMonth2 == null) {
			hrFormImpl.setLeavesMonth2("");
		}
		else {
			hrFormImpl.setLeavesMonth2(leavesMonth2);
		}

		if (leaveDaysMonth2 == null) {
			hrFormImpl.setLeaveDaysMonth2("");
		}
		else {
			hrFormImpl.setLeaveDaysMonth2(leaveDaysMonth2);
		}

		if (leaveDateMonth2 == null) {
			hrFormImpl.setLeaveDateMonth2("");
		}
		else {
			hrFormImpl.setLeaveDateMonth2(leaveDateMonth2);
		}

		if (leavesMonth3 == null) {
			hrFormImpl.setLeavesMonth3("");
		}
		else {
			hrFormImpl.setLeavesMonth3(leavesMonth3);
		}

		if (leaveDaysMonth3 == null) {
			hrFormImpl.setLeaveDaysMonth3("");
		}
		else {
			hrFormImpl.setLeaveDaysMonth3(leaveDaysMonth3);
		}

		if (leaveDateMonth3 == null) {
			hrFormImpl.setLeaveDateMonth3("");
		}
		else {
			hrFormImpl.setLeaveDateMonth3(leaveDateMonth3);
		}

		if (lopMonth1 == null) {
			hrFormImpl.setLopMonth1("");
		}
		else {
			hrFormImpl.setLopMonth1(lopMonth1);
		}

		if (lopDaysMonth1 == null) {
			hrFormImpl.setLopDaysMonth1("");
		}
		else {
			hrFormImpl.setLopDaysMonth1(lopDaysMonth1);
		}

		if (lopDateMonth1 == null) {
			hrFormImpl.setLopDateMonth1("");
		}
		else {
			hrFormImpl.setLopDateMonth1(lopDateMonth1);
		}

		if (lopMonth2 == null) {
			hrFormImpl.setLopMonth2("");
		}
		else {
			hrFormImpl.setLopMonth2(lopMonth2);
		}

		if (lopDaysMonth2 == null) {
			hrFormImpl.setLopDaysMonth2("");
		}
		else {
			hrFormImpl.setLopDaysMonth2(lopDaysMonth2);
		}

		if (lopDateMonth2 == null) {
			hrFormImpl.setLopDateMonth2("");
		}
		else {
			hrFormImpl.setLopDateMonth2(lopDateMonth2);
		}

		if (lopMonth3 == null) {
			hrFormImpl.setLopMonth3("");
		}
		else {
			hrFormImpl.setLopMonth3(lopMonth3);
		}

		if (lopDaysMonth3 == null) {
			hrFormImpl.setLopDaysMonth3("");
		}
		else {
			hrFormImpl.setLopDaysMonth3(lopDaysMonth3);
		}

		if (lopDateMonth3 == null) {
			hrFormImpl.setLopDateMonth3("");
		}
		else {
			hrFormImpl.setLopDateMonth3(lopDateMonth3);
		}

		if (earnedLeaveBalance == null) {
			hrFormImpl.setEarnedLeaveBalance("");
		}
		else {
			hrFormImpl.setEarnedLeaveBalance(earnedLeaveBalance);
		}

		if (hrRemark == null) {
			hrFormImpl.setHrRemark("");
		}
		else {
			hrFormImpl.setHrRemark(hrRemark);
		}

		if (updatedDate == Long.MIN_VALUE) {
			hrFormImpl.setUpdatedDate(null);
		}
		else {
			hrFormImpl.setUpdatedDate(new Date(updatedDate));
		}

		hrFormImpl.resetOriginalValues();

		return hrFormImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		id = objectInput.readLong();

		exitId = objectInput.readLong();

		foodOption = objectInput.readLong();
		foodOptionRemark = objectInput.readUTF();

		inductionFeedbackStatus = objectInput.readInt();
		inductionFeedbackRemark = objectInput.readUTF();

		inductionQuizStatus = objectInput.readInt();
		inductionQuizRemark = objectInput.readUTF();

		trainingFeedbackStatus = objectInput.readInt();
		trainingFeedbackRemark = objectInput.readUTF();

		exitInterviewStatus = objectInput.readInt();
		exitInterviewRemark = objectInput.readUTF();

		employeeDirectoryStatus = objectInput.readInt();
		employeeDirectoryRemark = objectInput.readUTF();

		lmsStatus = objectInput.readInt();
		lmsRemark = objectInput.readUTF();

		vantageCircleStatus = objectInput.readInt();
		vantageCircleRemark = objectInput.readUTF();

		birthdaySynergyStatus = objectInput.readInt();
		birthdaySynergyRemark = objectInput.readUTF();

		experienceLetterStatus = objectInput.readInt();
		experienceLetterRemark = objectInput.readUTF();

		ndaFormStatus = objectInput.readInt();
		ndaFormRemark = objectInput.readUTF();

		separationDocumentStatus = objectInput.readInt();
		separationDocumentRemark = objectInput.readUTF();
		trainingAgreementAmt = objectInput.readUTF();

		trainingAgreementStatus = objectInput.readInt();
		recoverableBonusAmt = objectInput.readUTF();

		recoverableBonusStatus = objectInput.readInt();
		noticePeriodRecoveryAmt = objectInput.readUTF();

		noticePeriodRecoveryStatus = objectInput.readInt();
		leavesMonth1 = objectInput.readUTF();
		leaveDaysMonth1 = objectInput.readUTF();
		leaveDateMonth1 = objectInput.readUTF();
		leavesMonth2 = objectInput.readUTF();
		leaveDaysMonth2 = objectInput.readUTF();
		leaveDateMonth2 = objectInput.readUTF();
		leavesMonth3 = objectInput.readUTF();
		leaveDaysMonth3 = objectInput.readUTF();
		leaveDateMonth3 = objectInput.readUTF();
		lopMonth1 = objectInput.readUTF();
		lopDaysMonth1 = objectInput.readUTF();
		lopDateMonth1 = objectInput.readUTF();
		lopMonth2 = objectInput.readUTF();
		lopDaysMonth2 = objectInput.readUTF();
		lopDateMonth2 = objectInput.readUTF();
		lopMonth3 = objectInput.readUTF();
		lopDaysMonth3 = objectInput.readUTF();
		lopDateMonth3 = objectInput.readUTF();
		earnedLeaveBalance = objectInput.readUTF();
		hrRemark = objectInput.readUTF();
		updatedDate = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(id);

		objectOutput.writeLong(exitId);

		objectOutput.writeLong(foodOption);

		if (foodOptionRemark == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(foodOptionRemark);
		}

		objectOutput.writeInt(inductionFeedbackStatus);

		if (inductionFeedbackRemark == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(inductionFeedbackRemark);
		}

		objectOutput.writeInt(inductionQuizStatus);

		if (inductionQuizRemark == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(inductionQuizRemark);
		}

		objectOutput.writeInt(trainingFeedbackStatus);

		if (trainingFeedbackRemark == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(trainingFeedbackRemark);
		}

		objectOutput.writeInt(exitInterviewStatus);

		if (exitInterviewRemark == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(exitInterviewRemark);
		}

		objectOutput.writeInt(employeeDirectoryStatus);

		if (employeeDirectoryRemark == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(employeeDirectoryRemark);
		}

		objectOutput.writeInt(lmsStatus);

		if (lmsRemark == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(lmsRemark);
		}

		objectOutput.writeInt(vantageCircleStatus);

		if (vantageCircleRemark == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(vantageCircleRemark);
		}

		objectOutput.writeInt(birthdaySynergyStatus);

		if (birthdaySynergyRemark == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(birthdaySynergyRemark);
		}

		objectOutput.writeInt(experienceLetterStatus);

		if (experienceLetterRemark == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(experienceLetterRemark);
		}

		objectOutput.writeInt(ndaFormStatus);

		if (ndaFormRemark == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(ndaFormRemark);
		}

		objectOutput.writeInt(separationDocumentStatus);

		if (separationDocumentRemark == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(separationDocumentRemark);
		}

		if (trainingAgreementAmt == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(trainingAgreementAmt);
		}

		objectOutput.writeInt(trainingAgreementStatus);

		if (recoverableBonusAmt == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(recoverableBonusAmt);
		}

		objectOutput.writeInt(recoverableBonusStatus);

		if (noticePeriodRecoveryAmt == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(noticePeriodRecoveryAmt);
		}

		objectOutput.writeInt(noticePeriodRecoveryStatus);

		if (leavesMonth1 == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(leavesMonth1);
		}

		if (leaveDaysMonth1 == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(leaveDaysMonth1);
		}

		if (leaveDateMonth1 == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(leaveDateMonth1);
		}

		if (leavesMonth2 == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(leavesMonth2);
		}

		if (leaveDaysMonth2 == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(leaveDaysMonth2);
		}

		if (leaveDateMonth2 == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(leaveDateMonth2);
		}

		if (leavesMonth3 == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(leavesMonth3);
		}

		if (leaveDaysMonth3 == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(leaveDaysMonth3);
		}

		if (leaveDateMonth3 == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(leaveDateMonth3);
		}

		if (lopMonth1 == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(lopMonth1);
		}

		if (lopDaysMonth1 == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(lopDaysMonth1);
		}

		if (lopDateMonth1 == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(lopDateMonth1);
		}

		if (lopMonth2 == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(lopMonth2);
		}

		if (lopDaysMonth2 == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(lopDaysMonth2);
		}

		if (lopDateMonth2 == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(lopDateMonth2);
		}

		if (lopMonth3 == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(lopMonth3);
		}

		if (lopDaysMonth3 == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(lopDaysMonth3);
		}

		if (lopDateMonth3 == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(lopDateMonth3);
		}

		if (earnedLeaveBalance == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(earnedLeaveBalance);
		}

		if (hrRemark == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(hrRemark);
		}

		objectOutput.writeLong(updatedDate);
	}

	public long id;
	public long exitId;
	public long foodOption;
	public String foodOptionRemark;
	public int inductionFeedbackStatus;
	public String inductionFeedbackRemark;
	public int inductionQuizStatus;
	public String inductionQuizRemark;
	public int trainingFeedbackStatus;
	public String trainingFeedbackRemark;
	public int exitInterviewStatus;
	public String exitInterviewRemark;
	public int employeeDirectoryStatus;
	public String employeeDirectoryRemark;
	public int lmsStatus;
	public String lmsRemark;
	public int vantageCircleStatus;
	public String vantageCircleRemark;
	public int birthdaySynergyStatus;
	public String birthdaySynergyRemark;
	public int experienceLetterStatus;
	public String experienceLetterRemark;
	public int ndaFormStatus;
	public String ndaFormRemark;
	public int separationDocumentStatus;
	public String separationDocumentRemark;
	public String trainingAgreementAmt;
	public int trainingAgreementStatus;
	public String recoverableBonusAmt;
	public int recoverableBonusStatus;
	public String noticePeriodRecoveryAmt;
	public int noticePeriodRecoveryStatus;
	public String leavesMonth1;
	public String leaveDaysMonth1;
	public String leaveDateMonth1;
	public String leavesMonth2;
	public String leaveDaysMonth2;
	public String leaveDateMonth2;
	public String leavesMonth3;
	public String leaveDaysMonth3;
	public String leaveDateMonth3;
	public String lopMonth1;
	public String lopDaysMonth1;
	public String lopDateMonth1;
	public String lopMonth2;
	public String lopDaysMonth2;
	public String lopDateMonth2;
	public String lopMonth3;
	public String lopDaysMonth3;
	public String lopDateMonth3;
	public String earnedLeaveBalance;
	public String hrRemark;
	public long updatedDate;

}