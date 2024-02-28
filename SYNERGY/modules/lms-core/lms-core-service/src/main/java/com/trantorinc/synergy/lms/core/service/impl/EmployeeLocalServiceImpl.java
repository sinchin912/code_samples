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

import com.trantorinc.synergy.lms.core.model.Employee;
import com.trantorinc.synergy.lms.core.model.impl.EmployeeImpl;
import com.trantorinc.synergy.lms.core.service.base.EmployeeLocalServiceBaseImpl;

import org.osgi.service.component.annotations.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Brian Wing Shun Chan
 */
@Component(
	property = "model.class.name=com.trantorinc.synergy.lms.core.model.Employee",
	service = AopService.class
)
public class EmployeeLocalServiceImpl extends EmployeeLocalServiceBaseImpl {
	public List<Employee> findByDoj(Date onDate) {
		return employeeFinder.findByDoj(onDate);
	}
	public Employee findByEmail(String email) {
		return employeeFinder.findByEmail(email);
	}

	public List<String> findUniqueAccounts(){
		return employeeFinder.findUniqueAccounts();
	}

	public List<Employee> findAllEmployees(){
		return   employeeFinder.findAllEmployees();
	}
	public List<Employee> findAllActiveEmployees(){
		List<Employee> employeeList = employeeFinder.findAllEmployees();
		return  employeeList.stream().filter(Employee :: isStatus).collect(Collectors.toList());
	}

	public List<Employee> findManagerReporters(String manager){
		return employeeFinder.findManagerReporters(manager);

	}

	public List<Employee> findBirthdays(Date date) {
		List<Object[]> employeeRawList = employeeFinder.findBirthdays(date);
		List<Employee> returnList = new ArrayList<>();
		for(Object[] employeeRaw : employeeRawList){
			Employee employee = new EmployeeImpl();
			employee.setEcode(String.valueOf(employeeRaw[0]));
			employee.setName(String.valueOf(employeeRaw[1]));
			employee.setFileId(String.valueOf(employeeRaw[2]));
			returnList.add(employee);
		}
		return returnList;
	}

	public List<Employee> findAnniversaries(Date date) {
		List<Object[]> employeeRawList = employeeFinder.findAnniversaries(date);
		List<Employee> returnList = new ArrayList<>();
		for(Object[] employeeRaw : employeeRawList){
			Employee employee = new EmployeeImpl();
			employee.setEcode(String.valueOf(employeeRaw[0]));
			employee.setName(String.valueOf(employeeRaw[1]));
			employee.setDoj((Date) employeeRaw[2]);
			employee.setFileId(String.valueOf(employeeRaw[3]));
			returnList.add(employee);
		}
		return returnList;
	}
}