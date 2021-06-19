package com.spring.service.product;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.dto.model.ProductDTO;
import com.spring.entity.Product;
import com.spring.repository.ProductRepository;
import com.spring.util.mappers.MapperUtil;
import com.spring.util.mappers.ProductMapper;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRespo;

	@Autowired
	private ProductMapper proMapper;

	@Autowired
	private MapperUtil mapperUtil;

	public ProductServiceImpl(ProductRepository productRespo, ProductMapper proMapper, MapperUtil mapperUtil) {
		this.productRespo = productRespo;
		this.proMapper = proMapper;
		this.mapperUtil = mapperUtil;

	}

	/**
	 * @see ProductService#save(ProductDTO)
	 */
	@Override
	public ProductDTO save(ProductDTO proDTO) {
		Product proEntity = proDTO.convertDTOToEntity();

		return this.proMapper.convertEntityToDTO(this.productRespo.save(proEntity));
	}

	/**
	 * @see ProductService#update(ProductDTO)
	 */
	@Override
	public ProductDTO update(ProductDTO proDTO) {
		Product proEntity = proDTO.convertDTOToEntity();

		return this.proMapper.convertEntityToDTO(this.productRespo.save(proEntity));
	}

	/**
	 * @see ProductService#findAll()
	 */
	@Override
	public List<ProductDTO> findAll() {
		List<ProductDTO> itemsDTO = new ArrayList<>();

		this.productRespo.findAll().stream().forEach(t -> itemsDTO.add(t.convertEntityToDTO()));
		return itemsDTO;
	}

	/**
	 * @see ProductService#findByDeletedAtIsNull()
	 */
	@Override
	public List<ProductDTO> findByDeletedAtIsNull() {
		List<ProductDTO> itemsDTO = new ArrayList<>();

		this.productRespo.findByDeletedAtIsNull().stream().forEach(t -> itemsDTO.add(t.convertEntityToDTO()));
		return itemsDTO;
	}

	/**
	 * @see ProductService#findByCategory(Long categoryID)
	 */
	@Override
	public List<ProductDTO> findByCategory(Long categoryID) {
		List<ProductDTO> itemsDTO = new ArrayList<>();

		this.productRespo.findByCategoryId(categoryID).stream().forEach(t -> itemsDTO.add(t.convertEntityToDTO()));
		return itemsDTO;
	}

	@Override
	public List<ProductDTO> findByDeletedAtIsNotNull() {
		List<ProductDTO> itemsDTO = new ArrayList<>();

		this.productRespo.findByDeletedAtIsNotNull().stream().forEach(t -> itemsDTO.add(t.convertEntityToDTO()));
		return itemsDTO;
	}

}
