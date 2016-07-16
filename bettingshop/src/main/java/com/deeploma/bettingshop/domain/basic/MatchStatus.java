package com.deeploma.bettingshop.domain.basic;

public enum MatchStatus {
	
	
	NOT_FINISHED(1),
	FINISHED(10);
	
    private Integer id;
	
	private MatchStatus(Integer id) {
		this.setId(id);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


}
