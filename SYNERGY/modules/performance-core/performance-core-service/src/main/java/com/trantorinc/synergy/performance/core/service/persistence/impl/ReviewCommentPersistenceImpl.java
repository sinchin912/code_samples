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

import com.trantorinc.synergy.performance.core.exception.NoSuchReviewCommentException;
import com.trantorinc.synergy.performance.core.model.ReviewComment;
import com.trantorinc.synergy.performance.core.model.ReviewCommentTable;
import com.trantorinc.synergy.performance.core.model.impl.ReviewCommentImpl;
import com.trantorinc.synergy.performance.core.model.impl.ReviewCommentModelImpl;
import com.trantorinc.synergy.performance.core.service.persistence.ReviewCommentPersistence;
import com.trantorinc.synergy.performance.core.service.persistence.ReviewCommentUtil;
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
 * The persistence implementation for the review comment service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = {ReviewCommentPersistence.class, BasePersistence.class})
public class ReviewCommentPersistenceImpl
	extends BasePersistenceImpl<ReviewComment>
	implements ReviewCommentPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>ReviewCommentUtil</code> to access the review comment persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		ReviewCommentImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;

	public ReviewCommentPersistenceImpl() {
		setModelClass(ReviewComment.class);

		setModelImplClass(ReviewCommentImpl.class);
		setModelPKClass(long.class);

		setTable(ReviewCommentTable.INSTANCE);
	}

	/**
	 * Caches the review comment in the entity cache if it is enabled.
	 *
	 * @param reviewComment the review comment
	 */
	@Override
	public void cacheResult(ReviewComment reviewComment) {
		entityCache.putResult(
			ReviewCommentImpl.class, reviewComment.getPrimaryKey(),
			reviewComment);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the review comments in the entity cache if it is enabled.
	 *
	 * @param reviewComments the review comments
	 */
	@Override
	public void cacheResult(List<ReviewComment> reviewComments) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (reviewComments.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (ReviewComment reviewComment : reviewComments) {
			if (entityCache.getResult(
					ReviewCommentImpl.class, reviewComment.getPrimaryKey()) ==
						null) {

				cacheResult(reviewComment);
			}
		}
	}

	/**
	 * Clears the cache for all review comments.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ReviewCommentImpl.class);

		finderCache.clearCache(ReviewCommentImpl.class);
	}

	/**
	 * Clears the cache for the review comment.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ReviewComment reviewComment) {
		entityCache.removeResult(ReviewCommentImpl.class, reviewComment);
	}

	@Override
	public void clearCache(List<ReviewComment> reviewComments) {
		for (ReviewComment reviewComment : reviewComments) {
			entityCache.removeResult(ReviewCommentImpl.class, reviewComment);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(ReviewCommentImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(ReviewCommentImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new review comment with the primary key. Does not add the review comment to the database.
	 *
	 * @param commentId the primary key for the new review comment
	 * @return the new review comment
	 */
	@Override
	public ReviewComment create(long commentId) {
		ReviewComment reviewComment = new ReviewCommentImpl();

		reviewComment.setNew(true);
		reviewComment.setPrimaryKey(commentId);

		return reviewComment;
	}

	/**
	 * Removes the review comment with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param commentId the primary key of the review comment
	 * @return the review comment that was removed
	 * @throws NoSuchReviewCommentException if a review comment with the primary key could not be found
	 */
	@Override
	public ReviewComment remove(long commentId)
		throws NoSuchReviewCommentException {

		return remove((Serializable)commentId);
	}

	/**
	 * Removes the review comment with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the review comment
	 * @return the review comment that was removed
	 * @throws NoSuchReviewCommentException if a review comment with the primary key could not be found
	 */
	@Override
	public ReviewComment remove(Serializable primaryKey)
		throws NoSuchReviewCommentException {

		Session session = null;

		try {
			session = openSession();

			ReviewComment reviewComment = (ReviewComment)session.get(
				ReviewCommentImpl.class, primaryKey);

			if (reviewComment == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchReviewCommentException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(reviewComment);
		}
		catch (NoSuchReviewCommentException noSuchEntityException) {
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
	protected ReviewComment removeImpl(ReviewComment reviewComment) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(reviewComment)) {
				reviewComment = (ReviewComment)session.get(
					ReviewCommentImpl.class, reviewComment.getPrimaryKeyObj());
			}

			if (reviewComment != null) {
				session.delete(reviewComment);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (reviewComment != null) {
			clearCache(reviewComment);
		}

		return reviewComment;
	}

	@Override
	public ReviewComment updateImpl(ReviewComment reviewComment) {
		boolean isNew = reviewComment.isNew();

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(reviewComment);
			}
			else {
				reviewComment = (ReviewComment)session.merge(reviewComment);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			ReviewCommentImpl.class, reviewComment, false, true);

		if (isNew) {
			reviewComment.setNew(false);
		}

		reviewComment.resetOriginalValues();

		return reviewComment;
	}

	/**
	 * Returns the review comment with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the review comment
	 * @return the review comment
	 * @throws NoSuchReviewCommentException if a review comment with the primary key could not be found
	 */
	@Override
	public ReviewComment findByPrimaryKey(Serializable primaryKey)
		throws NoSuchReviewCommentException {

		ReviewComment reviewComment = fetchByPrimaryKey(primaryKey);

		if (reviewComment == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchReviewCommentException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return reviewComment;
	}

	/**
	 * Returns the review comment with the primary key or throws a <code>NoSuchReviewCommentException</code> if it could not be found.
	 *
	 * @param commentId the primary key of the review comment
	 * @return the review comment
	 * @throws NoSuchReviewCommentException if a review comment with the primary key could not be found
	 */
	@Override
	public ReviewComment findByPrimaryKey(long commentId)
		throws NoSuchReviewCommentException {

		return findByPrimaryKey((Serializable)commentId);
	}

	/**
	 * Returns the review comment with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param commentId the primary key of the review comment
	 * @return the review comment, or <code>null</code> if a review comment with the primary key could not be found
	 */
	@Override
	public ReviewComment fetchByPrimaryKey(long commentId) {
		return fetchByPrimaryKey((Serializable)commentId);
	}

	/**
	 * Returns all the review comments.
	 *
	 * @return the review comments
	 */
	@Override
	public List<ReviewComment> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the review comments.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ReviewCommentModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of review comments
	 * @param end the upper bound of the range of review comments (not inclusive)
	 * @return the range of review comments
	 */
	@Override
	public List<ReviewComment> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the review comments.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ReviewCommentModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of review comments
	 * @param end the upper bound of the range of review comments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of review comments
	 */
	@Override
	public List<ReviewComment> findAll(
		int start, int end,
		OrderByComparator<ReviewComment> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the review comments.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ReviewCommentModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of review comments
	 * @param end the upper bound of the range of review comments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of review comments
	 */
	@Override
	public List<ReviewComment> findAll(
		int start, int end, OrderByComparator<ReviewComment> orderByComparator,
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

		List<ReviewComment> list = null;

		if (useFinderCache) {
			list = (List<ReviewComment>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_REVIEWCOMMENT);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_REVIEWCOMMENT;

				sql = sql.concat(ReviewCommentModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<ReviewComment>)QueryUtil.list(
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
	 * Removes all the review comments from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (ReviewComment reviewComment : findAll()) {
			remove(reviewComment);
		}
	}

	/**
	 * Returns the number of review comments.
	 *
	 * @return the number of review comments
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_REVIEWCOMMENT);

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
		return "commentId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_REVIEWCOMMENT;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return ReviewCommentModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the review comment persistence.
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

		_setReviewCommentUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setReviewCommentUtilPersistence(null);

		entityCache.removeCache(ReviewCommentImpl.class.getName());
	}

	private void _setReviewCommentUtilPersistence(
		ReviewCommentPersistence reviewCommentPersistence) {

		try {
			Field field = ReviewCommentUtil.class.getDeclaredField(
				"_persistence");

			field.setAccessible(true);

			field.set(null, reviewCommentPersistence);
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

	private static final String _SQL_SELECT_REVIEWCOMMENT =
		"SELECT reviewComment FROM ReviewComment reviewComment";

	private static final String _SQL_COUNT_REVIEWCOMMENT =
		"SELECT COUNT(reviewComment) FROM ReviewComment reviewComment";

	private static final String _ORDER_BY_ENTITY_ALIAS = "reviewComment.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No ReviewComment exists with the primary key ";

	private static final Log _log = LogFactoryUtil.getLog(
		ReviewCommentPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}