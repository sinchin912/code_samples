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

import com.trantorinc.synergy.notice.core.model.Resignation;
import com.trantorinc.synergy.notice.core.service.base.ResignationLocalServiceBaseImpl;

import org.osgi.service.component.annotations.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Brian Wing Shun Chan
 */
@Component(
	property = "model.class.name=com.trantorinc.synergy.notice.core.model.Resignation",
	service = AopService.class
)
public class ResignationLocalServiceImpl
	extends ResignationLocalServiceBaseImpl {
	public List<Resignation> findResignationByEcode(String ecode){ return resignationFinder.findResignationByEcode(ecode);}
	public List<Resignation> findEntryByManagerEmail(String email){ return resignationFinder.findEntryByManagerEmail(email); }
	public List<Resignation> findResignationsByLastWorkingDate(Date lastWorkingDay){ return resignationFinder.findResignationsByLastWorkingDate(lastWorkingDay); }
	public List<Resignation> findActiveResignationEntries(Date date){ return resignationFinder.findActiveResignationEntries(date); }
	public List<Resignation> findYearlyEntriesbyLWD(Date startDate , Date endDate){ return resignationFinder.findYearlyEntriesByLWD(startDate,endDate); }

	public List<Resignation> findYearlyEntriesbyCreationDate(Date startDate , Date endDate){ return resignationFinder.findYearlyEntriesByCreationDate(startDate,endDate); }
	public List<Integer> findUniqueResignationYears() {
		List<Integer> uniqueYears= new ArrayList<>();
		List<Object[]> uniqueYearsRawData= resignationFinder.findUniqueResignationYears();
		List<Date> dates= new ArrayList<>();
		if(!uniqueYearsRawData.isEmpty()){
			for(Object[] date :uniqueYearsRawData ){
				dates.add((Date)date[0]);
				dates.add((Date)date[1]);
			}
			for(Date date : dates){
				if(date!=null) {
					LocalDateTime localDateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
					if (!uniqueYears.contains(localDateTime.getYear())) {
						uniqueYears.add(localDateTime.getYear());
					}
				}
			}
		}
		return uniqueYears.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
	}
	public List<Resignation> findActiveResignationsByLastWorkingDate(Date date){ return resignationFinder.findActiveResignationsByLastWorkingDate(date); }
	public List<Resignation> findAllResignationsByHrState(){
		return resignationFinder.findAllResignationsByHrState();
	}
}