package com.deeploma.bettingshop.services.impls;

import static com.deeploma.bettingshop.domain.betting.TicketStatus.ACTIVE;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.builder.RecursiveToStringStyle;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.eclipse.jdt.internal.compiler.apt.model.ErrorTypeElement;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.deeploma.bettingshop.domain.basic.Match;
import com.deeploma.bettingshop.domain.basic.MatchStatus;
import com.deeploma.bettingshop.domain.betting.Ticket;
import com.deeploma.bettingshop.domain.betting.TicketRow;
import com.deeploma.bettingshop.domain.betting.TicketRowStatus;
import com.deeploma.bettingshop.exception.ApplicationException;
import com.deeploma.bettingshop.exception.ErrorType;
import com.deeploma.bettingshop.mapper.MatchTeamsMapper;
import com.deeploma.bettingshop.mapper.TicketMapper;
import com.deeploma.bettingshop.services.TicketService;


@Component
public class TicketServiceImpl implements TicketService{
	
	private final static Logger logger = LoggerFactory.getLogger(TicketServiceImpl.class);
	
	@Autowired
	TicketMapper ticketMapper;
	
	@Autowired
	private  ApplicationEventPublisher publisher;
	
	@Autowired
	private MatchTeamsMapper matchMapper;
	
	@Value("${ticket.check-time}")
	private boolean checkTime;
	
	@Value("${ticket.check-rules}")
	private boolean checkRules;

	@Override
	@Transactional
	public Ticket addTicket(Ticket ticket) {
	
		if (checkRules) {
		    checkTicketRules(ticket);
		}
		
		ticket.setTime(DateTime.now());
		
		ticketMapper.insertTicket(ticket);		
		for(TicketRow tRow : ticket.getTicketRows()) {
			tRow.setTicketId(ticket.getId());
			ticketMapper.insertTicketRow(tRow);
			tRow.setTicketRowStatus(TicketRowStatus.ACTIVE);
		}	
		ticket.setTicketStatus(ACTIVE);
		
		publisher.publishEvent(ticket);
		return ticket;
	}

	private void checkTicketRules(Ticket ticket) {
		
		checkBasicRules(ticket);
		checkMatchesRules(ticket);
		
	}

	private void checkMatchesRules(Ticket ticket) {
		Set<Long> playedMatches = new HashSet<Long>();
		ticket.getTicketRows().forEach(row -> {
			if (playedMatches.contains(row.getMatchId()))
				throw new ApplicationException(ErrorType.TICKET_CANNOT_CONTAIN_MORE_THAN_ONE_ROW_WITH_SAME_MATCH);
			playedMatches.add(row.getMatchId());
		});
	}

	private void checkBasicRules(Ticket ticket) {
		DateTime now = new DateTime();
		//TODO ovde bi trebalo iz cache-a uzimati meceve
		ticket.getTicketRows().forEach(ticketRow -> {
			Match match = matchMapper.findMatchById(ticketRow.getMatchId());
			if (MatchStatus.FINISHED.equals(match.getMatchStatus())) {
				logger.warn( "Kladjenje nije moguce. Mec je u pogresnom statusu [ {} ].", ToStringBuilder.reflectionToString(match, new RecursiveToStringStyle()));
				throw new ApplicationException(ErrorType.MATCH_BAD_STATUS);
			};
			if (checkTime && match.getStartTime().isAfter(now)) {
				logger.warn( "Kladjenje nije moguce. Mec je vec poceo[ {} ].", ToStringBuilder.reflectionToString(match, new RecursiveToStringStyle()));				
				throw new ApplicationException(ErrorType.MATCH_ALREADY_STARTED);
			}
		});
		
	}

}
