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

package com.trantorinc.synergy.game.core.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link PrizeLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see PrizeLocalService
 * @generated
 */
public class PrizeLocalServiceWrapper
	implements PrizeLocalService, ServiceWrapper<PrizeLocalService> {

	public PrizeLocalServiceWrapper() {
		this(null);
	}

	public PrizeLocalServiceWrapper(PrizeLocalService prizeLocalService) {
		_prizeLocalService = prizeLocalService;
	}

	/**
	 * Adds the prize to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect PrizeLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param prize the prize
	 * @return the prize that was added
	 */
	@Override
	public com.trantorinc.synergy.game.core.model.Prize addPrize(
		com.trantorinc.synergy.game.core.model.Prize prize) {

		return _prizeLocalService.addPrize(prize);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _prizeLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Creates a new prize with the primary key. Does not add the prize to the database.
	 *
	 * @param prizeId the primary key for the new prize
	 * @return the new prize
	 */
	@Override
	public com.trantorinc.synergy.game.core.model.Prize createPrize(
		long prizeId) {

		return _prizeLocalService.createPrize(prizeId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _prizeLocalService.deletePersistedModel(persistedModel);
	}

	/**
	 * Deletes the prize with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect PrizeLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param prizeId the primary key of the prize
	 * @return the prize that was removed
	 * @throws PortalException if a prize with the primary key could not be found
	 */
	@Override
	public com.trantorinc.synergy.game.core.model.Prize deletePrize(
			long prizeId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _prizeLocalService.deletePrize(prizeId);
	}

	/**
	 * Deletes the prize from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect PrizeLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param prize the prize
	 * @return the prize that was removed
	 */
	@Override
	public com.trantorinc.synergy.game.core.model.Prize deletePrize(
		com.trantorinc.synergy.game.core.model.Prize prize) {

		return _prizeLocalService.deletePrize(prize);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _prizeLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _prizeLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _prizeLocalService.dynamicQuery();
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

		return _prizeLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.trantorinc.synergy.game.core.model.impl.PrizeModelImpl</code>.
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

		return _prizeLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.trantorinc.synergy.game.core.model.impl.PrizeModelImpl</code>.
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

		return _prizeLocalService.dynamicQuery(
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

		return _prizeLocalService.dynamicQueryCount(dynamicQuery);
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

		return _prizeLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public com.trantorinc.synergy.game.core.model.Prize fetchPrize(
		long prizeId) {

		return _prizeLocalService.fetchPrize(prizeId);
	}

	@Override
	public java.util.List<com.trantorinc.synergy.game.core.model.Prize>
		findPrizesByYear(int year) {

		return _prizeLocalService.findPrizesByYear(year);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _prizeLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _prizeLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _prizeLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _prizeLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns the prize with the primary key.
	 *
	 * @param prizeId the primary key of the prize
	 * @return the prize
	 * @throws PortalException if a prize with the primary key could not be found
	 */
	@Override
	public com.trantorinc.synergy.game.core.model.Prize getPrize(long prizeId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _prizeLocalService.getPrize(prizeId);
	}

	/**
	 * Returns a range of all the prizes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.trantorinc.synergy.game.core.model.impl.PrizeModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of prizes
	 * @param end the upper bound of the range of prizes (not inclusive)
	 * @return the range of prizes
	 */
	@Override
	public java.util.List<com.trantorinc.synergy.game.core.model.Prize>
		getPrizes(int start, int end) {

		return _prizeLocalService.getPrizes(start, end);
	}

	/**
	 * Returns the number of prizes.
	 *
	 * @return the number of prizes
	 */
	@Override
	public int getPrizesCount() {
		return _prizeLocalService.getPrizesCount();
	}

	/**
	 * Updates the prize in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect PrizeLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param prize the prize
	 * @return the prize that was updated
	 */
	@Override
	public com.trantorinc.synergy.game.core.model.Prize updatePrize(
		com.trantorinc.synergy.game.core.model.Prize prize) {

		return _prizeLocalService.updatePrize(prize);
	}

	@Override
	public PrizeLocalService getWrappedService() {
		return _prizeLocalService;
	}

	@Override
	public void setWrappedService(PrizeLocalService prizeLocalService) {
		_prizeLocalService = prizeLocalService;
	}

	private PrizeLocalService _prizeLocalService;

}