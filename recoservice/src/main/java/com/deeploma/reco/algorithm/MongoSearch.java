package com.deeploma.reco.algorithm;



import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

import java.util.List;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.jongo.Aggregate.ResultsIterator;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.jongo.Jongo;
import org.jongo.ResultHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.Field;
import org.springframework.data.mongodb.core.aggregation.Fields;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.deeploma.reco.dto.UserTicket;
import com.google.common.collect.Lists;
import com.mongodb.DBObject;

@Component
public class MongoSearch {
	
    private final static Logger logger = LoggerFactory.getLogger(MongoSearch.class);
	
	@Autowired
	MongoTemplate mongoTemplate;
	
	@Autowired
	Jongo jongo;
	
	@Autowired 
	UserMatchesMatrix matrix;
	
	
	@Scheduled(initialDelay = 3000, fixedRate = 20000)
	public void find() {
		mongoTemplate.getCollection("userTickets");

		
		ResultsIterator<UserMatches>  coll = jongo.getCollection("userTickets").aggregate("{$unwind: '$tickets'}")
		.and("{ $unwind : '$tickets.rows' }")
		.and("{ $project :  {'_id' : '$_id', 'match' : '$tickets.rows.match'}}" )
		.and("{ $group : { "
				+ 	" '_id' : { '_id' : '$_id', 'matchId' : '$match._id' },"
				+ 	"'match' : { $max : '$match' },"
				+ 	"'count' : { $sum : 1 }}"
				+ "} ")
		.and("{ $group : { "
				+ " '_id' : '$_id._id',"
				+ " 'matches' : { $push : { 'match' : '$match', 'count' : '$count' } }}"
				+ "}")
		
		.as(UserMatches.class);
		
		
		List<UserMatches> userMatches = StreamSupport.stream(
	            Spliterators.spliteratorUnknownSize(coll,
	                    Spliterator.ORDERED), false).collect(
	            Collectors.<UserMatches> toList());
		
		matrix.setUsers(userMatches);
		//addGroupByField(Fields.field("_id", "_id")).addGroupByField(Fields.field("_id", "_id"));
		
		
		
		//mongoTemplate.aggregate(agg, "userTickets", String.class)
				
		//		.forEach(str -> logger.info("Mongo : "  + str));;
		
		//;
	}

}
