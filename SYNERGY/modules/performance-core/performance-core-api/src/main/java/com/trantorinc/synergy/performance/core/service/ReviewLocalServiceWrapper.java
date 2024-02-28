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
 * Provides a wrapper for {@link ReviewLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see ReviewLocalService
 * @generated
 */
public class ReviewLocalServiceWrapper
	implements ReviewLocalService, ServiceWrapper<ReviewLocalService> {

	public ReviewLocalServiceWrapper() {
		this(null);
	}

	public ReviewLocalServiceWrapper(ReviewLocalService reviewLocalService) {
		_reviewLocalService = reviewLocalService;
	}

	/**
	 * Adds the review to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ReviewLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param review the review
	 * @return the review that was added
	 */
	@Override
	public com.trantorinc.synergy.performance.core.model.Review addReview(
		com.trantorinc.synergy.performance.core.model.Review review) {

		return _reviewLocalService.addReview(review);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _reviewLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Creates a new review with the primary key. Does not add the review to the database.
	 *
	 * @param reviewId the primary key for the new review
	 * @return the new review
	 */
	@Override
	public com.trantorinc.synergy.performance.core.model.Review createReview(
		long reviewId) {

		return _reviewLocalService.createReview(reviewId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _reviewLocalService.deletePersistedModel(persistedModel);
	}

	/**
	 * Deletes the review with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ReviewLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param reviewId the primary key of the review
	 * @return the review that was removed
	 * @throws PortalException if a review with the primary key could not be found
	 */
	@Override
	public com.trantorinc.synergy.performance.core.model.Review deleteReview(
			long reviewId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _reviewLocalService.deleteReview(reviewId);
	}

	/**
	 * Deletes the review from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ReviewLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param review the review
	 * @return the review that was removed
	 */
	@Override
	public com.trantorinc.synergy.performance.core.model.Review deleteReview(
		com.trantorinc.synergy.performance.core.model.Review review) {

		return _reviewLocalService.deleteReview(review);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _reviewLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _reviewLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _reviewLocalService.dynamicQuery();
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

		return _reviewLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.trantorinc.synergy.performance.core.model.impl.ReviewModelImpl</code>.
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

		return _reviewLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.trantorinc.synergy.performance.core.model.impl.ReviewModelImpl</code>.
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

		return _reviewLocalService.dynamicQuery(
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

		return _reviewLocalService.dynamicQueryCount(dynamicQuery);
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

		return _reviewLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public com.trantorinc.synergy.performance.core.model.Review fetchReview(
		long reviewId) {

		return _reviewLocalService.fetchReview(reviewId);
	}

	@Override
	public java.util.List<com.trantorinc.synergy.performance.core.model.Review>
		findReviewByEcode(String ecode) {

		return _reviewLocalService.findReviewByEcode(ecode);
	}

	@Override
	public java.util.List<com.trantorinc.synergy.performance.core.model.Review>
		findReviewByKpiId(long kpiId) {

		return _reviewLocalService.findReviewByKpiId(kpiId);
	}

	@Override
	public java.util.List<com.trantorinc.synergy.performance.core.model.Review>
		findReviewByManager(
			String managerEmail, java.util.List<String> empCodes) {

		return _reviewLocalService.findReviewByManager(managerEmail, empCodes);
	}

	@Override
	public java.util.List<com.trantorinc.synergy.performance.core.model.Review>
		findReviewByReviewStartDate(java.util.Date fyStartDate) {

		return _reviewLocalService.findReviewByReviewStartDate(fyStartDate);
	}

	@Override
	public java.util.List<com.trantorinc.synergy.performance.core.model.Review>
		findReviewByState(int state) {

		return _reviewLocalService.findReviewByState(state);
	}

	@Override
	public java.util.List<java.util.Date> findUniqueReviewYears() {
		return _reviewLocalService.findUniqueReviewYears();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _reviewLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _reviewLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _reviewLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _reviewLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns the review with the primary key.
	 *
	 * @param reviewId the primary key of the review
	 * @return the review
	 * @throws PortalException if a review with the primary key could not be found
	 */
	@Override
	public com.trantorinc.synergy.performance.core.model.Review getReview(
			long reviewId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _reviewLocalService.getReview(reviewId);
	}

	/**
	 * Returns a range of all the reviews.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.trantorinc.synergy.performance.core.model.impl.ReviewModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of reviews
	 * @param end the upper bound of the range of reviews (not inclusive)
	 * @return the range of reviews
	 */
	@Override
	public java.util.List<com.trantorinc.synergy.performance.core.model.Review>
		getReviews(int start, int end) {

		return _reviewLocalService.getReviews(start, end);
	}

	/**
	 * Returns the number of reviews.
	 *
	 * @return the number of reviews
	 */
	@Override
	public int getReviewsCount() {
		return _reviewLocalService.getReviewsCount();
	}

	/**
	 * Updates the review in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ReviewLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param review the review
	 * @return the review that was updated
	 */
	@Override
	public com.trantorinc.synergy.performance.core.model.Review updateReview(
		com.trantorinc.synergy.performance.core.model.Review review) {

		return _reviewLocalService.updateReview(review);
	}

	@Override
	public ReviewLocalService getWrappedService() {
		return _reviewLocalService;
	}

	@Override
	public void setWrappedService(ReviewLocalService reviewLocalService) {
		_reviewLocalService = reviewLocalService;
	}

	private ReviewLocalService _reviewLocalService;

}