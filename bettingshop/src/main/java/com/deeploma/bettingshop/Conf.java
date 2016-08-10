package com.deeploma.bettingshop;

import java.util.List;

import org.springframework.boot.CommandLineRunner;

import com.deeploma.bettingshop.domain.basic.Competition;
import com.deeploma.bettingshop.mapper.CompetitionMapper;

//@Configuration

public class Conf {
	
	
	//@Bean 
	public CommandLineRunner run(CompetitionMapper mapper) {
		
		List<Competition> list = mapper.findAll();
		return null;
		
	}

}
