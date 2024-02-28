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
import com.liferay.portal.kernel.util.SetUtil;

import com.trantorinc.synergy.game.core.exception.NoSuchSantaException;
import com.trantorinc.synergy.game.core.model.Santa;
import com.trantorinc.synergy.game.core.model.SantaTable;
import com.trantorinc.synergy.game.core.model.impl.SantaImpl;
import com.trantorinc.synergy.game.core.model.impl.SantaModelImpl;
import com.trantorinc.synergy.game.core.service.persistence.SantaPersistence;
import com.trantorinc.synergy.game.core.service.persistence.SantaUtil;
import com.trantorinc.synergy.game.core.service.persistence.impl.constants.GAMEPersistenceConstants;

import java.io.Serializable;

import java.lang.reflect.Field;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * The persistence implementation for the santa service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = {SantaPersistence.class, BasePersistence.class})
public class SantaPersistenceImpl
	extends BasePersistenceImpl<Santa> implements SantaPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>SantaUtil</code> to access the santa persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		SantaImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;

	public SantaPersistenceImpl() {
		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("state", "state_");

		setDBColumnNames(dbColumnNames);

		setModelClass(Santa.class);

		setModelImplClass(SantaImpl.class);
		setModelPKClass(long.class);

		setTable(SantaTable.INSTANCE);
	}

	/**
	 * Caches the santa in the entity cache if it is enabled.
	 *
	 * @param santa the santa
	 */
	@Override
	public void cacheResult(Santa santa) {
		entityCache.putResult(SantaImpl.class, santa.getPrimaryKey(), santa);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the santas in the entity cache if it is enabled.
	 *
	 * @param santas the santas
	 */
	@Override
	public void cacheResult(List<Santa> santas) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (santas.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (Santa santa : santas) {
			if (entityCache.getResult(SantaImpl.class, santa.getPrimaryKey()) ==
					null) {

				cacheResult(santa);
			}
		}
	}

	/**
	 * Clears the cache for all santas.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(SantaImpl.class);

		finderCache.clearCache(SantaImpl.class);
	}

	/**
	 * Clears the cache for the santa.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Santa santa) {
		entityCache.removeResult(SantaImpl.class, santa);
	}

	@Override
	public void clearCache(List<Santa> santas) {
		for (Santa santa : santas) {
			entityCache.removeResult(SantaImpl.class, santa);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(SantaImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(SantaImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new santa with the primary key. Does not add the santa to the database.
	 *
	 * @param santaId the primary key for the new santa
	 * @return the new santa
	 */
	@Override
	public Santa create(long santaId) {
		Santa santa = new SantaImpl();

		santa.setNew(true);
		santa.setPrimaryKey(santaId);

		return santa;
	}

	/**
	 * Removes the santa with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param santaId the primary key of the santa
	 * @return the santa that was removed
	 * @throws NoSuchSantaException if a santa with the primary key could not be found
	 */
	@Override
	public Santa remove(long santaId) throws NoSuchSantaException {
		return remove((Serializable)santaId);
	}

	/**
	 * Removes the santa with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the santa
	 * @return the santa that was removed
	 * @throws NoSuchSantaException if a santa with the primary key could not be found
	 */
	@Override
	public Santa remove(Serializable primaryKey) throws NoSuchSantaException {
		Session session = null;

		try {
			session = openSession();

			Santa santa = (Santa)session.get(SantaImpl.class, primaryKey);

			if (santa == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchSantaException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(santa);
		}
		catch (NoSuchSantaException noSuchEntityException) {
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
	protected Santa removeImpl(Santa santa) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(santa)) {
				santa = (Santa)session.get(
					SantaImpl.class, santa.getPrimaryKeyObj());
			}

			if (santa != null) {
				session.delete(santa);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (santa != null) {
			clearCache(santa);
		}

		return santa;
	}

	@Override
	public Santa updateImpl(Santa santa) {
		boolean isNew = santa.isNew();

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(santa);
			}
			else {
				santa = (Santa)session.merge(santa);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(SantaImpl.class, santa, false, true);

		if (isNew) {
			santa.setNew(false);
		}

		santa.resetOriginalValues();

		return santa;
	}

	/**
	 * Returns the santa with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the santa
	 * @return the santa
	 * @throws NoSuchSantaException if a santa with the primary key could not be found
	 */
	@Override
	public Santa findByPrimaryKey(Serializable primaryKey)
		throws NoSuchSantaException {

		Santa santa = fetchByPrimaryKey(primaryKey);

		if (santa == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchSantaException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return santa;
	}

	/**
	 * Returns the santa with the primary key or throws a <code>NoSuchSantaException</code> if it could not be found.
	 *
	 * @param santaId the primary key of the santa
	 * @return the santa
	 * @throws NoSuchSantaException if a santa with the primary key could not be found
	 */
	@Override
	public Santa findByPrimaryKey(long santaId) throws NoSuchSantaException {
		return findByPrimaryKey((Serializable)santaId);
	}

	/**
	 * Returns the santa with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param santaId the primary key of the santa
	 * @return the santa, or <code>null</code> if a santa with the primary key could not be found
	 */
	@Override
	public Santa fetchByPrimaryKey(long santaId) {
		return fetchByPrimaryKey((Serializable)santaId);
	}

	/**
	 * Returns all the santas.
	 *
	 * @return the santas
	 */
	@Override
	public List<Santa> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the santas.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SantaModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of santas
	 * @param end the upper bound of the range of santas (not inclusive)
	 * @return the range of santas
	 */
	@Override
	public List<Santa> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the santas.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SantaModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of santas
	 * @param end the upper bound of the range of santas (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of santas
	 */
	@Override
	public List<Santa> findAll(
		int start, int end, OrderByComparator<Santa> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the santas.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SantaModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of santas
	 * @param end the upper bound of the range of santas (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of santas
	 */
	@Override
	public List<Santa> findAll(
		int start, int end, OrderByComparator<Santa> orderByComparator,
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

		List<Santa> list = null;

		if (useFinderCache) {
			list = (List<Santa>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_SANTA);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_SANTA;

				sql = sql.concat(SantaModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<Santa>)QueryUtil.list(
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
	 * Removes all the santas from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (Santa santa : findAll()) {
			remove(santa);
		}
	}

	/**
	 * Returns the number of santas.
	 *
	 * @return the number of santas
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_SANTA);

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
	public Set<String> getBadColumnNames() {
		return _badColumnNames;
	}

	@Override
	protected EntityCache getEntityCache() {
		return entityCache;
	}

	@Override
	protected String getPKDBName() {
		return "santaId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_SANTA;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return SantaModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the santa persistence.
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

		_setSantaUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setSantaUtilPersistence(null);

		entityCache.removeCache(SantaImpl.class.getName());
	}

	private void _setSantaUtilPersistence(SantaPersistence santaPersistence) {
		try {
			Field field = SantaUtil.class.getDeclaredField("_persistence");

			field.setAccessible(true);

			field.set(null, santaPersistence);
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

	private static final String _SQL_SELECT_SANTA =
		"SELECT santa FROM Santa santa";

	private static final String _SQL_COUNT_SANTA =
		"SELECT COUNT(santa) FROM Santa santa";

	private static final String _ORDER_BY_ENTITY_ALIAS = "santa.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No Santa exists with the primary key ";

	private static final Log _log = LogFactoryUtil.getLog(
		SantaPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"state"});

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}