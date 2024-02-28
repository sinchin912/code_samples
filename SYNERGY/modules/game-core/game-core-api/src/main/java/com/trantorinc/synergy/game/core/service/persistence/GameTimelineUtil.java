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

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.trantorinc.synergy.game.core.model.GameTimeline;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the game timeline service. This utility wraps <code>com.trantorinc.synergy.game.core.service.persistence.impl.GameTimelinePersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see GameTimelinePersistence
 * @generated
 */
public class GameTimelineUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(GameTimeline gameTimeline) {
		getPersistence().clearCache(gameTimeline);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#fetchByPrimaryKeys(Set)
	 */
	public static Map<Serializable, GameTimeline> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<GameTimeline> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<GameTimeline> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<GameTimeline> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<GameTimeline> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static GameTimeline update(GameTimeline gameTimeline) {
		return getPersistence().update(gameTimeline);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static GameTimeline update(
		GameTimeline gameTimeline, ServiceContext serviceContext) {

		return getPersistence().update(gameTimeline, serviceContext);
	}

	/**
	 * Caches the game timeline in the entity cache if it is enabled.
	 *
	 * @param gameTimeline the game timeline
	 */
	public static void cacheResult(GameTimeline gameTimeline) {
		getPersistence().cacheResult(gameTimeline);
	}

	/**
	 * Caches the game timelines in the entity cache if it is enabled.
	 *
	 * @param gameTimelines the game timelines
	 */
	public static void cacheResult(List<GameTimeline> gameTimelines) {
		getPersistence().cacheResult(gameTimelines);
	}

	/**
	 * Creates a new game timeline with the primary key. Does not add the game timeline to the database.
	 *
	 * @param name the primary key for the new game timeline
	 * @return the new game timeline
	 */
	public static GameTimeline create(String name) {
		return getPersistence().create(name);
	}

	/**
	 * Removes the game timeline with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param name the primary key of the game timeline
	 * @return the game timeline that was removed
	 * @throws NoSuchGameTimelineException if a game timeline with the primary key could not be found
	 */
	public static GameTimeline remove(String name)
		throws com.trantorinc.synergy.game.core.exception.
			NoSuchGameTimelineException {

		return getPersistence().remove(name);
	}

	public static GameTimeline updateImpl(GameTimeline gameTimeline) {
		return getPersistence().updateImpl(gameTimeline);
	}

	/**
	 * Returns the game timeline with the primary key or throws a <code>NoSuchGameTimelineException</code> if it could not be found.
	 *
	 * @param name the primary key of the game timeline
	 * @return the game timeline
	 * @throws NoSuchGameTimelineException if a game timeline with the primary key could not be found
	 */
	public static GameTimeline findByPrimaryKey(String name)
		throws com.trantorinc.synergy.game.core.exception.
			NoSuchGameTimelineException {

		return getPersistence().findByPrimaryKey(name);
	}

	/**
	 * Returns the game timeline with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param name the primary key of the game timeline
	 * @return the game timeline, or <code>null</code> if a game timeline with the primary key could not be found
	 */
	public static GameTimeline fetchByPrimaryKey(String name) {
		return getPersistence().fetchByPrimaryKey(name);
	}

	/**
	 * Returns all the game timelines.
	 *
	 * @return the game timelines
	 */
	public static List<GameTimeline> findAll() {
		return getPersistence().findAll();
	}

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
	public static List<GameTimeline> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

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
	public static List<GameTimeline> findAll(
		int start, int end, OrderByComparator<GameTimeline> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

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
	public static List<GameTimeline> findAll(
		int start, int end, OrderByComparator<GameTimeline> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the game timelines from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of game timelines.
	 *
	 * @return the number of game timelines
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static GameTimelinePersistence getPersistence() {
		return _persistence;
	}

	private static volatile GameTimelinePersistence _persistence;

}