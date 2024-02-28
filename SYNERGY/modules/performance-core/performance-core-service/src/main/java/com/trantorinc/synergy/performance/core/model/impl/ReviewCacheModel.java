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

package com.trantorinc.synergy.performance.core.model.impl;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import com.trantorinc.synergy.performance.core.model.Review;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Review in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ReviewCacheModel implements CacheModel<Review>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof ReviewCacheModel)) {
			return false;
		}

		ReviewCacheModel reviewCacheModel = (ReviewCacheModel)object;

		if (reviewId == reviewCacheModel.reviewId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, reviewId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(77);

		sb.append("{reviewId=");
		sb.append(reviewId);
		sb.append(", kpiId=");
		sb.append(kpiId);
		sb.append(", ecode=");
		sb.append(ecode);
		sb.append(", account=");
		sb.append(account);
		sb.append(", manager=");
		sb.append(manager);
		sb.append(", assignee=");
		sb.append(assignee);
		sb.append(", assigned=");
		sb.append(assigned);
		sb.append(", reviewStartDate=");
		sb.append(reviewStartDate);
		sb.append(", reviewState=");
		sb.append(reviewState);
		sb.append(", accountPrimary=");
		sb.append(accountPrimary);
		sb.append(", finalYearReview=");
		sb.append(finalYearReview);
		sb.append(", comment=");
		sb.append(comment);
		sb.append(", employeeComment=");
		sb.append(employeeComment);
		sb.append(", managerComment=");
		sb.append(managerComment);
		sb.append(", improvementComment=");
		sb.append(improvementComment);
		sb.append(", hrComment=");
		sb.append(hrComment);
		sb.append(", rating=");
		sb.append(rating);
		sb.append(", achievements=");
		sb.append(achievements);
		sb.append(", trainings=");
		sb.append(trainings);
		sb.append(", excelledArea=");
		sb.append(excelledArea);
		sb.append(", rolePlayed=");
		sb.append(rolePlayed);
		sb.append(", expectedDesignation=");
		sb.append(expectedDesignation);
		sb.append(", expectedSalary=");
		sb.append(expectedSalary);
		sb.append(", readyReason=");
		sb.append(readyReason);
		sb.append(", reasonRecommendation=");
		sb.append(reasonRecommendation);
		sb.append(", promotionRecommendation=");
		sb.append(promotionRecommendation);
		sb.append(", criticalToAccount=");
		sb.append(criticalToAccount);
		sb.append(", criticalToCompany=");
		sb.append(criticalToCompany);
		sb.append(", replacement=");
		sb.append(replacement);
		sb.append(", replacementDetail=");
		sb.append(replacementDetail);
		sb.append(", replacementReason=");
		sb.append(replacementReason);
		sb.append(", replacementReasonOther=");
		sb.append(replacementReasonOther);
		sb.append(", stage1Date=");
		sb.append(stage1Date);
		sb.append(", stage2Date=");
		sb.append(stage2Date);
		sb.append(", stage3Date=");
		sb.append(stage3Date);
		sb.append(", stage4Date=");
		sb.append(stage4Date);
		sb.append(", stage5Date=");
		sb.append(stage5Date);
		sb.append(", firstSubmit=");
		sb.append(firstSubmit);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Review toEntityModel() {
		ReviewImpl reviewImpl = new ReviewImpl();

		reviewImpl.setReviewId(reviewId);
		reviewImpl.setKpiId(kpiId);

		if (ecode == null) {
			reviewImpl.setEcode("");
		}
		else {
			reviewImpl.setEcode(ecode);
		}

		if (account == null) {
			reviewImpl.setAccount("");
		}
		else {
			reviewImpl.setAccount(account);
		}

		if (manager == null) {
			reviewImpl.setManager("");
		}
		else {
			reviewImpl.setManager(manager);
		}

		if (assignee == null) {
			reviewImpl.setAssignee("");
		}
		else {
			reviewImpl.setAssignee(assignee);
		}

		reviewImpl.setAssigned(assigned);

		if (reviewStartDate == Long.MIN_VALUE) {
			reviewImpl.setReviewStartDate(null);
		}
		else {
			reviewImpl.setReviewStartDate(new Date(reviewStartDate));
		}

		reviewImpl.setReviewState(reviewState);
		reviewImpl.setAccountPrimary(accountPrimary);
		reviewImpl.setFinalYearReview(finalYearReview);

		if (comment == null) {
			reviewImpl.setComment("");
		}
		else {
			reviewImpl.setComment(comment);
		}

		if (employeeComment == null) {
			reviewImpl.setEmployeeComment("");
		}
		else {
			reviewImpl.setEmployeeComment(employeeComment);
		}

		if (managerComment == null) {
			reviewImpl.setManagerComment("");
		}
		else {
			reviewImpl.setManagerComment(managerComment);
		}

		if (improvementComment == null) {
			reviewImpl.setImprovementComment("");
		}
		else {
			reviewImpl.setImprovementComment(improvementComment);
		}

		if (hrComment == null) {
			reviewImpl.setHrComment("");
		}
		else {
			reviewImpl.setHrComment(hrComment);
		}

		if (rating == null) {
			reviewImpl.setRating("");
		}
		else {
			reviewImpl.setRating(rating);
		}

		if (achievements == null) {
			reviewImpl.setAchievements("");
		}
		else {
			reviewImpl.setAchievements(achievements);
		}

		if (trainings == null) {
			reviewImpl.setTrainings("");
		}
		else {
			reviewImpl.setTrainings(trainings);
		}

		if (excelledArea == null) {
			reviewImpl.setExcelledArea("");
		}
		else {
			reviewImpl.setExcelledArea(excelledArea);
		}

		if (rolePlayed == null) {
			reviewImpl.setRolePlayed("");
		}
		else {
			reviewImpl.setRolePlayed(rolePlayed);
		}

		if (expectedDesignation == null) {
			reviewImpl.setExpectedDesignation("");
		}
		else {
			reviewImpl.setExpectedDesignation(expectedDesignation);
		}

		if (expectedSalary == null) {
			reviewImpl.setExpectedSalary("");
		}
		else {
			reviewImpl.setExpectedSalary(expectedSalary);
		}

		if (readyReason == null) {
			reviewImpl.setReadyReason("");
		}
		else {
			reviewImpl.setReadyReason(readyReason);
		}

		if (reasonRecommendation == null) {
			reviewImpl.setReasonRecommendation("");
		}
		else {
			reviewImpl.setReasonRecommendation(reasonRecommendation);
		}

		reviewImpl.setPromotionRecommendation(promotionRecommendation);
		reviewImpl.setCriticalToAccount(criticalToAccount);
		reviewImpl.setCriticalToCompany(criticalToCompany);
		reviewImpl.setReplacement(replacement);

		if (replacementDetail == null) {
			reviewImpl.setReplacementDetail("");
		}
		else {
			reviewImpl.setReplacementDetail(replacementDetail);
		}

		if (replacementReason == null) {
			reviewImpl.setReplacementReason("");
		}
		else {
			reviewImpl.setReplacementReason(replacementReason);
		}

		reviewImpl.setReplacementReasonOther(replacementReasonOther);

		if (stage1Date == Long.MIN_VALUE) {
			reviewImpl.setStage1Date(null);
		}
		else {
			reviewImpl.setStage1Date(new Date(stage1Date));
		}

		if (stage2Date == Long.MIN_VALUE) {
			reviewImpl.setStage2Date(null);
		}
		else {
			reviewImpl.setStage2Date(new Date(stage2Date));
		}

		if (stage3Date == Long.MIN_VALUE) {
			reviewImpl.setStage3Date(null);
		}
		else {
			reviewImpl.setStage3Date(new Date(stage3Date));
		}

		if (stage4Date == Long.MIN_VALUE) {
			reviewImpl.setStage4Date(null);
		}
		else {
			reviewImpl.setStage4Date(new Date(stage4Date));
		}

		if (stage5Date == Long.MIN_VALUE) {
			reviewImpl.setStage5Date(null);
		}
		else {
			reviewImpl.setStage5Date(new Date(stage5Date));
		}

		reviewImpl.setFirstSubmit(firstSubmit);

		reviewImpl.resetOriginalValues();

		return reviewImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		reviewId = objectInput.readLong();

		kpiId = objectInput.readLong();
		ecode = objectInput.readUTF();
		account = objectInput.readUTF();
		manager = objectInput.readUTF();
		assignee = objectInput.readUTF();

		assigned = objectInput.readBoolean();
		reviewStartDate = objectInput.readLong();

		reviewState = objectInput.readInt();

		accountPrimary = objectInput.readBoolean();

		finalYearReview = objectInput.readBoolean();
		comment = objectInput.readUTF();
		employeeComment = objectInput.readUTF();
		managerComment = objectInput.readUTF();
		improvementComment = objectInput.readUTF();
		hrComment = objectInput.readUTF();
		rating = objectInput.readUTF();
		achievements = objectInput.readUTF();
		trainings = objectInput.readUTF();
		excelledArea = objectInput.readUTF();
		rolePlayed = objectInput.readUTF();
		expectedDesignation = objectInput.readUTF();
		expectedSalary = objectInput.readUTF();
		readyReason = objectInput.readUTF();
		reasonRecommendation = objectInput.readUTF();

		promotionRecommendation = objectInput.readBoolean();

		criticalToAccount = objectInput.readInt();

		criticalToCompany = objectInput.readInt();

		replacement = objectInput.readInt();
		replacementDetail = objectInput.readUTF();
		replacementReason = objectInput.readUTF();

		replacementReasonOther = objectInput.readBoolean();
		stage1Date = objectInput.readLong();
		stage2Date = objectInput.readLong();
		stage3Date = objectInput.readLong();
		stage4Date = objectInput.readLong();
		stage5Date = objectInput.readLong();

		firstSubmit = objectInput.readBoolean();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(reviewId);

		objectOutput.writeLong(kpiId);

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

		if (manager == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(manager);
		}

		if (assignee == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(assignee);
		}

		objectOutput.writeBoolean(assigned);
		objectOutput.writeLong(reviewStartDate);

		objectOutput.writeInt(reviewState);

		objectOutput.writeBoolean(accountPrimary);

		objectOutput.writeBoolean(finalYearReview);

		if (comment == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(comment);
		}

		if (employeeComment == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(employeeComment);
		}

		if (managerComment == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(managerComment);
		}

		if (improvementComment == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(improvementComment);
		}

		if (hrComment == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(hrComment);
		}

		if (rating == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(rating);
		}

		if (achievements == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(achievements);
		}

		if (trainings == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(trainings);
		}

		if (excelledArea == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(excelledArea);
		}

		if (rolePlayed == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(rolePlayed);
		}

		if (expectedDesignation == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(expectedDesignation);
		}

		if (expectedSalary == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(expectedSalary);
		}

		if (readyReason == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(readyReason);
		}

		if (reasonRecommendation == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(reasonRecommendation);
		}

		objectOutput.writeBoolean(promotionRecommendation);

		objectOutput.writeInt(criticalToAccount);

		objectOutput.writeInt(criticalToCompany);

		objectOutput.writeInt(replacement);

		if (replacementDetail == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(replacementDetail);
		}

		if (replacementReason == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(replacementReason);
		}

		objectOutput.writeBoolean(replacementReasonOther);
		objectOutput.writeLong(stage1Date);
		objectOutput.writeLong(stage2Date);
		objectOutput.writeLong(stage3Date);
		objectOutput.writeLong(stage4Date);
		objectOutput.writeLong(stage5Date);

		objectOutput.writeBoolean(firstSubmit);
	}

	public long reviewId;
	public long kpiId;
	public String ecode;
	public String account;
	public String manager;
	public String assignee;
	public boolean assigned;
	public long reviewStartDate;
	public int reviewState;
	public boolean accountPrimary;
	public boolean finalYearReview;
	public String comment;
	public String employeeComment;
	public String managerComment;
	public String improvementComment;
	public String hrComment;
	public String rating;
	public String achievements;
	public String trainings;
	public String excelledArea;
	public String rolePlayed;
	public String expectedDesignation;
	public String expectedSalary;
	public String readyReason;
	public String reasonRecommendation;
	public boolean promotionRecommendation;
	public int criticalToAccount;
	public int criticalToCompany;
	public int replacement;
	public String replacementDetail;
	public String replacementReason;
	public boolean replacementReasonOther;
	public long stage1Date;
	public long stage2Date;
	public long stage3Date;
	public long stage4Date;
	public long stage5Date;
	public boolean firstSubmit;

}