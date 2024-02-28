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

package com.trantorinc.synergy.email.core.service.impl;

import com.liferay.portal.aop.AopService;

import com.trantorinc.synergy.email.core.model.EmailAttachment;
import com.trantorinc.synergy.email.core.service.base.EmailAttachmentLocalServiceBaseImpl;

import org.osgi.service.component.annotations.Component;

import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
@Component(
	property = "model.class.name=com.trantorinc.synergy.email.core.model.EmailAttachment",
	service = AopService.class
)
public class EmailAttachmentLocalServiceImpl
	extends EmailAttachmentLocalServiceBaseImpl {

	public List<EmailAttachment> findEmailAttachmentByEmailId(long emailId) {
		return emailAttachmentFinder.findByEmailId(emailId);
	}
}