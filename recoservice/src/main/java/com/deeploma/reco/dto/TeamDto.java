package com.deeploma.reco.dto;

import org.joda.time.DateTime;
import org.jongo.marshall.jackson.oid.MongoId;

public class TeamDto {
	
	@MongoId
	private Long id;
	
	private String name;
	
	private DateTime startTime;
	
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

	public DateTime getStartTime() {
		return startTime;
	}

	public void setStartTime(DateTime startTime) {
		this.startTime = startTime;
	}



}
