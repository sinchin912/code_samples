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

package com.trantorinc.synergy.notice.core.model;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link Resignation}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Resignation
 * @generated
 */
public class ResignationWrapper
	extends BaseModelWrapper<Resignation>
	implements ModelWrapper<Resignation>, Resignation {

	public ResignationWrapper(Resignation resignation) {
		super(resignation);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("id", getId());
		attributes.put("ecode", getEcode());
		attributes.put("account", getAccount());
		attributes.put("managerEmail", getManagerEmail());
		attributes.put("assigneeEmail", getAssigneeEmail());
		attributes.put("assigneeTo", isAssigneeTo());
		attributes.put("alternateEmail", getAlternateEmail());
		attributes.put("stateName", getStateName());
		attributes.put("cityName", getCityName());
		attributes.put("pincode", getPincode());
		attributes.put("postalAddress", getPostalAddress());
		attributes.put("reason", getReason());
		attributes.put("lastWorkingDate", getLastWorkingDate());
		attributes.put("managerSubmissionDate", getManagerSubmissionDate());
		attributes.put("hrSubmissionDate", getHrSubmissionDate());
		attributes.put("withdrawInitiatedDate", getWithdrawInitiatedDate());
		attributes.put("abscondTerminateDate", getAbscondTerminateDate());
		attributes.put("separationType", getSeparationType());
		attributes.put("keyToCompany", getKeyToCompany());
		attributes.put("keyToProject", getKeyToProject());
		attributes.put("rating", getRating());
		attributes.put("clientImpact", getClientImpact());
		attributes.put("keyToRetention", getKeyToRetention());
		attributes.put("reasonNonRetention", getReasonNonRetention());
		attributes.put("employeeComment", getEmployeeComment());
		attributes.put("hrComment", getHrComment());
		attributes.put("managerComment", getManagerComment());
		attributes.put("empWithdrawComment", getEmpWithdrawComment());
		attributes.put("hrWithdrawComment", getHrWithdrawComment());
		attributes.put("reasonForLeaving", getReasonForLeaving());
		attributes.put("reasonForLeavingByHr", getReasonForLeavingByHr());
		attributes.put("eligibleForRehire", getEligibleForRehire());
		attributes.put("otherIssue", getOtherIssue());
		attributes.put("replacementPlan", getReplacementPlan());
		attributes.put("replacementDetail", getReplacementDetail());
		attributes.put("status", getStatus());
		attributes.put("terminationType", getTerminationType());
		attributes.put("creationDate", getCreationDate());
		attributes.put("initiatedDate", getInitiatedDate());
		attributes.put("mobile", getMobile());
		attributes.put("noticePeriod", getNoticePeriod());
		attributes.put("itAssetsSubmissionDate", getItAssetsSubmissionDate());
		attributes.put("retainEmployeeDate", getRetainEmployeeDate());
		attributes.put("hasLaptop", getHasLaptop());
		attributes.put("hasMouse", getHasMouse());
		attributes.put("hasCharger", getHasCharger());
		attributes.put("hasHeadSet", getHasHeadSet());
		attributes.put("hasLaptopBag", getHasLaptopBag());
		attributes.put("hasHomeDesktop", getHasHomeDesktop());
		attributes.put("hasHomeMonitor", getHasHomeMonitor());
		attributes.put("otherAssetsList", getOtherAssetsList());
		attributes.put("exitQuestionnaire", isExitQuestionnaire());
		attributes.put("withdrawCount", getWithdrawCount());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long id = (Long)attributes.get("id");

		if (id != null) {
			setId(id);
		}

		String ecode = (String)attributes.get("ecode");

		if (ecode != null) {
			setEcode(ecode);
		}

		String account = (String)attributes.get("account");

		if (account != null) {
			setAccount(account);
		}

		String managerEmail = (String)attributes.get("managerEmail");

		if (managerEmail != null) {
			setManagerEmail(managerEmail);
		}

		String assigneeEmail = (String)attributes.get("assigneeEmail");

		if (assigneeEmail != null) {
			setAssigneeEmail(assigneeEmail);
		}

		Boolean assigneeTo = (Boolean)attributes.get("assigneeTo");

		if (assigneeTo != null) {
			setAssigneeTo(assigneeTo);
		}

		String alternateEmail = (String)attributes.get("alternateEmail");

		if (alternateEmail != null) {
			setAlternateEmail(alternateEmail);
		}

		String stateName = (String)attributes.get("stateName");

		if (stateName != null) {
			setStateName(stateName);
		}

		String cityName = (String)attributes.get("cityName");

		if (cityName != null) {
			setCityName(cityName);
		}

		String pincode = (String)attributes.get("pincode");

		if (pincode != null) {
			setPincode(pincode);
		}

		String postalAddress = (String)attributes.get("postalAddress");

		if (postalAddress != null) {
			setPostalAddress(postalAddress);
		}

		String reason = (String)attributes.get("reason");

		if (reason != null) {
			setReason(reason);
		}

		Date lastWorkingDate = (Date)attributes.get("lastWorkingDate");

		if (lastWorkingDate != null) {
			setLastWorkingDate(lastWorkingDate);
		}

		Date managerSubmissionDate = (Date)attributes.get(
			"managerSubmissionDate");

		if (managerSubmissionDate != null) {
			setManagerSubmissionDate(managerSubmissionDate);
		}

		Date hrSubmissionDate = (Date)attributes.get("hrSubmissionDate");

		if (hrSubmissionDate != null) {
			setHrSubmissionDate(hrSubmissionDate);
		}

		Date withdrawInitiatedDate = (Date)attributes.get(
			"withdrawInitiatedDate");

		if (withdrawInitiatedDate != null) {
			setWithdrawInitiatedDate(withdrawInitiatedDate);
		}

		Date abscondTerminateDate = (Date)attributes.get(
			"abscondTerminateDate");

		if (abscondTerminateDate != null) {
			setAbscondTerminateDate(abscondTerminateDate);
		}

		String separationType = (String)attributes.get("separationType");

		if (separationType != null) {
			setSeparationType(separationType);
		}

		String keyToCompany = (String)attributes.get("keyToCompany");

		if (keyToCompany != null) {
			setKeyToCompany(keyToCompany);
		}

		String keyToProject = (String)attributes.get("keyToProject");

		if (keyToProject != null) {
			setKeyToProject(keyToProject);
		}

		String rating = (String)attributes.get("rating");

		if (rating != null) {
			setRating(rating);
		}

		String clientImpact = (String)attributes.get("clientImpact");

		if (clientImpact != null) {
			setClientImpact(clientImpact);
		}

		String keyToRetention = (String)attributes.get("keyToRetention");

		if (keyToRetention != null) {
			setKeyToRetention(keyToRetention);
		}

		String reasonNonRetention = (String)attributes.get(
			"reasonNonRetention");

		if (reasonNonRetention != null) {
			setReasonNonRetention(reasonNonRetention);
		}

		String employeeComment = (String)attributes.get("employeeComment");

		if (employeeComment != null) {
			setEmployeeComment(employeeComment);
		}

		String hrComment = (String)attributes.get("hrComment");

		if (hrComment != null) {
			setHrComment(hrComment);
		}

		String managerComment = (String)attributes.get("managerComment");

		if (managerComment != null) {
			setManagerComment(managerComment);
		}

		String empWithdrawComment = (String)attributes.get(
			"empWithdrawComment");

		if (empWithdrawComment != null) {
			setEmpWithdrawComment(empWithdrawComment);
		}

		String hrWithdrawComment = (String)attributes.get("hrWithdrawComment");

		if (hrWithdrawComment != null) {
			setHrWithdrawComment(hrWithdrawComment);
		}

		String reasonForLeaving = (String)attributes.get("reasonForLeaving");

		if (reasonForLeaving != null) {
			setReasonForLeaving(reasonForLeaving);
		}

		String reasonForLeavingByHr = (String)attributes.get(
			"reasonForLeavingByHr");

		if (reasonForLeavingByHr != null) {
			setReasonForLeavingByHr(reasonForLeavingByHr);
		}

		String eligibleForRehire = (String)attributes.get("eligibleForRehire");

		if (eligibleForRehire != null) {
			setEligibleForRehire(eligibleForRehire);
		}

		String otherIssue = (String)attributes.get("otherIssue");

		if (otherIssue != null) {
			setOtherIssue(otherIssue);
		}

		String replacementPlan = (String)attributes.get("replacementPlan");

		if (replacementPlan != null) {
			setReplacementPlan(replacementPlan);
		}

		String replacementDetail = (String)attributes.get("replacementDetail");

		if (replacementDetail != null) {
			setReplacementDetail(replacementDetail);
		}

		Integer status = (Integer)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}

		String terminationType = (String)attributes.get("terminationType");

		if (terminationType != null) {
			setTerminationType(terminationType);
		}

		Date creationDate = (Date)attributes.get("creationDate");

		if (creationDate != null) {
			setCreationDate(creationDate);
		}

		Date initiatedDate = (Date)attributes.get("initiatedDate");

		if (initiatedDate != null) {
			setInitiatedDate(initiatedDate);
		}

		String mobile = (String)attributes.get("mobile");

		if (mobile != null) {
			setMobile(mobile);
		}

		String noticePeriod = (String)attributes.get("noticePeriod");

		if (noticePeriod != null) {
			setNoticePeriod(noticePeriod);
		}

		Date itAssetsSubmissionDate = (Date)attributes.get(
			"itAssetsSubmissionDate");

		if (itAssetsSubmissionDate != null) {
			setItAssetsSubmissionDate(itAssetsSubmissionDate);
		}

		Date retainEmployeeDate = (Date)attributes.get("retainEmployeeDate");

		if (retainEmployeeDate != null) {
			setRetainEmployeeDate(retainEmployeeDate);
		}

		String hasLaptop = (String)attributes.get("hasLaptop");

		if (hasLaptop != null) {
			setHasLaptop(hasLaptop);
		}

		String hasMouse = (String)attributes.get("hasMouse");

		if (hasMouse != null) {
			setHasMouse(hasMouse);
		}

		String hasCharger = (String)attributes.get("hasCharger");

		if (hasCharger != null) {
			setHasCharger(hasCharger);
		}

		String hasHeadSet = (String)attributes.get("hasHeadSet");

		if (hasHeadSet != null) {
			setHasHeadSet(hasHeadSet);
		}

		String hasLaptopBag = (String)attributes.get("hasLaptopBag");

		if (hasLaptopBag != null) {
			setHasLaptopBag(hasLaptopBag);
		}

		String hasHomeDesktop = (String)attributes.get("hasHomeDesktop");

		if (hasHomeDesktop != null) {
			setHasHomeDesktop(hasHomeDesktop);
		}

		String hasHomeMonitor = (String)attributes.get("hasHomeMonitor");

		if (hasHomeMonitor != null) {
			setHasHomeMonitor(hasHomeMonitor);
		}

		String otherAssetsList = (String)attributes.get("otherAssetsList");

		if (otherAssetsList != null) {
			setOtherAssetsList(otherAssetsList);
		}

		Boolean exitQuestionnaire = (Boolean)attributes.get(
			"exitQuestionnaire");

		if (exitQuestionnaire != null) {
			setExitQuestionnaire(exitQuestionnaire);
		}

		Integer withdrawCount = (Integer)attributes.get("withdrawCount");

		if (withdrawCount != null) {
			setWithdrawCount(withdrawCount);
		}
	}

	@Override
	public Resignation cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the abscond terminate date of this resignation.
	 *
	 * @return the abscond terminate date of this resignation
	 */
	@Override
	public Date getAbscondTerminateDate() {
		return model.getAbscondTerminateDate();
	}

	/**
	 * Returns the account of this resignation.
	 *
	 * @return the account of this resignation
	 */
	@Override
	public String getAccount() {
		return model.getAccount();
	}

	/**
	 * Returns the alternate email of this resignation.
	 *
	 * @return the alternate email of this resignation
	 */
	@Override
	public String getAlternateEmail() {
		return model.getAlternateEmail();
	}

	/**
	 * Returns the assignee email of this resignation.
	 *
	 * @return the assignee email of this resignation
	 */
	@Override
	public String getAssigneeEmail() {
		return model.getAssigneeEmail();
	}

	/**
	 * Returns the assignee to of this resignation.
	 *
	 * @return the assignee to of this resignation
	 */
	@Override
	public boolean getAssigneeTo() {
		return model.getAssigneeTo();
	}

	/**
	 * Returns the city name of this resignation.
	 *
	 * @return the city name of this resignation
	 */
	@Override
	public String getCityName() {
		return model.getCityName();
	}

	/**
	 * Returns the client impact of this resignation.
	 *
	 * @return the client impact of this resignation
	 */
	@Override
	public String getClientImpact() {
		return model.getClientImpact();
	}

	/**
	 * Returns the creation date of this resignation.
	 *
	 * @return the creation date of this resignation
	 */
	@Override
	public Date getCreationDate() {
		return model.getCreationDate();
	}

	/**
	 * Returns the ecode of this resignation.
	 *
	 * @return the ecode of this resignation
	 */
	@Override
	public String getEcode() {
		return model.getEcode();
	}

	/**
	 * Returns the eligible for rehire of this resignation.
	 *
	 * @return the eligible for rehire of this resignation
	 */
	@Override
	public String getEligibleForRehire() {
		return model.getEligibleForRehire();
	}

	/**
	 * Returns the employee comment of this resignation.
	 *
	 * @return the employee comment of this resignation
	 */
	@Override
	public String getEmployeeComment() {
		return model.getEmployeeComment();
	}

	/**
	 * Returns the emp withdraw comment of this resignation.
	 *
	 * @return the emp withdraw comment of this resignation
	 */
	@Override
	public String getEmpWithdrawComment() {
		return model.getEmpWithdrawComment();
	}

	/**
	 * Returns the exit questionnaire of this resignation.
	 *
	 * @return the exit questionnaire of this resignation
	 */
	@Override
	public boolean getExitQuestionnaire() {
		return model.getExitQuestionnaire();
	}

	/**
	 * Returns the has charger of this resignation.
	 *
	 * @return the has charger of this resignation
	 */
	@Override
	public String getHasCharger() {
		return model.getHasCharger();
	}

	/**
	 * Returns the has head set of this resignation.
	 *
	 * @return the has head set of this resignation
	 */
	@Override
	public String getHasHeadSet() {
		return model.getHasHeadSet();
	}

	/**
	 * Returns the has home desktop of this resignation.
	 *
	 * @return the has home desktop of this resignation
	 */
	@Override
	public String getHasHomeDesktop() {
		return model.getHasHomeDesktop();
	}

	/**
	 * Returns the has home monitor of this resignation.
	 *
	 * @return the has home monitor of this resignation
	 */
	@Override
	public String getHasHomeMonitor() {
		return model.getHasHomeMonitor();
	}

	/**
	 * Returns the has laptop of this resignation.
	 *
	 * @return the has laptop of this resignation
	 */
	@Override
	public String getHasLaptop() {
		return model.getHasLaptop();
	}

	/**
	 * Returns the has laptop bag of this resignation.
	 *
	 * @return the has laptop bag of this resignation
	 */
	@Override
	public String getHasLaptopBag() {
		return model.getHasLaptopBag();
	}

	/**
	 * Returns the has mouse of this resignation.
	 *
	 * @return the has mouse of this resignation
	 */
	@Override
	public String getHasMouse() {
		return model.getHasMouse();
	}

	/**
	 * Returns the hr comment of this resignation.
	 *
	 * @return the hr comment of this resignation
	 */
	@Override
	public String getHrComment() {
		return model.getHrComment();
	}

	/**
	 * Returns the hr submission date of this resignation.
	 *
	 * @return the hr submission date of this resignation
	 */
	@Override
	public Date getHrSubmissionDate() {
		return model.getHrSubmissionDate();
	}

	/**
	 * Returns the hr withdraw comment of this resignation.
	 *
	 * @return the hr withdraw comment of this resignation
	 */
	@Override
	public String getHrWithdrawComment() {
		return model.getHrWithdrawComment();
	}

	/**
	 * Returns the ID of this resignation.
	 *
	 * @return the ID of this resignation
	 */
	@Override
	public long getId() {
		return model.getId();
	}

	/**
	 * Returns the initiated date of this resignation.
	 *
	 * @return the initiated date of this resignation
	 */
	@Override
	public Date getInitiatedDate() {
		return model.getInitiatedDate();
	}

	/**
	 * Returns the it assets submission date of this resignation.
	 *
	 * @return the it assets submission date of this resignation
	 */
	@Override
	public Date getItAssetsSubmissionDate() {
		return model.getItAssetsSubmissionDate();
	}

	/**
	 * Returns the key to company of this resignation.
	 *
	 * @return the key to company of this resignation
	 */
	@Override
	public String getKeyToCompany() {
		return model.getKeyToCompany();
	}

	/**
	 * Returns the key to project of this resignation.
	 *
	 * @return the key to project of this resignation
	 */
	@Override
	public String getKeyToProject() {
		return model.getKeyToProject();
	}

	/**
	 * Returns the key to retention of this resignation.
	 *
	 * @return the key to retention of this resignation
	 */
	@Override
	public String getKeyToRetention() {
		return model.getKeyToRetention();
	}

	/**
	 * Returns the last working date of this resignation.
	 *
	 * @return the last working date of this resignation
	 */
	@Override
	public Date getLastWorkingDate() {
		return model.getLastWorkingDate();
	}

	/**
	 * Returns the manager comment of this resignation.
	 *
	 * @return the manager comment of this resignation
	 */
	@Override
	public String getManagerComment() {
		return model.getManagerComment();
	}

	/**
	 * Returns the manager email of this resignation.
	 *
	 * @return the manager email of this resignation
	 */
	@Override
	public String getManagerEmail() {
		return model.getManagerEmail();
	}

	/**
	 * Returns the manager submission date of this resignation.
	 *
	 * @return the manager submission date of this resignation
	 */
	@Override
	public Date getManagerSubmissionDate() {
		return model.getManagerSubmissionDate();
	}

	/**
	 * Returns the mobile of this resignation.
	 *
	 * @return the mobile of this resignation
	 */
	@Override
	public String getMobile() {
		return model.getMobile();
	}

	/**
	 * Returns the notice period of this resignation.
	 *
	 * @return the notice period of this resignation
	 */
	@Override
	public String getNoticePeriod() {
		return model.getNoticePeriod();
	}

	/**
	 * Returns the other assets list of this resignation.
	 *
	 * @return the other assets list of this resignation
	 */
	@Override
	public String getOtherAssetsList() {
		return model.getOtherAssetsList();
	}

	/**
	 * Returns the other issue of this resignation.
	 *
	 * @return the other issue of this resignation
	 */
	@Override
	public String getOtherIssue() {
		return model.getOtherIssue();
	}

	/**
	 * Returns the pincode of this resignation.
	 *
	 * @return the pincode of this resignation
	 */
	@Override
	public String getPincode() {
		return model.getPincode();
	}

	/**
	 * Returns the postal address of this resignation.
	 *
	 * @return the postal address of this resignation
	 */
	@Override
	public String getPostalAddress() {
		return model.getPostalAddress();
	}

	/**
	 * Returns the primary key of this resignation.
	 *
	 * @return the primary key of this resignation
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the rating of this resignation.
	 *
	 * @return the rating of this resignation
	 */
	@Override
	public String getRating() {
		return model.getRating();
	}

	/**
	 * Returns the reason of this resignation.
	 *
	 * @return the reason of this resignation
	 */
	@Override
	public String getReason() {
		return model.getReason();
	}

	/**
	 * Returns the reason for leaving of this resignation.
	 *
	 * @return the reason for leaving of this resignation
	 */
	@Override
	public String getReasonForLeaving() {
		return model.getReasonForLeaving();
	}

	/**
	 * Returns the reason for leaving by hr of this resignation.
	 *
	 * @return the reason for leaving by hr of this resignation
	 */
	@Override
	public String getReasonForLeavingByHr() {
		return model.getReasonForLeavingByHr();
	}

	/**
	 * Returns the reason non retention of this resignation.
	 *
	 * @return the reason non retention of this resignation
	 */
	@Override
	public String getReasonNonRetention() {
		return model.getReasonNonRetention();
	}

	/**
	 * Returns the replacement detail of this resignation.
	 *
	 * @return the replacement detail of this resignation
	 */
	@Override
	public String getReplacementDetail() {
		return model.getReplacementDetail();
	}

	/**
	 * Returns the replacement plan of this resignation.
	 *
	 * @return the replacement plan of this resignation
	 */
	@Override
	public String getReplacementPlan() {
		return model.getReplacementPlan();
	}

	/**
	 * Returns the retain employee date of this resignation.
	 *
	 * @return the retain employee date of this resignation
	 */
	@Override
	public Date getRetainEmployeeDate() {
		return model.getRetainEmployeeDate();
	}

	/**
	 * Returns the separation type of this resignation.
	 *
	 * @return the separation type of this resignation
	 */
	@Override
	public String getSeparationType() {
		return model.getSeparationType();
	}

	/**
	 * Returns the state name of this resignation.
	 *
	 * @return the state name of this resignation
	 */
	@Override
	public String getStateName() {
		return model.getStateName();
	}

	/**
	 * Returns the status of this resignation.
	 *
	 * @return the status of this resignation
	 */
	@Override
	public int getStatus() {
		return model.getStatus();
	}

	/**
	 * Returns the termination type of this resignation.
	 *
	 * @return the termination type of this resignation
	 */
	@Override
	public String getTerminationType() {
		return model.getTerminationType();
	}

	/**
	 * Returns the withdraw count of this resignation.
	 *
	 * @return the withdraw count of this resignation
	 */
	@Override
	public int getWithdrawCount() {
		return model.getWithdrawCount();
	}

	/**
	 * Returns the withdraw initiated date of this resignation.
	 *
	 * @return the withdraw initiated date of this resignation
	 */
	@Override
	public Date getWithdrawInitiatedDate() {
		return model.getWithdrawInitiatedDate();
	}

	/**
	 * Returns <code>true</code> if this resignation is assignee to.
	 *
	 * @return <code>true</code> if this resignation is assignee to; <code>false</code> otherwise
	 */
	@Override
	public boolean isAssigneeTo() {
		return model.isAssigneeTo();
	}

	/**
	 * Returns <code>true</code> if this resignation is exit questionnaire.
	 *
	 * @return <code>true</code> if this resignation is exit questionnaire; <code>false</code> otherwise
	 */
	@Override
	public boolean isExitQuestionnaire() {
		return model.isExitQuestionnaire();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the abscond terminate date of this resignation.
	 *
	 * @param abscondTerminateDate the abscond terminate date of this resignation
	 */
	@Override
	public void setAbscondTerminateDate(Date abscondTerminateDate) {
		model.setAbscondTerminateDate(abscondTerminateDate);
	}

	/**
	 * Sets the account of this resignation.
	 *
	 * @param account the account of this resignation
	 */
	@Override
	public void setAccount(String account) {
		model.setAccount(account);
	}

	/**
	 * Sets the alternate email of this resignation.
	 *
	 * @param alternateEmail the alternate email of this resignation
	 */
	@Override
	public void setAlternateEmail(String alternateEmail) {
		model.setAlternateEmail(alternateEmail);
	}

	/**
	 * Sets the assignee email of this resignation.
	 *
	 * @param assigneeEmail the assignee email of this resignation
	 */
	@Override
	public void setAssigneeEmail(String assigneeEmail) {
		model.setAssigneeEmail(assigneeEmail);
	}

	/**
	 * Sets whether this resignation is assignee to.
	 *
	 * @param assigneeTo the assignee to of this resignation
	 */
	@Override
	public void setAssigneeTo(boolean assigneeTo) {
		model.setAssigneeTo(assigneeTo);
	}

	/**
	 * Sets the city name of this resignation.
	 *
	 * @param cityName the city name of this resignation
	 */
	@Override
	public void setCityName(String cityName) {
		model.setCityName(cityName);
	}

	/**
	 * Sets the client impact of this resignation.
	 *
	 * @param clientImpact the client impact of this resignation
	 */
	@Override
	public void setClientImpact(String clientImpact) {
		model.setClientImpact(clientImpact);
	}

	/**
	 * Sets the creation date of this resignation.
	 *
	 * @param creationDate the creation date of this resignation
	 */
	@Override
	public void setCreationDate(Date creationDate) {
		model.setCreationDate(creationDate);
	}

	/**
	 * Sets the ecode of this resignation.
	 *
	 * @param ecode the ecode of this resignation
	 */
	@Override
	public void setEcode(String ecode) {
		model.setEcode(ecode);
	}

	/**
	 * Sets the eligible for rehire of this resignation.
	 *
	 * @param eligibleForRehire the eligible for rehire of this resignation
	 */
	@Override
	public void setEligibleForRehire(String eligibleForRehire) {
		model.setEligibleForRehire(eligibleForRehire);
	}

	/**
	 * Sets the employee comment of this resignation.
	 *
	 * @param employeeComment the employee comment of this resignation
	 */
	@Override
	public void setEmployeeComment(String employeeComment) {
		model.setEmployeeComment(employeeComment);
	}

	/**
	 * Sets the emp withdraw comment of this resignation.
	 *
	 * @param empWithdrawComment the emp withdraw comment of this resignation
	 */
	@Override
	public void setEmpWithdrawComment(String empWithdrawComment) {
		model.setEmpWithdrawComment(empWithdrawComment);
	}

	/**
	 * Sets whether this resignation is exit questionnaire.
	 *
	 * @param exitQuestionnaire the exit questionnaire of this resignation
	 */
	@Override
	public void setExitQuestionnaire(boolean exitQuestionnaire) {
		model.setExitQuestionnaire(exitQuestionnaire);
	}

	/**
	 * Sets the has charger of this resignation.
	 *
	 * @param hasCharger the has charger of this resignation
	 */
	@Override
	public void setHasCharger(String hasCharger) {
		model.setHasCharger(hasCharger);
	}

	/**
	 * Sets the has head set of this resignation.
	 *
	 * @param hasHeadSet the has head set of this resignation
	 */
	@Override
	public void setHasHeadSet(String hasHeadSet) {
		model.setHasHeadSet(hasHeadSet);
	}

	/**
	 * Sets the has home desktop of this resignation.
	 *
	 * @param hasHomeDesktop the has home desktop of this resignation
	 */
	@Override
	public void setHasHomeDesktop(String hasHomeDesktop) {
		model.setHasHomeDesktop(hasHomeDesktop);
	}

	/**
	 * Sets the has home monitor of this resignation.
	 *
	 * @param hasHomeMonitor the has home monitor of this resignation
	 */
	@Override
	public void setHasHomeMonitor(String hasHomeMonitor) {
		model.setHasHomeMonitor(hasHomeMonitor);
	}

	/**
	 * Sets the has laptop of this resignation.
	 *
	 * @param hasLaptop the has laptop of this resignation
	 */
	@Override
	public void setHasLaptop(String hasLaptop) {
		model.setHasLaptop(hasLaptop);
	}

	/**
	 * Sets the has laptop bag of this resignation.
	 *
	 * @param hasLaptopBag the has laptop bag of this resignation
	 */
	@Override
	public void setHasLaptopBag(String hasLaptopBag) {
		model.setHasLaptopBag(hasLaptopBag);
	}

	/**
	 * Sets the has mouse of this resignation.
	 *
	 * @param hasMouse the has mouse of this resignation
	 */
	@Override
	public void setHasMouse(String hasMouse) {
		model.setHasMouse(hasMouse);
	}

	/**
	 * Sets the hr comment of this resignation.
	 *
	 * @param hrComment the hr comment of this resignation
	 */
	@Override
	public void setHrComment(String hrComment) {
		model.setHrComment(hrComment);
	}

	/**
	 * Sets the hr submission date of this resignation.
	 *
	 * @param hrSubmissionDate the hr submission date of this resignation
	 */
	@Override
	public void setHrSubmissionDate(Date hrSubmissionDate) {
		model.setHrSubmissionDate(hrSubmissionDate);
	}

	/**
	 * Sets the hr withdraw comment of this resignation.
	 *
	 * @param hrWithdrawComment the hr withdraw comment of this resignation
	 */
	@Override
	public void setHrWithdrawComment(String hrWithdrawComment) {
		model.setHrWithdrawComment(hrWithdrawComment);
	}

	/**
	 * Sets the ID of this resignation.
	 *
	 * @param id the ID of this resignation
	 */
	@Override
	public void setId(long id) {
		model.setId(id);
	}

	/**
	 * Sets the initiated date of this resignation.
	 *
	 * @param initiatedDate the initiated date of this resignation
	 */
	@Override
	public void setInitiatedDate(Date initiatedDate) {
		model.setInitiatedDate(initiatedDate);
	}

	/**
	 * Sets the it assets submission date of this resignation.
	 *
	 * @param itAssetsSubmissionDate the it assets submission date of this resignation
	 */
	@Override
	public void setItAssetsSubmissionDate(Date itAssetsSubmissionDate) {
		model.setItAssetsSubmissionDate(itAssetsSubmissionDate);
	}

	/**
	 * Sets the key to company of this resignation.
	 *
	 * @param keyToCompany the key to company of this resignation
	 */
	@Override
	public void setKeyToCompany(String keyToCompany) {
		model.setKeyToCompany(keyToCompany);
	}

	/**
	 * Sets the key to project of this resignation.
	 *
	 * @param keyToProject the key to project of this resignation
	 */
	@Override
	public void setKeyToProject(String keyToProject) {
		model.setKeyToProject(keyToProject);
	}

	/**
	 * Sets the key to retention of this resignation.
	 *
	 * @param keyToRetention the key to retention of this resignation
	 */
	@Override
	public void setKeyToRetention(String keyToRetention) {
		model.setKeyToRetention(keyToRetention);
	}

	/**
	 * Sets the last working date of this resignation.
	 *
	 * @param lastWorkingDate the last working date of this resignation
	 */
	@Override
	public void setLastWorkingDate(Date lastWorkingDate) {
		model.setLastWorkingDate(lastWorkingDate);
	}

	/**
	 * Sets the manager comment of this resignation.
	 *
	 * @param managerComment the manager comment of this resignation
	 */
	@Override
	public void setManagerComment(String managerComment) {
		model.setManagerComment(managerComment);
	}

	/**
	 * Sets the manager email of this resignation.
	 *
	 * @param managerEmail the manager email of this resignation
	 */
	@Override
	public void setManagerEmail(String managerEmail) {
		model.setManagerEmail(managerEmail);
	}

	/**
	 * Sets the manager submission date of this resignation.
	 *
	 * @param managerSubmissionDate the manager submission date of this resignation
	 */
	@Override
	public void setManagerSubmissionDate(Date managerSubmissionDate) {
		model.setManagerSubmissionDate(managerSubmissionDate);
	}

	/**
	 * Sets the mobile of this resignation.
	 *
	 * @param mobile the mobile of this resignation
	 */
	@Override
	public void setMobile(String mobile) {
		model.setMobile(mobile);
	}

	/**
	 * Sets the notice period of this resignation.
	 *
	 * @param noticePeriod the notice period of this resignation
	 */
	@Override
	public void setNoticePeriod(String noticePeriod) {
		model.setNoticePeriod(noticePeriod);
	}

	/**
	 * Sets the other assets list of this resignation.
	 *
	 * @param otherAssetsList the other assets list of this resignation
	 */
	@Override
	public void setOtherAssetsList(String otherAssetsList) {
		model.setOtherAssetsList(otherAssetsList);
	}

	/**
	 * Sets the other issue of this resignation.
	 *
	 * @param otherIssue the other issue of this resignation
	 */
	@Override
	public void setOtherIssue(String otherIssue) {
		model.setOtherIssue(otherIssue);
	}

	/**
	 * Sets the pincode of this resignation.
	 *
	 * @param pincode the pincode of this resignation
	 */
	@Override
	public void setPincode(String pincode) {
		model.setPincode(pincode);
	}

	/**
	 * Sets the postal address of this resignation.
	 *
	 * @param postalAddress the postal address of this resignation
	 */
	@Override
	public void setPostalAddress(String postalAddress) {
		model.setPostalAddress(postalAddress);
	}

	/**
	 * Sets the primary key of this resignation.
	 *
	 * @param primaryKey the primary key of this resignation
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the rating of this resignation.
	 *
	 * @param rating the rating of this resignation
	 */
	@Override
	public void setRating(String rating) {
		model.setRating(rating);
	}

	/**
	 * Sets the reason of this resignation.
	 *
	 * @param reason the reason of this resignation
	 */
	@Override
	public void setReason(String reason) {
		model.setReason(reason);
	}

	/**
	 * Sets the reason for leaving of this resignation.
	 *
	 * @param reasonForLeaving the reason for leaving of this resignation
	 */
	@Override
	public void setReasonForLeaving(String reasonForLeaving) {
		model.setReasonForLeaving(reasonForLeaving);
	}

	/**
	 * Sets the reason for leaving by hr of this resignation.
	 *
	 * @param reasonForLeavingByHr the reason for leaving by hr of this resignation
	 */
	@Override
	public void setReasonForLeavingByHr(String reasonForLeavingByHr) {
		model.setReasonForLeavingByHr(reasonForLeavingByHr);
	}

	/**
	 * Sets the reason non retention of this resignation.
	 *
	 * @param reasonNonRetention the reason non retention of this resignation
	 */
	@Override
	public void setReasonNonRetention(String reasonNonRetention) {
		model.setReasonNonRetention(reasonNonRetention);
	}

	/**
	 * Sets the replacement detail of this resignation.
	 *
	 * @param replacementDetail the replacement detail of this resignation
	 */
	@Override
	public void setReplacementDetail(String replacementDetail) {
		model.setReplacementDetail(replacementDetail);
	}

	/**
	 * Sets the replacement plan of this resignation.
	 *
	 * @param replacementPlan the replacement plan of this resignation
	 */
	@Override
	public void setReplacementPlan(String replacementPlan) {
		model.setReplacementPlan(replacementPlan);
	}

	/**
	 * Sets the retain employee date of this resignation.
	 *
	 * @param retainEmployeeDate the retain employee date of this resignation
	 */
	@Override
	public void setRetainEmployeeDate(Date retainEmployeeDate) {
		model.setRetainEmployeeDate(retainEmployeeDate);
	}

	/**
	 * Sets the separation type of this resignation.
	 *
	 * @param separationType the separation type of this resignation
	 */
	@Override
	public void setSeparationType(String separationType) {
		model.setSeparationType(separationType);
	}

	/**
	 * Sets the state name of this resignation.
	 *
	 * @param stateName the state name of this resignation
	 */
	@Override
	public void setStateName(String stateName) {
		model.setStateName(stateName);
	}

	/**
	 * Sets the status of this resignation.
	 *
	 * @param status the status of this resignation
	 */
	@Override
	public void setStatus(int status) {
		model.setStatus(status);
	}

	/**
	 * Sets the termination type of this resignation.
	 *
	 * @param terminationType the termination type of this resignation
	 */
	@Override
	public void setTerminationType(String terminationType) {
		model.setTerminationType(terminationType);
	}

	/**
	 * Sets the withdraw count of this resignation.
	 *
	 * @param withdrawCount the withdraw count of this resignation
	 */
	@Override
	public void setWithdrawCount(int withdrawCount) {
		model.setWithdrawCount(withdrawCount);
	}

	/**
	 * Sets the withdraw initiated date of this resignation.
	 *
	 * @param withdrawInitiatedDate the withdraw initiated date of this resignation
	 */
	@Override
	public void setWithdrawInitiatedDate(Date withdrawInitiatedDate) {
		model.setWithdrawInitiatedDate(withdrawInitiatedDate);
	}

	@Override
	protected ResignationWrapper wrap(Resignation resignation) {
		return new ResignationWrapper(resignation);
	}

}