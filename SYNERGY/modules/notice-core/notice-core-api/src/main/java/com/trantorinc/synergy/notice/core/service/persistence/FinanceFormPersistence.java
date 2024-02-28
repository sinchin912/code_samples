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

import com.trantorinc.synergy.notice.core.exception.NoSuchFinanceFormException;
import com.trantorinc.synergy.notice.core.model.FinanceForm;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the finance form service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see FinanceFormUtil
 * @generated
 */
@ProviderType
public interface FinanceFormPersistence extends BasePersistence<FinanceForm> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link FinanceFormUtil} to access the finance form persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Caches the finance form in the entity cache if it is enabled.
	 *
	 * @param financeForm the finance form
	 */
	public void cacheResult(FinanceForm financeForm);

	/**
	 * Caches the finance forms in the entity cache if it is enabled.
	 *
	 * @param financeForms the finance forms
	 */
	public void cacheResult(java.util.List<FinanceForm> financeForms);

	/**
	 * Creates a new finance form with the primary key. Does not add the finance form to the database.
	 *
	 * @param id the primary key for the new finance form
	 * @return the new finance form
	 */
	public FinanceForm create(long id);

	/**
	 * Removes the finance form with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the finance form
	 * @return the finance form that was removed
	 * @throws NoSuchFinanceFormException if a finance form with the primary key could not be found
	 */
	public FinanceForm remove(long id) throws NoSuchFinanceFormException;

	public FinanceForm updateImpl(FinanceForm financeForm);

	/**
	 * Returns the finance form with the primary key or throws a <code>NoSuchFinanceFormException</code> if it could not be found.
	 *
	 * @param id the primary key of the finance form
	 * @return the finance form
	 * @throws NoSuchFinanceFormException if a finance form with the primary key could not be found
	 */
	public FinanceForm findByPrimaryKey(long id)
		throws NoSuchFinanceFormException;

	/**
	 * Returns the finance form with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the finance form
	 * @return the finance form, or <code>null</code> if a finance form with the primary key could not be found
	 */
	public FinanceForm fetchByPrimaryKey(long id);

	/**
	 * Returns all the finance forms.
	 *
	 * @return the finance forms
	 */
	public java.util.List<FinanceForm> findAll();

	/**
	 * Returns a range of all the finance forms.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FinanceFormModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of finance forms
	 * @param end the upper bound of the range of finance forms (not inclusive)
	 * @return the range of finance forms
	 */
	public java.util.List<FinanceForm> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the finance forms.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FinanceFormModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of finance forms
	 * @param end the upper bound of the range of finance forms (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of finance forms
	 */
	public java.util.List<FinanceForm> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<FinanceForm>
			orderByComparator);

	/**
	 * Returns an ordered range of all the finance forms.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FinanceFormModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of finance forms
	 * @param end the upper bound of the range of finance forms (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of finance forms
	 */
	public java.util.List<FinanceForm> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<FinanceForm>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the finance forms from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of finance forms.
	 *
	 * @return the number of finance forms
	 */
	public int countAll();

}