package com.deeploma.bettingshop.auth;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TokenResponse {
    @JsonProperty
    private String token;
    
    private String name;
    
    private String surname;
    
    private Long id;

    public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TokenResponse() {
    }

    public TokenResponse(String token, String name, String surname, Long id) {
        this.token = token;
        this.name = name;
        this.surname = surname;
        this.id = id;
    }
}
