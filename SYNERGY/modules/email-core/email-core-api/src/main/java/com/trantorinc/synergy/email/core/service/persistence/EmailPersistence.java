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

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import com.trantorinc.synergy.email.core.exception.NoSuchEmailException;
import com.trantorinc.synergy.email.core.model.Email;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the email service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see EmailUtil
 * @generated
 */
@ProviderType
public interface EmailPersistence extends BasePersistence<Email> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link EmailUtil} to access the email persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Caches the email in the entity cache if it is enabled.
	 *
	 * @param email the email
	 */
	public void cacheResult(Email email);

	/**
	 * Caches the emails in the entity cache if it is enabled.
	 *
	 * @param emails the emails
	 */
	public void cacheResult(java.util.List<Email> emails);

	/**
	 * Creates a new email with the primary key. Does not add the email to the database.
	 *
	 * @param emailId the primary key for the new email
	 * @return the new email
	 */
	public Email create(long emailId);

	/**
	 * Removes the email with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param emailId the primary key of the email
	 * @return the email that was removed
	 * @throws NoSuchEmailException if a email with the primary key could not be found
	 */
	public Email remove(long emailId) throws NoSuchEmailException;

	public Email updateImpl(Email email);

	/**
	 * Returns the email with the primary key or throws a <code>NoSuchEmailException</code> if it could not be found.
	 *
	 * @param emailId the primary key of the email
	 * @return the email
	 * @throws NoSuchEmailException if a email with the primary key could not be found
	 */
	public Email findByPrimaryKey(long emailId) throws NoSuchEmailException;

	/**
	 * Returns the email with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param emailId the primary key of the email
	 * @return the email, or <code>null</code> if a email with the primary key could not be found
	 */
	public Email fetchByPrimaryKey(long emailId);

	/**
	 * Returns all the emails.
	 *
	 * @return the emails
	 */
	public java.util.List<Email> findAll();

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
	public java.util.List<Email> findAll(int start, int end);

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
	public java.util.List<Email> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Email>
			orderByComparator);

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
	public java.util.List<Email> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Email>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the emails from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of emails.
	 *
	 * @return the number of emails
	 */
	public int countAll();

}