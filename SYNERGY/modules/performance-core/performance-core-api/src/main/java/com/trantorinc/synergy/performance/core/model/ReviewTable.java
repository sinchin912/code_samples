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

package com.trantorinc.synergy.performance.core.model;

import com.liferay.petra.sql.dsl.Column;
import com.liferay.petra.sql.dsl.base.BaseTable;

import java.sql.Types;

import java.util.Date;

/**
 * The table class for the &quot;PERFORMANCE_Review&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see Review
 * @generated
 */
public class ReviewTable extends BaseTable<ReviewTable> {

	public static final ReviewTable INSTANCE = new ReviewTable();

	public final Column<ReviewTable, Long> reviewId = createColumn(
		"reviewId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<ReviewTable, Long> kpiId = createColumn(
		"kpiId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<ReviewTable, String> ecode = createColumn(
		"ecode", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ReviewTable, String> account = createColumn(
		"account", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ReviewTable, String> manager = createColumn(
		"manager", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ReviewTable, String> assignee = createColumn(
		"assignee", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ReviewTable, Boolean> assigned = createColumn(
		"assigned", Boolean.class, Types.BOOLEAN, Column.FLAG_DEFAULT);
	public final Column<ReviewTable, Date> reviewStartDate = createColumn(
		"reviewStartDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<ReviewTable, Integer> reviewState = createColumn(
		"reviewState", Integer.class, Types.INTEGER, Column.FLAG_DEFAULT);
	public final Column<ReviewTable, Boolean> accountPrimary = createColumn(
		"accountPrimary", Boolean.class, Types.BOOLEAN, Column.FLAG_DEFAULT);
	public final Column<ReviewTable, Boolean> finalYearReview = createColumn(
		"finalYearReview", Boolean.class, Types.BOOLEAN, Column.FLAG_DEFAULT);
	public final Column<ReviewTable, String> comment = createColumn(
		"comment_", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ReviewTable, String> employeeComment = createColumn(
		"employeeComment", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ReviewTable, String> managerComment = createColumn(
		"managerComment", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ReviewTable, String> improvementComment = createColumn(
		"improvementComment", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ReviewTable, String> hrComment = createColumn(
		"hrComment", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ReviewTable, String> rating = createColumn(
		"rating", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ReviewTable, String> achievements = createColumn(
		"achievements", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ReviewTable, String> trainings = createColumn(
		"trainings", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ReviewTable, String> excelledArea = createColumn(
		"excelledArea", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ReviewTable, String> rolePlayed = createColumn(
		"rolePlayed", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ReviewTable, String> expectedDesignation = createColumn(
		"expectedDesignation", String.class, Types.VARCHAR,
		Column.FLAG_DEFAULT);
	public final Column<ReviewTable, String> expectedSalary = createColumn(
		"expectedSalary", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ReviewTable, String> readyReason = createColumn(
		"readyReason", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ReviewTable, String> reasonRecommendation =
		createColumn(
			"reasonRecommendation", String.class, Types.VARCHAR,
			Column.FLAG_DEFAULT);
	public final Column<ReviewTable, Boolean> promotionRecommendation =
		createColumn(
			"promotionRecommendation", Boolean.class, Types.BOOLEAN,
			Column.FLAG_DEFAULT);
	public final Column<ReviewTable, Integer> criticalToAccount = createColumn(
		"criticalToAccount", Integer.class, Types.INTEGER, Column.FLAG_DEFAULT);
	public final Column<ReviewTable, Integer> criticalToCompany = createColumn(
		"criticalToCompany", Integer.class, Types.INTEGER, Column.FLAG_DEFAULT);
	public final Column<ReviewTable, Integer> replacement = createColumn(
		"replacement", Integer.class, Types.INTEGER, Column.FLAG_DEFAULT);
	public final Column<ReviewTable, String> replacementDetail = createColumn(
		"replacementDetail", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ReviewTable, String> replacementReason = createColumn(
		"replacementReason", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ReviewTable, Boolean> replacementReasonOther =
		createColumn(
			"replacementReasonOther", Boolean.class, Types.BOOLEAN,
			Column.FLAG_DEFAULT);
	public final Column<ReviewTable, Date> stage1Date = createColumn(
		"stage1Date", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<ReviewTable, Date> stage2Date = createColumn(
		"stage2Date", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<ReviewTable, Date> stage3Date = createColumn(
		"stage3Date", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<ReviewTable, Date> stage4Date = createColumn(
		"stage4Date", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<ReviewTable, Date> stage5Date = createColumn(
		"stage5Date", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<ReviewTable, Boolean> firstSubmit = createColumn(
		"firstSubmit", Boolean.class, Types.BOOLEAN, Column.FLAG_DEFAULT);

	private ReviewTable() {
		super("PERFORMANCE_Review", ReviewTable::new);
	}

}