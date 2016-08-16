package com.deeploma.bettingshop.messaging;

import javax.jms.Queue;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessagingChannels {
    public static final String TICKETS_QUEUE = "tickets.queue";
    @Bean
    public Queue helloJMSQueue() {
        return new ActiveMQQueue(TICKETS_QUEUE);
    }
}