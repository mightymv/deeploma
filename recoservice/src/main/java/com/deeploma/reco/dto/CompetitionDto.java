package com.deeploma.reco.dto;

import org.jongo.marshall.jackson.oid.MongoId;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CompetitionDto {

	@MongoId
	private Long id;
	
	private String name;
	
	private SportDto sport;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public SportDto getSport() {
		return sport;
	}

	public void setSport(SportDto sport) {
		this.sport = sport;
	}

}
