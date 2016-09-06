package com.deeploma.bettingshop.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.deeploma.bettingshop.util.RandomResultGenerator;


@RestController
@RequestMapping(path= "/res")
@CrossOrigin
public class UtilResultControler {
	
	@Autowired
	private RandomResultGenerator generator;
	
	@RequestMapping(path= "/gen/{date}", method = RequestMethod.GET)
	public void  getOfferForDate(@PathVariable("date") String date) {
		
		generator.generateResultsForDate(date);
	}

}
