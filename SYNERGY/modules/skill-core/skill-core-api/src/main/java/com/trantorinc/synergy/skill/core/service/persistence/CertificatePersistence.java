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

package com.trantorinc.synergy.skill.core.service.persistence;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import com.trantorinc.synergy.skill.core.exception.NoSuchCertificateException;
import com.trantorinc.synergy.skill.core.model.Certificate;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the certificate service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CertificateUtil
 * @generated
 */
@ProviderType
public interface CertificatePersistence extends BasePersistence<Certificate> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CertificateUtil} to access the certificate persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Caches the certificate in the entity cache if it is enabled.
	 *
	 * @param certificate the certificate
	 */
	public void cacheResult(Certificate certificate);

	/**
	 * Caches the certificates in the entity cache if it is enabled.
	 *
	 * @param certificates the certificates
	 */
	public void cacheResult(java.util.List<Certificate> certificates);

	/**
	 * Creates a new certificate with the primary key. Does not add the certificate to the database.
	 *
	 * @param certificateId the primary key for the new certificate
	 * @return the new certificate
	 */
	public Certificate create(long certificateId);

	/**
	 * Removes the certificate with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param certificateId the primary key of the certificate
	 * @return the certificate that was removed
	 * @throws NoSuchCertificateException if a certificate with the primary key could not be found
	 */
	public Certificate remove(long certificateId)
		throws NoSuchCertificateException;

	public Certificate updateImpl(Certificate certificate);

	/**
	 * Returns the certificate with the primary key or throws a <code>NoSuchCertificateException</code> if it could not be found.
	 *
	 * @param certificateId the primary key of the certificate
	 * @return the certificate
	 * @throws NoSuchCertificateException if a certificate with the primary key could not be found
	 */
	public Certificate findByPrimaryKey(long certificateId)
		throws NoSuchCertificateException;

	/**
	 * Returns the certificate with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param certificateId the primary key of the certificate
	 * @return the certificate, or <code>null</code> if a certificate with the primary key could not be found
	 */
	public Certificate fetchByPrimaryKey(long certificateId);

	/**
	 * Returns all the certificates.
	 *
	 * @return the certificates
	 */
	public java.util.List<Certificate> findAll();

	/**
	 * Returns a range of all the certificates.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CertificateModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of certificates
	 * @param end the upper bound of the range of certificates (not inclusive)
	 * @return the range of certificates
	 */
	public java.util.List<Certificate> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the certificates.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CertificateModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of certificates
	 * @param end the upper bound of the range of certificates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of certificates
	 */
	public java.util.List<Certificate> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Certificate>
			orderByComparator);

	/**
	 * Returns an ordered range of all the certificates.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CertificateModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of certificates
	 * @param end the upper bound of the range of certificates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of certificates
	 */
	public java.util.List<Certificate> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Certificate>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the certificates from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of certificates.
	 *
	 * @return the number of certificates
	 */
	public int countAll();

}