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
 * The table class for the &quot;SKILL_SkillRejectionComment&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see SkillRejectionComment
 * @generated
 */
public class SkillRejectionCommentTable
	extends BaseTable<SkillRejectionCommentTable> {

	public static final SkillRejectionCommentTable INSTANCE =
		new SkillRejectionCommentTable();

	public final Column<SkillRejectionCommentTable, Long> commentId =
		createColumn(
			"commentId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<SkillRejectionCommentTable, String> ecode =
		createColumn("ecode", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<SkillRejectionCommentTable, String> author =
		createColumn(
			"author", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<SkillRejectionCommentTable, String> description =
		createColumn(
			"description", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<SkillRejectionCommentTable, Date> createdDate =
		createColumn(
			"createdDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);

	private SkillRejectionCommentTable() {
		super("SKILL_SkillRejectionComment", SkillRejectionCommentTable::new);
	}

}