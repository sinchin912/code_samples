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

import com.trantorinc.synergy.notice.core.exception.NoSuchHrFormException;
import com.trantorinc.synergy.notice.core.model.HrForm;
import com.trantorinc.synergy.notice.core.model.HrFormTable;
import com.trantorinc.synergy.notice.core.model.impl.HrFormImpl;
import com.trantorinc.synergy.notice.core.model.impl.HrFormModelImpl;
import com.trantorinc.synergy.notice.core.service.persistence.HrFormPersistence;
import com.trantorinc.synergy.notice.core.service.persistence.HrFormUtil;
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
 * The persistence implementation for the hr form service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = {HrFormPersistence.class, BasePersistence.class})
public class HrFormPersistenceImpl
	extends BasePersistenceImpl<HrForm> implements HrFormPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>HrFormUtil</code> to access the hr form persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		HrFormImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;

	public HrFormPersistenceImpl() {
		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("id", "id_");

		setDBColumnNames(dbColumnNames);

		setModelClass(HrForm.class);

		setModelImplClass(HrFormImpl.class);
		setModelPKClass(long.class);

		setTable(HrFormTable.INSTANCE);
	}

	/**
	 * Caches the hr form in the entity cache if it is enabled.
	 *
	 * @param hrForm the hr form
	 */
	@Override
	public void cacheResult(HrForm hrForm) {
		entityCache.putResult(HrFormImpl.class, hrForm.getPrimaryKey(), hrForm);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the hr forms in the entity cache if it is enabled.
	 *
	 * @param hrForms the hr forms
	 */
	@Override
	public void cacheResult(List<HrForm> hrForms) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (hrForms.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (HrForm hrForm : hrForms) {
			if (entityCache.getResult(
					HrFormImpl.class, hrForm.getPrimaryKey()) == null) {

				cacheResult(hrForm);
			}
		}
	}

	/**
	 * Clears the cache for all hr forms.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(HrFormImpl.class);

		finderCache.clearCache(HrFormImpl.class);
	}

	/**
	 * Clears the cache for the hr form.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(HrForm hrForm) {
		entityCache.removeResult(HrFormImpl.class, hrForm);
	}

	@Override
	public void clearCache(List<HrForm> hrForms) {
		for (HrForm hrForm : hrForms) {
			entityCache.removeResult(HrFormImpl.class, hrForm);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(HrFormImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(HrFormImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new hr form with the primary key. Does not add the hr form to the database.
	 *
	 * @param id the primary key for the new hr form
	 * @return the new hr form
	 */
	@Override
	public HrForm create(long id) {
		HrForm hrForm = new HrFormImpl();

		hrForm.setNew(true);
		hrForm.setPrimaryKey(id);

		return hrForm;
	}

	/**
	 * Removes the hr form with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the hr form
	 * @return the hr form that was removed
	 * @throws NoSuchHrFormException if a hr form with the primary key could not be found
	 */
	@Override
	public HrForm remove(long id) throws NoSuchHrFormException {
		return remove((Serializable)id);
	}

	/**
	 * Removes the hr form with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the hr form
	 * @return the hr form that was removed
	 * @throws NoSuchHrFormException if a hr form with the primary key could not be found
	 */
	@Override
	public HrForm remove(Serializable primaryKey) throws NoSuchHrFormException {
		Session session = null;

		try {
			session = openSession();

			HrForm hrForm = (HrForm)session.get(HrFormImpl.class, primaryKey);

			if (hrForm == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchHrFormException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(hrForm);
		}
		catch (NoSuchHrFormException noSuchEntityException) {
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
	protected HrForm removeImpl(HrForm hrForm) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(hrForm)) {
				hrForm = (HrForm)session.get(
					HrFormImpl.class, hrForm.getPrimaryKeyObj());
			}

			if (hrForm != null) {
				session.delete(hrForm);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (hrForm != null) {
			clearCache(hrForm);
		}

		return hrForm;
	}

	@Override
	public HrForm updateImpl(HrForm hrForm) {
		boolean isNew = hrForm.isNew();

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(hrForm);
			}
			else {
				hrForm = (HrForm)session.merge(hrForm);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(HrFormImpl.class, hrForm, false, true);

		if (isNew) {
			hrForm.setNew(false);
		}

		hrForm.resetOriginalValues();

		return hrForm;
	}

	/**
	 * Returns the hr form with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the hr form
	 * @return the hr form
	 * @throws NoSuchHrFormException if a hr form with the primary key could not be found
	 */
	@Override
	public HrForm findByPrimaryKey(Serializable primaryKey)
		throws NoSuchHrFormException {

		HrForm hrForm = fetchByPrimaryKey(primaryKey);

		if (hrForm == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchHrFormException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return hrForm;
	}

	/**
	 * Returns the hr form with the primary key or throws a <code>NoSuchHrFormException</code> if it could not be found.
	 *
	 * @param id the primary key of the hr form
	 * @return the hr form
	 * @throws NoSuchHrFormException if a hr form with the primary key could not be found
	 */
	@Override
	public HrForm findByPrimaryKey(long id) throws NoSuchHrFormException {
		return findByPrimaryKey((Serializable)id);
	}

	/**
	 * Returns the hr form with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the hr form
	 * @return the hr form, or <code>null</code> if a hr form with the primary key could not be found
	 */
	@Override
	public HrForm fetchByPrimaryKey(long id) {
		return fetchByPrimaryKey((Serializable)id);
	}

	/**
	 * Returns all the hr forms.
	 *
	 * @return the hr forms
	 */
	@Override
	public List<HrForm> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the hr forms.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HrFormModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of hr forms
	 * @param end the upper bound of the range of hr forms (not inclusive)
	 * @return the range of hr forms
	 */
	@Override
	public List<HrForm> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the hr forms.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HrFormModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of hr forms
	 * @param end the upper bound of the range of hr forms (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of hr forms
	 */
	@Override
	public List<HrForm> findAll(
		int start, int end, OrderByComparator<HrForm> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the hr forms.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HrFormModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of hr forms
	 * @param end the upper bound of the range of hr forms (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of hr forms
	 */
	@Override
	public List<HrForm> findAll(
		int start, int end, OrderByComparator<HrForm> orderByComparator,
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

		List<HrForm> list = null;

		if (useFinderCache) {
			list = (List<HrForm>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_HRFORM);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_HRFORM;

				sql = sql.concat(HrFormModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<HrForm>)QueryUtil.list(
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
	 * Removes all the hr forms from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (HrForm hrForm : findAll()) {
			remove(hrForm);
		}
	}

	/**
	 * Returns the number of hr forms.
	 *
	 * @return the number of hr forms
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_HRFORM);

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
		return _SQL_SELECT_HRFORM;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return HrFormModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the hr form persistence.
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

		_setHrFormUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setHrFormUtilPersistence(null);

		entityCache.removeCache(HrFormImpl.class.getName());
	}

	private void _setHrFormUtilPersistence(
		HrFormPersistence hrFormPersistence) {

		try {
			Field field = HrFormUtil.class.getDeclaredField("_persistence");

			field.setAccessible(true);

			field.set(null, hrFormPersistence);
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

	private static final String _SQL_SELECT_HRFORM =
		"SELECT hrForm FROM HrForm hrForm";

	private static final String _SQL_COUNT_HRFORM =
		"SELECT COUNT(hrForm) FROM HrForm hrForm";

	private static final String _ORDER_BY_ENTITY_ALIAS = "hrForm.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No HrForm exists with the primary key ";

	private static final Log _log = LogFactoryUtil.getLog(
		HrFormPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"id"});

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}