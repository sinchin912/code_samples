package com.hbg.rp.search.dto;

import java.io.File;
import java.io.Serializable;

/**
 * DTO for Zendesk
 * @author robin.sharma
 *
 */
public class ZendeskDTO extends ZendeskGenericDTO implements Serializable {

	private static final long serialVersionUID = -8786223001540504122L;

	private File file;
	private long shipmentId;


	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public long getShipmentId() {
		return shipmentId;
	}

	public void setShipmentId(long shipmentId) {
		this.shipmentId = shipmentId;
	}

}
