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

package com.trantorinc.synergy.game.core.service.persistence;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import com.trantorinc.synergy.game.core.exception.NoSuchSantaException;
import com.trantorinc.synergy.game.core.model.Santa;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the santa service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SantaUtil
 * @generated
 */
@ProviderType
public interface SantaPersistence extends BasePersistence<Santa> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link SantaUtil} to access the santa persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Caches the santa in the entity cache if it is enabled.
	 *
	 * @param santa the santa
	 */
	public void cacheResult(Santa santa);

	/**
	 * Caches the santas in the entity cache if it is enabled.
	 *
	 * @param santas the santas
	 */
	public void cacheResult(java.util.List<Santa> santas);

	/**
	 * Creates a new santa with the primary key. Does not add the santa to the database.
	 *
	 * @param santaId the primary key for the new santa
	 * @return the new santa
	 */
	public Santa create(long santaId);

	/**
	 * Removes the santa with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param santaId the primary key of the santa
	 * @return the santa that was removed
	 * @throws NoSuchSantaException if a santa with the primary key could not be found
	 */
	public Santa remove(long santaId) throws NoSuchSantaException;

	public Santa updateImpl(Santa santa);

	/**
	 * Returns the santa with the primary key or throws a <code>NoSuchSantaException</code> if it could not be found.
	 *
	 * @param santaId the primary key of the santa
	 * @return the santa
	 * @throws NoSuchSantaException if a santa with the primary key could not be found
	 */
	public Santa findByPrimaryKey(long santaId) throws NoSuchSantaException;

	/**
	 * Returns the santa with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param santaId the primary key of the santa
	 * @return the santa, or <code>null</code> if a santa with the primary key could not be found
	 */
	public Santa fetchByPrimaryKey(long santaId);

	/**
	 * Returns all the santas.
	 *
	 * @return the santas
	 */
	public java.util.List<Santa> findAll();

	/**
	 * Returns a range of all the santas.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SantaModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of santas
	 * @param end the upper bound of the range of santas (not inclusive)
	 * @return the range of santas
	 */
	public java.util.List<Santa> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the santas.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SantaModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of santas
	 * @param end the upper bound of the range of santas (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of santas
	 */
	public java.util.List<Santa> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Santa>
			orderByComparator);

	/**
	 * Returns an ordered range of all the santas.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SantaModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of santas
	 * @param end the upper bound of the range of santas (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of santas
	 */
	public java.util.List<Santa> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Santa>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the santas from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of santas.
	 *
	 * @return the number of santas
	 */
	public int countAll();

}