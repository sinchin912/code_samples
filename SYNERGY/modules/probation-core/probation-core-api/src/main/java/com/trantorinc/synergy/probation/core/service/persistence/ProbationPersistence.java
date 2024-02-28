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

package com.trantorinc.synergy.probation.core.service.persistence;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import com.trantorinc.synergy.probation.core.exception.NoSuchProbationException;
import com.trantorinc.synergy.probation.core.model.Probation;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the probation service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ProbationUtil
 * @generated
 */
@ProviderType
public interface ProbationPersistence extends BasePersistence<Probation> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ProbationUtil} to access the probation persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Caches the probation in the entity cache if it is enabled.
	 *
	 * @param probation the probation
	 */
	public void cacheResult(Probation probation);

	/**
	 * Caches the probations in the entity cache if it is enabled.
	 *
	 * @param probations the probations
	 */
	public void cacheResult(java.util.List<Probation> probations);

	/**
	 * Creates a new probation with the primary key. Does not add the probation to the database.
	 *
	 * @param ecode the primary key for the new probation
	 * @return the new probation
	 */
	public Probation create(String ecode);

	/**
	 * Removes the probation with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param ecode the primary key of the probation
	 * @return the probation that was removed
	 * @throws NoSuchProbationException if a probation with the primary key could not be found
	 */
	public Probation remove(String ecode) throws NoSuchProbationException;

	public Probation updateImpl(Probation probation);

	/**
	 * Returns the probation with the primary key or throws a <code>NoSuchProbationException</code> if it could not be found.
	 *
	 * @param ecode the primary key of the probation
	 * @return the probation
	 * @throws NoSuchProbationException if a probation with the primary key could not be found
	 */
	public Probation findByPrimaryKey(String ecode)
		throws NoSuchProbationException;

	/**
	 * Returns the probation with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param ecode the primary key of the probation
	 * @return the probation, or <code>null</code> if a probation with the primary key could not be found
	 */
	public Probation fetchByPrimaryKey(String ecode);

	/**
	 * Returns all the probations.
	 *
	 * @return the probations
	 */
	public java.util.List<Probation> findAll();

	/**
	 * Returns a range of all the probations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProbationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of probations
	 * @param end the upper bound of the range of probations (not inclusive)
	 * @return the range of probations
	 */
	public java.util.List<Probation> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the probations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProbationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of probations
	 * @param end the upper bound of the range of probations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of probations
	 */
	public java.util.List<Probation> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Probation>
			orderByComparator);

	/**
	 * Returns an ordered range of all the probations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProbationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of probations
	 * @param end the upper bound of the range of probations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of probations
	 */
	public java.util.List<Probation> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Probation>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the probations from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of probations.
	 *
	 * @return the number of probations
	 */
	public int countAll();

}