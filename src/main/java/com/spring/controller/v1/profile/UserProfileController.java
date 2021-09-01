package com.spring.controller.v1.profile;

import com.spring.dto.model.UserProfileDTO;
import com.spring.dto.response.Response;
import com.spring.service.user.profile.UserProfileService;
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
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1/user/profile")
public class UserProfileController {

    private UserProfileService userProfileService;

    @Autowired
    public UserProfileController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @PostMapping
    @ApiOperation(value = "Route to created user profile")
    public ResponseEntity<Response<UserProfileDTO>> create(
            @RequestHeader(value = ApiUtil.HEADER_API_VERSION, defaultValue = "${api.version}") String apiVersion,
            @Valid @RequestBody UserProfileDTO userProfileDTO,
            BindingResult result
    ) {

        Response<UserProfileDTO> response = new Response<>();

        if (result.hasErrors()) {
            result.getAllErrors().forEach(error -> response.addErrorMsgToResponse(error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(response);
        }

        UserProfileDTO entity = this.userProfileService.save(userProfileDTO);
        response.setData(entity);

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add(ApiUtil.HEADER_API_VERSION, apiVersion);

        return new ResponseEntity<>(response, headers, HttpStatus.CREATED);
    }

    @PutMapping
    @ApiOperation(value = "Route to updated user profile")
    public ResponseEntity<Response<UserProfileDTO>> update(
            @RequestHeader(value = ApiUtil.HEADER_API_VERSION, defaultValue = "${api.version}") String apiVersion,
            @RequestHeader(value = ApiUtil.HEADER_API_KEY, defaultValue = "${api.key}") String apiKey,
            @Valid @RequestBody UserProfileDTO userProfileDTO,
            BindingResult result
    ) {
        Response<UserProfileDTO> response = new Response<>();

        if (result.hasErrors()) {
            result.getAllErrors().forEach(error -> response.addErrorMsgToResponse(error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(response);
        }

        UserProfileDTO entity = this.userProfileService.save(userProfileDTO);
        response.setData(entity);

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add(ApiUtil.HEADER_API_VERSION, apiVersion);
        headers.add(ApiUtil.HEADER_API_KEY, apiKey);

        return new ResponseEntity<>(response, headers, HttpStatus.CREATED);
    }

    @GetMapping(value = "/findBy")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @ApiOperation(value = "Route to find profile by user Id")
    public ResponseEntity<Response<Optional<UserProfileDTO>>> findByUserId(
            @RequestHeader(value = ApiUtil.HEADER_API_VERSION, defaultValue = "${api.version}") String apiVersion,
            @RequestHeader(value = ApiUtil.HEADER_API_KEY, defaultValue = "${api.key}") String apiKey,
            @RequestParam(required = false) Long id
    ) {
        Response<Optional<UserProfileDTO>> response = new Response<>();

        response.setData(this.userProfileService.findByUserId(id));

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add(ApiUtil.HEADER_API_VERSION, apiVersion);
        headers.add(ApiUtil.HEADER_API_KEY, apiKey);

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }
}
