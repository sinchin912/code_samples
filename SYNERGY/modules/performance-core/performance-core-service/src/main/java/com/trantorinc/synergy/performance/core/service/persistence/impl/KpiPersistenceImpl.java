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

package com.trantorinc.synergy.performance.core.service.persistence.impl;

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

import com.trantorinc.synergy.performance.core.exception.NoSuchKpiException;
import com.trantorinc.synergy.performance.core.model.Kpi;
import com.trantorinc.synergy.performance.core.model.KpiTable;
import com.trantorinc.synergy.performance.core.model.impl.KpiImpl;
import com.trantorinc.synergy.performance.core.model.impl.KpiModelImpl;
import com.trantorinc.synergy.performance.core.service.persistence.KpiPersistence;
import com.trantorinc.synergy.performance.core.service.persistence.KpiUtil;
import com.trantorinc.synergy.performance.core.service.persistence.impl.constants.PERFORMANCEPersistenceConstants;

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
 * The persistence implementation for the kpi service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = {KpiPersistence.class, BasePersistence.class})
public class KpiPersistenceImpl
	extends BasePersistenceImpl<Kpi> implements KpiPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>KpiUtil</code> to access the kpi persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		KpiImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;

	public KpiPersistenceImpl() {
		setModelClass(Kpi.class);

		setModelImplClass(KpiImpl.class);
		setModelPKClass(long.class);

		setTable(KpiTable.INSTANCE);
	}

	/**
	 * Caches the kpi in the entity cache if it is enabled.
	 *
	 * @param kpi the kpi
	 */
	@Override
	public void cacheResult(Kpi kpi) {
		entityCache.putResult(KpiImpl.class, kpi.getPrimaryKey(), kpi);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the kpis in the entity cache if it is enabled.
	 *
	 * @param kpis the kpis
	 */
	@Override
	public void cacheResult(List<Kpi> kpis) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (kpis.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (Kpi kpi : kpis) {
			if (entityCache.getResult(KpiImpl.class, kpi.getPrimaryKey()) ==
					null) {

				cacheResult(kpi);
			}
		}
	}

	/**
	 * Clears the cache for all kpis.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(KpiImpl.class);

		finderCache.clearCache(KpiImpl.class);
	}

	/**
	 * Clears the cache for the kpi.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Kpi kpi) {
		entityCache.removeResult(KpiImpl.class, kpi);
	}

	@Override
	public void clearCache(List<Kpi> kpis) {
		for (Kpi kpi : kpis) {
			entityCache.removeResult(KpiImpl.class, kpi);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(KpiImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(KpiImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new kpi with the primary key. Does not add the kpi to the database.
	 *
	 * @param kpiId the primary key for the new kpi
	 * @return the new kpi
	 */
	@Override
	public Kpi create(long kpiId) {
		Kpi kpi = new KpiImpl();

		kpi.setNew(true);
		kpi.setPrimaryKey(kpiId);

		return kpi;
	}

	/**
	 * Removes the kpi with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param kpiId the primary key of the kpi
	 * @return the kpi that was removed
	 * @throws NoSuchKpiException if a kpi with the primary key could not be found
	 */
	@Override
	public Kpi remove(long kpiId) throws NoSuchKpiException {
		return remove((Serializable)kpiId);
	}

	/**
	 * Removes the kpi with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the kpi
	 * @return the kpi that was removed
	 * @throws NoSuchKpiException if a kpi with the primary key could not be found
	 */
	@Override
	public Kpi remove(Serializable primaryKey) throws NoSuchKpiException {
		Session session = null;

		try {
			session = openSession();

			Kpi kpi = (Kpi)session.get(KpiImpl.class, primaryKey);

			if (kpi == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchKpiException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(kpi);
		}
		catch (NoSuchKpiException noSuchEntityException) {
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
	protected Kpi removeImpl(Kpi kpi) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(kpi)) {
				kpi = (Kpi)session.get(KpiImpl.class, kpi.getPrimaryKeyObj());
			}

			if (kpi != null) {
				session.delete(kpi);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (kpi != null) {
			clearCache(kpi);
		}

		return kpi;
	}

	@Override
	public Kpi updateImpl(Kpi kpi) {
		boolean isNew = kpi.isNew();

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(kpi);
			}
			else {
				kpi = (Kpi)session.merge(kpi);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(KpiImpl.class, kpi, false, true);

		if (isNew) {
			kpi.setNew(false);
		}

		kpi.resetOriginalValues();

		return kpi;
	}

	/**
	 * Returns the kpi with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the kpi
	 * @return the kpi
	 * @throws NoSuchKpiException if a kpi with the primary key could not be found
	 */
	@Override
	public Kpi findByPrimaryKey(Serializable primaryKey)
		throws NoSuchKpiException {

		Kpi kpi = fetchByPrimaryKey(primaryKey);

		if (kpi == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchKpiException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return kpi;
	}

	/**
	 * Returns the kpi with the primary key or throws a <code>NoSuchKpiException</code> if it could not be found.
	 *
	 * @param kpiId the primary key of the kpi
	 * @return the kpi
	 * @throws NoSuchKpiException if a kpi with the primary key could not be found
	 */
	@Override
	public Kpi findByPrimaryKey(long kpiId) throws NoSuchKpiException {
		return findByPrimaryKey((Serializable)kpiId);
	}

	/**
	 * Returns the kpi with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param kpiId the primary key of the kpi
	 * @return the kpi, or <code>null</code> if a kpi with the primary key could not be found
	 */
	@Override
	public Kpi fetchByPrimaryKey(long kpiId) {
		return fetchByPrimaryKey((Serializable)kpiId);
	}

	/**
	 * Returns all the kpis.
	 *
	 * @return the kpis
	 */
	@Override
	public List<Kpi> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the kpis.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>KpiModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of kpis
	 * @param end the upper bound of the range of kpis (not inclusive)
	 * @return the range of kpis
	 */
	@Override
	public List<Kpi> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the kpis.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>KpiModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of kpis
	 * @param end the upper bound of the range of kpis (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of kpis
	 */
	@Override
	public List<Kpi> findAll(
		int start, int end, OrderByComparator<Kpi> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the kpis.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>KpiModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of kpis
	 * @param end the upper bound of the range of kpis (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of kpis
	 */
	@Override
	public List<Kpi> findAll(
		int start, int end, OrderByComparator<Kpi> orderByComparator,
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

		List<Kpi> list = null;

		if (useFinderCache) {
			list = (List<Kpi>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_KPI);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_KPI;

				sql = sql.concat(KpiModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<Kpi>)QueryUtil.list(
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
	 * Removes all the kpis from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (Kpi kpi : findAll()) {
			remove(kpi);
		}
	}

	/**
	 * Returns the number of kpis.
	 *
	 * @return the number of kpis
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_KPI);

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
		return "kpiId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_KPI;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return KpiModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the kpi persistence.
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

		_setKpiUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setKpiUtilPersistence(null);

		entityCache.removeCache(KpiImpl.class.getName());
	}

	private void _setKpiUtilPersistence(KpiPersistence kpiPersistence) {
		try {
			Field field = KpiUtil.class.getDeclaredField("_persistence");

			field.setAccessible(true);

			field.set(null, kpiPersistence);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@Override
	@Reference(
		target = PERFORMANCEPersistenceConstants.SERVICE_CONFIGURATION_FILTER,
		unbind = "-"
	)
	public void setConfiguration(Configuration configuration) {
	}

	@Override
	@Reference(
		target = PERFORMANCEPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	@Reference(
		target = PERFORMANCEPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@Reference
	protected EntityCache entityCache;

	@Reference
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_KPI = "SELECT kpi FROM Kpi kpi";

	private static final String _SQL_COUNT_KPI =
		"SELECT COUNT(kpi) FROM Kpi kpi";

	private static final String _ORDER_BY_ENTITY_ALIAS = "kpi.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No Kpi exists with the primary key ";

	private static final Log _log = LogFactoryUtil.getLog(
		KpiPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}