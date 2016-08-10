package com.deeploma.bettingshop.auth;

public class DbServiceAuthenticator implements ExternalServiceAuthenticator {

    @Override
    public AuthenticationWithToken  authenticate(String username, String password) {
    
        //TODO ovde treba da ide do baze i da proveri da li ih ima tamo
    	AuthenticationWithToken auth = new AuthenticationWithToken(username, password);
    	auth.setAuthenticated(true);
        return auth;
    }
}