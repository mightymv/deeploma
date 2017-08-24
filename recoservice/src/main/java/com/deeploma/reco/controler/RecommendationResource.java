package com.deeploma.reco.controler;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.deeploma.reco.algorithm.RecommendationStrategy;
import com.deeploma.reco.events.EventsListeners;

@RestController
@CrossOrigin
public class RecommendationResource {

	
	@Autowired
    private RecommendationStrategy strategy;

	private final static Logger logger = LoggerFactory.getLogger(EventsListeners.class);	
	
	@RequestMapping(path= "/{id}/recommendation", method = RequestMethod.GET)
	public List<Long>  recommendForUser(@PathVariable ("id") Long id) {	
		logger.info("Trazi se recommendation za igraca : {}", id);
		List<Long> result = strategy.recommendForUser(id);
 		return result.subList(0, result.size() >=3 ? 2 : result.size());
	}

}
