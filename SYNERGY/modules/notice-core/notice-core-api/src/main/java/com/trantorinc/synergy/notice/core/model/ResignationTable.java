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
 * The table class for the &quot;NOTICE_Resignation&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see Resignation
 * @generated
 */
public class ResignationTable extends BaseTable<ResignationTable> {

	public static final ResignationTable INSTANCE = new ResignationTable();

	public final Column<ResignationTable, Long> id = createColumn(
		"id_", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<ResignationTable, String> ecode = createColumn(
		"ecode", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ResignationTable, String> account = createColumn(
		"account", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ResignationTable, String> managerEmail = createColumn(
		"managerEmail", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ResignationTable, String> assigneeEmail = createColumn(
		"assigneeEmail", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ResignationTable, Boolean> assigneeTo = createColumn(
		"assigneeTo", Boolean.class, Types.BOOLEAN, Column.FLAG_DEFAULT);
	public final Column<ResignationTable, String> alternateEmail = createColumn(
		"alternateEmail", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ResignationTable, String> stateName = createColumn(
		"stateName", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ResignationTable, String> cityName = createColumn(
		"cityName", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ResignationTable, String> pincode = createColumn(
		"pincode", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ResignationTable, String> postalAddress = createColumn(
		"postalAddress", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ResignationTable, String> reason = createColumn(
		"reason", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ResignationTable, Date> lastWorkingDate = createColumn(
		"lastWorkingDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<ResignationTable, Date> managerSubmissionDate =
		createColumn(
			"managerSubmissionDate", Date.class, Types.TIMESTAMP,
			Column.FLAG_DEFAULT);
	public final Column<ResignationTable, Date> hrSubmissionDate = createColumn(
		"hrSubmissionDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<ResignationTable, Date> withdrawInitiatedDate =
		createColumn(
			"withdrawInitiatedDate", Date.class, Types.TIMESTAMP,
			Column.FLAG_DEFAULT);
	public final Column<ResignationTable, Date> abscondTerminateDate =
		createColumn(
			"abscondTerminateDate", Date.class, Types.TIMESTAMP,
			Column.FLAG_DEFAULT);
	public final Column<ResignationTable, String> separationType = createColumn(
		"separationType", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ResignationTable, String> keyToCompany = createColumn(
		"keyToCompany", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ResignationTable, String> keyToProject = createColumn(
		"keyToProject", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ResignationTable, String> rating = createColumn(
		"rating", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ResignationTable, String> clientImpact = createColumn(
		"clientImpact", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ResignationTable, String> keyToRetention = createColumn(
		"keyToRetention", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ResignationTable, String> reasonNonRetention =
		createColumn(
			"reasonNonRetention", String.class, Types.VARCHAR,
			Column.FLAG_DEFAULT);
	public final Column<ResignationTable, String> employeeComment =
		createColumn(
			"employeeComment", String.class, Types.VARCHAR,
			Column.FLAG_DEFAULT);
	public final Column<ResignationTable, String> hrComment = createColumn(
		"hrComment", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ResignationTable, String> managerComment = createColumn(
		"managerComment", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ResignationTable, String> empWithdrawComment =
		createColumn(
			"empWithdrawComment", String.class, Types.VARCHAR,
			Column.FLAG_DEFAULT);
	public final Column<ResignationTable, String> hrWithdrawComment =
		createColumn(
			"hrWithdrawComment", String.class, Types.VARCHAR,
			Column.FLAG_DEFAULT);
	public final Column<ResignationTable, String> reasonForLeaving =
		createColumn(
			"reasonForLeaving", String.class, Types.VARCHAR,
			Column.FLAG_DEFAULT);
	public final Column<ResignationTable, String> reasonForLeavingByHr =
		createColumn(
			"reasonForLeavingByHr", String.class, Types.VARCHAR,
			Column.FLAG_DEFAULT);
	public final Column<ResignationTable, String> eligibleForRehire =
		createColumn(
			"eligibleForRehire", String.class, Types.VARCHAR,
			Column.FLAG_DEFAULT);
	public final Column<ResignationTable, String> otherIssue = createColumn(
		"otherIssue", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ResignationTable, String> replacementPlan =
		createColumn(
			"replacementPlan", String.class, Types.VARCHAR,
			Column.FLAG_DEFAULT);
	public final Column<ResignationTable, String> replacementDetail =
		createColumn(
			"replacementDetail", String.class, Types.VARCHAR,
			Column.FLAG_DEFAULT);
	public final Column<ResignationTable, Integer> status = createColumn(
		"status", Integer.class, Types.INTEGER, Column.FLAG_DEFAULT);
	public final Column<ResignationTable, String> terminationType =
		createColumn(
			"terminationType", String.class, Types.VARCHAR,
			Column.FLAG_DEFAULT);
	public final Column<ResignationTable, Date> creationDate = createColumn(
		"creationDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<ResignationTable, Date> initiatedDate = createColumn(
		"initiatedDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<ResignationTable, String> mobile = createColumn(
		"mobile", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ResignationTable, String> noticePeriod = createColumn(
		"noticePeriod", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ResignationTable, Date> itAssetsSubmissionDate =
		createColumn(
			"itAssetsSubmissionDate", Date.class, Types.TIMESTAMP,
			Column.FLAG_DEFAULT);
	public final Column<ResignationTable, Date> retainEmployeeDate =
		createColumn(
			"retainEmployeeDate", Date.class, Types.TIMESTAMP,
			Column.FLAG_DEFAULT);
	public final Column<ResignationTable, String> hasLaptop = createColumn(
		"hasLaptop", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ResignationTable, String> hasMouse = createColumn(
		"hasMouse", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ResignationTable, String> hasCharger = createColumn(
		"hasCharger", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ResignationTable, String> hasHeadSet = createColumn(
		"hasHeadSet", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ResignationTable, String> hasLaptopBag = createColumn(
		"hasLaptopBag", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ResignationTable, String> hasHomeDesktop = createColumn(
		"hasHomeDesktop", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ResignationTable, String> hasHomeMonitor = createColumn(
		"hasHomeMonitor", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ResignationTable, String> otherAssetsList =
		createColumn(
			"otherAssetsList", String.class, Types.VARCHAR,
			Column.FLAG_DEFAULT);
	public final Column<ResignationTable, Boolean> exitQuestionnaire =
		createColumn(
			"exitQuestionnaire", Boolean.class, Types.BOOLEAN,
			Column.FLAG_DEFAULT);
	public final Column<ResignationTable, Integer> withdrawCount = createColumn(
		"withdrawCount", Integer.class, Types.INTEGER, Column.FLAG_DEFAULT);

	private ResignationTable() {
		super("NOTICE_Resignation", ResignationTable::new);
	}

}