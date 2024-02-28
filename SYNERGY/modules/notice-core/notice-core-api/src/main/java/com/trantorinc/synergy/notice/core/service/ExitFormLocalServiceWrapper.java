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
 * Provides a wrapper for {@link ExitFormLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see ExitFormLocalService
 * @generated
 */
public class ExitFormLocalServiceWrapper
	implements ExitFormLocalService, ServiceWrapper<ExitFormLocalService> {

	public ExitFormLocalServiceWrapper() {
		this(null);
	}

	public ExitFormLocalServiceWrapper(
		ExitFormLocalService exitFormLocalService) {

		_exitFormLocalService = exitFormLocalService;
	}

	/**
	 * Adds the exit form to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ExitFormLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param exitForm the exit form
	 * @return the exit form that was added
	 */
	@Override
	public com.trantorinc.synergy.notice.core.model.ExitForm addExitForm(
		com.trantorinc.synergy.notice.core.model.ExitForm exitForm) {

		return _exitFormLocalService.addExitForm(exitForm);
	}

	/**
	 * Creates a new exit form with the primary key. Does not add the exit form to the database.
	 *
	 * @param id the primary key for the new exit form
	 * @return the new exit form
	 */
	@Override
	public com.trantorinc.synergy.notice.core.model.ExitForm createExitForm(
		long id) {

		return _exitFormLocalService.createExitForm(id);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _exitFormLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Deletes the exit form from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ExitFormLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param exitForm the exit form
	 * @return the exit form that was removed
	 */
	@Override
	public com.trantorinc.synergy.notice.core.model.ExitForm deleteExitForm(
		com.trantorinc.synergy.notice.core.model.ExitForm exitForm) {

		return _exitFormLocalService.deleteExitForm(exitForm);
	}

	/**
	 * Deletes the exit form with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ExitFormLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param id the primary key of the exit form
	 * @return the exit form that was removed
	 * @throws PortalException if a exit form with the primary key could not be found
	 */
	@Override
	public com.trantorinc.synergy.notice.core.model.ExitForm deleteExitForm(
			long id)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _exitFormLocalService.deleteExitForm(id);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _exitFormLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _exitFormLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _exitFormLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _exitFormLocalService.dynamicQuery();
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

		return _exitFormLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.trantorinc.synergy.notice.core.model.impl.ExitFormModelImpl</code>.
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

		return _exitFormLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.trantorinc.synergy.notice.core.model.impl.ExitFormModelImpl</code>.
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

		return _exitFormLocalService.dynamicQuery(
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

		return _exitFormLocalService.dynamicQueryCount(dynamicQuery);
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

		return _exitFormLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.trantorinc.synergy.notice.core.model.ExitForm fetchExitForm(
		long id) {

		return _exitFormLocalService.fetchExitForm(id);
	}

	@Override
	public java.util.List<com.trantorinc.synergy.notice.core.model.ExitForm>
		findAllExitFormEntries() {

		return _exitFormLocalService.findAllExitFormEntries();
	}

	@Override
	public java.util.List<com.trantorinc.synergy.notice.core.model.ExitForm>
		findExitFormByResignationId(long resignationId) {

		return _exitFormLocalService.findExitFormByResignationId(resignationId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _exitFormLocalService.getActionableDynamicQuery();
	}

	/**
	 * Returns the exit form with the primary key.
	 *
	 * @param id the primary key of the exit form
	 * @return the exit form
	 * @throws PortalException if a exit form with the primary key could not be found
	 */
	@Override
	public com.trantorinc.synergy.notice.core.model.ExitForm getExitForm(
			long id)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _exitFormLocalService.getExitForm(id);
	}

	/**
	 * Returns a range of all the exit forms.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.trantorinc.synergy.notice.core.model.impl.ExitFormModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of exit forms
	 * @param end the upper bound of the range of exit forms (not inclusive)
	 * @return the range of exit forms
	 */
	@Override
	public java.util.List<com.trantorinc.synergy.notice.core.model.ExitForm>
		getExitForms(int start, int end) {

		return _exitFormLocalService.getExitForms(start, end);
	}

	/**
	 * Returns the number of exit forms.
	 *
	 * @return the number of exit forms
	 */
	@Override
	public int getExitFormsCount() {
		return _exitFormLocalService.getExitFormsCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _exitFormLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _exitFormLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _exitFormLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the exit form in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ExitFormLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param exitForm the exit form
	 * @return the exit form that was updated
	 */
	@Override
	public com.trantorinc.synergy.notice.core.model.ExitForm updateExitForm(
		com.trantorinc.synergy.notice.core.model.ExitForm exitForm) {

		return _exitFormLocalService.updateExitForm(exitForm);
	}

	@Override
	public ExitFormLocalService getWrappedService() {
		return _exitFormLocalService;
	}

	@Override
	public void setWrappedService(ExitFormLocalService exitFormLocalService) {
		_exitFormLocalService = exitFormLocalService;
	}

	private ExitFormLocalService _exitFormLocalService;

}