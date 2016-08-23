package com.deeploma.bettingshop.services.impls;

import java.io.IOException;
import java.util.List;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.deeploma.bettingshop.domain.betting.MatchOffer;
import com.deeploma.bettingshop.domain.betting.Ticket;
import com.deeploma.bettingshop.domain.betting.TicketRow;
import com.deeploma.bettingshop.mapper.CompetitionMapper;
import com.deeploma.bettingshop.mapper.MatchTeamsMapper;
import com.deeploma.bettingshop.mapper.OfferMapper;
import com.deeploma.bettingshop.mapper.SportMapper;
import com.deeploma.bettingshop.services.OfferService;
import com.deeploma.bettingshop.services.TicketService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;

@Component
public class OfferServiceImpl implements OfferService {
	

	private final static Logger logger = LoggerFactory.getLogger(OfferServiceImpl.class);

	@Autowired
	private CompetitionMapper  cMapper;
	
	
	@Autowired
	private SportMapper sMapper;
	
	
	@Autowired 
	private MatchTeamsMapper mtMapper;
	
	@Autowired
	private OfferMapper offerMapper;
	
	@Autowired
	private TicketService ticketService;
	
	@Override
	public List<MatchOffer>  getOffer(DateTime date) {
		return offerMapper.getOfferForDate(date);
	}

	
	/*//@Scheduled(initialDelay = 3000, fixedRate =3000)
	public void method() {
		System.out.println("IDemo");
		//Sport s = sMapper.findById(Integer.valueOf(1));
		List<Competition> comps = cMapper.findAll();
		Sport s = sMapper.findById(1);
		
		List<Result> results = mtMapper.findAllResults();
		List<Team> teams = mtMapper.findAllTeams();
		System.out.println(s.getName());
		
		Match m = mtMapper.findMatchById(1001);
		
		DateTime date = new DateTime("2016-06-20");
		List<MatchOffer>  offer = offerMapper.getOfferForDate(date);
		
	
		date.withTimeAtStartOfDay();
		
		System.out.println(m.getId());
		System.out.println(results.get(0).getResultStatus());
	}*/
	
	//@Scheduled(initialDelay = 3000, fixedRate =30000)
	public void insertTicket() {
		
		ObjectMapper objectMapper = new ObjectMapper();
		
		objectMapper.registerModule(new JodaModule());
		Ticket ticket = new Ticket();
		
		ticket.setTime(new DateTime());
		ticket.setUserId(Long.valueOf(45));
		
		TicketRow tr1= new TicketRow();
		tr1.setBetOddId(Long.valueOf(100));
		TicketRow tr2= new TicketRow();
		tr2.setBetOddId(Long.valueOf(102));
		TicketRow tr3= new TicketRow();
		tr3.setBetOddId(Long.valueOf(103));
		
		ticket.addTicketRow(tr1, 1.5d).addTicketRow(tr2, 1.6d).addTicketRow(tr3,2.4d);
		try {
			String mess = objectMapper.writeValueAsString(ticket);
			logger.info("TICKET JSON : {}", mess);
			
			Ticket t2 = objectMapper.readValue(mess, Ticket.class);
			
			logger.info("TICKET JSON2 : {}", t2.getId());
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//ticketService.addTicket(ticket);
        catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
