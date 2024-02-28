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

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link Prize}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Prize
 * @generated
 */
public class PrizeWrapper
	extends BaseModelWrapper<Prize> implements ModelWrapper<Prize>, Prize {

	public PrizeWrapper(Prize prize) {
		super(prize);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("prizeId", getPrizeId());
		attributes.put("year", getYear());
		attributes.put("sequence", getSequence());
		attributes.put("description", getDescription());
		attributes.put("winner", getWinner());
		attributes.put("ticketId", getTicketId());
		attributes.put("surprise", isSurprise());
		attributes.put("fileId", getFileId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long prizeId = (Long)attributes.get("prizeId");

		if (prizeId != null) {
			setPrizeId(prizeId);
		}

		Integer year = (Integer)attributes.get("year");

		if (year != null) {
			setYear(year);
		}

		Integer sequence = (Integer)attributes.get("sequence");

		if (sequence != null) {
			setSequence(sequence);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		String winner = (String)attributes.get("winner");

		if (winner != null) {
			setWinner(winner);
		}

		Long ticketId = (Long)attributes.get("ticketId");

		if (ticketId != null) {
			setTicketId(ticketId);
		}

		Boolean surprise = (Boolean)attributes.get("surprise");

		if (surprise != null) {
			setSurprise(surprise);
		}

		String fileId = (String)attributes.get("fileId");

		if (fileId != null) {
			setFileId(fileId);
		}
	}

	@Override
	public Prize cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the description of this prize.
	 *
	 * @return the description of this prize
	 */
	@Override
	public String getDescription() {
		return model.getDescription();
	}

	/**
	 * Returns the file ID of this prize.
	 *
	 * @return the file ID of this prize
	 */
	@Override
	public String getFileId() {
		return model.getFileId();
	}

	/**
	 * Returns the primary key of this prize.
	 *
	 * @return the primary key of this prize
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the prize ID of this prize.
	 *
	 * @return the prize ID of this prize
	 */
	@Override
	public long getPrizeId() {
		return model.getPrizeId();
	}

	/**
	 * Returns the sequence of this prize.
	 *
	 * @return the sequence of this prize
	 */
	@Override
	public int getSequence() {
		return model.getSequence();
	}

	/**
	 * Returns the surprise of this prize.
	 *
	 * @return the surprise of this prize
	 */
	@Override
	public boolean getSurprise() {
		return model.getSurprise();
	}

	/**
	 * Returns the ticket ID of this prize.
	 *
	 * @return the ticket ID of this prize
	 */
	@Override
	public long getTicketId() {
		return model.getTicketId();
	}

	/**
	 * Returns the winner of this prize.
	 *
	 * @return the winner of this prize
	 */
	@Override
	public String getWinner() {
		return model.getWinner();
	}

	/**
	 * Returns the year of this prize.
	 *
	 * @return the year of this prize
	 */
	@Override
	public int getYear() {
		return model.getYear();
	}

	/**
	 * Returns <code>true</code> if this prize is surprise.
	 *
	 * @return <code>true</code> if this prize is surprise; <code>false</code> otherwise
	 */
	@Override
	public boolean isSurprise() {
		return model.isSurprise();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the description of this prize.
	 *
	 * @param description the description of this prize
	 */
	@Override
	public void setDescription(String description) {
		model.setDescription(description);
	}

	/**
	 * Sets the file ID of this prize.
	 *
	 * @param fileId the file ID of this prize
	 */
	@Override
	public void setFileId(String fileId) {
		model.setFileId(fileId);
	}

	/**
	 * Sets the primary key of this prize.
	 *
	 * @param primaryKey the primary key of this prize
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the prize ID of this prize.
	 *
	 * @param prizeId the prize ID of this prize
	 */
	@Override
	public void setPrizeId(long prizeId) {
		model.setPrizeId(prizeId);
	}

	/**
	 * Sets the sequence of this prize.
	 *
	 * @param sequence the sequence of this prize
	 */
	@Override
	public void setSequence(int sequence) {
		model.setSequence(sequence);
	}

	/**
	 * Sets whether this prize is surprise.
	 *
	 * @param surprise the surprise of this prize
	 */
	@Override
	public void setSurprise(boolean surprise) {
		model.setSurprise(surprise);
	}

	/**
	 * Sets the ticket ID of this prize.
	 *
	 * @param ticketId the ticket ID of this prize
	 */
	@Override
	public void setTicketId(long ticketId) {
		model.setTicketId(ticketId);
	}

	/**
	 * Sets the winner of this prize.
	 *
	 * @param winner the winner of this prize
	 */
	@Override
	public void setWinner(String winner) {
		model.setWinner(winner);
	}

	/**
	 * Sets the year of this prize.
	 *
	 * @param year the year of this prize
	 */
	@Override
	public void setYear(int year) {
		model.setYear(year);
	}

	@Override
	protected PrizeWrapper wrap(Prize prize) {
		return new PrizeWrapper(prize);
	}

}