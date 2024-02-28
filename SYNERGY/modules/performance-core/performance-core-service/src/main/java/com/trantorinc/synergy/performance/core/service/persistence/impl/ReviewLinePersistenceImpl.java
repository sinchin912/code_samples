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

import com.trantorinc.synergy.performance.core.exception.NoSuchReviewLineException;
import com.trantorinc.synergy.performance.core.model.ReviewLine;
import com.trantorinc.synergy.performance.core.model.ReviewLineTable;
import com.trantorinc.synergy.performance.core.model.impl.ReviewLineImpl;
import com.trantorinc.synergy.performance.core.model.impl.ReviewLineModelImpl;
import com.trantorinc.synergy.performance.core.service.persistence.ReviewLinePersistence;
import com.trantorinc.synergy.performance.core.service.persistence.ReviewLineUtil;
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
 * The persistence implementation for the review line service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = {ReviewLinePersistence.class, BasePersistence.class})
public class ReviewLinePersistenceImpl
	extends BasePersistenceImpl<ReviewLine> implements ReviewLinePersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>ReviewLineUtil</code> to access the review line persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		ReviewLineImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;

	public ReviewLinePersistenceImpl() {
		setModelClass(ReviewLine.class);

		setModelImplClass(ReviewLineImpl.class);
		setModelPKClass(long.class);

		setTable(ReviewLineTable.INSTANCE);
	}

	/**
	 * Caches the review line in the entity cache if it is enabled.
	 *
	 * @param reviewLine the review line
	 */
	@Override
	public void cacheResult(ReviewLine reviewLine) {
		entityCache.putResult(
			ReviewLineImpl.class, reviewLine.getPrimaryKey(), reviewLine);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the review lines in the entity cache if it is enabled.
	 *
	 * @param reviewLines the review lines
	 */
	@Override
	public void cacheResult(List<ReviewLine> reviewLines) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (reviewLines.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (ReviewLine reviewLine : reviewLines) {
			if (entityCache.getResult(
					ReviewLineImpl.class, reviewLine.getPrimaryKey()) == null) {

				cacheResult(reviewLine);
			}
		}
	}

	/**
	 * Clears the cache for all review lines.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ReviewLineImpl.class);

		finderCache.clearCache(ReviewLineImpl.class);
	}

	/**
	 * Clears the cache for the review line.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ReviewLine reviewLine) {
		entityCache.removeResult(ReviewLineImpl.class, reviewLine);
	}

	@Override
	public void clearCache(List<ReviewLine> reviewLines) {
		for (ReviewLine reviewLine : reviewLines) {
			entityCache.removeResult(ReviewLineImpl.class, reviewLine);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(ReviewLineImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(ReviewLineImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new review line with the primary key. Does not add the review line to the database.
	 *
	 * @param lineId the primary key for the new review line
	 * @return the new review line
	 */
	@Override
	public ReviewLine create(long lineId) {
		ReviewLine reviewLine = new ReviewLineImpl();

		reviewLine.setNew(true);
		reviewLine.setPrimaryKey(lineId);

		return reviewLine;
	}

	/**
	 * Removes the review line with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param lineId the primary key of the review line
	 * @return the review line that was removed
	 * @throws NoSuchReviewLineException if a review line with the primary key could not be found
	 */
	@Override
	public ReviewLine remove(long lineId) throws NoSuchReviewLineException {
		return remove((Serializable)lineId);
	}

	/**
	 * Removes the review line with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the review line
	 * @return the review line that was removed
	 * @throws NoSuchReviewLineException if a review line with the primary key could not be found
	 */
	@Override
	public ReviewLine remove(Serializable primaryKey)
		throws NoSuchReviewLineException {

		Session session = null;

		try {
			session = openSession();

			ReviewLine reviewLine = (ReviewLine)session.get(
				ReviewLineImpl.class, primaryKey);

			if (reviewLine == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchReviewLineException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(reviewLine);
		}
		catch (NoSuchReviewLineException noSuchEntityException) {
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
	protected ReviewLine removeImpl(ReviewLine reviewLine) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(reviewLine)) {
				reviewLine = (ReviewLine)session.get(
					ReviewLineImpl.class, reviewLine.getPrimaryKeyObj());
			}

			if (reviewLine != null) {
				session.delete(reviewLine);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (reviewLine != null) {
			clearCache(reviewLine);
		}

		return reviewLine;
	}

	@Override
	public ReviewLine updateImpl(ReviewLine reviewLine) {
		boolean isNew = reviewLine.isNew();

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(reviewLine);
			}
			else {
				reviewLine = (ReviewLine)session.merge(reviewLine);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(ReviewLineImpl.class, reviewLine, false, true);

		if (isNew) {
			reviewLine.setNew(false);
		}

		reviewLine.resetOriginalValues();

		return reviewLine;
	}

	/**
	 * Returns the review line with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the review line
	 * @return the review line
	 * @throws NoSuchReviewLineException if a review line with the primary key could not be found
	 */
	@Override
	public ReviewLine findByPrimaryKey(Serializable primaryKey)
		throws NoSuchReviewLineException {

		ReviewLine reviewLine = fetchByPrimaryKey(primaryKey);

		if (reviewLine == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchReviewLineException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return reviewLine;
	}

	/**
	 * Returns the review line with the primary key or throws a <code>NoSuchReviewLineException</code> if it could not be found.
	 *
	 * @param lineId the primary key of the review line
	 * @return the review line
	 * @throws NoSuchReviewLineException if a review line with the primary key could not be found
	 */
	@Override
	public ReviewLine findByPrimaryKey(long lineId)
		throws NoSuchReviewLineException {

		return findByPrimaryKey((Serializable)lineId);
	}

	/**
	 * Returns the review line with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param lineId the primary key of the review line
	 * @return the review line, or <code>null</code> if a review line with the primary key could not be found
	 */
	@Override
	public ReviewLine fetchByPrimaryKey(long lineId) {
		return fetchByPrimaryKey((Serializable)lineId);
	}

	/**
	 * Returns all the review lines.
	 *
	 * @return the review lines
	 */
	@Override
	public List<ReviewLine> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the review lines.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ReviewLineModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of review lines
	 * @param end the upper bound of the range of review lines (not inclusive)
	 * @return the range of review lines
	 */
	@Override
	public List<ReviewLine> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the review lines.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ReviewLineModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of review lines
	 * @param end the upper bound of the range of review lines (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of review lines
	 */
	@Override
	public List<ReviewLine> findAll(
		int start, int end, OrderByComparator<ReviewLine> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the review lines.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ReviewLineModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of review lines
	 * @param end the upper bound of the range of review lines (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of review lines
	 */
	@Override
	public List<ReviewLine> findAll(
		int start, int end, OrderByComparator<ReviewLine> orderByComparator,
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

		List<ReviewLine> list = null;

		if (useFinderCache) {
			list = (List<ReviewLine>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_REVIEWLINE);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_REVIEWLINE;

				sql = sql.concat(ReviewLineModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<ReviewLine>)QueryUtil.list(
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
	 * Removes all the review lines from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (ReviewLine reviewLine : findAll()) {
			remove(reviewLine);
		}
	}

	/**
	 * Returns the number of review lines.
	 *
	 * @return the number of review lines
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_REVIEWLINE);

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
		return _SQL_SELECT_REVIEWLINE;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return ReviewLineModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the review line persistence.
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

		_setReviewLineUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setReviewLineUtilPersistence(null);

		entityCache.removeCache(ReviewLineImpl.class.getName());
	}

	private void _setReviewLineUtilPersistence(
		ReviewLinePersistence reviewLinePersistence) {

		try {
			Field field = ReviewLineUtil.class.getDeclaredField("_persistence");

			field.setAccessible(true);

			field.set(null, reviewLinePersistence);
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

	private static final String _SQL_SELECT_REVIEWLINE =
		"SELECT reviewLine FROM ReviewLine reviewLine";

	private static final String _SQL_COUNT_REVIEWLINE =
		"SELECT COUNT(reviewLine) FROM ReviewLine reviewLine";

	private static final String _ORDER_BY_ENTITY_ALIAS = "reviewLine.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No ReviewLine exists with the primary key ";

	private static final Log _log = LogFactoryUtil.getLog(
		ReviewLinePersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}