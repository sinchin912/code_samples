/**
 * 
 */
package com.hbg.rp.search.exceptions;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author ravi.kumar
 *
 */
public class ShipmentTrackingException extends PortalException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2941672401987800460L;

	/**
	 * 
	 */
	public ShipmentTrackingException() {
		super();
	}

	/**
	 * @param msg
	 */
	public ShipmentTrackingException(String msg) {
		super(msg);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public ShipmentTrackingException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param msg
	 * @param cause
	 */
	public ShipmentTrackingException(String msg, Throwable cause) {
		super(msg, cause);
		// TODO Auto-generated constructor stub
	}

}
