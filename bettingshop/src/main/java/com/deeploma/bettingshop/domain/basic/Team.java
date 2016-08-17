package com.deeploma.bettingshop.domain.basic;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Team {

	private Long id;
	
	private String name;
	
	@JsonIgnore
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
