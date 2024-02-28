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

package com.trantorinc.synergy.performance.core.service.persistence;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.trantorinc.synergy.performance.core.model.ReviewRollback;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the review rollback service. This utility wraps <code>com.trantorinc.synergy.performance.core.service.persistence.impl.ReviewRollbackPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ReviewRollbackPersistence
 * @generated
 */
public class ReviewRollbackUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(ReviewRollback reviewRollback) {
		getPersistence().clearCache(reviewRollback);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#fetchByPrimaryKeys(Set)
	 */
	public static Map<Serializable, ReviewRollback> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<ReviewRollback> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ReviewRollback> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ReviewRollback> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<ReviewRollback> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static ReviewRollback update(ReviewRollback reviewRollback) {
		return getPersistence().update(reviewRollback);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static ReviewRollback update(
		ReviewRollback reviewRollback, ServiceContext serviceContext) {

		return getPersistence().update(reviewRollback, serviceContext);
	}

	/**
	 * Caches the review rollback in the entity cache if it is enabled.
	 *
	 * @param reviewRollback the review rollback
	 */
	public static void cacheResult(ReviewRollback reviewRollback) {
		getPersistence().cacheResult(reviewRollback);
	}

	/**
	 * Caches the review rollbacks in the entity cache if it is enabled.
	 *
	 * @param reviewRollbacks the review rollbacks
	 */
	public static void cacheResult(List<ReviewRollback> reviewRollbacks) {
		getPersistence().cacheResult(reviewRollbacks);
	}

	/**
	 * Creates a new review rollback with the primary key. Does not add the review rollback to the database.
	 *
	 * @param rollbackId the primary key for the new review rollback
	 * @return the new review rollback
	 */
	public static ReviewRollback create(long rollbackId) {
		return getPersistence().create(rollbackId);
	}

	/**
	 * Removes the review rollback with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param rollbackId the primary key of the review rollback
	 * @return the review rollback that was removed
	 * @throws NoSuchReviewRollbackException if a review rollback with the primary key could not be found
	 */
	public static ReviewRollback remove(long rollbackId)
		throws com.trantorinc.synergy.performance.core.exception.
			NoSuchReviewRollbackException {

		return getPersistence().remove(rollbackId);
	}

	public static ReviewRollback updateImpl(ReviewRollback reviewRollback) {
		return getPersistence().updateImpl(reviewRollback);
	}

	/**
	 * Returns the review rollback with the primary key or throws a <code>NoSuchReviewRollbackException</code> if it could not be found.
	 *
	 * @param rollbackId the primary key of the review rollback
	 * @return the review rollback
	 * @throws NoSuchReviewRollbackException if a review rollback with the primary key could not be found
	 */
	public static ReviewRollback findByPrimaryKey(long rollbackId)
		throws com.trantorinc.synergy.performance.core.exception.
			NoSuchReviewRollbackException {

		return getPersistence().findByPrimaryKey(rollbackId);
	}

	/**
	 * Returns the review rollback with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param rollbackId the primary key of the review rollback
	 * @return the review rollback, or <code>null</code> if a review rollback with the primary key could not be found
	 */
	public static ReviewRollback fetchByPrimaryKey(long rollbackId) {
		return getPersistence().fetchByPrimaryKey(rollbackId);
	}

	/**
	 * Returns all the review rollbacks.
	 *
	 * @return the review rollbacks
	 */
	public static List<ReviewRollback> findAll() {
		return getPersistence().findAll();
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
	public static List<ReviewRollback> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
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
	public static List<ReviewRollback> findAll(
		int start, int end,
		OrderByComparator<ReviewRollback> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
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
	public static List<ReviewRollback> findAll(
		int start, int end, OrderByComparator<ReviewRollback> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the review rollbacks from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of review rollbacks.
	 *
	 * @return the number of review rollbacks
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static ReviewRollbackPersistence getPersistence() {
		return _persistence;
	}

	private static volatile ReviewRollbackPersistence _persistence;

}