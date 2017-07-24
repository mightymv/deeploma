package com.deeploma.bettingshop.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.deeploma.bettingshop.domain.basic.Match;
import com.deeploma.bettingshop.domain.basic.MatchStatus;
import com.deeploma.bettingshop.domain.basic.Result;
import com.deeploma.bettingshop.domain.basic.Team;

@MapperScan
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
