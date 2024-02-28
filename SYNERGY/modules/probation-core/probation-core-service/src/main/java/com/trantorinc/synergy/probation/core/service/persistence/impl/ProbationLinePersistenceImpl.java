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
import com.liferay.portal.kernel.service.persistence.BasePersistence;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.SetUtil;

import com.trantorinc.synergy.probation.core.exception.NoSuchProbationLineException;
import com.trantorinc.synergy.probation.core.model.ProbationLine;
import com.trantorinc.synergy.probation.core.model.ProbationLineTable;
import com.trantorinc.synergy.probation.core.model.impl.ProbationLineImpl;
import com.trantorinc.synergy.probation.core.model.impl.ProbationLineModelImpl;
import com.trantorinc.synergy.probation.core.service.persistence.ProbationLinePersistence;
import com.trantorinc.synergy.probation.core.service.persistence.ProbationLineUtil;
import com.trantorinc.synergy.probation.core.service.persistence.impl.constants.PROBATIONPersistenceConstants;

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
 * The persistence implementation for the probation line service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = {ProbationLinePersistence.class, BasePersistence.class})
public class ProbationLinePersistenceImpl
	extends BasePersistenceImpl<ProbationLine>
	implements ProbationLinePersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>ProbationLineUtil</code> to access the probation line persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		ProbationLineImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;

	public ProbationLinePersistenceImpl() {
		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("comment", "comment_");

		setDBColumnNames(dbColumnNames);

		setModelClass(ProbationLine.class);

		setModelImplClass(ProbationLineImpl.class);
		setModelPKClass(long.class);

		setTable(ProbationLineTable.INSTANCE);
	}

	/**
	 * Caches the probation line in the entity cache if it is enabled.
	 *
	 * @param probationLine the probation line
	 */
	@Override
	public void cacheResult(ProbationLine probationLine) {
		entityCache.putResult(
			ProbationLineImpl.class, probationLine.getPrimaryKey(),
			probationLine);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the probation lines in the entity cache if it is enabled.
	 *
	 * @param probationLines the probation lines
	 */
	@Override
	public void cacheResult(List<ProbationLine> probationLines) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (probationLines.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (ProbationLine probationLine : probationLines) {
			if (entityCache.getResult(
					ProbationLineImpl.class, probationLine.getPrimaryKey()) ==
						null) {

				cacheResult(probationLine);
			}
		}
	}

	/**
	 * Clears the cache for all probation lines.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ProbationLineImpl.class);

		finderCache.clearCache(ProbationLineImpl.class);
	}

	/**
	 * Clears the cache for the probation line.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ProbationLine probationLine) {
		entityCache.removeResult(ProbationLineImpl.class, probationLine);
	}

	@Override
	public void clearCache(List<ProbationLine> probationLines) {
		for (ProbationLine probationLine : probationLines) {
			entityCache.removeResult(ProbationLineImpl.class, probationLine);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(ProbationLineImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(ProbationLineImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new probation line with the primary key. Does not add the probation line to the database.
	 *
	 * @param lineId the primary key for the new probation line
	 * @return the new probation line
	 */
	@Override
	public ProbationLine create(long lineId) {
		ProbationLine probationLine = new ProbationLineImpl();

		probationLine.setNew(true);
		probationLine.setPrimaryKey(lineId);

		return probationLine;
	}

	/**
	 * Removes the probation line with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param lineId the primary key of the probation line
	 * @return the probation line that was removed
	 * @throws NoSuchProbationLineException if a probation line with the primary key could not be found
	 */
	@Override
	public ProbationLine remove(long lineId)
		throws NoSuchProbationLineException {

		return remove((Serializable)lineId);
	}

	/**
	 * Removes the probation line with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the probation line
	 * @return the probation line that was removed
	 * @throws NoSuchProbationLineException if a probation line with the primary key could not be found
	 */
	@Override
	public ProbationLine remove(Serializable primaryKey)
		throws NoSuchProbationLineException {

		Session session = null;

		try {
			session = openSession();

			ProbationLine probationLine = (ProbationLine)session.get(
				ProbationLineImpl.class, primaryKey);

			if (probationLine == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchProbationLineException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(probationLine);
		}
		catch (NoSuchProbationLineException noSuchEntityException) {
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
	protected ProbationLine removeImpl(ProbationLine probationLine) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(probationLine)) {
				probationLine = (ProbationLine)session.get(
					ProbationLineImpl.class, probationLine.getPrimaryKeyObj());
			}

			if (probationLine != null) {
				session.delete(probationLine);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (probationLine != null) {
			clearCache(probationLine);
		}

		return probationLine;
	}

	@Override
	public ProbationLine updateImpl(ProbationLine probationLine) {
		boolean isNew = probationLine.isNew();

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(probationLine);
			}
			else {
				probationLine = (ProbationLine)session.merge(probationLine);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			ProbationLineImpl.class, probationLine, false, true);

		if (isNew) {
			probationLine.setNew(false);
		}

		probationLine.resetOriginalValues();

		return probationLine;
	}

	/**
	 * Returns the probation line with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the probation line
	 * @return the probation line
	 * @throws NoSuchProbationLineException if a probation line with the primary key could not be found
	 */
	@Override
	public ProbationLine findByPrimaryKey(Serializable primaryKey)
		throws NoSuchProbationLineException {

		ProbationLine probationLine = fetchByPrimaryKey(primaryKey);

		if (probationLine == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchProbationLineException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return probationLine;
	}

	/**
	 * Returns the probation line with the primary key or throws a <code>NoSuchProbationLineException</code> if it could not be found.
	 *
	 * @param lineId the primary key of the probation line
	 * @return the probation line
	 * @throws NoSuchProbationLineException if a probation line with the primary key could not be found
	 */
	@Override
	public ProbationLine findByPrimaryKey(long lineId)
		throws NoSuchProbationLineException {

		return findByPrimaryKey((Serializable)lineId);
	}

	/**
	 * Returns the probation line with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param lineId the primary key of the probation line
	 * @return the probation line, or <code>null</code> if a probation line with the primary key could not be found
	 */
	@Override
	public ProbationLine fetchByPrimaryKey(long lineId) {
		return fetchByPrimaryKey((Serializable)lineId);
	}

	/**
	 * Returns all the probation lines.
	 *
	 * @return the probation lines
	 */
	@Override
	public List<ProbationLine> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the probation lines.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProbationLineModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of probation lines
	 * @param end the upper bound of the range of probation lines (not inclusive)
	 * @return the range of probation lines
	 */
	@Override
	public List<ProbationLine> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the probation lines.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProbationLineModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of probation lines
	 * @param end the upper bound of the range of probation lines (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of probation lines
	 */
	@Override
	public List<ProbationLine> findAll(
		int start, int end,
		OrderByComparator<ProbationLine> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the probation lines.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProbationLineModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of probation lines
	 * @param end the upper bound of the range of probation lines (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of probation lines
	 */
	@Override
	public List<ProbationLine> findAll(
		int start, int end, OrderByComparator<ProbationLine> orderByComparator,
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

		List<ProbationLine> list = null;

		if (useFinderCache) {
			list = (List<ProbationLine>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_PROBATIONLINE);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_PROBATIONLINE;

				sql = sql.concat(ProbationLineModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<ProbationLine>)QueryUtil.list(
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
	 * Removes all the probation lines from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (ProbationLine probationLine : findAll()) {
			remove(probationLine);
		}
	}

	/**
	 * Returns the number of probation lines.
	 *
	 * @return the number of probation lines
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_PROBATIONLINE);

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
		return "lineId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_PROBATIONLINE;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return ProbationLineModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the probation line persistence.
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

		_setProbationLineUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setProbationLineUtilPersistence(null);

		entityCache.removeCache(ProbationLineImpl.class.getName());
	}

	private void _setProbationLineUtilPersistence(
		ProbationLinePersistence probationLinePersistence) {

		try {
			Field field = ProbationLineUtil.class.getDeclaredField(
				"_persistence");

			field.setAccessible(true);

			field.set(null, probationLinePersistence);
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

	private static final String _SQL_SELECT_PROBATIONLINE =
		"SELECT probationLine FROM ProbationLine probationLine";

	private static final String _SQL_COUNT_PROBATIONLINE =
		"SELECT COUNT(probationLine) FROM ProbationLine probationLine";

	private static final String _ORDER_BY_ENTITY_ALIAS = "probationLine.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No ProbationLine exists with the primary key ";

	private static final Log _log = LogFactoryUtil.getLog(
		ProbationLinePersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"comment"});

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}