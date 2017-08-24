package com.deeploma.reco.controler;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.jongo.Jongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deeploma.reco.algorithm.RecommendationStorage;
import com.deeploma.reco.algorithm.RecommendationStrategy;
import com.deeploma.reco.algorithm.util.HTMLTableBuilder;
import com.deeploma.reco.algorithm.util.MathUtils;
import com.deeploma.reco.dto.MatchDto;
import com.google.common.collect.Table;

/**
 * Resurs za potrebe testa
 * @author veljkom
 *
 */
@RestController
public class UtilResource {
	
	@Autowired
    private RecommendationStrategy strategy;
	
	@Autowired
	private RecommendationStorage matrix;
	
	@Autowired 
	private Jongo jongo;
	
	@RequestMapping(path="/mongoreset")
	public String reset() {
		
		jongo.getCollection("tickets").remove();
		jongo.getCollection("userBehavs").remove();
		
		return "MONGO RESETOVAN";
	}
	
	@RequestMapping(path="/tabela/{id}")
	public String tabela(@PathVariable("id") Long id) {
		Table<MatchDto, MatchDto, Double> dataTable = matrix.getRelations();
		String text = buildHtmlMapForRElations(dataTable);
		 
		List<String> matches = strategy.lastNMatches(id).stream().map(match -> match.getId().toString()).collect(Collectors.toList());
		 
		text = "Poslednja tri meca na tiketima igraca: " +  matches.toString() + "<br /> <br> Preporuke za igraca " + strategy.recommendForUser(id).toString() + "<br /> <br> Tabela odnosa : <br />  " + text;
		 text = text  + "<br> <br> Tiketi korisnika :<br>" + buildHtmlMapForUserMatches(matrix.getUserTickets());
		 return text;
	}

	private String buildHtmlMapForRElations(Table<MatchDto, MatchDto, Double> dataTable) {
		//matrix.getRelations().get
		ArrayList<ArrayList<Object>> rows = new ArrayList<>();
		List<MatchDto>  listHeader = dataTable.columnKeySet().stream().sorted((m1, m2) -> m1.getId().compareTo(m2.getId())).collect(Collectors.toList());
			
		for (int i = 0 ; i < listHeader.size(); i++) {
			ArrayList<Object> row = new ArrayList<>();
			row.add(listHeader.get(i).getId().toString());
			for (int j = 0 ; j < listHeader.size(); j ++) {
				Double d = dataTable.get(listHeader.get(i), listHeader.get(j));
				row.add(d == null ? "/" : MathUtils.round(d, 2).toString());
			}
			rows.add(row);
		}
		List<String> header = listHeader.stream().map( match -> match.getId().toString()).collect(Collectors.toList());
		header.add(0, "X");
		HTMLTableBuilder htmlBuilder = new HTMLTableBuilder(null, true, rows.size(), listHeader.size() + 1);
		  htmlBuilder.addTableHeader( header.toArray(new String[header.size()]));
		  rows.forEach(row -> htmlBuilder.addRowValues( row.toArray(new String[row.size()])));
		 
		String table = htmlBuilder.build();
		return table;
	}
	
	private String buildHtmlMapForUserMatches(Table<Long, MatchDto, Double> dataTable) {
		//matrix.getRelations().get
		ArrayList<ArrayList<Object>> rows = new ArrayList<>();
		List<MatchDto>  listHeader = dataTable.columnKeySet().stream().sorted((m1, m2) -> m1.getId().compareTo(m2.getId())).collect(Collectors.toList());
		
		List<Long> rowsIds = dataTable.rowKeySet().stream().sorted().collect(Collectors.toList());
		
		for (Long user : rowsIds) {
			ArrayList<Object> row = new ArrayList<>();
			row.add(user.toString());
			for (MatchDto match : listHeader) {
				Double d = dataTable.get(user, match);
				row.add(d == null ? "/" : MathUtils.round(d, 2).toString());
			}
			rows.add(row);
		}
		List<String> header = listHeader.stream().map( match -> match.getId().toString()).collect(Collectors.toList());
		header.add(0, "X");
		HTMLTableBuilder htmlBuilder = new HTMLTableBuilder(null, true, rows.size(), listHeader.size() + 1);
		htmlBuilder.addTableHeader( header.toArray(new String[header.size()]));
		rows.forEach(row -> htmlBuilder.addRowValues( row.toArray(new String[row.size()])));
		 
		String table = htmlBuilder.build();
		return table;
	}


}
