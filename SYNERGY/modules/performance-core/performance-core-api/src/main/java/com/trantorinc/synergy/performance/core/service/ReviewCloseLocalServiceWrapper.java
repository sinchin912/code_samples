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
 * Provides a wrapper for {@link ReviewCloseLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see ReviewCloseLocalService
 * @generated
 */
public class ReviewCloseLocalServiceWrapper
	implements ReviewCloseLocalService,
			   ServiceWrapper<ReviewCloseLocalService> {

	public ReviewCloseLocalServiceWrapper() {
		this(null);
	}

	public ReviewCloseLocalServiceWrapper(
		ReviewCloseLocalService reviewCloseLocalService) {

		_reviewCloseLocalService = reviewCloseLocalService;
	}

	/**
	 * Adds the review close to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ReviewCloseLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param reviewClose the review close
	 * @return the review close that was added
	 */
	@Override
	public com.trantorinc.synergy.performance.core.model.ReviewClose
		addReviewClose(
			com.trantorinc.synergy.performance.core.model.ReviewClose
				reviewClose) {

		return _reviewCloseLocalService.addReviewClose(reviewClose);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _reviewCloseLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Creates a new review close with the primary key. Does not add the review close to the database.
	 *
	 * @param closeId the primary key for the new review close
	 * @return the new review close
	 */
	@Override
	public com.trantorinc.synergy.performance.core.model.ReviewClose
		createReviewClose(long closeId) {

		return _reviewCloseLocalService.createReviewClose(closeId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _reviewCloseLocalService.deletePersistedModel(persistedModel);
	}

	/**
	 * Deletes the review close with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ReviewCloseLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param closeId the primary key of the review close
	 * @return the review close that was removed
	 * @throws PortalException if a review close with the primary key could not be found
	 */
	@Override
	public com.trantorinc.synergy.performance.core.model.ReviewClose
			deleteReviewClose(long closeId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _reviewCloseLocalService.deleteReviewClose(closeId);
	}

	/**
	 * Deletes the review close from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ReviewCloseLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param reviewClose the review close
	 * @return the review close that was removed
	 */
	@Override
	public com.trantorinc.synergy.performance.core.model.ReviewClose
		deleteReviewClose(
			com.trantorinc.synergy.performance.core.model.ReviewClose
				reviewClose) {

		return _reviewCloseLocalService.deleteReviewClose(reviewClose);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _reviewCloseLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _reviewCloseLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _reviewCloseLocalService.dynamicQuery();
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

		return _reviewCloseLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.trantorinc.synergy.performance.core.model.impl.ReviewCloseModelImpl</code>.
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

		return _reviewCloseLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.trantorinc.synergy.performance.core.model.impl.ReviewCloseModelImpl</code>.
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

		return _reviewCloseLocalService.dynamicQuery(
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

		return _reviewCloseLocalService.dynamicQueryCount(dynamicQuery);
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

		return _reviewCloseLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.trantorinc.synergy.performance.core.model.ReviewClose
		fetchReviewClose(long closeId) {

		return _reviewCloseLocalService.fetchReviewClose(closeId);
	}

	@Override
	public java.util.List
		<com.trantorinc.synergy.performance.core.model.ReviewClose>
			findReviewCloseByEmail(String managerEmail) {

		return _reviewCloseLocalService.findReviewCloseByEmail(managerEmail);
	}

	@Override
	public java.util.List
		<com.trantorinc.synergy.performance.core.model.ReviewClose>
			findReviewCloseByReviewId(long reviewId) {

		return _reviewCloseLocalService.findReviewCloseByReviewId(reviewId);
	}

	@Override
	public java.util.List
		<com.trantorinc.synergy.performance.core.model.ReviewClose>
			findReviewCloseByStatus(int status) {

		return _reviewCloseLocalService.findReviewCloseByStatus(status);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _reviewCloseLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _reviewCloseLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _reviewCloseLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _reviewCloseLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns the review close with the primary key.
	 *
	 * @param closeId the primary key of the review close
	 * @return the review close
	 * @throws PortalException if a review close with the primary key could not be found
	 */
	@Override
	public com.trantorinc.synergy.performance.core.model.ReviewClose
			getReviewClose(long closeId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _reviewCloseLocalService.getReviewClose(closeId);
	}

	/**
	 * Returns a range of all the review closes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.trantorinc.synergy.performance.core.model.impl.ReviewCloseModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of review closes
	 * @param end the upper bound of the range of review closes (not inclusive)
	 * @return the range of review closes
	 */
	@Override
	public java.util.List
		<com.trantorinc.synergy.performance.core.model.ReviewClose>
			getReviewCloses(int start, int end) {

		return _reviewCloseLocalService.getReviewCloses(start, end);
	}

	/**
	 * Returns the number of review closes.
	 *
	 * @return the number of review closes
	 */
	@Override
	public int getReviewClosesCount() {
		return _reviewCloseLocalService.getReviewClosesCount();
	}

	/**
	 * Updates the review close in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ReviewCloseLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param reviewClose the review close
	 * @return the review close that was updated
	 */
	@Override
	public com.trantorinc.synergy.performance.core.model.ReviewClose
		updateReviewClose(
			com.trantorinc.synergy.performance.core.model.ReviewClose
				reviewClose) {

		return _reviewCloseLocalService.updateReviewClose(reviewClose);
	}

	@Override
	public ReviewCloseLocalService getWrappedService() {
		return _reviewCloseLocalService;
	}

	@Override
	public void setWrappedService(
		ReviewCloseLocalService reviewCloseLocalService) {

		_reviewCloseLocalService = reviewCloseLocalService;
	}

	private ReviewCloseLocalService _reviewCloseLocalService;

}