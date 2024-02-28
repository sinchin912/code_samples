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

import com.trantorinc.synergy.notice.core.model.Resignation;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Resignation in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ResignationCacheModel
	implements CacheModel<Resignation>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof ResignationCacheModel)) {
			return false;
		}

		ResignationCacheModel resignationCacheModel =
			(ResignationCacheModel)object;

		if (id == resignationCacheModel.id) {
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
		sb.append(", ecode=");
		sb.append(ecode);
		sb.append(", account=");
		sb.append(account);
		sb.append(", managerEmail=");
		sb.append(managerEmail);
		sb.append(", assigneeEmail=");
		sb.append(assigneeEmail);
		sb.append(", assigneeTo=");
		sb.append(assigneeTo);
		sb.append(", alternateEmail=");
		sb.append(alternateEmail);
		sb.append(", stateName=");
		sb.append(stateName);
		sb.append(", cityName=");
		sb.append(cityName);
		sb.append(", pincode=");
		sb.append(pincode);
		sb.append(", postalAddress=");
		sb.append(postalAddress);
		sb.append(", reason=");
		sb.append(reason);
		sb.append(", lastWorkingDate=");
		sb.append(lastWorkingDate);
		sb.append(", managerSubmissionDate=");
		sb.append(managerSubmissionDate);
		sb.append(", hrSubmissionDate=");
		sb.append(hrSubmissionDate);
		sb.append(", withdrawInitiatedDate=");
		sb.append(withdrawInitiatedDate);
		sb.append(", abscondTerminateDate=");
		sb.append(abscondTerminateDate);
		sb.append(", separationType=");
		sb.append(separationType);
		sb.append(", keyToCompany=");
		sb.append(keyToCompany);
		sb.append(", keyToProject=");
		sb.append(keyToProject);
		sb.append(", rating=");
		sb.append(rating);
		sb.append(", clientImpact=");
		sb.append(clientImpact);
		sb.append(", keyToRetention=");
		sb.append(keyToRetention);
		sb.append(", reasonNonRetention=");
		sb.append(reasonNonRetention);
		sb.append(", employeeComment=");
		sb.append(employeeComment);
		sb.append(", hrComment=");
		sb.append(hrComment);
		sb.append(", managerComment=");
		sb.append(managerComment);
		sb.append(", empWithdrawComment=");
		sb.append(empWithdrawComment);
		sb.append(", hrWithdrawComment=");
		sb.append(hrWithdrawComment);
		sb.append(", reasonForLeaving=");
		sb.append(reasonForLeaving);
		sb.append(", reasonForLeavingByHr=");
		sb.append(reasonForLeavingByHr);
		sb.append(", eligibleForRehire=");
		sb.append(eligibleForRehire);
		sb.append(", otherIssue=");
		sb.append(otherIssue);
		sb.append(", replacementPlan=");
		sb.append(replacementPlan);
		sb.append(", replacementDetail=");
		sb.append(replacementDetail);
		sb.append(", status=");
		sb.append(status);
		sb.append(", terminationType=");
		sb.append(terminationType);
		sb.append(", creationDate=");
		sb.append(creationDate);
		sb.append(", initiatedDate=");
		sb.append(initiatedDate);
		sb.append(", mobile=");
		sb.append(mobile);
		sb.append(", noticePeriod=");
		sb.append(noticePeriod);
		sb.append(", itAssetsSubmissionDate=");
		sb.append(itAssetsSubmissionDate);
		sb.append(", retainEmployeeDate=");
		sb.append(retainEmployeeDate);
		sb.append(", hasLaptop=");
		sb.append(hasLaptop);
		sb.append(", hasMouse=");
		sb.append(hasMouse);
		sb.append(", hasCharger=");
		sb.append(hasCharger);
		sb.append(", hasHeadSet=");
		sb.append(hasHeadSet);
		sb.append(", hasLaptopBag=");
		sb.append(hasLaptopBag);
		sb.append(", hasHomeDesktop=");
		sb.append(hasHomeDesktop);
		sb.append(", hasHomeMonitor=");
		sb.append(hasHomeMonitor);
		sb.append(", otherAssetsList=");
		sb.append(otherAssetsList);
		sb.append(", exitQuestionnaire=");
		sb.append(exitQuestionnaire);
		sb.append(", withdrawCount=");
		sb.append(withdrawCount);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Resignation toEntityModel() {
		ResignationImpl resignationImpl = new ResignationImpl();

		resignationImpl.setId(id);

		if (ecode == null) {
			resignationImpl.setEcode("");
		}
		else {
			resignationImpl.setEcode(ecode);
		}

		if (account == null) {
			resignationImpl.setAccount("");
		}
		else {
			resignationImpl.setAccount(account);
		}

		if (managerEmail == null) {
			resignationImpl.setManagerEmail("");
		}
		else {
			resignationImpl.setManagerEmail(managerEmail);
		}

		if (assigneeEmail == null) {
			resignationImpl.setAssigneeEmail("");
		}
		else {
			resignationImpl.setAssigneeEmail(assigneeEmail);
		}

		resignationImpl.setAssigneeTo(assigneeTo);

		if (alternateEmail == null) {
			resignationImpl.setAlternateEmail("");
		}
		else {
			resignationImpl.setAlternateEmail(alternateEmail);
		}

		if (stateName == null) {
			resignationImpl.setStateName("");
		}
		else {
			resignationImpl.setStateName(stateName);
		}

		if (cityName == null) {
			resignationImpl.setCityName("");
		}
		else {
			resignationImpl.setCityName(cityName);
		}

		if (pincode == null) {
			resignationImpl.setPincode("");
		}
		else {
			resignationImpl.setPincode(pincode);
		}

		if (postalAddress == null) {
			resignationImpl.setPostalAddress("");
		}
		else {
			resignationImpl.setPostalAddress(postalAddress);
		}

		if (reason == null) {
			resignationImpl.setReason("");
		}
		else {
			resignationImpl.setReason(reason);
		}

		if (lastWorkingDate == Long.MIN_VALUE) {
			resignationImpl.setLastWorkingDate(null);
		}
		else {
			resignationImpl.setLastWorkingDate(new Date(lastWorkingDate));
		}

		if (managerSubmissionDate == Long.MIN_VALUE) {
			resignationImpl.setManagerSubmissionDate(null);
		}
		else {
			resignationImpl.setManagerSubmissionDate(
				new Date(managerSubmissionDate));
		}

		if (hrSubmissionDate == Long.MIN_VALUE) {
			resignationImpl.setHrSubmissionDate(null);
		}
		else {
			resignationImpl.setHrSubmissionDate(new Date(hrSubmissionDate));
		}

		if (withdrawInitiatedDate == Long.MIN_VALUE) {
			resignationImpl.setWithdrawInitiatedDate(null);
		}
		else {
			resignationImpl.setWithdrawInitiatedDate(
				new Date(withdrawInitiatedDate));
		}

		if (abscondTerminateDate == Long.MIN_VALUE) {
			resignationImpl.setAbscondTerminateDate(null);
		}
		else {
			resignationImpl.setAbscondTerminateDate(
				new Date(abscondTerminateDate));
		}

		if (separationType == null) {
			resignationImpl.setSeparationType("");
		}
		else {
			resignationImpl.setSeparationType(separationType);
		}

		if (keyToCompany == null) {
			resignationImpl.setKeyToCompany("");
		}
		else {
			resignationImpl.setKeyToCompany(keyToCompany);
		}

		if (keyToProject == null) {
			resignationImpl.setKeyToProject("");
		}
		else {
			resignationImpl.setKeyToProject(keyToProject);
		}

		if (rating == null) {
			resignationImpl.setRating("");
		}
		else {
			resignationImpl.setRating(rating);
		}

		if (clientImpact == null) {
			resignationImpl.setClientImpact("");
		}
		else {
			resignationImpl.setClientImpact(clientImpact);
		}

		if (keyToRetention == null) {
			resignationImpl.setKeyToRetention("");
		}
		else {
			resignationImpl.setKeyToRetention(keyToRetention);
		}

		if (reasonNonRetention == null) {
			resignationImpl.setReasonNonRetention("");
		}
		else {
			resignationImpl.setReasonNonRetention(reasonNonRetention);
		}

		if (employeeComment == null) {
			resignationImpl.setEmployeeComment("");
		}
		else {
			resignationImpl.setEmployeeComment(employeeComment);
		}

		if (hrComment == null) {
			resignationImpl.setHrComment("");
		}
		else {
			resignationImpl.setHrComment(hrComment);
		}

		if (managerComment == null) {
			resignationImpl.setManagerComment("");
		}
		else {
			resignationImpl.setManagerComment(managerComment);
		}

		if (empWithdrawComment == null) {
			resignationImpl.setEmpWithdrawComment("");
		}
		else {
			resignationImpl.setEmpWithdrawComment(empWithdrawComment);
		}

		if (hrWithdrawComment == null) {
			resignationImpl.setHrWithdrawComment("");
		}
		else {
			resignationImpl.setHrWithdrawComment(hrWithdrawComment);
		}

		if (reasonForLeaving == null) {
			resignationImpl.setReasonForLeaving("");
		}
		else {
			resignationImpl.setReasonForLeaving(reasonForLeaving);
		}

		if (reasonForLeavingByHr == null) {
			resignationImpl.setReasonForLeavingByHr("");
		}
		else {
			resignationImpl.setReasonForLeavingByHr(reasonForLeavingByHr);
		}

		if (eligibleForRehire == null) {
			resignationImpl.setEligibleForRehire("");
		}
		else {
			resignationImpl.setEligibleForRehire(eligibleForRehire);
		}

		if (otherIssue == null) {
			resignationImpl.setOtherIssue("");
		}
		else {
			resignationImpl.setOtherIssue(otherIssue);
		}

		if (replacementPlan == null) {
			resignationImpl.setReplacementPlan("");
		}
		else {
			resignationImpl.setReplacementPlan(replacementPlan);
		}

		if (replacementDetail == null) {
			resignationImpl.setReplacementDetail("");
		}
		else {
			resignationImpl.setReplacementDetail(replacementDetail);
		}

		resignationImpl.setStatus(status);

		if (terminationType == null) {
			resignationImpl.setTerminationType("");
		}
		else {
			resignationImpl.setTerminationType(terminationType);
		}

		if (creationDate == Long.MIN_VALUE) {
			resignationImpl.setCreationDate(null);
		}
		else {
			resignationImpl.setCreationDate(new Date(creationDate));
		}

		if (initiatedDate == Long.MIN_VALUE) {
			resignationImpl.setInitiatedDate(null);
		}
		else {
			resignationImpl.setInitiatedDate(new Date(initiatedDate));
		}

		if (mobile == null) {
			resignationImpl.setMobile("");
		}
		else {
			resignationImpl.setMobile(mobile);
		}

		if (noticePeriod == null) {
			resignationImpl.setNoticePeriod("");
		}
		else {
			resignationImpl.setNoticePeriod(noticePeriod);
		}

		if (itAssetsSubmissionDate == Long.MIN_VALUE) {
			resignationImpl.setItAssetsSubmissionDate(null);
		}
		else {
			resignationImpl.setItAssetsSubmissionDate(
				new Date(itAssetsSubmissionDate));
		}

		if (retainEmployeeDate == Long.MIN_VALUE) {
			resignationImpl.setRetainEmployeeDate(null);
		}
		else {
			resignationImpl.setRetainEmployeeDate(new Date(retainEmployeeDate));
		}

		if (hasLaptop == null) {
			resignationImpl.setHasLaptop("");
		}
		else {
			resignationImpl.setHasLaptop(hasLaptop);
		}

		if (hasMouse == null) {
			resignationImpl.setHasMouse("");
		}
		else {
			resignationImpl.setHasMouse(hasMouse);
		}

		if (hasCharger == null) {
			resignationImpl.setHasCharger("");
		}
		else {
			resignationImpl.setHasCharger(hasCharger);
		}

		if (hasHeadSet == null) {
			resignationImpl.setHasHeadSet("");
		}
		else {
			resignationImpl.setHasHeadSet(hasHeadSet);
		}

		if (hasLaptopBag == null) {
			resignationImpl.setHasLaptopBag("");
		}
		else {
			resignationImpl.setHasLaptopBag(hasLaptopBag);
		}

		if (hasHomeDesktop == null) {
			resignationImpl.setHasHomeDesktop("");
		}
		else {
			resignationImpl.setHasHomeDesktop(hasHomeDesktop);
		}

		if (hasHomeMonitor == null) {
			resignationImpl.setHasHomeMonitor("");
		}
		else {
			resignationImpl.setHasHomeMonitor(hasHomeMonitor);
		}

		if (otherAssetsList == null) {
			resignationImpl.setOtherAssetsList("");
		}
		else {
			resignationImpl.setOtherAssetsList(otherAssetsList);
		}

		resignationImpl.setExitQuestionnaire(exitQuestionnaire);
		resignationImpl.setWithdrawCount(withdrawCount);

		resignationImpl.resetOriginalValues();

		return resignationImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		id = objectInput.readLong();
		ecode = objectInput.readUTF();
		account = objectInput.readUTF();
		managerEmail = objectInput.readUTF();
		assigneeEmail = objectInput.readUTF();

		assigneeTo = objectInput.readBoolean();
		alternateEmail = objectInput.readUTF();
		stateName = objectInput.readUTF();
		cityName = objectInput.readUTF();
		pincode = objectInput.readUTF();
		postalAddress = objectInput.readUTF();
		reason = objectInput.readUTF();
		lastWorkingDate = objectInput.readLong();
		managerSubmissionDate = objectInput.readLong();
		hrSubmissionDate = objectInput.readLong();
		withdrawInitiatedDate = objectInput.readLong();
		abscondTerminateDate = objectInput.readLong();
		separationType = objectInput.readUTF();
		keyToCompany = objectInput.readUTF();
		keyToProject = objectInput.readUTF();
		rating = objectInput.readUTF();
		clientImpact = objectInput.readUTF();
		keyToRetention = objectInput.readUTF();
		reasonNonRetention = objectInput.readUTF();
		employeeComment = objectInput.readUTF();
		hrComment = objectInput.readUTF();
		managerComment = objectInput.readUTF();
		empWithdrawComment = objectInput.readUTF();
		hrWithdrawComment = objectInput.readUTF();
		reasonForLeaving = objectInput.readUTF();
		reasonForLeavingByHr = objectInput.readUTF();
		eligibleForRehire = objectInput.readUTF();
		otherIssue = objectInput.readUTF();
		replacementPlan = objectInput.readUTF();
		replacementDetail = objectInput.readUTF();

		status = objectInput.readInt();
		terminationType = objectInput.readUTF();
		creationDate = objectInput.readLong();
		initiatedDate = objectInput.readLong();
		mobile = objectInput.readUTF();
		noticePeriod = objectInput.readUTF();
		itAssetsSubmissionDate = objectInput.readLong();
		retainEmployeeDate = objectInput.readLong();
		hasLaptop = objectInput.readUTF();
		hasMouse = objectInput.readUTF();
		hasCharger = objectInput.readUTF();
		hasHeadSet = objectInput.readUTF();
		hasLaptopBag = objectInput.readUTF();
		hasHomeDesktop = objectInput.readUTF();
		hasHomeMonitor = objectInput.readUTF();
		otherAssetsList = objectInput.readUTF();

		exitQuestionnaire = objectInput.readBoolean();

		withdrawCount = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(id);

		if (ecode == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(ecode);
		}

		if (account == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(account);
		}

		if (managerEmail == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(managerEmail);
		}

		if (assigneeEmail == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(assigneeEmail);
		}

		objectOutput.writeBoolean(assigneeTo);

		if (alternateEmail == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(alternateEmail);
		}

		if (stateName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(stateName);
		}

		if (cityName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(cityName);
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

		if (reason == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(reason);
		}

		objectOutput.writeLong(lastWorkingDate);
		objectOutput.writeLong(managerSubmissionDate);
		objectOutput.writeLong(hrSubmissionDate);
		objectOutput.writeLong(withdrawInitiatedDate);
		objectOutput.writeLong(abscondTerminateDate);

		if (separationType == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(separationType);
		}

		if (keyToCompany == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(keyToCompany);
		}

		if (keyToProject == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(keyToProject);
		}

		if (rating == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(rating);
		}

		if (clientImpact == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(clientImpact);
		}

		if (keyToRetention == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(keyToRetention);
		}

		if (reasonNonRetention == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(reasonNonRetention);
		}

		if (employeeComment == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(employeeComment);
		}

		if (hrComment == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(hrComment);
		}

		if (managerComment == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(managerComment);
		}

		if (empWithdrawComment == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(empWithdrawComment);
		}

		if (hrWithdrawComment == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(hrWithdrawComment);
		}

		if (reasonForLeaving == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(reasonForLeaving);
		}

		if (reasonForLeavingByHr == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(reasonForLeavingByHr);
		}

		if (eligibleForRehire == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(eligibleForRehire);
		}

		if (otherIssue == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(otherIssue);
		}

		if (replacementPlan == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(replacementPlan);
		}

		if (replacementDetail == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(replacementDetail);
		}

		objectOutput.writeInt(status);

		if (terminationType == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(terminationType);
		}

		objectOutput.writeLong(creationDate);
		objectOutput.writeLong(initiatedDate);

		if (mobile == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(mobile);
		}

		if (noticePeriod == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(noticePeriod);
		}

		objectOutput.writeLong(itAssetsSubmissionDate);
		objectOutput.writeLong(retainEmployeeDate);

		if (hasLaptop == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(hasLaptop);
		}

		if (hasMouse == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(hasMouse);
		}

		if (hasCharger == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(hasCharger);
		}

		if (hasHeadSet == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(hasHeadSet);
		}

		if (hasLaptopBag == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(hasLaptopBag);
		}

		if (hasHomeDesktop == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(hasHomeDesktop);
		}

		if (hasHomeMonitor == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(hasHomeMonitor);
		}

		if (otherAssetsList == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(otherAssetsList);
		}

		objectOutput.writeBoolean(exitQuestionnaire);

		objectOutput.writeInt(withdrawCount);
	}

	public long id;
	public String ecode;
	public String account;
	public String managerEmail;
	public String assigneeEmail;
	public boolean assigneeTo;
	public String alternateEmail;
	public String stateName;
	public String cityName;
	public String pincode;
	public String postalAddress;
	public String reason;
	public long lastWorkingDate;
	public long managerSubmissionDate;
	public long hrSubmissionDate;
	public long withdrawInitiatedDate;
	public long abscondTerminateDate;
	public String separationType;
	public String keyToCompany;
	public String keyToProject;
	public String rating;
	public String clientImpact;
	public String keyToRetention;
	public String reasonNonRetention;
	public String employeeComment;
	public String hrComment;
	public String managerComment;
	public String empWithdrawComment;
	public String hrWithdrawComment;
	public String reasonForLeaving;
	public String reasonForLeavingByHr;
	public String eligibleForRehire;
	public String otherIssue;
	public String replacementPlan;
	public String replacementDetail;
	public int status;
	public String terminationType;
	public long creationDate;
	public long initiatedDate;
	public String mobile;
	public String noticePeriod;
	public long itAssetsSubmissionDate;
	public long retainEmployeeDate;
	public String hasLaptop;
	public String hasMouse;
	public String hasCharger;
	public String hasHeadSet;
	public String hasLaptopBag;
	public String hasHomeDesktop;
	public String hasHomeMonitor;
	public String otherAssetsList;
	public boolean exitQuestionnaire;
	public int withdrawCount;

}