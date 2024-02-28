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

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The extended model interface for the GameTimeline service. Represents a row in the &quot;GAME_GameTimeline&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see GameTimelineModel
 * @generated
 */
@ImplementationClassName(
	"com.trantorinc.synergy.game.core.model.impl.GameTimelineImpl"
)
@ProviderType
public interface GameTimeline extends GameTimelineModel, PersistedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.trantorinc.synergy.game.core.model.impl.GameTimelineImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<GameTimeline, String> NAME_ACCESSOR =
		new Accessor<GameTimeline, String>() {

			@Override
			public String get(GameTimeline gameTimeline) {
				return gameTimeline.getName();
			}

			@Override
			public Class<String> getAttributeClass() {
				return String.class;
			}

			@Override
			public Class<GameTimeline> getTypeClass() {
				return GameTimeline.class;
			}

		};

}