package com.spring.controller.v1.user;

import com.spring.dto.model.UserDTO;
import com.spring.dto.response.Response;
import com.spring.service.user.UserService;
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
@RequestMapping("/api/v1/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Route to get all users")
    public ResponseEntity<Response<List<UserDTO>>> getAll(
            @RequestHeader(value = ApiUtil.HEADER_API_VERSION, defaultValue = "${api.version}") String apiVersion,
            @RequestHeader(value = ApiUtil.HEADER_API_KEY, defaultValue = "${api.key}") String apiKey
    ){
        Response<List<UserDTO>> response = new Response<>();

        response.setData(this.userService.getAll());

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add(ApiUtil.HEADER_API_VERSION, apiVersion);
        headers.add(ApiUtil.HEADER_API_KEY, apiKey);

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Route to updated a user")
    public ResponseEntity<Response<UserDTO>> update(
            @RequestHeader(value = ApiUtil.HEADER_API_VERSION, defaultValue = "${api.version}") String apiVersion,
            @RequestHeader(value = ApiUtil.HEADER_API_KEY, defaultValue = "${api.key}") String apiKey,
            @Valid @RequestBody UserDTO userDTO,
            BindingResult result
    ){

        System.out.println("password :"+userDTO.getPassword());
        System.out.println("status :"+userDTO.getStatus());
        Response<UserDTO> response = new Response<>();
        if(result.hasErrors()){
            result.getAllErrors().forEach(err ->response.addErrorMsgToResponse(err.getDefaultMessage()));
            return ResponseEntity.badRequest().body(response);
        }
        response.setData(this.userService.update(userDTO));

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add(ApiUtil.HEADER_API_VERSION, apiVersion);
        headers.add(ApiUtil.HEADER_API_KEY, apiKey);

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }
}
