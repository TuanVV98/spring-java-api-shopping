package com.spring.util.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.dto.model.CategoryDTO;
import com.spring.entity.Category;


@Service
public class CategoryMapper {

	@Autowired
	private ModelMapper mapper;

	public Category convertDTOToEntity(Object cateDTO ) {

		return mapper.map(cateDTO, Category.class);
	}

	public CategoryDTO convertEntityToDTO(Object cate) {

		return mapper.map(cate, CategoryDTO.class);
	}

}
