package com.deeploma.bettingshop.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.joda.time.DateTime;

import com.deeploma.bettingshop.domain.betting.UserStanding;
import com.deeploma.bettingshop.domain.betting.dto.UserStandingDto;

@Mapper
public interface UserStandingsMapper {

	public void insert (@Param("standing") UserStanding us);
	
	public List<UserStandingDto> findStandingsForDate(@Param("date") DateTime date);
	
	public void deleteForDate(@Param("date") DateTime date);

}
