package com.hbg.rp.search.util;

import com.hbg.rp.search.constants.ApplicationConstant;
import com.hbg.rp.search.exceptions.ShipmentTrackingException;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.portlet.ResourceResponse;

import com.hbg.rp.search.model.InvoiceExportDTO;
import com.hbg.rp.search.model.OrderExportDTO;
import com.hbg.rp.search.model.OrderStatusDTO;
import com.hbg.rp.search.model.ShipmentStatusDTO;
import com.hbg.rp.model.InvoiceHeader;
import com.hbg.rp.model.InvoiceLine;
import com.hbg.rp.model.OrderHeader;
import com.hbg.rp.model.OrderLine;
import com.hbg.rp.model.Product;
import com.hbg.rp.model.Shipment;
import com.hbg.rp.model.ShipmentLine;

import com.hbg.rp.search.dto.Tracking;

import com.hbg.rp.service.OrderHeaderLocalServiceUtil;

import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.hbg.rp.search.constants.ApplicationConstant.*;

/**
 * @author ravi.kumar
 */
@Component
public class ExportUtil implements IExportUtil {

	SimpleDateFormat osdDateFormatter = new SimpleDateFormat("yyyy-MM-dd");

	SimpleDateFormat searchExportDateFormatter = new SimpleDateFormat("yyyy_MM_dd_HH_mm");


	
	private final Log logger = LogFactoryUtil.getLog(this.getClass());

	@Autowired
	private IShipmentUtil shipmentUtil;

	@Autowired
	private IPortalMappingsUtil portalMappingsUtil;

	@Autowired
	private IShipmentTrackingUtil shipmentTrackingUtil;

	@Override

	public Workbook generateExcel(Object excelData, String permittedGroups,long extRepId,boolean hasAllTerritories)
			throws ParseException, SystemException, ShipmentTrackingException, NumberFormatException {
		Workbook workbook = new SXSSFWorkbook();
		if (excelData instanceof OrderExportDTO) {
			constructOrderDetailsWorkBook(workbook, (OrderExportDTO) excelData, permittedGroups);
		} else if (excelData instanceof InvoiceExportDTO) {
			InvoiceExportDTO invoiceExport = (InvoiceExportDTO) excelData;
			constructInvoiceDetailsWorkBook(workbook, invoiceExport, permittedGroups,extRepId);
		} else if (excelData instanceof OrderStatusDTO) {
			OrderStatusDTO shipmentStatus = (OrderStatusDTO) excelData;
			constructShipmentStatusDetailsWorkBook(workbook, shipmentStatus, permittedGroups,extRepId,hasAllTerritories);
		}
		return workbook;
	}

	/**
	 * Method for constructing OrderDetail Excel. ORDER DETAILS
	 *
	 * @throws SystemException
	 */
	private void constructOrderDetailsWorkBook(Workbook workbook, OrderExportDTO orderExportDTO, String permittedGroups)
			throws SystemException {
		Sheet sheet = workbook.createSheet("ORDER EXPORT");
		int currentIndex = 0;
		List<List<Object>> orderRows = new ArrayList<>();
		OrderHeader orderHeader = orderExportDTO.getOrderHeader();

		Map<String, Product> productMap = OrderHeaderLocalServiceUtil
				.getProductByOrderHeaderId(orderHeader.getOrderHeaderId(), false);
		
		Map<String, Map<String, String>> mappings = portalMappingsUtil.getMappingsForExport();
		
		for (OrderLine orderLine : orderExportDTO.getOrderLines()) {
			List<Object> statusOrderLineRows = new ArrayList<>();
			
			String lineStatusValue = mappings.get(MAPPING_LINE_STATUS).get(getStringValueOf(orderLine.getLineStatus()).toLowerCase());
			String lineStatus = lineStatusValue != null ? lineStatusValue : EMPTY_STRING;
			
			String statusDetailsValue = mappings.get(MAPPING_REASON_CODE_STATUS).get(getStringValueOf(orderLine.getLineStatusReasonCode()).toLowerCase());
			String statusDetails = statusDetailsValue != null ? statusDetailsValue : EMPTY_STRING;
			
			Product product = productMap.get(orderLine.getIsbn().trim());

			statusDetails = EMPTY_STRING.equals(statusDetails.trim()) ? "N/A" : statusDetails;
			statusOrderLineRows.add(orderHeader.getPoNbr()); // PO
			statusOrderLineRows.add(String.valueOf(orderHeader.getReferenceNbr())); // Order Refrence #
			statusOrderLineRows.add("Cancelled".equalsIgnoreCase(lineStatus) ? "Canceled" : lineStatus);// Line Status
			statusOrderLineRows.add((double) orderLine.getOrderQuantity());// Order QTY TOTAL in NUMBER
			statusOrderLineRows.add(orderLine.getIsbn()); // ISBN
			if (product != null) {
				statusOrderLineRows.add(product.getTitle());// FULL TITLE
				statusOrderLineRows.add(product.getByLine()); // FULL AUTHOR
				statusOrderLineRows.add(product.getProductDesc()); // Format
				try {
					statusOrderLineRows.add(formatDate(osdDateFormatter.parse(product.getOnSaleDate().split(SPACE_STRING)[0])));
				} catch (ParseException e) {
					statusOrderLineRows.add(EMPTY_STRING);
				} // OSD without timestamp
			} else {
				statusOrderLineRows.add(EMPTY_STRING);
				statusOrderLineRows.add(EMPTY_STRING);
				statusOrderLineRows.add(EMPTY_STRING);
				statusOrderLineRows.add(EMPTY_STRING);
			}
			statusOrderLineRows.add(orderLine.getMsrp());// US MSRP
			if (product != null) {
				statusOrderLineRows.add(product.getPublisherDesc());// Publisher
			} else {
				statusOrderLineRows.add(EMPTY_STRING);
			}
			statusOrderLineRows.add(orderLine.getPubStatusCode());// Pub Status
			statusOrderLineRows.add("shipped".equalsIgnoreCase(orderLine.getLineStatus()) ? "Shipped" : statusDetails); // Status Details
			statusOrderLineRows.add(formatDate(orderHeader.getOrderRecievedDate())); // Order Received Date
			statusOrderLineRows.add(orderHeader.getOrderSource()); // Order Source
			statusOrderLineRows.add(formatDate(orderHeader.getOrderProcessedDate())); // Order Processed Date
			statusOrderLineRows.add(orderHeader.getOrderStatus()); // Order Status
			statusOrderLineRows.add((double) orderHeader.getShippedQty()); // Total Shipped in NUMBER
			statusOrderLineRows.add((double) orderHeader.getReleasedQty());// Total Preparing to Ship in NUMBER
			statusOrderLineRows.add((double) orderHeader.getInProcessQty()); // Total In Progress in NUMBER
			statusOrderLineRows.add((double) orderHeader.getCreditHoldQty()); // Total Action Required in NUMBER
			statusOrderLineRows.add((double) orderHeader.getBackorderedQty()); // Total Backordered in NUMBER
			statusOrderLineRows.add((double) orderHeader.getCancelQty()); // Total Canceled in NUMBER
			statusOrderLineRows.add((double) orderHeader.getTotalQty()); // Total Order Quantity in NUMBER
			statusOrderLineRows.add(formatDate(orderLine.getCancellationDate()));// Order Line Cancel Date ,
			statusOrderLineRows.add(orderHeader.getAccountName()); // Account Name
			statusOrderLineRows.add(String.valueOf(orderHeader.getAccountNumber())); // Account Number
			statusOrderLineRows.add(orderExportDTO.getDestinationName()); // Destination
			statusOrderLineRows
					.add(orderHeader.getShiptoNumber() > 0 ? String.valueOf(orderHeader.getShiptoNumber()) : EMPTY_STRING); // Store #
			statusOrderLineRows.add(orderExportDTO.getDestinationAddress() + orderExportDTO.getDestinationZip()); // Destination Address
			statusOrderLineRows.add(orderLine.getLineStatusReasonCode()); // Reason Code (Order Line)
			statusOrderLineRows.add(String.format("%.2f", orderLine.getDiscountPct())); // Order Discount %
			statusOrderLineRows.add(orderExportDTO.getOfferCode()); // Offer Code

			double netAmount = 0.0;
			if (orderLine.getMsrp().split("\\$").length > 1) {
				try {
					netAmount = orderLine.getOrderQuantity() * Double.parseDouble(orderLine.getMsrp().split("\\$")[1])
							* (1 - (orderLine.getDiscountPct() / 100));
				} catch (NumberFormatException nfe) {
					netAmount = 0.0;
				}
			}

			Double calibratedNetAmount = Math.round(netAmount * 100.0) / 100.0;
			statusOrderLineRows.add(calibratedNetAmount); // Estimated Net Cost in $ in NUMBER
			if (permittedGroups.contains(orderLine.getReportingGroupCode()) || "0".equals(permittedGroups.trim())) {
				orderRows.add(statusOrderLineRows);
			}
		}
		addSimpleTable(orderHeaders, orderRows, EMPTY_STRING, sheet, workbook, currentIndex);
	}

	/**
	 * Construct shipment status details workBook.
	 */

