package com.deeploma.bettingshop.controler;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.deeploma.bettingshop.auth.AuthenticationWithToken;
import com.deeploma.bettingshop.auth.ServiceAuthenticator;
import com.deeploma.bettingshop.auth.TokenResponse;
import com.deeploma.bettingshop.auth.TokenUser;
import com.deeploma.bettingshop.domain.users.User;
import com.deeploma.bettingshop.services.UserService;


@RestController
@CrossOrigin
public class UsersControler {
	
	private final static Logger logger = LoggerFactory.getLogger(UsersControler.class);
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ServiceAuthenticator authService;
	
	
	@RequestMapping(path= "/user", method = RequestMethod.PUT, consumes = APPLICATION_JSON_VALUE )
	public void  addUser(@RequestBody User user) {
		logger.info("Zahtev za registraciju korisnika zapocet : {}", user );
		userService.addUser(user);
		logger.info("Zahtev za registraciju korisnika zavrsen : {}", user );
	}
	
	@RequestMapping(path= "/login2" , method = RequestMethod.POST)
	public TokenResponse login(@RequestHeader("X-Auth-Username") String username, @RequestHeader("X-Auth-Password") String password) {
		logger.info("Logovanje za korisnika : {}, {}", username, password);
		AuthenticationWithToken autToken =  authService.authenticate(username, password);
		
		TokenUser tokenUser = (TokenUser) autToken.getDetails();
	    logger.info("Uspesno logovanje za korisnika : {}", tokenUser.getUser());
	    TokenResponse tokenResponse = new TokenResponse(tokenUser.getToken(), tokenUser.getUser().getName(), tokenUser.getUser().getSurname(), tokenUser.getUser().getId());
	        
	    return tokenResponse;		
	}
	
}
