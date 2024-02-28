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

import com.trantorinc.synergy.notice.core.exception.NoSuchFinanceFormException;
import com.trantorinc.synergy.notice.core.model.FinanceForm;
import com.trantorinc.synergy.notice.core.model.FinanceFormTable;
import com.trantorinc.synergy.notice.core.model.impl.FinanceFormImpl;
import com.trantorinc.synergy.notice.core.model.impl.FinanceFormModelImpl;
import com.trantorinc.synergy.notice.core.service.persistence.FinanceFormPersistence;
import com.trantorinc.synergy.notice.core.service.persistence.FinanceFormUtil;
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
 * The persistence implementation for the finance form service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = {FinanceFormPersistence.class, BasePersistence.class})
public class FinanceFormPersistenceImpl
	extends BasePersistenceImpl<FinanceForm> implements FinanceFormPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>FinanceFormUtil</code> to access the finance form persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		FinanceFormImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;

	public FinanceFormPersistenceImpl() {
		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("id", "id_");

		setDBColumnNames(dbColumnNames);

		setModelClass(FinanceForm.class);

		setModelImplClass(FinanceFormImpl.class);
		setModelPKClass(long.class);

		setTable(FinanceFormTable.INSTANCE);
	}

	/**
	 * Caches the finance form in the entity cache if it is enabled.
	 *
	 * @param financeForm the finance form
	 */
	@Override
	public void cacheResult(FinanceForm financeForm) {
		entityCache.putResult(
			FinanceFormImpl.class, financeForm.getPrimaryKey(), financeForm);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the finance forms in the entity cache if it is enabled.
	 *
	 * @param financeForms the finance forms
	 */
	@Override
	public void cacheResult(List<FinanceForm> financeForms) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (financeForms.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (FinanceForm financeForm : financeForms) {
			if (entityCache.getResult(
					FinanceFormImpl.class, financeForm.getPrimaryKey()) ==
						null) {

				cacheResult(financeForm);
			}
		}
	}

	/**
	 * Clears the cache for all finance forms.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(FinanceFormImpl.class);

		finderCache.clearCache(FinanceFormImpl.class);
	}

	/**
	 * Clears the cache for the finance form.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(FinanceForm financeForm) {
		entityCache.removeResult(FinanceFormImpl.class, financeForm);
	}

	@Override
	public void clearCache(List<FinanceForm> financeForms) {
		for (FinanceForm financeForm : financeForms) {
			entityCache.removeResult(FinanceFormImpl.class, financeForm);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(FinanceFormImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(FinanceFormImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new finance form with the primary key. Does not add the finance form to the database.
	 *
	 * @param id the primary key for the new finance form
	 * @return the new finance form
	 */
	@Override
	public FinanceForm create(long id) {
		FinanceForm financeForm = new FinanceFormImpl();

		financeForm.setNew(true);
		financeForm.setPrimaryKey(id);

		return financeForm;
	}

	/**
	 * Removes the finance form with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the finance form
	 * @return the finance form that was removed
	 * @throws NoSuchFinanceFormException if a finance form with the primary key could not be found
	 */
	@Override
	public FinanceForm remove(long id) throws NoSuchFinanceFormException {
		return remove((Serializable)id);
	}

	/**
	 * Removes the finance form with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the finance form
	 * @return the finance form that was removed
	 * @throws NoSuchFinanceFormException if a finance form with the primary key could not be found
	 */
	@Override
	public FinanceForm remove(Serializable primaryKey)
		throws NoSuchFinanceFormException {

		Session session = null;

		try {
			session = openSession();

			FinanceForm financeForm = (FinanceForm)session.get(
				FinanceFormImpl.class, primaryKey);

			if (financeForm == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchFinanceFormException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(financeForm);
		}
		catch (NoSuchFinanceFormException noSuchEntityException) {
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
	protected FinanceForm removeImpl(FinanceForm financeForm) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(financeForm)) {
				financeForm = (FinanceForm)session.get(
					FinanceFormImpl.class, financeForm.getPrimaryKeyObj());
			}

			if (financeForm != null) {
				session.delete(financeForm);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (financeForm != null) {
			clearCache(financeForm);
		}

		return financeForm;
	}

	@Override
	public FinanceForm updateImpl(FinanceForm financeForm) {
		boolean isNew = financeForm.isNew();

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(financeForm);
			}
			else {
				financeForm = (FinanceForm)session.merge(financeForm);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(FinanceFormImpl.class, financeForm, false, true);

		if (isNew) {
			financeForm.setNew(false);
		}

		financeForm.resetOriginalValues();

		return financeForm;
	}

	/**
	 * Returns the finance form with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the finance form
	 * @return the finance form
	 * @throws NoSuchFinanceFormException if a finance form with the primary key could not be found
	 */
	@Override
	public FinanceForm findByPrimaryKey(Serializable primaryKey)
		throws NoSuchFinanceFormException {

		FinanceForm financeForm = fetchByPrimaryKey(primaryKey);

		if (financeForm == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchFinanceFormException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return financeForm;
	}

	/**
	 * Returns the finance form with the primary key or throws a <code>NoSuchFinanceFormException</code> if it could not be found.
	 *
	 * @param id the primary key of the finance form
	 * @return the finance form
	 * @throws NoSuchFinanceFormException if a finance form with the primary key could not be found
	 */
	@Override
	public FinanceForm findByPrimaryKey(long id)
		throws NoSuchFinanceFormException {

		return findByPrimaryKey((Serializable)id);
	}

	/**
	 * Returns the finance form with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the finance form
	 * @return the finance form, or <code>null</code> if a finance form with the primary key could not be found
	 */
	@Override
	public FinanceForm fetchByPrimaryKey(long id) {
		return fetchByPrimaryKey((Serializable)id);
	}

	/**
	 * Returns all the finance forms.
	 *
	 * @return the finance forms
	 */
	@Override
	public List<FinanceForm> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the finance forms.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FinanceFormModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of finance forms
	 * @param end the upper bound of the range of finance forms (not inclusive)
	 * @return the range of finance forms
	 */
	@Override
	public List<FinanceForm> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the finance forms.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FinanceFormModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of finance forms
	 * @param end the upper bound of the range of finance forms (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of finance forms
	 */
	@Override
	public List<FinanceForm> findAll(
		int start, int end, OrderByComparator<FinanceForm> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the finance forms.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FinanceFormModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of finance forms
	 * @param end the upper bound of the range of finance forms (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of finance forms
	 */
	@Override
	public List<FinanceForm> findAll(
		int start, int end, OrderByComparator<FinanceForm> orderByComparator,
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

		List<FinanceForm> list = null;

		if (useFinderCache) {
			list = (List<FinanceForm>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_FINANCEFORM);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_FINANCEFORM;

				sql = sql.concat(FinanceFormModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<FinanceForm>)QueryUtil.list(
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
	 * Removes all the finance forms from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (FinanceForm financeForm : findAll()) {
			remove(financeForm);
		}
	}

	/**
	 * Returns the number of finance forms.
	 *
	 * @return the number of finance forms
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_FINANCEFORM);

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
		return _SQL_SELECT_FINANCEFORM;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return FinanceFormModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the finance form persistence.
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

		_setFinanceFormUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setFinanceFormUtilPersistence(null);

		entityCache.removeCache(FinanceFormImpl.class.getName());
	}

	private void _setFinanceFormUtilPersistence(
		FinanceFormPersistence financeFormPersistence) {

		try {
			Field field = FinanceFormUtil.class.getDeclaredField(
				"_persistence");

			field.setAccessible(true);

			field.set(null, financeFormPersistence);
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

	private static final String _SQL_SELECT_FINANCEFORM =
		"SELECT financeForm FROM FinanceForm financeForm";

	private static final String _SQL_COUNT_FINANCEFORM =
		"SELECT COUNT(financeForm) FROM FinanceForm financeForm";

	private static final String _ORDER_BY_ENTITY_ALIAS = "financeForm.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No FinanceForm exists with the primary key ";

	private static final Log _log = LogFactoryUtil.getLog(
		FinanceFormPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"id"});

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}