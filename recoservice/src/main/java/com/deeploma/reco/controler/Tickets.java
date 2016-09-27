package com.deeploma.reco.controler;

import  java.util.Comparator;
import static java.util.stream.Collectors.toSet;

import java.util.Collections;
import java.util.Comparator;
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
public class Tickets {
	
	@Autowired
	private MongoRepository ma;
	
	@RequestMapping(path= "/{uid}/tickets", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<TicketDto>  ticketsForUser(@PathVariable ("uid") Long id) {	
		UserTickets ut = ma.findTicketsForUser(id);
		if (ut == null ) 
			return Collections.emptyList();
		return ut.getTickets().stream().sorted(Comparator.comparingLong(t -> ((TicketDto) t).getTime().getMillis()).reversed()).collect(Collectors.toList());
	}
	
	@RequestMapping(path= "/{uid}/{tid}/tickets", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public TicketDto  oneTicketForUser(@PathVariable ("uid") Long uid, @PathVariable ("tid") Long tid) {		
		List<TicketDto> userTickets = ticketsForUser(uid);
		
		return userTickets.stream().filter(tick -> tick.getId().equals(tid)).findFirst().get();
	}

}
