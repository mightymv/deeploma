package com.deeploma.bettingshop.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.mybatis.spring.annotation.MapperScan;

import com.deeploma.bettingshop.domain.basic.Sport;

@MapperScan
public interface SportMapper {
	
	@Select("SELECT * FROM SPORTS")
	public List<Sport> findAll();
	
	@Select("SELECT * FROM SPORTS WHERE id = ${id}")
	public Sport findById(@Param("id") Integer id);

}
