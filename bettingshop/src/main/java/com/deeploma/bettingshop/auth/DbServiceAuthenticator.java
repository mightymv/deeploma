package com.deeploma.bettingshop.auth;

import org.springframework.security.authentication.BadCredentialsException;

import com.deeploma.bettingshop.domain.users.User;
import com.deeploma.bettingshop.services.UserService;

public class DbServiceAuthenticator implements ServiceAuthenticator {
	
	private static String SECRET = "SECRET1";
	
	private TokenService tokenService;
	private UserService userService;

	
	public  DbServiceAuthenticator(TokenService tokenService, UserService userService) {
		this.tokenService = tokenService;
		this.userService = userService;
	}

	
    @Override
    public AuthenticationWithToken  authenticate(String username, String password) {
    
    	User user = userService.validateUser(username, password);
    	if (user == null) {
    		throw new BadCredentialsException ("Pogresni username i/ili sifra");
    	}
    	
    	AuthenticationWithToken auth = new AuthenticationWithToken(username, password);
    	auth.setAuthenticated(true);
         
        String newToken = tokenService.generateNewToken(username, user.getId(),SECRET ); //TODO videti sta sa ovim
        auth.setData(new TokenUser(user, newToken));
        tokenService.store(newToken, auth);

        return auth;
    }
}