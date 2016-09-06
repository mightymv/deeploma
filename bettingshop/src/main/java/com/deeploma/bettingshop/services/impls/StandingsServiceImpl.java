package com.deeploma.bettingshop.services.impls;

import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.deeploma.bettingshop.domain.betting.UserStanding;
import com.deeploma.bettingshop.domain.betting.dto.UserStandingDto;
import com.deeploma.bettingshop.mapper.UserStandingsMapper;
import com.deeploma.bettingshop.services.StandingsService;

@Component
public class StandingsServiceImpl implements StandingsService {
	
	@Autowired
	private UserStandingsMapper  mapper;

	@Override
	public List<UserStandingDto> getStandingsForDate(DateTime date) {
		return mapper.findStandingsForDate(date);
	}
	
	@Override
	public void deleteForDate(DateTime date) {
		mapper.deleteForDate(date);
	}

	@Override
	public void saveStanding(UserStanding standing) {
		mapper.insert(standing);
		
	}

}
