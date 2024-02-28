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
import com.liferay.portal.kernel.util.SetUtil;

import com.trantorinc.synergy.performance.core.exception.NoSuchReviewException;
import com.trantorinc.synergy.performance.core.model.Review;
import com.trantorinc.synergy.performance.core.model.ReviewTable;
import com.trantorinc.synergy.performance.core.model.impl.ReviewImpl;
import com.trantorinc.synergy.performance.core.model.impl.ReviewModelImpl;
import com.trantorinc.synergy.performance.core.service.persistence.ReviewPersistence;
import com.trantorinc.synergy.performance.core.service.persistence.ReviewUtil;
import com.trantorinc.synergy.performance.core.service.persistence.impl.constants.PERFORMANCEPersistenceConstants;

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
 * The persistence implementation for the review service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = {ReviewPersistence.class, BasePersistence.class})
public class ReviewPersistenceImpl
	extends BasePersistenceImpl<Review> implements ReviewPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>ReviewUtil</code> to access the review persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		ReviewImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;

	public ReviewPersistenceImpl() {
		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("comment", "comment_");

		setDBColumnNames(dbColumnNames);

		setModelClass(Review.class);

		setModelImplClass(ReviewImpl.class);
		setModelPKClass(long.class);

		setTable(ReviewTable.INSTANCE);
	}

	/**
	 * Caches the review in the entity cache if it is enabled.
	 *
	 * @param review the review
	 */
	@Override
	public void cacheResult(Review review) {
		entityCache.putResult(ReviewImpl.class, review.getPrimaryKey(), review);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the reviews in the entity cache if it is enabled.
	 *
	 * @param reviews the reviews
	 */
	@Override
	public void cacheResult(List<Review> reviews) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (reviews.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (Review review : reviews) {
			if (entityCache.getResult(
					ReviewImpl.class, review.getPrimaryKey()) == null) {

				cacheResult(review);
			}
		}
	}

	/**
	 * Clears the cache for all reviews.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ReviewImpl.class);

		finderCache.clearCache(ReviewImpl.class);
	}

	/**
	 * Clears the cache for the review.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Review review) {
		entityCache.removeResult(ReviewImpl.class, review);
	}

	@Override
	public void clearCache(List<Review> reviews) {
		for (Review review : reviews) {
			entityCache.removeResult(ReviewImpl.class, review);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(ReviewImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(ReviewImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new review with the primary key. Does not add the review to the database.
	 *
	 * @param reviewId the primary key for the new review
	 * @return the new review
	 */
	@Override
	public Review create(long reviewId) {
		Review review = new ReviewImpl();

		review.setNew(true);
		review.setPrimaryKey(reviewId);

		return review;
	}

	/**
	 * Removes the review with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param reviewId the primary key of the review
	 * @return the review that was removed
	 * @throws NoSuchReviewException if a review with the primary key could not be found
	 */
	@Override
	public Review remove(long reviewId) throws NoSuchReviewException {
		return remove((Serializable)reviewId);
	}

	/**
	 * Removes the review with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the review
	 * @return the review that was removed
	 * @throws NoSuchReviewException if a review with the primary key could not be found
	 */
	@Override
	public Review remove(Serializable primaryKey) throws NoSuchReviewException {
		Session session = null;

		try {
			session = openSession();

			Review review = (Review)session.get(ReviewImpl.class, primaryKey);

			if (review == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchReviewException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(review);
		}
		catch (NoSuchReviewException noSuchEntityException) {
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
	protected Review removeImpl(Review review) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(review)) {
				review = (Review)session.get(
					ReviewImpl.class, review.getPrimaryKeyObj());
			}

			if (review != null) {
				session.delete(review);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (review != null) {
			clearCache(review);
		}

		return review;
	}

	@Override
	public Review updateImpl(Review review) {
		boolean isNew = review.isNew();

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(review);
			}
			else {
				review = (Review)session.merge(review);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(ReviewImpl.class, review, false, true);

		if (isNew) {
			review.setNew(false);
		}

		review.resetOriginalValues();

		return review;
	}

	/**
	 * Returns the review with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the review
	 * @return the review
	 * @throws NoSuchReviewException if a review with the primary key could not be found
	 */
	@Override
	public Review findByPrimaryKey(Serializable primaryKey)
		throws NoSuchReviewException {

		Review review = fetchByPrimaryKey(primaryKey);

		if (review == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchReviewException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return review;
	}

	/**
	 * Returns the review with the primary key or throws a <code>NoSuchReviewException</code> if it could not be found.
	 *
	 * @param reviewId the primary key of the review
	 * @return the review
	 * @throws NoSuchReviewException if a review with the primary key could not be found
	 */
	@Override
	public Review findByPrimaryKey(long reviewId) throws NoSuchReviewException {
		return findByPrimaryKey((Serializable)reviewId);
	}

	/**
	 * Returns the review with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param reviewId the primary key of the review
	 * @return the review, or <code>null</code> if a review with the primary key could not be found
	 */
	@Override
	public Review fetchByPrimaryKey(long reviewId) {
		return fetchByPrimaryKey((Serializable)reviewId);
	}

	/**
	 * Returns all the reviews.
	 *
	 * @return the reviews
	 */
	@Override
	public List<Review> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the reviews.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ReviewModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of reviews
	 * @param end the upper bound of the range of reviews (not inclusive)
	 * @return the range of reviews
	 */
	@Override
	public List<Review> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the reviews.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ReviewModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of reviews
	 * @param end the upper bound of the range of reviews (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of reviews
	 */
	@Override
	public List<Review> findAll(
		int start, int end, OrderByComparator<Review> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the reviews.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ReviewModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of reviews
	 * @param end the upper bound of the range of reviews (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of reviews
	 */
	@Override
	public List<Review> findAll(
		int start, int end, OrderByComparator<Review> orderByComparator,
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

		List<Review> list = null;

		if (useFinderCache) {
			list = (List<Review>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_REVIEW);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_REVIEW;

				sql = sql.concat(ReviewModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<Review>)QueryUtil.list(
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
	 * Removes all the reviews from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (Review review : findAll()) {
			remove(review);
		}
	}

	/**
	 * Returns the number of reviews.
	 *
	 * @return the number of reviews
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_REVIEW);

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
		return "reviewId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_REVIEW;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return ReviewModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the review persistence.
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

		_setReviewUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setReviewUtilPersistence(null);

		entityCache.removeCache(ReviewImpl.class.getName());
	}

	private void _setReviewUtilPersistence(
		ReviewPersistence reviewPersistence) {

		try {
			Field field = ReviewUtil.class.getDeclaredField("_persistence");

			field.setAccessible(true);

			field.set(null, reviewPersistence);
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

	private static final String _SQL_SELECT_REVIEW =
		"SELECT review FROM Review review";

	private static final String _SQL_COUNT_REVIEW =
		"SELECT COUNT(review) FROM Review review";

	private static final String _ORDER_BY_ENTITY_ALIAS = "review.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No Review exists with the primary key ";

	private static final Log _log = LogFactoryUtil.getLog(
		ReviewPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"comment"});

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}