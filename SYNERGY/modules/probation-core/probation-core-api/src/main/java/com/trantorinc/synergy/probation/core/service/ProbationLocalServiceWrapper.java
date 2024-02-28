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

package com.trantorinc.synergy.probation.core.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ProbationLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see ProbationLocalService
 * @generated
 */
public class ProbationLocalServiceWrapper
	implements ProbationLocalService, ServiceWrapper<ProbationLocalService> {

	public ProbationLocalServiceWrapper() {
		this(null);
	}

	public ProbationLocalServiceWrapper(
		ProbationLocalService probationLocalService) {

		_probationLocalService = probationLocalService;
	}

	/**
	 * Adds the probation to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ProbationLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param probation the probation
	 * @return the probation that was added
	 */
	@Override
	public com.trantorinc.synergy.probation.core.model.Probation addProbation(
		com.trantorinc.synergy.probation.core.model.Probation probation) {

		return _probationLocalService.addProbation(probation);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _probationLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Creates a new probation with the primary key. Does not add the probation to the database.
	 *
	 * @param ecode the primary key for the new probation
	 * @return the new probation
	 */
	@Override
	public com.trantorinc.synergy.probation.core.model.Probation
		createProbation(String ecode) {

		return _probationLocalService.createProbation(ecode);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _probationLocalService.deletePersistedModel(persistedModel);
	}

	/**
	 * Deletes the probation from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ProbationLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param probation the probation
	 * @return the probation that was removed
	 */
	@Override
	public com.trantorinc.synergy.probation.core.model.Probation
		deleteProbation(
			com.trantorinc.synergy.probation.core.model.Probation probation) {

		return _probationLocalService.deleteProbation(probation);
	}

	/**
	 * Deletes the probation with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ProbationLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param ecode the primary key of the probation
	 * @return the probation that was removed
	 * @throws PortalException if a probation with the primary key could not be found
	 */
	@Override
	public com.trantorinc.synergy.probation.core.model.Probation
			deleteProbation(String ecode)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _probationLocalService.deleteProbation(ecode);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _probationLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _probationLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _probationLocalService.dynamicQuery();
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

		return _probationLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.trantorinc.synergy.probation.core.model.impl.ProbationModelImpl</code>.
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

		return _probationLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.trantorinc.synergy.probation.core.model.impl.ProbationModelImpl</code>.
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

		return _probationLocalService.dynamicQuery(
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

		return _probationLocalService.dynamicQueryCount(dynamicQuery);
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

		return _probationLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.trantorinc.synergy.probation.core.model.Probation fetchProbation(
		String ecode) {

		return _probationLocalService.fetchProbation(ecode);
	}

	@Override
	public java.util.List<com.trantorinc.synergy.probation.core.model.Probation>
		findAllCompleted() {

		return _probationLocalService.findAllCompleted();
	}

	@Override
	public java.util.List<com.trantorinc.synergy.probation.core.model.Probation>
		findAllPending() {

		return _probationLocalService.findAllPending();
	}

	@Override
	public java.util.List<com.trantorinc.synergy.probation.core.model.Probation>
		findByManager(String manager) {

		return _probationLocalService.findByManager(manager);
	}

	@Override
	public java.util.List<com.trantorinc.synergy.probation.core.model.Probation>
		findCompletedByDate(java.util.Date onDate) {

		return _probationLocalService.findCompletedByDate(onDate);
	}

	@Override
	public java.util.List<com.trantorinc.synergy.probation.core.model.Probation>
		findExtendedByDate(java.util.Date onDate) {

		return _probationLocalService.findExtendedByDate(onDate);
	}

	@Override
	public java.util.List<com.trantorinc.synergy.probation.core.model.Probation>
		findPendingByDate(java.util.Date onDate) {

		return _probationLocalService.findPendingByDate(onDate);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _probationLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _probationLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns the probation with the primary key.
	 *
	 * @param ecode the primary key of the probation
	 * @return the probation
	 * @throws PortalException if a probation with the primary key could not be found
	 */
	@Override
	public com.trantorinc.synergy.probation.core.model.Probation getProbation(
			String ecode)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _probationLocalService.getProbation(ecode);
	}

	/**
	 * Returns a range of all the probations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.trantorinc.synergy.probation.core.model.impl.ProbationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of probations
	 * @param end the upper bound of the range of probations (not inclusive)
	 * @return the range of probations
	 */
	@Override
	public java.util.List<com.trantorinc.synergy.probation.core.model.Probation>
		getProbations(int start, int end) {

		return _probationLocalService.getProbations(start, end);
	}

	/**
	 * Returns the number of probations.
	 *
	 * @return the number of probations
	 */
	@Override
	public int getProbationsCount() {
		return _probationLocalService.getProbationsCount();
	}

	/**
	 * Updates the probation in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ProbationLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param probation the probation
	 * @return the probation that was updated
	 */
	@Override
	public com.trantorinc.synergy.probation.core.model.Probation
		updateProbation(
			com.trantorinc.synergy.probation.core.model.Probation probation) {

		return _probationLocalService.updateProbation(probation);
	}

	@Override
	public ProbationLocalService getWrappedService() {
		return _probationLocalService;
	}

	@Override
	public void setWrappedService(ProbationLocalService probationLocalService) {
		_probationLocalService = probationLocalService;
	}

	private ProbationLocalService _probationLocalService;

}