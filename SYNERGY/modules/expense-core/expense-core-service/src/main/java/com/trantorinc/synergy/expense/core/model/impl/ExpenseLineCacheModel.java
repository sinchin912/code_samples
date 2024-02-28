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

import com.trantorinc.synergy.expense.core.model.ExpenseLine;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing ExpenseLine in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ExpenseLineCacheModel
	implements CacheModel<ExpenseLine>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof ExpenseLineCacheModel)) {
			return false;
		}

		ExpenseLineCacheModel expenseLineCacheModel =
			(ExpenseLineCacheModel)object;

		if (lineId == expenseLineCacheModel.lineId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, lineId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(13);

		sb.append("{lineId=");
		sb.append(lineId);
		sb.append(", expenseId=");
		sb.append(expenseId);
		sb.append(", startDate=");
		sb.append(startDate);
		sb.append(", endDate=");
		sb.append(endDate);
		sb.append(", description=");
		sb.append(description);
		sb.append(", billAmount=");
		sb.append(billAmount);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ExpenseLine toEntityModel() {
		ExpenseLineImpl expenseLineImpl = new ExpenseLineImpl();

		expenseLineImpl.setLineId(lineId);
		expenseLineImpl.setExpenseId(expenseId);

		if (startDate == Long.MIN_VALUE) {
			expenseLineImpl.setStartDate(null);
		}
		else {
			expenseLineImpl.setStartDate(new Date(startDate));
		}

		if (endDate == Long.MIN_VALUE) {
			expenseLineImpl.setEndDate(null);
		}
		else {
			expenseLineImpl.setEndDate(new Date(endDate));
		}

		if (description == null) {
			expenseLineImpl.setDescription("");
		}
		else {
			expenseLineImpl.setDescription(description);
		}

		expenseLineImpl.setBillAmount(billAmount);

		expenseLineImpl.resetOriginalValues();

		return expenseLineImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		lineId = objectInput.readLong();

		expenseId = objectInput.readLong();
		startDate = objectInput.readLong();
		endDate = objectInput.readLong();
		description = objectInput.readUTF();

		billAmount = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(lineId);

		objectOutput.writeLong(expenseId);
		objectOutput.writeLong(startDate);
		objectOutput.writeLong(endDate);

		if (description == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(description);
		}

		objectOutput.writeLong(billAmount);
	}

	public long lineId;
	public long expenseId;
	public long startDate;
	public long endDate;
	public String description;
	public long billAmount;

}