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

import com.trantorinc.synergy.performance.core.exception.NoSuchReviewCloseException;
import com.trantorinc.synergy.performance.core.model.ReviewClose;
import com.trantorinc.synergy.performance.core.model.ReviewCloseTable;
import com.trantorinc.synergy.performance.core.model.impl.ReviewCloseImpl;
import com.trantorinc.synergy.performance.core.model.impl.ReviewCloseModelImpl;
import com.trantorinc.synergy.performance.core.service.persistence.ReviewClosePersistence;
import com.trantorinc.synergy.performance.core.service.persistence.ReviewCloseUtil;
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
 * The persistence implementation for the review close service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = {ReviewClosePersistence.class, BasePersistence.class})
public class ReviewClosePersistenceImpl
	extends BasePersistenceImpl<ReviewClose> implements ReviewClosePersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>ReviewCloseUtil</code> to access the review close persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		ReviewCloseImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;

	public ReviewClosePersistenceImpl() {
		setModelClass(ReviewClose.class);

		setModelImplClass(ReviewCloseImpl.class);
		setModelPKClass(long.class);

		setTable(ReviewCloseTable.INSTANCE);
	}

	/**
	 * Caches the review close in the entity cache if it is enabled.
	 *
	 * @param reviewClose the review close
	 */
	@Override
	public void cacheResult(ReviewClose reviewClose) {
		entityCache.putResult(
			ReviewCloseImpl.class, reviewClose.getPrimaryKey(), reviewClose);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the review closes in the entity cache if it is enabled.
	 *
	 * @param reviewCloses the review closes
	 */
	@Override
	public void cacheResult(List<ReviewClose> reviewCloses) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (reviewCloses.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (ReviewClose reviewClose : reviewCloses) {
			if (entityCache.getResult(
					ReviewCloseImpl.class, reviewClose.getPrimaryKey()) ==
						null) {

				cacheResult(reviewClose);
			}
		}
	}

	/**
	 * Clears the cache for all review closes.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ReviewCloseImpl.class);

		finderCache.clearCache(ReviewCloseImpl.class);
	}

	/**
	 * Clears the cache for the review close.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ReviewClose reviewClose) {
		entityCache.removeResult(ReviewCloseImpl.class, reviewClose);
	}

	@Override
	public void clearCache(List<ReviewClose> reviewCloses) {
		for (ReviewClose reviewClose : reviewCloses) {
			entityCache.removeResult(ReviewCloseImpl.class, reviewClose);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(ReviewCloseImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(ReviewCloseImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new review close with the primary key. Does not add the review close to the database.
	 *
	 * @param closeId the primary key for the new review close
	 * @return the new review close
	 */
	@Override
	public ReviewClose create(long closeId) {
		ReviewClose reviewClose = new ReviewCloseImpl();

		reviewClose.setNew(true);
		reviewClose.setPrimaryKey(closeId);

		return reviewClose;
	}

	/**
	 * Removes the review close with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param closeId the primary key of the review close
	 * @return the review close that was removed
	 * @throws NoSuchReviewCloseException if a review close with the primary key could not be found
	 */
	@Override
	public ReviewClose remove(long closeId) throws NoSuchReviewCloseException {
		return remove((Serializable)closeId);
	}

	/**
	 * Removes the review close with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the review close
	 * @return the review close that was removed
	 * @throws NoSuchReviewCloseException if a review close with the primary key could not be found
	 */
	@Override
	public ReviewClose remove(Serializable primaryKey)
		throws NoSuchReviewCloseException {

		Session session = null;

		try {
			session = openSession();

			ReviewClose reviewClose = (ReviewClose)session.get(
				ReviewCloseImpl.class, primaryKey);

			if (reviewClose == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchReviewCloseException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(reviewClose);
		}
		catch (NoSuchReviewCloseException noSuchEntityException) {
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
	protected ReviewClose removeImpl(ReviewClose reviewClose) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(reviewClose)) {
				reviewClose = (ReviewClose)session.get(
					ReviewCloseImpl.class, reviewClose.getPrimaryKeyObj());
			}

			if (reviewClose != null) {
				session.delete(reviewClose);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (reviewClose != null) {
			clearCache(reviewClose);
		}

		return reviewClose;
	}

	@Override
	public ReviewClose updateImpl(ReviewClose reviewClose) {
		boolean isNew = reviewClose.isNew();

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(reviewClose);
			}
			else {
				reviewClose = (ReviewClose)session.merge(reviewClose);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(ReviewCloseImpl.class, reviewClose, false, true);

		if (isNew) {
			reviewClose.setNew(false);
		}

		reviewClose.resetOriginalValues();

		return reviewClose;
	}

	/**
	 * Returns the review close with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the review close
	 * @return the review close
	 * @throws NoSuchReviewCloseException if a review close with the primary key could not be found
	 */
	@Override
	public ReviewClose findByPrimaryKey(Serializable primaryKey)
		throws NoSuchReviewCloseException {

		ReviewClose reviewClose = fetchByPrimaryKey(primaryKey);

		if (reviewClose == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchReviewCloseException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return reviewClose;
	}

	/**
	 * Returns the review close with the primary key or throws a <code>NoSuchReviewCloseException</code> if it could not be found.
	 *
	 * @param closeId the primary key of the review close
	 * @return the review close
	 * @throws NoSuchReviewCloseException if a review close with the primary key could not be found
	 */
	@Override
	public ReviewClose findByPrimaryKey(long closeId)
		throws NoSuchReviewCloseException {

		return findByPrimaryKey((Serializable)closeId);
	}

	/**
	 * Returns the review close with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param closeId the primary key of the review close
	 * @return the review close, or <code>null</code> if a review close with the primary key could not be found
	 */
	@Override
	public ReviewClose fetchByPrimaryKey(long closeId) {
		return fetchByPrimaryKey((Serializable)closeId);
	}

	/**
	 * Returns all the review closes.
	 *
	 * @return the review closes
	 */
	@Override
	public List<ReviewClose> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the review closes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ReviewCloseModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of review closes
	 * @param end the upper bound of the range of review closes (not inclusive)
	 * @return the range of review closes
	 */
	@Override
	public List<ReviewClose> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the review closes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ReviewCloseModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of review closes
	 * @param end the upper bound of the range of review closes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of review closes
	 */
	@Override
	public List<ReviewClose> findAll(
		int start, int end, OrderByComparator<ReviewClose> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the review closes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ReviewCloseModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of review closes
	 * @param end the upper bound of the range of review closes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of review closes
	 */
	@Override
	public List<ReviewClose> findAll(
		int start, int end, OrderByComparator<ReviewClose> orderByComparator,
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

		List<ReviewClose> list = null;

		if (useFinderCache) {
			list = (List<ReviewClose>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_REVIEWCLOSE);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_REVIEWCLOSE;

				sql = sql.concat(ReviewCloseModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<ReviewClose>)QueryUtil.list(
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
	 * Removes all the review closes from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (ReviewClose reviewClose : findAll()) {
			remove(reviewClose);
		}
	}

	/**
	 * Returns the number of review closes.
	 *
	 * @return the number of review closes
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_REVIEWCLOSE);

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
		return "closeId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_REVIEWCLOSE;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return ReviewCloseModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the review close persistence.
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

		_setReviewCloseUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setReviewCloseUtilPersistence(null);

		entityCache.removeCache(ReviewCloseImpl.class.getName());
	}

	private void _setReviewCloseUtilPersistence(
		ReviewClosePersistence reviewClosePersistence) {

		try {
			Field field = ReviewCloseUtil.class.getDeclaredField(
				"_persistence");

			field.setAccessible(true);

			field.set(null, reviewClosePersistence);
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

	private static final String _SQL_SELECT_REVIEWCLOSE =
		"SELECT reviewClose FROM ReviewClose reviewClose";

	private static final String _SQL_COUNT_REVIEWCLOSE =
		"SELECT COUNT(reviewClose) FROM ReviewClose reviewClose";

	private static final String _ORDER_BY_ENTITY_ALIAS = "reviewClose.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No ReviewClose exists with the primary key ";

	private static final Log _log = LogFactoryUtil.getLog(
		ReviewClosePersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}