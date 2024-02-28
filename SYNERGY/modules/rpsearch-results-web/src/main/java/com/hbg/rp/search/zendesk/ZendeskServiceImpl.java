package com.hbg.rp.search.zendesk;

import com.hbg.rp.search.constants.ClaimConstants;
import com.hbg.rp.search.constants.ApplicationConstant;
import com.hbg.rp.model.ShipmentLine;
import com.hbg.rp.model.Product;
import com.hbg.rp.search.model.SubmitClaimDTO;
import com.hbg.rp.search.model.SubmitClaimLine;
import com.hbg.rp.search.constants.SearchConstant;
import com.hbg.rp.search.util.ICommonUtil;
import com.hbg.rp.search.zendesk.ClaimItem;
import com.hbg.rp.search.dto.ZendeskClaimDTO;
import com.hbg.rp.search.dto.ZendeskDTO;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.theme.ThemeDisplay;

import org.apache.commons.lang3.StringUtils;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zendesk.client.v2.model.Ticket;

import javax.portlet.ResourceRequest;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * The com.hbg.rp.zendesk.service.ZendeskServiceImpl is the implementaion 
 * class for Zendesk service.
 * @author robin.sharma
 */
@Service
public class ZendeskServiceImpl implements ZendeskService {
private final Log logger = LogFactoryUtil.getLog(this.getClass());
	
	private static final String HBG_ZEN_REST_CLIENT_URI = PropsUtil.get(ApplicationConstant.HBG_ZEN_REST_CLIENT_URI);
	private static final String HBG_ZEN_REST_CLIENT_CLAIM_TICKET_POST_URI = PropsUtil.get(ApplicationConstant.HBG_ZEN_REST_CLIENT_CLAIM_TICKET_POST_URI);

	@Autowired
	private IZendeskServiceHelper zendeskServiceHelper;
	
	@Autowired
	private ICommonUtil commonUtil;
	
	/**
	 * @see ZendeskService#createTicket(ZendeskDTO)
	 */
	@Override
	public Response createTicket(ZendeskDTO zendeskDTO)  {
		logger.info(" createTicket() <<");
		
		Response response = null;
		try {
			logger.info("Subscription Key :" + PropsUtil.get("hbg.zen.rest.client.claim.subscription.key"));
			logger.info("Zendesk URL :" + HBG_ZEN_REST_CLIENT_URI);
	        response = zendeskServiceHelper.getSSLClient().target(HBG_ZEN_REST_CLIENT_URI)
	              .request(MediaType.APPLICATION_JSON).header(ApplicationConstant.SUUBSCRIPTION_KEY, PropsUtil.get("hbg.zen.rest.client.claim.subscription.key"))
	              .post(Entity.entity(zendeskDTO, MediaType.APPLICATION_JSON));
	        
	        logger.info("response: " + response);
		} catch (Exception e) {
			logger.error("Message: " + e.getMessage() + "Stack trace: " + Arrays.toString(e.getStackTrace()), e);
		}
		
		logger.info(" createTicket() >>");
		return response;
	}

	/**
	 * @throws Exception 
	 * @see ZendeskService#createClaimTicket(ZendeskClaimDTO)
	 */
	@Override
	public Response createClaimTicket(ZendeskClaimDTO zendeskClaimDTO) throws Exception  {

		logger.info(" createClaimTicket() <<");
		if(zendeskClaimDTO == null){
			return null;
		}

		zendeskClaimDTO.setSubject(zendeskServiceHelper.getZendeskClaimDTOUpdatedSubject(zendeskClaimDTO));

		Response response = null;
		try {
			response = zendeskServiceHelper.getSSLClient()
					.target(HBG_ZEN_REST_CLIENT_CLAIM_TICKET_POST_URI)
					.request(MediaType.APPLICATION_JSON).header(ApplicationConstant.SUUBSCRIPTION_KEY, PropsUtil.get("hbg.zen.rest.client.claim.subscription.key"))
					.post(Entity.json(zendeskClaimDTO));
					//.post(Entity.entity(zendeskClaimDTO, MediaType.APPLICATION_JSON));
		} catch (Exception e) {
			logger.error("Message: " + e.getMessage() + "Stack trace: " + Arrays.toString(e.getStackTrace()), e);
			throw e;
		}

		logger.info(" createClaimTicket() >>");
		return response;
	}

