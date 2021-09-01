package com.spring.service.product;

import com.spring.dto.model.ProductDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    ProductDTO save(ProductDTO productDTO, MultipartFile file);

    ProductDTO update(ProductDTO productDTO);

    List<ProductDTO> getAll();

    List<ProductDTO> getAllIsNull();

    List<ProductDTO> findByCategory(String name);

    Optional<ProductDTO> findByModelAndDeletedAtIsNull(String model);

    Optional<ProductDTO> findByModel(String model);
}
