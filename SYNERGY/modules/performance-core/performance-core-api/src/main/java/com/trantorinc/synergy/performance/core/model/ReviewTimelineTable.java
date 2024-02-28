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
 * The table class for the &quot;PERFORMANCE_ReviewTimeline&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see ReviewTimeline
 * @generated
 */
public class ReviewTimelineTable extends BaseTable<ReviewTimelineTable> {

	public static final ReviewTimelineTable INSTANCE =
		new ReviewTimelineTable();

	public final Column<ReviewTimelineTable, Long> timelineId = createColumn(
		"timelineId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<ReviewTimelineTable, Integer> reviewState =
		createColumn(
			"reviewState", Integer.class, Types.INTEGER, Column.FLAG_DEFAULT);
	public final Column<ReviewTimelineTable, String> stateName = createColumn(
		"stateName", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ReviewTimelineTable, Date> endDate = createColumn(
		"endDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<ReviewTimelineTable, Boolean> finalYear = createColumn(
		"finalYear", Boolean.class, Types.BOOLEAN, Column.FLAG_DEFAULT);

	private ReviewTimelineTable() {
		super("PERFORMANCE_ReviewTimeline", ReviewTimelineTable::new);
	}

}