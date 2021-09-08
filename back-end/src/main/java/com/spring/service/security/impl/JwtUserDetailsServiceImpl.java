package com.spring.service.security.impl;

import com.spring.model.User;
import com.spring.model.security.JwtUserFactory;
import com.spring.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = this.userService.checkIfEmailExistsAndStatus(username);

        if (user.isPresent()) {
            return JwtUserFactory.create(user.get());
        }
        throw new UsernameNotFoundException("User/Email not found.");
    }
}
