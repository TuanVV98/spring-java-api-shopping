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

import com.spring.dto.model.OrderDTO;
import com.spring.service.order.OrderService;

@RestController
@RequestMapping("/rest-api/order")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}
	
	@PostMapping("/create")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<OrderDTO> create(@Valid @RequestBody OrderDTO orderDTO ){
		System.out.println("*** Calling create Order ***");
		
		OrderDTO newOrder = this.orderService.save(orderDTO);
		
		return new ResponseEntity<>(newOrder,HttpStatus.CREATED);
	}
	

	@GetMapping()
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<OrderDTO>> findAll(){
		
		List<OrderDTO> newOrder = this.orderService.findAll();
		
		return new ResponseEntity<>(newOrder,HttpStatus.OK);
	}

}