	private void constructShipmentStatusDetailsWorkBook(Workbook workbook, OrderStatusDTO orderStatusDTO,
			String permittedGroups,long extRepId,boolean hasAllTerritories) throws SystemException, NumberFormatException {
		Sheet sheet = workbook.createSheet("SHIPMENT EXPORT");
		int currentIndex = 0;
		List<List<Object>> shipmentRows = new ArrayList<>();
		String orderNumber = String.valueOf(orderStatusDTO.getOrderRefNo());
		OrderHeader orderHeader = OrderHeaderLocalServiceUtil
				.fetchOrderHeader(orderStatusDTO.getShipments().get(0).getShipments().get(0).getOrderHeaderId());
		String destinationName;
		String destinationAddress;

		if (orderHeader.getDestinationName().isEmpty()) {
			destinationName = orderHeader.getShiptoName();
			destinationAddress = orderHeader.getShiptoName() + ", " + orderHeader.getShiptoCity() + ", "
					+ orderHeader.getShiptoState() + orderHeader.getShiptoZip();
		} else {
			destinationName = orderHeader.getDestinationName();
			destinationAddress = orderHeader.getDestinationName() + ", " + orderHeader.getDestinationCity() + ", "
					+ orderHeader.getDestinationState() + orderHeader.getDestinationZip();
		}

		Map<String, Product> productMap = OrderHeaderLocalServiceUtil
				.getProductByOrderHeaderId(orderHeader.getOrderHeaderId(), true);

		for (ShipmentStatusDTO shipmentStatus : orderStatusDTO.getShipments()) {
			for (Shipment shipment : shipmentStatus.getShipments()) {
				String trackingNumber = EMPTY_STRING;
				Date now = new Date();
				Long currentTime = now.getTime() / (1000 * 60 * 60 * 24);
				Long shipTime = shipment.getShipDate().getTime() / (1000 * 60 * 60 * 24);

				if (shipment.getTrackingNumber() != null && !shipment.getTrackingNumber().trim().equals(EMPTY_STRING)) {
					trackingNumber = shipment.getTrackingNumber();
				} else if (shipment.getProNumber() != null && !shipment.getProNumber().trim().equals(EMPTY_STRING)) {
					trackingNumber = shipment.getProNumber();
				} else if (shipment.getBillOfLadingNumber() != null
						&& !EMPTY_STRING.equals(shipment.getBillOfLadingNumber().trim())) {
					trackingNumber = shipment.getBillOfLadingNumber();
				} else if ((currentTime - shipTime) > 120) {
					trackingNumber = ApplicationConstant.TRACKING_NUMBER_TEXT;
				}
				List<String> shipments = new ArrayList<>();
				shipments.add(orderNumber); // Order Reference #
				shipments.add(orderHeader.getAccountName()); // Account Name
				shipments.add(String.valueOf(orderHeader.getAccountNumber())); // Account Number
				shipments.add(destinationName); // Destination
				shipments.add(destinationAddress); // Destination Address
				shipments.add(orderHeader.getShiptoNumber() > 0 ? String.valueOf(orderHeader.getShiptoNumber()) : EMPTY_STRING); // Store #
				shipments.add(orderHeader.getPoNbr()); // PO
				shipments.add(String.valueOf(shipment.getInvoiceNumber())); // Invoice #
				shipments.add(shipment.getCarrierName()); // Shipment
				shipments.add(shipment.getServiceLevelDesc()); // Shipment Method
				shipments.add(shipment.getShipGroupId()); // Ship Group
				shipments.add(trackingNumber); // Carrier Tracking Number

				String orderPlacedDate;
				String shippedDate;
				String status;
				String deliveryDate;
				try {
					Tracking tracking = shipmentTrackingUtil.getShipmentTrackingDetails(shipment.getCarrierId(),
							trackingNumber, String.valueOf(shipment.getOrderHeaderId()),
							formatDate(shipment.getShipDate()));
					if (!tracking.getStatus().toString().equalsIgnoreCase("UNKNOWN") || tracking.getMessage() == null
							|| tracking.getMessage().equals(EMPTY_STRING)) {
						orderPlacedDate = formatDate(shipment.getOrderRecievedDate());
						shippedDate = formatDate(shipment.getShipDate());
						status = tracking.getStatus().toString();
						deliveryDate = formatDate(tracking.getDeliveryDate());
					} else {
						orderPlacedDate = "N/A";
						shippedDate = "N/A";
						status = tracking.getMessage();
						deliveryDate = "N/A";
					}
				} catch (ShipmentTrackingException e) {
					logger.info(e.getMessage(), e);
					orderPlacedDate = EMPTY_STRING;
					shippedDate = EMPTY_STRING;
					status = EMPTY_STRING;
					deliveryDate = EMPTY_STRING;
				}
				Map<String, String> criteriaMap = new HashMap<>();
				criteriaMap.put("shipmentHeaderId", String.valueOf(shipment.getShipmentHeaderId()));
				List<ShipmentLine> shipmentLines = shipmentUtil.getShipmentLines(criteriaMap,extRepId,hasAllTerritories);

				for (ShipmentLine lineStatus : shipmentLines) {
					if((permittedGroups.contains(lineStatus.getReportingGroupCode())
							|| permittedGroups.trim().equals("0"))  && (lineStatus.getShipmentLineId() != -11)){
						Product product = productMap.get(lineStatus.getIsbn().trim());

						List<Object> statusOrderLineRows = new ArrayList<>(shipments);
						statusOrderLineRows.add(orderPlacedDate); // Order Placed
						statusOrderLineRows.add((double) lineStatus.getShippedQuantity()); // Quantity Shipped in NUMBER
						statusOrderLineRows.add(status); // Status
						statusOrderLineRows.add(deliveryDate); // Delivery Date
						statusOrderLineRows.add((double) lineStatus.getOrderQuantity()); // Quantity total in NUMBER
						statusOrderLineRows.add(shippedDate); // Shipped Date
						statusOrderLineRows.add(lineStatus.getIsbn()); // Item Code(ISBN/EAN)
						if (product != null) {
							statusOrderLineRows.add(product.getTitle());// FULL TITLE
							statusOrderLineRows.add(product.getByLine()); // FULL AUTHOR
							statusOrderLineRows.add(product.getProductDesc()); // Format
							try {
								statusOrderLineRows.add(
										formatDate(osdDateFormatter.parse(product.getOnSaleDate().split(SPACE_STRING)[0])));
							} catch (ParseException e) {
								statusOrderLineRows.add(EMPTY_STRING);
							} // OSD without timestamp
						} else {
							statusOrderLineRows.add(EMPTY_STRING);
							statusOrderLineRows.add(EMPTY_STRING);
							statusOrderLineRows.add(EMPTY_STRING);
							statusOrderLineRows.add(EMPTY_STRING);
						}
						statusOrderLineRows.add(String.valueOf(lineStatus.getMsrp())); // US MSRP
						if (product != null) {
							statusOrderLineRows.add(product.getPublisherDesc()); // Publisher
						} else {
							statusOrderLineRows.add(EMPTY_STRING);
						}
						statusOrderLineRows.add(String.valueOf(lineStatus.getPubStatusCode())); // Pub Status
						shipmentRows.add(statusOrderLineRows);
					}
				}
			}
		}
		addSimpleTable(shipmentHeaders, shipmentRows, EMPTY_STRING, sheet, workbook, currentIndex);
	}

	private Map<String, String> getOrderHeaderCommonMap(OrderHeader orderHeader, OrderExportDTO orderExportDTO) {
		Map<String, String> orderHeaderMap = new LinkedHashMap<>();
		orderHeaderMap.put("PO", orderHeader.getPoNbr());
		orderHeaderMap.put("Order Received Date", formatDate(orderHeader.getOrderRecievedDate()));
		orderHeaderMap.put("Order Processed Date", formatDate(orderHeader.getOrderProcessedDate()));
		orderHeaderMap.put("Order Status", orderHeader.getOrderStatus());
		orderHeaderMap.put("Order Source", orderHeader.getOrderSource());
		orderHeaderMap.put("Account Name", orderHeader.getAccountName());
		orderHeaderMap.put("Account Number", String.valueOf(orderHeader.getAccountNumber()));
		orderHeaderMap.put("Offer Code", orderExportDTO.getOfferCode());
		orderHeaderMap.put("Destination", orderExportDTO.getDestinationName());
		if (orderHeader.getShiptoNumber() > 0) {
			orderHeaderMap.put("Store #", String.valueOf(orderHeader.getShiptoNumber()));
		}
		orderHeaderMap.put("Destination Address",
				orderExportDTO.getDestinationAddress() + orderExportDTO.getDestinationZip());

		return orderHeaderMap;
	}

	/**
	 * Method for constructing InvoiceDetail Excel. INVOICE DETAILS
	 */
	private void constructInvoiceDetailsWorkBook(Workbook workbook, InvoiceExportDTO invoiceExportDTO,
												 String permittedGroups,long extRepId) throws ParseException {
		Sheet sheet1 = workbook.createSheet("Invoice Header");
		String typeOfInvoice = invoiceExportDTO.getInvoiceHeader().getInvoiceType();
		int currentIndex = 0;
		currentIndex = addHeading(invoiceExportDTO.getPageHeader(), sheet1, workbook, currentIndex);

		Map<String, String> allInfoMap = new LinkedHashMap<>();
		if (invoiceExportDTO.getInvoiceHeader() != null) {
			if ("DBN".equals(typeOfInvoice)) {
				allInfoMap.put("Debit Number", String.valueOf(invoiceExportDTO.getInvoiceHeader().getInvoiceNumber()));
			} else if ("CRN".equals(typeOfInvoice)) {
				allInfoMap.put("Credit Number", String.valueOf(invoiceExportDTO.getInvoiceHeader().getInvoiceNumber()));
			} else {
				allInfoMap.put("Invoice Number",
						String.valueOf(invoiceExportDTO.getInvoiceHeader().getInvoiceNumber()));
			}
		}
		if (invoiceExportDTO.getOrderHeader() != null) {
			allInfoMap.putAll(
					getOrderHeaderCommonMap(invoiceExportDTO.getOrderHeader(), invoiceExportDTO.getOrderExportDTO()));
		}
		if (invoiceExportDTO.getFooterSummary() != null
				&& !"DBN".equals(invoiceExportDTO.getInvoiceHeader().getInvoiceType())
				&& !"CRN".equals(invoiceExportDTO.getInvoiceHeader().getInvoiceType())) {
			allInfoMap.putAll(getMapFromList(invoiceFooterCols, invoiceExportDTO.getFooterSummary()));
		}
		if (!invoiceExportDTO.getCurrencyText().trim().equals(EMPTY_STRING)
				&& !"DBN".equals(invoiceExportDTO.getInvoiceHeader().getInvoiceType())
				&& !"CRN".equals(invoiceExportDTO.getInvoiceHeader().getInvoiceType())) {
			allInfoMap.put("Billed in", invoiceExportDTO.getCurrencyText());
		}
		Map<String, String> shipInfoMap = getMapFromList(invoiceShipmentInfoCols,
				invoiceExportDTO.getInvoiceShipmentInfo());
		allInfoMap.putAll(shipInfoMap);

		// Single call for all columns in the invoice, credit & debit header sheets.
		addHorizontalTable(allInfoMap, sheet1, workbook, currentIndex);

		addInvoiceLines(invoiceExportDTO.getInvoiceLines(), invoiceExportDTO.getTotalShipped(),
				invoiceExportDTO.getNetCharge(), workbook, permittedGroups, invoiceExportDTO, extRepId);
	}

