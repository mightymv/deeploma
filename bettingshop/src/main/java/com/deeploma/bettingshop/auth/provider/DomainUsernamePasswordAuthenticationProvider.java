package com.deeploma.bettingshop.auth.provider;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import com.deeploma.bettingshop.auth.AuthenticationWithToken;
import com.deeploma.bettingshop.auth.ExternalServiceAuthenticator;
import com.deeploma.bettingshop.auth.TokenService;
import com.deeploma.bettingshop.auth.util.JwtTokenValidator;
import com.google.common.base.Optional;

public class DomainUsernamePasswordAuthenticationProvider implements AuthenticationProvider {

    private TokenService tokenService;
    private ExternalServiceAuthenticator externalServiceAuthenticator;

    public DomainUsernamePasswordAuthenticationProvider(TokenService tokenService, ExternalServiceAuthenticator externalServiceAuthenticator) {
        this.tokenService = tokenService;
        this.externalServiceAuthenticator = externalServiceAuthenticator;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        @SuppressWarnings("unchecked")
		Optional<String> username = (Optional<String>) authentication.getPrincipal();
        @SuppressWarnings("unchecked")
		Optional<String> password = (Optional<String>) authentication.getCredentials();

        if (!username.isPresent() || !password.isPresent()) {
            throw new BadCredentialsException("Invalid Domain User Credentials");
        }

        AuthenticationWithToken resultOfAuthentication = externalServiceAuthenticator.authenticate(username.get(), password.get());
        
        resultOfAuthentication.setAuthenticated(true);
        
        String newToken = tokenService.generateNewToken(username.get(), JwtTokenValidator.secret); //TODO videti sta sa ovim
        resultOfAuthentication.setToken(newToken);
        tokenService.store(newToken, resultOfAuthentication);

        return resultOfAuthentication;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
