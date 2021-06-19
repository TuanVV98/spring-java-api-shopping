package com.spring.service.orderDetails;

import java.util.List;

import com.spring.dto.model.OrderDetailsDTO;

public interface OrderDetailsService {

	
	/**
	 * Method save user in database
	 * 
	 * @since 06/06/2021
	 * 
	 * @param OrderDetailsDTO
	 * @return OrderDetailsDTO Object
	 */
	OrderDetailsDTO save(OrderDetailsDTO orderDetailsDTO);
	
	/**
	 * Method that find all users
	 * 
	 * @since 06/06/2021
	 * 
	 * @return <code>List<OrderDetailsDTO></code> object
	 */
	List<OrderDetailsDTO> findAll();
	
	/**
	 * Method find all by orderID
	 * 
	 * @since 15/06/2021
	 * @param order
	 * @return <code>List<OrderDetails></code> Object 
	 */
	List<OrderDetailsDTO> findByOrderId(Long orderId);
}