	private Map<String, String> getMapFromList(String[] headers, List<String> footerSummary) {
		LinkedHashMap<String, String> valueMap = new LinkedHashMap<>();
		for (int count = 0; count < footerSummary.size(); count++) {
			if (!EMPTY_STRING.equals(footerSummary.get(count))) {
				valueMap.put(headers[count], footerSummary.get(count));
			}
		}
		return valueMap;
	}

	private void addInvoiceLines(List<InvoiceLine> invoiceLines, String totalShipped, String netCharge,
								 Workbook workbook, String permittedGroups, InvoiceExportDTO invoiceExportDTO,long extRepId) throws ParseException {
		int currentIndex = 0;
		List<List<String>> invoiceLineRows = new ArrayList<>();
		String totalShippedStr = StringPool.BLANK;
		String netChargeStr = StringPool.BLANK;
		long totalShippedCalc = 0;
		double netChargeCalc = 0.0;
		for (InvoiceLine invoiceLine : invoiceLines) {
			if (permittedGroups.contains(invoiceLine.getReportingGroupCode()) || "0".equals(permittedGroups.trim())) {
				List<String> lineEntry = new ArrayList<>();
				lineEntry.add(invoiceLine.getIsbn());
				lineEntry.add(invoiceLine.getShortTitle());
				lineEntry.add(invoiceLine.getShortAuthor());
				if ("Fulfilled".equalsIgnoreCase(invoiceLine.getLineStatus())) {
					lineEntry.add(String.valueOf(invoiceLine.getQuantity()));
					totalShippedCalc = totalShippedCalc + invoiceLine.getQuantity(); 
					InvoiceHeader invoiceHeader = invoiceExportDTO.getInvoiceHeader();
					if (invoiceHeader != null && !"INV".equalsIgnoreCase(invoiceHeader.getInvoiceType())) {
						lineEntry.add(String.valueOf(invoiceLine.getQuantityClaim()));
					}
					lineEntry.add(formatAmount(invoiceLine.getUnitPrice()));
					lineEntry.add(formatAmount(invoiceLine.getDiscount()) + "%");
					lineEntry.add(formatAmount(invoiceLine.getLineAmount()));
					netChargeCalc = netChargeCalc + invoiceLine.getLineAmount(); 
				} else {
					lineEntry.add(String.valueOf(invoiceLine.getQuantity()));
					totalShippedCalc = totalShippedCalc + invoiceLine.getQuantity(); 
					lineEntry.add(ApplicationConstant.BOM_TYPE);
					lineEntry.add(invoiceLine.getLineStatus());
				}
				invoiceLineRows.add(lineEntry);
			}
		}
		totalShippedStr = String.valueOf(totalShippedCalc);
		netChargeStr = String.format("%.2f", netChargeCalc);
		
		if (!invoiceLineRows.isEmpty()) {
			Sheet sheet;

			// As 2nd sheet added. Adding a prefix with header of first sheet. NRP-2239
			String invoiceType = invoiceExportDTO.getInvoiceHeader().getInvoiceType();
			if ("DBN".equals(invoiceType)) {
				addPrefixToHeaderSheet(workbook, "debit");
				sheet = workbook.createSheet("Debit Details");
			} else if ("CRN".equals(invoiceType)) {
				addPrefixToHeaderSheet(workbook, "credit");
				sheet = workbook.createSheet("Credit Details");
			} else {
				addPrefixToHeaderSheet(workbook, "invoice");
				sheet = workbook.createSheet("Invoice Details");
			}

			if (!invoiceExportDTO.getRelatedTransactions().isEmpty()) {
				currentIndex = addVerticalTable(getMapFromList(new String[] { "All Related Transactions" },
						invoiceExportDTO.getRelatedTransactions()), sheet, workbook, currentIndex);
			}

			if ("DBN".equals(invoiceType)) {
				currentIndex = addHeading("Debit Memo Summary:", sheet, workbook, currentIndex);
			} else if ("CRN".equals(invoiceType)) {
				currentIndex = addHeading("Credit Memo Summary:", sheet, workbook, currentIndex);
			} else {
				currentIndex = addHeading("Invoice Lines:", sheet, workbook, currentIndex);
			}
			String[] lineItemsCols = invoiceLineCols;
			String totalText = StringPool.BLANK;
			if (extRepId > 0) {
				totalText = SALES_REPRESENTATIVE + " Total Shipped";
			} else {
				totalText = "Total Shipped";
			}
			String totalResult = EMPTY_STRING;
			if (invoiceExportDTO.getInvoiceHeader() != null) {
				if ("CRN".equalsIgnoreCase(invoiceExportDTO.getInvoiceHeader().getInvoiceType())) {
					lineItemsCols = invoiceLineColsCredit;
					if (extRepId > 0) {
						totalText = SALES_REPRESENTATIVE + " Quantity Credit Total";
					} else {
						totalText = "Quantity Credit Total";
					}
					currentIndex = addSubHeading("Credit", invoiceExportDTO.getInvoiceHeader(), currentIndex, sheet);
				} else if ("DBN".equalsIgnoreCase(invoiceExportDTO.getInvoiceHeader().getInvoiceType())) {
					lineItemsCols = invoiceLineColsDebit;
					if (extRepId > 0) {
						totalText = SALES_REPRESENTATIVE + " Quantity Debit Total";
					} else {
						totalText = "Quantity Debit Total";
					}
					currentIndex = addSubHeading("Debit", invoiceExportDTO.getInvoiceHeader(), currentIndex, sheet);
				}
				
				if (extRepId > 0) {
					totalResult = totalText + ": " + totalShippedStr;
				} else {
					totalResult = totalText + ": " + totalShipped;
				}
				
				if (invoiceExportDTO.getInvoiceHeader().getTotalFreightAmt() > 0) {
					if (extRepId > 0) {
						totalResult = totalResult + ",Shipping Charge: " + ApplicationConstant.ENCRYPTED_AMOUNT;
					} else {
						totalResult = totalResult + ",Shipping Charge: $" + invoiceExportDTO.getInvoiceHeader().getTotalFreightAmt();
					}
				}
				if (invoiceExportDTO.getInvoiceHeader().getTotalTaxAmt() > 0) {
					if (extRepId > 0) {
						totalResult = totalResult + ",Sales Tax: " + ApplicationConstant.ENCRYPTED_AMOUNT;
					} else {
						totalResult = totalResult + ",Sales Tax: $" + invoiceExportDTO.getInvoiceHeader().getTotalTaxAmt();
					}
				}
				if (invoiceExportDTO.getInvoiceHeader().getTotalHandlingAmt() > 0) {
					if (extRepId > 0) {
						totalResult = totalResult + ",Handling Charge: " + ApplicationConstant.ENCRYPTED_AMOUNT;
					} else {
						totalResult = totalResult + ",Handling Charge: $" + invoiceExportDTO.getInvoiceHeader().getTotalHandlingAmt();
					}
				}
				if (invoiceExportDTO.getInvoiceHeader().getPrepaidAmt() > 0) {
					if (extRepId > 0) {
						totalResult = totalResult + ",Pre-paid Amount: " + ApplicationConstant.ENCRYPTED_AMOUNT;
					} else {
						totalResult = totalResult + ",Pre-paid Amount: $" + invoiceExportDTO.getInvoiceHeader().getPrepaidAmt();
					}
				}
				if (extRepId > 0) {
					totalResult = totalResult + ",Total Net Charge(Tax Not Included): $" + netChargeStr;
				} else {
					totalResult = totalResult + ",Total Net Charge: $" + invoiceExportDTO.getInvoiceHeader().getTotalAmt();
				}
			}
			currentIndex = addTable(lineItemsCols, invoiceLineRows, totalResult, sheet, workbook, currentIndex);
			if (!invoiceExportDTO.getComments().isEmpty()) {
				addVerticalTable(getMapFromList(new String[] { "Comments" }, invoiceExportDTO.getComments()), sheet,
						workbook, currentIndex);
			}
		}
	}

	/**
	 * Get currency style.
	 *
	 * @param workbook
	 * @return
	 */
	private CellStyle getCurrencyStyle(Workbook workbook) {
		CellStyle style = workbook.createCellStyle();
		style.setDataFormat((short) 7);
		return style;
	}

	/**
	 * Get currency style.
	 *
	 * @param workbook
	 * @return
	 */
	private CellStyle getNumberStyle(Workbook workbook) {
		CellStyle style = workbook.createCellStyle();
		style.setDataFormat(HSSFDataFormat.getBuiltinFormat("#,##0"));
		return style;
	}

	/**
	 * Get column header style.
	 *
	 * @param workbook
	 * @return
	 */
	private CellStyle getColumnHeaderStyle(Workbook workbook) {
		Font font = workbook.createFont();
		font.setFontHeightInPoints((short) 14);
		font.setFontName("Calibri");
		font.setBold(true);
		font.setItalic(false);

		CellStyle style = workbook.createCellStyle();
		style.setFont(font);
		style.setAlignment(HorizontalAlignment.LEFT);
		return style;
	}

