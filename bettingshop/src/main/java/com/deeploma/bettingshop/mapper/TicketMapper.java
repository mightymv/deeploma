package com.deeploma.bettingshop.mapper;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.joda.time.DateTime;

import com.deeploma.bettingshop.domain.betting.Ticket;
import com.deeploma.bettingshop.domain.betting.TicketRow;
import com.deeploma.bettingshop.domain.betting.TicketRowStatus;
import com.deeploma.bettingshop.domain.betting.TicketStatus;

@Mapper
public interface TicketMapper {
	
	public List<Ticket> findByUserId(Integer userId);
	
	public List<Ticket> findByMatchId(Integer matchId);
	
	public void insertTicket(@Param("ticket")Ticket ticket);
	
	public void insertTicketRow(@Param("ticketRow")TicketRow ticketRow) ;
	
	public List<Ticket> findAllTicketsWithMatchIds(@Param("matchIds")Set<Long> matchIds);

	public void updateTicketStatus(@Param("status")TicketStatus status,@Param("id") Long id);

	public void updateTicketRowStatus(@Param("status")TicketRowStatus status,@Param("id") Long id);
	
	public List<Ticket> findAllWinnerTicketsForDate(@Param("date")DateTime date);
 
}
