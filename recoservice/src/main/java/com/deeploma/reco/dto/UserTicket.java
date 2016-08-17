package com.deeploma.reco.dto;

import org.springframework.data.annotation.Id;

public class UserTicket {
	
	private String username;
	
	@Id
	private Long userId;
	
	private TicketDto  ticketDto;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public TicketDto getTicketDto() {
		return ticketDto;
	}

	public void setTicketDto(TicketDto ticketDto) {
		this.ticketDto = ticketDto;
	}

}
