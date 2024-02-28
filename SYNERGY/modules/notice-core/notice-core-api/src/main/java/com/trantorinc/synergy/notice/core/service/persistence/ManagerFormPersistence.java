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

import com.trantorinc.synergy.notice.core.exception.NoSuchManagerFormException;
import com.trantorinc.synergy.notice.core.model.ManagerForm;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the manager form service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ManagerFormUtil
 * @generated
 */
@ProviderType
public interface ManagerFormPersistence extends BasePersistence<ManagerForm> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ManagerFormUtil} to access the manager form persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Caches the manager form in the entity cache if it is enabled.
	 *
	 * @param managerForm the manager form
	 */
	public void cacheResult(ManagerForm managerForm);

	/**
	 * Caches the manager forms in the entity cache if it is enabled.
	 *
	 * @param managerForms the manager forms
	 */
	public void cacheResult(java.util.List<ManagerForm> managerForms);

	/**
	 * Creates a new manager form with the primary key. Does not add the manager form to the database.
	 *
	 * @param id the primary key for the new manager form
	 * @return the new manager form
	 */
	public ManagerForm create(long id);

	/**
	 * Removes the manager form with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the manager form
	 * @return the manager form that was removed
	 * @throws NoSuchManagerFormException if a manager form with the primary key could not be found
	 */
	public ManagerForm remove(long id) throws NoSuchManagerFormException;

	public ManagerForm updateImpl(ManagerForm managerForm);

	/**
	 * Returns the manager form with the primary key or throws a <code>NoSuchManagerFormException</code> if it could not be found.
	 *
	 * @param id the primary key of the manager form
	 * @return the manager form
	 * @throws NoSuchManagerFormException if a manager form with the primary key could not be found
	 */
	public ManagerForm findByPrimaryKey(long id)
		throws NoSuchManagerFormException;

	/**
	 * Returns the manager form with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the manager form
	 * @return the manager form, or <code>null</code> if a manager form with the primary key could not be found
	 */
	public ManagerForm fetchByPrimaryKey(long id);

	/**
	 * Returns all the manager forms.
	 *
	 * @return the manager forms
	 */
	public java.util.List<ManagerForm> findAll();

	/**
	 * Returns a range of all the manager forms.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ManagerFormModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of manager forms
	 * @param end the upper bound of the range of manager forms (not inclusive)
	 * @return the range of manager forms
	 */
	public java.util.List<ManagerForm> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the manager forms.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ManagerFormModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of manager forms
	 * @param end the upper bound of the range of manager forms (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of manager forms
	 */
	public java.util.List<ManagerForm> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ManagerForm>
			orderByComparator);

	/**
	 * Returns an ordered range of all the manager forms.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ManagerFormModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of manager forms
	 * @param end the upper bound of the range of manager forms (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of manager forms
	 */
	public java.util.List<ManagerForm> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ManagerForm>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the manager forms from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of manager forms.
	 *
	 * @return the number of manager forms
	 */
	public int countAll();

}