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
 * The table class for the &quot;GAME_GameTimeline&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see GameTimeline
 * @generated
 */
public class GameTimelineTable extends BaseTable<GameTimelineTable> {

	public static final GameTimelineTable INSTANCE = new GameTimelineTable();

	public final Column<GameTimelineTable, String> name = createColumn(
		"name", String.class, Types.VARCHAR, Column.FLAG_PRIMARY);
	public final Column<GameTimelineTable, Date> openDate = createColumn(
		"openDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<GameTimelineTable, Date> freezeDate = createColumn(
		"freezeDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<GameTimelineTable, Date> actionDate = createColumn(
		"actionDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<GameTimelineTable, Date> closeDate = createColumn(
		"closeDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);

	private GameTimelineTable() {
		super("GAME_GameTimeline", GameTimelineTable::new);
	}

}