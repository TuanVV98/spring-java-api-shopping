package com.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.entity.OrderDetails;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetails, Long> {

	/**
	 * Method find all by orderID
	 * 
	 * @since 15/06/2021
	 * @param order
	 * @return <code>List<OrderDetails></code> Object 
	 */
	List<OrderDetails> findByOrderId(Long orderId);
	
}
