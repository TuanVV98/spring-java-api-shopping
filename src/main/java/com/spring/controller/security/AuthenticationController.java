package com.spring.controller.security;

import java.util.List;

import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.dto.model.security.JwtUserDTO;
import com.spring.dto.model.security.TokenDTO;
import com.spring.model.security.JwtUserDetailsImpl;
import com.spring.service.user.UserService;
import com.spring.util.security.JwtTokenUtil;

@RestController
@RequestMapping("/rest-api/auth")
public class AuthenticationController {

	@Autowired
	private UserDetailsService userDetailService;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserService userService;

	@Autowired
	AuthenticationManager authenticationManager;

	public AuthenticationController(UserDetailsService userDetailService, JwtTokenUtil jwtTokenUtil) {
		this.userDetailService = userDetailService;
		this.jwtTokenUtil = jwtTokenUtil;
	}

	@PostMapping
	public ResponseEntity<?> generateTokenJwt(@Valid @RequestBody JwtUserDTO jwtUserDTO) {
		System.out.println("*** Calling generate token ****");

		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(jwtUserDTO.getEmail(), jwtUserDTO.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		JwtUserDetailsImpl userDetailss = (JwtUserDetailsImpl) authentication.getPrincipal();

		String token = jwtTokenUtil.getToken(userDetailss);

		List<String> roles = userDetailss.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

		TokenDTO newToken = new TokenDTO(token, 
				userDetailss.getId(),  
				userDetailss.getUsername(),  
				userDetailss.getEmail(), 
				roles);

		return ResponseEntity.ok(newToken);
	}
}
