package com.deeploma.bettingshop.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.deeploma.bettingshop.domain.basic.Match;
import com.deeploma.bettingshop.domain.basic.Result;
import com.deeploma.bettingshop.domain.basic.Team;
import com.deeploma.bettingshop.domain.betting.BetOdd;
import com.deeploma.bettingshop.domain.betting.Ticket;

@Mapper
public interface MatchTeamsMapper {
	
	public List<Team> findAllTeams();
	
	public List<Team> findAllTeamsBySportId(@Param("sid") Integer sid);
		
	public List<Result> findAllResultsForMatchId(@Param("mid") Integer mid);
	
	public List<Result> findAllResults();
	
	public Match findMatchById(@Param("mid")Integer id);
	
	public Match findMatchByBetOddId(@Param("boid")Long boid);
	
	public List<Match> findAllMatches();

	public List<Result> findAllVerifiedResults();

	public List<Ticket> findAllActiveTicketsWithMatchIds(List<Long> matchIds);

	public List<BetOdd> findAllOddsForMatchIds(List<Long> matchIds);

}
