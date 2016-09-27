package com.deeploma.bettingshop.domain.basic;

import java.util.List;

import org.joda.time.DateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Match {

	private Long id;
	
	@JsonSerialize(using=com.deeploma.bettingshop.domain.DateTimeToStringSerializer.class )
	//@JsonDeserialize(using=com.fasterxml.jackson.datatype.joda.deser.DateTimeDeserializer.class )	
	private DateTime startTime;
	
	private Competition competition;
	
	private Team teamHome;
	
	private Team teamVisitor;
	
	private List<Result> results;
	
    private MatchStatus matchStatus;
    
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public DateTime getStartTime() {
		return startTime;
	}

	public void setStartTime(DateTime startTime) {
		this.startTime = startTime;
	}

	public Competition getCompetition() {
		return competition;
	}

	public void setCompetition(Competition competition) {
		this.competition = competition;
	}

	public Team getTeamHome() {
		return teamHome;
	}

	public void setTeamHome(Team teamHome) {
		this.teamHome = teamHome;
	}

	public Team getTeamVisitor() {
		return teamVisitor;
	}

	public void setTeamVisitor(Team teamVisitor) {
		this.teamVisitor = teamVisitor;
	}

	public List<Result> getResults() {
		return results;
	}

	public void setResults(List<Result> results) {
		this.results = results;
	}

	public MatchStatus getMatchStatus() {
		return matchStatus;
	}

	public void setMatchStatus(MatchStatus matchStatus) {
		this.matchStatus = matchStatus;
	}
}
