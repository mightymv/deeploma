package com.deeploma.bettingshop.domain.betting;

public enum TicketRowStatus {
	
	ACTIVE(1),
	LOSER(2),
    WINNER(3);
		
	private Integer id;
	
	private TicketRowStatus(Integer id) {
		this.setId(id);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
