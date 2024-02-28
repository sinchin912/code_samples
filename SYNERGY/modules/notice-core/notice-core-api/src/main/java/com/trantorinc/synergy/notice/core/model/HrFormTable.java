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
 * The table class for the &quot;NOTICE_HrForm&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see HrForm
 * @generated
 */
public class HrFormTable extends BaseTable<HrFormTable> {

	public static final HrFormTable INSTANCE = new HrFormTable();

	public final Column<HrFormTable, Long> id = createColumn(
		"id_", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<HrFormTable, Long> exitId = createColumn(
		"exitId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<HrFormTable, Long> foodOption = createColumn(
		"foodOption", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<HrFormTable, String> foodOptionRemark = createColumn(
		"foodOptionRemark", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<HrFormTable, Integer> inductionFeedbackStatus =
		createColumn(
			"inductionFeedbackStatus", Integer.class, Types.INTEGER,
			Column.FLAG_DEFAULT);
	public final Column<HrFormTable, String> inductionFeedbackRemark =
		createColumn(
			"inductionFeedbackRemark", String.class, Types.VARCHAR,
			Column.FLAG_DEFAULT);
	public final Column<HrFormTable, Integer> inductionQuizStatus =
		createColumn(
			"inductionQuizStatus", Integer.class, Types.INTEGER,
			Column.FLAG_DEFAULT);
	public final Column<HrFormTable, String> inductionQuizRemark = createColumn(
		"inductionQuizRemark", String.class, Types.VARCHAR,
		Column.FLAG_DEFAULT);
	public final Column<HrFormTable, Integer> trainingFeedbackStatus =
		createColumn(
			"trainingFeedbackStatus", Integer.class, Types.INTEGER,
			Column.FLAG_DEFAULT);
	public final Column<HrFormTable, String> trainingFeedbackRemark =
		createColumn(
			"trainingFeedbackRemark", String.class, Types.VARCHAR,
			Column.FLAG_DEFAULT);
	public final Column<HrFormTable, Integer> exitInterviewStatus =
		createColumn(
			"exitInterviewStatus", Integer.class, Types.INTEGER,
			Column.FLAG_DEFAULT);
	public final Column<HrFormTable, String> exitInterviewRemark = createColumn(
		"exitInterviewRemark", String.class, Types.VARCHAR,
		Column.FLAG_DEFAULT);
	public final Column<HrFormTable, Integer> employeeDirectoryStatus =
		createColumn(
			"employeeDirectoryStatus", Integer.class, Types.INTEGER,
			Column.FLAG_DEFAULT);
	public final Column<HrFormTable, String> employeeDirectoryRemark =
		createColumn(
			"employeeDirectoryRemark", String.class, Types.VARCHAR,
			Column.FLAG_DEFAULT);
	public final Column<HrFormTable, Integer> lmsStatus = createColumn(
		"lmsStatus", Integer.class, Types.INTEGER, Column.FLAG_DEFAULT);
	public final Column<HrFormTable, String> lmsRemark = createColumn(
		"lmsRemark", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<HrFormTable, Integer> vantageCircleStatus =
		createColumn(
			"vantageCircleStatus", Integer.class, Types.INTEGER,
			Column.FLAG_DEFAULT);
	public final Column<HrFormTable, String> vantageCircleRemark = createColumn(
		"vantageCircleRemark", String.class, Types.VARCHAR,
		Column.FLAG_DEFAULT);
	public final Column<HrFormTable, Integer> birthdaySynergyStatus =
		createColumn(
			"birthdaySynergyStatus", Integer.class, Types.INTEGER,
			Column.FLAG_DEFAULT);
	public final Column<HrFormTable, String> birthdaySynergyRemark =
		createColumn(
			"birthdaySynergyRemark", String.class, Types.VARCHAR,
			Column.FLAG_DEFAULT);
	public final Column<HrFormTable, Integer> experienceLetterStatus =
		createColumn(
			"experienceLetterStatus", Integer.class, Types.INTEGER,
			Column.FLAG_DEFAULT);
	public final Column<HrFormTable, String> experienceLetterRemark =
		createColumn(
			"experienceLetterRemark", String.class, Types.VARCHAR,
			Column.FLAG_DEFAULT);
	public final Column<HrFormTable, Integer> ndaFormStatus = createColumn(
		"ndaFormStatus", Integer.class, Types.INTEGER, Column.FLAG_DEFAULT);
	public final Column<HrFormTable, String> ndaFormRemark = createColumn(
		"ndaFormRemark", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<HrFormTable, Integer> separationDocumentStatus =
		createColumn(
			"separationDocumentStatus", Integer.class, Types.INTEGER,
			Column.FLAG_DEFAULT);
	public final Column<HrFormTable, String> separationDocumentRemark =
		createColumn(
			"separationDocumentRemark", String.class, Types.VARCHAR,
			Column.FLAG_DEFAULT);
	public final Column<HrFormTable, String> trainingAgreementAmt =
		createColumn(
			"trainingAgreementAmt", String.class, Types.VARCHAR,
			Column.FLAG_DEFAULT);
	public final Column<HrFormTable, Integer> trainingAgreementStatus =
		createColumn(
			"trainingAgreementStatus", Integer.class, Types.INTEGER,
			Column.FLAG_DEFAULT);
	public final Column<HrFormTable, String> recoverableBonusAmt = createColumn(
		"recoverableBonusAmt", String.class, Types.VARCHAR,
		Column.FLAG_DEFAULT);
	public final Column<HrFormTable, Integer> recoverableBonusStatus =
		createColumn(
			"recoverableBonusStatus", Integer.class, Types.INTEGER,
			Column.FLAG_DEFAULT);
	public final Column<HrFormTable, String> noticePeriodRecoveryAmt =
		createColumn(
			"noticePeriodRecoveryAmt", String.class, Types.VARCHAR,
			Column.FLAG_DEFAULT);
	public final Column<HrFormTable, Integer> noticePeriodRecoveryStatus =
		createColumn(
			"noticePeriodRecoveryStatus", Integer.class, Types.INTEGER,
			Column.FLAG_DEFAULT);
	public final Column<HrFormTable, String> leavesMonth1 = createColumn(
		"leavesMonth1", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<HrFormTable, String> leaveDaysMonth1 = createColumn(
		"leaveDaysMonth1", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<HrFormTable, String> leaveDateMonth1 = createColumn(
		"leaveDateMonth1", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<HrFormTable, String> leavesMonth2 = createColumn(
		"leavesMonth2", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<HrFormTable, String> leaveDaysMonth2 = createColumn(
		"leaveDaysMonth2", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<HrFormTable, String> leaveDateMonth2 = createColumn(
		"leaveDateMonth2", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<HrFormTable, String> leavesMonth3 = createColumn(
		"leavesMonth3", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<HrFormTable, String> leaveDaysMonth3 = createColumn(
		"leaveDaysMonth3", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<HrFormTable, String> leaveDateMonth3 = createColumn(
		"leaveDateMonth3", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<HrFormTable, String> lopMonth1 = createColumn(
		"lopMonth1", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<HrFormTable, String> lopDaysMonth1 = createColumn(
		"lopDaysMonth1", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<HrFormTable, String> lopDateMonth1 = createColumn(
		"lopDateMonth1", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<HrFormTable, String> lopMonth2 = createColumn(
		"lopMonth2", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<HrFormTable, String> lopDaysMonth2 = createColumn(
		"lopDaysMonth2", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<HrFormTable, String> lopDateMonth2 = createColumn(
		"lopDateMonth2", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<HrFormTable, String> lopMonth3 = createColumn(
		"lopMonth3", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<HrFormTable, String> lopDaysMonth3 = createColumn(
		"lopDaysMonth3", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<HrFormTable, String> lopDateMonth3 = createColumn(
		"lopDateMonth3", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<HrFormTable, String> earnedLeaveBalance = createColumn(
		"earnedLeaveBalance", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<HrFormTable, String> hrRemark = createColumn(
		"hrRemark", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<HrFormTable, Date> updatedDate = createColumn(
		"updatedDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);

	private HrFormTable() {
		super("NOTICE_HrForm", HrFormTable::new);
	}

}