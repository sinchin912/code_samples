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

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The extended model interface for the SkillGuide service. Represents a row in the &quot;SKILL_SkillGuide&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see SkillGuideModel
 * @generated
 */
@ImplementationClassName(
	"com.trantorinc.synergy.skill.core.model.impl.SkillGuideImpl"
)
@ProviderType
public interface SkillGuide extends PersistedModel, SkillGuideModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.trantorinc.synergy.skill.core.model.impl.SkillGuideImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<SkillGuide, Long> GUIDE_ID_ACCESSOR =
		new Accessor<SkillGuide, Long>() {

			@Override
			public Long get(SkillGuide skillGuide) {
				return skillGuide.getGuideId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<SkillGuide> getTypeClass() {
				return SkillGuide.class;
			}

		};

}