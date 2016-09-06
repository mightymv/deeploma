package com.deeploma.bettingshop.services;

import java.util.List;

import org.joda.time.DateTime;

import com.deeploma.bettingshop.domain.betting.UserStanding;
import com.deeploma.bettingshop.domain.betting.dto.UserStandingDto;

public interface StandingsService {
	
	public List<UserStandingDto>  getStandingsForDate(DateTime date);

	public void deleteForDate(DateTime date);
	
	public void saveStanding(UserStanding standing);

}
