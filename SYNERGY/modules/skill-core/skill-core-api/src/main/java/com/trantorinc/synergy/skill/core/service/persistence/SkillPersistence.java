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

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import com.trantorinc.synergy.skill.core.exception.NoSuchSkillException;
import com.trantorinc.synergy.skill.core.model.Skill;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the skill service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SkillUtil
 * @generated
 */
@ProviderType
public interface SkillPersistence extends BasePersistence<Skill> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link SkillUtil} to access the skill persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Caches the skill in the entity cache if it is enabled.
	 *
	 * @param skill the skill
	 */
	public void cacheResult(Skill skill);

	/**
	 * Caches the skills in the entity cache if it is enabled.
	 *
	 * @param skills the skills
	 */
	public void cacheResult(java.util.List<Skill> skills);

	/**
	 * Creates a new skill with the primary key. Does not add the skill to the database.
	 *
	 * @param skillId the primary key for the new skill
	 * @return the new skill
	 */
	public Skill create(long skillId);

	/**
	 * Removes the skill with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param skillId the primary key of the skill
	 * @return the skill that was removed
	 * @throws NoSuchSkillException if a skill with the primary key could not be found
	 */
	public Skill remove(long skillId) throws NoSuchSkillException;

	public Skill updateImpl(Skill skill);

	/**
	 * Returns the skill with the primary key or throws a <code>NoSuchSkillException</code> if it could not be found.
	 *
	 * @param skillId the primary key of the skill
	 * @return the skill
	 * @throws NoSuchSkillException if a skill with the primary key could not be found
	 */
	public Skill findByPrimaryKey(long skillId) throws NoSuchSkillException;

	/**
	 * Returns the skill with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param skillId the primary key of the skill
	 * @return the skill, or <code>null</code> if a skill with the primary key could not be found
	 */
	public Skill fetchByPrimaryKey(long skillId);

	/**
	 * Returns all the skills.
	 *
	 * @return the skills
	 */
	public java.util.List<Skill> findAll();

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
	public java.util.List<Skill> findAll(int start, int end);

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
	public java.util.List<Skill> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Skill>
			orderByComparator);

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
	public java.util.List<Skill> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Skill>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the skills from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of skills.
	 *
	 * @return the number of skills
	 */
	public int countAll();

}