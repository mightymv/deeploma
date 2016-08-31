package com.deeploma.bettingshop.services;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.deeploma.bettingshop.domain.basic.Result;
import com.deeploma.bettingshop.domain.basic.ResultStatus;
import com.deeploma.bettingshop.domain.betting.BetOdd;
import com.deeploma.bettingshop.domain.betting.Game;

import com.deeploma.bettingshop.domain.betting.Ticket;
import com.deeploma.bettingshop.domain.betting.TicketRow;
import com.deeploma.bettingshop.domain.betting.TicketStatus;

import static com.deeploma.bettingshop.domain.betting.TicketRowStatus.*;

import com.deeploma.bettingshop.mapper.MatchTeamsMapper;

import com.deeploma.bettingshop.validator.BetValidator;
import com.deeploma.bettingshop.validator.GameValidatorRegister;



public class TicketCalculator {
	
	private final static Logger logger = LoggerFactory.getLogger(TicketCalculator.class);
	
	@Autowired
	private MatchTeamsMapper mtMapper;
	
	
	@Transactional
	public void calculateTickets() {
		
		//ovo su rezultati koji su najsvezije verifikovani
		List<Result> verifiedResults = mtMapper.findAllVerifiedResults();

		Map<Long, List<Result>> matchResults = populateMatchResultsMap(verifiedResults);
		
		Set<Long> matchIds = matchResults.keySet();
		
		// nalazimo sve tikete na kojima se pojavljuju mecevi od interesa
		List<Ticket> ticketsForCalculations = mtMapper.findAllTicketsWithMatchIds(matchIds);
		
		//nadji sve kvote ko
		List<BetOdd> betOds = mtMapper.findAllOddsForMatchIds(matchIds);
		
		ticketsForCalculations.stream().forEach(ticket -> {  //TODO paralelizacija ove obrade
			calculateTicket(matchResults, betOds, ticket);
		});
		
		verifiedResults.forEach( result -> {
			mtMapper.updateResultStatus(ResultStatus.CALCULATED, result.getId());
		});
	}

	private void calculateTicket(Map<Long, List<Result>> matchResults, List<BetOdd> betOds, Ticket ticket) {
		List<TicketRow> activeRows = ticket.getTicketRows().stream()
		.filter(row -> ACTIVE.getId().equals(row.getTicketRowStatus().getId()))
		.collect(Collectors.toList()); //TODO ovde jos ostaje pitanje rekalkulacije, ako neko pogresi sa rezultatom pa ga ponovo prebaci u verified
		
		activeRows.forEach(row -> checkRow(row, matchResults, betOds));
		
		calculateTicketStatus(ticket);
	}

	private void calculateTicketStatus(Ticket ticket) {
		if (TicketStatus.ACTIVE.getId().equals(ticket.getTicketStatus().getId())) { //ako je do sada bio aktivan, onda ima smisla
			boolean loser = ticket.getTicketRows().stream().anyMatch( row -> checkLoser(row));
			
			if (loser) {
				mtMapper.updateTicketStatus(TicketStatus.LOSER, ticket.getId());
			} else {
			   boolean winner = ticket.getTicketRows().stream().allMatch(row -> checkWinner(row));
			   if (winner)
				   mtMapper.updateTicketStatus(TicketStatus.WINNER, ticket.getId());
			}
		}
	}

	private boolean checkLoser(TicketRow row) {
		return LOSER.getId().equals(row.getTicketRowStatus().getId());
	}

	private boolean checkWinner(TicketRow row) {
		return WINNER.getId().equals(row.getTicketRowStatus().getId());
	}

	private Map<Long, List<Result>> populateMatchResultsMap(List<Result> verifiedResults) {
		Map<Long , List<Result>> matchResults = new HashMap<Long , List<Result>>();
		
		verifiedResults.stream().forEach( res -> {
			if (matchResults.get(res.getMatchId()) == null) {
				matchResults.put(res.getMatchId(), new ArrayList<Result>());
			}
		    matchResults.get(res.getMatchId()).add(res);		
		});
		return matchResults;
	}

	private void checkRow(TicketRow row, Map<Long , List<Result>> matchResults, List<BetOdd> betOds) {
		
        List<Result> filteredResults = matchResults.get( row.getMatchId());
		
		if (filteredResults.isEmpty() ) {
			logger.debug("Nisu pronadjeni rezultati za match id : {} . (ovaj row nije zakacen tekucom kalkulacijom)" ,  row.getMatchId());			
			return;
		}
				
		BetOdd betOdd = findBetOdd(row, betOds);
		if (betOdd == null) {
			logger.warn("Nije pronadjena kvota za row id : {}", row.getId());
			return ;
		}
			
		BetValidator validator = resolveValidator(betOdd.getSubGame().getGame());
		
		Boolean rowState = validator.validate(betOdd.getSubGame(), filteredResults);
		
		if (rowState == null) {
			logger.debug("Validator nije mogao da izracuna dobitnost");
			return;
		}
		if (!rowState) {
			// setuj status reda na gubitni
			row.setTicketRowStatus(LOSER);
			mtMapper.setTicketRowStatus(LOSER, row.getId());
		} else {
			row.setTicketRowStatus(WINNER);
			mtMapper.setTicketRowStatus(WINNER, row.getId());
		} 
		
	}
	
    private BetOdd findBetOdd(TicketRow row, List<BetOdd> betOds ) {
    	for (BetOdd bo : betOds) {
			if (bo.getId().equals(row.getBetOddId()))
				return bo;
		}
    	
    	return null;
    }
    
    private BetValidator resolveValidator(Game game) {
    	return GameValidatorRegister.findValidatorForGameId(game.getId());
    }

}
