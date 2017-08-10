package com.deeploma.reco.mongodao;

import static java.util.Spliterators.spliteratorUnknownSize;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;
import static java.util.stream.StreamSupport.stream;

import java.util.List;
import java.util.Set;
import java.util.Spliterator;

import org.jongo.Aggregate.ResultsIterator;
import org.jongo.Jongo;
import org.jongo.MongoCursor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.deeploma.reco.algorithm.UserMatches;
import com.deeploma.reco.domain.UserBehaviour;
import com.deeploma.reco.dto.TicketDto;

@Component
public class MongoRepository {
	
	//private static final String USER_TICKETS = "userTickets";
	
	private static final String TICKETS = "tickets";
	
	private static final String USER_BEHAVIOURS = "userBehavs";
	
	@Autowired 
	private Jongo jongo;
	
	
	/*public UserTickets findTicketsForUser(Long id) {	
		return jongo.getCollection(USER_TICKETS).findOne("{_id: #}", id).as(UserTickets.class);
	}*/

	public Set<TicketDto> findTicketsForUser(Long id) {	
		MongoCursor<TicketDto> coll =  jongo.getCollection(TICKETS).find("{userId: #}", id).as(TicketDto.class);
		
		return stream(spliteratorUnknownSize(coll, Spliterator.ORDERED), false)
		.collect(toSet());
	}
	
	public void saveTicket(TicketDto ticket) {
		jongo.getCollection(TICKETS).save(ticket);
		
	}
	
	public Set<TicketDto> findTicketsInLast(Long hours) {
		
		
		return null;
	}
	
	public void saveUserBehaviours(UserBehaviour beh) {
		jongo.getCollection(USER_BEHAVIOURS).insert(beh);
	}
	
	/*public List<UserMatches> findUserMatches() {
		ResultsIterator<UserMatches>  coll = jongo.getCollection(USER_TICKETS).aggregate("{$unwind: '$tickets'}")
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
				
				
				return stream(spliteratorUnknownSize(coll, Spliterator.ORDERED), false)
						.collect(toList());
	}*/
	
	public List<UserMatches> findUsersMatches() {
		ResultsIterator<UserMatches>  coll = jongo.getCollection(TICKETS)
				.aggregate("{ $unwind : '$rows' }")
				.and("{ $project :  {'_id' : '$userId', 'match' : '$rows.match'}}" )
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
				
				
				return stream(spliteratorUnknownSize(coll, Spliterator.ORDERED), false)
						.collect(toList());
	}
	
	
    
	
	

	public List<UserBehaviour> findUserBehaviors(Long userId) {
		
		MongoCursor<UserBehaviour> iter = jongo.getCollection(USER_BEHAVIOURS).find("{userId: #}", userId).as(UserBehaviour.class);
		
		return stream(spliteratorUnknownSize(iter, Spliterator.ORDERED), false)
				.collect(toList());
		
	}
	
    public List<UserBehaviour> findUserBehaviors() {
		
		MongoCursor<UserBehaviour> iter = jongo.getCollection(USER_BEHAVIOURS).find().as(UserBehaviour.class);
		
		return stream(spliteratorUnknownSize(iter, Spliterator.ORDERED), false)
				.collect(toList());
		
	}
	

}
