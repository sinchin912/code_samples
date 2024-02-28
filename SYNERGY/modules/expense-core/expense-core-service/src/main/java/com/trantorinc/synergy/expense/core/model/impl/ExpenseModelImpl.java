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

package com.trantorinc.synergy.expense.core.model.impl;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringUtil;

import com.trantorinc.synergy.expense.core.model.Expense;
import com.trantorinc.synergy.expense.core.model.ExpenseModel;

import java.io.Serializable;

import java.lang.reflect.InvocationHandler;

import java.sql.Blob;
import java.sql.Types;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * The base model implementation for the Expense service. Represents a row in the &quot;EXPENSE_Expense&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>ExpenseModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link ExpenseImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ExpenseImpl
 * @generated
 */
public class ExpenseModelImpl
	extends BaseModelImpl<Expense> implements ExpenseModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a expense model instance should use the <code>Expense</code> interface instead.
	 */
	public static final String TABLE_NAME = "EXPENSE_Expense";

	public static final Object[][] TABLE_COLUMNS = {
		{"expenseId", Types.BIGINT}, {"ecode", Types.VARCHAR},
		{"expenseType", Types.VARCHAR}, {"totalBillAmount", Types.BIGINT},
		{"assignee", Types.VARCHAR}, {"managerComment", Types.VARCHAR},
		{"financeComment", Types.VARCHAR}, {"approvingManager", Types.VARCHAR},
		{"status", Types.INTEGER}, {"createdDate", Types.TIMESTAMP},
		{"managerDate", Types.TIMESTAMP}, {"financeDate", Types.TIMESTAMP},
		{"fileId", Types.VARCHAR}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("expenseId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("ecode", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("expenseType", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("totalBillAmount", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("assignee", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("managerComment", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("financeComment", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("approvingManager", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("status", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("createdDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("managerDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("financeDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("fileId", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE =
		"create table EXPENSE_Expense (expenseId LONG not null primary key,ecode VARCHAR(75) null,expenseType VARCHAR(75) null,totalBillAmount LONG,assignee VARCHAR(75) null,managerComment VARCHAR(1000) null,financeComment VARCHAR(1000) null,approvingManager VARCHAR(75) null,status INTEGER,createdDate DATE null,managerDate DATE null,financeDate DATE null,fileId VARCHAR(75) null)";

	public static final String TABLE_SQL_DROP = "drop table EXPENSE_Expense";

	public static final String ORDER_BY_JPQL =
		" ORDER BY expense.expenseId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY EXPENSE_Expense.expenseId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long EXPENSEID_COLUMN_BITMASK = 1L;

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static void setEntityCacheEnabled(boolean entityCacheEnabled) {
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static void setFinderCacheEnabled(boolean finderCacheEnabled) {
	}

	public ExpenseModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _expenseId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setExpenseId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _expenseId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return Expense.class;
	}

	@Override
	public String getModelClassName() {
		return Expense.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<Expense, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<Expense, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<Expense, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName, attributeGetterFunction.apply((Expense)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<Expense, Object>> attributeSetterBiConsumers =
			getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<Expense, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(Expense)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<Expense, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<Expense, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static final Map<String, Function<Expense, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<Expense, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<Expense, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<Expense, Object>>();
		Map<String, BiConsumer<Expense, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<Expense, ?>>();

		attributeGetterFunctions.put("expenseId", Expense::getExpenseId);
		attributeSetterBiConsumers.put(
			"expenseId", (BiConsumer<Expense, Long>)Expense::setExpenseId);
		attributeGetterFunctions.put("ecode", Expense::getEcode);
		attributeSetterBiConsumers.put(
			"ecode", (BiConsumer<Expense, String>)Expense::setEcode);
		attributeGetterFunctions.put("expenseType", Expense::getExpenseType);
		attributeSetterBiConsumers.put(
			"expenseType",
			(BiConsumer<Expense, String>)Expense::setExpenseType);
		attributeGetterFunctions.put(
			"totalBillAmount", Expense::getTotalBillAmount);
		attributeSetterBiConsumers.put(
			"totalBillAmount",
			(BiConsumer<Expense, Long>)Expense::setTotalBillAmount);
		attributeGetterFunctions.put("assignee", Expense::getAssignee);
		attributeSetterBiConsumers.put(
			"assignee", (BiConsumer<Expense, String>)Expense::setAssignee);
		attributeGetterFunctions.put(
			"managerComment", Expense::getManagerComment);
		attributeSetterBiConsumers.put(
			"managerComment",
			(BiConsumer<Expense, String>)Expense::setManagerComment);
		attributeGetterFunctions.put(
			"financeComment", Expense::getFinanceComment);
		attributeSetterBiConsumers.put(
			"financeComment",
			(BiConsumer<Expense, String>)Expense::setFinanceComment);
		attributeGetterFunctions.put(
			"approvingManager", Expense::getApprovingManager);
		attributeSetterBiConsumers.put(
			"approvingManager",
			(BiConsumer<Expense, String>)Expense::setApprovingManager);
		attributeGetterFunctions.put("status", Expense::getStatus);
		attributeSetterBiConsumers.put(
			"status", (BiConsumer<Expense, Integer>)Expense::setStatus);
		attributeGetterFunctions.put("createdDate", Expense::getCreatedDate);
		attributeSetterBiConsumers.put(
			"createdDate", (BiConsumer<Expense, Date>)Expense::setCreatedDate);
		attributeGetterFunctions.put("managerDate", Expense::getManagerDate);
		attributeSetterBiConsumers.put(
			"managerDate", (BiConsumer<Expense, Date>)Expense::setManagerDate);
		attributeGetterFunctions.put("financeDate", Expense::getFinanceDate);
		attributeSetterBiConsumers.put(
			"financeDate", (BiConsumer<Expense, Date>)Expense::setFinanceDate);
		attributeGetterFunctions.put("fileId", Expense::getFileId);
		attributeSetterBiConsumers.put(
			"fileId", (BiConsumer<Expense, String>)Expense::setFileId);

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@Override
	public long getExpenseId() {
		return _expenseId;
	}

	@Override
	public void setExpenseId(long expenseId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_expenseId = expenseId;
	}

	@Override
	public String getEcode() {
		if (_ecode == null) {
			return "";
		}
		else {
			return _ecode;
		}
	}

	@Override
	public void setEcode(String ecode) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_ecode = ecode;
	}

	@Override
	public String getExpenseType() {
		if (_expenseType == null) {
			return "";
		}
		else {
			return _expenseType;
		}
	}

	@Override
	public void setExpenseType(String expenseType) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_expenseType = expenseType;
	}

	@Override
	public long getTotalBillAmount() {
		return _totalBillAmount;
	}

	@Override
	public void setTotalBillAmount(long totalBillAmount) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_totalBillAmount = totalBillAmount;
	}

	@Override
	public String getAssignee() {
		if (_assignee == null) {
			return "";
		}
		else {
			return _assignee;
		}
	}

	@Override
	public void setAssignee(String assignee) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_assignee = assignee;
	}

	@Override
	public String getManagerComment() {
		if (_managerComment == null) {
			return "";
		}
		else {
			return _managerComment;
		}
	}

	@Override
	public void setManagerComment(String managerComment) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_managerComment = managerComment;
	}

	@Override
	public String getFinanceComment() {
		if (_financeComment == null) {
			return "";
		}
		else {
			return _financeComment;
		}
	}

	@Override
	public void setFinanceComment(String financeComment) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_financeComment = financeComment;
	}

	@Override
	public String getApprovingManager() {
		if (_approvingManager == null) {
			return "";
		}
		else {
			return _approvingManager;
		}
	}

	@Override
	public void setApprovingManager(String approvingManager) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_approvingManager = approvingManager;
	}

	@Override
	public int getStatus() {
		return _status;
	}

	@Override
	public void setStatus(int status) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_status = status;
	}

	@Override
	public Date getCreatedDate() {
		return _createdDate;
	}

	@Override
	public void setCreatedDate(Date createdDate) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_createdDate = createdDate;
	}

	@Override
	public Date getManagerDate() {
		return _managerDate;
	}

	@Override
	public void setManagerDate(Date managerDate) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_managerDate = managerDate;
	}

	@Override
	public Date getFinanceDate() {
		return _financeDate;
	}

	@Override
	public void setFinanceDate(Date financeDate) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_financeDate = financeDate;
	}

	@Override
	public String getFileId() {
		if (_fileId == null) {
			return "";
		}
		else {
			return _fileId;
		}
	}

	@Override
	public void setFileId(String fileId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_fileId = fileId;
	}

	public long getColumnBitmask() {
		if (_columnBitmask > 0) {
			return _columnBitmask;
		}

		if ((_columnOriginalValues == null) ||
			(_columnOriginalValues == Collections.EMPTY_MAP)) {

			return 0;
		}

		for (Map.Entry<String, Object> entry :
				_columnOriginalValues.entrySet()) {

			if (!Objects.equals(
					entry.getValue(), getColumnValue(entry.getKey()))) {

				_columnBitmask |= _columnBitmasks.get(entry.getKey());
			}
		}

		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			0, Expense.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public Expense toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, Expense>
				escapedModelProxyProviderFunction =
					EscapedModelProxyProviderFunctionHolder.
						_escapedModelProxyProviderFunction;

			_escapedModel = escapedModelProxyProviderFunction.apply(
				new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		ExpenseImpl expenseImpl = new ExpenseImpl();

		expenseImpl.setExpenseId(getExpenseId());
		expenseImpl.setEcode(getEcode());
		expenseImpl.setExpenseType(getExpenseType());
		expenseImpl.setTotalBillAmount(getTotalBillAmount());
		expenseImpl.setAssignee(getAssignee());
		expenseImpl.setManagerComment(getManagerComment());
		expenseImpl.setFinanceComment(getFinanceComment());
		expenseImpl.setApprovingManager(getApprovingManager());
		expenseImpl.setStatus(getStatus());
		expenseImpl.setCreatedDate(getCreatedDate());
		expenseImpl.setManagerDate(getManagerDate());
		expenseImpl.setFinanceDate(getFinanceDate());
		expenseImpl.setFileId(getFileId());

		expenseImpl.resetOriginalValues();

		return expenseImpl;
	}

	@Override
	public Expense cloneWithOriginalValues() {
		ExpenseImpl expenseImpl = new ExpenseImpl();

		expenseImpl.setExpenseId(
			this.<Long>getColumnOriginalValue("expenseId"));
		expenseImpl.setEcode(this.<String>getColumnOriginalValue("ecode"));
		expenseImpl.setExpenseType(
			this.<String>getColumnOriginalValue("expenseType"));
		expenseImpl.setTotalBillAmount(
			this.<Long>getColumnOriginalValue("totalBillAmount"));
		expenseImpl.setAssignee(
			this.<String>getColumnOriginalValue("assignee"));
		expenseImpl.setManagerComment(
			this.<String>getColumnOriginalValue("managerComment"));
		expenseImpl.setFinanceComment(
			this.<String>getColumnOriginalValue("financeComment"));
		expenseImpl.setApprovingManager(
			this.<String>getColumnOriginalValue("approvingManager"));
		expenseImpl.setStatus(this.<Integer>getColumnOriginalValue("status"));
		expenseImpl.setCreatedDate(
			this.<Date>getColumnOriginalValue("createdDate"));
		expenseImpl.setManagerDate(
			this.<Date>getColumnOriginalValue("managerDate"));
		expenseImpl.setFinanceDate(
			this.<Date>getColumnOriginalValue("financeDate"));
		expenseImpl.setFileId(this.<String>getColumnOriginalValue("fileId"));

		return expenseImpl;
	}

	@Override
	public int compareTo(Expense expense) {
		int value = 0;

		if (getExpenseId() < expense.getExpenseId()) {
			value = -1;
		}
		else if (getExpenseId() > expense.getExpenseId()) {
			value = 1;
		}
		else {
			value = 0;
		}

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof Expense)) {
			return false;
		}

		Expense expense = (Expense)object;

		long primaryKey = expense.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	@Override
	public boolean isEntityCacheEnabled() {
		return true;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	@Override
	public boolean isFinderCacheEnabled() {
		return true;
	}

	@Override
	public void resetOriginalValues() {
		_columnOriginalValues = Collections.emptyMap();

		_columnBitmask = 0;
	}

	@Override
	public CacheModel<Expense> toCacheModel() {
		ExpenseCacheModel expenseCacheModel = new ExpenseCacheModel();

		expenseCacheModel.expenseId = getExpenseId();

		expenseCacheModel.ecode = getEcode();

		String ecode = expenseCacheModel.ecode;

		if ((ecode != null) && (ecode.length() == 0)) {
			expenseCacheModel.ecode = null;
		}

		expenseCacheModel.expenseType = getExpenseType();

		String expenseType = expenseCacheModel.expenseType;

		if ((expenseType != null) && (expenseType.length() == 0)) {
			expenseCacheModel.expenseType = null;
		}

		expenseCacheModel.totalBillAmount = getTotalBillAmount();

		expenseCacheModel.assignee = getAssignee();

		String assignee = expenseCacheModel.assignee;

		if ((assignee != null) && (assignee.length() == 0)) {
			expenseCacheModel.assignee = null;
		}

		expenseCacheModel.managerComment = getManagerComment();

		String managerComment = expenseCacheModel.managerComment;

		if ((managerComment != null) && (managerComment.length() == 0)) {
			expenseCacheModel.managerComment = null;
		}

		expenseCacheModel.financeComment = getFinanceComment();

		String financeComment = expenseCacheModel.financeComment;

		if ((financeComment != null) && (financeComment.length() == 0)) {
			expenseCacheModel.financeComment = null;
		}

		expenseCacheModel.approvingManager = getApprovingManager();

		String approvingManager = expenseCacheModel.approvingManager;

		if ((approvingManager != null) && (approvingManager.length() == 0)) {
			expenseCacheModel.approvingManager = null;
		}

		expenseCacheModel.status = getStatus();

		Date createdDate = getCreatedDate();

		if (createdDate != null) {
			expenseCacheModel.createdDate = createdDate.getTime();
		}
		else {
			expenseCacheModel.createdDate = Long.MIN_VALUE;
		}

		Date managerDate = getManagerDate();

		if (managerDate != null) {
			expenseCacheModel.managerDate = managerDate.getTime();
		}
		else {
			expenseCacheModel.managerDate = Long.MIN_VALUE;
		}

		Date financeDate = getFinanceDate();

		if (financeDate != null) {
			expenseCacheModel.financeDate = financeDate.getTime();
		}
		else {
			expenseCacheModel.financeDate = Long.MIN_VALUE;
		}

		expenseCacheModel.fileId = getFileId();

		String fileId = expenseCacheModel.fileId;

		if ((fileId != null) && (fileId.length() == 0)) {
			expenseCacheModel.fileId = null;
		}

		return expenseCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<Expense, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<Expense, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<Expense, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("\"");
			sb.append(attributeName);
			sb.append("\": ");

			Object value = attributeGetterFunction.apply((Expense)this);

			if (value == null) {
				sb.append("null");
			}
			else if (value instanceof Blob || value instanceof Date ||
					 value instanceof Map || value instanceof String) {

				sb.append(
					"\"" + StringUtil.replace(value.toString(), "\"", "'") +
						"\"");
			}
			else {
				sb.append(value);
			}

			sb.append(", ");
		}

		if (sb.index() > 1) {
			sb.setIndex(sb.index() - 1);
		}

		sb.append("}");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, Expense>
			_escapedModelProxyProviderFunction =
				ProxyUtil.getProxyProviderFunction(
					Expense.class, ModelWrapper.class);

	}

	private long _expenseId;
	private String _ecode;
	private String _expenseType;
	private long _totalBillAmount;
	private String _assignee;
	private String _managerComment;
	private String _financeComment;
	private String _approvingManager;
	private int _status;
	private Date _createdDate;
	private Date _managerDate;
	private Date _financeDate;
	private String _fileId;

	public <T> T getColumnValue(String columnName) {
		Function<Expense, Object> function = _attributeGetterFunctions.get(
			columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((Expense)this);
	}

	public <T> T getColumnOriginalValue(String columnName) {
		if (_columnOriginalValues == null) {
			return null;
		}

		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		return (T)_columnOriginalValues.get(columnName);
	}

	private void _setColumnOriginalValues() {
		_columnOriginalValues = new HashMap<String, Object>();

		_columnOriginalValues.put("expenseId", _expenseId);
		_columnOriginalValues.put("ecode", _ecode);
		_columnOriginalValues.put("expenseType", _expenseType);
		_columnOriginalValues.put("totalBillAmount", _totalBillAmount);
		_columnOriginalValues.put("assignee", _assignee);
		_columnOriginalValues.put("managerComment", _managerComment);
		_columnOriginalValues.put("financeComment", _financeComment);
		_columnOriginalValues.put("approvingManager", _approvingManager);
		_columnOriginalValues.put("status", _status);
		_columnOriginalValues.put("createdDate", _createdDate);
		_columnOriginalValues.put("managerDate", _managerDate);
		_columnOriginalValues.put("financeDate", _financeDate);
		_columnOriginalValues.put("fileId", _fileId);
	}

	private transient Map<String, Object> _columnOriginalValues;

	public static long getColumnBitmask(String columnName) {
		return _columnBitmasks.get(columnName);
	}

	private static final Map<String, Long> _columnBitmasks;

	static {
		Map<String, Long> columnBitmasks = new HashMap<>();

		columnBitmasks.put("expenseId", 1L);

		columnBitmasks.put("ecode", 2L);

		columnBitmasks.put("expenseType", 4L);

		columnBitmasks.put("totalBillAmount", 8L);

		columnBitmasks.put("assignee", 16L);

		columnBitmasks.put("managerComment", 32L);

		columnBitmasks.put("financeComment", 64L);

		columnBitmasks.put("approvingManager", 128L);

		columnBitmasks.put("status", 256L);

		columnBitmasks.put("createdDate", 512L);

		columnBitmasks.put("managerDate", 1024L);

		columnBitmasks.put("financeDate", 2048L);

		columnBitmasks.put("fileId", 4096L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private Expense _escapedModel;

}