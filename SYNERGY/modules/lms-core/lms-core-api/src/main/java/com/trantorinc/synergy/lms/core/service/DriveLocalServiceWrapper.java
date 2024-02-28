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

package com.trantorinc.synergy.lms.core.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link DriveLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see DriveLocalService
 * @generated
 */
public class DriveLocalServiceWrapper
	implements DriveLocalService, ServiceWrapper<DriveLocalService> {

	public DriveLocalServiceWrapper() {
		this(null);
	}

	public DriveLocalServiceWrapper(DriveLocalService driveLocalService) {
		_driveLocalService = driveLocalService;
	}

	/**
	 * Adds the drive to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect DriveLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param drive the drive
	 * @return the drive that was added
	 */
	@Override
	public com.trantorinc.synergy.lms.core.model.Drive addDrive(
		com.trantorinc.synergy.lms.core.model.Drive drive) {

		return _driveLocalService.addDrive(drive);
	}

	/**
	 * Creates a new drive with the primary key. Does not add the drive to the database.
	 *
	 * @param driveId the primary key for the new drive
	 * @return the new drive
	 */
	@Override
	public com.trantorinc.synergy.lms.core.model.Drive createDrive(
		long driveId) {

		return _driveLocalService.createDrive(driveId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _driveLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Deletes the drive from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect DriveLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param drive the drive
	 * @return the drive that was removed
	 */
	@Override
	public com.trantorinc.synergy.lms.core.model.Drive deleteDrive(
		com.trantorinc.synergy.lms.core.model.Drive drive) {

		return _driveLocalService.deleteDrive(drive);
	}

	/**
	 * Deletes the drive with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect DriveLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param driveId the primary key of the drive
	 * @return the drive that was removed
	 * @throws PortalException if a drive with the primary key could not be found
	 */
	@Override
	public com.trantorinc.synergy.lms.core.model.Drive deleteDrive(long driveId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _driveLocalService.deleteDrive(driveId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _driveLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _driveLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _driveLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _driveLocalService.dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _driveLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.trantorinc.synergy.lms.core.model.impl.DriveModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return _driveLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.trantorinc.synergy.lms.core.model.impl.DriveModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return _driveLocalService.dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _driveLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return _driveLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public com.trantorinc.synergy.lms.core.model.Drive fetchDrive(
		long driveId) {

		return _driveLocalService.fetchDrive(driveId);
	}

	@Override
	public String findFolderIdByFolderName(String folderName) {
		return _driveLocalService.findFolderIdByFolderName(folderName);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _driveLocalService.getActionableDynamicQuery();
	}

	/**
	 * Returns the drive with the primary key.
	 *
	 * @param driveId the primary key of the drive
	 * @return the drive
	 * @throws PortalException if a drive with the primary key could not be found
	 */
	@Override
	public com.trantorinc.synergy.lms.core.model.Drive getDrive(long driveId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _driveLocalService.getDrive(driveId);
	}

	/**
	 * Returns a range of all the drives.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.trantorinc.synergy.lms.core.model.impl.DriveModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of drives
	 * @param end the upper bound of the range of drives (not inclusive)
	 * @return the range of drives
	 */
	@Override
	public java.util.List<com.trantorinc.synergy.lms.core.model.Drive>
		getDrives(int start, int end) {

		return _driveLocalService.getDrives(start, end);
	}

	/**
	 * Returns the number of drives.
	 *
	 * @return the number of drives
	 */
	@Override
	public int getDrivesCount() {
		return _driveLocalService.getDrivesCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _driveLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _driveLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _driveLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the drive in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect DriveLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param drive the drive
	 * @return the drive that was updated
	 */
	@Override
	public com.trantorinc.synergy.lms.core.model.Drive updateDrive(
		com.trantorinc.synergy.lms.core.model.Drive drive) {

		return _driveLocalService.updateDrive(drive);
	}

	@Override
	public DriveLocalService getWrappedService() {
		return _driveLocalService;
	}

	@Override
	public void setWrappedService(DriveLocalService driveLocalService) {
		_driveLocalService = driveLocalService;
	}

	private DriveLocalService _driveLocalService;

}