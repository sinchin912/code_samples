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
 * Provides a wrapper for {@link RecoveryReimbursementLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see RecoveryReimbursementLocalService
 * @generated
 */
public class RecoveryReimbursementLocalServiceWrapper
	implements RecoveryReimbursementLocalService,
			   ServiceWrapper<RecoveryReimbursementLocalService> {

	public RecoveryReimbursementLocalServiceWrapper() {
		this(null);
	}

	public RecoveryReimbursementLocalServiceWrapper(
		RecoveryReimbursementLocalService recoveryReimbursementLocalService) {

		_recoveryReimbursementLocalService = recoveryReimbursementLocalService;
	}

	/**
	 * Adds the recovery reimbursement to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect RecoveryReimbursementLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param recoveryReimbursement the recovery reimbursement
	 * @return the recovery reimbursement that was added
	 */
	@Override
	public com.trantorinc.synergy.notice.core.model.RecoveryReimbursement
		addRecoveryReimbursement(
			com.trantorinc.synergy.notice.core.model.RecoveryReimbursement
				recoveryReimbursement) {

		return _recoveryReimbursementLocalService.addRecoveryReimbursement(
			recoveryReimbursement);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _recoveryReimbursementLocalService.createPersistedModel(
			primaryKeyObj);
	}

	/**
	 * Creates a new recovery reimbursement with the primary key. Does not add the recovery reimbursement to the database.
	 *
	 * @param id the primary key for the new recovery reimbursement
	 * @return the new recovery reimbursement
	 */
	@Override
	public com.trantorinc.synergy.notice.core.model.RecoveryReimbursement
		createRecoveryReimbursement(long id) {

		return _recoveryReimbursementLocalService.createRecoveryReimbursement(
			id);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _recoveryReimbursementLocalService.deletePersistedModel(
			persistedModel);
	}

	/**
	 * Deletes the recovery reimbursement with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect RecoveryReimbursementLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param id the primary key of the recovery reimbursement
	 * @return the recovery reimbursement that was removed
	 * @throws PortalException if a recovery reimbursement with the primary key could not be found
	 */
	@Override
	public com.trantorinc.synergy.notice.core.model.RecoveryReimbursement
			deleteRecoveryReimbursement(long id)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _recoveryReimbursementLocalService.deleteRecoveryReimbursement(
			id);
	}

	/**
	 * Deletes the recovery reimbursement from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect RecoveryReimbursementLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param recoveryReimbursement the recovery reimbursement
	 * @return the recovery reimbursement that was removed
	 */
	@Override
	public com.trantorinc.synergy.notice.core.model.RecoveryReimbursement
		deleteRecoveryReimbursement(
			com.trantorinc.synergy.notice.core.model.RecoveryReimbursement
				recoveryReimbursement) {

		return _recoveryReimbursementLocalService.deleteRecoveryReimbursement(
			recoveryReimbursement);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _recoveryReimbursementLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _recoveryReimbursementLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _recoveryReimbursementLocalService.dynamicQuery();
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

		return _recoveryReimbursementLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.trantorinc.synergy.notice.core.model.impl.RecoveryReimbursementModelImpl</code>.
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

		return _recoveryReimbursementLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.trantorinc.synergy.notice.core.model.impl.RecoveryReimbursementModelImpl</code>.
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

		return _recoveryReimbursementLocalService.dynamicQuery(
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

		return _recoveryReimbursementLocalService.dynamicQueryCount(
			dynamicQuery);
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

		return _recoveryReimbursementLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.trantorinc.synergy.notice.core.model.RecoveryReimbursement
		fetchRecoveryReimbursement(long id) {

		return _recoveryReimbursementLocalService.fetchRecoveryReimbursement(
			id);
	}

	@Override
	public java.util.List
		<com.trantorinc.synergy.notice.core.model.RecoveryReimbursement>
			findAllRecoveryFormByDepartment(
				long departmentFormId, int department) {

		return _recoveryReimbursementLocalService.
			findAllRecoveryFormByDepartment(departmentFormId, department);
	}

	@Override
	public java.util.List
		<com.trantorinc.synergy.notice.core.model.RecoveryReimbursement>
			findRecoveryReimbursementFormByDepartmentFormId(
				long departmentFormId, int department) {

		return _recoveryReimbursementLocalService.
			findRecoveryReimbursementFormByDepartmentFormId(
				departmentFormId, department);
	}

	@Override
	public java.util.List
		<com.trantorinc.synergy.notice.core.model.RecoveryReimbursement>
			findSelectedRecoveryFormByDepartment(
				long departmentFormId, int department) {

		return _recoveryReimbursementLocalService.
			findSelectedRecoveryFormByDepartment(departmentFormId, department);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _recoveryReimbursementLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _recoveryReimbursementLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _recoveryReimbursementLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _recoveryReimbursementLocalService.getPersistedModel(
			primaryKeyObj);
	}

	/**
	 * Returns the recovery reimbursement with the primary key.
	 *
	 * @param id the primary key of the recovery reimbursement
	 * @return the recovery reimbursement
	 * @throws PortalException if a recovery reimbursement with the primary key could not be found
	 */
	@Override
	public com.trantorinc.synergy.notice.core.model.RecoveryReimbursement
			getRecoveryReimbursement(long id)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _recoveryReimbursementLocalService.getRecoveryReimbursement(id);
	}

	/**
	 * Returns a range of all the recovery reimbursements.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.trantorinc.synergy.notice.core.model.impl.RecoveryReimbursementModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of recovery reimbursements
	 * @param end the upper bound of the range of recovery reimbursements (not inclusive)
	 * @return the range of recovery reimbursements
	 */
	@Override
	public java.util.List
		<com.trantorinc.synergy.notice.core.model.RecoveryReimbursement>
			getRecoveryReimbursements(int start, int end) {

		return _recoveryReimbursementLocalService.getRecoveryReimbursements(
			start, end);
	}

	/**
	 * Returns the number of recovery reimbursements.
	 *
	 * @return the number of recovery reimbursements
	 */
	@Override
	public int getRecoveryReimbursementsCount() {
		return _recoveryReimbursementLocalService.
			getRecoveryReimbursementsCount();
	}

	/**
	 * Updates the recovery reimbursement in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect RecoveryReimbursementLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param recoveryReimbursement the recovery reimbursement
	 * @return the recovery reimbursement that was updated
	 */
	@Override
	public com.trantorinc.synergy.notice.core.model.RecoveryReimbursement
		updateRecoveryReimbursement(
			com.trantorinc.synergy.notice.core.model.RecoveryReimbursement
				recoveryReimbursement) {

		return _recoveryReimbursementLocalService.updateRecoveryReimbursement(
			recoveryReimbursement);
	}

	@Override
	public RecoveryReimbursementLocalService getWrappedService() {
		return _recoveryReimbursementLocalService;
	}

	@Override
	public void setWrappedService(
		RecoveryReimbursementLocalService recoveryReimbursementLocalService) {

		_recoveryReimbursementLocalService = recoveryReimbursementLocalService;
	}

	private RecoveryReimbursementLocalService
		_recoveryReimbursementLocalService;

}