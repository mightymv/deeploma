package com.deeploma.bettingshop.services;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.hibernate.validator.internal.util.privilegedactions.GetConstraintValidatorList;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.deeploma.bettingshop.domain.betting.Ticket;
import com.deeploma.bettingshop.mapper.MatchTeamsMapper;
import com.hazelcast.map.impl.SimpleEntryView;

import java.util.AbstractMap.SimpleEntry;

public class UserStandingsCalculator {
	
	private final static Logger logger = LoggerFactory.getLogger(UserStandingsCalculator.class);
	
	@Autowired
	private MatchTeamsMapper mtMapper;
	
	
	public void calculateStandings() {
		
		
		
		List<Ticket> tickets = mtMapper.findAllWinnersForDate(DateTime.now().withTimeAtStartOfDay());
		
		 Map<Long, List<Ticket>> groupByUser = tickets.stream().collect(Collectors.groupingBy(Ticket::getUserId));
		
		 Map<Long , Ticket> userTicket = groupByUser.entrySet().stream().map( entry -> { Optional<Ticket> maxTicket = entry.getValue().stream().collect(Collectors.maxBy(new Comparator<Ticket>() {

			@Override
			public int compare(Ticket t1, Ticket t2) {				
				return t1.getCumulativeOdd().compareTo(t2.getCumulativeOdd());
			}
		}));
			 
		 
		 return new SimpleEntry<Long, Ticket>(entry.getKey(), maxTicket.get());
		
		 }).collect(Collectors.toMap(entry -> entry.getKey(), entry -> entry.getValue()));
		
		 
		 //TODO snimi user ticket mapu
		 
	}

}
