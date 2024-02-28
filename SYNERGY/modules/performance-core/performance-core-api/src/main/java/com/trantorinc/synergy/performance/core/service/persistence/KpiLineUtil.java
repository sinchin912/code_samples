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

import com.trantorinc.synergy.performance.core.model.KpiLine;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the kpi line service. This utility wraps <code>com.trantorinc.synergy.performance.core.service.persistence.impl.KpiLinePersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see KpiLinePersistence
 * @generated
 */
public class KpiLineUtil {

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
	public static void clearCache(KpiLine kpiLine) {
		getPersistence().clearCache(kpiLine);
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
	public static Map<Serializable, KpiLine> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<KpiLine> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<KpiLine> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<KpiLine> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<KpiLine> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static KpiLine update(KpiLine kpiLine) {
		return getPersistence().update(kpiLine);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static KpiLine update(
		KpiLine kpiLine, ServiceContext serviceContext) {

		return getPersistence().update(kpiLine, serviceContext);
	}

	/**
	 * Caches the kpi line in the entity cache if it is enabled.
	 *
	 * @param kpiLine the kpi line
	 */
	public static void cacheResult(KpiLine kpiLine) {
		getPersistence().cacheResult(kpiLine);
	}

	/**
	 * Caches the kpi lines in the entity cache if it is enabled.
	 *
	 * @param kpiLines the kpi lines
	 */
	public static void cacheResult(List<KpiLine> kpiLines) {
		getPersistence().cacheResult(kpiLines);
	}

	/**
	 * Creates a new kpi line with the primary key. Does not add the kpi line to the database.
	 *
	 * @param lineId the primary key for the new kpi line
	 * @return the new kpi line
	 */
	public static KpiLine create(long lineId) {
		return getPersistence().create(lineId);
	}

	/**
	 * Removes the kpi line with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param lineId the primary key of the kpi line
	 * @return the kpi line that was removed
	 * @throws NoSuchKpiLineException if a kpi line with the primary key could not be found
	 */
	public static KpiLine remove(long lineId)
		throws com.trantorinc.synergy.performance.core.exception.
			NoSuchKpiLineException {

		return getPersistence().remove(lineId);
	}

	public static KpiLine updateImpl(KpiLine kpiLine) {
		return getPersistence().updateImpl(kpiLine);
	}

	/**
	 * Returns the kpi line with the primary key or throws a <code>NoSuchKpiLineException</code> if it could not be found.
	 *
	 * @param lineId the primary key of the kpi line
	 * @return the kpi line
	 * @throws NoSuchKpiLineException if a kpi line with the primary key could not be found
	 */
	public static KpiLine findByPrimaryKey(long lineId)
		throws com.trantorinc.synergy.performance.core.exception.
			NoSuchKpiLineException {

		return getPersistence().findByPrimaryKey(lineId);
	}

	/**
	 * Returns the kpi line with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param lineId the primary key of the kpi line
	 * @return the kpi line, or <code>null</code> if a kpi line with the primary key could not be found
	 */
	public static KpiLine fetchByPrimaryKey(long lineId) {
		return getPersistence().fetchByPrimaryKey(lineId);
	}

	/**
	 * Returns all the kpi lines.
	 *
	 * @return the kpi lines
	 */
	public static List<KpiLine> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the kpi lines.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>KpiLineModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of kpi lines
	 * @param end the upper bound of the range of kpi lines (not inclusive)
	 * @return the range of kpi lines
	 */
	public static List<KpiLine> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the kpi lines.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>KpiLineModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of kpi lines
	 * @param end the upper bound of the range of kpi lines (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of kpi lines
	 */
	public static List<KpiLine> findAll(
		int start, int end, OrderByComparator<KpiLine> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the kpi lines.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>KpiLineModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of kpi lines
	 * @param end the upper bound of the range of kpi lines (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of kpi lines
	 */
	public static List<KpiLine> findAll(
		int start, int end, OrderByComparator<KpiLine> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the kpi lines from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of kpi lines.
	 *
	 * @return the number of kpi lines
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static KpiLinePersistence getPersistence() {
		return _persistence;
	}

	private static volatile KpiLinePersistence _persistence;

}