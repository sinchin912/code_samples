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
 * Provides a wrapper for {@link AdminFormLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see AdminFormLocalService
 * @generated
 */
public class AdminFormLocalServiceWrapper
	implements AdminFormLocalService, ServiceWrapper<AdminFormLocalService> {

	public AdminFormLocalServiceWrapper() {
		this(null);
	}

	public AdminFormLocalServiceWrapper(
		AdminFormLocalService adminFormLocalService) {

		_adminFormLocalService = adminFormLocalService;
	}

	/**
	 * Adds the admin form to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AdminFormLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param adminForm the admin form
	 * @return the admin form that was added
	 */
	@Override
	public com.trantorinc.synergy.notice.core.model.AdminForm addAdminForm(
		com.trantorinc.synergy.notice.core.model.AdminForm adminForm) {

		return _adminFormLocalService.addAdminForm(adminForm);
	}

	/**
	 * Creates a new admin form with the primary key. Does not add the admin form to the database.
	 *
	 * @param id the primary key for the new admin form
	 * @return the new admin form
	 */
	@Override
	public com.trantorinc.synergy.notice.core.model.AdminForm createAdminForm(
		long id) {

		return _adminFormLocalService.createAdminForm(id);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _adminFormLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Deletes the admin form from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AdminFormLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param adminForm the admin form
	 * @return the admin form that was removed
	 */
	@Override
	public com.trantorinc.synergy.notice.core.model.AdminForm deleteAdminForm(
		com.trantorinc.synergy.notice.core.model.AdminForm adminForm) {

		return _adminFormLocalService.deleteAdminForm(adminForm);
	}

	/**
	 * Deletes the admin form with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AdminFormLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param id the primary key of the admin form
	 * @return the admin form that was removed
	 * @throws PortalException if a admin form with the primary key could not be found
	 */
	@Override
	public com.trantorinc.synergy.notice.core.model.AdminForm deleteAdminForm(
			long id)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _adminFormLocalService.deleteAdminForm(id);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _adminFormLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _adminFormLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _adminFormLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _adminFormLocalService.dynamicQuery();
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

		return _adminFormLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.trantorinc.synergy.notice.core.model.impl.AdminFormModelImpl</code>.
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

		return _adminFormLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.trantorinc.synergy.notice.core.model.impl.AdminFormModelImpl</code>.
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

		return _adminFormLocalService.dynamicQuery(
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

		return _adminFormLocalService.dynamicQueryCount(dynamicQuery);
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

		return _adminFormLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.trantorinc.synergy.notice.core.model.AdminForm fetchAdminForm(
		long id) {

		return _adminFormLocalService.fetchAdminForm(id);
	}

	@Override
	public java.util.List<com.trantorinc.synergy.notice.core.model.AdminForm>
		findAdminFormByExitId(long exitFormId) {

		return _adminFormLocalService.findAdminFormByExitId(exitFormId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _adminFormLocalService.getActionableDynamicQuery();
	}

	/**
	 * Returns the admin form with the primary key.
	 *
	 * @param id the primary key of the admin form
	 * @return the admin form
	 * @throws PortalException if a admin form with the primary key could not be found
	 */
	@Override
	public com.trantorinc.synergy.notice.core.model.AdminForm getAdminForm(
			long id)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _adminFormLocalService.getAdminForm(id);
	}

	/**
	 * Returns a range of all the admin forms.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.trantorinc.synergy.notice.core.model.impl.AdminFormModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of admin forms
	 * @param end the upper bound of the range of admin forms (not inclusive)
	 * @return the range of admin forms
	 */
	@Override
	public java.util.List<com.trantorinc.synergy.notice.core.model.AdminForm>
		getAdminForms(int start, int end) {

		return _adminFormLocalService.getAdminForms(start, end);
	}

	/**
	 * Returns the number of admin forms.
	 *
	 * @return the number of admin forms
	 */
	@Override
	public int getAdminFormsCount() {
		return _adminFormLocalService.getAdminFormsCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _adminFormLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _adminFormLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _adminFormLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the admin form in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AdminFormLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param adminForm the admin form
	 * @return the admin form that was updated
	 */
	@Override
	public com.trantorinc.synergy.notice.core.model.AdminForm updateAdminForm(
		com.trantorinc.synergy.notice.core.model.AdminForm adminForm) {

		return _adminFormLocalService.updateAdminForm(adminForm);
	}

	@Override
	public AdminFormLocalService getWrappedService() {
		return _adminFormLocalService;
	}

	@Override
	public void setWrappedService(AdminFormLocalService adminFormLocalService) {
		_adminFormLocalService = adminFormLocalService;
	}

	private AdminFormLocalService _adminFormLocalService;

}