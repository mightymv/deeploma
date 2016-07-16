package com.deeploma.bettingshop.validator;

import java.util.List;

import com.deeploma.bettingshop.domain.basic.Result;
import com.deeploma.bettingshop.domain.betting.TicketRow;

public interface BetValidator {
	
	public Boolean validate(TicketRow row, List<Result> results);

}
