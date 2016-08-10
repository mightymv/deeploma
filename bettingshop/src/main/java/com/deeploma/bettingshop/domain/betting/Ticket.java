package com.deeploma.bettingshop.domain.betting;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

public class Ticket {
	
	private Long id;
	
    private TicketStatus ticketStatus;
    
	private Long userId;
	 
	private DateTime time;
	
	private List<TicketRow>  ticketRows;
	
	public Double cumulativeOdd = 1.0;

	public Double getCumulativeOdd() {
		return cumulativeOdd;
	}

	public void setCumulativeOdd(Double cumulativeOdd) {
		this.cumulativeOdd = cumulativeOdd;
	}

	public DateTime getTime() {
		return time;
	}

	public void setTime(DateTime time) {
		this.time = time;
	}

	public List<TicketRow> getTicketRows() {
		return ticketRows;
	}

	public void setTicketRows(List<TicketRow> ticketRows) {
		this.ticketRows = ticketRows;
	}

	public TicketStatus getTicketStatus() {
		return ticketStatus;
	}

	public void setTicketStatus(TicketStatus ticketStatus) {
		this.ticketStatus = ticketStatus;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	public Ticket addTicketRow(TicketRow ticketRow, Double odd) {
		if (ticketRows == null) {
			ticketRows = new ArrayList<TicketRow>();
		}
		ticketRows.add(ticketRow);
		
		this.cumulativeOdd = this.cumulativeOdd * odd;
		return this;
	}

}
