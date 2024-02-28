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

package com.trantorinc.synergy.notice.core.service.persistence.impl;

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

import com.trantorinc.synergy.notice.core.exception.NoSuchExitStateException;
import com.trantorinc.synergy.notice.core.model.ExitState;
import com.trantorinc.synergy.notice.core.model.ExitStateTable;
import com.trantorinc.synergy.notice.core.model.impl.ExitStateImpl;
import com.trantorinc.synergy.notice.core.model.impl.ExitStateModelImpl;
import com.trantorinc.synergy.notice.core.service.persistence.ExitStatePersistence;
import com.trantorinc.synergy.notice.core.service.persistence.ExitStateUtil;
import com.trantorinc.synergy.notice.core.service.persistence.impl.constants.NOTICEPersistenceConstants;

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
 * The persistence implementation for the exit state service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = {ExitStatePersistence.class, BasePersistence.class})
public class ExitStatePersistenceImpl
	extends BasePersistenceImpl<ExitState> implements ExitStatePersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>ExitStateUtil</code> to access the exit state persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		ExitStateImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;

	public ExitStatePersistenceImpl() {
		setModelClass(ExitState.class);

		setModelImplClass(ExitStateImpl.class);
		setModelPKClass(long.class);

		setTable(ExitStateTable.INSTANCE);
	}

	/**
	 * Caches the exit state in the entity cache if it is enabled.
	 *
	 * @param exitState the exit state
	 */
	@Override
	public void cacheResult(ExitState exitState) {
		entityCache.putResult(
			ExitStateImpl.class, exitState.getPrimaryKey(), exitState);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the exit states in the entity cache if it is enabled.
	 *
	 * @param exitStates the exit states
	 */
	@Override
	public void cacheResult(List<ExitState> exitStates) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (exitStates.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (ExitState exitState : exitStates) {
			if (entityCache.getResult(
					ExitStateImpl.class, exitState.getPrimaryKey()) == null) {

				cacheResult(exitState);
			}
		}
	}

	/**
	 * Clears the cache for all exit states.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ExitStateImpl.class);

		finderCache.clearCache(ExitStateImpl.class);
	}

	/**
	 * Clears the cache for the exit state.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ExitState exitState) {
		entityCache.removeResult(ExitStateImpl.class, exitState);
	}

	@Override
	public void clearCache(List<ExitState> exitStates) {
		for (ExitState exitState : exitStates) {
			entityCache.removeResult(ExitStateImpl.class, exitState);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(ExitStateImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(ExitStateImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new exit state with the primary key. Does not add the exit state to the database.
	 *
	 * @param exitStateId the primary key for the new exit state
	 * @return the new exit state
	 */
	@Override
	public ExitState create(long exitStateId) {
		ExitState exitState = new ExitStateImpl();

		exitState.setNew(true);
		exitState.setPrimaryKey(exitStateId);

		return exitState;
	}

	/**
	 * Removes the exit state with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param exitStateId the primary key of the exit state
	 * @return the exit state that was removed
	 * @throws NoSuchExitStateException if a exit state with the primary key could not be found
	 */
	@Override
	public ExitState remove(long exitStateId) throws NoSuchExitStateException {
		return remove((Serializable)exitStateId);
	}

	/**
	 * Removes the exit state with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the exit state
	 * @return the exit state that was removed
	 * @throws NoSuchExitStateException if a exit state with the primary key could not be found
	 */
	@Override
	public ExitState remove(Serializable primaryKey)
		throws NoSuchExitStateException {

		Session session = null;

		try {
			session = openSession();

			ExitState exitState = (ExitState)session.get(
				ExitStateImpl.class, primaryKey);

			if (exitState == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchExitStateException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(exitState);
		}
		catch (NoSuchExitStateException noSuchEntityException) {
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
	protected ExitState removeImpl(ExitState exitState) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(exitState)) {
				exitState = (ExitState)session.get(
					ExitStateImpl.class, exitState.getPrimaryKeyObj());
			}

			if (exitState != null) {
				session.delete(exitState);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (exitState != null) {
			clearCache(exitState);
		}

		return exitState;
	}

	@Override
	public ExitState updateImpl(ExitState exitState) {
		boolean isNew = exitState.isNew();

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(exitState);
			}
			else {
				exitState = (ExitState)session.merge(exitState);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(ExitStateImpl.class, exitState, false, true);

		if (isNew) {
			exitState.setNew(false);
		}

		exitState.resetOriginalValues();

		return exitState;
	}

	/**
	 * Returns the exit state with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the exit state
	 * @return the exit state
	 * @throws NoSuchExitStateException if a exit state with the primary key could not be found
	 */
	@Override
	public ExitState findByPrimaryKey(Serializable primaryKey)
		throws NoSuchExitStateException {

		ExitState exitState = fetchByPrimaryKey(primaryKey);

		if (exitState == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchExitStateException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return exitState;
	}

	/**
	 * Returns the exit state with the primary key or throws a <code>NoSuchExitStateException</code> if it could not be found.
	 *
	 * @param exitStateId the primary key of the exit state
	 * @return the exit state
	 * @throws NoSuchExitStateException if a exit state with the primary key could not be found
	 */
	@Override
	public ExitState findByPrimaryKey(long exitStateId)
		throws NoSuchExitStateException {

		return findByPrimaryKey((Serializable)exitStateId);
	}

	/**
	 * Returns the exit state with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param exitStateId the primary key of the exit state
	 * @return the exit state, or <code>null</code> if a exit state with the primary key could not be found
	 */
	@Override
	public ExitState fetchByPrimaryKey(long exitStateId) {
		return fetchByPrimaryKey((Serializable)exitStateId);
	}

	/**
	 * Returns all the exit states.
	 *
	 * @return the exit states
	 */
	@Override
	public List<ExitState> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the exit states.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ExitStateModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of exit states
	 * @param end the upper bound of the range of exit states (not inclusive)
	 * @return the range of exit states
	 */
	@Override
	public List<ExitState> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the exit states.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ExitStateModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of exit states
	 * @param end the upper bound of the range of exit states (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of exit states
	 */
	@Override
	public List<ExitState> findAll(
		int start, int end, OrderByComparator<ExitState> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the exit states.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ExitStateModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of exit states
	 * @param end the upper bound of the range of exit states (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of exit states
	 */
	@Override
	public List<ExitState> findAll(
		int start, int end, OrderByComparator<ExitState> orderByComparator,
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

		List<ExitState> list = null;

		if (useFinderCache) {
			list = (List<ExitState>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_EXITSTATE);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_EXITSTATE;

				sql = sql.concat(ExitStateModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<ExitState>)QueryUtil.list(
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
	 * Removes all the exit states from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (ExitState exitState : findAll()) {
			remove(exitState);
		}
	}

	/**
	 * Returns the number of exit states.
	 *
	 * @return the number of exit states
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_EXITSTATE);

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
		return "exitStateId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_EXITSTATE;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return ExitStateModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the exit state persistence.
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

		_setExitStateUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setExitStateUtilPersistence(null);

		entityCache.removeCache(ExitStateImpl.class.getName());
	}

	private void _setExitStateUtilPersistence(
		ExitStatePersistence exitStatePersistence) {

		try {
			Field field = ExitStateUtil.class.getDeclaredField("_persistence");

			field.setAccessible(true);

			field.set(null, exitStatePersistence);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@Override
	@Reference(
		target = NOTICEPersistenceConstants.SERVICE_CONFIGURATION_FILTER,
		unbind = "-"
	)
	public void setConfiguration(Configuration configuration) {
	}

	@Override
	@Reference(
		target = NOTICEPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	@Reference(
		target = NOTICEPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@Reference
	protected EntityCache entityCache;

	@Reference
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_EXITSTATE =
		"SELECT exitState FROM ExitState exitState";

	private static final String _SQL_COUNT_EXITSTATE =
		"SELECT COUNT(exitState) FROM ExitState exitState";

	private static final String _ORDER_BY_ENTITY_ALIAS = "exitState.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No ExitState exists with the primary key ";

	private static final Log _log = LogFactoryUtil.getLog(
		ExitStatePersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}