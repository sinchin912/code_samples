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
 * The table class for the &quot;NOTICE_QuestionnaireForm&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see QuestionnaireForm
 * @generated
 */
public class QuestionnaireFormTable extends BaseTable<QuestionnaireFormTable> {

	public static final QuestionnaireFormTable INSTANCE =
		new QuestionnaireFormTable();

	public final Column<QuestionnaireFormTable, Long> id = createColumn(
		"id_", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<QuestionnaireFormTable, Long> resignationId =
		createColumn(
			"resignationId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<QuestionnaireFormTable, String> workExperience =
		createColumn(
			"workExperience", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<QuestionnaireFormTable, String>
		communicationWithEmployees = createColumn(
			"communicationWithEmployees", String.class, Types.VARCHAR,
			Column.FLAG_DEFAULT);
	public final Column<QuestionnaireFormTable, String> trainingOpportunity =
		createColumn(
			"trainingOpportunity", String.class, Types.VARCHAR,
			Column.FLAG_DEFAULT);
	public final Column<QuestionnaireFormTable, String> dealingWithStaff =
		createColumn(
			"dealingWithStaff", String.class, Types.VARCHAR,
			Column.FLAG_DEFAULT);
	public final Column<QuestionnaireFormTable, String> satisfactionLevel =
		createColumn(
			"satisfactionLevel", String.class, Types.VARCHAR,
			Column.FLAG_DEFAULT);
	public final Column<QuestionnaireFormTable, String> reasonForLeaving =
		createColumn(
			"reasonForLeaving", String.class, Types.VARCHAR,
			Column.FLAG_DEFAULT);
	public final Column<QuestionnaireFormTable, String>
		reasonForLeavingDescribe = createColumn(
			"reasonForLeavingDescribe", String.class, Types.VARCHAR,
			Column.FLAG_DEFAULT);
	public final Column<QuestionnaireFormTable, String> reasonForJoining =
		createColumn(
			"reasonForJoining", String.class, Types.VARCHAR,
			Column.FLAG_DEFAULT);
	public final Column<QuestionnaireFormTable, Integer> workAgain =
		createColumn(
			"workAgain", Integer.class, Types.INTEGER, Column.FLAG_DEFAULT);
	public final Column<QuestionnaireFormTable, String> notWorkAgain =
		createColumn(
			"notWorkAgain", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<QuestionnaireFormTable, Integer> recommendTrantor =
		createColumn(
			"recommendTrantor", Integer.class, Types.INTEGER,
			Column.FLAG_DEFAULT);
	public final Column<QuestionnaireFormTable, String> notRecommendTrantor =
		createColumn(
			"notRecommendTrantor", String.class, Types.VARCHAR,
			Column.FLAG_DEFAULT);
	public final Column<QuestionnaireFormTable, String> companyName =
		createColumn(
			"companyName", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<QuestionnaireFormTable, String> companyDetails =
		createColumn(
			"companyDetails", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<QuestionnaireFormTable, String> designation =
		createColumn(
			"designation", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<QuestionnaireFormTable, String> location = createColumn(
		"location  ", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<QuestionnaireFormTable, String> salaryHike =
		createColumn(
			"salaryHike", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<QuestionnaireFormTable, String> feedback = createColumn(
		"feedback ", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<QuestionnaireFormTable, String> hrRemark = createColumn(
		"hrRemark", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<QuestionnaireFormTable, Date> submittedDate =
		createColumn(
			"submittedDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);

	private QuestionnaireFormTable() {
		super("NOTICE_QuestionnaireForm", QuestionnaireFormTable::new);
	}

}