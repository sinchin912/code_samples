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
 * Provides a wrapper for {@link ReviewRollbackLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see ReviewRollbackLocalService
 * @generated
 */
public class ReviewRollbackLocalServiceWrapper
	implements ReviewRollbackLocalService,
			   ServiceWrapper<ReviewRollbackLocalService> {

	public ReviewRollbackLocalServiceWrapper() {
		this(null);
	}

	public ReviewRollbackLocalServiceWrapper(
		ReviewRollbackLocalService reviewRollbackLocalService) {

		_reviewRollbackLocalService = reviewRollbackLocalService;
	}

	/**
	 * Adds the review rollback to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ReviewRollbackLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param reviewRollback the review rollback
	 * @return the review rollback that was added
	 */
	@Override
	public com.trantorinc.synergy.performance.core.model.ReviewRollback
		addReviewRollback(
			com.trantorinc.synergy.performance.core.model.ReviewRollback
				reviewRollback) {

		return _reviewRollbackLocalService.addReviewRollback(reviewRollback);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _reviewRollbackLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Creates a new review rollback with the primary key. Does not add the review rollback to the database.
	 *
	 * @param rollbackId the primary key for the new review rollback
	 * @return the new review rollback
	 */
	@Override
	public com.trantorinc.synergy.performance.core.model.ReviewRollback
		createReviewRollback(long rollbackId) {

		return _reviewRollbackLocalService.createReviewRollback(rollbackId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _reviewRollbackLocalService.deletePersistedModel(persistedModel);
	}

	/**
	 * Deletes the review rollback with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ReviewRollbackLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param rollbackId the primary key of the review rollback
	 * @return the review rollback that was removed
	 * @throws PortalException if a review rollback with the primary key could not be found
	 */
	@Override
	public com.trantorinc.synergy.performance.core.model.ReviewRollback
			deleteReviewRollback(long rollbackId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _reviewRollbackLocalService.deleteReviewRollback(rollbackId);
	}

	/**
	 * Deletes the review rollback from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ReviewRollbackLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param reviewRollback the review rollback
	 * @return the review rollback that was removed
	 */
	@Override
	public com.trantorinc.synergy.performance.core.model.ReviewRollback
		deleteReviewRollback(
			com.trantorinc.synergy.performance.core.model.ReviewRollback
				reviewRollback) {

		return _reviewRollbackLocalService.deleteReviewRollback(reviewRollback);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _reviewRollbackLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _reviewRollbackLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _reviewRollbackLocalService.dynamicQuery();
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

		return _reviewRollbackLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.trantorinc.synergy.performance.core.model.impl.ReviewRollbackModelImpl</code>.
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

		return _reviewRollbackLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.trantorinc.synergy.performance.core.model.impl.ReviewRollbackModelImpl</code>.
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

		return _reviewRollbackLocalService.dynamicQuery(
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

		return _reviewRollbackLocalService.dynamicQueryCount(dynamicQuery);
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

		return _reviewRollbackLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.trantorinc.synergy.performance.core.model.ReviewRollback
		fetchReviewRollback(long rollbackId) {

		return _reviewRollbackLocalService.fetchReviewRollback(rollbackId);
	}

	@Override
	public java.util.List
		<com.trantorinc.synergy.performance.core.model.ReviewRollback>
			findReviewRollbackByReviewId(long reviewId) {

		return _reviewRollbackLocalService.findReviewRollbackByReviewId(
			reviewId);
	}

	@Override
	public java.util.List
		<com.trantorinc.synergy.performance.core.model.ReviewRollback>
			findReviewRollbackByStatus(int status) {

		return _reviewRollbackLocalService.findReviewRollbackByStatus(status);
	}

	@Override
	public java.util.List
		<com.trantorinc.synergy.performance.core.model.ReviewRollback>
			findReviewRollbackByStatus(String managerEmail) {

		return _reviewRollbackLocalService.findReviewRollbackByStatus(
			managerEmail);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _reviewRollbackLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _reviewRollbackLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _reviewRollbackLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _reviewRollbackLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns the review rollback with the primary key.
	 *
	 * @param rollbackId the primary key of the review rollback
	 * @return the review rollback
	 * @throws PortalException if a review rollback with the primary key could not be found
	 */
	@Override
	public com.trantorinc.synergy.performance.core.model.ReviewRollback
			getReviewRollback(long rollbackId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _reviewRollbackLocalService.getReviewRollback(rollbackId);
	}

	/**
	 * Returns a range of all the review rollbacks.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.trantorinc.synergy.performance.core.model.impl.ReviewRollbackModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of review rollbacks
	 * @param end the upper bound of the range of review rollbacks (not inclusive)
	 * @return the range of review rollbacks
	 */
	@Override
	public java.util.List
		<com.trantorinc.synergy.performance.core.model.ReviewRollback>
			getReviewRollbacks(int start, int end) {

		return _reviewRollbackLocalService.getReviewRollbacks(start, end);
	}

	/**
	 * Returns the number of review rollbacks.
	 *
	 * @return the number of review rollbacks
	 */
	@Override
	public int getReviewRollbacksCount() {
		return _reviewRollbackLocalService.getReviewRollbacksCount();
	}

	/**
	 * Updates the review rollback in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ReviewRollbackLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param reviewRollback the review rollback
	 * @return the review rollback that was updated
	 */
	@Override
	public com.trantorinc.synergy.performance.core.model.ReviewRollback
		updateReviewRollback(
			com.trantorinc.synergy.performance.core.model.ReviewRollback
				reviewRollback) {

		return _reviewRollbackLocalService.updateReviewRollback(reviewRollback);
	}

	@Override
	public ReviewRollbackLocalService getWrappedService() {
		return _reviewRollbackLocalService;
	}

	@Override
	public void setWrappedService(
		ReviewRollbackLocalService reviewRollbackLocalService) {

		_reviewRollbackLocalService = reviewRollbackLocalService;
	}

	private ReviewRollbackLocalService _reviewRollbackLocalService;

}