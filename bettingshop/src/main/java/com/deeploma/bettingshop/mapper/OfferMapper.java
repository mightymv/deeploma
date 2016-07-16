package com.deeploma.bettingshop.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.joda.time.DateTime;

import com.deeploma.bettingshop.domain.betting.BetOdd;

@Mapper
public interface OfferMapper {
	
	
	public List<BetOdd> getOfferForDate(DateTime date);

}
