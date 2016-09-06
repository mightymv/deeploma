package com.deeploma.reco.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.deeploma.reco.domen.UserTickets;
import com.deeploma.reco.dto.TicketDto;
import com.deeploma.reco.mongodao.MongoAccess;

@RestController
public class Tickets {
	
	@Autowired
	private MongoAccess ma;
	
	@RequestMapping(path= "/{uid}/tickets", method = RequestMethod.GET)
	public UserTickets  ticketsForUser(@PathVariable ("uid") Long id) {		
		return ma.findTicketsForUser(id);
	}
	
	@RequestMapping(path= "/{uid}/{tid}/tickets", method = RequestMethod.GET)
	public TicketDto  oneTicketForUser(@PathVariable ("uid") Long uid, @PathVariable ("tid") Long tid) {		
		UserTickets userTickets = ticketsForUser(uid);
		
		return userTickets.getTickets().stream().filter(tick -> tick.getId().equals(tid)).findFirst().get();
	}

}
