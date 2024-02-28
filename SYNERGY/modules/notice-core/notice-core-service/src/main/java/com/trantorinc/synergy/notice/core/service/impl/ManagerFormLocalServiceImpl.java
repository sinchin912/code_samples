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

package com.trantorinc.synergy.notice.core.service.impl;

import com.liferay.portal.aop.AopService;

import com.trantorinc.synergy.notice.core.model.ManagerForm;
import com.trantorinc.synergy.notice.core.service.base.ManagerFormLocalServiceBaseImpl;

import org.osgi.service.component.annotations.Component;

import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
@Component(
	property = "model.class.name=com.trantorinc.synergy.notice.core.model.ManagerForm",
	service = AopService.class
)
public class ManagerFormLocalServiceImpl
	extends ManagerFormLocalServiceBaseImpl {
	public List<ManagerForm> findManagerFormByExitId(long exitFormId)
	{
		return managerFormFinder.findManagerFormByExitId(exitFormId);
	}
}