package com.spring.dto.model;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import org.modelmapper.ModelMapper;

import com.spring.entity.Order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

	
	private Long id;
	
	@NotNull
	private String address;
	
	@NotNull
	private Date createdAt;
	
	@Null
	private Date deletedAt;
	
	@Null
	private Integer deletedUser;
	
	@NotNull
	private Long userID;
	
	public Order convertDTOToEntity() {
		return new ModelMapper().map(this, Order.class);
	}
}