	/**
	 * @see ZendeskService#getTickets()
	 */
	@Override
	public Iterable<Ticket> getTickets() {
		logger.info(" createTickets() << ");
		
		Iterable<Ticket> iterableTicket = null;
		Response responseFromZen = null;
		try {
			responseFromZen = zendeskServiceHelper.getSSLClient().target(HBG_ZEN_REST_CLIENT_URI)
	              .request(MediaType.APPLICATION_JSON).header(ApplicationConstant.SUUBSCRIPTION_KEY, PropsUtil.get("hbg.zen.rest.client.claim.subscription.key"))
	              .get(Response.class);
	        logger.info("response: " + responseFromZen);     
			if (responseFromZen.getStatus() == 200) {
				iterableTicket = responseFromZen.readEntity(new GenericType<List<Ticket>>() {});
			} else {
				logger.info("Error in creating Zendesk ticket :" + responseFromZen.getStatus());
			}
		} catch (Exception e) {
			logger.error("Message: " + e.getMessage() + "Stack trace: " + Arrays.toString(e.getStackTrace()), e);
		}
			
		logger.info(" createTickets() >> " + iterableTicket);		
		return iterableTicket;
	}

	/**
	 * @see ZendeskService#getTicket(long)
	 */
	@Override
	public Ticket getTicket(long ticketId) {
		logger.info(" getTicket( " + ticketId + " ) <<");	
		
		Ticket ticket = null;
		Response responseFromZen = null;
		try {
			responseFromZen = zendeskServiceHelper
				  .getSSLClient()
				  .target(HBG_ZEN_REST_CLIENT_URI)
				  .path(String.valueOf(ticketId))
	              .request(MediaType.APPLICATION_JSON).header(ApplicationConstant.SUUBSCRIPTION_KEY, PropsUtil.get("hbg.zen.rest.client.claim.subscription.key"))
	              .get(Response.class);
	        logger.info("response: " + responseFromZen);
	      
			if (responseFromZen.getStatus() == 200) {
				ticket = responseFromZen.readEntity(Ticket.class);
			} else {
				logger.info("Error in creating Zendesk ticket :" + responseFromZen.getStatus());
			}
		} catch (Exception e) {
			logger.error("Message: " + e.getMessage() + "Stack trace: " + Arrays.toString(e.getStackTrace()), e);
		}
			
		logger.info(" getTicket() >>");		
		return ticket;
	}
	
/**
	 * Create zendesk data.
	 * 
	 * @param request
	 * @return
	 */
	@Override
	public ZendeskDTO createZendeskData(ResourceRequest request) {
		logger.info(" createZendeskData() <<");
		
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);

		String refNo = ParamUtil.getString(request, ApplicationConstant.REF_NO);
		String poNo = ParamUtil.getString(request, ApplicationConstant.PO_NO);
		String invoiceNo = ParamUtil.getString(request, ApplicationConstant.INVOICE_NO);
		String shipGroup = ParamUtil.getString(request, ApplicationConstant.SHIPGROUP);
		String shipment = ParamUtil.getString(request, ApplicationConstant.SHIPMENT);
		String shipmentMethod = ParamUtil.getString(request, ApplicationConstant.SHIPMENT_METHOD);
		String shipmentHeaderId = ParamUtil.getString(request, ApplicationConstant.SHIPMENT_HEADER_ID);
		String accountName = ParamUtil.getString(request, ApplicationConstant.ACCOUNT_NAME);
		String accountNumber = ParamUtil.getString(request, ApplicationConstant.ACCOUNT_NUMBER);
		String orderRecievedDate = ParamUtil.getString(request, ApplicationConstant.ORDER_RECV_DATE);
		String shipDate = ParamUtil.getString(request, ApplicationConstant.SHIP_DATE);
		String offerCode = ParamUtil.getString(request, ApplicationConstant.OFFER_CODE);
		String orderSource = ParamUtil.getString(request, ApplicationConstant.ORDER_SOURCE);
		String orderProcessedDate = ParamUtil.getString(request, ApplicationConstant.ORDER_PROCESSED_DATE);
		String shiptoNumber = ParamUtil.getString(request, ApplicationConstant.SHIP_TO_NUMBER);
		String destinationAddress = ParamUtil.getString(request, ApplicationConstant.DESITNATION_ADDRESS);

		// Fetch these details from liferay context
		String requesterEmail = themeDisplay.getUser().getEmailAddress();
		logger.info(" requesterEmail: " + requesterEmail);
		String requesterName = themeDisplay.getUser().getFullName();
		logger.info(" requesterName: " + requesterName);

		ZendeskDTO dto = new ZendeskDTO();
		dto.setRequestorName(requesterName);
		dto.setRequestorEmail(requesterEmail);

