package com.deeploma.bettingshop.controler;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.*;

import org.springframework.beans.factory.annotation.Autowired;

import static org.springframework.http.MediaType.*;
import org.springframework.web.bind.annotation.RestController;

import com.deeploma.bettingshop.domain.betting.Ticket;
import com.deeploma.bettingshop.services.TicketService;

@RestController
@RequestMapping(path= "/ticket")
public class TicketControler {
	
	@Autowired
	TicketService ticketSrv;
	
	@RequestMapping(path= "/add", method= PUT , produces = APPLICATION_JSON_VALUE)
	public void add(@RequestBody Ticket ticket) {
		
		ticketSrv.addTicket(ticket);
		
	}

}
