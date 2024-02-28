package com.hbg.rp.search.model;

import com.hbg.rp.model.OrderLine;

import java.util.List;


/**
 * DTO for Line Status
 * 
 * @author ravi.kumar
 */
public class LineStatusDTO {
	private String name;
	private List<OrderLine> lines;
	private Long total;
	
	public List<OrderLine> getLines() {
		return lines;
	}
	public void setLines(List<OrderLine> lines) {
		this.lines = lines;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	
}