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

package com.trantorinc.synergy.expense.core.model;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link ExpenseLine}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ExpenseLine
 * @generated
 */
public class ExpenseLineWrapper
	extends BaseModelWrapper<ExpenseLine>
	implements ExpenseLine, ModelWrapper<ExpenseLine> {

	public ExpenseLineWrapper(ExpenseLine expenseLine) {
		super(expenseLine);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("lineId", getLineId());
		attributes.put("expenseId", getExpenseId());
		attributes.put("startDate", getStartDate());
		attributes.put("endDate", getEndDate());
		attributes.put("description", getDescription());
		attributes.put("billAmount", getBillAmount());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long lineId = (Long)attributes.get("lineId");

		if (lineId != null) {
			setLineId(lineId);
		}

		Long expenseId = (Long)attributes.get("expenseId");

		if (expenseId != null) {
			setExpenseId(expenseId);
		}

		Date startDate = (Date)attributes.get("startDate");

		if (startDate != null) {
			setStartDate(startDate);
		}

		Date endDate = (Date)attributes.get("endDate");

		if (endDate != null) {
			setEndDate(endDate);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		Long billAmount = (Long)attributes.get("billAmount");

		if (billAmount != null) {
			setBillAmount(billAmount);
		}
	}

	@Override
	public ExpenseLine cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the bill amount of this expense line.
	 *
	 * @return the bill amount of this expense line
	 */
	@Override
	public long getBillAmount() {
		return model.getBillAmount();
	}

	/**
	 * Returns the description of this expense line.
	 *
	 * @return the description of this expense line
	 */
	@Override
	public String getDescription() {
		return model.getDescription();
	}

	/**
	 * Returns the end date of this expense line.
	 *
	 * @return the end date of this expense line
	 */
	@Override
	public Date getEndDate() {
		return model.getEndDate();
	}

	/**
	 * Returns the expense ID of this expense line.
	 *
	 * @return the expense ID of this expense line
	 */
	@Override
	public long getExpenseId() {
		return model.getExpenseId();
	}

	/**
	 * Returns the line ID of this expense line.
	 *
	 * @return the line ID of this expense line
	 */
	@Override
	public long getLineId() {
		return model.getLineId();
	}

	/**
	 * Returns the primary key of this expense line.
	 *
	 * @return the primary key of this expense line
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the start date of this expense line.
	 *
	 * @return the start date of this expense line
	 */
	@Override
	public Date getStartDate() {
		return model.getStartDate();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the bill amount of this expense line.
	 *
	 * @param billAmount the bill amount of this expense line
	 */
	@Override
	public void setBillAmount(long billAmount) {
		model.setBillAmount(billAmount);
	}

	/**
	 * Sets the description of this expense line.
	 *
	 * @param description the description of this expense line
	 */
	@Override
	public void setDescription(String description) {
		model.setDescription(description);
	}

	/**
	 * Sets the end date of this expense line.
	 *
	 * @param endDate the end date of this expense line
	 */
	@Override
	public void setEndDate(Date endDate) {
		model.setEndDate(endDate);
	}

	/**
	 * Sets the expense ID of this expense line.
	 *
	 * @param expenseId the expense ID of this expense line
	 */
	@Override
	public void setExpenseId(long expenseId) {
		model.setExpenseId(expenseId);
	}

	/**
	 * Sets the line ID of this expense line.
	 *
	 * @param lineId the line ID of this expense line
	 */
	@Override
	public void setLineId(long lineId) {
		model.setLineId(lineId);
	}

	/**
	 * Sets the primary key of this expense line.
	 *
	 * @param primaryKey the primary key of this expense line
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the start date of this expense line.
	 *
	 * @param startDate the start date of this expense line
	 */
	@Override
	public void setStartDate(Date startDate) {
		model.setStartDate(startDate);
	}

	@Override
	protected ExpenseLineWrapper wrap(ExpenseLine expenseLine) {
		return new ExpenseLineWrapper(expenseLine);
	}

}