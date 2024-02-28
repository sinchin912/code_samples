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
import com.liferay.portal.kernel.util.SetUtil;

import com.trantorinc.synergy.notice.core.exception.NoSuchResignationException;
import com.trantorinc.synergy.notice.core.model.Resignation;
import com.trantorinc.synergy.notice.core.model.ResignationTable;
import com.trantorinc.synergy.notice.core.model.impl.ResignationImpl;
import com.trantorinc.synergy.notice.core.model.impl.ResignationModelImpl;
import com.trantorinc.synergy.notice.core.service.persistence.ResignationPersistence;
import com.trantorinc.synergy.notice.core.service.persistence.ResignationUtil;
import com.trantorinc.synergy.notice.core.service.persistence.impl.constants.NOTICEPersistenceConstants;

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
 * The persistence implementation for the resignation service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = {ResignationPersistence.class, BasePersistence.class})
public class ResignationPersistenceImpl
	extends BasePersistenceImpl<Resignation> implements ResignationPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>ResignationUtil</code> to access the resignation persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		ResignationImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;

	public ResignationPersistenceImpl() {
		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("id", "id_");

		setDBColumnNames(dbColumnNames);

		setModelClass(Resignation.class);

		setModelImplClass(ResignationImpl.class);
		setModelPKClass(long.class);

		setTable(ResignationTable.INSTANCE);
	}

	/**
	 * Caches the resignation in the entity cache if it is enabled.
	 *
	 * @param resignation the resignation
	 */
	@Override
	public void cacheResult(Resignation resignation) {
		entityCache.putResult(
			ResignationImpl.class, resignation.getPrimaryKey(), resignation);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the resignations in the entity cache if it is enabled.
	 *
	 * @param resignations the resignations
	 */
	@Override
	public void cacheResult(List<Resignation> resignations) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (resignations.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (Resignation resignation : resignations) {
			if (entityCache.getResult(
					ResignationImpl.class, resignation.getPrimaryKey()) ==
						null) {

				cacheResult(resignation);
			}
		}
	}

	/**
	 * Clears the cache for all resignations.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ResignationImpl.class);

		finderCache.clearCache(ResignationImpl.class);
	}

	/**
	 * Clears the cache for the resignation.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Resignation resignation) {
		entityCache.removeResult(ResignationImpl.class, resignation);
	}

	@Override
	public void clearCache(List<Resignation> resignations) {
		for (Resignation resignation : resignations) {
			entityCache.removeResult(ResignationImpl.class, resignation);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(ResignationImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(ResignationImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new resignation with the primary key. Does not add the resignation to the database.
	 *
	 * @param id the primary key for the new resignation
	 * @return the new resignation
	 */
	@Override
	public Resignation create(long id) {
		Resignation resignation = new ResignationImpl();

		resignation.setNew(true);
		resignation.setPrimaryKey(id);

		return resignation;
	}

	/**
	 * Removes the resignation with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the resignation
	 * @return the resignation that was removed
	 * @throws NoSuchResignationException if a resignation with the primary key could not be found
	 */
	@Override
	public Resignation remove(long id) throws NoSuchResignationException {
		return remove((Serializable)id);
	}

	/**
	 * Removes the resignation with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the resignation
	 * @return the resignation that was removed
	 * @throws NoSuchResignationException if a resignation with the primary key could not be found
	 */
	@Override
	public Resignation remove(Serializable primaryKey)
		throws NoSuchResignationException {

		Session session = null;

		try {
			session = openSession();

			Resignation resignation = (Resignation)session.get(
				ResignationImpl.class, primaryKey);

			if (resignation == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchResignationException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(resignation);
		}
		catch (NoSuchResignationException noSuchEntityException) {
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
	protected Resignation removeImpl(Resignation resignation) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(resignation)) {
				resignation = (Resignation)session.get(
					ResignationImpl.class, resignation.getPrimaryKeyObj());
			}

			if (resignation != null) {
				session.delete(resignation);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (resignation != null) {
			clearCache(resignation);
		}

		return resignation;
	}

	@Override
	public Resignation updateImpl(Resignation resignation) {
		boolean isNew = resignation.isNew();

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(resignation);
			}
			else {
				resignation = (Resignation)session.merge(resignation);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(ResignationImpl.class, resignation, false, true);

		if (isNew) {
			resignation.setNew(false);
		}

		resignation.resetOriginalValues();

		return resignation;
	}

	/**
	 * Returns the resignation with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the resignation
	 * @return the resignation
	 * @throws NoSuchResignationException if a resignation with the primary key could not be found
	 */
	@Override
	public Resignation findByPrimaryKey(Serializable primaryKey)
		throws NoSuchResignationException {

		Resignation resignation = fetchByPrimaryKey(primaryKey);

		if (resignation == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchResignationException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return resignation;
	}

	/**
	 * Returns the resignation with the primary key or throws a <code>NoSuchResignationException</code> if it could not be found.
	 *
	 * @param id the primary key of the resignation
	 * @return the resignation
	 * @throws NoSuchResignationException if a resignation with the primary key could not be found
	 */
	@Override
	public Resignation findByPrimaryKey(long id)
		throws NoSuchResignationException {

		return findByPrimaryKey((Serializable)id);
	}

	/**
	 * Returns the resignation with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the resignation
	 * @return the resignation, or <code>null</code> if a resignation with the primary key could not be found
	 */
	@Override
	public Resignation fetchByPrimaryKey(long id) {
		return fetchByPrimaryKey((Serializable)id);
	}

	/**
	 * Returns all the resignations.
	 *
	 * @return the resignations
	 */
	@Override
	public List<Resignation> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the resignations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ResignationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of resignations
	 * @param end the upper bound of the range of resignations (not inclusive)
	 * @return the range of resignations
	 */
	@Override
	public List<Resignation> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the resignations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ResignationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of resignations
	 * @param end the upper bound of the range of resignations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of resignations
	 */
	@Override
	public List<Resignation> findAll(
		int start, int end, OrderByComparator<Resignation> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the resignations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ResignationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of resignations
	 * @param end the upper bound of the range of resignations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of resignations
	 */
	@Override
	public List<Resignation> findAll(
		int start, int end, OrderByComparator<Resignation> orderByComparator,
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

		List<Resignation> list = null;

		if (useFinderCache) {
			list = (List<Resignation>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_RESIGNATION);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_RESIGNATION;

				sql = sql.concat(ResignationModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<Resignation>)QueryUtil.list(
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
	 * Removes all the resignations from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (Resignation resignation : findAll()) {
			remove(resignation);
		}
	}

	/**
	 * Returns the number of resignations.
	 *
	 * @return the number of resignations
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_RESIGNATION);

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
		return "id_";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_RESIGNATION;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return ResignationModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the resignation persistence.
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

		_setResignationUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setResignationUtilPersistence(null);

		entityCache.removeCache(ResignationImpl.class.getName());
	}

	private void _setResignationUtilPersistence(
		ResignationPersistence resignationPersistence) {

		try {
			Field field = ResignationUtil.class.getDeclaredField(
				"_persistence");

			field.setAccessible(true);

			field.set(null, resignationPersistence);
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

	private static final String _SQL_SELECT_RESIGNATION =
		"SELECT resignation FROM Resignation resignation";

	private static final String _SQL_COUNT_RESIGNATION =
		"SELECT COUNT(resignation) FROM Resignation resignation";

	private static final String _ORDER_BY_ENTITY_ALIAS = "resignation.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No Resignation exists with the primary key ";

	private static final Log _log = LogFactoryUtil.getLog(
		ResignationPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"id"});

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}