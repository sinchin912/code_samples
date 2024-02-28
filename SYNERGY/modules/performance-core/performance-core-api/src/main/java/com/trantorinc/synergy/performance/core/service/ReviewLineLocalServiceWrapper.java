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
 * Provides a wrapper for {@link ReviewLineLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see ReviewLineLocalService
 * @generated
 */
public class ReviewLineLocalServiceWrapper
	implements ReviewLineLocalService, ServiceWrapper<ReviewLineLocalService> {

	public ReviewLineLocalServiceWrapper() {
		this(null);
	}

	public ReviewLineLocalServiceWrapper(
		ReviewLineLocalService reviewLineLocalService) {

		_reviewLineLocalService = reviewLineLocalService;
	}

	/**
	 * Adds the review line to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ReviewLineLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param reviewLine the review line
	 * @return the review line that was added
	 */
	@Override
	public com.trantorinc.synergy.performance.core.model.ReviewLine
		addReviewLine(
			com.trantorinc.synergy.performance.core.model.ReviewLine
				reviewLine) {

		return _reviewLineLocalService.addReviewLine(reviewLine);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _reviewLineLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Creates a new review line with the primary key. Does not add the review line to the database.
	 *
	 * @param lineId the primary key for the new review line
	 * @return the new review line
	 */
	@Override
	public com.trantorinc.synergy.performance.core.model.ReviewLine
		createReviewLine(long lineId) {

		return _reviewLineLocalService.createReviewLine(lineId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _reviewLineLocalService.deletePersistedModel(persistedModel);
	}

	/**
	 * Deletes the review line with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ReviewLineLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param lineId the primary key of the review line
	 * @return the review line that was removed
	 * @throws PortalException if a review line with the primary key could not be found
	 */
	@Override
	public com.trantorinc.synergy.performance.core.model.ReviewLine
			deleteReviewLine(long lineId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _reviewLineLocalService.deleteReviewLine(lineId);
	}

	/**
	 * Deletes the review line from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ReviewLineLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param reviewLine the review line
	 * @return the review line that was removed
	 */
	@Override
	public com.trantorinc.synergy.performance.core.model.ReviewLine
		deleteReviewLine(
			com.trantorinc.synergy.performance.core.model.ReviewLine
				reviewLine) {

		return _reviewLineLocalService.deleteReviewLine(reviewLine);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _reviewLineLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _reviewLineLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _reviewLineLocalService.dynamicQuery();
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

		return _reviewLineLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.trantorinc.synergy.performance.core.model.impl.ReviewLineModelImpl</code>.
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

		return _reviewLineLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.trantorinc.synergy.performance.core.model.impl.ReviewLineModelImpl</code>.
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

		return _reviewLineLocalService.dynamicQuery(
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

		return _reviewLineLocalService.dynamicQueryCount(dynamicQuery);
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

		return _reviewLineLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.trantorinc.synergy.performance.core.model.ReviewLine
		fetchReviewLine(long lineId) {

		return _reviewLineLocalService.fetchReviewLine(lineId);
	}

	@Override
	public java.util.List
		<com.trantorinc.synergy.performance.core.model.ReviewLine>
			findReviewLineByReviewId(long reviewId) {

		return _reviewLineLocalService.findReviewLineByReviewId(reviewId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _reviewLineLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _reviewLineLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _reviewLineLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _reviewLineLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns the review line with the primary key.
	 *
	 * @param lineId the primary key of the review line
	 * @return the review line
	 * @throws PortalException if a review line with the primary key could not be found
	 */
	@Override
	public com.trantorinc.synergy.performance.core.model.ReviewLine
			getReviewLine(long lineId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _reviewLineLocalService.getReviewLine(lineId);
	}

	/**
	 * Returns a range of all the review lines.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.trantorinc.synergy.performance.core.model.impl.ReviewLineModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of review lines
	 * @param end the upper bound of the range of review lines (not inclusive)
	 * @return the range of review lines
	 */
	@Override
	public java.util.List
		<com.trantorinc.synergy.performance.core.model.ReviewLine>
			getReviewLines(int start, int end) {

		return _reviewLineLocalService.getReviewLines(start, end);
	}

	/**
	 * Returns the number of review lines.
	 *
	 * @return the number of review lines
	 */
	@Override
	public int getReviewLinesCount() {
		return _reviewLineLocalService.getReviewLinesCount();
	}

	/**
	 * Updates the review line in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ReviewLineLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param reviewLine the review line
	 * @return the review line that was updated
	 */
	@Override
	public com.trantorinc.synergy.performance.core.model.ReviewLine
		updateReviewLine(
			com.trantorinc.synergy.performance.core.model.ReviewLine
				reviewLine) {

		return _reviewLineLocalService.updateReviewLine(reviewLine);
	}

	@Override
	public ReviewLineLocalService getWrappedService() {
		return _reviewLineLocalService;
	}

	@Override
	public void setWrappedService(
		ReviewLineLocalService reviewLineLocalService) {

		_reviewLineLocalService = reviewLineLocalService;
	}

	private ReviewLineLocalService _reviewLineLocalService;

}