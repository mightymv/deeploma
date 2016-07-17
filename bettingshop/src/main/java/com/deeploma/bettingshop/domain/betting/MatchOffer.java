package com.deeploma.bettingshop.domain.betting;

import java.util.List;

import com.deeploma.bettingshop.domain.basic.Match;

public class MatchOffer extends Match {
	
	
	
	private List<BetOdd>  odds;

	public List<BetOdd> getOdds() {
		return odds;
	}

	public void setOdds(List<BetOdd> odds) {
		this.odds = odds;
	}

}
