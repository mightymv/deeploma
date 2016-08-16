package com.deeploma.bettingshop.domain.betting.dto;

public class TicketRowDto {
	
	private Long id;
	
	private MatchDto match;
	
	private Long betOddId;

	public MatchDto getMatch() {
		return match;
	}

	public void setMatch(MatchDto match) {
		this.match = match;
	}

	public Long getBetOddId() {
		return betOddId;
	}

	public void setBetOddId(Long betOddId) {
		this.betOddId = betOddId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
