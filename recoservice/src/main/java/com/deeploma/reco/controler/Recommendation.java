package com.deeploma.reco.controler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.deeploma.reco.algorithm.RecommendationStrategy;

@RestController
@CrossOrigin
public class Recommendation {

	
	@Autowired
    private RecommendationStrategy strategy;

	
	@RequestMapping(path= "/{id}/recommendation", method = RequestMethod.GET)
	public List<Long>  recommendForUser(@PathVariable ("id") Long id) {	
		return strategy.recommendForUser(id);
	}
	
	
	
	

}
