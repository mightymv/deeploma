package com.deeploma.bettingshop.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.deeploma.bettingshop.domain.basic.Sport;

@Mapper
public interface SportMapper {
	
	@Select("SELECT * FROM SPORTS")
	public List<Sport> findAll();
	
	@Select("SELECT * FROM SPORTS WHERE id = ${id}")
	public Sport findById(@Param("id") Integer id);

}
