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

import com.trantorinc.synergy.performance.core.exception.NoSuchReviewRollbackException;
import com.trantorinc.synergy.performance.core.model.ReviewRollback;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the review rollback service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ReviewRollbackUtil
 * @generated
 */
@ProviderType
public interface ReviewRollbackPersistence
	extends BasePersistence<ReviewRollback> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ReviewRollbackUtil} to access the review rollback persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Caches the review rollback in the entity cache if it is enabled.
	 *
	 * @param reviewRollback the review rollback
	 */
	public void cacheResult(ReviewRollback reviewRollback);

	/**
	 * Caches the review rollbacks in the entity cache if it is enabled.
	 *
	 * @param reviewRollbacks the review rollbacks
	 */
	public void cacheResult(java.util.List<ReviewRollback> reviewRollbacks);

	/**
	 * Creates a new review rollback with the primary key. Does not add the review rollback to the database.
	 *
	 * @param rollbackId the primary key for the new review rollback
	 * @return the new review rollback
	 */
	public ReviewRollback create(long rollbackId);

	/**
	 * Removes the review rollback with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param rollbackId the primary key of the review rollback
	 * @return the review rollback that was removed
	 * @throws NoSuchReviewRollbackException if a review rollback with the primary key could not be found
	 */
	public ReviewRollback remove(long rollbackId)
		throws NoSuchReviewRollbackException;

	public ReviewRollback updateImpl(ReviewRollback reviewRollback);

	/**
	 * Returns the review rollback with the primary key or throws a <code>NoSuchReviewRollbackException</code> if it could not be found.
	 *
	 * @param rollbackId the primary key of the review rollback
	 * @return the review rollback
	 * @throws NoSuchReviewRollbackException if a review rollback with the primary key could not be found
	 */
	public ReviewRollback findByPrimaryKey(long rollbackId)
		throws NoSuchReviewRollbackException;

	/**
	 * Returns the review rollback with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param rollbackId the primary key of the review rollback
	 * @return the review rollback, or <code>null</code> if a review rollback with the primary key could not be found
	 */
	public ReviewRollback fetchByPrimaryKey(long rollbackId);

	/**
	 * Returns all the review rollbacks.
	 *
	 * @return the review rollbacks
	 */
	public java.util.List<ReviewRollback> findAll();

	/**
	 * Returns a range of all the review rollbacks.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ReviewRollbackModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of review rollbacks
	 * @param end the upper bound of the range of review rollbacks (not inclusive)
	 * @return the range of review rollbacks
	 */
	public java.util.List<ReviewRollback> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the review rollbacks.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ReviewRollbackModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of review rollbacks
	 * @param end the upper bound of the range of review rollbacks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of review rollbacks
	 */
	public java.util.List<ReviewRollback> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ReviewRollback>
			orderByComparator);

	/**
	 * Returns an ordered range of all the review rollbacks.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ReviewRollbackModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of review rollbacks
	 * @param end the upper bound of the range of review rollbacks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of review rollbacks
	 */
	public java.util.List<ReviewRollback> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ReviewRollback>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the review rollbacks from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of review rollbacks.
	 *
	 * @return the number of review rollbacks
	 */
	public int countAll();

}