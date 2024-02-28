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

package com.trantorinc.synergy.notice.core.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ResignationLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see ResignationLocalService
 * @generated
 */
public class ResignationLocalServiceWrapper
	implements ResignationLocalService,
			   ServiceWrapper<ResignationLocalService> {

	public ResignationLocalServiceWrapper() {
		this(null);
	}

	public ResignationLocalServiceWrapper(
		ResignationLocalService resignationLocalService) {

		_resignationLocalService = resignationLocalService;
	}

	/**
	 * Adds the resignation to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ResignationLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param resignation the resignation
	 * @return the resignation that was added
	 */
	@Override
	public com.trantorinc.synergy.notice.core.model.Resignation addResignation(
		com.trantorinc.synergy.notice.core.model.Resignation resignation) {

		return _resignationLocalService.addResignation(resignation);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _resignationLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Creates a new resignation with the primary key. Does not add the resignation to the database.
	 *
	 * @param id the primary key for the new resignation
	 * @return the new resignation
	 */
	@Override
	public com.trantorinc.synergy.notice.core.model.Resignation
		createResignation(long id) {

		return _resignationLocalService.createResignation(id);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _resignationLocalService.deletePersistedModel(persistedModel);
	}

	/**
	 * Deletes the resignation with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ResignationLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param id the primary key of the resignation
	 * @return the resignation that was removed
	 * @throws PortalException if a resignation with the primary key could not be found
	 */
	@Override
	public com.trantorinc.synergy.notice.core.model.Resignation
			deleteResignation(long id)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _resignationLocalService.deleteResignation(id);
	}

	/**
	 * Deletes the resignation from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ResignationLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param resignation the resignation
	 * @return the resignation that was removed
	 */
	@Override
	public com.trantorinc.synergy.notice.core.model.Resignation
		deleteResignation(
			com.trantorinc.synergy.notice.core.model.Resignation resignation) {

		return _resignationLocalService.deleteResignation(resignation);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _resignationLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _resignationLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _resignationLocalService.dynamicQuery();
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

		return _resignationLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.trantorinc.synergy.notice.core.model.impl.ResignationModelImpl</code>.
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

		return _resignationLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.trantorinc.synergy.notice.core.model.impl.ResignationModelImpl</code>.
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

		return _resignationLocalService.dynamicQuery(
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

		return _resignationLocalService.dynamicQueryCount(dynamicQuery);
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

		return _resignationLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.trantorinc.synergy.notice.core.model.Resignation
		fetchResignation(long id) {

		return _resignationLocalService.fetchResignation(id);
	}

	@Override
	public java.util.List<com.trantorinc.synergy.notice.core.model.Resignation>
		findActiveResignationEntries(java.util.Date date) {

		return _resignationLocalService.findActiveResignationEntries(date);
	}

	@Override
	public java.util.List<com.trantorinc.synergy.notice.core.model.Resignation>
		findActiveResignationsByLastWorkingDate(java.util.Date date) {

		return _resignationLocalService.findActiveResignationsByLastWorkingDate(
			date);
	}

	@Override
	public java.util.List<com.trantorinc.synergy.notice.core.model.Resignation>
		findAllResignationsByHrState() {

		return _resignationLocalService.findAllResignationsByHrState();
	}

	@Override
	public java.util.List<com.trantorinc.synergy.notice.core.model.Resignation>
		findEntryByManagerEmail(String email) {

		return _resignationLocalService.findEntryByManagerEmail(email);
	}

	@Override
	public java.util.List<com.trantorinc.synergy.notice.core.model.Resignation>
		findResignationByEcode(String ecode) {

		return _resignationLocalService.findResignationByEcode(ecode);
	}

	@Override
	public java.util.List<com.trantorinc.synergy.notice.core.model.Resignation>
		findResignationsByLastWorkingDate(java.util.Date lastWorkingDay) {

		return _resignationLocalService.findResignationsByLastWorkingDate(
			lastWorkingDay);
	}

	@Override
	public java.util.List<Integer> findUniqueResignationYears() {
		return _resignationLocalService.findUniqueResignationYears();
	}

	@Override
	public java.util.List<com.trantorinc.synergy.notice.core.model.Resignation>
		findYearlyEntriesbyCreationDate(
			java.util.Date startDate, java.util.Date endDate) {

		return _resignationLocalService.findYearlyEntriesbyCreationDate(
			startDate, endDate);
	}

	@Override
	public java.util.List<com.trantorinc.synergy.notice.core.model.Resignation>
		findYearlyEntriesbyLWD(
			java.util.Date startDate, java.util.Date endDate) {

		return _resignationLocalService.findYearlyEntriesbyLWD(
			startDate, endDate);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _resignationLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _resignationLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _resignationLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _resignationLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns the resignation with the primary key.
	 *
	 * @param id the primary key of the resignation
	 * @return the resignation
	 * @throws PortalException if a resignation with the primary key could not be found
	 */
	@Override
	public com.trantorinc.synergy.notice.core.model.Resignation getResignation(
			long id)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _resignationLocalService.getResignation(id);
	}

	/**
	 * Returns a range of all the resignations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.trantorinc.synergy.notice.core.model.impl.ResignationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of resignations
	 * @param end the upper bound of the range of resignations (not inclusive)
	 * @return the range of resignations
	 */
	@Override
	public java.util.List<com.trantorinc.synergy.notice.core.model.Resignation>
		getResignations(int start, int end) {

		return _resignationLocalService.getResignations(start, end);
	}

	/**
	 * Returns the number of resignations.
	 *
	 * @return the number of resignations
	 */
	@Override
	public int getResignationsCount() {
		return _resignationLocalService.getResignationsCount();
	}

	/**
	 * Updates the resignation in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ResignationLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param resignation the resignation
	 * @return the resignation that was updated
	 */
	@Override
	public com.trantorinc.synergy.notice.core.model.Resignation
		updateResignation(
			com.trantorinc.synergy.notice.core.model.Resignation resignation) {

		return _resignationLocalService.updateResignation(resignation);
	}

	@Override
	public ResignationLocalService getWrappedService() {
		return _resignationLocalService;
	}

	@Override
	public void setWrappedService(
		ResignationLocalService resignationLocalService) {

		_resignationLocalService = resignationLocalService;
	}

	private ResignationLocalService _resignationLocalService;

}