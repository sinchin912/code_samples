package com.hbg.rp.search.dto;

import java.io.Serializable;
import java.util.Map;

/**
 * @author ravi.kumar
 */
public class SearchOrderDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Map mapObj;

	public Map getMapObj() {
		return mapObj;
	}

	public void setMapObj(Map mapObj) {
		this.mapObj = mapObj;
	}
}