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
 * The table class for the &quot;GAME_Santa&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see Santa
 * @generated
 */
public class SantaTable extends BaseTable<SantaTable> {

	public static final SantaTable INSTANCE = new SantaTable();

	public final Column<SantaTable, Long> santaId = createColumn(
		"santaId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<SantaTable, Integer> year = createColumn(
		"year", Integer.class, Types.INTEGER, Column.FLAG_DEFAULT);
	public final Column<SantaTable, String> ecode = createColumn(
		"ecode", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<SantaTable, String> mobile = createColumn(
		"mobile", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<SantaTable, String> city = createColumn(
		"city", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<SantaTable, String> state = createColumn(
		"state_", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<SantaTable, String> pincode = createColumn(
		"pincode", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<SantaTable, String> postalAddress = createColumn(
		"postalAddress", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<SantaTable, String> santaEcode = createColumn(
		"santaEcode", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<SantaTable, String> guessedEcode = createColumn(
		"guessedEcode", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<SantaTable, Boolean> giftSent = createColumn(
		"giftSent", Boolean.class, Types.BOOLEAN, Column.FLAG_DEFAULT);
	public final Column<SantaTable, Boolean> emailSent = createColumn(
		"emailSent", Boolean.class, Types.BOOLEAN, Column.FLAG_DEFAULT);
	public final Column<SantaTable, String> fileId = createColumn(
		"fileId", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<SantaTable, Date> createDatetime = createColumn(
		"createDatetime", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);

	private SantaTable() {
		super("GAME_Santa", SantaTable::new);
	}

}