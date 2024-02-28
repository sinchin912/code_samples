/**
 * 
 */
package com.hbg.rp.search.helper;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import org.springframework.stereotype.Component;
/**
 * CommonUtilHelper class.
 * 
 * @author ravi.kumar
 */

@Component
public class CommonUtilHelper implements ICommonUtilHelper {
	
	/**
	 * Empty Constructor.
	 */
	public CommonUtilHelper() {
		logger.info("CommonUtilHelper() << ");
		logger.info("CommonUtilHelper() >> ");
	}

	
	
	/* (non-Javadoc)
	 * @see main.java.com.hbg.rp.util.ICommonUtilHelper#getCriteriaMapFromEntrySet(java.util.Set)
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Map getCriteriaMapFromEntrySet(Set<Entry<String, String[]>> entrySet) {
		logger.info("getCriteriaMapFromEntrySet() << ");
		
		Map criteria = new HashMap<>();
		if(entrySet == null) {
			return criteria;
		}
		
		for (Entry<String, String[]> entry : entrySet) {
			if(entry == null) {
			   continue;
			}
			
			String currentKey = entry.getKey();
			String currentValue = entry.getValue()[0];
			String currentKeyValue = currentValue != null ? currentValue.trim() : currentValue;
			
			criteria.put(currentKey, currentKeyValue);
		}
		
		logger.info("getCriteriaMapFromEntrySet() >> ");
		return criteria;
	} 
	
	private static final Log logger = LogFactoryUtil.getLog(CommonUtilHelper.class);

}
