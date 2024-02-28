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

package com.trantorinc.synergy.lms.core.model;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link Holiday}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Holiday
 * @generated
 */
public class HolidayWrapper
	extends BaseModelWrapper<Holiday>
	implements Holiday, ModelWrapper<Holiday> {

	public HolidayWrapper(Holiday holiday) {
		super(holiday);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("holidayId", getHolidayId());
		attributes.put("holidayYear", getHolidayYear());
		attributes.put("onDate", getOnDate());
		attributes.put("name", getName());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long holidayId = (Long)attributes.get("holidayId");

		if (holidayId != null) {
			setHolidayId(holidayId);
		}

		Integer holidayYear = (Integer)attributes.get("holidayYear");

		if (holidayYear != null) {
			setHolidayYear(holidayYear);
		}

		Date onDate = (Date)attributes.get("onDate");

		if (onDate != null) {
			setOnDate(onDate);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}
	}

	@Override
	public Holiday cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the holiday ID of this holiday.
	 *
	 * @return the holiday ID of this holiday
	 */
	@Override
	public long getHolidayId() {
		return model.getHolidayId();
	}

	/**
	 * Returns the holiday year of this holiday.
	 *
	 * @return the holiday year of this holiday
	 */
	@Override
	public int getHolidayYear() {
		return model.getHolidayYear();
	}

	/**
	 * Returns the name of this holiday.
	 *
	 * @return the name of this holiday
	 */
	@Override
	public String getName() {
		return model.getName();
	}

	/**
	 * Returns the on date of this holiday.
	 *
	 * @return the on date of this holiday
	 */
	@Override
	public Date getOnDate() {
		return model.getOnDate();
	}

	/**
	 * Returns the primary key of this holiday.
	 *
	 * @return the primary key of this holiday
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the holiday ID of this holiday.
	 *
	 * @param holidayId the holiday ID of this holiday
	 */
	@Override
	public void setHolidayId(long holidayId) {
		model.setHolidayId(holidayId);
	}

	/**
	 * Sets the holiday year of this holiday.
	 *
	 * @param holidayYear the holiday year of this holiday
	 */
	@Override
	public void setHolidayYear(int holidayYear) {
		model.setHolidayYear(holidayYear);
	}

	/**
	 * Sets the name of this holiday.
	 *
	 * @param name the name of this holiday
	 */
	@Override
	public void setName(String name) {
		model.setName(name);
	}

	/**
	 * Sets the on date of this holiday.
	 *
	 * @param onDate the on date of this holiday
	 */
	@Override
	public void setOnDate(Date onDate) {
		model.setOnDate(onDate);
	}

	/**
	 * Sets the primary key of this holiday.
	 *
	 * @param primaryKey the primary key of this holiday
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	@Override
	protected HolidayWrapper wrap(Holiday holiday) {
		return new HolidayWrapper(holiday);
	}

}