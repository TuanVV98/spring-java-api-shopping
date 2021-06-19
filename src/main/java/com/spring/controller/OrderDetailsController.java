package com.spring.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.dto.model.OrderDetailsDTO;
import com.spring.service.orderDetails.OrderDetailsService;

@RestController
@RequestMapping("/rest-api/order-details")
public class OrderDetailsController {

	@Autowired
	private OrderDetailsService orderDetailsService;

	public OrderDetailsController(OrderDetailsService orderDetailsService) {
		this.orderDetailsService = orderDetailsService;
	}

	@PostMapping("/create")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<OrderDetailsDTO> create(@Valid @RequestBody OrderDetailsDTO orderDetailsDTO) {

		System.out.println("*** Calling order deatils ***");

		OrderDetailsDTO newOrderDetails = this.orderDetailsService.save(orderDetailsDTO);
//		System.out.println("order id:"+orderDetailsDTO.getOrderID());
//		System.out.println("product id:"+orderDetailsDTO.getProductID());
//		System.out.println("price:"+orderDetailsDTO.getPrice());
//		System.out.println("quanlity:"+orderDetailsDTO.getQuanlity());
//		
		return new ResponseEntity<>(newOrderDetails, HttpStatus.CREATED);
	}

	@GetMapping("/findByOrder/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<OrderDetailsDTO>> findByOrder(@PathVariable("id") long id) {
		System.out.println("*** Calling find all order deatils by order id  ***");
		System.out.println("order id :" + id);
		List<OrderDetailsDTO> newOrderDetail = this.orderDetailsService.findByOrderId(id);

		return new ResponseEntity<>(newOrderDetail, HttpStatus.OK);
	}

}
