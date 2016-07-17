package com.deeploma.bettingshop.controler;

import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deeploma.bettingshop.domain.betting.MatchOffer;
import com.deeploma.bettingshop.services.OfferService;

@RestController
@RequestMapping(path= "/offer")
public class OfferControler {
	
	
	@Autowired
	OfferService offerService;
	
	@RequestMapping(path= "/{date}")
	public List<MatchOffer>  getOfferForDate(@PathVariable("date") String date) {
		return offerService.getOffer(new DateTime(date));
	}

}
