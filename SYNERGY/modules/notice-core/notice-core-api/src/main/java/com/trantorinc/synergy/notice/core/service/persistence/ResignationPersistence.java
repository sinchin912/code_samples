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

package com.trantorinc.synergy.notice.core.service.persistence;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import com.trantorinc.synergy.notice.core.exception.NoSuchResignationException;
import com.trantorinc.synergy.notice.core.model.Resignation;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the resignation service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ResignationUtil
 * @generated
 */
@ProviderType
public interface ResignationPersistence extends BasePersistence<Resignation> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ResignationUtil} to access the resignation persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Caches the resignation in the entity cache if it is enabled.
	 *
	 * @param resignation the resignation
	 */
	public void cacheResult(Resignation resignation);

	/**
	 * Caches the resignations in the entity cache if it is enabled.
	 *
	 * @param resignations the resignations
	 */
	public void cacheResult(java.util.List<Resignation> resignations);

	/**
	 * Creates a new resignation with the primary key. Does not add the resignation to the database.
	 *
	 * @param id the primary key for the new resignation
	 * @return the new resignation
	 */
	public Resignation create(long id);

	/**
	 * Removes the resignation with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the resignation
	 * @return the resignation that was removed
	 * @throws NoSuchResignationException if a resignation with the primary key could not be found
	 */
	public Resignation remove(long id) throws NoSuchResignationException;

	public Resignation updateImpl(Resignation resignation);

	/**
	 * Returns the resignation with the primary key or throws a <code>NoSuchResignationException</code> if it could not be found.
	 *
	 * @param id the primary key of the resignation
	 * @return the resignation
	 * @throws NoSuchResignationException if a resignation with the primary key could not be found
	 */
	public Resignation findByPrimaryKey(long id)
		throws NoSuchResignationException;

	/**
	 * Returns the resignation with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the resignation
	 * @return the resignation, or <code>null</code> if a resignation with the primary key could not be found
	 */
	public Resignation fetchByPrimaryKey(long id);

	/**
	 * Returns all the resignations.
	 *
	 * @return the resignations
	 */
	public java.util.List<Resignation> findAll();

	/**
	 * Returns a range of all the resignations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ResignationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of resignations
	 * @param end the upper bound of the range of resignations (not inclusive)
	 * @return the range of resignations
	 */
	public java.util.List<Resignation> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the resignations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ResignationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of resignations
	 * @param end the upper bound of the range of resignations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of resignations
	 */
	public java.util.List<Resignation> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Resignation>
			orderByComparator);

	/**
	 * Returns an ordered range of all the resignations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ResignationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of resignations
	 * @param end the upper bound of the range of resignations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of resignations
	 */
	public java.util.List<Resignation> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Resignation>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the resignations from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of resignations.
	 *
	 * @return the number of resignations
	 */
	public int countAll();

}