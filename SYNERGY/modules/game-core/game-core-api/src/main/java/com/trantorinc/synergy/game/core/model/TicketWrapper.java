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

package com.trantorinc.synergy.game.core.model;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link Ticket}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Ticket
 * @generated
 */
public class TicketWrapper
	extends BaseModelWrapper<Ticket> implements ModelWrapper<Ticket>, Ticket {

	public TicketWrapper(Ticket ticket) {
		super(ticket);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("ticketId", getTicketId());
		attributes.put("year", getYear());
		attributes.put("ecode", getEcode());
		attributes.put("ticketNumber", getTicketNumber());
		attributes.put("createDatetime", getCreateDatetime());
		attributes.put("draw", isDraw());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long ticketId = (Long)attributes.get("ticketId");

		if (ticketId != null) {
			setTicketId(ticketId);
		}

		Integer year = (Integer)attributes.get("year");

		if (year != null) {
			setYear(year);
		}

		String ecode = (String)attributes.get("ecode");

		if (ecode != null) {
			setEcode(ecode);
		}

		String ticketNumber = (String)attributes.get("ticketNumber");

		if (ticketNumber != null) {
			setTicketNumber(ticketNumber);
		}

		Date createDatetime = (Date)attributes.get("createDatetime");

		if (createDatetime != null) {
			setCreateDatetime(createDatetime);
		}

		Boolean draw = (Boolean)attributes.get("draw");

		if (draw != null) {
			setDraw(draw);
		}
	}

	@Override
	public Ticket cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the create datetime of this ticket.
	 *
	 * @return the create datetime of this ticket
	 */
	@Override
	public Date getCreateDatetime() {
		return model.getCreateDatetime();
	}

	/**
	 * Returns the draw of this ticket.
	 *
	 * @return the draw of this ticket
	 */
	@Override
	public boolean getDraw() {
		return model.getDraw();
	}

	/**
	 * Returns the ecode of this ticket.
	 *
	 * @return the ecode of this ticket
	 */
	@Override
	public String getEcode() {
		return model.getEcode();
	}

	/**
	 * Returns the primary key of this ticket.
	 *
	 * @return the primary key of this ticket
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the ticket ID of this ticket.
	 *
	 * @return the ticket ID of this ticket
	 */
	@Override
	public long getTicketId() {
		return model.getTicketId();
	}

	/**
	 * Returns the ticket number of this ticket.
	 *
	 * @return the ticket number of this ticket
	 */
	@Override
	public String getTicketNumber() {
		return model.getTicketNumber();
	}

	/**
	 * Returns the year of this ticket.
	 *
	 * @return the year of this ticket
	 */
	@Override
	public int getYear() {
		return model.getYear();
	}

	/**
	 * Returns <code>true</code> if this ticket is draw.
	 *
	 * @return <code>true</code> if this ticket is draw; <code>false</code> otherwise
	 */
	@Override
	public boolean isDraw() {
		return model.isDraw();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the create datetime of this ticket.
	 *
	 * @param createDatetime the create datetime of this ticket
	 */
	@Override
	public void setCreateDatetime(Date createDatetime) {
		model.setCreateDatetime(createDatetime);
	}

	/**
	 * Sets whether this ticket is draw.
	 *
	 * @param draw the draw of this ticket
	 */
	@Override
	public void setDraw(boolean draw) {
		model.setDraw(draw);
	}

	/**
	 * Sets the ecode of this ticket.
	 *
	 * @param ecode the ecode of this ticket
	 */
	@Override
	public void setEcode(String ecode) {
		model.setEcode(ecode);
	}

	/**
	 * Sets the primary key of this ticket.
	 *
	 * @param primaryKey the primary key of this ticket
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the ticket ID of this ticket.
	 *
	 * @param ticketId the ticket ID of this ticket
	 */
	@Override
	public void setTicketId(long ticketId) {
		model.setTicketId(ticketId);
	}

	/**
	 * Sets the ticket number of this ticket.
	 *
	 * @param ticketNumber the ticket number of this ticket
	 */
	@Override
	public void setTicketNumber(String ticketNumber) {
		model.setTicketNumber(ticketNumber);
	}

	/**
	 * Sets the year of this ticket.
	 *
	 * @param year the year of this ticket
	 */
	@Override
	public void setYear(int year) {
		model.setYear(year);
	}

	@Override
	protected TicketWrapper wrap(Ticket ticket) {
		return new TicketWrapper(ticket);
	}

}