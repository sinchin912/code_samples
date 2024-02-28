package com.hbg.rp.search.util;

import com.hbg.rp.model.*;
import com.hbg.rp.search.constants.ApplicationConstant;
import com.hbg.rp.search.constants.ApplicationPropertyReader;
import com.hbg.rp.search.constants.SearchConstant;
import com.hbg.rp.search.dto.ShipmentTrackingDTO;
import com.hbg.rp.search.exceptions.ShipmentTrackingException;
import com.hbg.rp.search.model.LineStatusDTO;
import com.hbg.rp.search.model.OrderDTO;
import com.hbg.rp.search.model.OrderStatusDTO;
import com.hbg.rp.search.model.ShipmentStatusDTO;
import com.hbg.rp.service.InvoiceHeaderLocalServiceUtil;
import com.hbg.rp.service.OrderHeaderLocalServiceUtil;
import com.hbg.rp.service.OrderLineLocalServiceUtil;
import com.hbg.rp.service.ProductLocalServiceUtil;
import com.hbg.rp.service.ShipmentLocalServiceUtil;
import com.hbg.rp.service.SpecialOfferLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.portlet.PortletRequest;
import javax.portlet.PortletSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * @author ravi.kumar
 *
 */
@Component
public class OrderUtil implements IOrderUtil {

	private static String orderStatuses = ApplicationPropertyReader.getProperty("ORDER_STATUSES");

	private static String inProgressStatusesProp = ApplicationPropertyReader.getProperty("IN_PROGRESS_STATUSES");
	private static final Set<String> inProgressStatuses = new HashSet<>(
			Arrays.asList(inProgressStatusesProp.split("\\s*,\\s*")));

	private static final String PARAM_LOGGED_USER_ID = "loggedUserId";
	private static final String PARAM_HAS_ALL_ACCOUNTS = "hasAllAccounts";
	private static final String PARAM_LIFERAY_SHARED_USER_ALL_ACCOUNTS = "LIFERAY_SHARED_user_all_accounts";
	private static final String STATUS_RELEASED = "RELEASED";
	private static final String STATUS_CREDIT_HOLD = "CREDIT HOLD";
	private static final String STATUS_BACKORDERED = "BACKORDERED";
	private static final String STATUS_CANCELLED = "CANCELLED";
	private static final String STATUS_DELETED = "DELETED";
	
	@Autowired
	private ICommonUtil commonUtil;
	
	@Autowired
	private IDateUtil dateUtil;

	@Autowired
	private IShipmentUtil shipmentUtil;
	
	@Autowired
	private IShipmentTrackingUtil shipmentTrackingUtil;

	/*
	 * Empty Constructor.
	 */
	public OrderUtil() {
		// default constructor
	}

	/**
	 * 
	 * @return
	 * @throws SystemException
	 */
	@Override
	public Map<String, Object> getPreRequisiteModel() throws SystemException {
		Map<String, Object> model = new HashMap<>();
		model.put(ApplicationConstant.PARAM_STATUSES, Arrays.asList(orderStatuses.split("\\s*,\\s*")));
		return model;
	}



	/**
	 * 
	 * @param orderSearchCriteriaMap
	 * @return
	 * @throws SystemException
	 */
	@Override
	public List<OrderDTO> getOrderHeaders(Map<String, String> orderSearchCriteriaMap, long extRepId, boolean hasAllTerritories)
			throws SystemException {
		List<OrderDTO> orderDTOData = new ArrayList<>();
		if (null != orderSearchCriteriaMap && (orderSearchCriteriaMap.get(ApplicationConstant.ITEM_CODE) != null
				&& orderSearchCriteriaMap.get(ApplicationConstant.HIDDEN_ITEM_CODE) != null)) {
			String itemCode = orderSearchCriteriaMap.get(ApplicationConstant.ITEM_CODE);
			String hiddenItemCode = orderSearchCriteriaMap.get(ApplicationConstant.HIDDEN_ITEM_CODE);
			if (!itemCode.equals(hiddenItemCode)) {
				// Fetch ISBN from product
				List<Product> productData = ProductLocalServiceUtil.getConvertedISBN(orderSearchCriteriaMap);
				if (null != productData && !productData.isEmpty())
					orderSearchCriteriaMap.put(ApplicationConstant.ITEM_CODE, productData.get(0) + "");
			}
		}

		List<OrderHeader> orderHeaderData = OrderHeaderLocalServiceUtil.getOrderHeaders(orderSearchCriteriaMap,
				ApplicationConstant.PAGE_SIZE, extRepId,hasAllTerritories);
		if (null != orderSearchCriteriaMap) {
			String itemCode = orderSearchCriteriaMap.get(ApplicationConstant.ITEM_CODE);
			orderDTOData = createOrderDTOList(orderHeaderData, itemCode,extRepId, hasAllTerritories);
		}
		calculateOrderDetailList(orderDTOData);
		return orderDTOData;
	}
	
