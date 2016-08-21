package com.deeploma.bettingshop.controler;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsersControler {
	
	@RequestMapping(path= "/addUser", method = RequestMethod.GET)
	public void  addUser() {
				System.out.println("IDEMOOO USER");
	}

}
