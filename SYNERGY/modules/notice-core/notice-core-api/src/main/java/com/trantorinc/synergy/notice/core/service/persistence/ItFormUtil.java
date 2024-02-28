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

import com.trantorinc.synergy.notice.core.model.ItForm;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the it form service. This utility wraps <code>com.trantorinc.synergy.notice.core.service.persistence.impl.ItFormPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ItFormPersistence
 * @generated
 */
public class ItFormUtil {

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
	public static void clearCache(ItForm itForm) {
		getPersistence().clearCache(itForm);
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
	public static Map<Serializable, ItForm> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<ItForm> findWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ItForm> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ItForm> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<ItForm> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static ItForm update(ItForm itForm) {
		return getPersistence().update(itForm);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static ItForm update(ItForm itForm, ServiceContext serviceContext) {
		return getPersistence().update(itForm, serviceContext);
	}

	/**
	 * Caches the it form in the entity cache if it is enabled.
	 *
	 * @param itForm the it form
	 */
	public static void cacheResult(ItForm itForm) {
		getPersistence().cacheResult(itForm);
	}

	/**
	 * Caches the it forms in the entity cache if it is enabled.
	 *
	 * @param itForms the it forms
	 */
	public static void cacheResult(List<ItForm> itForms) {
		getPersistence().cacheResult(itForms);
	}

	/**
	 * Creates a new it form with the primary key. Does not add the it form to the database.
	 *
	 * @param id the primary key for the new it form
	 * @return the new it form
	 */
	public static ItForm create(long id) {
		return getPersistence().create(id);
	}

	/**
	 * Removes the it form with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the it form
	 * @return the it form that was removed
	 * @throws NoSuchItFormException if a it form with the primary key could not be found
	 */
	public static ItForm remove(long id)
		throws com.trantorinc.synergy.notice.core.exception.
			NoSuchItFormException {

		return getPersistence().remove(id);
	}

	public static ItForm updateImpl(ItForm itForm) {
		return getPersistence().updateImpl(itForm);
	}

	/**
	 * Returns the it form with the primary key or throws a <code>NoSuchItFormException</code> if it could not be found.
	 *
	 * @param id the primary key of the it form
	 * @return the it form
	 * @throws NoSuchItFormException if a it form with the primary key could not be found
	 */
	public static ItForm findByPrimaryKey(long id)
		throws com.trantorinc.synergy.notice.core.exception.
			NoSuchItFormException {

		return getPersistence().findByPrimaryKey(id);
	}

	/**
	 * Returns the it form with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the it form
	 * @return the it form, or <code>null</code> if a it form with the primary key could not be found
	 */
	public static ItForm fetchByPrimaryKey(long id) {
		return getPersistence().fetchByPrimaryKey(id);
	}

	/**
	 * Returns all the it forms.
	 *
	 * @return the it forms
	 */
	public static List<ItForm> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the it forms.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ItFormModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of it forms
	 * @param end the upper bound of the range of it forms (not inclusive)
	 * @return the range of it forms
	 */
	public static List<ItForm> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the it forms.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ItFormModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of it forms
	 * @param end the upper bound of the range of it forms (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of it forms
	 */
	public static List<ItForm> findAll(
		int start, int end, OrderByComparator<ItForm> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the it forms.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ItFormModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of it forms
	 * @param end the upper bound of the range of it forms (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of it forms
	 */
	public static List<ItForm> findAll(
		int start, int end, OrderByComparator<ItForm> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the it forms from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of it forms.
	 *
	 * @return the number of it forms
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static ItFormPersistence getPersistence() {
		return _persistence;
	}

	private static volatile ItFormPersistence _persistence;

}