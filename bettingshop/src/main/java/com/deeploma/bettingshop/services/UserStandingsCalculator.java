package com.deeploma.bettingshop.services;

import static java.util.Comparator.comparingDouble;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.maxBy;
import static java.util.stream.Collectors.toMap;

import java.util.AbstractMap.SimpleEntry;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.deeploma.bettingshop.domain.betting.Ticket;
import com.deeploma.bettingshop.domain.betting.UserStanding;
import com.deeploma.bettingshop.mapper.TicketMapper;


@Component
public class UserStandingsCalculator {
	
	private final static Logger logger = LoggerFactory.getLogger(UserStandingsCalculator.class);
	
	@Autowired
	private TicketMapper tMapper;
	
	@Autowired StandingsService ssrv;
	
	@Transactional
	public void calculateStandings() {
		
		
		DateTime time = DateTime.parse("2016-8-10");
		List<Ticket> tickets = tMapper.findAllWinnerTicketsForDate(time);
		
		 Map<Long, List<Ticket>> groupByUser = tickets.stream().collect(groupingBy(Ticket::getUserId));
		
		 Map<Long , Ticket> userTicket = groupByUser.entrySet().stream().map( entry -> 
		 { 	Optional<Ticket> maxTicket = entry.getValue().stream()
		 		.collect(maxBy(comparingDouble(t -> t.getCumulativeOdd())));

			
			return new SimpleEntry<Long, Ticket>(entry.getKey(), maxTicket.get());
		
		 }).collect(toMap(entry -> entry.getKey(), entry -> entry.getValue()));
		
		 ssrv.deleteForDate(time);
		 
		 userTicket.entrySet().forEach(entry ->  {		 
		 	logger.info("User {} . Ticket {} " , entry.getKey(), entry.getValue().getId());
		 	UserStanding standing = new UserStanding();
		 	standing.setDateOf(time);
		 	standing.setTicketId(entry.getValue().getId());
		 	standing.setUserId(entry.getKey());
		 	standing.setBestResult(entry.getValue().getCumulativeOdd());
		 	ssrv.saveStanding(standing);
		 }
		 );
		 
	}

}
