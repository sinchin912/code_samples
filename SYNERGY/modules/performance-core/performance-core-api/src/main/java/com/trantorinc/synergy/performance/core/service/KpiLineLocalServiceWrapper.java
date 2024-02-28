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

package com.trantorinc.synergy.performance.core.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link KpiLineLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see KpiLineLocalService
 * @generated
 */
public class KpiLineLocalServiceWrapper
	implements KpiLineLocalService, ServiceWrapper<KpiLineLocalService> {

	public KpiLineLocalServiceWrapper() {
		this(null);
	}

	public KpiLineLocalServiceWrapper(KpiLineLocalService kpiLineLocalService) {
		_kpiLineLocalService = kpiLineLocalService;
	}

	/**
	 * Adds the kpi line to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect KpiLineLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param kpiLine the kpi line
	 * @return the kpi line that was added
	 */
	@Override
	public com.trantorinc.synergy.performance.core.model.KpiLine addKpiLine(
		com.trantorinc.synergy.performance.core.model.KpiLine kpiLine) {

		return _kpiLineLocalService.addKpiLine(kpiLine);
	}

	/**
	 * Creates a new kpi line with the primary key. Does not add the kpi line to the database.
	 *
	 * @param lineId the primary key for the new kpi line
	 * @return the new kpi line
	 */
	@Override
	public com.trantorinc.synergy.performance.core.model.KpiLine createKpiLine(
		long lineId) {

		return _kpiLineLocalService.createKpiLine(lineId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _kpiLineLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Deletes the kpi line from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect KpiLineLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param kpiLine the kpi line
	 * @return the kpi line that was removed
	 */
	@Override
	public com.trantorinc.synergy.performance.core.model.KpiLine deleteKpiLine(
		com.trantorinc.synergy.performance.core.model.KpiLine kpiLine) {

		return _kpiLineLocalService.deleteKpiLine(kpiLine);
	}

	/**
	 * Deletes the kpi line with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect KpiLineLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param lineId the primary key of the kpi line
	 * @return the kpi line that was removed
	 * @throws PortalException if a kpi line with the primary key could not be found
	 */
	@Override
	public com.trantorinc.synergy.performance.core.model.KpiLine deleteKpiLine(
			long lineId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _kpiLineLocalService.deleteKpiLine(lineId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _kpiLineLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _kpiLineLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _kpiLineLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _kpiLineLocalService.dynamicQuery();
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

		return _kpiLineLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.trantorinc.synergy.performance.core.model.impl.KpiLineModelImpl</code>.
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

		return _kpiLineLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.trantorinc.synergy.performance.core.model.impl.KpiLineModelImpl</code>.
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

		return _kpiLineLocalService.dynamicQuery(
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

		return _kpiLineLocalService.dynamicQueryCount(dynamicQuery);
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

		return _kpiLineLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public com.trantorinc.synergy.performance.core.model.KpiLine fetchKpiLine(
		long lineId) {

		return _kpiLineLocalService.fetchKpiLine(lineId);
	}

	@Override
	public java.util.List<com.trantorinc.synergy.performance.core.model.KpiLine>
		findKpiLineByKpiId(long kpiId) {

		return _kpiLineLocalService.findKpiLineByKpiId(kpiId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _kpiLineLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _kpiLineLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the kpi line with the primary key.
	 *
	 * @param lineId the primary key of the kpi line
	 * @return the kpi line
	 * @throws PortalException if a kpi line with the primary key could not be found
	 */
	@Override
	public com.trantorinc.synergy.performance.core.model.KpiLine getKpiLine(
			long lineId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _kpiLineLocalService.getKpiLine(lineId);
	}

	/**
	 * Returns a range of all the kpi lines.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.trantorinc.synergy.performance.core.model.impl.KpiLineModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of kpi lines
	 * @param end the upper bound of the range of kpi lines (not inclusive)
	 * @return the range of kpi lines
	 */
	@Override
	public java.util.List<com.trantorinc.synergy.performance.core.model.KpiLine>
		getKpiLines(int start, int end) {

		return _kpiLineLocalService.getKpiLines(start, end);
	}

	/**
	 * Returns the number of kpi lines.
	 *
	 * @return the number of kpi lines
	 */
	@Override
	public int getKpiLinesCount() {
		return _kpiLineLocalService.getKpiLinesCount();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _kpiLineLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _kpiLineLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the kpi line in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect KpiLineLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param kpiLine the kpi line
	 * @return the kpi line that was updated
	 */
	@Override
	public com.trantorinc.synergy.performance.core.model.KpiLine updateKpiLine(
		com.trantorinc.synergy.performance.core.model.KpiLine kpiLine) {

		return _kpiLineLocalService.updateKpiLine(kpiLine);
	}

	@Override
	public KpiLineLocalService getWrappedService() {
		return _kpiLineLocalService;
	}

	@Override
	public void setWrappedService(KpiLineLocalService kpiLineLocalService) {
		_kpiLineLocalService = kpiLineLocalService;
	}

	private KpiLineLocalService _kpiLineLocalService;

}