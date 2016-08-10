package com.deeploma.bettingshop.auth;

public interface ServiceAuthenticator {

    AuthenticationWithToken authenticate(String username, String password);
}