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

package com.trantorinc.synergy.skill.core.model;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link Certificate}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Certificate
 * @generated
 */
public class CertificateWrapper
	extends BaseModelWrapper<Certificate>
	implements Certificate, ModelWrapper<Certificate> {

	public CertificateWrapper(Certificate certificate) {
		super(certificate);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("certificateId", getCertificateId());
		attributes.put("ecode", getEcode());
		attributes.put("name", getName());
		attributes.put("category", getCategory());
		attributes.put("description", getDescription());
		attributes.put("fileId", getFileId());
		attributes.put("filename", getFilename());
		attributes.put("createdDate", getCreatedDate());
		attributes.put("expiryDate", getExpiryDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long certificateId = (Long)attributes.get("certificateId");

		if (certificateId != null) {
			setCertificateId(certificateId);
		}

		String ecode = (String)attributes.get("ecode");

		if (ecode != null) {
			setEcode(ecode);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String category = (String)attributes.get("category");

		if (category != null) {
			setCategory(category);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		String fileId = (String)attributes.get("fileId");

		if (fileId != null) {
			setFileId(fileId);
		}

		String filename = (String)attributes.get("filename");

		if (filename != null) {
			setFilename(filename);
		}

		Date createdDate = (Date)attributes.get("createdDate");

		if (createdDate != null) {
			setCreatedDate(createdDate);
		}

		Date expiryDate = (Date)attributes.get("expiryDate");

		if (expiryDate != null) {
			setExpiryDate(expiryDate);
		}
	}

	@Override
	public Certificate cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the category of this certificate.
	 *
	 * @return the category of this certificate
	 */
	@Override
	public String getCategory() {
		return model.getCategory();
	}

	/**
	 * Returns the certificate ID of this certificate.
	 *
	 * @return the certificate ID of this certificate
	 */
	@Override
	public long getCertificateId() {
		return model.getCertificateId();
	}

	/**
	 * Returns the created date of this certificate.
	 *
	 * @return the created date of this certificate
	 */
	@Override
	public Date getCreatedDate() {
		return model.getCreatedDate();
	}

	/**
	 * Returns the description of this certificate.
	 *
	 * @return the description of this certificate
	 */
	@Override
	public String getDescription() {
		return model.getDescription();
	}

	/**
	 * Returns the ecode of this certificate.
	 *
	 * @return the ecode of this certificate
	 */
	@Override
	public String getEcode() {
		return model.getEcode();
	}

	/**
	 * Returns the expiry date of this certificate.
	 *
	 * @return the expiry date of this certificate
	 */
	@Override
	public Date getExpiryDate() {
		return model.getExpiryDate();
	}

	/**
	 * Returns the file ID of this certificate.
	 *
	 * @return the file ID of this certificate
	 */
	@Override
	public String getFileId() {
		return model.getFileId();
	}

	/**
	 * Returns the filename of this certificate.
	 *
	 * @return the filename of this certificate
	 */
	@Override
	public String getFilename() {
		return model.getFilename();
	}

	/**
	 * Returns the name of this certificate.
	 *
	 * @return the name of this certificate
	 */
	@Override
	public String getName() {
		return model.getName();
	}

	/**
	 * Returns the primary key of this certificate.
	 *
	 * @return the primary key of this certificate
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
	 * Sets the category of this certificate.
	 *
	 * @param category the category of this certificate
	 */
	@Override
	public void setCategory(String category) {
		model.setCategory(category);
	}

	/**
	 * Sets the certificate ID of this certificate.
	 *
	 * @param certificateId the certificate ID of this certificate
	 */
	@Override
	public void setCertificateId(long certificateId) {
		model.setCertificateId(certificateId);
	}

	/**
	 * Sets the created date of this certificate.
	 *
	 * @param createdDate the created date of this certificate
	 */
	@Override
	public void setCreatedDate(Date createdDate) {
		model.setCreatedDate(createdDate);
	}

	/**
	 * Sets the description of this certificate.
	 *
	 * @param description the description of this certificate
	 */
	@Override
	public void setDescription(String description) {
		model.setDescription(description);
	}

	/**
	 * Sets the ecode of this certificate.
	 *
	 * @param ecode the ecode of this certificate
	 */
	@Override
	public void setEcode(String ecode) {
		model.setEcode(ecode);
	}

	/**
	 * Sets the expiry date of this certificate.
	 *
	 * @param expiryDate the expiry date of this certificate
	 */
	@Override
	public void setExpiryDate(Date expiryDate) {
		model.setExpiryDate(expiryDate);
	}

	/**
	 * Sets the file ID of this certificate.
	 *
	 * @param fileId the file ID of this certificate
	 */
	@Override
	public void setFileId(String fileId) {
		model.setFileId(fileId);
	}

	/**
	 * Sets the filename of this certificate.
	 *
	 * @param filename the filename of this certificate
	 */
	@Override
	public void setFilename(String filename) {
		model.setFilename(filename);
	}

	/**
	 * Sets the name of this certificate.
	 *
	 * @param name the name of this certificate
	 */
	@Override
	public void setName(String name) {
		model.setName(name);
	}

	/**
	 * Sets the primary key of this certificate.
	 *
	 * @param primaryKey the primary key of this certificate
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	@Override
	protected CertificateWrapper wrap(Certificate certificate) {
		return new CertificateWrapper(certificate);
	}

}