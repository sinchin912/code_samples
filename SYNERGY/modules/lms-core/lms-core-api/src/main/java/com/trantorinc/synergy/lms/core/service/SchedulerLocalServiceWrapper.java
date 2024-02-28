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
 * Provides a wrapper for {@link SchedulerLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see SchedulerLocalService
 * @generated
 */
public class SchedulerLocalServiceWrapper
	implements SchedulerLocalService, ServiceWrapper<SchedulerLocalService> {

	public SchedulerLocalServiceWrapper() {
		this(null);
	}

	public SchedulerLocalServiceWrapper(
		SchedulerLocalService schedulerLocalService) {

		_schedulerLocalService = schedulerLocalService;
	}

	/**
	 * Adds the scheduler to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect SchedulerLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param scheduler the scheduler
	 * @return the scheduler that was added
	 */
	@Override
	public com.trantorinc.synergy.lms.core.model.Scheduler addScheduler(
		com.trantorinc.synergy.lms.core.model.Scheduler scheduler) {

		return _schedulerLocalService.addScheduler(scheduler);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _schedulerLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Creates a new scheduler with the primary key. Does not add the scheduler to the database.
	 *
	 * @param schedulerId the primary key for the new scheduler
	 * @return the new scheduler
	 */
	@Override
	public com.trantorinc.synergy.lms.core.model.Scheduler createScheduler(
		long schedulerId) {

		return _schedulerLocalService.createScheduler(schedulerId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _schedulerLocalService.deletePersistedModel(persistedModel);
	}

	/**
	 * Deletes the scheduler with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect SchedulerLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param schedulerId the primary key of the scheduler
	 * @return the scheduler that was removed
	 * @throws PortalException if a scheduler with the primary key could not be found
	 */
	@Override
	public com.trantorinc.synergy.lms.core.model.Scheduler deleteScheduler(
			long schedulerId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _schedulerLocalService.deleteScheduler(schedulerId);
	}

	/**
	 * Deletes the scheduler from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect SchedulerLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param scheduler the scheduler
	 * @return the scheduler that was removed
	 */
	@Override
	public com.trantorinc.synergy.lms.core.model.Scheduler deleteScheduler(
		com.trantorinc.synergy.lms.core.model.Scheduler scheduler) {

		return _schedulerLocalService.deleteScheduler(scheduler);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _schedulerLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _schedulerLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _schedulerLocalService.dynamicQuery();
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

		return _schedulerLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.trantorinc.synergy.lms.core.model.impl.SchedulerModelImpl</code>.
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

		return _schedulerLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.trantorinc.synergy.lms.core.model.impl.SchedulerModelImpl</code>.
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

		return _schedulerLocalService.dynamicQuery(
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

		return _schedulerLocalService.dynamicQueryCount(dynamicQuery);
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

		return _schedulerLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.trantorinc.synergy.lms.core.model.Scheduler fetchScheduler(
		long schedulerId) {

		return _schedulerLocalService.fetchScheduler(schedulerId);
	}

	@Override
	public com.trantorinc.synergy.lms.core.model.Scheduler
		findSchedulerByNameAndDate(String name, java.util.Date onDate) {

		return _schedulerLocalService.findSchedulerByNameAndDate(name, onDate);
	}

	@Override
	public java.util.List<com.trantorinc.synergy.lms.core.model.Scheduler>
		findSchedulersByDate(java.util.Date onDate) {

		return _schedulerLocalService.findSchedulersByDate(onDate);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _schedulerLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _schedulerLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _schedulerLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _schedulerLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns the scheduler with the primary key.
	 *
	 * @param schedulerId the primary key of the scheduler
	 * @return the scheduler
	 * @throws PortalException if a scheduler with the primary key could not be found
	 */
	@Override
	public com.trantorinc.synergy.lms.core.model.Scheduler getScheduler(
			long schedulerId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _schedulerLocalService.getScheduler(schedulerId);
	}

	/**
	 * Returns a range of all the schedulers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.trantorinc.synergy.lms.core.model.impl.SchedulerModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of schedulers
	 * @param end the upper bound of the range of schedulers (not inclusive)
	 * @return the range of schedulers
	 */
	@Override
	public java.util.List<com.trantorinc.synergy.lms.core.model.Scheduler>
		getSchedulers(int start, int end) {

		return _schedulerLocalService.getSchedulers(start, end);
	}

	/**
	 * Returns the number of schedulers.
	 *
	 * @return the number of schedulers
	 */
	@Override
	public int getSchedulersCount() {
		return _schedulerLocalService.getSchedulersCount();
	}

	/**
	 * Updates the scheduler in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect SchedulerLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param scheduler the scheduler
	 * @return the scheduler that was updated
	 */
	@Override
	public com.trantorinc.synergy.lms.core.model.Scheduler updateScheduler(
		com.trantorinc.synergy.lms.core.model.Scheduler scheduler) {

		return _schedulerLocalService.updateScheduler(scheduler);
	}

	@Override
	public SchedulerLocalService getWrappedService() {
		return _schedulerLocalService;
	}

	@Override
	public void setWrappedService(SchedulerLocalService schedulerLocalService) {
		_schedulerLocalService = schedulerLocalService;
	}

	private SchedulerLocalService _schedulerLocalService;

}