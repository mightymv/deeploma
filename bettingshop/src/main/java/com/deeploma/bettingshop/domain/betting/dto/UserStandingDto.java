package com.deeploma.bettingshop.domain.betting.dto;

import org.joda.time.DateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserStandingDto {
     
	@JsonSerialize(using=com.deeploma.bettingshop.domain.DateTimeToStringSerializer.class)
	private DateTime dateOf;
	
	private Long ticketId;
	
	private String username;
	
	private Long userId;
	
	private Double bestResult;

	public DateTime getDateOf() {
		return dateOf;
	}

	public void setDateOf(DateTime dateOf) {
		this.dateOf = dateOf;
	}

	public Long getTicketId() {
		return ticketId;
	}

	public void setTicketId(Long ticketId) {
		this.ticketId = ticketId;
	}

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

	public Double getBestResult() {
		return bestResult;
	}

	public void setBestResult(Double bestResult) {
		this.bestResult = bestResult;
	}
	
	

}
