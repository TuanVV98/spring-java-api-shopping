package com.spring.service.order;

import java.util.List;
import java.util.Optional;

import com.spring.dto.model.OrderDTO;
import com.spring.dto.model.UserDTO;
import com.spring.entity.User;

public interface OrderService {

	/**
	 * Method save user in database
	 * 
	 * @since 06/06/2021
	 * 
	 * @param user
	 * @return UserDTO Object
	 */
	OrderDTO save(OrderDTO orderDTO);
	
	/**
	 * Method that find all users
	 * 
	 * @since 06/06/2021
	 * 
	 * @return <code>List<UserDTO></code> object
	 */
	List<OrderDTO> findAll();
	
	/**
	 * Method that find all users
	 * 
	 * @since 11/06/2021
	 * @param Staring email
	 * @return <code>Optional<UserDTO></code> object
	 */
//	Optional<User> findByEmail(String email);
	
}
