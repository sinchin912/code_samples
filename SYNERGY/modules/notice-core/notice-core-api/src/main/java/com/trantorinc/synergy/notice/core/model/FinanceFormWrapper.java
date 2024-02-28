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
 * This class is a wrapper for {@link FinanceForm}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see FinanceForm
 * @generated
 */
public class FinanceFormWrapper
	extends BaseModelWrapper<FinanceForm>
	implements FinanceForm, ModelWrapper<FinanceForm> {

	public FinanceFormWrapper(FinanceForm financeForm) {
		super(financeForm);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("id", getId());
		attributes.put("exitId", getExitId());
		attributes.put("lastSalaryPaidDays", getLastSalaryPaidDays());
		attributes.put("lastSalaryPaidMonth", getLastSalaryPaidMonth());
		attributes.put("lastSalaryPaidYear", getLastSalaryPaidYear());
		attributes.put("taxProofStatus", getTaxProofStatus());
		attributes.put("taxProofRemark", getTaxProofRemark());
		attributes.put("foodReimbursementStatus", getFoodReimbursementStatus());
		attributes.put("foodReimbursementAmt", getFoodReimbursementAmt());
		attributes.put("travelRecoveryAmt", getTravelRecoveryAmt());
		attributes.put("travelRecoveryStatus", getTravelRecoveryStatus());
		attributes.put("travelRecoveryApproved", isTravelRecoveryApproved());
		attributes.put("travelRecoveryComment", getTravelRecoveryComment());
		attributes.put("hotelRecoveryAmt", getHotelRecoveryAmt());
		attributes.put("hotelRecoveryStatus", getHotelRecoveryStatus());
		attributes.put("hotelRecoveryApproved", isHotelRecoveryApproved());
		attributes.put("hotelRecoveryComment", getHotelRecoveryComment());
		attributes.put(
			"internationalRecoveryAmt", getInternationalRecoveryAmt());
		attributes.put(
			"internationalRecoveryStatus", getInternationalRecoveryStatus());
		attributes.put(
			"internationalRecoveryApproved", isInternationalRecoveryApproved());
		attributes.put(
			"internationalRecoveryComment", getInternationalRecoveryComment());
		attributes.put("educationRecoveryAmt", getEducationRecoveryAmt());
		attributes.put("educationRecoveryStatus", getEducationRecoveryStatus());
		attributes.put(
			"educationRecoveryApproved", isEducationRecoveryApproved());
		attributes.put(
			"educationRecoveryComment", getEducationRecoveryComment());
		attributes.put("joiningBonusRecoveryAmt", getJoiningBonusRecoveryAmt());
		attributes.put(
			"joiningBonusRecoveryStatus", getJoiningBonusRecoveryStatus());
		attributes.put("joiningRecoveryApproved", isJoiningRecoveryApproved());
		attributes.put("joiningRecoveryComment", getJoiningRecoveryComment());
		attributes.put("noticePeriodRecoveryAmt", getNoticePeriodRecoveryAmt());
		attributes.put(
			"noticePeriodRecoveryStatus", getNoticePeriodRecoveryStatus());
		attributes.put(
			"noticePeriodRecoveryApproved", isNoticePeriodRecoveryApproved());
		attributes.put(
			"noticePeriodRecoveryComment", getNoticePeriodRecoveryComment());
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

		Long lastSalaryPaidDays = (Long)attributes.get("lastSalaryPaidDays");

		if (lastSalaryPaidDays != null) {
			setLastSalaryPaidDays(lastSalaryPaidDays);
		}

		String lastSalaryPaidMonth = (String)attributes.get(
			"lastSalaryPaidMonth");

		if (lastSalaryPaidMonth != null) {
			setLastSalaryPaidMonth(lastSalaryPaidMonth);
		}

		String lastSalaryPaidYear = (String)attributes.get(
			"lastSalaryPaidYear");

		if (lastSalaryPaidYear != null) {
			setLastSalaryPaidYear(lastSalaryPaidYear);
		}

		Integer taxProofStatus = (Integer)attributes.get("taxProofStatus");

		if (taxProofStatus != null) {
			setTaxProofStatus(taxProofStatus);
		}

		String taxProofRemark = (String)attributes.get("taxProofRemark");

		if (taxProofRemark != null) {
			setTaxProofRemark(taxProofRemark);
		}

		Integer foodReimbursementStatus = (Integer)attributes.get(
			"foodReimbursementStatus");

		if (foodReimbursementStatus != null) {
			setFoodReimbursementStatus(foodReimbursementStatus);
		}

		String foodReimbursementAmt = (String)attributes.get(
			"foodReimbursementAmt");

		if (foodReimbursementAmt != null) {
			setFoodReimbursementAmt(foodReimbursementAmt);
		}

		String travelRecoveryAmt = (String)attributes.get("travelRecoveryAmt");

		if (travelRecoveryAmt != null) {
			setTravelRecoveryAmt(travelRecoveryAmt);
		}

		Integer travelRecoveryStatus = (Integer)attributes.get(
			"travelRecoveryStatus");

		if (travelRecoveryStatus != null) {
			setTravelRecoveryStatus(travelRecoveryStatus);
		}

		Boolean travelRecoveryApproved = (Boolean)attributes.get(
			"travelRecoveryApproved");

		if (travelRecoveryApproved != null) {
			setTravelRecoveryApproved(travelRecoveryApproved);
		}

		String travelRecoveryComment = (String)attributes.get(
			"travelRecoveryComment");

		if (travelRecoveryComment != null) {
			setTravelRecoveryComment(travelRecoveryComment);
		}

		String hotelRecoveryAmt = (String)attributes.get("hotelRecoveryAmt");

		if (hotelRecoveryAmt != null) {
			setHotelRecoveryAmt(hotelRecoveryAmt);
		}

		Integer hotelRecoveryStatus = (Integer)attributes.get(
			"hotelRecoveryStatus");

		if (hotelRecoveryStatus != null) {
			setHotelRecoveryStatus(hotelRecoveryStatus);
		}

		Boolean hotelRecoveryApproved = (Boolean)attributes.get(
			"hotelRecoveryApproved");

		if (hotelRecoveryApproved != null) {
			setHotelRecoveryApproved(hotelRecoveryApproved);
		}

		String hotelRecoveryComment = (String)attributes.get(
			"hotelRecoveryComment");

		if (hotelRecoveryComment != null) {
			setHotelRecoveryComment(hotelRecoveryComment);
		}

		String internationalRecoveryAmt = (String)attributes.get(
			"internationalRecoveryAmt");

		if (internationalRecoveryAmt != null) {
			setInternationalRecoveryAmt(internationalRecoveryAmt);
		}

		Integer internationalRecoveryStatus = (Integer)attributes.get(
			"internationalRecoveryStatus");

		if (internationalRecoveryStatus != null) {
			setInternationalRecoveryStatus(internationalRecoveryStatus);
		}

		Boolean internationalRecoveryApproved = (Boolean)attributes.get(
			"internationalRecoveryApproved");

		if (internationalRecoveryApproved != null) {
			setInternationalRecoveryApproved(internationalRecoveryApproved);
		}

		String internationalRecoveryComment = (String)attributes.get(
			"internationalRecoveryComment");

		if (internationalRecoveryComment != null) {
			setInternationalRecoveryComment(internationalRecoveryComment);
		}

		String educationRecoveryAmt = (String)attributes.get(
			"educationRecoveryAmt");

		if (educationRecoveryAmt != null) {
			setEducationRecoveryAmt(educationRecoveryAmt);
		}

		Integer educationRecoveryStatus = (Integer)attributes.get(
			"educationRecoveryStatus");

		if (educationRecoveryStatus != null) {
			setEducationRecoveryStatus(educationRecoveryStatus);
		}

		Boolean educationRecoveryApproved = (Boolean)attributes.get(
			"educationRecoveryApproved");

		if (educationRecoveryApproved != null) {
			setEducationRecoveryApproved(educationRecoveryApproved);
		}

		String educationRecoveryComment = (String)attributes.get(
			"educationRecoveryComment");

		if (educationRecoveryComment != null) {
			setEducationRecoveryComment(educationRecoveryComment);
		}

		String joiningBonusRecoveryAmt = (String)attributes.get(
			"joiningBonusRecoveryAmt");

		if (joiningBonusRecoveryAmt != null) {
			setJoiningBonusRecoveryAmt(joiningBonusRecoveryAmt);
		}

		Integer joiningBonusRecoveryStatus = (Integer)attributes.get(
			"joiningBonusRecoveryStatus");

		if (joiningBonusRecoveryStatus != null) {
			setJoiningBonusRecoveryStatus(joiningBonusRecoveryStatus);
		}

		Boolean joiningRecoveryApproved = (Boolean)attributes.get(
			"joiningRecoveryApproved");

		if (joiningRecoveryApproved != null) {
			setJoiningRecoveryApproved(joiningRecoveryApproved);
		}

		String joiningRecoveryComment = (String)attributes.get(
			"joiningRecoveryComment");

		if (joiningRecoveryComment != null) {
			setJoiningRecoveryComment(joiningRecoveryComment);
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

		Boolean noticePeriodRecoveryApproved = (Boolean)attributes.get(
			"noticePeriodRecoveryApproved");

		if (noticePeriodRecoveryApproved != null) {
			setNoticePeriodRecoveryApproved(noticePeriodRecoveryApproved);
		}

		String noticePeriodRecoveryComment = (String)attributes.get(
			"noticePeriodRecoveryComment");

		if (noticePeriodRecoveryComment != null) {
			setNoticePeriodRecoveryComment(noticePeriodRecoveryComment);
		}

		Date updatedDate = (Date)attributes.get("updatedDate");

		if (updatedDate != null) {
			setUpdatedDate(updatedDate);
		}
	}

	@Override
	public FinanceForm cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the education recovery amt of this finance form.
	 *
	 * @return the education recovery amt of this finance form
	 */
	@Override
	public String getEducationRecoveryAmt() {
		return model.getEducationRecoveryAmt();
	}

	/**
	 * Returns the education recovery approved of this finance form.
	 *
	 * @return the education recovery approved of this finance form
	 */
	@Override
	public boolean getEducationRecoveryApproved() {
		return model.getEducationRecoveryApproved();
	}

	/**
	 * Returns the education recovery comment of this finance form.
	 *
	 * @return the education recovery comment of this finance form
	 */
	@Override
	public String getEducationRecoveryComment() {
		return model.getEducationRecoveryComment();
	}

	/**
	 * Returns the education recovery status of this finance form.
	 *
	 * @return the education recovery status of this finance form
	 */
	@Override
	public int getEducationRecoveryStatus() {
		return model.getEducationRecoveryStatus();
	}

	/**
	 * Returns the exit ID of this finance form.
	 *
	 * @return the exit ID of this finance form
	 */
	@Override
	public long getExitId() {
		return model.getExitId();
	}

	/**
	 * Returns the food reimbursement amt of this finance form.
	 *
	 * @return the food reimbursement amt of this finance form
	 */
	@Override
	public String getFoodReimbursementAmt() {
		return model.getFoodReimbursementAmt();
	}

	/**
	 * Returns the food reimbursement status of this finance form.
	 *
	 * @return the food reimbursement status of this finance form
	 */
	@Override
	public int getFoodReimbursementStatus() {
		return model.getFoodReimbursementStatus();
	}

	/**
	 * Returns the hotel recovery amt of this finance form.
	 *
	 * @return the hotel recovery amt of this finance form
	 */
	@Override
	public String getHotelRecoveryAmt() {
		return model.getHotelRecoveryAmt();
	}

	/**
	 * Returns the hotel recovery approved of this finance form.
	 *
	 * @return the hotel recovery approved of this finance form
	 */
	@Override
	public boolean getHotelRecoveryApproved() {
		return model.getHotelRecoveryApproved();
	}

	/**
	 * Returns the hotel recovery comment of this finance form.
	 *
	 * @return the hotel recovery comment of this finance form
	 */
	@Override
	public String getHotelRecoveryComment() {
		return model.getHotelRecoveryComment();
	}

	/**
	 * Returns the hotel recovery status of this finance form.
	 *
	 * @return the hotel recovery status of this finance form
	 */
	@Override
	public int getHotelRecoveryStatus() {
		return model.getHotelRecoveryStatus();
	}

	/**
	 * Returns the ID of this finance form.
	 *
	 * @return the ID of this finance form
	 */
	@Override
	public long getId() {
		return model.getId();
	}

	/**
	 * Returns the international recovery amt of this finance form.
	 *
	 * @return the international recovery amt of this finance form
	 */
	@Override
	public String getInternationalRecoveryAmt() {
		return model.getInternationalRecoveryAmt();
	}

	/**
	 * Returns the international recovery approved of this finance form.
	 *
	 * @return the international recovery approved of this finance form
	 */
	@Override
	public boolean getInternationalRecoveryApproved() {
		return model.getInternationalRecoveryApproved();
	}

	/**
	 * Returns the international recovery comment of this finance form.
	 *
	 * @return the international recovery comment of this finance form
	 */
	@Override
	public String getInternationalRecoveryComment() {
		return model.getInternationalRecoveryComment();
	}

	/**
	 * Returns the international recovery status of this finance form.
	 *
	 * @return the international recovery status of this finance form
	 */
	@Override
	public int getInternationalRecoveryStatus() {
		return model.getInternationalRecoveryStatus();
	}

	/**
	 * Returns the joining bonus recovery amt of this finance form.
	 *
	 * @return the joining bonus recovery amt of this finance form
	 */
	@Override
	public String getJoiningBonusRecoveryAmt() {
		return model.getJoiningBonusRecoveryAmt();
	}

	/**
	 * Returns the joining bonus recovery status of this finance form.
	 *
	 * @return the joining bonus recovery status of this finance form
	 */
	@Override
	public int getJoiningBonusRecoveryStatus() {
		return model.getJoiningBonusRecoveryStatus();
	}

	/**
	 * Returns the joining recovery approved of this finance form.
	 *
	 * @return the joining recovery approved of this finance form
	 */
	@Override
	public boolean getJoiningRecoveryApproved() {
		return model.getJoiningRecoveryApproved();
	}

	/**
	 * Returns the joining recovery comment of this finance form.
	 *
	 * @return the joining recovery comment of this finance form
	 */
	@Override
	public String getJoiningRecoveryComment() {
		return model.getJoiningRecoveryComment();
	}

	/**
	 * Returns the last salary paid days of this finance form.
	 *
	 * @return the last salary paid days of this finance form
	 */
	@Override
	public long getLastSalaryPaidDays() {
		return model.getLastSalaryPaidDays();
	}

	/**
	 * Returns the last salary paid month of this finance form.
	 *
	 * @return the last salary paid month of this finance form
	 */
	@Override
	public String getLastSalaryPaidMonth() {
		return model.getLastSalaryPaidMonth();
	}

	/**
	 * Returns the last salary paid year of this finance form.
	 *
	 * @return the last salary paid year of this finance form
	 */
	@Override
	public String getLastSalaryPaidYear() {
		return model.getLastSalaryPaidYear();
	}

	/**
	 * Returns the notice period recovery amt of this finance form.
	 *
	 * @return the notice period recovery amt of this finance form
	 */
	@Override
	public String getNoticePeriodRecoveryAmt() {
		return model.getNoticePeriodRecoveryAmt();
	}

	/**
	 * Returns the notice period recovery approved of this finance form.
	 *
	 * @return the notice period recovery approved of this finance form
	 */
	@Override
	public boolean getNoticePeriodRecoveryApproved() {
		return model.getNoticePeriodRecoveryApproved();
	}

	/**
	 * Returns the notice period recovery comment of this finance form.
	 *
	 * @return the notice period recovery comment of this finance form
	 */
	@Override
	public String getNoticePeriodRecoveryComment() {
		return model.getNoticePeriodRecoveryComment();
	}

	/**
	 * Returns the notice period recovery status of this finance form.
	 *
	 * @return the notice period recovery status of this finance form
	 */
	@Override
	public int getNoticePeriodRecoveryStatus() {
		return model.getNoticePeriodRecoveryStatus();
	}

	/**
	 * Returns the primary key of this finance form.
	 *
	 * @return the primary key of this finance form
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the tax proof remark of this finance form.
	 *
	 * @return the tax proof remark of this finance form
	 */
	@Override
	public String getTaxProofRemark() {
		return model.getTaxProofRemark();
	}

	/**
	 * Returns the tax proof status of this finance form.
	 *
	 * @return the tax proof status of this finance form
	 */
	@Override
	public int getTaxProofStatus() {
		return model.getTaxProofStatus();
	}

	/**
	 * Returns the travel recovery amt of this finance form.
	 *
	 * @return the travel recovery amt of this finance form
	 */
	@Override
	public String getTravelRecoveryAmt() {
		return model.getTravelRecoveryAmt();
	}

	/**
	 * Returns the travel recovery approved of this finance form.
	 *
	 * @return the travel recovery approved of this finance form
	 */
	@Override
	public boolean getTravelRecoveryApproved() {
		return model.getTravelRecoveryApproved();
	}

	/**
	 * Returns the travel recovery comment of this finance form.
	 *
	 * @return the travel recovery comment of this finance form
	 */
	@Override
	public String getTravelRecoveryComment() {
		return model.getTravelRecoveryComment();
	}

	/**
	 * Returns the travel recovery status of this finance form.
	 *
	 * @return the travel recovery status of this finance form
	 */
	@Override
	public int getTravelRecoveryStatus() {
		return model.getTravelRecoveryStatus();
	}

	/**
	 * Returns the updated date of this finance form.
	 *
	 * @return the updated date of this finance form
	 */
	@Override
	public Date getUpdatedDate() {
		return model.getUpdatedDate();
	}

	/**
	 * Returns <code>true</code> if this finance form is education recovery approved.
	 *
	 * @return <code>true</code> if this finance form is education recovery approved; <code>false</code> otherwise
	 */
	@Override
	public boolean isEducationRecoveryApproved() {
		return model.isEducationRecoveryApproved();
	}

	/**
	 * Returns <code>true</code> if this finance form is hotel recovery approved.
	 *
	 * @return <code>true</code> if this finance form is hotel recovery approved; <code>false</code> otherwise
	 */
	@Override
	public boolean isHotelRecoveryApproved() {
		return model.isHotelRecoveryApproved();
	}

	/**
	 * Returns <code>true</code> if this finance form is international recovery approved.
	 *
	 * @return <code>true</code> if this finance form is international recovery approved; <code>false</code> otherwise
	 */
	@Override
	public boolean isInternationalRecoveryApproved() {
		return model.isInternationalRecoveryApproved();
	}

	/**
	 * Returns <code>true</code> if this finance form is joining recovery approved.
	 *
	 * @return <code>true</code> if this finance form is joining recovery approved; <code>false</code> otherwise
	 */
	@Override
	public boolean isJoiningRecoveryApproved() {
		return model.isJoiningRecoveryApproved();
	}

	/**
	 * Returns <code>true</code> if this finance form is notice period recovery approved.
	 *
	 * @return <code>true</code> if this finance form is notice period recovery approved; <code>false</code> otherwise
	 */
	@Override
	public boolean isNoticePeriodRecoveryApproved() {
		return model.isNoticePeriodRecoveryApproved();
	}

	/**
	 * Returns <code>true</code> if this finance form is travel recovery approved.
	 *
	 * @return <code>true</code> if this finance form is travel recovery approved; <code>false</code> otherwise
	 */
	@Override
	public boolean isTravelRecoveryApproved() {
		return model.isTravelRecoveryApproved();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the education recovery amt of this finance form.
	 *
	 * @param educationRecoveryAmt the education recovery amt of this finance form
	 */
	@Override
	public void setEducationRecoveryAmt(String educationRecoveryAmt) {
		model.setEducationRecoveryAmt(educationRecoveryAmt);
	}

	/**
	 * Sets whether this finance form is education recovery approved.
	 *
	 * @param educationRecoveryApproved the education recovery approved of this finance form
	 */
	@Override
	public void setEducationRecoveryApproved(
		boolean educationRecoveryApproved) {

		model.setEducationRecoveryApproved(educationRecoveryApproved);
	}

	/**
	 * Sets the education recovery comment of this finance form.
	 *
	 * @param educationRecoveryComment the education recovery comment of this finance form
	 */
	@Override
	public void setEducationRecoveryComment(String educationRecoveryComment) {
		model.setEducationRecoveryComment(educationRecoveryComment);
	}

	/**
	 * Sets the education recovery status of this finance form.
	 *
	 * @param educationRecoveryStatus the education recovery status of this finance form
	 */
	@Override
	public void setEducationRecoveryStatus(int educationRecoveryStatus) {
		model.setEducationRecoveryStatus(educationRecoveryStatus);
	}

	/**
	 * Sets the exit ID of this finance form.
	 *
	 * @param exitId the exit ID of this finance form
	 */
	@Override
	public void setExitId(long exitId) {
		model.setExitId(exitId);
	}

	/**
	 * Sets the food reimbursement amt of this finance form.
	 *
	 * @param foodReimbursementAmt the food reimbursement amt of this finance form
	 */
	@Override
	public void setFoodReimbursementAmt(String foodReimbursementAmt) {
		model.setFoodReimbursementAmt(foodReimbursementAmt);
	}

	/**
	 * Sets the food reimbursement status of this finance form.
	 *
	 * @param foodReimbursementStatus the food reimbursement status of this finance form
	 */
	@Override
	public void setFoodReimbursementStatus(int foodReimbursementStatus) {
		model.setFoodReimbursementStatus(foodReimbursementStatus);
	}

	/**
	 * Sets the hotel recovery amt of this finance form.
	 *
	 * @param hotelRecoveryAmt the hotel recovery amt of this finance form
	 */
	@Override
	public void setHotelRecoveryAmt(String hotelRecoveryAmt) {
		model.setHotelRecoveryAmt(hotelRecoveryAmt);
	}

	/**
	 * Sets whether this finance form is hotel recovery approved.
	 *
	 * @param hotelRecoveryApproved the hotel recovery approved of this finance form
	 */
	@Override
	public void setHotelRecoveryApproved(boolean hotelRecoveryApproved) {
		model.setHotelRecoveryApproved(hotelRecoveryApproved);
	}

	/**
	 * Sets the hotel recovery comment of this finance form.
	 *
	 * @param hotelRecoveryComment the hotel recovery comment of this finance form
	 */
	@Override
	public void setHotelRecoveryComment(String hotelRecoveryComment) {
		model.setHotelRecoveryComment(hotelRecoveryComment);
	}

	/**
	 * Sets the hotel recovery status of this finance form.
	 *
	 * @param hotelRecoveryStatus the hotel recovery status of this finance form
	 */
	@Override
	public void setHotelRecoveryStatus(int hotelRecoveryStatus) {
		model.setHotelRecoveryStatus(hotelRecoveryStatus);
	}

	/**
	 * Sets the ID of this finance form.
	 *
	 * @param id the ID of this finance form
	 */
	@Override
	public void setId(long id) {
		model.setId(id);
	}

	/**
	 * Sets the international recovery amt of this finance form.
	 *
	 * @param internationalRecoveryAmt the international recovery amt of this finance form
	 */
	@Override
	public void setInternationalRecoveryAmt(String internationalRecoveryAmt) {
		model.setInternationalRecoveryAmt(internationalRecoveryAmt);
	}

	/**
	 * Sets whether this finance form is international recovery approved.
	 *
	 * @param internationalRecoveryApproved the international recovery approved of this finance form
	 */
	@Override
	public void setInternationalRecoveryApproved(
		boolean internationalRecoveryApproved) {

		model.setInternationalRecoveryApproved(internationalRecoveryApproved);
	}

	/**
	 * Sets the international recovery comment of this finance form.
	 *
	 * @param internationalRecoveryComment the international recovery comment of this finance form
	 */
	@Override
	public void setInternationalRecoveryComment(
		String internationalRecoveryComment) {

		model.setInternationalRecoveryComment(internationalRecoveryComment);
	}

	/**
	 * Sets the international recovery status of this finance form.
	 *
	 * @param internationalRecoveryStatus the international recovery status of this finance form
	 */
	@Override
	public void setInternationalRecoveryStatus(
		int internationalRecoveryStatus) {

		model.setInternationalRecoveryStatus(internationalRecoveryStatus);
	}

	/**
	 * Sets the joining bonus recovery amt of this finance form.
	 *
	 * @param joiningBonusRecoveryAmt the joining bonus recovery amt of this finance form
	 */
	@Override
	public void setJoiningBonusRecoveryAmt(String joiningBonusRecoveryAmt) {
		model.setJoiningBonusRecoveryAmt(joiningBonusRecoveryAmt);
	}

	/**
	 * Sets the joining bonus recovery status of this finance form.
	 *
	 * @param joiningBonusRecoveryStatus the joining bonus recovery status of this finance form
	 */
	@Override
	public void setJoiningBonusRecoveryStatus(int joiningBonusRecoveryStatus) {
		model.setJoiningBonusRecoveryStatus(joiningBonusRecoveryStatus);
	}

	/**
	 * Sets whether this finance form is joining recovery approved.
	 *
	 * @param joiningRecoveryApproved the joining recovery approved of this finance form
	 */
	@Override
	public void setJoiningRecoveryApproved(boolean joiningRecoveryApproved) {
		model.setJoiningRecoveryApproved(joiningRecoveryApproved);
	}

	/**
	 * Sets the joining recovery comment of this finance form.
	 *
	 * @param joiningRecoveryComment the joining recovery comment of this finance form
	 */
	@Override
	public void setJoiningRecoveryComment(String joiningRecoveryComment) {
		model.setJoiningRecoveryComment(joiningRecoveryComment);
	}

	/**
	 * Sets the last salary paid days of this finance form.
	 *
	 * @param lastSalaryPaidDays the last salary paid days of this finance form
	 */
	@Override
	public void setLastSalaryPaidDays(long lastSalaryPaidDays) {
		model.setLastSalaryPaidDays(lastSalaryPaidDays);
	}

	/**
	 * Sets the last salary paid month of this finance form.
	 *
	 * @param lastSalaryPaidMonth the last salary paid month of this finance form
	 */
	@Override
	public void setLastSalaryPaidMonth(String lastSalaryPaidMonth) {
		model.setLastSalaryPaidMonth(lastSalaryPaidMonth);
	}

	/**
	 * Sets the last salary paid year of this finance form.
	 *
	 * @param lastSalaryPaidYear the last salary paid year of this finance form
	 */
	@Override
	public void setLastSalaryPaidYear(String lastSalaryPaidYear) {
		model.setLastSalaryPaidYear(lastSalaryPaidYear);
	}

	/**
	 * Sets the notice period recovery amt of this finance form.
	 *
	 * @param noticePeriodRecoveryAmt the notice period recovery amt of this finance form
	 */
	@Override
	public void setNoticePeriodRecoveryAmt(String noticePeriodRecoveryAmt) {
		model.setNoticePeriodRecoveryAmt(noticePeriodRecoveryAmt);
	}

	/**
	 * Sets whether this finance form is notice period recovery approved.
	 *
	 * @param noticePeriodRecoveryApproved the notice period recovery approved of this finance form
	 */
	@Override
	public void setNoticePeriodRecoveryApproved(
		boolean noticePeriodRecoveryApproved) {

		model.setNoticePeriodRecoveryApproved(noticePeriodRecoveryApproved);
	}

	/**
	 * Sets the notice period recovery comment of this finance form.
	 *
	 * @param noticePeriodRecoveryComment the notice period recovery comment of this finance form
	 */
	@Override
	public void setNoticePeriodRecoveryComment(
		String noticePeriodRecoveryComment) {

		model.setNoticePeriodRecoveryComment(noticePeriodRecoveryComment);
	}

	/**
	 * Sets the notice period recovery status of this finance form.
	 *
	 * @param noticePeriodRecoveryStatus the notice period recovery status of this finance form
	 */
	@Override
	public void setNoticePeriodRecoveryStatus(int noticePeriodRecoveryStatus) {
		model.setNoticePeriodRecoveryStatus(noticePeriodRecoveryStatus);
	}

	/**
	 * Sets the primary key of this finance form.
	 *
	 * @param primaryKey the primary key of this finance form
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the tax proof remark of this finance form.
	 *
	 * @param taxProofRemark the tax proof remark of this finance form
	 */
	@Override
	public void setTaxProofRemark(String taxProofRemark) {
		model.setTaxProofRemark(taxProofRemark);
	}

	/**
	 * Sets the tax proof status of this finance form.
	 *
	 * @param taxProofStatus the tax proof status of this finance form
	 */
	@Override
	public void setTaxProofStatus(int taxProofStatus) {
		model.setTaxProofStatus(taxProofStatus);
	}

	/**
	 * Sets the travel recovery amt of this finance form.
	 *
	 * @param travelRecoveryAmt the travel recovery amt of this finance form
	 */
	@Override
	public void setTravelRecoveryAmt(String travelRecoveryAmt) {
		model.setTravelRecoveryAmt(travelRecoveryAmt);
	}

	/**
	 * Sets whether this finance form is travel recovery approved.
	 *
	 * @param travelRecoveryApproved the travel recovery approved of this finance form
	 */
	@Override
	public void setTravelRecoveryApproved(boolean travelRecoveryApproved) {
		model.setTravelRecoveryApproved(travelRecoveryApproved);
	}

	/**
	 * Sets the travel recovery comment of this finance form.
	 *
	 * @param travelRecoveryComment the travel recovery comment of this finance form
	 */
	@Override
	public void setTravelRecoveryComment(String travelRecoveryComment) {
		model.setTravelRecoveryComment(travelRecoveryComment);
	}

	/**
	 * Sets the travel recovery status of this finance form.
	 *
	 * @param travelRecoveryStatus the travel recovery status of this finance form
	 */
	@Override
	public void setTravelRecoveryStatus(int travelRecoveryStatus) {
		model.setTravelRecoveryStatus(travelRecoveryStatus);
	}

	/**
	 * Sets the updated date of this finance form.
	 *
	 * @param updatedDate the updated date of this finance form
	 */
	@Override
	public void setUpdatedDate(Date updatedDate) {
		model.setUpdatedDate(updatedDate);
	}

	@Override
	protected FinanceFormWrapper wrap(FinanceForm financeForm) {
		return new FinanceFormWrapper(financeForm);
	}

}