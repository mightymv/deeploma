package com.deeploma.reco.algorithm;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.deeploma.reco.controler.Recommendation;
import com.deeploma.reco.domain.UserTickets;
import com.deeploma.reco.dto.MatchDto;
import com.deeploma.reco.dto.TicketDto;
import com.deeploma.reco.mongodao.MongoRepository;

@Component
@Primary
public class SimpleRecommendationStrategy implements RecommendationStrategy{
	
	private static final int N_MATCHES = 3;

	private final static Logger logger = LoggerFactory.getLogger(SimpleRecommendationStrategy.class);
	
	@Autowired
	private MongoRepository ma;
	
	@Autowired
	private RecommendationStorage matrix;
	
	@Override
	public List<Long>  recommendForUser(Long id) {
		
		List<MatchDto> lastNmatches = lastNMatches(id);				
		Set<Long> userMatchesSet = lastNmatches.stream().map(match -> match.getId()).collect(toSet());				
		List<Long>  matches = matrix.findBestRecommandationForMatches(lastNmatches);

		// izbaci one koje je igrao do tada
 		return matches.stream().filter(matchId -> !userMatchesSet.contains(matchId)).collect(toList());
	}

	@Override
	public List<MatchDto> lastNMatches(Long userId) {
		//logger.info("Recomendations za igraca : " + id);
		Set<TicketDto> ut = ma.findTicketsForUser(userId);
		if (ut == null || ut.isEmpty())
			return Collections.emptyList();
		
		List<TicketDto> ts = ut.stream()
				.sorted((time1, time2) -> Long.compare(time2.getTime().getMillis(), time1.getTime().getMillis()))
				.collect(toList());
		
		List<MatchDto> allUserMatches = ts.stream().map( t -> t.getRows()
				.stream().map(r -> r.getMatch()).collect(toList())).flatMap( m -> m.stream()).distinct().collect(toList());
		//logger.info("Za igraca [{}],  ovo su poslednji odigrani mecevi : {}" , id, Arrays.toString(allUserMatches.toArray()));
		
		List<MatchDto> lastNmatches = allUserMatches.subList(0, allUserMatches.size() >= N_MATCHES ? N_MATCHES : allUserMatches.size());
		return lastNmatches;
	}

}
