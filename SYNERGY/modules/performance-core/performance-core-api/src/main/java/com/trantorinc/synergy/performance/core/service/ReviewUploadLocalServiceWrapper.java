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

package com.trantorinc.synergy.performance.core.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ReviewUploadLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see ReviewUploadLocalService
 * @generated
 */
public class ReviewUploadLocalServiceWrapper
	implements ReviewUploadLocalService,
			   ServiceWrapper<ReviewUploadLocalService> {

	public ReviewUploadLocalServiceWrapper() {
		this(null);
	}

	public ReviewUploadLocalServiceWrapper(
		ReviewUploadLocalService reviewUploadLocalService) {

		_reviewUploadLocalService = reviewUploadLocalService;
	}

	/**
	 * Adds the review upload to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ReviewUploadLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param reviewUpload the review upload
	 * @return the review upload that was added
	 */
	@Override
	public com.trantorinc.synergy.performance.core.model.ReviewUpload
		addReviewUpload(
			com.trantorinc.synergy.performance.core.model.ReviewUpload
				reviewUpload) {

		return _reviewUploadLocalService.addReviewUpload(reviewUpload);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _reviewUploadLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Creates a new review upload with the primary key. Does not add the review upload to the database.
	 *
	 * @param uploadId the primary key for the new review upload
	 * @return the new review upload
	 */
	@Override
	public com.trantorinc.synergy.performance.core.model.ReviewUpload
		createReviewUpload(long uploadId) {

		return _reviewUploadLocalService.createReviewUpload(uploadId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _reviewUploadLocalService.deletePersistedModel(persistedModel);
	}

	/**
	 * Deletes the review upload with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ReviewUploadLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param uploadId the primary key of the review upload
	 * @return the review upload that was removed
	 * @throws PortalException if a review upload with the primary key could not be found
	 */
	@Override
	public com.trantorinc.synergy.performance.core.model.ReviewUpload
			deleteReviewUpload(long uploadId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _reviewUploadLocalService.deleteReviewUpload(uploadId);
	}

	/**
	 * Deletes the review upload from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ReviewUploadLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param reviewUpload the review upload
	 * @return the review upload that was removed
	 */
	@Override
	public com.trantorinc.synergy.performance.core.model.ReviewUpload
		deleteReviewUpload(
			com.trantorinc.synergy.performance.core.model.ReviewUpload
				reviewUpload) {

		return _reviewUploadLocalService.deleteReviewUpload(reviewUpload);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _reviewUploadLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _reviewUploadLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _reviewUploadLocalService.dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _reviewUploadLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.trantorinc.synergy.performance.core.model.impl.ReviewUploadModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return _reviewUploadLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.trantorinc.synergy.performance.core.model.impl.ReviewUploadModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return _reviewUploadLocalService.dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _reviewUploadLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return _reviewUploadLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.trantorinc.synergy.performance.core.model.ReviewUpload
		fetchReviewUpload(long uploadId) {

		return _reviewUploadLocalService.fetchReviewUpload(uploadId);
	}

	@Override
	public java.util.List
		<com.trantorinc.synergy.performance.core.model.ReviewUpload>
			findReviewUploadByReviewId(long reviewId) {

		return _reviewUploadLocalService.findReviewUploadByReviewId(reviewId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _reviewUploadLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _reviewUploadLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _reviewUploadLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _reviewUploadLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns the review upload with the primary key.
	 *
	 * @param uploadId the primary key of the review upload
	 * @return the review upload
	 * @throws PortalException if a review upload with the primary key could not be found
	 */
	@Override
	public com.trantorinc.synergy.performance.core.model.ReviewUpload
			getReviewUpload(long uploadId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _reviewUploadLocalService.getReviewUpload(uploadId);
	}

	/**
	 * Returns a range of all the review uploads.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.trantorinc.synergy.performance.core.model.impl.ReviewUploadModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of review uploads
	 * @param end the upper bound of the range of review uploads (not inclusive)
	 * @return the range of review uploads
	 */
	@Override
	public java.util.List
		<com.trantorinc.synergy.performance.core.model.ReviewUpload>
			getReviewUploads(int start, int end) {

		return _reviewUploadLocalService.getReviewUploads(start, end);
	}

	/**
	 * Returns the number of review uploads.
	 *
	 * @return the number of review uploads
	 */
	@Override
	public int getReviewUploadsCount() {
		return _reviewUploadLocalService.getReviewUploadsCount();
	}

	/**
	 * Updates the review upload in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ReviewUploadLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param reviewUpload the review upload
	 * @return the review upload that was updated
	 */
	@Override
	public com.trantorinc.synergy.performance.core.model.ReviewUpload
		updateReviewUpload(
			com.trantorinc.synergy.performance.core.model.ReviewUpload
				reviewUpload) {

		return _reviewUploadLocalService.updateReviewUpload(reviewUpload);
	}

	@Override
	public ReviewUploadLocalService getWrappedService() {
		return _reviewUploadLocalService;
	}

	@Override
	public void setWrappedService(
		ReviewUploadLocalService reviewUploadLocalService) {

		_reviewUploadLocalService = reviewUploadLocalService;
	}

	private ReviewUploadLocalService _reviewUploadLocalService;

}