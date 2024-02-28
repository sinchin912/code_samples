package com.hbg.rp.search.util;

import com.hbg.rp.search.constants.ApplicationConstant;
import com.hbg.rp.service.NrpPubstatusMappingLocalServiceUtil;
import com.hbg.rp.service.ProductLocalServiceUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * <p>
 * The com.hbg.rp.util.CatalogUtil is the implementation of
 * </p>
 * {@link ICatalogUtil}.
 * 
 * @author ravi.kumar
 *
 */
@Component
public class CatalogUtil implements ICatalogUtil {

	private static final String PARAM_CATALOG_DATA = "catalogData";
	private static final String PARAM_PUBSTATUS_DATA = "pubStatus";
	private static final String PARAM_FORMAT_DATA = "formats";

	@Autowired
	private IPortalMappingsUtil portalMappingsUtil;

	@Autowired
	private IGenericCacheHandler genericCacheHandler;

	@Override
	public Map<String, Object> getPrerequisitModel() throws SystemException {
		Map<String, Object> model = new HashMap<>();
		List<String> sortedReportingGroups = genericCacheHandler.getAllReportingGroups();
		Collections.sort(sortedReportingGroups);
		model.put(ApplicationConstant.PARAM_REPORTING_GROUPS, sortedReportingGroups);
		List<String> formats = ProductLocalServiceUtil.getFormats();
		model.put(PARAM_FORMAT_DATA, formats);

		Map<String, String> psMap = portalMappingsUtil.getPubStatusData();
		Map<String, String> messageMap = NrpPubstatusMappingLocalServiceUtil.getAllPubStatusMap();	
		Set<String> pubStatusKeys = psMap.keySet();
		Iterator<String> it = pubStatusKeys.iterator();
		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();
		while (it.hasNext()) {
			String key = it.next();
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
			jsonObject.put("code", key.toUpperCase());
			jsonObject.put("desc", messageMap.containsKey(key.toUpperCase()) ? messageMap.get(key.toUpperCase()) : psMap.get(key));
			jsonArray.put(jsonObject);
		}
		model.put(PARAM_PUBSTATUS_DATA, jsonArray);
		return model;
	}

	
	private static final Log logger = LogFactoryUtil.getLog(CatalogUtil.class);

}
