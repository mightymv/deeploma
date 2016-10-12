package com.deeploma.bettingshop.messaging;

import org.springframework.context.ApplicationEvent;

import com.deeploma.bettingshop.domain.betting.Ticket;

public class TicketEvent extends ApplicationEvent{

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1858492063417125684L;

	public TicketEvent(Ticket source) {
		super(source);
		// TODO Auto-generated constructor stub
	}

}
