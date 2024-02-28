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

import java.util.Date;

/**
 * The table class for the &quot;NOTICE_ManagerForm&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see ManagerForm
 * @generated
 */
public class ManagerFormTable extends BaseTable<ManagerFormTable> {

	public static final ManagerFormTable INSTANCE = new ManagerFormTable();

	public final Column<ManagerFormTable, Long> id = createColumn(
		"id_", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<ManagerFormTable, Long> exitId = createColumn(
		"exitId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<ManagerFormTable, Integer> ticketNo = createColumn(
		"ticketNo", Integer.class, Types.INTEGER, Column.FLAG_DEFAULT);
	public final Column<ManagerFormTable, String> ticketNoRemark = createColumn(
		"ticketNoRemark", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ManagerFormTable, Integer> separationDocument =
		createColumn(
			"separationDocument", Integer.class, Types.INTEGER,
			Column.FLAG_DEFAULT);
	public final Column<ManagerFormTable, String> separationDocumentRemark =
		createColumn(
			"separationDocumentRemark", String.class, Types.VARCHAR,
			Column.FLAG_DEFAULT);
	public final Column<ManagerFormTable, Date> updatedDate = createColumn(
		"updatedDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);

	private ManagerFormTable() {
		super("NOTICE_ManagerForm", ManagerFormTable::new);
	}

}