		StringBuilder sb = new StringBuilder(SearchConstant.ZENDESK_SUBJECT);
		sb.append(accountName);
		sb.append(SearchConstant.SB_TRACKING_REQUEST);
		sb.append(refNo);
		dto.setSubject(sb.toString());

		sb = new StringBuilder();
		sb.append(SearchConstant.SB_PORTAL_ACCOUNT_LOGIN_NAME);
		sb.append(requesterName);
		sb.append(System.lineSeparator());

		sb.append(SearchConstant.SB_EMAIL_ADDRESS);
		sb.append(requesterEmail);
		sb.append(System.lineSeparator());

		sb.append(SearchConstant.SB_ACCOUNT_NAME);
		sb.append(accountName);
		sb.append(System.lineSeparator());

		sb.append(SearchConstant.SB_ACCOUNT_NUMBER);
		sb.append(accountNumber);
		sb.append(System.lineSeparator());

		sb.append(SearchConstant.SB_INVOICE_NUMBER);
		sb.append(invoiceNo);
		sb.append(System.lineSeparator());

		sb.append(SearchConstant.SB_SHIP_GROUP);
		sb.append(shipGroup);
		sb.append(System.lineSeparator());

		sb.append(SearchConstant.SB_PO);
		sb.append(poNo);
		sb.append(System.lineSeparator());

		sb.append(SearchConstant.SB_REF_NO);
		sb.append(refNo);
		sb.append(System.lineSeparator());

		sb.append(SearchConstant.SB_OFFER_CODE);
		sb.append(offerCode);
		sb.append(System.lineSeparator());

		sb.append(SearchConstant.SB_ORDER_SOURCE);
		sb.append(orderSource);
		sb.append(System.lineSeparator());

		sb.append(SearchConstant.SB_ORDER_RECEIVED_DATE);
		sb.append(orderRecievedDate);
		sb.append(System.lineSeparator());

		sb.append(SearchConstant.SB_ORDER_PROCESSED_DATE);
		sb.append(orderProcessedDate);
		sb.append(System.lineSeparator());

		sb.append(SearchConstant.SB_SHIP_DATE);
		sb.append(shipDate);
		sb.append(System.lineSeparator());

		sb.append(SearchConstant.SB_STORE);
		sb.append(shiptoNumber);
		sb.append(System.lineSeparator());

		sb.append(SearchConstant.SB_DESTINATION_ADDR);
		sb.append(destinationAddress);
		sb.append(System.lineSeparator());

		sb.append(SearchConstant.SB_SHIPMENT);
		sb.append(shipment);
		sb.append(System.lineSeparator());

		sb.append(SearchConstant.SB_SHIPMENT_METHOD);
		sb.append(shipmentMethod);
		sb.append(System.lineSeparator());
		String zendeskData = sb.toString();
		logger.info("zendeskData: " + zendeskData);
		
		dto.setDescription(zendeskData);
		if (null != shipmentHeaderId && !shipmentHeaderId.isEmpty())
			dto.setShipmentId(Long.valueOf(shipmentHeaderId));
		
		String userId = String.valueOf(themeDisplay.getUserId());
		logger.info(" themeDisplay.getUserId():  " + userId);
		
		//Commented out as a fix for the NRP-2572
		//dto.setUserId(themeDisplay.getUserId());
		
		logger.info(" createZendeskData() >> " + dto);
		
