package com.spring.service.category;

import com.spring.dto.model.CategoryDTO;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CategoryService {

    CategoryDTO save(CategoryDTO categoryDTO);

    CategoryDTO update(CategoryDTO categoryDTO);

    List<CategoryDTO> findAllIsNull();

    List<CategoryDTO> findAllIsNotNull();
}
