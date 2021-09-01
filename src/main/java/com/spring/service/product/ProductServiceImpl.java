package com.spring.service.product;

import com.spring.dto.model.ProductDTO;
import com.spring.model.Product;
import com.spring.repository.ProductRepository;
import com.spring.service.files.FilesStorageService;
import com.spring.utils.mapper.MapperUtil;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    private FilesStorageService filesStorageService;

    private MapperUtil mapperUtil;

    @Autowired
    public ProductServiceImpl(
            ProductRepository productRepository,
            FilesStorageService filesStorageService,
            MapperUtil mapperUtil
    ) {
        this.productRepository = productRepository;
        this.filesStorageService = filesStorageService;
        this.mapperUtil = mapperUtil;
    }

    @Override
    public ProductDTO save(ProductDTO productDTO, MultipartFile file) {
        Product entity = productDTO.convertDTOToEntity();

        if (!file.isEmpty()) {
            String uuid = UUID.randomUUID().toString();
            String extension = FilenameUtils.getExtension(file.getOriginalFilename());
            String filename = uuid + "." + extension;
//            System.out.println("file name :"+filename);
            entity.setImage(filename);
            this.filesStorageService.save(filename, file);
        }
        return this.productRepository.save(entity).convertEntityToDTO();
    }

    @Override
    public ProductDTO update(ProductDTO productDTO) {
        Product product = this.productRepository.save(productDTO.convertDTOToEntity());
        return product.convertEntityToDTO();
    }

    @Override
    public List<ProductDTO> getAll() {
        return this.productRepository.findByDeletedAtIsNull();

//        return mapperUtil.mapList(this.productRepository.findByDeletedAtIsNull(),ProductDTO.class);
    }

    @Override
    public List<ProductDTO> getAllIsNull() {
        return this.productRepository.findByDeletedAtIsNotNull();
    }

    @Override
    public List<ProductDTO> findByCategory(String name) {
        return mapperUtil.mapList(this.productRepository.findByCategory(name), ProductDTO.class);
    }

    @Override
    public Optional<ProductDTO> findByModelAndDeletedAtIsNull(String model) {
        Optional<Product> entity = this.productRepository.findByModelAndDeletedAtIsNull(model);
        if (entity.isPresent()) {
            return entity.map(Product::convertEntityToDTO);
        }
        return Optional.empty();
    }

    @Override
    public Optional<ProductDTO> findByModel(String model) {
        Optional<Product> entity = this.productRepository.findByModel(model);
        if (entity.isPresent()) {
            return entity.map(Product::convertEntityToDTO);
        }
        return Optional.empty();
    }
}
