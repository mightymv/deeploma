package com.deeploma.bettingshop.services.impls;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.deeploma.bettingshop.domain.users.User;
import com.deeploma.bettingshop.domain.users.UserStatus;
import com.deeploma.bettingshop.mapper.UserMapper;
import com.deeploma.bettingshop.services.UserService;


@Component
public class UserServiceImp implements UserService {
	
	@Autowired
	UserMapper userMapper;

	@Override
	public User find(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User addUser(User user) {
		userMapper.insertUser(user);
		return user;
	}

	@Override
	public User validateUser(String username, String password) {
		User user = userMapper.findByEmailAndPassword(username, password);
		
		if (user == null || !user.getStatus().getId().equals(UserStatus.ACTIVE.getId())) 
			return null;
		
		return user;
	}

}
