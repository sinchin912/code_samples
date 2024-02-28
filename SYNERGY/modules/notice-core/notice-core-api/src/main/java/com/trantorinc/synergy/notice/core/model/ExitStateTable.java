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

package com.trantorinc.synergy.notice.core.model;

import com.liferay.petra.sql.dsl.Column;
import com.liferay.petra.sql.dsl.base.BaseTable;

import java.sql.Types;

/**
 * The table class for the &quot;NOTICE_ExitState&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see ExitState
 * @generated
 */
public class ExitStateTable extends BaseTable<ExitStateTable> {

	public static final ExitStateTable INSTANCE = new ExitStateTable();

	public final Column<ExitStateTable, Long> exitStateId = createColumn(
		"exitStateId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<ExitStateTable, Integer> exitStateKey = createColumn(
		"exitStateKey", Integer.class, Types.INTEGER, Column.FLAG_DEFAULT);
	public final Column<ExitStateTable, String> exitStateDescription =
		createColumn(
			"exitStateDescription", String.class, Types.VARCHAR,
			Column.FLAG_DEFAULT);
	public final Column<ExitStateTable, String> exitStateDisplay = createColumn(
		"exitStateDisplay", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);

	private ExitStateTable() {
		super("NOTICE_ExitState", ExitStateTable::new);
	}

}