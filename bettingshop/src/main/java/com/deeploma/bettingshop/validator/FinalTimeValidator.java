package com.deeploma.bettingshop.validator;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.deeploma.bettingshop.domain.basic.Result;
import com.deeploma.bettingshop.domain.basic.ResultType;
import com.deeploma.bettingshop.domain.betting.SubGame;

public class FinalTimeValidator implements BetValidator {
	
	private final static Logger logger = LoggerFactory.getLogger(FinalTimeValidator.class);

	@Override
	public Boolean validate(final SubGame subGame, List<Result> results) {
		
		Optional<Result> result = results.stream().filter(res -> res.getResultType().equals(ResultType.FULL_TIME_SCORE)).findFirst();
		
		if (!result.isPresent()) {
			logger.debug("Nije pronadjen tip rezultata potreban za izracunavanje dobitnosti");
			return null;
		}
		
		switch (subGame) {
		
		case HOME_TEAM_WIN_FULL_TIME : {
			return (result.get().getHomeTeam() > result.get().getVisitorTeam());
		}
		
		case DRAW_FULL_TIME:{
			return (result.get().getHomeTeam().equals(result.get().getVisitorTeam()));
		}
			
		
		case VISITOR_TEAM_WIN_FULL_TIME: {
			return (result.get().getHomeTeam() < result.get().getVisitorTeam());
		}
			
		
		default:
			break;			
		}
		
		return null;
	}

}
