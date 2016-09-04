package com.deeploma.bettingshop.util;

import java.util.List;
import java.util.Optional;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;

import com.deeploma.bettingshop.domain.basic.Result;
import com.deeploma.bettingshop.domain.basic.ResultStatus;
import com.deeploma.bettingshop.domain.basic.ResultType;
import com.deeploma.bettingshop.domain.betting.BetOdd;
import com.deeploma.bettingshop.domain.betting.MatchOffer;
import com.deeploma.bettingshop.domain.betting.SubGame;
import com.deeploma.bettingshop.mapper.OfferMapper;

public class RandomResultGenerator {
	
	@Autowired
	private OfferMapper offerMapper;
	
	public void generateResultsForDate(String date) {
		
		
		List<MatchOffer> matches = offerMapper.getOfferForDate(new DateTime(date));
		
		matches.stream().forEach( match -> {
			List<BetOdd>  odds = match.getOdds();
			
			Optional<BetOdd> halfTimeOdd = odds.stream().filter(odd -> SubGame.HOME_TEAM_WIN_HALF_TIME.getId().equals(odd.getSubGame().getId())).findFirst();
			
			Optional<BetOdd> fullTimeOdd = odds.stream().filter(odd -> SubGame.HOME_TEAM_WIN_FULL_TIME.getId().equals(odd.getSubGame().getId())).findFirst();
			
			
			Result halfTimeResult = generateHalfTimeResult(halfTimeOdd.get());
			
			Result fullTimeResult = generateHalfTimeResult(halfTimeOdd.get(), halfTimeResult);
			
			halfTimeResult.setResultStatus(ResultStatus.VERIFIED);
			halfTimeResult.setMatchId(match.getId());
			
			
			fullTimeResult.setResultStatus(ResultStatus.VERIFIED);
			fullTimeResult.setMatchId(match.getId());
			
			
			
		});
	}

	private Result generateHalfTimeResult(BetOdd betOdd, Result halfTimeResult) {
		// TODO Auto-generated method stub
		
		Result res = new Result();
		res.setResultType(ResultType.HALF_TIME_SCORE);
		
		return res;
	}

	private Result generateHalfTimeResult(BetOdd betOdd) {
		Result res = new Result();
		res.setResultType(ResultType.FULL_TIME_SCORE);
		
		return res;
	}

}
