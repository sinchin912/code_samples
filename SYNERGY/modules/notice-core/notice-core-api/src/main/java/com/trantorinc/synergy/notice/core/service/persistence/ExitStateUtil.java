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

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.trantorinc.synergy.notice.core.model.ExitState;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the exit state service. This utility wraps <code>com.trantorinc.synergy.notice.core.service.persistence.impl.ExitStatePersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ExitStatePersistence
 * @generated
 */
public class ExitStateUtil {

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
	public static void clearCache(ExitState exitState) {
		getPersistence().clearCache(exitState);
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
	public static Map<Serializable, ExitState> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<ExitState> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ExitState> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ExitState> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<ExitState> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static ExitState update(ExitState exitState) {
		return getPersistence().update(exitState);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static ExitState update(
		ExitState exitState, ServiceContext serviceContext) {

		return getPersistence().update(exitState, serviceContext);
	}

	/**
	 * Caches the exit state in the entity cache if it is enabled.
	 *
	 * @param exitState the exit state
	 */
	public static void cacheResult(ExitState exitState) {
		getPersistence().cacheResult(exitState);
	}

	/**
	 * Caches the exit states in the entity cache if it is enabled.
	 *
	 * @param exitStates the exit states
	 */
	public static void cacheResult(List<ExitState> exitStates) {
		getPersistence().cacheResult(exitStates);
	}

	/**
	 * Creates a new exit state with the primary key. Does not add the exit state to the database.
	 *
	 * @param exitStateId the primary key for the new exit state
	 * @return the new exit state
	 */
	public static ExitState create(long exitStateId) {
		return getPersistence().create(exitStateId);
	}

	/**
	 * Removes the exit state with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param exitStateId the primary key of the exit state
	 * @return the exit state that was removed
	 * @throws NoSuchExitStateException if a exit state with the primary key could not be found
	 */
	public static ExitState remove(long exitStateId)
		throws com.trantorinc.synergy.notice.core.exception.
			NoSuchExitStateException {

		return getPersistence().remove(exitStateId);
	}

	public static ExitState updateImpl(ExitState exitState) {
		return getPersistence().updateImpl(exitState);
	}

	/**
	 * Returns the exit state with the primary key or throws a <code>NoSuchExitStateException</code> if it could not be found.
	 *
	 * @param exitStateId the primary key of the exit state
	 * @return the exit state
	 * @throws NoSuchExitStateException if a exit state with the primary key could not be found
	 */
	public static ExitState findByPrimaryKey(long exitStateId)
		throws com.trantorinc.synergy.notice.core.exception.
			NoSuchExitStateException {

		return getPersistence().findByPrimaryKey(exitStateId);
	}

	/**
	 * Returns the exit state with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param exitStateId the primary key of the exit state
	 * @return the exit state, or <code>null</code> if a exit state with the primary key could not be found
	 */
	public static ExitState fetchByPrimaryKey(long exitStateId) {
		return getPersistence().fetchByPrimaryKey(exitStateId);
	}

	/**
	 * Returns all the exit states.
	 *
	 * @return the exit states
	 */
	public static List<ExitState> findAll() {
		return getPersistence().findAll();
	}

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
	public static List<ExitState> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

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
	public static List<ExitState> findAll(
		int start, int end, OrderByComparator<ExitState> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

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
	public static List<ExitState> findAll(
		int start, int end, OrderByComparator<ExitState> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the exit states from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of exit states.
	 *
	 * @return the number of exit states
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static ExitStatePersistence getPersistence() {
		return _persistence;
	}

	private static volatile ExitStatePersistence _persistence;

}