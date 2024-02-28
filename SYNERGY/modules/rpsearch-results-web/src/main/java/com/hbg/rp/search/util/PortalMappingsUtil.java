package com.hbg.rp.search.util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.hbg.rp.model.PortalMapping;
import com.hbg.rp.service.PortalMappingLocalServiceUtil;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * This class is responsible for loading the mapping data from table
 * 'portal_mapping' based on key 'LINE_STATUS_REASON_CODE'. As there may be
 * other mapping data stored inside the same table.
 * 
 * @author ravi.kumar
 */
@Component
public class PortalMappingsUtil implements IPortalMappingsUtil {

	private static final String EMPTY = "";
	private static final String MAPPING_REASON_CODE_STATUS = "LINE_STATUS_REASON_CODE";
	private static final String MAPPING_LINE_STATUS = "ORDER_LINE_STATUS";
	private static final String HBG_ADDRESS_FIELDS = "INVOICE_ADDRESS_FIELDS";
	private static final String MAPPING_FINANCIAL_TRANSACTION_TYPE = "FINANCIAL_TRANSACTION_TYPE";
	private static final String REPORTING_GROUP = "REPORTING_GROUP";
	private static final String FORMAT = "FORMAT";
	private static final String PUB_STATUS = "PUB_STATUS";
	private static boolean isMappingsPopulated = false;

	/*
	 * Please add any new key defined above into the KEYS list, if it's values needs
	 * to be fetched along with other values.
	 */
	private static final List<String> KEYS = Arrays.asList(MAPPING_REASON_CODE_STATUS, MAPPING_LINE_STATUS,
			HBG_ADDRESS_FIELDS, MAPPING_FINANCIAL_TRANSACTION_TYPE, REPORTING_GROUP, FORMAT, PUB_STATUS);

	private static final Map<String, Map<String, String>> mappings = new HashMap<>();

	/**
	 * Empty Constructor.
	 */
	public PortalMappingsUtil() {
		//default constructor
	}

	/**
	 * Init mappings post construct.
	 * 
	 * @throws SystemException
	 */
	private void initMappings() throws SystemException {
		for (String key : KEYS) {
			List<PortalMapping> list = PortalMappingLocalServiceUtil.findPortalMappingsByName(key);
			if (list != null && !list.isEmpty()) {
				Map<String, String> currentMapping;
				if (mappings.get(key) != null) {
					currentMapping = mappings.get(key);
				} else {
					currentMapping = new HashMap<>();
					mappings.put(key, currentMapping);
				}

				for (PortalMapping pMapping : list) {
					currentMapping.put(pMapping.getOrigValue().toLowerCase(), pMapping.getMappedValue());
				}
			}
		}
	}

	@Override
	public Map<String, String> getPubStatusData() {
		if (!isMappingsPopulated) {
			initMappings();
		}
		return mappings.get(PUB_STATUS);
	}

	@Override
	public Map<String, String> getFinancialTransactionMap() {
		if (!isMappingsPopulated) {
			initMappings();
		}
		return mappings.get(MAPPING_FINANCIAL_TRANSACTION_TYPE);
	}

	/**
	 * This method provides the status based on the reason code provided.
	 *
	 * @param reasonCode
	 * @see com.hbg.rp.search.util.IPortalMappingsUtil#getStatus(java.lang.String)
	 */
	@Override
	public String getStatus(String reasonCode) {
		if (!isMappingsPopulated) {
			initMappings();
		}
		if (reasonCode == null || "".equals(reasonCode.trim())) {
			return EMPTY;
		} else {
			reasonCode = reasonCode.trim();
		}

		String value = mappings.get(MAPPING_REASON_CODE_STATUS).get(reasonCode.toLowerCase());
		return value != null ? value : EMPTY;
	}

	/**
	 * This method provides the mapped line status based on the reason code
	 * provided.
	 *
	 * @param lineStatus
	 * @see com.hbg.rp.search.util.IPortalMappingsUtil#getMappedLineStatus(java.lang.String)
	 */
	@Override
	public String getMappedLineStatus(String lineStatus) {
		if (!isMappingsPopulated) {
			initMappings();
		}
		if (lineStatus == null || "".equals(lineStatus.trim())) {
			return EMPTY;
		} else {
			lineStatus = lineStatus.trim();
		}

		String value = mappings.get(MAPPING_LINE_STATUS).get(lineStatus.toLowerCase());
		return value != null ? value : EMPTY;
	}

	/**
	 *  Provides the map of address fields with mapped values.
	 * @return map
	 */

	@Override
	public Map<String, String> getHBGAddressMap() {
		if (!isMappingsPopulated) {
			initMappings();
		}
		return mappings.get(HBG_ADDRESS_FIELDS);
	}

	@Override
	public String getFinancialTransactionType(String category) {
		if (!isMappingsPopulated) {
			initMappings();
		}
		String searchStr = null != category ? category.toLowerCase() : "";
		return null != mappings.get(MAPPING_FINANCIAL_TRANSACTION_TYPE).get(searchStr)
				? mappings.get(MAPPING_FINANCIAL_TRANSACTION_TYPE).get(searchStr)
				: "";
	}
	@Override
	public String getAddressField(String field) {
		return null;
	}

	/**
	 * Method to return maps of line-statuses, format, publishers, reason-code versus corresponding display values
	 * @return map
	 */
	@Override
	public Map<String, Map<String, String>> getMappingsForExport(){
		return mappings;
	}


	/**
	 * Method to return maps of reason-code versus corresponding display values
	 * @return map
	 */
	@Override
	public Map<String, String> getReasonCodeMap() {
		if (!isMappingsPopulated) {
			initMappings();
		}
		return mappings.get(MAPPING_REASON_CODE_STATUS);
	}

	/**
	 * Method to return maps of line-statuses versus corresponding display values
	 * @return map
	 */
	@Override
	public Map<String, String> getLineStatusMap() {
		if (!isMappingsPopulated) {
			initMappings();
		}
		return mappings.get(MAPPING_LINE_STATUS);
	}

}
