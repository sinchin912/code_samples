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
 * This class is a wrapper for {@link ItForm}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ItForm
 * @generated
 */
public class ItFormWrapper
	extends BaseModelWrapper<ItForm> implements ItForm, ModelWrapper<ItForm> {

	public ItFormWrapper(ItForm itForm) {
		super(itForm);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("id", getId());
		attributes.put("exitId", getExitId());
		attributes.put("ticketNo", getTicketNo());
		attributes.put("ticketNoRemark", getTicketNoRemark());
		attributes.put("mailPst", getMailPst());
		attributes.put("mailPstRemark", getMailPstRemark());
		attributes.put("downloadFolder", getDownloadFolder());
		attributes.put("downloadFolderRemark", getDownloadFolderRemark());
		attributes.put("documentFolder", getDocumentFolder());
		attributes.put("documentFolderRemark", getDocumentFolderRemark());
		attributes.put("otherData", getOtherData());
		attributes.put("otherDataRemark", getOtherDataRemark());
		attributes.put("googleDrive", getGoogleDrive());
		attributes.put("googleDriveRemark", getGoogleDriveRemark());
		attributes.put("others", getOthers());
		attributes.put("othersRemark", getOthersRemark());
		attributes.put("mailForwarding", getMailForwarding());
		attributes.put("mailForwardingRemark", getMailForwardingRemark());
		attributes.put("emailDisable", getEmailDisable());
		attributes.put("emailDisableRemark", getEmailDisableRemark());
		attributes.put("systemRecovered", getSystemRecovered());
		attributes.put("systemRecoveredRemark", getSystemRecoveredRemark());
		attributes.put("clientSystemRecovered", getClientSystemRecovered());
		attributes.put(
			"clientSystemRecoveredRemark", getClientSystemRecoveredRemark());
		attributes.put("accessCardDisable", getAccessCardDisable());
		attributes.put("accessCardDisableRemark", getAccessCardDisableRemark());
		attributes.put("separationDocument", getSeparationDocument());
		attributes.put(
			"separationDocumentRemark", getSeparationDocumentRemark());
		attributes.put("systemRecoveryAmt", getSystemRecoveryAmt());
		attributes.put("systemRecoveryAmtStatus", getSystemRecoveryAmtStatus());
		attributes.put("systemRecoveryApproved", isSystemRecoveryApproved());
		attributes.put("systemRecoveryComment", getSystemRecoveryComment());
		attributes.put("laptopRecoveryAmt", getLaptopRecoveryAmt());
		attributes.put("laptopRecoveryAmtStatus", getLaptopRecoveryAmtStatus());
		attributes.put("laptopRecoveryApproved", isLaptopRecoveryApproved());
		attributes.put("laptopRecoveryComment", getLaptopRecoveryComment());
		attributes.put(
			"communicationRecoveryAmt", getCommunicationRecoveryAmt());
		attributes.put(
			"communicationRecoveryStatus", getCommunicationRecoveryStatus());
		attributes.put(
			"communicationRecoveryApproved", isCommunicationRecoveryApproved());
		attributes.put(
			"communicationRecoveryComment", getCommunicationRecoveryComment());
		attributes.put("headphoneRecoveryAmt", getHeadphoneRecoveryAmt());
		attributes.put(
			"headphoneRecoveryAmtStatus", getHeadphoneRecoveryAmtStatus());
		attributes.put(
			"headphoneRecoveryApproved", isHeadphoneRecoveryApproved());
		attributes.put(
			"headphoneRecoveryComment", getHeadphoneRecoveryComment());
		attributes.put("laptopBagRecoveryAmt", getLaptopBagRecoveryAmt());
		attributes.put(
			"laptopBagRecoveryAmtStatus", getLaptopBagRecoveryAmtStatus());
		attributes.put(
			"laptopBagRecoveryApproved", isLaptopBagRecoveryApproved());
		attributes.put(
			"laptopBagRecoveryComment", getLaptopBagRecoveryComment());
		attributes.put("monitorRecoveryAmt", getMonitorRecoveryAmt());
		attributes.put(
			"monitorRecoveryAmtStatus", getMonitorRecoveryAmtStatus());
		attributes.put("monitorRecoveryApproved", isMonitorRecoveryApproved());
		attributes.put("monitorRecoveryComment", getMonitorRecoveryComment());
		attributes.put("chargerRecoveryAmt", getChargerRecoveryAmt());
		attributes.put(
			"chargerRecoveryAmtStatus", getChargerRecoveryAmtStatus());
		attributes.put("chargerRecoveryApproved", isChargerRecoveryApproved());
		attributes.put("chargerRecoveryComment", getChargerRecoveryComment());
		attributes.put("mouseRecoveryAmt", getMouseRecoveryAmt());
		attributes.put("mouseRecoveryAmtStatus", getMouseRecoveryAmtStatus());
		attributes.put("mouseRecoveryApproved", isMouseRecoveryApproved());
		attributes.put("mouseRecoveryComment", getMouseRecoveryComment());
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

		Integer mailPst = (Integer)attributes.get("mailPst");

		if (mailPst != null) {
			setMailPst(mailPst);
		}

		String mailPstRemark = (String)attributes.get("mailPstRemark");

		if (mailPstRemark != null) {
			setMailPstRemark(mailPstRemark);
		}

		Integer downloadFolder = (Integer)attributes.get("downloadFolder");

		if (downloadFolder != null) {
			setDownloadFolder(downloadFolder);
		}

		String downloadFolderRemark = (String)attributes.get(
			"downloadFolderRemark");

		if (downloadFolderRemark != null) {
			setDownloadFolderRemark(downloadFolderRemark);
		}

		Integer documentFolder = (Integer)attributes.get("documentFolder");

		if (documentFolder != null) {
			setDocumentFolder(documentFolder);
		}

		String documentFolderRemark = (String)attributes.get(
			"documentFolderRemark");

		if (documentFolderRemark != null) {
			setDocumentFolderRemark(documentFolderRemark);
		}

		Integer otherData = (Integer)attributes.get("otherData");

		if (otherData != null) {
			setOtherData(otherData);
		}

		String otherDataRemark = (String)attributes.get("otherDataRemark");

		if (otherDataRemark != null) {
			setOtherDataRemark(otherDataRemark);
		}

		Integer googleDrive = (Integer)attributes.get("googleDrive");

		if (googleDrive != null) {
			setGoogleDrive(googleDrive);
		}

		String googleDriveRemark = (String)attributes.get("googleDriveRemark");

		if (googleDriveRemark != null) {
			setGoogleDriveRemark(googleDriveRemark);
		}

		Integer others = (Integer)attributes.get("others");

		if (others != null) {
			setOthers(others);
		}

		String othersRemark = (String)attributes.get("othersRemark");

		if (othersRemark != null) {
			setOthersRemark(othersRemark);
		}

		Integer mailForwarding = (Integer)attributes.get("mailForwarding");

		if (mailForwarding != null) {
			setMailForwarding(mailForwarding);
		}

		String mailForwardingRemark = (String)attributes.get(
			"mailForwardingRemark");

		if (mailForwardingRemark != null) {
			setMailForwardingRemark(mailForwardingRemark);
		}

		Integer emailDisable = (Integer)attributes.get("emailDisable");

		if (emailDisable != null) {
			setEmailDisable(emailDisable);
		}

		String emailDisableRemark = (String)attributes.get(
			"emailDisableRemark");

		if (emailDisableRemark != null) {
			setEmailDisableRemark(emailDisableRemark);
		}

		Integer systemRecovered = (Integer)attributes.get("systemRecovered");

		if (systemRecovered != null) {
			setSystemRecovered(systemRecovered);
		}

		String systemRecoveredRemark = (String)attributes.get(
			"systemRecoveredRemark");

		if (systemRecoveredRemark != null) {
			setSystemRecoveredRemark(systemRecoveredRemark);
		}

		Integer clientSystemRecovered = (Integer)attributes.get(
			"clientSystemRecovered");

		if (clientSystemRecovered != null) {
			setClientSystemRecovered(clientSystemRecovered);
		}

		String clientSystemRecoveredRemark = (String)attributes.get(
			"clientSystemRecoveredRemark");

		if (clientSystemRecoveredRemark != null) {
			setClientSystemRecoveredRemark(clientSystemRecoveredRemark);
		}

		Integer accessCardDisable = (Integer)attributes.get(
			"accessCardDisable");

		if (accessCardDisable != null) {
			setAccessCardDisable(accessCardDisable);
		}

		String accessCardDisableRemark = (String)attributes.get(
			"accessCardDisableRemark");

		if (accessCardDisableRemark != null) {
			setAccessCardDisableRemark(accessCardDisableRemark);
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

		String systemRecoveryAmt = (String)attributes.get("systemRecoveryAmt");

		if (systemRecoveryAmt != null) {
			setSystemRecoveryAmt(systemRecoveryAmt);
		}

		Integer systemRecoveryAmtStatus = (Integer)attributes.get(
			"systemRecoveryAmtStatus");

		if (systemRecoveryAmtStatus != null) {
			setSystemRecoveryAmtStatus(systemRecoveryAmtStatus);
		}

		Boolean systemRecoveryApproved = (Boolean)attributes.get(
			"systemRecoveryApproved");

		if (systemRecoveryApproved != null) {
			setSystemRecoveryApproved(systemRecoveryApproved);
		}

		String systemRecoveryComment = (String)attributes.get(
			"systemRecoveryComment");

		if (systemRecoveryComment != null) {
			setSystemRecoveryComment(systemRecoveryComment);
		}

		String laptopRecoveryAmt = (String)attributes.get("laptopRecoveryAmt");

		if (laptopRecoveryAmt != null) {
			setLaptopRecoveryAmt(laptopRecoveryAmt);
		}

		Integer laptopRecoveryAmtStatus = (Integer)attributes.get(
			"laptopRecoveryAmtStatus");

		if (laptopRecoveryAmtStatus != null) {
			setLaptopRecoveryAmtStatus(laptopRecoveryAmtStatus);
		}

		Boolean laptopRecoveryApproved = (Boolean)attributes.get(
			"laptopRecoveryApproved");

		if (laptopRecoveryApproved != null) {
			setLaptopRecoveryApproved(laptopRecoveryApproved);
		}

		String laptopRecoveryComment = (String)attributes.get(
			"laptopRecoveryComment");

		if (laptopRecoveryComment != null) {
			setLaptopRecoveryComment(laptopRecoveryComment);
		}

		String communicationRecoveryAmt = (String)attributes.get(
			"communicationRecoveryAmt");

		if (communicationRecoveryAmt != null) {
			setCommunicationRecoveryAmt(communicationRecoveryAmt);
		}

		Integer communicationRecoveryStatus = (Integer)attributes.get(
			"communicationRecoveryStatus");

		if (communicationRecoveryStatus != null) {
			setCommunicationRecoveryStatus(communicationRecoveryStatus);
		}

		Boolean communicationRecoveryApproved = (Boolean)attributes.get(
			"communicationRecoveryApproved");

		if (communicationRecoveryApproved != null) {
			setCommunicationRecoveryApproved(communicationRecoveryApproved);
		}

		String communicationRecoveryComment = (String)attributes.get(
			"communicationRecoveryComment");

		if (communicationRecoveryComment != null) {
			setCommunicationRecoveryComment(communicationRecoveryComment);
		}

		String headphoneRecoveryAmt = (String)attributes.get(
			"headphoneRecoveryAmt");

		if (headphoneRecoveryAmt != null) {
			setHeadphoneRecoveryAmt(headphoneRecoveryAmt);
		}

		Integer headphoneRecoveryAmtStatus = (Integer)attributes.get(
			"headphoneRecoveryAmtStatus");

		if (headphoneRecoveryAmtStatus != null) {
			setHeadphoneRecoveryAmtStatus(headphoneRecoveryAmtStatus);
		}

		Boolean headphoneRecoveryApproved = (Boolean)attributes.get(
			"headphoneRecoveryApproved");

		if (headphoneRecoveryApproved != null) {
			setHeadphoneRecoveryApproved(headphoneRecoveryApproved);
		}

		String headphoneRecoveryComment = (String)attributes.get(
			"headphoneRecoveryComment");

		if (headphoneRecoveryComment != null) {
			setHeadphoneRecoveryComment(headphoneRecoveryComment);
		}

		String laptopBagRecoveryAmt = (String)attributes.get(
			"laptopBagRecoveryAmt");

		if (laptopBagRecoveryAmt != null) {
			setLaptopBagRecoveryAmt(laptopBagRecoveryAmt);
		}

		Integer laptopBagRecoveryAmtStatus = (Integer)attributes.get(
			"laptopBagRecoveryAmtStatus");

		if (laptopBagRecoveryAmtStatus != null) {
			setLaptopBagRecoveryAmtStatus(laptopBagRecoveryAmtStatus);
		}

		Boolean laptopBagRecoveryApproved = (Boolean)attributes.get(
			"laptopBagRecoveryApproved");

		if (laptopBagRecoveryApproved != null) {
			setLaptopBagRecoveryApproved(laptopBagRecoveryApproved);
		}

		String laptopBagRecoveryComment = (String)attributes.get(
			"laptopBagRecoveryComment");

		if (laptopBagRecoveryComment != null) {
			setLaptopBagRecoveryComment(laptopBagRecoveryComment);
		}

		String monitorRecoveryAmt = (String)attributes.get(
			"monitorRecoveryAmt");

		if (monitorRecoveryAmt != null) {
			setMonitorRecoveryAmt(monitorRecoveryAmt);
		}

		Integer monitorRecoveryAmtStatus = (Integer)attributes.get(
			"monitorRecoveryAmtStatus");

		if (monitorRecoveryAmtStatus != null) {
			setMonitorRecoveryAmtStatus(monitorRecoveryAmtStatus);
		}

		Boolean monitorRecoveryApproved = (Boolean)attributes.get(
			"monitorRecoveryApproved");

		if (monitorRecoveryApproved != null) {
			setMonitorRecoveryApproved(monitorRecoveryApproved);
		}

		String monitorRecoveryComment = (String)attributes.get(
			"monitorRecoveryComment");

		if (monitorRecoveryComment != null) {
			setMonitorRecoveryComment(monitorRecoveryComment);
		}

		String chargerRecoveryAmt = (String)attributes.get(
			"chargerRecoveryAmt");

		if (chargerRecoveryAmt != null) {
			setChargerRecoveryAmt(chargerRecoveryAmt);
		}

		Integer chargerRecoveryAmtStatus = (Integer)attributes.get(
			"chargerRecoveryAmtStatus");

		if (chargerRecoveryAmtStatus != null) {
			setChargerRecoveryAmtStatus(chargerRecoveryAmtStatus);
		}

		Boolean chargerRecoveryApproved = (Boolean)attributes.get(
			"chargerRecoveryApproved");

		if (chargerRecoveryApproved != null) {
			setChargerRecoveryApproved(chargerRecoveryApproved);
		}

		String chargerRecoveryComment = (String)attributes.get(
			"chargerRecoveryComment");

		if (chargerRecoveryComment != null) {
			setChargerRecoveryComment(chargerRecoveryComment);
		}

		String mouseRecoveryAmt = (String)attributes.get("mouseRecoveryAmt");

		if (mouseRecoveryAmt != null) {
			setMouseRecoveryAmt(mouseRecoveryAmt);
		}

		Integer mouseRecoveryAmtStatus = (Integer)attributes.get(
			"mouseRecoveryAmtStatus");

		if (mouseRecoveryAmtStatus != null) {
			setMouseRecoveryAmtStatus(mouseRecoveryAmtStatus);
		}

		Boolean mouseRecoveryApproved = (Boolean)attributes.get(
			"mouseRecoveryApproved");

		if (mouseRecoveryApproved != null) {
			setMouseRecoveryApproved(mouseRecoveryApproved);
		}

		String mouseRecoveryComment = (String)attributes.get(
			"mouseRecoveryComment");

		if (mouseRecoveryComment != null) {
			setMouseRecoveryComment(mouseRecoveryComment);
		}

		Date updatedDate = (Date)attributes.get("updatedDate");

		if (updatedDate != null) {
			setUpdatedDate(updatedDate);
		}
	}

	@Override
	public ItForm cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the access card disable of this it form.
	 *
	 * @return the access card disable of this it form
	 */
	@Override
	public int getAccessCardDisable() {
		return model.getAccessCardDisable();
	}

	/**
	 * Returns the access card disable remark of this it form.
	 *
	 * @return the access card disable remark of this it form
	 */
	@Override
	public String getAccessCardDisableRemark() {
		return model.getAccessCardDisableRemark();
	}

	/**
	 * Returns the charger recovery amt of this it form.
	 *
	 * @return the charger recovery amt of this it form
	 */
	@Override
	public String getChargerRecoveryAmt() {
		return model.getChargerRecoveryAmt();
	}

	/**
	 * Returns the charger recovery amt status of this it form.
	 *
	 * @return the charger recovery amt status of this it form
	 */
	@Override
	public int getChargerRecoveryAmtStatus() {
		return model.getChargerRecoveryAmtStatus();
	}

	/**
	 * Returns the charger recovery approved of this it form.
	 *
	 * @return the charger recovery approved of this it form
	 */
	@Override
	public boolean getChargerRecoveryApproved() {
		return model.getChargerRecoveryApproved();
	}

	/**
	 * Returns the charger recovery comment of this it form.
	 *
	 * @return the charger recovery comment of this it form
	 */
	@Override
	public String getChargerRecoveryComment() {
		return model.getChargerRecoveryComment();
	}

	/**
	 * Returns the client system recovered of this it form.
	 *
	 * @return the client system recovered of this it form
	 */
	@Override
	public int getClientSystemRecovered() {
		return model.getClientSystemRecovered();
	}

	/**
	 * Returns the client system recovered remark of this it form.
	 *
	 * @return the client system recovered remark of this it form
	 */
	@Override
	public String getClientSystemRecoveredRemark() {
		return model.getClientSystemRecoveredRemark();
	}

	/**
	 * Returns the communication recovery amt of this it form.
	 *
	 * @return the communication recovery amt of this it form
	 */
	@Override
	public String getCommunicationRecoveryAmt() {
		return model.getCommunicationRecoveryAmt();
	}

	/**
	 * Returns the communication recovery approved of this it form.
	 *
	 * @return the communication recovery approved of this it form
	 */
	@Override
	public boolean getCommunicationRecoveryApproved() {
		return model.getCommunicationRecoveryApproved();
	}

	/**
	 * Returns the communication recovery comment of this it form.
	 *
	 * @return the communication recovery comment of this it form
	 */
	@Override
	public String getCommunicationRecoveryComment() {
		return model.getCommunicationRecoveryComment();
	}

	/**
	 * Returns the communication recovery status of this it form.
	 *
	 * @return the communication recovery status of this it form
	 */
	@Override
	public int getCommunicationRecoveryStatus() {
		return model.getCommunicationRecoveryStatus();
	}

	/**
	 * Returns the document folder of this it form.
	 *
	 * @return the document folder of this it form
	 */
	@Override
	public int getDocumentFolder() {
		return model.getDocumentFolder();
	}

	/**
	 * Returns the document folder remark of this it form.
	 *
	 * @return the document folder remark of this it form
	 */
	@Override
	public String getDocumentFolderRemark() {
		return model.getDocumentFolderRemark();
	}

	/**
	 * Returns the download folder of this it form.
	 *
	 * @return the download folder of this it form
	 */
	@Override
	public int getDownloadFolder() {
		return model.getDownloadFolder();
	}

	/**
	 * Returns the download folder remark of this it form.
	 *
	 * @return the download folder remark of this it form
	 */
	@Override
	public String getDownloadFolderRemark() {
		return model.getDownloadFolderRemark();
	}

	/**
	 * Returns the email disable of this it form.
	 *
	 * @return the email disable of this it form
	 */
	@Override
	public int getEmailDisable() {
		return model.getEmailDisable();
	}

	/**
	 * Returns the email disable remark of this it form.
	 *
	 * @return the email disable remark of this it form
	 */
	@Override
	public String getEmailDisableRemark() {
		return model.getEmailDisableRemark();
	}

	/**
	 * Returns the exit ID of this it form.
	 *
	 * @return the exit ID of this it form
	 */
	@Override
	public long getExitId() {
		return model.getExitId();
	}

	/**
	 * Returns the google drive of this it form.
	 *
	 * @return the google drive of this it form
	 */
	@Override
	public int getGoogleDrive() {
		return model.getGoogleDrive();
	}

	/**
	 * Returns the google drive remark of this it form.
	 *
	 * @return the google drive remark of this it form
	 */
	@Override
	public String getGoogleDriveRemark() {
		return model.getGoogleDriveRemark();
	}

	/**
	 * Returns the headphone recovery amt of this it form.
	 *
	 * @return the headphone recovery amt of this it form
	 */
	@Override
	public String getHeadphoneRecoveryAmt() {
		return model.getHeadphoneRecoveryAmt();
	}

	/**
	 * Returns the headphone recovery amt status of this it form.
	 *
	 * @return the headphone recovery amt status of this it form
	 */
	@Override
	public int getHeadphoneRecoveryAmtStatus() {
		return model.getHeadphoneRecoveryAmtStatus();
	}

	/**
	 * Returns the headphone recovery approved of this it form.
	 *
	 * @return the headphone recovery approved of this it form
	 */
	@Override
	public boolean getHeadphoneRecoveryApproved() {
		return model.getHeadphoneRecoveryApproved();
	}

	/**
	 * Returns the headphone recovery comment of this it form.
	 *
	 * @return the headphone recovery comment of this it form
	 */
	@Override
	public String getHeadphoneRecoveryComment() {
		return model.getHeadphoneRecoveryComment();
	}

	/**
	 * Returns the ID of this it form.
	 *
	 * @return the ID of this it form
	 */
	@Override
	public long getId() {
		return model.getId();
	}

	/**
	 * Returns the laptop bag recovery amt of this it form.
	 *
	 * @return the laptop bag recovery amt of this it form
	 */
	@Override
	public String getLaptopBagRecoveryAmt() {
		return model.getLaptopBagRecoveryAmt();
	}

	/**
	 * Returns the laptop bag recovery amt status of this it form.
	 *
	 * @return the laptop bag recovery amt status of this it form
	 */
	@Override
	public int getLaptopBagRecoveryAmtStatus() {
		return model.getLaptopBagRecoveryAmtStatus();
	}

	/**
	 * Returns the laptop bag recovery approved of this it form.
	 *
	 * @return the laptop bag recovery approved of this it form
	 */
	@Override
	public boolean getLaptopBagRecoveryApproved() {
		return model.getLaptopBagRecoveryApproved();
	}

	/**
	 * Returns the laptop bag recovery comment of this it form.
	 *
	 * @return the laptop bag recovery comment of this it form
	 */
	@Override
	public String getLaptopBagRecoveryComment() {
		return model.getLaptopBagRecoveryComment();
	}

	/**
	 * Returns the laptop recovery amt of this it form.
	 *
	 * @return the laptop recovery amt of this it form
	 */
	@Override
	public String getLaptopRecoveryAmt() {
		return model.getLaptopRecoveryAmt();
	}

	/**
	 * Returns the laptop recovery amt status of this it form.
	 *
	 * @return the laptop recovery amt status of this it form
	 */
	@Override
	public int getLaptopRecoveryAmtStatus() {
		return model.getLaptopRecoveryAmtStatus();
	}

	/**
	 * Returns the laptop recovery approved of this it form.
	 *
	 * @return the laptop recovery approved of this it form
	 */
	@Override
	public boolean getLaptopRecoveryApproved() {
		return model.getLaptopRecoveryApproved();
	}

	/**
	 * Returns the laptop recovery comment of this it form.
	 *
	 * @return the laptop recovery comment of this it form
	 */
	@Override
	public String getLaptopRecoveryComment() {
		return model.getLaptopRecoveryComment();
	}

	/**
	 * Returns the mail forwarding of this it form.
	 *
	 * @return the mail forwarding of this it form
	 */
	@Override
	public int getMailForwarding() {
		return model.getMailForwarding();
	}

	/**
	 * Returns the mail forwarding remark of this it form.
	 *
	 * @return the mail forwarding remark of this it form
	 */
	@Override
	public String getMailForwardingRemark() {
		return model.getMailForwardingRemark();
	}

	/**
	 * Returns the mail pst of this it form.
	 *
	 * @return the mail pst of this it form
	 */
	@Override
	public int getMailPst() {
		return model.getMailPst();
	}

	/**
	 * Returns the mail pst remark of this it form.
	 *
	 * @return the mail pst remark of this it form
	 */
	@Override
	public String getMailPstRemark() {
		return model.getMailPstRemark();
	}

	/**
	 * Returns the monitor recovery amt of this it form.
	 *
	 * @return the monitor recovery amt of this it form
	 */
	@Override
	public String getMonitorRecoveryAmt() {
		return model.getMonitorRecoveryAmt();
	}

	/**
	 * Returns the monitor recovery amt status of this it form.
	 *
	 * @return the monitor recovery amt status of this it form
	 */
	@Override
	public int getMonitorRecoveryAmtStatus() {
		return model.getMonitorRecoveryAmtStatus();
	}

	/**
	 * Returns the monitor recovery approved of this it form.
	 *
	 * @return the monitor recovery approved of this it form
	 */
	@Override
	public boolean getMonitorRecoveryApproved() {
		return model.getMonitorRecoveryApproved();
	}

	/**
	 * Returns the monitor recovery comment of this it form.
	 *
	 * @return the monitor recovery comment of this it form
	 */
	@Override
	public String getMonitorRecoveryComment() {
		return model.getMonitorRecoveryComment();
	}

	/**
	 * Returns the mouse recovery amt of this it form.
	 *
	 * @return the mouse recovery amt of this it form
	 */
	@Override
	public String getMouseRecoveryAmt() {
		return model.getMouseRecoveryAmt();
	}

	/**
	 * Returns the mouse recovery amt status of this it form.
	 *
	 * @return the mouse recovery amt status of this it form
	 */
	@Override
	public int getMouseRecoveryAmtStatus() {
		return model.getMouseRecoveryAmtStatus();
	}

	/**
	 * Returns the mouse recovery approved of this it form.
	 *
	 * @return the mouse recovery approved of this it form
	 */
	@Override
	public boolean getMouseRecoveryApproved() {
		return model.getMouseRecoveryApproved();
	}

	/**
	 * Returns the mouse recovery comment of this it form.
	 *
	 * @return the mouse recovery comment of this it form
	 */
	@Override
	public String getMouseRecoveryComment() {
		return model.getMouseRecoveryComment();
	}

	/**
	 * Returns the other data of this it form.
	 *
	 * @return the other data of this it form
	 */
	@Override
	public int getOtherData() {
		return model.getOtherData();
	}

	/**
	 * Returns the other data remark of this it form.
	 *
	 * @return the other data remark of this it form
	 */
	@Override
	public String getOtherDataRemark() {
		return model.getOtherDataRemark();
	}

	/**
	 * Returns the others of this it form.
	 *
	 * @return the others of this it form
	 */
	@Override
	public int getOthers() {
		return model.getOthers();
	}

	/**
	 * Returns the others remark of this it form.
	 *
	 * @return the others remark of this it form
	 */
	@Override
	public String getOthersRemark() {
		return model.getOthersRemark();
	}

	/**
	 * Returns the primary key of this it form.
	 *
	 * @return the primary key of this it form
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the separation document of this it form.
	 *
	 * @return the separation document of this it form
	 */
	@Override
	public int getSeparationDocument() {
		return model.getSeparationDocument();
	}

	/**
	 * Returns the separation document remark of this it form.
	 *
	 * @return the separation document remark of this it form
	 */
	@Override
	public String getSeparationDocumentRemark() {
		return model.getSeparationDocumentRemark();
	}

	/**
	 * Returns the system recovered of this it form.
	 *
	 * @return the system recovered of this it form
	 */
	@Override
	public int getSystemRecovered() {
		return model.getSystemRecovered();
	}

	/**
	 * Returns the system recovered remark of this it form.
	 *
	 * @return the system recovered remark of this it form
	 */
	@Override
	public String getSystemRecoveredRemark() {
		return model.getSystemRecoveredRemark();
	}

	/**
	 * Returns the system recovery amt of this it form.
	 *
	 * @return the system recovery amt of this it form
	 */
	@Override
	public String getSystemRecoveryAmt() {
		return model.getSystemRecoveryAmt();
	}

	/**
	 * Returns the system recovery amt status of this it form.
	 *
	 * @return the system recovery amt status of this it form
	 */
	@Override
	public int getSystemRecoveryAmtStatus() {
		return model.getSystemRecoveryAmtStatus();
	}

	/**
	 * Returns the system recovery approved of this it form.
	 *
	 * @return the system recovery approved of this it form
	 */
	@Override
	public boolean getSystemRecoveryApproved() {
		return model.getSystemRecoveryApproved();
	}

	/**
	 * Returns the system recovery comment of this it form.
	 *
	 * @return the system recovery comment of this it form
	 */
	@Override
	public String getSystemRecoveryComment() {
		return model.getSystemRecoveryComment();
	}

	/**
	 * Returns the ticket no of this it form.
	 *
	 * @return the ticket no of this it form
	 */
	@Override
	public int getTicketNo() {
		return model.getTicketNo();
	}

	/**
	 * Returns the ticket no remark of this it form.
	 *
	 * @return the ticket no remark of this it form
	 */
	@Override
	public String getTicketNoRemark() {
		return model.getTicketNoRemark();
	}

	/**
	 * Returns the updated date of this it form.
	 *
	 * @return the updated date of this it form
	 */
	@Override
	public Date getUpdatedDate() {
		return model.getUpdatedDate();
	}

	/**
	 * Returns <code>true</code> if this it form is charger recovery approved.
	 *
	 * @return <code>true</code> if this it form is charger recovery approved; <code>false</code> otherwise
	 */
	@Override
	public boolean isChargerRecoveryApproved() {
		return model.isChargerRecoveryApproved();
	}

	/**
	 * Returns <code>true</code> if this it form is communication recovery approved.
	 *
	 * @return <code>true</code> if this it form is communication recovery approved; <code>false</code> otherwise
	 */
	@Override
	public boolean isCommunicationRecoveryApproved() {
		return model.isCommunicationRecoveryApproved();
	}

	/**
	 * Returns <code>true</code> if this it form is headphone recovery approved.
	 *
	 * @return <code>true</code> if this it form is headphone recovery approved; <code>false</code> otherwise
	 */
	@Override
	public boolean isHeadphoneRecoveryApproved() {
		return model.isHeadphoneRecoveryApproved();
	}

	/**
	 * Returns <code>true</code> if this it form is laptop bag recovery approved.
	 *
	 * @return <code>true</code> if this it form is laptop bag recovery approved; <code>false</code> otherwise
	 */
	@Override
	public boolean isLaptopBagRecoveryApproved() {
		return model.isLaptopBagRecoveryApproved();
	}

	/**
	 * Returns <code>true</code> if this it form is laptop recovery approved.
	 *
	 * @return <code>true</code> if this it form is laptop recovery approved; <code>false</code> otherwise
	 */
	@Override
	public boolean isLaptopRecoveryApproved() {
		return model.isLaptopRecoveryApproved();
	}

	/**
	 * Returns <code>true</code> if this it form is monitor recovery approved.
	 *
	 * @return <code>true</code> if this it form is monitor recovery approved; <code>false</code> otherwise
	 */
	@Override
	public boolean isMonitorRecoveryApproved() {
		return model.isMonitorRecoveryApproved();
	}

	/**
	 * Returns <code>true</code> if this it form is mouse recovery approved.
	 *
	 * @return <code>true</code> if this it form is mouse recovery approved; <code>false</code> otherwise
	 */
	@Override
	public boolean isMouseRecoveryApproved() {
		return model.isMouseRecoveryApproved();
	}

	/**
	 * Returns <code>true</code> if this it form is system recovery approved.
	 *
	 * @return <code>true</code> if this it form is system recovery approved; <code>false</code> otherwise
	 */
	@Override
	public boolean isSystemRecoveryApproved() {
		return model.isSystemRecoveryApproved();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the access card disable of this it form.
	 *
	 * @param accessCardDisable the access card disable of this it form
	 */
	@Override
	public void setAccessCardDisable(int accessCardDisable) {
		model.setAccessCardDisable(accessCardDisable);
	}

	/**
	 * Sets the access card disable remark of this it form.
	 *
	 * @param accessCardDisableRemark the access card disable remark of this it form
	 */
	@Override
	public void setAccessCardDisableRemark(String accessCardDisableRemark) {
		model.setAccessCardDisableRemark(accessCardDisableRemark);
	}

	/**
	 * Sets the charger recovery amt of this it form.
	 *
	 * @param chargerRecoveryAmt the charger recovery amt of this it form
	 */
	@Override
	public void setChargerRecoveryAmt(String chargerRecoveryAmt) {
		model.setChargerRecoveryAmt(chargerRecoveryAmt);
	}

	/**
	 * Sets the charger recovery amt status of this it form.
	 *
	 * @param chargerRecoveryAmtStatus the charger recovery amt status of this it form
	 */
	@Override
	public void setChargerRecoveryAmtStatus(int chargerRecoveryAmtStatus) {
		model.setChargerRecoveryAmtStatus(chargerRecoveryAmtStatus);
	}

	/**
	 * Sets whether this it form is charger recovery approved.
	 *
	 * @param chargerRecoveryApproved the charger recovery approved of this it form
	 */
	@Override
	public void setChargerRecoveryApproved(boolean chargerRecoveryApproved) {
		model.setChargerRecoveryApproved(chargerRecoveryApproved);
	}

	/**
	 * Sets the charger recovery comment of this it form.
	 *
	 * @param chargerRecoveryComment the charger recovery comment of this it form
	 */
	@Override
	public void setChargerRecoveryComment(String chargerRecoveryComment) {
		model.setChargerRecoveryComment(chargerRecoveryComment);
	}

	/**
	 * Sets the client system recovered of this it form.
	 *
	 * @param clientSystemRecovered the client system recovered of this it form
	 */
	@Override
	public void setClientSystemRecovered(int clientSystemRecovered) {
		model.setClientSystemRecovered(clientSystemRecovered);
	}

	/**
	 * Sets the client system recovered remark of this it form.
	 *
	 * @param clientSystemRecoveredRemark the client system recovered remark of this it form
	 */
	@Override
	public void setClientSystemRecoveredRemark(
		String clientSystemRecoveredRemark) {

		model.setClientSystemRecoveredRemark(clientSystemRecoveredRemark);
	}

	/**
	 * Sets the communication recovery amt of this it form.
	 *
	 * @param communicationRecoveryAmt the communication recovery amt of this it form
	 */
	@Override
	public void setCommunicationRecoveryAmt(String communicationRecoveryAmt) {
		model.setCommunicationRecoveryAmt(communicationRecoveryAmt);
	}

	/**
	 * Sets whether this it form is communication recovery approved.
	 *
	 * @param communicationRecoveryApproved the communication recovery approved of this it form
	 */
	@Override
	public void setCommunicationRecoveryApproved(
		boolean communicationRecoveryApproved) {

		model.setCommunicationRecoveryApproved(communicationRecoveryApproved);
	}

	/**
	 * Sets the communication recovery comment of this it form.
	 *
	 * @param communicationRecoveryComment the communication recovery comment of this it form
	 */
	@Override
	public void setCommunicationRecoveryComment(
		String communicationRecoveryComment) {

		model.setCommunicationRecoveryComment(communicationRecoveryComment);
	}

	/**
	 * Sets the communication recovery status of this it form.
	 *
	 * @param communicationRecoveryStatus the communication recovery status of this it form
	 */
	@Override
	public void setCommunicationRecoveryStatus(
		int communicationRecoveryStatus) {

		model.setCommunicationRecoveryStatus(communicationRecoveryStatus);
	}

	/**
	 * Sets the document folder of this it form.
	 *
	 * @param documentFolder the document folder of this it form
	 */
	@Override
	public void setDocumentFolder(int documentFolder) {
		model.setDocumentFolder(documentFolder);
	}

	/**
	 * Sets the document folder remark of this it form.
	 *
	 * @param documentFolderRemark the document folder remark of this it form
	 */
	@Override
	public void setDocumentFolderRemark(String documentFolderRemark) {
		model.setDocumentFolderRemark(documentFolderRemark);
	}

	/**
	 * Sets the download folder of this it form.
	 *
	 * @param downloadFolder the download folder of this it form
	 */
	@Override
	public void setDownloadFolder(int downloadFolder) {
		model.setDownloadFolder(downloadFolder);
	}

	/**
	 * Sets the download folder remark of this it form.
	 *
	 * @param downloadFolderRemark the download folder remark of this it form
	 */
	@Override
	public void setDownloadFolderRemark(String downloadFolderRemark) {
		model.setDownloadFolderRemark(downloadFolderRemark);
	}

	/**
	 * Sets the email disable of this it form.
	 *
	 * @param emailDisable the email disable of this it form
	 */
	@Override
	public void setEmailDisable(int emailDisable) {
		model.setEmailDisable(emailDisable);
	}

	/**
	 * Sets the email disable remark of this it form.
	 *
	 * @param emailDisableRemark the email disable remark of this it form
	 */
	@Override
	public void setEmailDisableRemark(String emailDisableRemark) {
		model.setEmailDisableRemark(emailDisableRemark);
	}

	/**
	 * Sets the exit ID of this it form.
	 *
	 * @param exitId the exit ID of this it form
	 */
	@Override
	public void setExitId(long exitId) {
		model.setExitId(exitId);
	}

	/**
	 * Sets the google drive of this it form.
	 *
	 * @param googleDrive the google drive of this it form
	 */
	@Override
	public void setGoogleDrive(int googleDrive) {
		model.setGoogleDrive(googleDrive);
	}

	/**
	 * Sets the google drive remark of this it form.
	 *
	 * @param googleDriveRemark the google drive remark of this it form
	 */
	@Override
	public void setGoogleDriveRemark(String googleDriveRemark) {
		model.setGoogleDriveRemark(googleDriveRemark);
	}

	/**
	 * Sets the headphone recovery amt of this it form.
	 *
	 * @param headphoneRecoveryAmt the headphone recovery amt of this it form
	 */
	@Override
	public void setHeadphoneRecoveryAmt(String headphoneRecoveryAmt) {
		model.setHeadphoneRecoveryAmt(headphoneRecoveryAmt);
	}

	/**
	 * Sets the headphone recovery amt status of this it form.
	 *
	 * @param headphoneRecoveryAmtStatus the headphone recovery amt status of this it form
	 */
	@Override
	public void setHeadphoneRecoveryAmtStatus(int headphoneRecoveryAmtStatus) {
		model.setHeadphoneRecoveryAmtStatus(headphoneRecoveryAmtStatus);
	}

	/**
	 * Sets whether this it form is headphone recovery approved.
	 *
	 * @param headphoneRecoveryApproved the headphone recovery approved of this it form
	 */
	@Override
	public void setHeadphoneRecoveryApproved(
		boolean headphoneRecoveryApproved) {

		model.setHeadphoneRecoveryApproved(headphoneRecoveryApproved);
	}

	/**
	 * Sets the headphone recovery comment of this it form.
	 *
	 * @param headphoneRecoveryComment the headphone recovery comment of this it form
	 */
	@Override
	public void setHeadphoneRecoveryComment(String headphoneRecoveryComment) {
		model.setHeadphoneRecoveryComment(headphoneRecoveryComment);
	}

	/**
	 * Sets the ID of this it form.
	 *
	 * @param id the ID of this it form
	 */
	@Override
	public void setId(long id) {
		model.setId(id);
	}

	/**
	 * Sets the laptop bag recovery amt of this it form.
	 *
	 * @param laptopBagRecoveryAmt the laptop bag recovery amt of this it form
	 */
	@Override
	public void setLaptopBagRecoveryAmt(String laptopBagRecoveryAmt) {
		model.setLaptopBagRecoveryAmt(laptopBagRecoveryAmt);
	}

	/**
	 * Sets the laptop bag recovery amt status of this it form.
	 *
	 * @param laptopBagRecoveryAmtStatus the laptop bag recovery amt status of this it form
	 */
	@Override
	public void setLaptopBagRecoveryAmtStatus(int laptopBagRecoveryAmtStatus) {
		model.setLaptopBagRecoveryAmtStatus(laptopBagRecoveryAmtStatus);
	}

	/**
	 * Sets whether this it form is laptop bag recovery approved.
	 *
	 * @param laptopBagRecoveryApproved the laptop bag recovery approved of this it form
	 */
	@Override
	public void setLaptopBagRecoveryApproved(
		boolean laptopBagRecoveryApproved) {

		model.setLaptopBagRecoveryApproved(laptopBagRecoveryApproved);
	}

	/**
	 * Sets the laptop bag recovery comment of this it form.
	 *
	 * @param laptopBagRecoveryComment the laptop bag recovery comment of this it form
	 */
	@Override
	public void setLaptopBagRecoveryComment(String laptopBagRecoveryComment) {
		model.setLaptopBagRecoveryComment(laptopBagRecoveryComment);
	}

	/**
	 * Sets the laptop recovery amt of this it form.
	 *
	 * @param laptopRecoveryAmt the laptop recovery amt of this it form
	 */
	@Override
	public void setLaptopRecoveryAmt(String laptopRecoveryAmt) {
		model.setLaptopRecoveryAmt(laptopRecoveryAmt);
	}

	/**
	 * Sets the laptop recovery amt status of this it form.
	 *
	 * @param laptopRecoveryAmtStatus the laptop recovery amt status of this it form
	 */
	@Override
	public void setLaptopRecoveryAmtStatus(int laptopRecoveryAmtStatus) {
		model.setLaptopRecoveryAmtStatus(laptopRecoveryAmtStatus);
	}

	/**
	 * Sets whether this it form is laptop recovery approved.
	 *
	 * @param laptopRecoveryApproved the laptop recovery approved of this it form
	 */
	@Override
	public void setLaptopRecoveryApproved(boolean laptopRecoveryApproved) {
		model.setLaptopRecoveryApproved(laptopRecoveryApproved);
	}

	/**
	 * Sets the laptop recovery comment of this it form.
	 *
	 * @param laptopRecoveryComment the laptop recovery comment of this it form
	 */
	@Override
	public void setLaptopRecoveryComment(String laptopRecoveryComment) {
		model.setLaptopRecoveryComment(laptopRecoveryComment);
	}

	/**
	 * Sets the mail forwarding of this it form.
	 *
	 * @param mailForwarding the mail forwarding of this it form
	 */
	@Override
	public void setMailForwarding(int mailForwarding) {
		model.setMailForwarding(mailForwarding);
	}

	/**
	 * Sets the mail forwarding remark of this it form.
	 *
	 * @param mailForwardingRemark the mail forwarding remark of this it form
	 */
	@Override
	public void setMailForwardingRemark(String mailForwardingRemark) {
		model.setMailForwardingRemark(mailForwardingRemark);
	}

	/**
	 * Sets the mail pst of this it form.
	 *
	 * @param mailPst the mail pst of this it form
	 */
	@Override
	public void setMailPst(int mailPst) {
		model.setMailPst(mailPst);
	}

	/**
	 * Sets the mail pst remark of this it form.
	 *
	 * @param mailPstRemark the mail pst remark of this it form
	 */
	@Override
	public void setMailPstRemark(String mailPstRemark) {
		model.setMailPstRemark(mailPstRemark);
	}

	/**
	 * Sets the monitor recovery amt of this it form.
	 *
	 * @param monitorRecoveryAmt the monitor recovery amt of this it form
	 */
	@Override
	public void setMonitorRecoveryAmt(String monitorRecoveryAmt) {
		model.setMonitorRecoveryAmt(monitorRecoveryAmt);
	}

	/**
	 * Sets the monitor recovery amt status of this it form.
	 *
	 * @param monitorRecoveryAmtStatus the monitor recovery amt status of this it form
	 */
	@Override
	public void setMonitorRecoveryAmtStatus(int monitorRecoveryAmtStatus) {
		model.setMonitorRecoveryAmtStatus(monitorRecoveryAmtStatus);
	}

	/**
	 * Sets whether this it form is monitor recovery approved.
	 *
	 * @param monitorRecoveryApproved the monitor recovery approved of this it form
	 */
	@Override
	public void setMonitorRecoveryApproved(boolean monitorRecoveryApproved) {
		model.setMonitorRecoveryApproved(monitorRecoveryApproved);
	}

	/**
	 * Sets the monitor recovery comment of this it form.
	 *
	 * @param monitorRecoveryComment the monitor recovery comment of this it form
	 */
	@Override
	public void setMonitorRecoveryComment(String monitorRecoveryComment) {
		model.setMonitorRecoveryComment(monitorRecoveryComment);
	}

	/**
	 * Sets the mouse recovery amt of this it form.
	 *
	 * @param mouseRecoveryAmt the mouse recovery amt of this it form
	 */
	@Override
	public void setMouseRecoveryAmt(String mouseRecoveryAmt) {
		model.setMouseRecoveryAmt(mouseRecoveryAmt);
	}

	/**
	 * Sets the mouse recovery amt status of this it form.
	 *
	 * @param mouseRecoveryAmtStatus the mouse recovery amt status of this it form
	 */
	@Override
	public void setMouseRecoveryAmtStatus(int mouseRecoveryAmtStatus) {
		model.setMouseRecoveryAmtStatus(mouseRecoveryAmtStatus);
	}

	/**
	 * Sets whether this it form is mouse recovery approved.
	 *
	 * @param mouseRecoveryApproved the mouse recovery approved of this it form
	 */
	@Override
	public void setMouseRecoveryApproved(boolean mouseRecoveryApproved) {
		model.setMouseRecoveryApproved(mouseRecoveryApproved);
	}

	/**
	 * Sets the mouse recovery comment of this it form.
	 *
	 * @param mouseRecoveryComment the mouse recovery comment of this it form
	 */
	@Override
	public void setMouseRecoveryComment(String mouseRecoveryComment) {
		model.setMouseRecoveryComment(mouseRecoveryComment);
	}

	/**
	 * Sets the other data of this it form.
	 *
	 * @param otherData the other data of this it form
	 */
	@Override
	public void setOtherData(int otherData) {
		model.setOtherData(otherData);
	}

	/**
	 * Sets the other data remark of this it form.
	 *
	 * @param otherDataRemark the other data remark of this it form
	 */
	@Override
	public void setOtherDataRemark(String otherDataRemark) {
		model.setOtherDataRemark(otherDataRemark);
	}

	/**
	 * Sets the others of this it form.
	 *
	 * @param others the others of this it form
	 */
	@Override
	public void setOthers(int others) {
		model.setOthers(others);
	}

	/**
	 * Sets the others remark of this it form.
	 *
	 * @param othersRemark the others remark of this it form
	 */
	@Override
	public void setOthersRemark(String othersRemark) {
		model.setOthersRemark(othersRemark);
	}

	/**
	 * Sets the primary key of this it form.
	 *
	 * @param primaryKey the primary key of this it form
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the separation document of this it form.
	 *
	 * @param separationDocument the separation document of this it form
	 */
	@Override
	public void setSeparationDocument(int separationDocument) {
		model.setSeparationDocument(separationDocument);
	}

	/**
	 * Sets the separation document remark of this it form.
	 *
	 * @param separationDocumentRemark the separation document remark of this it form
	 */
	@Override
	public void setSeparationDocumentRemark(String separationDocumentRemark) {
		model.setSeparationDocumentRemark(separationDocumentRemark);
	}

	/**
	 * Sets the system recovered of this it form.
	 *
	 * @param systemRecovered the system recovered of this it form
	 */
	@Override
	public void setSystemRecovered(int systemRecovered) {
		model.setSystemRecovered(systemRecovered);
	}

	/**
	 * Sets the system recovered remark of this it form.
	 *
	 * @param systemRecoveredRemark the system recovered remark of this it form
	 */
	@Override
	public void setSystemRecoveredRemark(String systemRecoveredRemark) {
		model.setSystemRecoveredRemark(systemRecoveredRemark);
	}

	/**
	 * Sets the system recovery amt of this it form.
	 *
	 * @param systemRecoveryAmt the system recovery amt of this it form
	 */
	@Override
	public void setSystemRecoveryAmt(String systemRecoveryAmt) {
		model.setSystemRecoveryAmt(systemRecoveryAmt);
	}

	/**
	 * Sets the system recovery amt status of this it form.
	 *
	 * @param systemRecoveryAmtStatus the system recovery amt status of this it form
	 */
	@Override
	public void setSystemRecoveryAmtStatus(int systemRecoveryAmtStatus) {
		model.setSystemRecoveryAmtStatus(systemRecoveryAmtStatus);
	}

	/**
	 * Sets whether this it form is system recovery approved.
	 *
	 * @param systemRecoveryApproved the system recovery approved of this it form
	 */
	@Override
	public void setSystemRecoveryApproved(boolean systemRecoveryApproved) {
		model.setSystemRecoveryApproved(systemRecoveryApproved);
	}

	/**
	 * Sets the system recovery comment of this it form.
	 *
	 * @param systemRecoveryComment the system recovery comment of this it form
	 */
	@Override
	public void setSystemRecoveryComment(String systemRecoveryComment) {
		model.setSystemRecoveryComment(systemRecoveryComment);
	}

	/**
	 * Sets the ticket no of this it form.
	 *
	 * @param ticketNo the ticket no of this it form
	 */
	@Override
	public void setTicketNo(int ticketNo) {
		model.setTicketNo(ticketNo);
	}

	/**
	 * Sets the ticket no remark of this it form.
	 *
	 * @param ticketNoRemark the ticket no remark of this it form
	 */
	@Override
	public void setTicketNoRemark(String ticketNoRemark) {
		model.setTicketNoRemark(ticketNoRemark);
	}

	/**
	 * Sets the updated date of this it form.
	 *
	 * @param updatedDate the updated date of this it form
	 */
	@Override
	public void setUpdatedDate(Date updatedDate) {
		model.setUpdatedDate(updatedDate);
	}

	@Override
	protected ItFormWrapper wrap(ItForm itForm) {
		return new ItFormWrapper(itForm);
	}

}