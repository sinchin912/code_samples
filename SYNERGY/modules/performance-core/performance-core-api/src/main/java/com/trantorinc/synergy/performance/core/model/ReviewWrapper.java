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

package com.trantorinc.synergy.performance.core.model;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link Review}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Review
 * @generated
 */
public class ReviewWrapper
	extends BaseModelWrapper<Review> implements ModelWrapper<Review>, Review {

	public ReviewWrapper(Review review) {
		super(review);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("reviewId", getReviewId());
		attributes.put("kpiId", getKpiId());
		attributes.put("ecode", getEcode());
		attributes.put("account", getAccount());
		attributes.put("manager", getManager());
		attributes.put("assignee", getAssignee());
		attributes.put("assigned", isAssigned());
		attributes.put("reviewStartDate", getReviewStartDate());
		attributes.put("reviewState", getReviewState());
		attributes.put("accountPrimary", isAccountPrimary());
		attributes.put("finalYearReview", isFinalYearReview());
		attributes.put("comment", getComment());
		attributes.put("employeeComment", getEmployeeComment());
		attributes.put("managerComment", getManagerComment());
		attributes.put("improvementComment", getImprovementComment());
		attributes.put("hrComment", getHrComment());
		attributes.put("rating", getRating());
		attributes.put("achievements", getAchievements());
		attributes.put("trainings", getTrainings());
		attributes.put("excelledArea", getExcelledArea());
		attributes.put("rolePlayed", getRolePlayed());
		attributes.put("expectedDesignation", getExpectedDesignation());
		attributes.put("expectedSalary", getExpectedSalary());
		attributes.put("readyReason", getReadyReason());
		attributes.put("reasonRecommendation", getReasonRecommendation());
		attributes.put("promotionRecommendation", isPromotionRecommendation());
		attributes.put("criticalToAccount", getCriticalToAccount());
		attributes.put("criticalToCompany", getCriticalToCompany());
		attributes.put("replacement", getReplacement());
		attributes.put("replacementDetail", getReplacementDetail());
		attributes.put("replacementReason", getReplacementReason());
		attributes.put("replacementReasonOther", isReplacementReasonOther());
		attributes.put("stage1Date", getStage1Date());
		attributes.put("stage2Date", getStage2Date());
		attributes.put("stage3Date", getStage3Date());
		attributes.put("stage4Date", getStage4Date());
		attributes.put("stage5Date", getStage5Date());
		attributes.put("firstSubmit", isFirstSubmit());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long reviewId = (Long)attributes.get("reviewId");

		if (reviewId != null) {
			setReviewId(reviewId);
		}

		Long kpiId = (Long)attributes.get("kpiId");

		if (kpiId != null) {
			setKpiId(kpiId);
		}

		String ecode = (String)attributes.get("ecode");

		if (ecode != null) {
			setEcode(ecode);
		}

		String account = (String)attributes.get("account");

		if (account != null) {
			setAccount(account);
		}

		String manager = (String)attributes.get("manager");

		if (manager != null) {
			setManager(manager);
		}

		String assignee = (String)attributes.get("assignee");

		if (assignee != null) {
			setAssignee(assignee);
		}

		Boolean assigned = (Boolean)attributes.get("assigned");

		if (assigned != null) {
			setAssigned(assigned);
		}

		Date reviewStartDate = (Date)attributes.get("reviewStartDate");

		if (reviewStartDate != null) {
			setReviewStartDate(reviewStartDate);
		}

		Integer reviewState = (Integer)attributes.get("reviewState");

		if (reviewState != null) {
			setReviewState(reviewState);
		}

		Boolean accountPrimary = (Boolean)attributes.get("accountPrimary");

		if (accountPrimary != null) {
			setAccountPrimary(accountPrimary);
		}

		Boolean finalYearReview = (Boolean)attributes.get("finalYearReview");

		if (finalYearReview != null) {
			setFinalYearReview(finalYearReview);
		}

		String comment = (String)attributes.get("comment");

		if (comment != null) {
			setComment(comment);
		}

		String employeeComment = (String)attributes.get("employeeComment");

		if (employeeComment != null) {
			setEmployeeComment(employeeComment);
		}

		String managerComment = (String)attributes.get("managerComment");

		if (managerComment != null) {
			setManagerComment(managerComment);
		}

		String improvementComment = (String)attributes.get(
			"improvementComment");

		if (improvementComment != null) {
			setImprovementComment(improvementComment);
		}

		String hrComment = (String)attributes.get("hrComment");

		if (hrComment != null) {
			setHrComment(hrComment);
		}

		String rating = (String)attributes.get("rating");

		if (rating != null) {
			setRating(rating);
		}

		String achievements = (String)attributes.get("achievements");

		if (achievements != null) {
			setAchievements(achievements);
		}

		String trainings = (String)attributes.get("trainings");

		if (trainings != null) {
			setTrainings(trainings);
		}

		String excelledArea = (String)attributes.get("excelledArea");

		if (excelledArea != null) {
			setExcelledArea(excelledArea);
		}

		String rolePlayed = (String)attributes.get("rolePlayed");

		if (rolePlayed != null) {
			setRolePlayed(rolePlayed);
		}

		String expectedDesignation = (String)attributes.get(
			"expectedDesignation");

		if (expectedDesignation != null) {
			setExpectedDesignation(expectedDesignation);
		}

		String expectedSalary = (String)attributes.get("expectedSalary");

		if (expectedSalary != null) {
			setExpectedSalary(expectedSalary);
		}

		String readyReason = (String)attributes.get("readyReason");

		if (readyReason != null) {
			setReadyReason(readyReason);
		}

		String reasonRecommendation = (String)attributes.get(
			"reasonRecommendation");

		if (reasonRecommendation != null) {
			setReasonRecommendation(reasonRecommendation);
		}

		Boolean promotionRecommendation = (Boolean)attributes.get(
			"promotionRecommendation");

		if (promotionRecommendation != null) {
			setPromotionRecommendation(promotionRecommendation);
		}

		Integer criticalToAccount = (Integer)attributes.get(
			"criticalToAccount");

		if (criticalToAccount != null) {
			setCriticalToAccount(criticalToAccount);
		}

		Integer criticalToCompany = (Integer)attributes.get(
			"criticalToCompany");

		if (criticalToCompany != null) {
			setCriticalToCompany(criticalToCompany);
		}

		Integer replacement = (Integer)attributes.get("replacement");

		if (replacement != null) {
			setReplacement(replacement);
		}

		String replacementDetail = (String)attributes.get("replacementDetail");

		if (replacementDetail != null) {
			setReplacementDetail(replacementDetail);
		}

		String replacementReason = (String)attributes.get("replacementReason");

		if (replacementReason != null) {
			setReplacementReason(replacementReason);
		}

		Boolean replacementReasonOther = (Boolean)attributes.get(
			"replacementReasonOther");

		if (replacementReasonOther != null) {
			setReplacementReasonOther(replacementReasonOther);
		}

		Date stage1Date = (Date)attributes.get("stage1Date");

		if (stage1Date != null) {
			setStage1Date(stage1Date);
		}

		Date stage2Date = (Date)attributes.get("stage2Date");

		if (stage2Date != null) {
			setStage2Date(stage2Date);
		}

		Date stage3Date = (Date)attributes.get("stage3Date");

		if (stage3Date != null) {
			setStage3Date(stage3Date);
		}

		Date stage4Date = (Date)attributes.get("stage4Date");

		if (stage4Date != null) {
			setStage4Date(stage4Date);
		}

		Date stage5Date = (Date)attributes.get("stage5Date");

		if (stage5Date != null) {
			setStage5Date(stage5Date);
		}

		Boolean firstSubmit = (Boolean)attributes.get("firstSubmit");

		if (firstSubmit != null) {
			setFirstSubmit(firstSubmit);
		}
	}

	@Override
	public Review cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the account of this review.
	 *
	 * @return the account of this review
	 */
	@Override
	public String getAccount() {
		return model.getAccount();
	}

	/**
	 * Returns the account primary of this review.
	 *
	 * @return the account primary of this review
	 */
	@Override
	public boolean getAccountPrimary() {
		return model.getAccountPrimary();
	}

	/**
	 * Returns the achievements of this review.
	 *
	 * @return the achievements of this review
	 */
	@Override
	public String getAchievements() {
		return model.getAchievements();
	}

	/**
	 * Returns the assigned of this review.
	 *
	 * @return the assigned of this review
	 */
	@Override
	public boolean getAssigned() {
		return model.getAssigned();
	}

	/**
	 * Returns the assignee of this review.
	 *
	 * @return the assignee of this review
	 */
	@Override
	public String getAssignee() {
		return model.getAssignee();
	}

	/**
	 * Returns the comment of this review.
	 *
	 * @return the comment of this review
	 */
	@Override
	public String getComment() {
		return model.getComment();
	}

	/**
	 * Returns the critical to account of this review.
	 *
	 * @return the critical to account of this review
	 */
	@Override
	public int getCriticalToAccount() {
		return model.getCriticalToAccount();
	}

	/**
	 * Returns the critical to company of this review.
	 *
	 * @return the critical to company of this review
	 */
	@Override
	public int getCriticalToCompany() {
		return model.getCriticalToCompany();
	}

	/**
	 * Returns the ecode of this review.
	 *
	 * @return the ecode of this review
	 */
	@Override
	public String getEcode() {
		return model.getEcode();
	}

	/**
	 * Returns the employee comment of this review.
	 *
	 * @return the employee comment of this review
	 */
	@Override
	public String getEmployeeComment() {
		return model.getEmployeeComment();
	}

	/**
	 * Returns the excelled area of this review.
	 *
	 * @return the excelled area of this review
	 */
	@Override
	public String getExcelledArea() {
		return model.getExcelledArea();
	}

	/**
	 * Returns the expected designation of this review.
	 *
	 * @return the expected designation of this review
	 */
	@Override
	public String getExpectedDesignation() {
		return model.getExpectedDesignation();
	}

	/**
	 * Returns the expected salary of this review.
	 *
	 * @return the expected salary of this review
	 */
	@Override
	public String getExpectedSalary() {
		return model.getExpectedSalary();
	}

	/**
	 * Returns the final year review of this review.
	 *
	 * @return the final year review of this review
	 */
	@Override
	public boolean getFinalYearReview() {
		return model.getFinalYearReview();
	}

	/**
	 * Returns the first submit of this review.
	 *
	 * @return the first submit of this review
	 */
	@Override
	public boolean getFirstSubmit() {
		return model.getFirstSubmit();
	}

	/**
	 * Returns the hr comment of this review.
	 *
	 * @return the hr comment of this review
	 */
	@Override
	public String getHrComment() {
		return model.getHrComment();
	}

	/**
	 * Returns the improvement comment of this review.
	 *
	 * @return the improvement comment of this review
	 */
	@Override
	public String getImprovementComment() {
		return model.getImprovementComment();
	}

	/**
	 * Returns the kpi ID of this review.
	 *
	 * @return the kpi ID of this review
	 */
	@Override
	public long getKpiId() {
		return model.getKpiId();
	}

	/**
	 * Returns the manager of this review.
	 *
	 * @return the manager of this review
	 */
	@Override
	public String getManager() {
		return model.getManager();
	}

	/**
	 * Returns the manager comment of this review.
	 *
	 * @return the manager comment of this review
	 */
	@Override
	public String getManagerComment() {
		return model.getManagerComment();
	}

	/**
	 * Returns the primary key of this review.
	 *
	 * @return the primary key of this review
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the promotion recommendation of this review.
	 *
	 * @return the promotion recommendation of this review
	 */
	@Override
	public boolean getPromotionRecommendation() {
		return model.getPromotionRecommendation();
	}

	/**
	 * Returns the rating of this review.
	 *
	 * @return the rating of this review
	 */
	@Override
	public String getRating() {
		return model.getRating();
	}

	/**
	 * Returns the ready reason of this review.
	 *
	 * @return the ready reason of this review
	 */
	@Override
	public String getReadyReason() {
		return model.getReadyReason();
	}

	/**
	 * Returns the reason recommendation of this review.
	 *
	 * @return the reason recommendation of this review
	 */
	@Override
	public String getReasonRecommendation() {
		return model.getReasonRecommendation();
	}

	/**
	 * Returns the replacement of this review.
	 *
	 * @return the replacement of this review
	 */
	@Override
	public int getReplacement() {
		return model.getReplacement();
	}

	/**
	 * Returns the replacement detail of this review.
	 *
	 * @return the replacement detail of this review
	 */
	@Override
	public String getReplacementDetail() {
		return model.getReplacementDetail();
	}

	/**
	 * Returns the replacement reason of this review.
	 *
	 * @return the replacement reason of this review
	 */
	@Override
	public String getReplacementReason() {
		return model.getReplacementReason();
	}

	/**
	 * Returns the replacement reason other of this review.
	 *
	 * @return the replacement reason other of this review
	 */
	@Override
	public boolean getReplacementReasonOther() {
		return model.getReplacementReasonOther();
	}

	/**
	 * Returns the review ID of this review.
	 *
	 * @return the review ID of this review
	 */
	@Override
	public long getReviewId() {
		return model.getReviewId();
	}

	/**
	 * Returns the review start date of this review.
	 *
	 * @return the review start date of this review
	 */
	@Override
	public Date getReviewStartDate() {
		return model.getReviewStartDate();
	}

	/**
	 * Returns the review state of this review.
	 *
	 * @return the review state of this review
	 */
	@Override
	public int getReviewState() {
		return model.getReviewState();
	}

	/**
	 * Returns the role played of this review.
	 *
	 * @return the role played of this review
	 */
	@Override
	public String getRolePlayed() {
		return model.getRolePlayed();
	}

	/**
	 * Returns the stage1 date of this review.
	 *
	 * @return the stage1 date of this review
	 */
	@Override
	public Date getStage1Date() {
		return model.getStage1Date();
	}

	/**
	 * Returns the stage2 date of this review.
	 *
	 * @return the stage2 date of this review
	 */
	@Override
	public Date getStage2Date() {
		return model.getStage2Date();
	}

	/**
	 * Returns the stage3 date of this review.
	 *
	 * @return the stage3 date of this review
	 */
	@Override
	public Date getStage3Date() {
		return model.getStage3Date();
	}

	/**
	 * Returns the stage4 date of this review.
	 *
	 * @return the stage4 date of this review
	 */
	@Override
	public Date getStage4Date() {
		return model.getStage4Date();
	}

	/**
	 * Returns the stage5 date of this review.
	 *
	 * @return the stage5 date of this review
	 */
	@Override
	public Date getStage5Date() {
		return model.getStage5Date();
	}

	/**
	 * Returns the trainings of this review.
	 *
	 * @return the trainings of this review
	 */
	@Override
	public String getTrainings() {
		return model.getTrainings();
	}

	/**
	 * Returns <code>true</code> if this review is account primary.
	 *
	 * @return <code>true</code> if this review is account primary; <code>false</code> otherwise
	 */
	@Override
	public boolean isAccountPrimary() {
		return model.isAccountPrimary();
	}

	/**
	 * Returns <code>true</code> if this review is assigned.
	 *
	 * @return <code>true</code> if this review is assigned; <code>false</code> otherwise
	 */
	@Override
	public boolean isAssigned() {
		return model.isAssigned();
	}

	/**
	 * Returns <code>true</code> if this review is final year review.
	 *
	 * @return <code>true</code> if this review is final year review; <code>false</code> otherwise
	 */
	@Override
	public boolean isFinalYearReview() {
		return model.isFinalYearReview();
	}

	/**
	 * Returns <code>true</code> if this review is first submit.
	 *
	 * @return <code>true</code> if this review is first submit; <code>false</code> otherwise
	 */
	@Override
	public boolean isFirstSubmit() {
		return model.isFirstSubmit();
	}

	/**
	 * Returns <code>true</code> if this review is promotion recommendation.
	 *
	 * @return <code>true</code> if this review is promotion recommendation; <code>false</code> otherwise
	 */
	@Override
	public boolean isPromotionRecommendation() {
		return model.isPromotionRecommendation();
	}

	/**
	 * Returns <code>true</code> if this review is replacement reason other.
	 *
	 * @return <code>true</code> if this review is replacement reason other; <code>false</code> otherwise
	 */
	@Override
	public boolean isReplacementReasonOther() {
		return model.isReplacementReasonOther();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the account of this review.
	 *
	 * @param account the account of this review
	 */
	@Override
	public void setAccount(String account) {
		model.setAccount(account);
	}

	/**
	 * Sets whether this review is account primary.
	 *
	 * @param accountPrimary the account primary of this review
	 */
	@Override
	public void setAccountPrimary(boolean accountPrimary) {
		model.setAccountPrimary(accountPrimary);
	}

	/**
	 * Sets the achievements of this review.
	 *
	 * @param achievements the achievements of this review
	 */
	@Override
	public void setAchievements(String achievements) {
		model.setAchievements(achievements);
	}

	/**
	 * Sets whether this review is assigned.
	 *
	 * @param assigned the assigned of this review
	 */
	@Override
	public void setAssigned(boolean assigned) {
		model.setAssigned(assigned);
	}

	/**
	 * Sets the assignee of this review.
	 *
	 * @param assignee the assignee of this review
	 */
	@Override
	public void setAssignee(String assignee) {
		model.setAssignee(assignee);
	}

	/**
	 * Sets the comment of this review.
	 *
	 * @param comment the comment of this review
	 */
	@Override
	public void setComment(String comment) {
		model.setComment(comment);
	}

	/**
	 * Sets the critical to account of this review.
	 *
	 * @param criticalToAccount the critical to account of this review
	 */
	@Override
	public void setCriticalToAccount(int criticalToAccount) {
		model.setCriticalToAccount(criticalToAccount);
	}

	/**
	 * Sets the critical to company of this review.
	 *
	 * @param criticalToCompany the critical to company of this review
	 */
	@Override
	public void setCriticalToCompany(int criticalToCompany) {
		model.setCriticalToCompany(criticalToCompany);
	}

	/**
	 * Sets the ecode of this review.
	 *
	 * @param ecode the ecode of this review
	 */
	@Override
	public void setEcode(String ecode) {
		model.setEcode(ecode);
	}

	/**
	 * Sets the employee comment of this review.
	 *
	 * @param employeeComment the employee comment of this review
	 */
	@Override
	public void setEmployeeComment(String employeeComment) {
		model.setEmployeeComment(employeeComment);
	}

	/**
	 * Sets the excelled area of this review.
	 *
	 * @param excelledArea the excelled area of this review
	 */
	@Override
	public void setExcelledArea(String excelledArea) {
		model.setExcelledArea(excelledArea);
	}

	/**
	 * Sets the expected designation of this review.
	 *
	 * @param expectedDesignation the expected designation of this review
	 */
	@Override
	public void setExpectedDesignation(String expectedDesignation) {
		model.setExpectedDesignation(expectedDesignation);
	}

	/**
	 * Sets the expected salary of this review.
	 *
	 * @param expectedSalary the expected salary of this review
	 */
	@Override
	public void setExpectedSalary(String expectedSalary) {
		model.setExpectedSalary(expectedSalary);
	}

	/**
	 * Sets whether this review is final year review.
	 *
	 * @param finalYearReview the final year review of this review
	 */
	@Override
	public void setFinalYearReview(boolean finalYearReview) {
		model.setFinalYearReview(finalYearReview);
	}

	/**
	 * Sets whether this review is first submit.
	 *
	 * @param firstSubmit the first submit of this review
	 */
	@Override
	public void setFirstSubmit(boolean firstSubmit) {
		model.setFirstSubmit(firstSubmit);
	}

	/**
	 * Sets the hr comment of this review.
	 *
	 * @param hrComment the hr comment of this review
	 */
	@Override
	public void setHrComment(String hrComment) {
		model.setHrComment(hrComment);
	}

	/**
	 * Sets the improvement comment of this review.
	 *
	 * @param improvementComment the improvement comment of this review
	 */
	@Override
	public void setImprovementComment(String improvementComment) {
		model.setImprovementComment(improvementComment);
	}

	/**
	 * Sets the kpi ID of this review.
	 *
	 * @param kpiId the kpi ID of this review
	 */
	@Override
	public void setKpiId(long kpiId) {
		model.setKpiId(kpiId);
	}

	/**
	 * Sets the manager of this review.
	 *
	 * @param manager the manager of this review
	 */
	@Override
	public void setManager(String manager) {
		model.setManager(manager);
	}

	/**
	 * Sets the manager comment of this review.
	 *
	 * @param managerComment the manager comment of this review
	 */
	@Override
	public void setManagerComment(String managerComment) {
		model.setManagerComment(managerComment);
	}

	/**
	 * Sets the primary key of this review.
	 *
	 * @param primaryKey the primary key of this review
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets whether this review is promotion recommendation.
	 *
	 * @param promotionRecommendation the promotion recommendation of this review
	 */
	@Override
	public void setPromotionRecommendation(boolean promotionRecommendation) {
		model.setPromotionRecommendation(promotionRecommendation);
	}

	/**
	 * Sets the rating of this review.
	 *
	 * @param rating the rating of this review
	 */
	@Override
	public void setRating(String rating) {
		model.setRating(rating);
	}

	/**
	 * Sets the ready reason of this review.
	 *
	 * @param readyReason the ready reason of this review
	 */
	@Override
	public void setReadyReason(String readyReason) {
		model.setReadyReason(readyReason);
	}

	/**
	 * Sets the reason recommendation of this review.
	 *
	 * @param reasonRecommendation the reason recommendation of this review
	 */
	@Override
	public void setReasonRecommendation(String reasonRecommendation) {
		model.setReasonRecommendation(reasonRecommendation);
	}

	/**
	 * Sets the replacement of this review.
	 *
	 * @param replacement the replacement of this review
	 */
	@Override
	public void setReplacement(int replacement) {
		model.setReplacement(replacement);
	}

	/**
	 * Sets the replacement detail of this review.
	 *
	 * @param replacementDetail the replacement detail of this review
	 */
	@Override
	public void setReplacementDetail(String replacementDetail) {
		model.setReplacementDetail(replacementDetail);
	}

	/**
	 * Sets the replacement reason of this review.
	 *
	 * @param replacementReason the replacement reason of this review
	 */
	@Override
	public void setReplacementReason(String replacementReason) {
		model.setReplacementReason(replacementReason);
	}

	/**
	 * Sets whether this review is replacement reason other.
	 *
	 * @param replacementReasonOther the replacement reason other of this review
	 */
	@Override
	public void setReplacementReasonOther(boolean replacementReasonOther) {
		model.setReplacementReasonOther(replacementReasonOther);
	}

	/**
	 * Sets the review ID of this review.
	 *
	 * @param reviewId the review ID of this review
	 */
	@Override
	public void setReviewId(long reviewId) {
		model.setReviewId(reviewId);
	}

	/**
	 * Sets the review start date of this review.
	 *
	 * @param reviewStartDate the review start date of this review
	 */
	@Override
	public void setReviewStartDate(Date reviewStartDate) {
		model.setReviewStartDate(reviewStartDate);
	}

	/**
	 * Sets the review state of this review.
	 *
	 * @param reviewState the review state of this review
	 */
	@Override
	public void setReviewState(int reviewState) {
		model.setReviewState(reviewState);
	}

	/**
	 * Sets the role played of this review.
	 *
	 * @param rolePlayed the role played of this review
	 */
	@Override
	public void setRolePlayed(String rolePlayed) {
		model.setRolePlayed(rolePlayed);
	}

	/**
	 * Sets the stage1 date of this review.
	 *
	 * @param stage1Date the stage1 date of this review
	 */
	@Override
	public void setStage1Date(Date stage1Date) {
		model.setStage1Date(stage1Date);
	}

	/**
	 * Sets the stage2 date of this review.
	 *
	 * @param stage2Date the stage2 date of this review
	 */
	@Override
	public void setStage2Date(Date stage2Date) {
		model.setStage2Date(stage2Date);
	}

	/**
	 * Sets the stage3 date of this review.
	 *
	 * @param stage3Date the stage3 date of this review
	 */
	@Override
	public void setStage3Date(Date stage3Date) {
		model.setStage3Date(stage3Date);
	}

	/**
	 * Sets the stage4 date of this review.
	 *
	 * @param stage4Date the stage4 date of this review
	 */
	@Override
	public void setStage4Date(Date stage4Date) {
		model.setStage4Date(stage4Date);
	}

	/**
	 * Sets the stage5 date of this review.
	 *
	 * @param stage5Date the stage5 date of this review
	 */
	@Override
	public void setStage5Date(Date stage5Date) {
		model.setStage5Date(stage5Date);
	}

	/**
	 * Sets the trainings of this review.
	 *
	 * @param trainings the trainings of this review
	 */
	@Override
	public void setTrainings(String trainings) {
		model.setTrainings(trainings);
	}

	@Override
	protected ReviewWrapper wrap(Review review) {
		return new ReviewWrapper(review);
	}

}