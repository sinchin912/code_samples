package com.hbg.rp.search.util;

import java.util.Date;

/**
 * The com.hbg.rp.util.IDateUtil is an interface for date utilities.
 * References {@link DateUtil}
 * 
 * @author ravi.kumar
 */
public interface IDateUtil {

	/**
	 * Converts an ISO 8601 formatted string to a java.util.Date object
	 * @param s Date in string format
	 * @return Date
	 */
	Date stringToDate(String s);

}