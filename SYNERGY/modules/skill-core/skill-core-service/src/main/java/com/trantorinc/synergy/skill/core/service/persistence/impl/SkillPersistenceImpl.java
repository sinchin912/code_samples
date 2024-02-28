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

import com.trantorinc.synergy.skill.core.exception.NoSuchSkillException;
import com.trantorinc.synergy.skill.core.model.Skill;
import com.trantorinc.synergy.skill.core.model.SkillTable;
import com.trantorinc.synergy.skill.core.model.impl.SkillImpl;
import com.trantorinc.synergy.skill.core.model.impl.SkillModelImpl;
import com.trantorinc.synergy.skill.core.service.persistence.SkillPersistence;
import com.trantorinc.synergy.skill.core.service.persistence.SkillUtil;
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
 * The persistence implementation for the skill service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = {SkillPersistence.class, BasePersistence.class})
public class SkillPersistenceImpl
	extends BasePersistenceImpl<Skill> implements SkillPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>SkillUtil</code> to access the skill persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		SkillImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;

	public SkillPersistenceImpl() {
		setModelClass(Skill.class);

		setModelImplClass(SkillImpl.class);
		setModelPKClass(long.class);

		setTable(SkillTable.INSTANCE);
	}

	/**
	 * Caches the skill in the entity cache if it is enabled.
	 *
	 * @param skill the skill
	 */
	@Override
	public void cacheResult(Skill skill) {
		entityCache.putResult(SkillImpl.class, skill.getPrimaryKey(), skill);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the skills in the entity cache if it is enabled.
	 *
	 * @param skills the skills
	 */
	@Override
	public void cacheResult(List<Skill> skills) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (skills.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (Skill skill : skills) {
			if (entityCache.getResult(SkillImpl.class, skill.getPrimaryKey()) ==
					null) {

				cacheResult(skill);
			}
		}
	}

	/**
	 * Clears the cache for all skills.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(SkillImpl.class);

		finderCache.clearCache(SkillImpl.class);
	}

	/**
	 * Clears the cache for the skill.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Skill skill) {
		entityCache.removeResult(SkillImpl.class, skill);
	}

	@Override
	public void clearCache(List<Skill> skills) {
		for (Skill skill : skills) {
			entityCache.removeResult(SkillImpl.class, skill);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(SkillImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(SkillImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new skill with the primary key. Does not add the skill to the database.
	 *
	 * @param skillId the primary key for the new skill
	 * @return the new skill
	 */
	@Override
	public Skill create(long skillId) {
		Skill skill = new SkillImpl();

		skill.setNew(true);
		skill.setPrimaryKey(skillId);

		return skill;
	}

	/**
	 * Removes the skill with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param skillId the primary key of the skill
	 * @return the skill that was removed
	 * @throws NoSuchSkillException if a skill with the primary key could not be found
	 */
	@Override
	public Skill remove(long skillId) throws NoSuchSkillException {
		return remove((Serializable)skillId);
	}

	/**
	 * Removes the skill with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the skill
	 * @return the skill that was removed
	 * @throws NoSuchSkillException if a skill with the primary key could not be found
	 */
	@Override
	public Skill remove(Serializable primaryKey) throws NoSuchSkillException {
		Session session = null;

		try {
			session = openSession();

			Skill skill = (Skill)session.get(SkillImpl.class, primaryKey);

			if (skill == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchSkillException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(skill);
		}
		catch (NoSuchSkillException noSuchEntityException) {
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
	protected Skill removeImpl(Skill skill) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(skill)) {
				skill = (Skill)session.get(
					SkillImpl.class, skill.getPrimaryKeyObj());
			}

			if (skill != null) {
				session.delete(skill);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (skill != null) {
			clearCache(skill);
		}

		return skill;
	}

	@Override
	public Skill updateImpl(Skill skill) {
		boolean isNew = skill.isNew();

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(skill);
			}
			else {
				skill = (Skill)session.merge(skill);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(SkillImpl.class, skill, false, true);

		if (isNew) {
			skill.setNew(false);
		}

		skill.resetOriginalValues();

		return skill;
	}

	/**
	 * Returns the skill with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the skill
	 * @return the skill
	 * @throws NoSuchSkillException if a skill with the primary key could not be found
	 */
	@Override
	public Skill findByPrimaryKey(Serializable primaryKey)
		throws NoSuchSkillException {

		Skill skill = fetchByPrimaryKey(primaryKey);

		if (skill == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchSkillException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return skill;
	}

	/**
	 * Returns the skill with the primary key or throws a <code>NoSuchSkillException</code> if it could not be found.
	 *
	 * @param skillId the primary key of the skill
	 * @return the skill
	 * @throws NoSuchSkillException if a skill with the primary key could not be found
	 */
	@Override
	public Skill findByPrimaryKey(long skillId) throws NoSuchSkillException {
		return findByPrimaryKey((Serializable)skillId);
	}

	/**
	 * Returns the skill with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param skillId the primary key of the skill
	 * @return the skill, or <code>null</code> if a skill with the primary key could not be found
	 */
	@Override
	public Skill fetchByPrimaryKey(long skillId) {
		return fetchByPrimaryKey((Serializable)skillId);
	}

	/**
	 * Returns all the skills.
	 *
	 * @return the skills
	 */
	@Override
	public List<Skill> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<Skill> findAll(int start, int end) {
		return findAll(start, end, null);
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
	@Override
	public List<Skill> findAll(
		int start, int end, OrderByComparator<Skill> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
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
	@Override
	public List<Skill> findAll(
		int start, int end, OrderByComparator<Skill> orderByComparator,
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

		List<Skill> list = null;

		if (useFinderCache) {
			list = (List<Skill>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_SKILL);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_SKILL;

				sql = sql.concat(SkillModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<Skill>)QueryUtil.list(
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
	 * Removes all the skills from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (Skill skill : findAll()) {
			remove(skill);
		}
	}

	/**
	 * Returns the number of skills.
	 *
	 * @return the number of skills
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_SKILL);

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
		return "skillId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_SKILL;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return SkillModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the skill persistence.
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

		_setSkillUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setSkillUtilPersistence(null);

		entityCache.removeCache(SkillImpl.class.getName());
	}

	private void _setSkillUtilPersistence(SkillPersistence skillPersistence) {
		try {
			Field field = SkillUtil.class.getDeclaredField("_persistence");

			field.setAccessible(true);

			field.set(null, skillPersistence);
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

	private static final String _SQL_SELECT_SKILL =
		"SELECT skill FROM Skill skill";

	private static final String _SQL_COUNT_SKILL =
		"SELECT COUNT(skill) FROM Skill skill";

	private static final String _ORDER_BY_ENTITY_ALIAS = "skill.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No Skill exists with the primary key ";

	private static final Log _log = LogFactoryUtil.getLog(
		SkillPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}