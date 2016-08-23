package com.deeploma.bettingshop.controler;

import java.util.List;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.deeploma.bettingshop.domain.betting.MatchOffer;
import com.deeploma.bettingshop.services.OfferService;

@RestController
@RequestMapping(path= "/offer")
public class OfferControler {
	
	 private final static Logger logger = LoggerFactory.getLogger(OfferControler.class);
	
	
	@Autowired
	OfferService offerService;
	
	@RequestMapping(path= "/{date}", method = RequestMethod.GET)
	public List<MatchOffer>  getOfferForDate(@PathVariable("date") String date) {
		
		//Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		//String username=   (String) auth.getPrincipal();
		//logger.info("Korisnik {} trazi ponudu", username);
		return offerService.getOffer(new DateTime(date));
	}

}
