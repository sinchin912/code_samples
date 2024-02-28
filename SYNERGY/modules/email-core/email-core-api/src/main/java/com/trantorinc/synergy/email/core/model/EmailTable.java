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

package com.trantorinc.synergy.email.core.model;

import com.liferay.petra.sql.dsl.Column;
import com.liferay.petra.sql.dsl.base.BaseTable;

import java.sql.Types;

import java.util.Date;

/**
 * The table class for the &quot;EMAIL_Email&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see Email
 * @generated
 */
public class EmailTable extends BaseTable<EmailTable> {

	public static final EmailTable INSTANCE = new EmailTable();

	public final Column<EmailTable, Long> emailId = createColumn(
		"emailId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<EmailTable, Boolean> scheduled = createColumn(
		"scheduled", Boolean.class, Types.BOOLEAN, Column.FLAG_DEFAULT);
	public final Column<EmailTable, String> module = createColumn(
		"module", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<EmailTable, String> toAddress = createColumn(
		"toAddress", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<EmailTable, String> ccAddress = createColumn(
		"ccAddress", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<EmailTable, String> bccAddress = createColumn(
		"bccAddress", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<EmailTable, String> subject = createColumn(
		"subject", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<EmailTable, String> body = createColumn(
		"body", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<EmailTable, Date> createdDate = createColumn(
		"createdDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<EmailTable, Boolean> sent = createColumn(
		"sent", Boolean.class, Types.BOOLEAN, Column.FLAG_DEFAULT);

	private EmailTable() {
		super("EMAIL_Email", EmailTable::new);
	}

}