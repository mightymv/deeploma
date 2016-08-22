package com.deeploma.bettingshop.auth;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

import java.util.Collection;

public class AuthenticationWithToken extends PreAuthenticatedAuthenticationToken {
    /**
	 * 
	 */
	private static final long serialVersionUID = 7483527671663102761L;
	
	public AuthenticationWithToken(Object aPrincipal, Object aCredentials) {
        super(aPrincipal, aCredentials);

    }

    public AuthenticationWithToken(Object aPrincipal, Object aCredentials, Collection<? extends GrantedAuthority> anAuthorities) {
        super(aPrincipal, aCredentials, anAuthorities);
    }

    public void setData(TokenUser token) {
        setDetails(token);
    }

    public TokenUser getToken() {
        return (TokenUser)getDetails();
    }


}