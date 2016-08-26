package com.deeploma.reco.domen;

import java.util.Set;

import org.jongo.marshall.jackson.oid.MongoId;
import org.springframework.data.annotation.Id;

import com.deeploma.reco.dto.TicketDto;

public class UserTickets {
	
	private String username;

	@Id
	@MongoId
	private Long userId;
	
	private Set<TicketDto>  tickets;
	
    public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Set<TicketDto> getTickets() {
		return tickets;
	}

	public void setTickets(Set<TicketDto> ticketDto) {
		this.tickets = ticketDto;
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
		UserTickets other = (UserTickets) obj;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}


}
