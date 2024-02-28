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

import com.liferay.petra.sql.dsl.Column;
import com.liferay.petra.sql.dsl.base.BaseTable;

import java.sql.Types;

import java.util.Date;

/**
 * The table class for the &quot;NOTICE_FinanceForm&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see FinanceForm
 * @generated
 */
public class FinanceFormTable extends BaseTable<FinanceFormTable> {

	public static final FinanceFormTable INSTANCE = new FinanceFormTable();

	public final Column<FinanceFormTable, Long> id = createColumn(
		"id_", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<FinanceFormTable, Long> exitId = createColumn(
		"exitId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<FinanceFormTable, Long> lastSalaryPaidDays =
		createColumn(
			"lastSalaryPaidDays", Long.class, Types.BIGINT,
			Column.FLAG_DEFAULT);
	public final Column<FinanceFormTable, String> lastSalaryPaidMonth =
		createColumn(
			"lastSalaryPaidMonth", String.class, Types.VARCHAR,
			Column.FLAG_DEFAULT);
	public final Column<FinanceFormTable, String> lastSalaryPaidYear =
		createColumn(
			"lastSalaryPaidYear", String.class, Types.VARCHAR,
			Column.FLAG_DEFAULT);
	public final Column<FinanceFormTable, Integer> taxProofStatus =
		createColumn(
			"taxProofStatus", Integer.class, Types.INTEGER,
			Column.FLAG_DEFAULT);
	public final Column<FinanceFormTable, String> taxProofRemark = createColumn(
		"taxProofRemark", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<FinanceFormTable, Integer> foodReimbursementStatus =
		createColumn(
			"foodReimbursementStatus", Integer.class, Types.INTEGER,
			Column.FLAG_DEFAULT);
	public final Column<FinanceFormTable, String> foodReimbursementAmt =
		createColumn(
			"foodReimbursementAmt", String.class, Types.VARCHAR,
			Column.FLAG_DEFAULT);
	public final Column<FinanceFormTable, String> travelRecoveryAmt =
		createColumn(
			"travelRecoveryAmt", String.class, Types.VARCHAR,
			Column.FLAG_DEFAULT);
	public final Column<FinanceFormTable, Integer> travelRecoveryStatus =
		createColumn(
			"travelRecoveryStatus", Integer.class, Types.INTEGER,
			Column.FLAG_DEFAULT);
	public final Column<FinanceFormTable, Boolean> travelRecoveryApproved =
		createColumn(
			"travelRecoveryApproved", Boolean.class, Types.BOOLEAN,
			Column.FLAG_DEFAULT);
	public final Column<FinanceFormTable, String> travelRecoveryComment =
		createColumn(
			"travelRecoveryComment", String.class, Types.VARCHAR,
			Column.FLAG_DEFAULT);
	public final Column<FinanceFormTable, String> hotelRecoveryAmt =
		createColumn(
			"hotelRecoveryAmt", String.class, Types.VARCHAR,
			Column.FLAG_DEFAULT);
	public final Column<FinanceFormTable, Integer> hotelRecoveryStatus =
		createColumn(
			"hotelRecoveryStatus", Integer.class, Types.INTEGER,
			Column.FLAG_DEFAULT);
	public final Column<FinanceFormTable, Boolean> hotelRecoveryApproved =
		createColumn(
			"hotelRecoveryApproved", Boolean.class, Types.BOOLEAN,
			Column.FLAG_DEFAULT);
	public final Column<FinanceFormTable, String> hotelRecoveryComment =
		createColumn(
			"hotelRecoveryComment", String.class, Types.VARCHAR,
			Column.FLAG_DEFAULT);
	public final Column<FinanceFormTable, String> internationalRecoveryAmt =
		createColumn(
			"internationalRecoveryAmt", String.class, Types.VARCHAR,
			Column.FLAG_DEFAULT);
	public final Column<FinanceFormTable, Integer> internationalRecoveryStatus =
		createColumn(
			"internationalRecoveryStatus", Integer.class, Types.INTEGER,
			Column.FLAG_DEFAULT);
	public final Column<FinanceFormTable, Boolean>
		internationalRecoveryApproved = createColumn(
			"internationalRecoveryApproved", Boolean.class, Types.BOOLEAN,
			Column.FLAG_DEFAULT);
	public final Column<FinanceFormTable, String> internationalRecoveryComment =
		createColumn(
			"internationalRecoveryComment", String.class, Types.VARCHAR,
			Column.FLAG_DEFAULT);
	public final Column<FinanceFormTable, String> educationRecoveryAmt =
		createColumn(
			"educationRecoveryAmt", String.class, Types.VARCHAR,
			Column.FLAG_DEFAULT);
	public final Column<FinanceFormTable, Integer> educationRecoveryStatus =
		createColumn(
			"educationRecoveryStatus", Integer.class, Types.INTEGER,
			Column.FLAG_DEFAULT);
	public final Column<FinanceFormTable, Boolean> educationRecoveryApproved =
		createColumn(
			"educationRecoveryApproved", Boolean.class, Types.BOOLEAN,
			Column.FLAG_DEFAULT);
	public final Column<FinanceFormTable, String> educationRecoveryComment =
		createColumn(
			"educationRecoveryComment", String.class, Types.VARCHAR,
			Column.FLAG_DEFAULT);
	public final Column<FinanceFormTable, String> joiningBonusRecoveryAmt =
		createColumn(
			"joiningBonusRecoveryAmt", String.class, Types.VARCHAR,
			Column.FLAG_DEFAULT);
	public final Column<FinanceFormTable, Integer> joiningBonusRecoveryStatus =
		createColumn(
			"joiningBonusRecoveryStatus", Integer.class, Types.INTEGER,
			Column.FLAG_DEFAULT);
	public final Column<FinanceFormTable, Boolean> joiningRecoveryApproved =
		createColumn(
			"joiningRecoveryApproved", Boolean.class, Types.BOOLEAN,
			Column.FLAG_DEFAULT);
	public final Column<FinanceFormTable, String> joiningRecoveryComment =
		createColumn(
			"joiningRecoveryComment", String.class, Types.VARCHAR,
			Column.FLAG_DEFAULT);
	public final Column<FinanceFormTable, String> noticePeriodRecoveryAmt =
		createColumn(
			"noticePeriodRecoveryAmt", String.class, Types.VARCHAR,
			Column.FLAG_DEFAULT);
	public final Column<FinanceFormTable, Integer> noticePeriodRecoveryStatus =
		createColumn(
			"noticePeriodRecoveryStatus", Integer.class, Types.INTEGER,
			Column.FLAG_DEFAULT);
	public final Column<FinanceFormTable, Boolean>
		noticePeriodRecoveryApproved = createColumn(
			"noticePeriodRecoveryApproved", Boolean.class, Types.BOOLEAN,
			Column.FLAG_DEFAULT);
	public final Column<FinanceFormTable, String> noticePeriodRecoveryComment =
		createColumn(
			"noticePeriodRecoveryComment", String.class, Types.VARCHAR,
			Column.FLAG_DEFAULT);
	public final Column<FinanceFormTable, Date> updatedDate = createColumn(
		"updatedDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);

	private FinanceFormTable() {
		super("NOTICE_FinanceForm", FinanceFormTable::new);
	}

}