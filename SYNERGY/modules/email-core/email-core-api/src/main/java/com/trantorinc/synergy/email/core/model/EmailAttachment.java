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

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The extended model interface for the EmailAttachment service. Represents a row in the &quot;EMAIL_EmailAttachment&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see EmailAttachmentModel
 * @generated
 */
@ImplementationClassName(
	"com.trantorinc.synergy.email.core.model.impl.EmailAttachmentImpl"
)
@ProviderType
public interface EmailAttachment extends EmailAttachmentModel, PersistedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.trantorinc.synergy.email.core.model.impl.EmailAttachmentImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<EmailAttachment, Long>
		EMAIL_ATTACHMENT_ID_ACCESSOR = new Accessor<EmailAttachment, Long>() {

			@Override
			public Long get(EmailAttachment emailAttachment) {
				return emailAttachment.getEmailAttachmentId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<EmailAttachment> getTypeClass() {
				return EmailAttachment.class;
			}

		};

}