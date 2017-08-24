package com.deeploma.reco.algorithm;

import static java.util.Comparator.comparingDouble;
import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.ToDoubleFunction;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.deeploma.reco.domain.SingleUserTicket;
import com.deeploma.reco.domain.UserMatchCountBehaviour;
import com.deeploma.reco.dto.MatchDto;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

/**
 * Ovde je sva logika oko izracunavanja za preporuke. I mnogo je ruzan kod.
 * @author veljkom
 *
 */
@Component
public class RecommendationStorage {
	
	private final static Logger logger = LoggerFactory.getLogger(RecommendationStorage.class);
		
	private Table<Long, MatchDto, Double> userTickets = HashBasedTable.create();

	private Table<MatchDto, MatchDto, Double> matchToMatchRelations = HashBasedTable.create();	
	
	private HashMap<MatchDto, Double>  matchesVectors = new HashMap<MatchDto, Double>();
	
	public Table<Long, MatchDto, Double> getUserTickets() {
		return userTickets;
	}
	
	//TODO ne znam da li sam u zivotu napisao ovako ruzan kod
	/**
	 * 
	 * @param usersMatches
	 * @param behaviours
	 */
	public void recalculate(List<UserMatches> usersMatches, Map<Long, UserMatchCountBehaviour> behaviours) {
		
		Table<Long, MatchDto, Double> newUserTickets = HashBasedTable.create();		
		Table<MatchDto, MatchDto, Double> newMatchToMatchRelations = HashBasedTable.create();		
		HashMap<MatchDto, Double>  newMatchesVectors = new HashMap<MatchDto, Double>();
				
		//reload userTickets
		usersMatches.forEach( userMatches -> {
			userMatches.getMatches().forEach(matchData -> {
				newUserTickets.put(userMatches.getId(), matchData.getMatch(), calculateValue(matchData.getCount(), behValue(behaviours, userMatches, matchData)));
			    
			});
			logger.info("User: " + userMatches.getId() + " _ " + newUserTickets.row(userMatches.getId()).entrySet());
		});
						
		newUserTickets.columnKeySet().stream().forEach(match -> {			
			recalculateVector(newUserTickets, newMatchesVectors, match);			
		});
		
		logVectors(newMatchesVectors);	
		
		newUserTickets.columnKeySet().stream().forEach(match -> recalculateForMatchRelations(newUserTickets, newMatchToMatchRelations, newMatchesVectors, match, false));
				
		//set new values
		this.userTickets = newUserTickets;
		this.matchToMatchRelations = newMatchToMatchRelations;
		this.matchesVectors = newMatchesVectors;
		logTable(newMatchToMatchRelations);
	}

	private int behValue(Map<Long, UserMatchCountBehaviour> res, UserMatches uMatches, MatchCount matchData) {
		UserMatchCountBehaviour beh = res.get(uMatches.getId());
		if (beh == null) 
			return 0;
		Map<Long, AtomicInteger> matchCounts = beh.getMatchesCount();
		if (matchCounts == null) {
			return 0;
		}
		AtomicInteger val = matchCounts.get(matchData.getMatch().getId());
		
		return val == null ? 0 : val.intValue();
	}

	private double calculateValue(int ticketPlayedMatch, int behaviourMatch) {
		double tick = 5 + (ticketPlayedMatch -1) > 10 ? 10 : 5 + (ticketPlayedMatch -1);
		double beh = behaviourMatch;
		return beh + tick > 10 ? 10 : beh + tick;
	}

	private void recalculateVector(Table<Long, MatchDto, Double> newTable, HashMap<MatchDto, Double> vectors, MatchDto match) {
		Collection<Double> vals = newTable.column(match).values();
		Double sum = vals.stream().mapToDouble( val -> val * val).sum();
		vectors.put(match, Math.sqrt(sum));	
		
	}

	private void logVectors(HashMap<MatchDto, Double> vectors) {
		logger.info("Broj elemenata u vektorima: " + vectors.size());
		vectors.entrySet().forEach(entry -> logger.info( "Vector---   Match : {} , vector value {} " ,  entry.getKey().getId() , entry.getValue()));
	}
	
