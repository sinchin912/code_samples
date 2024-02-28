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

import com.liferay.petra.sql.dsl.query.DSLQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.trantorinc.synergy.notice.core.model.Resignation;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service utility for Resignation. This utility wraps
 * <code>com.trantorinc.synergy.notice.core.service.impl.ResignationLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see ResignationLocalService
 * @generated
 */
public class ResignationLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.trantorinc.synergy.notice.core.service.impl.ResignationLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the resignation to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ResignationLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param resignation the resignation
	 * @return the resignation that was added
	 */
	public static Resignation addResignation(Resignation resignation) {
		return getService().addResignation(resignation);
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel createPersistedModel(
			Serializable primaryKeyObj)
		throws PortalException {

		return getService().createPersistedModel(primaryKeyObj);
	}

	/**
	 * Creates a new resignation with the primary key. Does not add the resignation to the database.
	 *
	 * @param id the primary key for the new resignation
	 * @return the new resignation
	 */
	public static Resignation createResignation(long id) {
		return getService().createResignation(id);
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel deletePersistedModel(
			PersistedModel persistedModel)
		throws PortalException {

		return getService().deletePersistedModel(persistedModel);
	}

	/**
	 * Deletes the resignation with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ResignationLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param id the primary key of the resignation
	 * @return the resignation that was removed
	 * @throws PortalException if a resignation with the primary key could not be found
	 */
	public static Resignation deleteResignation(long id)
		throws PortalException {

		return getService().deleteResignation(id);
	}

	/**
	 * Deletes the resignation from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ResignationLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param resignation the resignation
	 * @return the resignation that was removed
	 */
	public static Resignation deleteResignation(Resignation resignation) {
		return getService().deleteResignation(resignation);
	}

	public static <T> T dslQuery(DSLQuery dslQuery) {
		return getService().dslQuery(dslQuery);
	}

	public static int dslQueryCount(DSLQuery dslQuery) {
		return getService().dslQueryCount(dslQuery);
	}

	public static DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	public static <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.trantorinc.synergy.notice.core.model.impl.ResignationModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	public static <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.trantorinc.synergy.notice.core.model.impl.ResignationModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	public static <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<T> orderByComparator) {

		return getService().dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(DynamicQuery dynamicQuery) {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(
		DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static Resignation fetchResignation(long id) {
		return getService().fetchResignation(id);
	}

	public static List<Resignation> findActiveResignationEntries(
		java.util.Date date) {

		return getService().findActiveResignationEntries(date);
	}

	public static List<Resignation> findActiveResignationsByLastWorkingDate(
		java.util.Date date) {

		return getService().findActiveResignationsByLastWorkingDate(date);
	}

	public static List<Resignation> findAllResignationsByHrState() {
		return getService().findAllResignationsByHrState();
	}

	public static List<Resignation> findEntryByManagerEmail(String email) {
		return getService().findEntryByManagerEmail(email);
	}

	public static List<Resignation> findResignationByEcode(String ecode) {
		return getService().findResignationByEcode(ecode);
	}

	public static List<Resignation> findResignationsByLastWorkingDate(
		java.util.Date lastWorkingDay) {

		return getService().findResignationsByLastWorkingDate(lastWorkingDay);
	}

	public static List<Integer> findUniqueResignationYears() {
		return getService().findUniqueResignationYears();
	}

	public static List<Resignation> findYearlyEntriesbyCreationDate(
		java.util.Date startDate, java.util.Date endDate) {

		return getService().findYearlyEntriesbyCreationDate(startDate, endDate);
	}

	public static List<Resignation> findYearlyEntriesbyLWD(
		java.util.Date startDate, java.util.Date endDate) {

		return getService().findYearlyEntriesbyLWD(startDate, endDate);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	public static
		com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
			getIndexableActionableDynamicQuery() {

		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns the resignation with the primary key.
	 *
	 * @param id the primary key of the resignation
	 * @return the resignation
	 * @throws PortalException if a resignation with the primary key could not be found
	 */
	public static Resignation getResignation(long id) throws PortalException {
		return getService().getResignation(id);
	}

	/**
	 * Returns a range of all the resignations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.trantorinc.synergy.notice.core.model.impl.ResignationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of resignations
	 * @param end the upper bound of the range of resignations (not inclusive)
	 * @return the range of resignations
	 */
	public static List<Resignation> getResignations(int start, int end) {
		return getService().getResignations(start, end);
	}

	/**
	 * Returns the number of resignations.
	 *
	 * @return the number of resignations
	 */
	public static int getResignationsCount() {
		return getService().getResignationsCount();
	}

	/**
	 * Updates the resignation in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ResignationLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param resignation the resignation
	 * @return the resignation that was updated
	 */
	public static Resignation updateResignation(Resignation resignation) {
		return getService().updateResignation(resignation);
	}

	public static ResignationLocalService getService() {
		return _service;
	}

	private static volatile ResignationLocalService _service;

}