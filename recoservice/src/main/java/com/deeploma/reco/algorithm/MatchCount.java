package com.deeploma.reco.algorithm;

import com.deeploma.reco.dto.MatchDto;

public class MatchCount {
	
	private MatchDto match;
	
	private int count;
	
	public MatchCount(){
		
	}
	
	public MatchCount(MatchDto match , int count) {
		this.match = match;
		this.count = count;
	}

	public MatchDto getMatch() {
		return match;
	}

	public void setMatch(MatchDto match) {
		this.match = match;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}
