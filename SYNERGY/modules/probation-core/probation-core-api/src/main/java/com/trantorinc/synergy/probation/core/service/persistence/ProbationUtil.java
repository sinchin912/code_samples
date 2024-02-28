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

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.trantorinc.synergy.probation.core.model.Probation;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the probation service. This utility wraps <code>com.trantorinc.synergy.probation.core.service.persistence.impl.ProbationPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ProbationPersistence
 * @generated
 */
public class ProbationUtil {

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
	public static void clearCache(Probation probation) {
		getPersistence().clearCache(probation);
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
	public static Map<Serializable, Probation> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<Probation> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Probation> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Probation> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<Probation> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static Probation update(Probation probation) {
		return getPersistence().update(probation);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static Probation update(
		Probation probation, ServiceContext serviceContext) {

		return getPersistence().update(probation, serviceContext);
	}

	/**
	 * Caches the probation in the entity cache if it is enabled.
	 *
	 * @param probation the probation
	 */
	public static void cacheResult(Probation probation) {
		getPersistence().cacheResult(probation);
	}

	/**
	 * Caches the probations in the entity cache if it is enabled.
	 *
	 * @param probations the probations
	 */
	public static void cacheResult(List<Probation> probations) {
		getPersistence().cacheResult(probations);
	}

	/**
	 * Creates a new probation with the primary key. Does not add the probation to the database.
	 *
	 * @param ecode the primary key for the new probation
	 * @return the new probation
	 */
	public static Probation create(String ecode) {
		return getPersistence().create(ecode);
	}

	/**
	 * Removes the probation with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param ecode the primary key of the probation
	 * @return the probation that was removed
	 * @throws NoSuchProbationException if a probation with the primary key could not be found
	 */
	public static Probation remove(String ecode)
		throws com.trantorinc.synergy.probation.core.exception.
			NoSuchProbationException {

		return getPersistence().remove(ecode);
	}

	public static Probation updateImpl(Probation probation) {
		return getPersistence().updateImpl(probation);
	}

	/**
	 * Returns the probation with the primary key or throws a <code>NoSuchProbationException</code> if it could not be found.
	 *
	 * @param ecode the primary key of the probation
	 * @return the probation
	 * @throws NoSuchProbationException if a probation with the primary key could not be found
	 */
	public static Probation findByPrimaryKey(String ecode)
		throws com.trantorinc.synergy.probation.core.exception.
			NoSuchProbationException {

		return getPersistence().findByPrimaryKey(ecode);
	}

	/**
	 * Returns the probation with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param ecode the primary key of the probation
	 * @return the probation, or <code>null</code> if a probation with the primary key could not be found
	 */
	public static Probation fetchByPrimaryKey(String ecode) {
		return getPersistence().fetchByPrimaryKey(ecode);
	}

	/**
	 * Returns all the probations.
	 *
	 * @return the probations
	 */
	public static List<Probation> findAll() {
		return getPersistence().findAll();
	}

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
	public static List<Probation> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

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
	public static List<Probation> findAll(
		int start, int end, OrderByComparator<Probation> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

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
	public static List<Probation> findAll(
		int start, int end, OrderByComparator<Probation> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the probations from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of probations.
	 *
	 * @return the number of probations
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static ProbationPersistence getPersistence() {
		return _persistence;
	}

	private static volatile ProbationPersistence _persistence;

}