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

import com.trantorinc.synergy.performance.core.model.ReviewUpload;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the review upload service. This utility wraps <code>com.trantorinc.synergy.performance.core.service.persistence.impl.ReviewUploadPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ReviewUploadPersistence
 * @generated
 */
public class ReviewUploadUtil {

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
	public static void clearCache(ReviewUpload reviewUpload) {
		getPersistence().clearCache(reviewUpload);
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
	public static Map<Serializable, ReviewUpload> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<ReviewUpload> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ReviewUpload> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ReviewUpload> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<ReviewUpload> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static ReviewUpload update(ReviewUpload reviewUpload) {
		return getPersistence().update(reviewUpload);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static ReviewUpload update(
		ReviewUpload reviewUpload, ServiceContext serviceContext) {

		return getPersistence().update(reviewUpload, serviceContext);
	}

	/**
	 * Caches the review upload in the entity cache if it is enabled.
	 *
	 * @param reviewUpload the review upload
	 */
	public static void cacheResult(ReviewUpload reviewUpload) {
		getPersistence().cacheResult(reviewUpload);
	}

	/**
	 * Caches the review uploads in the entity cache if it is enabled.
	 *
	 * @param reviewUploads the review uploads
	 */
	public static void cacheResult(List<ReviewUpload> reviewUploads) {
		getPersistence().cacheResult(reviewUploads);
	}

	/**
	 * Creates a new review upload with the primary key. Does not add the review upload to the database.
	 *
	 * @param uploadId the primary key for the new review upload
	 * @return the new review upload
	 */
	public static ReviewUpload create(long uploadId) {
		return getPersistence().create(uploadId);
	}

	/**
	 * Removes the review upload with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param uploadId the primary key of the review upload
	 * @return the review upload that was removed
	 * @throws NoSuchReviewUploadException if a review upload with the primary key could not be found
	 */
	public static ReviewUpload remove(long uploadId)
		throws com.trantorinc.synergy.performance.core.exception.
			NoSuchReviewUploadException {

		return getPersistence().remove(uploadId);
	}

	public static ReviewUpload updateImpl(ReviewUpload reviewUpload) {
		return getPersistence().updateImpl(reviewUpload);
	}

	/**
	 * Returns the review upload with the primary key or throws a <code>NoSuchReviewUploadException</code> if it could not be found.
	 *
	 * @param uploadId the primary key of the review upload
	 * @return the review upload
	 * @throws NoSuchReviewUploadException if a review upload with the primary key could not be found
	 */
	public static ReviewUpload findByPrimaryKey(long uploadId)
		throws com.trantorinc.synergy.performance.core.exception.
			NoSuchReviewUploadException {

		return getPersistence().findByPrimaryKey(uploadId);
	}

	/**
	 * Returns the review upload with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param uploadId the primary key of the review upload
	 * @return the review upload, or <code>null</code> if a review upload with the primary key could not be found
	 */
	public static ReviewUpload fetchByPrimaryKey(long uploadId) {
		return getPersistence().fetchByPrimaryKey(uploadId);
	}

	/**
	 * Returns all the review uploads.
	 *
	 * @return the review uploads
	 */
	public static List<ReviewUpload> findAll() {
		return getPersistence().findAll();
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
	public static List<ReviewUpload> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
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
	public static List<ReviewUpload> findAll(
		int start, int end, OrderByComparator<ReviewUpload> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
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
	public static List<ReviewUpload> findAll(
		int start, int end, OrderByComparator<ReviewUpload> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the review uploads from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of review uploads.
	 *
	 * @return the number of review uploads
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static ReviewUploadPersistence getPersistence() {
		return _persistence;
	}

	private static volatile ReviewUploadPersistence _persistence;

}