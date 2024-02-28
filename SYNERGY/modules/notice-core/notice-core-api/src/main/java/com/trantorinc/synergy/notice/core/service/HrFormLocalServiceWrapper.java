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
 * Provides a wrapper for {@link HrFormLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see HrFormLocalService
 * @generated
 */
public class HrFormLocalServiceWrapper
	implements HrFormLocalService, ServiceWrapper<HrFormLocalService> {

	public HrFormLocalServiceWrapper() {
		this(null);
	}

	public HrFormLocalServiceWrapper(HrFormLocalService hrFormLocalService) {
		_hrFormLocalService = hrFormLocalService;
	}

	/**
	 * Adds the hr form to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect HrFormLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param hrForm the hr form
	 * @return the hr form that was added
	 */
	@Override
	public com.trantorinc.synergy.notice.core.model.HrForm addHrForm(
		com.trantorinc.synergy.notice.core.model.HrForm hrForm) {

		return _hrFormLocalService.addHrForm(hrForm);
	}

	/**
	 * Creates a new hr form with the primary key. Does not add the hr form to the database.
	 *
	 * @param id the primary key for the new hr form
	 * @return the new hr form
	 */
	@Override
	public com.trantorinc.synergy.notice.core.model.HrForm createHrForm(
		long id) {

		return _hrFormLocalService.createHrForm(id);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _hrFormLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Deletes the hr form from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect HrFormLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param hrForm the hr form
	 * @return the hr form that was removed
	 */
	@Override
	public com.trantorinc.synergy.notice.core.model.HrForm deleteHrForm(
		com.trantorinc.synergy.notice.core.model.HrForm hrForm) {

		return _hrFormLocalService.deleteHrForm(hrForm);
	}

	/**
	 * Deletes the hr form with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect HrFormLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param id the primary key of the hr form
	 * @return the hr form that was removed
	 * @throws PortalException if a hr form with the primary key could not be found
	 */
	@Override
	public com.trantorinc.synergy.notice.core.model.HrForm deleteHrForm(long id)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _hrFormLocalService.deleteHrForm(id);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _hrFormLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _hrFormLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _hrFormLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _hrFormLocalService.dynamicQuery();
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

		return _hrFormLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.trantorinc.synergy.notice.core.model.impl.HrFormModelImpl</code>.
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

		return _hrFormLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.trantorinc.synergy.notice.core.model.impl.HrFormModelImpl</code>.
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

		return _hrFormLocalService.dynamicQuery(
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

		return _hrFormLocalService.dynamicQueryCount(dynamicQuery);
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

		return _hrFormLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public com.trantorinc.synergy.notice.core.model.HrForm fetchHrForm(
		long id) {

		return _hrFormLocalService.fetchHrForm(id);
	}

	@Override
	public java.util.List<com.trantorinc.synergy.notice.core.model.HrForm>
		findHrFormByExitId(long exitFormId) {

		return _hrFormLocalService.findHrFormByExitId(exitFormId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _hrFormLocalService.getActionableDynamicQuery();
	}

	/**
	 * Returns the hr form with the primary key.
	 *
	 * @param id the primary key of the hr form
	 * @return the hr form
	 * @throws PortalException if a hr form with the primary key could not be found
	 */
	@Override
	public com.trantorinc.synergy.notice.core.model.HrForm getHrForm(long id)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _hrFormLocalService.getHrForm(id);
	}

	/**
	 * Returns a range of all the hr forms.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.trantorinc.synergy.notice.core.model.impl.HrFormModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of hr forms
	 * @param end the upper bound of the range of hr forms (not inclusive)
	 * @return the range of hr forms
	 */
	@Override
	public java.util.List<com.trantorinc.synergy.notice.core.model.HrForm>
		getHrForms(int start, int end) {

		return _hrFormLocalService.getHrForms(start, end);
	}

	/**
	 * Returns the number of hr forms.
	 *
	 * @return the number of hr forms
	 */
	@Override
	public int getHrFormsCount() {
		return _hrFormLocalService.getHrFormsCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _hrFormLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _hrFormLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _hrFormLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the hr form in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect HrFormLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param hrForm the hr form
	 * @return the hr form that was updated
	 */
	@Override
	public com.trantorinc.synergy.notice.core.model.HrForm updateHrForm(
		com.trantorinc.synergy.notice.core.model.HrForm hrForm) {

		return _hrFormLocalService.updateHrForm(hrForm);
	}

	@Override
	public HrFormLocalService getWrappedService() {
		return _hrFormLocalService;
	}

	@Override
	public void setWrappedService(HrFormLocalService hrFormLocalService) {
		_hrFormLocalService = hrFormLocalService;
	}

	private HrFormLocalService _hrFormLocalService;

}