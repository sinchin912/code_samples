package com.hbg.rp.search.util;

import com.liferay.portal.kernel.exception.SystemException;

import java.util.Map;

/**
 * The com.hbg.rp.util.IInvoiceUtil is an interface for Invoice Util. 
 * 
 * @author ravi.kumar
 */
public interface IInvoiceUtil {

	/**
	 * Get prerequisite model.
	 * @return
	 * @throws SystemException
	 */
	Map<String, Object> getPrerequisitModel() throws SystemException;
	
}
