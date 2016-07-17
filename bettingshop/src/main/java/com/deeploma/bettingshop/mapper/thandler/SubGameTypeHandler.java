package com.deeploma.bettingshop.mapper.thandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import com.deeploma.bettingshop.domain.betting.SubGame;


@MappedTypes(SubGame.class)
public class SubGameTypeHandler implements  TypeHandler<SubGame> {

	@Override
	public void setParameter(PreparedStatement ps, int i, SubGame parameter, JdbcType jdbcType) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public SubGame getResult(ResultSet rs, String columnName) throws SQLException {
		Integer id = rs.getInt(columnName);				
		return SubGame.getForId(id);		
	}

	@Override
	public SubGame getResult(ResultSet rs, int columnIndex) throws SQLException {
		Integer id = rs.getInt(columnIndex);				
		return SubGame.getForId(id);
	}

	@Override
	public SubGame getResult(CallableStatement cs, int columnIndex) throws SQLException {
		Integer id = cs.getInt(columnIndex);				
		return SubGame.getForId(id);
	}

	

}
