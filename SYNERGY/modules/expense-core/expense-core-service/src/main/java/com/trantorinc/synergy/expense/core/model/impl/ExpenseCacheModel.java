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

package com.trantorinc.synergy.expense.core.model.impl;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import com.trantorinc.synergy.expense.core.model.Expense;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Expense in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ExpenseCacheModel implements CacheModel<Expense>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof ExpenseCacheModel)) {
			return false;
		}

		ExpenseCacheModel expenseCacheModel = (ExpenseCacheModel)object;

		if (expenseId == expenseCacheModel.expenseId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, expenseId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(27);

		sb.append("{expenseId=");
		sb.append(expenseId);
		sb.append(", ecode=");
		sb.append(ecode);
		sb.append(", expenseType=");
		sb.append(expenseType);
		sb.append(", totalBillAmount=");
		sb.append(totalBillAmount);
		sb.append(", assignee=");
		sb.append(assignee);
		sb.append(", managerComment=");
		sb.append(managerComment);
		sb.append(", financeComment=");
		sb.append(financeComment);
		sb.append(", approvingManager=");
		sb.append(approvingManager);
		sb.append(", status=");
		sb.append(status);
		sb.append(", createdDate=");
		sb.append(createdDate);
		sb.append(", managerDate=");
		sb.append(managerDate);
		sb.append(", financeDate=");
		sb.append(financeDate);
		sb.append(", fileId=");
		sb.append(fileId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Expense toEntityModel() {
		ExpenseImpl expenseImpl = new ExpenseImpl();

		expenseImpl.setExpenseId(expenseId);

		if (ecode == null) {
			expenseImpl.setEcode("");
		}
		else {
			expenseImpl.setEcode(ecode);
		}

		if (expenseType == null) {
			expenseImpl.setExpenseType("");
		}
		else {
			expenseImpl.setExpenseType(expenseType);
		}

		expenseImpl.setTotalBillAmount(totalBillAmount);

		if (assignee == null) {
			expenseImpl.setAssignee("");
		}
		else {
			expenseImpl.setAssignee(assignee);
		}

		if (managerComment == null) {
			expenseImpl.setManagerComment("");
		}
		else {
			expenseImpl.setManagerComment(managerComment);
		}

		if (financeComment == null) {
			expenseImpl.setFinanceComment("");
		}
		else {
			expenseImpl.setFinanceComment(financeComment);
		}

		if (approvingManager == null) {
			expenseImpl.setApprovingManager("");
		}
		else {
			expenseImpl.setApprovingManager(approvingManager);
		}

		expenseImpl.setStatus(status);

		if (createdDate == Long.MIN_VALUE) {
			expenseImpl.setCreatedDate(null);
		}
		else {
			expenseImpl.setCreatedDate(new Date(createdDate));
		}

		if (managerDate == Long.MIN_VALUE) {
			expenseImpl.setManagerDate(null);
		}
		else {
			expenseImpl.setManagerDate(new Date(managerDate));
		}

		if (financeDate == Long.MIN_VALUE) {
			expenseImpl.setFinanceDate(null);
		}
		else {
			expenseImpl.setFinanceDate(new Date(financeDate));
		}

		if (fileId == null) {
			expenseImpl.setFileId("");
		}
		else {
			expenseImpl.setFileId(fileId);
		}

		expenseImpl.resetOriginalValues();

		return expenseImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		expenseId = objectInput.readLong();
		ecode = objectInput.readUTF();
		expenseType = objectInput.readUTF();

		totalBillAmount = objectInput.readLong();
		assignee = objectInput.readUTF();
		managerComment = objectInput.readUTF();
		financeComment = objectInput.readUTF();
		approvingManager = objectInput.readUTF();

		status = objectInput.readInt();
		createdDate = objectInput.readLong();
		managerDate = objectInput.readLong();
		financeDate = objectInput.readLong();
		fileId = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(expenseId);

		if (ecode == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(ecode);
		}

		if (expenseType == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(expenseType);
		}

		objectOutput.writeLong(totalBillAmount);

		if (assignee == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(assignee);
		}

		if (managerComment == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(managerComment);
		}

		if (financeComment == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(financeComment);
		}

		if (approvingManager == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(approvingManager);
		}

		objectOutput.writeInt(status);
		objectOutput.writeLong(createdDate);
		objectOutput.writeLong(managerDate);
		objectOutput.writeLong(financeDate);

		if (fileId == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(fileId);
		}
	}

	public long expenseId;
	public String ecode;
	public String expenseType;
	public long totalBillAmount;
	public String assignee;
	public String managerComment;
	public String financeComment;
	public String approvingManager;
	public int status;
	public long createdDate;
	public long managerDate;
	public long financeDate;
	public String fileId;

}