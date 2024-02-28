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

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.trantorinc.synergy.lms.core.model.Drive;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the drive service. This utility wraps <code>com.trantorinc.synergy.lms.core.service.persistence.impl.DrivePersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DrivePersistence
 * @generated
 */
public class DriveUtil {

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
	public static void clearCache(Drive drive) {
		getPersistence().clearCache(drive);
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
	public static Map<Serializable, Drive> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<Drive> findWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Drive> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Drive> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<Drive> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static Drive update(Drive drive) {
		return getPersistence().update(drive);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static Drive update(Drive drive, ServiceContext serviceContext) {
		return getPersistence().update(drive, serviceContext);
	}

	/**
	 * Caches the drive in the entity cache if it is enabled.
	 *
	 * @param drive the drive
	 */
	public static void cacheResult(Drive drive) {
		getPersistence().cacheResult(drive);
	}

	/**
	 * Caches the drives in the entity cache if it is enabled.
	 *
	 * @param drives the drives
	 */
	public static void cacheResult(List<Drive> drives) {
		getPersistence().cacheResult(drives);
	}

	/**
	 * Creates a new drive with the primary key. Does not add the drive to the database.
	 *
	 * @param driveId the primary key for the new drive
	 * @return the new drive
	 */
	public static Drive create(long driveId) {
		return getPersistence().create(driveId);
	}

	/**
	 * Removes the drive with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param driveId the primary key of the drive
	 * @return the drive that was removed
	 * @throws NoSuchDriveException if a drive with the primary key could not be found
	 */
	public static Drive remove(long driveId)
		throws com.trantorinc.synergy.lms.core.exception.NoSuchDriveException {

		return getPersistence().remove(driveId);
	}

	public static Drive updateImpl(Drive drive) {
		return getPersistence().updateImpl(drive);
	}

	/**
	 * Returns the drive with the primary key or throws a <code>NoSuchDriveException</code> if it could not be found.
	 *
	 * @param driveId the primary key of the drive
	 * @return the drive
	 * @throws NoSuchDriveException if a drive with the primary key could not be found
	 */
	public static Drive findByPrimaryKey(long driveId)
		throws com.trantorinc.synergy.lms.core.exception.NoSuchDriveException {

		return getPersistence().findByPrimaryKey(driveId);
	}

	/**
	 * Returns the drive with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param driveId the primary key of the drive
	 * @return the drive, or <code>null</code> if a drive with the primary key could not be found
	 */
	public static Drive fetchByPrimaryKey(long driveId) {
		return getPersistence().fetchByPrimaryKey(driveId);
	}

	/**
	 * Returns all the drives.
	 *
	 * @return the drives
	 */
	public static List<Drive> findAll() {
		return getPersistence().findAll();
	}

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
	public static List<Drive> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

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
	public static List<Drive> findAll(
		int start, int end, OrderByComparator<Drive> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

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
	public static List<Drive> findAll(
		int start, int end, OrderByComparator<Drive> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the drives from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of drives.
	 *
	 * @return the number of drives
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static DrivePersistence getPersistence() {
		return _persistence;
	}

	private static volatile DrivePersistence _persistence;

}