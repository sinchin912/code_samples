package com.hbg.rp.search.dto;

import com.hbg.rp.search.model.SubmitClaimHeader;
import com.hbg.rp.search.model.SubmitClaimLine;

import java.util.List;

public class SubmitClaimDTO {
	
	private SubmitClaimHeader submitClaimHeader;
	private List<SubmitClaimLine> submitClaimLineList;
	
	public SubmitClaimHeader getSubmitClaimHeader() {
		return submitClaimHeader;
	}
	public void setSubmitClaimHeader(SubmitClaimHeader submitClaimHeader) {
		this.submitClaimHeader = submitClaimHeader;
	}
	public List<SubmitClaimLine> getSubmitClaimLineList() {
		return submitClaimLineList;
	}
	public void setSubmitClaimLineList(List<SubmitClaimLine> submitClaimLineList) {
		this.submitClaimLineList = submitClaimLineList;
	}
	
	

}
