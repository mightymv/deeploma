package com.deeploma.bettingshop.domain.basic;

public class Result {	

	private Long id;
	
	private ResultType resultType;
	
	private Integer homeTeam;
	
	private Integer visitorTeam;
	
	private Long matchId;
	
	private ResultStatus resultStatus;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ResultType getResultType() {
		return resultType;
	}

	public void setResultType(ResultType resultType) {
		this.resultType = resultType;
	}

	public Integer getHomeTeam() {
		return homeTeam;
	}

	public void setHomeTeam(Integer homeTeam) {
		this.homeTeam = homeTeam;
	}

	public Integer getVisitorTeam() {
		return visitorTeam;
	}

	public void setVisitorTeam(Integer visitorTeam) {
		this.visitorTeam = visitorTeam;
	}

	public Long getMatchId() {
		return matchId;
	}

	public void setMatchId(Long matchId) {
		this.matchId = matchId;
	}

	public ResultStatus getResultStatus() {
		return resultStatus;
	}

	public void setResultStatus(ResultStatus resultStatus) {
		this.resultStatus = resultStatus;
	}

}
