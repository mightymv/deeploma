package com.deeploma.bettingshop.services.impls;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.deeploma.bettingshop.domain.betting.MatchOffer;
import com.deeploma.bettingshop.domain.betting.Ticket;
import com.deeploma.bettingshop.domain.betting.TicketRow;
import com.deeploma.bettingshop.mapper.OfferMapper;
import com.deeploma.bettingshop.services.OfferService;
import com.deeploma.bettingshop.services.TicketService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;

@Component
public class OfferServiceImpl implements OfferService {
	

	private final static Logger logger = LoggerFactory.getLogger(OfferServiceImpl.class);

	@Autowired
	private OfferMapper offerMapper;

	
	@Autowired
	private DataSource dource;
	
	@Override
	public List<MatchOffer>  getOffer(DateTime date) {
		logger.info(dource.getClass().getName());
		return offerMapper.getOfferForDate(date);
	}
	
	@Scheduled(initialDelay = 3000, fixedRate =90000)
	public void get() {
		logger.info("IDEMOOOO " + dource.getClass().getName());
		offerMapper.getOfferForDate(new DateTime("2016-08-10"));
	}
	
	
	//@Autowired
	//TicketService ticketSrv;

	//@Scheduled(initialDelay = 3000, fixedRate =2000)
	public void add() {
		Ticket ticket = new Ticket();
		
		ticket.setTime(new DateTime());
		ticket.setUserId(Long.valueOf(-45));
		
		TicketRow tr1= new TicketRow();
		tr1.setBetOddId(Long.valueOf(100));
		TicketRow tr2= new TicketRow();
		tr2.setBetOddId(Long.valueOf(102));
		TicketRow tr3= new TicketRow();
		tr3.setBetOddId(Long.valueOf(103));
		
		
		ticket.setCumulativeOdd(5d);
		ticket.setTicketRows(new ArrayList<>());
		//ticketSrv.addTicket(ticket);
		logger.info("IDEMOOOO tiket");

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
