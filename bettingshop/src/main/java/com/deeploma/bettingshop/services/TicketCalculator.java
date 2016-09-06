package com.deeploma.bettingshop.services;

import java.util.HashMap;
import java.util.List;

import static java.util.stream.Collectors.groupingBy;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.deeploma.bettingshop.domain.basic.Result;
import com.deeploma.bettingshop.domain.basic.ResultStatus;
import com.deeploma.bettingshop.domain.betting.BetOdd;
import com.deeploma.bettingshop.domain.betting.BetOddStatus;
import com.deeploma.bettingshop.domain.betting.Game;

import com.deeploma.bettingshop.domain.betting.Ticket;
import com.deeploma.bettingshop.domain.betting.TicketRow;
import com.deeploma.bettingshop.domain.betting.TicketStatus;
import com.deeploma.bettingshop.dto.ResultsVerified;

import static com.deeploma.bettingshop.domain.basic.ResultStatus.CALCULATED;
import static com.deeploma.bettingshop.domain.betting.BetOddStatus.ACTIVE;
import static com.deeploma.bettingshop.domain.betting.TicketRowStatus.*;
import static com.deeploma.bettingshop.domain.betting.TicketStatus.ACTIVE;

import com.deeploma.bettingshop.mapper.MatchTeamsMapper;
import com.deeploma.bettingshop.mapper.OfferMapper;
import com.deeploma.bettingshop.mapper.ResultMapper;
import com.deeploma.bettingshop.mapper.TicketMapper;
import com.deeploma.bettingshop.messaging.SendingBean;
import com.deeploma.bettingshop.validator.BetValidator;
import com.deeploma.bettingshop.validator.GameValidatorRegister;

import reactor.bus.Event;
import reactor.fn.Consumer;


@Component
public class TicketCalculator implements Consumer<Event<ResultsVerified>>{
	
	private final static Logger logger = LoggerFactory.getLogger(TicketCalculator.class);
	
	@Autowired
	private OfferMapper offerMapper;
	
	@Autowired
	private ResultMapper resultMapper;
	
	@Autowired
	private TicketMapper ticketMapper;
	
	@Autowired
	private UserStandingsCalculator userCalculator;
	
	@Autowired
	SendingBean sendingBean;
	
	@Transactional
	public void calculateBetOdds() {
		
		//ovo su rezultati koji su najsvezije verifikovani
		List<Result> verifiedResults = resultMapper.findAllVerifiedResults();

		Map<Long, List<Result>> matchResults = verifiedResults.stream().collect(groupingBy(Result::getMatchId));
		
		Set<Long> matchIds = matchResults.keySet();	
		if (matchIds == null || matchIds.isEmpty()) 
			return;
		//nadji sve kvote ko
		List<BetOdd> betOds = offerMapper.findAllOddsForMatchIds(matchIds);
		
		betOds.forEach(odd -> {
			 BetValidator validator = resolveValidator(odd.getSubGame().getGame());			
			 Boolean oddStatus = validator.validate(odd.getSubGame(), matchResults.get(odd.getMatch().getId()));
			 if (oddStatus == null) {
					logger.debug("Validator nije mogao da izracuna dobitnost");
					return;
				}
				if (!oddStatus) {
					// setuj status kvote
					logger.debug("Kvota {} , je {}", odd.getId(), BetOddStatus.LOSER);
					offerMapper.updateBetOddStatus(odd.getId(), BetOddStatus.LOSER);									
				} else {
					logger.debug("Kvota {} , je {}", odd.getId(), BetOddStatus.WINNER);					
					offerMapper.updateBetOddStatus(odd.getId(), BetOddStatus.WINNER);					
				} 
		 
		});       
		
	}
		
	@Transactional
	public void calculateTickets() {
		
		//ovo su rezultati koji su najsvezije verifikovani
		List<Result> verifiedResults = resultMapper.findAllVerifiedResults();

		Map<Long, List<Result>> matchResults = verifiedResults.stream().collect(groupingBy(Result::getMatchId));
		
		Set<Long> matchIds = matchResults.keySet();
		
		if (matchIds == null || matchIds.isEmpty()) 
			return;
		
		// nalazimo sve tikete na kojima se pojavljuju mecevi od interesa
		List<Ticket> ticketsForCalculations = ticketMapper.findAllTicketsWithMatchIds(matchIds);
		
		//nadji sve kvote ko
		List<BetOdd> betOds = offerMapper.findAllOddsForMatchIds(matchIds);
		
		ticketsForCalculations.stream().forEach(ticket -> {  //TODO paralelizacija ove obrade mozda
			calculateTicket(matchResults, betOds, ticket);
		});
		
		verifiedResults.forEach( result -> {
			resultMapper.updateResultStatus(result.getId(), CALCULATED);
		});
		
		ticketsForCalculations.forEach(ticket -> sendingBean.sendTicket(ticket)); // ovo je u transakciji - principijelno nije ok
	}

	private void calculateTicket(Map<Long, List<Result>> matchResults, List<BetOdd> betOds, Ticket ticket) {
		List<TicketRow> activeRows = ticket.getTicketRows().stream()
		.filter(row -> ACTIVE.getId().equals(row.getTicketRowStatus().getId()))
		.collect(Collectors.toList()); //TODO ovde jos ostaje pitanje rekalkulacije, ako neko pogresi sa rezultatom pa ga ponovo prebaci u verified
		
		activeRows.forEach(row -> checkRow(row, betOds));
		
		calculateTicketStatus(ticket);
	}

	private void calculateTicketStatus(Ticket ticket) {
		if (ACTIVE.getId().equals(ticket.getTicketStatus().getId())) { //ako je do sada bio aktivan, onda ima smisla
			boolean loser = ticket.getTicketRows().stream().anyMatch( row -> checkLoser(row));
			
			if (loser) {
				ticket.setTicketStatus(TicketStatus.LOSER);
				ticketMapper.updateTicketStatus(TicketStatus.LOSER, ticket.getId());
			} else {
			   boolean winner = ticket.getTicketRows().stream().allMatch(row -> checkWinner(row));
			   if (winner)
				   ticket.setTicketStatus(TicketStatus.WINNER);
				   ticketMapper.updateTicketStatus(TicketStatus.WINNER, ticket.getId());
			}
		}
	}

	private boolean checkLoser(TicketRow row) {
		return LOSER.getId().equals(row.getTicketRowStatus().getId());
	}

	private boolean checkWinner(TicketRow row) {
		return WINNER.getId().equals(row.getTicketRowStatus().getId());
	}

	private void checkRow(TicketRow row,  List<BetOdd> betOds) {
	
				
		BetOdd betOdd = findBetOdd(row, betOds);
		if (betOdd == null) {
			logger.warn("Nije pronadjena kvota za row id : {}", row.getId());
			return ;
		}
			
	
		if (betOdd.getBetOddStatus().getId().equals(ACTIVE.getId())) {
			return;
		}
		if (BetOddStatus.LOSER.getId().equals(betOdd.getBetOddStatus().getId())) {
			// setuj status reda na gubitni
			row.setTicketRowStatus(LOSER);
			ticketMapper.updateTicketRowStatus(LOSER, row.getId());
		} else {
			row.setTicketRowStatus(WINNER);
			ticketMapper.updateTicketRowStatus(WINNER, row.getId());
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
    
 


	@Override
	@Transactional
	public void accept(Event<ResultsVerified> t) {
		logger.info("Ide se u obracun kvota");
		calculateBetOdds();
		calculateTickets();
		userCalculator.calculateStandings();
	}

	

}
