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

package com.trantorinc.synergy.skill.core.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link CertificateLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see CertificateLocalService
 * @generated
 */
public class CertificateLocalServiceWrapper
	implements CertificateLocalService,
			   ServiceWrapper<CertificateLocalService> {

	public CertificateLocalServiceWrapper() {
		this(null);
	}

	public CertificateLocalServiceWrapper(
		CertificateLocalService certificateLocalService) {

		_certificateLocalService = certificateLocalService;
	}

	/**
	 * Adds the certificate to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CertificateLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param certificate the certificate
	 * @return the certificate that was added
	 */
	@Override
	public com.trantorinc.synergy.skill.core.model.Certificate addCertificate(
		com.trantorinc.synergy.skill.core.model.Certificate certificate) {

		return _certificateLocalService.addCertificate(certificate);
	}

	/**
	 * Creates a new certificate with the primary key. Does not add the certificate to the database.
	 *
	 * @param certificateId the primary key for the new certificate
	 * @return the new certificate
	 */
	@Override
	public com.trantorinc.synergy.skill.core.model.Certificate
		createCertificate(long certificateId) {

		return _certificateLocalService.createCertificate(certificateId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _certificateLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Deletes the certificate from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CertificateLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param certificate the certificate
	 * @return the certificate that was removed
	 */
	@Override
	public com.trantorinc.synergy.skill.core.model.Certificate
		deleteCertificate(
			com.trantorinc.synergy.skill.core.model.Certificate certificate) {

		return _certificateLocalService.deleteCertificate(certificate);
	}

	/**
	 * Deletes the certificate with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CertificateLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param certificateId the primary key of the certificate
	 * @return the certificate that was removed
	 * @throws PortalException if a certificate with the primary key could not be found
	 */
	@Override
	public com.trantorinc.synergy.skill.core.model.Certificate
			deleteCertificate(long certificateId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _certificateLocalService.deleteCertificate(certificateId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _certificateLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _certificateLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _certificateLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _certificateLocalService.dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _certificateLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.trantorinc.synergy.skill.core.model.impl.CertificateModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return _certificateLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.trantorinc.synergy.skill.core.model.impl.CertificateModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return _certificateLocalService.dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _certificateLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return _certificateLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.trantorinc.synergy.skill.core.model.Certificate fetchCertificate(
		long certificateId) {

		return _certificateLocalService.fetchCertificate(certificateId);
	}

	@Override
	public java.util.List<com.trantorinc.synergy.skill.core.model.Certificate>
		findCertificatesByEcode(String ecode) {

		return _certificateLocalService.findCertificatesByEcode(ecode);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _certificateLocalService.getActionableDynamicQuery();
	}

	/**
	 * Returns the certificate with the primary key.
	 *
	 * @param certificateId the primary key of the certificate
	 * @return the certificate
	 * @throws PortalException if a certificate with the primary key could not be found
	 */
	@Override
	public com.trantorinc.synergy.skill.core.model.Certificate getCertificate(
			long certificateId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _certificateLocalService.getCertificate(certificateId);
	}

	/**
	 * Returns a range of all the certificates.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.trantorinc.synergy.skill.core.model.impl.CertificateModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of certificates
	 * @param end the upper bound of the range of certificates (not inclusive)
	 * @return the range of certificates
	 */
	@Override
	public java.util.List<com.trantorinc.synergy.skill.core.model.Certificate>
		getCertificates(int start, int end) {

		return _certificateLocalService.getCertificates(start, end);
	}

	/**
	 * Returns the number of certificates.
	 *
	 * @return the number of certificates
	 */
	@Override
	public int getCertificatesCount() {
		return _certificateLocalService.getCertificatesCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _certificateLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _certificateLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _certificateLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the certificate in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CertificateLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param certificate the certificate
	 * @return the certificate that was updated
	 */
	@Override
	public com.trantorinc.synergy.skill.core.model.Certificate
		updateCertificate(
			com.trantorinc.synergy.skill.core.model.Certificate certificate) {

		return _certificateLocalService.updateCertificate(certificate);
	}

	@Override
	public CertificateLocalService getWrappedService() {
		return _certificateLocalService;
	}

	@Override
	public void setWrappedService(
		CertificateLocalService certificateLocalService) {

		_certificateLocalService = certificateLocalService;
	}

	private CertificateLocalService _certificateLocalService;

}