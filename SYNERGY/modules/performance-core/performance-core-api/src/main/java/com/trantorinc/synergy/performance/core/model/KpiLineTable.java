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
 * The table class for the &quot;PERFORMANCE_KpiLine&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see KpiLine
 * @generated
 */
public class KpiLineTable extends BaseTable<KpiLineTable> {

	public static final KpiLineTable INSTANCE = new KpiLineTable();

	public final Column<KpiLineTable, Long> lineId = createColumn(
		"lineId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<KpiLineTable, Long> kpiId = createColumn(
		"kpiId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<KpiLineTable, Long> guideId = createColumn(
		"guideId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<KpiLineTable, String> title = createColumn(
		"title", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<KpiLineTable, String> description = createColumn(
		"description", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<KpiLineTable, String> target = createColumn(
		"target", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<KpiLineTable, Date> createDate = createColumn(
		"createDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);

	private KpiLineTable() {
		super("PERFORMANCE_KpiLine", KpiLineTable::new);
	}

}