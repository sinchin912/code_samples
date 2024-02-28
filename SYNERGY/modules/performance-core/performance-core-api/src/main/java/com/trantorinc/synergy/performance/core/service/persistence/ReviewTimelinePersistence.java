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

import com.trantorinc.synergy.performance.core.exception.NoSuchReviewTimelineException;
import com.trantorinc.synergy.performance.core.model.ReviewTimeline;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the review timeline service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ReviewTimelineUtil
 * @generated
 */
@ProviderType
public interface ReviewTimelinePersistence
	extends BasePersistence<ReviewTimeline> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ReviewTimelineUtil} to access the review timeline persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Caches the review timeline in the entity cache if it is enabled.
	 *
	 * @param reviewTimeline the review timeline
	 */
	public void cacheResult(ReviewTimeline reviewTimeline);

	/**
	 * Caches the review timelines in the entity cache if it is enabled.
	 *
	 * @param reviewTimelines the review timelines
	 */
	public void cacheResult(java.util.List<ReviewTimeline> reviewTimelines);

	/**
	 * Creates a new review timeline with the primary key. Does not add the review timeline to the database.
	 *
	 * @param timelineId the primary key for the new review timeline
	 * @return the new review timeline
	 */
	public ReviewTimeline create(long timelineId);

	/**
	 * Removes the review timeline with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param timelineId the primary key of the review timeline
	 * @return the review timeline that was removed
	 * @throws NoSuchReviewTimelineException if a review timeline with the primary key could not be found
	 */
	public ReviewTimeline remove(long timelineId)
		throws NoSuchReviewTimelineException;

	public ReviewTimeline updateImpl(ReviewTimeline reviewTimeline);

	/**
	 * Returns the review timeline with the primary key or throws a <code>NoSuchReviewTimelineException</code> if it could not be found.
	 *
	 * @param timelineId the primary key of the review timeline
	 * @return the review timeline
	 * @throws NoSuchReviewTimelineException if a review timeline with the primary key could not be found
	 */
	public ReviewTimeline findByPrimaryKey(long timelineId)
		throws NoSuchReviewTimelineException;

	/**
	 * Returns the review timeline with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param timelineId the primary key of the review timeline
	 * @return the review timeline, or <code>null</code> if a review timeline with the primary key could not be found
	 */
	public ReviewTimeline fetchByPrimaryKey(long timelineId);

	/**
	 * Returns all the review timelines.
	 *
	 * @return the review timelines
	 */
	public java.util.List<ReviewTimeline> findAll();

	/**
	 * Returns a range of all the review timelines.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ReviewTimelineModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of review timelines
	 * @param end the upper bound of the range of review timelines (not inclusive)
	 * @return the range of review timelines
	 */
	public java.util.List<ReviewTimeline> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the review timelines.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ReviewTimelineModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of review timelines
	 * @param end the upper bound of the range of review timelines (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of review timelines
	 */
	public java.util.List<ReviewTimeline> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ReviewTimeline>
			orderByComparator);

	/**
	 * Returns an ordered range of all the review timelines.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ReviewTimelineModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of review timelines
	 * @param end the upper bound of the range of review timelines (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of review timelines
	 */
	public java.util.List<ReviewTimeline> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ReviewTimeline>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the review timelines from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of review timelines.
	 *
	 * @return the number of review timelines
	 */
	public int countAll();

}