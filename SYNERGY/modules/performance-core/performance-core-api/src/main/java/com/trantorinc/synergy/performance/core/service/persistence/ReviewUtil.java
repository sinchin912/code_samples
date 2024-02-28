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

import com.trantorinc.synergy.performance.core.model.Review;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the review service. This utility wraps <code>com.trantorinc.synergy.performance.core.service.persistence.impl.ReviewPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ReviewPersistence
 * @generated
 */
public class ReviewUtil {

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
	public static void clearCache(Review review) {
		getPersistence().clearCache(review);
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
	public static Map<Serializable, Review> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<Review> findWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Review> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Review> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<Review> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static Review update(Review review) {
		return getPersistence().update(review);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static Review update(Review review, ServiceContext serviceContext) {
		return getPersistence().update(review, serviceContext);
	}

	/**
	 * Caches the review in the entity cache if it is enabled.
	 *
	 * @param review the review
	 */
	public static void cacheResult(Review review) {
		getPersistence().cacheResult(review);
	}

	/**
	 * Caches the reviews in the entity cache if it is enabled.
	 *
	 * @param reviews the reviews
	 */
	public static void cacheResult(List<Review> reviews) {
		getPersistence().cacheResult(reviews);
	}

	/**
	 * Creates a new review with the primary key. Does not add the review to the database.
	 *
	 * @param reviewId the primary key for the new review
	 * @return the new review
	 */
	public static Review create(long reviewId) {
		return getPersistence().create(reviewId);
	}

	/**
	 * Removes the review with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param reviewId the primary key of the review
	 * @return the review that was removed
	 * @throws NoSuchReviewException if a review with the primary key could not be found
	 */
	public static Review remove(long reviewId)
		throws com.trantorinc.synergy.performance.core.exception.
			NoSuchReviewException {

		return getPersistence().remove(reviewId);
	}

	public static Review updateImpl(Review review) {
		return getPersistence().updateImpl(review);
	}

	/**
	 * Returns the review with the primary key or throws a <code>NoSuchReviewException</code> if it could not be found.
	 *
	 * @param reviewId the primary key of the review
	 * @return the review
	 * @throws NoSuchReviewException if a review with the primary key could not be found
	 */
	public static Review findByPrimaryKey(long reviewId)
		throws com.trantorinc.synergy.performance.core.exception.
			NoSuchReviewException {

		return getPersistence().findByPrimaryKey(reviewId);
	}

	/**
	 * Returns the review with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param reviewId the primary key of the review
	 * @return the review, or <code>null</code> if a review with the primary key could not be found
	 */
	public static Review fetchByPrimaryKey(long reviewId) {
		return getPersistence().fetchByPrimaryKey(reviewId);
	}

	/**
	 * Returns all the reviews.
	 *
	 * @return the reviews
	 */
	public static List<Review> findAll() {
		return getPersistence().findAll();
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
	public static List<Review> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
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
	public static List<Review> findAll(
		int start, int end, OrderByComparator<Review> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
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
	public static List<Review> findAll(
		int start, int end, OrderByComparator<Review> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the reviews from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of reviews.
	 *
	 * @return the number of reviews
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static ReviewPersistence getPersistence() {
		return _persistence;
	}

	private static volatile ReviewPersistence _persistence;

}