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

import com.trantorinc.synergy.notice.core.model.QuestionnaireForm;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing QuestionnaireForm in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class QuestionnaireFormCacheModel
	implements CacheModel<QuestionnaireForm>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof QuestionnaireFormCacheModel)) {
			return false;
		}

		QuestionnaireFormCacheModel questionnaireFormCacheModel =
			(QuestionnaireFormCacheModel)object;

		if (id == questionnaireFormCacheModel.id) {
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
		StringBundler sb = new StringBundler(45);

		sb.append("{id=");
		sb.append(id);
		sb.append(", resignationId=");
		sb.append(resignationId);
		sb.append(", workExperience=");
		sb.append(workExperience);
		sb.append(", communicationWithEmployees=");
		sb.append(communicationWithEmployees);
		sb.append(", trainingOpportunity=");
		sb.append(trainingOpportunity);
		sb.append(", dealingWithStaff=");
		sb.append(dealingWithStaff);
		sb.append(", satisfactionLevel=");
		sb.append(satisfactionLevel);
		sb.append(", reasonForLeaving=");
		sb.append(reasonForLeaving);
		sb.append(", reasonForLeavingDescribe=");
		sb.append(reasonForLeavingDescribe);
		sb.append(", reasonForJoining=");
		sb.append(reasonForJoining);
		sb.append(", workAgain=");
		sb.append(workAgain);
		sb.append(", notWorkAgain=");
		sb.append(notWorkAgain);
		sb.append(", recommendTrantor=");
		sb.append(recommendTrantor);
		sb.append(", notRecommendTrantor=");
		sb.append(notRecommendTrantor);
		sb.append(", companyName=");
		sb.append(companyName);
		sb.append(", companyDetails=");
		sb.append(companyDetails);
		sb.append(", designation=");
		sb.append(designation);
		sb.append(", location  =");
		sb.append(location);
		sb.append(", salaryHike=");
		sb.append(salaryHike);
		sb.append(", feedback =");
		sb.append(feedback);
		sb.append(", hrRemark=");
		sb.append(hrRemark);
		sb.append(", submittedDate=");
		sb.append(submittedDate);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public QuestionnaireForm toEntityModel() {
		QuestionnaireFormImpl questionnaireFormImpl =
			new QuestionnaireFormImpl();

		questionnaireFormImpl.setId(id);
		questionnaireFormImpl.setResignationId(resignationId);

		if (workExperience == null) {
			questionnaireFormImpl.setWorkExperience("");
		}
		else {
			questionnaireFormImpl.setWorkExperience(workExperience);
		}

		if (communicationWithEmployees == null) {
			questionnaireFormImpl.setCommunicationWithEmployees("");
		}
		else {
			questionnaireFormImpl.setCommunicationWithEmployees(
				communicationWithEmployees);
		}

		if (trainingOpportunity == null) {
			questionnaireFormImpl.setTrainingOpportunity("");
		}
		else {
			questionnaireFormImpl.setTrainingOpportunity(trainingOpportunity);
		}

		if (dealingWithStaff == null) {
			questionnaireFormImpl.setDealingWithStaff("");
		}
		else {
			questionnaireFormImpl.setDealingWithStaff(dealingWithStaff);
		}

		if (satisfactionLevel == null) {
			questionnaireFormImpl.setSatisfactionLevel("");
		}
		else {
			questionnaireFormImpl.setSatisfactionLevel(satisfactionLevel);
		}

		if (reasonForLeaving == null) {
			questionnaireFormImpl.setReasonForLeaving("");
		}
		else {
			questionnaireFormImpl.setReasonForLeaving(reasonForLeaving);
		}

		if (reasonForLeavingDescribe == null) {
			questionnaireFormImpl.setReasonForLeavingDescribe("");
		}
		else {
			questionnaireFormImpl.setReasonForLeavingDescribe(
				reasonForLeavingDescribe);
		}

		if (reasonForJoining == null) {
			questionnaireFormImpl.setReasonForJoining("");
		}
		else {
			questionnaireFormImpl.setReasonForJoining(reasonForJoining);
		}

		questionnaireFormImpl.setWorkAgain(workAgain);

		if (notWorkAgain == null) {
			questionnaireFormImpl.setNotWorkAgain("");
		}
		else {
			questionnaireFormImpl.setNotWorkAgain(notWorkAgain);
		}

		questionnaireFormImpl.setRecommendTrantor(recommendTrantor);

		if (notRecommendTrantor == null) {
			questionnaireFormImpl.setNotRecommendTrantor("");
		}
		else {
			questionnaireFormImpl.setNotRecommendTrantor(notRecommendTrantor);
		}

		if (companyName == null) {
			questionnaireFormImpl.setCompanyName("");
		}
		else {
			questionnaireFormImpl.setCompanyName(companyName);
		}

		if (companyDetails == null) {
			questionnaireFormImpl.setCompanyDetails("");
		}
		else {
			questionnaireFormImpl.setCompanyDetails(companyDetails);
		}

		if (designation == null) {
			questionnaireFormImpl.setDesignation("");
		}
		else {
			questionnaireFormImpl.setDesignation(designation);
		}

		if (location == null) {
			questionnaireFormImpl.setLocation("");
		}
		else {
			questionnaireFormImpl.setLocation(location);
		}

		if (salaryHike == null) {
			questionnaireFormImpl.setSalaryHike("");
		}
		else {
			questionnaireFormImpl.setSalaryHike(salaryHike);
		}

		if (feedback == null) {
			questionnaireFormImpl.setFeedback("");
		}
		else {
			questionnaireFormImpl.setFeedback(feedback);
		}

		if (hrRemark == null) {
			questionnaireFormImpl.setHrRemark("");
		}
		else {
			questionnaireFormImpl.setHrRemark(hrRemark);
		}

		if (submittedDate == Long.MIN_VALUE) {
			questionnaireFormImpl.setSubmittedDate(null);
		}
		else {
			questionnaireFormImpl.setSubmittedDate(new Date(submittedDate));
		}

		questionnaireFormImpl.resetOriginalValues();

		return questionnaireFormImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		id = objectInput.readLong();

		resignationId = objectInput.readLong();
		workExperience = objectInput.readUTF();
		communicationWithEmployees = objectInput.readUTF();
		trainingOpportunity = objectInput.readUTF();
		dealingWithStaff = objectInput.readUTF();
		satisfactionLevel = objectInput.readUTF();
		reasonForLeaving = objectInput.readUTF();
		reasonForLeavingDescribe = objectInput.readUTF();
		reasonForJoining = objectInput.readUTF();

		workAgain = objectInput.readInt();
		notWorkAgain = objectInput.readUTF();

		recommendTrantor = objectInput.readInt();
		notRecommendTrantor = objectInput.readUTF();
		companyName = objectInput.readUTF();
		companyDetails = objectInput.readUTF();
		designation = objectInput.readUTF();
		location = objectInput.readUTF();
		salaryHike = objectInput.readUTF();
		feedback = objectInput.readUTF();
		hrRemark = objectInput.readUTF();
		submittedDate = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(id);

		objectOutput.writeLong(resignationId);

		if (workExperience == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(workExperience);
		}

		if (communicationWithEmployees == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(communicationWithEmployees);
		}

		if (trainingOpportunity == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(trainingOpportunity);
		}

		if (dealingWithStaff == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(dealingWithStaff);
		}

		if (satisfactionLevel == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(satisfactionLevel);
		}

		if (reasonForLeaving == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(reasonForLeaving);
		}

		if (reasonForLeavingDescribe == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(reasonForLeavingDescribe);
		}

		if (reasonForJoining == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(reasonForJoining);
		}

		objectOutput.writeInt(workAgain);

		if (notWorkAgain == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(notWorkAgain);
		}

		objectOutput.writeInt(recommendTrantor);

		if (notRecommendTrantor == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(notRecommendTrantor);
		}

		if (companyName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(companyName);
		}

		if (companyDetails == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(companyDetails);
		}

		if (designation == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(designation);
		}

		if (location == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(location);
		}

		if (salaryHike == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(salaryHike);
		}

		if (feedback == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(feedback);
		}

		if (hrRemark == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(hrRemark);
		}

		objectOutput.writeLong(submittedDate);
	}

	public long id;
	public long resignationId;
	public String workExperience;
	public String communicationWithEmployees;
	public String trainingOpportunity;
	public String dealingWithStaff;
	public String satisfactionLevel;
	public String reasonForLeaving;
	public String reasonForLeavingDescribe;
	public String reasonForJoining;
	public int workAgain;
	public String notWorkAgain;
	public int recommendTrantor;
	public String notRecommendTrantor;
	public String companyName;
	public String companyDetails;
	public String designation;
	public String location;
	public String salaryHike;
	public String feedback;
	public String hrRemark;
	public long submittedDate;

}