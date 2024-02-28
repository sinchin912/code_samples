/**
 * 
 */
package com.hbg.rp.search.util;

import com.liferay.portal.kernel.json.JSONArray;

import java.util.List;

/**
 * The com.hbg.rp.cache.IGenericCacheHandler is the Generic Cache Handler k
 * 
 * @author ravi.kumar
 */
public interface IGenericCacheHandler {

	JSONArray getAccountList();

	List<String> getAllReportingGroups();

}
