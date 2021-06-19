package com.spring.dto.model.security;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtUserDTO {

	@NotNull(message = "Enter an email")
	@NotEmpty(message = "Enter an email")
	private String email;
	
	@NotNull(message = "Enter a password")
	@NotEmpty(message = "Enter a password")
	private String password;
}
