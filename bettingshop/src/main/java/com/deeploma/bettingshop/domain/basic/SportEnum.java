package com.deeploma.bettingshop.domain.basic;

public enum SportEnum {
	
	FOOTBALL(1),
	BASKETBALL(2);
	
    private Integer id;
	
	private SportEnum(Integer id) {
		this.setId(id);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
