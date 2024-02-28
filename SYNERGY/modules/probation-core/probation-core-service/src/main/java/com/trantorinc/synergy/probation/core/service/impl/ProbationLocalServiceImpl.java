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

package com.trantorinc.synergy.probation.core.service.impl;

import com.liferay.portal.aop.AopService;

import com.trantorinc.synergy.probation.core.model.Probation;
import com.trantorinc.synergy.probation.core.service.base.ProbationLocalServiceBaseImpl;

import org.osgi.service.component.annotations.Component;

import java.util.Date;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
@Component(
	property = "model.class.name=com.trantorinc.synergy.probation.core.model.Probation",
	service = AopService.class
)
public class ProbationLocalServiceImpl extends ProbationLocalServiceBaseImpl {

	public List<Probation> findByManager(String manager){
		return probationFinder.findByManager(manager);
	}

	public List<Probation> findAllCompleted(){
		return probationFinder.findByState(true);
	}

	public List<Probation> findAllPending(){
		return probationFinder.findByState(false);
	}

	public List<Probation> findCompletedByDate(Date onDate){
		return probationFinder.findByStateAndDate(true, onDate);
	}

	public List<Probation> findPendingByDate(Date onDate){
		return probationFinder.findByStateAndDate(false, onDate);
	}

	public List<Probation> findExtendedByDate(Date onDate){
		return probationFinder.findExtendedByDate(onDate);
	}
}