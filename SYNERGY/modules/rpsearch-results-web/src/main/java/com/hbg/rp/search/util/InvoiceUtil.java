package com.hbg.rp.search.util;

import static com.hbg.rp.search.constants.ApplicationConstant.INV_INVOICE_LIMIT;
import static com.hbg.rp.search.constants.ApplicationConstant.INV_INVOICE_NBR;
import static com.hbg.rp.search.constants.ApplicationConstant.INV_INVOICE_ORDER_REQUIRED;
import static com.hbg.rp.search.constants.ApplicationConstant.INVALID_PDF_CODE;
import static com.hbg.rp.search.constants.ApplicationConstant.INVALID_PDF_MESSAGE;
import static com.hbg.rp.search.constants.ApplicationConstant.VALID_PDF_CODE;

import com.hbg.rp.model.InvoiceHeader;
import com.hbg.rp.model.InvoiceLine;
import com.hbg.rp.model.InvoicePrintData;
import com.hbg.rp.model.OrderHeader;
import com.hbg.rp.model.OrderLine;
import com.hbg.rp.model.Shipment;
import com.hbg.rp.model.SpecialOffer;
import com.hbg.rp.search.constants.ApplicationConstant;
import com.hbg.rp.search.model.InvoiceDTO;
import com.hbg.rp.search.model.InvoiceExportDTO;
import com.hbg.rp.search.model.InvoiceLinesDTO;
import com.hbg.rp.search.model.OrderDTO;
import com.hbg.rp.search.model.OrderExportDTO;
import com.hbg.rp.service.InvoiceHeaderLocalServiceUtil;
import com.hbg.rp.service.InvoiceLineLocalServiceUtil;
import com.hbg.rp.service.InvoicePrintDataLocalServiceUtil;
import com.hbg.rp.service.OrderHeaderLocalServiceUtil;
import com.hbg.rp.service.OrderLineLocalServiceUtil;
import com.hbg.rp.service.ShipmentLocalServiceUtil;
import com.hbg.rp.service.SpecialOfferLocalServiceUtil;
import com.liferay.portal.kernel.exception.SystemException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import javax.portlet.PortletRequest;
import javax.portlet.PortletSession;

