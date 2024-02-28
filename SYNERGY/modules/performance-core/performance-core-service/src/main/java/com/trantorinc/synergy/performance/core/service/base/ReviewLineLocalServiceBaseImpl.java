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

package com.trantorinc.synergy.performance.core.service.base;

import com.liferay.petra.sql.dsl.query.DSLQuery;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DefaultActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalServiceImpl;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.service.persistence.BasePersistence;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;

import com.trantorinc.synergy.performance.core.model.ReviewLine;
import com.trantorinc.synergy.performance.core.service.ReviewLineLocalService;
import com.trantorinc.synergy.performance.core.service.ReviewLineLocalServiceUtil;
import com.trantorinc.synergy.performance.core.service.persistence.KpiFinder;
import com.trantorinc.synergy.performance.core.service.persistence.KpiGuidePersistence;
import com.trantorinc.synergy.performance.core.service.persistence.KpiLineFinder;
import com.trantorinc.synergy.performance.core.service.persistence.KpiLinePersistence;
import com.trantorinc.synergy.performance.core.service.persistence.KpiPersistence;
import com.trantorinc.synergy.performance.core.service.persistence.ReviewCloseFinder;
import com.trantorinc.synergy.performance.core.service.persistence.ReviewClosePersistence;
import com.trantorinc.synergy.performance.core.service.persistence.ReviewCommentFinder;
import com.trantorinc.synergy.performance.core.service.persistence.ReviewCommentPersistence;
import com.trantorinc.synergy.performance.core.service.persistence.ReviewFinder;
import com.trantorinc.synergy.performance.core.service.persistence.ReviewLineFinder;
import com.trantorinc.synergy.performance.core.service.persistence.ReviewLinePersistence;
import com.trantorinc.synergy.performance.core.service.persistence.ReviewPersistence;
import com.trantorinc.synergy.performance.core.service.persistence.ReviewRollbackFinder;
import com.trantorinc.synergy.performance.core.service.persistence.ReviewRollbackPersistence;
import com.trantorinc.synergy.performance.core.service.persistence.ReviewTimelinePersistence;
import com.trantorinc.synergy.performance.core.service.persistence.ReviewUploadFinder;
import com.trantorinc.synergy.performance.core.service.persistence.ReviewUploadPersistence;

import java.io.Serializable;

import java.lang.reflect.Field;

import java.util.List;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * Provides the base implementation for the review line local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.trantorinc.synergy.performance.core.service.impl.ReviewLineLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.trantorinc.synergy.performance.core.service.impl.ReviewLineLocalServiceImpl
 * @generated
 */
