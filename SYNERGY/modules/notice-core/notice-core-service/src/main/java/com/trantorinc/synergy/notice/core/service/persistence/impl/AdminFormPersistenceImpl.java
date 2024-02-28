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

import com.trantorinc.synergy.notice.core.exception.NoSuchAdminFormException;
import com.trantorinc.synergy.notice.core.model.AdminForm;
import com.trantorinc.synergy.notice.core.model.AdminFormTable;
import com.trantorinc.synergy.notice.core.model.impl.AdminFormImpl;
import com.trantorinc.synergy.notice.core.model.impl.AdminFormModelImpl;
import com.trantorinc.synergy.notice.core.service.persistence.AdminFormPersistence;
import com.trantorinc.synergy.notice.core.service.persistence.AdminFormUtil;
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
 * The persistence implementation for the admin form service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = {AdminFormPersistence.class, BasePersistence.class})
public class AdminFormPersistenceImpl
	extends BasePersistenceImpl<AdminForm> implements AdminFormPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>AdminFormUtil</code> to access the admin form persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		AdminFormImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;

	public AdminFormPersistenceImpl() {
		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("id", "id_");

		setDBColumnNames(dbColumnNames);

		setModelClass(AdminForm.class);

		setModelImplClass(AdminFormImpl.class);
		setModelPKClass(long.class);

		setTable(AdminFormTable.INSTANCE);
	}

	/**
	 * Caches the admin form in the entity cache if it is enabled.
	 *
	 * @param adminForm the admin form
	 */
	@Override
	public void cacheResult(AdminForm adminForm) {
		entityCache.putResult(
			AdminFormImpl.class, adminForm.getPrimaryKey(), adminForm);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the admin forms in the entity cache if it is enabled.
	 *
	 * @param adminForms the admin forms
	 */
	@Override
	public void cacheResult(List<AdminForm> adminForms) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (adminForms.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (AdminForm adminForm : adminForms) {
			if (entityCache.getResult(
					AdminFormImpl.class, adminForm.getPrimaryKey()) == null) {

				cacheResult(adminForm);
			}
		}
	}

	/**
	 * Clears the cache for all admin forms.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(AdminFormImpl.class);

		finderCache.clearCache(AdminFormImpl.class);
	}

	/**
	 * Clears the cache for the admin form.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(AdminForm adminForm) {
		entityCache.removeResult(AdminFormImpl.class, adminForm);
	}

	@Override
	public void clearCache(List<AdminForm> adminForms) {
		for (AdminForm adminForm : adminForms) {
			entityCache.removeResult(AdminFormImpl.class, adminForm);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(AdminFormImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(AdminFormImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new admin form with the primary key. Does not add the admin form to the database.
	 *
	 * @param id the primary key for the new admin form
	 * @return the new admin form
	 */
	@Override
	public AdminForm create(long id) {
		AdminForm adminForm = new AdminFormImpl();

		adminForm.setNew(true);
		adminForm.setPrimaryKey(id);

		return adminForm;
	}

	/**
	 * Removes the admin form with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the admin form
	 * @return the admin form that was removed
	 * @throws NoSuchAdminFormException if a admin form with the primary key could not be found
	 */
	@Override
	public AdminForm remove(long id) throws NoSuchAdminFormException {
		return remove((Serializable)id);
	}

	/**
	 * Removes the admin form with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the admin form
	 * @return the admin form that was removed
	 * @throws NoSuchAdminFormException if a admin form with the primary key could not be found
	 */
	@Override
	public AdminForm remove(Serializable primaryKey)
		throws NoSuchAdminFormException {

		Session session = null;

		try {
			session = openSession();

			AdminForm adminForm = (AdminForm)session.get(
				AdminFormImpl.class, primaryKey);

			if (adminForm == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchAdminFormException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(adminForm);
		}
		catch (NoSuchAdminFormException noSuchEntityException) {
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
	protected AdminForm removeImpl(AdminForm adminForm) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(adminForm)) {
				adminForm = (AdminForm)session.get(
					AdminFormImpl.class, adminForm.getPrimaryKeyObj());
			}

			if (adminForm != null) {
				session.delete(adminForm);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (adminForm != null) {
			clearCache(adminForm);
		}

		return adminForm;
	}

	@Override
	public AdminForm updateImpl(AdminForm adminForm) {
		boolean isNew = adminForm.isNew();

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(adminForm);
			}
			else {
				adminForm = (AdminForm)session.merge(adminForm);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(AdminFormImpl.class, adminForm, false, true);

		if (isNew) {
			adminForm.setNew(false);
		}

		adminForm.resetOriginalValues();

		return adminForm;
	}

	/**
	 * Returns the admin form with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the admin form
	 * @return the admin form
	 * @throws NoSuchAdminFormException if a admin form with the primary key could not be found
	 */
	@Override
	public AdminForm findByPrimaryKey(Serializable primaryKey)
		throws NoSuchAdminFormException {

		AdminForm adminForm = fetchByPrimaryKey(primaryKey);

		if (adminForm == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchAdminFormException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return adminForm;
	}

	/**
	 * Returns the admin form with the primary key or throws a <code>NoSuchAdminFormException</code> if it could not be found.
	 *
	 * @param id the primary key of the admin form
	 * @return the admin form
	 * @throws NoSuchAdminFormException if a admin form with the primary key could not be found
	 */
	@Override
	public AdminForm findByPrimaryKey(long id) throws NoSuchAdminFormException {
		return findByPrimaryKey((Serializable)id);
	}

	/**
	 * Returns the admin form with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the admin form
	 * @return the admin form, or <code>null</code> if a admin form with the primary key could not be found
	 */
	@Override
	public AdminForm fetchByPrimaryKey(long id) {
		return fetchByPrimaryKey((Serializable)id);
	}

	/**
	 * Returns all the admin forms.
	 *
	 * @return the admin forms
	 */
	@Override
	public List<AdminForm> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the admin forms.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AdminFormModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of admin forms
	 * @param end the upper bound of the range of admin forms (not inclusive)
	 * @return the range of admin forms
	 */
	@Override
	public List<AdminForm> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the admin forms.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AdminFormModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of admin forms
	 * @param end the upper bound of the range of admin forms (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of admin forms
	 */
	@Override
	public List<AdminForm> findAll(
		int start, int end, OrderByComparator<AdminForm> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the admin forms.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AdminFormModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of admin forms
	 * @param end the upper bound of the range of admin forms (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of admin forms
	 */
	@Override
	public List<AdminForm> findAll(
		int start, int end, OrderByComparator<AdminForm> orderByComparator,
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

		List<AdminForm> list = null;

		if (useFinderCache) {
			list = (List<AdminForm>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_ADMINFORM);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_ADMINFORM;

				sql = sql.concat(AdminFormModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<AdminForm>)QueryUtil.list(
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
	 * Removes all the admin forms from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (AdminForm adminForm : findAll()) {
			remove(adminForm);
		}
	}

	/**
	 * Returns the number of admin forms.
	 *
	 * @return the number of admin forms
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_ADMINFORM);

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
		return _SQL_SELECT_ADMINFORM;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return AdminFormModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the admin form persistence.
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

		_setAdminFormUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setAdminFormUtilPersistence(null);

		entityCache.removeCache(AdminFormImpl.class.getName());
	}

	private void _setAdminFormUtilPersistence(
		AdminFormPersistence adminFormPersistence) {

		try {
			Field field = AdminFormUtil.class.getDeclaredField("_persistence");

			field.setAccessible(true);

			field.set(null, adminFormPersistence);
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

	private static final String _SQL_SELECT_ADMINFORM =
		"SELECT adminForm FROM AdminForm adminForm";

	private static final String _SQL_COUNT_ADMINFORM =
		"SELECT COUNT(adminForm) FROM AdminForm adminForm";

	private static final String _ORDER_BY_ENTITY_ALIAS = "adminForm.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No AdminForm exists with the primary key ";

	private static final Log _log = LogFactoryUtil.getLog(
		AdminFormPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"id"});

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}