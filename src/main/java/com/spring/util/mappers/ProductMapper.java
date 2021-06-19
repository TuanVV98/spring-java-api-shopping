package com.spring.util.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.dto.model.ProductDTO;
import com.spring.entity.Product;


@Service
public class ProductMapper {

	
	@Autowired
	private ModelMapper mapper;

	public Product convertDTOToEntity(ProductDTO proDTO) {

		return mapper.map(ProductDTO.class, Product.class);
	}

	public ProductDTO convertEntityToDTO(Product product) {

		return mapper.map(product, ProductDTO.class);
	}
}
