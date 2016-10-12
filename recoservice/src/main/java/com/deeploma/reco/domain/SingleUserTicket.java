package com.deeploma.reco.domain;

import org.jongo.marshall.jackson.oid.MongoId;

import com.deeploma.reco.dto.TicketDto;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SingleUserTicket {
	
	private String username;
	
	@MongoId
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
