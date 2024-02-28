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

package com.trantorinc.synergy.skill.core.model.impl;

import com.trantorinc.synergy.skill.core.model.Certificate;
import com.trantorinc.synergy.skill.core.service.CertificateLocalServiceUtil;

/**
 * The extended model base implementation for the Certificate service. Represents a row in the &quot;SKILL_Certificate&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link CertificateImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CertificateImpl
 * @see Certificate
 * @generated
 */
public abstract class CertificateBaseImpl
	extends CertificateModelImpl implements Certificate {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a certificate model instance should use the <code>Certificate</code> interface instead.
	 */
	@Override
	public void persist() {
		if (this.isNew()) {
			CertificateLocalServiceUtil.addCertificate(this);
		}
		else {
			CertificateLocalServiceUtil.updateCertificate(this);
		}
	}

}