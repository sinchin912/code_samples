package com.hbg.rp.search.zendesk;

/**
 * Interface for Zendesk Service Helper.
 * @author vladimir.iagoupov
 */

import com.hbg.rp.search.dto.ZendeskClaimDTO;

import javax.ws.rs.client.Client;

public interface IZendeskServiceHelper {
	Client getSSLClient() throws Exception;

    String getZendeskClaimDTOUpdatedSubject(ZendeskClaimDTO zendeskClaimDTO);
}