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
 * The table class for the &quot;EXPENSE_ExpenseLine&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see ExpenseLine
 * @generated
 */
public class ExpenseLineTable extends BaseTable<ExpenseLineTable> {

	public static final ExpenseLineTable INSTANCE = new ExpenseLineTable();

	public final Column<ExpenseLineTable, Long> lineId = createColumn(
		"lineId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<ExpenseLineTable, Long> expenseId = createColumn(
		"expenseId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<ExpenseLineTable, Date> startDate = createColumn(
		"startDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<ExpenseLineTable, Date> endDate = createColumn(
		"endDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<ExpenseLineTable, String> description = createColumn(
		"description", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ExpenseLineTable, Long> billAmount = createColumn(
		"billAmount", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);

	private ExpenseLineTable() {
		super("EXPENSE_ExpenseLine", ExpenseLineTable::new);
	}

}