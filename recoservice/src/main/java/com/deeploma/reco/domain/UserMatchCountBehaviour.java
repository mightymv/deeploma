package com.deeploma.reco.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class UserMatchCountBehaviour {

	private Long userId;
	
	private Map<Long, AtomicInteger> matchCount = new HashMap<Long, AtomicInteger>();
	
	
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Map<Long, AtomicInteger> getMatchesCount() {
		return matchCount;
	}

	public void setMatchCount(Map<Long, AtomicInteger> matchCount) {
		this.matchCount = matchCount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserMatchCountBehaviour other = (UserMatchCountBehaviour) obj;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

	

}
