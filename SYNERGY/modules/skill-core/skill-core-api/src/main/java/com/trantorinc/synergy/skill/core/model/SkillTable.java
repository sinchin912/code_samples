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

/**
 * The table class for the &quot;SKILL_Skill&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see Skill
 * @generated
 */
public class SkillTable extends BaseTable<SkillTable> {

	public static final SkillTable INSTANCE = new SkillTable();

	public final Column<SkillTable, Long> skillId = createColumn(
		"skillId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<SkillTable, String> ecode = createColumn(
		"ecode", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<SkillTable, String> project = createColumn(
		"project", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<SkillTable, String> coreSkill = createColumn(
		"coreSkill", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<SkillTable, String> subSkill = createColumn(
		"subSkill", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<SkillTable, String> tool = createColumn(
		"tool", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<SkillTable, String> validity = createColumn(
		"validity", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<SkillTable, String> version = createColumn(
		"version", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<SkillTable, Integer> status = createColumn(
		"status", Integer.class, Types.INTEGER, Column.FLAG_DEFAULT);
	public final Column<SkillTable, String> reviewer = createColumn(
		"reviewer", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<SkillTable, String> manager = createColumn(
		"manager", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<SkillTable, Boolean> primarySkill = createColumn(
		"primarySkill", Boolean.class, Types.BOOLEAN, Column.FLAG_DEFAULT);
	public final Column<SkillTable, Integer> proficiencyLevel = createColumn(
		"proficiencyLevel", Integer.class, Types.INTEGER, Column.FLAG_DEFAULT);
	public final Column<SkillTable, Integer> requiredLevel = createColumn(
		"requiredLevel", Integer.class, Types.INTEGER, Column.FLAG_DEFAULT);

	private SkillTable() {
		super("SKILL_Skill", SkillTable::new);
	}

}