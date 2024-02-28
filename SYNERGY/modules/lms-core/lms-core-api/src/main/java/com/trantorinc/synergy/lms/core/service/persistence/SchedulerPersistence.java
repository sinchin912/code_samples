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

package com.trantorinc.synergy.lms.core.service.persistence;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import com.trantorinc.synergy.lms.core.exception.NoSuchSchedulerException;
import com.trantorinc.synergy.lms.core.model.Scheduler;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the scheduler service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SchedulerUtil
 * @generated
 */
@ProviderType
public interface SchedulerPersistence extends BasePersistence<Scheduler> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link SchedulerUtil} to access the scheduler persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Caches the scheduler in the entity cache if it is enabled.
	 *
	 * @param scheduler the scheduler
	 */
	public void cacheResult(Scheduler scheduler);

	/**
	 * Caches the schedulers in the entity cache if it is enabled.
	 *
	 * @param schedulers the schedulers
	 */
	public void cacheResult(java.util.List<Scheduler> schedulers);

	/**
	 * Creates a new scheduler with the primary key. Does not add the scheduler to the database.
	 *
	 * @param schedulerId the primary key for the new scheduler
	 * @return the new scheduler
	 */
	public Scheduler create(long schedulerId);

	/**
	 * Removes the scheduler with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param schedulerId the primary key of the scheduler
	 * @return the scheduler that was removed
	 * @throws NoSuchSchedulerException if a scheduler with the primary key could not be found
	 */
	public Scheduler remove(long schedulerId) throws NoSuchSchedulerException;

	public Scheduler updateImpl(Scheduler scheduler);

	/**
	 * Returns the scheduler with the primary key or throws a <code>NoSuchSchedulerException</code> if it could not be found.
	 *
	 * @param schedulerId the primary key of the scheduler
	 * @return the scheduler
	 * @throws NoSuchSchedulerException if a scheduler with the primary key could not be found
	 */
	public Scheduler findByPrimaryKey(long schedulerId)
		throws NoSuchSchedulerException;

	/**
	 * Returns the scheduler with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param schedulerId the primary key of the scheduler
	 * @return the scheduler, or <code>null</code> if a scheduler with the primary key could not be found
	 */
	public Scheduler fetchByPrimaryKey(long schedulerId);

	/**
	 * Returns all the schedulers.
	 *
	 * @return the schedulers
	 */
	public java.util.List<Scheduler> findAll();

	/**
	 * Returns a range of all the schedulers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SchedulerModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of schedulers
	 * @param end the upper bound of the range of schedulers (not inclusive)
	 * @return the range of schedulers
	 */
	public java.util.List<Scheduler> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the schedulers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SchedulerModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of schedulers
	 * @param end the upper bound of the range of schedulers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of schedulers
	 */
	public java.util.List<Scheduler> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Scheduler>
			orderByComparator);

	/**
	 * Returns an ordered range of all the schedulers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SchedulerModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of schedulers
	 * @param end the upper bound of the range of schedulers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of schedulers
	 */
	public java.util.List<Scheduler> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Scheduler>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the schedulers from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of schedulers.
	 *
	 * @return the number of schedulers
	 */
	public int countAll();

}