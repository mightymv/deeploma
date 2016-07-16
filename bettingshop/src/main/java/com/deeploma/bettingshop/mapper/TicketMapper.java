package com.deeploma.bettingshop.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.deeploma.bettingshop.domain.betting.Ticket;

@Mapper
public interface TicketMapper {
	
	public List<Ticket> findByUserId(Integer userId);
	
	public List<Ticket> findByMatchId(Integer matchId);
	
 
}
