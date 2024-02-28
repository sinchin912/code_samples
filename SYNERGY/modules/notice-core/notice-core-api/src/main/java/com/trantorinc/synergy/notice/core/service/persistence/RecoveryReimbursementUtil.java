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

package com.trantorinc.synergy.notice.core.service.persistence;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.trantorinc.synergy.notice.core.model.RecoveryReimbursement;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the recovery reimbursement service. This utility wraps <code>com.trantorinc.synergy.notice.core.service.persistence.impl.RecoveryReimbursementPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see RecoveryReimbursementPersistence
 * @generated
 */
public class RecoveryReimbursementUtil {

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
	public static void clearCache(RecoveryReimbursement recoveryReimbursement) {
		getPersistence().clearCache(recoveryReimbursement);
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
	public static Map<Serializable, RecoveryReimbursement> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<RecoveryReimbursement> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<RecoveryReimbursement> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<RecoveryReimbursement> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<RecoveryReimbursement> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static RecoveryReimbursement update(
		RecoveryReimbursement recoveryReimbursement) {

		return getPersistence().update(recoveryReimbursement);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static RecoveryReimbursement update(
		RecoveryReimbursement recoveryReimbursement,
		ServiceContext serviceContext) {

		return getPersistence().update(recoveryReimbursement, serviceContext);
	}

	/**
	 * Caches the recovery reimbursement in the entity cache if it is enabled.
	 *
	 * @param recoveryReimbursement the recovery reimbursement
	 */
	public static void cacheResult(
		RecoveryReimbursement recoveryReimbursement) {

		getPersistence().cacheResult(recoveryReimbursement);
	}

	/**
	 * Caches the recovery reimbursements in the entity cache if it is enabled.
	 *
	 * @param recoveryReimbursements the recovery reimbursements
	 */
	public static void cacheResult(
		List<RecoveryReimbursement> recoveryReimbursements) {

		getPersistence().cacheResult(recoveryReimbursements);
	}

	/**
	 * Creates a new recovery reimbursement with the primary key. Does not add the recovery reimbursement to the database.
	 *
	 * @param id the primary key for the new recovery reimbursement
	 * @return the new recovery reimbursement
	 */
	public static RecoveryReimbursement create(long id) {
		return getPersistence().create(id);
	}

	/**
	 * Removes the recovery reimbursement with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the recovery reimbursement
	 * @return the recovery reimbursement that was removed
	 * @throws NoSuchRecoveryReimbursementException if a recovery reimbursement with the primary key could not be found
	 */
	public static RecoveryReimbursement remove(long id)
		throws com.trantorinc.synergy.notice.core.exception.
			NoSuchRecoveryReimbursementException {

		return getPersistence().remove(id);
	}

	public static RecoveryReimbursement updateImpl(
		RecoveryReimbursement recoveryReimbursement) {

		return getPersistence().updateImpl(recoveryReimbursement);
	}

	/**
	 * Returns the recovery reimbursement with the primary key or throws a <code>NoSuchRecoveryReimbursementException</code> if it could not be found.
	 *
	 * @param id the primary key of the recovery reimbursement
	 * @return the recovery reimbursement
	 * @throws NoSuchRecoveryReimbursementException if a recovery reimbursement with the primary key could not be found
	 */
	public static RecoveryReimbursement findByPrimaryKey(long id)
		throws com.trantorinc.synergy.notice.core.exception.
			NoSuchRecoveryReimbursementException {

		return getPersistence().findByPrimaryKey(id);
	}

	/**
	 * Returns the recovery reimbursement with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the recovery reimbursement
	 * @return the recovery reimbursement, or <code>null</code> if a recovery reimbursement with the primary key could not be found
	 */
	public static RecoveryReimbursement fetchByPrimaryKey(long id) {
		return getPersistence().fetchByPrimaryKey(id);
	}

	/**
	 * Returns all the recovery reimbursements.
	 *
	 * @return the recovery reimbursements
	 */
	public static List<RecoveryReimbursement> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the recovery reimbursements.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RecoveryReimbursementModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of recovery reimbursements
	 * @param end the upper bound of the range of recovery reimbursements (not inclusive)
	 * @return the range of recovery reimbursements
	 */
	public static List<RecoveryReimbursement> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the recovery reimbursements.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RecoveryReimbursementModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of recovery reimbursements
	 * @param end the upper bound of the range of recovery reimbursements (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of recovery reimbursements
	 */
	public static List<RecoveryReimbursement> findAll(
		int start, int end,
		OrderByComparator<RecoveryReimbursement> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the recovery reimbursements.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RecoveryReimbursementModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of recovery reimbursements
	 * @param end the upper bound of the range of recovery reimbursements (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of recovery reimbursements
	 */
	public static List<RecoveryReimbursement> findAll(
		int start, int end,
		OrderByComparator<RecoveryReimbursement> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the recovery reimbursements from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of recovery reimbursements.
	 *
	 * @return the number of recovery reimbursements
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static RecoveryReimbursementPersistence getPersistence() {
		return _persistence;
	}

	private static volatile RecoveryReimbursementPersistence _persistence;

}