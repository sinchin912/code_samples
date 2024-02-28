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

package com.trantorinc.synergy.skill.core.model.impl;

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

import com.trantorinc.synergy.skill.core.model.SkillGuide;
import com.trantorinc.synergy.skill.core.model.SkillGuideModel;

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
 * The base model implementation for the SkillGuide service. Represents a row in the &quot;SKILL_SkillGuide&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>SkillGuideModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link SkillGuideImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SkillGuideImpl
 * @generated
 */
public class SkillGuideModelImpl
	extends BaseModelImpl<SkillGuide> implements SkillGuideModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a skill guide model instance should use the <code>SkillGuide</code> interface instead.
	 */
	public static final String TABLE_NAME = "SKILL_SkillGuide";

	public static final Object[][] TABLE_COLUMNS = {
		{"guideId", Types.BIGINT}, {"coreSkill", Types.VARCHAR},
		{"subSkill", Types.VARCHAR}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("guideId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("coreSkill", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("subSkill", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE =
		"create table SKILL_SkillGuide (guideId LONG not null primary key,coreSkill VARCHAR(75) null,subSkill VARCHAR(75) null)";

	public static final String TABLE_SQL_DROP = "drop table SKILL_SkillGuide";

	public static final String ORDER_BY_JPQL =
		" ORDER BY skillGuide.guideId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY SKILL_SkillGuide.guideId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long GUIDEID_COLUMN_BITMASK = 1L;

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

	public SkillGuideModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _guideId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setGuideId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _guideId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return SkillGuide.class;
	}

	@Override
	public String getModelClassName() {
		return SkillGuide.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<SkillGuide, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<SkillGuide, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<SkillGuide, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName, attributeGetterFunction.apply((SkillGuide)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<SkillGuide, Object>> attributeSetterBiConsumers =
			getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<SkillGuide, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(SkillGuide)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<SkillGuide, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<SkillGuide, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static final Map<String, Function<SkillGuide, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<SkillGuide, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<SkillGuide, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<SkillGuide, Object>>();
		Map<String, BiConsumer<SkillGuide, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<SkillGuide, ?>>();

		attributeGetterFunctions.put("guideId", SkillGuide::getGuideId);
		attributeSetterBiConsumers.put(
			"guideId", (BiConsumer<SkillGuide, Long>)SkillGuide::setGuideId);
		attributeGetterFunctions.put("coreSkill", SkillGuide::getCoreSkill);
		attributeSetterBiConsumers.put(
			"coreSkill",
			(BiConsumer<SkillGuide, String>)SkillGuide::setCoreSkill);
		attributeGetterFunctions.put("subSkill", SkillGuide::getSubSkill);
		attributeSetterBiConsumers.put(
			"subSkill",
			(BiConsumer<SkillGuide, String>)SkillGuide::setSubSkill);

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@Override
	public long getGuideId() {
		return _guideId;
	}

	@Override
	public void setGuideId(long guideId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_guideId = guideId;
	}

	@Override
	public String getCoreSkill() {
		if (_coreSkill == null) {
			return "";
		}
		else {
			return _coreSkill;
		}
	}

	@Override
	public void setCoreSkill(String coreSkill) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_coreSkill = coreSkill;
	}

	@Override
	public String getSubSkill() {
		if (_subSkill == null) {
			return "";
		}
		else {
			return _subSkill;
		}
	}

	@Override
	public void setSubSkill(String subSkill) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_subSkill = subSkill;
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
			0, SkillGuide.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public SkillGuide toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, SkillGuide>
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
		SkillGuideImpl skillGuideImpl = new SkillGuideImpl();

		skillGuideImpl.setGuideId(getGuideId());
		skillGuideImpl.setCoreSkill(getCoreSkill());
		skillGuideImpl.setSubSkill(getSubSkill());

		skillGuideImpl.resetOriginalValues();

		return skillGuideImpl;
	}

	@Override
	public SkillGuide cloneWithOriginalValues() {
		SkillGuideImpl skillGuideImpl = new SkillGuideImpl();

		skillGuideImpl.setGuideId(this.<Long>getColumnOriginalValue("guideId"));
		skillGuideImpl.setCoreSkill(
			this.<String>getColumnOriginalValue("coreSkill"));
		skillGuideImpl.setSubSkill(
			this.<String>getColumnOriginalValue("subSkill"));

		return skillGuideImpl;
	}

	@Override
	public int compareTo(SkillGuide skillGuide) {
		int value = 0;

		if (getGuideId() < skillGuide.getGuideId()) {
			value = -1;
		}
		else if (getGuideId() > skillGuide.getGuideId()) {
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

		if (!(object instanceof SkillGuide)) {
			return false;
		}

		SkillGuide skillGuide = (SkillGuide)object;

		long primaryKey = skillGuide.getPrimaryKey();

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
	public CacheModel<SkillGuide> toCacheModel() {
		SkillGuideCacheModel skillGuideCacheModel = new SkillGuideCacheModel();

		skillGuideCacheModel.guideId = getGuideId();

		skillGuideCacheModel.coreSkill = getCoreSkill();

		String coreSkill = skillGuideCacheModel.coreSkill;

		if ((coreSkill != null) && (coreSkill.length() == 0)) {
			skillGuideCacheModel.coreSkill = null;
		}

		skillGuideCacheModel.subSkill = getSubSkill();

		String subSkill = skillGuideCacheModel.subSkill;

		if ((subSkill != null) && (subSkill.length() == 0)) {
			skillGuideCacheModel.subSkill = null;
		}

		return skillGuideCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<SkillGuide, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<SkillGuide, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<SkillGuide, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("\"");
			sb.append(attributeName);
			sb.append("\": ");

			Object value = attributeGetterFunction.apply((SkillGuide)this);

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

		private static final Function<InvocationHandler, SkillGuide>
			_escapedModelProxyProviderFunction =
				ProxyUtil.getProxyProviderFunction(
					SkillGuide.class, ModelWrapper.class);

	}

	private long _guideId;
	private String _coreSkill;
	private String _subSkill;

	public <T> T getColumnValue(String columnName) {
		Function<SkillGuide, Object> function = _attributeGetterFunctions.get(
			columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((SkillGuide)this);
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

		_columnOriginalValues.put("guideId", _guideId);
		_columnOriginalValues.put("coreSkill", _coreSkill);
		_columnOriginalValues.put("subSkill", _subSkill);
	}

	private transient Map<String, Object> _columnOriginalValues;

	public static long getColumnBitmask(String columnName) {
		return _columnBitmasks.get(columnName);
	}

	private static final Map<String, Long> _columnBitmasks;

	static {
		Map<String, Long> columnBitmasks = new HashMap<>();

		columnBitmasks.put("guideId", 1L);

		columnBitmasks.put("coreSkill", 2L);

		columnBitmasks.put("subSkill", 4L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private SkillGuide _escapedModel;

}