package com.hbg.rp.search.util;

import com.liferay.portal.kernel.exception.SystemException;

import java.util.Map;

/**
 * The com.hbg.rp.util.IOrderUtil is the interface for {@link OrderUtil}.
 * 
 * @author ravi.kumar
 */
public interface IOrderUtil {

	/**
	 * Get prerequisite model.
	 * @return
	 * @throws SystemException
	 */
	Map<String, Object> getPreRequisiteModel() throws SystemException;

}