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

import com.trantorinc.synergy.notice.core.exception.NoSuchItFormException;
import com.trantorinc.synergy.notice.core.model.ItForm;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the it form service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ItFormUtil
 * @generated
 */
@ProviderType
public interface ItFormPersistence extends BasePersistence<ItForm> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ItFormUtil} to access the it form persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Caches the it form in the entity cache if it is enabled.
	 *
	 * @param itForm the it form
	 */
	public void cacheResult(ItForm itForm);

	/**
	 * Caches the it forms in the entity cache if it is enabled.
	 *
	 * @param itForms the it forms
	 */
	public void cacheResult(java.util.List<ItForm> itForms);

	/**
	 * Creates a new it form with the primary key. Does not add the it form to the database.
	 *
	 * @param id the primary key for the new it form
	 * @return the new it form
	 */
	public ItForm create(long id);

	/**
	 * Removes the it form with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the it form
	 * @return the it form that was removed
	 * @throws NoSuchItFormException if a it form with the primary key could not be found
	 */
	public ItForm remove(long id) throws NoSuchItFormException;

	public ItForm updateImpl(ItForm itForm);

	/**
	 * Returns the it form with the primary key or throws a <code>NoSuchItFormException</code> if it could not be found.
	 *
	 * @param id the primary key of the it form
	 * @return the it form
	 * @throws NoSuchItFormException if a it form with the primary key could not be found
	 */
	public ItForm findByPrimaryKey(long id) throws NoSuchItFormException;

	/**
	 * Returns the it form with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the it form
	 * @return the it form, or <code>null</code> if a it form with the primary key could not be found
	 */
	public ItForm fetchByPrimaryKey(long id);

	/**
	 * Returns all the it forms.
	 *
	 * @return the it forms
	 */
	public java.util.List<ItForm> findAll();

	/**
	 * Returns a range of all the it forms.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ItFormModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of it forms
	 * @param end the upper bound of the range of it forms (not inclusive)
	 * @return the range of it forms
	 */
	public java.util.List<ItForm> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the it forms.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ItFormModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of it forms
	 * @param end the upper bound of the range of it forms (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of it forms
	 */
	public java.util.List<ItForm> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ItForm>
			orderByComparator);

	/**
	 * Returns an ordered range of all the it forms.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ItFormModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of it forms
	 * @param end the upper bound of the range of it forms (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of it forms
	 */
	public java.util.List<ItForm> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ItForm>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the it forms from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of it forms.
	 *
	 * @return the number of it forms
	 */
	public int countAll();

}