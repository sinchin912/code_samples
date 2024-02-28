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
 * Provides a wrapper for {@link KpiLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see KpiLocalService
 * @generated
 */
public class KpiLocalServiceWrapper
	implements KpiLocalService, ServiceWrapper<KpiLocalService> {

	public KpiLocalServiceWrapper() {
		this(null);
	}

	public KpiLocalServiceWrapper(KpiLocalService kpiLocalService) {
		_kpiLocalService = kpiLocalService;
	}

	/**
	 * Adds the kpi to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect KpiLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param kpi the kpi
	 * @return the kpi that was added
	 */
	@Override
	public com.trantorinc.synergy.performance.core.model.Kpi addKpi(
		com.trantorinc.synergy.performance.core.model.Kpi kpi) {

		return _kpiLocalService.addKpi(kpi);
	}

	/**
	 * Creates a new kpi with the primary key. Does not add the kpi to the database.
	 *
	 * @param kpiId the primary key for the new kpi
	 * @return the new kpi
	 */
	@Override
	public com.trantorinc.synergy.performance.core.model.Kpi createKpi(
		long kpiId) {

		return _kpiLocalService.createKpi(kpiId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _kpiLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Deletes the kpi from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect KpiLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param kpi the kpi
	 * @return the kpi that was removed
	 */
	@Override
	public com.trantorinc.synergy.performance.core.model.Kpi deleteKpi(
		com.trantorinc.synergy.performance.core.model.Kpi kpi) {

		return _kpiLocalService.deleteKpi(kpi);
	}

	/**
	 * Deletes the kpi with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect KpiLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param kpiId the primary key of the kpi
	 * @return the kpi that was removed
	 * @throws PortalException if a kpi with the primary key could not be found
	 */
	@Override
	public com.trantorinc.synergy.performance.core.model.Kpi deleteKpi(
			long kpiId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _kpiLocalService.deleteKpi(kpiId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _kpiLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _kpiLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _kpiLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _kpiLocalService.dynamicQuery();
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

		return _kpiLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.trantorinc.synergy.performance.core.model.impl.KpiModelImpl</code>.
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

		return _kpiLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.trantorinc.synergy.performance.core.model.impl.KpiModelImpl</code>.
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

		return _kpiLocalService.dynamicQuery(
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

		return _kpiLocalService.dynamicQueryCount(dynamicQuery);
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

		return _kpiLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public com.trantorinc.synergy.performance.core.model.Kpi fetchKpi(
		long kpiId) {

		return _kpiLocalService.fetchKpi(kpiId);
	}

	@Override
	public java.util.List<com.trantorinc.synergy.performance.core.model.Kpi>
		findKpiByEcode(String ecode) {

		return _kpiLocalService.findKpiByEcode(ecode);
	}

	@Override
	public java.util.List<com.trantorinc.synergy.performance.core.model.Kpi>
		findKpiByManagerEmail(String email) {

		return _kpiLocalService.findKpiByManagerEmail(email);
	}

	@Override
	public java.util.List<com.trantorinc.synergy.performance.core.model.Kpi>
		findKpiByReviewerEmail(String email) {

		return _kpiLocalService.findKpiByReviewerEmail(email);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _kpiLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _kpiLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the kpi with the primary key.
	 *
	 * @param kpiId the primary key of the kpi
	 * @return the kpi
	 * @throws PortalException if a kpi with the primary key could not be found
	 */
	@Override
	public com.trantorinc.synergy.performance.core.model.Kpi getKpi(long kpiId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _kpiLocalService.getKpi(kpiId);
	}

	/**
	 * Returns a range of all the kpis.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.trantorinc.synergy.performance.core.model.impl.KpiModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of kpis
	 * @param end the upper bound of the range of kpis (not inclusive)
	 * @return the range of kpis
	 */
	@Override
	public java.util.List<com.trantorinc.synergy.performance.core.model.Kpi>
		getKpis(int start, int end) {

		return _kpiLocalService.getKpis(start, end);
	}

	/**
	 * Returns the number of kpis.
	 *
	 * @return the number of kpis
	 */
	@Override
	public int getKpisCount() {
		return _kpiLocalService.getKpisCount();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _kpiLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _kpiLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the kpi in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect KpiLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param kpi the kpi
	 * @return the kpi that was updated
	 */
	@Override
	public com.trantorinc.synergy.performance.core.model.Kpi updateKpi(
		com.trantorinc.synergy.performance.core.model.Kpi kpi) {

		return _kpiLocalService.updateKpi(kpi);
	}

	@Override
	public KpiLocalService getWrappedService() {
		return _kpiLocalService;
	}

	@Override
	public void setWrappedService(KpiLocalService kpiLocalService) {
		_kpiLocalService = kpiLocalService;
	}

	private KpiLocalService _kpiLocalService;

}