package com.spring.service.category;

import com.spring.dto.model.CategoryDTO;
import com.spring.model.Category;
import com.spring.repository.CategoryRepository;
import com.spring.utils.mapper.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    private MapperUtil mapperUtil;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, MapperUtil mapperUtil) {
        this.categoryRepository = categoryRepository;
        this.mapperUtil = mapperUtil;
    }

    @Override
    public CategoryDTO save(CategoryDTO categoryDTO) {
        Category category = this.categoryRepository.save(categoryDTO.convertDTOToEntity());
        return category.convertEntityToDTO();
    }

    @Override
    public CategoryDTO update(CategoryDTO categoryDTO) {
//        categoryDTO.setUpdatedAt(LocalDateTime.now());
        Category category = this.categoryRepository.save(categoryDTO.convertDTOToEntity());
        return category.convertEntityToDTO();
    }

    @Override
    public List<CategoryDTO> findAllIsNull() {
        return mapperUtil.mapList(this.categoryRepository.findByDeletedAtIsNull(),CategoryDTO.class);
    }

    @Override
    public List<CategoryDTO> findAllIsNotNull() {
        return mapperUtil.mapList(this.categoryRepository.findByDeletedAtIsNotNull(),CategoryDTO.class);
    }
}
