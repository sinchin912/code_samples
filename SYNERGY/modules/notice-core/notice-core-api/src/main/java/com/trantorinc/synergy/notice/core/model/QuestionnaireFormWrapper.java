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
 * This class is a wrapper for {@link QuestionnaireForm}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see QuestionnaireForm
 * @generated
 */
public class QuestionnaireFormWrapper
	extends BaseModelWrapper<QuestionnaireForm>
	implements ModelWrapper<QuestionnaireForm>, QuestionnaireForm {

	public QuestionnaireFormWrapper(QuestionnaireForm questionnaireForm) {
		super(questionnaireForm);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("id", getId());
		attributes.put("resignationId", getResignationId());
		attributes.put("workExperience", getWorkExperience());
		attributes.put(
			"communicationWithEmployees", getCommunicationWithEmployees());
		attributes.put("trainingOpportunity", getTrainingOpportunity());
		attributes.put("dealingWithStaff", getDealingWithStaff());
		attributes.put("satisfactionLevel", getSatisfactionLevel());
		attributes.put("reasonForLeaving", getReasonForLeaving());
		attributes.put(
			"reasonForLeavingDescribe", getReasonForLeavingDescribe());
		attributes.put("reasonForJoining", getReasonForJoining());
		attributes.put("workAgain", getWorkAgain());
		attributes.put("notWorkAgain", getNotWorkAgain());
		attributes.put("recommendTrantor", getRecommendTrantor());
		attributes.put("notRecommendTrantor", getNotRecommendTrantor());
		attributes.put("companyName", getCompanyName());
		attributes.put("companyDetails", getCompanyDetails());
		attributes.put("designation", getDesignation());
		attributes.put("location  ", getLocation());
		attributes.put("salaryHike", getSalaryHike());
		attributes.put("feedback ", getFeedback());
		attributes.put("hrRemark", getHrRemark());
		attributes.put("submittedDate", getSubmittedDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long id = (Long)attributes.get("id");

		if (id != null) {
			setId(id);
		}

		Long resignationId = (Long)attributes.get("resignationId");

		if (resignationId != null) {
			setResignationId(resignationId);
		}

		String workExperience = (String)attributes.get("workExperience");

		if (workExperience != null) {
			setWorkExperience(workExperience);
		}

		String communicationWithEmployees = (String)attributes.get(
			"communicationWithEmployees");

		if (communicationWithEmployees != null) {
			setCommunicationWithEmployees(communicationWithEmployees);
		}

		String trainingOpportunity = (String)attributes.get(
			"trainingOpportunity");

		if (trainingOpportunity != null) {
			setTrainingOpportunity(trainingOpportunity);
		}

		String dealingWithStaff = (String)attributes.get("dealingWithStaff");

		if (dealingWithStaff != null) {
			setDealingWithStaff(dealingWithStaff);
		}

		String satisfactionLevel = (String)attributes.get("satisfactionLevel");

		if (satisfactionLevel != null) {
			setSatisfactionLevel(satisfactionLevel);
		}

		String reasonForLeaving = (String)attributes.get("reasonForLeaving");

		if (reasonForLeaving != null) {
			setReasonForLeaving(reasonForLeaving);
		}

		String reasonForLeavingDescribe = (String)attributes.get(
			"reasonForLeavingDescribe");

		if (reasonForLeavingDescribe != null) {
			setReasonForLeavingDescribe(reasonForLeavingDescribe);
		}

		String reasonForJoining = (String)attributes.get("reasonForJoining");

		if (reasonForJoining != null) {
			setReasonForJoining(reasonForJoining);
		}

		Integer workAgain = (Integer)attributes.get("workAgain");

		if (workAgain != null) {
			setWorkAgain(workAgain);
		}

		String notWorkAgain = (String)attributes.get("notWorkAgain");

		if (notWorkAgain != null) {
			setNotWorkAgain(notWorkAgain);
		}

		Integer recommendTrantor = (Integer)attributes.get("recommendTrantor");

		if (recommendTrantor != null) {
			setRecommendTrantor(recommendTrantor);
		}

		String notRecommendTrantor = (String)attributes.get(
			"notRecommendTrantor");

		if (notRecommendTrantor != null) {
			setNotRecommendTrantor(notRecommendTrantor);
		}

		String companyName = (String)attributes.get("companyName");

		if (companyName != null) {
			setCompanyName(companyName);
		}

		String companyDetails = (String)attributes.get("companyDetails");

		if (companyDetails != null) {
			setCompanyDetails(companyDetails);
		}

		String designation = (String)attributes.get("designation");

		if (designation != null) {
			setDesignation(designation);
		}

		String location = (String)attributes.get("location  ");

		if (location != null) {
			setLocation(location);
		}

		String salaryHike = (String)attributes.get("salaryHike");

		if (salaryHike != null) {
			setSalaryHike(salaryHike);
		}

		String feedback = (String)attributes.get("feedback ");

		if (feedback != null) {
			setFeedback(feedback);
		}

		String hrRemark = (String)attributes.get("hrRemark");

		if (hrRemark != null) {
			setHrRemark(hrRemark);
		}

		Date submittedDate = (Date)attributes.get("submittedDate");

		if (submittedDate != null) {
			setSubmittedDate(submittedDate);
		}
	}

	@Override
	public QuestionnaireForm cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the communication with employees of this questionnaire form.
	 *
	 * @return the communication with employees of this questionnaire form
	 */
	@Override
	public String getCommunicationWithEmployees() {
		return model.getCommunicationWithEmployees();
	}

	/**
	 * Returns the company details of this questionnaire form.
	 *
	 * @return the company details of this questionnaire form
	 */
	@Override
	public String getCompanyDetails() {
		return model.getCompanyDetails();
	}

	/**
	 * Returns the company name of this questionnaire form.
	 *
	 * @return the company name of this questionnaire form
	 */
	@Override
	public String getCompanyName() {
		return model.getCompanyName();
	}

	/**
	 * Returns the dealing with staff of this questionnaire form.
	 *
	 * @return the dealing with staff of this questionnaire form
	 */
	@Override
	public String getDealingWithStaff() {
		return model.getDealingWithStaff();
	}

	/**
	 * Returns the designation of this questionnaire form.
	 *
	 * @return the designation of this questionnaire form
	 */
	@Override
	public String getDesignation() {
		return model.getDesignation();
	}

	/**
	 * Returns the feedback of this questionnaire form.
	 *
	 * @return the feedback of this questionnaire form
	 */
	@Override
	public String getFeedback() {
		return model.getFeedback();
	}

	/**
	 * Returns the hr remark of this questionnaire form.
	 *
	 * @return the hr remark of this questionnaire form
	 */
	@Override
	public String getHrRemark() {
		return model.getHrRemark();
	}

	/**
	 * Returns the ID of this questionnaire form.
	 *
	 * @return the ID of this questionnaire form
	 */
	@Override
	public long getId() {
		return model.getId();
	}

	/**
	 * Returns the location of this questionnaire form.
	 *
	 * @return the location of this questionnaire form
	 */
	@Override
	public String getLocation() {
		return model.getLocation();
	}

	/**
	 * Returns the not recommend trantor of this questionnaire form.
	 *
	 * @return the not recommend trantor of this questionnaire form
	 */
	@Override
	public String getNotRecommendTrantor() {
		return model.getNotRecommendTrantor();
	}

	/**
	 * Returns the not work again of this questionnaire form.
	 *
	 * @return the not work again of this questionnaire form
	 */
	@Override
	public String getNotWorkAgain() {
		return model.getNotWorkAgain();
	}

	/**
	 * Returns the primary key of this questionnaire form.
	 *
	 * @return the primary key of this questionnaire form
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the reason for joining of this questionnaire form.
	 *
	 * @return the reason for joining of this questionnaire form
	 */
	@Override
	public String getReasonForJoining() {
		return model.getReasonForJoining();
	}

	/**
	 * Returns the reason for leaving of this questionnaire form.
	 *
	 * @return the reason for leaving of this questionnaire form
	 */
	@Override
	public String getReasonForLeaving() {
		return model.getReasonForLeaving();
	}

	/**
	 * Returns the reason for leaving describe of this questionnaire form.
	 *
	 * @return the reason for leaving describe of this questionnaire form
	 */
	@Override
	public String getReasonForLeavingDescribe() {
		return model.getReasonForLeavingDescribe();
	}

	/**
	 * Returns the recommend trantor of this questionnaire form.
	 *
	 * @return the recommend trantor of this questionnaire form
	 */
	@Override
	public int getRecommendTrantor() {
		return model.getRecommendTrantor();
	}

	/**
	 * Returns the resignation ID of this questionnaire form.
	 *
	 * @return the resignation ID of this questionnaire form
	 */
	@Override
	public long getResignationId() {
		return model.getResignationId();
	}

	/**
	 * Returns the salary hike of this questionnaire form.
	 *
	 * @return the salary hike of this questionnaire form
	 */
	@Override
	public String getSalaryHike() {
		return model.getSalaryHike();
	}

	/**
	 * Returns the satisfaction level of this questionnaire form.
	 *
	 * @return the satisfaction level of this questionnaire form
	 */
	@Override
	public String getSatisfactionLevel() {
		return model.getSatisfactionLevel();
	}

	/**
	 * Returns the submitted date of this questionnaire form.
	 *
	 * @return the submitted date of this questionnaire form
	 */
	@Override
	public Date getSubmittedDate() {
		return model.getSubmittedDate();
	}

	/**
	 * Returns the training opportunity of this questionnaire form.
	 *
	 * @return the training opportunity of this questionnaire form
	 */
	@Override
	public String getTrainingOpportunity() {
		return model.getTrainingOpportunity();
	}

	/**
	 * Returns the work again of this questionnaire form.
	 *
	 * @return the work again of this questionnaire form
	 */
	@Override
	public int getWorkAgain() {
		return model.getWorkAgain();
	}

	/**
	 * Returns the work experience of this questionnaire form.
	 *
	 * @return the work experience of this questionnaire form
	 */
	@Override
	public String getWorkExperience() {
		return model.getWorkExperience();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the communication with employees of this questionnaire form.
	 *
	 * @param communicationWithEmployees the communication with employees of this questionnaire form
	 */
	@Override
	public void setCommunicationWithEmployees(
		String communicationWithEmployees) {

		model.setCommunicationWithEmployees(communicationWithEmployees);
	}

	/**
	 * Sets the company details of this questionnaire form.
	 *
	 * @param companyDetails the company details of this questionnaire form
	 */
	@Override
	public void setCompanyDetails(String companyDetails) {
		model.setCompanyDetails(companyDetails);
	}

	/**
	 * Sets the company name of this questionnaire form.
	 *
	 * @param companyName the company name of this questionnaire form
	 */
	@Override
	public void setCompanyName(String companyName) {
		model.setCompanyName(companyName);
	}

	/**
	 * Sets the dealing with staff of this questionnaire form.
	 *
	 * @param dealingWithStaff the dealing with staff of this questionnaire form
	 */
	@Override
	public void setDealingWithStaff(String dealingWithStaff) {
		model.setDealingWithStaff(dealingWithStaff);
	}

	/**
	 * Sets the designation of this questionnaire form.
	 *
	 * @param designation the designation of this questionnaire form
	 */
	@Override
	public void setDesignation(String designation) {
		model.setDesignation(designation);
	}

	/**
	 * Sets the feedback of this questionnaire form.
	 *
	 * @param feedback  the feedback of this questionnaire form
	 */
	@Override
	public void setFeedback(String feedback) {
		model.setFeedback(feedback);
	}

	/**
	 * Sets the hr remark of this questionnaire form.
	 *
	 * @param hrRemark the hr remark of this questionnaire form
	 */
	@Override
	public void setHrRemark(String hrRemark) {
		model.setHrRemark(hrRemark);
	}

	/**
	 * Sets the ID of this questionnaire form.
	 *
	 * @param id the ID of this questionnaire form
	 */
	@Override
	public void setId(long id) {
		model.setId(id);
	}

	/**
	 * Sets the location of this questionnaire form.
	 *
	 * @param location   the location of this questionnaire form
	 */
	@Override
	public void setLocation(String location) {
		model.setLocation(location);
	}

	/**
	 * Sets the not recommend trantor of this questionnaire form.
	 *
	 * @param notRecommendTrantor the not recommend trantor of this questionnaire form
	 */
	@Override
	public void setNotRecommendTrantor(String notRecommendTrantor) {
		model.setNotRecommendTrantor(notRecommendTrantor);
	}

	/**
	 * Sets the not work again of this questionnaire form.
	 *
	 * @param notWorkAgain the not work again of this questionnaire form
	 */
	@Override
	public void setNotWorkAgain(String notWorkAgain) {
		model.setNotWorkAgain(notWorkAgain);
	}

	/**
	 * Sets the primary key of this questionnaire form.
	 *
	 * @param primaryKey the primary key of this questionnaire form
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the reason for joining of this questionnaire form.
	 *
	 * @param reasonForJoining the reason for joining of this questionnaire form
	 */
	@Override
	public void setReasonForJoining(String reasonForJoining) {
		model.setReasonForJoining(reasonForJoining);
	}

	/**
	 * Sets the reason for leaving of this questionnaire form.
	 *
	 * @param reasonForLeaving the reason for leaving of this questionnaire form
	 */
	@Override
	public void setReasonForLeaving(String reasonForLeaving) {
		model.setReasonForLeaving(reasonForLeaving);
	}

	/**
	 * Sets the reason for leaving describe of this questionnaire form.
	 *
	 * @param reasonForLeavingDescribe the reason for leaving describe of this questionnaire form
	 */
	@Override
	public void setReasonForLeavingDescribe(String reasonForLeavingDescribe) {
		model.setReasonForLeavingDescribe(reasonForLeavingDescribe);
	}

	/**
	 * Sets the recommend trantor of this questionnaire form.
	 *
	 * @param recommendTrantor the recommend trantor of this questionnaire form
	 */
	@Override
	public void setRecommendTrantor(int recommendTrantor) {
		model.setRecommendTrantor(recommendTrantor);
	}

	/**
	 * Sets the resignation ID of this questionnaire form.
	 *
	 * @param resignationId the resignation ID of this questionnaire form
	 */
	@Override
	public void setResignationId(long resignationId) {
		model.setResignationId(resignationId);
	}

	/**
	 * Sets the salary hike of this questionnaire form.
	 *
	 * @param salaryHike the salary hike of this questionnaire form
	 */
	@Override
	public void setSalaryHike(String salaryHike) {
		model.setSalaryHike(salaryHike);
	}

	/**
	 * Sets the satisfaction level of this questionnaire form.
	 *
	 * @param satisfactionLevel the satisfaction level of this questionnaire form
	 */
	@Override
	public void setSatisfactionLevel(String satisfactionLevel) {
		model.setSatisfactionLevel(satisfactionLevel);
	}

	/**
	 * Sets the submitted date of this questionnaire form.
	 *
	 * @param submittedDate the submitted date of this questionnaire form
	 */
	@Override
	public void setSubmittedDate(Date submittedDate) {
		model.setSubmittedDate(submittedDate);
	}

	/**
	 * Sets the training opportunity of this questionnaire form.
	 *
	 * @param trainingOpportunity the training opportunity of this questionnaire form
	 */
	@Override
	public void setTrainingOpportunity(String trainingOpportunity) {
		model.setTrainingOpportunity(trainingOpportunity);
	}

	/**
	 * Sets the work again of this questionnaire form.
	 *
	 * @param workAgain the work again of this questionnaire form
	 */
	@Override
	public void setWorkAgain(int workAgain) {
		model.setWorkAgain(workAgain);
	}

	/**
	 * Sets the work experience of this questionnaire form.
	 *
	 * @param workExperience the work experience of this questionnaire form
	 */
	@Override
	public void setWorkExperience(String workExperience) {
		model.setWorkExperience(workExperience);
	}

	@Override
	protected QuestionnaireFormWrapper wrap(
		QuestionnaireForm questionnaireForm) {

		return new QuestionnaireFormWrapper(questionnaireForm);
	}

}