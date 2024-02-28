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
 * The table class for the &quot;PERFORMANCE_ReviewRollback&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see ReviewRollback
 * @generated
 */
public class ReviewRollbackTable extends BaseTable<ReviewRollbackTable> {

	public static final ReviewRollbackTable INSTANCE =
		new ReviewRollbackTable();

	public final Column<ReviewRollbackTable, Long> rollbackId = createColumn(
		"rollbackId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<ReviewRollbackTable, Long> reviewId = createColumn(
		"reviewId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<ReviewRollbackTable, String> requestBy = createColumn(
		"requestBy", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ReviewRollbackTable, Date> requestDate = createColumn(
		"requestDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<ReviewRollbackTable, Date> actionDate = createColumn(
		"actionDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<ReviewRollbackTable, Integer> status = createColumn(
		"status", Integer.class, Types.INTEGER, Column.FLAG_DEFAULT);

	private ReviewRollbackTable() {
		super("PERFORMANCE_ReviewRollback", ReviewRollbackTable::new);
	}

}