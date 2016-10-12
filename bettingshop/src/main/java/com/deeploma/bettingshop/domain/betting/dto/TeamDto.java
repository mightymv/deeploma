package com.deeploma.bettingshop.domain.betting.dto;

import com.deeploma.bettingshop.domain.basic.Team;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TeamDto {
	
	private Long id;
	
	private String name;
	
	public TeamDto(){
		
	}
	
	public TeamDto(Team teamHome) {
		this.id = teamHome.getId();
		
		this.name = teamHome.getName();
	}

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



}
