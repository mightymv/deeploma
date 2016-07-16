package com.deeploma.bettingshop;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
