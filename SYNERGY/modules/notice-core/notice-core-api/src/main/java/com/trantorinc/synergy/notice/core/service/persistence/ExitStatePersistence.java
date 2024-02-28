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

import com.trantorinc.synergy.notice.core.exception.NoSuchExitStateException;
import com.trantorinc.synergy.notice.core.model.ExitState;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the exit state service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ExitStateUtil
 * @generated
 */
@ProviderType
public interface ExitStatePersistence extends BasePersistence<ExitState> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ExitStateUtil} to access the exit state persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Caches the exit state in the entity cache if it is enabled.
	 *
	 * @param exitState the exit state
	 */
	public void cacheResult(ExitState exitState);

	/**
	 * Caches the exit states in the entity cache if it is enabled.
	 *
	 * @param exitStates the exit states
	 */
	public void cacheResult(java.util.List<ExitState> exitStates);

	/**
	 * Creates a new exit state with the primary key. Does not add the exit state to the database.
	 *
	 * @param exitStateId the primary key for the new exit state
	 * @return the new exit state
	 */
	public ExitState create(long exitStateId);

	/**
	 * Removes the exit state with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param exitStateId the primary key of the exit state
	 * @return the exit state that was removed
	 * @throws NoSuchExitStateException if a exit state with the primary key could not be found
	 */
	public ExitState remove(long exitStateId) throws NoSuchExitStateException;

	public ExitState updateImpl(ExitState exitState);

	/**
	 * Returns the exit state with the primary key or throws a <code>NoSuchExitStateException</code> if it could not be found.
	 *
	 * @param exitStateId the primary key of the exit state
	 * @return the exit state
	 * @throws NoSuchExitStateException if a exit state with the primary key could not be found
	 */
	public ExitState findByPrimaryKey(long exitStateId)
		throws NoSuchExitStateException;

	/**
	 * Returns the exit state with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param exitStateId the primary key of the exit state
	 * @return the exit state, or <code>null</code> if a exit state with the primary key could not be found
	 */
	public ExitState fetchByPrimaryKey(long exitStateId);

	/**
	 * Returns all the exit states.
	 *
	 * @return the exit states
	 */
	public java.util.List<ExitState> findAll();

	/**
	 * Returns a range of all the exit states.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ExitStateModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of exit states
	 * @param end the upper bound of the range of exit states (not inclusive)
	 * @return the range of exit states
	 */
	public java.util.List<ExitState> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the exit states.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ExitStateModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of exit states
	 * @param end the upper bound of the range of exit states (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of exit states
	 */
	public java.util.List<ExitState> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ExitState>
			orderByComparator);

	/**
	 * Returns an ordered range of all the exit states.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ExitStateModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of exit states
	 * @param end the upper bound of the range of exit states (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of exit states
	 */
	public java.util.List<ExitState> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ExitState>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the exit states from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of exit states.
	 *
	 * @return the number of exit states
	 */
	public int countAll();

}