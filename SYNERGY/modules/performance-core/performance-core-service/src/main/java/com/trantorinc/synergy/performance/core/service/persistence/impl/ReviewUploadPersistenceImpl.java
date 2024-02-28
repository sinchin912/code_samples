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

import com.trantorinc.synergy.performance.core.exception.NoSuchReviewUploadException;
import com.trantorinc.synergy.performance.core.model.ReviewUpload;
import com.trantorinc.synergy.performance.core.model.ReviewUploadTable;
import com.trantorinc.synergy.performance.core.model.impl.ReviewUploadImpl;
import com.trantorinc.synergy.performance.core.model.impl.ReviewUploadModelImpl;
import com.trantorinc.synergy.performance.core.service.persistence.ReviewUploadPersistence;
import com.trantorinc.synergy.performance.core.service.persistence.ReviewUploadUtil;
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
 * The persistence implementation for the review upload service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = {ReviewUploadPersistence.class, BasePersistence.class})
public class ReviewUploadPersistenceImpl
	extends BasePersistenceImpl<ReviewUpload>
	implements ReviewUploadPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>ReviewUploadUtil</code> to access the review upload persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		ReviewUploadImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;

	public ReviewUploadPersistenceImpl() {
		setModelClass(ReviewUpload.class);

		setModelImplClass(ReviewUploadImpl.class);
		setModelPKClass(long.class);

		setTable(ReviewUploadTable.INSTANCE);
	}

	/**
	 * Caches the review upload in the entity cache if it is enabled.
	 *
	 * @param reviewUpload the review upload
	 */
	@Override
	public void cacheResult(ReviewUpload reviewUpload) {
		entityCache.putResult(
			ReviewUploadImpl.class, reviewUpload.getPrimaryKey(), reviewUpload);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the review uploads in the entity cache if it is enabled.
	 *
	 * @param reviewUploads the review uploads
	 */
	@Override
	public void cacheResult(List<ReviewUpload> reviewUploads) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (reviewUploads.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (ReviewUpload reviewUpload : reviewUploads) {
			if (entityCache.getResult(
					ReviewUploadImpl.class, reviewUpload.getPrimaryKey()) ==
						null) {

				cacheResult(reviewUpload);
			}
		}
	}

	/**
	 * Clears the cache for all review uploads.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ReviewUploadImpl.class);

		finderCache.clearCache(ReviewUploadImpl.class);
	}

	/**
	 * Clears the cache for the review upload.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ReviewUpload reviewUpload) {
		entityCache.removeResult(ReviewUploadImpl.class, reviewUpload);
	}

	@Override
	public void clearCache(List<ReviewUpload> reviewUploads) {
		for (ReviewUpload reviewUpload : reviewUploads) {
			entityCache.removeResult(ReviewUploadImpl.class, reviewUpload);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(ReviewUploadImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(ReviewUploadImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new review upload with the primary key. Does not add the review upload to the database.
	 *
	 * @param uploadId the primary key for the new review upload
	 * @return the new review upload
	 */
	@Override
	public ReviewUpload create(long uploadId) {
		ReviewUpload reviewUpload = new ReviewUploadImpl();

		reviewUpload.setNew(true);
		reviewUpload.setPrimaryKey(uploadId);

		return reviewUpload;
	}

	/**
	 * Removes the review upload with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param uploadId the primary key of the review upload
	 * @return the review upload that was removed
	 * @throws NoSuchReviewUploadException if a review upload with the primary key could not be found
	 */
	@Override
	public ReviewUpload remove(long uploadId)
		throws NoSuchReviewUploadException {

		return remove((Serializable)uploadId);
	}

	/**
	 * Removes the review upload with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the review upload
	 * @return the review upload that was removed
	 * @throws NoSuchReviewUploadException if a review upload with the primary key could not be found
	 */
	@Override
	public ReviewUpload remove(Serializable primaryKey)
		throws NoSuchReviewUploadException {

		Session session = null;

		try {
			session = openSession();

			ReviewUpload reviewUpload = (ReviewUpload)session.get(
				ReviewUploadImpl.class, primaryKey);

			if (reviewUpload == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchReviewUploadException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(reviewUpload);
		}
		catch (NoSuchReviewUploadException noSuchEntityException) {
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
	protected ReviewUpload removeImpl(ReviewUpload reviewUpload) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(reviewUpload)) {
				reviewUpload = (ReviewUpload)session.get(
					ReviewUploadImpl.class, reviewUpload.getPrimaryKeyObj());
			}

			if (reviewUpload != null) {
				session.delete(reviewUpload);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (reviewUpload != null) {
			clearCache(reviewUpload);
		}

		return reviewUpload;
	}

	@Override
	public ReviewUpload updateImpl(ReviewUpload reviewUpload) {
		boolean isNew = reviewUpload.isNew();

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(reviewUpload);
			}
			else {
				reviewUpload = (ReviewUpload)session.merge(reviewUpload);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			ReviewUploadImpl.class, reviewUpload, false, true);

		if (isNew) {
			reviewUpload.setNew(false);
		}

		reviewUpload.resetOriginalValues();

		return reviewUpload;
	}

	/**
	 * Returns the review upload with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the review upload
	 * @return the review upload
	 * @throws NoSuchReviewUploadException if a review upload with the primary key could not be found
	 */
	@Override
	public ReviewUpload findByPrimaryKey(Serializable primaryKey)
		throws NoSuchReviewUploadException {

		ReviewUpload reviewUpload = fetchByPrimaryKey(primaryKey);

		if (reviewUpload == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchReviewUploadException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return reviewUpload;
	}

	/**
	 * Returns the review upload with the primary key or throws a <code>NoSuchReviewUploadException</code> if it could not be found.
	 *
	 * @param uploadId the primary key of the review upload
	 * @return the review upload
	 * @throws NoSuchReviewUploadException if a review upload with the primary key could not be found
	 */
	@Override
	public ReviewUpload findByPrimaryKey(long uploadId)
		throws NoSuchReviewUploadException {

		return findByPrimaryKey((Serializable)uploadId);
	}

	/**
	 * Returns the review upload with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param uploadId the primary key of the review upload
	 * @return the review upload, or <code>null</code> if a review upload with the primary key could not be found
	 */
	@Override
	public ReviewUpload fetchByPrimaryKey(long uploadId) {
		return fetchByPrimaryKey((Serializable)uploadId);
	}

	/**
	 * Returns all the review uploads.
	 *
	 * @return the review uploads
	 */
	@Override
	public List<ReviewUpload> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the review uploads.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ReviewUploadModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of review uploads
	 * @param end the upper bound of the range of review uploads (not inclusive)
	 * @return the range of review uploads
	 */
	@Override
	public List<ReviewUpload> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the review uploads.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ReviewUploadModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of review uploads
	 * @param end the upper bound of the range of review uploads (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of review uploads
	 */
	@Override
	public List<ReviewUpload> findAll(
		int start, int end, OrderByComparator<ReviewUpload> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the review uploads.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ReviewUploadModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of review uploads
	 * @param end the upper bound of the range of review uploads (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of review uploads
	 */
	@Override
	public List<ReviewUpload> findAll(
		int start, int end, OrderByComparator<ReviewUpload> orderByComparator,
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

		List<ReviewUpload> list = null;

		if (useFinderCache) {
			list = (List<ReviewUpload>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_REVIEWUPLOAD);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_REVIEWUPLOAD;

				sql = sql.concat(ReviewUploadModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<ReviewUpload>)QueryUtil.list(
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
	 * Removes all the review uploads from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (ReviewUpload reviewUpload : findAll()) {
			remove(reviewUpload);
		}
	}

	/**
	 * Returns the number of review uploads.
	 *
	 * @return the number of review uploads
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_REVIEWUPLOAD);

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
		return "uploadId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_REVIEWUPLOAD;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return ReviewUploadModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the review upload persistence.
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

		_setReviewUploadUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setReviewUploadUtilPersistence(null);

		entityCache.removeCache(ReviewUploadImpl.class.getName());
	}

	private void _setReviewUploadUtilPersistence(
		ReviewUploadPersistence reviewUploadPersistence) {

		try {
			Field field = ReviewUploadUtil.class.getDeclaredField(
				"_persistence");

			field.setAccessible(true);

			field.set(null, reviewUploadPersistence);
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

	private static final String _SQL_SELECT_REVIEWUPLOAD =
		"SELECT reviewUpload FROM ReviewUpload reviewUpload";

	private static final String _SQL_COUNT_REVIEWUPLOAD =
		"SELECT COUNT(reviewUpload) FROM ReviewUpload reviewUpload";

	private static final String _ORDER_BY_ENTITY_ALIAS = "reviewUpload.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No ReviewUpload exists with the primary key ";

	private static final Log _log = LogFactoryUtil.getLog(
		ReviewUploadPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}