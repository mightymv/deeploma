package com.deeploma.bettingshop.messaging;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.deeploma.bettingshop.domain.basic.Match;
import com.deeploma.bettingshop.domain.betting.Ticket;
import com.deeploma.bettingshop.domain.betting.TicketRow;
import com.deeploma.bettingshop.domain.betting.dto.MatchDto;
import com.deeploma.bettingshop.domain.betting.dto.TicketDto;
import com.deeploma.bettingshop.domain.betting.dto.TicketRowDto;
import com.deeploma.bettingshop.domain.betting.dto.UserTicket;
import com.deeploma.bettingshop.mapper.MatchTeamsMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;



@Component
public class SendingBean {
	
	private final static Logger logger = LoggerFactory.getLogger(SendingBean.class);


    private JmsMessagingTemplate template;
    
    ObjectMapper objectMapper ;
    
    @Autowired
    MatchTeamsMapper matchMapper;

    @Autowired
    public SendingBean(JmsMessagingTemplate template) {
    	 this.template = template;
    	 objectMapper = new ObjectMapper();
    	 //objectMapper.registerModule(new JodaModule());
    }

    private ExecutorService exService = Executors.newFixedThreadPool(10);

    public void sendTicket(Ticket ticket) {
        
    	String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	logger.info("Idemo u slanje tiketa, {}", (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
 	    Runnable send = new Runnable() {
			
			@Override
			public void run() {
				try {
					logger.info("Idemo u slanje tiketa 2");
					UserTicket userTicket = prepareForSend(ticket, username);
					String mess = objectMapper.writeValueAsString(userTicket);
					logger.info("Salje se event sledece sadrzine : {}", mess);
					template.send(MessagingChannels.TICKETS_QUEUE, MessageBuilder.withPayload(mess).build());
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
		
		List<TicketRowDto> rows = ticket.getTicketRows().stream().map(  trow-> convertToDto(trow)).collect(Collectors.toList());		
	    tick.setRows(rows);			
	
	    ticketDto.setTicketDto(tick);
	    
		return ticketDto;
	}

	private TicketRowDto convertToDto(TicketRow trow) {
		TicketRowDto rowDto = new TicketRowDto();
		rowDto.setId(trow.getId());		
		rowDto.setBetOddId(trow.getBetOddId());
				
		Match match = matchMapper.findMatchByBetOddId(trow.getBetOddId());
		
		MatchDto mDto = new MatchDto();
		mDto.setId(match.getId());
		mDto.setStartTime(match.getStartTime());
		mDto.setTeamHome(match.getTeamHome());
		mDto.setTeamVisitor(match.getTeamVisitor());
		rowDto.setMatch(match);
				
 		return rowDto;
	}
}