	/**
	 * 
	 * @param headerData
	 * @param itemCode
	 * @return
	 * @throws SystemException
	 */
	@Override
	public List<OrderDTO> createOrderDTOList(List<OrderHeader> headerData, String itemCode, long extRepId, boolean hasAllTerritories)
			throws SystemException {

		List<OrderDTO> orderDtoList = new ArrayList<>();

		List<Long> orderKeys = new ArrayList<>();
		List<Long> shipToKeys = new ArrayList<>();

		for (OrderHeader order : headerData) {
			orderKeys.add(order.getOrderHeaderId());
			if (order.getShipToKey() > 0) {
				shipToKeys.add(order.getShipToKey());
			}
		}

		Map<Long, List<OrderLine>> lineHeaderMap = new HashMap<>();
		if (!"".equals(itemCode) && null != itemCode && !orderKeys.isEmpty()) {
			List<OrderLine> orderLinesWithLongTitle = OrderLineLocalServiceUtil.getOrderLinesForAllOrders(orderKeys, itemCode, extRepId, hasAllTerritories);
			for (OrderLine line : orderLinesWithLongTitle) {
				List<OrderLine> ol = new ArrayList<>();
				ol.add(line);
				if (lineHeaderMap.get(line.getOrderHeaderId()) != null) {
					ol.addAll(lineHeaderMap.get(line.getOrderHeaderId()));
				}
				lineHeaderMap.put(line.getOrderHeaderId(), ol);
			}
		}

		for (OrderHeader header : headerData) {
			OrderDTO orderDTO = new OrderDTO();
			orderDTO.setOrderLines(lineHeaderMap.get(header.getOrderHeaderId()));
			orderDTO.setOrderHeader(header);
			orderDtoList.add(orderDTO);
		}
		return orderDtoList;
	}
	
	@Override
	public void calculateOrderDetailList(List<OrderDTO> orderDTOList){
		if(orderDTOList != null){
			for(OrderDTO orderDTO: orderDTOList){
				calculateOrderDetail(orderDTO);
			}
		}
	}
	
	@Override
	public void calculateOrderDetail(OrderDTO orderDTO) {
		if( orderDTO != null && orderDTO.getOrderHeader()!=null){
			if("Cancelled".equalsIgnoreCase(orderDTO.getOrderHeader().getOrderStatus()) 
					|| "Deleted".equalsIgnoreCase(orderDTO.getOrderHeader().getOrderStatus())) {
				orderDTO.setStatusMessage("Please contact Customer Service for more information");
				orderDTO.setStatusText("CANCELED");
			} else if ("Shipped with Exceptions".equalsIgnoreCase(orderDTO.getOrderHeader().getOrderStatus())) {
				orderDTO.setStatusMessage("We consider this order complete");
				orderDTO.setStatusText(orderDTO.getOrderHeader().getOrderStatus());
			} else {
				orderDTO.setStatusText(orderDTO.getOrderHeader().getOrderStatus());
			}
			
			if("edi order".equalsIgnoreCase(orderDTO.getOrderHeader().getOrderSource())) {
				orderDTO.getOrderHeader().setOrderSource("EDI Order");
			}
			
			if(orderDTO.getOrderHeader().getDestinationName().isEmpty()){
				orderDTO.setDestinationName(orderDTO.getOrderHeader().getShiptoName());
				orderDTO.setDestinationAddress(orderDTO.getOrderHeader().getShiptoName()+", "+orderDTO.getOrderHeader().getShiptoCity()+", "+orderDTO.getOrderHeader().getShiptoState());
				orderDTO.setDestinationZip(orderDTO.getOrderHeader().getShiptoZip());
			} else{
				orderDTO.setDestinationName(orderDTO.getOrderHeader().getDestinationName());
				orderDTO.setDestinationAddress(orderDTO.getOrderHeader().getDestinationName()+", "+orderDTO.getOrderHeader().getDestinationCity()+", "+orderDTO.getOrderHeader().getDestinationState());
				orderDTO.setDestinationZip(orderDTO.getOrderHeader().getDestinationZip());
			}
			
			if(orderDTO.getSpecialOffers() != null && !orderDTO.getSpecialOffers().isEmpty()) {
				List<String> offers = new ArrayList<>();
				for(SpecialOffer offer: orderDTO.getSpecialOffers()){
					offers.add(offer.getOfferCode());
				}
				orderDTO.setOfferCode(StringUtils.join(offers, ","));
			} else{
				orderDTO.setOfferCode("N/A");
			}
		}
	}

