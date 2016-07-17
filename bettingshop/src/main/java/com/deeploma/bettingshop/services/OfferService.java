package com.deeploma.bettingshop.services;

import java.util.List;

import org.joda.time.DateTime;

import com.deeploma.bettingshop.domain.betting.MatchOffer;

public interface OfferService {
	
	public List<MatchOffer>  getOffer(DateTime date);

}
