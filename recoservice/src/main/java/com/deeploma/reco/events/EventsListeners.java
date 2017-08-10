package com.deeploma.reco.events;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.activemq.command.ActiveMQBytesMessage;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.jms.annotation.JmsListener;

import com.deeploma.reco.domain.UserBehaviour;
import com.deeploma.reco.dto.TicketDto;
import com.deeploma.reco.dto.UserBehavioursDto;
import com.deeploma.reco.mongodao.MongoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class EventsListeners {

	private static final String BROWSER_CLICKS = "aca.recommend.queue";

	private final static Logger logger = LoggerFactory.getLogger(EventsListeners.class);
	
	public static final String TICKETS_QUEUE = "tickets.queue";

	@Autowired
	private ObjectMapper objm;;
	
	@Autowired
    private MongoRepository ma;
	
	public EventsListeners() {
	}

	@JmsListener(destination = TICKETS_QUEUE)
	public void receiveUserTicket(String ticketMessage) {
		logger.info("Stigao je ticket json : {}" , ticketMessage);		
		try {
			TicketDto resp = objm.readValue(ticketMessage, TicketDto.class);							 
			ma.saveTicket(resp);  			 			 
		} catch (IOException e) {
			logger.error("Doslo je do greske prilikom snimanja novog ticketa - " + ticketMessage, e);
		}
	       
	 }
	
	@JmsListener(destination = BROWSER_CLICKS)
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