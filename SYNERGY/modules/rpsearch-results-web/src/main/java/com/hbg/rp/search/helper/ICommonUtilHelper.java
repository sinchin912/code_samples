package com.hbg.rp.search.helper;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * CommonUtilHelper interface.
 * 
 * @author ravi.kumar
 */
public interface ICommonUtilHelper {

	@SuppressWarnings({ "rawtypes" })
	Map getCriteriaMapFromEntrySet(Set<Entry<String, String[]>> entrySet);

}