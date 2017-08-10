package com.deeploma.bettingshop;

import static reactor.bus.selector.Selectors.$;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

import com.deeploma.bettingshop.dto.ResultsVerified;
import com.deeploma.bettingshop.services.TicketCalculator;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.joda.JodaModule;

import reactor.Environment;
import reactor.bus.Event;
import reactor.bus.EventBus;
import reactor.fn.Consumer;

@Configuration
public class Conf {
	
	@Autowired
	private Consumer<Event<ResultsVerified>> tc;

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
    
    @Bean
    public Filter logFilter() {
        CommonsRequestLoggingFilter filter = new CommonsRequestLoggingFilter();
        filter.setIncludeQueryString(true);
        filter.setIncludePayload(true);
        filter.setMaxPayloadLength(5120);
        return filter;
    }
    
 


}
