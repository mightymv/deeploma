package com.deeploma.bettingshop.messaging;

import static com.deeploma.bettingshop.messaging.MessagingChannels.TICKETS_QUEUE;
import static java.util.concurrent.Executors.newFixedThreadPool;
import static java.util.stream.Collectors.toList;
import static org.springframework.messaging.support.MessageBuilder.withPayload;

import java.util.List;
import java.util.concurrent.ExecutorService;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.format.DateTimeFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.PayloadApplicationEvent;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import com.deeploma.bettingshop.domain.betting.Ticket;
import com.deeploma.bettingshop.domain.betting.dto.TicketDto;
import com.deeploma.bettingshop.domain.betting.dto.TicketRowDto;
import com.deeploma.bettingshop.mapper.MatchTeamsMapper;
import com.deeploma.bettingshop.services.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class TicketSender {
	
	private final static Logger logger = LoggerFactory.getLogger(TicketSender.class);

    private JmsMessagingTemplate template;
   
    @Autowired
    private ObjectMapper objectMapper ;
    
    @Autowired
    private MatchTeamsMapper matchMapper;
    
    @Autowired
    private UserService uService;
    
    public TicketSender() {
    	
    }
    
    private ExecutorService exService = newFixedThreadPool(10);

    @Autowired
    public TicketSender(JmsMessagingTemplate template) {
    	 this.template = template;
    }
    
    
    @TransactionalEventListener(phase=TransactionPhase.AFTER_COMMIT)
    public void ticketEventTransactionListener(PayloadApplicationEvent<Ticket> tickEvent) {
	   sendTicket(tickEvent.getPayload());
    }
   

    public void sendTicket(Ticket ticket) {
        
    	String username = uService.find(ticket.getUserId()).getUsername();
    	logger.info("Idemo u slanje tiketa : {}, user : {}", ticket.getId(), username);
 	    Runnable send = new Runnable() {
			
			@Override
			public void run() {
				try {
					TicketDto userTicket = prepareForSend(ticket, username);
					String ticketMessage = objectMapper.writeValueAsString(userTicket);
					logger.info("Salje se event sledece sadrzine : {}", ticketMessage);
					template.send(TICKETS_QUEUE, withPayload(ticketMessage).build());
				} catch (JsonProcessingException e) {
					logger.error("Nije uspelo slanje.", e);
				} catch (Exception ex) {
					logger.error("Nije uspelo slanje 2.", ex);
				}
				
			}
		};
    	
    	exService.submit(send);
    	
    }

	private TicketDto prepareForSend(Ticket ticket, String username) {		
		TicketDto tick = new TicketDto();
		tick.setUserId(ticket.getUserId());
		tick.setUsername(username);
		tick.setId(ticket.getId());		
		tick.setTime(ticket.getTime());		
		List<TicketRowDto> rows = ticket.getTicketRows().stream()
				.map(trow -> new TicketRowDto(trow, matchMapper.findMatchById(trow.getMatchId())))   // da, da , znam... 
				.collect(toList());		
	    tick.setRows(rows);
	    tick.setCumulativeOdd(ticket.getCumulativeOdd());
	    tick.setStatus(ticket.getTicketStatus().name());	
	    return tick;
	}

	

public static void main (String [] args) {
	String pattern = "dd-MMM-yy hh.mm.ss aa";
	DateTime joda1 = DateTime.parse("30-OCT-16 03.01.01 AM", DateTimeFormat.forPattern(pattern));
	DateTime joda2 = DateTime.parse("30-OCT-16 03.03.00 AM", DateTimeFormat.forPattern(pattern));
	
	
	
	
	Duration dur = new Duration(joda1, joda2);
	
	System.out.println(dur.getStandardMinutes());
};
	
}