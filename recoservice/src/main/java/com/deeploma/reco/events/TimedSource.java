package com.deeploma.reco.events;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

@EnableBinding(Sink.class)
public class TimedSource {



  @StreamListener(Sink.INPUT)
  public void processVote(String vote) {
      
  }
}