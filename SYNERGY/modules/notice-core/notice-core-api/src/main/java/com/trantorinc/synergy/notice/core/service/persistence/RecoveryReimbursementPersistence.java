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

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import com.trantorinc.synergy.notice.core.exception.NoSuchRecoveryReimbursementException;
import com.trantorinc.synergy.notice.core.model.RecoveryReimbursement;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the recovery reimbursement service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see RecoveryReimbursementUtil
 * @generated
 */
@ProviderType
public interface RecoveryReimbursementPersistence
	extends BasePersistence<RecoveryReimbursement> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link RecoveryReimbursementUtil} to access the recovery reimbursement persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Caches the recovery reimbursement in the entity cache if it is enabled.
	 *
	 * @param recoveryReimbursement the recovery reimbursement
	 */
	public void cacheResult(RecoveryReimbursement recoveryReimbursement);

	/**
	 * Caches the recovery reimbursements in the entity cache if it is enabled.
	 *
	 * @param recoveryReimbursements the recovery reimbursements
	 */
	public void cacheResult(
		java.util.List<RecoveryReimbursement> recoveryReimbursements);

	/**
	 * Creates a new recovery reimbursement with the primary key. Does not add the recovery reimbursement to the database.
	 *
	 * @param id the primary key for the new recovery reimbursement
	 * @return the new recovery reimbursement
	 */
	public RecoveryReimbursement create(long id);

	/**
	 * Removes the recovery reimbursement with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the recovery reimbursement
	 * @return the recovery reimbursement that was removed
	 * @throws NoSuchRecoveryReimbursementException if a recovery reimbursement with the primary key could not be found
	 */
	public RecoveryReimbursement remove(long id)
		throws NoSuchRecoveryReimbursementException;

	public RecoveryReimbursement updateImpl(
		RecoveryReimbursement recoveryReimbursement);

	/**
	 * Returns the recovery reimbursement with the primary key or throws a <code>NoSuchRecoveryReimbursementException</code> if it could not be found.
	 *
	 * @param id the primary key of the recovery reimbursement
	 * @return the recovery reimbursement
	 * @throws NoSuchRecoveryReimbursementException if a recovery reimbursement with the primary key could not be found
	 */
	public RecoveryReimbursement findByPrimaryKey(long id)
		throws NoSuchRecoveryReimbursementException;

	/**
	 * Returns the recovery reimbursement with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the recovery reimbursement
	 * @return the recovery reimbursement, or <code>null</code> if a recovery reimbursement with the primary key could not be found
	 */
	public RecoveryReimbursement fetchByPrimaryKey(long id);

	/**
	 * Returns all the recovery reimbursements.
	 *
	 * @return the recovery reimbursements
	 */
	public java.util.List<RecoveryReimbursement> findAll();

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
	public java.util.List<RecoveryReimbursement> findAll(int start, int end);

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
	public java.util.List<RecoveryReimbursement> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<RecoveryReimbursement>
			orderByComparator);

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
	public java.util.List<RecoveryReimbursement> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<RecoveryReimbursement>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the recovery reimbursements from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of recovery reimbursements.
	 *
	 * @return the number of recovery reimbursements
	 */
	public int countAll();

}