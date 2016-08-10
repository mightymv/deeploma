package com.deeploma.bettingshop.services.impls;

import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.deeploma.bettingshop.domain.betting.MatchOffer;
import com.deeploma.bettingshop.mapper.CompetitionMapper;
import com.deeploma.bettingshop.mapper.MatchTeamsMapper;
import com.deeploma.bettingshop.mapper.OfferMapper;
import com.deeploma.bettingshop.mapper.SportMapper;
import com.deeploma.bettingshop.services.OfferService;
import com.deeploma.bettingshop.services.TicketService;

@Component
public class OfferServiceImpl implements OfferService {
	
	
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
	}
	
	//@Scheduled(initialDelay = 3000, fixedRate =3000)
	public void insertTicket() {
		Ticket ticket = new Ticket();
		
		ticket.setTime(new DateTime());
		ticket.setUserId(Long.valueOf(45));
		
		TicketRow tr1= new TicketRow();
		tr1.setBetOddId(Long.valueOf(100));
		TicketRow tr2= new TicketRow();
		tr2.setBetOddId(Long.valueOf(102));
		TicketRow tr3= new TicketRow();
		tr3.setBetOddId(Long.valueOf(103));
		
		//ticket.addTicketRow(tr1).addTicketRow(tr2).addTicketRow(tr3);
		
		//ticketService.addTicket(ticket);
	}*/

}
