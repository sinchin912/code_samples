package com.hbg.rp.search.util;

import java.util.Map;

import com.liferay.portal.kernel.exception.SystemException;

/**
 * @author ravi.kumar
 *
 */
public interface ICatalogUtil {

	/**
	 * Get prerequisite model.
	 * @return
	 * @throws SystemException
	 */
	Map<String, Object> getPrerequisitModel() throws SystemException;
	
}
