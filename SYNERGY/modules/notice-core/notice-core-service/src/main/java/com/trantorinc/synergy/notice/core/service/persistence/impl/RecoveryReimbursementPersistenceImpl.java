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

package com.trantorinc.synergy.notice.core.service.persistence.impl;

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
import com.liferay.portal.kernel.util.SetUtil;

import com.trantorinc.synergy.notice.core.exception.NoSuchRecoveryReimbursementException;
import com.trantorinc.synergy.notice.core.model.RecoveryReimbursement;
import com.trantorinc.synergy.notice.core.model.RecoveryReimbursementTable;
import com.trantorinc.synergy.notice.core.model.impl.RecoveryReimbursementImpl;
import com.trantorinc.synergy.notice.core.model.impl.RecoveryReimbursementModelImpl;
import com.trantorinc.synergy.notice.core.service.persistence.RecoveryReimbursementPersistence;
import com.trantorinc.synergy.notice.core.service.persistence.RecoveryReimbursementUtil;
import com.trantorinc.synergy.notice.core.service.persistence.impl.constants.NOTICEPersistenceConstants;

import java.io.Serializable;

import java.lang.reflect.Field;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * The persistence implementation for the recovery reimbursement service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(
	service = {RecoveryReimbursementPersistence.class, BasePersistence.class}
)
public class RecoveryReimbursementPersistenceImpl
	extends BasePersistenceImpl<RecoveryReimbursement>
	implements RecoveryReimbursementPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>RecoveryReimbursementUtil</code> to access the recovery reimbursement persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		RecoveryReimbursementImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;

	public RecoveryReimbursementPersistenceImpl() {
		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("id", "id_");
		dbColumnNames.put("comment", "comment_");

		setDBColumnNames(dbColumnNames);

		setModelClass(RecoveryReimbursement.class);

		setModelImplClass(RecoveryReimbursementImpl.class);
		setModelPKClass(long.class);

		setTable(RecoveryReimbursementTable.INSTANCE);
	}

	/**
	 * Caches the recovery reimbursement in the entity cache if it is enabled.
	 *
	 * @param recoveryReimbursement the recovery reimbursement
	 */
	@Override
	public void cacheResult(RecoveryReimbursement recoveryReimbursement) {
		entityCache.putResult(
			RecoveryReimbursementImpl.class,
			recoveryReimbursement.getPrimaryKey(), recoveryReimbursement);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the recovery reimbursements in the entity cache if it is enabled.
	 *
	 * @param recoveryReimbursements the recovery reimbursements
	 */
	@Override
	public void cacheResult(
		List<RecoveryReimbursement> recoveryReimbursements) {

		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (recoveryReimbursements.size() >
				 _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (RecoveryReimbursement recoveryReimbursement :
				recoveryReimbursements) {

			if (entityCache.getResult(
					RecoveryReimbursementImpl.class,
					recoveryReimbursement.getPrimaryKey()) == null) {

				cacheResult(recoveryReimbursement);
			}
		}
	}

	/**
	 * Clears the cache for all recovery reimbursements.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(RecoveryReimbursementImpl.class);

		finderCache.clearCache(RecoveryReimbursementImpl.class);
	}

	/**
	 * Clears the cache for the recovery reimbursement.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(RecoveryReimbursement recoveryReimbursement) {
		entityCache.removeResult(
			RecoveryReimbursementImpl.class, recoveryReimbursement);
	}

	@Override
	public void clearCache(List<RecoveryReimbursement> recoveryReimbursements) {
		for (RecoveryReimbursement recoveryReimbursement :
				recoveryReimbursements) {

			entityCache.removeResult(
				RecoveryReimbursementImpl.class, recoveryReimbursement);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(RecoveryReimbursementImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(
				RecoveryReimbursementImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new recovery reimbursement with the primary key. Does not add the recovery reimbursement to the database.
	 *
	 * @param id the primary key for the new recovery reimbursement
	 * @return the new recovery reimbursement
	 */
	@Override
	public RecoveryReimbursement create(long id) {
		RecoveryReimbursement recoveryReimbursement =
			new RecoveryReimbursementImpl();

		recoveryReimbursement.setNew(true);
		recoveryReimbursement.setPrimaryKey(id);

		return recoveryReimbursement;
	}

	/**
	 * Removes the recovery reimbursement with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the recovery reimbursement
	 * @return the recovery reimbursement that was removed
	 * @throws NoSuchRecoveryReimbursementException if a recovery reimbursement with the primary key could not be found
	 */
	@Override
	public RecoveryReimbursement remove(long id)
		throws NoSuchRecoveryReimbursementException {

		return remove((Serializable)id);
	}

	/**
	 * Removes the recovery reimbursement with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the recovery reimbursement
	 * @return the recovery reimbursement that was removed
	 * @throws NoSuchRecoveryReimbursementException if a recovery reimbursement with the primary key could not be found
	 */
	@Override
	public RecoveryReimbursement remove(Serializable primaryKey)
		throws NoSuchRecoveryReimbursementException {

		Session session = null;

		try {
			session = openSession();

			RecoveryReimbursement recoveryReimbursement =
				(RecoveryReimbursement)session.get(
					RecoveryReimbursementImpl.class, primaryKey);

			if (recoveryReimbursement == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchRecoveryReimbursementException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(recoveryReimbursement);
		}
		catch (NoSuchRecoveryReimbursementException noSuchEntityException) {
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
	protected RecoveryReimbursement removeImpl(
		RecoveryReimbursement recoveryReimbursement) {

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(recoveryReimbursement)) {
				recoveryReimbursement = (RecoveryReimbursement)session.get(
					RecoveryReimbursementImpl.class,
					recoveryReimbursement.getPrimaryKeyObj());
			}

			if (recoveryReimbursement != null) {
				session.delete(recoveryReimbursement);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (recoveryReimbursement != null) {
			clearCache(recoveryReimbursement);
		}

		return recoveryReimbursement;
	}

	@Override
	public RecoveryReimbursement updateImpl(
		RecoveryReimbursement recoveryReimbursement) {

		boolean isNew = recoveryReimbursement.isNew();

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(recoveryReimbursement);
			}
			else {
				recoveryReimbursement = (RecoveryReimbursement)session.merge(
					recoveryReimbursement);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			RecoveryReimbursementImpl.class, recoveryReimbursement, false,
			true);

		if (isNew) {
			recoveryReimbursement.setNew(false);
		}

		recoveryReimbursement.resetOriginalValues();

		return recoveryReimbursement;
	}

	/**
	 * Returns the recovery reimbursement with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the recovery reimbursement
	 * @return the recovery reimbursement
	 * @throws NoSuchRecoveryReimbursementException if a recovery reimbursement with the primary key could not be found
	 */
	@Override
	public RecoveryReimbursement findByPrimaryKey(Serializable primaryKey)
		throws NoSuchRecoveryReimbursementException {

		RecoveryReimbursement recoveryReimbursement = fetchByPrimaryKey(
			primaryKey);

		if (recoveryReimbursement == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchRecoveryReimbursementException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return recoveryReimbursement;
	}

	/**
	 * Returns the recovery reimbursement with the primary key or throws a <code>NoSuchRecoveryReimbursementException</code> if it could not be found.
	 *
	 * @param id the primary key of the recovery reimbursement
	 * @return the recovery reimbursement
	 * @throws NoSuchRecoveryReimbursementException if a recovery reimbursement with the primary key could not be found
	 */
	@Override
	public RecoveryReimbursement findByPrimaryKey(long id)
		throws NoSuchRecoveryReimbursementException {

		return findByPrimaryKey((Serializable)id);
	}

	/**
	 * Returns the recovery reimbursement with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the recovery reimbursement
	 * @return the recovery reimbursement, or <code>null</code> if a recovery reimbursement with the primary key could not be found
	 */
	@Override
	public RecoveryReimbursement fetchByPrimaryKey(long id) {
		return fetchByPrimaryKey((Serializable)id);
	}

	/**
	 * Returns all the recovery reimbursements.
	 *
	 * @return the recovery reimbursements
	 */
	@Override
	public List<RecoveryReimbursement> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the recovery reimbursements.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RecoveryReimbursementModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of recovery reimbursements
	 * @param end the upper bound of the range of recovery reimbursements (not inclusive)
	 * @return the range of recovery reimbursements
	 */
	@Override
	public List<RecoveryReimbursement> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the recovery reimbursements.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RecoveryReimbursementModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of recovery reimbursements
	 * @param end the upper bound of the range of recovery reimbursements (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of recovery reimbursements
	 */
	@Override
	public List<RecoveryReimbursement> findAll(
		int start, int end,
		OrderByComparator<RecoveryReimbursement> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the recovery reimbursements.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RecoveryReimbursementModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of recovery reimbursements
	 * @param end the upper bound of the range of recovery reimbursements (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of recovery reimbursements
	 */
	@Override
	public List<RecoveryReimbursement> findAll(
		int start, int end,
		OrderByComparator<RecoveryReimbursement> orderByComparator,
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

		List<RecoveryReimbursement> list = null;

		if (useFinderCache) {
			list = (List<RecoveryReimbursement>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_RECOVERYREIMBURSEMENT);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_RECOVERYREIMBURSEMENT;

				sql = sql.concat(RecoveryReimbursementModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<RecoveryReimbursement>)QueryUtil.list(
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
	 * Removes all the recovery reimbursements from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (RecoveryReimbursement recoveryReimbursement : findAll()) {
			remove(recoveryReimbursement);
		}
	}

	/**
	 * Returns the number of recovery reimbursements.
	 *
	 * @return the number of recovery reimbursements
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(
					_SQL_COUNT_RECOVERYREIMBURSEMENT);

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
	public Set<String> getBadColumnNames() {
		return _badColumnNames;
	}

	@Override
	protected EntityCache getEntityCache() {
		return entityCache;
	}

	@Override
	protected String getPKDBName() {
		return "id_";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_RECOVERYREIMBURSEMENT;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return RecoveryReimbursementModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the recovery reimbursement persistence.
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

		_setRecoveryReimbursementUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setRecoveryReimbursementUtilPersistence(null);

		entityCache.removeCache(RecoveryReimbursementImpl.class.getName());
	}

	private void _setRecoveryReimbursementUtilPersistence(
		RecoveryReimbursementPersistence recoveryReimbursementPersistence) {

		try {
			Field field = RecoveryReimbursementUtil.class.getDeclaredField(
				"_persistence");

			field.setAccessible(true);

			field.set(null, recoveryReimbursementPersistence);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@Override
	@Reference(
		target = NOTICEPersistenceConstants.SERVICE_CONFIGURATION_FILTER,
		unbind = "-"
	)
	public void setConfiguration(Configuration configuration) {
	}

	@Override
	@Reference(
		target = NOTICEPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	@Reference(
		target = NOTICEPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@Reference
	protected EntityCache entityCache;

	@Reference
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_RECOVERYREIMBURSEMENT =
		"SELECT recoveryReimbursement FROM RecoveryReimbursement recoveryReimbursement";

	private static final String _SQL_COUNT_RECOVERYREIMBURSEMENT =
		"SELECT COUNT(recoveryReimbursement) FROM RecoveryReimbursement recoveryReimbursement";

	private static final String _ORDER_BY_ENTITY_ALIAS =
		"recoveryReimbursement.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No RecoveryReimbursement exists with the primary key ";

	private static final Log _log = LogFactoryUtil.getLog(
		RecoveryReimbursementPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"id", "comment"});

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}