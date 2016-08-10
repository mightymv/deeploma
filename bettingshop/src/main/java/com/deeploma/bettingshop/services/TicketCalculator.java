package com.deeploma.bettingshop.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.deeploma.bettingshop.domain.basic.Result;
import com.deeploma.bettingshop.domain.betting.BetOdd;
import com.deeploma.bettingshop.domain.betting.SubGame;
import com.deeploma.bettingshop.domain.betting.Ticket;
import com.deeploma.bettingshop.domain.betting.TicketRow;
import com.deeploma.bettingshop.domain.betting.TicketRowStatus;
import com.deeploma.bettingshop.mapper.MatchTeamsMapper;
import com.deeploma.bettingshop.validator.BetValidator;

public class TicketCalculator {
	
	@Autowired
	private MatchTeamsMapper mtMapper;
	
	public void calculateTickets() {
		
		
		List<Result> verifiedResults = mtMapper.findAllVerifiedResults();
		
		List<Long> matchIds = verifiedResults.stream().map(result -> result.getMatchId()).collect(Collectors.toList());
		
		List<Ticket> ticketsForCalculations = mtMapper.findAllActiveTicketsWithMatchIds(matchIds);
		
		List<BetOdd> betOds = mtMapper.findAllOddsForMatchIds(matchIds);
		
		ticketsForCalculations.forEach(ticket -> {
			List<TicketRow> activeRows = ticket.getTicketRows().stream()
			.filter(row -> row.getTicketRowStatus().equals(TicketRowStatus.ACTIVE))
			.collect(Collectors.toList());
	
			
			activeRows.forEach(row -> checkRow(row, verifiedResults, betOds));
			
		});
	}

	private Object checkRow(TicketRow row, List<Result> verifiedResults, List<BetOdd> betOds) {
		
		
		BetOdd betOdd = findMatchId(row, betOds);
		Long matchId = betOdd.getMatch().getId();
		
		List<Result> filteredResults = verifiedResults.stream().filter(result -> result.getMatchId().equals(matchId)).collect(Collectors.toList());
		
		BetValidator validator = findValidator(betOdd.getSubGame());
		
		Boolean rowState = validator.validate(row, filteredResults);
		
		return null;
	}
	
    private BetOdd findMatchId(TicketRow row, List<BetOdd> betOds ) {
    	for (BetOdd bo : betOds) {
			if (bo.getId().equals(row.getBetOddId()))
				return bo;
		}
    	
    	return null;
    }
    
    private BetValidator findValidator(SubGame game) {
    	return null;
    }

}
