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

package com.trantorinc.synergy.skill.core.service.persistence;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.trantorinc.synergy.skill.core.model.Skill;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the skill service. This utility wraps <code>com.trantorinc.synergy.skill.core.service.persistence.impl.SkillPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SkillPersistence
 * @generated
 */
public class SkillUtil {

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
	public static void clearCache(Skill skill) {
		getPersistence().clearCache(skill);
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
	public static Map<Serializable, Skill> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<Skill> findWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Skill> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Skill> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<Skill> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static Skill update(Skill skill) {
		return getPersistence().update(skill);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static Skill update(Skill skill, ServiceContext serviceContext) {
		return getPersistence().update(skill, serviceContext);
	}

	/**
	 * Caches the skill in the entity cache if it is enabled.
	 *
	 * @param skill the skill
	 */
	public static void cacheResult(Skill skill) {
		getPersistence().cacheResult(skill);
	}

	/**
	 * Caches the skills in the entity cache if it is enabled.
	 *
	 * @param skills the skills
	 */
	public static void cacheResult(List<Skill> skills) {
		getPersistence().cacheResult(skills);
	}

	/**
	 * Creates a new skill with the primary key. Does not add the skill to the database.
	 *
	 * @param skillId the primary key for the new skill
	 * @return the new skill
	 */
	public static Skill create(long skillId) {
		return getPersistence().create(skillId);
	}

	/**
	 * Removes the skill with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param skillId the primary key of the skill
	 * @return the skill that was removed
	 * @throws NoSuchSkillException if a skill with the primary key could not be found
	 */
	public static Skill remove(long skillId)
		throws com.trantorinc.synergy.skill.core.exception.
			NoSuchSkillException {

		return getPersistence().remove(skillId);
	}

	public static Skill updateImpl(Skill skill) {
		return getPersistence().updateImpl(skill);
	}

	/**
	 * Returns the skill with the primary key or throws a <code>NoSuchSkillException</code> if it could not be found.
	 *
	 * @param skillId the primary key of the skill
	 * @return the skill
	 * @throws NoSuchSkillException if a skill with the primary key could not be found
	 */
	public static Skill findByPrimaryKey(long skillId)
		throws com.trantorinc.synergy.skill.core.exception.
			NoSuchSkillException {

		return getPersistence().findByPrimaryKey(skillId);
	}

	/**
	 * Returns the skill with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param skillId the primary key of the skill
	 * @return the skill, or <code>null</code> if a skill with the primary key could not be found
	 */
	public static Skill fetchByPrimaryKey(long skillId) {
		return getPersistence().fetchByPrimaryKey(skillId);
	}

	/**
	 * Returns all the skills.
	 *
	 * @return the skills
	 */
	public static List<Skill> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the skills.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SkillModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of skills
	 * @param end the upper bound of the range of skills (not inclusive)
	 * @return the range of skills
	 */
	public static List<Skill> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the skills.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SkillModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of skills
	 * @param end the upper bound of the range of skills (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of skills
	 */
	public static List<Skill> findAll(
		int start, int end, OrderByComparator<Skill> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the skills.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SkillModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of skills
	 * @param end the upper bound of the range of skills (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of skills
	 */
	public static List<Skill> findAll(
		int start, int end, OrderByComparator<Skill> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the skills from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of skills.
	 *
	 * @return the number of skills
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static SkillPersistence getPersistence() {
		return _persistence;
	}

	private static volatile SkillPersistence _persistence;

}