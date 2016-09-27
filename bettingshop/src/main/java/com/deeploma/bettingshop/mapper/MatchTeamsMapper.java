package com.deeploma.bettingshop.mapper;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.joda.time.DateTime;

import com.deeploma.bettingshop.domain.basic.Match;
import com.deeploma.bettingshop.domain.basic.MatchStatus;
import com.deeploma.bettingshop.domain.basic.Result;
import com.deeploma.bettingshop.domain.basic.ResultStatus;
import com.deeploma.bettingshop.domain.basic.Team;
import com.deeploma.bettingshop.domain.betting.BetOdd;
import com.deeploma.bettingshop.domain.betting.Ticket;
import com.deeploma.bettingshop.domain.betting.TicketRowStatus;
import com.deeploma.bettingshop.domain.betting.TicketStatus;

@Mapper
public interface MatchTeamsMapper {
	
	public List<Team> findAllTeams();
	
	public List<Team> findAllTeamsBySportId(@Param("sid") Integer sid);
		
	public List<Result> findAllResultsForMatchId(@Param("mid") Integer mid);
	
	public List<Result> findAllResults();
	
	public Match findMatchById(@Param("mid")Long id);
	
	public Match findMatchByBetOddId(@Param("boid")Long boid);
	
	public List<Match> findAllMatches();
	
	public void updateMatchStatus(@Param("mid")Long mid,@Param("status") MatchStatus status);

}
