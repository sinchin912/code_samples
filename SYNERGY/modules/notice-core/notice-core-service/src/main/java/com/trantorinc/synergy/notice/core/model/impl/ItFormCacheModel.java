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

package com.trantorinc.synergy.notice.core.model.impl;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import com.trantorinc.synergy.notice.core.model.ItForm;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing ItForm in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ItFormCacheModel implements CacheModel<ItForm>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof ItFormCacheModel)) {
			return false;
		}

		ItFormCacheModel itFormCacheModel = (ItFormCacheModel)object;

		if (id == itFormCacheModel.id) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, id);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(123);

		sb.append("{id=");
		sb.append(id);
		sb.append(", exitId=");
		sb.append(exitId);
		sb.append(", ticketNo=");
		sb.append(ticketNo);
		sb.append(", ticketNoRemark=");
		sb.append(ticketNoRemark);
		sb.append(", mailPst=");
		sb.append(mailPst);
		sb.append(", mailPstRemark=");
		sb.append(mailPstRemark);
		sb.append(", downloadFolder=");
		sb.append(downloadFolder);
		sb.append(", downloadFolderRemark=");
		sb.append(downloadFolderRemark);
		sb.append(", documentFolder=");
		sb.append(documentFolder);
		sb.append(", documentFolderRemark=");
		sb.append(documentFolderRemark);
		sb.append(", otherData=");
		sb.append(otherData);
		sb.append(", otherDataRemark=");
		sb.append(otherDataRemark);
		sb.append(", googleDrive=");
		sb.append(googleDrive);
		sb.append(", googleDriveRemark=");
		sb.append(googleDriveRemark);
		sb.append(", others=");
		sb.append(others);
		sb.append(", othersRemark=");
		sb.append(othersRemark);
		sb.append(", mailForwarding=");
		sb.append(mailForwarding);
		sb.append(", mailForwardingRemark=");
		sb.append(mailForwardingRemark);
		sb.append(", emailDisable=");
		sb.append(emailDisable);
		sb.append(", emailDisableRemark=");
		sb.append(emailDisableRemark);
		sb.append(", systemRecovered=");
		sb.append(systemRecovered);
		sb.append(", systemRecoveredRemark=");
		sb.append(systemRecoveredRemark);
		sb.append(", clientSystemRecovered=");
		sb.append(clientSystemRecovered);
		sb.append(", clientSystemRecoveredRemark=");
		sb.append(clientSystemRecoveredRemark);
		sb.append(", accessCardDisable=");
		sb.append(accessCardDisable);
		sb.append(", accessCardDisableRemark=");
		sb.append(accessCardDisableRemark);
		sb.append(", separationDocument=");
		sb.append(separationDocument);
		sb.append(", separationDocumentRemark=");
		sb.append(separationDocumentRemark);
		sb.append(", systemRecoveryAmt=");
		sb.append(systemRecoveryAmt);
		sb.append(", systemRecoveryAmtStatus=");
		sb.append(systemRecoveryAmtStatus);
		sb.append(", systemRecoveryApproved=");
		sb.append(systemRecoveryApproved);
		sb.append(", systemRecoveryComment=");
		sb.append(systemRecoveryComment);
		sb.append(", laptopRecoveryAmt=");
		sb.append(laptopRecoveryAmt);
		sb.append(", laptopRecoveryAmtStatus=");
		sb.append(laptopRecoveryAmtStatus);
		sb.append(", laptopRecoveryApproved=");
		sb.append(laptopRecoveryApproved);
		sb.append(", laptopRecoveryComment=");
		sb.append(laptopRecoveryComment);
		sb.append(", communicationRecoveryAmt=");
		sb.append(communicationRecoveryAmt);
		sb.append(", communicationRecoveryStatus=");
		sb.append(communicationRecoveryStatus);
		sb.append(", communicationRecoveryApproved=");
		sb.append(communicationRecoveryApproved);
		sb.append(", communicationRecoveryComment=");
		sb.append(communicationRecoveryComment);
		sb.append(", headphoneRecoveryAmt=");
		sb.append(headphoneRecoveryAmt);
		sb.append(", headphoneRecoveryAmtStatus=");
		sb.append(headphoneRecoveryAmtStatus);
		sb.append(", headphoneRecoveryApproved=");
		sb.append(headphoneRecoveryApproved);
		sb.append(", headphoneRecoveryComment=");
		sb.append(headphoneRecoveryComment);
		sb.append(", laptopBagRecoveryAmt=");
		sb.append(laptopBagRecoveryAmt);
		sb.append(", laptopBagRecoveryAmtStatus=");
		sb.append(laptopBagRecoveryAmtStatus);
		sb.append(", laptopBagRecoveryApproved=");
		sb.append(laptopBagRecoveryApproved);
		sb.append(", laptopBagRecoveryComment=");
		sb.append(laptopBagRecoveryComment);
		sb.append(", monitorRecoveryAmt=");
		sb.append(monitorRecoveryAmt);
		sb.append(", monitorRecoveryAmtStatus=");
		sb.append(monitorRecoveryAmtStatus);
		sb.append(", monitorRecoveryApproved=");
		sb.append(monitorRecoveryApproved);
		sb.append(", monitorRecoveryComment=");
		sb.append(monitorRecoveryComment);
		sb.append(", chargerRecoveryAmt=");
		sb.append(chargerRecoveryAmt);
		sb.append(", chargerRecoveryAmtStatus=");
		sb.append(chargerRecoveryAmtStatus);
		sb.append(", chargerRecoveryApproved=");
		sb.append(chargerRecoveryApproved);
		sb.append(", chargerRecoveryComment=");
		sb.append(chargerRecoveryComment);
		sb.append(", mouseRecoveryAmt=");
		sb.append(mouseRecoveryAmt);
		sb.append(", mouseRecoveryAmtStatus=");
		sb.append(mouseRecoveryAmtStatus);
		sb.append(", mouseRecoveryApproved=");
		sb.append(mouseRecoveryApproved);
		sb.append(", mouseRecoveryComment=");
		sb.append(mouseRecoveryComment);
		sb.append(", updatedDate=");
		sb.append(updatedDate);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ItForm toEntityModel() {
		ItFormImpl itFormImpl = new ItFormImpl();

		itFormImpl.setId(id);
		itFormImpl.setExitId(exitId);
		itFormImpl.setTicketNo(ticketNo);

		if (ticketNoRemark == null) {
			itFormImpl.setTicketNoRemark("");
		}
		else {
			itFormImpl.setTicketNoRemark(ticketNoRemark);
		}

		itFormImpl.setMailPst(mailPst);

		if (mailPstRemark == null) {
			itFormImpl.setMailPstRemark("");
		}
		else {
			itFormImpl.setMailPstRemark(mailPstRemark);
		}

		itFormImpl.setDownloadFolder(downloadFolder);

		if (downloadFolderRemark == null) {
			itFormImpl.setDownloadFolderRemark("");
		}
		else {
			itFormImpl.setDownloadFolderRemark(downloadFolderRemark);
		}

		itFormImpl.setDocumentFolder(documentFolder);

		if (documentFolderRemark == null) {
			itFormImpl.setDocumentFolderRemark("");
		}
		else {
			itFormImpl.setDocumentFolderRemark(documentFolderRemark);
		}

		itFormImpl.setOtherData(otherData);

		if (otherDataRemark == null) {
			itFormImpl.setOtherDataRemark("");
		}
		else {
			itFormImpl.setOtherDataRemark(otherDataRemark);
		}

		itFormImpl.setGoogleDrive(googleDrive);

		if (googleDriveRemark == null) {
			itFormImpl.setGoogleDriveRemark("");
		}
		else {
			itFormImpl.setGoogleDriveRemark(googleDriveRemark);
		}

		itFormImpl.setOthers(others);

		if (othersRemark == null) {
			itFormImpl.setOthersRemark("");
		}
		else {
			itFormImpl.setOthersRemark(othersRemark);
		}

		itFormImpl.setMailForwarding(mailForwarding);

		if (mailForwardingRemark == null) {
			itFormImpl.setMailForwardingRemark("");
		}
		else {
			itFormImpl.setMailForwardingRemark(mailForwardingRemark);
		}

		itFormImpl.setEmailDisable(emailDisable);

		if (emailDisableRemark == null) {
			itFormImpl.setEmailDisableRemark("");
		}
		else {
			itFormImpl.setEmailDisableRemark(emailDisableRemark);
		}

		itFormImpl.setSystemRecovered(systemRecovered);

		if (systemRecoveredRemark == null) {
			itFormImpl.setSystemRecoveredRemark("");
		}
		else {
			itFormImpl.setSystemRecoveredRemark(systemRecoveredRemark);
		}

		itFormImpl.setClientSystemRecovered(clientSystemRecovered);

		if (clientSystemRecoveredRemark == null) {
			itFormImpl.setClientSystemRecoveredRemark("");
		}
		else {
			itFormImpl.setClientSystemRecoveredRemark(
				clientSystemRecoveredRemark);
		}

		itFormImpl.setAccessCardDisable(accessCardDisable);

		if (accessCardDisableRemark == null) {
			itFormImpl.setAccessCardDisableRemark("");
		}
		else {
			itFormImpl.setAccessCardDisableRemark(accessCardDisableRemark);
		}

		itFormImpl.setSeparationDocument(separationDocument);

		if (separationDocumentRemark == null) {
			itFormImpl.setSeparationDocumentRemark("");
		}
		else {
			itFormImpl.setSeparationDocumentRemark(separationDocumentRemark);
		}

		if (systemRecoveryAmt == null) {
			itFormImpl.setSystemRecoveryAmt("");
		}
		else {
			itFormImpl.setSystemRecoveryAmt(systemRecoveryAmt);
		}

		itFormImpl.setSystemRecoveryAmtStatus(systemRecoveryAmtStatus);
		itFormImpl.setSystemRecoveryApproved(systemRecoveryApproved);

		if (systemRecoveryComment == null) {
			itFormImpl.setSystemRecoveryComment("");
		}
		else {
			itFormImpl.setSystemRecoveryComment(systemRecoveryComment);
		}

		if (laptopRecoveryAmt == null) {
			itFormImpl.setLaptopRecoveryAmt("");
		}
		else {
			itFormImpl.setLaptopRecoveryAmt(laptopRecoveryAmt);
		}

		itFormImpl.setLaptopRecoveryAmtStatus(laptopRecoveryAmtStatus);
		itFormImpl.setLaptopRecoveryApproved(laptopRecoveryApproved);

		if (laptopRecoveryComment == null) {
			itFormImpl.setLaptopRecoveryComment("");
		}
		else {
			itFormImpl.setLaptopRecoveryComment(laptopRecoveryComment);
		}

		if (communicationRecoveryAmt == null) {
			itFormImpl.setCommunicationRecoveryAmt("");
		}
		else {
			itFormImpl.setCommunicationRecoveryAmt(communicationRecoveryAmt);
		}

		itFormImpl.setCommunicationRecoveryStatus(communicationRecoveryStatus);
		itFormImpl.setCommunicationRecoveryApproved(
			communicationRecoveryApproved);

		if (communicationRecoveryComment == null) {
			itFormImpl.setCommunicationRecoveryComment("");
		}
		else {
			itFormImpl.setCommunicationRecoveryComment(
				communicationRecoveryComment);
		}

		if (headphoneRecoveryAmt == null) {
			itFormImpl.setHeadphoneRecoveryAmt("");
		}
		else {
			itFormImpl.setHeadphoneRecoveryAmt(headphoneRecoveryAmt);
		}

		itFormImpl.setHeadphoneRecoveryAmtStatus(headphoneRecoveryAmtStatus);
		itFormImpl.setHeadphoneRecoveryApproved(headphoneRecoveryApproved);

		if (headphoneRecoveryComment == null) {
			itFormImpl.setHeadphoneRecoveryComment("");
		}
		else {
			itFormImpl.setHeadphoneRecoveryComment(headphoneRecoveryComment);
		}

		if (laptopBagRecoveryAmt == null) {
			itFormImpl.setLaptopBagRecoveryAmt("");
		}
		else {
			itFormImpl.setLaptopBagRecoveryAmt(laptopBagRecoveryAmt);
		}

		itFormImpl.setLaptopBagRecoveryAmtStatus(laptopBagRecoveryAmtStatus);
		itFormImpl.setLaptopBagRecoveryApproved(laptopBagRecoveryApproved);

		if (laptopBagRecoveryComment == null) {
			itFormImpl.setLaptopBagRecoveryComment("");
		}
		else {
			itFormImpl.setLaptopBagRecoveryComment(laptopBagRecoveryComment);
		}

		if (monitorRecoveryAmt == null) {
			itFormImpl.setMonitorRecoveryAmt("");
		}
		else {
			itFormImpl.setMonitorRecoveryAmt(monitorRecoveryAmt);
		}

		itFormImpl.setMonitorRecoveryAmtStatus(monitorRecoveryAmtStatus);
		itFormImpl.setMonitorRecoveryApproved(monitorRecoveryApproved);

		if (monitorRecoveryComment == null) {
			itFormImpl.setMonitorRecoveryComment("");
		}
		else {
			itFormImpl.setMonitorRecoveryComment(monitorRecoveryComment);
		}

		if (chargerRecoveryAmt == null) {
			itFormImpl.setChargerRecoveryAmt("");
		}
		else {
			itFormImpl.setChargerRecoveryAmt(chargerRecoveryAmt);
		}

		itFormImpl.setChargerRecoveryAmtStatus(chargerRecoveryAmtStatus);
		itFormImpl.setChargerRecoveryApproved(chargerRecoveryApproved);

		if (chargerRecoveryComment == null) {
			itFormImpl.setChargerRecoveryComment("");
		}
		else {
			itFormImpl.setChargerRecoveryComment(chargerRecoveryComment);
		}

		if (mouseRecoveryAmt == null) {
			itFormImpl.setMouseRecoveryAmt("");
		}
		else {
			itFormImpl.setMouseRecoveryAmt(mouseRecoveryAmt);
		}

		itFormImpl.setMouseRecoveryAmtStatus(mouseRecoveryAmtStatus);
		itFormImpl.setMouseRecoveryApproved(mouseRecoveryApproved);

		if (mouseRecoveryComment == null) {
			itFormImpl.setMouseRecoveryComment("");
		}
		else {
			itFormImpl.setMouseRecoveryComment(mouseRecoveryComment);
		}

		if (updatedDate == Long.MIN_VALUE) {
			itFormImpl.setUpdatedDate(null);
		}
		else {
			itFormImpl.setUpdatedDate(new Date(updatedDate));
		}

		itFormImpl.resetOriginalValues();

		return itFormImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		id = objectInput.readLong();

		exitId = objectInput.readLong();

		ticketNo = objectInput.readInt();
		ticketNoRemark = objectInput.readUTF();

		mailPst = objectInput.readInt();
		mailPstRemark = objectInput.readUTF();

		downloadFolder = objectInput.readInt();
		downloadFolderRemark = objectInput.readUTF();

		documentFolder = objectInput.readInt();
		documentFolderRemark = objectInput.readUTF();

		otherData = objectInput.readInt();
		otherDataRemark = objectInput.readUTF();

		googleDrive = objectInput.readInt();
		googleDriveRemark = objectInput.readUTF();

		others = objectInput.readInt();
		othersRemark = objectInput.readUTF();

		mailForwarding = objectInput.readInt();
		mailForwardingRemark = objectInput.readUTF();

		emailDisable = objectInput.readInt();
		emailDisableRemark = objectInput.readUTF();

		systemRecovered = objectInput.readInt();
		systemRecoveredRemark = objectInput.readUTF();

		clientSystemRecovered = objectInput.readInt();
		clientSystemRecoveredRemark = objectInput.readUTF();

		accessCardDisable = objectInput.readInt();
		accessCardDisableRemark = objectInput.readUTF();

		separationDocument = objectInput.readInt();
		separationDocumentRemark = objectInput.readUTF();
		systemRecoveryAmt = objectInput.readUTF();

		systemRecoveryAmtStatus = objectInput.readInt();

		systemRecoveryApproved = objectInput.readBoolean();
		systemRecoveryComment = objectInput.readUTF();
		laptopRecoveryAmt = objectInput.readUTF();

		laptopRecoveryAmtStatus = objectInput.readInt();

		laptopRecoveryApproved = objectInput.readBoolean();
		laptopRecoveryComment = objectInput.readUTF();
		communicationRecoveryAmt = objectInput.readUTF();

		communicationRecoveryStatus = objectInput.readInt();

		communicationRecoveryApproved = objectInput.readBoolean();
		communicationRecoveryComment = objectInput.readUTF();
		headphoneRecoveryAmt = objectInput.readUTF();

		headphoneRecoveryAmtStatus = objectInput.readInt();

		headphoneRecoveryApproved = objectInput.readBoolean();
		headphoneRecoveryComment = objectInput.readUTF();
		laptopBagRecoveryAmt = objectInput.readUTF();

		laptopBagRecoveryAmtStatus = objectInput.readInt();

		laptopBagRecoveryApproved = objectInput.readBoolean();
		laptopBagRecoveryComment = objectInput.readUTF();
		monitorRecoveryAmt = objectInput.readUTF();

		monitorRecoveryAmtStatus = objectInput.readInt();

		monitorRecoveryApproved = objectInput.readBoolean();
		monitorRecoveryComment = objectInput.readUTF();
		chargerRecoveryAmt = objectInput.readUTF();

		chargerRecoveryAmtStatus = objectInput.readInt();

		chargerRecoveryApproved = objectInput.readBoolean();
		chargerRecoveryComment = objectInput.readUTF();
		mouseRecoveryAmt = objectInput.readUTF();

		mouseRecoveryAmtStatus = objectInput.readInt();

		mouseRecoveryApproved = objectInput.readBoolean();
		mouseRecoveryComment = objectInput.readUTF();
		updatedDate = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(id);

		objectOutput.writeLong(exitId);

		objectOutput.writeInt(ticketNo);

		if (ticketNoRemark == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(ticketNoRemark);
		}

		objectOutput.writeInt(mailPst);

		if (mailPstRemark == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(mailPstRemark);
		}

		objectOutput.writeInt(downloadFolder);

		if (downloadFolderRemark == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(downloadFolderRemark);
		}

		objectOutput.writeInt(documentFolder);

		if (documentFolderRemark == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(documentFolderRemark);
		}

		objectOutput.writeInt(otherData);

		if (otherDataRemark == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(otherDataRemark);
		}

		objectOutput.writeInt(googleDrive);

		if (googleDriveRemark == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(googleDriveRemark);
		}

		objectOutput.writeInt(others);

		if (othersRemark == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(othersRemark);
		}

		objectOutput.writeInt(mailForwarding);

		if (mailForwardingRemark == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(mailForwardingRemark);
		}

		objectOutput.writeInt(emailDisable);

		if (emailDisableRemark == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(emailDisableRemark);
		}

		objectOutput.writeInt(systemRecovered);

		if (systemRecoveredRemark == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(systemRecoveredRemark);
		}

		objectOutput.writeInt(clientSystemRecovered);

		if (clientSystemRecoveredRemark == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(clientSystemRecoveredRemark);
		}

		objectOutput.writeInt(accessCardDisable);

		if (accessCardDisableRemark == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(accessCardDisableRemark);
		}

		objectOutput.writeInt(separationDocument);

		if (separationDocumentRemark == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(separationDocumentRemark);
		}

		if (systemRecoveryAmt == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(systemRecoveryAmt);
		}

		objectOutput.writeInt(systemRecoveryAmtStatus);

		objectOutput.writeBoolean(systemRecoveryApproved);

		if (systemRecoveryComment == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(systemRecoveryComment);
		}

		if (laptopRecoveryAmt == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(laptopRecoveryAmt);
		}

		objectOutput.writeInt(laptopRecoveryAmtStatus);

		objectOutput.writeBoolean(laptopRecoveryApproved);

		if (laptopRecoveryComment == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(laptopRecoveryComment);
		}

		if (communicationRecoveryAmt == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(communicationRecoveryAmt);
		}

		objectOutput.writeInt(communicationRecoveryStatus);

		objectOutput.writeBoolean(communicationRecoveryApproved);

		if (communicationRecoveryComment == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(communicationRecoveryComment);
		}

		if (headphoneRecoveryAmt == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(headphoneRecoveryAmt);
		}

		objectOutput.writeInt(headphoneRecoveryAmtStatus);

		objectOutput.writeBoolean(headphoneRecoveryApproved);

		if (headphoneRecoveryComment == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(headphoneRecoveryComment);
		}

		if (laptopBagRecoveryAmt == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(laptopBagRecoveryAmt);
		}

		objectOutput.writeInt(laptopBagRecoveryAmtStatus);

		objectOutput.writeBoolean(laptopBagRecoveryApproved);

		if (laptopBagRecoveryComment == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(laptopBagRecoveryComment);
		}

		if (monitorRecoveryAmt == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(monitorRecoveryAmt);
		}

		objectOutput.writeInt(monitorRecoveryAmtStatus);

		objectOutput.writeBoolean(monitorRecoveryApproved);

		if (monitorRecoveryComment == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(monitorRecoveryComment);
		}

		if (chargerRecoveryAmt == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(chargerRecoveryAmt);
		}

		objectOutput.writeInt(chargerRecoveryAmtStatus);

		objectOutput.writeBoolean(chargerRecoveryApproved);

		if (chargerRecoveryComment == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(chargerRecoveryComment);
		}

		if (mouseRecoveryAmt == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(mouseRecoveryAmt);
		}

		objectOutput.writeInt(mouseRecoveryAmtStatus);

		objectOutput.writeBoolean(mouseRecoveryApproved);

		if (mouseRecoveryComment == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(mouseRecoveryComment);
		}

		objectOutput.writeLong(updatedDate);
	}

	public long id;
	public long exitId;
	public int ticketNo;
	public String ticketNoRemark;
	public int mailPst;
	public String mailPstRemark;
	public int downloadFolder;
	public String downloadFolderRemark;
	public int documentFolder;
	public String documentFolderRemark;
	public int otherData;
	public String otherDataRemark;
	public int googleDrive;
	public String googleDriveRemark;
	public int others;
	public String othersRemark;
	public int mailForwarding;
	public String mailForwardingRemark;
	public int emailDisable;
	public String emailDisableRemark;
	public int systemRecovered;
	public String systemRecoveredRemark;
	public int clientSystemRecovered;
	public String clientSystemRecoveredRemark;
	public int accessCardDisable;
	public String accessCardDisableRemark;
	public int separationDocument;
	public String separationDocumentRemark;
	public String systemRecoveryAmt;
	public int systemRecoveryAmtStatus;
	public boolean systemRecoveryApproved;
	public String systemRecoveryComment;
	public String laptopRecoveryAmt;
	public int laptopRecoveryAmtStatus;
	public boolean laptopRecoveryApproved;
	public String laptopRecoveryComment;
	public String communicationRecoveryAmt;
	public int communicationRecoveryStatus;
	public boolean communicationRecoveryApproved;
	public String communicationRecoveryComment;
	public String headphoneRecoveryAmt;
	public int headphoneRecoveryAmtStatus;
	public boolean headphoneRecoveryApproved;
	public String headphoneRecoveryComment;
	public String laptopBagRecoveryAmt;
	public int laptopBagRecoveryAmtStatus;
	public boolean laptopBagRecoveryApproved;
	public String laptopBagRecoveryComment;
	public String monitorRecoveryAmt;
	public int monitorRecoveryAmtStatus;
	public boolean monitorRecoveryApproved;
	public String monitorRecoveryComment;
	public String chargerRecoveryAmt;
	public int chargerRecoveryAmtStatus;
	public boolean chargerRecoveryApproved;
	public String chargerRecoveryComment;
	public String mouseRecoveryAmt;
	public int mouseRecoveryAmtStatus;
	public boolean mouseRecoveryApproved;
	public String mouseRecoveryComment;
	public long updatedDate;

}