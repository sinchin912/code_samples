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

import com.trantorinc.synergy.notice.core.model.QuestionnaireForm;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the questionnaire form service. This utility wraps <code>com.trantorinc.synergy.notice.core.service.persistence.impl.QuestionnaireFormPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see QuestionnaireFormPersistence
 * @generated
 */
public class QuestionnaireFormUtil {

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
	public static void clearCache(QuestionnaireForm questionnaireForm) {
		getPersistence().clearCache(questionnaireForm);
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
	public static Map<Serializable, QuestionnaireForm> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<QuestionnaireForm> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<QuestionnaireForm> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<QuestionnaireForm> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<QuestionnaireForm> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static QuestionnaireForm update(
		QuestionnaireForm questionnaireForm) {

		return getPersistence().update(questionnaireForm);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static QuestionnaireForm update(
		QuestionnaireForm questionnaireForm, ServiceContext serviceContext) {

		return getPersistence().update(questionnaireForm, serviceContext);
	}

	/**
	 * Caches the questionnaire form in the entity cache if it is enabled.
	 *
	 * @param questionnaireForm the questionnaire form
	 */
	public static void cacheResult(QuestionnaireForm questionnaireForm) {
		getPersistence().cacheResult(questionnaireForm);
	}

	/**
	 * Caches the questionnaire forms in the entity cache if it is enabled.
	 *
	 * @param questionnaireForms the questionnaire forms
	 */
	public static void cacheResult(List<QuestionnaireForm> questionnaireForms) {
		getPersistence().cacheResult(questionnaireForms);
	}

	/**
	 * Creates a new questionnaire form with the primary key. Does not add the questionnaire form to the database.
	 *
	 * @param id the primary key for the new questionnaire form
	 * @return the new questionnaire form
	 */
	public static QuestionnaireForm create(long id) {
		return getPersistence().create(id);
	}

	/**
	 * Removes the questionnaire form with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the questionnaire form
	 * @return the questionnaire form that was removed
	 * @throws NoSuchQuestionnaireFormException if a questionnaire form with the primary key could not be found
	 */
	public static QuestionnaireForm remove(long id)
		throws com.trantorinc.synergy.notice.core.exception.
			NoSuchQuestionnaireFormException {

		return getPersistence().remove(id);
	}

	public static QuestionnaireForm updateImpl(
		QuestionnaireForm questionnaireForm) {

		return getPersistence().updateImpl(questionnaireForm);
	}

	/**
	 * Returns the questionnaire form with the primary key or throws a <code>NoSuchQuestionnaireFormException</code> if it could not be found.
	 *
	 * @param id the primary key of the questionnaire form
	 * @return the questionnaire form
	 * @throws NoSuchQuestionnaireFormException if a questionnaire form with the primary key could not be found
	 */
	public static QuestionnaireForm findByPrimaryKey(long id)
		throws com.trantorinc.synergy.notice.core.exception.
			NoSuchQuestionnaireFormException {

		return getPersistence().findByPrimaryKey(id);
	}

	/**
	 * Returns the questionnaire form with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the questionnaire form
	 * @return the questionnaire form, or <code>null</code> if a questionnaire form with the primary key could not be found
	 */
	public static QuestionnaireForm fetchByPrimaryKey(long id) {
		return getPersistence().fetchByPrimaryKey(id);
	}

	/**
	 * Returns all the questionnaire forms.
	 *
	 * @return the questionnaire forms
	 */
	public static List<QuestionnaireForm> findAll() {
		return getPersistence().findAll();
	}

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
	public static List<QuestionnaireForm> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

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
	public static List<QuestionnaireForm> findAll(
		int start, int end,
		OrderByComparator<QuestionnaireForm> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

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
	public static List<QuestionnaireForm> findAll(
		int start, int end,
		OrderByComparator<QuestionnaireForm> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the questionnaire forms from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of questionnaire forms.
	 *
	 * @return the number of questionnaire forms
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static QuestionnaireFormPersistence getPersistence() {
		return _persistence;
	}

	private static volatile QuestionnaireFormPersistence _persistence;

}