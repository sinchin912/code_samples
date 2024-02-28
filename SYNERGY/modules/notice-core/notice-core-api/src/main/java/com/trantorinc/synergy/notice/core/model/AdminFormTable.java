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
 * The table class for the &quot;NOTICE_AdminForm&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see AdminForm
 * @generated
 */
public class AdminFormTable extends BaseTable<AdminFormTable> {

	public static final AdminFormTable INSTANCE = new AdminFormTable();

	public final Column<AdminFormTable, Long> id = createColumn(
		"id_", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<AdminFormTable, Long> exitId = createColumn(
		"exitId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<AdminFormTable, String> stationaryRecoveryAmt =
		createColumn(
			"stationaryRecoveryAmt", String.class, Types.VARCHAR,
			Column.FLAG_DEFAULT);
	public final Column<AdminFormTable, Integer> stationaryRecoveryAmtStatus =
		createColumn(
			"stationaryRecoveryAmtStatus", Integer.class, Types.INTEGER,
			Column.FLAG_DEFAULT);
	public final Column<AdminFormTable, Boolean> stationaryRecoveryApproved =
		createColumn(
			"stationaryRecoveryApproved", Boolean.class, Types.BOOLEAN,
			Column.FLAG_DEFAULT);
	public final Column<AdminFormTable, String> stationaryRecoveryComment =
		createColumn(
			"stationaryRecoveryComment", String.class, Types.VARCHAR,
			Column.FLAG_DEFAULT);
	public final Column<AdminFormTable, String> keysRecoveryAmt = createColumn(
		"keysRecoveryAmt", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<AdminFormTable, Integer> keysRecoveryAmtStatus =
		createColumn(
			"keysRecoveryAmtStatus", Integer.class, Types.INTEGER,
			Column.FLAG_DEFAULT);
	public final Column<AdminFormTable, Boolean> keyRecoveryApproved =
		createColumn(
			"keyRecoveryApproved", Boolean.class, Types.BOOLEAN,
			Column.FLAG_DEFAULT);
	public final Column<AdminFormTable, String> keyRecoveryComment =
		createColumn(
			"keyRecoveryComment", String.class, Types.VARCHAR,
			Column.FLAG_DEFAULT);
	public final Column<AdminFormTable, String> libraryRecoveryAmt =
		createColumn(
			"libraryRecoveryAmt", String.class, Types.VARCHAR,
			Column.FLAG_DEFAULT);
	public final Column<AdminFormTable, Integer> libraryRecoveryAmtStatus =
		createColumn(
			"libraryRecoveryAmtStatus", Integer.class, Types.INTEGER,
			Column.FLAG_DEFAULT);
	public final Column<AdminFormTable, Boolean> libraryRecoveryApproved =
		createColumn(
			"libraryRecoveryApproved", Boolean.class, Types.BOOLEAN,
			Column.FLAG_DEFAULT);
	public final Column<AdminFormTable, String> libraryRecoveryComment =
		createColumn(
			"libraryRecoveryComment", String.class, Types.VARCHAR,
			Column.FLAG_DEFAULT);
	public final Column<AdminFormTable, String> sportsRecoveryAmt =
		createColumn(
			"sportsRecoveryAmt", String.class, Types.VARCHAR,
			Column.FLAG_DEFAULT);
	public final Column<AdminFormTable, Integer> sportsRecoveryAmtStatus =
		createColumn(
			"sportsRecoveryAmtStatus", Integer.class, Types.INTEGER,
			Column.FLAG_DEFAULT);
	public final Column<AdminFormTable, Boolean> sportsRecoveryApproved =
		createColumn(
			"sportsRecoveryApproved", Boolean.class, Types.BOOLEAN,
			Column.FLAG_DEFAULT);
	public final Column<AdminFormTable, String> sportsRecoveryComment =
		createColumn(
			"sportsRecoveryComment", String.class, Types.VARCHAR,
			Column.FLAG_DEFAULT);
	public final Column<AdminFormTable, String> infraRecoveryAmt = createColumn(
		"infraRecoveryAmt", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<AdminFormTable, Integer> infraRecoveryAmtStatus =
		createColumn(
			"infraRecoveryAmtStatus", Integer.class, Types.INTEGER,
			Column.FLAG_DEFAULT);
	public final Column<AdminFormTable, Boolean> infraRecoveryApproved =
		createColumn(
			"infraRecoveryApproved", Boolean.class, Types.BOOLEAN,
			Column.FLAG_DEFAULT);
	public final Column<AdminFormTable, String> infraRecoveryComment =
		createColumn(
			"infraRecoveryComment", String.class, Types.VARCHAR,
			Column.FLAG_DEFAULT);
	public final Column<AdminFormTable, String> lunchRecoveryAmt = createColumn(
		"lunchRecoveryAmt", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<AdminFormTable, Integer> lunchRecoveryAmtStatus =
		createColumn(
			"lunchRecoveryAmtStatus", Integer.class, Types.INTEGER,
			Column.FLAG_DEFAULT);
	public final Column<AdminFormTable, Boolean> lunchRecoveryApproved =
		createColumn(
			"lunchRecoveryApproved", Boolean.class, Types.BOOLEAN,
			Column.FLAG_DEFAULT);
	public final Column<AdminFormTable, String> lunchRecoveryComment =
		createColumn(
			"lunchRecoveryComment", String.class, Types.VARCHAR,
			Column.FLAG_DEFAULT);
	public final Column<AdminFormTable, String> accessCardRecoveryAmt =
		createColumn(
			"accessCardRecoveryAmt", String.class, Types.VARCHAR,
			Column.FLAG_DEFAULT);
	public final Column<AdminFormTable, Integer> accessCardRecoveryAmtStatus =
		createColumn(
			"accessCardRecoveryAmtStatus", Integer.class, Types.INTEGER,
			Column.FLAG_DEFAULT);
	public final Column<AdminFormTable, Boolean> accessCardRecoveryApproved =
		createColumn(
			"accessCardRecoveryApproved", Boolean.class, Types.BOOLEAN,
			Column.FLAG_DEFAULT);
	public final Column<AdminFormTable, String> accessCardRecoveryComment =
		createColumn(
			"accessCardRecoveryComment", String.class, Types.VARCHAR,
			Column.FLAG_DEFAULT);
	public final Column<AdminFormTable, String> identityCardRecoveryAmt =
		createColumn(
			"identityCardRecoveryAmt", String.class, Types.VARCHAR,
			Column.FLAG_DEFAULT);
	public final Column<AdminFormTable, Integer> identityCardRecoveryStatus =
		createColumn(
			"identityCardRecoveryStatus", Integer.class, Types.INTEGER,
			Column.FLAG_DEFAULT);
	public final Column<AdminFormTable, Boolean> identityCardRecoveryApproved =
		createColumn(
			"identityCardRecoveryApproved", Boolean.class, Types.BOOLEAN,
			Column.FLAG_DEFAULT);
	public final Column<AdminFormTable, String> identityCardRecoveryComment =
		createColumn(
			"identityCardRecoveryComment", String.class, Types.VARCHAR,
			Column.FLAG_DEFAULT);
	public final Column<AdminFormTable, Date> updatedDate = createColumn(
		"updatedDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);

	private AdminFormTable() {
		super("NOTICE_AdminForm", AdminFormTable::new);
	}

}