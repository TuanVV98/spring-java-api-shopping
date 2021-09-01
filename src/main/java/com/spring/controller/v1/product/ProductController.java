package com.spring.controller.v1.product;


import com.spring.dto.model.ProductDTO;
import com.spring.dto.response.Response;
import com.spring.service.files.FilesStorageService;
import com.spring.service.product.ProductService;
import com.spring.utils.ApiUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private ProductService productService;

    private FilesStorageService filesStorageService;

    @Autowired
    public ProductController(ProductService productService, FilesStorageService filesStorageService) {
        this.productService = productService;
        this.filesStorageService = filesStorageService;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Route to created products")
    public ResponseEntity<Response<ProductDTO>> create(
            @RequestHeader(value = ApiUtil.HEADER_API_VERSION, defaultValue = "${api.version}") String apiVersion,
            @RequestHeader(value = ApiUtil.HEADER_API_KEY, defaultValue = "${api.key}") String apiKey,
            @RequestPart("properties") @Valid ProductDTO productDTO,
            @RequestPart("file") @Valid @NotNull @NotBlank MultipartFile file,
            BindingResult result
    ) {
        Response<ProductDTO> response = new Response<>();

        if (result.hasErrors()) {
            result.getAllErrors().forEach(err -> response.addErrorMsgToResponse(err.getDefaultMessage()));
            return ResponseEntity.badRequest().body(response);
        }

        if(this.productService.findByModel(productDTO.getModel()).isPresent()){
            response.addErrorMsgToResponse("Product already exists for this model !");
            return ResponseEntity.badRequest().body(response);
        }

//        this.filesStorageService.save(file);
        response.setData(this.productService.save(productDTO, file));

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add(ApiUtil.HEADER_API_VERSION, apiVersion);
        headers.add(ApiUtil.HEADER_API_KEY, apiKey);

        return new ResponseEntity<>(response, headers, HttpStatus.CREATED);
    }


    @PutMapping(value = "/{id}/file", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Route to updated a product there are files")
    public ResponseEntity<Response<ProductDTO>> updateWithFile(
            @RequestHeader(value = ApiUtil.HEADER_API_VERSION, defaultValue = "${api.version}") String apiVersion,
            @RequestHeader(value = ApiUtil.HEADER_API_KEY, defaultValue = "${api.key}") String apiKey,
            @RequestPart("properties") @Valid ProductDTO productDTO,
            @RequestPart("file") @Valid @NotNull @NotBlank MultipartFile file,
            BindingResult result
    ) {
        System.out.println("file :" + file);
        System.out.println("name :" + productDTO.getName());
        System.out.println("findByCategory id :" + productDTO.getCategoryID());
        System.out.println("images :" + productDTO.getImage());
        Response<ProductDTO> response = new Response<>();

        if (result.hasErrors()) {
            result.getAllErrors().forEach(err -> response.addErrorMsgToResponse(err.getDefaultMessage()));
            return ResponseEntity.badRequest().body(response);
        }

        response.setData(this.productService.save(productDTO, file));

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add(ApiUtil.HEADER_API_VERSION, apiVersion);
        headers.add(ApiUtil.HEADER_API_KEY, apiKey);

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Route to updated a product there aren't files")
    public ResponseEntity<Response<ProductDTO>> update(
            @RequestHeader(value = ApiUtil.HEADER_API_VERSION, defaultValue = "${api.version}") String apiVersion,
            @RequestHeader(value = ApiUtil.HEADER_API_KEY, defaultValue = "${api.key}") String apiKey,
            @Valid @RequestBody ProductDTO productDTO,
            BindingResult result
    ) {
        Response<ProductDTO> response = new Response<>();

        if (result.hasErrors()) {
            result.getAllErrors().forEach(err -> response.addErrorMsgToResponse(err.getDefaultMessage()));
            return ResponseEntity.badRequest().body(response);
        }

        response.setData(this.productService.update(productDTO));

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add(ApiUtil.HEADER_API_VERSION, apiVersion);
        headers.add(ApiUtil.HEADER_API_KEY, apiKey);

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @GetMapping(value = "/image", produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    @ApiOperation(value = "Route to get image by filename")
    public byte[] getImage(@RequestParam(required = false) String filename) throws IOException {
        System.out.println("file name :" + filename);
        return this.filesStorageService.downloadFtpFile(filename);
    }

//    @GetMapping(value = "/image", produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
//    public byte[] getImage(@RequestParam(required = false) String filename) throws IOException {
//        System.out.println("file name :" + filename);
//        return this.filesStorageService.downloadFtpFile(filename);
//    }

    @GetMapping
    @ApiOperation(value = "Route to get all products")
    public ResponseEntity<Response<List<ProductDTO>>> getAll(
            @RequestHeader(value = ApiUtil.HEADER_API_VERSION, defaultValue = "${api.version}") String apiVersion,
            @RequestHeader(value = ApiUtil.HEADER_API_KEY, defaultValue = "${api.key}") String apiKey
    ) {
        Response<List<ProductDTO>> response = new Response<>();

        response.setData(this.productService.getAll());

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add(ApiUtil.HEADER_API_VERSION, apiVersion);
        headers.add(ApiUtil.HEADER_API_KEY, apiKey);

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @GetMapping("/soft-delete")
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Route to get all products deleted ")
    public ResponseEntity<Response<List<ProductDTO>>> getAllIsNull(
            @RequestHeader(value = ApiUtil.HEADER_API_VERSION, defaultValue = "${api.version}") String apiVersion,
            @RequestHeader(value = ApiUtil.HEADER_API_KEY, defaultValue = "${api.key}") String apiKey
    ) {
        Response<List<ProductDTO>> response = new Response<>();

        response.setData(this.productService.getAllIsNull());

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add(ApiUtil.HEADER_API_VERSION, apiVersion);
        headers.add(ApiUtil.HEADER_API_KEY, apiKey);

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @GetMapping(value = "/byCategory")
    @ApiOperation(value = "Route to find products by category Id")
    public ResponseEntity<Response<List<ProductDTO>>> findByCategoryID(
            @RequestHeader(value = ApiUtil.HEADER_API_VERSION, defaultValue = "${api.version}") String apiVersion,
            @RequestHeader(value = ApiUtil.HEADER_API_KEY, defaultValue = "${api.key}") String apiKey,
            @RequestParam(required = false) String name
    ) {
        Response<List<ProductDTO>> response = new Response<>();
        response.setData(this.productService.findByCategory(name));

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add(ApiUtil.HEADER_API_VERSION, apiVersion);
        headers.add(ApiUtil.HEADER_API_KEY, apiKey);

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @GetMapping(value = "/byModel")
    @ApiOperation(value = "Route to find products by model")
    public ResponseEntity<Response<Optional<ProductDTO>>> findByModel(
            @RequestHeader(value = ApiUtil.HEADER_API_VERSION, defaultValue = "${api.version}") String apiVersion,
            @RequestHeader(value = ApiUtil.HEADER_API_KEY, defaultValue = "${api.key}") String apiKey,
            @RequestParam(required = false) String model
    ) {
        System.out.println("model :" + model);
        Response<Optional<ProductDTO>> response = new Response<>();

        Optional<ProductDTO> entity = this.productService.findByModelAndDeletedAtIsNull(model);
        response.setData(entity);
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add(ApiUtil.HEADER_API_VERSION, apiVersion);
        headers.add(ApiUtil.HEADER_API_KEY, apiKey);

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }
}
