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

package com.trantorinc.synergy.skill.core.service.persistence.impl;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.configuration.Configuration;
import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.SessionFactory;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.persistence.BasePersistence;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;

import com.trantorinc.synergy.skill.core.exception.NoSuchSkillRejectionCommentException;
import com.trantorinc.synergy.skill.core.model.SkillRejectionComment;
import com.trantorinc.synergy.skill.core.model.SkillRejectionCommentTable;
import com.trantorinc.synergy.skill.core.model.impl.SkillRejectionCommentImpl;
import com.trantorinc.synergy.skill.core.model.impl.SkillRejectionCommentModelImpl;
import com.trantorinc.synergy.skill.core.service.persistence.SkillRejectionCommentPersistence;
import com.trantorinc.synergy.skill.core.service.persistence.SkillRejectionCommentUtil;
import com.trantorinc.synergy.skill.core.service.persistence.impl.constants.SKILLPersistenceConstants;

import java.io.Serializable;

import java.lang.reflect.Field;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * The persistence implementation for the skill rejection comment service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(
	service = {SkillRejectionCommentPersistence.class, BasePersistence.class}
)
public class SkillRejectionCommentPersistenceImpl
	extends BasePersistenceImpl<SkillRejectionComment>
	implements SkillRejectionCommentPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>SkillRejectionCommentUtil</code> to access the skill rejection comment persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		SkillRejectionCommentImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;

	public SkillRejectionCommentPersistenceImpl() {
		setModelClass(SkillRejectionComment.class);

		setModelImplClass(SkillRejectionCommentImpl.class);
		setModelPKClass(long.class);

		setTable(SkillRejectionCommentTable.INSTANCE);
	}

	/**
	 * Caches the skill rejection comment in the entity cache if it is enabled.
	 *
	 * @param skillRejectionComment the skill rejection comment
	 */
	@Override
	public void cacheResult(SkillRejectionComment skillRejectionComment) {
		entityCache.putResult(
			SkillRejectionCommentImpl.class,
			skillRejectionComment.getPrimaryKey(), skillRejectionComment);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the skill rejection comments in the entity cache if it is enabled.
	 *
	 * @param skillRejectionComments the skill rejection comments
	 */
	@Override
	public void cacheResult(
		List<SkillRejectionComment> skillRejectionComments) {

		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (skillRejectionComments.size() >
				 _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (SkillRejectionComment skillRejectionComment :
				skillRejectionComments) {

			if (entityCache.getResult(
					SkillRejectionCommentImpl.class,
					skillRejectionComment.getPrimaryKey()) == null) {

				cacheResult(skillRejectionComment);
			}
		}
	}

	/**
	 * Clears the cache for all skill rejection comments.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(SkillRejectionCommentImpl.class);

		finderCache.clearCache(SkillRejectionCommentImpl.class);
	}

	/**
	 * Clears the cache for the skill rejection comment.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(SkillRejectionComment skillRejectionComment) {
		entityCache.removeResult(
			SkillRejectionCommentImpl.class, skillRejectionComment);
	}

	@Override
	public void clearCache(List<SkillRejectionComment> skillRejectionComments) {
		for (SkillRejectionComment skillRejectionComment :
				skillRejectionComments) {

			entityCache.removeResult(
				SkillRejectionCommentImpl.class, skillRejectionComment);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(SkillRejectionCommentImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(
				SkillRejectionCommentImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new skill rejection comment with the primary key. Does not add the skill rejection comment to the database.
	 *
	 * @param commentId the primary key for the new skill rejection comment
	 * @return the new skill rejection comment
	 */
	@Override
	public SkillRejectionComment create(long commentId) {
		SkillRejectionComment skillRejectionComment =
			new SkillRejectionCommentImpl();

		skillRejectionComment.setNew(true);
		skillRejectionComment.setPrimaryKey(commentId);

		return skillRejectionComment;
	}

	/**
	 * Removes the skill rejection comment with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param commentId the primary key of the skill rejection comment
	 * @return the skill rejection comment that was removed
	 * @throws NoSuchSkillRejectionCommentException if a skill rejection comment with the primary key could not be found
	 */
	@Override
	public SkillRejectionComment remove(long commentId)
		throws NoSuchSkillRejectionCommentException {

		return remove((Serializable)commentId);
	}

	/**
	 * Removes the skill rejection comment with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the skill rejection comment
	 * @return the skill rejection comment that was removed
	 * @throws NoSuchSkillRejectionCommentException if a skill rejection comment with the primary key could not be found
	 */
	@Override
	public SkillRejectionComment remove(Serializable primaryKey)
		throws NoSuchSkillRejectionCommentException {

		Session session = null;

		try {
			session = openSession();

			SkillRejectionComment skillRejectionComment =
				(SkillRejectionComment)session.get(
					SkillRejectionCommentImpl.class, primaryKey);

			if (skillRejectionComment == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchSkillRejectionCommentException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(skillRejectionComment);
		}
		catch (NoSuchSkillRejectionCommentException noSuchEntityException) {
			throw noSuchEntityException;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected SkillRejectionComment removeImpl(
		SkillRejectionComment skillRejectionComment) {

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(skillRejectionComment)) {
				skillRejectionComment = (SkillRejectionComment)session.get(
					SkillRejectionCommentImpl.class,
					skillRejectionComment.getPrimaryKeyObj());
			}

			if (skillRejectionComment != null) {
				session.delete(skillRejectionComment);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (skillRejectionComment != null) {
			clearCache(skillRejectionComment);
		}

		return skillRejectionComment;
	}

	@Override
	public SkillRejectionComment updateImpl(
		SkillRejectionComment skillRejectionComment) {

		boolean isNew = skillRejectionComment.isNew();

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(skillRejectionComment);
			}
			else {
				skillRejectionComment = (SkillRejectionComment)session.merge(
					skillRejectionComment);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			SkillRejectionCommentImpl.class, skillRejectionComment, false,
			true);

		if (isNew) {
			skillRejectionComment.setNew(false);
		}

		skillRejectionComment.resetOriginalValues();

		return skillRejectionComment;
	}

	/**
	 * Returns the skill rejection comment with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the skill rejection comment
	 * @return the skill rejection comment
	 * @throws NoSuchSkillRejectionCommentException if a skill rejection comment with the primary key could not be found
	 */
	@Override
	public SkillRejectionComment findByPrimaryKey(Serializable primaryKey)
		throws NoSuchSkillRejectionCommentException {

		SkillRejectionComment skillRejectionComment = fetchByPrimaryKey(
			primaryKey);

		if (skillRejectionComment == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchSkillRejectionCommentException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return skillRejectionComment;
	}

	/**
	 * Returns the skill rejection comment with the primary key or throws a <code>NoSuchSkillRejectionCommentException</code> if it could not be found.
	 *
	 * @param commentId the primary key of the skill rejection comment
	 * @return the skill rejection comment
	 * @throws NoSuchSkillRejectionCommentException if a skill rejection comment with the primary key could not be found
	 */
	@Override
	public SkillRejectionComment findByPrimaryKey(long commentId)
		throws NoSuchSkillRejectionCommentException {

		return findByPrimaryKey((Serializable)commentId);
	}

	/**
	 * Returns the skill rejection comment with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param commentId the primary key of the skill rejection comment
	 * @return the skill rejection comment, or <code>null</code> if a skill rejection comment with the primary key could not be found
	 */
	@Override
	public SkillRejectionComment fetchByPrimaryKey(long commentId) {
		return fetchByPrimaryKey((Serializable)commentId);
	}

	/**
	 * Returns all the skill rejection comments.
	 *
	 * @return the skill rejection comments
	 */
	@Override
	public List<SkillRejectionComment> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

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
	@Override
	public List<SkillRejectionComment> findAll(int start, int end) {
		return findAll(start, end, null);
	}

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
	@Override
	public List<SkillRejectionComment> findAll(
		int start, int end,
		OrderByComparator<SkillRejectionComment> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

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
	@Override
	public List<SkillRejectionComment> findAll(
		int start, int end,
		OrderByComparator<SkillRejectionComment> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindAll;
				finderArgs = FINDER_ARGS_EMPTY;
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindAll;
			finderArgs = new Object[] {start, end, orderByComparator};
		}

		List<SkillRejectionComment> list = null;

		if (useFinderCache) {
			list = (List<SkillRejectionComment>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_SKILLREJECTIONCOMMENT);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_SKILLREJECTIONCOMMENT;

				sql = sql.concat(SkillRejectionCommentModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<SkillRejectionComment>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the skill rejection comments from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (SkillRejectionComment skillRejectionComment : findAll()) {
			remove(skillRejectionComment);
		}
	}

	/**
	 * Returns the number of skill rejection comments.
	 *
	 * @return the number of skill rejection comments
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(
					_SQL_COUNT_SKILLREJECTIONCOMMENT);

				count = (Long)query.uniqueResult();

				finderCache.putResult(
					_finderPathCountAll, FINDER_ARGS_EMPTY, count);
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	@Override
	protected EntityCache getEntityCache() {
		return entityCache;
	}

	@Override
	protected String getPKDBName() {
		return "commentId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_SKILLREJECTIONCOMMENT;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return SkillRejectionCommentModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the skill rejection comment persistence.
	 */
	@Activate
	public void activate() {
		_valueObjectFinderCacheListThreshold = GetterUtil.getInteger(
			PropsUtil.get(PropsKeys.VALUE_OBJECT_FINDER_CACHE_LIST_THRESHOLD));

		_finderPathWithPaginationFindAll = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0],
			new String[0], true);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0],
			new String[0], true);

		_finderPathCountAll = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0], new String[0], false);

		_setSkillRejectionCommentUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setSkillRejectionCommentUtilPersistence(null);

		entityCache.removeCache(SkillRejectionCommentImpl.class.getName());
	}

	private void _setSkillRejectionCommentUtilPersistence(
		SkillRejectionCommentPersistence skillRejectionCommentPersistence) {

		try {
			Field field = SkillRejectionCommentUtil.class.getDeclaredField(
				"_persistence");

			field.setAccessible(true);

			field.set(null, skillRejectionCommentPersistence);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@Override
	@Reference(
		target = SKILLPersistenceConstants.SERVICE_CONFIGURATION_FILTER,
		unbind = "-"
	)
	public void setConfiguration(Configuration configuration) {
	}

	@Override
	@Reference(
		target = SKILLPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	@Reference(
		target = SKILLPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@Reference
	protected EntityCache entityCache;

	@Reference
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_SKILLREJECTIONCOMMENT =
		"SELECT skillRejectionComment FROM SkillRejectionComment skillRejectionComment";

	private static final String _SQL_COUNT_SKILLREJECTIONCOMMENT =
		"SELECT COUNT(skillRejectionComment) FROM SkillRejectionComment skillRejectionComment";

	private static final String _ORDER_BY_ENTITY_ALIAS =
		"skillRejectionComment.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No SkillRejectionComment exists with the primary key ";

	private static final Log _log = LogFactoryUtil.getLog(
		SkillRejectionCommentPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}