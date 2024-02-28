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

package com.trantorinc.synergy.skill.core.model;

import com.liferay.petra.sql.dsl.Column;
import com.liferay.petra.sql.dsl.base.BaseTable;

import java.sql.Types;

import java.util.Date;

/**
 * The table class for the &quot;SKILL_Certificate&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see Certificate
 * @generated
 */
public class CertificateTable extends BaseTable<CertificateTable> {

	public static final CertificateTable INSTANCE = new CertificateTable();

	public final Column<CertificateTable, Long> certificateId = createColumn(
		"certificateId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<CertificateTable, String> ecode = createColumn(
		"ecode", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<CertificateTable, String> name = createColumn(
		"name", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<CertificateTable, String> category = createColumn(
		"category", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<CertificateTable, String> description = createColumn(
		"description", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<CertificateTable, String> fileId = createColumn(
		"fileId", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<CertificateTable, String> filename = createColumn(
		"filename", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<CertificateTable, Date> createdDate = createColumn(
		"createdDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<CertificateTable, Date> expiryDate = createColumn(
		"expiryDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);

	private CertificateTable() {
		super("SKILL_Certificate", CertificateTable::new);
	}

}