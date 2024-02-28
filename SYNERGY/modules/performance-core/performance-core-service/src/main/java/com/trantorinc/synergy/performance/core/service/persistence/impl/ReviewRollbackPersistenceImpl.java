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

import com.trantorinc.synergy.performance.core.exception.NoSuchReviewRollbackException;
import com.trantorinc.synergy.performance.core.model.ReviewRollback;
import com.trantorinc.synergy.performance.core.model.ReviewRollbackTable;
import com.trantorinc.synergy.performance.core.model.impl.ReviewRollbackImpl;
import com.trantorinc.synergy.performance.core.model.impl.ReviewRollbackModelImpl;
import com.trantorinc.synergy.performance.core.service.persistence.ReviewRollbackPersistence;
import com.trantorinc.synergy.performance.core.service.persistence.ReviewRollbackUtil;
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
 * The persistence implementation for the review rollback service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = {ReviewRollbackPersistence.class, BasePersistence.class})
public class ReviewRollbackPersistenceImpl
	extends BasePersistenceImpl<ReviewRollback>
	implements ReviewRollbackPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>ReviewRollbackUtil</code> to access the review rollback persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		ReviewRollbackImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;

	public ReviewRollbackPersistenceImpl() {
		setModelClass(ReviewRollback.class);

		setModelImplClass(ReviewRollbackImpl.class);
		setModelPKClass(long.class);

		setTable(ReviewRollbackTable.INSTANCE);
	}

	/**
	 * Caches the review rollback in the entity cache if it is enabled.
	 *
	 * @param reviewRollback the review rollback
	 */
	@Override
	public void cacheResult(ReviewRollback reviewRollback) {
		entityCache.putResult(
			ReviewRollbackImpl.class, reviewRollback.getPrimaryKey(),
			reviewRollback);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the review rollbacks in the entity cache if it is enabled.
	 *
	 * @param reviewRollbacks the review rollbacks
	 */
	@Override
	public void cacheResult(List<ReviewRollback> reviewRollbacks) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (reviewRollbacks.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (ReviewRollback reviewRollback : reviewRollbacks) {
			if (entityCache.getResult(
					ReviewRollbackImpl.class, reviewRollback.getPrimaryKey()) ==
						null) {

				cacheResult(reviewRollback);
			}
		}
	}

	/**
	 * Clears the cache for all review rollbacks.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ReviewRollbackImpl.class);

		finderCache.clearCache(ReviewRollbackImpl.class);
	}

	/**
	 * Clears the cache for the review rollback.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ReviewRollback reviewRollback) {
		entityCache.removeResult(ReviewRollbackImpl.class, reviewRollback);
	}

	@Override
	public void clearCache(List<ReviewRollback> reviewRollbacks) {
		for (ReviewRollback reviewRollback : reviewRollbacks) {
			entityCache.removeResult(ReviewRollbackImpl.class, reviewRollback);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(ReviewRollbackImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(ReviewRollbackImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new review rollback with the primary key. Does not add the review rollback to the database.
	 *
	 * @param rollbackId the primary key for the new review rollback
	 * @return the new review rollback
	 */
	@Override
	public ReviewRollback create(long rollbackId) {
		ReviewRollback reviewRollback = new ReviewRollbackImpl();

		reviewRollback.setNew(true);
		reviewRollback.setPrimaryKey(rollbackId);

		return reviewRollback;
	}

	/**
	 * Removes the review rollback with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param rollbackId the primary key of the review rollback
	 * @return the review rollback that was removed
	 * @throws NoSuchReviewRollbackException if a review rollback with the primary key could not be found
	 */
	@Override
	public ReviewRollback remove(long rollbackId)
		throws NoSuchReviewRollbackException {

		return remove((Serializable)rollbackId);
	}

	/**
	 * Removes the review rollback with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the review rollback
	 * @return the review rollback that was removed
	 * @throws NoSuchReviewRollbackException if a review rollback with the primary key could not be found
	 */
	@Override
	public ReviewRollback remove(Serializable primaryKey)
		throws NoSuchReviewRollbackException {

		Session session = null;

		try {
			session = openSession();

			ReviewRollback reviewRollback = (ReviewRollback)session.get(
				ReviewRollbackImpl.class, primaryKey);

			if (reviewRollback == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchReviewRollbackException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(reviewRollback);
		}
		catch (NoSuchReviewRollbackException noSuchEntityException) {
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
	protected ReviewRollback removeImpl(ReviewRollback reviewRollback) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(reviewRollback)) {
				reviewRollback = (ReviewRollback)session.get(
					ReviewRollbackImpl.class,
					reviewRollback.getPrimaryKeyObj());
			}

			if (reviewRollback != null) {
				session.delete(reviewRollback);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (reviewRollback != null) {
			clearCache(reviewRollback);
		}

		return reviewRollback;
	}

	@Override
	public ReviewRollback updateImpl(ReviewRollback reviewRollback) {
		boolean isNew = reviewRollback.isNew();

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(reviewRollback);
			}
			else {
				reviewRollback = (ReviewRollback)session.merge(reviewRollback);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			ReviewRollbackImpl.class, reviewRollback, false, true);

		if (isNew) {
			reviewRollback.setNew(false);
		}

		reviewRollback.resetOriginalValues();

		return reviewRollback;
	}

	/**
	 * Returns the review rollback with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the review rollback
	 * @return the review rollback
	 * @throws NoSuchReviewRollbackException if a review rollback with the primary key could not be found
	 */
	@Override
	public ReviewRollback findByPrimaryKey(Serializable primaryKey)
		throws NoSuchReviewRollbackException {

		ReviewRollback reviewRollback = fetchByPrimaryKey(primaryKey);

		if (reviewRollback == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchReviewRollbackException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return reviewRollback;
	}

	/**
	 * Returns the review rollback with the primary key or throws a <code>NoSuchReviewRollbackException</code> if it could not be found.
	 *
	 * @param rollbackId the primary key of the review rollback
	 * @return the review rollback
	 * @throws NoSuchReviewRollbackException if a review rollback with the primary key could not be found
	 */
	@Override
	public ReviewRollback findByPrimaryKey(long rollbackId)
		throws NoSuchReviewRollbackException {

		return findByPrimaryKey((Serializable)rollbackId);
	}

	/**
	 * Returns the review rollback with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param rollbackId the primary key of the review rollback
	 * @return the review rollback, or <code>null</code> if a review rollback with the primary key could not be found
	 */
	@Override
	public ReviewRollback fetchByPrimaryKey(long rollbackId) {
		return fetchByPrimaryKey((Serializable)rollbackId);
	}

	/**
	 * Returns all the review rollbacks.
	 *
	 * @return the review rollbacks
	 */
	@Override
	public List<ReviewRollback> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the review rollbacks.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ReviewRollbackModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of review rollbacks
	 * @param end the upper bound of the range of review rollbacks (not inclusive)
	 * @return the range of review rollbacks
	 */
	@Override
	public List<ReviewRollback> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the review rollbacks.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ReviewRollbackModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of review rollbacks
	 * @param end the upper bound of the range of review rollbacks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of review rollbacks
	 */
	@Override
	public List<ReviewRollback> findAll(
		int start, int end,
		OrderByComparator<ReviewRollback> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the review rollbacks.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ReviewRollbackModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of review rollbacks
	 * @param end the upper bound of the range of review rollbacks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of review rollbacks
	 */
	@Override
	public List<ReviewRollback> findAll(
		int start, int end, OrderByComparator<ReviewRollback> orderByComparator,
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

		List<ReviewRollback> list = null;

		if (useFinderCache) {
			list = (List<ReviewRollback>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_REVIEWROLLBACK);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_REVIEWROLLBACK;

				sql = sql.concat(ReviewRollbackModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<ReviewRollback>)QueryUtil.list(
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
	 * Removes all the review rollbacks from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (ReviewRollback reviewRollback : findAll()) {
			remove(reviewRollback);
		}
	}

	/**
	 * Returns the number of review rollbacks.
	 *
	 * @return the number of review rollbacks
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_REVIEWROLLBACK);

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
		return "rollbackId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_REVIEWROLLBACK;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return ReviewRollbackModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the review rollback persistence.
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

		_setReviewRollbackUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setReviewRollbackUtilPersistence(null);

		entityCache.removeCache(ReviewRollbackImpl.class.getName());
	}

	private void _setReviewRollbackUtilPersistence(
		ReviewRollbackPersistence reviewRollbackPersistence) {

		try {
			Field field = ReviewRollbackUtil.class.getDeclaredField(
				"_persistence");

			field.setAccessible(true);

			field.set(null, reviewRollbackPersistence);
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

	private static final String _SQL_SELECT_REVIEWROLLBACK =
		"SELECT reviewRollback FROM ReviewRollback reviewRollback";

	private static final String _SQL_COUNT_REVIEWROLLBACK =
		"SELECT COUNT(reviewRollback) FROM ReviewRollback reviewRollback";

	private static final String _ORDER_BY_ENTITY_ALIAS = "reviewRollback.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No ReviewRollback exists with the primary key ";

	private static final Log _log = LogFactoryUtil.getLog(
		ReviewRollbackPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}