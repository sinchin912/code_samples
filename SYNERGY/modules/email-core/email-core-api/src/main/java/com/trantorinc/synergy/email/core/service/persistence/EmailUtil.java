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

package com.trantorinc.synergy.email.core.service.persistence;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.trantorinc.synergy.email.core.model.Email;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the email service. This utility wraps <code>com.trantorinc.synergy.email.core.service.persistence.impl.EmailPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see EmailPersistence
 * @generated
 */
public class EmailUtil {

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
	public static void clearCache(Email email) {
		getPersistence().clearCache(email);
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
	public static Map<Serializable, Email> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<Email> findWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Email> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Email> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<Email> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static Email update(Email email) {
		return getPersistence().update(email);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static Email update(Email email, ServiceContext serviceContext) {
		return getPersistence().update(email, serviceContext);
	}

	/**
	 * Caches the email in the entity cache if it is enabled.
	 *
	 * @param email the email
	 */
	public static void cacheResult(Email email) {
		getPersistence().cacheResult(email);
	}

	/**
	 * Caches the emails in the entity cache if it is enabled.
	 *
	 * @param emails the emails
	 */
	public static void cacheResult(List<Email> emails) {
		getPersistence().cacheResult(emails);
	}

	/**
	 * Creates a new email with the primary key. Does not add the email to the database.
	 *
	 * @param emailId the primary key for the new email
	 * @return the new email
	 */
	public static Email create(long emailId) {
		return getPersistence().create(emailId);
	}

	/**
	 * Removes the email with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param emailId the primary key of the email
	 * @return the email that was removed
	 * @throws NoSuchEmailException if a email with the primary key could not be found
	 */
	public static Email remove(long emailId)
		throws com.trantorinc.synergy.email.core.exception.
			NoSuchEmailException {

		return getPersistence().remove(emailId);
	}

	public static Email updateImpl(Email email) {
		return getPersistence().updateImpl(email);
	}

	/**
	 * Returns the email with the primary key or throws a <code>NoSuchEmailException</code> if it could not be found.
	 *
	 * @param emailId the primary key of the email
	 * @return the email
	 * @throws NoSuchEmailException if a email with the primary key could not be found
	 */
	public static Email findByPrimaryKey(long emailId)
		throws com.trantorinc.synergy.email.core.exception.
			NoSuchEmailException {

		return getPersistence().findByPrimaryKey(emailId);
	}

	/**
	 * Returns the email with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param emailId the primary key of the email
	 * @return the email, or <code>null</code> if a email with the primary key could not be found
	 */
	public static Email fetchByPrimaryKey(long emailId) {
		return getPersistence().fetchByPrimaryKey(emailId);
	}

	/**
	 * Returns all the emails.
	 *
	 * @return the emails
	 */
	public static List<Email> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the emails.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EmailModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of emails
	 * @param end the upper bound of the range of emails (not inclusive)
	 * @return the range of emails
	 */
	public static List<Email> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the emails.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EmailModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of emails
	 * @param end the upper bound of the range of emails (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of emails
	 */
	public static List<Email> findAll(
		int start, int end, OrderByComparator<Email> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the emails.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EmailModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of emails
	 * @param end the upper bound of the range of emails (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of emails
	 */
	public static List<Email> findAll(
		int start, int end, OrderByComparator<Email> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the emails from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of emails.
	 *
	 * @return the number of emails
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static EmailPersistence getPersistence() {
		return _persistence;
	}

	private static volatile EmailPersistence _persistence;

}