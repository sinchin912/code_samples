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

import com.liferay.petra.sql.dsl.Column;
import com.liferay.petra.sql.dsl.base.BaseTable;

import java.sql.Types;

import java.util.Date;

/**
 * The table class for the &quot;EXPENSE_Expense&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see Expense
 * @generated
 */
public class ExpenseTable extends BaseTable<ExpenseTable> {

	public static final ExpenseTable INSTANCE = new ExpenseTable();

	public final Column<ExpenseTable, Long> expenseId = createColumn(
		"expenseId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<ExpenseTable, String> ecode = createColumn(
		"ecode", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ExpenseTable, String> expenseType = createColumn(
		"expenseType", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ExpenseTable, Long> totalBillAmount = createColumn(
		"totalBillAmount", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<ExpenseTable, String> assignee = createColumn(
		"assignee", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ExpenseTable, String> managerComment = createColumn(
		"managerComment", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ExpenseTable, String> financeComment = createColumn(
		"financeComment", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ExpenseTable, String> approvingManager = createColumn(
		"approvingManager", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ExpenseTable, Integer> status = createColumn(
		"status", Integer.class, Types.INTEGER, Column.FLAG_DEFAULT);
	public final Column<ExpenseTable, Date> createdDate = createColumn(
		"createdDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<ExpenseTable, Date> managerDate = createColumn(
		"managerDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<ExpenseTable, Date> financeDate = createColumn(
		"financeDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<ExpenseTable, String> fileId = createColumn(
		"fileId", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);

	private ExpenseTable() {
		super("EXPENSE_Expense", ExpenseTable::new);
	}

}