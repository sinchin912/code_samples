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

import com.trantorinc.synergy.performance.core.exception.NoSuchKpiLineException;
import com.trantorinc.synergy.performance.core.model.KpiLine;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the kpi line service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see KpiLineUtil
 * @generated
 */
@ProviderType
public interface KpiLinePersistence extends BasePersistence<KpiLine> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link KpiLineUtil} to access the kpi line persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Caches the kpi line in the entity cache if it is enabled.
	 *
	 * @param kpiLine the kpi line
	 */
	public void cacheResult(KpiLine kpiLine);

	/**
	 * Caches the kpi lines in the entity cache if it is enabled.
	 *
	 * @param kpiLines the kpi lines
	 */
	public void cacheResult(java.util.List<KpiLine> kpiLines);

	/**
	 * Creates a new kpi line with the primary key. Does not add the kpi line to the database.
	 *
	 * @param lineId the primary key for the new kpi line
	 * @return the new kpi line
	 */
	public KpiLine create(long lineId);

	/**
	 * Removes the kpi line with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param lineId the primary key of the kpi line
	 * @return the kpi line that was removed
	 * @throws NoSuchKpiLineException if a kpi line with the primary key could not be found
	 */
	public KpiLine remove(long lineId) throws NoSuchKpiLineException;

	public KpiLine updateImpl(KpiLine kpiLine);

	/**
	 * Returns the kpi line with the primary key or throws a <code>NoSuchKpiLineException</code> if it could not be found.
	 *
	 * @param lineId the primary key of the kpi line
	 * @return the kpi line
	 * @throws NoSuchKpiLineException if a kpi line with the primary key could not be found
	 */
	public KpiLine findByPrimaryKey(long lineId) throws NoSuchKpiLineException;

	/**
	 * Returns the kpi line with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param lineId the primary key of the kpi line
	 * @return the kpi line, or <code>null</code> if a kpi line with the primary key could not be found
	 */
	public KpiLine fetchByPrimaryKey(long lineId);

	/**
	 * Returns all the kpi lines.
	 *
	 * @return the kpi lines
	 */
	public java.util.List<KpiLine> findAll();

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
	public java.util.List<KpiLine> findAll(int start, int end);

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
	public java.util.List<KpiLine> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<KpiLine>
			orderByComparator);

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
	public java.util.List<KpiLine> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<KpiLine>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the kpi lines from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of kpi lines.
	 *
	 * @return the number of kpi lines
	 */
	public int countAll();

}