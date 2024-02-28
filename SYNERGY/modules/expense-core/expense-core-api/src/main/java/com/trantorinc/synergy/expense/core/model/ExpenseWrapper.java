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
 * This class is a wrapper for {@link Expense}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Expense
 * @generated
 */
public class ExpenseWrapper
	extends BaseModelWrapper<Expense>
	implements Expense, ModelWrapper<Expense> {

	public ExpenseWrapper(Expense expense) {
		super(expense);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("expenseId", getExpenseId());
		attributes.put("ecode", getEcode());
		attributes.put("expenseType", getExpenseType());
		attributes.put("totalBillAmount", getTotalBillAmount());
		attributes.put("assignee", getAssignee());
		attributes.put("managerComment", getManagerComment());
		attributes.put("financeComment", getFinanceComment());
		attributes.put("approvingManager", getApprovingManager());
		attributes.put("status", getStatus());
		attributes.put("createdDate", getCreatedDate());
		attributes.put("managerDate", getManagerDate());
		attributes.put("financeDate", getFinanceDate());
		attributes.put("fileId", getFileId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long expenseId = (Long)attributes.get("expenseId");

		if (expenseId != null) {
			setExpenseId(expenseId);
		}

		String ecode = (String)attributes.get("ecode");

		if (ecode != null) {
			setEcode(ecode);
		}

		String expenseType = (String)attributes.get("expenseType");

		if (expenseType != null) {
			setExpenseType(expenseType);
		}

		Long totalBillAmount = (Long)attributes.get("totalBillAmount");

		if (totalBillAmount != null) {
			setTotalBillAmount(totalBillAmount);
		}

		String assignee = (String)attributes.get("assignee");

		if (assignee != null) {
			setAssignee(assignee);
		}

		String managerComment = (String)attributes.get("managerComment");

		if (managerComment != null) {
			setManagerComment(managerComment);
		}

		String financeComment = (String)attributes.get("financeComment");

		if (financeComment != null) {
			setFinanceComment(financeComment);
		}

		String approvingManager = (String)attributes.get("approvingManager");

		if (approvingManager != null) {
			setApprovingManager(approvingManager);
		}

		Integer status = (Integer)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}

		Date createdDate = (Date)attributes.get("createdDate");

		if (createdDate != null) {
			setCreatedDate(createdDate);
		}

		Date managerDate = (Date)attributes.get("managerDate");

		if (managerDate != null) {
			setManagerDate(managerDate);
		}

		Date financeDate = (Date)attributes.get("financeDate");

		if (financeDate != null) {
			setFinanceDate(financeDate);
		}

		String fileId = (String)attributes.get("fileId");

		if (fileId != null) {
			setFileId(fileId);
		}
	}

	@Override
	public Expense cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the approving manager of this expense.
	 *
	 * @return the approving manager of this expense
	 */
	@Override
	public String getApprovingManager() {
		return model.getApprovingManager();
	}

	/**
	 * Returns the assignee of this expense.
	 *
	 * @return the assignee of this expense
	 */
	@Override
	public String getAssignee() {
		return model.getAssignee();
	}

	/**
	 * Returns the created date of this expense.
	 *
	 * @return the created date of this expense
	 */
	@Override
	public Date getCreatedDate() {
		return model.getCreatedDate();
	}

	/**
	 * Returns the ecode of this expense.
	 *
	 * @return the ecode of this expense
	 */
	@Override
	public String getEcode() {
		return model.getEcode();
	}

	/**
	 * Returns the expense ID of this expense.
	 *
	 * @return the expense ID of this expense
	 */
	@Override
	public long getExpenseId() {
		return model.getExpenseId();
	}

	/**
	 * Returns the expense type of this expense.
	 *
	 * @return the expense type of this expense
	 */
	@Override
	public String getExpenseType() {
		return model.getExpenseType();
	}

	/**
	 * Returns the file ID of this expense.
	 *
	 * @return the file ID of this expense
	 */
	@Override
	public String getFileId() {
		return model.getFileId();
	}

	/**
	 * Returns the finance comment of this expense.
	 *
	 * @return the finance comment of this expense
	 */
	@Override
	public String getFinanceComment() {
		return model.getFinanceComment();
	}

	/**
	 * Returns the finance date of this expense.
	 *
	 * @return the finance date of this expense
	 */
	@Override
	public Date getFinanceDate() {
		return model.getFinanceDate();
	}

	/**
	 * Returns the manager comment of this expense.
	 *
	 * @return the manager comment of this expense
	 */
	@Override
	public String getManagerComment() {
		return model.getManagerComment();
	}

	/**
	 * Returns the manager date of this expense.
	 *
	 * @return the manager date of this expense
	 */
	@Override
	public Date getManagerDate() {
		return model.getManagerDate();
	}

	/**
	 * Returns the primary key of this expense.
	 *
	 * @return the primary key of this expense
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the status of this expense.
	 *
	 * @return the status of this expense
	 */
	@Override
	public int getStatus() {
		return model.getStatus();
	}

	/**
	 * Returns the total bill amount of this expense.
	 *
	 * @return the total bill amount of this expense
	 */
	@Override
	public long getTotalBillAmount() {
		return model.getTotalBillAmount();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the approving manager of this expense.
	 *
	 * @param approvingManager the approving manager of this expense
	 */
	@Override
	public void setApprovingManager(String approvingManager) {
		model.setApprovingManager(approvingManager);
	}

	/**
	 * Sets the assignee of this expense.
	 *
	 * @param assignee the assignee of this expense
	 */
	@Override
	public void setAssignee(String assignee) {
		model.setAssignee(assignee);
	}

	/**
	 * Sets the created date of this expense.
	 *
	 * @param createdDate the created date of this expense
	 */
	@Override
	public void setCreatedDate(Date createdDate) {
		model.setCreatedDate(createdDate);
	}

	/**
	 * Sets the ecode of this expense.
	 *
	 * @param ecode the ecode of this expense
	 */
	@Override
	public void setEcode(String ecode) {
		model.setEcode(ecode);
	}

	/**
	 * Sets the expense ID of this expense.
	 *
	 * @param expenseId the expense ID of this expense
	 */
	@Override
	public void setExpenseId(long expenseId) {
		model.setExpenseId(expenseId);
	}

	/**
	 * Sets the expense type of this expense.
	 *
	 * @param expenseType the expense type of this expense
	 */
	@Override
	public void setExpenseType(String expenseType) {
		model.setExpenseType(expenseType);
	}

	/**
	 * Sets the file ID of this expense.
	 *
	 * @param fileId the file ID of this expense
	 */
	@Override
	public void setFileId(String fileId) {
		model.setFileId(fileId);
	}

	/**
	 * Sets the finance comment of this expense.
	 *
	 * @param financeComment the finance comment of this expense
	 */
	@Override
	public void setFinanceComment(String financeComment) {
		model.setFinanceComment(financeComment);
	}

	/**
	 * Sets the finance date of this expense.
	 *
	 * @param financeDate the finance date of this expense
	 */
	@Override
	public void setFinanceDate(Date financeDate) {
		model.setFinanceDate(financeDate);
	}

	/**
	 * Sets the manager comment of this expense.
	 *
	 * @param managerComment the manager comment of this expense
	 */
	@Override
	public void setManagerComment(String managerComment) {
		model.setManagerComment(managerComment);
	}

	/**
	 * Sets the manager date of this expense.
	 *
	 * @param managerDate the manager date of this expense
	 */
	@Override
	public void setManagerDate(Date managerDate) {
		model.setManagerDate(managerDate);
	}

	/**
	 * Sets the primary key of this expense.
	 *
	 * @param primaryKey the primary key of this expense
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the status of this expense.
	 *
	 * @param status the status of this expense
	 */
	@Override
	public void setStatus(int status) {
		model.setStatus(status);
	}

	/**
	 * Sets the total bill amount of this expense.
	 *
	 * @param totalBillAmount the total bill amount of this expense
	 */
	@Override
	public void setTotalBillAmount(long totalBillAmount) {
		model.setTotalBillAmount(totalBillAmount);
	}

	@Override
	protected ExpenseWrapper wrap(Expense expense) {
		return new ExpenseWrapper(expense);
	}

}