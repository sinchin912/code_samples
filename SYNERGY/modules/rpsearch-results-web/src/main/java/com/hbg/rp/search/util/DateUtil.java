package com.hbg.rp.search.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import org.springframework.stereotype.Component;
/**
 * The com.hbg.rp.util.DateUtil is the implementation class of {@link IDateUtil}.
 * 
 * @author ravi.kumar
 */
@Component
public class DateUtil implements IDateUtil {
	private static final String DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
	private static final String DATE_FORMAT = "yyyy-MM-dd";
			
	/**
	 * Empty Constructor.
	 */
	public DateUtil() {}
	
	/**
	 * Converts an ISO 8601 formatted string to a java.util.Date object
	 * @param s
	 * @return
	 */
	@Override
	public  Date stringToDate(String s) {
		if (s == null)
			return null;
		DateFormat df = new SimpleDateFormat(DATE_TIME_FORMAT);
		try {
			return df.parse(s);
		} catch (ParseException e) {
			df = new SimpleDateFormat(DATE_FORMAT);
			try {
				return df.parse(s);
			} catch (ParseException e1) {
				logger.error(e1.getMessage(), e1);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		return null;
	}
	
	private static final Log logger = LogFactoryUtil.getLog(DateUtil.class);

}
