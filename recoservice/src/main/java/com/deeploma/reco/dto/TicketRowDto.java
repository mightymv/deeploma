package com.deeploma.reco.dto;

import org.jongo.marshall.jackson.oid.MongoId;

import com.fasterxml.jackson.annotation.JsonInclude;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class TicketRowDto {
	
	@MongoId
	private Long id;
	
	private MatchDto match;
	
	private Long betOddId;
	
	private String subGameShortName;
	
	private String status;
	
	private String odd;

	public MatchDto getMatch() {
		return match;
	}

	public void setMatch(MatchDto match) {
		this.match = match;
	}

	public Long getBetOddId() {
		return betOddId;
	}

	public void setBetOddId(Long betOddId) {
		this.betOddId = betOddId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSubGameShortName() {
		return subGameShortName;
	}

	public void setSubGameShortName(String subGameShortName) {
		this.subGameShortName = subGameShortName;
	}

	public String getOdd() {
		return odd;
	}

	public void setOdd(String odd) {
		this.odd = odd;
	}

}