	@Override
	public Map<String, String> getOnSaleDateMap(List<OrderLine> orderLines) throws SystemException {
		String statusCodeNYP = "NYP";
		String statusCodeOS = "OS";
		ArrayList<String> isbns = new ArrayList<>();
		for (OrderLine orderLine : orderLines) {
			//Logic modified for NRP-2629
			String pubStatusCode = orderLine.getPubStatusCode() != null ? orderLine.getPubStatusCode() : "";
			if (pubStatusCode.equalsIgnoreCase(statusCodeNYP) || pubStatusCode.equalsIgnoreCase(statusCodeOS)) {
				isbns.add(orderLine.getIsbn());
			}
		}
		// Find OnSaleDate for these isbns.
		Map<String, String> isbnOsdMap = new HashMap<>();
		if (!isbns.isEmpty()) {
			List<Product> products = ProductLocalServiceUtil.getProducts(isbns);
			if (products != null && !products.isEmpty()) {
				for (Product product : products) {
					isbnOsdMap.put(product.getIsbn(), product.getOnSaleDate());
				}
			}
		}
		return isbnOsdMap;
	}

	/**
	 * 
	 * @param refNo
	 * @return
	 * @throws SystemException
	 */
	@Override
	public OrderDTO getOrderDetailData(String refNo, boolean linesRequired, PortletRequest request, long extRepId, boolean hasAllTerritories) throws SystemException {
		logger.info(" getOrderDetailData() << ");

		OrderDTO orderDTO = new OrderDTO();
		Map<String, String> criteriaMap = new HashMap<>();
		
		criteriaMap.put("refno", refNo);
		criteriaMap.put(PARAM_LOGGED_USER_ID, commonUtil.getLoggedInUserId(request));
		criteriaMap.put(PARAM_HAS_ALL_ACCOUNTS, String.valueOf(request.getPortletSession().getAttribute(PARAM_LIFERAY_SHARED_USER_ALL_ACCOUNTS, PortletSession.APPLICATION_SCOPE)));

		List<OrderHeader> orderHeaderData = OrderHeaderLocalServiceUtil.getOrderHeaders(criteriaMap, 10, extRepId, hasAllTerritories);

		if (!orderHeaderData.isEmpty()) {
			orderDTO.setOrderHeader(orderHeaderData.get(0));
			if (linesRequired) {
				List<OrderLine> orderLinesWithLongTitle = OrderLineLocalServiceUtil
						.getOrderLinesByHeaderId(orderHeaderData.get(0).getOrderHeaderId(),extRepId, hasAllTerritories);
				orderDTO.setOrderLines(orderLinesWithLongTitle);
				List<SpecialOffer> specialOffersForOrder = SpecialOfferLocalServiceUtil
						.getOffersByHeaderKey(orderHeaderData.get(0).getOrderHeaderId());
				orderDTO.setSpecialOffers(specialOffersForOrder);
			}
		}
		calculateOrderDetail(orderDTO);

		return orderDTO;
	}

