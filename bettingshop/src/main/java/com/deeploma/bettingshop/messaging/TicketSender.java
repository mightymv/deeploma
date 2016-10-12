package com.deeploma.bettingshop.messaging;

import static com.deeploma.bettingshop.messaging.MessagingChannels.TICKETS_QUEUE;
import static java.util.concurrent.Executors.newFixedThreadPool;
import static java.util.stream.Collectors.toList;
import static org.springframework.messaging.support.MessageBuilder.withPayload;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
import com.deeploma.bettingshop.domain.betting.dto.UserTicket;
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
					UserTicket userTicket = prepareForSend(ticket, username);
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

	private UserTicket prepareForSend(Ticket ticket, String username) {
		UserTicket ticketDto = new UserTicket();		
		ticketDto.setUserId(ticket.getUserId());		
		ticketDto.setUsername(username);		
		TicketDto tick = new TicketDto();
		tick.setId(ticket.getId());		
		tick.setTime(ticket.getTime());		
		List<TicketRowDto> rows = ticket.getTicketRows().stream().map(trow -> new TicketRowDto(trow, matchMapper.findMatchById(trow.getMatchId()))).collect(toList());		
	    tick.setRows(rows);	    
	    tick.setStatus(ticket.getTicketStatus().name());	
	    ticketDto.setTicketDto(tick);	    
		return ticketDto;
	}

	
}