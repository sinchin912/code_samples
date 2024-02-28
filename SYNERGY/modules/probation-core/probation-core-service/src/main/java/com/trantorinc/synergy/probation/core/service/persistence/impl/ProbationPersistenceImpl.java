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

package com.trantorinc.synergy.probation.core.service.persistence.impl;

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
import com.liferay.portal.kernel.util.SetUtil;

import com.trantorinc.synergy.probation.core.exception.NoSuchProbationException;
import com.trantorinc.synergy.probation.core.model.Probation;
import com.trantorinc.synergy.probation.core.model.ProbationTable;
import com.trantorinc.synergy.probation.core.model.impl.ProbationImpl;
import com.trantorinc.synergy.probation.core.model.impl.ProbationModelImpl;
import com.trantorinc.synergy.probation.core.service.persistence.ProbationPersistence;
import com.trantorinc.synergy.probation.core.service.persistence.ProbationUtil;
import com.trantorinc.synergy.probation.core.service.persistence.impl.constants.PROBATIONPersistenceConstants;

import java.io.Serializable;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;

import java.util.Date;
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
 * The persistence implementation for the probation service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = {ProbationPersistence.class, BasePersistence.class})
public class ProbationPersistenceImpl
	extends BasePersistenceImpl<Probation> implements ProbationPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>ProbationUtil</code> to access the probation persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		ProbationImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;

	public ProbationPersistenceImpl() {
		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("state", "state_");
		dbColumnNames.put("comment", "comment_");

		setDBColumnNames(dbColumnNames);

		setModelClass(Probation.class);

		setModelImplClass(ProbationImpl.class);
		setModelPKClass(String.class);

		setTable(ProbationTable.INSTANCE);
	}

	/**
	 * Caches the probation in the entity cache if it is enabled.
	 *
	 * @param probation the probation
	 */
	@Override
	public void cacheResult(Probation probation) {
		entityCache.putResult(
			ProbationImpl.class, probation.getPrimaryKey(), probation);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the probations in the entity cache if it is enabled.
	 *
	 * @param probations the probations
	 */
	@Override
	public void cacheResult(List<Probation> probations) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (probations.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (Probation probation : probations) {
			if (entityCache.getResult(
					ProbationImpl.class, probation.getPrimaryKey()) == null) {

				cacheResult(probation);
			}
		}
	}

	/**
	 * Clears the cache for all probations.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ProbationImpl.class);

		finderCache.clearCache(ProbationImpl.class);
	}

	/**
	 * Clears the cache for the probation.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Probation probation) {
		entityCache.removeResult(ProbationImpl.class, probation);
	}

	@Override
	public void clearCache(List<Probation> probations) {
		for (Probation probation : probations) {
			entityCache.removeResult(ProbationImpl.class, probation);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(ProbationImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(ProbationImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new probation with the primary key. Does not add the probation to the database.
	 *
	 * @param ecode the primary key for the new probation
	 * @return the new probation
	 */
	@Override
	public Probation create(String ecode) {
		Probation probation = new ProbationImpl();

		probation.setNew(true);
		probation.setPrimaryKey(ecode);

		return probation;
	}

	/**
	 * Removes the probation with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param ecode the primary key of the probation
	 * @return the probation that was removed
	 * @throws NoSuchProbationException if a probation with the primary key could not be found
	 */
	@Override
	public Probation remove(String ecode) throws NoSuchProbationException {
		return remove((Serializable)ecode);
	}

	/**
	 * Removes the probation with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the probation
	 * @return the probation that was removed
	 * @throws NoSuchProbationException if a probation with the primary key could not be found
	 */
	@Override
	public Probation remove(Serializable primaryKey)
		throws NoSuchProbationException {

		Session session = null;

		try {
			session = openSession();

			Probation probation = (Probation)session.get(
				ProbationImpl.class, primaryKey);

			if (probation == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchProbationException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(probation);
		}
		catch (NoSuchProbationException noSuchEntityException) {
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
	protected Probation removeImpl(Probation probation) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(probation)) {
				probation = (Probation)session.get(
					ProbationImpl.class, probation.getPrimaryKeyObj());
			}

			if (probation != null) {
				session.delete(probation);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (probation != null) {
			clearCache(probation);
		}

		return probation;
	}

	@Override
	public Probation updateImpl(Probation probation) {
		boolean isNew = probation.isNew();

		if (!(probation instanceof ProbationModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(probation.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(probation);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in probation proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom Probation implementation " +
					probation.getClass());
		}

		ProbationModelImpl probationModelImpl = (ProbationModelImpl)probation;

		if (isNew && (probation.getCreateDate() == null)) {
			ServiceContext serviceContext =
				ServiceContextThreadLocal.getServiceContext();

			Date date = new Date();

			if (serviceContext == null) {
				probation.setCreateDate(date);
			}
			else {
				probation.setCreateDate(serviceContext.getCreateDate(date));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(probation);
			}
			else {
				probation = (Probation)session.merge(probation);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(ProbationImpl.class, probation, false, true);

		if (isNew) {
			probation.setNew(false);
		}

		probation.resetOriginalValues();

		return probation;
	}

	/**
	 * Returns the probation with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the probation
	 * @return the probation
	 * @throws NoSuchProbationException if a probation with the primary key could not be found
	 */
	@Override
	public Probation findByPrimaryKey(Serializable primaryKey)
		throws NoSuchProbationException {

		Probation probation = fetchByPrimaryKey(primaryKey);

		if (probation == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchProbationException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return probation;
	}

	/**
	 * Returns the probation with the primary key or throws a <code>NoSuchProbationException</code> if it could not be found.
	 *
	 * @param ecode the primary key of the probation
	 * @return the probation
	 * @throws NoSuchProbationException if a probation with the primary key could not be found
	 */
	@Override
	public Probation findByPrimaryKey(String ecode)
		throws NoSuchProbationException {

		return findByPrimaryKey((Serializable)ecode);
	}

	/**
	 * Returns the probation with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param ecode the primary key of the probation
	 * @return the probation, or <code>null</code> if a probation with the primary key could not be found
	 */
	@Override
	public Probation fetchByPrimaryKey(String ecode) {
		return fetchByPrimaryKey((Serializable)ecode);
	}

	/**
	 * Returns all the probations.
	 *
	 * @return the probations
	 */
	@Override
	public List<Probation> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the probations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProbationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of probations
	 * @param end the upper bound of the range of probations (not inclusive)
	 * @return the range of probations
	 */
	@Override
	public List<Probation> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the probations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProbationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of probations
	 * @param end the upper bound of the range of probations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of probations
	 */
	@Override
	public List<Probation> findAll(
		int start, int end, OrderByComparator<Probation> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the probations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProbationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of probations
	 * @param end the upper bound of the range of probations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of probations
	 */
	@Override
	public List<Probation> findAll(
		int start, int end, OrderByComparator<Probation> orderByComparator,
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

		List<Probation> list = null;

		if (useFinderCache) {
			list = (List<Probation>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_PROBATION);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_PROBATION;

				sql = sql.concat(ProbationModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<Probation>)QueryUtil.list(
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
	 * Removes all the probations from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (Probation probation : findAll()) {
			remove(probation);
		}
	}

	/**
	 * Returns the number of probations.
	 *
	 * @return the number of probations
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_PROBATION);

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
		return "ecode";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_PROBATION;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return ProbationModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the probation persistence.
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

		_setProbationUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setProbationUtilPersistence(null);

		entityCache.removeCache(ProbationImpl.class.getName());
	}

	private void _setProbationUtilPersistence(
		ProbationPersistence probationPersistence) {

		try {
			Field field = ProbationUtil.class.getDeclaredField("_persistence");

			field.setAccessible(true);

			field.set(null, probationPersistence);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@Override
	@Reference(
		target = PROBATIONPersistenceConstants.SERVICE_CONFIGURATION_FILTER,
		unbind = "-"
	)
	public void setConfiguration(Configuration configuration) {
	}

	@Override
	@Reference(
		target = PROBATIONPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	@Reference(
		target = PROBATIONPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@Reference
	protected EntityCache entityCache;

	@Reference
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_PROBATION =
		"SELECT probation FROM Probation probation";

	private static final String _SQL_COUNT_PROBATION =
		"SELECT COUNT(probation) FROM Probation probation";

	private static final String _ORDER_BY_ENTITY_ALIAS = "probation.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No Probation exists with the primary key ";

	private static final Log _log = LogFactoryUtil.getLog(
		ProbationPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"state", "comment"});

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}