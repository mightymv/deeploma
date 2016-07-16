package com.deeploma.bettingshop.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.deeploma.bettingshop.domain.basic.Competition;

@Mapper
public interface CompetitionMapper {
	
	public List<Competition> findAll();

}
