package com.deeploma.bettingshop.domain.betting.dto;

import org.joda.time.DateTime;

import com.deeploma.bettingshop.domain.basic.Competition;
import com.deeploma.bettingshop.domain.basic.Team;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class MatchDto {

	private Long id;
	@JsonSerialize(using = com.fasterxml.jackson.datatype.joda.ser.DateTimeSerializer.class)
	@JsonDeserialize(using = com.fasterxml.jackson.datatype.joda.deser.DateTimeDeserializer.class)
	private DateTime startTime;

	private Competition competition;

	private Team teamHome;

	private Team teamVisitor;

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

	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
