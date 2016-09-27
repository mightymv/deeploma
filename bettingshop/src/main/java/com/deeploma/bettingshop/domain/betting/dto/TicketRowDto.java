package com.deeploma.bettingshop.domain.betting.dto;

import com.deeploma.bettingshop.domain.basic.Match;
import com.deeploma.bettingshop.domain.betting.TicketRow;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TicketRowDto {
	
	private Long id;
	
	private MatchDto match;
	
	private Long betOddId;
	
	private String subGameShortName;
	
	private String status;
	
	private String odd;
	
	public TicketRowDto(TicketRow trow, Match match) {
		this.setId(trow.getId());		
		this.setBetOddId(trow.getBetOddId());
		MatchDto mDto = new MatchDto(match);		
		this.setMatch(mDto);
		this.setStatus(trow.getTicketRowStatus().name());
		this.setSubGameShortName(trow.getSubGameShortName());
		this.setOdd(trow.getOdd().toString());
	}

	public TicketRowDto() {
		
	}

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
