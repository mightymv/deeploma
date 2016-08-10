package com.deeploma.bettingshop.domain.betting;

public enum BetOddStatus {
	
	ACTIVE(1),
	LOSER(2),
    WINNER(3);
		
	private Integer id;
	
	
	private BetOddStatus(Integer id) {
		this.setId(id);
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	
	public BetOddStatus findById(Integer id) {
		for (BetOddStatus status : values()) {
			if (status.getId().equals(id)) {
				return status;
			}
		}
		
		throw new IllegalArgumentException(String.format("BetOddStatus with id [{}] does not exist", id));
	}
	
}
