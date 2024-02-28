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

import com.trantorinc.synergy.notice.core.model.ManagerForm;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the manager form service. This utility wraps <code>com.trantorinc.synergy.notice.core.service.persistence.impl.ManagerFormPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ManagerFormPersistence
 * @generated
 */
public class ManagerFormUtil {

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
	public static void clearCache(ManagerForm managerForm) {
		getPersistence().clearCache(managerForm);
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
	public static Map<Serializable, ManagerForm> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<ManagerForm> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ManagerForm> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ManagerForm> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<ManagerForm> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static ManagerForm update(ManagerForm managerForm) {
		return getPersistence().update(managerForm);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static ManagerForm update(
		ManagerForm managerForm, ServiceContext serviceContext) {

		return getPersistence().update(managerForm, serviceContext);
	}

	/**
	 * Caches the manager form in the entity cache if it is enabled.
	 *
	 * @param managerForm the manager form
	 */
	public static void cacheResult(ManagerForm managerForm) {
		getPersistence().cacheResult(managerForm);
	}

	/**
	 * Caches the manager forms in the entity cache if it is enabled.
	 *
	 * @param managerForms the manager forms
	 */
	public static void cacheResult(List<ManagerForm> managerForms) {
		getPersistence().cacheResult(managerForms);
	}

	/**
	 * Creates a new manager form with the primary key. Does not add the manager form to the database.
	 *
	 * @param id the primary key for the new manager form
	 * @return the new manager form
	 */
	public static ManagerForm create(long id) {
		return getPersistence().create(id);
	}

	/**
	 * Removes the manager form with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the manager form
	 * @return the manager form that was removed
	 * @throws NoSuchManagerFormException if a manager form with the primary key could not be found
	 */
	public static ManagerForm remove(long id)
		throws com.trantorinc.synergy.notice.core.exception.
			NoSuchManagerFormException {

		return getPersistence().remove(id);
	}

	public static ManagerForm updateImpl(ManagerForm managerForm) {
		return getPersistence().updateImpl(managerForm);
	}

	/**
	 * Returns the manager form with the primary key or throws a <code>NoSuchManagerFormException</code> if it could not be found.
	 *
	 * @param id the primary key of the manager form
	 * @return the manager form
	 * @throws NoSuchManagerFormException if a manager form with the primary key could not be found
	 */
	public static ManagerForm findByPrimaryKey(long id)
		throws com.trantorinc.synergy.notice.core.exception.
			NoSuchManagerFormException {

		return getPersistence().findByPrimaryKey(id);
	}

	/**
	 * Returns the manager form with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the manager form
	 * @return the manager form, or <code>null</code> if a manager form with the primary key could not be found
	 */
	public static ManagerForm fetchByPrimaryKey(long id) {
		return getPersistence().fetchByPrimaryKey(id);
	}

	/**
	 * Returns all the manager forms.
	 *
	 * @return the manager forms
	 */
	public static List<ManagerForm> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the manager forms.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ManagerFormModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of manager forms
	 * @param end the upper bound of the range of manager forms (not inclusive)
	 * @return the range of manager forms
	 */
	public static List<ManagerForm> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the manager forms.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ManagerFormModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of manager forms
	 * @param end the upper bound of the range of manager forms (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of manager forms
	 */
	public static List<ManagerForm> findAll(
		int start, int end, OrderByComparator<ManagerForm> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the manager forms.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ManagerFormModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of manager forms
	 * @param end the upper bound of the range of manager forms (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of manager forms
	 */
	public static List<ManagerForm> findAll(
		int start, int end, OrderByComparator<ManagerForm> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the manager forms from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of manager forms.
	 *
	 * @return the number of manager forms
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static ManagerFormPersistence getPersistence() {
		return _persistence;
	}

	private static volatile ManagerFormPersistence _persistence;

}