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

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import com.trantorinc.synergy.expense.core.exception.NoSuchExpenseLineException;
import com.trantorinc.synergy.expense.core.model.ExpenseLine;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the expense line service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ExpenseLineUtil
 * @generated
 */
@ProviderType
public interface ExpenseLinePersistence extends BasePersistence<ExpenseLine> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ExpenseLineUtil} to access the expense line persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Caches the expense line in the entity cache if it is enabled.
	 *
	 * @param expenseLine the expense line
	 */
	public void cacheResult(ExpenseLine expenseLine);

	/**
	 * Caches the expense lines in the entity cache if it is enabled.
	 *
	 * @param expenseLines the expense lines
	 */
	public void cacheResult(java.util.List<ExpenseLine> expenseLines);

	/**
	 * Creates a new expense line with the primary key. Does not add the expense line to the database.
	 *
	 * @param lineId the primary key for the new expense line
	 * @return the new expense line
	 */
	public ExpenseLine create(long lineId);

	/**
	 * Removes the expense line with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param lineId the primary key of the expense line
	 * @return the expense line that was removed
	 * @throws NoSuchExpenseLineException if a expense line with the primary key could not be found
	 */
	public ExpenseLine remove(long lineId) throws NoSuchExpenseLineException;

	public ExpenseLine updateImpl(ExpenseLine expenseLine);

	/**
	 * Returns the expense line with the primary key or throws a <code>NoSuchExpenseLineException</code> if it could not be found.
	 *
	 * @param lineId the primary key of the expense line
	 * @return the expense line
	 * @throws NoSuchExpenseLineException if a expense line with the primary key could not be found
	 */
	public ExpenseLine findByPrimaryKey(long lineId)
		throws NoSuchExpenseLineException;

	/**
	 * Returns the expense line with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param lineId the primary key of the expense line
	 * @return the expense line, or <code>null</code> if a expense line with the primary key could not be found
	 */
	public ExpenseLine fetchByPrimaryKey(long lineId);

	/**
	 * Returns all the expense lines.
	 *
	 * @return the expense lines
	 */
	public java.util.List<ExpenseLine> findAll();

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
	public java.util.List<ExpenseLine> findAll(int start, int end);

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
	public java.util.List<ExpenseLine> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ExpenseLine>
			orderByComparator);

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
	public java.util.List<ExpenseLine> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ExpenseLine>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the expense lines from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of expense lines.
	 *
	 * @return the number of expense lines
	 */
	public int countAll();

}