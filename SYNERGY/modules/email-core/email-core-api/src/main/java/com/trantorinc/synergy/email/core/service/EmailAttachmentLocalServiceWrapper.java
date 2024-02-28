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

package com.trantorinc.synergy.email.core.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link EmailAttachmentLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see EmailAttachmentLocalService
 * @generated
 */
public class EmailAttachmentLocalServiceWrapper
	implements EmailAttachmentLocalService,
			   ServiceWrapper<EmailAttachmentLocalService> {

	public EmailAttachmentLocalServiceWrapper() {
		this(null);
	}

	public EmailAttachmentLocalServiceWrapper(
		EmailAttachmentLocalService emailAttachmentLocalService) {

		_emailAttachmentLocalService = emailAttachmentLocalService;
	}

	/**
	 * Adds the email attachment to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect EmailAttachmentLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param emailAttachment the email attachment
	 * @return the email attachment that was added
	 */
	@Override
	public com.trantorinc.synergy.email.core.model.EmailAttachment
		addEmailAttachment(
			com.trantorinc.synergy.email.core.model.EmailAttachment
				emailAttachment) {

		return _emailAttachmentLocalService.addEmailAttachment(emailAttachment);
	}

	/**
	 * Creates a new email attachment with the primary key. Does not add the email attachment to the database.
	 *
	 * @param emailAttachmentId the primary key for the new email attachment
	 * @return the new email attachment
	 */
	@Override
	public com.trantorinc.synergy.email.core.model.EmailAttachment
		createEmailAttachment(long emailAttachmentId) {

		return _emailAttachmentLocalService.createEmailAttachment(
			emailAttachmentId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _emailAttachmentLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Deletes the email attachment from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect EmailAttachmentLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param emailAttachment the email attachment
	 * @return the email attachment that was removed
	 */
	@Override
	public com.trantorinc.synergy.email.core.model.EmailAttachment
		deleteEmailAttachment(
			com.trantorinc.synergy.email.core.model.EmailAttachment
				emailAttachment) {

		return _emailAttachmentLocalService.deleteEmailAttachment(
			emailAttachment);
	}

	/**
	 * Deletes the email attachment with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect EmailAttachmentLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param emailAttachmentId the primary key of the email attachment
	 * @return the email attachment that was removed
	 * @throws PortalException if a email attachment with the primary key could not be found
	 */
	@Override
	public com.trantorinc.synergy.email.core.model.EmailAttachment
			deleteEmailAttachment(long emailAttachmentId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _emailAttachmentLocalService.deleteEmailAttachment(
			emailAttachmentId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _emailAttachmentLocalService.deletePersistedModel(
			persistedModel);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _emailAttachmentLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _emailAttachmentLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _emailAttachmentLocalService.dynamicQuery();
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

		return _emailAttachmentLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.trantorinc.synergy.email.core.model.impl.EmailAttachmentModelImpl</code>.
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

		return _emailAttachmentLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.trantorinc.synergy.email.core.model.impl.EmailAttachmentModelImpl</code>.
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

		return _emailAttachmentLocalService.dynamicQuery(
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

		return _emailAttachmentLocalService.dynamicQueryCount(dynamicQuery);
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

		return _emailAttachmentLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.trantorinc.synergy.email.core.model.EmailAttachment
		fetchEmailAttachment(long emailAttachmentId) {

		return _emailAttachmentLocalService.fetchEmailAttachment(
			emailAttachmentId);
	}

	@Override
	public java.util.List
		<com.trantorinc.synergy.email.core.model.EmailAttachment>
			findEmailAttachmentByEmailId(long emailId) {

		return _emailAttachmentLocalService.findEmailAttachmentByEmailId(
			emailId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _emailAttachmentLocalService.getActionableDynamicQuery();
	}

	/**
	 * Returns the email attachment with the primary key.
	 *
	 * @param emailAttachmentId the primary key of the email attachment
	 * @return the email attachment
	 * @throws PortalException if a email attachment with the primary key could not be found
	 */
	@Override
	public com.trantorinc.synergy.email.core.model.EmailAttachment
			getEmailAttachment(long emailAttachmentId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _emailAttachmentLocalService.getEmailAttachment(
			emailAttachmentId);
	}

	/**
	 * Returns a range of all the email attachments.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.trantorinc.synergy.email.core.model.impl.EmailAttachmentModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of email attachments
	 * @param end the upper bound of the range of email attachments (not inclusive)
	 * @return the range of email attachments
	 */
	@Override
	public java.util.List
		<com.trantorinc.synergy.email.core.model.EmailAttachment>
			getEmailAttachments(int start, int end) {

		return _emailAttachmentLocalService.getEmailAttachments(start, end);
	}

	/**
	 * Returns the number of email attachments.
	 *
	 * @return the number of email attachments
	 */
	@Override
	public int getEmailAttachmentsCount() {
		return _emailAttachmentLocalService.getEmailAttachmentsCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _emailAttachmentLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _emailAttachmentLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _emailAttachmentLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the email attachment in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect EmailAttachmentLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param emailAttachment the email attachment
	 * @return the email attachment that was updated
	 */
	@Override
	public com.trantorinc.synergy.email.core.model.EmailAttachment
		updateEmailAttachment(
			com.trantorinc.synergy.email.core.model.EmailAttachment
				emailAttachment) {

		return _emailAttachmentLocalService.updateEmailAttachment(
			emailAttachment);
	}

	@Override
	public EmailAttachmentLocalService getWrappedService() {
		return _emailAttachmentLocalService;
	}

	@Override
	public void setWrappedService(
		EmailAttachmentLocalService emailAttachmentLocalService) {

		_emailAttachmentLocalService = emailAttachmentLocalService;
	}

	private EmailAttachmentLocalService _emailAttachmentLocalService;

}