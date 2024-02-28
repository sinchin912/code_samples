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
 * Provides a wrapper for {@link ManagerFormLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see ManagerFormLocalService
 * @generated
 */
public class ManagerFormLocalServiceWrapper
	implements ManagerFormLocalService,
			   ServiceWrapper<ManagerFormLocalService> {

	public ManagerFormLocalServiceWrapper() {
		this(null);
	}

	public ManagerFormLocalServiceWrapper(
		ManagerFormLocalService managerFormLocalService) {

		_managerFormLocalService = managerFormLocalService;
	}

	/**
	 * Adds the manager form to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ManagerFormLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param managerForm the manager form
	 * @return the manager form that was added
	 */
	@Override
	public com.trantorinc.synergy.notice.core.model.ManagerForm addManagerForm(
		com.trantorinc.synergy.notice.core.model.ManagerForm managerForm) {

		return _managerFormLocalService.addManagerForm(managerForm);
	}

	/**
	 * Creates a new manager form with the primary key. Does not add the manager form to the database.
	 *
	 * @param id the primary key for the new manager form
	 * @return the new manager form
	 */
	@Override
	public com.trantorinc.synergy.notice.core.model.ManagerForm
		createManagerForm(long id) {

		return _managerFormLocalService.createManagerForm(id);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _managerFormLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Deletes the manager form with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ManagerFormLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param id the primary key of the manager form
	 * @return the manager form that was removed
	 * @throws PortalException if a manager form with the primary key could not be found
	 */
	@Override
	public com.trantorinc.synergy.notice.core.model.ManagerForm
			deleteManagerForm(long id)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _managerFormLocalService.deleteManagerForm(id);
	}

	/**
	 * Deletes the manager form from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ManagerFormLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param managerForm the manager form
	 * @return the manager form that was removed
	 */
	@Override
	public com.trantorinc.synergy.notice.core.model.ManagerForm
		deleteManagerForm(
			com.trantorinc.synergy.notice.core.model.ManagerForm managerForm) {

		return _managerFormLocalService.deleteManagerForm(managerForm);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _managerFormLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _managerFormLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _managerFormLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _managerFormLocalService.dynamicQuery();
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

		return _managerFormLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.trantorinc.synergy.notice.core.model.impl.ManagerFormModelImpl</code>.
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

		return _managerFormLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.trantorinc.synergy.notice.core.model.impl.ManagerFormModelImpl</code>.
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

		return _managerFormLocalService.dynamicQuery(
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

		return _managerFormLocalService.dynamicQueryCount(dynamicQuery);
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

		return _managerFormLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.trantorinc.synergy.notice.core.model.ManagerForm
		fetchManagerForm(long id) {

		return _managerFormLocalService.fetchManagerForm(id);
	}

	@Override
	public java.util.List<com.trantorinc.synergy.notice.core.model.ManagerForm>
		findManagerFormByExitId(long exitFormId) {

		return _managerFormLocalService.findManagerFormByExitId(exitFormId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _managerFormLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _managerFormLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the manager form with the primary key.
	 *
	 * @param id the primary key of the manager form
	 * @return the manager form
	 * @throws PortalException if a manager form with the primary key could not be found
	 */
	@Override
	public com.trantorinc.synergy.notice.core.model.ManagerForm getManagerForm(
			long id)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _managerFormLocalService.getManagerForm(id);
	}

	/**
	 * Returns a range of all the manager forms.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.trantorinc.synergy.notice.core.model.impl.ManagerFormModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of manager forms
	 * @param end the upper bound of the range of manager forms (not inclusive)
	 * @return the range of manager forms
	 */
	@Override
	public java.util.List<com.trantorinc.synergy.notice.core.model.ManagerForm>
		getManagerForms(int start, int end) {

		return _managerFormLocalService.getManagerForms(start, end);
	}

	/**
	 * Returns the number of manager forms.
	 *
	 * @return the number of manager forms
	 */
	@Override
	public int getManagerFormsCount() {
		return _managerFormLocalService.getManagerFormsCount();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _managerFormLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _managerFormLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the manager form in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ManagerFormLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param managerForm the manager form
	 * @return the manager form that was updated
	 */
	@Override
	public com.trantorinc.synergy.notice.core.model.ManagerForm
		updateManagerForm(
			com.trantorinc.synergy.notice.core.model.ManagerForm managerForm) {

		return _managerFormLocalService.updateManagerForm(managerForm);
	}

	@Override
	public ManagerFormLocalService getWrappedService() {
		return _managerFormLocalService;
	}

	@Override
	public void setWrappedService(
		ManagerFormLocalService managerFormLocalService) {

		_managerFormLocalService = managerFormLocalService;
	}

	private ManagerFormLocalService _managerFormLocalService;

}