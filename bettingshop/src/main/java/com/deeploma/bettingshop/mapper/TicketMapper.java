package com.deeploma.bettingshop.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.deeploma.bettingshop.domain.betting.Ticket;
import com.deeploma.bettingshop.domain.betting.TicketRow;

@Mapper
public interface TicketMapper {
	
	public List<Ticket> findByUserId(Integer userId);
	
	public List<Ticket> findByMatchId(Integer matchId);
	
	public void insertTicket(@Param("ticket")Ticket ticket);
	
	public void insertTicketRow(@Param("ticketRow")TicketRow ticketRow) ;
	
 
}