import org.apache.commons.lang3.StringUtils;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hbg.conduent.QuoteClient;
import com.hbg.conduent.QuoteConfiguration;
import com.hbg.conduent.wsdl.ArrayOfSearchResult;
import com.hbg.conduent.wsdl.DataField;
import com.hbg.conduent.wsdl.GetDocumentsListResponse;
import com.hbg.conduent.wsdl.SResult;
import com.hbg.conduent.wsdl.SearchResult;

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

	@Autowired
	private IPortalMappingsUtil portalMappingsUtil;
	
	@Autowired
	private ICommonUtil commonUtil;
	
	@Autowired
	private IExportUtil exportUtil;
	
	@Autowired
	private IOrderUtil orderUtil;
	
	
	/**
	 * Get prerequisite model for Financial Transaction Type
	 * 
	 * @return
	 * @throws SystemException
	 */
	@Override
	public Map<String, Object> getPreRequisiteModel() throws SystemException {
		Map<String, Object> model = new HashMap<>();
		Map<String, String> transactionTypeMap = portalMappingsUtil.getFinancialTransactionMap();
		Set<String> transactions = new HashSet<>(transactionTypeMap.values());
		List<String> sortedTransactionsList = new ArrayList<>(transactions);
		Collections.sort(sortedTransactionsList);
		model.put(ApplicationConstant.PARAM_TRANSACTION_TYPE, sortedTransactionsList);
		return model;
	}

	@Override
	public List<Long> getInvoiceHeaders(List<Long> headerIds) throws SystemException {
		List<Long> invoiceHeaderKeys = null;
		if(headerIds != null){
			List<InvoiceHeader> invoiceHeaders = InvoiceHeaderLocalServiceUtil.getInvoiceHeaders(headerIds);
			invoiceHeaderKeys = new ArrayList<>();
			if(invoiceHeaders != null){
				for(InvoiceHeader invoiceHeader: invoiceHeaders){
					invoiceHeaderKeys.add(invoiceHeader.getInvoiceHeaderId());
				}
			}
		}
		return invoiceHeaderKeys;
	}
	
	/**
	 * Get the Invoices.
	 * 
	 * @param invoiceSearchCriteriaMap
	 *            Search criteria
	 **/
	@Override
	public Map<String, Object> getInvoices(Map<String, String> invoiceSearchCriteriaMap, long extRepId,boolean hasAllTerritories) throws SystemException {

		logger.info("getInvoices() <<");

		Map<String, Object> invoicesMap = new HashMap<>();

		/* Invoice Category Check */
		setInvoiceCategory(invoiceSearchCriteriaMap);
		long startTime = System.currentTimeMillis();
		invoiceSearchCriteriaMap.put(ApplicationConstant.PARAM_INVOICE_LIMIT, ApplicationConstant.PAGE_SIZE.toString());
		List<InvoiceHeader> invoiceHeaderList = InvoiceHeaderLocalServiceUtil.getInvoices(invoiceSearchCriteriaMap, extRepId,hasAllTerritories);

		invoicesMap.put(PARAM_INVOICEDATA, invoiceHeaderList);
		if (null != invoiceSearchCriteriaMap.get(ITEM_CODE) && !"".equals(invoiceSearchCriteriaMap.get(ITEM_CODE))) {
			List<Long> invoiceHeaderIds = new ArrayList<>();
			List<Long> invoiceNumbers = new ArrayList<>();
			Map<Long, Long> invoiceNumbersHeaderId = new HashMap<>();
			for (InvoiceHeader invoiceHeader : invoiceHeaderList) {
				invoiceHeaderIds.add(invoiceHeader.getInvoiceHeaderId());
				invoiceNumbers.add(invoiceHeader.getInvoiceNumber());
				invoiceNumbersHeaderId.put(invoiceHeader.getInvoiceHeaderId(),
					invoiceHeader.getInvoiceNumber());
			}
			
			List<InvoiceLine> invoiceLines = InvoiceLineLocalServiceUtil.getInvoiceLinesForInvoiceHeaders(
					invoiceHeaderIds, invoiceSearchCriteriaMap.get(ITEM_CODE), extRepId,hasAllTerritories);
			
			//List<InvoiceLine> invoiceLines = addLongTitle(defaultInvoiceLines); //NRP 2746 Release 1.6: Change Short Title to Title on site and reports

			/* Change statuses based on print data records. */
			List<InvoicePrintData> printDataRecords = InvoicePrintDataLocalServiceUtil
					.getInvoicePrintDataForInvoiceHeaders(invoiceNumbers,
							invoiceSearchCriteriaMap.get(ITEM_CODE),
							ApplicationConstant.INVOICE_PRINT_DATA_FIELD1_LINES);
			Map<String, String> statuses = new HashMap<>();
			for (InvoicePrintData invoicePrintData: printDataRecords) {
				String lineNumber = invoicePrintData.getField5();
				long invoiceNumber = invoicePrintData.getInvoiceNumber();
				String keyMap = invoiceNumber + lineNumber;
				statuses.put(keyMap, invoicePrintData.getField16());
			}
			for (InvoiceLine invoiceLine : invoiceLines) {
				Long invoiceNumber = invoiceNumbersHeaderId.get(invoiceLine.getInvoiceHeaderId());
				Long lineNumber = invoiceLine.getInvoiceLineNumber();
				String keyMap = invoiceNumber +""+ lineNumber;
				if ( !StringUtils.isEmpty(statuses.get(keyMap)) && 
						!invoiceLine.getLineStatus().equals(ApplicationConstant.INV_INVOICE_LINE_STATUS_FULFILLED) ) {
					invoiceLine.setLineStatus(statuses.get(keyMap));
				}
			}
			invoicesMap.put(PARAM_INVOICELINES, invoiceLines);
		}
		long endTime = System.currentTimeMillis();
		long delta = endTime - startTime;
		logger.info("getInvoices() >> times: " + delta + " ms");

		return invoicesMap;
	}
	
	private void setInvoiceCategory(Map<String, String> criteria) {
		String selectedTransType = criteria.get(PARAM_INVOICE_TRANS_TYPE);
		StringBuilder transactionTypeList = new StringBuilder();
		if (null != criteria.get(PARAM_INVOICE_SEARCH)
				&& "transactions".equalsIgnoreCase(criteria.get(PARAM_INVOICE_SEARCH)) && null != selectedTransType) {
			Map<String, String> transactionTypeMap = portalMappingsUtil.getFinancialTransactionMap();
			for (Entry<String, String> mapEntry : transactionTypeMap.entrySet()) {
				if (mapEntry.getValue().equalsIgnoreCase(selectedTransType)) {
					transactionTypeList.append("'" + mapEntry.getKey().toUpperCase() + "',");
				}
			}
			if (transactionTypeList.length() > 0) {
				criteria.put(PARAM_INVOICE_CATEGORY,
						transactionTypeList.substring(0, transactionTypeList.length() - 1));
			}
		}
	}
	
	/**
	 * This method fetch invoice detail data based on invoice number.
	 *
	 */
	@Override
	public InvoiceDTO getInvoiceDetailData(String invoiceNumber, PortletRequest request, long extRepId,boolean hasAllTerritories)
			throws SystemException {
		InvoiceDTO inDto = new InvoiceDTO();
		Map<String, String> criteria = new HashMap<>();
		criteria.put(INV_INVOICE_NBR, invoiceNumber);
		criteria.put(INV_INVOICE_LIMIT, "1"); // Added limit as 1.
		criteria.put(INV_INVOICE_ORDER_REQUIRED, "false"); // Order not required as limit is 1.

		addPermissions(criteria, request);
		List<InvoiceHeader> invoiceHeaders = InvoiceHeaderLocalServiceUtil.getInvoices(criteria,extRepId,hasAllTerritories); // query
		if (!invoiceHeaders.isEmpty()) {
			InvoiceHeader invoiceHeader = invoiceHeaders.get(0);
			inDto.setInvoiceHeader(invoiceHeader);

			/* Construct shipTo & billTo */
			inDto.setBillToAddress(
					constructAddress(invoiceHeader.getBillToAddressOne(), invoiceHeader.getBillToAddressTwo(),
							invoiceHeader.getBillToAddressThree(), invoiceHeader.getBillToAddressFour()));
			inDto.setShipToAddress(
					constructAddress(invoiceHeader.getShipToAddressOne(), invoiceHeader.getShipToAddressTwo(),
							invoiceHeader.getShipToAddressThree(), invoiceHeader.getShipToAddressFour()));
			inDto.setDestinationAddress(
					constructAddress(invoiceHeader.getDestinationAddress1(), invoiceHeader.getDestinationAddress2(),
							invoiceHeader.getDestinationAddress3(), invoiceHeader.getDestinationAddress4()));

			long invoiceHeaderId = invoiceHeader.getInvoiceHeaderId();
			List<InvoiceLine> unModifiableInvoiceLines = InvoiceLineLocalServiceUtil.findByinvoiceHeaderId(invoiceHeaderId,extRepId,hasAllTerritories); // query
			List<InvoiceLine> invoiceLines = new ArrayList<>(unModifiableInvoiceLines);
			long orderHeaderId = invoiceHeader.getOrderHeaderId();
			
			/*
			 * Get order & special offers only if orderHeaderId is not null.
			 */
			if (orderHeaderId > 0) {
				Map<String, String> criteriaOrder = new HashMap<>();
				long referenceNbr = invoiceHeader.getReferenceNumber();
				criteriaOrder.put(ApplicationConstant.PARAM_REFNO, String.valueOf(referenceNbr));
				addPermissions(criteriaOrder, request);
				List<OrderHeader> listOrders = OrderHeaderLocalServiceUtil.getOrderHeaders(criteriaOrder, 1, extRepId, hasAllTerritories); // query
				if (listOrders != null && !listOrders.isEmpty()) {
					OrderDTO orderDTO = new OrderDTO();
					orderDTO.setOrderHeader(listOrders.get(0));

					List<OrderLine> orderLines = OrderLineLocalServiceUtil.getOrderLinesByHeaderId(orderHeaderId,extRepId,hasAllTerritories);
					orderDTO.setOrderLines(orderLines);
					
					List<SpecialOffer> specialOffersForOrder = SpecialOfferLocalServiceUtil
							.getOffersByHeaderKey(orderHeaderId); // query
					orderDTO.setSpecialOffers(specialOffersForOrder);
					inDto.setOrderDTO(orderDTO);
				}

				Map<String, String> criteriaShipment = new HashMap<>();
				criteriaShipment.put(ApplicationConstant.PARAM_SEARCH_SHIP_INVOICE_NBR, invoiceNumber);
				criteriaShipment.put(ApplicationConstant.PARAM_SEARCH_SHIP_REFERENCE_NBR, String.valueOf(referenceNbr));
				addPermissions(criteriaShipment, request);
				@SuppressWarnings("unchecked")
				List<Shipment> shipmentData = (List<Shipment>) ShipmentLocalServiceUtil.getShipments(criteriaShipment,extRepId,hasAllTerritories)
					.get(ApplicationConstant.PARAM_SHIPMENT_LIST);
				
				inDto.setHasShipments(false);
				if (shipmentData != null && !shipmentData.isEmpty()) {
					inDto.setHasShipments(true);
				}
			}

			/* Fetch invoice-print-records based on invoice number */
			String invoiceNbr = String.valueOf(invoiceHeader.getInvoiceNumber());
			List<InvoicePrintData> invoicePrintRecords = InvoicePrintDataLocalServiceUtil
					.getInvoiceComments(invoiceNbr);
			List<String> comments = new ArrayList<>();
			Map<Integer, String> comments35Map = new HashMap<>();

			Map<String, InvoiceLinesDTO> invoiceLine40CustomValuesMap = new HashMap<>();
			List<InvoicePrintData> invoice40PrintDatas = new ArrayList<>(); 
			List<InvoiceLine> finalizedInvoiceLines = new ArrayList<>();
			/* Separation of records based on Field1 value. */
			getCustomMaps(invoicePrintRecords, comments35Map, invoice40PrintDatas, invoiceLine40CustomValuesMap);
			/* Sorting of comments. */
			comments.addAll(new TreeMap<>(comments35Map).values());
			inDto.setInvoiceComments(comments);
			
			/* Sorting of invoice lines. Nrp-1674 */
			final Map<String, List<String>> isbnToInvoicePrintMapping = new HashMap<>();
			for (InvoicePrintData invoicePrintData: invoice40PrintDatas) {
				String[] strArr = {invoicePrintData.getField2(), invoicePrintData.getField3()};
		        List<String> arr = new ArrayList<>(Arrays.asList(strArr));
				String lineNumber = invoicePrintData.getField5();
				String isbn = invoicePrintData.getField6();
				isbnToInvoicePrintMapping.put(lineNumber+isbn, arr);
			}
			Collections.sort(invoiceLines, new Comparator<InvoiceLine>() {
				@Override
				public int compare(InvoiceLine o1, InvoiceLine o2) {
					List<String> arr1 = isbnToInvoicePrintMapping.get(o1.getInvoiceLineNumber() + o1.getIsbn());
					List<String> arr2 = isbnToInvoicePrintMapping.get(o2.getInvoiceLineNumber() + o2.getIsbn());
					if (arr1 == null || arr2 == null || arr1.isEmpty()|| arr2.isEmpty()) {
						return 0;
					}
					// Compare first by Field2 i.e. Envelope.
					String x1 = arr1.get(0) != null ? arr1.get(0) : "0";
		            String x2 = arr2.get(0) != null ? arr2.get(0) : "0";
		            int sComp = Integer.valueOf(x1).compareTo(Integer.valueOf(x2));
		            if (sComp != 0) {
		               return sComp;
		            } 
					// Compare first by Field3 i.e. Record Sequence.
		            String str1 = arr1.get(1) != null ? arr1.get(1) : "0";
		            String str2 = arr2.get(1) != null ? arr2.get(1) : "0";
		            return Integer.valueOf(str1).compareTo(Integer.valueOf(str2));
				}
			});
			/* Change line status, modify ISBN, title, author and add bom entries */
			getFinalizedInvoiceLines(invoiceLines, invoice40PrintDatas,invoiceLine40CustomValuesMap, finalizedInvoiceLines);
			inDto.setInvoiceLines(finalizedInvoiceLines);
		}
		return inDto;
	}
	
	private void addPermissions(Map<String, String> criteria, PortletRequest request) {
		criteria.put("loggedUserId", commonUtil.getLoggedInUserId(request));
		criteria.put("hasAllAccounts", String.valueOf(request.getPortletSession()
				.getAttribute("LIFERAY_SHARED_user_all_accounts", PortletSession.APPLICATION_SCOPE)));
	}
	
	private void getCustomMaps(
			List<InvoicePrintData> invoicePrintRecords,
			Map<Integer, String> comments35Map, List<InvoicePrintData> invoice40PrintDatas, Map<String, InvoiceLinesDTO> invoiceLineCustomValuesMap) {
		for (InvoicePrintData invoicePrintData: invoicePrintRecords) {
			if (invoicePrintData.getField1().equals(ApplicationConstant.INVOICE_PRINT_DATA_FIELD1_COMMENTS)) {
				comments35Map.put(Integer.parseInt(invoicePrintData.getField3()), 
						invoicePrintData.getField5());
			} else if (invoicePrintData.getField1().equals(ApplicationConstant.INVOICE_PRINT_DATA_FIELD1_LINES) ) {
				String lineNumber = invoicePrintData.getField5();
				String isbn = invoicePrintData.getField6();
				InvoiceLinesDTO customInvoiceLineValue = new InvoiceLinesDTO();
				customInvoiceLineValue.setStatus(invoicePrintData.getField16());
				customInvoiceLineValue.setIsbn(invoicePrintData.getField7());		
				invoiceLineCustomValuesMap.put(lineNumber+isbn, customInvoiceLineValue);
				invoice40PrintDatas.add(invoicePrintData);
			}
		}
	}
	
	private void getFinalizedInvoiceLines(List<InvoiceLine> invoiceLines,  List<InvoicePrintData> invoice40PrintDatas, Map<String, InvoiceLinesDTO> invoiceLineCustomValuesMap, List<InvoiceLine> finalizedInvoiceLines) {
		for (InvoiceLine invoiceLine : invoiceLines) {
			String lineNumber = String.valueOf(invoiceLine.getInvoiceLineNumber());
			String isbn = invoiceLine.getIsbn();
			String keyMap = lineNumber+isbn;
			InvoiceLine originalInvoiceLine = (InvoiceLine)invoiceLine.clone();
			if ( invoiceLineCustomValuesMap.get(keyMap) != null) {
				if(!invoiceLine.getLineStatus().equals(ApplicationConstant.INV_INVOICE_LINE_STATUS_FULFILLED)) {
					invoiceLine.setLineStatus(invoiceLineCustomValuesMap.get(keyMap).getStatus());	
				}
				if(!invoiceLineCustomValuesMap.get(keyMap).getIsbn().trim().isEmpty() && invoiceLineCustomValuesMap.get(keyMap).getIsbn()!= null) {
					invoiceLine.setIsbn(isbn+"-"+invoiceLineCustomValuesMap.get(keyMap).getIsbn());// not working : \n , <br/> , &lt;br/&gt; , \n\r , \r\n
				}
			}
			finalizedInvoiceLines.add(invoiceLine);
			if( invoiceLine.getBomType().trim().equals(ApplicationConstant.INVOICE_LINE_BOM_TYPE)  && !invoice40PrintDatas.isEmpty()) {
				List<InvoiceLine> invoiceLineBomEntries = getBomEntriesForInvoiceLine(originalInvoiceLine, invoice40PrintDatas);
				finalizedInvoiceLines.addAll(invoiceLineBomEntries);
			}
		}
	}

	private List<InvoiceLine> getBomEntriesForInvoiceLine(InvoiceLine invoiceLine, List<InvoicePrintData> invoicePrintDatas) {
		List<InvoiceLine> invoiceLineBomEntries = new ArrayList<>();
		for(InvoicePrintData invoicePrintData : invoicePrintDatas){
			if(invoicePrintData.getField5().trim().equals(String.valueOf(invoiceLine.getInvoiceLineNumber())) && !invoicePrintData.getField6().trim().equals(invoiceLine.getIsbn().trim())){
				InvoiceLine bomEntry = (InvoiceLine)invoiceLine.clone();
				bomEntry.setLineStatus(invoicePrintData.getField16());
				bomEntry.setIsbn(invoicePrintData.getField6());
				bomEntry.setShortTitle(invoicePrintData.getField9());
				bomEntry.setShortAuthor(invoicePrintData.getField8());
				invoiceLineBomEntries.add(bomEntry);
			}
		}
		sortInvoiceLineOnISBN(invoiceLineBomEntries);
		return invoiceLineBomEntries;
	}
	
	private void sortInvoiceLineOnISBN(List<InvoiceLine> invoiceLineBomEntries) {
		if (!invoiceLineBomEntries.isEmpty()) {
			 Collections.sort(invoiceLineBomEntries, new Comparator<InvoiceLine>() {
			      @Override
			      public int compare(final InvoiceLine object1, final InvoiceLine object2) {
			          return object1.getIsbn().compareTo(object2.getIsbn());
			      }
			  });
			 }
	}

	private String constructAddress(String addr1, String addr2, String addr3, String addr4) {
		StringBuilder sb = new StringBuilder();
		addField(sb, addr1);
		addField(sb, addr2);
		addField(sb, addr3);
		addField(sb, addr4);
		String result = sb.toString();
		result = result.length() - result.lastIndexOf(", ") == 2 ? result.substring(0, result.length() - 2) : result;
		return result;
	}

	private void addField(StringBuilder sb, String field) {
		if (field != null && !field.trim().isEmpty()) { 
			sb.append(field + ", ");
		}
	}
	
	@Override
	public InvoiceExportDTO getInvoiceExportData(InvoiceDTO invoiceDto, Map<String, String> hBGAddressMap,Long extRepId,boolean hasAllTerritories) throws ParseException {
		InvoiceExportDTO exportDTO = new InvoiceExportDTO();
		boolean isInvoice =  false;
		List<String> shipmentInfo = new ArrayList<>();
		double totalNetCharge = 0.0;
		if(null != invoiceDto.getInvoiceHeader()){
			exportDTO.setInvoiceHeader(invoiceDto.getInvoiceHeader());
			
			if(invoiceDto.getInvoiceHeader().getReferenceNumber() > 0){
				exportDTO.setRefrenceNumber(invoiceDto.getInvoiceHeader().getReferenceNumber());
			} else {
				exportDTO.setRefrenceNumber(invoiceDto.getInvoiceHeader().getInvoiceNumber());
			}
			
			if(invoiceDto.getOrderDTO() != null && invoiceDto.getOrderDTO().getOrderHeader() != null){
				exportDTO.setPageHeader("Order "+invoiceDto.getInvoiceHeader().getReferenceNumber()+" > Invoices, Credits and Debits");
				exportDTO.setOrderHeader(invoiceDto.getOrderDTO().getOrderHeader());
				isInvoice = true;
			} else {	
				String transactionType = portalMappingsUtil.getFinancialTransactionType(invoiceDto.getInvoiceHeader().getInvoiceCategory());
				if("DBN".equalsIgnoreCase(invoiceDto.getInvoiceHeader().getInvoiceType()) 
						|| "CRN".equalsIgnoreCase(invoiceDto.getInvoiceHeader().getInvoiceType())) {
					transactionType = transactionType.concat(" Memo");
				} else {
					isInvoice = true;
				}
				exportDTO.setPageHeader(transactionType +" "+invoiceDto.getInvoiceHeader().getInvoiceNumber());
			}
			
			String shipTo = "";
			if(invoiceDto.getInvoiceHeader().getDestinationName() != null && !invoiceDto.getInvoiceHeader().getDestinationName().trim().isEmpty()){
				shipTo = invoiceDto.getInvoiceHeader().getDestinationName() + "\n" +invoiceDto.getDestinationAddress()+ "\n"+ invoiceDto.getInvoiceHeader().getDestinationCity()+ "\n"+invoiceDto.getInvoiceHeader().getDestinationState()+ "\n"+invoiceDto.getInvoiceHeader().getDestinationZip();
			} else if(invoiceDto.getInvoiceHeader().getShipToName() != null && !invoiceDto.getInvoiceHeader().getShipToName().trim().isEmpty()){
				shipTo = invoiceDto.getInvoiceHeader().getShipToName() + "\n" +invoiceDto.getShipToAddress()+ "\n"+ invoiceDto.getInvoiceHeader().getShipToCity()+ "\n"+invoiceDto.getInvoiceHeader().getShipToState()+ "\n"+invoiceDto.getInvoiceHeader().getShipToZip();
			}
			shipmentInfo.add(shipTo);
			
			String billTo = "";
			if(invoiceDto.getInvoiceHeader().getBillToName() != null && !invoiceDto.getInvoiceHeader().getBillToName().trim().isEmpty()){
				billTo = invoiceDto.getInvoiceHeader().getBillToName() + "\n" +invoiceDto.getBillToAddress()+ "\n"+ invoiceDto.getInvoiceHeader().getBillToCity()+ "\n"+invoiceDto.getInvoiceHeader().getBillToState()+ "\n"+invoiceDto.getInvoiceHeader().getBillToZip();
			}
			shipmentInfo.add(billTo);
			String promoCode = "N/A";
			if(invoiceDto.getInvoiceHeader().getOfferCode() != null && !invoiceDto.getInvoiceHeader().getOfferCode().trim().isEmpty()){
				promoCode =invoiceDto.getInvoiceHeader().getOfferCode();
			}
			shipmentInfo.add(promoCode);
			exportDTO.setInvoiceShipmentInfo(shipmentInfo);
			
			if(isInvoice){
				List<String> footerSummary = new ArrayList<>();
				footerSummary.add(exportUtil.formatDate(new SimpleDateFormat("yyyy-MM-dd").parse(invoiceDto.getInvoiceHeader().getInvoiceDate())));
				if(extRepId > 0) {
					footerSummary.add(ApplicationConstant.ENCRYPTED_AMOUNT);
					footerSummary.add(ApplicationConstant.ENCRYPTED_AMOUNT);
					footerSummary.add(ApplicationConstant.ENCRYPTED_AMOUNT);
				}else {
					footerSummary.add(exportUtil.formatCurrency(invoiceDto.getInvoiceHeader().getTotalAmt()));
					footerSummary.add(exportUtil.formatCurrency(invoiceDto.getInvoiceHeader().getPrepaidAmt()));
					footerSummary.add(exportUtil.formatCurrency(invoiceDto.getInvoiceHeader().getTotalAmt() - invoiceDto.getInvoiceHeader().getPrepaidAmt()));
				}
				if(null != invoiceDto.getInvoiceHeader().getTermCode() && !invoiceDto.getInvoiceHeader().getTermCode().isEmpty() && !"0".equals(invoiceDto.getInvoiceHeader().getTermCode().trim()))
					footerSummary.add("NET "+ invoiceDto.getInvoiceHeader().getTermCode()+ " Days");
				else
					footerSummary.add("");
				footerSummary.add(hBGAddressMap.get("hachette_address_name") + "\n"+hBGAddressMap.get("hachette_address_po") + "\n" +hBGAddressMap.get("hachette_address_1") + "\n"+hBGAddressMap.get("hachette_address_2") );
				footerSummary.add(
						 "Customer Financial Services: "+hBGAddressMap.get("customer_financial_service")+ "\n"
						+"Customer Service: "+ hBGAddressMap.get("customer_service")+ "\n"
						+"Email: "+ hBGAddressMap.get("cs_email"));
				footerSummary.add(hBGAddressMap.get("duns"));
				footerSummary.add(hBGAddressMap.get("tin"));			
				exportDTO.setFooterSummary(footerSummary);
			}
			
			OrderDTO orderDTO = invoiceDto.getOrderDTO();
			orderUtil.calculateOrderDetail(orderDTO);
			exportDTO.setOrderExportDTO(new OrderExportDTO(orderDTO));
			String currCode = invoiceDto.getInvoiceHeader() != null ? invoiceDto.getInvoiceHeader().getCurrCode() : ""; 
			if ("CAD".equalsIgnoreCase(currCode)) {
				exportDTO.setCurrencyText("Canadian Dollars");
			} else if("USD".equalsIgnoreCase(currCode)) {
				exportDTO.setCurrencyText("US Dollars");
			} else{
				exportDTO.setCurrencyText("");
			}
		}
		
		exportDTO.setRelatedTransactions(new ArrayList<>());
		if(null != invoiceDto.getInvoiceLines()){
			exportDTO.setInvoiceLines(invoiceDto.getInvoiceLines());
			long totalQuantity = 0;
			for(InvoiceLine invoiceLine : invoiceDto.getInvoiceLines()){
				if(ApplicationConstant.INV_INVOICE_LINE_STATUS_FULFILLED.equals(invoiceLine.getLineStatus())){
					totalQuantity = totalQuantity + invoiceLine.getQuantity();
					totalNetCharge = totalNetCharge + invoiceLine.getLineAmount();
				}
			}
			exportDTO.setTotalShipped(String.valueOf(totalQuantity));
		}
		exportDTO.setNetCharge(exportUtil.formatAmount(totalNetCharge).replace(ApplicationConstant.COMMA,ApplicationConstant.EMPTY_STRING).trim());
		
		if(null != invoiceDto.getInvoiceComments()){
			StringBuilder comment = new StringBuilder();
			for(String entry : invoiceDto.getInvoiceComments()){
				if(entry.contains("Attn")){
					if(entry.contains("<")){
						comment.append(entry.substring(0, entry.indexOf('<')));
					}
					else{
						comment.append(entry);
					}
				}
				else{
					comment.append(entry);
				}
				comment.append("\n");
			}
			List<String> commentList = new ArrayList<>();
			commentList.add(comment.toString());
			exportDTO.setComments(commentList);
		}
		return exportDTO;
	}
	
	private static final Log logger = LogFactoryUtil.getLog(InvoiceUtil.class);


	@Override
	public String getInvoicePdf(String categoryName, String reportName, String invoiceNumber) {
		QuoteClient quoteClient = QuoteConfiguration.getClientInstance();
		GetDocumentsListResponse responseInvoice = quoteClient.getInvoice(categoryName, reportName, invoiceNumber);
		SResult sResult = responseInvoice.getGetDocumentsListResult();
		String responseStr = "";
		if (sResult != null) {
			ArrayOfSearchResult resultArray = sResult.getSearchResultList();
			if (resultArray != null && !resultArray.getSearchResult().isEmpty()) {
				SearchResult searchResultSelected = null;
				if (resultArray.getSearchResult().size() > 1) {
					for (SearchResult searchResult: resultArray.getSearchResult()) {
						boolean summaryInvoice = false;
						for (DataField dataField: searchResult.getDataFields().getDataField()) {
							if ("DocType".equals(dataField.getDataFieldName()) && "100".equals(dataField.getDataFieldValue())) {
								summaryInvoice = true;
								break;
							}
						}
						if (!summaryInvoice) {
							searchResultSelected = searchResult;
						}
					}
				} 
				
				if (searchResultSelected == null) {
					searchResultSelected = resultArray.getSearchResult().get(0);
				}
				
				responseStr = "{'code':'" + VALID_PDF_CODE + "', 'data':'"
						+ searchResultSelected.getPDFUrl() + "'}";
			} else {
				responseStr = "{'code':'" + INVALID_PDF_CODE + "','message':'" + INVALID_PDF_MESSAGE + "'}";
			}
		}
		return responseStr;
	}

}
