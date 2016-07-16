package com.deeploma.bettingshop.domain.betting;

public enum TicketStatus {
	
	ACTIVE(1),
	LOSER(5),
    WINNER(10),
    CALCULATED(50);
		
	private Integer id;
	
	private TicketStatus(Integer id) {
		this.setId(id);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
