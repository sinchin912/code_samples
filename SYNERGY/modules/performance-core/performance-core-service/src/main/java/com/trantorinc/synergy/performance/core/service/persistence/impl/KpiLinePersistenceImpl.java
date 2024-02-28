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
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.persistence.BasePersistence;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.ProxyUtil;

import com.trantorinc.synergy.performance.core.exception.NoSuchKpiLineException;
import com.trantorinc.synergy.performance.core.model.KpiLine;
import com.trantorinc.synergy.performance.core.model.KpiLineTable;
import com.trantorinc.synergy.performance.core.model.impl.KpiLineImpl;
import com.trantorinc.synergy.performance.core.model.impl.KpiLineModelImpl;
import com.trantorinc.synergy.performance.core.service.persistence.KpiLinePersistence;
import com.trantorinc.synergy.performance.core.service.persistence.KpiLineUtil;
import com.trantorinc.synergy.performance.core.service.persistence.impl.constants.PERFORMANCEPersistenceConstants;

import java.io.Serializable;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * The persistence implementation for the kpi line service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = {KpiLinePersistence.class, BasePersistence.class})
public class KpiLinePersistenceImpl
	extends BasePersistenceImpl<KpiLine> implements KpiLinePersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>KpiLineUtil</code> to access the kpi line persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		KpiLineImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;

	public KpiLinePersistenceImpl() {
		setModelClass(KpiLine.class);

		setModelImplClass(KpiLineImpl.class);
		setModelPKClass(long.class);

		setTable(KpiLineTable.INSTANCE);
	}

	/**
	 * Caches the kpi line in the entity cache if it is enabled.
	 *
	 * @param kpiLine the kpi line
	 */
	@Override
	public void cacheResult(KpiLine kpiLine) {
		entityCache.putResult(
			KpiLineImpl.class, kpiLine.getPrimaryKey(), kpiLine);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the kpi lines in the entity cache if it is enabled.
	 *
	 * @param kpiLines the kpi lines
	 */
	@Override
	public void cacheResult(List<KpiLine> kpiLines) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (kpiLines.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (KpiLine kpiLine : kpiLines) {
			if (entityCache.getResult(
					KpiLineImpl.class, kpiLine.getPrimaryKey()) == null) {

				cacheResult(kpiLine);
			}
		}
	}

	/**
	 * Clears the cache for all kpi lines.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(KpiLineImpl.class);

		finderCache.clearCache(KpiLineImpl.class);
	}

	/**
	 * Clears the cache for the kpi line.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(KpiLine kpiLine) {
		entityCache.removeResult(KpiLineImpl.class, kpiLine);
	}

	@Override
	public void clearCache(List<KpiLine> kpiLines) {
		for (KpiLine kpiLine : kpiLines) {
			entityCache.removeResult(KpiLineImpl.class, kpiLine);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(KpiLineImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(KpiLineImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new kpi line with the primary key. Does not add the kpi line to the database.
	 *
	 * @param lineId the primary key for the new kpi line
	 * @return the new kpi line
	 */
	@Override
	public KpiLine create(long lineId) {
		KpiLine kpiLine = new KpiLineImpl();

		kpiLine.setNew(true);
		kpiLine.setPrimaryKey(lineId);

		return kpiLine;
	}

	/**
	 * Removes the kpi line with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param lineId the primary key of the kpi line
	 * @return the kpi line that was removed
	 * @throws NoSuchKpiLineException if a kpi line with the primary key could not be found
	 */
	@Override
	public KpiLine remove(long lineId) throws NoSuchKpiLineException {
		return remove((Serializable)lineId);
	}

	/**
	 * Removes the kpi line with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the kpi line
	 * @return the kpi line that was removed
	 * @throws NoSuchKpiLineException if a kpi line with the primary key could not be found
	 */
	@Override
	public KpiLine remove(Serializable primaryKey)
		throws NoSuchKpiLineException {

		Session session = null;

		try {
			session = openSession();

			KpiLine kpiLine = (KpiLine)session.get(
				KpiLineImpl.class, primaryKey);

			if (kpiLine == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchKpiLineException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(kpiLine);
		}
		catch (NoSuchKpiLineException noSuchEntityException) {
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
	protected KpiLine removeImpl(KpiLine kpiLine) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(kpiLine)) {
				kpiLine = (KpiLine)session.get(
					KpiLineImpl.class, kpiLine.getPrimaryKeyObj());
			}

			if (kpiLine != null) {
				session.delete(kpiLine);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (kpiLine != null) {
			clearCache(kpiLine);
		}

		return kpiLine;
	}

	@Override
	public KpiLine updateImpl(KpiLine kpiLine) {
		boolean isNew = kpiLine.isNew();

		if (!(kpiLine instanceof KpiLineModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(kpiLine.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(kpiLine);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in kpiLine proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom KpiLine implementation " +
					kpiLine.getClass());
		}

		KpiLineModelImpl kpiLineModelImpl = (KpiLineModelImpl)kpiLine;

		if (isNew && (kpiLine.getCreateDate() == null)) {
			ServiceContext serviceContext =
				ServiceContextThreadLocal.getServiceContext();

			Date date = new Date();

			if (serviceContext == null) {
				kpiLine.setCreateDate(date);
			}
			else {
				kpiLine.setCreateDate(serviceContext.getCreateDate(date));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(kpiLine);
			}
			else {
				kpiLine = (KpiLine)session.merge(kpiLine);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(KpiLineImpl.class, kpiLine, false, true);

		if (isNew) {
			kpiLine.setNew(false);
		}

		kpiLine.resetOriginalValues();

		return kpiLine;
	}

	/**
	 * Returns the kpi line with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the kpi line
	 * @return the kpi line
	 * @throws NoSuchKpiLineException if a kpi line with the primary key could not be found
	 */
	@Override
	public KpiLine findByPrimaryKey(Serializable primaryKey)
		throws NoSuchKpiLineException {

		KpiLine kpiLine = fetchByPrimaryKey(primaryKey);

		if (kpiLine == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchKpiLineException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return kpiLine;
	}

	/**
	 * Returns the kpi line with the primary key or throws a <code>NoSuchKpiLineException</code> if it could not be found.
	 *
	 * @param lineId the primary key of the kpi line
	 * @return the kpi line
	 * @throws NoSuchKpiLineException if a kpi line with the primary key could not be found
	 */
	@Override
	public KpiLine findByPrimaryKey(long lineId) throws NoSuchKpiLineException {
		return findByPrimaryKey((Serializable)lineId);
	}

	/**
	 * Returns the kpi line with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param lineId the primary key of the kpi line
	 * @return the kpi line, or <code>null</code> if a kpi line with the primary key could not be found
	 */
	@Override
	public KpiLine fetchByPrimaryKey(long lineId) {
		return fetchByPrimaryKey((Serializable)lineId);
	}

	/**
	 * Returns all the kpi lines.
	 *
	 * @return the kpi lines
	 */
	@Override
	public List<KpiLine> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the kpi lines.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>KpiLineModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of kpi lines
	 * @param end the upper bound of the range of kpi lines (not inclusive)
	 * @return the range of kpi lines
	 */
	@Override
	public List<KpiLine> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the kpi lines.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>KpiLineModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of kpi lines
	 * @param end the upper bound of the range of kpi lines (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of kpi lines
	 */
	@Override
	public List<KpiLine> findAll(
		int start, int end, OrderByComparator<KpiLine> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the kpi lines.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>KpiLineModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of kpi lines
	 * @param end the upper bound of the range of kpi lines (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of kpi lines
	 */
	@Override
	public List<KpiLine> findAll(
		int start, int end, OrderByComparator<KpiLine> orderByComparator,
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

		List<KpiLine> list = null;

		if (useFinderCache) {
			list = (List<KpiLine>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_KPILINE);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_KPILINE;

				sql = sql.concat(KpiLineModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<KpiLine>)QueryUtil.list(
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
	 * Removes all the kpi lines from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (KpiLine kpiLine : findAll()) {
			remove(kpiLine);
		}
	}

	/**
	 * Returns the number of kpi lines.
	 *
	 * @return the number of kpi lines
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_KPILINE);

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
		return "lineId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_KPILINE;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return KpiLineModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the kpi line persistence.
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

		_setKpiLineUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setKpiLineUtilPersistence(null);

		entityCache.removeCache(KpiLineImpl.class.getName());
	}

	private void _setKpiLineUtilPersistence(
		KpiLinePersistence kpiLinePersistence) {

		try {
			Field field = KpiLineUtil.class.getDeclaredField("_persistence");

			field.setAccessible(true);

			field.set(null, kpiLinePersistence);
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

	private static final String _SQL_SELECT_KPILINE =
		"SELECT kpiLine FROM KpiLine kpiLine";

	private static final String _SQL_COUNT_KPILINE =
		"SELECT COUNT(kpiLine) FROM KpiLine kpiLine";

	private static final String _ORDER_BY_ENTITY_ALIAS = "kpiLine.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No KpiLine exists with the primary key ";

	private static final Log _log = LogFactoryUtil.getLog(
		KpiLinePersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}