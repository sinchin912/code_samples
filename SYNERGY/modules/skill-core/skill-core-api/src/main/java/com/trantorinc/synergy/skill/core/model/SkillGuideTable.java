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
 * The table class for the &quot;SKILL_SkillGuide&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see SkillGuide
 * @generated
 */
public class SkillGuideTable extends BaseTable<SkillGuideTable> {

	public static final SkillGuideTable INSTANCE = new SkillGuideTable();

	public final Column<SkillGuideTable, Long> guideId = createColumn(
		"guideId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<SkillGuideTable, String> coreSkill = createColumn(
		"coreSkill", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<SkillGuideTable, String> subSkill = createColumn(
		"subSkill", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);

	private SkillGuideTable() {
		super("SKILL_SkillGuide", SkillGuideTable::new);
	}

}