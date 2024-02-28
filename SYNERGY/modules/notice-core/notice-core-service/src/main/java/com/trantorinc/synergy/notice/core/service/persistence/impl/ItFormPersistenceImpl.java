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

import com.trantorinc.synergy.notice.core.exception.NoSuchItFormException;
import com.trantorinc.synergy.notice.core.model.ItForm;
import com.trantorinc.synergy.notice.core.model.ItFormTable;
import com.trantorinc.synergy.notice.core.model.impl.ItFormImpl;
import com.trantorinc.synergy.notice.core.model.impl.ItFormModelImpl;
import com.trantorinc.synergy.notice.core.service.persistence.ItFormPersistence;
import com.trantorinc.synergy.notice.core.service.persistence.ItFormUtil;
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
 * The persistence implementation for the it form service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = {ItFormPersistence.class, BasePersistence.class})
public class ItFormPersistenceImpl
	extends BasePersistenceImpl<ItForm> implements ItFormPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>ItFormUtil</code> to access the it form persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		ItFormImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;

	public ItFormPersistenceImpl() {
		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("id", "id_");

		setDBColumnNames(dbColumnNames);

		setModelClass(ItForm.class);

		setModelImplClass(ItFormImpl.class);
		setModelPKClass(long.class);

		setTable(ItFormTable.INSTANCE);
	}

	/**
	 * Caches the it form in the entity cache if it is enabled.
	 *
	 * @param itForm the it form
	 */
	@Override
	public void cacheResult(ItForm itForm) {
		entityCache.putResult(ItFormImpl.class, itForm.getPrimaryKey(), itForm);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the it forms in the entity cache if it is enabled.
	 *
	 * @param itForms the it forms
	 */
	@Override
	public void cacheResult(List<ItForm> itForms) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (itForms.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (ItForm itForm : itForms) {
			if (entityCache.getResult(
					ItFormImpl.class, itForm.getPrimaryKey()) == null) {

				cacheResult(itForm);
			}
		}
	}

	/**
	 * Clears the cache for all it forms.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ItFormImpl.class);

		finderCache.clearCache(ItFormImpl.class);
	}

	/**
	 * Clears the cache for the it form.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ItForm itForm) {
		entityCache.removeResult(ItFormImpl.class, itForm);
	}

	@Override
	public void clearCache(List<ItForm> itForms) {
		for (ItForm itForm : itForms) {
			entityCache.removeResult(ItFormImpl.class, itForm);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(ItFormImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(ItFormImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new it form with the primary key. Does not add the it form to the database.
	 *
	 * @param id the primary key for the new it form
	 * @return the new it form
	 */
	@Override
	public ItForm create(long id) {
		ItForm itForm = new ItFormImpl();

		itForm.setNew(true);
		itForm.setPrimaryKey(id);

		return itForm;
	}

	/**
	 * Removes the it form with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the it form
	 * @return the it form that was removed
	 * @throws NoSuchItFormException if a it form with the primary key could not be found
	 */
	@Override
	public ItForm remove(long id) throws NoSuchItFormException {
		return remove((Serializable)id);
	}

	/**
	 * Removes the it form with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the it form
	 * @return the it form that was removed
	 * @throws NoSuchItFormException if a it form with the primary key could not be found
	 */
	@Override
	public ItForm remove(Serializable primaryKey) throws NoSuchItFormException {
		Session session = null;

		try {
			session = openSession();

			ItForm itForm = (ItForm)session.get(ItFormImpl.class, primaryKey);

			if (itForm == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchItFormException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(itForm);
		}
		catch (NoSuchItFormException noSuchEntityException) {
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
	protected ItForm removeImpl(ItForm itForm) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(itForm)) {
				itForm = (ItForm)session.get(
					ItFormImpl.class, itForm.getPrimaryKeyObj());
			}

			if (itForm != null) {
				session.delete(itForm);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (itForm != null) {
			clearCache(itForm);
		}

		return itForm;
	}

	@Override
	public ItForm updateImpl(ItForm itForm) {
		boolean isNew = itForm.isNew();

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(itForm);
			}
			else {
				itForm = (ItForm)session.merge(itForm);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(ItFormImpl.class, itForm, false, true);

		if (isNew) {
			itForm.setNew(false);
		}

		itForm.resetOriginalValues();

		return itForm;
	}

	/**
	 * Returns the it form with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the it form
	 * @return the it form
	 * @throws NoSuchItFormException if a it form with the primary key could not be found
	 */
	@Override
	public ItForm findByPrimaryKey(Serializable primaryKey)
		throws NoSuchItFormException {

		ItForm itForm = fetchByPrimaryKey(primaryKey);

		if (itForm == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchItFormException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return itForm;
	}

	/**
	 * Returns the it form with the primary key or throws a <code>NoSuchItFormException</code> if it could not be found.
	 *
	 * @param id the primary key of the it form
	 * @return the it form
	 * @throws NoSuchItFormException if a it form with the primary key could not be found
	 */
	@Override
	public ItForm findByPrimaryKey(long id) throws NoSuchItFormException {
		return findByPrimaryKey((Serializable)id);
	}

	/**
	 * Returns the it form with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the it form
	 * @return the it form, or <code>null</code> if a it form with the primary key could not be found
	 */
	@Override
	public ItForm fetchByPrimaryKey(long id) {
		return fetchByPrimaryKey((Serializable)id);
	}

	/**
	 * Returns all the it forms.
	 *
	 * @return the it forms
	 */
	@Override
	public List<ItForm> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the it forms.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ItFormModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of it forms
	 * @param end the upper bound of the range of it forms (not inclusive)
	 * @return the range of it forms
	 */
	@Override
	public List<ItForm> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the it forms.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ItFormModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of it forms
	 * @param end the upper bound of the range of it forms (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of it forms
	 */
	@Override
	public List<ItForm> findAll(
		int start, int end, OrderByComparator<ItForm> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the it forms.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ItFormModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of it forms
	 * @param end the upper bound of the range of it forms (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of it forms
	 */
	@Override
	public List<ItForm> findAll(
		int start, int end, OrderByComparator<ItForm> orderByComparator,
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

		List<ItForm> list = null;

		if (useFinderCache) {
			list = (List<ItForm>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_ITFORM);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_ITFORM;

				sql = sql.concat(ItFormModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<ItForm>)QueryUtil.list(
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
	 * Removes all the it forms from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (ItForm itForm : findAll()) {
			remove(itForm);
		}
	}

	/**
	 * Returns the number of it forms.
	 *
	 * @return the number of it forms
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_ITFORM);

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
		return _SQL_SELECT_ITFORM;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return ItFormModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the it form persistence.
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

		_setItFormUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setItFormUtilPersistence(null);

		entityCache.removeCache(ItFormImpl.class.getName());
	}

	private void _setItFormUtilPersistence(
		ItFormPersistence itFormPersistence) {

		try {
			Field field = ItFormUtil.class.getDeclaredField("_persistence");

			field.setAccessible(true);

			field.set(null, itFormPersistence);
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

	private static final String _SQL_SELECT_ITFORM =
		"SELECT itForm FROM ItForm itForm";

	private static final String _SQL_COUNT_ITFORM =
		"SELECT COUNT(itForm) FROM ItForm itForm";

	private static final String _ORDER_BY_ENTITY_ALIAS = "itForm.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No ItForm exists with the primary key ";

	private static final Log _log = LogFactoryUtil.getLog(
		ItFormPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"id"});

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}