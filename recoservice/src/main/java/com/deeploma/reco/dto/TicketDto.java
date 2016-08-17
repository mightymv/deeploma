package com.deeploma.reco.dto;

import java.util.List;

import org.joda.time.DateTime;
import org.springframework.data.annotation.Id;

public class TicketDto {
	
	

	@Id
	private Long id;
	
	private List<TicketRowDto> rows;
	
	private DateTime startTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<TicketRowDto> getRows() {
		return rows;
	}

	public void setRows(List<TicketRowDto> rows) {
		this.rows = rows;
	}

	public DateTime getStartTime() {
		return startTime;
	}

	public void setStartTime(DateTime startTime) {
		this.startTime = startTime;
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		TicketDto other = (TicketDto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
