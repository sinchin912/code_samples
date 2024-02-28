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

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.trantorinc.synergy.game.core.model.Santa;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the santa service. This utility wraps <code>com.trantorinc.synergy.game.core.service.persistence.impl.SantaPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SantaPersistence
 * @generated
 */
public class SantaUtil {

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
	public static void clearCache(Santa santa) {
		getPersistence().clearCache(santa);
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
	public static Map<Serializable, Santa> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<Santa> findWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Santa> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Santa> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<Santa> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static Santa update(Santa santa) {
		return getPersistence().update(santa);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static Santa update(Santa santa, ServiceContext serviceContext) {
		return getPersistence().update(santa, serviceContext);
	}

	/**
	 * Caches the santa in the entity cache if it is enabled.
	 *
	 * @param santa the santa
	 */
	public static void cacheResult(Santa santa) {
		getPersistence().cacheResult(santa);
	}

	/**
	 * Caches the santas in the entity cache if it is enabled.
	 *
	 * @param santas the santas
	 */
	public static void cacheResult(List<Santa> santas) {
		getPersistence().cacheResult(santas);
	}

	/**
	 * Creates a new santa with the primary key. Does not add the santa to the database.
	 *
	 * @param santaId the primary key for the new santa
	 * @return the new santa
	 */
	public static Santa create(long santaId) {
		return getPersistence().create(santaId);
	}

	/**
	 * Removes the santa with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param santaId the primary key of the santa
	 * @return the santa that was removed
	 * @throws NoSuchSantaException if a santa with the primary key could not be found
	 */
	public static Santa remove(long santaId)
		throws com.trantorinc.synergy.game.core.exception.NoSuchSantaException {

		return getPersistence().remove(santaId);
	}

	public static Santa updateImpl(Santa santa) {
		return getPersistence().updateImpl(santa);
	}

	/**
	 * Returns the santa with the primary key or throws a <code>NoSuchSantaException</code> if it could not be found.
	 *
	 * @param santaId the primary key of the santa
	 * @return the santa
	 * @throws NoSuchSantaException if a santa with the primary key could not be found
	 */
	public static Santa findByPrimaryKey(long santaId)
		throws com.trantorinc.synergy.game.core.exception.NoSuchSantaException {

		return getPersistence().findByPrimaryKey(santaId);
	}

	/**
	 * Returns the santa with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param santaId the primary key of the santa
	 * @return the santa, or <code>null</code> if a santa with the primary key could not be found
	 */
	public static Santa fetchByPrimaryKey(long santaId) {
		return getPersistence().fetchByPrimaryKey(santaId);
	}

	/**
	 * Returns all the santas.
	 *
	 * @return the santas
	 */
	public static List<Santa> findAll() {
		return getPersistence().findAll();
	}

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
	public static List<Santa> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

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
	public static List<Santa> findAll(
		int start, int end, OrderByComparator<Santa> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

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
	public static List<Santa> findAll(
		int start, int end, OrderByComparator<Santa> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the santas from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of santas.
	 *
	 * @return the number of santas
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static SantaPersistence getPersistence() {
		return _persistence;
	}

	private static volatile SantaPersistence _persistence;

}