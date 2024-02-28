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

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.trantorinc.synergy.skill.core.model.Certificate;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the certificate service. This utility wraps <code>com.trantorinc.synergy.skill.core.service.persistence.impl.CertificatePersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CertificatePersistence
 * @generated
 */
public class CertificateUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(Certificate certificate) {
		getPersistence().clearCache(certificate);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#fetchByPrimaryKeys(Set)
	 */
	public static Map<Serializable, Certificate> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<Certificate> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Certificate> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Certificate> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<Certificate> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static Certificate update(Certificate certificate) {
		return getPersistence().update(certificate);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static Certificate update(
		Certificate certificate, ServiceContext serviceContext) {

		return getPersistence().update(certificate, serviceContext);
	}

	/**
	 * Caches the certificate in the entity cache if it is enabled.
	 *
	 * @param certificate the certificate
	 */
	public static void cacheResult(Certificate certificate) {
		getPersistence().cacheResult(certificate);
	}

	/**
	 * Caches the certificates in the entity cache if it is enabled.
	 *
	 * @param certificates the certificates
	 */
	public static void cacheResult(List<Certificate> certificates) {
		getPersistence().cacheResult(certificates);
	}

	/**
	 * Creates a new certificate with the primary key. Does not add the certificate to the database.
	 *
	 * @param certificateId the primary key for the new certificate
	 * @return the new certificate
	 */
	public static Certificate create(long certificateId) {
		return getPersistence().create(certificateId);
	}

	/**
	 * Removes the certificate with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param certificateId the primary key of the certificate
	 * @return the certificate that was removed
	 * @throws NoSuchCertificateException if a certificate with the primary key could not be found
	 */
	public static Certificate remove(long certificateId)
		throws com.trantorinc.synergy.skill.core.exception.
			NoSuchCertificateException {

		return getPersistence().remove(certificateId);
	}

	public static Certificate updateImpl(Certificate certificate) {
		return getPersistence().updateImpl(certificate);
	}

	/**
	 * Returns the certificate with the primary key or throws a <code>NoSuchCertificateException</code> if it could not be found.
	 *
	 * @param certificateId the primary key of the certificate
	 * @return the certificate
	 * @throws NoSuchCertificateException if a certificate with the primary key could not be found
	 */
	public static Certificate findByPrimaryKey(long certificateId)
		throws com.trantorinc.synergy.skill.core.exception.
			NoSuchCertificateException {

		return getPersistence().findByPrimaryKey(certificateId);
	}

	/**
	 * Returns the certificate with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param certificateId the primary key of the certificate
	 * @return the certificate, or <code>null</code> if a certificate with the primary key could not be found
	 */
	public static Certificate fetchByPrimaryKey(long certificateId) {
		return getPersistence().fetchByPrimaryKey(certificateId);
	}

	/**
	 * Returns all the certificates.
	 *
	 * @return the certificates
	 */
	public static List<Certificate> findAll() {
		return getPersistence().findAll();
	}

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
	public static List<Certificate> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

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
	public static List<Certificate> findAll(
		int start, int end, OrderByComparator<Certificate> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

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
	public static List<Certificate> findAll(
		int start, int end, OrderByComparator<Certificate> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the certificates from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of certificates.
	 *
	 * @return the number of certificates
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static CertificatePersistence getPersistence() {
		return _persistence;
	}

	private static volatile CertificatePersistence _persistence;

}