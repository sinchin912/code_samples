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

package com.trantorinc.synergy.notice.core.model;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link ManagerForm}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ManagerForm
 * @generated
 */
public class ManagerFormWrapper
	extends BaseModelWrapper<ManagerForm>
	implements ManagerForm, ModelWrapper<ManagerForm> {

	public ManagerFormWrapper(ManagerForm managerForm) {
		super(managerForm);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("id", getId());
		attributes.put("exitId", getExitId());
		attributes.put("ticketNo", getTicketNo());
		attributes.put("ticketNoRemark", getTicketNoRemark());
		attributes.put("separationDocument", getSeparationDocument());
		attributes.put(
			"separationDocumentRemark", getSeparationDocumentRemark());
		attributes.put("updatedDate", getUpdatedDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long id = (Long)attributes.get("id");

		if (id != null) {
			setId(id);
		}

		Long exitId = (Long)attributes.get("exitId");

		if (exitId != null) {
			setExitId(exitId);
		}

		Integer ticketNo = (Integer)attributes.get("ticketNo");

		if (ticketNo != null) {
			setTicketNo(ticketNo);
		}

		String ticketNoRemark = (String)attributes.get("ticketNoRemark");

		if (ticketNoRemark != null) {
			setTicketNoRemark(ticketNoRemark);
		}

		Integer separationDocument = (Integer)attributes.get(
			"separationDocument");

		if (separationDocument != null) {
			setSeparationDocument(separationDocument);
		}

		String separationDocumentRemark = (String)attributes.get(
			"separationDocumentRemark");

		if (separationDocumentRemark != null) {
			setSeparationDocumentRemark(separationDocumentRemark);
		}

		Date updatedDate = (Date)attributes.get("updatedDate");

		if (updatedDate != null) {
			setUpdatedDate(updatedDate);
		}
	}

	@Override
	public ManagerForm cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the exit ID of this manager form.
	 *
	 * @return the exit ID of this manager form
	 */
	@Override
	public long getExitId() {
		return model.getExitId();
	}

	/**
	 * Returns the ID of this manager form.
	 *
	 * @return the ID of this manager form
	 */
	@Override
	public long getId() {
		return model.getId();
	}

	/**
	 * Returns the primary key of this manager form.
	 *
	 * @return the primary key of this manager form
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the separation document of this manager form.
	 *
	 * @return the separation document of this manager form
	 */
	@Override
	public int getSeparationDocument() {
		return model.getSeparationDocument();
	}

	/**
	 * Returns the separation document remark of this manager form.
	 *
	 * @return the separation document remark of this manager form
	 */
	@Override
	public String getSeparationDocumentRemark() {
		return model.getSeparationDocumentRemark();
	}

	/**
	 * Returns the ticket no of this manager form.
	 *
	 * @return the ticket no of this manager form
	 */
	@Override
	public int getTicketNo() {
		return model.getTicketNo();
	}

	/**
	 * Returns the ticket no remark of this manager form.
	 *
	 * @return the ticket no remark of this manager form
	 */
	@Override
	public String getTicketNoRemark() {
		return model.getTicketNoRemark();
	}

	/**
	 * Returns the updated date of this manager form.
	 *
	 * @return the updated date of this manager form
	 */
	@Override
	public Date getUpdatedDate() {
		return model.getUpdatedDate();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the exit ID of this manager form.
	 *
	 * @param exitId the exit ID of this manager form
	 */
	@Override
	public void setExitId(long exitId) {
		model.setExitId(exitId);
	}

	/**
	 * Sets the ID of this manager form.
	 *
	 * @param id the ID of this manager form
	 */
	@Override
	public void setId(long id) {
		model.setId(id);
	}

	/**
	 * Sets the primary key of this manager form.
	 *
	 * @param primaryKey the primary key of this manager form
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the separation document of this manager form.
	 *
	 * @param separationDocument the separation document of this manager form
	 */
	@Override
	public void setSeparationDocument(int separationDocument) {
		model.setSeparationDocument(separationDocument);
	}

	/**
	 * Sets the separation document remark of this manager form.
	 *
	 * @param separationDocumentRemark the separation document remark of this manager form
	 */
	@Override
	public void setSeparationDocumentRemark(String separationDocumentRemark) {
		model.setSeparationDocumentRemark(separationDocumentRemark);
	}

	/**
	 * Sets the ticket no of this manager form.
	 *
	 * @param ticketNo the ticket no of this manager form
	 */
	@Override
	public void setTicketNo(int ticketNo) {
		model.setTicketNo(ticketNo);
	}

	/**
	 * Sets the ticket no remark of this manager form.
	 *
	 * @param ticketNoRemark the ticket no remark of this manager form
	 */
	@Override
	public void setTicketNoRemark(String ticketNoRemark) {
		model.setTicketNoRemark(ticketNoRemark);
	}

	/**
	 * Sets the updated date of this manager form.
	 *
	 * @param updatedDate the updated date of this manager form
	 */
	@Override
	public void setUpdatedDate(Date updatedDate) {
		model.setUpdatedDate(updatedDate);
	}

	@Override
	protected ManagerFormWrapper wrap(ManagerForm managerForm) {
		return new ManagerFormWrapper(managerForm);
	}

}