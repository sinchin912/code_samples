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

import java.util.Date;

/**
 * The table class for the &quot;PROBATION_Probation&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see Probation
 * @generated
 */
public class ProbationTable extends BaseTable<ProbationTable> {

	public static final ProbationTable INSTANCE = new ProbationTable();

	public final Column<ProbationTable, String> ecode = createColumn(
		"ecode", String.class, Types.VARCHAR, Column.FLAG_PRIMARY);
	public final Column<ProbationTable, String> manager = createColumn(
		"manager", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ProbationTable, String> reviewer = createColumn(
		"reviewer", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ProbationTable, Integer> state = createColumn(
		"state_", Integer.class, Types.INTEGER, Column.FLAG_DEFAULT);
	public final Column<ProbationTable, String> stateName = createColumn(
		"stateName", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ProbationTable, Date> alertDate = createColumn(
		"alertDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<ProbationTable, Date> createDate = createColumn(
		"createDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<ProbationTable, Date> updateDate = createColumn(
		"updateDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<ProbationTable, String> areaOfStrength = createColumn(
		"areaOfStrength", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ProbationTable, String> areaOfImprovement =
		createColumn(
			"areaOfImprovement", String.class, Types.VARCHAR,
			Column.FLAG_DEFAULT);
	public final Column<ProbationTable, String> comment = createColumn(
		"comment_", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);

	private ProbationTable() {
		super("PROBATION_Probation", ProbationTable::new);
	}

}