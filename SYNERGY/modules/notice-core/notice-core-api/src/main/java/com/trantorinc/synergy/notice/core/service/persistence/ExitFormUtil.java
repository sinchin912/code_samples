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

import com.trantorinc.synergy.notice.core.model.ExitForm;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the exit form service. This utility wraps <code>com.trantorinc.synergy.notice.core.service.persistence.impl.ExitFormPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ExitFormPersistence
 * @generated
 */
public class ExitFormUtil {

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
	public static void clearCache(ExitForm exitForm) {
		getPersistence().clearCache(exitForm);
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
	public static Map<Serializable, ExitForm> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<ExitForm> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ExitForm> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ExitForm> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<ExitForm> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static ExitForm update(ExitForm exitForm) {
		return getPersistence().update(exitForm);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static ExitForm update(
		ExitForm exitForm, ServiceContext serviceContext) {

		return getPersistence().update(exitForm, serviceContext);
	}

	/**
	 * Caches the exit form in the entity cache if it is enabled.
	 *
	 * @param exitForm the exit form
	 */
	public static void cacheResult(ExitForm exitForm) {
		getPersistence().cacheResult(exitForm);
	}

	/**
	 * Caches the exit forms in the entity cache if it is enabled.
	 *
	 * @param exitForms the exit forms
	 */
	public static void cacheResult(List<ExitForm> exitForms) {
		getPersistence().cacheResult(exitForms);
	}

	/**
	 * Creates a new exit form with the primary key. Does not add the exit form to the database.
	 *
	 * @param id the primary key for the new exit form
	 * @return the new exit form
	 */
	public static ExitForm create(long id) {
		return getPersistence().create(id);
	}

	/**
	 * Removes the exit form with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the exit form
	 * @return the exit form that was removed
	 * @throws NoSuchExitFormException if a exit form with the primary key could not be found
	 */
	public static ExitForm remove(long id)
		throws com.trantorinc.synergy.notice.core.exception.
			NoSuchExitFormException {

		return getPersistence().remove(id);
	}

	public static ExitForm updateImpl(ExitForm exitForm) {
		return getPersistence().updateImpl(exitForm);
	}

	/**
	 * Returns the exit form with the primary key or throws a <code>NoSuchExitFormException</code> if it could not be found.
	 *
	 * @param id the primary key of the exit form
	 * @return the exit form
	 * @throws NoSuchExitFormException if a exit form with the primary key could not be found
	 */
	public static ExitForm findByPrimaryKey(long id)
		throws com.trantorinc.synergy.notice.core.exception.
			NoSuchExitFormException {

		return getPersistence().findByPrimaryKey(id);
	}

	/**
	 * Returns the exit form with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the exit form
	 * @return the exit form, or <code>null</code> if a exit form with the primary key could not be found
	 */
	public static ExitForm fetchByPrimaryKey(long id) {
		return getPersistence().fetchByPrimaryKey(id);
	}

	/**
	 * Returns all the exit forms.
	 *
	 * @return the exit forms
	 */
	public static List<ExitForm> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the exit forms.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ExitFormModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of exit forms
	 * @param end the upper bound of the range of exit forms (not inclusive)
	 * @return the range of exit forms
	 */
	public static List<ExitForm> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the exit forms.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ExitFormModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of exit forms
	 * @param end the upper bound of the range of exit forms (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of exit forms
	 */
	public static List<ExitForm> findAll(
		int start, int end, OrderByComparator<ExitForm> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the exit forms.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ExitFormModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of exit forms
	 * @param end the upper bound of the range of exit forms (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of exit forms
	 */
	public static List<ExitForm> findAll(
		int start, int end, OrderByComparator<ExitForm> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the exit forms from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of exit forms.
	 *
	 * @return the number of exit forms
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static ExitFormPersistence getPersistence() {
		return _persistence;
	}

	private static volatile ExitFormPersistence _persistence;

}