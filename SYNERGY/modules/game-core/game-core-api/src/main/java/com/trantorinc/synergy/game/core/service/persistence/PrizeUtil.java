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

import com.trantorinc.synergy.game.core.model.Prize;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the prize service. This utility wraps <code>com.trantorinc.synergy.game.core.service.persistence.impl.PrizePersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PrizePersistence
 * @generated
 */
public class PrizeUtil {

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
	public static void clearCache(Prize prize) {
		getPersistence().clearCache(prize);
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
	public static Map<Serializable, Prize> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<Prize> findWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Prize> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Prize> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<Prize> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static Prize update(Prize prize) {
		return getPersistence().update(prize);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static Prize update(Prize prize, ServiceContext serviceContext) {
		return getPersistence().update(prize, serviceContext);
	}

	/**
	 * Caches the prize in the entity cache if it is enabled.
	 *
	 * @param prize the prize
	 */
	public static void cacheResult(Prize prize) {
		getPersistence().cacheResult(prize);
	}

	/**
	 * Caches the prizes in the entity cache if it is enabled.
	 *
	 * @param prizes the prizes
	 */
	public static void cacheResult(List<Prize> prizes) {
		getPersistence().cacheResult(prizes);
	}

	/**
	 * Creates a new prize with the primary key. Does not add the prize to the database.
	 *
	 * @param prizeId the primary key for the new prize
	 * @return the new prize
	 */
	public static Prize create(long prizeId) {
		return getPersistence().create(prizeId);
	}

	/**
	 * Removes the prize with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param prizeId the primary key of the prize
	 * @return the prize that was removed
	 * @throws NoSuchPrizeException if a prize with the primary key could not be found
	 */
	public static Prize remove(long prizeId)
		throws com.trantorinc.synergy.game.core.exception.NoSuchPrizeException {

		return getPersistence().remove(prizeId);
	}

	public static Prize updateImpl(Prize prize) {
		return getPersistence().updateImpl(prize);
	}

	/**
	 * Returns the prize with the primary key or throws a <code>NoSuchPrizeException</code> if it could not be found.
	 *
	 * @param prizeId the primary key of the prize
	 * @return the prize
	 * @throws NoSuchPrizeException if a prize with the primary key could not be found
	 */
	public static Prize findByPrimaryKey(long prizeId)
		throws com.trantorinc.synergy.game.core.exception.NoSuchPrizeException {

		return getPersistence().findByPrimaryKey(prizeId);
	}

	/**
	 * Returns the prize with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param prizeId the primary key of the prize
	 * @return the prize, or <code>null</code> if a prize with the primary key could not be found
	 */
	public static Prize fetchByPrimaryKey(long prizeId) {
		return getPersistence().fetchByPrimaryKey(prizeId);
	}

	/**
	 * Returns all the prizes.
	 *
	 * @return the prizes
	 */
	public static List<Prize> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the prizes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PrizeModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of prizes
	 * @param end the upper bound of the range of prizes (not inclusive)
	 * @return the range of prizes
	 */
	public static List<Prize> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the prizes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PrizeModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of prizes
	 * @param end the upper bound of the range of prizes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of prizes
	 */
	public static List<Prize> findAll(
		int start, int end, OrderByComparator<Prize> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the prizes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PrizeModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of prizes
	 * @param end the upper bound of the range of prizes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of prizes
	 */
	public static List<Prize> findAll(
		int start, int end, OrderByComparator<Prize> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the prizes from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of prizes.
	 *
	 * @return the number of prizes
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static PrizePersistence getPersistence() {
		return _persistence;
	}

	private static volatile PrizePersistence _persistence;

}