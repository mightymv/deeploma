package com.deeploma.reco.algorithm;

import java.util.List;

import com.deeploma.reco.dto.MatchDto;

public interface RecommendationStrategy {

	public List<Long> recommendForUser(Long id);

	public List<MatchDto> lastNMatches(Long id);
	
	

}
