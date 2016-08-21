package com.deeploma.reco.events;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.jongo.Jongo;
import org.jongo.MongoCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.jms.annotation.JmsListener;

import com.deeploma.reco.domen.UserTickets;
import com.deeploma.reco.dto.TicketDto;
import com.deeploma.reco.dto.UserTicket;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;

@Configuration
public class EventListeners {

	private final static Logger logger = LoggerFactory.getLogger(EventListeners.class);
	
	public static final String TICKETS_QUEUE = "tickets.queue";

	@Autowired
	ObjectMapper objm;;
	
	MongoTemplate mTemplate;

	@Autowired
	MongoTemplate mongoTemplate;
	
	@Autowired 
	Jongo jongo;
	
	public EventListeners() {
	}

	@JmsListener(destination = TICKETS_QUEUE)
	public void receiveOrder(String userTicket) {
		logger.info("Stigao je json : {}" , userTicket);
		UserTicket resp;
		try {
			resp = objm.readValue(userTicket, UserTicket.class);			
			//UserTickets ut = mongoTemplate.findOne(query(where("userId").is(resp.getUserId())), UserTickets.class);
			
			MongoCollection collection = jongo.getCollection("userTickets");
			
			UserTickets ut = collection.findOne("{_id: #}", resp.getUserId()).as(UserTickets.class);
			 
			if (ut == null) {
				 ut = new UserTickets();
				 ut.setUserId(resp.getUserId());
				 ut.setUsername(resp.getUsername());				 
				 Set<TicketDto> tickets = new HashSet<TicketDto>();				
				 ut.setTickets(tickets);		 				
			 }			 
			
			 ut.getTickets().add(resp.getTicketDto());
			 ut.getTickets().forEach(tick -> logger.info(tick.getId().toString()));
			 mongoTemplate.save(ut);
			 
		} catch (IOException e) {
			logger.error("Doslo je do greske prilikom snimanja novo user ticket-a" , e);
		}
	       
	 }
}