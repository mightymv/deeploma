package com.deeploma.bettingshop.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import com.deeploma.bettingshop.domain.users.User;
import com.deeploma.bettingshop.services.UserService;

public class DemoAuthenticationProvider implements AuthenticationProvider {

    // This would be a JPA repository to snag your user entities
    private final UserService userService;
    
    @Autowired
    public DemoAuthenticationProvider(UserService userRepository) {
        this.userService = userRepository;
    }    
    
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        
        DemoAuthenticationToken demoAuthentication = (DemoAuthenticationToken) authentication;        
        User user = userService.find(demoAuthentication.getUid());
        
        if(user == null){
            throw new UnknownUserException("Could not find user with ID: " + demoAuthentication.getUid());
        }
        
        return demoAuthentication;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return DemoAuthenticationToken.class.isAssignableFrom(authentication);
    }

}
