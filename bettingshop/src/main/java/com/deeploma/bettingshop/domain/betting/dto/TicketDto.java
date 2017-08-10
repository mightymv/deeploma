package com.deeploma.bettingshop.domain.betting.dto;

import java.util.List;

import org.joda.time.DateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TicketDto {
	
	private Long id;
	
    private String username;
	
	private Long userId;
	
	private List<TicketRowDto> rows;
	
	private String status;
	
	private Double cumulativeOdd;
	
	@JsonSerialize(using=com.fasterxml.jackson.datatype.joda.ser.DateTimeSerializer.class )	
	private DateTime time;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<TicketRowDto> getRows() {
		return rows;
	}

	public void setRows(List<TicketRowDto> rows) {
		this.rows = rows;
	}

	public DateTime getTime() {
		return time;
	}

	public void setTime(DateTime time) {
		this.time = time;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public Double getCumulativeOdd() {
		return cumulativeOdd;
	}

	public void setCumulativeOdd(Double cumulativeOdd) {
		this.cumulativeOdd = cumulativeOdd;
	}
	

}
