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

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.trantorinc.synergy.lms.core.model.Scheduler;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the scheduler service. This utility wraps <code>com.trantorinc.synergy.lms.core.service.persistence.impl.SchedulerPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SchedulerPersistence
 * @generated
 */
public class SchedulerUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(Scheduler scheduler) {
		getPersistence().clearCache(scheduler);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#fetchByPrimaryKeys(Set)
	 */
	public static Map<Serializable, Scheduler> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<Scheduler> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Scheduler> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Scheduler> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<Scheduler> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static Scheduler update(Scheduler scheduler) {
		return getPersistence().update(scheduler);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static Scheduler update(
		Scheduler scheduler, ServiceContext serviceContext) {

		return getPersistence().update(scheduler, serviceContext);
	}

	/**
	 * Caches the scheduler in the entity cache if it is enabled.
	 *
	 * @param scheduler the scheduler
	 */
	public static void cacheResult(Scheduler scheduler) {
		getPersistence().cacheResult(scheduler);
	}

	/**
	 * Caches the schedulers in the entity cache if it is enabled.
	 *
	 * @param schedulers the schedulers
	 */
	public static void cacheResult(List<Scheduler> schedulers) {
		getPersistence().cacheResult(schedulers);
	}

	/**
	 * Creates a new scheduler with the primary key. Does not add the scheduler to the database.
	 *
	 * @param schedulerId the primary key for the new scheduler
	 * @return the new scheduler
	 */
	public static Scheduler create(long schedulerId) {
		return getPersistence().create(schedulerId);
	}

	/**
	 * Removes the scheduler with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param schedulerId the primary key of the scheduler
	 * @return the scheduler that was removed
	 * @throws NoSuchSchedulerException if a scheduler with the primary key could not be found
	 */
	public static Scheduler remove(long schedulerId)
		throws com.trantorinc.synergy.lms.core.exception.
			NoSuchSchedulerException {

		return getPersistence().remove(schedulerId);
	}

	public static Scheduler updateImpl(Scheduler scheduler) {
		return getPersistence().updateImpl(scheduler);
	}

	/**
	 * Returns the scheduler with the primary key or throws a <code>NoSuchSchedulerException</code> if it could not be found.
	 *
	 * @param schedulerId the primary key of the scheduler
	 * @return the scheduler
	 * @throws NoSuchSchedulerException if a scheduler with the primary key could not be found
	 */
	public static Scheduler findByPrimaryKey(long schedulerId)
		throws com.trantorinc.synergy.lms.core.exception.
			NoSuchSchedulerException {

		return getPersistence().findByPrimaryKey(schedulerId);
	}

	/**
	 * Returns the scheduler with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param schedulerId the primary key of the scheduler
	 * @return the scheduler, or <code>null</code> if a scheduler with the primary key could not be found
	 */
	public static Scheduler fetchByPrimaryKey(long schedulerId) {
		return getPersistence().fetchByPrimaryKey(schedulerId);
	}

	/**
	 * Returns all the schedulers.
	 *
	 * @return the schedulers
	 */
	public static List<Scheduler> findAll() {
		return getPersistence().findAll();
	}

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
	public static List<Scheduler> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

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
	public static List<Scheduler> findAll(
		int start, int end, OrderByComparator<Scheduler> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

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
	public static List<Scheduler> findAll(
		int start, int end, OrderByComparator<Scheduler> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the schedulers from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of schedulers.
	 *
	 * @return the number of schedulers
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static SchedulerPersistence getPersistence() {
		return _persistence;
	}

	private static volatile SchedulerPersistence _persistence;

}