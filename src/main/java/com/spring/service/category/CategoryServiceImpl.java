package com.spring.service.category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.dto.model.CategoryDTO;
import com.spring.entity.Category;
import com.spring.repository.CategoryRepository;
import com.spring.util.mappers.CategoryMapper;
import com.spring.util.mappers.MapperUtil;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository category;

	@Autowired
	private CategoryMapper cateMapper;

	@Autowired
	private MapperUtil mapperUtil;

	public CategoryServiceImpl(CategoryRepository category, MapperUtil mapperUtil, CategoryMapper cateMapper) {
		this.category = category;
		this.cateMapper = cateMapper;
		this.mapperUtil = mapperUtil;
	}

	/**
	 * @see CategoryService#save(Category)
	 */
	@Override
	public CategoryDTO save(CategoryDTO cateDTO) {

		Category cateEntity = this.cateMapper.convertDTOToEntity(cateDTO);

		return this.cateMapper.convertEntityToDTO(this.category.save(cateEntity));
	}

	/**
	 * @see CategoryService#update(CategoryDTO)
	 */
	@Override
	public CategoryDTO update(CategoryDTO cateDTO) {

		Category cateEntity = this.cateMapper.convertDTOToEntity(cateDTO);

		return this.cateMapper.convertEntityToDTO(this.category.save(cateEntity));
	}

	/**
	 * @see CategoryService#findAll()
	 */
	@Override
	public List<CategoryDTO> findAll() {

//		for (int i = 0; i < this.category.findAll().size(); i++) {
//			System.out.println("list cate : " + this.category.findAll().get(i).getCreatedAt());
//		}
		return mapperUtil.mapList(this.category.findAll(), CategoryDTO.class);
	}

	/**
	 * @see CategoryService#findByDeletedAtIsNull()
	 */

	@Override
	public List<CategoryDTO> findByDeletedAtIsNull() {

		return mapperUtil.mapList(this.category.findByDeletedAtIsNull(), CategoryDTO.class);

	}

}
