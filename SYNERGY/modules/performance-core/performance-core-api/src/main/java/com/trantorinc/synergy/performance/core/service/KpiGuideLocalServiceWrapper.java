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
 * Provides a wrapper for {@link KpiGuideLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see KpiGuideLocalService
 * @generated
 */
public class KpiGuideLocalServiceWrapper
	implements KpiGuideLocalService, ServiceWrapper<KpiGuideLocalService> {

	public KpiGuideLocalServiceWrapper() {
		this(null);
	}

	public KpiGuideLocalServiceWrapper(
		KpiGuideLocalService kpiGuideLocalService) {

		_kpiGuideLocalService = kpiGuideLocalService;
	}

	/**
	 * Adds the kpi guide to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect KpiGuideLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param kpiGuide the kpi guide
	 * @return the kpi guide that was added
	 */
	@Override
	public com.trantorinc.synergy.performance.core.model.KpiGuide addKpiGuide(
		com.trantorinc.synergy.performance.core.model.KpiGuide kpiGuide) {

		return _kpiGuideLocalService.addKpiGuide(kpiGuide);
	}

	/**
	 * Creates a new kpi guide with the primary key. Does not add the kpi guide to the database.
	 *
	 * @param guideId the primary key for the new kpi guide
	 * @return the new kpi guide
	 */
	@Override
	public com.trantorinc.synergy.performance.core.model.KpiGuide
		createKpiGuide(long guideId) {

		return _kpiGuideLocalService.createKpiGuide(guideId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _kpiGuideLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Deletes the kpi guide from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect KpiGuideLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param kpiGuide the kpi guide
	 * @return the kpi guide that was removed
	 */
	@Override
	public com.trantorinc.synergy.performance.core.model.KpiGuide
		deleteKpiGuide(
			com.trantorinc.synergy.performance.core.model.KpiGuide kpiGuide) {

		return _kpiGuideLocalService.deleteKpiGuide(kpiGuide);
	}

	/**
	 * Deletes the kpi guide with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect KpiGuideLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param guideId the primary key of the kpi guide
	 * @return the kpi guide that was removed
	 * @throws PortalException if a kpi guide with the primary key could not be found
	 */
	@Override
	public com.trantorinc.synergy.performance.core.model.KpiGuide
			deleteKpiGuide(long guideId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _kpiGuideLocalService.deleteKpiGuide(guideId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _kpiGuideLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _kpiGuideLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _kpiGuideLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _kpiGuideLocalService.dynamicQuery();
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

		return _kpiGuideLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.trantorinc.synergy.performance.core.model.impl.KpiGuideModelImpl</code>.
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

		return _kpiGuideLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.trantorinc.synergy.performance.core.model.impl.KpiGuideModelImpl</code>.
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

		return _kpiGuideLocalService.dynamicQuery(
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

		return _kpiGuideLocalService.dynamicQueryCount(dynamicQuery);
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

		return _kpiGuideLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.trantorinc.synergy.performance.core.model.KpiGuide fetchKpiGuide(
		long guideId) {

		return _kpiGuideLocalService.fetchKpiGuide(guideId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _kpiGuideLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _kpiGuideLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the kpi guide with the primary key.
	 *
	 * @param guideId the primary key of the kpi guide
	 * @return the kpi guide
	 * @throws PortalException if a kpi guide with the primary key could not be found
	 */
	@Override
	public com.trantorinc.synergy.performance.core.model.KpiGuide getKpiGuide(
			long guideId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _kpiGuideLocalService.getKpiGuide(guideId);
	}

	/**
	 * Returns a range of all the kpi guides.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.trantorinc.synergy.performance.core.model.impl.KpiGuideModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of kpi guides
	 * @param end the upper bound of the range of kpi guides (not inclusive)
	 * @return the range of kpi guides
	 */
	@Override
	public java.util.List
		<com.trantorinc.synergy.performance.core.model.KpiGuide> getKpiGuides(
			int start, int end) {

		return _kpiGuideLocalService.getKpiGuides(start, end);
	}

	/**
	 * Returns the number of kpi guides.
	 *
	 * @return the number of kpi guides
	 */
	@Override
	public int getKpiGuidesCount() {
		return _kpiGuideLocalService.getKpiGuidesCount();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _kpiGuideLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _kpiGuideLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the kpi guide in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect KpiGuideLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param kpiGuide the kpi guide
	 * @return the kpi guide that was updated
	 */
	@Override
	public com.trantorinc.synergy.performance.core.model.KpiGuide
		updateKpiGuide(
			com.trantorinc.synergy.performance.core.model.KpiGuide kpiGuide) {

		return _kpiGuideLocalService.updateKpiGuide(kpiGuide);
	}

	@Override
	public KpiGuideLocalService getWrappedService() {
		return _kpiGuideLocalService;
	}

	@Override
	public void setWrappedService(KpiGuideLocalService kpiGuideLocalService) {
		_kpiGuideLocalService = kpiGuideLocalService;
	}

	private KpiGuideLocalService _kpiGuideLocalService;

}