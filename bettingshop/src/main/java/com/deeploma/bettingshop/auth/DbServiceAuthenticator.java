package com.deeploma.bettingshop.auth;

import java.util.Random;

import org.springframework.security.authentication.BadCredentialsException;

import com.deeploma.bettingshop.auth.util.JwtTokenValidator;
import com.deeploma.bettingshop.domain.users.User;
import com.deeploma.bettingshop.services.UserService;

public class DbServiceAuthenticator implements ServiceAuthenticator {
	
	private TokenService tokenService;
	private UserService userService;
	
	public  DbServiceAuthenticator(TokenService tokenService, UserService userService) {
		this.tokenService = tokenService;
		this.userService = userService;
	}

	
    @Override
    public AuthenticationWithToken  authenticate(String username, String password) {
    
        //TODO ovde treba da ide do baze i da proveri da li ih ima tamo
    	Random r = new Random();
    	User user = userService.validateUser(username, password);
    	if (user == null) {
    		throw new BadCredentialsException ("Pogresni username i/ili sifra");
    	}
    	
    	AuthenticationWithToken auth = new AuthenticationWithToken(username, password);
    	auth.setAuthenticated(true);
         
        String newToken = tokenService.generateNewToken(username, Long.valueOf(r.nextInt(100)),JwtTokenValidator.secret); //TODO videti sta sa ovim
        auth.setData(new TokenUser(user, newToken));
        tokenService.store(newToken, auth);

        return auth;
    }
}