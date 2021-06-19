package com.spring.service.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.spring.entity.User;
import com.spring.repository.UserRepository;
import com.spring.service.user.UserService;
import com.spring.util.security.BcryptUtil;
import com.spring.util.security.JwtUserFactory;

@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	public JwtUserDetailsServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		System.out.println("email : "+email);
		Optional<User> user = this.userRepository.findByEmailAndDeletedAtIsNull(email);
		
		if (user.isPresent()) {
//			boolean checkPwd = BcryptUtil.decode(password, encrypted)
			return JwtUserFactory.create(user.get());
		}
		throw new UsernameNotFoundException("User/Email not found.s");
	}

}
