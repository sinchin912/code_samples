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

package com.trantorinc.synergy.email.core.service.persistence.impl;

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

import com.trantorinc.synergy.email.core.exception.NoSuchEmailAttachmentException;
import com.trantorinc.synergy.email.core.model.EmailAttachment;
import com.trantorinc.synergy.email.core.model.EmailAttachmentTable;
import com.trantorinc.synergy.email.core.model.impl.EmailAttachmentImpl;
import com.trantorinc.synergy.email.core.model.impl.EmailAttachmentModelImpl;
import com.trantorinc.synergy.email.core.service.persistence.EmailAttachmentPersistence;
import com.trantorinc.synergy.email.core.service.persistence.EmailAttachmentUtil;
import com.trantorinc.synergy.email.core.service.persistence.impl.constants.EMAILPersistenceConstants;

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
 * The persistence implementation for the email attachment service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = {EmailAttachmentPersistence.class, BasePersistence.class})
public class EmailAttachmentPersistenceImpl
	extends BasePersistenceImpl<EmailAttachment>
	implements EmailAttachmentPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>EmailAttachmentUtil</code> to access the email attachment persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		EmailAttachmentImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;

	public EmailAttachmentPersistenceImpl() {
		setModelClass(EmailAttachment.class);

		setModelImplClass(EmailAttachmentImpl.class);
		setModelPKClass(long.class);

		setTable(EmailAttachmentTable.INSTANCE);
	}

	/**
	 * Caches the email attachment in the entity cache if it is enabled.
	 *
	 * @param emailAttachment the email attachment
	 */
	@Override
	public void cacheResult(EmailAttachment emailAttachment) {
		entityCache.putResult(
			EmailAttachmentImpl.class, emailAttachment.getPrimaryKey(),
			emailAttachment);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the email attachments in the entity cache if it is enabled.
	 *
	 * @param emailAttachments the email attachments
	 */
	@Override
	public void cacheResult(List<EmailAttachment> emailAttachments) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (emailAttachments.size() >
				 _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (EmailAttachment emailAttachment : emailAttachments) {
			if (entityCache.getResult(
					EmailAttachmentImpl.class,
					emailAttachment.getPrimaryKey()) == null) {

				cacheResult(emailAttachment);
			}
		}
	}

	/**
	 * Clears the cache for all email attachments.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(EmailAttachmentImpl.class);

		finderCache.clearCache(EmailAttachmentImpl.class);
	}

	/**
	 * Clears the cache for the email attachment.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(EmailAttachment emailAttachment) {
		entityCache.removeResult(EmailAttachmentImpl.class, emailAttachment);
	}

	@Override
	public void clearCache(List<EmailAttachment> emailAttachments) {
		for (EmailAttachment emailAttachment : emailAttachments) {
			entityCache.removeResult(
				EmailAttachmentImpl.class, emailAttachment);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(EmailAttachmentImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(EmailAttachmentImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new email attachment with the primary key. Does not add the email attachment to the database.
	 *
	 * @param emailAttachmentId the primary key for the new email attachment
	 * @return the new email attachment
	 */
	@Override
	public EmailAttachment create(long emailAttachmentId) {
		EmailAttachment emailAttachment = new EmailAttachmentImpl();

		emailAttachment.setNew(true);
		emailAttachment.setPrimaryKey(emailAttachmentId);

		return emailAttachment;
	}

	/**
	 * Removes the email attachment with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param emailAttachmentId the primary key of the email attachment
	 * @return the email attachment that was removed
	 * @throws NoSuchEmailAttachmentException if a email attachment with the primary key could not be found
	 */
	@Override
	public EmailAttachment remove(long emailAttachmentId)
		throws NoSuchEmailAttachmentException {

		return remove((Serializable)emailAttachmentId);
	}

	/**
	 * Removes the email attachment with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the email attachment
	 * @return the email attachment that was removed
	 * @throws NoSuchEmailAttachmentException if a email attachment with the primary key could not be found
	 */
	@Override
	public EmailAttachment remove(Serializable primaryKey)
		throws NoSuchEmailAttachmentException {

		Session session = null;

		try {
			session = openSession();

			EmailAttachment emailAttachment = (EmailAttachment)session.get(
				EmailAttachmentImpl.class, primaryKey);

			if (emailAttachment == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchEmailAttachmentException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(emailAttachment);
		}
		catch (NoSuchEmailAttachmentException noSuchEntityException) {
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
	protected EmailAttachment removeImpl(EmailAttachment emailAttachment) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(emailAttachment)) {
				emailAttachment = (EmailAttachment)session.get(
					EmailAttachmentImpl.class,
					emailAttachment.getPrimaryKeyObj());
			}

			if (emailAttachment != null) {
				session.delete(emailAttachment);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (emailAttachment != null) {
			clearCache(emailAttachment);
		}

		return emailAttachment;
	}

	@Override
	public EmailAttachment updateImpl(EmailAttachment emailAttachment) {
		boolean isNew = emailAttachment.isNew();

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(emailAttachment);
			}
			else {
				emailAttachment = (EmailAttachment)session.merge(
					emailAttachment);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			EmailAttachmentImpl.class, emailAttachment, false, true);

		if (isNew) {
			emailAttachment.setNew(false);
		}

		emailAttachment.resetOriginalValues();

		return emailAttachment;
	}

	/**
	 * Returns the email attachment with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the email attachment
	 * @return the email attachment
	 * @throws NoSuchEmailAttachmentException if a email attachment with the primary key could not be found
	 */
	@Override
	public EmailAttachment findByPrimaryKey(Serializable primaryKey)
		throws NoSuchEmailAttachmentException {

		EmailAttachment emailAttachment = fetchByPrimaryKey(primaryKey);

		if (emailAttachment == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchEmailAttachmentException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return emailAttachment;
	}

	/**
	 * Returns the email attachment with the primary key or throws a <code>NoSuchEmailAttachmentException</code> if it could not be found.
	 *
	 * @param emailAttachmentId the primary key of the email attachment
	 * @return the email attachment
	 * @throws NoSuchEmailAttachmentException if a email attachment with the primary key could not be found
	 */
	@Override
	public EmailAttachment findByPrimaryKey(long emailAttachmentId)
		throws NoSuchEmailAttachmentException {

		return findByPrimaryKey((Serializable)emailAttachmentId);
	}

	/**
	 * Returns the email attachment with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param emailAttachmentId the primary key of the email attachment
	 * @return the email attachment, or <code>null</code> if a email attachment with the primary key could not be found
	 */
	@Override
	public EmailAttachment fetchByPrimaryKey(long emailAttachmentId) {
		return fetchByPrimaryKey((Serializable)emailAttachmentId);
	}

	/**
	 * Returns all the email attachments.
	 *
	 * @return the email attachments
	 */
	@Override
	public List<EmailAttachment> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the email attachments.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EmailAttachmentModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of email attachments
	 * @param end the upper bound of the range of email attachments (not inclusive)
	 * @return the range of email attachments
	 */
	@Override
	public List<EmailAttachment> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the email attachments.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EmailAttachmentModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of email attachments
	 * @param end the upper bound of the range of email attachments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of email attachments
	 */
	@Override
	public List<EmailAttachment> findAll(
		int start, int end,
		OrderByComparator<EmailAttachment> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the email attachments.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EmailAttachmentModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of email attachments
	 * @param end the upper bound of the range of email attachments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of email attachments
	 */
	@Override
	public List<EmailAttachment> findAll(
		int start, int end,
		OrderByComparator<EmailAttachment> orderByComparator,
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

		List<EmailAttachment> list = null;

		if (useFinderCache) {
			list = (List<EmailAttachment>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_EMAILATTACHMENT);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_EMAILATTACHMENT;

				sql = sql.concat(EmailAttachmentModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<EmailAttachment>)QueryUtil.list(
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
	 * Removes all the email attachments from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (EmailAttachment emailAttachment : findAll()) {
			remove(emailAttachment);
		}
	}

	/**
	 * Returns the number of email attachments.
	 *
	 * @return the number of email attachments
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_EMAILATTACHMENT);

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
		return "emailAttachmentId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_EMAILATTACHMENT;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return EmailAttachmentModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the email attachment persistence.
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

		_setEmailAttachmentUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setEmailAttachmentUtilPersistence(null);

		entityCache.removeCache(EmailAttachmentImpl.class.getName());
	}

	private void _setEmailAttachmentUtilPersistence(
		EmailAttachmentPersistence emailAttachmentPersistence) {

		try {
			Field field = EmailAttachmentUtil.class.getDeclaredField(
				"_persistence");

			field.setAccessible(true);

			field.set(null, emailAttachmentPersistence);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@Override
	@Reference(
		target = EMAILPersistenceConstants.SERVICE_CONFIGURATION_FILTER,
		unbind = "-"
	)
	public void setConfiguration(Configuration configuration) {
	}

	@Override
	@Reference(
		target = EMAILPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	@Reference(
		target = EMAILPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@Reference
	protected EntityCache entityCache;

	@Reference
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_EMAILATTACHMENT =
		"SELECT emailAttachment FROM EmailAttachment emailAttachment";

	private static final String _SQL_COUNT_EMAILATTACHMENT =
		"SELECT COUNT(emailAttachment) FROM EmailAttachment emailAttachment";

	private static final String _ORDER_BY_ENTITY_ALIAS = "emailAttachment.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No EmailAttachment exists with the primary key ";

	private static final Log _log = LogFactoryUtil.getLog(
		EmailAttachmentPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}