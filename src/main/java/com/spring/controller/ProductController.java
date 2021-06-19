package com.spring.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.dto.model.ProductDTO;
import com.spring.service.product.ProductService;

@RestController
@RequestMapping("/rest-api/product")
public class ProductController {

	@Autowired
	private ProductService productService;

	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<List<ProductDTO>> findAll() {
		System.out.println("*** Calling find all products ***");

		List<ProductDTO> newProDTO = this.productService.findAll();
		return new ResponseEntity<>(newProDTO, HttpStatus.OK);
	}

	@PostMapping("/create")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<ProductDTO> create(@Valid @RequestBody ProductDTO proDTO) {
		System.out.println("*** Calling create product ***");
//		System.out.println(
//				"name:" + proDTO.getName() + " ;cate : " + proDTO.getCategoryId() + " ;image : " + proDTO.getImage());

		ProductDTO newProDTO = this.productService.save(proDTO);

		return new ResponseEntity<>(newProDTO, HttpStatus.CREATED);
	}

	@PostMapping("/update")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<ProductDTO> update(@Valid @RequestBody ProductDTO proDTO) {
		System.out.println("*** Calling create product ***");
//		System.out.println(
//				"name:" + proDTO.getName() + " ;cate : " + proDTO.getCategoryId() + " ;image : " + proDTO.getImage()+" deleted at "+proDTO.getDeletedUser());

		ProductDTO newProDTO = this.productService.update(proDTO);

		return new ResponseEntity<>(newProDTO, HttpStatus.OK);
	}

	@GetMapping("/findByDeletedAtIsNull")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<List<ProductDTO>> findByDeletedAtIsNull() {

		System.out.println("*** Calling find all products by delete at is null ***");

		List<ProductDTO> newProDTO = this.productService.findByDeletedAtIsNull();
		
		return new ResponseEntity<>(newProDTO, HttpStatus.OK);
	}
	
	@GetMapping("/findByDeletedAtIsNotNull")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<ProductDTO>> findByDeletedAtIsNotNull() {

		System.out.println("*** Calling find all products by delete at is not null ***");

		List<ProductDTO> newProDTO = this.productService.findByDeletedAtIsNotNull();
		
		return new ResponseEntity<>(newProDTO, HttpStatus.OK);
	}


	@GetMapping("/findByCategory/{id}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<List<ProductDTO>> findByCategory(@PathVariable("id") long id) {

		System.out.println("*** Calling find all products by category ***");

//		System.out.println("id : "+id);
		List<ProductDTO> newProDTO = this.productService.findByCategory(id);
		
	return new ResponseEntity<>(newProDTO, HttpStatus.OK);
	}
}
