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

/**
 * The table class for the &quot;EMAIL_EmailAttachment&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see EmailAttachment
 * @generated
 */
public class EmailAttachmentTable extends BaseTable<EmailAttachmentTable> {

	public static final EmailAttachmentTable INSTANCE =
		new EmailAttachmentTable();

	public final Column<EmailAttachmentTable, Long> emailAttachmentId =
		createColumn(
			"emailAttachmentId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<EmailAttachmentTable, Long> emailId = createColumn(
		"emailId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<EmailAttachmentTable, String> attachmentName =
		createColumn(
			"attachmentName", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<EmailAttachmentTable, String> attachmentFileId =
		createColumn(
			"attachmentFileId", String.class, Types.VARCHAR,
			Column.FLAG_DEFAULT);

	private EmailAttachmentTable() {
		super("EMAIL_EmailAttachment", EmailAttachmentTable::new);
	}

}