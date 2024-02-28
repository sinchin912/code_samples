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

package com.trantorinc.synergy.game.core.service.persistence.impl;

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

import com.trantorinc.synergy.game.core.exception.NoSuchPrizeException;
import com.trantorinc.synergy.game.core.model.Prize;
import com.trantorinc.synergy.game.core.model.PrizeTable;
import com.trantorinc.synergy.game.core.model.impl.PrizeImpl;
import com.trantorinc.synergy.game.core.model.impl.PrizeModelImpl;
import com.trantorinc.synergy.game.core.service.persistence.PrizePersistence;
import com.trantorinc.synergy.game.core.service.persistence.PrizeUtil;
import com.trantorinc.synergy.game.core.service.persistence.impl.constants.GAMEPersistenceConstants;

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
 * The persistence implementation for the prize service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = {PrizePersistence.class, BasePersistence.class})
public class PrizePersistenceImpl
	extends BasePersistenceImpl<Prize> implements PrizePersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>PrizeUtil</code> to access the prize persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		PrizeImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;

	public PrizePersistenceImpl() {
		setModelClass(Prize.class);

		setModelImplClass(PrizeImpl.class);
		setModelPKClass(long.class);

		setTable(PrizeTable.INSTANCE);
	}

	/**
	 * Caches the prize in the entity cache if it is enabled.
	 *
	 * @param prize the prize
	 */
	@Override
	public void cacheResult(Prize prize) {
		entityCache.putResult(PrizeImpl.class, prize.getPrimaryKey(), prize);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the prizes in the entity cache if it is enabled.
	 *
	 * @param prizes the prizes
	 */
	@Override
	public void cacheResult(List<Prize> prizes) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (prizes.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (Prize prize : prizes) {
			if (entityCache.getResult(PrizeImpl.class, prize.getPrimaryKey()) ==
					null) {

				cacheResult(prize);
			}
		}
	}

	/**
	 * Clears the cache for all prizes.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(PrizeImpl.class);

		finderCache.clearCache(PrizeImpl.class);
	}

	/**
	 * Clears the cache for the prize.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Prize prize) {
		entityCache.removeResult(PrizeImpl.class, prize);
	}

	@Override
	public void clearCache(List<Prize> prizes) {
		for (Prize prize : prizes) {
			entityCache.removeResult(PrizeImpl.class, prize);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(PrizeImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(PrizeImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new prize with the primary key. Does not add the prize to the database.
	 *
	 * @param prizeId the primary key for the new prize
	 * @return the new prize
	 */
	@Override
	public Prize create(long prizeId) {
		Prize prize = new PrizeImpl();

		prize.setNew(true);
		prize.setPrimaryKey(prizeId);

		return prize;
	}

	/**
	 * Removes the prize with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param prizeId the primary key of the prize
	 * @return the prize that was removed
	 * @throws NoSuchPrizeException if a prize with the primary key could not be found
	 */
	@Override
	public Prize remove(long prizeId) throws NoSuchPrizeException {
		return remove((Serializable)prizeId);
	}

	/**
	 * Removes the prize with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the prize
	 * @return the prize that was removed
	 * @throws NoSuchPrizeException if a prize with the primary key could not be found
	 */
	@Override
	public Prize remove(Serializable primaryKey) throws NoSuchPrizeException {
		Session session = null;

		try {
			session = openSession();

			Prize prize = (Prize)session.get(PrizeImpl.class, primaryKey);

			if (prize == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchPrizeException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(prize);
		}
		catch (NoSuchPrizeException noSuchEntityException) {
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
	protected Prize removeImpl(Prize prize) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(prize)) {
				prize = (Prize)session.get(
					PrizeImpl.class, prize.getPrimaryKeyObj());
			}

			if (prize != null) {
				session.delete(prize);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (prize != null) {
			clearCache(prize);
		}

		return prize;
	}

	@Override
	public Prize updateImpl(Prize prize) {
		boolean isNew = prize.isNew();

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(prize);
			}
			else {
				prize = (Prize)session.merge(prize);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(PrizeImpl.class, prize, false, true);

		if (isNew) {
			prize.setNew(false);
		}

		prize.resetOriginalValues();

		return prize;
	}

	/**
	 * Returns the prize with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the prize
	 * @return the prize
	 * @throws NoSuchPrizeException if a prize with the primary key could not be found
	 */
	@Override
	public Prize findByPrimaryKey(Serializable primaryKey)
		throws NoSuchPrizeException {

		Prize prize = fetchByPrimaryKey(primaryKey);

		if (prize == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchPrizeException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return prize;
	}

	/**
	 * Returns the prize with the primary key or throws a <code>NoSuchPrizeException</code> if it could not be found.
	 *
	 * @param prizeId the primary key of the prize
	 * @return the prize
	 * @throws NoSuchPrizeException if a prize with the primary key could not be found
	 */
	@Override
	public Prize findByPrimaryKey(long prizeId) throws NoSuchPrizeException {
		return findByPrimaryKey((Serializable)prizeId);
	}

	/**
	 * Returns the prize with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param prizeId the primary key of the prize
	 * @return the prize, or <code>null</code> if a prize with the primary key could not be found
	 */
	@Override
	public Prize fetchByPrimaryKey(long prizeId) {
		return fetchByPrimaryKey((Serializable)prizeId);
	}

	/**
	 * Returns all the prizes.
	 *
	 * @return the prizes
	 */
	@Override
	public List<Prize> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the prizes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PrizeModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of prizes
	 * @param end the upper bound of the range of prizes (not inclusive)
	 * @return the range of prizes
	 */
	@Override
	public List<Prize> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the prizes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PrizeModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of prizes
	 * @param end the upper bound of the range of prizes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of prizes
	 */
	@Override
	public List<Prize> findAll(
		int start, int end, OrderByComparator<Prize> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the prizes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PrizeModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of prizes
	 * @param end the upper bound of the range of prizes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of prizes
	 */
	@Override
	public List<Prize> findAll(
		int start, int end, OrderByComparator<Prize> orderByComparator,
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

		List<Prize> list = null;

		if (useFinderCache) {
			list = (List<Prize>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_PRIZE);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_PRIZE;

				sql = sql.concat(PrizeModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<Prize>)QueryUtil.list(
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
	 * Removes all the prizes from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (Prize prize : findAll()) {
			remove(prize);
		}
	}

	/**
	 * Returns the number of prizes.
	 *
	 * @return the number of prizes
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_PRIZE);

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
		return "prizeId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_PRIZE;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return PrizeModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the prize persistence.
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

		_setPrizeUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setPrizeUtilPersistence(null);

		entityCache.removeCache(PrizeImpl.class.getName());
	}

	private void _setPrizeUtilPersistence(PrizePersistence prizePersistence) {
		try {
			Field field = PrizeUtil.class.getDeclaredField("_persistence");

			field.setAccessible(true);

			field.set(null, prizePersistence);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@Override
	@Reference(
		target = GAMEPersistenceConstants.SERVICE_CONFIGURATION_FILTER,
		unbind = "-"
	)
	public void setConfiguration(Configuration configuration) {
	}

	@Override
	@Reference(
		target = GAMEPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	@Reference(
		target = GAMEPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@Reference
	protected EntityCache entityCache;

	@Reference
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_PRIZE =
		"SELECT prize FROM Prize prize";

	private static final String _SQL_COUNT_PRIZE =
		"SELECT COUNT(prize) FROM Prize prize";

	private static final String _ORDER_BY_ENTITY_ALIAS = "prize.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No Prize exists with the primary key ";

	private static final Log _log = LogFactoryUtil.getLog(
		PrizePersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}