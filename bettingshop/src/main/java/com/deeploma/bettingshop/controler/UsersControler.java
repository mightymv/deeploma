package com.deeploma.bettingshop.controler;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsersControler {
	
	@RequestMapping(path= "/user", method = RequestMethod.GET)
	public String  getOfferForDate() {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String username = (String) auth.getPrincipal();
	   
		return username; // OVO JE SAMO ZA PROBU
	}

}
