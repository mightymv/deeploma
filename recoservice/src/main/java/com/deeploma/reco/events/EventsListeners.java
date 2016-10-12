package com.deeploma.reco.events;

import java.io.IOException;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.activemq.command.ActiveMQBytesMessage;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;

import com.deeploma.reco.domain.SingleUserTicket;
import com.deeploma.reco.domain.UserBehaviour;
import com.deeploma.reco.domain.UserTickets;
import com.deeploma.reco.dto.MatchTimeDto;
import com.deeploma.reco.dto.TicketDto;
import com.deeploma.reco.dto.UserBehavioursDto;
import com.deeploma.reco.mongodao.MongoRepository;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class EventsListeners {

	private final static Logger logger = LoggerFactory.getLogger(EventsListeners.class);
	
	public static final String TICKETS_QUEUE = "tickets.queue";

	@Autowired
	ObjectMapper objm;;
		
	@Autowired
	MongoTemplate mongoTemplate;

	@Autowired
    MongoRepository ma;
	
	public EventsListeners() {
	}

	@JmsListener(destination = TICKETS_QUEUE)
	public void receiveUserTicket(String userTicket) {
		logger.info("Stigao je ticket json : {}" , userTicket);
		SingleUserTicket resp;
		try {
			resp = objm.readValue(userTicket, SingleUserTicket.class);			
			
			UserTickets ut = ma.findTicketsForUser(resp.getUserId());
			 
			if (ut == null) {
				 ut = new UserTickets();
				 ut.setUserId(resp.getUserId());
				 ut.setUsername(resp.getUsername());				 
				 Set<TicketDto> tickets = new HashSet<TicketDto>();				
				 ut.setTickets(tickets);		 				
			 }			 
			 ut.getTickets().remove(resp.getTicketDto());  // da bih izveo upsert
			 ut.getTickets().add(resp.getTicketDto());
			 ut.getTickets().forEach(tick -> logger.info(tick.getId().toString()));
			 ma.saveUserTickets(ut);  
			 
			 
		} catch (IOException e) {
			logger.error("Doslo je do greske prilikom snimanja novo user ticket-a" , e);
		}
	       
	 }
	
	@JmsListener(destination = "aca.recommend.queue")
	public void receiveBehaviours(ActiveMQBytesMessage userBehaviour) {
		String event = new String(userBehaviour.getContent().getData());
		logger.info("Stigao je behaviour json : {}" , event);
		
		try {
			UserBehavioursDto dto = objm.readValue(event, UserBehavioursDto.class);
			if (dto.getTime() == null) {
				dto.setTime(Calendar.getInstance().getTimeInMillis()); // ako nema
			}
			
			List<UserBehaviour> behdto = dto.getPrioMatches().stream().map(matchId -> new UserBehaviour(dto.getUserId(), matchId, new DateTime(dto.getTime()))).collect(Collectors.toList());	
			behdto.stream().forEach(beh -> ma.saveUserBehaviours(beh));
			//ma.saveUserBehaviours(behdto);
				
		} catch (IOException e) {
			logger.error("Doslo je do greske prilikom parsiranja event-a za ponasanje igraca", e);
		}
		
	}
} 