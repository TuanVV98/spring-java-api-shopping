package com.spring.service.user;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.spring.dto.model.UserDTO;
import com.spring.dto.model.security.JwtUserDTO;
import com.spring.entity.User;


public interface UserService {

	/**
	 * Method save user in database
	 * 
	 * @since 06/06/2021
	 * 
	 * @param user
	 * @return UserDTO Object
	 */
	UserDTO save(UserDTO userDTO);
	
	/**
	 * Method that find all users
	 * 
	 * @since 06/06/2021
	 * 
	 * @return <code>List<UserDTO></code> object
	 */
	List<UserDTO> findAll();
	
	/**
	 * Method that find all users
	 * 
	 * @since 11/06/2021
	 * @param Staring email
	 * @return <code>Optional<UserDTO></code> object
	 */
	Optional<User> findByEmail(String email);
	
	/**
	 * Method check account by email and password
	 * 
	 * @since 15/06/2021
	 * @param JwtUserDTO jwtUserDTO
	 * 
	 * @return true and false
	 */
	boolean checkAccount(JwtUserDTO jwtUserDTO);
	
	/**
	 * Method find all by deleted at is null in users
	 * 
	 * @since 15/06/2021
	 * 
	 * @return <code>List<UserDTO></code> object
	 */
	List<UserDTO> findByDeletedAtIsNull();
	
	/**
	 * Method find all by deleted at is not null in users
	 * 
	 * @since 15/06/2021
	 *  
	 * @return <code>List<UserDTO></code> object
	 */
	List<UserDTO> findByDeletedAtIsNotNull();
}
