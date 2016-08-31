package com.deeploma.bettingshop.validator;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.deeploma.bettingshop.domain.basic.Result;
import com.deeploma.bettingshop.domain.basic.ResultType;
import com.deeploma.bettingshop.domain.betting.SubGame;

public class HalfTimeValidator implements BetValidator {
	
	private final static Logger logger = LoggerFactory.getLogger(HalfTimeValidator.class);


	@Override
	public Boolean validate(SubGame subGame, List<Result> results) {
		Optional<Result> result = results.stream().filter(res -> res.getResultType().equals(ResultType.HALF_TIME_SCORE)).findFirst();
		
		if (!result.isPresent()) {
			logger.debug("Nije pronadjen tip rezultata potreban za izracunavanje dobitnosti");
			return null;
		}
		
		switch (subGame) {
		
		case HOME_TEAM_WIN_HALF_TIME : {
			return (result.get().getHomeTeam() > result.get().getVisitorTeam());
		}		
		case DRAW_HALF_TIME:{
			return (result.get().getHomeTeam().equals(result.get().getVisitorTeam()));
		}
					
		case VISITOR_TEAM_WIN_HALF_TIME: {
			return (result.get().getHomeTeam() < result.get().getVisitorTeam());
		}
		
		default:
			break;			
		}
		
		return null;
	}

}
