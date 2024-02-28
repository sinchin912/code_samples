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

package com.trantorinc.synergy.game.core.service.persistence.impl;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.configuration.Configuration;
import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.SessionFactory;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.persistence.BasePersistence;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;

import com.trantorinc.synergy.game.core.exception.NoSuchGameTimelineException;
import com.trantorinc.synergy.game.core.model.GameTimeline;
import com.trantorinc.synergy.game.core.model.GameTimelineTable;
import com.trantorinc.synergy.game.core.model.impl.GameTimelineImpl;
import com.trantorinc.synergy.game.core.model.impl.GameTimelineModelImpl;
import com.trantorinc.synergy.game.core.service.persistence.GameTimelinePersistence;
import com.trantorinc.synergy.game.core.service.persistence.GameTimelineUtil;
import com.trantorinc.synergy.game.core.service.persistence.impl.constants.GAMEPersistenceConstants;

import java.io.Serializable;

import java.lang.reflect.Field;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * The persistence implementation for the game timeline service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = {GameTimelinePersistence.class, BasePersistence.class})
public class GameTimelinePersistenceImpl
	extends BasePersistenceImpl<GameTimeline>
	implements GameTimelinePersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>GameTimelineUtil</code> to access the game timeline persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		GameTimelineImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;

	public GameTimelinePersistenceImpl() {
		setModelClass(GameTimeline.class);

		setModelImplClass(GameTimelineImpl.class);
		setModelPKClass(String.class);

		setTable(GameTimelineTable.INSTANCE);
	}

	/**
	 * Caches the game timeline in the entity cache if it is enabled.
	 *
	 * @param gameTimeline the game timeline
	 */
	@Override
	public void cacheResult(GameTimeline gameTimeline) {
		entityCache.putResult(
			GameTimelineImpl.class, gameTimeline.getPrimaryKey(), gameTimeline);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the game timelines in the entity cache if it is enabled.
	 *
	 * @param gameTimelines the game timelines
	 */
	@Override
	public void cacheResult(List<GameTimeline> gameTimelines) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (gameTimelines.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (GameTimeline gameTimeline : gameTimelines) {
			if (entityCache.getResult(
					GameTimelineImpl.class, gameTimeline.getPrimaryKey()) ==
						null) {

				cacheResult(gameTimeline);
			}
		}
	}

	/**
	 * Clears the cache for all game timelines.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(GameTimelineImpl.class);

		finderCache.clearCache(GameTimelineImpl.class);
	}

	/**
	 * Clears the cache for the game timeline.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(GameTimeline gameTimeline) {
		entityCache.removeResult(GameTimelineImpl.class, gameTimeline);
	}

	@Override
	public void clearCache(List<GameTimeline> gameTimelines) {
		for (GameTimeline gameTimeline : gameTimelines) {
			entityCache.removeResult(GameTimelineImpl.class, gameTimeline);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(GameTimelineImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(GameTimelineImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new game timeline with the primary key. Does not add the game timeline to the database.
	 *
	 * @param name the primary key for the new game timeline
	 * @return the new game timeline
	 */
	@Override
	public GameTimeline create(String name) {
		GameTimeline gameTimeline = new GameTimelineImpl();

		gameTimeline.setNew(true);
		gameTimeline.setPrimaryKey(name);

		return gameTimeline;
	}

	/**
	 * Removes the game timeline with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param name the primary key of the game timeline
	 * @return the game timeline that was removed
	 * @throws NoSuchGameTimelineException if a game timeline with the primary key could not be found
	 */
	@Override
	public GameTimeline remove(String name) throws NoSuchGameTimelineException {
		return remove((Serializable)name);
	}

	/**
	 * Removes the game timeline with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the game timeline
	 * @return the game timeline that was removed
	 * @throws NoSuchGameTimelineException if a game timeline with the primary key could not be found
	 */
	@Override
	public GameTimeline remove(Serializable primaryKey)
		throws NoSuchGameTimelineException {

		Session session = null;

		try {
			session = openSession();

			GameTimeline gameTimeline = (GameTimeline)session.get(
				GameTimelineImpl.class, primaryKey);

			if (gameTimeline == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchGameTimelineException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(gameTimeline);
		}
		catch (NoSuchGameTimelineException noSuchEntityException) {
			throw noSuchEntityException;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected GameTimeline removeImpl(GameTimeline gameTimeline) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(gameTimeline)) {
				gameTimeline = (GameTimeline)session.get(
					GameTimelineImpl.class, gameTimeline.getPrimaryKeyObj());
			}

			if (gameTimeline != null) {
				session.delete(gameTimeline);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (gameTimeline != null) {
			clearCache(gameTimeline);
		}

		return gameTimeline;
	}

	@Override
	public GameTimeline updateImpl(GameTimeline gameTimeline) {
		boolean isNew = gameTimeline.isNew();

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(gameTimeline);
			}
			else {
				gameTimeline = (GameTimeline)session.merge(gameTimeline);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			GameTimelineImpl.class, gameTimeline, false, true);

		if (isNew) {
			gameTimeline.setNew(false);
		}

		gameTimeline.resetOriginalValues();

		return gameTimeline;
	}

	/**
	 * Returns the game timeline with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the game timeline
	 * @return the game timeline
	 * @throws NoSuchGameTimelineException if a game timeline with the primary key could not be found
	 */
	@Override
	public GameTimeline findByPrimaryKey(Serializable primaryKey)
		throws NoSuchGameTimelineException {

		GameTimeline gameTimeline = fetchByPrimaryKey(primaryKey);

		if (gameTimeline == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchGameTimelineException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return gameTimeline;
	}

	/**
	 * Returns the game timeline with the primary key or throws a <code>NoSuchGameTimelineException</code> if it could not be found.
	 *
	 * @param name the primary key of the game timeline
	 * @return the game timeline
	 * @throws NoSuchGameTimelineException if a game timeline with the primary key could not be found
	 */
	@Override
	public GameTimeline findByPrimaryKey(String name)
		throws NoSuchGameTimelineException {

		return findByPrimaryKey((Serializable)name);
	}

	/**
	 * Returns the game timeline with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param name the primary key of the game timeline
	 * @return the game timeline, or <code>null</code> if a game timeline with the primary key could not be found
	 */
	@Override
	public GameTimeline fetchByPrimaryKey(String name) {
		return fetchByPrimaryKey((Serializable)name);
	}

	/**
	 * Returns all the game timelines.
	 *
	 * @return the game timelines
	 */
	@Override
	public List<GameTimeline> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<GameTimeline> findAll(int start, int end) {
		return findAll(start, end, null);
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
	@Override
	public List<GameTimeline> findAll(
		int start, int end, OrderByComparator<GameTimeline> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
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
	@Override
	public List<GameTimeline> findAll(
		int start, int end, OrderByComparator<GameTimeline> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindAll;
				finderArgs = FINDER_ARGS_EMPTY;
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindAll;
			finderArgs = new Object[] {start, end, orderByComparator};
		}

		List<GameTimeline> list = null;

		if (useFinderCache) {
			list = (List<GameTimeline>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_GAMETIMELINE);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_GAMETIMELINE;

				sql = sql.concat(GameTimelineModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<GameTimeline>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the game timelines from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (GameTimeline gameTimeline : findAll()) {
			remove(gameTimeline);
		}
	}

	/**
	 * Returns the number of game timelines.
	 *
	 * @return the number of game timelines
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_GAMETIMELINE);

				count = (Long)query.uniqueResult();

				finderCache.putResult(
					_finderPathCountAll, FINDER_ARGS_EMPTY, count);
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	@Override
	protected EntityCache getEntityCache() {
		return entityCache;
	}

	@Override
	protected String getPKDBName() {
		return "name";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_GAMETIMELINE;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return GameTimelineModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the game timeline persistence.
	 */
	@Activate
	public void activate() {
		_valueObjectFinderCacheListThreshold = GetterUtil.getInteger(
			PropsUtil.get(PropsKeys.VALUE_OBJECT_FINDER_CACHE_LIST_THRESHOLD));

		_finderPathWithPaginationFindAll = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0],
			new String[0], true);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0],
			new String[0], true);

		_finderPathCountAll = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0], new String[0], false);

		_setGameTimelineUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setGameTimelineUtilPersistence(null);

		entityCache.removeCache(GameTimelineImpl.class.getName());
	}

	private void _setGameTimelineUtilPersistence(
		GameTimelinePersistence gameTimelinePersistence) {

		try {
			Field field = GameTimelineUtil.class.getDeclaredField(
				"_persistence");

			field.setAccessible(true);

			field.set(null, gameTimelinePersistence);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@Override
	@Reference(
		target = GAMEPersistenceConstants.SERVICE_CONFIGURATION_FILTER,
		unbind = "-"
	)
	public void setConfiguration(Configuration configuration) {
	}

	@Override
	@Reference(
		target = GAMEPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	@Reference(
		target = GAMEPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@Reference
	protected EntityCache entityCache;

	@Reference
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_GAMETIMELINE =
		"SELECT gameTimeline FROM GameTimeline gameTimeline";

	private static final String _SQL_COUNT_GAMETIMELINE =
		"SELECT COUNT(gameTimeline) FROM GameTimeline gameTimeline";

	private static final String _ORDER_BY_ENTITY_ALIAS = "gameTimeline.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No GameTimeline exists with the primary key ";

	private static final Log _log = LogFactoryUtil.getLog(
		GameTimelinePersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}