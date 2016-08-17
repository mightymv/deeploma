package com.deeploma.bettingshop.domain.betting;

public class TicketRow {
	
	private Long id;
		
	private Long ticketId;
	
	private Long betOddId;
	
	private Long matchId;

	private String subGameShortName;
	
	private Double odd;
	
	private TicketRowStatus ticketRowStatus;
	
	public Long getTicketId() {
		return ticketId;
	}

	public void setTicketId(Long ticketId) {
		this.ticketId = ticketId;
	}

	public Long getBetOddId() {
		return betOddId;
	}

	public void setBetOddId(Long betOddId) {
		this.betOddId = betOddId;
	}

	public TicketRowStatus getTicketRowStatus() {
		return ticketRowStatus;
	}

	public void setTicketRowStatus(TicketRowStatus ticketRowStatus) {
		this.ticketRowStatus = ticketRowStatus;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getMatchId() {
		return matchId;
	}

	public void setMatchId(Long matchId) {
		this.matchId = matchId;
	}

	public String getSubGameShortName() {
		return subGameShortName;
	}

	public void setSubGameShortName(String subGameShortName) {
		this.subGameShortName = subGameShortName;
	}

	public Double getOdd() {
		return odd;
	}

	public void setOdd(Double odd) {
		this.odd = odd;
	}
	
	
	
}
