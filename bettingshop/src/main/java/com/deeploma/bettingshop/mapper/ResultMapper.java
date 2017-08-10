package com.deeploma.bettingshop.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.deeploma.bettingshop.domain.basic.Result;
import com.deeploma.bettingshop.domain.basic.ResultStatus;

@MapperScan
public interface ResultMapper {
	
	public void insertResult(@Param("result") Result result);
	
	public void updateResultStatus(@Param("id") Long id, @Param("status") ResultStatus status);

	public List<Result> findAllVerifiedResults();

}
