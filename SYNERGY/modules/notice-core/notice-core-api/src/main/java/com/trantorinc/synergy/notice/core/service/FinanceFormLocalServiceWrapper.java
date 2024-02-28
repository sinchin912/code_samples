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
 * Provides a wrapper for {@link FinanceFormLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see FinanceFormLocalService
 * @generated
 */
public class FinanceFormLocalServiceWrapper
	implements FinanceFormLocalService,
			   ServiceWrapper<FinanceFormLocalService> {

	public FinanceFormLocalServiceWrapper() {
		this(null);
	}

	public FinanceFormLocalServiceWrapper(
		FinanceFormLocalService financeFormLocalService) {

		_financeFormLocalService = financeFormLocalService;
	}

	/**
	 * Adds the finance form to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect FinanceFormLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param financeForm the finance form
	 * @return the finance form that was added
	 */
	@Override
	public com.trantorinc.synergy.notice.core.model.FinanceForm addFinanceForm(
		com.trantorinc.synergy.notice.core.model.FinanceForm financeForm) {

		return _financeFormLocalService.addFinanceForm(financeForm);
	}

	/**
	 * Creates a new finance form with the primary key. Does not add the finance form to the database.
	 *
	 * @param id the primary key for the new finance form
	 * @return the new finance form
	 */
	@Override
	public com.trantorinc.synergy.notice.core.model.FinanceForm
		createFinanceForm(long id) {

		return _financeFormLocalService.createFinanceForm(id);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _financeFormLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Deletes the finance form from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect FinanceFormLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param financeForm the finance form
	 * @return the finance form that was removed
	 */
	@Override
	public com.trantorinc.synergy.notice.core.model.FinanceForm
		deleteFinanceForm(
			com.trantorinc.synergy.notice.core.model.FinanceForm financeForm) {

		return _financeFormLocalService.deleteFinanceForm(financeForm);
	}

	/**
	 * Deletes the finance form with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect FinanceFormLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param id the primary key of the finance form
	 * @return the finance form that was removed
	 * @throws PortalException if a finance form with the primary key could not be found
	 */
	@Override
	public com.trantorinc.synergy.notice.core.model.FinanceForm
			deleteFinanceForm(long id)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _financeFormLocalService.deleteFinanceForm(id);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _financeFormLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _financeFormLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _financeFormLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _financeFormLocalService.dynamicQuery();
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

		return _financeFormLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.trantorinc.synergy.notice.core.model.impl.FinanceFormModelImpl</code>.
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

		return _financeFormLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.trantorinc.synergy.notice.core.model.impl.FinanceFormModelImpl</code>.
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

		return _financeFormLocalService.dynamicQuery(
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

		return _financeFormLocalService.dynamicQueryCount(dynamicQuery);
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

		return _financeFormLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.trantorinc.synergy.notice.core.model.FinanceForm
		fetchFinanceForm(long id) {

		return _financeFormLocalService.fetchFinanceForm(id);
	}

	@Override
	public java.util.List<com.trantorinc.synergy.notice.core.model.FinanceForm>
		findFinanceFormByExitId(long exitFormId) {

		return _financeFormLocalService.findFinanceFormByExitId(exitFormId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _financeFormLocalService.getActionableDynamicQuery();
	}

	/**
	 * Returns the finance form with the primary key.
	 *
	 * @param id the primary key of the finance form
	 * @return the finance form
	 * @throws PortalException if a finance form with the primary key could not be found
	 */
	@Override
	public com.trantorinc.synergy.notice.core.model.FinanceForm getFinanceForm(
			long id)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _financeFormLocalService.getFinanceForm(id);
	}

	/**
	 * Returns a range of all the finance forms.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.trantorinc.synergy.notice.core.model.impl.FinanceFormModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of finance forms
	 * @param end the upper bound of the range of finance forms (not inclusive)
	 * @return the range of finance forms
	 */
	@Override
	public java.util.List<com.trantorinc.synergy.notice.core.model.FinanceForm>
		getFinanceForms(int start, int end) {

		return _financeFormLocalService.getFinanceForms(start, end);
	}

	/**
	 * Returns the number of finance forms.
	 *
	 * @return the number of finance forms
	 */
	@Override
	public int getFinanceFormsCount() {
		return _financeFormLocalService.getFinanceFormsCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _financeFormLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _financeFormLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _financeFormLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the finance form in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect FinanceFormLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param financeForm the finance form
	 * @return the finance form that was updated
	 */
	@Override
	public com.trantorinc.synergy.notice.core.model.FinanceForm
		updateFinanceForm(
			com.trantorinc.synergy.notice.core.model.FinanceForm financeForm) {

		return _financeFormLocalService.updateFinanceForm(financeForm);
	}

	@Override
	public FinanceFormLocalService getWrappedService() {
		return _financeFormLocalService;
	}

	@Override
	public void setWrappedService(
		FinanceFormLocalService financeFormLocalService) {

		_financeFormLocalService = financeFormLocalService;
	}

	private FinanceFormLocalService _financeFormLocalService;

}