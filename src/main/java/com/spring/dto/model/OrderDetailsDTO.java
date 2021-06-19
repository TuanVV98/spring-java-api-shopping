package com.spring.dto.model;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import org.modelmapper.ModelMapper;

import com.spring.entity.OrderDetails;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailsDTO {

	
	private Long id;
	
	@NotNull
	private float price;
	
	@NotNull
	private Integer quanlity;
	
	@NotNull
	private Date createdAt;
	
	@Null
	private Date deletedAt;
	
	@Null
	private Integer deletedUser;
	
	@NotNull
	private Long orderID;
	
	@NotNull
	private Long productID;
	
	public OrderDetails convertDTOToEntity() {
		return new ModelMapper().map(this, OrderDetails.class);
	}
}
