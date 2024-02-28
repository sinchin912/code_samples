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

package com.trantorinc.synergy.lms.core.service.persistence;

import org.osgi.annotation.versioning.ProviderType;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public interface EmployeeFinder {

	public java.util.List<String> findUniqueAccounts();

	public java.util.List<com.trantorinc.synergy.lms.core.model.Employee>
		findAllEmployees();

	public java.util.List<com.trantorinc.synergy.lms.core.model.Employee>
		findManagerReporters(String manager);

	public com.trantorinc.synergy.lms.core.model.Employee findByEmail(
		String email);

	public java.util.List<com.trantorinc.synergy.lms.core.model.Employee>
		findByDoj(java.util.Date doj);

	public java.util.List<Object[]> findAnniversaries(java.util.Date today);

	public java.util.List<Object[]> findBirthdays(java.util.Date today);

}