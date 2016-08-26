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
import com.deeploma.bettingshop.domain.betting.dto.TeamDto;
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
   
    @Autowired
    ObjectMapper objectMapper ;
    
    @Autowired
    MatchTeamsMapper matchMapper;
    
    public SendingBean() {
    	
    }

    @Autowired
    public SendingBean(JmsMessagingTemplate template) {
    	 this.template = template;
    }

    private ExecutorService exService = Executors.newFixedThreadPool(10);

    public void sendTicket(Ticket ticket) {
        
    	String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	logger.info("Idemo u slanje tiketa, {}", (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
 	    Runnable send = new Runnable() {
			
			@Override
			public void run() {
				try {
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
		
		tick.setTime(ticket.getTime());
		
		List<TicketRowDto> rows = ticket.getTicketRows().stream().map(  trow -> convertToDto(trow)).collect(Collectors.toList());		
	    tick.setRows(rows);			
	
	    ticketDto.setTicketDto(tick);
	    
		return ticketDto;
	}

	private TicketRowDto convertToDto(TicketRow trow) {
		TicketRowDto rowDto = new TicketRowDto();
		rowDto.setId(trow.getId());		
		rowDto.setBetOddId(trow.getBetOddId());
				
		Match match = matchMapper.findMatchById(trow.getMatchId());
		
		MatchDto mDto = new MatchDto();
		mDto.setId(match.getId());
		mDto.setStartTime(match.getStartTime());
		mDto.setTeamHome(new TeamDto(match.getTeamHome()));
		mDto.setTeamVisitor(new TeamDto(match.getTeamVisitor()));
		mDto.setCompetition(match.getCompetition());
		rowDto.setMatch(mDto);
				
 		return rowDto;
	}
}