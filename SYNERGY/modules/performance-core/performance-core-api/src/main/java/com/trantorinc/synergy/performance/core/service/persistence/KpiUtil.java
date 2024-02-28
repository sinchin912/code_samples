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

package com.trantorinc.synergy.performance.core.service.persistence;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.trantorinc.synergy.performance.core.model.Kpi;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the kpi service. This utility wraps <code>com.trantorinc.synergy.performance.core.service.persistence.impl.KpiPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see KpiPersistence
 * @generated
 */
public class KpiUtil {

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
	public static void clearCache(Kpi kpi) {
		getPersistence().clearCache(kpi);
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
	public static Map<Serializable, Kpi> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<Kpi> findWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Kpi> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Kpi> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<Kpi> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static Kpi update(Kpi kpi) {
		return getPersistence().update(kpi);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static Kpi update(Kpi kpi, ServiceContext serviceContext) {
		return getPersistence().update(kpi, serviceContext);
	}

	/**
	 * Caches the kpi in the entity cache if it is enabled.
	 *
	 * @param kpi the kpi
	 */
	public static void cacheResult(Kpi kpi) {
		getPersistence().cacheResult(kpi);
	}

	/**
	 * Caches the kpis in the entity cache if it is enabled.
	 *
	 * @param kpis the kpis
	 */
	public static void cacheResult(List<Kpi> kpis) {
		getPersistence().cacheResult(kpis);
	}

	/**
	 * Creates a new kpi with the primary key. Does not add the kpi to the database.
	 *
	 * @param kpiId the primary key for the new kpi
	 * @return the new kpi
	 */
	public static Kpi create(long kpiId) {
		return getPersistence().create(kpiId);
	}

	/**
	 * Removes the kpi with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param kpiId the primary key of the kpi
	 * @return the kpi that was removed
	 * @throws NoSuchKpiException if a kpi with the primary key could not be found
	 */
	public static Kpi remove(long kpiId)
		throws com.trantorinc.synergy.performance.core.exception.
			NoSuchKpiException {

		return getPersistence().remove(kpiId);
	}

	public static Kpi updateImpl(Kpi kpi) {
		return getPersistence().updateImpl(kpi);
	}

	/**
	 * Returns the kpi with the primary key or throws a <code>NoSuchKpiException</code> if it could not be found.
	 *
	 * @param kpiId the primary key of the kpi
	 * @return the kpi
	 * @throws NoSuchKpiException if a kpi with the primary key could not be found
	 */
	public static Kpi findByPrimaryKey(long kpiId)
		throws com.trantorinc.synergy.performance.core.exception.
			NoSuchKpiException {

		return getPersistence().findByPrimaryKey(kpiId);
	}

	/**
	 * Returns the kpi with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param kpiId the primary key of the kpi
	 * @return the kpi, or <code>null</code> if a kpi with the primary key could not be found
	 */
	public static Kpi fetchByPrimaryKey(long kpiId) {
		return getPersistence().fetchByPrimaryKey(kpiId);
	}

	/**
	 * Returns all the kpis.
	 *
	 * @return the kpis
	 */
	public static List<Kpi> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the kpis.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>KpiModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of kpis
	 * @param end the upper bound of the range of kpis (not inclusive)
	 * @return the range of kpis
	 */
	public static List<Kpi> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the kpis.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>KpiModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of kpis
	 * @param end the upper bound of the range of kpis (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of kpis
	 */
	public static List<Kpi> findAll(
		int start, int end, OrderByComparator<Kpi> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the kpis.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>KpiModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of kpis
	 * @param end the upper bound of the range of kpis (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of kpis
	 */
	public static List<Kpi> findAll(
		int start, int end, OrderByComparator<Kpi> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the kpis from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of kpis.
	 *
	 * @return the number of kpis
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static KpiPersistence getPersistence() {
		return _persistence;
	}

	private static volatile KpiPersistence _persistence;

}