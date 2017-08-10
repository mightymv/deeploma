package com.deeploma.bettingshop.controler;

import java.util.Comparator;
import java.util.List;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.deeploma.bettingshop.domain.betting.MatchOffer;
import com.deeploma.bettingshop.services.OfferService;

@RestController
@RequestMapping(path= "/offer")
@CrossOrigin
public class OfferControler {
	
	 private final static Logger logger = LoggerFactory.getLogger(OfferControler.class);
		
	@Autowired
	private OfferService offerService;
	
	
	@RequestMapping(path= {"/{date}", "/v2/{date}"}, method = RequestMethod.GET)
	public List<MatchOffer>  getOfferForDate(@PathVariable("date") String date) {
		
		logger.info("Trazi se ponuda za : {} " , date);
		List<MatchOffer> result = offerService.getOffer(new DateTime(date));
		result.sort(new Comparator<MatchOffer>() {

			@Override
			public int compare(MatchOffer o1, MatchOffer o2) {
				return o1.getStartTime().compareTo(o2.getStartTime());
			}
		});
		return result;
	}


}
