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

import com.trantorinc.synergy.performance.core.exception.NoSuchReviewTimelineException;
import com.trantorinc.synergy.performance.core.model.ReviewTimeline;
import com.trantorinc.synergy.performance.core.model.ReviewTimelineTable;
import com.trantorinc.synergy.performance.core.model.impl.ReviewTimelineImpl;
import com.trantorinc.synergy.performance.core.model.impl.ReviewTimelineModelImpl;
import com.trantorinc.synergy.performance.core.service.persistence.ReviewTimelinePersistence;
import com.trantorinc.synergy.performance.core.service.persistence.ReviewTimelineUtil;
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
 * The persistence implementation for the review timeline service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = {ReviewTimelinePersistence.class, BasePersistence.class})
public class ReviewTimelinePersistenceImpl
	extends BasePersistenceImpl<ReviewTimeline>
	implements ReviewTimelinePersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>ReviewTimelineUtil</code> to access the review timeline persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		ReviewTimelineImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;

	public ReviewTimelinePersistenceImpl() {
		setModelClass(ReviewTimeline.class);

		setModelImplClass(ReviewTimelineImpl.class);
		setModelPKClass(long.class);

		setTable(ReviewTimelineTable.INSTANCE);
	}

	/**
	 * Caches the review timeline in the entity cache if it is enabled.
	 *
	 * @param reviewTimeline the review timeline
	 */
	@Override
	public void cacheResult(ReviewTimeline reviewTimeline) {
		entityCache.putResult(
			ReviewTimelineImpl.class, reviewTimeline.getPrimaryKey(),
			reviewTimeline);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the review timelines in the entity cache if it is enabled.
	 *
	 * @param reviewTimelines the review timelines
	 */
	@Override
	public void cacheResult(List<ReviewTimeline> reviewTimelines) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (reviewTimelines.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (ReviewTimeline reviewTimeline : reviewTimelines) {
			if (entityCache.getResult(
					ReviewTimelineImpl.class, reviewTimeline.getPrimaryKey()) ==
						null) {

				cacheResult(reviewTimeline);
			}
		}
	}

	/**
	 * Clears the cache for all review timelines.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ReviewTimelineImpl.class);

		finderCache.clearCache(ReviewTimelineImpl.class);
	}

	/**
	 * Clears the cache for the review timeline.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ReviewTimeline reviewTimeline) {
		entityCache.removeResult(ReviewTimelineImpl.class, reviewTimeline);
	}

	@Override
	public void clearCache(List<ReviewTimeline> reviewTimelines) {
		for (ReviewTimeline reviewTimeline : reviewTimelines) {
			entityCache.removeResult(ReviewTimelineImpl.class, reviewTimeline);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(ReviewTimelineImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(ReviewTimelineImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new review timeline with the primary key. Does not add the review timeline to the database.
	 *
	 * @param timelineId the primary key for the new review timeline
	 * @return the new review timeline
	 */
	@Override
	public ReviewTimeline create(long timelineId) {
		ReviewTimeline reviewTimeline = new ReviewTimelineImpl();

		reviewTimeline.setNew(true);
		reviewTimeline.setPrimaryKey(timelineId);

		return reviewTimeline;
	}

	/**
	 * Removes the review timeline with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param timelineId the primary key of the review timeline
	 * @return the review timeline that was removed
	 * @throws NoSuchReviewTimelineException if a review timeline with the primary key could not be found
	 */
	@Override
	public ReviewTimeline remove(long timelineId)
		throws NoSuchReviewTimelineException {

		return remove((Serializable)timelineId);
	}

	/**
	 * Removes the review timeline with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the review timeline
	 * @return the review timeline that was removed
	 * @throws NoSuchReviewTimelineException if a review timeline with the primary key could not be found
	 */
	@Override
	public ReviewTimeline remove(Serializable primaryKey)
		throws NoSuchReviewTimelineException {

		Session session = null;

		try {
			session = openSession();

			ReviewTimeline reviewTimeline = (ReviewTimeline)session.get(
				ReviewTimelineImpl.class, primaryKey);

			if (reviewTimeline == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchReviewTimelineException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(reviewTimeline);
		}
		catch (NoSuchReviewTimelineException noSuchEntityException) {
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
	protected ReviewTimeline removeImpl(ReviewTimeline reviewTimeline) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(reviewTimeline)) {
				reviewTimeline = (ReviewTimeline)session.get(
					ReviewTimelineImpl.class,
					reviewTimeline.getPrimaryKeyObj());
			}

			if (reviewTimeline != null) {
				session.delete(reviewTimeline);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (reviewTimeline != null) {
			clearCache(reviewTimeline);
		}

		return reviewTimeline;
	}

	@Override
	public ReviewTimeline updateImpl(ReviewTimeline reviewTimeline) {
		boolean isNew = reviewTimeline.isNew();

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(reviewTimeline);
			}
			else {
				reviewTimeline = (ReviewTimeline)session.merge(reviewTimeline);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			ReviewTimelineImpl.class, reviewTimeline, false, true);

		if (isNew) {
			reviewTimeline.setNew(false);
		}

		reviewTimeline.resetOriginalValues();

		return reviewTimeline;
	}

	/**
	 * Returns the review timeline with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the review timeline
	 * @return the review timeline
	 * @throws NoSuchReviewTimelineException if a review timeline with the primary key could not be found
	 */
	@Override
	public ReviewTimeline findByPrimaryKey(Serializable primaryKey)
		throws NoSuchReviewTimelineException {

		ReviewTimeline reviewTimeline = fetchByPrimaryKey(primaryKey);

		if (reviewTimeline == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchReviewTimelineException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return reviewTimeline;
	}

	/**
	 * Returns the review timeline with the primary key or throws a <code>NoSuchReviewTimelineException</code> if it could not be found.
	 *
	 * @param timelineId the primary key of the review timeline
	 * @return the review timeline
	 * @throws NoSuchReviewTimelineException if a review timeline with the primary key could not be found
	 */
	@Override
	public ReviewTimeline findByPrimaryKey(long timelineId)
		throws NoSuchReviewTimelineException {

		return findByPrimaryKey((Serializable)timelineId);
	}

	/**
	 * Returns the review timeline with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param timelineId the primary key of the review timeline
	 * @return the review timeline, or <code>null</code> if a review timeline with the primary key could not be found
	 */
	@Override
	public ReviewTimeline fetchByPrimaryKey(long timelineId) {
		return fetchByPrimaryKey((Serializable)timelineId);
	}

	/**
	 * Returns all the review timelines.
	 *
	 * @return the review timelines
	 */
	@Override
	public List<ReviewTimeline> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the review timelines.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ReviewTimelineModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of review timelines
	 * @param end the upper bound of the range of review timelines (not inclusive)
	 * @return the range of review timelines
	 */
	@Override
	public List<ReviewTimeline> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the review timelines.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ReviewTimelineModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of review timelines
	 * @param end the upper bound of the range of review timelines (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of review timelines
	 */
	@Override
	public List<ReviewTimeline> findAll(
		int start, int end,
		OrderByComparator<ReviewTimeline> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the review timelines.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ReviewTimelineModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of review timelines
	 * @param end the upper bound of the range of review timelines (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of review timelines
	 */
	@Override
	public List<ReviewTimeline> findAll(
		int start, int end, OrderByComparator<ReviewTimeline> orderByComparator,
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

		List<ReviewTimeline> list = null;

		if (useFinderCache) {
			list = (List<ReviewTimeline>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_REVIEWTIMELINE);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_REVIEWTIMELINE;

				sql = sql.concat(ReviewTimelineModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<ReviewTimeline>)QueryUtil.list(
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
	 * Removes all the review timelines from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (ReviewTimeline reviewTimeline : findAll()) {
			remove(reviewTimeline);
		}
	}

	/**
	 * Returns the number of review timelines.
	 *
	 * @return the number of review timelines
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_REVIEWTIMELINE);

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
		return "timelineId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_REVIEWTIMELINE;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return ReviewTimelineModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the review timeline persistence.
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

		_setReviewTimelineUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setReviewTimelineUtilPersistence(null);

		entityCache.removeCache(ReviewTimelineImpl.class.getName());
	}

	private void _setReviewTimelineUtilPersistence(
		ReviewTimelinePersistence reviewTimelinePersistence) {

		try {
			Field field = ReviewTimelineUtil.class.getDeclaredField(
				"_persistence");

			field.setAccessible(true);

			field.set(null, reviewTimelinePersistence);
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

	private static final String _SQL_SELECT_REVIEWTIMELINE =
		"SELECT reviewTimeline FROM ReviewTimeline reviewTimeline";

	private static final String _SQL_COUNT_REVIEWTIMELINE =
		"SELECT COUNT(reviewTimeline) FROM ReviewTimeline reviewTimeline";

	private static final String _ORDER_BY_ENTITY_ALIAS = "reviewTimeline.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No ReviewTimeline exists with the primary key ";

	private static final Log _log = LogFactoryUtil.getLog(
		ReviewTimelinePersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}