package com.deeploma.reco.dto;

import org.jongo.marshall.jackson.oid.MongoId;

public class Competition {

	@MongoId
	private Long id;
	
	private String name;
	
	private Sport sport;

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

	public Sport getSport() {
		return sport;
	}

	public void setSport(Sport sport) {
		this.sport = sport;
	}

}
