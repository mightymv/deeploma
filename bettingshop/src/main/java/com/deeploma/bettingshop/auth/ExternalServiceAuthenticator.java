package com.deeploma.bettingshop.auth;

public interface ExternalServiceAuthenticator {

    AuthenticationWithToken authenticate(String username, String password);
}