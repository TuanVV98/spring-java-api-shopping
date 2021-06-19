package com.spring.service.product;

import java.util.List;

import com.spring.dto.model.ProductDTO;
import com.spring.entity.Category;
import com.spring.entity.Product;

public interface ProductService {

	
	/**
	 * Method save product in database
	 * 
	 * @since 11/06/2021
	 * 
	 * @param proDTO
	 * @return Product Object
	 */
	ProductDTO save(ProductDTO proDTO);
	
	/**
	 * Method update category in database
	 * 
	 * @since 11/06/2021
	 * 
	 * @param proDTO
	 * @return Product Object
	 */
	ProductDTO update(ProductDTO proDTO);
	
	/**
	 * Method that find all Products
	 * 
	 * @since 11/06/2021
	 * 
	 * @return <code>List<ProductDTO></code> Object
	 */
	List<ProductDTO> findAll();
	
	/**
	 * Method that find by deletedAt is null in Products
	 * 
	 * @since 11/06/2021
	 * 
	 * @return <code>List<ProductDTO></code> Object
	 */
	List<ProductDTO> findByDeletedAtIsNull();
	
	/**
	 * Method that find by category id in products
	 * 
	 * @since 11/06/2021
	 * @param Long categoryID
	 * 
	 * @return <code>List<ProductDTO></code> Object
	 */
	List<ProductDTO> findByCategory(Long categoryID);
	
	/**
	 * Method that find by deletedAt is not null in Products
	 * 
	 * @since 15/06/2021
	 * 
	 * @return <code>List<ProductDTO></code> Object
	 */
	List<ProductDTO>   findByDeletedAtIsNotNull();
	
}
