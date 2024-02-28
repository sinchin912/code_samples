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

package com.trantorinc.synergy.skill.core.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link SkillGuideLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see SkillGuideLocalService
 * @generated
 */
public class SkillGuideLocalServiceWrapper
	implements ServiceWrapper<SkillGuideLocalService>, SkillGuideLocalService {

	public SkillGuideLocalServiceWrapper() {
		this(null);
	}

	public SkillGuideLocalServiceWrapper(
		SkillGuideLocalService skillGuideLocalService) {

		_skillGuideLocalService = skillGuideLocalService;
	}

	/**
	 * Adds the skill guide to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect SkillGuideLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param skillGuide the skill guide
	 * @return the skill guide that was added
	 */
	@Override
	public com.trantorinc.synergy.skill.core.model.SkillGuide addSkillGuide(
		com.trantorinc.synergy.skill.core.model.SkillGuide skillGuide) {

		return _skillGuideLocalService.addSkillGuide(skillGuide);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _skillGuideLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Creates a new skill guide with the primary key. Does not add the skill guide to the database.
	 *
	 * @param guideId the primary key for the new skill guide
	 * @return the new skill guide
	 */
	@Override
	public com.trantorinc.synergy.skill.core.model.SkillGuide createSkillGuide(
		long guideId) {

		return _skillGuideLocalService.createSkillGuide(guideId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _skillGuideLocalService.deletePersistedModel(persistedModel);
	}

	/**
	 * Deletes the skill guide with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect SkillGuideLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param guideId the primary key of the skill guide
	 * @return the skill guide that was removed
	 * @throws PortalException if a skill guide with the primary key could not be found
	 */
	@Override
	public com.trantorinc.synergy.skill.core.model.SkillGuide deleteSkillGuide(
			long guideId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _skillGuideLocalService.deleteSkillGuide(guideId);
	}

	/**
	 * Deletes the skill guide from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect SkillGuideLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param skillGuide the skill guide
	 * @return the skill guide that was removed
	 */
	@Override
	public com.trantorinc.synergy.skill.core.model.SkillGuide deleteSkillGuide(
		com.trantorinc.synergy.skill.core.model.SkillGuide skillGuide) {

		return _skillGuideLocalService.deleteSkillGuide(skillGuide);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _skillGuideLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _skillGuideLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _skillGuideLocalService.dynamicQuery();
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

		return _skillGuideLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.trantorinc.synergy.skill.core.model.impl.SkillGuideModelImpl</code>.
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

		return _skillGuideLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.trantorinc.synergy.skill.core.model.impl.SkillGuideModelImpl</code>.
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

		return _skillGuideLocalService.dynamicQuery(
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

		return _skillGuideLocalService.dynamicQueryCount(dynamicQuery);
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

		return _skillGuideLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.trantorinc.synergy.skill.core.model.SkillGuide fetchSkillGuide(
		long guideId) {

		return _skillGuideLocalService.fetchSkillGuide(guideId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _skillGuideLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _skillGuideLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _skillGuideLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _skillGuideLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns the skill guide with the primary key.
	 *
	 * @param guideId the primary key of the skill guide
	 * @return the skill guide
	 * @throws PortalException if a skill guide with the primary key could not be found
	 */
	@Override
	public com.trantorinc.synergy.skill.core.model.SkillGuide getSkillGuide(
			long guideId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _skillGuideLocalService.getSkillGuide(guideId);
	}

	/**
	 * Returns a range of all the skill guides.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.trantorinc.synergy.skill.core.model.impl.SkillGuideModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of skill guides
	 * @param end the upper bound of the range of skill guides (not inclusive)
	 * @return the range of skill guides
	 */
	@Override
	public java.util.List<com.trantorinc.synergy.skill.core.model.SkillGuide>
		getSkillGuides(int start, int end) {

		return _skillGuideLocalService.getSkillGuides(start, end);
	}

	/**
	 * Returns the number of skill guides.
	 *
	 * @return the number of skill guides
	 */
	@Override
	public int getSkillGuidesCount() {
		return _skillGuideLocalService.getSkillGuidesCount();
	}

	/**
	 * Updates the skill guide in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect SkillGuideLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param skillGuide the skill guide
	 * @return the skill guide that was updated
	 */
	@Override
	public com.trantorinc.synergy.skill.core.model.SkillGuide updateSkillGuide(
		com.trantorinc.synergy.skill.core.model.SkillGuide skillGuide) {

		return _skillGuideLocalService.updateSkillGuide(skillGuide);
	}

	@Override
	public SkillGuideLocalService getWrappedService() {
		return _skillGuideLocalService;
	}

	@Override
	public void setWrappedService(
		SkillGuideLocalService skillGuideLocalService) {

		_skillGuideLocalService = skillGuideLocalService;
	}

	private SkillGuideLocalService _skillGuideLocalService;

}