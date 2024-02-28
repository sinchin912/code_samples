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
 * Provides a wrapper for {@link ItFormLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see ItFormLocalService
 * @generated
 */
public class ItFormLocalServiceWrapper
	implements ItFormLocalService, ServiceWrapper<ItFormLocalService> {

	public ItFormLocalServiceWrapper() {
		this(null);
	}

	public ItFormLocalServiceWrapper(ItFormLocalService itFormLocalService) {
		_itFormLocalService = itFormLocalService;
	}

	/**
	 * Adds the it form to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ItFormLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param itForm the it form
	 * @return the it form that was added
	 */
	@Override
	public com.trantorinc.synergy.notice.core.model.ItForm addItForm(
		com.trantorinc.synergy.notice.core.model.ItForm itForm) {

		return _itFormLocalService.addItForm(itForm);
	}

	/**
	 * Creates a new it form with the primary key. Does not add the it form to the database.
	 *
	 * @param id the primary key for the new it form
	 * @return the new it form
	 */
	@Override
	public com.trantorinc.synergy.notice.core.model.ItForm createItForm(
		long id) {

		return _itFormLocalService.createItForm(id);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _itFormLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Deletes the it form from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ItFormLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param itForm the it form
	 * @return the it form that was removed
	 */
	@Override
	public com.trantorinc.synergy.notice.core.model.ItForm deleteItForm(
		com.trantorinc.synergy.notice.core.model.ItForm itForm) {

		return _itFormLocalService.deleteItForm(itForm);
	}

	/**
	 * Deletes the it form with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ItFormLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param id the primary key of the it form
	 * @return the it form that was removed
	 * @throws PortalException if a it form with the primary key could not be found
	 */
	@Override
	public com.trantorinc.synergy.notice.core.model.ItForm deleteItForm(long id)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _itFormLocalService.deleteItForm(id);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _itFormLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _itFormLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _itFormLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _itFormLocalService.dynamicQuery();
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

		return _itFormLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.trantorinc.synergy.notice.core.model.impl.ItFormModelImpl</code>.
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

		return _itFormLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.trantorinc.synergy.notice.core.model.impl.ItFormModelImpl</code>.
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

		return _itFormLocalService.dynamicQuery(
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

		return _itFormLocalService.dynamicQueryCount(dynamicQuery);
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

		return _itFormLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public com.trantorinc.synergy.notice.core.model.ItForm fetchItForm(
		long id) {

		return _itFormLocalService.fetchItForm(id);
	}

	@Override
	public java.util.List<com.trantorinc.synergy.notice.core.model.ItForm>
		findItFormByExitId(long exitFormId) {

		return _itFormLocalService.findItFormByExitId(exitFormId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _itFormLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _itFormLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the it form with the primary key.
	 *
	 * @param id the primary key of the it form
	 * @return the it form
	 * @throws PortalException if a it form with the primary key could not be found
	 */
	@Override
	public com.trantorinc.synergy.notice.core.model.ItForm getItForm(long id)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _itFormLocalService.getItForm(id);
	}

	/**
	 * Returns a range of all the it forms.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.trantorinc.synergy.notice.core.model.impl.ItFormModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of it forms
	 * @param end the upper bound of the range of it forms (not inclusive)
	 * @return the range of it forms
	 */
	@Override
	public java.util.List<com.trantorinc.synergy.notice.core.model.ItForm>
		getItForms(int start, int end) {

		return _itFormLocalService.getItForms(start, end);
	}

	/**
	 * Returns the number of it forms.
	 *
	 * @return the number of it forms
	 */
	@Override
	public int getItFormsCount() {
		return _itFormLocalService.getItFormsCount();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _itFormLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _itFormLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the it form in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ItFormLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param itForm the it form
	 * @return the it form that was updated
	 */
	@Override
	public com.trantorinc.synergy.notice.core.model.ItForm updateItForm(
		com.trantorinc.synergy.notice.core.model.ItForm itForm) {

		return _itFormLocalService.updateItForm(itForm);
	}

	@Override
	public ItFormLocalService getWrappedService() {
		return _itFormLocalService;
	}

	@Override
	public void setWrappedService(ItFormLocalService itFormLocalService) {
		_itFormLocalService = itFormLocalService;
	}

	private ItFormLocalService _itFormLocalService;

}