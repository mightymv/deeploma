package com.deeploma.bettingshop.controler;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;

import com.deeploma.bettingshop.domain.users.User;

public class TestControler {
	
	@RequestMapping(path= "/user")
	public String  getOfferForDate() {
		
		 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	     User user = (User) auth.getPrincipal();
		
		return "Uspelo je";
	}

}
