package com.deeploma.bettingshop.domain.betting;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape= JsonFormat.Shape.OBJECT)
public enum Game {

	HALF_TIME_SCORE_GOALS(2),
		
	FULL_TIME_SCORE_GOALS(1);
	
	private Integer id;
	
	private String name;
	
	private Game (Integer id) {
		this.setId(id);
		this.name= name();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
		
	public String getName() {
		return name;
	}
}
