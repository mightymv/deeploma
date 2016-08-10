package com.deeploma.bettingshop.services.impls;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.deeploma.bettingshop.domain.betting.Ticket;
import com.deeploma.bettingshop.domain.betting.TicketRow;
import com.deeploma.bettingshop.mapper.TicketMapper;
import com.deeploma.bettingshop.services.TicketService;


@Component
public class TicketServiceImpl implements TicketService{
	
	@Autowired
	TicketMapper ticketMapper;

	@Override
	@Transactional
	public Ticket addTicket(Ticket ticket) {
	
		//TODO check time of played bets
		
		ticket.setTime(DateTime.now());
		
		ticketMapper.insertTicket(ticket);		
		for(TicketRow tRow : ticket.getTicketRows()) {
			tRow.setTicketId(ticket.getId());
			ticketMapper.insertTicketRow(tRow);			
		}		
		return ticket;
	}

}
