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

package com.trantorinc.synergy.expense.core.service.persistence.impl;

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

import com.trantorinc.synergy.expense.core.exception.NoSuchExpenseLineException;
import com.trantorinc.synergy.expense.core.model.ExpenseLine;
import com.trantorinc.synergy.expense.core.model.ExpenseLineTable;
import com.trantorinc.synergy.expense.core.model.impl.ExpenseLineImpl;
import com.trantorinc.synergy.expense.core.model.impl.ExpenseLineModelImpl;
import com.trantorinc.synergy.expense.core.service.persistence.ExpenseLinePersistence;
import com.trantorinc.synergy.expense.core.service.persistence.ExpenseLineUtil;
import com.trantorinc.synergy.expense.core.service.persistence.impl.constants.EXPENSEPersistenceConstants;

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
 * The persistence implementation for the expense line service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = {ExpenseLinePersistence.class, BasePersistence.class})
public class ExpenseLinePersistenceImpl
	extends BasePersistenceImpl<ExpenseLine> implements ExpenseLinePersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>ExpenseLineUtil</code> to access the expense line persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		ExpenseLineImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;

	public ExpenseLinePersistenceImpl() {
		setModelClass(ExpenseLine.class);

		setModelImplClass(ExpenseLineImpl.class);
		setModelPKClass(long.class);

		setTable(ExpenseLineTable.INSTANCE);
	}

	/**
	 * Caches the expense line in the entity cache if it is enabled.
	 *
	 * @param expenseLine the expense line
	 */
	@Override
	public void cacheResult(ExpenseLine expenseLine) {
		entityCache.putResult(
			ExpenseLineImpl.class, expenseLine.getPrimaryKey(), expenseLine);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the expense lines in the entity cache if it is enabled.
	 *
	 * @param expenseLines the expense lines
	 */
	@Override
	public void cacheResult(List<ExpenseLine> expenseLines) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (expenseLines.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (ExpenseLine expenseLine : expenseLines) {
			if (entityCache.getResult(
					ExpenseLineImpl.class, expenseLine.getPrimaryKey()) ==
						null) {

				cacheResult(expenseLine);
			}
		}
	}

	/**
	 * Clears the cache for all expense lines.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ExpenseLineImpl.class);

		finderCache.clearCache(ExpenseLineImpl.class);
	}

	/**
	 * Clears the cache for the expense line.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ExpenseLine expenseLine) {
		entityCache.removeResult(ExpenseLineImpl.class, expenseLine);
	}

	@Override
	public void clearCache(List<ExpenseLine> expenseLines) {
		for (ExpenseLine expenseLine : expenseLines) {
			entityCache.removeResult(ExpenseLineImpl.class, expenseLine);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(ExpenseLineImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(ExpenseLineImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new expense line with the primary key. Does not add the expense line to the database.
	 *
	 * @param lineId the primary key for the new expense line
	 * @return the new expense line
	 */
	@Override
	public ExpenseLine create(long lineId) {
		ExpenseLine expenseLine = new ExpenseLineImpl();

		expenseLine.setNew(true);
		expenseLine.setPrimaryKey(lineId);

		return expenseLine;
	}

	/**
	 * Removes the expense line with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param lineId the primary key of the expense line
	 * @return the expense line that was removed
	 * @throws NoSuchExpenseLineException if a expense line with the primary key could not be found
	 */
	@Override
	public ExpenseLine remove(long lineId) throws NoSuchExpenseLineException {
		return remove((Serializable)lineId);
	}

	/**
	 * Removes the expense line with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the expense line
	 * @return the expense line that was removed
	 * @throws NoSuchExpenseLineException if a expense line with the primary key could not be found
	 */
	@Override
	public ExpenseLine remove(Serializable primaryKey)
		throws NoSuchExpenseLineException {

		Session session = null;

		try {
			session = openSession();

			ExpenseLine expenseLine = (ExpenseLine)session.get(
				ExpenseLineImpl.class, primaryKey);

			if (expenseLine == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchExpenseLineException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(expenseLine);
		}
		catch (NoSuchExpenseLineException noSuchEntityException) {
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
	protected ExpenseLine removeImpl(ExpenseLine expenseLine) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(expenseLine)) {
				expenseLine = (ExpenseLine)session.get(
					ExpenseLineImpl.class, expenseLine.getPrimaryKeyObj());
			}

			if (expenseLine != null) {
				session.delete(expenseLine);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (expenseLine != null) {
			clearCache(expenseLine);
		}

		return expenseLine;
	}

	@Override
	public ExpenseLine updateImpl(ExpenseLine expenseLine) {
		boolean isNew = expenseLine.isNew();

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(expenseLine);
			}
			else {
				expenseLine = (ExpenseLine)session.merge(expenseLine);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(ExpenseLineImpl.class, expenseLine, false, true);

		if (isNew) {
			expenseLine.setNew(false);
		}

		expenseLine.resetOriginalValues();

		return expenseLine;
	}

	/**
	 * Returns the expense line with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the expense line
	 * @return the expense line
	 * @throws NoSuchExpenseLineException if a expense line with the primary key could not be found
	 */
	@Override
	public ExpenseLine findByPrimaryKey(Serializable primaryKey)
		throws NoSuchExpenseLineException {

		ExpenseLine expenseLine = fetchByPrimaryKey(primaryKey);

		if (expenseLine == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchExpenseLineException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return expenseLine;
	}

	/**
	 * Returns the expense line with the primary key or throws a <code>NoSuchExpenseLineException</code> if it could not be found.
	 *
	 * @param lineId the primary key of the expense line
	 * @return the expense line
	 * @throws NoSuchExpenseLineException if a expense line with the primary key could not be found
	 */
	@Override
	public ExpenseLine findByPrimaryKey(long lineId)
		throws NoSuchExpenseLineException {

		return findByPrimaryKey((Serializable)lineId);
	}

	/**
	 * Returns the expense line with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param lineId the primary key of the expense line
	 * @return the expense line, or <code>null</code> if a expense line with the primary key could not be found
	 */
	@Override
	public ExpenseLine fetchByPrimaryKey(long lineId) {
		return fetchByPrimaryKey((Serializable)lineId);
	}

	/**
	 * Returns all the expense lines.
	 *
	 * @return the expense lines
	 */
	@Override
	public List<ExpenseLine> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the expense lines.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ExpenseLineModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of expense lines
	 * @param end the upper bound of the range of expense lines (not inclusive)
	 * @return the range of expense lines
	 */
	@Override
	public List<ExpenseLine> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the expense lines.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ExpenseLineModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of expense lines
	 * @param end the upper bound of the range of expense lines (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of expense lines
	 */
	@Override
	public List<ExpenseLine> findAll(
		int start, int end, OrderByComparator<ExpenseLine> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the expense lines.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ExpenseLineModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of expense lines
	 * @param end the upper bound of the range of expense lines (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of expense lines
	 */
	@Override
	public List<ExpenseLine> findAll(
		int start, int end, OrderByComparator<ExpenseLine> orderByComparator,
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

		List<ExpenseLine> list = null;

		if (useFinderCache) {
			list = (List<ExpenseLine>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_EXPENSELINE);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_EXPENSELINE;

				sql = sql.concat(ExpenseLineModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<ExpenseLine>)QueryUtil.list(
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
	 * Removes all the expense lines from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (ExpenseLine expenseLine : findAll()) {
			remove(expenseLine);
		}
	}

	/**
	 * Returns the number of expense lines.
	 *
	 * @return the number of expense lines
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_EXPENSELINE);

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
		return "lineId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_EXPENSELINE;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return ExpenseLineModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the expense line persistence.
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

		_setExpenseLineUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setExpenseLineUtilPersistence(null);

		entityCache.removeCache(ExpenseLineImpl.class.getName());
	}

	private void _setExpenseLineUtilPersistence(
		ExpenseLinePersistence expenseLinePersistence) {

		try {
			Field field = ExpenseLineUtil.class.getDeclaredField(
				"_persistence");

			field.setAccessible(true);

			field.set(null, expenseLinePersistence);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@Override
	@Reference(
		target = EXPENSEPersistenceConstants.SERVICE_CONFIGURATION_FILTER,
		unbind = "-"
	)
	public void setConfiguration(Configuration configuration) {
	}

	@Override
	@Reference(
		target = EXPENSEPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	@Reference(
		target = EXPENSEPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@Reference
	protected EntityCache entityCache;

	@Reference
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_EXPENSELINE =
		"SELECT expenseLine FROM ExpenseLine expenseLine";

	private static final String _SQL_COUNT_EXPENSELINE =
		"SELECT COUNT(expenseLine) FROM ExpenseLine expenseLine";

	private static final String _ORDER_BY_ENTITY_ALIAS = "expenseLine.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No ExpenseLine exists with the primary key ";

	private static final Log _log = LogFactoryUtil.getLog(
		ExpenseLinePersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}