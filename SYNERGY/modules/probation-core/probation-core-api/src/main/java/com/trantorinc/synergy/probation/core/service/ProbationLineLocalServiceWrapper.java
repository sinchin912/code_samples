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
 * Provides a wrapper for {@link ProbationLineLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see ProbationLineLocalService
 * @generated
 */
public class ProbationLineLocalServiceWrapper
	implements ProbationLineLocalService,
			   ServiceWrapper<ProbationLineLocalService> {

	public ProbationLineLocalServiceWrapper() {
		this(null);
	}

	public ProbationLineLocalServiceWrapper(
		ProbationLineLocalService probationLineLocalService) {

		_probationLineLocalService = probationLineLocalService;
	}

	/**
	 * Adds the probation line to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ProbationLineLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param probationLine the probation line
	 * @return the probation line that was added
	 */
	@Override
	public com.trantorinc.synergy.probation.core.model.ProbationLine
		addProbationLine(
			com.trantorinc.synergy.probation.core.model.ProbationLine
				probationLine) {

		return _probationLineLocalService.addProbationLine(probationLine);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _probationLineLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Creates a new probation line with the primary key. Does not add the probation line to the database.
	 *
	 * @param lineId the primary key for the new probation line
	 * @return the new probation line
	 */
	@Override
	public com.trantorinc.synergy.probation.core.model.ProbationLine
		createProbationLine(long lineId) {

		return _probationLineLocalService.createProbationLine(lineId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _probationLineLocalService.deletePersistedModel(persistedModel);
	}

	/**
	 * Deletes the probation line with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ProbationLineLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param lineId the primary key of the probation line
	 * @return the probation line that was removed
	 * @throws PortalException if a probation line with the primary key could not be found
	 */
	@Override
	public com.trantorinc.synergy.probation.core.model.ProbationLine
			deleteProbationLine(long lineId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _probationLineLocalService.deleteProbationLine(lineId);
	}

	/**
	 * Deletes the probation line from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ProbationLineLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param probationLine the probation line
	 * @return the probation line that was removed
	 */
	@Override
	public com.trantorinc.synergy.probation.core.model.ProbationLine
		deleteProbationLine(
			com.trantorinc.synergy.probation.core.model.ProbationLine
				probationLine) {

		return _probationLineLocalService.deleteProbationLine(probationLine);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _probationLineLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _probationLineLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _probationLineLocalService.dynamicQuery();
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

		return _probationLineLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.trantorinc.synergy.probation.core.model.impl.ProbationLineModelImpl</code>.
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

		return _probationLineLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.trantorinc.synergy.probation.core.model.impl.ProbationLineModelImpl</code>.
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

		return _probationLineLocalService.dynamicQuery(
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

		return _probationLineLocalService.dynamicQueryCount(dynamicQuery);
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

		return _probationLineLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.trantorinc.synergy.probation.core.model.ProbationLine
		fetchProbationLine(long lineId) {

		return _probationLineLocalService.fetchProbationLine(lineId);
	}

	@Override
	public java.util.List
		<com.trantorinc.synergy.probation.core.model.ProbationLine>
			findByProbationId(String probationId) {

		return _probationLineLocalService.findByProbationId(probationId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _probationLineLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _probationLineLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _probationLineLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _probationLineLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns the probation line with the primary key.
	 *
	 * @param lineId the primary key of the probation line
	 * @return the probation line
	 * @throws PortalException if a probation line with the primary key could not be found
	 */
	@Override
	public com.trantorinc.synergy.probation.core.model.ProbationLine
			getProbationLine(long lineId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _probationLineLocalService.getProbationLine(lineId);
	}

	/**
	 * Returns a range of all the probation lines.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.trantorinc.synergy.probation.core.model.impl.ProbationLineModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of probation lines
	 * @param end the upper bound of the range of probation lines (not inclusive)
	 * @return the range of probation lines
	 */
	@Override
	public java.util.List
		<com.trantorinc.synergy.probation.core.model.ProbationLine>
			getProbationLines(int start, int end) {

		return _probationLineLocalService.getProbationLines(start, end);
	}

	/**
	 * Returns the number of probation lines.
	 *
	 * @return the number of probation lines
	 */
	@Override
	public int getProbationLinesCount() {
		return _probationLineLocalService.getProbationLinesCount();
	}

	/**
	 * Updates the probation line in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ProbationLineLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param probationLine the probation line
	 * @return the probation line that was updated
	 */
	@Override
	public com.trantorinc.synergy.probation.core.model.ProbationLine
		updateProbationLine(
			com.trantorinc.synergy.probation.core.model.ProbationLine
				probationLine) {

		return _probationLineLocalService.updateProbationLine(probationLine);
	}

	@Override
	public ProbationLineLocalService getWrappedService() {
		return _probationLineLocalService;
	}

	@Override
	public void setWrappedService(
		ProbationLineLocalService probationLineLocalService) {

		_probationLineLocalService = probationLineLocalService;
	}

	private ProbationLineLocalService _probationLineLocalService;

}