package com.spring.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.dto.model.UserDTO;
import com.spring.service.user.UserService;

@RestController
@RequestMapping("/rest-api/user")
public class UserController {

	@Autowired
	private UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	/**
	 * Method that creates an user in the API.
	 * 
	 * @param dto
	 * @param result
	 * @return ResponseEntity with a Response<UserDTO> object and the HTTP status
	 * 
	 *         HTTP Status:
	 * 
	 *         201 - Created: Everything worked as expected. 
	 *         400 - Bad Request: The request was unacceptable, often due to missing a required parameter.
	 *         401 - Unauthorized: No valid API key provided. 
	 *         403 - Forbidden: The API key doesn't have permissions to perform the request. 
	 *         404 - Not Found: The requested resource doesn't exist. 
	 *         409 - Conflict: The request conflicts with another request (perhaps due to using the same
	 *         idempotent key). 
	 *         429 - Too Many Requests: Too many requests hit the
	 *         API too quickly. We recommend an exponential back-off of your
	 *         requests. 
	 *         500, 502, 503, 504 - Server Errors: something went wrong on
	 *         API end (These are rare).
	 */
	@PostMapping("/create")
	public ResponseEntity<UserDTO> create(@Valid @RequestBody UserDTO userDTO) {

		System.out.println("*** Calling create user ****");
		System.out.println("Role : " + userDTO.getRole());
		UserDTO newUser = this.userService.save(userDTO);

		return new ResponseEntity<>(newUser, HttpStatus.CREATED);
	}
	
	@PostMapping("/update")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<UserDTO> update(@Valid @RequestBody UserDTO userDTO) {

		System.out.println("*** Calling update user ****");
		System.out.println("Role : " + userDTO.getRole());
		UserDTO newUser = this.userService.save(userDTO);

		return new ResponseEntity<>(newUser, HttpStatus.OK);
	}

	@GetMapping("/findByDeletedAtIsNull")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<UserDTO>> findByDeletedAtIsNull() {

		List<UserDTO> newUser = this.userService.findByDeletedAtIsNull();
		
		return new ResponseEntity<>(newUser, HttpStatus.OK);
	}
	
	@GetMapping("/findByDeletedAtIsNotNull")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<UserDTO>> findByDeletedAtIsNotNull() {

		List<UserDTO> newUser = this.userService.findByDeletedAtIsNotNull();
		
		return new ResponseEntity<>(newUser, HttpStatus.OK);
	}

}
