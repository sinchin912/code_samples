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

import com.trantorinc.synergy.skill.core.exception.NoSuchSkillGuideException;
import com.trantorinc.synergy.skill.core.model.SkillGuide;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the skill guide service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SkillGuideUtil
 * @generated
 */
@ProviderType
public interface SkillGuidePersistence extends BasePersistence<SkillGuide> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link SkillGuideUtil} to access the skill guide persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Caches the skill guide in the entity cache if it is enabled.
	 *
	 * @param skillGuide the skill guide
	 */
	public void cacheResult(SkillGuide skillGuide);

	/**
	 * Caches the skill guides in the entity cache if it is enabled.
	 *
	 * @param skillGuides the skill guides
	 */
	public void cacheResult(java.util.List<SkillGuide> skillGuides);

	/**
	 * Creates a new skill guide with the primary key. Does not add the skill guide to the database.
	 *
	 * @param guideId the primary key for the new skill guide
	 * @return the new skill guide
	 */
	public SkillGuide create(long guideId);

	/**
	 * Removes the skill guide with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param guideId the primary key of the skill guide
	 * @return the skill guide that was removed
	 * @throws NoSuchSkillGuideException if a skill guide with the primary key could not be found
	 */
	public SkillGuide remove(long guideId) throws NoSuchSkillGuideException;

	public SkillGuide updateImpl(SkillGuide skillGuide);

	/**
	 * Returns the skill guide with the primary key or throws a <code>NoSuchSkillGuideException</code> if it could not be found.
	 *
	 * @param guideId the primary key of the skill guide
	 * @return the skill guide
	 * @throws NoSuchSkillGuideException if a skill guide with the primary key could not be found
	 */
	public SkillGuide findByPrimaryKey(long guideId)
		throws NoSuchSkillGuideException;

	/**
	 * Returns the skill guide with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param guideId the primary key of the skill guide
	 * @return the skill guide, or <code>null</code> if a skill guide with the primary key could not be found
	 */
	public SkillGuide fetchByPrimaryKey(long guideId);

	/**
	 * Returns all the skill guides.
	 *
	 * @return the skill guides
	 */
	public java.util.List<SkillGuide> findAll();

	/**
	 * Returns a range of all the skill guides.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SkillGuideModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of skill guides
	 * @param end the upper bound of the range of skill guides (not inclusive)
	 * @return the range of skill guides
	 */
	public java.util.List<SkillGuide> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the skill guides.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SkillGuideModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of skill guides
	 * @param end the upper bound of the range of skill guides (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of skill guides
	 */
	public java.util.List<SkillGuide> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SkillGuide>
			orderByComparator);

	/**
	 * Returns an ordered range of all the skill guides.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SkillGuideModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of skill guides
	 * @param end the upper bound of the range of skill guides (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of skill guides
	 */
	public java.util.List<SkillGuide> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SkillGuide>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the skill guides from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of skill guides.
	 *
	 * @return the number of skill guides
	 */
	public int countAll();

}