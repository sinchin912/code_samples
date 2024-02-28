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
 * This class is a wrapper for {@link HrForm}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see HrForm
 * @generated
 */
public class HrFormWrapper
	extends BaseModelWrapper<HrForm> implements HrForm, ModelWrapper<HrForm> {

	public HrFormWrapper(HrForm hrForm) {
		super(hrForm);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("id", getId());
		attributes.put("exitId", getExitId());
		attributes.put("foodOption", getFoodOption());
		attributes.put("foodOptionRemark", getFoodOptionRemark());
		attributes.put("inductionFeedbackStatus", getInductionFeedbackStatus());
		attributes.put("inductionFeedbackRemark", getInductionFeedbackRemark());
		attributes.put("inductionQuizStatus", getInductionQuizStatus());
		attributes.put("inductionQuizRemark", getInductionQuizRemark());
		attributes.put("trainingFeedbackStatus", getTrainingFeedbackStatus());
		attributes.put("trainingFeedbackRemark", getTrainingFeedbackRemark());
		attributes.put("exitInterviewStatus", getExitInterviewStatus());
		attributes.put("exitInterviewRemark", getExitInterviewRemark());
		attributes.put("employeeDirectoryStatus", getEmployeeDirectoryStatus());
		attributes.put("employeeDirectoryRemark", getEmployeeDirectoryRemark());
		attributes.put("lmsStatus", getLmsStatus());
		attributes.put("lmsRemark", getLmsRemark());
		attributes.put("vantageCircleStatus", getVantageCircleStatus());
		attributes.put("vantageCircleRemark", getVantageCircleRemark());
		attributes.put("birthdaySynergyStatus", getBirthdaySynergyStatus());
		attributes.put("birthdaySynergyRemark", getBirthdaySynergyRemark());
		attributes.put("experienceLetterStatus", getExperienceLetterStatus());
		attributes.put("experienceLetterRemark", getExperienceLetterRemark());
		attributes.put("ndaFormStatus", getNdaFormStatus());
		attributes.put("ndaFormRemark", getNdaFormRemark());
		attributes.put(
			"separationDocumentStatus", getSeparationDocumentStatus());
		attributes.put(
			"separationDocumentRemark", getSeparationDocumentRemark());
		attributes.put("trainingAgreementAmt", getTrainingAgreementAmt());
		attributes.put("trainingAgreementStatus", getTrainingAgreementStatus());
		attributes.put("recoverableBonusAmt", getRecoverableBonusAmt());
		attributes.put("recoverableBonusStatus", getRecoverableBonusStatus());
		attributes.put("noticePeriodRecoveryAmt", getNoticePeriodRecoveryAmt());
		attributes.put(
			"noticePeriodRecoveryStatus", getNoticePeriodRecoveryStatus());
		attributes.put("leavesMonth1", getLeavesMonth1());
		attributes.put("leaveDaysMonth1", getLeaveDaysMonth1());
		attributes.put("leaveDateMonth1", getLeaveDateMonth1());
		attributes.put("leavesMonth2", getLeavesMonth2());
		attributes.put("leaveDaysMonth2", getLeaveDaysMonth2());
		attributes.put("leaveDateMonth2", getLeaveDateMonth2());
		attributes.put("leavesMonth3", getLeavesMonth3());
		attributes.put("leaveDaysMonth3", getLeaveDaysMonth3());
		attributes.put("leaveDateMonth3", getLeaveDateMonth3());
		attributes.put("lopMonth1", getLopMonth1());
		attributes.put("lopDaysMonth1", getLopDaysMonth1());
		attributes.put("lopDateMonth1", getLopDateMonth1());
		attributes.put("lopMonth2", getLopMonth2());
		attributes.put("lopDaysMonth2", getLopDaysMonth2());
		attributes.put("lopDateMonth2", getLopDateMonth2());
		attributes.put("lopMonth3", getLopMonth3());
		attributes.put("lopDaysMonth3", getLopDaysMonth3());
		attributes.put("lopDateMonth3", getLopDateMonth3());
		attributes.put("earnedLeaveBalance", getEarnedLeaveBalance());
		attributes.put("hrRemark", getHrRemark());
		attributes.put("updatedDate", getUpdatedDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long id = (Long)attributes.get("id");

		if (id != null) {
			setId(id);
		}

		Long exitId = (Long)attributes.get("exitId");

		if (exitId != null) {
			setExitId(exitId);
		}

		Long foodOption = (Long)attributes.get("foodOption");

		if (foodOption != null) {
			setFoodOption(foodOption);
		}

		String foodOptionRemark = (String)attributes.get("foodOptionRemark");

		if (foodOptionRemark != null) {
			setFoodOptionRemark(foodOptionRemark);
		}

		Integer inductionFeedbackStatus = (Integer)attributes.get(
			"inductionFeedbackStatus");

		if (inductionFeedbackStatus != null) {
			setInductionFeedbackStatus(inductionFeedbackStatus);
		}

		String inductionFeedbackRemark = (String)attributes.get(
			"inductionFeedbackRemark");

		if (inductionFeedbackRemark != null) {
			setInductionFeedbackRemark(inductionFeedbackRemark);
		}

		Integer inductionQuizStatus = (Integer)attributes.get(
			"inductionQuizStatus");

		if (inductionQuizStatus != null) {
			setInductionQuizStatus(inductionQuizStatus);
		}

		String inductionQuizRemark = (String)attributes.get(
			"inductionQuizRemark");

		if (inductionQuizRemark != null) {
			setInductionQuizRemark(inductionQuizRemark);
		}

		Integer trainingFeedbackStatus = (Integer)attributes.get(
			"trainingFeedbackStatus");

		if (trainingFeedbackStatus != null) {
			setTrainingFeedbackStatus(trainingFeedbackStatus);
		}

		String trainingFeedbackRemark = (String)attributes.get(
			"trainingFeedbackRemark");

		if (trainingFeedbackRemark != null) {
			setTrainingFeedbackRemark(trainingFeedbackRemark);
		}

		Integer exitInterviewStatus = (Integer)attributes.get(
			"exitInterviewStatus");

		if (exitInterviewStatus != null) {
			setExitInterviewStatus(exitInterviewStatus);
		}

		String exitInterviewRemark = (String)attributes.get(
			"exitInterviewRemark");

		if (exitInterviewRemark != null) {
			setExitInterviewRemark(exitInterviewRemark);
		}

		Integer employeeDirectoryStatus = (Integer)attributes.get(
			"employeeDirectoryStatus");

		if (employeeDirectoryStatus != null) {
			setEmployeeDirectoryStatus(employeeDirectoryStatus);
		}

		String employeeDirectoryRemark = (String)attributes.get(
			"employeeDirectoryRemark");

		if (employeeDirectoryRemark != null) {
			setEmployeeDirectoryRemark(employeeDirectoryRemark);
		}

		Integer lmsStatus = (Integer)attributes.get("lmsStatus");

		if (lmsStatus != null) {
			setLmsStatus(lmsStatus);
		}

		String lmsRemark = (String)attributes.get("lmsRemark");

		if (lmsRemark != null) {
			setLmsRemark(lmsRemark);
		}

		Integer vantageCircleStatus = (Integer)attributes.get(
			"vantageCircleStatus");

		if (vantageCircleStatus != null) {
			setVantageCircleStatus(vantageCircleStatus);
		}

		String vantageCircleRemark = (String)attributes.get(
			"vantageCircleRemark");

		if (vantageCircleRemark != null) {
			setVantageCircleRemark(vantageCircleRemark);
		}

		Integer birthdaySynergyStatus = (Integer)attributes.get(
			"birthdaySynergyStatus");

		if (birthdaySynergyStatus != null) {
			setBirthdaySynergyStatus(birthdaySynergyStatus);
		}

		String birthdaySynergyRemark = (String)attributes.get(
			"birthdaySynergyRemark");

		if (birthdaySynergyRemark != null) {
			setBirthdaySynergyRemark(birthdaySynergyRemark);
		}

		Integer experienceLetterStatus = (Integer)attributes.get(
			"experienceLetterStatus");

		if (experienceLetterStatus != null) {
			setExperienceLetterStatus(experienceLetterStatus);
		}

		String experienceLetterRemark = (String)attributes.get(
			"experienceLetterRemark");

		if (experienceLetterRemark != null) {
			setExperienceLetterRemark(experienceLetterRemark);
		}

		Integer ndaFormStatus = (Integer)attributes.get("ndaFormStatus");

		if (ndaFormStatus != null) {
			setNdaFormStatus(ndaFormStatus);
		}

		String ndaFormRemark = (String)attributes.get("ndaFormRemark");

		if (ndaFormRemark != null) {
			setNdaFormRemark(ndaFormRemark);
		}

		Integer separationDocumentStatus = (Integer)attributes.get(
			"separationDocumentStatus");

		if (separationDocumentStatus != null) {
			setSeparationDocumentStatus(separationDocumentStatus);
		}

		String separationDocumentRemark = (String)attributes.get(
			"separationDocumentRemark");

		if (separationDocumentRemark != null) {
			setSeparationDocumentRemark(separationDocumentRemark);
		}

		String trainingAgreementAmt = (String)attributes.get(
			"trainingAgreementAmt");

		if (trainingAgreementAmt != null) {
			setTrainingAgreementAmt(trainingAgreementAmt);
		}

		Integer trainingAgreementStatus = (Integer)attributes.get(
			"trainingAgreementStatus");

		if (trainingAgreementStatus != null) {
			setTrainingAgreementStatus(trainingAgreementStatus);
		}

		String recoverableBonusAmt = (String)attributes.get(
			"recoverableBonusAmt");

		if (recoverableBonusAmt != null) {
			setRecoverableBonusAmt(recoverableBonusAmt);
		}

		Integer recoverableBonusStatus = (Integer)attributes.get(
			"recoverableBonusStatus");

		if (recoverableBonusStatus != null) {
			setRecoverableBonusStatus(recoverableBonusStatus);
		}

		String noticePeriodRecoveryAmt = (String)attributes.get(
			"noticePeriodRecoveryAmt");

		if (noticePeriodRecoveryAmt != null) {
			setNoticePeriodRecoveryAmt(noticePeriodRecoveryAmt);
		}

		Integer noticePeriodRecoveryStatus = (Integer)attributes.get(
			"noticePeriodRecoveryStatus");

		if (noticePeriodRecoveryStatus != null) {
			setNoticePeriodRecoveryStatus(noticePeriodRecoveryStatus);
		}

		String leavesMonth1 = (String)attributes.get("leavesMonth1");

		if (leavesMonth1 != null) {
			setLeavesMonth1(leavesMonth1);
		}

		String leaveDaysMonth1 = (String)attributes.get("leaveDaysMonth1");

		if (leaveDaysMonth1 != null) {
			setLeaveDaysMonth1(leaveDaysMonth1);
		}

		String leaveDateMonth1 = (String)attributes.get("leaveDateMonth1");

		if (leaveDateMonth1 != null) {
			setLeaveDateMonth1(leaveDateMonth1);
		}

		String leavesMonth2 = (String)attributes.get("leavesMonth2");

		if (leavesMonth2 != null) {
			setLeavesMonth2(leavesMonth2);
		}

		String leaveDaysMonth2 = (String)attributes.get("leaveDaysMonth2");

		if (leaveDaysMonth2 != null) {
			setLeaveDaysMonth2(leaveDaysMonth2);
		}

		String leaveDateMonth2 = (String)attributes.get("leaveDateMonth2");

		if (leaveDateMonth2 != null) {
			setLeaveDateMonth2(leaveDateMonth2);
		}

		String leavesMonth3 = (String)attributes.get("leavesMonth3");

		if (leavesMonth3 != null) {
			setLeavesMonth3(leavesMonth3);
		}

		String leaveDaysMonth3 = (String)attributes.get("leaveDaysMonth3");

		if (leaveDaysMonth3 != null) {
			setLeaveDaysMonth3(leaveDaysMonth3);
		}

		String leaveDateMonth3 = (String)attributes.get("leaveDateMonth3");

		if (leaveDateMonth3 != null) {
			setLeaveDateMonth3(leaveDateMonth3);
		}

		String lopMonth1 = (String)attributes.get("lopMonth1");

		if (lopMonth1 != null) {
			setLopMonth1(lopMonth1);
		}

		String lopDaysMonth1 = (String)attributes.get("lopDaysMonth1");

		if (lopDaysMonth1 != null) {
			setLopDaysMonth1(lopDaysMonth1);
		}

		String lopDateMonth1 = (String)attributes.get("lopDateMonth1");

		if (lopDateMonth1 != null) {
			setLopDateMonth1(lopDateMonth1);
		}

		String lopMonth2 = (String)attributes.get("lopMonth2");

		if (lopMonth2 != null) {
			setLopMonth2(lopMonth2);
		}

		String lopDaysMonth2 = (String)attributes.get("lopDaysMonth2");

		if (lopDaysMonth2 != null) {
			setLopDaysMonth2(lopDaysMonth2);
		}

		String lopDateMonth2 = (String)attributes.get("lopDateMonth2");

		if (lopDateMonth2 != null) {
			setLopDateMonth2(lopDateMonth2);
		}

		String lopMonth3 = (String)attributes.get("lopMonth3");

		if (lopMonth3 != null) {
			setLopMonth3(lopMonth3);
		}

		String lopDaysMonth3 = (String)attributes.get("lopDaysMonth3");

		if (lopDaysMonth3 != null) {
			setLopDaysMonth3(lopDaysMonth3);
		}

		String lopDateMonth3 = (String)attributes.get("lopDateMonth3");

		if (lopDateMonth3 != null) {
			setLopDateMonth3(lopDateMonth3);
		}

		String earnedLeaveBalance = (String)attributes.get(
			"earnedLeaveBalance");

		if (earnedLeaveBalance != null) {
			setEarnedLeaveBalance(earnedLeaveBalance);
		}

		String hrRemark = (String)attributes.get("hrRemark");

		if (hrRemark != null) {
			setHrRemark(hrRemark);
		}

		Date updatedDate = (Date)attributes.get("updatedDate");

		if (updatedDate != null) {
			setUpdatedDate(updatedDate);
		}
	}

	@Override
	public HrForm cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the birthday synergy remark of this hr form.
	 *
	 * @return the birthday synergy remark of this hr form
	 */
	@Override
	public String getBirthdaySynergyRemark() {
		return model.getBirthdaySynergyRemark();
	}

	/**
	 * Returns the birthday synergy status of this hr form.
	 *
	 * @return the birthday synergy status of this hr form
	 */
	@Override
	public int getBirthdaySynergyStatus() {
		return model.getBirthdaySynergyStatus();
	}

	/**
	 * Returns the earned leave balance of this hr form.
	 *
	 * @return the earned leave balance of this hr form
	 */
	@Override
	public String getEarnedLeaveBalance() {
		return model.getEarnedLeaveBalance();
	}

	/**
	 * Returns the employee directory remark of this hr form.
	 *
	 * @return the employee directory remark of this hr form
	 */
	@Override
	public String getEmployeeDirectoryRemark() {
		return model.getEmployeeDirectoryRemark();
	}

	/**
	 * Returns the employee directory status of this hr form.
	 *
	 * @return the employee directory status of this hr form
	 */
	@Override
	public int getEmployeeDirectoryStatus() {
		return model.getEmployeeDirectoryStatus();
	}

	/**
	 * Returns the exit ID of this hr form.
	 *
	 * @return the exit ID of this hr form
	 */
	@Override
	public long getExitId() {
		return model.getExitId();
	}

	/**
	 * Returns the exit interview remark of this hr form.
	 *
	 * @return the exit interview remark of this hr form
	 */
	@Override
	public String getExitInterviewRemark() {
		return model.getExitInterviewRemark();
	}

	/**
	 * Returns the exit interview status of this hr form.
	 *
	 * @return the exit interview status of this hr form
	 */
	@Override
	public int getExitInterviewStatus() {
		return model.getExitInterviewStatus();
	}

	/**
	 * Returns the experience letter remark of this hr form.
	 *
	 * @return the experience letter remark of this hr form
	 */
	@Override
	public String getExperienceLetterRemark() {
		return model.getExperienceLetterRemark();
	}

	/**
	 * Returns the experience letter status of this hr form.
	 *
	 * @return the experience letter status of this hr form
	 */
	@Override
	public int getExperienceLetterStatus() {
		return model.getExperienceLetterStatus();
	}

	/**
	 * Returns the food option of this hr form.
	 *
	 * @return the food option of this hr form
	 */
	@Override
	public long getFoodOption() {
		return model.getFoodOption();
	}

	/**
	 * Returns the food option remark of this hr form.
	 *
	 * @return the food option remark of this hr form
	 */
	@Override
	public String getFoodOptionRemark() {
		return model.getFoodOptionRemark();
	}

	/**
	 * Returns the hr remark of this hr form.
	 *
	 * @return the hr remark of this hr form
	 */
	@Override
	public String getHrRemark() {
		return model.getHrRemark();
	}

	/**
	 * Returns the ID of this hr form.
	 *
	 * @return the ID of this hr form
	 */
	@Override
	public long getId() {
		return model.getId();
	}

	/**
	 * Returns the induction feedback remark of this hr form.
	 *
	 * @return the induction feedback remark of this hr form
	 */
	@Override
	public String getInductionFeedbackRemark() {
		return model.getInductionFeedbackRemark();
	}

	/**
	 * Returns the induction feedback status of this hr form.
	 *
	 * @return the induction feedback status of this hr form
	 */
	@Override
	public int getInductionFeedbackStatus() {
		return model.getInductionFeedbackStatus();
	}

	/**
	 * Returns the induction quiz remark of this hr form.
	 *
	 * @return the induction quiz remark of this hr form
	 */
	@Override
	public String getInductionQuizRemark() {
		return model.getInductionQuizRemark();
	}

	/**
	 * Returns the induction quiz status of this hr form.
	 *
	 * @return the induction quiz status of this hr form
	 */
	@Override
	public int getInductionQuizStatus() {
		return model.getInductionQuizStatus();
	}

	/**
	 * Returns the leave date month1 of this hr form.
	 *
	 * @return the leave date month1 of this hr form
	 */
	@Override
	public String getLeaveDateMonth1() {
		return model.getLeaveDateMonth1();
	}

	/**
	 * Returns the leave date month2 of this hr form.
	 *
	 * @return the leave date month2 of this hr form
	 */
	@Override
	public String getLeaveDateMonth2() {
		return model.getLeaveDateMonth2();
	}

	/**
	 * Returns the leave date month3 of this hr form.
	 *
	 * @return the leave date month3 of this hr form
	 */
	@Override
	public String getLeaveDateMonth3() {
		return model.getLeaveDateMonth3();
	}

	/**
	 * Returns the leave days month1 of this hr form.
	 *
	 * @return the leave days month1 of this hr form
	 */
	@Override
	public String getLeaveDaysMonth1() {
		return model.getLeaveDaysMonth1();
	}

	/**
	 * Returns the leave days month2 of this hr form.
	 *
	 * @return the leave days month2 of this hr form
	 */
	@Override
	public String getLeaveDaysMonth2() {
		return model.getLeaveDaysMonth2();
	}

	/**
	 * Returns the leave days month3 of this hr form.
	 *
	 * @return the leave days month3 of this hr form
	 */
	@Override
	public String getLeaveDaysMonth3() {
		return model.getLeaveDaysMonth3();
	}

	/**
	 * Returns the leaves month1 of this hr form.
	 *
	 * @return the leaves month1 of this hr form
	 */
	@Override
	public String getLeavesMonth1() {
		return model.getLeavesMonth1();
	}

	/**
	 * Returns the leaves month2 of this hr form.
	 *
	 * @return the leaves month2 of this hr form
	 */
	@Override
	public String getLeavesMonth2() {
		return model.getLeavesMonth2();
	}

	/**
	 * Returns the leaves month3 of this hr form.
	 *
	 * @return the leaves month3 of this hr form
	 */
	@Override
	public String getLeavesMonth3() {
		return model.getLeavesMonth3();
	}

	/**
	 * Returns the lms remark of this hr form.
	 *
	 * @return the lms remark of this hr form
	 */
	@Override
	public String getLmsRemark() {
		return model.getLmsRemark();
	}

	/**
	 * Returns the lms status of this hr form.
	 *
	 * @return the lms status of this hr form
	 */
	@Override
	public int getLmsStatus() {
		return model.getLmsStatus();
	}

	/**
	 * Returns the lop date month1 of this hr form.
	 *
	 * @return the lop date month1 of this hr form
	 */
	@Override
	public String getLopDateMonth1() {
		return model.getLopDateMonth1();
	}

	/**
	 * Returns the lop date month2 of this hr form.
	 *
	 * @return the lop date month2 of this hr form
	 */
	@Override
	public String getLopDateMonth2() {
		return model.getLopDateMonth2();
	}

	/**
	 * Returns the lop date month3 of this hr form.
	 *
	 * @return the lop date month3 of this hr form
	 */
	@Override
	public String getLopDateMonth3() {
		return model.getLopDateMonth3();
	}

	/**
	 * Returns the lop days month1 of this hr form.
	 *
	 * @return the lop days month1 of this hr form
	 */
	@Override
	public String getLopDaysMonth1() {
		return model.getLopDaysMonth1();
	}

	/**
	 * Returns the lop days month2 of this hr form.
	 *
	 * @return the lop days month2 of this hr form
	 */
	@Override
	public String getLopDaysMonth2() {
		return model.getLopDaysMonth2();
	}

	/**
	 * Returns the lop days month3 of this hr form.
	 *
	 * @return the lop days month3 of this hr form
	 */
	@Override
	public String getLopDaysMonth3() {
		return model.getLopDaysMonth3();
	}

	/**
	 * Returns the lop month1 of this hr form.
	 *
	 * @return the lop month1 of this hr form
	 */
	@Override
	public String getLopMonth1() {
		return model.getLopMonth1();
	}

	/**
	 * Returns the lop month2 of this hr form.
	 *
	 * @return the lop month2 of this hr form
	 */
	@Override
	public String getLopMonth2() {
		return model.getLopMonth2();
	}

	/**
	 * Returns the lop month3 of this hr form.
	 *
	 * @return the lop month3 of this hr form
	 */
	@Override
	public String getLopMonth3() {
		return model.getLopMonth3();
	}

	/**
	 * Returns the nda form remark of this hr form.
	 *
	 * @return the nda form remark of this hr form
	 */
	@Override
	public String getNdaFormRemark() {
		return model.getNdaFormRemark();
	}

	/**
	 * Returns the nda form status of this hr form.
	 *
	 * @return the nda form status of this hr form
	 */
	@Override
	public int getNdaFormStatus() {
		return model.getNdaFormStatus();
	}

	/**
	 * Returns the notice period recovery amt of this hr form.
	 *
	 * @return the notice period recovery amt of this hr form
	 */
	@Override
	public String getNoticePeriodRecoveryAmt() {
		return model.getNoticePeriodRecoveryAmt();
	}

	/**
	 * Returns the notice period recovery status of this hr form.
	 *
	 * @return the notice period recovery status of this hr form
	 */
	@Override
	public int getNoticePeriodRecoveryStatus() {
		return model.getNoticePeriodRecoveryStatus();
	}

	/**
	 * Returns the primary key of this hr form.
	 *
	 * @return the primary key of this hr form
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the recoverable bonus amt of this hr form.
	 *
	 * @return the recoverable bonus amt of this hr form
	 */
	@Override
	public String getRecoverableBonusAmt() {
		return model.getRecoverableBonusAmt();
	}

	/**
	 * Returns the recoverable bonus status of this hr form.
	 *
	 * @return the recoverable bonus status of this hr form
	 */
	@Override
	public int getRecoverableBonusStatus() {
		return model.getRecoverableBonusStatus();
	}

	/**
	 * Returns the separation document remark of this hr form.
	 *
	 * @return the separation document remark of this hr form
	 */
	@Override
	public String getSeparationDocumentRemark() {
		return model.getSeparationDocumentRemark();
	}

	/**
	 * Returns the separation document status of this hr form.
	 *
	 * @return the separation document status of this hr form
	 */
	@Override
	public int getSeparationDocumentStatus() {
		return model.getSeparationDocumentStatus();
	}

	/**
	 * Returns the training agreement amt of this hr form.
	 *
	 * @return the training agreement amt of this hr form
	 */
	@Override
	public String getTrainingAgreementAmt() {
		return model.getTrainingAgreementAmt();
	}

	/**
	 * Returns the training agreement status of this hr form.
	 *
	 * @return the training agreement status of this hr form
	 */
	@Override
	public int getTrainingAgreementStatus() {
		return model.getTrainingAgreementStatus();
	}

	/**
	 * Returns the training feedback remark of this hr form.
	 *
	 * @return the training feedback remark of this hr form
	 */
	@Override
	public String getTrainingFeedbackRemark() {
		return model.getTrainingFeedbackRemark();
	}

	/**
	 * Returns the training feedback status of this hr form.
	 *
	 * @return the training feedback status of this hr form
	 */
	@Override
	public int getTrainingFeedbackStatus() {
		return model.getTrainingFeedbackStatus();
	}

	/**
	 * Returns the updated date of this hr form.
	 *
	 * @return the updated date of this hr form
	 */
	@Override
	public Date getUpdatedDate() {
		return model.getUpdatedDate();
	}

	/**
	 * Returns the vantage circle remark of this hr form.
	 *
	 * @return the vantage circle remark of this hr form
	 */
	@Override
	public String getVantageCircleRemark() {
		return model.getVantageCircleRemark();
	}

	/**
	 * Returns the vantage circle status of this hr form.
	 *
	 * @return the vantage circle status of this hr form
	 */
	@Override
	public int getVantageCircleStatus() {
		return model.getVantageCircleStatus();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the birthday synergy remark of this hr form.
	 *
	 * @param birthdaySynergyRemark the birthday synergy remark of this hr form
	 */
	@Override
	public void setBirthdaySynergyRemark(String birthdaySynergyRemark) {
		model.setBirthdaySynergyRemark(birthdaySynergyRemark);
	}

	/**
	 * Sets the birthday synergy status of this hr form.
	 *
	 * @param birthdaySynergyStatus the birthday synergy status of this hr form
	 */
	@Override
	public void setBirthdaySynergyStatus(int birthdaySynergyStatus) {
		model.setBirthdaySynergyStatus(birthdaySynergyStatus);
	}

	/**
	 * Sets the earned leave balance of this hr form.
	 *
	 * @param earnedLeaveBalance the earned leave balance of this hr form
	 */
	@Override
	public void setEarnedLeaveBalance(String earnedLeaveBalance) {
		model.setEarnedLeaveBalance(earnedLeaveBalance);
	}

	/**
	 * Sets the employee directory remark of this hr form.
	 *
	 * @param employeeDirectoryRemark the employee directory remark of this hr form
	 */
	@Override
	public void setEmployeeDirectoryRemark(String employeeDirectoryRemark) {
		model.setEmployeeDirectoryRemark(employeeDirectoryRemark);
	}

	/**
	 * Sets the employee directory status of this hr form.
	 *
	 * @param employeeDirectoryStatus the employee directory status of this hr form
	 */
	@Override
	public void setEmployeeDirectoryStatus(int employeeDirectoryStatus) {
		model.setEmployeeDirectoryStatus(employeeDirectoryStatus);
	}

	/**
	 * Sets the exit ID of this hr form.
	 *
	 * @param exitId the exit ID of this hr form
	 */
	@Override
	public void setExitId(long exitId) {
		model.setExitId(exitId);
	}

	/**
	 * Sets the exit interview remark of this hr form.
	 *
	 * @param exitInterviewRemark the exit interview remark of this hr form
	 */
	@Override
	public void setExitInterviewRemark(String exitInterviewRemark) {
		model.setExitInterviewRemark(exitInterviewRemark);
	}

	/**
	 * Sets the exit interview status of this hr form.
	 *
	 * @param exitInterviewStatus the exit interview status of this hr form
	 */
	@Override
	public void setExitInterviewStatus(int exitInterviewStatus) {
		model.setExitInterviewStatus(exitInterviewStatus);
	}

	/**
	 * Sets the experience letter remark of this hr form.
	 *
	 * @param experienceLetterRemark the experience letter remark of this hr form
	 */
	@Override
	public void setExperienceLetterRemark(String experienceLetterRemark) {
		model.setExperienceLetterRemark(experienceLetterRemark);
	}

	/**
	 * Sets the experience letter status of this hr form.
	 *
	 * @param experienceLetterStatus the experience letter status of this hr form
	 */
	@Override
	public void setExperienceLetterStatus(int experienceLetterStatus) {
		model.setExperienceLetterStatus(experienceLetterStatus);
	}

	/**
	 * Sets the food option of this hr form.
	 *
	 * @param foodOption the food option of this hr form
	 */
	@Override
	public void setFoodOption(long foodOption) {
		model.setFoodOption(foodOption);
	}

	/**
	 * Sets the food option remark of this hr form.
	 *
	 * @param foodOptionRemark the food option remark of this hr form
	 */
	@Override
	public void setFoodOptionRemark(String foodOptionRemark) {
		model.setFoodOptionRemark(foodOptionRemark);
	}

	/**
	 * Sets the hr remark of this hr form.
	 *
	 * @param hrRemark the hr remark of this hr form
	 */
	@Override
	public void setHrRemark(String hrRemark) {
		model.setHrRemark(hrRemark);
	}

	/**
	 * Sets the ID of this hr form.
	 *
	 * @param id the ID of this hr form
	 */
	@Override
	public void setId(long id) {
		model.setId(id);
	}

	/**
	 * Sets the induction feedback remark of this hr form.
	 *
	 * @param inductionFeedbackRemark the induction feedback remark of this hr form
	 */
	@Override
	public void setInductionFeedbackRemark(String inductionFeedbackRemark) {
		model.setInductionFeedbackRemark(inductionFeedbackRemark);
	}

	/**
	 * Sets the induction feedback status of this hr form.
	 *
	 * @param inductionFeedbackStatus the induction feedback status of this hr form
	 */
	@Override
	public void setInductionFeedbackStatus(int inductionFeedbackStatus) {
		model.setInductionFeedbackStatus(inductionFeedbackStatus);
	}

	/**
	 * Sets the induction quiz remark of this hr form.
	 *
	 * @param inductionQuizRemark the induction quiz remark of this hr form
	 */
	@Override
	public void setInductionQuizRemark(String inductionQuizRemark) {
		model.setInductionQuizRemark(inductionQuizRemark);
	}

	/**
	 * Sets the induction quiz status of this hr form.
	 *
	 * @param inductionQuizStatus the induction quiz status of this hr form
	 */
	@Override
	public void setInductionQuizStatus(int inductionQuizStatus) {
		model.setInductionQuizStatus(inductionQuizStatus);
	}

	/**
	 * Sets the leave date month1 of this hr form.
	 *
	 * @param leaveDateMonth1 the leave date month1 of this hr form
	 */
	@Override
	public void setLeaveDateMonth1(String leaveDateMonth1) {
		model.setLeaveDateMonth1(leaveDateMonth1);
	}

	/**
	 * Sets the leave date month2 of this hr form.
	 *
	 * @param leaveDateMonth2 the leave date month2 of this hr form
	 */
	@Override
	public void setLeaveDateMonth2(String leaveDateMonth2) {
		model.setLeaveDateMonth2(leaveDateMonth2);
	}

	/**
	 * Sets the leave date month3 of this hr form.
	 *
	 * @param leaveDateMonth3 the leave date month3 of this hr form
	 */
	@Override
	public void setLeaveDateMonth3(String leaveDateMonth3) {
		model.setLeaveDateMonth3(leaveDateMonth3);
	}

	/**
	 * Sets the leave days month1 of this hr form.
	 *
	 * @param leaveDaysMonth1 the leave days month1 of this hr form
	 */
	@Override
	public void setLeaveDaysMonth1(String leaveDaysMonth1) {
		model.setLeaveDaysMonth1(leaveDaysMonth1);
	}

	/**
	 * Sets the leave days month2 of this hr form.
	 *
	 * @param leaveDaysMonth2 the leave days month2 of this hr form
	 */
	@Override
	public void setLeaveDaysMonth2(String leaveDaysMonth2) {
		model.setLeaveDaysMonth2(leaveDaysMonth2);
	}

	/**
	 * Sets the leave days month3 of this hr form.
	 *
	 * @param leaveDaysMonth3 the leave days month3 of this hr form
	 */
	@Override
	public void setLeaveDaysMonth3(String leaveDaysMonth3) {
		model.setLeaveDaysMonth3(leaveDaysMonth3);
	}

	/**
	 * Sets the leaves month1 of this hr form.
	 *
	 * @param leavesMonth1 the leaves month1 of this hr form
	 */
	@Override
	public void setLeavesMonth1(String leavesMonth1) {
		model.setLeavesMonth1(leavesMonth1);
	}

	/**
	 * Sets the leaves month2 of this hr form.
	 *
	 * @param leavesMonth2 the leaves month2 of this hr form
	 */
	@Override
	public void setLeavesMonth2(String leavesMonth2) {
		model.setLeavesMonth2(leavesMonth2);
	}

	/**
	 * Sets the leaves month3 of this hr form.
	 *
	 * @param leavesMonth3 the leaves month3 of this hr form
	 */
	@Override
	public void setLeavesMonth3(String leavesMonth3) {
		model.setLeavesMonth3(leavesMonth3);
	}

	/**
	 * Sets the lms remark of this hr form.
	 *
	 * @param lmsRemark the lms remark of this hr form
	 */
	@Override
	public void setLmsRemark(String lmsRemark) {
		model.setLmsRemark(lmsRemark);
	}

	/**
	 * Sets the lms status of this hr form.
	 *
	 * @param lmsStatus the lms status of this hr form
	 */
	@Override
	public void setLmsStatus(int lmsStatus) {
		model.setLmsStatus(lmsStatus);
	}

	/**
	 * Sets the lop date month1 of this hr form.
	 *
	 * @param lopDateMonth1 the lop date month1 of this hr form
	 */
	@Override
	public void setLopDateMonth1(String lopDateMonth1) {
		model.setLopDateMonth1(lopDateMonth1);
	}

	/**
	 * Sets the lop date month2 of this hr form.
	 *
	 * @param lopDateMonth2 the lop date month2 of this hr form
	 */
	@Override
	public void setLopDateMonth2(String lopDateMonth2) {
		model.setLopDateMonth2(lopDateMonth2);
	}

	/**
	 * Sets the lop date month3 of this hr form.
	 *
	 * @param lopDateMonth3 the lop date month3 of this hr form
	 */
	@Override
	public void setLopDateMonth3(String lopDateMonth3) {
		model.setLopDateMonth3(lopDateMonth3);
	}

	/**
	 * Sets the lop days month1 of this hr form.
	 *
	 * @param lopDaysMonth1 the lop days month1 of this hr form
	 */
	@Override
	public void setLopDaysMonth1(String lopDaysMonth1) {
		model.setLopDaysMonth1(lopDaysMonth1);
	}

	/**
	 * Sets the lop days month2 of this hr form.
	 *
	 * @param lopDaysMonth2 the lop days month2 of this hr form
	 */
	@Override
	public void setLopDaysMonth2(String lopDaysMonth2) {
		model.setLopDaysMonth2(lopDaysMonth2);
	}

	/**
	 * Sets the lop days month3 of this hr form.
	 *
	 * @param lopDaysMonth3 the lop days month3 of this hr form
	 */
	@Override
	public void setLopDaysMonth3(String lopDaysMonth3) {
		model.setLopDaysMonth3(lopDaysMonth3);
	}

	/**
	 * Sets the lop month1 of this hr form.
	 *
	 * @param lopMonth1 the lop month1 of this hr form
	 */
	@Override
	public void setLopMonth1(String lopMonth1) {
		model.setLopMonth1(lopMonth1);
	}

	/**
	 * Sets the lop month2 of this hr form.
	 *
	 * @param lopMonth2 the lop month2 of this hr form
	 */
	@Override
	public void setLopMonth2(String lopMonth2) {
		model.setLopMonth2(lopMonth2);
	}

	/**
	 * Sets the lop month3 of this hr form.
	 *
	 * @param lopMonth3 the lop month3 of this hr form
	 */
	@Override
	public void setLopMonth3(String lopMonth3) {
		model.setLopMonth3(lopMonth3);
	}

	/**
	 * Sets the nda form remark of this hr form.
	 *
	 * @param ndaFormRemark the nda form remark of this hr form
	 */
	@Override
	public void setNdaFormRemark(String ndaFormRemark) {
		model.setNdaFormRemark(ndaFormRemark);
	}

	/**
	 * Sets the nda form status of this hr form.
	 *
	 * @param ndaFormStatus the nda form status of this hr form
	 */
	@Override
	public void setNdaFormStatus(int ndaFormStatus) {
		model.setNdaFormStatus(ndaFormStatus);
	}

	/**
	 * Sets the notice period recovery amt of this hr form.
	 *
	 * @param noticePeriodRecoveryAmt the notice period recovery amt of this hr form
	 */
	@Override
	public void setNoticePeriodRecoveryAmt(String noticePeriodRecoveryAmt) {
		model.setNoticePeriodRecoveryAmt(noticePeriodRecoveryAmt);
	}

	/**
	 * Sets the notice period recovery status of this hr form.
	 *
	 * @param noticePeriodRecoveryStatus the notice period recovery status of this hr form
	 */
	@Override
	public void setNoticePeriodRecoveryStatus(int noticePeriodRecoveryStatus) {
		model.setNoticePeriodRecoveryStatus(noticePeriodRecoveryStatus);
	}

	/**
	 * Sets the primary key of this hr form.
	 *
	 * @param primaryKey the primary key of this hr form
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the recoverable bonus amt of this hr form.
	 *
	 * @param recoverableBonusAmt the recoverable bonus amt of this hr form
	 */
	@Override
	public void setRecoverableBonusAmt(String recoverableBonusAmt) {
		model.setRecoverableBonusAmt(recoverableBonusAmt);
	}

	/**
	 * Sets the recoverable bonus status of this hr form.
	 *
	 * @param recoverableBonusStatus the recoverable bonus status of this hr form
	 */
	@Override
	public void setRecoverableBonusStatus(int recoverableBonusStatus) {
		model.setRecoverableBonusStatus(recoverableBonusStatus);
	}

	/**
	 * Sets the separation document remark of this hr form.
	 *
	 * @param separationDocumentRemark the separation document remark of this hr form
	 */
	@Override
	public void setSeparationDocumentRemark(String separationDocumentRemark) {
		model.setSeparationDocumentRemark(separationDocumentRemark);
	}

	/**
	 * Sets the separation document status of this hr form.
	 *
	 * @param separationDocumentStatus the separation document status of this hr form
	 */
	@Override
	public void setSeparationDocumentStatus(int separationDocumentStatus) {
		model.setSeparationDocumentStatus(separationDocumentStatus);
	}

	/**
	 * Sets the training agreement amt of this hr form.
	 *
	 * @param trainingAgreementAmt the training agreement amt of this hr form
	 */
	@Override
	public void setTrainingAgreementAmt(String trainingAgreementAmt) {
		model.setTrainingAgreementAmt(trainingAgreementAmt);
	}

	/**
	 * Sets the training agreement status of this hr form.
	 *
	 * @param trainingAgreementStatus the training agreement status of this hr form
	 */
	@Override
	public void setTrainingAgreementStatus(int trainingAgreementStatus) {
		model.setTrainingAgreementStatus(trainingAgreementStatus);
	}

	/**
	 * Sets the training feedback remark of this hr form.
	 *
	 * @param trainingFeedbackRemark the training feedback remark of this hr form
	 */
	@Override
	public void setTrainingFeedbackRemark(String trainingFeedbackRemark) {
		model.setTrainingFeedbackRemark(trainingFeedbackRemark);
	}

	/**
	 * Sets the training feedback status of this hr form.
	 *
	 * @param trainingFeedbackStatus the training feedback status of this hr form
	 */
	@Override
	public void setTrainingFeedbackStatus(int trainingFeedbackStatus) {
		model.setTrainingFeedbackStatus(trainingFeedbackStatus);
	}

	/**
	 * Sets the updated date of this hr form.
	 *
	 * @param updatedDate the updated date of this hr form
	 */
	@Override
	public void setUpdatedDate(Date updatedDate) {
		model.setUpdatedDate(updatedDate);
	}

	/**
	 * Sets the vantage circle remark of this hr form.
	 *
	 * @param vantageCircleRemark the vantage circle remark of this hr form
	 */
	@Override
	public void setVantageCircleRemark(String vantageCircleRemark) {
		model.setVantageCircleRemark(vantageCircleRemark);
	}

	/**
	 * Sets the vantage circle status of this hr form.
	 *
	 * @param vantageCircleStatus the vantage circle status of this hr form
	 */
	@Override
	public void setVantageCircleStatus(int vantageCircleStatus) {
		model.setVantageCircleStatus(vantageCircleStatus);
	}

	@Override
	protected HrFormWrapper wrap(HrForm hrForm) {
		return new HrFormWrapper(hrForm);
	}

}