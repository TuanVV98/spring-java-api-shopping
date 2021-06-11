package com.spring.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.dto.model.CategoryDTO;
import com.spring.service.category.CategoryService;

@RestController
@RequestMapping("/rest-api/category")
public class CategoryController {

	private CategoryService category;

	@Autowired
	public CategoryController(CategoryService category) {
		this.category = category;
	}

	@GetMapping
	public ResponseEntity<List<CategoryDTO>> findAll() {
		System.out.println("****  Calling find all Category ****");

		List<CategoryDTO> newCateDTO = this.category.findAll();

		return new ResponseEntity<>(newCateDTO, HttpStatus.OK);
	}

	@PostMapping("/create")
	public ResponseEntity<CategoryDTO> create(@Valid @RequestBody CategoryDTO cateDTO) {

		System.out.println("****  Calling created Category ****");

		CategoryDTO newCateDTO = this.category.save(cateDTO);

		return new ResponseEntity<>(newCateDTO, HttpStatus.CREATED);
	}

	@PostMapping("/update")
	public ResponseEntity<CategoryDTO> update(@Valid @RequestBody CategoryDTO cateDTO) {
		System.out.println("****  Calling update Category ****");
		System.out.println("Cate : " + cateDTO.getName() + "---" + cateDTO.getId());

		CategoryDTO newCateDTO = this.category.update(cateDTO);
		return new ResponseEntity<>(newCateDTO, HttpStatus.OK);
	}

}
