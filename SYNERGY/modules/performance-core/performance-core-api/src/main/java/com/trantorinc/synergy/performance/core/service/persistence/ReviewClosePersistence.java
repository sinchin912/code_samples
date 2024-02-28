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

import com.trantorinc.synergy.performance.core.exception.NoSuchReviewCloseException;
import com.trantorinc.synergy.performance.core.model.ReviewClose;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the review close service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ReviewCloseUtil
 * @generated
 */
@ProviderType
public interface ReviewClosePersistence extends BasePersistence<ReviewClose> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ReviewCloseUtil} to access the review close persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Caches the review close in the entity cache if it is enabled.
	 *
	 * @param reviewClose the review close
	 */
	public void cacheResult(ReviewClose reviewClose);

	/**
	 * Caches the review closes in the entity cache if it is enabled.
	 *
	 * @param reviewCloses the review closes
	 */
	public void cacheResult(java.util.List<ReviewClose> reviewCloses);

	/**
	 * Creates a new review close with the primary key. Does not add the review close to the database.
	 *
	 * @param closeId the primary key for the new review close
	 * @return the new review close
	 */
	public ReviewClose create(long closeId);

	/**
	 * Removes the review close with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param closeId the primary key of the review close
	 * @return the review close that was removed
	 * @throws NoSuchReviewCloseException if a review close with the primary key could not be found
	 */
	public ReviewClose remove(long closeId) throws NoSuchReviewCloseException;

	public ReviewClose updateImpl(ReviewClose reviewClose);

	/**
	 * Returns the review close with the primary key or throws a <code>NoSuchReviewCloseException</code> if it could not be found.
	 *
	 * @param closeId the primary key of the review close
	 * @return the review close
	 * @throws NoSuchReviewCloseException if a review close with the primary key could not be found
	 */
	public ReviewClose findByPrimaryKey(long closeId)
		throws NoSuchReviewCloseException;

	/**
	 * Returns the review close with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param closeId the primary key of the review close
	 * @return the review close, or <code>null</code> if a review close with the primary key could not be found
	 */
	public ReviewClose fetchByPrimaryKey(long closeId);

	/**
	 * Returns all the review closes.
	 *
	 * @return the review closes
	 */
	public java.util.List<ReviewClose> findAll();

	/**
	 * Returns a range of all the review closes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ReviewCloseModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of review closes
	 * @param end the upper bound of the range of review closes (not inclusive)
	 * @return the range of review closes
	 */
	public java.util.List<ReviewClose> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the review closes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ReviewCloseModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of review closes
	 * @param end the upper bound of the range of review closes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of review closes
	 */
	public java.util.List<ReviewClose> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ReviewClose>
			orderByComparator);

	/**
	 * Returns an ordered range of all the review closes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ReviewCloseModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of review closes
	 * @param end the upper bound of the range of review closes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of review closes
	 */
	public java.util.List<ReviewClose> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ReviewClose>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the review closes from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of review closes.
	 *
	 * @return the number of review closes
	 */
	public int countAll();

}