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
 * Provides a wrapper for {@link ExitStateLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see ExitStateLocalService
 * @generated
 */
public class ExitStateLocalServiceWrapper
	implements ExitStateLocalService, ServiceWrapper<ExitStateLocalService> {

	public ExitStateLocalServiceWrapper() {
		this(null);
	}

	public ExitStateLocalServiceWrapper(
		ExitStateLocalService exitStateLocalService) {

		_exitStateLocalService = exitStateLocalService;
	}

	/**
	 * Adds the exit state to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ExitStateLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param exitState the exit state
	 * @return the exit state that was added
	 */
	@Override
	public com.trantorinc.synergy.notice.core.model.ExitState addExitState(
		com.trantorinc.synergy.notice.core.model.ExitState exitState) {

		return _exitStateLocalService.addExitState(exitState);
	}

	/**
	 * Creates a new exit state with the primary key. Does not add the exit state to the database.
	 *
	 * @param exitStateId the primary key for the new exit state
	 * @return the new exit state
	 */
	@Override
	public com.trantorinc.synergy.notice.core.model.ExitState createExitState(
		long exitStateId) {

		return _exitStateLocalService.createExitState(exitStateId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _exitStateLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Deletes the exit state from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ExitStateLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param exitState the exit state
	 * @return the exit state that was removed
	 */
	@Override
	public com.trantorinc.synergy.notice.core.model.ExitState deleteExitState(
		com.trantorinc.synergy.notice.core.model.ExitState exitState) {

		return _exitStateLocalService.deleteExitState(exitState);
	}

	/**
	 * Deletes the exit state with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ExitStateLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param exitStateId the primary key of the exit state
	 * @return the exit state that was removed
	 * @throws PortalException if a exit state with the primary key could not be found
	 */
	@Override
	public com.trantorinc.synergy.notice.core.model.ExitState deleteExitState(
			long exitStateId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _exitStateLocalService.deleteExitState(exitStateId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _exitStateLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _exitStateLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _exitStateLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _exitStateLocalService.dynamicQuery();
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

		return _exitStateLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.trantorinc.synergy.notice.core.model.impl.ExitStateModelImpl</code>.
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

		return _exitStateLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.trantorinc.synergy.notice.core.model.impl.ExitStateModelImpl</code>.
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

		return _exitStateLocalService.dynamicQuery(
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

		return _exitStateLocalService.dynamicQueryCount(dynamicQuery);
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

		return _exitStateLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.trantorinc.synergy.notice.core.model.ExitState fetchExitState(
		long exitStateId) {

		return _exitStateLocalService.fetchExitState(exitStateId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _exitStateLocalService.getActionableDynamicQuery();
	}

	/**
	 * Returns the exit state with the primary key.
	 *
	 * @param exitStateId the primary key of the exit state
	 * @return the exit state
	 * @throws PortalException if a exit state with the primary key could not be found
	 */
	@Override
	public com.trantorinc.synergy.notice.core.model.ExitState getExitState(
			long exitStateId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _exitStateLocalService.getExitState(exitStateId);
	}

	/**
	 * Returns a range of all the exit states.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.trantorinc.synergy.notice.core.model.impl.ExitStateModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of exit states
	 * @param end the upper bound of the range of exit states (not inclusive)
	 * @return the range of exit states
	 */
	@Override
	public java.util.List<com.trantorinc.synergy.notice.core.model.ExitState>
		getExitStates(int start, int end) {

		return _exitStateLocalService.getExitStates(start, end);
	}

	/**
	 * Returns the number of exit states.
	 *
	 * @return the number of exit states
	 */
	@Override
	public int getExitStatesCount() {
		return _exitStateLocalService.getExitStatesCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _exitStateLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _exitStateLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _exitStateLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the exit state in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ExitStateLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param exitState the exit state
	 * @return the exit state that was updated
	 */
	@Override
	public com.trantorinc.synergy.notice.core.model.ExitState updateExitState(
		com.trantorinc.synergy.notice.core.model.ExitState exitState) {

		return _exitStateLocalService.updateExitState(exitState);
	}

	@Override
	public ExitStateLocalService getWrappedService() {
		return _exitStateLocalService;
	}

	@Override
	public void setWrappedService(ExitStateLocalService exitStateLocalService) {
		_exitStateLocalService = exitStateLocalService;
	}

	private ExitStateLocalService _exitStateLocalService;

}