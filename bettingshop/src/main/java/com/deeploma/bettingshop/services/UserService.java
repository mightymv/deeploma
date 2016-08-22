package com.deeploma.bettingshop.services;

import com.deeploma.bettingshop.domain.users.User;

public interface UserService {
	
	public User find(Long id) ;
	
	public User addUser(User user);
	
	public User validateUser(String username, String password) ;

}
