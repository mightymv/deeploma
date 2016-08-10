package com.deeploma.bettingshop.auth;

import java.util.Random;

import com.deeploma.bettingshop.auth.util.JwtTokenValidator;

public class DbServiceAuthenticator implements ServiceAuthenticator {
	
	private TokenService tokenService;
	
	public  DbServiceAuthenticator(TokenService tokenService) {
		this.tokenService = tokenService;
	}

    @Override
    public AuthenticationWithToken  authenticate(String username, String password) {
    
        //TODO ovde treba da ide do baze i da proveri da li ih ima tamo
    	Random r = new Random();
    	
    	AuthenticationWithToken auth = new AuthenticationWithToken(username, password);
    	auth.setAuthenticated(true);
         
        String newToken = tokenService.generateNewToken(username, Long.valueOf(r.nextInt(100)),JwtTokenValidator.secret); //TODO videti sta sa ovim
        auth.setToken(newToken);
        tokenService.store(newToken, auth);

        return auth;
    }
}