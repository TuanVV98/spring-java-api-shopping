package com.spring.dto.model;

import java.util.Date;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import org.modelmapper.ModelMapper;

import com.spring.entity.Product;

import lombok.*;




@Setter
@Getter
@AllArgsConstructor

public class ProductDTO {
	
	private Long id;

	@NotNull
	private String model;
	
	@NotNull
	private String name;
	
	@NotNull
	private float price;
	
	@NotNull
	private String image;
	
	@NotNull
	private String description;
	
	@NotNull
	private Date createdAt;

	
	private Date deletedAt;

	
	private Long deletedUser;
	
	@NotNull
	private Long categoryId;
	
	public ProductDTO() {}
	
	public Product convertDTOToEntity() {
		return new ModelMapper().map(this, Product.class);
	}
}
