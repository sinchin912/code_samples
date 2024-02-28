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

package com.trantorinc.synergy.skill.core.service.persistence.impl;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.configuration.Configuration;
import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.SessionFactory;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.persistence.BasePersistence;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;

import com.trantorinc.synergy.skill.core.exception.NoSuchCertificateException;
import com.trantorinc.synergy.skill.core.model.Certificate;
import com.trantorinc.synergy.skill.core.model.CertificateTable;
import com.trantorinc.synergy.skill.core.model.impl.CertificateImpl;
import com.trantorinc.synergy.skill.core.model.impl.CertificateModelImpl;
import com.trantorinc.synergy.skill.core.service.persistence.CertificatePersistence;
import com.trantorinc.synergy.skill.core.service.persistence.CertificateUtil;
import com.trantorinc.synergy.skill.core.service.persistence.impl.constants.SKILLPersistenceConstants;

import java.io.Serializable;

import java.lang.reflect.Field;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * The persistence implementation for the certificate service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = {CertificatePersistence.class, BasePersistence.class})
public class CertificatePersistenceImpl
	extends BasePersistenceImpl<Certificate> implements CertificatePersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>CertificateUtil</code> to access the certificate persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		CertificateImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;

	public CertificatePersistenceImpl() {
		setModelClass(Certificate.class);

		setModelImplClass(CertificateImpl.class);
		setModelPKClass(long.class);

		setTable(CertificateTable.INSTANCE);
	}

	/**
	 * Caches the certificate in the entity cache if it is enabled.
	 *
	 * @param certificate the certificate
	 */
	@Override
	public void cacheResult(Certificate certificate) {
		entityCache.putResult(
			CertificateImpl.class, certificate.getPrimaryKey(), certificate);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the certificates in the entity cache if it is enabled.
	 *
	 * @param certificates the certificates
	 */
	@Override
	public void cacheResult(List<Certificate> certificates) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (certificates.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (Certificate certificate : certificates) {
			if (entityCache.getResult(
					CertificateImpl.class, certificate.getPrimaryKey()) ==
						null) {

				cacheResult(certificate);
			}
		}
	}

	/**
	 * Clears the cache for all certificates.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(CertificateImpl.class);

		finderCache.clearCache(CertificateImpl.class);
	}

	/**
	 * Clears the cache for the certificate.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Certificate certificate) {
		entityCache.removeResult(CertificateImpl.class, certificate);
	}

	@Override
	public void clearCache(List<Certificate> certificates) {
		for (Certificate certificate : certificates) {
			entityCache.removeResult(CertificateImpl.class, certificate);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(CertificateImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(CertificateImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new certificate with the primary key. Does not add the certificate to the database.
	 *
	 * @param certificateId the primary key for the new certificate
	 * @return the new certificate
	 */
	@Override
	public Certificate create(long certificateId) {
		Certificate certificate = new CertificateImpl();

		certificate.setNew(true);
		certificate.setPrimaryKey(certificateId);

		return certificate;
	}

	/**
	 * Removes the certificate with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param certificateId the primary key of the certificate
	 * @return the certificate that was removed
	 * @throws NoSuchCertificateException if a certificate with the primary key could not be found
	 */
	@Override
	public Certificate remove(long certificateId)
		throws NoSuchCertificateException {

		return remove((Serializable)certificateId);
	}

	/**
	 * Removes the certificate with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the certificate
	 * @return the certificate that was removed
	 * @throws NoSuchCertificateException if a certificate with the primary key could not be found
	 */
	@Override
	public Certificate remove(Serializable primaryKey)
		throws NoSuchCertificateException {

		Session session = null;

		try {
			session = openSession();

			Certificate certificate = (Certificate)session.get(
				CertificateImpl.class, primaryKey);

			if (certificate == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchCertificateException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(certificate);
		}
		catch (NoSuchCertificateException noSuchEntityException) {
			throw noSuchEntityException;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected Certificate removeImpl(Certificate certificate) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(certificate)) {
				certificate = (Certificate)session.get(
					CertificateImpl.class, certificate.getPrimaryKeyObj());
			}

			if (certificate != null) {
				session.delete(certificate);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (certificate != null) {
			clearCache(certificate);
		}

		return certificate;
	}

	@Override
	public Certificate updateImpl(Certificate certificate) {
		boolean isNew = certificate.isNew();

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(certificate);
			}
			else {
				certificate = (Certificate)session.merge(certificate);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(CertificateImpl.class, certificate, false, true);

		if (isNew) {
			certificate.setNew(false);
		}

		certificate.resetOriginalValues();

		return certificate;
	}

	/**
	 * Returns the certificate with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the certificate
	 * @return the certificate
	 * @throws NoSuchCertificateException if a certificate with the primary key could not be found
	 */
	@Override
	public Certificate findByPrimaryKey(Serializable primaryKey)
		throws NoSuchCertificateException {

		Certificate certificate = fetchByPrimaryKey(primaryKey);

		if (certificate == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchCertificateException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return certificate;
	}

	/**
	 * Returns the certificate with the primary key or throws a <code>NoSuchCertificateException</code> if it could not be found.
	 *
	 * @param certificateId the primary key of the certificate
	 * @return the certificate
	 * @throws NoSuchCertificateException if a certificate with the primary key could not be found
	 */
	@Override
	public Certificate findByPrimaryKey(long certificateId)
		throws NoSuchCertificateException {

		return findByPrimaryKey((Serializable)certificateId);
	}

	/**
	 * Returns the certificate with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param certificateId the primary key of the certificate
	 * @return the certificate, or <code>null</code> if a certificate with the primary key could not be found
	 */
	@Override
	public Certificate fetchByPrimaryKey(long certificateId) {
		return fetchByPrimaryKey((Serializable)certificateId);
	}

	/**
	 * Returns all the certificates.
	 *
	 * @return the certificates
	 */
	@Override
	public List<Certificate> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<Certificate> findAll(int start, int end) {
		return findAll(start, end, null);
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
	@Override
	public List<Certificate> findAll(
		int start, int end, OrderByComparator<Certificate> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
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
	@Override
	public List<Certificate> findAll(
		int start, int end, OrderByComparator<Certificate> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindAll;
				finderArgs = FINDER_ARGS_EMPTY;
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindAll;
			finderArgs = new Object[] {start, end, orderByComparator};
		}

		List<Certificate> list = null;

		if (useFinderCache) {
			list = (List<Certificate>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_CERTIFICATE);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_CERTIFICATE;

				sql = sql.concat(CertificateModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<Certificate>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the certificates from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (Certificate certificate : findAll()) {
			remove(certificate);
		}
	}

	/**
	 * Returns the number of certificates.
	 *
	 * @return the number of certificates
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_CERTIFICATE);

				count = (Long)query.uniqueResult();

				finderCache.putResult(
					_finderPathCountAll, FINDER_ARGS_EMPTY, count);
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	@Override
	protected EntityCache getEntityCache() {
		return entityCache;
	}

	@Override
	protected String getPKDBName() {
		return "certificateId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_CERTIFICATE;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return CertificateModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the certificate persistence.
	 */
	@Activate
	public void activate() {
		_valueObjectFinderCacheListThreshold = GetterUtil.getInteger(
			PropsUtil.get(PropsKeys.VALUE_OBJECT_FINDER_CACHE_LIST_THRESHOLD));

		_finderPathWithPaginationFindAll = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0],
			new String[0], true);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0],
			new String[0], true);

		_finderPathCountAll = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0], new String[0], false);

		_setCertificateUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setCertificateUtilPersistence(null);

		entityCache.removeCache(CertificateImpl.class.getName());
	}

	private void _setCertificateUtilPersistence(
		CertificatePersistence certificatePersistence) {

		try {
			Field field = CertificateUtil.class.getDeclaredField(
				"_persistence");

			field.setAccessible(true);

			field.set(null, certificatePersistence);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@Override
	@Reference(
		target = SKILLPersistenceConstants.SERVICE_CONFIGURATION_FILTER,
		unbind = "-"
	)
	public void setConfiguration(Configuration configuration) {
	}

	@Override
	@Reference(
		target = SKILLPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	@Reference(
		target = SKILLPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@Reference
	protected EntityCache entityCache;

	@Reference
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_CERTIFICATE =
		"SELECT certificate FROM Certificate certificate";

	private static final String _SQL_COUNT_CERTIFICATE =
		"SELECT COUNT(certificate) FROM Certificate certificate";

	private static final String _ORDER_BY_ENTITY_ALIAS = "certificate.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No Certificate exists with the primary key ";

	private static final Log _log = LogFactoryUtil.getLog(
		CertificatePersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}