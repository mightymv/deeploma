package com.deeploma.bettingshop.services.impls;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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
		return userMapper.findById(id);
	}

	@Override
	@Transactional
	public User addUser(User user) {
		user.setUsername(user.getUsername().toLowerCase());
		userMapper.insertUser(user);
		return user;
	}

	@Override
	public User validateUser(String username, String password) {
		username = username.toLowerCase();
		User user = userMapper.findByUsernameAndPassword(username, password);
		
		if (user == null || !user.getStatus().getId().equals(UserStatus.ACTIVE.getId())) 
			return null;
		
		return user;
	}

}
