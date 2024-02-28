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

import com.trantorinc.synergy.email.core.exception.NoSuchEmailAttachmentException;
import com.trantorinc.synergy.email.core.model.EmailAttachment;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the email attachment service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see EmailAttachmentUtil
 * @generated
 */
@ProviderType
public interface EmailAttachmentPersistence
	extends BasePersistence<EmailAttachment> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link EmailAttachmentUtil} to access the email attachment persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Caches the email attachment in the entity cache if it is enabled.
	 *
	 * @param emailAttachment the email attachment
	 */
	public void cacheResult(EmailAttachment emailAttachment);

	/**
	 * Caches the email attachments in the entity cache if it is enabled.
	 *
	 * @param emailAttachments the email attachments
	 */
	public void cacheResult(java.util.List<EmailAttachment> emailAttachments);

	/**
	 * Creates a new email attachment with the primary key. Does not add the email attachment to the database.
	 *
	 * @param emailAttachmentId the primary key for the new email attachment
	 * @return the new email attachment
	 */
	public EmailAttachment create(long emailAttachmentId);

	/**
	 * Removes the email attachment with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param emailAttachmentId the primary key of the email attachment
	 * @return the email attachment that was removed
	 * @throws NoSuchEmailAttachmentException if a email attachment with the primary key could not be found
	 */
	public EmailAttachment remove(long emailAttachmentId)
		throws NoSuchEmailAttachmentException;

	public EmailAttachment updateImpl(EmailAttachment emailAttachment);

	/**
	 * Returns the email attachment with the primary key or throws a <code>NoSuchEmailAttachmentException</code> if it could not be found.
	 *
	 * @param emailAttachmentId the primary key of the email attachment
	 * @return the email attachment
	 * @throws NoSuchEmailAttachmentException if a email attachment with the primary key could not be found
	 */
	public EmailAttachment findByPrimaryKey(long emailAttachmentId)
		throws NoSuchEmailAttachmentException;

	/**
	 * Returns the email attachment with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param emailAttachmentId the primary key of the email attachment
	 * @return the email attachment, or <code>null</code> if a email attachment with the primary key could not be found
	 */
	public EmailAttachment fetchByPrimaryKey(long emailAttachmentId);

	/**
	 * Returns all the email attachments.
	 *
	 * @return the email attachments
	 */
	public java.util.List<EmailAttachment> findAll();

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
	public java.util.List<EmailAttachment> findAll(int start, int end);

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
	public java.util.List<EmailAttachment> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<EmailAttachment>
			orderByComparator);

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
	public java.util.List<EmailAttachment> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<EmailAttachment>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the email attachments from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of email attachments.
	 *
	 * @return the number of email attachments
	 */
	public int countAll();

}