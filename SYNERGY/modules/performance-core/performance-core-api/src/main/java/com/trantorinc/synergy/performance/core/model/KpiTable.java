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
 * The table class for the &quot;PERFORMANCE_Kpi&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see Kpi
 * @generated
 */
public class KpiTable extends BaseTable<KpiTable> {

	public static final KpiTable INSTANCE = new KpiTable();

	public final Column<KpiTable, Long> kpiId = createColumn(
		"kpiId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<KpiTable, String> ecode = createColumn(
		"ecode", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<KpiTable, String> account = createColumn(
		"account", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<KpiTable, String> managerEmail = createColumn(
		"managerEmail", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<KpiTable, String> reviewerEmail = createColumn(
		"reviewerEmail", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<KpiTable, Boolean> accountPrimary = createColumn(
		"accountPrimary", Boolean.class, Types.BOOLEAN, Column.FLAG_DEFAULT);
	public final Column<KpiTable, Date> updateDate = createColumn(
		"updateDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<KpiTable, Boolean> kpiSettingStatus = createColumn(
		"kpiSettingStatus", Boolean.class, Types.BOOLEAN, Column.FLAG_DEFAULT);
	public final Column<KpiTable, String> rejectionComment = createColumn(
		"rejectionComment", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);

	private KpiTable() {
		super("PERFORMANCE_Kpi", KpiTable::new);
	}

}