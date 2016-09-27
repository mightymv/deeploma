package com.deeploma.reco.dto;

import org.joda.time.DateTime;
import org.jongo.marshall.jackson.oid.MongoId;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;


public class MatchDto {

	
    @MongoId
	private Long id;
    
	private DateTime startTime;

	private CompetitionDto competition;

	private TeamDto teamHome;

	private TeamDto teamVisitor;
	
	public MatchDto(){
		
	}
	
	public MatchDto(Long id ) {
		this.id = id;
	}

	public DateTime getStartTime() {
		return startTime;
	}

	public void setStartTime(DateTime startTime) {
		this.startTime = startTime;
	}

	public CompetitionDto getCompetition() {
		return competition;
	}

	public void setCompetition(CompetitionDto competition) {
		this.competition = competition;
	}

	public TeamDto getTeamHome() {
		return teamHome;
	}

	public void setTeamHome(TeamDto teamHome) {
		this.teamHome = teamHome;
	}

	public TeamDto getTeamVisitor() {
		return teamVisitor;
	}

	public void setTeamVisitor(TeamDto teamVisitor) {
		this.teamVisitor = teamVisitor;
	}

	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		MatchDto other = (MatchDto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MatchDto [id=" + id + ", teamHome=" + teamHome.getName() + ", teamVisitor=" + teamVisitor.getName() + "]";
	}

}
