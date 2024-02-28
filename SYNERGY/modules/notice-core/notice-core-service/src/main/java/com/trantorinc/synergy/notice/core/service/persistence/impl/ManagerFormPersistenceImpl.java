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

import com.trantorinc.synergy.notice.core.exception.NoSuchManagerFormException;
import com.trantorinc.synergy.notice.core.model.ManagerForm;
import com.trantorinc.synergy.notice.core.model.ManagerFormTable;
import com.trantorinc.synergy.notice.core.model.impl.ManagerFormImpl;
import com.trantorinc.synergy.notice.core.model.impl.ManagerFormModelImpl;
import com.trantorinc.synergy.notice.core.service.persistence.ManagerFormPersistence;
import com.trantorinc.synergy.notice.core.service.persistence.ManagerFormUtil;
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
 * The persistence implementation for the manager form service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = {ManagerFormPersistence.class, BasePersistence.class})
public class ManagerFormPersistenceImpl
	extends BasePersistenceImpl<ManagerForm> implements ManagerFormPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>ManagerFormUtil</code> to access the manager form persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		ManagerFormImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;

	public ManagerFormPersistenceImpl() {
		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("id", "id_");

		setDBColumnNames(dbColumnNames);

		setModelClass(ManagerForm.class);

		setModelImplClass(ManagerFormImpl.class);
		setModelPKClass(long.class);

		setTable(ManagerFormTable.INSTANCE);
	}

	/**
	 * Caches the manager form in the entity cache if it is enabled.
	 *
	 * @param managerForm the manager form
	 */
	@Override
	public void cacheResult(ManagerForm managerForm) {
		entityCache.putResult(
			ManagerFormImpl.class, managerForm.getPrimaryKey(), managerForm);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the manager forms in the entity cache if it is enabled.
	 *
	 * @param managerForms the manager forms
	 */
	@Override
	public void cacheResult(List<ManagerForm> managerForms) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (managerForms.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (ManagerForm managerForm : managerForms) {
			if (entityCache.getResult(
					ManagerFormImpl.class, managerForm.getPrimaryKey()) ==
						null) {

				cacheResult(managerForm);
			}
		}
	}

	/**
	 * Clears the cache for all manager forms.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ManagerFormImpl.class);

		finderCache.clearCache(ManagerFormImpl.class);
	}

	/**
	 * Clears the cache for the manager form.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ManagerForm managerForm) {
		entityCache.removeResult(ManagerFormImpl.class, managerForm);
	}

	@Override
	public void clearCache(List<ManagerForm> managerForms) {
		for (ManagerForm managerForm : managerForms) {
			entityCache.removeResult(ManagerFormImpl.class, managerForm);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(ManagerFormImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(ManagerFormImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new manager form with the primary key. Does not add the manager form to the database.
	 *
	 * @param id the primary key for the new manager form
	 * @return the new manager form
	 */
	@Override
	public ManagerForm create(long id) {
		ManagerForm managerForm = new ManagerFormImpl();

		managerForm.setNew(true);
		managerForm.setPrimaryKey(id);

		return managerForm;
	}

	/**
	 * Removes the manager form with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the manager form
	 * @return the manager form that was removed
	 * @throws NoSuchManagerFormException if a manager form with the primary key could not be found
	 */
	@Override
	public ManagerForm remove(long id) throws NoSuchManagerFormException {
		return remove((Serializable)id);
	}

	/**
	 * Removes the manager form with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the manager form
	 * @return the manager form that was removed
	 * @throws NoSuchManagerFormException if a manager form with the primary key could not be found
	 */
	@Override
	public ManagerForm remove(Serializable primaryKey)
		throws NoSuchManagerFormException {

		Session session = null;

		try {
			session = openSession();

			ManagerForm managerForm = (ManagerForm)session.get(
				ManagerFormImpl.class, primaryKey);

			if (managerForm == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchManagerFormException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(managerForm);
		}
		catch (NoSuchManagerFormException noSuchEntityException) {
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
	protected ManagerForm removeImpl(ManagerForm managerForm) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(managerForm)) {
				managerForm = (ManagerForm)session.get(
					ManagerFormImpl.class, managerForm.getPrimaryKeyObj());
			}

			if (managerForm != null) {
				session.delete(managerForm);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (managerForm != null) {
			clearCache(managerForm);
		}

		return managerForm;
	}

	@Override
	public ManagerForm updateImpl(ManagerForm managerForm) {
		boolean isNew = managerForm.isNew();

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(managerForm);
			}
			else {
				managerForm = (ManagerForm)session.merge(managerForm);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(ManagerFormImpl.class, managerForm, false, true);

		if (isNew) {
			managerForm.setNew(false);
		}

		managerForm.resetOriginalValues();

		return managerForm;
	}

	/**
	 * Returns the manager form with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the manager form
	 * @return the manager form
	 * @throws NoSuchManagerFormException if a manager form with the primary key could not be found
	 */
	@Override
	public ManagerForm findByPrimaryKey(Serializable primaryKey)
		throws NoSuchManagerFormException {

		ManagerForm managerForm = fetchByPrimaryKey(primaryKey);

		if (managerForm == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchManagerFormException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return managerForm;
	}

	/**
	 * Returns the manager form with the primary key or throws a <code>NoSuchManagerFormException</code> if it could not be found.
	 *
	 * @param id the primary key of the manager form
	 * @return the manager form
	 * @throws NoSuchManagerFormException if a manager form with the primary key could not be found
	 */
	@Override
	public ManagerForm findByPrimaryKey(long id)
		throws NoSuchManagerFormException {

		return findByPrimaryKey((Serializable)id);
	}

	/**
	 * Returns the manager form with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the manager form
	 * @return the manager form, or <code>null</code> if a manager form with the primary key could not be found
	 */
	@Override
	public ManagerForm fetchByPrimaryKey(long id) {
		return fetchByPrimaryKey((Serializable)id);
	}

	/**
	 * Returns all the manager forms.
	 *
	 * @return the manager forms
	 */
	@Override
	public List<ManagerForm> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the manager forms.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ManagerFormModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of manager forms
	 * @param end the upper bound of the range of manager forms (not inclusive)
	 * @return the range of manager forms
	 */
	@Override
	public List<ManagerForm> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the manager forms.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ManagerFormModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of manager forms
	 * @param end the upper bound of the range of manager forms (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of manager forms
	 */
	@Override
	public List<ManagerForm> findAll(
		int start, int end, OrderByComparator<ManagerForm> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the manager forms.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ManagerFormModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of manager forms
	 * @param end the upper bound of the range of manager forms (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of manager forms
	 */
	@Override
	public List<ManagerForm> findAll(
		int start, int end, OrderByComparator<ManagerForm> orderByComparator,
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

		List<ManagerForm> list = null;

		if (useFinderCache) {
			list = (List<ManagerForm>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_MANAGERFORM);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_MANAGERFORM;

				sql = sql.concat(ManagerFormModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<ManagerForm>)QueryUtil.list(
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
	 * Removes all the manager forms from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (ManagerForm managerForm : findAll()) {
			remove(managerForm);
		}
	}

	/**
	 * Returns the number of manager forms.
	 *
	 * @return the number of manager forms
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_MANAGERFORM);

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
		return _SQL_SELECT_MANAGERFORM;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return ManagerFormModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the manager form persistence.
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

		_setManagerFormUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setManagerFormUtilPersistence(null);

		entityCache.removeCache(ManagerFormImpl.class.getName());
	}

	private void _setManagerFormUtilPersistence(
		ManagerFormPersistence managerFormPersistence) {

		try {
			Field field = ManagerFormUtil.class.getDeclaredField(
				"_persistence");

			field.setAccessible(true);

			field.set(null, managerFormPersistence);
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

	private static final String _SQL_SELECT_MANAGERFORM =
		"SELECT managerForm FROM ManagerForm managerForm";

	private static final String _SQL_COUNT_MANAGERFORM =
		"SELECT COUNT(managerForm) FROM ManagerForm managerForm";

	private static final String _ORDER_BY_ENTITY_ALIAS = "managerForm.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No ManagerForm exists with the primary key ";

	private static final Log _log = LogFactoryUtil.getLog(
		ManagerFormPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"id"});

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}