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

import com.trantorinc.synergy.performance.core.exception.NoSuchKpiGuideException;
import com.trantorinc.synergy.performance.core.model.KpiGuide;
import com.trantorinc.synergy.performance.core.model.KpiGuideTable;
import com.trantorinc.synergy.performance.core.model.impl.KpiGuideImpl;
import com.trantorinc.synergy.performance.core.model.impl.KpiGuideModelImpl;
import com.trantorinc.synergy.performance.core.service.persistence.KpiGuidePersistence;
import com.trantorinc.synergy.performance.core.service.persistence.KpiGuideUtil;
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
 * The persistence implementation for the kpi guide service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = {KpiGuidePersistence.class, BasePersistence.class})
public class KpiGuidePersistenceImpl
	extends BasePersistenceImpl<KpiGuide> implements KpiGuidePersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>KpiGuideUtil</code> to access the kpi guide persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		KpiGuideImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;

	public KpiGuidePersistenceImpl() {
		setModelClass(KpiGuide.class);

		setModelImplClass(KpiGuideImpl.class);
		setModelPKClass(long.class);

		setTable(KpiGuideTable.INSTANCE);
	}

	/**
	 * Caches the kpi guide in the entity cache if it is enabled.
	 *
	 * @param kpiGuide the kpi guide
	 */
	@Override
	public void cacheResult(KpiGuide kpiGuide) {
		entityCache.putResult(
			KpiGuideImpl.class, kpiGuide.getPrimaryKey(), kpiGuide);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the kpi guides in the entity cache if it is enabled.
	 *
	 * @param kpiGuides the kpi guides
	 */
	@Override
	public void cacheResult(List<KpiGuide> kpiGuides) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (kpiGuides.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (KpiGuide kpiGuide : kpiGuides) {
			if (entityCache.getResult(
					KpiGuideImpl.class, kpiGuide.getPrimaryKey()) == null) {

				cacheResult(kpiGuide);
			}
		}
	}

	/**
	 * Clears the cache for all kpi guides.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(KpiGuideImpl.class);

		finderCache.clearCache(KpiGuideImpl.class);
	}

	/**
	 * Clears the cache for the kpi guide.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(KpiGuide kpiGuide) {
		entityCache.removeResult(KpiGuideImpl.class, kpiGuide);
	}

	@Override
	public void clearCache(List<KpiGuide> kpiGuides) {
		for (KpiGuide kpiGuide : kpiGuides) {
			entityCache.removeResult(KpiGuideImpl.class, kpiGuide);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(KpiGuideImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(KpiGuideImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new kpi guide with the primary key. Does not add the kpi guide to the database.
	 *
	 * @param guideId the primary key for the new kpi guide
	 * @return the new kpi guide
	 */
	@Override
	public KpiGuide create(long guideId) {
		KpiGuide kpiGuide = new KpiGuideImpl();

		kpiGuide.setNew(true);
		kpiGuide.setPrimaryKey(guideId);

		return kpiGuide;
	}

	/**
	 * Removes the kpi guide with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param guideId the primary key of the kpi guide
	 * @return the kpi guide that was removed
	 * @throws NoSuchKpiGuideException if a kpi guide with the primary key could not be found
	 */
	@Override
	public KpiGuide remove(long guideId) throws NoSuchKpiGuideException {
		return remove((Serializable)guideId);
	}

	/**
	 * Removes the kpi guide with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the kpi guide
	 * @return the kpi guide that was removed
	 * @throws NoSuchKpiGuideException if a kpi guide with the primary key could not be found
	 */
	@Override
	public KpiGuide remove(Serializable primaryKey)
		throws NoSuchKpiGuideException {

		Session session = null;

		try {
			session = openSession();

			KpiGuide kpiGuide = (KpiGuide)session.get(
				KpiGuideImpl.class, primaryKey);

			if (kpiGuide == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchKpiGuideException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(kpiGuide);
		}
		catch (NoSuchKpiGuideException noSuchEntityException) {
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
	protected KpiGuide removeImpl(KpiGuide kpiGuide) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(kpiGuide)) {
				kpiGuide = (KpiGuide)session.get(
					KpiGuideImpl.class, kpiGuide.getPrimaryKeyObj());
			}

			if (kpiGuide != null) {
				session.delete(kpiGuide);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (kpiGuide != null) {
			clearCache(kpiGuide);
		}

		return kpiGuide;
	}

	@Override
	public KpiGuide updateImpl(KpiGuide kpiGuide) {
		boolean isNew = kpiGuide.isNew();

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(kpiGuide);
			}
			else {
				kpiGuide = (KpiGuide)session.merge(kpiGuide);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(KpiGuideImpl.class, kpiGuide, false, true);

		if (isNew) {
			kpiGuide.setNew(false);
		}

		kpiGuide.resetOriginalValues();

		return kpiGuide;
	}

	/**
	 * Returns the kpi guide with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the kpi guide
	 * @return the kpi guide
	 * @throws NoSuchKpiGuideException if a kpi guide with the primary key could not be found
	 */
	@Override
	public KpiGuide findByPrimaryKey(Serializable primaryKey)
		throws NoSuchKpiGuideException {

		KpiGuide kpiGuide = fetchByPrimaryKey(primaryKey);

		if (kpiGuide == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchKpiGuideException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return kpiGuide;
	}

	/**
	 * Returns the kpi guide with the primary key or throws a <code>NoSuchKpiGuideException</code> if it could not be found.
	 *
	 * @param guideId the primary key of the kpi guide
	 * @return the kpi guide
	 * @throws NoSuchKpiGuideException if a kpi guide with the primary key could not be found
	 */
	@Override
	public KpiGuide findByPrimaryKey(long guideId)
		throws NoSuchKpiGuideException {

		return findByPrimaryKey((Serializable)guideId);
	}

	/**
	 * Returns the kpi guide with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param guideId the primary key of the kpi guide
	 * @return the kpi guide, or <code>null</code> if a kpi guide with the primary key could not be found
	 */
	@Override
	public KpiGuide fetchByPrimaryKey(long guideId) {
		return fetchByPrimaryKey((Serializable)guideId);
	}

	/**
	 * Returns all the kpi guides.
	 *
	 * @return the kpi guides
	 */
	@Override
	public List<KpiGuide> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the kpi guides.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>KpiGuideModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of kpi guides
	 * @param end the upper bound of the range of kpi guides (not inclusive)
	 * @return the range of kpi guides
	 */
	@Override
	public List<KpiGuide> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the kpi guides.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>KpiGuideModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of kpi guides
	 * @param end the upper bound of the range of kpi guides (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of kpi guides
	 */
	@Override
	public List<KpiGuide> findAll(
		int start, int end, OrderByComparator<KpiGuide> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the kpi guides.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>KpiGuideModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of kpi guides
	 * @param end the upper bound of the range of kpi guides (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of kpi guides
	 */
	@Override
	public List<KpiGuide> findAll(
		int start, int end, OrderByComparator<KpiGuide> orderByComparator,
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

		List<KpiGuide> list = null;

		if (useFinderCache) {
			list = (List<KpiGuide>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_KPIGUIDE);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_KPIGUIDE;

				sql = sql.concat(KpiGuideModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<KpiGuide>)QueryUtil.list(
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
	 * Removes all the kpi guides from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (KpiGuide kpiGuide : findAll()) {
			remove(kpiGuide);
		}
	}

	/**
	 * Returns the number of kpi guides.
	 *
	 * @return the number of kpi guides
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_KPIGUIDE);

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
		return "guideId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_KPIGUIDE;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return KpiGuideModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the kpi guide persistence.
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

		_setKpiGuideUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setKpiGuideUtilPersistence(null);

		entityCache.removeCache(KpiGuideImpl.class.getName());
	}

	private void _setKpiGuideUtilPersistence(
		KpiGuidePersistence kpiGuidePersistence) {

		try {
			Field field = KpiGuideUtil.class.getDeclaredField("_persistence");

			field.setAccessible(true);

			field.set(null, kpiGuidePersistence);
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

	private static final String _SQL_SELECT_KPIGUIDE =
		"SELECT kpiGuide FROM KpiGuide kpiGuide";

	private static final String _SQL_COUNT_KPIGUIDE =
		"SELECT COUNT(kpiGuide) FROM KpiGuide kpiGuide";

	private static final String _ORDER_BY_ENTITY_ALIAS = "kpiGuide.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No KpiGuide exists with the primary key ";

	private static final Log _log = LogFactoryUtil.getLog(
		KpiGuidePersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}