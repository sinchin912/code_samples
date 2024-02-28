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

package com.trantorinc.synergy.probation.core.service.persistence;

import org.osgi.annotation.versioning.ProviderType;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public interface ProbationFinder {

	public java.util.List<com.trantorinc.synergy.probation.core.model.Probation>
		findByManager(String manager);

	public java.util.List<com.trantorinc.synergy.probation.core.model.Probation>
		findByState(boolean completed);

	public java.util.List<com.trantorinc.synergy.probation.core.model.Probation>
		findByStateAndDate(boolean completed, java.util.Date onDate);

	public java.util.List<com.trantorinc.synergy.probation.core.model.Probation>
		findExtendedByDate(java.util.Date onDate);

}