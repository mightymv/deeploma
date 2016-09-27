package com.deeploma.reco.domain;

import org.joda.time.DateTime;

public class UserBehaviour {


	private Long userId;
		
	private Long matchId;
	
	private DateTime time;
	
	public UserBehaviour() {
		
	}

	public UserBehaviour(Long userId, Long matchId, DateTime time) {
		super();
		this.userId = userId;
		this.matchId = matchId;
		this.time = time;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getMatchId() {
		return matchId;
	}

	public void setMatchId(Long matchId) {
		this.matchId = matchId;
	}

	public DateTime getTime() {
		return time;
	}

	public void setTime(DateTime time) {
		this.time = time;
	}


	
	
	

}