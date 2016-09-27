package com.deeploma.bettingshop.controler;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import javax.inject.Provider;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.deeploma.bettingshop.domain.betting.Ticket;
import com.deeploma.bettingshop.messaging.TicketEvent;
import com.deeploma.bettingshop.messaging.TicketSender;
import com.deeploma.bettingshop.services.TicketService;

@RestController
@RequestMapping(path= "/ticket")
@CrossOrigin
public class TicketControler {
	
	@Autowired
	TicketService ticketSrv;

	@CrossOrigin
	@RequestMapping(path= "/add", method= PUT, consumes = APPLICATION_JSON_VALUE)
	public void add(@RequestBody Ticket ticket) {
	
		ticketSrv.addTicket(ticket);		

	}
	

	
	
	
	

	

}
