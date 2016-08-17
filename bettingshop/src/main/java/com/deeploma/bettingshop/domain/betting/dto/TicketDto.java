package com.deeploma.bettingshop.domain.betting.dto;

import java.util.List;

import org.joda.time.DateTime;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class TicketDto {
	
	private Long id;
	
	private List<TicketRowDto> rows;
	
	@JsonSerialize(using=com.fasterxml.jackson.datatype.joda.ser.DateTimeSerializer.class )	
	private DateTime startTime;

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

	public DateTime getStartTime() {
		return startTime;
	}

	public void setStartTime(DateTime startTime) {
		this.startTime = startTime;
	}

}
