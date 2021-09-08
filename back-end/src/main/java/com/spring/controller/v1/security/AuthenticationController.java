package com.spring.controller.v1.security;


import com.spring.dto.model.UserDTO;
import com.spring.dto.model.security.JwtUserDTO;
import com.spring.dto.model.security.TokenDTO;
import com.spring.dto.response.Response;
import com.spring.model.User;
import com.spring.model.security.JwtUserDetailsImpl;
import com.spring.service.user.UserService;
import com.spring.utils.ApiUtil;
import com.spring.utils.JwtTokenUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/user/auth")
public class AuthenticationController {


    private AuthenticationManager authenticationManager;

    private JwtTokenUtil jwtTokenUtil;

    private UserDetailsService userDetailsService;

    private UserService userService;

    @Autowired
    public AuthenticationController(
            AuthenticationManager authenticationManager,
            JwtTokenUtil jwtTokenUtil,
            UserDetailsService userDetailsService,
            UserService userService
    ) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsService = userDetailsService;
        this.userService = userService;
    }

    /**
     * 201 - Created: Everything worked as expected.
     * 400 - Bad Request: The request was unacceptable, often due to missing a required parameter.
     * 401 - Unauthorized: No valid API key provided.
     * 403 - Forbidden: The API key doesn't have permissions to perform the request.
     * 404 - Not Found: The requested resource doesn't exist.
     * 409 - Conflict: The request conflicts with another request (perhaps due to using the same idempotent key).
     * 429 - Too Many Requests: Too many requests hit the API too quickly. We recommend an exponential back-off of your requests.
     * 500, 502, 503, 504 - Server Errors: something went wrong on API end (These are rare).
     */

    @PostMapping
    @ApiOperation(value = "Route to generate token Jwt")
    public ResponseEntity<Response<TokenDTO>> generateTokenJwt(
            @RequestHeader(value = ApiUtil.HEADER_API_VERSION, defaultValue = "${api.version}") String apiVersion,
            @Valid @RequestBody JwtUserDTO jwtUserDTO,
            BindingResult result
    ) throws AuthenticationException {

        Response<TokenDTO> response = new Response<>();
        if (result.hasErrors()) {
            result.getAllErrors().forEach(error -> response.addErrorMsgToResponse(error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(response);
        }

        if(this.userService.checkIfEmailExistsAndStatus(jwtUserDTO.getEmail()).isEmpty()){
            response.addErrorMsgToResponse("Email or password Incorrect !!");
            return ResponseEntity.badRequest().body(response);
        }

        Authentication authentication = authenticationManager.authenticate
                (new UsernamePasswordAuthenticationToken(jwtUserDTO.getEmail(), jwtUserDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

//        UserDetails userDetails = userDetailsService.loadUserByUsername(jwtUserDTO.getEmail());

        JwtUserDetailsImpl userDetails = (JwtUserDetailsImpl) authentication.getPrincipal();
        String token = jwtTokenUtil.getToken(userDetails);
        response.setData(new TokenDTO(token));

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add(ApiUtil.HEADER_API_VERSION, apiVersion);

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    /**
     * https://www.baeldung.com/get-user-in-spring-security
     */
    @GetMapping
    @ApiOperation(value = "Route to get user by Jwt")
    public ResponseEntity<Response<UserDTO>> getUser(
            @RequestHeader(value = ApiUtil.HEADER_API_VERSION, defaultValue = "${api.version}") String apiVersion,
            Principal principal
    ) {

        Response<UserDTO> response = new Response<>();

        Optional<User> user = this.userService.findByUsername(principal.getName());
        user.ifPresent(value -> response.setData(new UserDTO(value.getId(), value.getRole())));
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add(ApiUtil.HEADER_API_VERSION, apiVersion);

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }
}