	private void recalculateForMatchRelations(Table<Long, MatchDto, Double> newTable,
			Table<MatchDto, MatchDto, Double> relations, HashMap<MatchDto, Double> vectors, MatchDto match, boolean force) {
			
		newTable.columnKeySet().stream().forEach(match2 -> {
			if (!match2.equals(match) && (relations.get(match2, match) == null || force)) { 
				double res = 0;
				for (Long userId: newTable.column(match).keySet()) {
					Double num1 = newTable.column(match).get(userId);
					Double num2 = newTable.column(match2).get(userId);
					num1 = num1 != null ? num1 : 0;
					num2 = num2 != null ? num2 : 0;
				    res = res + num1 * num2;						
				}
				//int ran = ThreadLocalRandom.current().nextInt(1, 20);
			    relations.put(match, match2, (res / (vectors.get(match) * vectors.get(match2) ) ));
			    relations.put(match2, match, (res / (vectors.get(match) * vectors.get(match2) ) ));
			}				
		});		
	}
	
	/**
	 * Novi tiket, update podataka 
	 *
	 * @param userTicket
	 */
	//TODO potencijalno usko grlo, multithreading
	public void addUserTicket(SingleUserTicket userTicket) {
		
		Long userId = userTicket.getUserId();		
		List<MatchDto> matches = userTicket.getTicketDto().getRows().stream().map(row -> row.getMatch()).collect(Collectors.toList());
		matches.forEach(match -> {
			Double val = userTickets.get(userId, match);
			logger.info(val.toString());
			userTickets.put(userId, match, Double.valueOf(val + 1));
			recalculateVector( userTickets, matchesVectors, match);
			recalculateForMatchRelations(userTickets, matchToMatchRelations, matchesVectors , match, true);
		});
		logTable(matchToMatchRelations);
		
	}

	private void logTable(Table<MatchDto, MatchDto, Double> table) {
		logger.info("-------------------  Novo izracunavanje  -----------------------");
		table.rowKeySet().forEach(match1 -> {
			table.columnKeySet().forEach(match2 -> {
				logger.info("Odnos : " + match1.getId() + " -> " + match2.getId() + " : " + table.get(match1, match2));
			});
		});
	}
	
	public List<Long> findBestRecommandationForMatches(List<MatchDto> baseMatches) {
				
		List<Entry<MatchDto, Double>>  entryes = sortEntriesByValueAndCollectList(filterMatrixRows(baseMatches));
		
		List<MatchDto> result = transformToMatchListAndFilterDistinct(entryes);			
		logger.info("Sortirana lista id-eva : {}" , Arrays.toString(result.toArray()));	
		
		return result.stream().map(match -> match.getId()).collect(Collectors.toList());
	}
	
	private List<MatchDto> transformToMatchListAndFilterDistinct(List<Entry<MatchDto, Double>> entryes) {
		List<MatchDto> result = entryes.stream().map(en -> en.getKey()).distinct().collect(toList());
		List<Long> rsIds = result.stream().map(m -> m.getId()).collect(toList());
		logger.info("transforming " + rsIds.toString());
  		return result ; 
	}

	private List<Entry<MatchDto, Double>> sortEntriesByValueAndCollectList(List<Entry<MatchDto, Double>> res) {
		return res.stream().filter(matchEntry -> matchEntry.getValue() != null).sorted(comparingDouble(comp()).reversed()).collect(Collectors.toList());
	}

	public List<Entry<MatchDto, Double>> filterMatrixRows(List<MatchDto> baseMatches) {
		return baseMatches.stream().map
				(match -> matchToMatchRelations.row(match).entrySet())
				.collect(ArrayList::new, List::addAll, List::addAll);  // opako	
	}

	@SuppressWarnings("unchecked")
	private ToDoubleFunction<? super Object> comp() {
		return e -> ((Entry<MatchDto, Double>) e).getValue();
	}
	
	public Table<MatchDto, MatchDto, Double> getRelations() {
		return matchToMatchRelations;
	}


}
