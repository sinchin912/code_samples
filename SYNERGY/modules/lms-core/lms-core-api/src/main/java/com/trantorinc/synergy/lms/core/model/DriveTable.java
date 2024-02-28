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

package com.trantorinc.synergy.lms.core.model;

import com.liferay.petra.sql.dsl.Column;
import com.liferay.petra.sql.dsl.base.BaseTable;

import java.sql.Types;

import java.util.Date;

/**
 * The table class for the &quot;LMS_Drive&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see Drive
 * @generated
 */
public class DriveTable extends BaseTable<DriveTable> {

	public static final DriveTable INSTANCE = new DriveTable();

	public final Column<DriveTable, Long> driveId = createColumn(
		"driveId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<DriveTable, String> folderName = createColumn(
		"folderName", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<DriveTable, String> folderId = createColumn(
		"folderId", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<DriveTable, Integer> filesCount = createColumn(
		"filesCount", Integer.class, Types.INTEGER, Column.FLAG_DEFAULT);
	public final Column<DriveTable, Date> updateDate = createColumn(
		"updateDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);

	private DriveTable() {
		super("LMS_Drive", DriveTable::new);
	}

}