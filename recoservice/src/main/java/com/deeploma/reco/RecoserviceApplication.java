package com.deeploma.reco;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.TimeZone;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.jongo.Jongo;
import org.jongo.Mapper;
import org.jongo.marshall.jackson.JacksonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.mongodb.ReadPreference;

@SpringBootApplication
@EnableWebMvc
@EnableScheduling
public class RecoserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecoserviceApplication.class, args);
	}
	
	@Autowired
	MongoTemplate mongo;
	
	@Bean
	@Primary
	public ObjectMapper customObjectMapper() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JodaModule());
		mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		return mapper;
	}
	
	@Bean
	public Jongo configureJongo() {
		JsonDeserializer<DateTime> deserializer = new JsonDeserializer<DateTime>() {

			@Override
			public DateTime deserialize(JsonParser p, DeserializationContext ctxt)
					throws IOException, JsonProcessingException {
				// "Mon May 23 17:00:00 CEST 2016"
				java.time.format.DateTimeFormatter dateTimeWithZone = java.time.format.DateTimeFormatter
						.ofPattern("E MMM d H:m:s z uuuu");
				ZonedDateTime zonedDateTime = ZonedDateTime.parse(p.getText(), dateTimeWithZone);
				DateTime dt = new DateTime(zonedDateTime.toInstant().toEpochMilli(),
						DateTimeZone.forTimeZone(TimeZone.getTimeZone(zonedDateTime.getZone())));
				return dt;
			}
		};
		
		JsonSerializer<DateTime> serializer=new JsonSerializer<DateTime>(){

			@Override
			public void serialize(DateTime value, JsonGenerator gen, SerializerProvider serializers)
					throws IOException, JsonProcessingException {
				Date date = new Date(value.getMillis());
				gen.writeObject(date);
			}
			
		};
		Mapper maper = new JacksonMapper.Builder()
				.addDeserializer(DateTime.class, deserializer)
				.addSerializer(DateTime.class, serializer)
				.build();
		mongo.setReadPreference(ReadPreference.secondaryPreferred());
		Jongo jongo = new Jongo(mongo.getDb(), maper);
		return jongo;
	}
}
