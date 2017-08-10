package com.deeploma.bettingshop.services.impls;

import java.util.List;

import javax.sql.DataSource;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.deeploma.bettingshop.domain.betting.MatchOffer;
import com.deeploma.bettingshop.mapper.OfferMapper;
import com.deeploma.bettingshop.services.OfferService;

@Component
public class OfferServiceImpl implements OfferService {
	

	@SuppressWarnings("unused")
	private final static Logger logger = LoggerFactory.getLogger(OfferServiceImpl.class);

	@Autowired
	private OfferMapper offerMapper;
	
	@Override
	public List<MatchOffer>  getOffer(DateTime date) {
		return offerMapper.getOfferForDate(date);
	}
	

}
