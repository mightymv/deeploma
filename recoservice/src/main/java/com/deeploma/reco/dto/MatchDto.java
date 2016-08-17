package com.deeploma.reco.dto;

import org.joda.time.DateTime;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class MatchDto {

	private Long id;
	@JsonSerialize(using = com.fasterxml.jackson.datatype.joda.ser.DateTimeSerializer.class)
	private DateTime startTime;

	private Competition competition;

	private TeamDto teamHome;

	private TeamDto teamVisitor;

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

}
