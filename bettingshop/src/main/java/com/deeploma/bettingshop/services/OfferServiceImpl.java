package com.deeploma.bettingshop.services;

import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.deeploma.bettingshop.domain.basic.Competition;
import com.deeploma.bettingshop.domain.basic.Match;
import com.deeploma.bettingshop.domain.basic.Result;
import com.deeploma.bettingshop.domain.basic.Sport;
import com.deeploma.bettingshop.domain.basic.Team;
import com.deeploma.bettingshop.domain.betting.MatchOffer;
import com.deeploma.bettingshop.mapper.CompetitionMapper;
import com.deeploma.bettingshop.mapper.MatchTeamsMapper;
import com.deeploma.bettingshop.mapper.OfferMapper;
import com.deeploma.bettingshop.mapper.SportMapper;

@Component
public class OfferServiceImpl implements OfferService {
	
	
	@Autowired
	CompetitionMapper  cMapper;
	
	
	@Autowired
	SportMapper sMapper;
	
	
	@Autowired 
	MatchTeamsMapper mtMapper;
	
	@Autowired
	OfferMapper offerMapper;
	
	@Override
	public List<MatchOffer>  getOffer(DateTime date) {
		return offerMapper.getOfferForDate(date);
	}
	
	//@Scheduled(initialDelay = 3000, fixedRate =3000)
	public void method() {
		System.out.println("IDemo");
		//Sport s = sMapper.findById(Integer.valueOf(1));
		List<Competition> comps = cMapper.findAll();
		Sport s = sMapper.findById(1);
		
		List<Result> results = mtMapper.findAllResults();
		List<Team> teams = mtMapper.findAllTeams();
		System.out.println(s.getName());
		
		Match m = mtMapper.findMatchById(1001);
		
		DateTime date = new DateTime("2016-06-20");
		List<MatchOffer>  offer = offerMapper.getOfferForDate(date);
		
	
		date.withTimeAtStartOfDay();
		
		System.out.println(m.getId());
		System.out.println(results.get(0).getResultStatus());
	}

}
