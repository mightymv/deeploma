package com.deeploma.bettingshop.mapper;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;

import com.deeploma.bettingshop.domain.basic.Competition;

@MapperScan
public interface CompetitionMapper {
	
	public List<Competition> findAll();

}