	/**
	 * Get column header style.
	 *
	 * @param workbook
	 * @return
	 */
	private CellStyle getColumnHeaderValueStyle(Workbook workbook) {
		Font font = workbook.createFont();
		font.setFontHeightInPoints((short) 11);
		font.setFontName("Calibri");
		font.setItalic(false);

		CellStyle style = workbook.createCellStyle();
		style.setFont(font);
		style.setWrapText(true);
		return style;
	}

	/**
	 * Format date
	 *
	 * @param date
	 * @return String
	 */
	@Override
	public String formatDate(Date date) {
		if (date != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy");
			return sdf.format(date);
		}
		return EMPTY_STRING;
	}

	/**
	 * Common method for all the headers.
	 *
	 * @param heading
	 * @param sheet
	 * @param workbook
	 * @param currentIndex
	 * @return
	 */
	private int addHeading(String heading, Sheet sheet, Workbook workbook, int currentIndex) {
		Row summaryHeader = sheet.createRow(currentIndex);
		sheet.addMergedRegion(new CellRangeAddress(currentIndex, currentIndex, 0, 10));
		Cell summaryHeaderCell = summaryHeader.createCell(0);
		summaryHeaderCell.setCellStyle(getColumnHeaderStyle(workbook));
		summaryHeaderCell.setCellValue(heading);
		return currentIndex + 2;
	}

	private void addSimpleTable(String[] tableHeaders, List<List<Object>> tableRows, String tableResult, Sheet sheet,
								Workbook workbook, int currentIndex) {

		Row headerRow = sheet.createRow(currentIndex);
		for (int headerCount = 0; headerCount < tableHeaders.length; headerCount++) {
			Cell orderValueCell = headerRow.createCell(headerCount);
			orderValueCell.setCellStyle(getColumnHeaderStyle(workbook));
			orderValueCell.setCellValue(tableHeaders[headerCount]);
		}
		currentIndex++;

		for (List<Object> tableEntry : tableRows) {
			int dataCount = tableEntry.size();
			Row tableData = sheet.createRow(currentIndex);
			for (int count = 0; count < dataCount; count++) {
				if (tableEntry.get(count).getClass().equals(String.class)) {
					tableData.createCell(count).setCellValue(String.valueOf(tableEntry.get(count)));
				} else {
					tableData.createCell(count).setCellValue((double) tableEntry.get(count));
				}
			}
			currentIndex++;
		}

		if (!tableResult.trim().isEmpty()) {
			String[] resultArray = tableResult.split(",");
			currentIndex++;
			for (String cellValue : resultArray) {
				Row tableResultRow = sheet.createRow(currentIndex);
				Cell tableResultCell = tableResultRow.createCell(1);
				tableResultCell.setCellStyle(getColumnHeaderStyle(workbook));
				tableResultCell.setCellValue(cellValue);
				currentIndex++;
			}
		}

	}

	private void addFormattedTable(String[] tableHeaders, String[] tableHeaderMeta, List<List<Object>> tableRows,
								   Sheet sheet, Workbook workbook, int currentIndex) {

		Row headerRow = sheet.createRow(currentIndex);
		for (int headerCount = 0; headerCount < tableHeaders.length; headerCount++) {
			Cell orderValueCell = headerRow.createCell(headerCount);
			orderValueCell.setCellStyle(getColumnHeaderStyle(workbook));
			orderValueCell.setCellValue(tableHeaders[headerCount]);
		}
		currentIndex++;

		CellStyle numberStyle = getNumberStyle(workbook);
		CellStyle currencyStyle = getCurrencyStyle(workbook);

		for (List<Object> tableEntry : tableRows) {
			int dataCount = tableEntry.size();
			Row tableData = sheet.createRow(currentIndex);
			for (int count = 0; count < dataCount; count++) {
				if (tableHeaderMeta[count].equalsIgnoreCase(NUMBER_CELL_FORMAT)) {
					Cell numberCell = tableData.createCell(count);
					numberCell.setCellStyle(numberStyle);
					numberCell.setCellValue((double) tableEntry.get(count));
				} else if (tableHeaderMeta[count].equalsIgnoreCase(CURRENCY_CELL_FORMAT)) {
					Cell numberCell = tableData.createCell(count);
					numberCell.setCellStyle(currencyStyle);
					try {
						numberCell.setCellValue((double) tableEntry.get(count));
					}catch(Exception e) {
						numberCell.setCellValue(String.valueOf(tableEntry.get(count)));
					}
				} else {
					tableData.createCell(count).setCellValue(String.valueOf(tableEntry.get(count)));
				}
			}
			currentIndex++;
		}
	}

	private int addTable(String[] tableHeaders, List<List<String>> tableRows, String tableResult, Sheet sheet,
						 Workbook workbook, int currentIndex) {

		Row headerRow = sheet.createRow(currentIndex);
		for (int headerCount = 0; headerCount < tableHeaders.length; headerCount++) {
			Cell orderValueCell = headerRow.createCell(headerCount);
			orderValueCell.setCellStyle(getColumnHeaderStyle(workbook));
			orderValueCell.setCellValue(tableHeaders[headerCount]);
		}
		currentIndex++;

		for (List<String> tableEntry : tableRows) {
			int dataCount = tableEntry.size();
			Row tableData = sheet.createRow(currentIndex);
			for (int count = 0; count < dataCount; count++) {
				if (tableEntry.get(count).equalsIgnoreCase(ApplicationConstant.BOM_TYPE)) {
					// Logic for Bom Type invoices
					tableData.createCell(count).setCellValue(tableEntry.get(count + 1));
					sheet.addMergedRegion(new CellRangeAddress(currentIndex, currentIndex, count, count + 2));
					break;
				} else {
					tableData.createCell(count).setCellValue(tableEntry.get(count));
				}
			}
			currentIndex++;
		}

		if (!tableResult.trim().isEmpty()) {
			String[] resultArray = tableResult.split(",");
			currentIndex++;
			for (String cellValue : resultArray) {
				Row tableResultRow = sheet.createRow(currentIndex);
				Cell tableResultCell = tableResultRow.createCell(0);
				tableResultCell.setCellStyle(getColumnHeaderStyle(workbook));
				tableResultCell.setCellValue(cellValue);
				currentIndex++;
			}
		}

		return currentIndex + 2;
	}

	/**
	 * Method to add vertical table.
	 */
	private int addVerticalTable(Map<String, String> tableData, Sheet sheet, Workbook workbook, int currentIndex) {
		for (Map.Entry<String, String> entry : tableData.entrySet()) {
			if (!entry.getValue().trim().equals(EMPTY_STRING)) {
				Row entryRow = sheet.createRow(currentIndex);
				Cell tableHeaderCell = entryRow.createCell(0);
				tableHeaderCell.setCellStyle(getColumnHeaderStyle(workbook));
				tableHeaderCell.setCellValue(entry.getKey());
				if (!"NA".equals(entry.getValue())) {
					Cell tableValueCell = entryRow.createCell(1);
					tableValueCell.setCellValue(entry.getValue());
					tableValueCell.setCellStyle(getColumnHeaderValueStyle(workbook));
				}
				currentIndex++;
			}
		}
		return 0;
	}

	/**
	 * Method for adding values horizontally under header sheets i.e. // NRP-2238
	 * Order Header Invoice Header Shipment Header
	 */
	private void addHorizontalTable(Map<String, String> tableData, Sheet sheet, Workbook workbook, int currentIndex) {
		// Add header row.
		Row headerRow = sheet.createRow(currentIndex);
		currentIndex++;
		Row valueRow = sheet.createRow(currentIndex);
		CellStyle cellStyleHeader = getColumnHeaderStyle(workbook);
		CellStyle cellStyleValue = getColumnHeaderValueStyle(workbook);

		int count = 0;
		for (Entry<String, String> entry : tableData.entrySet()) {
			Cell headerCell = headerRow.createCell(count);
			headerCell.setCellStyle(cellStyleHeader);
			headerCell.setCellValue(entry.getKey());

			String value = entry.getValue();
			if (value.trim().equals(EMPTY_STRING) || "NA".equals(entry.getValue())) {
				// Empty the value in case of space or NA.
				value = EMPTY_STRING;
			}
			Cell valueCell = valueRow.createCell(count);
			valueCell.setCellStyle(cellStyleValue);
			valueCell.setCellValue(value);
			count++;
		}
	}

	@Override
	public String formatAmount(double amount) {
		DecimalFormat df = new DecimalFormat("#,##0.00;-#,##0.00");
		return df.format(amount);
	}

	@Override
	public String formatCurrency(double amount) {
		return DOLLAR + formatAmount(amount);
	}

	@Override
	public String exportFileName(Object exportObject) {
		String fileProperty;
		if (exportObject instanceof OrderExportDTO) {
			fileProperty = "attachment; filename=Order_"
					+ ((OrderExportDTO) exportObject).getOrderHeader().getReferenceNbr() + FILE_FORMAT;
		} else if (exportObject instanceof InvoiceExportDTO) {
			fileProperty = "attachment; filename=Invoice_" + ((InvoiceExportDTO) exportObject).getRefrenceNumber()
					+ FILE_FORMAT;
		} else if (exportObject instanceof OrderStatusDTO) {
			OrderStatusDTO statusDTO = (OrderStatusDTO) exportObject;
			if (!statusDTO.getShipments().isEmpty()) {
				fileProperty = "attachment; filename=Shipment_"
						+ statusDTO.getShipments().get(0).getShipments().get(0).getReferenceNumber() + FILE_FORMAT;
			} else {
				fileProperty = "attachment; filename=Shipment_" + statusDTO.getOrderRefNo() + FILE_FORMAT;
			}
		} else {
			fileProperty = "attachment; filename=Error_Something_Went_Wrong.xlsx";
		}
		return fileProperty;
	}

