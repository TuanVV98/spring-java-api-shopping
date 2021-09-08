package com.spring.controller.v1.category;

import ch.qos.logback.core.boolex.EvaluationException;
import com.spring.dto.model.CategoryDTO;
import com.spring.dto.response.Response;
import com.spring.service.category.CategoryService;
import com.spring.utils.ApiUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Route to created categories")
    public ResponseEntity<Response<CategoryDTO>> create(
            @RequestHeader(value = ApiUtil.HEADER_API_VERSION, defaultValue = "${api.version}") String apiVersion,
            @RequestHeader(value = ApiUtil.HEADER_API_KEY, defaultValue = "${api.key}") String apiKey,
            @Valid @RequestBody CategoryDTO categoryDTO,
            BindingResult result
    ) {
//        System.out.println("name :" + categoryDTO.getName());
//        System.out.println("context :" + categoryDTO.getContext());
        Response<CategoryDTO> response = new Response<>();
        if (result.hasErrors()) {
            result.getAllErrors().forEach(err -> response.addErrorMsgToResponse(err.getDefaultMessage()));
            return ResponseEntity.badRequest().body(response);
        }

        response.setData(this.categoryService.save(categoryDTO));

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add(ApiUtil.HEADER_API_VERSION, apiVersion);
        headers.add(ApiUtil.HEADER_API_KEY, apiKey);

        return new ResponseEntity<>(response, headers, HttpStatus.CREATED);
    }

    @GetMapping
    @ApiOperation(value="Route to get all categories")
    public ResponseEntity<Response<List<CategoryDTO>>> getAll(
            @RequestHeader(value = ApiUtil.HEADER_API_VERSION, defaultValue = "${api.version}") String apiVersion,
            @RequestHeader(value = ApiUtil.HEADER_API_KEY, defaultValue = "${api.key}") String apiKey
    ) {
        Response<List<CategoryDTO>> response = new Response<>();

        response.setData(this.categoryService.findAllIsNull());

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add(ApiUtil.HEADER_API_VERSION, apiVersion);
        headers.add(ApiUtil.HEADER_API_KEY, apiKey);

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @GetMapping(value = "/soft-delete")
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Route to get all categories deleted ")
    public ResponseEntity<Response<List<CategoryDTO>>> getAllIsNull(
            @RequestHeader(value = ApiUtil.HEADER_API_VERSION, defaultValue = "${api.version}") String apiVersion,
            @RequestHeader(value = ApiUtil.HEADER_API_KEY, defaultValue = "${api.key}") String apiKey
    ) {
        Response<List<CategoryDTO>> response = new Response<>();

        response.setData(this.categoryService.findAllIsNotNull());

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add(ApiUtil.HEADER_API_VERSION, apiVersion);
        headers.add(ApiUtil.HEADER_API_KEY, apiKey);

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Route to updated a category ")
    public ResponseEntity<Response<CategoryDTO>> update(
            @RequestHeader(value = ApiUtil.HEADER_API_VERSION, defaultValue = "${api.version}") String apiVersion,
            @RequestHeader(value = ApiUtil.HEADER_API_KEY, defaultValue = "${api.key}") String apiKey,
            @Valid @RequestBody CategoryDTO categoryDTO,
            BindingResult result
    ) {
//        System.out.println("id : " + categoryDTO.getId());
//        System.out.println("name : " + categoryDTO.getName());
//        System.out.println("context : " + categoryDTO.getContext());

        Response<CategoryDTO> response = new Response<>();
        if (result.hasErrors()) {
            result.getAllErrors().forEach(err -> response.addErrorMsgToResponse(err.getDefaultMessage()));
            return ResponseEntity.badRequest().body(response);
        }

        response.setData(this.categoryService.update(categoryDTO));

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add(ApiUtil.HEADER_API_VERSION, apiVersion);
        headers.add(ApiUtil.HEADER_API_KEY, apiKey);

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

}
