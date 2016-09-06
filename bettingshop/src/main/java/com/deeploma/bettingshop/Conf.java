package com.deeploma.bettingshop;

import static reactor.bus.selector.Selectors.$;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.deeploma.bettingshop.services.TicketCalculator;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.joda.JodaModule;

import reactor.Environment;
import reactor.bus.EventBus;

@Configuration
public class Conf {
	
	@Autowired
	private TicketCalculator tc;

	@Bean
	@Primary
	public ObjectMapper customObjectMapper() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JodaModule());
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		mapper.enable(DeserializationFeature.READ_ENUMS_USING_TO_STRING);
		mapper.setSerializationInclusion(Include.NON_NULL);
		return mapper;
	}
	
	@Bean
    public Environment env() {
        return Environment.initializeIfEmpty()
                          .assignErrorJournal();
    }

    @Bean
    public EventBus createEventBus(Environment env) {
    	EventBus bus = EventBus.create(env, Environment.THREAD_POOL);
    	bus.on($("results"), tc);
	    return bus;
    }

}
