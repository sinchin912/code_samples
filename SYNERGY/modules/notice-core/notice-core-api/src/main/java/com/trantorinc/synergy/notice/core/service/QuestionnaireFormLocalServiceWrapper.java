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

package com.trantorinc.synergy.notice.core.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link QuestionnaireFormLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see QuestionnaireFormLocalService
 * @generated
 */
public class QuestionnaireFormLocalServiceWrapper
	implements QuestionnaireFormLocalService,
			   ServiceWrapper<QuestionnaireFormLocalService> {

	public QuestionnaireFormLocalServiceWrapper() {
		this(null);
	}

	public QuestionnaireFormLocalServiceWrapper(
		QuestionnaireFormLocalService questionnaireFormLocalService) {

		_questionnaireFormLocalService = questionnaireFormLocalService;
	}

	/**
	 * Adds the questionnaire form to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect QuestionnaireFormLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param questionnaireForm the questionnaire form
	 * @return the questionnaire form that was added
	 */
	@Override
	public com.trantorinc.synergy.notice.core.model.QuestionnaireForm
		addQuestionnaireForm(
			com.trantorinc.synergy.notice.core.model.QuestionnaireForm
				questionnaireForm) {

		return _questionnaireFormLocalService.addQuestionnaireForm(
			questionnaireForm);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _questionnaireFormLocalService.createPersistedModel(
			primaryKeyObj);
	}

	/**
	 * Creates a new questionnaire form with the primary key. Does not add the questionnaire form to the database.
	 *
	 * @param id the primary key for the new questionnaire form
	 * @return the new questionnaire form
	 */
	@Override
	public com.trantorinc.synergy.notice.core.model.QuestionnaireForm
		createQuestionnaireForm(long id) {

		return _questionnaireFormLocalService.createQuestionnaireForm(id);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _questionnaireFormLocalService.deletePersistedModel(
			persistedModel);
	}

	/**
	 * Deletes the questionnaire form with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect QuestionnaireFormLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param id the primary key of the questionnaire form
	 * @return the questionnaire form that was removed
	 * @throws PortalException if a questionnaire form with the primary key could not be found
	 */
	@Override
	public com.trantorinc.synergy.notice.core.model.QuestionnaireForm
			deleteQuestionnaireForm(long id)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _questionnaireFormLocalService.deleteQuestionnaireForm(id);
	}

	/**
	 * Deletes the questionnaire form from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect QuestionnaireFormLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param questionnaireForm the questionnaire form
	 * @return the questionnaire form that was removed
	 */
	@Override
	public com.trantorinc.synergy.notice.core.model.QuestionnaireForm
		deleteQuestionnaireForm(
			com.trantorinc.synergy.notice.core.model.QuestionnaireForm
				questionnaireForm) {

		return _questionnaireFormLocalService.deleteQuestionnaireForm(
			questionnaireForm);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _questionnaireFormLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _questionnaireFormLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _questionnaireFormLocalService.dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _questionnaireFormLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.trantorinc.synergy.notice.core.model.impl.QuestionnaireFormModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return _questionnaireFormLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.trantorinc.synergy.notice.core.model.impl.QuestionnaireFormModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return _questionnaireFormLocalService.dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _questionnaireFormLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return _questionnaireFormLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.trantorinc.synergy.notice.core.model.QuestionnaireForm
		fetchQuestionnaireForm(long id) {

		return _questionnaireFormLocalService.fetchQuestionnaireForm(id);
	}

	@Override
	public java.util.List
		<com.trantorinc.synergy.notice.core.model.QuestionnaireForm>
			findAllQuestionnaireFormEntries() {

		return _questionnaireFormLocalService.findAllQuestionnaireFormEntries();
	}

	@Override
	public java.util.List
		<com.trantorinc.synergy.notice.core.model.QuestionnaireForm>
			findQuestionnaireFormByResignationId(long resignationId) {

		return _questionnaireFormLocalService.
			findQuestionnaireFormByResignationId(resignationId);
	}

	@Override
	public java.util.List
		<com.trantorinc.synergy.notice.core.model.QuestionnaireForm>
			findYearlyEntries(
				java.util.Date startDate, java.util.Date endDate) {

		return _questionnaireFormLocalService.findYearlyEntries(
			startDate, endDate);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _questionnaireFormLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _questionnaireFormLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _questionnaireFormLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _questionnaireFormLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns the questionnaire form with the primary key.
	 *
	 * @param id the primary key of the questionnaire form
	 * @return the questionnaire form
	 * @throws PortalException if a questionnaire form with the primary key could not be found
	 */
	@Override
	public com.trantorinc.synergy.notice.core.model.QuestionnaireForm
			getQuestionnaireForm(long id)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _questionnaireFormLocalService.getQuestionnaireForm(id);
	}

	/**
	 * Returns a range of all the questionnaire forms.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.trantorinc.synergy.notice.core.model.impl.QuestionnaireFormModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of questionnaire forms
	 * @param end the upper bound of the range of questionnaire forms (not inclusive)
	 * @return the range of questionnaire forms
	 */
	@Override
	public java.util.List
		<com.trantorinc.synergy.notice.core.model.QuestionnaireForm>
			getQuestionnaireForms(int start, int end) {

		return _questionnaireFormLocalService.getQuestionnaireForms(start, end);
	}

	/**
	 * Returns the number of questionnaire forms.
	 *
	 * @return the number of questionnaire forms
	 */
	@Override
	public int getQuestionnaireFormsCount() {
		return _questionnaireFormLocalService.getQuestionnaireFormsCount();
	}

	/**
	 * Updates the questionnaire form in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect QuestionnaireFormLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param questionnaireForm the questionnaire form
	 * @return the questionnaire form that was updated
	 */
	@Override
	public com.trantorinc.synergy.notice.core.model.QuestionnaireForm
		updateQuestionnaireForm(
			com.trantorinc.synergy.notice.core.model.QuestionnaireForm
				questionnaireForm) {

		return _questionnaireFormLocalService.updateQuestionnaireForm(
			questionnaireForm);
	}

	@Override
	public QuestionnaireFormLocalService getWrappedService() {
		return _questionnaireFormLocalService;
	}

	@Override
	public void setWrappedService(
		QuestionnaireFormLocalService questionnaireFormLocalService) {

		_questionnaireFormLocalService = questionnaireFormLocalService;
	}

	private QuestionnaireFormLocalService _questionnaireFormLocalService;

}