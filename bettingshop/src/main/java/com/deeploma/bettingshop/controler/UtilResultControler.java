package com.deeploma.bettingshop.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
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
	
	@Autowired
	private  JdbcTemplate jdbcTemplate;

	

	
	@RequestMapping(path= "/gen/{date}", method = RequestMethod.GET)
	public String  getOfferForDate(@PathVariable("date") String date) {
		
		generator.generateResultsForDate(date);
		return "GENERISANI REZULTATI. OBACUNATI TIKETI.";
	}
	@RequestMapping(path= "/resetAll", method = RequestMethod.GET)
	@Transactional
	public String resetAll() {
		
		jdbcTemplate.execute("delete from standings");
		jdbcTemplate.execute("delete from ticketrows");
		jdbcTemplate.execute("delete from tickets");
		jdbcTemplate.execute("delete from results");
		jdbcTemplate.execute("update matches set matchstatus = 'NOT_FINISHED'");
		jdbcTemplate.execute("update betodds set BETODDSTATUS = 'ACTIVE'");
		return "RESETOVANO STANJE";
	}

}
