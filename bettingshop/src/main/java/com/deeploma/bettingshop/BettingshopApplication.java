package com.deeploma.bettingshop;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.deeploma.bettingshop.messaging.MessagingChannels;

@SpringBootApplication
@MapperScan("com.deeploma.bettingshop.mapper")
@EnableScheduling
@Configuration
@EnableWebMvc
@ComponentScan
@EnableAutoConfiguration
@EnableConfigurationProperties
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
