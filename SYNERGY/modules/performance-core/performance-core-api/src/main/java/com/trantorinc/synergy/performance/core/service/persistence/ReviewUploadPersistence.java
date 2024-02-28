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

import com.trantorinc.synergy.performance.core.exception.NoSuchReviewUploadException;
import com.trantorinc.synergy.performance.core.model.ReviewUpload;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the review upload service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ReviewUploadUtil
 * @generated
 */
@ProviderType
public interface ReviewUploadPersistence extends BasePersistence<ReviewUpload> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ReviewUploadUtil} to access the review upload persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Caches the review upload in the entity cache if it is enabled.
	 *
	 * @param reviewUpload the review upload
	 */
	public void cacheResult(ReviewUpload reviewUpload);

	/**
	 * Caches the review uploads in the entity cache if it is enabled.
	 *
	 * @param reviewUploads the review uploads
	 */
	public void cacheResult(java.util.List<ReviewUpload> reviewUploads);

	/**
	 * Creates a new review upload with the primary key. Does not add the review upload to the database.
	 *
	 * @param uploadId the primary key for the new review upload
	 * @return the new review upload
	 */
	public ReviewUpload create(long uploadId);

	/**
	 * Removes the review upload with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param uploadId the primary key of the review upload
	 * @return the review upload that was removed
	 * @throws NoSuchReviewUploadException if a review upload with the primary key could not be found
	 */
	public ReviewUpload remove(long uploadId)
		throws NoSuchReviewUploadException;

	public ReviewUpload updateImpl(ReviewUpload reviewUpload);

	/**
	 * Returns the review upload with the primary key or throws a <code>NoSuchReviewUploadException</code> if it could not be found.
	 *
	 * @param uploadId the primary key of the review upload
	 * @return the review upload
	 * @throws NoSuchReviewUploadException if a review upload with the primary key could not be found
	 */
	public ReviewUpload findByPrimaryKey(long uploadId)
		throws NoSuchReviewUploadException;

	/**
	 * Returns the review upload with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param uploadId the primary key of the review upload
	 * @return the review upload, or <code>null</code> if a review upload with the primary key could not be found
	 */
	public ReviewUpload fetchByPrimaryKey(long uploadId);

	/**
	 * Returns all the review uploads.
	 *
	 * @return the review uploads
	 */
	public java.util.List<ReviewUpload> findAll();

	/**
	 * Returns a range of all the review uploads.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ReviewUploadModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of review uploads
	 * @param end the upper bound of the range of review uploads (not inclusive)
	 * @return the range of review uploads
	 */
	public java.util.List<ReviewUpload> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the review uploads.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ReviewUploadModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of review uploads
	 * @param end the upper bound of the range of review uploads (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of review uploads
	 */
	public java.util.List<ReviewUpload> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ReviewUpload>
			orderByComparator);

	/**
	 * Returns an ordered range of all the review uploads.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ReviewUploadModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of review uploads
	 * @param end the upper bound of the range of review uploads (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of review uploads
	 */
	public java.util.List<ReviewUpload> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ReviewUpload>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the review uploads from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of review uploads.
	 *
	 * @return the number of review uploads
	 */
	public int countAll();

}