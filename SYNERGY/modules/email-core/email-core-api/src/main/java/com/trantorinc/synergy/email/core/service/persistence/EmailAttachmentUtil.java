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

import com.trantorinc.synergy.email.core.model.EmailAttachment;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the email attachment service. This utility wraps <code>com.trantorinc.synergy.email.core.service.persistence.impl.EmailAttachmentPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see EmailAttachmentPersistence
 * @generated
 */
public class EmailAttachmentUtil {

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
	public static void clearCache(EmailAttachment emailAttachment) {
		getPersistence().clearCache(emailAttachment);
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
	public static Map<Serializable, EmailAttachment> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<EmailAttachment> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<EmailAttachment> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<EmailAttachment> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<EmailAttachment> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static EmailAttachment update(EmailAttachment emailAttachment) {
		return getPersistence().update(emailAttachment);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static EmailAttachment update(
		EmailAttachment emailAttachment, ServiceContext serviceContext) {

		return getPersistence().update(emailAttachment, serviceContext);
	}

	/**
	 * Caches the email attachment in the entity cache if it is enabled.
	 *
	 * @param emailAttachment the email attachment
	 */
	public static void cacheResult(EmailAttachment emailAttachment) {
		getPersistence().cacheResult(emailAttachment);
	}

	/**
	 * Caches the email attachments in the entity cache if it is enabled.
	 *
	 * @param emailAttachments the email attachments
	 */
	public static void cacheResult(List<EmailAttachment> emailAttachments) {
		getPersistence().cacheResult(emailAttachments);
	}

	/**
	 * Creates a new email attachment with the primary key. Does not add the email attachment to the database.
	 *
	 * @param emailAttachmentId the primary key for the new email attachment
	 * @return the new email attachment
	 */
	public static EmailAttachment create(long emailAttachmentId) {
		return getPersistence().create(emailAttachmentId);
	}

	/**
	 * Removes the email attachment with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param emailAttachmentId the primary key of the email attachment
	 * @return the email attachment that was removed
	 * @throws NoSuchEmailAttachmentException if a email attachment with the primary key could not be found
	 */
	public static EmailAttachment remove(long emailAttachmentId)
		throws com.trantorinc.synergy.email.core.exception.
			NoSuchEmailAttachmentException {

		return getPersistence().remove(emailAttachmentId);
	}

	public static EmailAttachment updateImpl(EmailAttachment emailAttachment) {
		return getPersistence().updateImpl(emailAttachment);
	}

	/**
	 * Returns the email attachment with the primary key or throws a <code>NoSuchEmailAttachmentException</code> if it could not be found.
	 *
	 * @param emailAttachmentId the primary key of the email attachment
	 * @return the email attachment
	 * @throws NoSuchEmailAttachmentException if a email attachment with the primary key could not be found
	 */
	public static EmailAttachment findByPrimaryKey(long emailAttachmentId)
		throws com.trantorinc.synergy.email.core.exception.
			NoSuchEmailAttachmentException {

		return getPersistence().findByPrimaryKey(emailAttachmentId);
	}

	/**
	 * Returns the email attachment with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param emailAttachmentId the primary key of the email attachment
	 * @return the email attachment, or <code>null</code> if a email attachment with the primary key could not be found
	 */
	public static EmailAttachment fetchByPrimaryKey(long emailAttachmentId) {
		return getPersistence().fetchByPrimaryKey(emailAttachmentId);
	}

	/**
	 * Returns all the email attachments.
	 *
	 * @return the email attachments
	 */
	public static List<EmailAttachment> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the email attachments.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EmailAttachmentModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of email attachments
	 * @param end the upper bound of the range of email attachments (not inclusive)
	 * @return the range of email attachments
	 */
	public static List<EmailAttachment> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the email attachments.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EmailAttachmentModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of email attachments
	 * @param end the upper bound of the range of email attachments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of email attachments
	 */
	public static List<EmailAttachment> findAll(
		int start, int end,
		OrderByComparator<EmailAttachment> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the email attachments.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EmailAttachmentModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of email attachments
	 * @param end the upper bound of the range of email attachments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of email attachments
	 */
	public static List<EmailAttachment> findAll(
		int start, int end,
		OrderByComparator<EmailAttachment> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the email attachments from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of email attachments.
	 *
	 * @return the number of email attachments
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static EmailAttachmentPersistence getPersistence() {
		return _persistence;
	}

	private static volatile EmailAttachmentPersistence _persistence;

}