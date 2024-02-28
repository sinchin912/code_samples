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

import com.trantorinc.synergy.notice.core.exception.NoSuchQuestionnaireFormException;
import com.trantorinc.synergy.notice.core.model.QuestionnaireForm;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the questionnaire form service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see QuestionnaireFormUtil
 * @generated
 */
@ProviderType
public interface QuestionnaireFormPersistence
	extends BasePersistence<QuestionnaireForm> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link QuestionnaireFormUtil} to access the questionnaire form persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Caches the questionnaire form in the entity cache if it is enabled.
	 *
	 * @param questionnaireForm the questionnaire form
	 */
	public void cacheResult(QuestionnaireForm questionnaireForm);

	/**
	 * Caches the questionnaire forms in the entity cache if it is enabled.
	 *
	 * @param questionnaireForms the questionnaire forms
	 */
	public void cacheResult(
		java.util.List<QuestionnaireForm> questionnaireForms);

	/**
	 * Creates a new questionnaire form with the primary key. Does not add the questionnaire form to the database.
	 *
	 * @param id the primary key for the new questionnaire form
	 * @return the new questionnaire form
	 */
	public QuestionnaireForm create(long id);

	/**
	 * Removes the questionnaire form with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the questionnaire form
	 * @return the questionnaire form that was removed
	 * @throws NoSuchQuestionnaireFormException if a questionnaire form with the primary key could not be found
	 */
	public QuestionnaireForm remove(long id)
		throws NoSuchQuestionnaireFormException;

	public QuestionnaireForm updateImpl(QuestionnaireForm questionnaireForm);

	/**
	 * Returns the questionnaire form with the primary key or throws a <code>NoSuchQuestionnaireFormException</code> if it could not be found.
	 *
	 * @param id the primary key of the questionnaire form
	 * @return the questionnaire form
	 * @throws NoSuchQuestionnaireFormException if a questionnaire form with the primary key could not be found
	 */
	public QuestionnaireForm findByPrimaryKey(long id)
		throws NoSuchQuestionnaireFormException;

	/**
	 * Returns the questionnaire form with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the questionnaire form
	 * @return the questionnaire form, or <code>null</code> if a questionnaire form with the primary key could not be found
	 */
	public QuestionnaireForm fetchByPrimaryKey(long id);

	/**
	 * Returns all the questionnaire forms.
	 *
	 * @return the questionnaire forms
	 */
	public java.util.List<QuestionnaireForm> findAll();

	/**
	 * Returns a range of all the questionnaire forms.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>QuestionnaireFormModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of questionnaire forms
	 * @param end the upper bound of the range of questionnaire forms (not inclusive)
	 * @return the range of questionnaire forms
	 */
	public java.util.List<QuestionnaireForm> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the questionnaire forms.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>QuestionnaireFormModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of questionnaire forms
	 * @param end the upper bound of the range of questionnaire forms (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of questionnaire forms
	 */
	public java.util.List<QuestionnaireForm> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<QuestionnaireForm>
			orderByComparator);

	/**
	 * Returns an ordered range of all the questionnaire forms.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>QuestionnaireFormModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of questionnaire forms
	 * @param end the upper bound of the range of questionnaire forms (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of questionnaire forms
	 */
	public java.util.List<QuestionnaireForm> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<QuestionnaireForm>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the questionnaire forms from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of questionnaire forms.
	 *
	 * @return the number of questionnaire forms
	 */
	public int countAll();

}