package com.deeploma.bettingshop.controler;

import java.util.List;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.deeploma.bettingshop.domain.betting.dto.UserStandingDto;
import com.deeploma.bettingshop.services.StandingsService;

@RestController
@RequestMapping(path= "/standings")
@CrossOrigin
public class StandingsControler {
	
	 private final static Logger logger = LoggerFactory.getLogger(OfferControler.class);
		
		@Autowired
		private StandingsService sSrv;
		
		
		@RequestMapping(path= "/{date}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
		public List<UserStandingDto>  getStandingsForDate(@PathVariable("date") String date) {
		
			logger.info("Trazi se tabela poretka igraca za datum : {} " , date);
			return sSrv.getStandingsForDate(new DateTime(date));
		}

}
