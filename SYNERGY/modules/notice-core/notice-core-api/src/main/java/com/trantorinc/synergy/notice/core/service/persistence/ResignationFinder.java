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

package com.trantorinc.synergy.notice.core.service.persistence;

import org.osgi.annotation.versioning.ProviderType;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public interface ResignationFinder {

	public java.util.List<com.trantorinc.synergy.notice.core.model.Resignation>
		findResignationByEcode(String ecode);

	public java.util.List<com.trantorinc.synergy.notice.core.model.Resignation>
		findEntryByManagerEmail(String email);

	public java.util.List<com.trantorinc.synergy.notice.core.model.Resignation>
		findResignationsByLastWorkingDate(java.util.Date lastWorkingDay);

	public java.util.List<com.trantorinc.synergy.notice.core.model.Resignation>
		findActiveResignationEntries(java.util.Date todaysDate);

	public java.util.List<com.trantorinc.synergy.notice.core.model.Resignation>
		findYearlyEntriesByLWD(
			java.util.Date startDate, java.util.Date endDate);

	public java.util.List<com.trantorinc.synergy.notice.core.model.Resignation>
		findYearlyEntriesByCreationDate(
			java.util.Date startDate, java.util.Date endDate);

	public java.util.List<Object[]> findUniqueResignationYears();

	public java.util.List<com.trantorinc.synergy.notice.core.model.Resignation>
		findActiveResignationsByLastWorkingDate(java.util.Date lastWorkingDay);

	public java.util.List<com.trantorinc.synergy.notice.core.model.Resignation>
		findAllResignationsByHrState();

}