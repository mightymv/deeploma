package com.deeploma.reco.algorithm;

import java.util.List;

import org.jongo.marshall.jackson.oid.MongoId;

public class UserMatches {


	@MongoId
	private Long id ;
	
    private List<MatchCount> matches;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<MatchCount> getMatches() {
		return matches;
	}

	public void setMatches(List<MatchCount> matches) {
		this.matches = matches;
	}
	
}
