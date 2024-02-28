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

import com.trantorinc.synergy.skill.core.exception.NoSuchSkillGuideException;
import com.trantorinc.synergy.skill.core.model.SkillGuide;
import com.trantorinc.synergy.skill.core.model.SkillGuideTable;
import com.trantorinc.synergy.skill.core.model.impl.SkillGuideImpl;
import com.trantorinc.synergy.skill.core.model.impl.SkillGuideModelImpl;
import com.trantorinc.synergy.skill.core.service.persistence.SkillGuidePersistence;
import com.trantorinc.synergy.skill.core.service.persistence.SkillGuideUtil;
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
 * The persistence implementation for the skill guide service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = {SkillGuidePersistence.class, BasePersistence.class})
public class SkillGuidePersistenceImpl
	extends BasePersistenceImpl<SkillGuide> implements SkillGuidePersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>SkillGuideUtil</code> to access the skill guide persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		SkillGuideImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;

	public SkillGuidePersistenceImpl() {
		setModelClass(SkillGuide.class);

		setModelImplClass(SkillGuideImpl.class);
		setModelPKClass(long.class);

		setTable(SkillGuideTable.INSTANCE);
	}

	/**
	 * Caches the skill guide in the entity cache if it is enabled.
	 *
	 * @param skillGuide the skill guide
	 */
	@Override
	public void cacheResult(SkillGuide skillGuide) {
		entityCache.putResult(
			SkillGuideImpl.class, skillGuide.getPrimaryKey(), skillGuide);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the skill guides in the entity cache if it is enabled.
	 *
	 * @param skillGuides the skill guides
	 */
	@Override
	public void cacheResult(List<SkillGuide> skillGuides) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (skillGuides.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (SkillGuide skillGuide : skillGuides) {
			if (entityCache.getResult(
					SkillGuideImpl.class, skillGuide.getPrimaryKey()) == null) {

				cacheResult(skillGuide);
			}
		}
	}

	/**
	 * Clears the cache for all skill guides.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(SkillGuideImpl.class);

		finderCache.clearCache(SkillGuideImpl.class);
	}

	/**
	 * Clears the cache for the skill guide.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(SkillGuide skillGuide) {
		entityCache.removeResult(SkillGuideImpl.class, skillGuide);
	}

	@Override
	public void clearCache(List<SkillGuide> skillGuides) {
		for (SkillGuide skillGuide : skillGuides) {
			entityCache.removeResult(SkillGuideImpl.class, skillGuide);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(SkillGuideImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(SkillGuideImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new skill guide with the primary key. Does not add the skill guide to the database.
	 *
	 * @param guideId the primary key for the new skill guide
	 * @return the new skill guide
	 */
	@Override
	public SkillGuide create(long guideId) {
		SkillGuide skillGuide = new SkillGuideImpl();

		skillGuide.setNew(true);
		skillGuide.setPrimaryKey(guideId);

		return skillGuide;
	}

	/**
	 * Removes the skill guide with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param guideId the primary key of the skill guide
	 * @return the skill guide that was removed
	 * @throws NoSuchSkillGuideException if a skill guide with the primary key could not be found
	 */
	@Override
	public SkillGuide remove(long guideId) throws NoSuchSkillGuideException {
		return remove((Serializable)guideId);
	}

	/**
	 * Removes the skill guide with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the skill guide
	 * @return the skill guide that was removed
	 * @throws NoSuchSkillGuideException if a skill guide with the primary key could not be found
	 */
	@Override
	public SkillGuide remove(Serializable primaryKey)
		throws NoSuchSkillGuideException {

		Session session = null;

		try {
			session = openSession();

			SkillGuide skillGuide = (SkillGuide)session.get(
				SkillGuideImpl.class, primaryKey);

			if (skillGuide == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchSkillGuideException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(skillGuide);
		}
		catch (NoSuchSkillGuideException noSuchEntityException) {
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
	protected SkillGuide removeImpl(SkillGuide skillGuide) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(skillGuide)) {
				skillGuide = (SkillGuide)session.get(
					SkillGuideImpl.class, skillGuide.getPrimaryKeyObj());
			}

			if (skillGuide != null) {
				session.delete(skillGuide);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (skillGuide != null) {
			clearCache(skillGuide);
		}

		return skillGuide;
	}

	@Override
	public SkillGuide updateImpl(SkillGuide skillGuide) {
		boolean isNew = skillGuide.isNew();

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(skillGuide);
			}
			else {
				skillGuide = (SkillGuide)session.merge(skillGuide);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(SkillGuideImpl.class, skillGuide, false, true);

		if (isNew) {
			skillGuide.setNew(false);
		}

		skillGuide.resetOriginalValues();

		return skillGuide;
	}

	/**
	 * Returns the skill guide with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the skill guide
	 * @return the skill guide
	 * @throws NoSuchSkillGuideException if a skill guide with the primary key could not be found
	 */
	@Override
	public SkillGuide findByPrimaryKey(Serializable primaryKey)
		throws NoSuchSkillGuideException {

		SkillGuide skillGuide = fetchByPrimaryKey(primaryKey);

		if (skillGuide == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchSkillGuideException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return skillGuide;
	}

	/**
	 * Returns the skill guide with the primary key or throws a <code>NoSuchSkillGuideException</code> if it could not be found.
	 *
	 * @param guideId the primary key of the skill guide
	 * @return the skill guide
	 * @throws NoSuchSkillGuideException if a skill guide with the primary key could not be found
	 */
	@Override
	public SkillGuide findByPrimaryKey(long guideId)
		throws NoSuchSkillGuideException {

		return findByPrimaryKey((Serializable)guideId);
	}

	/**
	 * Returns the skill guide with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param guideId the primary key of the skill guide
	 * @return the skill guide, or <code>null</code> if a skill guide with the primary key could not be found
	 */
	@Override
	public SkillGuide fetchByPrimaryKey(long guideId) {
		return fetchByPrimaryKey((Serializable)guideId);
	}

	/**
	 * Returns all the skill guides.
	 *
	 * @return the skill guides
	 */
	@Override
	public List<SkillGuide> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

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
	@Override
	public List<SkillGuide> findAll(int start, int end) {
		return findAll(start, end, null);
	}

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
	@Override
	public List<SkillGuide> findAll(
		int start, int end, OrderByComparator<SkillGuide> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

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
	@Override
	public List<SkillGuide> findAll(
		int start, int end, OrderByComparator<SkillGuide> orderByComparator,
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

		List<SkillGuide> list = null;

		if (useFinderCache) {
			list = (List<SkillGuide>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_SKILLGUIDE);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_SKILLGUIDE;

				sql = sql.concat(SkillGuideModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<SkillGuide>)QueryUtil.list(
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
	 * Removes all the skill guides from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (SkillGuide skillGuide : findAll()) {
			remove(skillGuide);
		}
	}

	/**
	 * Returns the number of skill guides.
	 *
	 * @return the number of skill guides
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_SKILLGUIDE);

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
		return "guideId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_SKILLGUIDE;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return SkillGuideModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the skill guide persistence.
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

		_setSkillGuideUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setSkillGuideUtilPersistence(null);

		entityCache.removeCache(SkillGuideImpl.class.getName());
	}

	private void _setSkillGuideUtilPersistence(
		SkillGuidePersistence skillGuidePersistence) {

		try {
			Field field = SkillGuideUtil.class.getDeclaredField("_persistence");

			field.setAccessible(true);

			field.set(null, skillGuidePersistence);
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

	private static final String _SQL_SELECT_SKILLGUIDE =
		"SELECT skillGuide FROM SkillGuide skillGuide";

	private static final String _SQL_COUNT_SKILLGUIDE =
		"SELECT COUNT(skillGuide) FROM SkillGuide skillGuide";

	private static final String _ORDER_BY_ENTITY_ALIAS = "skillGuide.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No SkillGuide exists with the primary key ";

	private static final Log _log = LogFactoryUtil.getLog(
		SkillGuidePersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}