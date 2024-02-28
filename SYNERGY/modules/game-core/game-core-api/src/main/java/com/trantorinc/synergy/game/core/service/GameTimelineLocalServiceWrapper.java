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

package com.trantorinc.synergy.game.core.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link GameTimelineLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see GameTimelineLocalService
 * @generated
 */
public class GameTimelineLocalServiceWrapper
	implements GameTimelineLocalService,
			   ServiceWrapper<GameTimelineLocalService> {

	public GameTimelineLocalServiceWrapper() {
		this(null);
	}

	public GameTimelineLocalServiceWrapper(
		GameTimelineLocalService gameTimelineLocalService) {

		_gameTimelineLocalService = gameTimelineLocalService;
	}

	/**
	 * Adds the game timeline to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect GameTimelineLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param gameTimeline the game timeline
	 * @return the game timeline that was added
	 */
	@Override
	public com.trantorinc.synergy.game.core.model.GameTimeline addGameTimeline(
		com.trantorinc.synergy.game.core.model.GameTimeline gameTimeline) {

		return _gameTimelineLocalService.addGameTimeline(gameTimeline);
	}

	/**
	 * Creates a new game timeline with the primary key. Does not add the game timeline to the database.
	 *
	 * @param name the primary key for the new game timeline
	 * @return the new game timeline
	 */
	@Override
	public com.trantorinc.synergy.game.core.model.GameTimeline
		createGameTimeline(String name) {

		return _gameTimelineLocalService.createGameTimeline(name);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _gameTimelineLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Deletes the game timeline from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect GameTimelineLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param gameTimeline the game timeline
	 * @return the game timeline that was removed
	 */
	@Override
	public com.trantorinc.synergy.game.core.model.GameTimeline
		deleteGameTimeline(
			com.trantorinc.synergy.game.core.model.GameTimeline gameTimeline) {

		return _gameTimelineLocalService.deleteGameTimeline(gameTimeline);
	}

	/**
	 * Deletes the game timeline with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect GameTimelineLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param name the primary key of the game timeline
	 * @return the game timeline that was removed
	 * @throws PortalException if a game timeline with the primary key could not be found
	 */
	@Override
	public com.trantorinc.synergy.game.core.model.GameTimeline
			deleteGameTimeline(String name)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _gameTimelineLocalService.deleteGameTimeline(name);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _gameTimelineLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _gameTimelineLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _gameTimelineLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _gameTimelineLocalService.dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _gameTimelineLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.trantorinc.synergy.game.core.model.impl.GameTimelineModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return _gameTimelineLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.trantorinc.synergy.game.core.model.impl.GameTimelineModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return _gameTimelineLocalService.dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _gameTimelineLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return _gameTimelineLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.trantorinc.synergy.game.core.model.GameTimeline
		fetchGameTimeline(String name) {

		return _gameTimelineLocalService.fetchGameTimeline(name);
	}

	@Override
	public java.util.List<com.trantorinc.synergy.game.core.model.GameTimeline>
		getCalibratedTimeline() {

		return _gameTimelineLocalService.getCalibratedTimeline();
	}

	/**
	 * Returns the game timeline with the primary key.
	 *
	 * @param name the primary key of the game timeline
	 * @return the game timeline
	 * @throws PortalException if a game timeline with the primary key could not be found
	 */
	@Override
	public com.trantorinc.synergy.game.core.model.GameTimeline getGameTimeline(
			String name)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _gameTimelineLocalService.getGameTimeline(name);
	}

	/**
	 * Returns a range of all the game timelines.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.trantorinc.synergy.game.core.model.impl.GameTimelineModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of game timelines
	 * @param end the upper bound of the range of game timelines (not inclusive)
	 * @return the range of game timelines
	 */
	@Override
	public java.util.List<com.trantorinc.synergy.game.core.model.GameTimeline>
		getGameTimelines(int start, int end) {

		return _gameTimelineLocalService.getGameTimelines(start, end);
	}

	/**
	 * Returns the number of game timelines.
	 *
	 * @return the number of game timelines
	 */
	@Override
	public int getGameTimelinesCount() {
		return _gameTimelineLocalService.getGameTimelinesCount();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _gameTimelineLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _gameTimelineLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the game timeline in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect GameTimelineLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param gameTimeline the game timeline
	 * @return the game timeline that was updated
	 */
	@Override
	public com.trantorinc.synergy.game.core.model.GameTimeline
		updateGameTimeline(
			com.trantorinc.synergy.game.core.model.GameTimeline gameTimeline) {

		return _gameTimelineLocalService.updateGameTimeline(gameTimeline);
	}

	@Override
	public GameTimelineLocalService getWrappedService() {
		return _gameTimelineLocalService;
	}

	@Override
	public void setWrappedService(
		GameTimelineLocalService gameTimelineLocalService) {

		_gameTimelineLocalService = gameTimelineLocalService;
	}

	private GameTimelineLocalService _gameTimelineLocalService;

}