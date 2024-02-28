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

import com.trantorinc.synergy.performance.core.exception.NoSuchReviewLineException;
import com.trantorinc.synergy.performance.core.model.ReviewLine;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the review line service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ReviewLineUtil
 * @generated
 */
@ProviderType
public interface ReviewLinePersistence extends BasePersistence<ReviewLine> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ReviewLineUtil} to access the review line persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Caches the review line in the entity cache if it is enabled.
	 *
	 * @param reviewLine the review line
	 */
	public void cacheResult(ReviewLine reviewLine);

	/**
	 * Caches the review lines in the entity cache if it is enabled.
	 *
	 * @param reviewLines the review lines
	 */
	public void cacheResult(java.util.List<ReviewLine> reviewLines);

	/**
	 * Creates a new review line with the primary key. Does not add the review line to the database.
	 *
	 * @param lineId the primary key for the new review line
	 * @return the new review line
	 */
	public ReviewLine create(long lineId);

	/**
	 * Removes the review line with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param lineId the primary key of the review line
	 * @return the review line that was removed
	 * @throws NoSuchReviewLineException if a review line with the primary key could not be found
	 */
	public ReviewLine remove(long lineId) throws NoSuchReviewLineException;

	public ReviewLine updateImpl(ReviewLine reviewLine);

	/**
	 * Returns the review line with the primary key or throws a <code>NoSuchReviewLineException</code> if it could not be found.
	 *
	 * @param lineId the primary key of the review line
	 * @return the review line
	 * @throws NoSuchReviewLineException if a review line with the primary key could not be found
	 */
	public ReviewLine findByPrimaryKey(long lineId)
		throws NoSuchReviewLineException;

	/**
	 * Returns the review line with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param lineId the primary key of the review line
	 * @return the review line, or <code>null</code> if a review line with the primary key could not be found
	 */
	public ReviewLine fetchByPrimaryKey(long lineId);

	/**
	 * Returns all the review lines.
	 *
	 * @return the review lines
	 */
	public java.util.List<ReviewLine> findAll();

	/**
	 * Returns a range of all the review lines.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ReviewLineModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of review lines
	 * @param end the upper bound of the range of review lines (not inclusive)
	 * @return the range of review lines
	 */
	public java.util.List<ReviewLine> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the review lines.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ReviewLineModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of review lines
	 * @param end the upper bound of the range of review lines (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of review lines
	 */
	public java.util.List<ReviewLine> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ReviewLine>
			orderByComparator);

	/**
	 * Returns an ordered range of all the review lines.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ReviewLineModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of review lines
	 * @param end the upper bound of the range of review lines (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of review lines
	 */
	public java.util.List<ReviewLine> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ReviewLine>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the review lines from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of review lines.
	 *
	 * @return the number of review lines
	 */
	public int countAll();

}