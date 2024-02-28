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
 * The table class for the &quot;PERFORMANCE_ReviewClose&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see ReviewClose
 * @generated
 */
public class ReviewCloseTable extends BaseTable<ReviewCloseTable> {

	public static final ReviewCloseTable INSTANCE = new ReviewCloseTable();

	public final Column<ReviewCloseTable, Long> closeId = createColumn(
		"closeId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<ReviewCloseTable, Long> reviewId = createColumn(
		"reviewId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<ReviewCloseTable, String> requestBy = createColumn(
		"requestBy", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ReviewCloseTable, Date> requestDate = createColumn(
		"requestDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<ReviewCloseTable, Date> actionDate = createColumn(
		"actionDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<ReviewCloseTable, Integer> status = createColumn(
		"status", Integer.class, Types.INTEGER, Column.FLAG_DEFAULT);

	private ReviewCloseTable() {
		super("PERFORMANCE_ReviewClose", ReviewCloseTable::new);
	}

}