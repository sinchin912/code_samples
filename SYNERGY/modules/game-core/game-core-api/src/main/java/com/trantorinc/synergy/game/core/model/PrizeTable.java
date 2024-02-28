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

/**
 * The table class for the &quot;GAME_Prize&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see Prize
 * @generated
 */
public class PrizeTable extends BaseTable<PrizeTable> {

	public static final PrizeTable INSTANCE = new PrizeTable();

	public final Column<PrizeTable, Long> prizeId = createColumn(
		"prizeId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<PrizeTable, Integer> year = createColumn(
		"year", Integer.class, Types.INTEGER, Column.FLAG_DEFAULT);
	public final Column<PrizeTable, Integer> sequence = createColumn(
		"sequence", Integer.class, Types.INTEGER, Column.FLAG_DEFAULT);
	public final Column<PrizeTable, String> description = createColumn(
		"description", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<PrizeTable, String> winner = createColumn(
		"winner", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<PrizeTable, Long> ticketId = createColumn(
		"ticketId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<PrizeTable, Boolean> surprise = createColumn(
		"surprise", Boolean.class, Types.BOOLEAN, Column.FLAG_DEFAULT);
	public final Column<PrizeTable, String> fileId = createColumn(
		"fileId", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);

	private PrizeTable() {
		super("GAME_Prize", PrizeTable::new);
	}

}