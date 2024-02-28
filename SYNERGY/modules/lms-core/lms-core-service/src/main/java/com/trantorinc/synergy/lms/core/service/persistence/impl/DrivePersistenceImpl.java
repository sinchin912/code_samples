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

package com.trantorinc.synergy.lms.core.service.persistence.impl;

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

import com.trantorinc.synergy.lms.core.exception.NoSuchDriveException;
import com.trantorinc.synergy.lms.core.model.Drive;
import com.trantorinc.synergy.lms.core.model.DriveTable;
import com.trantorinc.synergy.lms.core.model.impl.DriveImpl;
import com.trantorinc.synergy.lms.core.model.impl.DriveModelImpl;
import com.trantorinc.synergy.lms.core.service.persistence.DrivePersistence;
import com.trantorinc.synergy.lms.core.service.persistence.DriveUtil;
import com.trantorinc.synergy.lms.core.service.persistence.impl.constants.LMSPersistenceConstants;

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
 * The persistence implementation for the drive service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = {DrivePersistence.class, BasePersistence.class})
public class DrivePersistenceImpl
	extends BasePersistenceImpl<Drive> implements DrivePersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>DriveUtil</code> to access the drive persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		DriveImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;

	public DrivePersistenceImpl() {
		setModelClass(Drive.class);

		setModelImplClass(DriveImpl.class);
		setModelPKClass(long.class);

		setTable(DriveTable.INSTANCE);
	}

	/**
	 * Caches the drive in the entity cache if it is enabled.
	 *
	 * @param drive the drive
	 */
	@Override
	public void cacheResult(Drive drive) {
		entityCache.putResult(DriveImpl.class, drive.getPrimaryKey(), drive);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the drives in the entity cache if it is enabled.
	 *
	 * @param drives the drives
	 */
	@Override
	public void cacheResult(List<Drive> drives) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (drives.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (Drive drive : drives) {
			if (entityCache.getResult(DriveImpl.class, drive.getPrimaryKey()) ==
					null) {

				cacheResult(drive);
			}
		}
	}

	/**
	 * Clears the cache for all drives.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(DriveImpl.class);

		finderCache.clearCache(DriveImpl.class);
	}

	/**
	 * Clears the cache for the drive.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Drive drive) {
		entityCache.removeResult(DriveImpl.class, drive);
	}

	@Override
	public void clearCache(List<Drive> drives) {
		for (Drive drive : drives) {
			entityCache.removeResult(DriveImpl.class, drive);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(DriveImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(DriveImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new drive with the primary key. Does not add the drive to the database.
	 *
	 * @param driveId the primary key for the new drive
	 * @return the new drive
	 */
	@Override
	public Drive create(long driveId) {
		Drive drive = new DriveImpl();

		drive.setNew(true);
		drive.setPrimaryKey(driveId);

		return drive;
	}

	/**
	 * Removes the drive with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param driveId the primary key of the drive
	 * @return the drive that was removed
	 * @throws NoSuchDriveException if a drive with the primary key could not be found
	 */
	@Override
	public Drive remove(long driveId) throws NoSuchDriveException {
		return remove((Serializable)driveId);
	}

	/**
	 * Removes the drive with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the drive
	 * @return the drive that was removed
	 * @throws NoSuchDriveException if a drive with the primary key could not be found
	 */
	@Override
	public Drive remove(Serializable primaryKey) throws NoSuchDriveException {
		Session session = null;

		try {
			session = openSession();

			Drive drive = (Drive)session.get(DriveImpl.class, primaryKey);

			if (drive == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchDriveException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(drive);
		}
		catch (NoSuchDriveException noSuchEntityException) {
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
	protected Drive removeImpl(Drive drive) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(drive)) {
				drive = (Drive)session.get(
					DriveImpl.class, drive.getPrimaryKeyObj());
			}

			if (drive != null) {
				session.delete(drive);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (drive != null) {
			clearCache(drive);
		}

		return drive;
	}

	@Override
	public Drive updateImpl(Drive drive) {
		boolean isNew = drive.isNew();

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(drive);
			}
			else {
				drive = (Drive)session.merge(drive);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(DriveImpl.class, drive, false, true);

		if (isNew) {
			drive.setNew(false);
		}

		drive.resetOriginalValues();

		return drive;
	}

	/**
	 * Returns the drive with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the drive
	 * @return the drive
	 * @throws NoSuchDriveException if a drive with the primary key could not be found
	 */
	@Override
	public Drive findByPrimaryKey(Serializable primaryKey)
		throws NoSuchDriveException {

		Drive drive = fetchByPrimaryKey(primaryKey);

		if (drive == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchDriveException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return drive;
	}

	/**
	 * Returns the drive with the primary key or throws a <code>NoSuchDriveException</code> if it could not be found.
	 *
	 * @param driveId the primary key of the drive
	 * @return the drive
	 * @throws NoSuchDriveException if a drive with the primary key could not be found
	 */
	@Override
	public Drive findByPrimaryKey(long driveId) throws NoSuchDriveException {
		return findByPrimaryKey((Serializable)driveId);
	}

	/**
	 * Returns the drive with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param driveId the primary key of the drive
	 * @return the drive, or <code>null</code> if a drive with the primary key could not be found
	 */
	@Override
	public Drive fetchByPrimaryKey(long driveId) {
		return fetchByPrimaryKey((Serializable)driveId);
	}

	/**
	 * Returns all the drives.
	 *
	 * @return the drives
	 */
	@Override
	public List<Drive> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the drives.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DriveModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of drives
	 * @param end the upper bound of the range of drives (not inclusive)
	 * @return the range of drives
	 */
	@Override
	public List<Drive> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the drives.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DriveModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of drives
	 * @param end the upper bound of the range of drives (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of drives
	 */
	@Override
	public List<Drive> findAll(
		int start, int end, OrderByComparator<Drive> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the drives.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DriveModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of drives
	 * @param end the upper bound of the range of drives (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of drives
	 */
	@Override
	public List<Drive> findAll(
		int start, int end, OrderByComparator<Drive> orderByComparator,
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

		List<Drive> list = null;

		if (useFinderCache) {
			list = (List<Drive>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_DRIVE);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_DRIVE;

				sql = sql.concat(DriveModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<Drive>)QueryUtil.list(
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
	 * Removes all the drives from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (Drive drive : findAll()) {
			remove(drive);
		}
	}

	/**
	 * Returns the number of drives.
	 *
	 * @return the number of drives
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_DRIVE);

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
		return "driveId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_DRIVE;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return DriveModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the drive persistence.
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

		_setDriveUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setDriveUtilPersistence(null);

		entityCache.removeCache(DriveImpl.class.getName());
	}

	private void _setDriveUtilPersistence(DrivePersistence drivePersistence) {
		try {
			Field field = DriveUtil.class.getDeclaredField("_persistence");

			field.setAccessible(true);

			field.set(null, drivePersistence);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@Override
	@Reference(
		target = LMSPersistenceConstants.SERVICE_CONFIGURATION_FILTER,
		unbind = "-"
	)
	public void setConfiguration(Configuration configuration) {
	}

	@Override
	@Reference(
		target = LMSPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	@Reference(
		target = LMSPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@Reference
	protected EntityCache entityCache;

	@Reference
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_DRIVE =
		"SELECT drive FROM Drive drive";

	private static final String _SQL_COUNT_DRIVE =
		"SELECT COUNT(drive) FROM Drive drive";

	private static final String _ORDER_BY_ENTITY_ALIAS = "drive.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No Drive exists with the primary key ";

	private static final Log _log = LogFactoryUtil.getLog(
		DrivePersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}