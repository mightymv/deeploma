package com.deeploma.bettingshop.domain.betting;

import com.deeploma.bettingshop.domain.basic.Match;

public class BetOdd {
	
	private Long id;
	
	private SubGame subGame;
	
	private Match match;
	
	private Double value;
	
	private BetOddStatus betOddStatus;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BetOddStatus getBetOddStatus() {
		return betOddStatus;
	}

	public void setBetOddStatus(BetOddStatus betOddStatus) {
		this.betOddStatus = betOddStatus;
	}

	public SubGame getSubGame() {
		return subGame;
	}

	public void setSubGame(SubGame subGame) {
		this.subGame = subGame;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public Match getMatch() {
		return match;
	}

	public void setMatch(Match match) {
		this.match = match;
	}


}
