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

import com.trantorinc.synergy.notice.core.model.ExitForm;
import com.trantorinc.synergy.notice.core.model.impl.ExitFormImpl;
import com.trantorinc.synergy.notice.core.service.base.ExitFormLocalServiceBaseImpl;

import org.osgi.service.component.annotations.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
@Component(
	property = "model.class.name=com.trantorinc.synergy.notice.core.model.ExitForm",
	service = AopService.class
)
public class ExitFormLocalServiceImpl extends ExitFormLocalServiceBaseImpl {
	public List<ExitForm> findExitFormByResignationId(long resignationId){
		return exitFormFinder.findExitFormByResignationId(resignationId);
	}
	public List<ExitForm> findAllExitFormEntries(){

		List<Object[]> allExitFormEntries=exitFormFinder.findAllExitFormEntries();
		List<ExitForm> returnList = new ArrayList<>();
		for(Object[] allExitFormData : allExitFormEntries){
			ExitForm exitFormData= new ExitFormImpl();
			exitFormData.setId((Long) allExitFormData[0]);
			exitFormData.setResignationId((Long) allExitFormData[1]);
			exitFormData.setManagerFormStatus((Boolean) allExitFormData[2]);
			returnList.add(exitFormData);
		}
		return  returnList;
	}
}