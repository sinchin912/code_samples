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

import com.trantorinc.synergy.skill.core.exception.NoSuchSkillRejectionCommentException;
import com.trantorinc.synergy.skill.core.model.SkillRejectionComment;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the skill rejection comment service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SkillRejectionCommentUtil
 * @generated
 */
@ProviderType
public interface SkillRejectionCommentPersistence
	extends BasePersistence<SkillRejectionComment> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link SkillRejectionCommentUtil} to access the skill rejection comment persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Caches the skill rejection comment in the entity cache if it is enabled.
	 *
	 * @param skillRejectionComment the skill rejection comment
	 */
	public void cacheResult(SkillRejectionComment skillRejectionComment);

	/**
	 * Caches the skill rejection comments in the entity cache if it is enabled.
	 *
	 * @param skillRejectionComments the skill rejection comments
	 */
	public void cacheResult(
		java.util.List<SkillRejectionComment> skillRejectionComments);

	/**
	 * Creates a new skill rejection comment with the primary key. Does not add the skill rejection comment to the database.
	 *
	 * @param commentId the primary key for the new skill rejection comment
	 * @return the new skill rejection comment
	 */
	public SkillRejectionComment create(long commentId);

	/**
	 * Removes the skill rejection comment with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param commentId the primary key of the skill rejection comment
	 * @return the skill rejection comment that was removed
	 * @throws NoSuchSkillRejectionCommentException if a skill rejection comment with the primary key could not be found
	 */
	public SkillRejectionComment remove(long commentId)
		throws NoSuchSkillRejectionCommentException;

	public SkillRejectionComment updateImpl(
		SkillRejectionComment skillRejectionComment);

	/**
	 * Returns the skill rejection comment with the primary key or throws a <code>NoSuchSkillRejectionCommentException</code> if it could not be found.
	 *
	 * @param commentId the primary key of the skill rejection comment
	 * @return the skill rejection comment
	 * @throws NoSuchSkillRejectionCommentException if a skill rejection comment with the primary key could not be found
	 */
	public SkillRejectionComment findByPrimaryKey(long commentId)
		throws NoSuchSkillRejectionCommentException;

	/**
	 * Returns the skill rejection comment with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param commentId the primary key of the skill rejection comment
	 * @return the skill rejection comment, or <code>null</code> if a skill rejection comment with the primary key could not be found
	 */
	public SkillRejectionComment fetchByPrimaryKey(long commentId);

	/**
	 * Returns all the skill rejection comments.
	 *
	 * @return the skill rejection comments
	 */
	public java.util.List<SkillRejectionComment> findAll();

	/**
	 * Returns a range of all the skill rejection comments.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SkillRejectionCommentModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of skill rejection comments
	 * @param end the upper bound of the range of skill rejection comments (not inclusive)
	 * @return the range of skill rejection comments
	 */
	public java.util.List<SkillRejectionComment> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the skill rejection comments.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SkillRejectionCommentModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of skill rejection comments
	 * @param end the upper bound of the range of skill rejection comments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of skill rejection comments
	 */
	public java.util.List<SkillRejectionComment> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SkillRejectionComment>
			orderByComparator);

	/**
	 * Returns an ordered range of all the skill rejection comments.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SkillRejectionCommentModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of skill rejection comments
	 * @param end the upper bound of the range of skill rejection comments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of skill rejection comments
	 */
	public java.util.List<SkillRejectionComment> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SkillRejectionComment>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the skill rejection comments from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of skill rejection comments.
	 *
	 * @return the number of skill rejection comments
	 */
	public int countAll();

}