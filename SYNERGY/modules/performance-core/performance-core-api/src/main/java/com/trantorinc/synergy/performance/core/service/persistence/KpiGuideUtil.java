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

import com.trantorinc.synergy.performance.core.model.KpiGuide;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the kpi guide service. This utility wraps <code>com.trantorinc.synergy.performance.core.service.persistence.impl.KpiGuidePersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see KpiGuidePersistence
 * @generated
 */
public class KpiGuideUtil {

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
	public static void clearCache(KpiGuide kpiGuide) {
		getPersistence().clearCache(kpiGuide);
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
	public static Map<Serializable, KpiGuide> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<KpiGuide> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<KpiGuide> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<KpiGuide> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<KpiGuide> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static KpiGuide update(KpiGuide kpiGuide) {
		return getPersistence().update(kpiGuide);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static KpiGuide update(
		KpiGuide kpiGuide, ServiceContext serviceContext) {

		return getPersistence().update(kpiGuide, serviceContext);
	}

	/**
	 * Caches the kpi guide in the entity cache if it is enabled.
	 *
	 * @param kpiGuide the kpi guide
	 */
	public static void cacheResult(KpiGuide kpiGuide) {
		getPersistence().cacheResult(kpiGuide);
	}

	/**
	 * Caches the kpi guides in the entity cache if it is enabled.
	 *
	 * @param kpiGuides the kpi guides
	 */
	public static void cacheResult(List<KpiGuide> kpiGuides) {
		getPersistence().cacheResult(kpiGuides);
	}

	/**
	 * Creates a new kpi guide with the primary key. Does not add the kpi guide to the database.
	 *
	 * @param guideId the primary key for the new kpi guide
	 * @return the new kpi guide
	 */
	public static KpiGuide create(long guideId) {
		return getPersistence().create(guideId);
	}

	/**
	 * Removes the kpi guide with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param guideId the primary key of the kpi guide
	 * @return the kpi guide that was removed
	 * @throws NoSuchKpiGuideException if a kpi guide with the primary key could not be found
	 */
	public static KpiGuide remove(long guideId)
		throws com.trantorinc.synergy.performance.core.exception.
			NoSuchKpiGuideException {

		return getPersistence().remove(guideId);
	}

	public static KpiGuide updateImpl(KpiGuide kpiGuide) {
		return getPersistence().updateImpl(kpiGuide);
	}

	/**
	 * Returns the kpi guide with the primary key or throws a <code>NoSuchKpiGuideException</code> if it could not be found.
	 *
	 * @param guideId the primary key of the kpi guide
	 * @return the kpi guide
	 * @throws NoSuchKpiGuideException if a kpi guide with the primary key could not be found
	 */
	public static KpiGuide findByPrimaryKey(long guideId)
		throws com.trantorinc.synergy.performance.core.exception.
			NoSuchKpiGuideException {

		return getPersistence().findByPrimaryKey(guideId);
	}

	/**
	 * Returns the kpi guide with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param guideId the primary key of the kpi guide
	 * @return the kpi guide, or <code>null</code> if a kpi guide with the primary key could not be found
	 */
	public static KpiGuide fetchByPrimaryKey(long guideId) {
		return getPersistence().fetchByPrimaryKey(guideId);
	}

	/**
	 * Returns all the kpi guides.
	 *
	 * @return the kpi guides
	 */
	public static List<KpiGuide> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the kpi guides.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>KpiGuideModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of kpi guides
	 * @param end the upper bound of the range of kpi guides (not inclusive)
	 * @return the range of kpi guides
	 */
	public static List<KpiGuide> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the kpi guides.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>KpiGuideModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of kpi guides
	 * @param end the upper bound of the range of kpi guides (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of kpi guides
	 */
	public static List<KpiGuide> findAll(
		int start, int end, OrderByComparator<KpiGuide> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the kpi guides.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>KpiGuideModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of kpi guides
	 * @param end the upper bound of the range of kpi guides (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of kpi guides
	 */
	public static List<KpiGuide> findAll(
		int start, int end, OrderByComparator<KpiGuide> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the kpi guides from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of kpi guides.
	 *
	 * @return the number of kpi guides
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static KpiGuidePersistence getPersistence() {
		return _persistence;
	}

	private static volatile KpiGuidePersistence _persistence;

}