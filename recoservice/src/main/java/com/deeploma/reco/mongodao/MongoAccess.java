package com.deeploma.reco.mongodao;

import org.jongo.Jongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.deeploma.reco.domen.UserTickets;

@Component
public class MongoAccess {
	
	@Autowired 
	private Jongo jongo;
	
	
	public UserTickets findTicketsForUser(Long id) {	
		return jongo.getCollection("userTickets").findOne("{_id: #}", id).as(UserTickets.class);
	}
	
	public void saveTicket(UserTickets ut) {
		jongo.getCollection("userTickets").save(ut);
		
	}

}
