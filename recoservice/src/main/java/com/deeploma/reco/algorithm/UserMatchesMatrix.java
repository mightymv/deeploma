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
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.ToDoubleFunction;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.deeploma.reco.domain.SingleUserTicket;
import com.deeploma.reco.domain.UserMatchCountBehaviour;
import com.deeploma.reco.dto.MatchDto;
import com.deeploma.reco.dto.TicketDto;
import com.deeploma.reco.dto.TicketRowDto;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;


@Component
public class UserMatchesMatrix {
	
	private final static Logger logger = LoggerFactory.getLogger(UserMatchesMatrix.class);
	
	
	Table<Long, MatchDto, Double> userTickets = HashBasedTable.create();
	
	public Table<Long, MatchDto, Double> getUserTickets() {
		return userTickets;
	}

	Table<MatchDto, MatchDto, Double> relations = HashBasedTable.create();
	
	
	HashMap<MatchDto, Double>  vectors = new HashMap<MatchDto, Double>();
	
	
	//Table<String, MatchDto, Double> gTable = HashBasedTable.create();
	
	public static void main (String [] arg) {
		
	}
	
	public void setUsers(List<UserMatches> list, Map<Long, UserMatchCountBehaviour> res) {
		
		//logger.info(" --------   Novo --------");
		Table<Long, MatchDto, Double> newTable = HashBasedTable.create();		
		Table<MatchDto, MatchDto, Double> newRelations = HashBasedTable.create();		
		HashMap<MatchDto, Double>  vectors = new HashMap<MatchDto, Double>();
		
		list.forEach( uMatches -> {
			uMatches.getMatches().forEach(matchData -> {
				newTable.put(uMatches.getId(), matchData.getMatch(), calculateValue(matchData.getCount(), behValue(res, uMatches, matchData)));
			    
			});
			//logger.info("User: " + uMatches.getId() + " _ " + newTable.row(uMatches.getId()).entrySet());
		});
				
		
		newTable.columnKeySet().stream().forEach(match -> {			
			recalculateVector(newTable, vectors, match);			
		});
		
		//vectors.entrySet().forEach(entry -> logger.info( "Vector- Match : {} , value {} "  +  entry.getKey().getId() + " vec : " + entry.getValue()));
		
		
		newTable.columnKeySet().stream().forEach(match -> recalculateForMatch(newTable, newRelations, vectors, match, false));
		
		/*newRelations.rowKeySet().forEach(match1 -> {
			newRelations.columnKeySet().forEach(match2 -> {
				//logger.info("Odnos : " + match1.getId() + " -> " + match2.getId() + " : " + newRelations.get(match1, match2));
			});
		});*/
		this.userTickets = newTable;
		this.relations = newRelations;
		this.vectors = vectors;
	}

	private int behValue(Map<Long, UserMatchCountBehaviour> res, UserMatches uMatches, MatchCount matchData) {
		UserMatchCountBehaviour beh = res.get(uMatches.getId());
		if (beh == null) 
			return 0;
		Map<Long, AtomicInteger> matchCounts = beh.getMatchCount();
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

	
	private void recalculateForMatch(Table<Long, MatchDto, Double> newTable,
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
			    relations.put(match, match2, res / (vectors.get(match) * vectors.get(match2)));
			    relations.put(match2, match, res / (vectors.get(match) * vectors.get(match2)));
			}
				
		});
		
	}
	
	public void addUserTicket(SingleUserTicket userTicket) {
		
		Long userId = userTicket.getUserId();
		
		List<MatchDto> matches = userTicket.getTicketDto().getRows().stream().map(row -> row.getMatch()).collect(Collectors.toList());
		matches.forEach(match -> {
			Double val = userTickets.get(userId, match);
			System.out.println(val);
			userTickets.put(userId, match, Double.valueOf(val + 1));
			recalculateVector( userTickets, vectors, match);
			recalculateForMatch(userTickets, relations, vectors , match, true);
		});
		System.out.println("Idemo novoooo -----------------------");
		relations.rowKeySet().forEach(match1 -> {
			relations.columnKeySet().forEach(match2 -> {
				System.out.println("Odnos : " + match1.getId() + " -> " + match2.getId() + " : " + relations.get(match1, match2));
			});
		});
		
	}
	
	public List<Long> findBestMatchesFor(List<MatchDto> baseMatches) {
		
		Set<Entry<MatchDto, Double>> res = filterMatrixRows(baseMatches);
		
		List<Entry<MatchDto, Double>>  entryes = sortEntriesByValue(res);
				
		List<MatchDto>  idemo = transformToMatchIddsListAndFilterDistinct(entryes);
			
		logger.info("Sortirana lista : {}" , Arrays.toString(idemo.toArray()));
				
		return idemo.stream().map(match -> match.getId()).collect(Collectors.toList());
	}

	private List<MatchDto> transformToMatchIddsListAndFilterDistinct(List<Entry<MatchDto, Double>> entryes) {
		return entryes.stream().map( en -> en.getKey()).distinct().collect(toList());
	}

	private List<Entry<MatchDto, Double>> sortEntriesByValue(Set<Entry<MatchDto, Double>> res) {
		return res.stream().filter(matchEntry -> matchEntry.getValue() != null).sorted(comparingDouble(comp()).reversed()).collect(Collectors.toList());
	}

	public Set<Entry<MatchDto, Double>> filterMatrixRows(List<MatchDto> baseMatches) {
		Set<Entry<MatchDto, Double>> res = new HashSet<Entry<MatchDto, Double>>();
		
		for(MatchDto match : baseMatches) {
			Set<Entry<MatchDto, Double>> matchRels = relations.row(match).entrySet();		
			res.addAll(matchRels);
		}
		return res;
	}

	private ToDoubleFunction<? super Object> comp() {
		return e -> ((Entry<MatchDto, Double>) e).getValue();
	}
	
	public Table<MatchDto, MatchDto, Double> getRelations() {
		return relations;
	}


}
