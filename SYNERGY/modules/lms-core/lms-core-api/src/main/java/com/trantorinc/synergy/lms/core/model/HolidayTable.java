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

package com.trantorinc.synergy.lms.core.model;

import com.liferay.petra.sql.dsl.Column;
import com.liferay.petra.sql.dsl.base.BaseTable;

import java.sql.Types;

import java.util.Date;

/**
 * The table class for the &quot;LMS_Holiday&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see Holiday
 * @generated
 */
public class HolidayTable extends BaseTable<HolidayTable> {

	public static final HolidayTable INSTANCE = new HolidayTable();

	public final Column<HolidayTable, Long> holidayId = createColumn(
		"holidayId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<HolidayTable, Integer> holidayYear = createColumn(
		"holidayYear", Integer.class, Types.INTEGER, Column.FLAG_DEFAULT);
	public final Column<HolidayTable, Date> onDate = createColumn(
		"onDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<HolidayTable, String> name = createColumn(
		"name", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);

	private HolidayTable() {
		super("LMS_Holiday", HolidayTable::new);
	}

}