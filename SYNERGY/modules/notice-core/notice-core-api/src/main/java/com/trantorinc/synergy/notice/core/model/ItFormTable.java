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
 * The table class for the &quot;NOTICE_ItForm&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see ItForm
 * @generated
 */
public class ItFormTable extends BaseTable<ItFormTable> {

	public static final ItFormTable INSTANCE = new ItFormTable();

	public final Column<ItFormTable, Long> id = createColumn(
		"id_", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<ItFormTable, Long> exitId = createColumn(
		"exitId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<ItFormTable, Integer> ticketNo = createColumn(
		"ticketNo", Integer.class, Types.INTEGER, Column.FLAG_DEFAULT);
	public final Column<ItFormTable, String> ticketNoRemark = createColumn(
		"ticketNoRemark", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ItFormTable, Integer> mailPst = createColumn(
		"mailPst", Integer.class, Types.INTEGER, Column.FLAG_DEFAULT);
	public final Column<ItFormTable, String> mailPstRemark = createColumn(
		"mailPstRemark", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ItFormTable, Integer> downloadFolder = createColumn(
		"downloadFolder", Integer.class, Types.INTEGER, Column.FLAG_DEFAULT);
	public final Column<ItFormTable, String> downloadFolderRemark =
		createColumn(
			"downloadFolderRemark", String.class, Types.VARCHAR,
			Column.FLAG_DEFAULT);
	public final Column<ItFormTable, Integer> documentFolder = createColumn(
		"documentFolder", Integer.class, Types.INTEGER, Column.FLAG_DEFAULT);
	public final Column<ItFormTable, String> documentFolderRemark =
		createColumn(
			"documentFolderRemark", String.class, Types.VARCHAR,
			Column.FLAG_DEFAULT);
	public final Column<ItFormTable, Integer> otherData = createColumn(
		"otherData", Integer.class, Types.INTEGER, Column.FLAG_DEFAULT);
	public final Column<ItFormTable, String> otherDataRemark = createColumn(
		"otherDataRemark", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ItFormTable, Integer> googleDrive = createColumn(
		"googleDrive", Integer.class, Types.INTEGER, Column.FLAG_DEFAULT);
	public final Column<ItFormTable, String> googleDriveRemark = createColumn(
		"googleDriveRemark", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ItFormTable, Integer> others = createColumn(
		"others", Integer.class, Types.INTEGER, Column.FLAG_DEFAULT);
	public final Column<ItFormTable, String> othersRemark = createColumn(
		"othersRemark", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ItFormTable, Integer> mailForwarding = createColumn(
		"mailForwarding", Integer.class, Types.INTEGER, Column.FLAG_DEFAULT);
	public final Column<ItFormTable, String> mailForwardingRemark =
		createColumn(
			"mailForwardingRemark", String.class, Types.VARCHAR,
			Column.FLAG_DEFAULT);
	public final Column<ItFormTable, Integer> emailDisable = createColumn(
		"emailDisable", Integer.class, Types.INTEGER, Column.FLAG_DEFAULT);
	public final Column<ItFormTable, String> emailDisableRemark = createColumn(
		"emailDisableRemark", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ItFormTable, Integer> systemRecovered = createColumn(
		"systemRecovered", Integer.class, Types.INTEGER, Column.FLAG_DEFAULT);
	public final Column<ItFormTable, String> systemRecoveredRemark =
		createColumn(
			"systemRecoveredRemark", String.class, Types.VARCHAR,
			Column.FLAG_DEFAULT);
	public final Column<ItFormTable, Integer> clientSystemRecovered =
		createColumn(
			"clientSystemRecovered", Integer.class, Types.INTEGER,
			Column.FLAG_DEFAULT);
	public final Column<ItFormTable, String> clientSystemRecoveredRemark =
		createColumn(
			"clientSystemRecoveredRemark", String.class, Types.VARCHAR,
			Column.FLAG_DEFAULT);
	public final Column<ItFormTable, Integer> accessCardDisable = createColumn(
		"accessCardDisable", Integer.class, Types.INTEGER, Column.FLAG_DEFAULT);
	public final Column<ItFormTable, String> accessCardDisableRemark =
		createColumn(
			"accessCardDisableRemark", String.class, Types.VARCHAR,
			Column.FLAG_DEFAULT);
	public final Column<ItFormTable, Integer> separationDocument = createColumn(
		"separationDocument", Integer.class, Types.INTEGER,
		Column.FLAG_DEFAULT);
	public final Column<ItFormTable, String> separationDocumentRemark =
		createColumn(
			"separationDocumentRemark", String.class, Types.VARCHAR,
			Column.FLAG_DEFAULT);
	public final Column<ItFormTable, String> systemRecoveryAmt = createColumn(
		"systemRecoveryAmt", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ItFormTable, Integer> systemRecoveryAmtStatus =
		createColumn(
			"systemRecoveryAmtStatus", Integer.class, Types.INTEGER,
			Column.FLAG_DEFAULT);
	public final Column<ItFormTable, Boolean> systemRecoveryApproved =
		createColumn(
			"systemRecoveryApproved", Boolean.class, Types.BOOLEAN,
			Column.FLAG_DEFAULT);
	public final Column<ItFormTable, String> systemRecoveryComment =
		createColumn(
			"systemRecoveryComment", String.class, Types.VARCHAR,
			Column.FLAG_DEFAULT);
	public final Column<ItFormTable, String> laptopRecoveryAmt = createColumn(
		"laptopRecoveryAmt", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ItFormTable, Integer> laptopRecoveryAmtStatus =
		createColumn(
			"laptopRecoveryAmtStatus", Integer.class, Types.INTEGER,
			Column.FLAG_DEFAULT);
	public final Column<ItFormTable, Boolean> laptopRecoveryApproved =
		createColumn(
			"laptopRecoveryApproved", Boolean.class, Types.BOOLEAN,
			Column.FLAG_DEFAULT);
	public final Column<ItFormTable, String> laptopRecoveryComment =
		createColumn(
			"laptopRecoveryComment", String.class, Types.VARCHAR,
			Column.FLAG_DEFAULT);
	public final Column<ItFormTable, String> communicationRecoveryAmt =
		createColumn(
			"communicationRecoveryAmt", String.class, Types.VARCHAR,
			Column.FLAG_DEFAULT);
	public final Column<ItFormTable, Integer> communicationRecoveryStatus =
		createColumn(
			"communicationRecoveryStatus", Integer.class, Types.INTEGER,
			Column.FLAG_DEFAULT);
	public final Column<ItFormTable, Boolean> communicationRecoveryApproved =
		createColumn(
			"communicationRecoveryApproved", Boolean.class, Types.BOOLEAN,
			Column.FLAG_DEFAULT);
	public final Column<ItFormTable, String> communicationRecoveryComment =
		createColumn(
			"communicationRecoveryComment", String.class, Types.VARCHAR,
			Column.FLAG_DEFAULT);
	public final Column<ItFormTable, String> headphoneRecoveryAmt =
		createColumn(
			"headphoneRecoveryAmt", String.class, Types.VARCHAR,
			Column.FLAG_DEFAULT);
	public final Column<ItFormTable, Integer> headphoneRecoveryAmtStatus =
		createColumn(
			"headphoneRecoveryAmtStatus", Integer.class, Types.INTEGER,
			Column.FLAG_DEFAULT);
	public final Column<ItFormTable, Boolean> headphoneRecoveryApproved =
		createColumn(
			"headphoneRecoveryApproved", Boolean.class, Types.BOOLEAN,
			Column.FLAG_DEFAULT);
	public final Column<ItFormTable, String> headphoneRecoveryComment =
		createColumn(
			"headphoneRecoveryComment", String.class, Types.VARCHAR,
			Column.FLAG_DEFAULT);
	public final Column<ItFormTable, String> laptopBagRecoveryAmt =
		createColumn(
			"laptopBagRecoveryAmt", String.class, Types.VARCHAR,
			Column.FLAG_DEFAULT);
	public final Column<ItFormTable, Integer> laptopBagRecoveryAmtStatus =
		createColumn(
			"laptopBagRecoveryAmtStatus", Integer.class, Types.INTEGER,
			Column.FLAG_DEFAULT);
	public final Column<ItFormTable, Boolean> laptopBagRecoveryApproved =
		createColumn(
			"laptopBagRecoveryApproved", Boolean.class, Types.BOOLEAN,
			Column.FLAG_DEFAULT);
	public final Column<ItFormTable, String> laptopBagRecoveryComment =
		createColumn(
			"laptopBagRecoveryComment", String.class, Types.VARCHAR,
			Column.FLAG_DEFAULT);
	public final Column<ItFormTable, String> monitorRecoveryAmt = createColumn(
		"monitorRecoveryAmt", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ItFormTable, Integer> monitorRecoveryAmtStatus =
		createColumn(
			"monitorRecoveryAmtStatus", Integer.class, Types.INTEGER,
			Column.FLAG_DEFAULT);
	public final Column<ItFormTable, Boolean> monitorRecoveryApproved =
		createColumn(
			"monitorRecoveryApproved", Boolean.class, Types.BOOLEAN,
			Column.FLAG_DEFAULT);
	public final Column<ItFormTable, String> monitorRecoveryComment =
		createColumn(
			"monitorRecoveryComment", String.class, Types.VARCHAR,
			Column.FLAG_DEFAULT);
	public final Column<ItFormTable, String> chargerRecoveryAmt = createColumn(
		"chargerRecoveryAmt", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ItFormTable, Integer> chargerRecoveryAmtStatus =
		createColumn(
			"chargerRecoveryAmtStatus", Integer.class, Types.INTEGER,
			Column.FLAG_DEFAULT);
	public final Column<ItFormTable, Boolean> chargerRecoveryApproved =
		createColumn(
			"chargerRecoveryApproved", Boolean.class, Types.BOOLEAN,
			Column.FLAG_DEFAULT);
	public final Column<ItFormTable, String> chargerRecoveryComment =
		createColumn(
			"chargerRecoveryComment", String.class, Types.VARCHAR,
			Column.FLAG_DEFAULT);
	public final Column<ItFormTable, String> mouseRecoveryAmt = createColumn(
		"mouseRecoveryAmt", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ItFormTable, Integer> mouseRecoveryAmtStatus =
		createColumn(
			"mouseRecoveryAmtStatus", Integer.class, Types.INTEGER,
			Column.FLAG_DEFAULT);
	public final Column<ItFormTable, Boolean> mouseRecoveryApproved =
		createColumn(
			"mouseRecoveryApproved", Boolean.class, Types.BOOLEAN,
			Column.FLAG_DEFAULT);
	public final Column<ItFormTable, String> mouseRecoveryComment =
		createColumn(
			"mouseRecoveryComment", String.class, Types.VARCHAR,
			Column.FLAG_DEFAULT);
	public final Column<ItFormTable, Date> updatedDate = createColumn(
		"updatedDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);

	private ItFormTable() {
		super("NOTICE_ItForm", ItFormTable::new);
	}

}