	@Override
	public OutputStream writeExcel(ResourceResponse response, Object excelData, String permittedGroups,long extRepId,boolean hasAllTerritories)
			throws IOException, ParseException, SystemException, ShipmentTrackingException, NumberFormatException {

		Workbook workbook = generateExcel(excelData, permittedGroups,extRepId, hasAllTerritories);

		// Auto-sizing of columns
		int maxColumnsToAutosize = 50;
		Iterator<Sheet> iterator = workbook.sheetIterator();
		while (iterator.hasNext()) {
			SXSSFSheet sheet = (SXSSFSheet) iterator.next();
			sheet.trackAllColumnsForAutoSizing();
			for (int i = 0; i < maxColumnsToAutosize; i++) {
				sheet.autoSizeColumn(i);
			}
		}

		response.setContentType(ApplicationConstant.EXPORT_CONTENT_TYPE);
		response.addProperty(CONTENT_DISPOSITION, exportFileName(excelData));
		OutputStream outStream = response.getPortletOutputStream();
		workbook.write(outStream);
		return outStream;
	}

	/**
	 * Method for add sub-heading
	 */
	private int addSubHeading(String type, InvoiceHeader invoiceHeader, int currentIndex, Sheet sheet)
			throws ParseException {
		Row subHeader = sheet.createRow(currentIndex);
		Cell subHeaderCell = subHeader.createCell(0);
		subHeaderCell.setCellValue(type + " Memo Number: " + invoiceHeader.getInvoiceNumber());
		Cell subHeaderCellDate = subHeader.createCell(1);
		subHeaderCellDate.setCellValue(type + " Memo Date: "
				+ formatDate(new SimpleDateFormat("yyyy-MM-dd").parse(invoiceHeader.getInvoiceDate())));
		if ((!EMPTY_STRING.equals(invoiceHeader.getChargebackNumber().trim())) && (null != invoiceHeader.getChargebackNumber())
				&& (!invoiceHeader.getChargebackNumber().isEmpty())) {
			Cell subHeaderCellChargeBack = subHeader.createCell(2);
			subHeaderCellChargeBack.setCellValue("Charge Back Number: " + invoiceHeader.getChargebackNumber());
		}
		return currentIndex + 2;
	}

