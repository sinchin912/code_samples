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

package com.trantorinc.synergy.game.core.service.persistence;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import com.trantorinc.synergy.game.core.exception.NoSuchGameTimelineException;
import com.trantorinc.synergy.game.core.model.GameTimeline;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the game timeline service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see GameTimelineUtil
 * @generated
 */
@ProviderType
public interface GameTimelinePersistence extends BasePersistence<GameTimeline> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link GameTimelineUtil} to access the game timeline persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Caches the game timeline in the entity cache if it is enabled.
	 *
	 * @param gameTimeline the game timeline
	 */
	public void cacheResult(GameTimeline gameTimeline);

	/**
	 * Caches the game timelines in the entity cache if it is enabled.
	 *
	 * @param gameTimelines the game timelines
	 */
	public void cacheResult(java.util.List<GameTimeline> gameTimelines);

	/**
	 * Creates a new game timeline with the primary key. Does not add the game timeline to the database.
	 *
	 * @param name the primary key for the new game timeline
	 * @return the new game timeline
	 */
	public GameTimeline create(String name);

	/**
	 * Removes the game timeline with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param name the primary key of the game timeline
	 * @return the game timeline that was removed
	 * @throws NoSuchGameTimelineException if a game timeline with the primary key could not be found
	 */
	public GameTimeline remove(String name) throws NoSuchGameTimelineException;

	public GameTimeline updateImpl(GameTimeline gameTimeline);

	/**
	 * Returns the game timeline with the primary key or throws a <code>NoSuchGameTimelineException</code> if it could not be found.
	 *
	 * @param name the primary key of the game timeline
	 * @return the game timeline
	 * @throws NoSuchGameTimelineException if a game timeline with the primary key could not be found
	 */
	public GameTimeline findByPrimaryKey(String name)
		throws NoSuchGameTimelineException;

	/**
	 * Returns the game timeline with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param name the primary key of the game timeline
	 * @return the game timeline, or <code>null</code> if a game timeline with the primary key could not be found
	 */
	public GameTimeline fetchByPrimaryKey(String name);

	/**
	 * Returns all the game timelines.
	 *
	 * @return the game timelines
	 */
	public java.util.List<GameTimeline> findAll();

	/**
	 * Returns a range of all the game timelines.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>GameTimelineModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of game timelines
	 * @param end the upper bound of the range of game timelines (not inclusive)
	 * @return the range of game timelines
	 */
	public java.util.List<GameTimeline> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the game timelines.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>GameTimelineModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of game timelines
	 * @param end the upper bound of the range of game timelines (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of game timelines
	 */
	public java.util.List<GameTimeline> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<GameTimeline>
			orderByComparator);

	/**
	 * Returns an ordered range of all the game timelines.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>GameTimelineModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of game timelines
	 * @param end the upper bound of the range of game timelines (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of game timelines
	 */
	public java.util.List<GameTimeline> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<GameTimeline>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the game timelines from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of game timelines.
	 *
	 * @return the number of game timelines
	 */
	public int countAll();

}