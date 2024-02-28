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

package com.trantorinc.synergy.probation.core.model.impl;

import com.trantorinc.synergy.probation.core.model.Probation;
import com.trantorinc.synergy.probation.core.service.ProbationLocalServiceUtil;

/**
 * The extended model base implementation for the Probation service. Represents a row in the &quot;PROBATION_Probation&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link ProbationImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ProbationImpl
 * @see Probation
 * @generated
 */
public abstract class ProbationBaseImpl
	extends ProbationModelImpl implements Probation {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a probation model instance should use the <code>Probation</code> interface instead.
	 */
	@Override
	public void persist() {
		if (this.isNew()) {
			ProbationLocalServiceUtil.addProbation(this);
		}
		else {
			ProbationLocalServiceUtil.updateProbation(this);
		}
	}

}