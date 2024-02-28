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

package com.trantorinc.synergy.game.core.model;

import com.liferay.petra.sql.dsl.Column;
import com.liferay.petra.sql.dsl.base.BaseTable;

import java.sql.Types;

import java.util.Date;

/**
 * The table class for the &quot;GAME_Ticket&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see Ticket
 * @generated
 */
public class TicketTable extends BaseTable<TicketTable> {

	public static final TicketTable INSTANCE = new TicketTable();

	public final Column<TicketTable, Long> ticketId = createColumn(
		"ticketId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<TicketTable, Integer> year = createColumn(
		"year", Integer.class, Types.INTEGER, Column.FLAG_DEFAULT);
	public final Column<TicketTable, String> ecode = createColumn(
		"ecode", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<TicketTable, String> ticketNumber = createColumn(
		"ticketNumber", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<TicketTable, Date> createDatetime = createColumn(
		"createDatetime", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<TicketTable, Boolean> draw = createColumn(
		"draw", Boolean.class, Types.BOOLEAN, Column.FLAG_DEFAULT);

	private TicketTable() {
		super("GAME_Ticket", TicketTable::new);
	}

}