		return dto;
	}

	@Override
	public ZendeskClaimDTO createZendeskClaimDTO(SubmitClaimDTO submitClaimDTO, String requestorName, Long claimTypeKey) throws SystemException {
	
		ZendeskClaimDTO zendeskClaimDTO = new ZendeskClaimDTO();
		zendeskClaimDTO.setClaimSource(ClaimConstants.CLAIM_SOURCE);
		zendeskClaimDTO.setAccount(submitClaimDTO.getSubmitClaimHeader().getAccountNumber());
		zendeskClaimDTO.setAccountName(submitClaimDTO.getSubmitClaimHeader().getBillToName());
		zendeskClaimDTO.setInvoice(submitClaimDTO.getSubmitClaimHeader().getInvoiceNumber().toString());
		//zendeskClaimDTO.setClaimType(ClaimConstants.ZENDESK_CLAIM_TYPE);
		zendeskClaimDTO.setClaimComments(null != submitClaimDTO.getSubmitClaimHeader().getClaimComments() ? submitClaimDTO.getSubmitClaimHeader().getClaimComments() : "");
		zendeskClaimDTO.setRetailerClaim(null != submitClaimDTO.getSubmitClaimHeader().getRetailerClaimNumber() ? submitClaimDTO.getSubmitClaimHeader().getRetailerClaimNumber() : "");
		zendeskClaimDTO.setRequestorName(requestorName);
		zendeskClaimDTO.setRequestorEmail(submitClaimDTO.getSubmitClaimHeader().getRequestedByEmail());
		zendeskClaimDTO.setSubject(ClaimConstants.ZENDESK_CLAIM_EMAIL_SUBJECT);
		zendeskClaimDTO.setDescription(ClaimConstants.ZENDESK_CLAIM_EMAIL_DESCRPTION);
		zendeskClaimDTO.setUserId(Long.valueOf(submitClaimDTO.getSubmitClaimHeader().getRequestedBy()));
		
		boolean isRshipment = Boolean.FALSE;
		List<ClaimItem> claimItemList = new ArrayList<>();
		BigDecimal totalClaimAmount = BigDecimal.ZERO;
		
		String claimTypeDesc = ClaimConstants.ZENDESK_CLAIM_TYPE;
		List<SubmitClaimLine> defClaimLineList = submitClaimDTO.getSubmitClaimLineList();
		List<SubmitClaimLine> claimLineListWithLongTitle = addLongTitle(defClaimLineList);
		for (SubmitClaimLine submitClaimLine : claimLineListWithLongTitle) {
			if (null != submitClaimLine.getClaimLineNumber() && claimTypeKey.equals(submitClaimLine.getClaimTypeKey())) {
				ClaimItem claimItem = new ClaimItem();
				claimItem.setCode(submitClaimLine.getIsbn());
				claimItem.setQuantityClaimed(submitClaimLine.getClaimQty().toString());
				claimItem.setQuantityShipped(submitClaimLine.getShipQty().toString());
				if (StringUtils.isNotBlank(submitClaimLine.getReshipFlag())) {
					claimItem.setReship(ClaimConstants.RESHIP_FLAG_YES);
					isRshipment = Boolean.TRUE;
				} else {
					claimItem.setReship(ClaimConstants.RESHIP_FLAG_NO);
				}
				claimItem.setShortTitle(submitClaimLine.getShortTittle());
				claimItem.setWrongIsbn(submitClaimLine.getWrongIsbn());

				Double mul = submitClaimLine.getClaimQty().doubleValue() * 
						     submitClaimLine.getUnitPrice() * (1- ((null != submitClaimLine.getDiscountPct() ? submitClaimLine.getDiscountPct() : 0 )/100));
				totalClaimAmount = totalClaimAmount.add(BigDecimal.valueOf(mul).setScale(2, RoundingMode.HALF_UP));
				submitClaimLine.getClaimQty();
				claimTypeDesc = submitClaimLine.getClaimTypeDesc();
				claimItemList.add(claimItem);
			}
		}
		zendeskClaimDTO.setAmount(totalClaimAmount);
		zendeskClaimDTO.setReshipment(isRshipment);
		zendeskClaimDTO.setClaimItemList(claimItemList);
		zendeskClaimDTO.setClaimType(claimTypeDesc);
		return zendeskClaimDTO;
	}
	
	/**
	 * 
	 * @param submitClaimLines
	 * @return
	 * @throws SystemException
	 */
	private List<SubmitClaimLine> addLongTitle(List<SubmitClaimLine> submitClaimLines) throws SystemException {
		if (!submitClaimLines.isEmpty()) {
			List<String> isbnList = new ArrayList<>();
			for (SubmitClaimLine submitClaimLine : submitClaimLines) {
				if(submitClaimLine.getIsbn()!=null) {
					isbnList.add(submitClaimLine.getIsbn());
				}
			}
			Map<String, Product> productTitleMap = commonUtil.getProductTitleMap(isbnList);
			for (SubmitClaimLine submitClaimLine : submitClaimLines) {
				if(submitClaimLine.getIsbn()!= null && productTitleMap.get(submitClaimLine.getIsbn())!= null) {
					if(productTitleMap.get(submitClaimLine.getIsbn()).getTitle()!= null) {
						submitClaimLine.setShortTittle(productTitleMap.get(submitClaimLine.getIsbn()).getTitle());
					}
					else {
						submitClaimLine.setShortTittle("");
					}
					if(productTitleMap.get(submitClaimLine.getIsbn()).getByLine()!= null) {
						submitClaimLine.setShortAuthor(productTitleMap.get(submitClaimLine.getIsbn()).getByLine()); //NRP-2876 Change Short Author value to original Author in NRP
					}
					else {
						submitClaimLine.setShortAuthor("");
					}
				}
				else {
					submitClaimLine.setShortTittle("");
					submitClaimLine.setShortAuthor("");
				}
			}
		}
		return submitClaimLines;
	}

}
