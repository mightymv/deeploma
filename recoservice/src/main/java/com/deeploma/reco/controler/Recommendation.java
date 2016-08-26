package com.deeploma.reco.controler;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Recommendation {
	
	@RequestMapping(path= "/{id}/recommendation", method = RequestMethod.GET)
	public List<Long>  recommendForUser(@PathVariable ("id") Long id) {
		
		//nadji poslednje meceve za korisnika
		
		return null;
	}

}
