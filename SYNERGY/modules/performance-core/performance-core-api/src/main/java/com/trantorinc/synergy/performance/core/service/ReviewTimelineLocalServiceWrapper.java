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
 * Provides a wrapper for {@link ReviewTimelineLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see ReviewTimelineLocalService
 * @generated
 */
public class ReviewTimelineLocalServiceWrapper
	implements ReviewTimelineLocalService,
			   ServiceWrapper<ReviewTimelineLocalService> {

	public ReviewTimelineLocalServiceWrapper() {
		this(null);
	}

	public ReviewTimelineLocalServiceWrapper(
		ReviewTimelineLocalService reviewTimelineLocalService) {

		_reviewTimelineLocalService = reviewTimelineLocalService;
	}

	/**
	 * Adds the review timeline to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ReviewTimelineLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param reviewTimeline the review timeline
	 * @return the review timeline that was added
	 */
	@Override
	public com.trantorinc.synergy.performance.core.model.ReviewTimeline
		addReviewTimeline(
			com.trantorinc.synergy.performance.core.model.ReviewTimeline
				reviewTimeline) {

		return _reviewTimelineLocalService.addReviewTimeline(reviewTimeline);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _reviewTimelineLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Creates a new review timeline with the primary key. Does not add the review timeline to the database.
	 *
	 * @param timelineId the primary key for the new review timeline
	 * @return the new review timeline
	 */
	@Override
	public com.trantorinc.synergy.performance.core.model.ReviewTimeline
		createReviewTimeline(long timelineId) {

		return _reviewTimelineLocalService.createReviewTimeline(timelineId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _reviewTimelineLocalService.deletePersistedModel(persistedModel);
	}

	/**
	 * Deletes the review timeline with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ReviewTimelineLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param timelineId the primary key of the review timeline
	 * @return the review timeline that was removed
	 * @throws PortalException if a review timeline with the primary key could not be found
	 */
	@Override
	public com.trantorinc.synergy.performance.core.model.ReviewTimeline
			deleteReviewTimeline(long timelineId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _reviewTimelineLocalService.deleteReviewTimeline(timelineId);
	}

	/**
	 * Deletes the review timeline from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ReviewTimelineLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param reviewTimeline the review timeline
	 * @return the review timeline that was removed
	 */
	@Override
	public com.trantorinc.synergy.performance.core.model.ReviewTimeline
		deleteReviewTimeline(
			com.trantorinc.synergy.performance.core.model.ReviewTimeline
				reviewTimeline) {

		return _reviewTimelineLocalService.deleteReviewTimeline(reviewTimeline);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _reviewTimelineLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _reviewTimelineLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _reviewTimelineLocalService.dynamicQuery();
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

		return _reviewTimelineLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.trantorinc.synergy.performance.core.model.impl.ReviewTimelineModelImpl</code>.
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

		return _reviewTimelineLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.trantorinc.synergy.performance.core.model.impl.ReviewTimelineModelImpl</code>.
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

		return _reviewTimelineLocalService.dynamicQuery(
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

		return _reviewTimelineLocalService.dynamicQueryCount(dynamicQuery);
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

		return _reviewTimelineLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.trantorinc.synergy.performance.core.model.ReviewTimeline
		fetchReviewTimeline(long timelineId) {

		return _reviewTimelineLocalService.fetchReviewTimeline(timelineId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _reviewTimelineLocalService.getActionableDynamicQuery();
	}

	@Override
	public java.util.List
		<com.trantorinc.synergy.performance.core.model.ReviewTimeline>
			getCalibratedTimelines() {

		return _reviewTimelineLocalService.getCalibratedTimelines();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _reviewTimelineLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _reviewTimelineLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _reviewTimelineLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns the review timeline with the primary key.
	 *
	 * @param timelineId the primary key of the review timeline
	 * @return the review timeline
	 * @throws PortalException if a review timeline with the primary key could not be found
	 */
	@Override
	public com.trantorinc.synergy.performance.core.model.ReviewTimeline
			getReviewTimeline(long timelineId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _reviewTimelineLocalService.getReviewTimeline(timelineId);
	}

	/**
	 * Returns a range of all the review timelines.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.trantorinc.synergy.performance.core.model.impl.ReviewTimelineModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of review timelines
	 * @param end the upper bound of the range of review timelines (not inclusive)
	 * @return the range of review timelines
	 */
	@Override
	public java.util.List
		<com.trantorinc.synergy.performance.core.model.ReviewTimeline>
			getReviewTimelines(int start, int end) {

		return _reviewTimelineLocalService.getReviewTimelines(start, end);
	}

	/**
	 * Returns the number of review timelines.
	 *
	 * @return the number of review timelines
	 */
	@Override
	public int getReviewTimelinesCount() {
		return _reviewTimelineLocalService.getReviewTimelinesCount();
	}

	/**
	 * Updates the review timeline in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ReviewTimelineLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param reviewTimeline the review timeline
	 * @return the review timeline that was updated
	 */
	@Override
	public com.trantorinc.synergy.performance.core.model.ReviewTimeline
		updateReviewTimeline(
			com.trantorinc.synergy.performance.core.model.ReviewTimeline
				reviewTimeline) {

		return _reviewTimelineLocalService.updateReviewTimeline(reviewTimeline);
	}

	@Override
	public ReviewTimelineLocalService getWrappedService() {
		return _reviewTimelineLocalService;
	}

	@Override
	public void setWrappedService(
		ReviewTimelineLocalService reviewTimelineLocalService) {

		_reviewTimelineLocalService = reviewTimelineLocalService;
	}

	private ReviewTimelineLocalService _reviewTimelineLocalService;

}