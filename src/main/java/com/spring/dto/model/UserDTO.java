package com.spring.dto.model;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;

import org.springframework.beans.factory.annotation.Autowired;

import com.spring.util.security.BcryptUtil;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

	@Autowired
	private BcryptUtil bcryptUtil;
	
	private Long id;
	
	@NotNull
	private String username;
	
	@NotNull
	private String email;
	
	@NotNull
	private String password;	
	
	@NotNull
	private String address;
	
	@NotNull
	@Pattern(regexp="^(ADMIN|USER)$", 
	message="ADMIN or USER .")
	private String role;
	
	@Null
	private Date createdAt;
	
	@Null
	private Date deletedAt;
	
	@Null
	private Integer deletedUser;
	
	public String getPassword() {
		return BcryptUtil.getHash(this.password);
	}
}
