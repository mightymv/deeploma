package com.deeploma.bettingshop.domain.users;

public enum UserStatus {
	
	ACTIVE(1),
	BLOCKED(10);
   
		
	private Integer id;
	
	private UserStatus(Integer id) {
		this.setId(id);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
