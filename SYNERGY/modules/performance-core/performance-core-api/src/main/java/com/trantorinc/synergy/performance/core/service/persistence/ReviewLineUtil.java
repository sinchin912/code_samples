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

import com.trantorinc.synergy.performance.core.model.ReviewLine;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the review line service. This utility wraps <code>com.trantorinc.synergy.performance.core.service.persistence.impl.ReviewLinePersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ReviewLinePersistence
 * @generated
 */
public class ReviewLineUtil {

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
	public static void clearCache(ReviewLine reviewLine) {
		getPersistence().clearCache(reviewLine);
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
	public static Map<Serializable, ReviewLine> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<ReviewLine> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ReviewLine> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ReviewLine> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<ReviewLine> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static ReviewLine update(ReviewLine reviewLine) {
		return getPersistence().update(reviewLine);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static ReviewLine update(
		ReviewLine reviewLine, ServiceContext serviceContext) {

		return getPersistence().update(reviewLine, serviceContext);
	}

	/**
	 * Caches the review line in the entity cache if it is enabled.
	 *
	 * @param reviewLine the review line
	 */
	public static void cacheResult(ReviewLine reviewLine) {
		getPersistence().cacheResult(reviewLine);
	}

	/**
	 * Caches the review lines in the entity cache if it is enabled.
	 *
	 * @param reviewLines the review lines
	 */
	public static void cacheResult(List<ReviewLine> reviewLines) {
		getPersistence().cacheResult(reviewLines);
	}

	/**
	 * Creates a new review line with the primary key. Does not add the review line to the database.
	 *
	 * @param lineId the primary key for the new review line
	 * @return the new review line
	 */
	public static ReviewLine create(long lineId) {
		return getPersistence().create(lineId);
	}

	/**
	 * Removes the review line with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param lineId the primary key of the review line
	 * @return the review line that was removed
	 * @throws NoSuchReviewLineException if a review line with the primary key could not be found
	 */
	public static ReviewLine remove(long lineId)
		throws com.trantorinc.synergy.performance.core.exception.
			NoSuchReviewLineException {

		return getPersistence().remove(lineId);
	}

	public static ReviewLine updateImpl(ReviewLine reviewLine) {
		return getPersistence().updateImpl(reviewLine);
	}

	/**
	 * Returns the review line with the primary key or throws a <code>NoSuchReviewLineException</code> if it could not be found.
	 *
	 * @param lineId the primary key of the review line
	 * @return the review line
	 * @throws NoSuchReviewLineException if a review line with the primary key could not be found
	 */
	public static ReviewLine findByPrimaryKey(long lineId)
		throws com.trantorinc.synergy.performance.core.exception.
			NoSuchReviewLineException {

		return getPersistence().findByPrimaryKey(lineId);
	}

	/**
	 * Returns the review line with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param lineId the primary key of the review line
	 * @return the review line, or <code>null</code> if a review line with the primary key could not be found
	 */
	public static ReviewLine fetchByPrimaryKey(long lineId) {
		return getPersistence().fetchByPrimaryKey(lineId);
	}

	/**
	 * Returns all the review lines.
	 *
	 * @return the review lines
	 */
	public static List<ReviewLine> findAll() {
		return getPersistence().findAll();
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
	public static List<ReviewLine> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
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
	public static List<ReviewLine> findAll(
		int start, int end, OrderByComparator<ReviewLine> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
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
	public static List<ReviewLine> findAll(
		int start, int end, OrderByComparator<ReviewLine> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the review lines from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of review lines.
	 *
	 * @return the number of review lines
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static ReviewLinePersistence getPersistence() {
		return _persistence;
	}

	private static volatile ReviewLinePersistence _persistence;

}