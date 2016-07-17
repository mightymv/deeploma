package com.deeploma.bettingshop.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.joda.time.DateTime;

import com.deeploma.bettingshop.domain.betting.MatchOffer;

@Mapper
public interface OfferMapper {
		
	public List<MatchOffer> getOfferForMatchId(@Param("mid")Integer id);
	
	public List<MatchOffer> getOffer();
	
	public List<MatchOffer> getOfferForDate(@Param("date")DateTime date);

}
