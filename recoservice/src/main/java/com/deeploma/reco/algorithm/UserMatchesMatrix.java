package com.deeploma.reco.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.deeploma.reco.dto.MatchDto;
import com.deeploma.reco.dto.TicketDto;
import com.deeploma.reco.dto.TicketRowDto;
import com.deeploma.reco.dto.UserTicket;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;


@Component
public class UserMatchesMatrix {
	
	private final static Logger logger = LoggerFactory.getLogger(UserMatchesMatrix.class);
	
	
	Table<Long, MatchDto, Integer> userTickets = HashBasedTable.create();
	
	Table<MatchDto, MatchDto, Double> relations = HashBasedTable.create();
	
	HashMap<MatchDto, Double>  vectors = new HashMap<MatchDto, Double>();
	
	
	//Table<String, MatchDto, Double> gTable = HashBasedTable.create();
	
	public static void main (String [] arg) {
		
		UserMatchesMatrix matrix = new UserMatchesMatrix();
		
		UserMatches user1 = new UserMatches();
		MatchCount m1 = new MatchCount(new MatchDto(101l), 3);
		MatchCount m2 = new MatchCount(new MatchDto(102l), 1);
		MatchCount m3 = new MatchCount(new MatchDto(104l), 5);
		
		ArrayList<MatchCount> matches = new ArrayList<MatchCount>();
		matches.add(m1);
		matches.add(m2);
		matches.add(m3);
		
		user1.setId(1l);
		user1.setMatches(matches);
		
		UserMatches user2 = new UserMatches();
		MatchCount m11 = new MatchCount(new MatchDto(101l), 3);
		MatchCount m12 = new MatchCount(new MatchDto(103l), 2);
		MatchCount m13 = new MatchCount(new MatchDto(104l), 4);
		
		ArrayList<MatchCount> matches1 = new ArrayList<MatchCount>();
		matches1.add(m11);
		matches1.add(m12);
		matches1.add(m13);
		
		user2.setId(2l);
		user2.setMatches(matches1);
		
		UserMatches user3 = new UserMatches();
		MatchCount m21 = new MatchCount(new MatchDto(101l), 5);
		MatchCount m22 = new MatchCount(new MatchDto(103l), 3);

		
		ArrayList<MatchCount> matches2 = new ArrayList<MatchCount>();
		matches2.add(m21);
		matches2.add(m22);

		
		user3.setId(3l);
		user3.setMatches(matches2);
	
		ArrayList<UserMatches>  ums = new ArrayList<UserMatches>();
 		ums.add(user1);
 		ums.add(user2);
 		ums.add(user3);
 		
 		matrix.setUsers(ums);
 		
 		UserTicket ut = new UserTicket();
 		ut.setUserId(1l);
 		
 		TicketDto ticket = new TicketDto();
 		TicketRowDto row = new TicketRowDto();
 		row.setMatch(new MatchDto(101l));
 		ticket.setRows(Arrays.asList(row));
 		ut.setTicketDto(ticket);
 		
 		
 		matrix.addUserTicket(ut);
 		
 		
 		List<Entry<MatchDto, Double>> res =   matrix.findBestMatchesFor(101l, Arrays.asList(new MatchDto(102l), new MatchDto(104l)), 4);
 	    
 		res.forEach(entry -> logger.info( "REZZZZ- Match : {} , value {} "  +  entry.getKey().getId() + " val : " + entry.getValue()));
 		
	}
	
	public void setUsers(List<UserMatches> list) {
		Table<Long, MatchDto, Integer> newTable = HashBasedTable.create();
		
		Table<MatchDto, MatchDto, Double> newRelations = HashBasedTable.create();
		
		list.forEach( uMatches -> {
			uMatches.getMatches().forEach(matchData -> {
				newTable.put(uMatches.getId(), matchData.getMatch(), matchData.getCount());
			});
		});
		
		HashMap<MatchDto, Double>  vectors = new HashMap<MatchDto, Double>();
		newTable.columnKeySet().stream().forEach(match -> {
			
			recalculateVector(newTable, vectors, match);
			
		});
		
		vectors.entrySet().forEach(entry -> logger.info( "Vector- Match : {} , value {} "  +  entry.getKey().getId() + " vec : " + entry.getValue()));
		
		
		newTable.columnKeySet().stream().forEach(match -> recalculateForMatch(newTable, newRelations, vectors, match, false));
		
		newRelations.rowKeySet().forEach(match1 -> {
			newRelations.columnKeySet().forEach(match2 -> {
				logger.info("Odnos : " + match1.getId() + " -> " + match2.getId() + " : " + newRelations.get(match1, match2));
			});
		});
		this.userTickets = newTable;
		this.relations = newRelations;
		this.vectors = vectors;
	}

	private void recalculateVector(Table<Long, MatchDto, Integer> newTable, HashMap<MatchDto, Double> vectors, MatchDto match) {
		Collection<Integer> vals = newTable.column(match).values();
		Double sum = vals.stream().mapToDouble( val -> val * val).sum();
		vectors.put(match, Math.sqrt(sum));
		
	}

	
	private void recalculateForMatch(Table<Long, MatchDto, Integer> newTable,
			Table<MatchDto, MatchDto, Double> relations, HashMap<MatchDto, Double> vectors, MatchDto match, boolean force) {
		newTable.columnKeySet().stream().forEach(match2 -> {
			if (!match2.equals(match) && (relations.get(match2, match) == null || force)) { 
				int res = 0;
				for (Long userId: newTable.column(match).keySet()) {
					Integer num1 = newTable.column(match).get(userId);
					Integer num2 = newTable.column(match2).get(userId);
					num1 = num1 != null ? num1 : 0;
					num2 = num2 != null ? num2 : 0;
				    res = res + num1 * num2;						
				}
			    relations.put(match, match2, res / (vectors.get(match) * vectors.get(match2)));
			    relations.put(match2, match, res / (vectors.get(match) * vectors.get(match2)));
			}
				
		});
		
	}
	
	public void addUserTicket(UserTicket userTicket) {
		
		Long userId = userTicket.getUserId();
		
		List<MatchDto> matches = userTicket.getTicketDto().getRows().stream().map(row -> row.getMatch()).collect(Collectors.toList());
		matches.forEach(match -> {
			Integer val = userTickets.get(userId, match);
			System.out.println(val);
			userTickets.put(userId, match, val + 1);
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
	
	public List<Entry<MatchDto, Double>> findBestMatchesFor(Long userId, List<MatchDto> baseMatches, int numberOfMatches) {
		
		Set<Entry<MatchDto, Double>> res = new HashSet<Entry<MatchDto, Double>>();
		
		for(MatchDto match : baseMatches) {
			Set<Entry<MatchDto, Double>> matchRels = relations.row(match).entrySet();		
			res.addAll(matchRels);
		}
		
		Set<MatchDto> foo = new HashSet<MatchDto>(baseMatches);
		Set<Long> matches = foo.stream().map(dto -> dto.getId()).collect(Collectors.toSet());
		
		Comparator<Entry<MatchDto, Double>> byEmployeeNumber = (e1, e2) -> Double.compare(  e1.getValue(), e2.getValue());
		List<Entry<MatchDto, Double>>  idemo = res.stream().filter(matchEntry -> matchEntry.getValue() != null && !matches.contains(matchEntry.getKey().getId())).sorted(byEmployeeNumber).collect(Collectors.toList());
				      
		idemo = idemo.subList(idemo.size() - numberOfMatches, idemo.size());

		return idemo;
	}

}
