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

import com.trantorinc.synergy.notice.core.exception.NoSuchExitFormException;
import com.trantorinc.synergy.notice.core.model.ExitForm;
import com.trantorinc.synergy.notice.core.model.ExitFormTable;
import com.trantorinc.synergy.notice.core.model.impl.ExitFormImpl;
import com.trantorinc.synergy.notice.core.model.impl.ExitFormModelImpl;
import com.trantorinc.synergy.notice.core.service.persistence.ExitFormPersistence;
import com.trantorinc.synergy.notice.core.service.persistence.ExitFormUtil;
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
 * The persistence implementation for the exit form service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = {ExitFormPersistence.class, BasePersistence.class})
public class ExitFormPersistenceImpl
	extends BasePersistenceImpl<ExitForm> implements ExitFormPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>ExitFormUtil</code> to access the exit form persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		ExitFormImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;

	public ExitFormPersistenceImpl() {
		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("id", "id_");

		setDBColumnNames(dbColumnNames);

		setModelClass(ExitForm.class);

		setModelImplClass(ExitFormImpl.class);
		setModelPKClass(long.class);

		setTable(ExitFormTable.INSTANCE);
	}

	/**
	 * Caches the exit form in the entity cache if it is enabled.
	 *
	 * @param exitForm the exit form
	 */
	@Override
	public void cacheResult(ExitForm exitForm) {
		entityCache.putResult(
			ExitFormImpl.class, exitForm.getPrimaryKey(), exitForm);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the exit forms in the entity cache if it is enabled.
	 *
	 * @param exitForms the exit forms
	 */
	@Override
	public void cacheResult(List<ExitForm> exitForms) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (exitForms.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (ExitForm exitForm : exitForms) {
			if (entityCache.getResult(
					ExitFormImpl.class, exitForm.getPrimaryKey()) == null) {

				cacheResult(exitForm);
			}
		}
	}

	/**
	 * Clears the cache for all exit forms.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ExitFormImpl.class);

		finderCache.clearCache(ExitFormImpl.class);
	}

	/**
	 * Clears the cache for the exit form.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ExitForm exitForm) {
		entityCache.removeResult(ExitFormImpl.class, exitForm);
	}

	@Override
	public void clearCache(List<ExitForm> exitForms) {
		for (ExitForm exitForm : exitForms) {
			entityCache.removeResult(ExitFormImpl.class, exitForm);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(ExitFormImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(ExitFormImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new exit form with the primary key. Does not add the exit form to the database.
	 *
	 * @param id the primary key for the new exit form
	 * @return the new exit form
	 */
	@Override
	public ExitForm create(long id) {
		ExitForm exitForm = new ExitFormImpl();

		exitForm.setNew(true);
		exitForm.setPrimaryKey(id);

		return exitForm;
	}

	/**
	 * Removes the exit form with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the exit form
	 * @return the exit form that was removed
	 * @throws NoSuchExitFormException if a exit form with the primary key could not be found
	 */
	@Override
	public ExitForm remove(long id) throws NoSuchExitFormException {
		return remove((Serializable)id);
	}

	/**
	 * Removes the exit form with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the exit form
	 * @return the exit form that was removed
	 * @throws NoSuchExitFormException if a exit form with the primary key could not be found
	 */
	@Override
	public ExitForm remove(Serializable primaryKey)
		throws NoSuchExitFormException {

		Session session = null;

		try {
			session = openSession();

			ExitForm exitForm = (ExitForm)session.get(
				ExitFormImpl.class, primaryKey);

			if (exitForm == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchExitFormException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(exitForm);
		}
		catch (NoSuchExitFormException noSuchEntityException) {
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
	protected ExitForm removeImpl(ExitForm exitForm) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(exitForm)) {
				exitForm = (ExitForm)session.get(
					ExitFormImpl.class, exitForm.getPrimaryKeyObj());
			}

			if (exitForm != null) {
				session.delete(exitForm);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (exitForm != null) {
			clearCache(exitForm);
		}

		return exitForm;
	}

	@Override
	public ExitForm updateImpl(ExitForm exitForm) {
		boolean isNew = exitForm.isNew();

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(exitForm);
			}
			else {
				exitForm = (ExitForm)session.merge(exitForm);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(ExitFormImpl.class, exitForm, false, true);

		if (isNew) {
			exitForm.setNew(false);
		}

		exitForm.resetOriginalValues();

		return exitForm;
	}

	/**
	 * Returns the exit form with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the exit form
	 * @return the exit form
	 * @throws NoSuchExitFormException if a exit form with the primary key could not be found
	 */
	@Override
	public ExitForm findByPrimaryKey(Serializable primaryKey)
		throws NoSuchExitFormException {

		ExitForm exitForm = fetchByPrimaryKey(primaryKey);

		if (exitForm == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchExitFormException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return exitForm;
	}

	/**
	 * Returns the exit form with the primary key or throws a <code>NoSuchExitFormException</code> if it could not be found.
	 *
	 * @param id the primary key of the exit form
	 * @return the exit form
	 * @throws NoSuchExitFormException if a exit form with the primary key could not be found
	 */
	@Override
	public ExitForm findByPrimaryKey(long id) throws NoSuchExitFormException {
		return findByPrimaryKey((Serializable)id);
	}

	/**
	 * Returns the exit form with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the exit form
	 * @return the exit form, or <code>null</code> if a exit form with the primary key could not be found
	 */
	@Override
	public ExitForm fetchByPrimaryKey(long id) {
		return fetchByPrimaryKey((Serializable)id);
	}

	/**
	 * Returns all the exit forms.
	 *
	 * @return the exit forms
	 */
	@Override
	public List<ExitForm> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the exit forms.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ExitFormModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of exit forms
	 * @param end the upper bound of the range of exit forms (not inclusive)
	 * @return the range of exit forms
	 */
	@Override
	public List<ExitForm> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the exit forms.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ExitFormModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of exit forms
	 * @param end the upper bound of the range of exit forms (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of exit forms
	 */
	@Override
	public List<ExitForm> findAll(
		int start, int end, OrderByComparator<ExitForm> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the exit forms.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ExitFormModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of exit forms
	 * @param end the upper bound of the range of exit forms (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of exit forms
	 */
	@Override
	public List<ExitForm> findAll(
		int start, int end, OrderByComparator<ExitForm> orderByComparator,
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

		List<ExitForm> list = null;

		if (useFinderCache) {
			list = (List<ExitForm>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_EXITFORM);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_EXITFORM;

				sql = sql.concat(ExitFormModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<ExitForm>)QueryUtil.list(
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
	 * Removes all the exit forms from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (ExitForm exitForm : findAll()) {
			remove(exitForm);
		}
	}

	/**
	 * Returns the number of exit forms.
	 *
	 * @return the number of exit forms
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_EXITFORM);

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
		return _SQL_SELECT_EXITFORM;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return ExitFormModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the exit form persistence.
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

		_setExitFormUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setExitFormUtilPersistence(null);

		entityCache.removeCache(ExitFormImpl.class.getName());
	}

	private void _setExitFormUtilPersistence(
		ExitFormPersistence exitFormPersistence) {

		try {
			Field field = ExitFormUtil.class.getDeclaredField("_persistence");

			field.setAccessible(true);

			field.set(null, exitFormPersistence);
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

	private static final String _SQL_SELECT_EXITFORM =
		"SELECT exitForm FROM ExitForm exitForm";

	private static final String _SQL_COUNT_EXITFORM =
		"SELECT COUNT(exitForm) FROM ExitForm exitForm";

	private static final String _ORDER_BY_ENTITY_ALIAS = "exitForm.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No ExitForm exists with the primary key ";

	private static final Log _log = LogFactoryUtil.getLog(
		ExitFormPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"id"});

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}