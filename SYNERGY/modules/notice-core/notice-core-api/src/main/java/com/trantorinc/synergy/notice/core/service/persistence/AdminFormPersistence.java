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

import com.trantorinc.synergy.notice.core.exception.NoSuchAdminFormException;
import com.trantorinc.synergy.notice.core.model.AdminForm;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the admin form service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AdminFormUtil
 * @generated
 */
@ProviderType
public interface AdminFormPersistence extends BasePersistence<AdminForm> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link AdminFormUtil} to access the admin form persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Caches the admin form in the entity cache if it is enabled.
	 *
	 * @param adminForm the admin form
	 */
	public void cacheResult(AdminForm adminForm);

	/**
	 * Caches the admin forms in the entity cache if it is enabled.
	 *
	 * @param adminForms the admin forms
	 */
	public void cacheResult(java.util.List<AdminForm> adminForms);

	/**
	 * Creates a new admin form with the primary key. Does not add the admin form to the database.
	 *
	 * @param id the primary key for the new admin form
	 * @return the new admin form
	 */
	public AdminForm create(long id);

	/**
	 * Removes the admin form with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the admin form
	 * @return the admin form that was removed
	 * @throws NoSuchAdminFormException if a admin form with the primary key could not be found
	 */
	public AdminForm remove(long id) throws NoSuchAdminFormException;

	public AdminForm updateImpl(AdminForm adminForm);

	/**
	 * Returns the admin form with the primary key or throws a <code>NoSuchAdminFormException</code> if it could not be found.
	 *
	 * @param id the primary key of the admin form
	 * @return the admin form
	 * @throws NoSuchAdminFormException if a admin form with the primary key could not be found
	 */
	public AdminForm findByPrimaryKey(long id) throws NoSuchAdminFormException;

	/**
	 * Returns the admin form with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the admin form
	 * @return the admin form, or <code>null</code> if a admin form with the primary key could not be found
	 */
	public AdminForm fetchByPrimaryKey(long id);

	/**
	 * Returns all the admin forms.
	 *
	 * @return the admin forms
	 */
	public java.util.List<AdminForm> findAll();

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
	public java.util.List<AdminForm> findAll(int start, int end);

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
	public java.util.List<AdminForm> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AdminForm>
			orderByComparator);

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
	public java.util.List<AdminForm> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AdminForm>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the admin forms from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of admin forms.
	 *
	 * @return the number of admin forms
	 */
	public int countAll();

}