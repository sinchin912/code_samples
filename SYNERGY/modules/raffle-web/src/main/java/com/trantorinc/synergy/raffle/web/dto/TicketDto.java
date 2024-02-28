package com.trantorinc.synergy.raffle.web.dto;


public class TicketDto {

	private long id;
	private String number;
	private String prizeDescription;
	private String prizePicId;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getPrizeDescription() {
		return prizeDescription;
	}

	public void setPrizeDescription(String prizeDescription) {
		this.prizeDescription = prizeDescription;
	}

	public String getPrizePicId() {
		return prizePicId;
	}

	public void setPrizePicId(String prizePicId) {
		this.prizePicId = prizePicId;
	}
}
