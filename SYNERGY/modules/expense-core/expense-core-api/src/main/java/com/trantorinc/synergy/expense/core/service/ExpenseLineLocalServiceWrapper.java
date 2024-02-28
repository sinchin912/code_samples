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

package com.trantorinc.synergy.expense.core.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ExpenseLineLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see ExpenseLineLocalService
 * @generated
 */
public class ExpenseLineLocalServiceWrapper
	implements ExpenseLineLocalService,
			   ServiceWrapper<ExpenseLineLocalService> {

	public ExpenseLineLocalServiceWrapper() {
		this(null);
	}

	public ExpenseLineLocalServiceWrapper(
		ExpenseLineLocalService expenseLineLocalService) {

		_expenseLineLocalService = expenseLineLocalService;
	}

	/**
	 * Adds the expense line to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ExpenseLineLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param expenseLine the expense line
	 * @return the expense line that was added
	 */
	@Override
	public com.trantorinc.synergy.expense.core.model.ExpenseLine addExpenseLine(
		com.trantorinc.synergy.expense.core.model.ExpenseLine expenseLine) {

		return _expenseLineLocalService.addExpenseLine(expenseLine);
	}

	/**
	 * Creates a new expense line with the primary key. Does not add the expense line to the database.
	 *
	 * @param lineId the primary key for the new expense line
	 * @return the new expense line
	 */
	@Override
	public com.trantorinc.synergy.expense.core.model.ExpenseLine
		createExpenseLine(long lineId) {

		return _expenseLineLocalService.createExpenseLine(lineId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _expenseLineLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Deletes the expense line from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ExpenseLineLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param expenseLine the expense line
	 * @return the expense line that was removed
	 */
	@Override
	public com.trantorinc.synergy.expense.core.model.ExpenseLine
		deleteExpenseLine(
			com.trantorinc.synergy.expense.core.model.ExpenseLine expenseLine) {

		return _expenseLineLocalService.deleteExpenseLine(expenseLine);
	}

	/**
	 * Deletes the expense line with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ExpenseLineLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param lineId the primary key of the expense line
	 * @return the expense line that was removed
	 * @throws PortalException if a expense line with the primary key could not be found
	 */
	@Override
	public com.trantorinc.synergy.expense.core.model.ExpenseLine
			deleteExpenseLine(long lineId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _expenseLineLocalService.deleteExpenseLine(lineId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _expenseLineLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _expenseLineLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _expenseLineLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _expenseLineLocalService.dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _expenseLineLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.trantorinc.synergy.expense.core.model.impl.ExpenseLineModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return _expenseLineLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.trantorinc.synergy.expense.core.model.impl.ExpenseLineModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return _expenseLineLocalService.dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _expenseLineLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return _expenseLineLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.trantorinc.synergy.expense.core.model.ExpenseLine
		fetchExpenseLine(long lineId) {

		return _expenseLineLocalService.fetchExpenseLine(lineId);
	}

	@Override
	public java.util.List<com.trantorinc.synergy.expense.core.model.ExpenseLine>
		findByExpenseId(long expenseId) {

		return _expenseLineLocalService.findByExpenseId(expenseId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _expenseLineLocalService.getActionableDynamicQuery();
	}

	/**
	 * Returns the expense line with the primary key.
	 *
	 * @param lineId the primary key of the expense line
	 * @return the expense line
	 * @throws PortalException if a expense line with the primary key could not be found
	 */
	@Override
	public com.trantorinc.synergy.expense.core.model.ExpenseLine getExpenseLine(
			long lineId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _expenseLineLocalService.getExpenseLine(lineId);
	}

	/**
	 * Returns a range of all the expense lines.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.trantorinc.synergy.expense.core.model.impl.ExpenseLineModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of expense lines
	 * @param end the upper bound of the range of expense lines (not inclusive)
	 * @return the range of expense lines
	 */
	@Override
	public java.util.List<com.trantorinc.synergy.expense.core.model.ExpenseLine>
		getExpenseLines(int start, int end) {

		return _expenseLineLocalService.getExpenseLines(start, end);
	}

	/**
	 * Returns the number of expense lines.
	 *
	 * @return the number of expense lines
	 */
	@Override
	public int getExpenseLinesCount() {
		return _expenseLineLocalService.getExpenseLinesCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _expenseLineLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _expenseLineLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _expenseLineLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the expense line in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ExpenseLineLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param expenseLine the expense line
	 * @return the expense line that was updated
	 */
	@Override
	public com.trantorinc.synergy.expense.core.model.ExpenseLine
		updateExpenseLine(
			com.trantorinc.synergy.expense.core.model.ExpenseLine expenseLine) {

		return _expenseLineLocalService.updateExpenseLine(expenseLine);
	}

	@Override
	public ExpenseLineLocalService getWrappedService() {
		return _expenseLineLocalService;
	}

	@Override
	public void setWrappedService(
		ExpenseLineLocalService expenseLineLocalService) {

		_expenseLineLocalService = expenseLineLocalService;
	}

	private ExpenseLineLocalService _expenseLineLocalService;

}