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
 * The table class for the &quot;NOTICE_ExitForm&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see ExitForm
 * @generated
 */
public class ExitFormTable extends BaseTable<ExitFormTable> {

	public static final ExitFormTable INSTANCE = new ExitFormTable();

	public final Column<ExitFormTable, Long> id = createColumn(
		"id_", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<ExitFormTable, Long> resignationId = createColumn(
		"resignationId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<ExitFormTable, Boolean> managerFormStatus =
		createColumn(
			"managerFormStatus", Boolean.class, Types.BOOLEAN,
			Column.FLAG_DEFAULT);
	public final Column<ExitFormTable, Boolean> hrFormStatus = createColumn(
		"hrFormStatus", Boolean.class, Types.BOOLEAN, Column.FLAG_DEFAULT);
	public final Column<ExitFormTable, Boolean> itFormStatus = createColumn(
		"itFormStatus", Boolean.class, Types.BOOLEAN, Column.FLAG_DEFAULT);
	public final Column<ExitFormTable, Boolean> adminFormStatus = createColumn(
		"adminFormStatus", Boolean.class, Types.BOOLEAN, Column.FLAG_DEFAULT);
	public final Column<ExitFormTable, Boolean> financeFormStatus =
		createColumn(
			"financeFormStatus", Boolean.class, Types.BOOLEAN,
			Column.FLAG_DEFAULT);

	private ExitFormTable() {
		super("NOTICE_ExitForm", ExitFormTable::new);
	}

}