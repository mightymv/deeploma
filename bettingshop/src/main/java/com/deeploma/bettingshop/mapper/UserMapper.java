package com.deeploma.bettingshop.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.deeploma.bettingshop.domain.users.User;

@Mapper
public interface UserMapper {
	
	public User findByUsernameAndPassword(@Param("username")String username,@Param("password") String password);
	
	public void insertUser(@Param("user")User user);
	
	public User findById(@Param("id") Long id);

}
