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
 * Provides a wrapper for {@link ReviewCommentLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see ReviewCommentLocalService
 * @generated
 */
public class ReviewCommentLocalServiceWrapper
	implements ReviewCommentLocalService,
			   ServiceWrapper<ReviewCommentLocalService> {

	public ReviewCommentLocalServiceWrapper() {
		this(null);
	}

	public ReviewCommentLocalServiceWrapper(
		ReviewCommentLocalService reviewCommentLocalService) {

		_reviewCommentLocalService = reviewCommentLocalService;
	}

	/**
	 * Adds the review comment to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ReviewCommentLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param reviewComment the review comment
	 * @return the review comment that was added
	 */
	@Override
	public com.trantorinc.synergy.performance.core.model.ReviewComment
		addReviewComment(
			com.trantorinc.synergy.performance.core.model.ReviewComment
				reviewComment) {

		return _reviewCommentLocalService.addReviewComment(reviewComment);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _reviewCommentLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Creates a new review comment with the primary key. Does not add the review comment to the database.
	 *
	 * @param commentId the primary key for the new review comment
	 * @return the new review comment
	 */
	@Override
	public com.trantorinc.synergy.performance.core.model.ReviewComment
		createReviewComment(long commentId) {

		return _reviewCommentLocalService.createReviewComment(commentId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _reviewCommentLocalService.deletePersistedModel(persistedModel);
	}

	/**
	 * Deletes the review comment with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ReviewCommentLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param commentId the primary key of the review comment
	 * @return the review comment that was removed
	 * @throws PortalException if a review comment with the primary key could not be found
	 */
	@Override
	public com.trantorinc.synergy.performance.core.model.ReviewComment
			deleteReviewComment(long commentId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _reviewCommentLocalService.deleteReviewComment(commentId);
	}

	/**
	 * Deletes the review comment from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ReviewCommentLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param reviewComment the review comment
	 * @return the review comment that was removed
	 */
	@Override
	public com.trantorinc.synergy.performance.core.model.ReviewComment
		deleteReviewComment(
			com.trantorinc.synergy.performance.core.model.ReviewComment
				reviewComment) {

		return _reviewCommentLocalService.deleteReviewComment(reviewComment);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _reviewCommentLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _reviewCommentLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _reviewCommentLocalService.dynamicQuery();
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

		return _reviewCommentLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.trantorinc.synergy.performance.core.model.impl.ReviewCommentModelImpl</code>.
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

		return _reviewCommentLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.trantorinc.synergy.performance.core.model.impl.ReviewCommentModelImpl</code>.
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

		return _reviewCommentLocalService.dynamicQuery(
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

		return _reviewCommentLocalService.dynamicQueryCount(dynamicQuery);
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

		return _reviewCommentLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.trantorinc.synergy.performance.core.model.ReviewComment
		fetchReviewComment(long commentId) {

		return _reviewCommentLocalService.fetchReviewComment(commentId);
	}

	@Override
	public java.util.List
		<com.trantorinc.synergy.performance.core.model.ReviewComment>
			findReviewCommentByReviewId(long reviewId) {

		return _reviewCommentLocalService.findReviewCommentByReviewId(reviewId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _reviewCommentLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _reviewCommentLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _reviewCommentLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _reviewCommentLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns the review comment with the primary key.
	 *
	 * @param commentId the primary key of the review comment
	 * @return the review comment
	 * @throws PortalException if a review comment with the primary key could not be found
	 */
	@Override
	public com.trantorinc.synergy.performance.core.model.ReviewComment
			getReviewComment(long commentId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _reviewCommentLocalService.getReviewComment(commentId);
	}

	/**
	 * Returns a range of all the review comments.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.trantorinc.synergy.performance.core.model.impl.ReviewCommentModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of review comments
	 * @param end the upper bound of the range of review comments (not inclusive)
	 * @return the range of review comments
	 */
	@Override
	public java.util.List
		<com.trantorinc.synergy.performance.core.model.ReviewComment>
			getReviewComments(int start, int end) {

		return _reviewCommentLocalService.getReviewComments(start, end);
	}

	/**
	 * Returns the number of review comments.
	 *
	 * @return the number of review comments
	 */
	@Override
	public int getReviewCommentsCount() {
		return _reviewCommentLocalService.getReviewCommentsCount();
	}

	/**
	 * Updates the review comment in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ReviewCommentLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param reviewComment the review comment
	 * @return the review comment that was updated
	 */
	@Override
	public com.trantorinc.synergy.performance.core.model.ReviewComment
		updateReviewComment(
			com.trantorinc.synergy.performance.core.model.ReviewComment
				reviewComment) {

		return _reviewCommentLocalService.updateReviewComment(reviewComment);
	}

	@Override
	public ReviewCommentLocalService getWrappedService() {
		return _reviewCommentLocalService;
	}

	@Override
	public void setWrappedService(
		ReviewCommentLocalService reviewCommentLocalService) {

		_reviewCommentLocalService = reviewCommentLocalService;
	}

	private ReviewCommentLocalService _reviewCommentLocalService;

}