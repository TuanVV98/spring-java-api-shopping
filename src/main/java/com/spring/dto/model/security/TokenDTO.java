package com.spring.dto.model.security;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TokenDTO {

	private Long id;
	
	private String username;
	
	private String token;
	
	private String email;
	
	private List<String> roles;
	
	public TokenDTO(String token, Long id, String username,String email,List<String> roles) {
		this.token = token;
		this.id = id;
		this.username = username;
		this.email = email;
		this.roles = roles;
	}

}
