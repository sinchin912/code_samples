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

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import com.trantorinc.synergy.performance.core.exception.NoSuchKpiException;
import com.trantorinc.synergy.performance.core.model.Kpi;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the kpi service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see KpiUtil
 * @generated
 */
@ProviderType
public interface KpiPersistence extends BasePersistence<Kpi> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link KpiUtil} to access the kpi persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Caches the kpi in the entity cache if it is enabled.
	 *
	 * @param kpi the kpi
	 */
	public void cacheResult(Kpi kpi);

	/**
	 * Caches the kpis in the entity cache if it is enabled.
	 *
	 * @param kpis the kpis
	 */
	public void cacheResult(java.util.List<Kpi> kpis);

	/**
	 * Creates a new kpi with the primary key. Does not add the kpi to the database.
	 *
	 * @param kpiId the primary key for the new kpi
	 * @return the new kpi
	 */
	public Kpi create(long kpiId);

	/**
	 * Removes the kpi with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param kpiId the primary key of the kpi
	 * @return the kpi that was removed
	 * @throws NoSuchKpiException if a kpi with the primary key could not be found
	 */
	public Kpi remove(long kpiId) throws NoSuchKpiException;

	public Kpi updateImpl(Kpi kpi);

	/**
	 * Returns the kpi with the primary key or throws a <code>NoSuchKpiException</code> if it could not be found.
	 *
	 * @param kpiId the primary key of the kpi
	 * @return the kpi
	 * @throws NoSuchKpiException if a kpi with the primary key could not be found
	 */
	public Kpi findByPrimaryKey(long kpiId) throws NoSuchKpiException;

	/**
	 * Returns the kpi with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param kpiId the primary key of the kpi
	 * @return the kpi, or <code>null</code> if a kpi with the primary key could not be found
	 */
	public Kpi fetchByPrimaryKey(long kpiId);

	/**
	 * Returns all the kpis.
	 *
	 * @return the kpis
	 */
	public java.util.List<Kpi> findAll();

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
	public java.util.List<Kpi> findAll(int start, int end);

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
	public java.util.List<Kpi> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Kpi>
			orderByComparator);

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
	public java.util.List<Kpi> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Kpi> orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the kpis from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of kpis.
	 *
	 * @return the number of kpis
	 */
	public int countAll();

}