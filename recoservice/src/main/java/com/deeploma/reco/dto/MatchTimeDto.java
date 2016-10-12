package com.deeploma.reco.dto;

import org.joda.time.DateTime;

public class MatchTimeDto {
	
	private DateTime time;
	
	private Long matchId;
	
	public MatchTimeDto() {
		
	}
	public MatchTimeDto(DateTime time, Long matchId) {
		this.time = time;
		this.matchId = matchId;
	}
	public DateTime getTime() {
		return time;
	}
	public void setTime(DateTime time) {
		this.time = time;
	}
	public Long getMatchId() {
		return matchId;
	}
	public void setMatchId(Long matchId) {
		this.matchId = matchId;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((matchId == null) ? 0 : matchId.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MatchTimeDto other = (MatchTimeDto) obj;
		if (matchId == null) {
			if (other.matchId != null)
				return false;
		} else if (!matchId.equals(other.matchId))
			return false;
		return true;
	}
}