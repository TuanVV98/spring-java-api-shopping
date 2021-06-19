package com.spring.service.category;

import java.util.List;

import com.spring.dto.model.CategoryDTO;
//import com.spring.entity.Category;
import com.spring.entity.Category;

public interface CategoryService {

	/**
	 * Method save category in database
	 * 
	 * @since 07/06/2021
	 * 
	 * @param cate
	 * @return Category Object
	 */
	CategoryDTO save(CategoryDTO cateDTO);
	
	/**
	 * Method update category in database
	 * 
	 * @since 07/06/2021
	 * 
	 * @param cate
	 * @return Category Object
	 */
	CategoryDTO update(CategoryDTO cateDTO);
	
	/**
	 * Method that find all Categories
	 * 
	 * @since 07/06/2021
	 * 
	 * @return <code>List<CategoryDTO></code> Object
	 */
	List<CategoryDTO> findAll();
	
	/**
	 * Method that find by deletedAt is null Categories
	 * 
	 * @since 07/06/2021
	 * 
	 * @return <code>List<CategoryDTO></code> Object
	 */
	List<CategoryDTO> findByDeletedAtIsNull();
	

	/**
	 * Method that find by deletedAt is not null in Categories
	 * 
	 * @since 16/06/2021
	 * 
	 * @return <code>List<CategoryDTO></code> Object
	 */
	List<CategoryDTO>   findByDeletedAtIsNotNull();
}
