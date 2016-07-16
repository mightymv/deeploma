package com.deeploma.bettingshop;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.deeploma.bettingshop.mapper")
@EnableScheduling
public class BettingshopApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(BettingshopApplication.class, args);
	}
	
	/*@Bean 
	public CommandLineRunner run(CompetitionMapper mapper) {
		
		List<Competition> list = mapper.findAll();
		return null;
		
	}*/
}
