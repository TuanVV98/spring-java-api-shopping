package com.spring.controller.v1.registration;


import com.spring.dto.model.UserDTO;
import com.spring.dto.response.Response;
import com.spring.model.VerificationToken;
import com.spring.service.user.UserService;
import com.spring.service.verificationToken.VerificationTokenService;
import com.spring.utils.ApiUtil;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/user/register")
public class RegistrationController {


    private UserService userService;

    private VerificationTokenService verificationTokenService;


    @Autowired
    public RegistrationController(
            UserService userService,
            VerificationTokenService verificationTokenService) {
        this.userService = userService;
        this.verificationTokenService = verificationTokenService;
    }


    @PostMapping
    @ApiOperation(value = "Route to register user")
    public ResponseEntity<Response<UserDTO>> register(
            @RequestHeader(value = ApiUtil.HEADER_API_VERSION, defaultValue = "${api.version}") String apiVersion,
            @Valid @RequestBody UserDTO userDTO,
            BindingResult result
    ) {
        Response<UserDTO> response = new Response<>();

        if (result.hasErrors()) {
            result.getAllErrors().forEach(error -> response.addErrorMsgToResponse(error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(response);
        }

        if(this.userService.checkIfUsernameExists(userDTO.getUsername()).isPresent()){
            response.addErrorMsgToResponse("User already exists for this username !");
            return ResponseEntity.badRequest().body(response);
        }

        if(this.userService.checkIfEmailExists(userDTO.getEmail()).isPresent()){
            response.addErrorMsgToResponse("User already exists for this email !");
            return ResponseEntity.badRequest().body(response);
        }
//
        response.setData(this.userService.register(userDTO));
//
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add(ApiUtil.HEADER_API_VERSION, apiVersion);

        return new ResponseEntity<>(response, headers, HttpStatus.CREATED);
    }

    @GetMapping(value = "/verify")
    @ApiOperation(value = "Route to verify user with token in email")
    public ResponseEntity<?> verifyUser(@RequestParam(required = false) String token) {

        Response<String> response = new Response<>();

        Optional<VerificationToken> checkValidToken = this.verificationTokenService.checkValidToken(token);

        if(checkValidToken.isEmpty()){
            response.addErrorMsgToResponse("Token is not valid !");
            return ResponseEntity.accepted().body(response);
        }

        Optional<VerificationToken> checkConfirmToken = this.verificationTokenService.checkConfirmToken(token);

        if(checkConfirmToken.isEmpty()){
            response.addErrorMsgToResponse("User has been activated ! Thank you !");
            return ResponseEntity.accepted().body(response);
        }

        boolean verifyUser = this.userService.verifyAccount(checkConfirmToken);

        if(!verifyUser){
            response.addErrorMsgToResponse("Token has expired !");
            return ResponseEntity.badRequest().body(response);
        }

        response.setData("Confirm !!");
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

}
