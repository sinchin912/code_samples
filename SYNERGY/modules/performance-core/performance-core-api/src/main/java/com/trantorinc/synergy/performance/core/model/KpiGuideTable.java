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
 * The table class for the &quot;PERFORMANCE_KpiGuide&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see KpiGuide
 * @generated
 */
public class KpiGuideTable extends BaseTable<KpiGuideTable> {

	public static final KpiGuideTable INSTANCE = new KpiGuideTable();

	public final Column<KpiGuideTable, Long> guideId = createColumn(
		"guideId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<KpiGuideTable, String> title = createColumn(
		"title", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<KpiGuideTable, String> description = createColumn(
		"description", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<KpiGuideTable, Boolean> attribute = createColumn(
		"attribute", Boolean.class, Types.BOOLEAN, Column.FLAG_DEFAULT);
	public final Column<KpiGuideTable, Boolean> other = createColumn(
		"other", Boolean.class, Types.BOOLEAN, Column.FLAG_DEFAULT);
	public final Column<KpiGuideTable, Boolean> mandatory = createColumn(
		"mandatory", Boolean.class, Types.BOOLEAN, Column.FLAG_DEFAULT);

	private KpiGuideTable() {
		super("PERFORMANCE_KpiGuide", KpiGuideTable::new);
	}

}