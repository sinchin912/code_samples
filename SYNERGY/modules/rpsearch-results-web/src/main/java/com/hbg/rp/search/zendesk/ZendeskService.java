package com.hbg.rp.search.zendesk;

import com.hbg.rp.search.model.SubmitClaimDTO;
import com.hbg.rp.search.dto.ZendeskClaimDTO;
import com.hbg.rp.search.dto.ZendeskDTO;
import com.liferay.portal.kernel.exception.SystemException;

import org.zendesk.client.v2.model.Ticket;

import javax.portlet.ResourceRequest;
import javax.ws.rs.core.Response;
import java.io.IOException;

/**
 * Interface for Zendesk Service.
 * @author robin.sharma
 */
public interface ZendeskService {
	
	
	public ZendeskDTO createZendeskData(ResourceRequest request);
	
	/**
	 * Create ticket on Zendesk
	 * @param zendeskDTO
	 * @return
	 * @throws IOException
	 */
	Response createTicket(ZendeskDTO zendeskDTO);

	/**
	 * Create Claim ticket on Zendesk
	 * @param zendeskClaimDTO
	 * @return
	 * @throws Exception 
	 * @throws IOException
	 */
    Response createClaimTicket(ZendeskClaimDTO zendeskClaimDTO) throws Exception;

    /**
	 * Get all tickets.
	 * @return
	 */
	Iterable<Ticket> getTickets();
	
	/**
	 * Get ticket by Id.
	 * @param id
	 * @return
	 */
	Ticket getTicket(long id);
	
	/**
	 * @param submitClaimDTO
	 * @param requestorName
	 * @return ZendeskClaimDTO
	 * @throws SystemException 
	 */
	ZendeskClaimDTO createZendeskClaimDTO(SubmitClaimDTO submitClaimDTO, String requestorName, Long claimTypeKey) throws SystemException;
	
}
