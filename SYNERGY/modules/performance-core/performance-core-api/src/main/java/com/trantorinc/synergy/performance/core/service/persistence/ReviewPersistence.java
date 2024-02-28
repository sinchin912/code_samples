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

import com.trantorinc.synergy.performance.core.exception.NoSuchReviewException;
import com.trantorinc.synergy.performance.core.model.Review;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the review service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ReviewUtil
 * @generated
 */
@ProviderType
public interface ReviewPersistence extends BasePersistence<Review> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ReviewUtil} to access the review persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Caches the review in the entity cache if it is enabled.
	 *
	 * @param review the review
	 */
	public void cacheResult(Review review);

	/**
	 * Caches the reviews in the entity cache if it is enabled.
	 *
	 * @param reviews the reviews
	 */
	public void cacheResult(java.util.List<Review> reviews);

	/**
	 * Creates a new review with the primary key. Does not add the review to the database.
	 *
	 * @param reviewId the primary key for the new review
	 * @return the new review
	 */
	public Review create(long reviewId);

	/**
	 * Removes the review with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param reviewId the primary key of the review
	 * @return the review that was removed
	 * @throws NoSuchReviewException if a review with the primary key could not be found
	 */
	public Review remove(long reviewId) throws NoSuchReviewException;

	public Review updateImpl(Review review);

	/**
	 * Returns the review with the primary key or throws a <code>NoSuchReviewException</code> if it could not be found.
	 *
	 * @param reviewId the primary key of the review
	 * @return the review
	 * @throws NoSuchReviewException if a review with the primary key could not be found
	 */
	public Review findByPrimaryKey(long reviewId) throws NoSuchReviewException;

	/**
	 * Returns the review with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param reviewId the primary key of the review
	 * @return the review, or <code>null</code> if a review with the primary key could not be found
	 */
	public Review fetchByPrimaryKey(long reviewId);

	/**
	 * Returns all the reviews.
	 *
	 * @return the reviews
	 */
	public java.util.List<Review> findAll();

	/**
	 * Returns a range of all the reviews.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ReviewModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of reviews
	 * @param end the upper bound of the range of reviews (not inclusive)
	 * @return the range of reviews
	 */
	public java.util.List<Review> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the reviews.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ReviewModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of reviews
	 * @param end the upper bound of the range of reviews (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of reviews
	 */
	public java.util.List<Review> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Review>
			orderByComparator);

	/**
	 * Returns an ordered range of all the reviews.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ReviewModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of reviews
	 * @param end the upper bound of the range of reviews (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of reviews
	 */
	public java.util.List<Review> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Review>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the reviews from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of reviews.
	 *
	 * @return the number of reviews
	 */
	public int countAll();

}