	/**
	 *
	 * @param orderSearchCriteriaMap
	 * @param pageName
	 * @param extRepId
	 * @return
	 * @throws SystemException
	 */
	@Override
	public List<OrderDTO> getOrderHeadersForDefaultLanding(Map<String, String> orderSearchCriteriaMap, String pageName, long extRepId, boolean hasAllTerritories)
			throws SystemException {
		List<OrderDTO> orderDTOData;
		List<OrderHeader> orderHeaderData = pageName.equalsIgnoreCase(ApplicationConstant.PAGE_NAME_RECENT_ACTIVITY)
				? OrderHeaderLocalServiceUtil.getOrderHeaders(orderSearchCriteriaMap, 6, extRepId,hasAllTerritories)
				: OrderHeaderLocalServiceUtil.getOrderHeaders(orderSearchCriteriaMap, 10, extRepId,hasAllTerritories);
		String itemCode = orderSearchCriteriaMap.get(ApplicationConstant.ITEM_CODE);
		orderDTOData = createOrderDTOList(orderHeaderData, itemCode,extRepId, hasAllTerritories);
		calculateOrderDetailList(orderDTOData);
		return orderDTOData;
	}
	
	/**
	 * This method will set the order lines grouped by statuses.
	 * 
	 * @param orderHeaderData
	 * @param orderStatusDTO
	 * @throws SystemException
	 * @throws PortalException 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void setOrderStatusData(List<OrderHeader> orderHeaderData, OrderStatusDTO orderStatusDTO, PortletRequest request,long extRepId, boolean hasAllTerritories)
			throws SystemException, PortalException {

		/* Fetch order lines for a order */
		if(orderHeaderData != null && !orderHeaderData.isEmpty() && orderHeaderData.get(0) != null) {
			OrderHeader orderHeader = orderHeaderData.get(0);
			long refNo = orderHeader.getReferenceNbr();
			orderStatusDTO.setOrderRefNo(refNo);
			List<OrderLine> defaultOrderLines = OrderLineLocalServiceUtil
					.getOrderLinesByHeaderId(orderHeader.getOrderHeaderId(),extRepId, hasAllTerritories);
			List<OrderLine> orderLines = addLongTitle(defaultOrderLines);
			List<OrderLine> releasedOrderLines = new ArrayList<>();
			List<OrderLine> inProgressOrderLines = new ArrayList<>();
			List<OrderLine> actionRequiredOrderLines = new ArrayList<>();
			List<OrderLine> backOrderedOrderLines = new ArrayList<>();
			List<OrderLine> cancelledOrderLines = new ArrayList<>();
			List<LineStatusDTO> orderLineData = new LinkedList<>();
			List<ShipmentStatusDTO> shipments = new LinkedList<>();

			for (OrderLine orderLine : orderLines) {
				if (!StringUtils.isEmpty(orderLine.getLineStatus())) {
					String orderLineStatus = orderLine.getLineStatus();
					/* Fetch shipments for a order */
					if (STATUS_RELEASED.equalsIgnoreCase(orderLineStatus)) {
						releasedOrderLines.add(orderLine);
					} else if (inProgressStatuses.contains(orderLineStatus.toUpperCase())) {
						inProgressOrderLines.add(orderLine);
					} else if (STATUS_CREDIT_HOLD.equalsIgnoreCase(orderLineStatus)) {
						actionRequiredOrderLines.add(orderLine);
					} else if (STATUS_BACKORDERED.equalsIgnoreCase(orderLineStatus)) {
						backOrderedOrderLines.add(orderLine);
					} else if (STATUS_CANCELLED.equalsIgnoreCase(orderLineStatus)
							|| STATUS_DELETED.equalsIgnoreCase(orderLineStatus)) {
						cancelledOrderLines.add(orderLine);
					}
				}
			}

			/* Fetch shipments for an order ref no */
			Map<String, String> criteriaMap = new HashMap<>();
			criteriaMap.put(ApplicationConstant.PARAM_SEARCH_SHIP_REFERENCE_NBR, Long.toString(refNo));
			criteriaMap.put(PARAM_LOGGED_USER_ID, commonUtil.getLoggedInUserId(request));
			criteriaMap.put(PARAM_HAS_ALL_ACCOUNTS, String.valueOf(request.getPortletSession().getAttribute(PARAM_LIFERAY_SHARED_USER_ALL_ACCOUNTS, PortletSession.APPLICATION_SCOPE)));

			Map<String, String> requestMap = (Map<String, String>) request.getAttribute("criteriaMap");
			String invoiceNo = requestMap.get(ApplicationConstant.PARAM_SEARCH_SHIP_INVOICE_NBR);
			if(invoiceNo != null && !invoiceNo.isEmpty()) {
				criteriaMap.put(ApplicationConstant.PARAM_SEARCH_SHIP_INVOICE_NBR, invoiceNo);
			}
			
			Map<String, Object> shipmentMap = ShipmentLocalServiceUtil.getShipments(criteriaMap,extRepId,hasAllTerritories);
			List<Shipment> shipmentData = ShipmentLocalServiceUtil.getShipmentsList(criteriaMap,extRepId,hasAllTerritories);
			Map<String, String> shipmentCriteriaMap = new HashMap<>();
			Map<String, List<ShipmentLine>> shipmentLinesMap = new HashMap<>();
			Map<String, ShipmentTrackingDTO> shipmentTrackingMap = new HashMap<>();

			
			Map<Long, Date> invoiceHeaderDateMap = new HashMap<>();
			for(Shipment shipment : shipmentData) {

				InvoiceHeader invoiceHeader = InvoiceHeaderLocalServiceUtil.getInvoiceHeader(shipment.getInvoiceHeaderId());
				if(StringUtils.isNotBlank(invoiceHeader.getInvoiceDate())) {
					Date invoiceDate = dateUtil.stringToDate(invoiceHeader.getInvoiceDate());
					invoiceHeaderDateMap.put(shipment.getInvoiceHeaderId(), invoiceDate);
				}

				shipmentCriteriaMap.put(SearchConstant.SHIPMENT_HEADER_ID, String.valueOf(shipment.getShipmentHeaderId()));
				List<ShipmentLine> shipmentLines = shipmentUtil.getShipmentLines(shipmentCriteriaMap,extRepId,hasAllTerritories);
				shipmentLinesMap.put(String.valueOf(shipment.getShipmentHeaderId()), shipmentLines);

				String shipmentCarrier = shipment.getCarrierId();
				String trackingNumber = shipment.getTrackingNumber();
				String orderHeaderId = String.valueOf(shipment.getOrderHeaderId());
				SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy");
				String shipDate = sdf.format(shipment.getShipDate());
				String shipmentHeaderId = String.valueOf(shipment.getShipmentHeaderId());
				String loggedUserId = commonUtil.getLoggedInUserId(request);
				try{
					ShipmentTrackingDTO shipmentTrackingDTO = new ShipmentTrackingDTO();
					shipmentTrackingDTO.setZENDESK(ShipmentLocalServiceUtil.isZendeskInitiated(loggedUserId, shipmentHeaderId));
					shipmentTrackingDTO.setTrackingData(shipmentTrackingUtil.getShipmentTrackingDetails(shipmentCarrier, trackingNumber, orderHeaderId,shipDate));
					shipmentTrackingMap.put(shipmentHeaderId, shipmentTrackingDTO);
				}catch(ShipmentTrackingException se) {
					//Do nothing...
				}
			}
			
			Map<String,Boolean> orderLinesExists = new HashMap<>();
			if (!shipmentData.isEmpty()) {
				ShipmentStatusDTO statusDTO = new ShipmentStatusDTO();
				statusDTO.setName(ApplicationConstant.ORDER_LINE_STATUS_SHIPPED);
				statusDTO.setShipments(shipmentData);
				statusDTO.setInvoiceHeaderDateMap(invoiceHeaderDateMap);
				shipments.add(statusDTO);
			}
			if (!releasedOrderLines.isEmpty()) {
				orderLineData.add(createLineStatusDTO(ApplicationConstant.PREPARING_TO_SHIP, releasedOrderLines,
						orderHeader.getReleasedQty()));
				orderLinesExists.put(ApplicationConstant.PREPARING_TO_SHIP, true);
			}
			if (!inProgressOrderLines.isEmpty()) {
				orderLineData.add(createLineStatusDTO(ApplicationConstant.IN_PROGRESS, inProgressOrderLines,
						orderHeader.getInProcessQty()));
				orderLinesExists.put(ApplicationConstant.IN_PROGRESS, true);
			}
			if (!actionRequiredOrderLines.isEmpty()) {
				orderLineData.add(createLineStatusDTO(ApplicationConstant.ACTION_REQUIRED, actionRequiredOrderLines,
						orderHeader.getCreditHoldQty()));
				orderLinesExists.put(ApplicationConstant.ACTION_REQUIRED, true);
			}
			if (!backOrderedOrderLines.isEmpty()) {
				orderLineData.add(createLineStatusDTO(ApplicationConstant.BACKORDERED, backOrderedOrderLines,
						orderHeader.getBackorderedQty()));
				orderLinesExists.put(ApplicationConstant.BACKORDERED, true);
			}
			if (!cancelledOrderLines.isEmpty()) {
				orderLineData.add(createLineStatusDTO(ApplicationConstant.CANCELED, cancelledOrderLines,
						orderHeader.getCancelQty()));
				orderLinesExists.put(ApplicationConstant.CANCELED, true);
			}
			orderStatusDTO.setOrderLinesExists(orderLinesExists);
			orderStatusDTO.setSupportedCarrierData(shipmentTrackingUtil.getAllCarrierDetails());
			orderStatusDTO.setOrderLines(orderLineData);
			orderStatusDTO.setShipments(shipments);
			orderStatusDTO.setShipmentLine(shipmentLinesMap);
			orderStatusDTO.setShipmentTracking(shipmentTrackingMap);
			orderStatusDTO.setInvoiceHeaderIds((List<Long>)shipmentMap.get("invoiceHeaderIds"));
		}
	}
	
	private LineStatusDTO createLineStatusDTO(String status, List<OrderLine> orderLines, long qty) {
		LineStatusDTO lineStatusDTO = new LineStatusDTO();
		lineStatusDTO.setName(status);
		lineStatusDTO.setLines(orderLines);
		lineStatusDTO.setTotal(qty);
		return lineStatusDTO;
	}
	
	/**
	 * 
	 * @param orderLines
	 * @return
	 * @throws SystemException
	 */
	private List<OrderLine> addLongTitle(List<OrderLine> orderLines) throws SystemException {
		if (!orderLines.isEmpty()) {
			List<String> isbnList = new ArrayList<>();
			for (OrderLine orderLine : orderLines) {
				isbnList.add(orderLine.getIsbn());
			}
			Map<String, Product> productTitleMap = commonUtil.getProductTitleMap(isbnList);
			for (OrderLine orderLine : orderLines) {
				if(orderLine.getIsbn()!= null && productTitleMap.get(orderLine.getIsbn())!= null) {
					if(productTitleMap.get(orderLine.getIsbn()).getTitle()!= null) {
						orderLine.setShortTitle(productTitleMap.get(orderLine.getIsbn()).getTitle());
					}
					else {
						orderLine.setShortTitle("");
					}
					if(productTitleMap.get(orderLine.getIsbn()).getByLine()!= null) {
						orderLine.setShortAuthor(productTitleMap.get(orderLine.getIsbn()).getByLine()); //NRP-2876 Change Short Author value to original Author in NRP
					}
					else {
						orderLine.setShortAuthor("");
					}
				}
				else {
					orderLine.setShortTitle("");
					orderLine.setShortAuthor("");
				}
			}
		}
		return orderLines;
	}

	/**
	 * This method will set the order lines grouped by statuses.
	 * @param orderHeader
	 * @param orderLines
	 * @param orderStatusDTO
	 * @param request
	 * @throws SystemException
	 * @throws PortalException
	 */
	@SuppressWarnings("unchecked")
	public void setOrderAndLineStatusData(OrderHeader orderHeader, List<OrderLine> orderLines,
			OrderStatusDTO orderStatusDTO, PortletRequest request, long extRepId, boolean hasAllTerritories) throws SystemException, PortalException {
		logger.info("=************* setOrderAndLineStatusData start*************" + java.time.LocalDateTime.now());
		/* Fetch order lines for a order */
		if (orderHeader != null) {
			long refNo = orderHeader.getReferenceNbr();
			orderStatusDTO.setOrderRefNo(refNo);
			List<OrderLine> releasedOrderLines = new ArrayList<>();
			List<OrderLine> inProgressOrderLines = new ArrayList<>();
			List<OrderLine> actionRequiredOrderLines = new ArrayList<>();
			List<OrderLine> backOrderedOrderLines = new ArrayList<>();
			List<OrderLine> cancelledOrderLines = new ArrayList<>();
			List<LineStatusDTO> orderLineData = new LinkedList<>();
			List<ShipmentStatusDTO> shipments = new LinkedList<>();

			for (OrderLine orderLine : orderLines) {
				if (!StringUtils.isEmpty(orderLine.getLineStatus())) {
					String orderLineStatus = orderLine.getLineStatus();
					/* Fetch shipments for a order */
					if (STATUS_RELEASED.equalsIgnoreCase(orderLineStatus)) {
						releasedOrderLines.add(orderLine);
					} else if (inProgressStatuses.contains(orderLineStatus.toUpperCase())) {
						inProgressOrderLines.add(orderLine);
					} else if (STATUS_CREDIT_HOLD.equalsIgnoreCase(orderLineStatus)) {
						actionRequiredOrderLines.add(orderLine);
					} else if (STATUS_BACKORDERED.equalsIgnoreCase(orderLineStatus)) {
						backOrderedOrderLines.add(orderLine);
					} else if (STATUS_CANCELLED.equalsIgnoreCase(orderLineStatus)
							|| STATUS_DELETED.equalsIgnoreCase(orderLineStatus)) {
						cancelledOrderLines.add(orderLine);
					}
				}
			}

			/* Fetch shipments for an order ref no */
			Map<String, String> criteriaMap = new HashMap<>();
			criteriaMap.put(ApplicationConstant.PARAM_SEARCH_SHIP_REFERENCE_NBR, Long.toString(refNo));
			criteriaMap.put(PARAM_LOGGED_USER_ID, commonUtil.getLoggedInUserId(request));
			criteriaMap.put(PARAM_HAS_ALL_ACCOUNTS, String.valueOf(request.getPortletSession()
					.getAttribute(PARAM_LIFERAY_SHARED_USER_ALL_ACCOUNTS, PortletSession.APPLICATION_SCOPE)));

			Map<String, String> requestMap = (Map<String, String>) request.getAttribute("criteriaMap");
			String invoiceNo = requestMap.get(ApplicationConstant.PARAM_SEARCH_SHIP_INVOICE_NBR);
			if (invoiceNo != null && !invoiceNo.isEmpty()) {
				criteriaMap.put(ApplicationConstant.PARAM_SEARCH_SHIP_INVOICE_NBR, invoiceNo);
			}

			Map<String, ShipmentCarrier> supportedCarrierData = shipmentTrackingUtil.getAllCarrierDetails();
			Map<String, Object> shipmentMap = ShipmentLocalServiceUtil.getShipments(criteriaMap,extRepId,hasAllTerritories);
			List<Shipment> shipmentData = ShipmentLocalServiceUtil.getShipmentsList(criteriaMap,extRepId,hasAllTerritories);
			Map<String, String> shipmentCriteriaMap = new HashMap<>();
			Map<String, List<ShipmentLine>> shipmentLinesMap = new HashMap<>();
			Map<String, ShipmentTrackingDTO> shipmentTrackingMap = new HashMap<>();

			Map<Long, Date> invoiceHeaderDateMap = new HashMap<>();
			for (Shipment shipment : shipmentData) {
				InvoiceHeader invoiceHeader = InvoiceHeaderLocalServiceUtil
						.getInvoiceHeader(shipment.getInvoiceHeaderId());
				if (StringUtils.isNotBlank(invoiceHeader.getInvoiceDate())) {
					Date invoiceDate = dateUtil.stringToDate(invoiceHeader.getInvoiceDate());
					invoiceHeaderDateMap.put(shipment.getInvoiceHeaderId(), invoiceDate);
				}

				shipmentCriteriaMap.put(SearchConstant.SHIPMENT_HEADER_ID,
						String.valueOf(shipment.getShipmentHeaderId()));
				List<ShipmentLine> shipmentLines = shipmentUtil.getShipmentLines(shipmentCriteriaMap,extRepId,hasAllTerritories);
				shipmentLinesMap.put(String.valueOf(shipment.getShipmentHeaderId()), shipmentLines);

				String shipmentCarrier = shipment.getCarrierId();
				String trackingNum = shipment.getTrackingNumber();
				String proNum = shipment.getProNumber();
				String bolNum = shipment.getBillOfLadingNumber();
				String finalTrackingNumber;
				String supportTrackingPost = supportedCarrierData.get(shipmentCarrier) != null ? supportedCarrierData.get(shipmentCarrier).getSupportedTrackingType() : "";
				logger.info("Supported tracking type "+supportTrackingPost+" found for carrier code "+shipmentCarrier);
				if("PRO".equalsIgnoreCase(supportTrackingPost)){
					finalTrackingNumber = proNum;
				} else if("BOL".equalsIgnoreCase(supportTrackingPost)){
					finalTrackingNumber = bolNum;
				} else {
					if(!StringUtils.isEmpty(trackingNum)){
						finalTrackingNumber = trackingNum;
					} else if(!StringUtils.isEmpty(proNum)){
						finalTrackingNumber = proNum;
					} else {
						finalTrackingNumber = bolNum;
					}
				}
				logger.info("Final Tracking number for Aftership call is "+finalTrackingNumber);

				String orderHeaderId = String.valueOf(shipment.getOrderHeaderId());
				SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy");
				String shipDate = sdf.format(shipment.getShipDate());
				String shipmentHeaderId = String.valueOf(shipment.getShipmentHeaderId());
				String loggedUserId = commonUtil.getLoggedInUserId(request);
				try {
					ShipmentTrackingDTO shipmentTrackingDTO = new ShipmentTrackingDTO();
					shipmentTrackingDTO
							.setZENDESK(ShipmentLocalServiceUtil.isZendeskInitiated(loggedUserId, shipmentHeaderId));
					shipmentTrackingDTO.setTrackingData(shipmentTrackingUtil.getShipmentTrackingDetails(shipmentCarrier,
							finalTrackingNumber, orderHeaderId, shipDate));
					shipmentTrackingMap.put(shipmentHeaderId, shipmentTrackingDTO);
				} catch (ShipmentTrackingException se) {
					// Do nothing...
				}
			}

			Map<String, Boolean> orderLinesExists = new HashMap<>();
			if (!shipmentData.isEmpty()) {
				ShipmentStatusDTO statusDTO = new ShipmentStatusDTO();
				statusDTO.setName(ApplicationConstant.ORDER_LINE_STATUS_SHIPPED);
				statusDTO.setShipments(shipmentData);
				statusDTO.setInvoiceHeaderDateMap(invoiceHeaderDateMap);
				shipments.add(statusDTO);
			}
			if (!releasedOrderLines.isEmpty()) {
				orderLineData.add(createLineStatusDTO(ApplicationConstant.PREPARING_TO_SHIP, releasedOrderLines,
						orderHeader.getReleasedQty()));
				orderLinesExists.put(ApplicationConstant.PREPARING_TO_SHIP, true);
			}
			if (!inProgressOrderLines.isEmpty()) {
				orderLineData.add(createLineStatusDTO(ApplicationConstant.IN_PROGRESS, inProgressOrderLines,
						orderHeader.getInProcessQty()));
				orderLinesExists.put(ApplicationConstant.IN_PROGRESS, true);
			}
			if (!actionRequiredOrderLines.isEmpty()) {
				orderLineData.add(createLineStatusDTO(ApplicationConstant.ACTION_REQUIRED, actionRequiredOrderLines,
						orderHeader.getCreditHoldQty()));
				orderLinesExists.put(ApplicationConstant.ACTION_REQUIRED, true);
			}
			if (!backOrderedOrderLines.isEmpty()) {
				orderLineData.add(createLineStatusDTO(ApplicationConstant.BACKORDERED, backOrderedOrderLines,
						orderHeader.getBackorderedQty()));
				orderLinesExists.put(ApplicationConstant.BACKORDERED, true);
			}
			if (!cancelledOrderLines.isEmpty()) {
				orderLineData.add(createLineStatusDTO(ApplicationConstant.CANCELED, cancelledOrderLines,
						orderHeader.getCancelQty()));
				orderLinesExists.put(ApplicationConstant.CANCELED, true);
			}
			orderStatusDTO.setOrderLinesExists(orderLinesExists);
			orderStatusDTO.setSupportedCarrierData(supportedCarrierData);
			orderStatusDTO.setOrderLines(orderLineData);
			orderStatusDTO.setShipments(shipments);
			orderStatusDTO.setShipmentLine(shipmentLinesMap);
			orderStatusDTO.setShipmentTracking(shipmentTrackingMap);
			orderStatusDTO.setInvoiceHeaderIds((List<Long>) shipmentMap.get("invoiceHeaderIds"));
			logger.info("=************* setOrderAndLineStatusData end*************" + java.time.LocalDateTime.now());
		}
	}
	
	private static final Log logger = LogFactoryUtil.getLog(OrderUtil.class);

}
