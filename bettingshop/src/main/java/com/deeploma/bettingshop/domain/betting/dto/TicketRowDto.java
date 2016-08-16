package com.deeploma.bettingshop.domain.betting.dto;

import com.deeploma.bettingshop.domain.basic.Match;

public class TicketRowDto {
	
	private Long id;
	
	private Match match;
	
	private Long betOddId;

	public Match getMatch() {
		return match;
	}

	public void setMatch(Match match) {
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
