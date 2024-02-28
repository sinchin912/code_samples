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

package com.trantorinc.synergy.email.core.model;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link EmailAttachment}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see EmailAttachment
 * @generated
 */
public class EmailAttachmentWrapper
	extends BaseModelWrapper<EmailAttachment>
	implements EmailAttachment, ModelWrapper<EmailAttachment> {

	public EmailAttachmentWrapper(EmailAttachment emailAttachment) {
		super(emailAttachment);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("emailAttachmentId", getEmailAttachmentId());
		attributes.put("emailId", getEmailId());
		attributes.put("attachmentName", getAttachmentName());
		attributes.put("attachmentFileId", getAttachmentFileId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long emailAttachmentId = (Long)attributes.get("emailAttachmentId");

		if (emailAttachmentId != null) {
			setEmailAttachmentId(emailAttachmentId);
		}

		Long emailId = (Long)attributes.get("emailId");

		if (emailId != null) {
			setEmailId(emailId);
		}

		String attachmentName = (String)attributes.get("attachmentName");

		if (attachmentName != null) {
			setAttachmentName(attachmentName);
		}

		String attachmentFileId = (String)attributes.get("attachmentFileId");

		if (attachmentFileId != null) {
			setAttachmentFileId(attachmentFileId);
		}
	}

	@Override
	public EmailAttachment cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the attachment file ID of this email attachment.
	 *
	 * @return the attachment file ID of this email attachment
	 */
	@Override
	public String getAttachmentFileId() {
		return model.getAttachmentFileId();
	}

	/**
	 * Returns the attachment name of this email attachment.
	 *
	 * @return the attachment name of this email attachment
	 */
	@Override
	public String getAttachmentName() {
		return model.getAttachmentName();
	}

	/**
	 * Returns the email attachment ID of this email attachment.
	 *
	 * @return the email attachment ID of this email attachment
	 */
	@Override
	public long getEmailAttachmentId() {
		return model.getEmailAttachmentId();
	}

	/**
	 * Returns the email ID of this email attachment.
	 *
	 * @return the email ID of this email attachment
	 */
	@Override
	public long getEmailId() {
		return model.getEmailId();
	}

	/**
	 * Returns the primary key of this email attachment.
	 *
	 * @return the primary key of this email attachment
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the attachment file ID of this email attachment.
	 *
	 * @param attachmentFileId the attachment file ID of this email attachment
	 */
	@Override
	public void setAttachmentFileId(String attachmentFileId) {
		model.setAttachmentFileId(attachmentFileId);
	}

	/**
	 * Sets the attachment name of this email attachment.
	 *
	 * @param attachmentName the attachment name of this email attachment
	 */
	@Override
	public void setAttachmentName(String attachmentName) {
		model.setAttachmentName(attachmentName);
	}

	/**
	 * Sets the email attachment ID of this email attachment.
	 *
	 * @param emailAttachmentId the email attachment ID of this email attachment
	 */
	@Override
	public void setEmailAttachmentId(long emailAttachmentId) {
		model.setEmailAttachmentId(emailAttachmentId);
	}

	/**
	 * Sets the email ID of this email attachment.
	 *
	 * @param emailId the email ID of this email attachment
	 */
	@Override
	public void setEmailId(long emailId) {
		model.setEmailId(emailId);
	}

	/**
	 * Sets the primary key of this email attachment.
	 *
	 * @param primaryKey the primary key of this email attachment
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	@Override
	protected EmailAttachmentWrapper wrap(EmailAttachment emailAttachment) {
		return new EmailAttachmentWrapper(emailAttachment);
	}

}