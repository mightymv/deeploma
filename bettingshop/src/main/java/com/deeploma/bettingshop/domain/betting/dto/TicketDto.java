package com.deeploma.bettingshop.domain.betting.dto;

import java.util.List;

public class TicketDto {
	
	private Long id;
	
	private List<TicketRowDto> rows;

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

}
