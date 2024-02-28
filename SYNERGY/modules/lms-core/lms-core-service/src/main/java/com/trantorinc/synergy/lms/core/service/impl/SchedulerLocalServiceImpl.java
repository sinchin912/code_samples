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

package com.trantorinc.synergy.lms.core.service.impl;

import com.liferay.portal.aop.AopService;

import com.trantorinc.synergy.lms.core.model.Scheduler;
import com.trantorinc.synergy.lms.core.service.base.SchedulerLocalServiceBaseImpl;

import org.osgi.service.component.annotations.Component;

import java.util.Date;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
@Component(
	property = "model.class.name=com.trantorinc.synergy.lms.core.model.Scheduler",
	service = AopService.class
)
public class SchedulerLocalServiceImpl extends SchedulerLocalServiceBaseImpl {

	public Scheduler findSchedulerByNameAndDate(String name, Date onDate) {
		Scheduler scheduler = null;
		List<Scheduler> machingSchedulers = schedulerFinder.findByNameAndDate(name,onDate);
		if(!machingSchedulers.isEmpty()){
			scheduler = machingSchedulers.get(0);
		}
		return scheduler;
	}

	public List<Scheduler> findSchedulersByDate(Date onDate) {
		return schedulerFinder.findByDate(onDate);
	}
}