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
 * The table class for the &quot;PERFORMANCE_ReviewUpload&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see ReviewUpload
 * @generated
 */
public class ReviewUploadTable extends BaseTable<ReviewUploadTable> {

	public static final ReviewUploadTable INSTANCE = new ReviewUploadTable();

	public final Column<ReviewUploadTable, Long> uploadId = createColumn(
		"uploadId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<ReviewUploadTable, Long> reviewId = createColumn(
		"reviewId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<ReviewUploadTable, String> attachmentName =
		createColumn(
			"attachmentName", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ReviewUploadTable, String> fileId = createColumn(
		"fileId", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ReviewUploadTable, Date> createdDate = createColumn(
		"createdDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);

	private ReviewUploadTable() {
		super("PERFORMANCE_ReviewUpload", ReviewUploadTable::new);
	}

}