package com.deeploma.bettingshop.domain.betting;

public class BetOdd {
	
	private Long id;
	
	private SubGame subGame;
	
	private Long matchId;
	
	private Double value;
	
	private BetOddStatus betOddStatus;

	public Long getMatchId() {
		return matchId;
	}

	public void setMatchId(Long matchId) {
		this.matchId = matchId;
	}

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


}
