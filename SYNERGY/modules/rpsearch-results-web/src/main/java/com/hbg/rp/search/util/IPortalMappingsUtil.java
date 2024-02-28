package com.hbg.rp.search.util;

import java.util.Map;

/**
 * The com.hbg.rp.util.IPortalMappingsUtil is the interface for {@link PortalMappingsUtil}.
 * 
 * @author ravi.kumar
 */
public interface IPortalMappingsUtil {
	
	/**
	 * Provides the map of all pub statuses
	 * @return
	 */
	Map<String,String> getPubStatusData();
	
	/**
	 * Provides the map of Financial Transaction Types.
	 * @param
	 * @return
	 */
	Map<String, String> getFinancialTransactionMap();

	/**
	 * This method provides the status based on the reason code provided.
	 * @param reasonCode
	 */
	/* (non-Javadoc)
	 * @see com.hbg.rp.util.IPortalMappingsUtil#getStatus(java.lang.String)
	 */
	String getStatus(String reasonCode);

	/**
	 * This method provides the mapped line status based on the reason code provided.
	 * @param lineStatus
	 */
	String getMappedLineStatus(String lineStatus);
	
	/**
	 * This method provides the mapped address field value based on the field name provided.
	 * @param field
	 * @return
	 */
	String getAddressField(String field);
	
	/**
	 * This method provides the mapped address field value based on the field name provided.
	 * @param field
	 * @return
	 */
	Map<String, String> getHBGAddressMap();

	/**
	 * Provides the Financial Transaction Type based on Invoice Category.
	 * @param category
	 * @return
	 */
	String getFinancialTransactionType(String category);
	
	/**
	 * Provides to get all mappings 
	 * @return
	 */
	Map<String, Map<String, String>> getMappingsForExport();

	Map<String, String> getLineStatusMap();
	Map<String, String> getReasonCodeMap();
	
}