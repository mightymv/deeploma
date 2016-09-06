package com.deeploma.bettingshop.controler;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import javax.inject.Provider;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deeploma.bettingshop.domain.betting.Ticket;
import com.deeploma.bettingshop.messaging.SendingBean;
import com.deeploma.bettingshop.services.TicketService;

@RestController
@RequestMapping(path= "/ticket")
public class TicketControler {
	
	@Autowired
	TicketService ticketSrv;
	
	@Autowired (required = false)	
	Provider<SendingBean> sb;
	
	@RequestMapping(path= "/add", method= PUT , produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_UTF8_VALUE)
	public void add(@RequestBody Ticket ticket) {
	
		ticketSrv.addTicket(ticket);
		
		sb.get().sendTicket(ticket);
	}
	
	@RequestMapping(path= "/add2", method= POST , produces = APPLICATION_JSON_VALUE)
	public void add2() {
	
		Ticket ticket = new Ticket();
		
		ticket.setUserId(123l);
		ticket.setTime(DateTime.now());
		
		sb.get().sendTicket(ticket);
	}
	
	
	public void sendTicketEvent(Ticket ticket) {
		try {
			sb.get().sendTicket(ticket);
		} catch (Exception ex){
			
		}
		
	}

	

}
