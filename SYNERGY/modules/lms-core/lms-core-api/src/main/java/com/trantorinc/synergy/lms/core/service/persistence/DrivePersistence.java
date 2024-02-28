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

package com.trantorinc.synergy.lms.core.service.persistence;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import com.trantorinc.synergy.lms.core.exception.NoSuchDriveException;
import com.trantorinc.synergy.lms.core.model.Drive;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the drive service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DriveUtil
 * @generated
 */
@ProviderType
public interface DrivePersistence extends BasePersistence<Drive> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link DriveUtil} to access the drive persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Caches the drive in the entity cache if it is enabled.
	 *
	 * @param drive the drive
	 */
	public void cacheResult(Drive drive);

	/**
	 * Caches the drives in the entity cache if it is enabled.
	 *
	 * @param drives the drives
	 */
	public void cacheResult(java.util.List<Drive> drives);

	/**
	 * Creates a new drive with the primary key. Does not add the drive to the database.
	 *
	 * @param driveId the primary key for the new drive
	 * @return the new drive
	 */
	public Drive create(long driveId);

	/**
	 * Removes the drive with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param driveId the primary key of the drive
	 * @return the drive that was removed
	 * @throws NoSuchDriveException if a drive with the primary key could not be found
	 */
	public Drive remove(long driveId) throws NoSuchDriveException;

	public Drive updateImpl(Drive drive);

	/**
	 * Returns the drive with the primary key or throws a <code>NoSuchDriveException</code> if it could not be found.
	 *
	 * @param driveId the primary key of the drive
	 * @return the drive
	 * @throws NoSuchDriveException if a drive with the primary key could not be found
	 */
	public Drive findByPrimaryKey(long driveId) throws NoSuchDriveException;

	/**
	 * Returns the drive with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param driveId the primary key of the drive
	 * @return the drive, or <code>null</code> if a drive with the primary key could not be found
	 */
	public Drive fetchByPrimaryKey(long driveId);

	/**
	 * Returns all the drives.
	 *
	 * @return the drives
	 */
	public java.util.List<Drive> findAll();

	/**
	 * Returns a range of all the drives.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DriveModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of drives
	 * @param end the upper bound of the range of drives (not inclusive)
	 * @return the range of drives
	 */
	public java.util.List<Drive> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the drives.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DriveModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of drives
	 * @param end the upper bound of the range of drives (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of drives
	 */
	public java.util.List<Drive> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Drive>
			orderByComparator);

	/**
	 * Returns an ordered range of all the drives.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DriveModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of drives
	 * @param end the upper bound of the range of drives (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of drives
	 */
	public java.util.List<Drive> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Drive>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the drives from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of drives.
	 *
	 * @return the number of drives
	 */
	public int countAll();

}