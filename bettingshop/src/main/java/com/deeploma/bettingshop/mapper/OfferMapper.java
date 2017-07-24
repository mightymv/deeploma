package com.deeploma.bettingshop.mapper;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;
import org.joda.time.DateTime;
import org.mybatis.spring.annotation.MapperScan;

import com.deeploma.bettingshop.domain.betting.BetOdd;
import com.deeploma.bettingshop.domain.betting.BetOddStatus;
import com.deeploma.bettingshop.domain.betting.MatchOffer;

@MapperScan
public interface OfferMapper {
		
	public List<MatchOffer> getOfferForMatchId(@Param("mid")Integer id);
	
	public List<MatchOffer> getOffer();
	
	public List<MatchOffer> getOfferForDate(@Param("date")DateTime date);

	public void updateBetOddStatus(@Param("id") Long id, @Param("status") BetOddStatus betOddStatus);

	public List<BetOdd> findAllOddsForMatchIds(@Param("matchIds")Set<Long> matchIds);

}
