package com.deeploma.bettingshop.auth;

import com.deeploma.bettingshop.domain.users.User;

public class TokenUser {
	
	private User user;

	private String token;
	
	public TokenUser (User user, String token) {
		this.user = user;
		this.token = token;
	}
	
	public User getUser() {
		return user;
	}

	public String getToken() {
		return token;
	}

}
