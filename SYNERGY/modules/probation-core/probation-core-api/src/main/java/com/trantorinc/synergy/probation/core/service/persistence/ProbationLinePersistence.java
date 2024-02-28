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

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import com.trantorinc.synergy.probation.core.exception.NoSuchProbationLineException;
import com.trantorinc.synergy.probation.core.model.ProbationLine;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the probation line service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ProbationLineUtil
 * @generated
 */
@ProviderType
public interface ProbationLinePersistence
	extends BasePersistence<ProbationLine> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ProbationLineUtil} to access the probation line persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Caches the probation line in the entity cache if it is enabled.
	 *
	 * @param probationLine the probation line
	 */
	public void cacheResult(ProbationLine probationLine);

	/**
	 * Caches the probation lines in the entity cache if it is enabled.
	 *
	 * @param probationLines the probation lines
	 */
	public void cacheResult(java.util.List<ProbationLine> probationLines);

	/**
	 * Creates a new probation line with the primary key. Does not add the probation line to the database.
	 *
	 * @param lineId the primary key for the new probation line
	 * @return the new probation line
	 */
	public ProbationLine create(long lineId);

	/**
	 * Removes the probation line with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param lineId the primary key of the probation line
	 * @return the probation line that was removed
	 * @throws NoSuchProbationLineException if a probation line with the primary key could not be found
	 */
	public ProbationLine remove(long lineId)
		throws NoSuchProbationLineException;

	public ProbationLine updateImpl(ProbationLine probationLine);

	/**
	 * Returns the probation line with the primary key or throws a <code>NoSuchProbationLineException</code> if it could not be found.
	 *
	 * @param lineId the primary key of the probation line
	 * @return the probation line
	 * @throws NoSuchProbationLineException if a probation line with the primary key could not be found
	 */
	public ProbationLine findByPrimaryKey(long lineId)
		throws NoSuchProbationLineException;

	/**
	 * Returns the probation line with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param lineId the primary key of the probation line
	 * @return the probation line, or <code>null</code> if a probation line with the primary key could not be found
	 */
	public ProbationLine fetchByPrimaryKey(long lineId);

	/**
	 * Returns all the probation lines.
	 *
	 * @return the probation lines
	 */
	public java.util.List<ProbationLine> findAll();

	/**
	 * Returns a range of all the probation lines.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProbationLineModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of probation lines
	 * @param end the upper bound of the range of probation lines (not inclusive)
	 * @return the range of probation lines
	 */
	public java.util.List<ProbationLine> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the probation lines.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProbationLineModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of probation lines
	 * @param end the upper bound of the range of probation lines (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of probation lines
	 */
	public java.util.List<ProbationLine> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProbationLine>
			orderByComparator);

	/**
	 * Returns an ordered range of all the probation lines.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProbationLineModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of probation lines
	 * @param end the upper bound of the range of probation lines (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of probation lines
	 */
	public java.util.List<ProbationLine> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProbationLine>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the probation lines from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of probation lines.
	 *
	 * @return the number of probation lines
	 */
	public int countAll();

}