package com.deeploma.reco.controler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.deeploma.reco.domen.UserTickets;
import com.deeploma.reco.mongodao.MongoAccess;

@RestController
public class Tickets {
	
	@Autowired
	private MongoAccess ma;
	
	@RequestMapping(path= "/{id}/tickets", method = RequestMethod.GET)
	public UserTickets  ticketsForUser(@PathVariable ("id") Long id) {		
		return ma.findTicketsForUser(id);
	}

}
