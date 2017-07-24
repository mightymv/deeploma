package com.deeploma.reco.dto;

import java.util.List;

import org.joda.time.DateTime;
import org.jongo.marshall.jackson.oid.MongoId;
import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TicketDto {
	
	@Id
	@MongoId
	private Long id;
	
	private String username;
	
	private Long userId;
	
	private List<TicketRowDto> rows;
	
	private Double cumulativeOdd;
	
	private DateTime time;
	
	private String status;

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

	public DateTime getTime() {
		return time;
	}

	public void setStartTime(DateTime time) {
		this.time = time;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public Double getCumulativeOdd() {
		return cumulativeOdd;
	}

	public void setCumulativeOdd(Double cumulativeOdd) {
		this.cumulativeOdd = cumulativeOdd;
	}

}
