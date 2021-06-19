package com.spring.util.security;

import com.spring.entity.User;
import com.spring.enumeration.RoleEnum;
import com.spring.model.security.JwtUserDetailsImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;


public class JwtUserFactory {

	
	
	public static JwtUserDetailsImpl create(User user) {
		return new JwtUserDetailsImpl(user.getId(),user.getUsername() ,user.getEmail(), user.getPassword(), createGrantedAuthorities(user.getRole()));
	}
	
	
	
	private static List<GrantedAuthority> createGrantedAuthorities(RoleEnum role) {
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(role.toString()));
		return authorities;
	}
	
}
