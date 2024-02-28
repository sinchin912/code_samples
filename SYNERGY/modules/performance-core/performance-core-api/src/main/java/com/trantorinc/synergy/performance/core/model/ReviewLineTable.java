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

/**
 * The table class for the &quot;PERFORMANCE_ReviewLine&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see ReviewLine
 * @generated
 */
public class ReviewLineTable extends BaseTable<ReviewLineTable> {

	public static final ReviewLineTable INSTANCE = new ReviewLineTable();

	public final Column<ReviewLineTable, Long> lineId = createColumn(
		"lineId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<ReviewLineTable, Long> reviewId = createColumn(
		"reviewId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<ReviewLineTable, Long> guideId = createColumn(
		"guideId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<ReviewLineTable, String> title = createColumn(
		"title", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ReviewLineTable, String> description = createColumn(
		"description", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ReviewLineTable, String> target = createColumn(
		"target", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ReviewLineTable, String> employeeRating = createColumn(
		"employeeRating", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ReviewLineTable, String> employeeComment = createColumn(
		"employeeComment", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ReviewLineTable, String> managerRating = createColumn(
		"managerRating", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ReviewLineTable, String> managerComment = createColumn(
		"managerComment", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ReviewLineTable, String> hrRating = createColumn(
		"hrRating", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ReviewLineTable, String> hrComment = createColumn(
		"hrComment", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);

	private ReviewLineTable() {
		super("PERFORMANCE_ReviewLine", ReviewLineTable::new);
	}

}