public abstract class ReviewLineLocalServiceBaseImpl
	extends BaseLocalServiceImpl
	implements AopService, IdentifiableOSGiService, ReviewLineLocalService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>ReviewLineLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>ReviewLineLocalServiceUtil</code>.
	 */

	/**
	 * Adds the review line to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ReviewLineLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param reviewLine the review line
	 * @return the review line that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public ReviewLine addReviewLine(ReviewLine reviewLine) {
		reviewLine.setNew(true);

		return reviewLinePersistence.update(reviewLine);
	}

	/**
	 * Creates a new review line with the primary key. Does not add the review line to the database.
	 *
	 * @param lineId the primary key for the new review line
	 * @return the new review line
	 */
	@Override
	@Transactional(enabled = false)
	public ReviewLine createReviewLine(long lineId) {
		return reviewLinePersistence.create(lineId);
	}

	/**
	 * Deletes the review line with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ReviewLineLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param lineId the primary key of the review line
	 * @return the review line that was removed
	 * @throws PortalException if a review line with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public ReviewLine deleteReviewLine(long lineId) throws PortalException {
		return reviewLinePersistence.remove(lineId);
	}

	/**
	 * Deletes the review line from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ReviewLineLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param reviewLine the review line
	 * @return the review line that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public ReviewLine deleteReviewLine(ReviewLine reviewLine) {
		return reviewLinePersistence.remove(reviewLine);
	}

	@Override
	public <T> T dslQuery(DSLQuery dslQuery) {
		return reviewLinePersistence.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(DSLQuery dslQuery) {
		Long count = dslQuery(dslQuery);

		return count.intValue();
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(
			ReviewLine.class, clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return reviewLinePersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.trantorinc.synergy.performance.core.model.impl.ReviewLineModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return reviewLinePersistence.findWithDynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.trantorinc.synergy.performance.core.model.impl.ReviewLineModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<T> orderByComparator) {

		return reviewLinePersistence.findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery) {
		return reviewLinePersistence.countWithDynamicQuery(dynamicQuery);
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
		DynamicQuery dynamicQuery, Projection projection) {

		return reviewLinePersistence.countWithDynamicQuery(
			dynamicQuery, projection);
	}

	@Override
	public ReviewLine fetchReviewLine(long lineId) {
		return reviewLinePersistence.fetchByPrimaryKey(lineId);
	}

	/**
	 * Returns the review line with the primary key.
	 *
	 * @param lineId the primary key of the review line
	 * @return the review line
	 * @throws PortalException if a review line with the primary key could not be found
	 */
	@Override
	public ReviewLine getReviewLine(long lineId) throws PortalException {
		return reviewLinePersistence.findByPrimaryKey(lineId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery =
			new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(reviewLineLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(ReviewLine.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("lineId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(
			reviewLineLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(ReviewLine.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName("lineId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {

		actionableDynamicQuery.setBaseLocalService(reviewLineLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(ReviewLine.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("lineId");
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel createPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return reviewLinePersistence.create(((Long)primaryKeyObj).longValue());
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {

		if (_log.isWarnEnabled()) {
			_log.warn(
				"Implement ReviewLineLocalServiceImpl#deleteReviewLine(ReviewLine) to avoid orphaned data");
		}

		return reviewLineLocalService.deleteReviewLine(
			(ReviewLine)persistedModel);
	}

	@Override
	public BasePersistence<ReviewLine> getBasePersistence() {
		return reviewLinePersistence;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return reviewLinePersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns a range of all the review lines.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.trantorinc.synergy.performance.core.model.impl.ReviewLineModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of review lines
	 * @param end the upper bound of the range of review lines (not inclusive)
	 * @return the range of review lines
	 */
	@Override
	public List<ReviewLine> getReviewLines(int start, int end) {
		return reviewLinePersistence.findAll(start, end);
	}

	/**
	 * Returns the number of review lines.
	 *
	 * @return the number of review lines
	 */
	@Override
	public int getReviewLinesCount() {
		return reviewLinePersistence.countAll();
	}

	/**
	 * Updates the review line in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ReviewLineLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param reviewLine the review line
	 * @return the review line that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public ReviewLine updateReviewLine(ReviewLine reviewLine) {
		return reviewLinePersistence.update(reviewLine);
	}

	@Deactivate
	protected void deactivate() {
		_setLocalServiceUtilService(null);
	}

	@Override
	public Class<?>[] getAopInterfaces() {
		return new Class<?>[] {
			ReviewLineLocalService.class, IdentifiableOSGiService.class,
			PersistedModelLocalService.class
		};
	}

	@Override
	public void setAopProxy(Object aopProxy) {
		reviewLineLocalService = (ReviewLineLocalService)aopProxy;

		_setLocalServiceUtilService(reviewLineLocalService);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return ReviewLineLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return ReviewLine.class;
	}

	protected String getModelClassName() {
		return ReviewLine.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = reviewLinePersistence.getDataSource();

			DB db = DBManagerUtil.getDB();

			sql = db.buildSQL(sql);
			sql = PortalUtil.transformSQL(sql);

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(
				dataSource, sql);

			sqlUpdate.update();
		}
		catch (Exception exception) {
			throw new SystemException(exception);
		}
	}

	private void _setLocalServiceUtilService(
		ReviewLineLocalService reviewLineLocalService) {

		try {
			Field field = ReviewLineLocalServiceUtil.class.getDeclaredField(
				"_service");

			field.setAccessible(true);

			field.set(null, reviewLineLocalService);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@Reference
	protected KpiPersistence kpiPersistence;

	@Reference
	protected KpiFinder kpiFinder;

	@Reference
	protected KpiGuidePersistence kpiGuidePersistence;

	@Reference
	protected KpiLinePersistence kpiLinePersistence;

	@Reference
	protected KpiLineFinder kpiLineFinder;

	@Reference
	protected ReviewPersistence reviewPersistence;

	@Reference
	protected ReviewFinder reviewFinder;

	@Reference
	protected ReviewClosePersistence reviewClosePersistence;

	@Reference
	protected ReviewCloseFinder reviewCloseFinder;

	@Reference
	protected ReviewCommentPersistence reviewCommentPersistence;

	@Reference
	protected ReviewCommentFinder reviewCommentFinder;

	protected ReviewLineLocalService reviewLineLocalService;

	@Reference
	protected ReviewLinePersistence reviewLinePersistence;

	@Reference
	protected ReviewLineFinder reviewLineFinder;

	@Reference
	protected ReviewRollbackPersistence reviewRollbackPersistence;

	@Reference
	protected ReviewRollbackFinder reviewRollbackFinder;

	@Reference
	protected ReviewTimelinePersistence reviewTimelinePersistence;

	@Reference
	protected ReviewUploadPersistence reviewUploadPersistence;

	@Reference
	protected ReviewUploadFinder reviewUploadFinder;

	@Reference
	protected com.liferay.counter.kernel.service.CounterLocalService
		counterLocalService;

	@Reference
	protected com.liferay.portal.kernel.service.ClassNameLocalService
		classNameLocalService;

	@Reference
	protected com.liferay.portal.kernel.service.ResourceLocalService
		resourceLocalService;

	@Reference
	protected com.liferay.portal.kernel.service.UserLocalService
		userLocalService;

	private static final Log _log = LogFactoryUtil.getLog(
		ReviewLineLocalServiceBaseImpl.class);

}