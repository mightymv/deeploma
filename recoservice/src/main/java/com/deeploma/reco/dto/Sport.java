package com.deeploma.reco.dto;

import org.jongo.marshall.jackson.oid.MongoId;

public class Sport {
	
	@MongoId
    private Integer id;
    
    private String name;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	

}
