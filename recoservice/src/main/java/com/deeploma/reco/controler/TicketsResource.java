package com.deeploma.reco.controler;

import java.util.Collections;
import  java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.deeploma.reco.domain.UserTickets;
import com.deeploma.reco.dto.TicketDto;
import com.deeploma.reco.mongodao.MongoRepository;


@RestController
@CrossOrigin
public class TicketsResource {
	
	@Autowired
	private MongoRepository repository;
	
	@RequestMapping(path= "/{uid}/tickets", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<TicketDto>  ticketsForUser(@PathVariable ("uid") Long id) {
		Set<TicketDto> tickets = repository.findTicketsForUser(id);
		if (tickets  == null || tickets.isEmpty() ) 
			return Collections.emptyList();
		UserTickets ut = new UserTickets();
		ut.setTickets(tickets);
		
		return ut.getTickets().stream().sorted(Comparator.comparingLong(t -> ((TicketDto) t).getTime().getMillis()).reversed()).collect(Collectors.toList());
	}
	
	@RequestMapping(path= "/{uid}/{tid}/tickets", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public TicketDto  oneTicketForUser(@PathVariable ("uid") Long uid, @PathVariable ("tid") Long tid) {		
		List<TicketDto> userTickets = ticketsForUser(uid);
		
		return userTickets.stream().filter(tick -> tick.getId().equals(tid)).findFirst().get();
	}

}
