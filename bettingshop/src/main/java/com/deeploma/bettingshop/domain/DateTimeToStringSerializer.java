package com.deeploma.bettingshop.domain;

import java.io.IOException;


import org.joda.time.DateTime;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class DateTimeToStringSerializer extends JsonSerializer<DateTime> {

    @Override
    public void serialize(DateTime tmpDate, 
                          JsonGenerator jsonGenerator, 
                          SerializerProvider serializerProvider) 
                          throws IOException, JsonProcessingException {
        jsonGenerator.writeObject(tmpDate.toString());
    }
}