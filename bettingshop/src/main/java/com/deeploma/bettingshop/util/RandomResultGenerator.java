package com.deeploma.bettingshop.util;

import java.util.List;
import java.util.Optional;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.deeploma.bettingshop.domain.basic.Result;
import com.deeploma.bettingshop.domain.basic.ResultStatus;
import com.deeploma.bettingshop.domain.basic.ResultType;
import com.deeploma.bettingshop.domain.betting.BetOdd;
import com.deeploma.bettingshop.domain.betting.MatchOffer;
import com.deeploma.bettingshop.domain.betting.SubGame;
import com.deeploma.bettingshop.dto.ResultsVerified;
import com.deeploma.bettingshop.mapper.OfferMapper;
import com.deeploma.bettingshop.mapper.ResultMapper;

import reactor.bus.Event;
import reactor.bus.EventBus;


@Component
public class RandomResultGenerator {
	
	private final static Logger logger = LoggerFactory.getLogger(RandomResultGenerator.class);
	
	
	@Autowired
	private OfferMapper offerMapper;
	
	@Autowired
	private ResultMapper resMapper;
	
	@Autowired
	private EventBus eventBus;
	
	
	@Transactional
	public void generateResultsForDate(String date) {

		List<MatchOffer> matches = offerMapper.getOfferForDate(new DateTime(date));
		
		matches.stream().forEach( match -> {
			generateResults(match);
	
		});
		
		eventBus.notify("results", Event.wrap(new ResultsVerified()));
	}

	private void generateResults(MatchOffer match) {
		if (match.getResults() != null && !match.getResults().isEmpty()) 
			return;
		List<BetOdd>  odds = match.getOdds();
		
		Optional<BetOdd> halfTimeOdd = odds.stream().filter(odd -> SubGame.HOME_TEAM_WIN_HALF_TIME.getId().equals(odd.getSubGame().getId())).findFirst();
		
		Optional<BetOdd> fullTimeOdd = odds.stream().filter(odd -> SubGame.HOME_TEAM_WIN_FULL_TIME.getId().equals(odd.getSubGame().getId())).findFirst();
		
		
		Result halfTimeResult = generateHalfTimeResult(halfTimeOdd.get());		
		Result fullTimeResult = generateFullTimeResult(fullTimeOdd.get(), halfTimeResult);
		
		halfTimeResult.setResultStatus(ResultStatus.VERIFIED);
		halfTimeResult.setMatchId(match.getId());
				
		fullTimeResult.setResultStatus(ResultStatus.VERIFIED);
		fullTimeResult.setMatchId(match.getId());
		
		try {
			resMapper.insertResult(halfTimeResult);
			resMapper.insertResult(fullTimeResult);
		} catch (Throwable ex){
			logger.debug("Greska insert rezultata");
		}
	}

	private Result generateFullTimeResult(BetOdd betOdd, Result halfTimeResult) {
		// TODO Auto-generated method stub
		
		Result res = new Result();
		res.setResultType(ResultType.FULL_TIME_SCORE);
		res.setHomeTeam(Integer.valueOf(3));
		res.setVisitorTeam(Integer.valueOf(1));
		return res;
	}

	private Result generateHalfTimeResult(BetOdd betOdd) {
		Result res = new Result();
		res.setResultType(ResultType.HALF_TIME_SCORE);
		res.setHomeTeam(Integer.valueOf(1));
		res.setVisitorTeam(Integer.valueOf(1));
		return res;
	}

}
