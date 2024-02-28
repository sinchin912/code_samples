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

package com.trantorinc.synergy.notice.core.service.persistence.impl;

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
import com.liferay.portal.kernel.util.SetUtil;

import com.trantorinc.synergy.notice.core.exception.NoSuchQuestionnaireFormException;
import com.trantorinc.synergy.notice.core.model.QuestionnaireForm;
import com.trantorinc.synergy.notice.core.model.QuestionnaireFormTable;
import com.trantorinc.synergy.notice.core.model.impl.QuestionnaireFormImpl;
import com.trantorinc.synergy.notice.core.model.impl.QuestionnaireFormModelImpl;
import com.trantorinc.synergy.notice.core.service.persistence.QuestionnaireFormPersistence;
import com.trantorinc.synergy.notice.core.service.persistence.QuestionnaireFormUtil;
import com.trantorinc.synergy.notice.core.service.persistence.impl.constants.NOTICEPersistenceConstants;

import java.io.Serializable;

import java.lang.reflect.Field;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * The persistence implementation for the questionnaire form service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(
	service = {QuestionnaireFormPersistence.class, BasePersistence.class}
)
public class QuestionnaireFormPersistenceImpl
	extends BasePersistenceImpl<QuestionnaireForm>
	implements QuestionnaireFormPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>QuestionnaireFormUtil</code> to access the questionnaire form persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		QuestionnaireFormImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;

	public QuestionnaireFormPersistenceImpl() {
		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("id", "id_");

		setDBColumnNames(dbColumnNames);

		setModelClass(QuestionnaireForm.class);

		setModelImplClass(QuestionnaireFormImpl.class);
		setModelPKClass(long.class);

		setTable(QuestionnaireFormTable.INSTANCE);
	}

	/**
	 * Caches the questionnaire form in the entity cache if it is enabled.
	 *
	 * @param questionnaireForm the questionnaire form
	 */
	@Override
	public void cacheResult(QuestionnaireForm questionnaireForm) {
		entityCache.putResult(
			QuestionnaireFormImpl.class, questionnaireForm.getPrimaryKey(),
			questionnaireForm);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the questionnaire forms in the entity cache if it is enabled.
	 *
	 * @param questionnaireForms the questionnaire forms
	 */
	@Override
	public void cacheResult(List<QuestionnaireForm> questionnaireForms) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (questionnaireForms.size() >
				 _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (QuestionnaireForm questionnaireForm : questionnaireForms) {
			if (entityCache.getResult(
					QuestionnaireFormImpl.class,
					questionnaireForm.getPrimaryKey()) == null) {

				cacheResult(questionnaireForm);
			}
		}
	}

	/**
	 * Clears the cache for all questionnaire forms.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(QuestionnaireFormImpl.class);

		finderCache.clearCache(QuestionnaireFormImpl.class);
	}

	/**
	 * Clears the cache for the questionnaire form.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(QuestionnaireForm questionnaireForm) {
		entityCache.removeResult(
			QuestionnaireFormImpl.class, questionnaireForm);
	}

	@Override
	public void clearCache(List<QuestionnaireForm> questionnaireForms) {
		for (QuestionnaireForm questionnaireForm : questionnaireForms) {
			entityCache.removeResult(
				QuestionnaireFormImpl.class, questionnaireForm);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(QuestionnaireFormImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(QuestionnaireFormImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new questionnaire form with the primary key. Does not add the questionnaire form to the database.
	 *
	 * @param id the primary key for the new questionnaire form
	 * @return the new questionnaire form
	 */
	@Override
	public QuestionnaireForm create(long id) {
		QuestionnaireForm questionnaireForm = new QuestionnaireFormImpl();

		questionnaireForm.setNew(true);
		questionnaireForm.setPrimaryKey(id);

		return questionnaireForm;
	}

	/**
	 * Removes the questionnaire form with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the questionnaire form
	 * @return the questionnaire form that was removed
	 * @throws NoSuchQuestionnaireFormException if a questionnaire form with the primary key could not be found
	 */
	@Override
	public QuestionnaireForm remove(long id)
		throws NoSuchQuestionnaireFormException {

		return remove((Serializable)id);
	}

	/**
	 * Removes the questionnaire form with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the questionnaire form
	 * @return the questionnaire form that was removed
	 * @throws NoSuchQuestionnaireFormException if a questionnaire form with the primary key could not be found
	 */
	@Override
	public QuestionnaireForm remove(Serializable primaryKey)
		throws NoSuchQuestionnaireFormException {

		Session session = null;

		try {
			session = openSession();

			QuestionnaireForm questionnaireForm =
				(QuestionnaireForm)session.get(
					QuestionnaireFormImpl.class, primaryKey);

			if (questionnaireForm == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchQuestionnaireFormException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(questionnaireForm);
		}
		catch (NoSuchQuestionnaireFormException noSuchEntityException) {
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
	protected QuestionnaireForm removeImpl(
		QuestionnaireForm questionnaireForm) {

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(questionnaireForm)) {
				questionnaireForm = (QuestionnaireForm)session.get(
					QuestionnaireFormImpl.class,
					questionnaireForm.getPrimaryKeyObj());
			}

			if (questionnaireForm != null) {
				session.delete(questionnaireForm);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (questionnaireForm != null) {
			clearCache(questionnaireForm);
		}

		return questionnaireForm;
	}

	@Override
	public QuestionnaireForm updateImpl(QuestionnaireForm questionnaireForm) {
		boolean isNew = questionnaireForm.isNew();

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(questionnaireForm);
			}
			else {
				questionnaireForm = (QuestionnaireForm)session.merge(
					questionnaireForm);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			QuestionnaireFormImpl.class, questionnaireForm, false, true);

		if (isNew) {
			questionnaireForm.setNew(false);
		}

		questionnaireForm.resetOriginalValues();

		return questionnaireForm;
	}

	/**
	 * Returns the questionnaire form with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the questionnaire form
	 * @return the questionnaire form
	 * @throws NoSuchQuestionnaireFormException if a questionnaire form with the primary key could not be found
	 */
	@Override
	public QuestionnaireForm findByPrimaryKey(Serializable primaryKey)
		throws NoSuchQuestionnaireFormException {

		QuestionnaireForm questionnaireForm = fetchByPrimaryKey(primaryKey);

		if (questionnaireForm == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchQuestionnaireFormException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return questionnaireForm;
	}

	/**
	 * Returns the questionnaire form with the primary key or throws a <code>NoSuchQuestionnaireFormException</code> if it could not be found.
	 *
	 * @param id the primary key of the questionnaire form
	 * @return the questionnaire form
	 * @throws NoSuchQuestionnaireFormException if a questionnaire form with the primary key could not be found
	 */
	@Override
	public QuestionnaireForm findByPrimaryKey(long id)
		throws NoSuchQuestionnaireFormException {

		return findByPrimaryKey((Serializable)id);
	}

	/**
	 * Returns the questionnaire form with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the questionnaire form
	 * @return the questionnaire form, or <code>null</code> if a questionnaire form with the primary key could not be found
	 */
	@Override
	public QuestionnaireForm fetchByPrimaryKey(long id) {
		return fetchByPrimaryKey((Serializable)id);
	}

	/**
	 * Returns all the questionnaire forms.
	 *
	 * @return the questionnaire forms
	 */
	@Override
	public List<QuestionnaireForm> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the questionnaire forms.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>QuestionnaireFormModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of questionnaire forms
	 * @param end the upper bound of the range of questionnaire forms (not inclusive)
	 * @return the range of questionnaire forms
	 */
	@Override
	public List<QuestionnaireForm> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the questionnaire forms.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>QuestionnaireFormModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of questionnaire forms
	 * @param end the upper bound of the range of questionnaire forms (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of questionnaire forms
	 */
	@Override
	public List<QuestionnaireForm> findAll(
		int start, int end,
		OrderByComparator<QuestionnaireForm> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the questionnaire forms.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>QuestionnaireFormModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of questionnaire forms
	 * @param end the upper bound of the range of questionnaire forms (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of questionnaire forms
	 */
	@Override
	public List<QuestionnaireForm> findAll(
		int start, int end,
		OrderByComparator<QuestionnaireForm> orderByComparator,
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

		List<QuestionnaireForm> list = null;

		if (useFinderCache) {
			list = (List<QuestionnaireForm>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_QUESTIONNAIREFORM);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_QUESTIONNAIREFORM;

				sql = sql.concat(QuestionnaireFormModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<QuestionnaireForm>)QueryUtil.list(
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
	 * Removes all the questionnaire forms from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (QuestionnaireForm questionnaireForm : findAll()) {
			remove(questionnaireForm);
		}
	}

	/**
	 * Returns the number of questionnaire forms.
	 *
	 * @return the number of questionnaire forms
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_QUESTIONNAIREFORM);

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
	public Set<String> getBadColumnNames() {
		return _badColumnNames;
	}

	@Override
	protected EntityCache getEntityCache() {
		return entityCache;
	}

	@Override
	protected String getPKDBName() {
		return "id_";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_QUESTIONNAIREFORM;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return QuestionnaireFormModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the questionnaire form persistence.
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

		_setQuestionnaireFormUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setQuestionnaireFormUtilPersistence(null);

		entityCache.removeCache(QuestionnaireFormImpl.class.getName());
	}

	private void _setQuestionnaireFormUtilPersistence(
		QuestionnaireFormPersistence questionnaireFormPersistence) {

		try {
			Field field = QuestionnaireFormUtil.class.getDeclaredField(
				"_persistence");

			field.setAccessible(true);

			field.set(null, questionnaireFormPersistence);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@Override
	@Reference(
		target = NOTICEPersistenceConstants.SERVICE_CONFIGURATION_FILTER,
		unbind = "-"
	)
	public void setConfiguration(Configuration configuration) {
	}

	@Override
	@Reference(
		target = NOTICEPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	@Reference(
		target = NOTICEPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@Reference
	protected EntityCache entityCache;

	@Reference
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_QUESTIONNAIREFORM =
		"SELECT questionnaireForm FROM QuestionnaireForm questionnaireForm";

	private static final String _SQL_COUNT_QUESTIONNAIREFORM =
		"SELECT COUNT(questionnaireForm) FROM QuestionnaireForm questionnaireForm";

	private static final String _ORDER_BY_ENTITY_ALIAS = "questionnaireForm.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No QuestionnaireForm exists with the primary key ";

	private static final Log _log = LogFactoryUtil.getLog(
		QuestionnaireFormPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"id"});

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}