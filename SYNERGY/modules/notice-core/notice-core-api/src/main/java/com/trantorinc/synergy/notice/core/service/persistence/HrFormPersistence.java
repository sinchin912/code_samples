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

import com.trantorinc.synergy.notice.core.exception.NoSuchHrFormException;
import com.trantorinc.synergy.notice.core.model.HrForm;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the hr form service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see HrFormUtil
 * @generated
 */
@ProviderType
public interface HrFormPersistence extends BasePersistence<HrForm> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link HrFormUtil} to access the hr form persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Caches the hr form in the entity cache if it is enabled.
	 *
	 * @param hrForm the hr form
	 */
	public void cacheResult(HrForm hrForm);

	/**
	 * Caches the hr forms in the entity cache if it is enabled.
	 *
	 * @param hrForms the hr forms
	 */
	public void cacheResult(java.util.List<HrForm> hrForms);

	/**
	 * Creates a new hr form with the primary key. Does not add the hr form to the database.
	 *
	 * @param id the primary key for the new hr form
	 * @return the new hr form
	 */
	public HrForm create(long id);

	/**
	 * Removes the hr form with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the hr form
	 * @return the hr form that was removed
	 * @throws NoSuchHrFormException if a hr form with the primary key could not be found
	 */
	public HrForm remove(long id) throws NoSuchHrFormException;

	public HrForm updateImpl(HrForm hrForm);

	/**
	 * Returns the hr form with the primary key or throws a <code>NoSuchHrFormException</code> if it could not be found.
	 *
	 * @param id the primary key of the hr form
	 * @return the hr form
	 * @throws NoSuchHrFormException if a hr form with the primary key could not be found
	 */
	public HrForm findByPrimaryKey(long id) throws NoSuchHrFormException;

	/**
	 * Returns the hr form with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the hr form
	 * @return the hr form, or <code>null</code> if a hr form with the primary key could not be found
	 */
	public HrForm fetchByPrimaryKey(long id);

	/**
	 * Returns all the hr forms.
	 *
	 * @return the hr forms
	 */
	public java.util.List<HrForm> findAll();

	/**
	 * Returns a range of all the hr forms.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HrFormModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of hr forms
	 * @param end the upper bound of the range of hr forms (not inclusive)
	 * @return the range of hr forms
	 */
	public java.util.List<HrForm> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the hr forms.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HrFormModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of hr forms
	 * @param end the upper bound of the range of hr forms (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of hr forms
	 */
	public java.util.List<HrForm> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<HrForm>
			orderByComparator);

	/**
	 * Returns an ordered range of all the hr forms.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HrFormModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of hr forms
	 * @param end the upper bound of the range of hr forms (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of hr forms
	 */
	public java.util.List<HrForm> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<HrForm>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the hr forms from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of hr forms.
	 *
	 * @return the number of hr forms
	 */
	public int countAll();

}