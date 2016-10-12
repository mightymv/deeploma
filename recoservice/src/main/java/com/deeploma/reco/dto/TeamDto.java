package com.deeploma.reco.dto;

import org.jongo.marshall.jackson.oid.MongoId;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TeamDto {
	
	@MongoId
	private Long id;
	
	private String name;
	
	public TeamDto(){
		
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
