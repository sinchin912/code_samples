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

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import com.trantorinc.synergy.performance.core.exception.NoSuchReviewCommentException;
import com.trantorinc.synergy.performance.core.model.ReviewComment;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the review comment service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ReviewCommentUtil
 * @generated
 */
@ProviderType
public interface ReviewCommentPersistence
	extends BasePersistence<ReviewComment> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ReviewCommentUtil} to access the review comment persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Caches the review comment in the entity cache if it is enabled.
	 *
	 * @param reviewComment the review comment
	 */
	public void cacheResult(ReviewComment reviewComment);

	/**
	 * Caches the review comments in the entity cache if it is enabled.
	 *
	 * @param reviewComments the review comments
	 */
	public void cacheResult(java.util.List<ReviewComment> reviewComments);

	/**
	 * Creates a new review comment with the primary key. Does not add the review comment to the database.
	 *
	 * @param commentId the primary key for the new review comment
	 * @return the new review comment
	 */
	public ReviewComment create(long commentId);

	/**
	 * Removes the review comment with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param commentId the primary key of the review comment
	 * @return the review comment that was removed
	 * @throws NoSuchReviewCommentException if a review comment with the primary key could not be found
	 */
	public ReviewComment remove(long commentId)
		throws NoSuchReviewCommentException;

	public ReviewComment updateImpl(ReviewComment reviewComment);

	/**
	 * Returns the review comment with the primary key or throws a <code>NoSuchReviewCommentException</code> if it could not be found.
	 *
	 * @param commentId the primary key of the review comment
	 * @return the review comment
	 * @throws NoSuchReviewCommentException if a review comment with the primary key could not be found
	 */
	public ReviewComment findByPrimaryKey(long commentId)
		throws NoSuchReviewCommentException;

	/**
	 * Returns the review comment with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param commentId the primary key of the review comment
	 * @return the review comment, or <code>null</code> if a review comment with the primary key could not be found
	 */
	public ReviewComment fetchByPrimaryKey(long commentId);

	/**
	 * Returns all the review comments.
	 *
	 * @return the review comments
	 */
	public java.util.List<ReviewComment> findAll();

	/**
	 * Returns a range of all the review comments.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ReviewCommentModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of review comments
	 * @param end the upper bound of the range of review comments (not inclusive)
	 * @return the range of review comments
	 */
	public java.util.List<ReviewComment> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the review comments.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ReviewCommentModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of review comments
	 * @param end the upper bound of the range of review comments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of review comments
	 */
	public java.util.List<ReviewComment> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ReviewComment>
			orderByComparator);

	/**
	 * Returns an ordered range of all the review comments.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ReviewCommentModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of review comments
	 * @param end the upper bound of the range of review comments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of review comments
	 */
	public java.util.List<ReviewComment> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ReviewComment>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the review comments from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of review comments.
	 *
	 * @return the number of review comments
	 */
	public int countAll();

}