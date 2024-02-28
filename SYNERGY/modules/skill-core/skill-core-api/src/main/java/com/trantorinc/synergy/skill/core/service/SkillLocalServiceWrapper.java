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
 * Provides a wrapper for {@link SkillLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see SkillLocalService
 * @generated
 */
public class SkillLocalServiceWrapper
	implements ServiceWrapper<SkillLocalService>, SkillLocalService {

	public SkillLocalServiceWrapper() {
		this(null);
	}

	public SkillLocalServiceWrapper(SkillLocalService skillLocalService) {
		_skillLocalService = skillLocalService;
	}

	/**
	 * Adds the skill to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect SkillLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param skill the skill
	 * @return the skill that was added
	 */
	@Override
	public com.trantorinc.synergy.skill.core.model.Skill addSkill(
		com.trantorinc.synergy.skill.core.model.Skill skill) {

		return _skillLocalService.addSkill(skill);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _skillLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Creates a new skill with the primary key. Does not add the skill to the database.
	 *
	 * @param skillId the primary key for the new skill
	 * @return the new skill
	 */
	@Override
	public com.trantorinc.synergy.skill.core.model.Skill createSkill(
		long skillId) {

		return _skillLocalService.createSkill(skillId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _skillLocalService.deletePersistedModel(persistedModel);
	}

	/**
	 * Deletes the skill with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect SkillLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param skillId the primary key of the skill
	 * @return the skill that was removed
	 * @throws PortalException if a skill with the primary key could not be found
	 */
	@Override
	public com.trantorinc.synergy.skill.core.model.Skill deleteSkill(
			long skillId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _skillLocalService.deleteSkill(skillId);
	}

	/**
	 * Deletes the skill from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect SkillLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param skill the skill
	 * @return the skill that was removed
	 */
	@Override
	public com.trantorinc.synergy.skill.core.model.Skill deleteSkill(
		com.trantorinc.synergy.skill.core.model.Skill skill) {

		return _skillLocalService.deleteSkill(skill);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _skillLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _skillLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _skillLocalService.dynamicQuery();
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

		return _skillLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.trantorinc.synergy.skill.core.model.impl.SkillModelImpl</code>.
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

		return _skillLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.trantorinc.synergy.skill.core.model.impl.SkillModelImpl</code>.
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

		return _skillLocalService.dynamicQuery(
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

		return _skillLocalService.dynamicQueryCount(dynamicQuery);
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

		return _skillLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public com.trantorinc.synergy.skill.core.model.Skill fetchSkill(
		long skillId) {

		return _skillLocalService.fetchSkill(skillId);
	}

	@Override
	public java.util.List<com.trantorinc.synergy.skill.core.model.Skill>
		findSkillsByEmployee(String ecode) {

		return _skillLocalService.findSkillsByEmployee(ecode);
	}

	@Override
	public java.util.List<com.trantorinc.synergy.skill.core.model.Skill>
		findSkillsByReviewer(String reviewerEmail) {

		return _skillLocalService.findSkillsByReviewer(reviewerEmail);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _skillLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _skillLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _skillLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _skillLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns the skill with the primary key.
	 *
	 * @param skillId the primary key of the skill
	 * @return the skill
	 * @throws PortalException if a skill with the primary key could not be found
	 */
	@Override
	public com.trantorinc.synergy.skill.core.model.Skill getSkill(long skillId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _skillLocalService.getSkill(skillId);
	}

	/**
	 * Returns a range of all the skills.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.trantorinc.synergy.skill.core.model.impl.SkillModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of skills
	 * @param end the upper bound of the range of skills (not inclusive)
	 * @return the range of skills
	 */
	@Override
	public java.util.List<com.trantorinc.synergy.skill.core.model.Skill>
		getSkills(int start, int end) {

		return _skillLocalService.getSkills(start, end);
	}

	/**
	 * Returns the number of skills.
	 *
	 * @return the number of skills
	 */
	@Override
	public int getSkillsCount() {
		return _skillLocalService.getSkillsCount();
	}

	/**
	 * Updates the skill in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect SkillLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param skill the skill
	 * @return the skill that was updated
	 */
	@Override
	public com.trantorinc.synergy.skill.core.model.Skill updateSkill(
		com.trantorinc.synergy.skill.core.model.Skill skill) {

		return _skillLocalService.updateSkill(skill);
	}

	@Override
	public SkillLocalService getWrappedService() {
		return _skillLocalService;
	}

	@Override
	public void setWrappedService(SkillLocalService skillLocalService) {
		_skillLocalService = skillLocalService;
	}

	private SkillLocalService _skillLocalService;

}