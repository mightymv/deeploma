package com.deeploma.bettingshop.controler;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.deeploma.bettingshop.domain.users.User;
import com.deeploma.bettingshop.services.UserService;

@RestController
public class UsersControler {
	
	private final static Logger logger = LoggerFactory.getLogger(UsersControler.class);
	
	@Autowired
	private UserService userService;
	
	
	@RequestMapping(path= "/user", method = RequestMethod.PUT, consumes = APPLICATION_JSON_VALUE)
	public void  addUser(@RequestBody User user) {
		logger.info("Zahtev za registraciju korisnika zapocet : {}", user );
		userService.addUser(user);
		logger.info("Zahtev za registraciju korisnika zavrsen : {}", user );
	}

}
