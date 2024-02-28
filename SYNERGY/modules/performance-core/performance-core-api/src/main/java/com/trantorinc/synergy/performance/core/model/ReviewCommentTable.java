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
 * The table class for the &quot;PERFORMANCE_ReviewComment&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see ReviewComment
 * @generated
 */
public class ReviewCommentTable extends BaseTable<ReviewCommentTable> {

	public static final ReviewCommentTable INSTANCE = new ReviewCommentTable();

	public final Column<ReviewCommentTable, Long> commentId = createColumn(
		"commentId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<ReviewCommentTable, Long> reviewId = createColumn(
		"reviewId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<ReviewCommentTable, String> author = createColumn(
		"author", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ReviewCommentTable, String> description = createColumn(
		"description", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ReviewCommentTable, Date> createdDate = createColumn(
		"createdDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);

	private ReviewCommentTable() {
		super("PERFORMANCE_ReviewComment", ReviewCommentTable::new);
	}

}