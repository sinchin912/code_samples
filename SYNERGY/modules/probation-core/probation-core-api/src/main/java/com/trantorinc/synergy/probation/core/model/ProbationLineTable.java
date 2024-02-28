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

package com.trantorinc.synergy.probation.core.model;

import com.liferay.petra.sql.dsl.Column;
import com.liferay.petra.sql.dsl.base.BaseTable;

import java.sql.Types;

/**
 * The table class for the &quot;PROBATION_ProbationLine&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see ProbationLine
 * @generated
 */
public class ProbationLineTable extends BaseTable<ProbationLineTable> {

	public static final ProbationLineTable INSTANCE = new ProbationLineTable();

	public final Column<ProbationLineTable, Long> lineId = createColumn(
		"lineId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<ProbationLineTable, String> probationId = createColumn(
		"probationId", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ProbationLineTable, Integer> lineNumber = createColumn(
		"lineNumber", Integer.class, Types.INTEGER, Column.FLAG_DEFAULT);
	public final Column<ProbationLineTable, Integer> rating = createColumn(
		"rating", Integer.class, Types.INTEGER, Column.FLAG_DEFAULT);
	public final Column<ProbationLineTable, String> comment = createColumn(
		"comment_", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);

	private ProbationLineTable() {
		super("PROBATION_ProbationLine", ProbationLineTable::new);
	}

}