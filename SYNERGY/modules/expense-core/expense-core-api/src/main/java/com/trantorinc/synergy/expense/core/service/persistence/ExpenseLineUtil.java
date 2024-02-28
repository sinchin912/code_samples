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

package com.trantorinc.synergy.expense.core.service.persistence;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.trantorinc.synergy.expense.core.model.ExpenseLine;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the expense line service. This utility wraps <code>com.trantorinc.synergy.expense.core.service.persistence.impl.ExpenseLinePersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ExpenseLinePersistence
 * @generated
 */
public class ExpenseLineUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(ExpenseLine expenseLine) {
		getPersistence().clearCache(expenseLine);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#fetchByPrimaryKeys(Set)
	 */
	public static Map<Serializable, ExpenseLine> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<ExpenseLine> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ExpenseLine> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ExpenseLine> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<ExpenseLine> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static ExpenseLine update(ExpenseLine expenseLine) {
		return getPersistence().update(expenseLine);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static ExpenseLine update(
		ExpenseLine expenseLine, ServiceContext serviceContext) {

		return getPersistence().update(expenseLine, serviceContext);
	}

	/**
	 * Caches the expense line in the entity cache if it is enabled.
	 *
	 * @param expenseLine the expense line
	 */
	public static void cacheResult(ExpenseLine expenseLine) {
		getPersistence().cacheResult(expenseLine);
	}

	/**
	 * Caches the expense lines in the entity cache if it is enabled.
	 *
	 * @param expenseLines the expense lines
	 */
	public static void cacheResult(List<ExpenseLine> expenseLines) {
		getPersistence().cacheResult(expenseLines);
	}

	/**
	 * Creates a new expense line with the primary key. Does not add the expense line to the database.
	 *
	 * @param lineId the primary key for the new expense line
	 * @return the new expense line
	 */
	public static ExpenseLine create(long lineId) {
		return getPersistence().create(lineId);
	}

	/**
	 * Removes the expense line with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param lineId the primary key of the expense line
	 * @return the expense line that was removed
	 * @throws NoSuchExpenseLineException if a expense line with the primary key could not be found
	 */
	public static ExpenseLine remove(long lineId)
		throws com.trantorinc.synergy.expense.core.exception.
			NoSuchExpenseLineException {

		return getPersistence().remove(lineId);
	}

	public static ExpenseLine updateImpl(ExpenseLine expenseLine) {
		return getPersistence().updateImpl(expenseLine);
	}

	/**
	 * Returns the expense line with the primary key or throws a <code>NoSuchExpenseLineException</code> if it could not be found.
	 *
	 * @param lineId the primary key of the expense line
	 * @return the expense line
	 * @throws NoSuchExpenseLineException if a expense line with the primary key could not be found
	 */
	public static ExpenseLine findByPrimaryKey(long lineId)
		throws com.trantorinc.synergy.expense.core.exception.
			NoSuchExpenseLineException {

		return getPersistence().findByPrimaryKey(lineId);
	}

	/**
	 * Returns the expense line with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param lineId the primary key of the expense line
	 * @return the expense line, or <code>null</code> if a expense line with the primary key could not be found
	 */
	public static ExpenseLine fetchByPrimaryKey(long lineId) {
		return getPersistence().fetchByPrimaryKey(lineId);
	}

	/**
	 * Returns all the expense lines.
	 *
	 * @return the expense lines
	 */
	public static List<ExpenseLine> findAll() {
		return getPersistence().findAll();
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
	public static List<ExpenseLine> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
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
	public static List<ExpenseLine> findAll(
		int start, int end, OrderByComparator<ExpenseLine> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
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
	public static List<ExpenseLine> findAll(
		int start, int end, OrderByComparator<ExpenseLine> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the expense lines from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of expense lines.
	 *
	 * @return the number of expense lines
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static ExpenseLinePersistence getPersistence() {
		return _persistence;
	}

	private static volatile ExpenseLinePersistence _persistence;

}