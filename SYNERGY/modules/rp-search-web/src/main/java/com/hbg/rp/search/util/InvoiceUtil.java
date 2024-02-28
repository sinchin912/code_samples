package com.hbg.rp.search.util;

import com.hbg.rp.search.constants.ApplicationConstant;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * The com.hbg.rp.util.InvoiceUtil is the implementation of
 * {@link IInvoiceUtil}.
 * 
 * @author ravi.kumar
 */
@Component
public class InvoiceUtil implements IInvoiceUtil {

	private static final String PARAM_INVOICEDATA = "invoiceData";
	private static final String PARAM_INVOICELINES = "invoiceLines";
	private static final String ITEM_CODE = "invoice-itemcode";
	private static final String PARAM_INVOICE_SEARCH = "invoice-search-param";
	private static final String PARAM_INVOICE_TRANS_TYPE = "transactionopt";
	private static final String PARAM_INVOICE_CATEGORY = "invoice-category";
	private static final String PARAM_LABEL = "label";
	private static final List<String> INVOICE_COMMENT_TYPES_ALLOWED = Arrays.asList(
			"ALLCD", 
			"INV", 
			"PLINV", 
			"SSE");

	@Autowired
	private IPortalMappingsUtil portalMappingsUtil;
	
	
	/**
	 * Get prerequisite model for Financial Transaction Type
	 * 
	 * @return
	 * @throws SystemException
	 */
	@Override
	public Map<String, Object> getPrerequisitModel() throws SystemException {
		Map<String, Object> model = new HashMap<>();
		Map<String, String> transactionTypeMap = portalMappingsUtil.getFinancialTransactionMap();
		Set<String> transactions = new HashSet<>(transactionTypeMap.values());
		List<String> sortedTransactionsList = new ArrayList<>(transactions);
		Collections.sort(sortedTransactionsList);
		model.put(ApplicationConstant.PARAM_TRANSACTION_TYPE, sortedTransactionsList);
		return model;
	}

	
	private static final Log logger = LogFactoryUtil.getLog(InvoiceUtil.class);

}