	/**
	 * Method for add prefix to header sheet.
	 *
	 * @param workbook
	 * @param type
	 */
	private void addPrefixToHeaderSheet(Workbook workbook, String type) {
		try {
			Sheet headerSheet = workbook.getSheetAt(0);
			if (null != headerSheet && headerSheet.getRow(0) != null && headerSheet.getRow(0).getCell(0) != null) {
				Cell headerColumn = headerSheet.getRow(0).getCell(0);
				String value = headerColumn.getStringCellValue() + " (see second tab for " + type + " details) ";
				headerColumn.setCellValue(value);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	@Override
	public OutputStream writeSearchResultExcel(ResourceResponse response, List<Object[]> searchList,
			String permittedGroups, String exportType,Long extRepId,boolean hasAllTerritories)
			throws IOException, ParseException, SystemException, PortalException {
		OutputStream outStream = response.getPortletOutputStream();
		Workbook workbook = new SXSSFWorkbook();
		constructSearchResultWorkBook(workbook, searchList, permittedGroups, exportType, extRepId);
		int maxColumnsToAutosize = 50;
		Iterator<Sheet> iterator = workbook.sheetIterator();
		while (iterator.hasNext()) {
			SXSSFSheet sheet = (SXSSFSheet) iterator.next();
			sheet.trackAllColumnsForAutoSizing();
			for (int i = 0; i < maxColumnsToAutosize; i++) {
				sheet.autoSizeColumn(i);
			}
		}
		response.setContentType(ApplicationConstant.EXPORT_CONTENT_TYPE);
		Date now = new Date();
		String exportFilename = searchExportDateFormatter.format(now);
		if (exportType.equalsIgnoreCase("Order")) {
			response.addProperty(CONTENT_DISPOSITION,
					"attachment; filename=Order_Search_" + exportFilename + FILE_FORMAT);
		} else if (exportType.equalsIgnoreCase("Shipment")) {
			response.addProperty(CONTENT_DISPOSITION,
					"attachment; filename=Shipment_Search_" + exportFilename + FILE_FORMAT);
		} else if (exportType.equalsIgnoreCase("credits")) {
			response.addProperty(CONTENT_DISPOSITION,
					"attachment; filename=Credit_Search_" + exportFilename + FILE_FORMAT);
		} else if (exportType.equalsIgnoreCase("debits")) {
			response.addProperty(CONTENT_DISPOSITION,
					"attachment; filename=Debit_Search_" + exportFilename + FILE_FORMAT);
		} else if (exportType.equalsIgnoreCase(TRANSACTIONS)) {
			response.addProperty(CONTENT_DISPOSITION,
					"attachment; filename=Financial_Transactions_Search_" + exportFilename + FILE_FORMAT);
		} else {
			response.addProperty(CONTENT_DISPOSITION,
					"attachment; filename=Invoice_Search_" + exportFilename + FILE_FORMAT);
		}
		workbook.write(outStream);
		return outStream;
	}

	/**
	 * This method constructs workbook from DB results
	 * Performance improved by caching map from portalMappingUtil and using it same during iteration
	 * Performance improved by replacing BigDecimal to Double
	 * @param workbook
	 * @param searchResults
	 * @param permittedGroups
	 * @param exportType
	 * @param extRepId
	 * @throws SystemException
	 * @throws PortalException
	 * @throws ParseException
	 */
	private void constructSearchResultWorkBook(Workbook workbook, List<Object[]> searchResults, String permittedGroups,
			String exportType,Long extRepId) throws SystemException, PortalException, ParseException {
		List<List<Object>> sheetData = new ArrayList<>();
		logger.info("Total export:" + searchResults.size());
		int currentIndex = 0;
		String[] headers;
		String[] columnStyle;
		Sheet sheet;
		if (exportType.equalsIgnoreCase("Order")) {
			Map<String, Map<String, String>> mappings = portalMappingsUtil.getMappingsForExport();
			headers = orderCols;
			columnStyle = orderStyles;
			sheet = workbook.createSheet("ORDER EXPORT");
			for (Object[] objectInstance : searchResults) {
				List<Object> statusOrderLineRows = new ArrayList<>();
				String unFormattedDate = String.valueOf(objectInstance[12]).trim();
				if (!(unFormattedDate.equalsIgnoreCase(NULL_STRING) || unFormattedDate.equalsIgnoreCase(EMPTY_STRING))) {
					statusOrderLineRows.add(formatDate(osdDateFormatter.parse(unFormattedDate.split(SPACE_STRING)[0])));// Order placed Date
				} else {
					statusOrderLineRows.add(EMPTY_STRING);
				}
				String poNum = String.valueOf(objectInstance[0]).trim();
				statusOrderLineRows.add(poNum.equalsIgnoreCase(NULL_STRING) ? EMPTY_STRING : poNum); // PO
				statusOrderLineRows.add(String.valueOf(objectInstance[1])); // Order Refrence #
				String lineStatusValue = mappings.get(MAPPING_LINE_STATUS).get(getStringValueOf(objectInstance[2]).toLowerCase());
				String lineStatus = lineStatusValue != null ? lineStatusValue : EMPTY_STRING;
				statusOrderLineRows.add("Cancelled".equalsIgnoreCase(lineStatus) ? "Canceled" : lineStatus);// Line Status
				double orderLineQuantity = 0;
				if (objectInstance[3] != null) {
					orderLineQuantity = new Double(String.valueOf(objectInstance[3]));
				}
				statusOrderLineRows.add(orderLineQuantity);// Order QTY TOTAL in NUMBER
				statusOrderLineRows.add(String.valueOf(objectInstance[4])); // ISBN
				statusOrderLineRows.add(String.valueOf(objectInstance[5]));// FULL TITLE
				statusOrderLineRows.add(String.valueOf(objectInstance[6])); // FULL AUTHOR
				String format = String.valueOf(objectInstance[7]).trim();
				statusOrderLineRows.add(format.equalsIgnoreCase(NULL_STRING) ? EMPTY_STRING : format); // format
				unFormattedDate = String.valueOf(objectInstance[8]).trim();
				if (!(unFormattedDate.equalsIgnoreCase(NULL_STRING) || unFormattedDate.equalsIgnoreCase(EMPTY_STRING))) {
					statusOrderLineRows.add(formatDate(osdDateFormatter.parse(unFormattedDate.split(SPACE_STRING)[0])));// OSD
				} else {
					statusOrderLineRows.add(EMPTY_STRING);
				}
				String msrp = String.valueOf(objectInstance[9]).trim();
				if (msrp.equalsIgnoreCase(NULL_STRING) || msrp.equalsIgnoreCase(EMPTY_STRING)) {
					msrp = DATA_CANNOT_BE_DISPLAYED;
				} else {
					Double numUsMsrp = Double.parseDouble(msrp);
					msrp = DOLLAR + String.format("%.2f", numUsMsrp);
				}
				statusOrderLineRows.add(msrp);// US MSRP
				String canadianMsrp = String.valueOf(objectInstance[38]).trim();
				if (canadianMsrp.equalsIgnoreCase(NULL_STRING) || canadianMsrp.equalsIgnoreCase(EMPTY_STRING)) {
					canadianMsrp = DATA_CANNOT_BE_DISPLAYED;
				} else {
					Double numCanMsrp = Double.parseDouble(canadianMsrp);
					canadianMsrp = DOLLAR + String.format("%.2f", numCanMsrp);
				}
				statusOrderLineRows.add(canadianMsrp);// Canadian MSRP

				String publisher = String.valueOf(objectInstance[10]).trim();
				statusOrderLineRows.add(publisher.equalsIgnoreCase(NULL_STRING) ? EMPTY_STRING : publisher); // Publisher
				statusOrderLineRows.add(String.valueOf(objectInstance[11]));// Pub Status
				String orderLineStatusReasonCode = getStringValueOf(objectInstance[35]);
				String statusDetailsvalue = mappings.get(MAPPING_LINE_STATUS).get(orderLineStatusReasonCode.toLowerCase());
				String statusDetails = statusDetailsvalue != null ? statusDetailsvalue : EMPTY_STRING;
				statusDetails = EMPTY_STRING.equals(statusDetails.trim()) ? "N/A" : statusDetails;
				statusOrderLineRows
						.add("shipped".equalsIgnoreCase(String.valueOf(objectInstance[2])) ? "Shipped" : statusDetails); // Status Details
				statusOrderLineRows.add(String.valueOf(objectInstance[13])); // Order Source
				unFormattedDate = String.valueOf(objectInstance[14]).trim();
				if (!(unFormattedDate.equalsIgnoreCase(NULL_STRING) || unFormattedDate.equalsIgnoreCase(EMPTY_STRING))) {
					statusOrderLineRows.add(formatDate(osdDateFormatter.parse(unFormattedDate.split(SPACE_STRING)[0])));// Order Processed Date
				} else {
					statusOrderLineRows.add(EMPTY_STRING);
				}
				statusOrderLineRows.add(String.valueOf(objectInstance[15])); // Order Status

				double shippedQuantity = 0;
				if (objectInstance[16] != null) {
					shippedQuantity = new Double(String.valueOf(objectInstance[16]));
				}
				statusOrderLineRows.add(shippedQuantity); // Total Shipped in NUMBER

				double prepShippedQuantity = 0;
				if (objectInstance[17] != null) {
					prepShippedQuantity = new Double(String.valueOf(objectInstance[17]));
				}
				statusOrderLineRows.add(prepShippedQuantity);// Total Preparing to Ship in NUMBER

				double inProgressQuantity = 0;
				if (objectInstance[18] != null) {
					inProgressQuantity = new Double(String.valueOf(objectInstance[18]));
				}
				statusOrderLineRows.add(inProgressQuantity); // Total In Progress in NUMBER

				double actionQuantity = 0;
				if (objectInstance[19] != null) {
					actionQuantity = new Double(String.valueOf(objectInstance[19]));
				}
				statusOrderLineRows.add(actionQuantity); // Total Action Required in NUMBER

				double backorderQuantity = 0;
				if (objectInstance[20] != null) {
					backorderQuantity = new Double(String.valueOf(objectInstance[20]));
				}
				statusOrderLineRows.add(backorderQuantity); // Total Backordered in NUMBER

				double cancelledQuantity = 0;
				if (objectInstance[21] != null) {
					cancelledQuantity = new Double(String.valueOf(objectInstance[21]));
				}
				statusOrderLineRows.add(cancelledQuantity); // Total Canceled in NUMBER

				double orderQuantity = 0;
				if (objectInstance[22] != null) {
					orderQuantity = new Double(String.valueOf(objectInstance[22]));
				}
				statusOrderLineRows.add(orderQuantity); // Total Order Quantity in NUMBER

				unFormattedDate = String.valueOf(objectInstance[23]).trim();
				if (!(unFormattedDate.equalsIgnoreCase(NULL_STRING) || unFormattedDate.equalsIgnoreCase(EMPTY_STRING))) {
					statusOrderLineRows.add(formatDate(osdDateFormatter.parse(unFormattedDate.split(SPACE_STRING)[0])));// Order Line Cancel Date
				} else {
					statusOrderLineRows.add(EMPTY_STRING);
				}
				statusOrderLineRows.add(String.valueOf(objectInstance[24])); // Account Name
				statusOrderLineRows.add(String.valueOf(objectInstance[25])); // Account Number
				String destinationName = String.valueOf(objectInstance[26]).trim();
				String destinationAddress;
				if (destinationName.equalsIgnoreCase(NULL_STRING) || destinationName.equalsIgnoreCase(EMPTY_STRING)) {
					destinationName = String.valueOf(objectInstance[30]);
					destinationAddress = constructDestination(destinationName, String.valueOf(objectInstance[31]),
							String.valueOf(objectInstance[32]), String.valueOf(objectInstance[33]));
				} else {
					destinationAddress = constructDestination(destinationName, String.valueOf(objectInstance[27]),
							String.valueOf(objectInstance[28]), String.valueOf(objectInstance[29]));
				}
				statusOrderLineRows.add(destinationName); // Destination
				String shiptoNumberStr = String.valueOf(objectInstance[34]);

				int shiptoNumber = 0;
				if (!(shiptoNumberStr.equalsIgnoreCase(NULL_STRING) || shiptoNumberStr.equalsIgnoreCase(EMPTY_STRING))) {
					shiptoNumber = Integer.parseInt(shiptoNumberStr);
				}
				statusOrderLineRows.add(shiptoNumber > 0 ? String.valueOf(shiptoNumber) : EMPTY_STRING); // Store #
				statusOrderLineRows.add(destinationAddress); // Destination Address
				statusOrderLineRows.add(orderLineStatusReasonCode); // Reason Code(Order Line)

				String discountPct = String.valueOf(objectInstance[36]).trim();
				double discountNumPct = 0.00;
				if (!(discountPct.equalsIgnoreCase(NULL_STRING) || discountPct.equalsIgnoreCase(EMPTY_STRING))) {
					discountNumPct = Double.parseDouble(discountPct);
					discountPct = String.format("%.2f", discountNumPct);
				} else {
					discountPct = "0.00";
				}
				statusOrderLineRows.add(discountPct); // Order Discount %
				String offerCodes = String.valueOf(objectInstance[37]).trim();
				statusOrderLineRows.add(offerCodes.equalsIgnoreCase(NULL_STRING) ? "N/A" : offerCodes); // Offer Code

				if (msrp.startsWith(DOLLAR)) {
					double netAmount = orderLineQuantity * Double.parseDouble(msrp.split("\\$")[1])
							* (1 - (discountNumPct / 100));
					Double calibratedNetAmount = Math.round(netAmount * 100.0) / 100.0;
					statusOrderLineRows.add(calibratedNetAmount); // Estimated Net Cost in $ in NUMBER
				} else {
					statusOrderLineRows.add((double) 0);
				}

				String orderlineReportingGroupCode = String.valueOf(objectInstance[39]).trim();
				if (permittedGroups.contains(orderlineReportingGroupCode) || "0".equals(permittedGroups.trim())) {
					sheetData.add(statusOrderLineRows);
				}
			}

		} else if (exportType.equalsIgnoreCase("Shipment")) {
			headers = shipmentCols;
			columnStyle = shipmentStyles;
			BigDecimal conversionVariable;
			sheet = workbook.createSheet("SHIPMENT EXPORT");
			Map<String, Integer> indexMap = new HashMap<>();
			int index = 0;
			String mapKey;
			for (Object[] objectInstance : searchResults) {
				String shipmentLineReportingGroupCode = String.valueOf(objectInstance[33]).trim();
				if (permittedGroups.contains(shipmentLineReportingGroupCode) || "0".equals(permittedGroups.trim())) {

					String currentReferenceNum = String.valueOf(objectInstance[10]);
					String localString = String.valueOf(objectInstance[12]).trim();
					String currentIsbn = localString.equalsIgnoreCase(NULL_STRING) ? EMPTY_STRING : localString;
					String tracking = String.valueOf(objectInstance[1]).trim();
					String pro = String.valueOf(objectInstance[2]).trim();
					String bol = String.valueOf(objectInstance[3]).trim();
					String supportedTracking = String.valueOf(objectInstance[36]).trim();
					String carrierTracking = EMPTY_STRING;

					if (supportedTracking.equalsIgnoreCase("BOL")) {
						carrierTracking = bol;
					} else if (supportedTracking.equalsIgnoreCase("PRO")) {
						carrierTracking = pro;
					} else {
						if (!(tracking.equalsIgnoreCase(NULL_STRING) || tracking.equalsIgnoreCase(EMPTY_STRING))) {
							carrierTracking = tracking;
						} else if (!(pro.equalsIgnoreCase(NULL_STRING) || pro.equalsIgnoreCase(EMPTY_STRING))) {
							carrierTracking = pro;
						} else if (!(bol.equalsIgnoreCase(NULL_STRING) || bol.equalsIgnoreCase(EMPTY_STRING))) {
							carrierTracking = bol;
						}
					}

					mapKey = currentReferenceNum + currentIsbn + carrierTracking;

					double shippedQuantity = 0;
					if (objectInstance[35] != null) {
						conversionVariable = new BigDecimal(String.valueOf(objectInstance[35]));
						shippedQuantity = conversionVariable.doubleValue();
					}

					if (indexMap.containsKey(mapKey)) {
						List<Object> excelEntry = sheetData.get(indexMap.get(mapKey));
						double finalShippedQuantity = new Double(excelEntry.get(10).toString()) + shippedQuantity;
						excelEntry.set(10, finalShippedQuantity);
						sheetData.set(indexMap.get(mapKey), excelEntry);
					} else {
						List<Object> statusShipmentLineRows = new ArrayList<>();
						String shipGroup = String.valueOf(objectInstance[0]).trim();
						statusShipmentLineRows.add(shipGroup.equalsIgnoreCase(NULL_STRING) ? EMPTY_STRING : shipGroup); // Ship Group

						statusShipmentLineRows.add(carrierTracking); // Carrier Tracking Number

						statusShipmentLineRows.add(String.valueOf(objectInstance[4]));// Shipment Carrier
						statusShipmentLineRows.add(String.valueOf(objectInstance[5])); // Shipment Method
						statusShipmentLineRows.add(String.valueOf(objectInstance[6])); // Invoice Number

						String unFormattedDate = String.valueOf(objectInstance[7]).trim();
						if (!(unFormattedDate.equalsIgnoreCase(NULL_STRING) || unFormattedDate.equalsIgnoreCase(EMPTY_STRING))) {
							statusShipmentLineRows
									.add(formatDate(osdDateFormatter.parse(unFormattedDate.split(SPACE_STRING)[0])));// Order placed Date
						} else {
							statusShipmentLineRows.add(EMPTY_STRING);
						}
						unFormattedDate = String.valueOf(objectInstance[8]).trim();
						if (!(unFormattedDate.equalsIgnoreCase(NULL_STRING) || unFormattedDate.equalsIgnoreCase(EMPTY_STRING))) {
							statusShipmentLineRows
									.add(formatDate(osdDateFormatter.parse(unFormattedDate.split(SPACE_STRING)[0])));// Items
							// Shipped
							// Date
						} else {
							statusShipmentLineRows.add(EMPTY_STRING);
						}

						statusShipmentLineRows.add(String.valueOf(objectInstance[9]));// PO

						statusShipmentLineRows.add(currentReferenceNum); // Order Reference #

						double lineQuantity = 0;
						if (objectInstance[11] != null) {
							conversionVariable = new BigDecimal(String.valueOf(objectInstance[11]));
							lineQuantity = conversionVariable.doubleValue();
						}
						statusShipmentLineRows.add(lineQuantity);// Line Quantity Total in number
						statusShipmentLineRows.add(shippedQuantity);// Shipped Line Quantity Total in number
						statusShipmentLineRows.add(currentIsbn); // ISBN
						localString = String.valueOf(objectInstance[13]).trim();
						statusShipmentLineRows.add(localString.equalsIgnoreCase(NULL_STRING) ? EMPTY_STRING : localString);// FULL
						// TITLE
						localString = String.valueOf(objectInstance[14]).trim();
						statusShipmentLineRows.add(localString.equalsIgnoreCase(NULL_STRING) ? EMPTY_STRING : localString); // FULL
						// AUTHOR
						localString = String.valueOf(objectInstance[15]).trim();
						statusShipmentLineRows.add(localString.equalsIgnoreCase(NULL_STRING) ? EMPTY_STRING : localString); // Format
						unFormattedDate = String.valueOf(objectInstance[16]).trim();
						if (!(unFormattedDate.equalsIgnoreCase(NULL_STRING) || unFormattedDate.equalsIgnoreCase(EMPTY_STRING))) {
							statusShipmentLineRows
									.add(formatDate(osdDateFormatter.parse(unFormattedDate.split(SPACE_STRING)[0])));// OSD
						} else {
							statusShipmentLineRows.add(EMPTY_STRING);
						}

						String msrp = String.valueOf(objectInstance[17]).trim();
						if (msrp.equalsIgnoreCase(NULL_STRING) || msrp.equalsIgnoreCase(EMPTY_STRING)) {
							msrp = DATA_CANNOT_BE_DISPLAYED;
						} else {
							Double numUsMsrp = Double.parseDouble(msrp);
							msrp = DOLLAR + String.format("%.2f", numUsMsrp);
						}
						statusShipmentLineRows.add(msrp);// US MSRP
						String canadianMsrp = String.valueOf(objectInstance[32]).trim();
						if (canadianMsrp.equalsIgnoreCase(NULL_STRING) || canadianMsrp.equalsIgnoreCase(EMPTY_STRING)) {
							canadianMsrp = DATA_CANNOT_BE_DISPLAYED;
						} else {
							Double numCanMsrp = Double.parseDouble(canadianMsrp);
							canadianMsrp = DOLLAR + String.format("%.2f", numCanMsrp);
						}
						statusShipmentLineRows.add(canadianMsrp);// Canadian MSRP

						localString = String.valueOf(objectInstance[18]).trim();
						statusShipmentLineRows.add(localString.equalsIgnoreCase(NULL_STRING) ? EMPTY_STRING : localString); // Publisher
						statusShipmentLineRows.add(String.valueOf(objectInstance[19]));// Pub Status
						String statusDetails = String.valueOf(objectInstance[34]).trim();
						statusShipmentLineRows.add(statusDetails.equalsIgnoreCase(NULL_STRING) ? EMPTY_STRING : statusDetails); // Status
						// Details
						statusShipmentLineRows.add(String.valueOf(objectInstance[20]));// Order Source
						statusShipmentLineRows.add(String.valueOf(objectInstance[21]));// Account Name
						statusShipmentLineRows.add(String.valueOf(objectInstance[22]));// Account Number

						String finalDestinationName = String.valueOf(objectInstance[28]).trim();
						String shipToName = String.valueOf(objectInstance[24]).trim();
						String destinationName = EMPTY_STRING;
						String destinationAddress = EMPTY_STRING;
						if (!finalDestinationName.equalsIgnoreCase(NULL_STRING)
								&& !finalDestinationName.equalsIgnoreCase(EMPTY_STRING)) {
							destinationName = finalDestinationName;
							destinationAddress = constructDestination(destinationName,
									String.valueOf(objectInstance[29]), String.valueOf(objectInstance[30]),
									String.valueOf(objectInstance[31]));
						} else if (!shipToName.equalsIgnoreCase(NULL_STRING) && !shipToName.equalsIgnoreCase(EMPTY_STRING)) {
							destinationName = shipToName;
							destinationAddress = constructDestination(destinationName,
									String.valueOf(objectInstance[25]), String.valueOf(objectInstance[26]),
									String.valueOf(objectInstance[27]));
						}
						statusShipmentLineRows.add(destinationName); // Destination

						String shiptoNumberStr = String.valueOf(objectInstance[23]);
						int shiptoNumber = 0;
						if (!(shiptoNumberStr.equalsIgnoreCase(NULL_STRING) || shiptoNumberStr.equalsIgnoreCase(EMPTY_STRING))) {
							shiptoNumber = Integer.parseInt(shiptoNumberStr);
						}
						statusShipmentLineRows.add(shiptoNumber > 0 ? String.valueOf(shiptoNumber) : EMPTY_STRING); // Store #
						statusShipmentLineRows.add(destinationAddress); // Destination Address
						sheetData.add(statusShipmentLineRows);
						indexMap.put(mapKey, index);
						index++;
					}

				}
			}

		} else {

			if (exportType.equalsIgnoreCase("credits")) {
				headers = creditCols;
				columnStyle = creditStyles;
				sheet = workbook.createSheet("CREDITS EXPORT");
			} else if (exportType.equalsIgnoreCase("debits")) {
				headers = debitCols;
				columnStyle = debitStyles;
				sheet = workbook.createSheet("DEBITS EXPORT");
			} else if (exportType.equalsIgnoreCase(TRANSACTIONS)) {
				headers = transactionCols;
				columnStyle = transactionStyles;
				sheet = workbook.createSheet("TRANSACTIONS EXPORT");
			} else { // invoices or
				headers = invoiceCols;
				columnStyle = invoiceStyles;
				sheet = workbook.createSheet("INVOICE EXPORT");
			}
			Map<String, String> hBGAddressMap = portalMappingsUtil.getHBGAddressMap();
			for (Object[] objectInstance : searchResults) {
				String unFormattedDate;
				List<Object> invoiceHeaderList = new ArrayList<>();

				if (exportType.equalsIgnoreCase(TRANSACTIONS)) { // Transaction Type for trantions only
					String transactionType = String.valueOf(objectInstance[55]).trim();
					switch (transactionType) {
						case "INV":
							invoiceHeaderList.add("Invoice");
							break;
						case "DBN":
							invoiceHeaderList.add("Debit");
							break;
						case "CRN":
							invoiceHeaderList.add("Credit");
							break;
						default:
							invoiceHeaderList.add("Transaction");
							break;
					}

				}

				unFormattedDate = String.valueOf(objectInstance[18]).trim();
				if (!(unFormattedDate.equalsIgnoreCase(NULL_STRING) || unFormattedDate.equalsIgnoreCase(EMPTY_STRING))) {
					invoiceHeaderList.add(formatDate(osdDateFormatter.parse(unFormattedDate)));// Invoice date
				} else {
					invoiceHeaderList.add(EMPTY_STRING);
				}

				invoiceHeaderList.add(String.valueOf(objectInstance[0])); // Invoice Number
				invoiceHeaderList.add(String.valueOf(objectInstance[1])); // PO
				String orderNumber = String.valueOf(objectInstance[2]).trim();
				invoiceHeaderList.add(orderNumber.equalsIgnoreCase(NULL_STRING) ? EMPTY_STRING : orderNumber); // Order #
				unFormattedDate = String.valueOf(objectInstance[3]).trim();
				if (!(unFormattedDate.equalsIgnoreCase(NULL_STRING) || unFormattedDate.equalsIgnoreCase(EMPTY_STRING))) {
					invoiceHeaderList.add(formatDate(osdDateFormatter.parse(unFormattedDate.split(SPACE_STRING)[0])));// Order Received Date
				} else {
					invoiceHeaderList.add(EMPTY_STRING);
				}
				unFormattedDate = String.valueOf(objectInstance[4]).trim();
				if (!(unFormattedDate.equalsIgnoreCase(NULL_STRING) || unFormattedDate.equalsIgnoreCase(EMPTY_STRING))) {
					invoiceHeaderList.add(formatDate(osdDateFormatter.parse(unFormattedDate.split(SPACE_STRING)[0])));// Order Received Date
				} else {
					invoiceHeaderList.add(EMPTY_STRING);
				}
				String orderStatus = String.valueOf(objectInstance[5]).trim();
				invoiceHeaderList.add(orderStatus.equalsIgnoreCase(NULL_STRING) ? EMPTY_STRING : orderStatus); // Order Status
				String orderSource = String.valueOf(objectInstance[6]).trim();
				invoiceHeaderList.add(orderSource.equalsIgnoreCase(NULL_STRING) ? EMPTY_STRING : orderSource); // Order Source
				String accountName = String.valueOf(objectInstance[7]).trim();
				invoiceHeaderList.add(accountName.equalsIgnoreCase(NULL_STRING) ? EMPTY_STRING : accountName); // Account Name
				String accountNumber = String.valueOf(objectInstance[8]).trim();
				invoiceHeaderList.add(accountNumber.equalsIgnoreCase(NULL_STRING) ? EMPTY_STRING : accountNumber); // Account Number
				String offerCodes = String.valueOf(objectInstance[54]).trim();
				invoiceHeaderList.add(offerCodes.equalsIgnoreCase(NULL_STRING) ? "N/A" : offerCodes); // Offer Code
				String reasoncode = String.valueOf(objectInstance[56]).trim();
				invoiceHeaderList.add(reasoncode.equalsIgnoreCase(NULL_STRING) ? EMPTY_STRING : reasoncode); // Reason code
				String finalDestinationName = String.valueOf(objectInstance[9]).trim();
				String shipToName = String.valueOf(objectInstance[13]).trim();
				String destinationName = EMPTY_STRING;
				String destinationAddress = EMPTY_STRING;
				if (!finalDestinationName.equalsIgnoreCase(NULL_STRING) && !finalDestinationName.equalsIgnoreCase(EMPTY_STRING)) {
					destinationName = finalDestinationName;
					destinationAddress = constructDestination(destinationName, String.valueOf(objectInstance[10]),
							String.valueOf(objectInstance[11]), String.valueOf(objectInstance[12]));
				} else if (!shipToName.equalsIgnoreCase(NULL_STRING) && !shipToName.equalsIgnoreCase(EMPTY_STRING)) {
					destinationName = shipToName;
					destinationAddress = constructDestination(destinationName, String.valueOf(objectInstance[14]),
							String.valueOf(objectInstance[15]), String.valueOf(objectInstance[16]));
				}
				invoiceHeaderList.add(destinationName); // Destination
				String shiptoNumberStr = String.valueOf(objectInstance[17]);

				int shiptoNumber = 0;
				if (!(shiptoNumberStr.equalsIgnoreCase(NULL_STRING) || shiptoNumberStr.equalsIgnoreCase(EMPTY_STRING))) {
					shiptoNumber = Integer.parseInt(shiptoNumberStr);
				}
				invoiceHeaderList.add(shiptoNumber > 0 ? String.valueOf(shiptoNumber) : EMPTY_STRING); // Store #
				invoiceHeaderList.add(destinationAddress); // Destination Address

				BigDecimal bd2;
				String totalAmtStr = String.valueOf(objectInstance[19]).trim();
				double totalAmount = 0.0;
				double prepaidAmount = 0.0;
				if (extRepId > 0) {
					invoiceHeaderList.add(ApplicationConstant.ENCRYPTED_AMOUNT);// Total Amount
				} else if (totalAmtStr != null && !totalAmtStr.equalsIgnoreCase(NULL_STRING)
						&& !totalAmtStr.equalsIgnoreCase(EMPTY_STRING)) {
					bd2 = new BigDecimal(totalAmtStr);
					totalAmount = bd2.doubleValue();
					invoiceHeaderList.add(totalAmount); // Total Amount
				} else {
					invoiceHeaderList.add(totalAmount); // Total Amount
				}
				
				String prepaidAmtStr = String.valueOf(objectInstance[20]).trim();
				if (extRepId > 0) {
					invoiceHeaderList.add(ApplicationConstant.ENCRYPTED_AMOUNT);// Pre-paid Amount
				} else if (prepaidAmtStr != null && !prepaidAmtStr.equalsIgnoreCase(NULL_STRING)
						&& !prepaidAmtStr.equalsIgnoreCase(EMPTY_STRING)) {
					bd2 = new BigDecimal(prepaidAmtStr);
					prepaidAmount = bd2.doubleValue();
					invoiceHeaderList.add(prepaidAmount); // Pre-paid Amount
				} else {
					invoiceHeaderList.add(prepaidAmount); // Pre-paid Amount
				}
				
				if (extRepId > 0) {
					invoiceHeaderList.add(ApplicationConstant.ENCRYPTED_AMOUNT);// Amount Due
				} else {
					double amountDue = totalAmount - prepaidAmount;
					invoiceHeaderList.add(amountDue); // Amount Due
				}
				
				invoiceHeaderList.add(hBGAddressMap.get("duns")); // DUNS
				invoiceHeaderList.add(hBGAddressMap.get("tin")); // TIN
				String billedIn = String.valueOf(objectInstance[21]).trim();
				if (billedIn.equalsIgnoreCase("CAD")) {
					billedIn = "Canadian Dollars";
				} else {
					billedIn = "US Dollars";
				}
				invoiceHeaderList.add(billedIn); // Billed in
				String shipTo = constructAddress(String.valueOf(objectInstance[22]), String.valueOf(objectInstance[23]),
						String.valueOf(objectInstance[24]), String.valueOf(objectInstance[25]),
						String.valueOf(objectInstance[26]), String.valueOf(objectInstance[27]),
						String.valueOf(objectInstance[28]), String.valueOf(objectInstance[29]));
				String finalDest = constructAddress(String.valueOf(objectInstance[30]),
						String.valueOf(objectInstance[31]), String.valueOf(objectInstance[32]),
						String.valueOf(objectInstance[33]), String.valueOf(objectInstance[34]),
						String.valueOf(objectInstance[35]), String.valueOf(objectInstance[36]),
						String.valueOf(objectInstance[37]));
				String billto = constructAddress(String.valueOf(objectInstance[38]), String.valueOf(objectInstance[39]),
						String.valueOf(objectInstance[40]), String.valueOf(objectInstance[41]),
						String.valueOf(objectInstance[42]), String.valueOf(objectInstance[43]),
						String.valueOf(objectInstance[44]), String.valueOf(objectInstance[45]));
				if (finalDest.trim().equalsIgnoreCase(EMPTY_STRING)) {
					finalDest = shipTo;
				}
				invoiceHeaderList.add(finalDest); // Ship To
				invoiceHeaderList.add(billto); // Bill To
				String promocode = String.valueOf(objectInstance[46]);
				if (promocode.equalsIgnoreCase(EMPTY_STRING) || promocode.equalsIgnoreCase(NULL_STRING)) {
					promocode = "N/A";
				}
				invoiceHeaderList.add(promocode); // Promo Code Used on Order
				invoiceHeaderList.add(String.valueOf(objectInstance[47])); // Item Code(ISBN/EAN)
				invoiceHeaderList.add(String.valueOf(objectInstance[48])); // Full Title
				invoiceHeaderList.add(String.valueOf(objectInstance[49])); // Full Author
				String rawString = String.valueOf(objectInstance[50]).trim();
				invoiceHeaderList.add(rawString.equalsIgnoreCase(NULL_STRING) ? "0" : rawString); // Quantity Shipped
				rawString = String.valueOf(objectInstance[51]).trim();
				double unitPrice = 0.0;
				if (!rawString.equalsIgnoreCase(NULL_STRING)) {
					unitPrice = Double.parseDouble(rawString);
				}
				invoiceHeaderList.add(unitPrice); // Unit Price
				rawString = String.valueOf(objectInstance[52]).trim();
				invoiceHeaderList.add(rawString.equalsIgnoreCase(NULL_STRING) ? "0" : rawString);// Discount %
				rawString = String.valueOf(objectInstance[53]).trim();
				double netcharge = 0.0;
				if (!rawString.equalsIgnoreCase(NULL_STRING)) {
					netcharge = Double.parseDouble(rawString);
				}
				invoiceHeaderList.add(netcharge);// Net Charge

				String invoicelineReportingGroupCode = String.valueOf(objectInstance[57]).trim();
				if (permittedGroups.contains(invoicelineReportingGroupCode) || "0".equals(permittedGroups.trim())) {
					sheetData.add(invoiceHeaderList);
				}
			}
		}
		if (sheetData.isEmpty()) {
			List<Object> blankList = new ArrayList<>();
			String[] blankStyle = new String[headers.length];
			for (int x = 0; x < headers.length; x++) {
				blankList.add(EMPTY_STRING);
				blankStyle[x] = TEXT_CELL_FORMAT;
			}
			sheetData.add(blankList);
			columnStyle = blankStyle;
		}
		addFormattedTable(headers, columnStyle, sheetData, sheet, workbook, currentIndex);
	}

	private String constructDestination(String name, String city, String state, String zip) {
		StringBuilder sb = new StringBuilder();
		addField(sb, name);
		addField(sb, city);
		addField(sb, state);
		addField(sb, zip);
		String result = sb.toString();
		result = result.length() - result.lastIndexOf(", ") == 2 ? result.substring(0, result.length() - 2) : result;
		return result;
	}

	private String constructAddress(String name, String addr1, String addr2, String addr3, String addr4, String city,
									String state, String zip) {
		StringBuilder sb = new StringBuilder();
		addField(sb, name);
		addField(sb, addr1);
		addField(sb, addr2);
		addField(sb, addr3);
		addField(sb, addr4);
		addField(sb, city);
		addField(sb, state);
		addField(sb, zip);
		String result = sb.toString();
		result = result.length() - result.lastIndexOf(", ") == 2 ? result.substring(0, result.length() - 2) : result;
		return result;
	}

	private void addField(StringBuilder sb, String field) {
		if (field != null && !field.trim().isEmpty() && !field.trim().equalsIgnoreCase(NULL_STRING)) {
			sb.append(field);
			sb.append(", ");
		}
	}

	private String getStringValueOf(Object object){
		String stringValue = "";
		if(null != object && !"null".equalsIgnoreCase(String.valueOf(object))){
			stringValue = String.valueOf(object).trim();
		}
		return stringValue;
	}

}