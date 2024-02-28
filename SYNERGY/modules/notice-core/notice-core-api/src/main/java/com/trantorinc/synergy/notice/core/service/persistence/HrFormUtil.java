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

package com.trantorinc.synergy.notice.core.service.persistence;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.trantorinc.synergy.notice.core.model.HrForm;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the hr form service. This utility wraps <code>com.trantorinc.synergy.notice.core.service.persistence.impl.HrFormPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see HrFormPersistence
 * @generated
 */
public class HrFormUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(HrForm hrForm) {
		getPersistence().clearCache(hrForm);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#fetchByPrimaryKeys(Set)
	 */
	public static Map<Serializable, HrForm> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<HrForm> findWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<HrForm> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<HrForm> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<HrForm> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static HrForm update(HrForm hrForm) {
		return getPersistence().update(hrForm);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static HrForm update(HrForm hrForm, ServiceContext serviceContext) {
		return getPersistence().update(hrForm, serviceContext);
	}

	/**
	 * Caches the hr form in the entity cache if it is enabled.
	 *
	 * @param hrForm the hr form
	 */
	public static void cacheResult(HrForm hrForm) {
		getPersistence().cacheResult(hrForm);
	}

	/**
	 * Caches the hr forms in the entity cache if it is enabled.
	 *
	 * @param hrForms the hr forms
	 */
	public static void cacheResult(List<HrForm> hrForms) {
		getPersistence().cacheResult(hrForms);
	}

	/**
	 * Creates a new hr form with the primary key. Does not add the hr form to the database.
	 *
	 * @param id the primary key for the new hr form
	 * @return the new hr form
	 */
	public static HrForm create(long id) {
		return getPersistence().create(id);
	}

	/**
	 * Removes the hr form with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the hr form
	 * @return the hr form that was removed
	 * @throws NoSuchHrFormException if a hr form with the primary key could not be found
	 */
	public static HrForm remove(long id)
		throws com.trantorinc.synergy.notice.core.exception.
			NoSuchHrFormException {

		return getPersistence().remove(id);
	}

	public static HrForm updateImpl(HrForm hrForm) {
		return getPersistence().updateImpl(hrForm);
	}

	/**
	 * Returns the hr form with the primary key or throws a <code>NoSuchHrFormException</code> if it could not be found.
	 *
	 * @param id the primary key of the hr form
	 * @return the hr form
	 * @throws NoSuchHrFormException if a hr form with the primary key could not be found
	 */
	public static HrForm findByPrimaryKey(long id)
		throws com.trantorinc.synergy.notice.core.exception.
			NoSuchHrFormException {

		return getPersistence().findByPrimaryKey(id);
	}

	/**
	 * Returns the hr form with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the hr form
	 * @return the hr form, or <code>null</code> if a hr form with the primary key could not be found
	 */
	public static HrForm fetchByPrimaryKey(long id) {
		return getPersistence().fetchByPrimaryKey(id);
	}

	/**
	 * Returns all the hr forms.
	 *
	 * @return the hr forms
	 */
	public static List<HrForm> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the hr forms.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HrFormModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of hr forms
	 * @param end the upper bound of the range of hr forms (not inclusive)
	 * @return the range of hr forms
	 */
	public static List<HrForm> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the hr forms.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HrFormModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of hr forms
	 * @param end the upper bound of the range of hr forms (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of hr forms
	 */
	public static List<HrForm> findAll(
		int start, int end, OrderByComparator<HrForm> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the hr forms.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HrFormModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of hr forms
	 * @param end the upper bound of the range of hr forms (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of hr forms
	 */
	public static List<HrForm> findAll(
		int start, int end, OrderByComparator<HrForm> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the hr forms from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of hr forms.
	 *
	 * @return the number of hr forms
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static HrFormPersistence getPersistence() {
		return _persistence;
	}

	private static volatile HrFormPersistence _persistence;

}