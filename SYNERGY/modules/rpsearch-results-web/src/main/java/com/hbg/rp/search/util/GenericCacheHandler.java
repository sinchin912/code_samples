package com.hbg.rp.search.util;

import com.hbg.rp.service.BillToAccountLocalServiceUtil;
import com.hbg.rp.service.ProductLocalServiceUtil;
import com.liferay.portal.kernel.json.JSONArray;

import java.util.List;

import org.springframework.stereotype.Component;

/**
 * The com.hbg.rp.cache.GenericCacheHandler is the generic cache handler for
 * account list.
 * 
 * @author ravi.kumar
 */
@Component
public class GenericCacheHandler implements IGenericCacheHandler {

	/**
	 * Instantiates GenericCacheHandler.
	 */
	public GenericCacheHandler() {
	}

	/**
	 * Gets the list of accounts as stored via
	 * {@link #getAccountsForOrganisation()}.
	 * 
	 * @see com.hbg.rp.cache.IGenericCacheHandler#getAccountList()
	 */
	@Override
	public JSONArray getAccountList() {
		return BillToAccountLocalServiceUtil.getAccountsForOrganisation(null);
	}

	@Override
	public List<String> getAllReportingGroups() {
		return ProductLocalServiceUtil.getAllReportingGroups();
	}

}
