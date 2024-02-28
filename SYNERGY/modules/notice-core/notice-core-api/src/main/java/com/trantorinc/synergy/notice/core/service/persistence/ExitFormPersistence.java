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

import com.trantorinc.synergy.notice.core.exception.NoSuchExitFormException;
import com.trantorinc.synergy.notice.core.model.ExitForm;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the exit form service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ExitFormUtil
 * @generated
 */
@ProviderType
public interface ExitFormPersistence extends BasePersistence<ExitForm> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ExitFormUtil} to access the exit form persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Caches the exit form in the entity cache if it is enabled.
	 *
	 * @param exitForm the exit form
	 */
	public void cacheResult(ExitForm exitForm);

	/**
	 * Caches the exit forms in the entity cache if it is enabled.
	 *
	 * @param exitForms the exit forms
	 */
	public void cacheResult(java.util.List<ExitForm> exitForms);

	/**
	 * Creates a new exit form with the primary key. Does not add the exit form to the database.
	 *
	 * @param id the primary key for the new exit form
	 * @return the new exit form
	 */
	public ExitForm create(long id);

	/**
	 * Removes the exit form with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the exit form
	 * @return the exit form that was removed
	 * @throws NoSuchExitFormException if a exit form with the primary key could not be found
	 */
	public ExitForm remove(long id) throws NoSuchExitFormException;

	public ExitForm updateImpl(ExitForm exitForm);

	/**
	 * Returns the exit form with the primary key or throws a <code>NoSuchExitFormException</code> if it could not be found.
	 *
	 * @param id the primary key of the exit form
	 * @return the exit form
	 * @throws NoSuchExitFormException if a exit form with the primary key could not be found
	 */
	public ExitForm findByPrimaryKey(long id) throws NoSuchExitFormException;

	/**
	 * Returns the exit form with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the exit form
	 * @return the exit form, or <code>null</code> if a exit form with the primary key could not be found
	 */
	public ExitForm fetchByPrimaryKey(long id);

	/**
	 * Returns all the exit forms.
	 *
	 * @return the exit forms
	 */
	public java.util.List<ExitForm> findAll();

	/**
	 * Returns a range of all the exit forms.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ExitFormModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of exit forms
	 * @param end the upper bound of the range of exit forms (not inclusive)
	 * @return the range of exit forms
	 */
	public java.util.List<ExitForm> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the exit forms.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ExitFormModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of exit forms
	 * @param end the upper bound of the range of exit forms (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of exit forms
	 */
	public java.util.List<ExitForm> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ExitForm>
			orderByComparator);

	/**
	 * Returns an ordered range of all the exit forms.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ExitFormModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of exit forms
	 * @param end the upper bound of the range of exit forms (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of exit forms
	 */
	public java.util.List<ExitForm> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ExitForm>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the exit forms from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of exit forms.
	 *
	 * @return the number of exit forms
	 */
	public int countAll();

}