package com.deeploma.bettingshop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;

@Configuration
public class Conf {
	
	
	@Bean
	@Primary
	public ObjectMapper customObjectMapper() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JodaModule());
		mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		mapper.enable(DeserializationFeature.READ_ENUMS_USING_TO_STRING);
		return mapper;
	}

}
