package com.spring.controller.v1.order;

import com.spring.dto.model.OrderDTO;
import com.spring.dto.response.Response;
import com.spring.service.order.OrderService;
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
@RequestMapping(value = "/api/v1/orders")
public class OrderController {

    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    //  @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @PostMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @ApiOperation(value = "Route to create orders")
    public ResponseEntity<Response<OrderDTO>> create(
            @RequestHeader(value = ApiUtil.HEADER_API_VERSION, defaultValue = "${api.version}") String apiVersion,
            @RequestHeader(value = ApiUtil.HEADER_API_KEY, defaultValue = "${api.key}") String apiKey,
            @Valid @RequestBody OrderDTO orderDTO,
            BindingResult result
    ) {

//        System.out.println("id :" + orderDTO.getUserID());
//        System.out.println("name :" + orderDTO.getFullName());
//        System.out.println("mobile :" + orderDTO.getMobile());
//        System.out.println("address :" + orderDTO.getAddress());
//        System.out.println("total :" + orderDTO.getSubTotal());
        Response<OrderDTO> response = new Response<>();
        if (result.hasErrors()) {
            result.getAllErrors().forEach(error -> response.addErrorMsgToResponse(error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(response);
        }
        OrderDTO entity = this.orderService.create(orderDTO);
        response.setData(entity);

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add(ApiUtil.HEADER_API_VERSION, apiVersion);
        headers.add(ApiUtil.HEADER_API_KEY, apiKey);

        return new ResponseEntity<>(response, headers, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Route to updated a order")
    public ResponseEntity<Response<OrderDTO>> update(
            @RequestHeader(value = ApiUtil.HEADER_API_VERSION, defaultValue = "${api.version}") String apiVersion,
            @RequestHeader(value = ApiUtil.HEADER_API_KEY, defaultValue = "${api.key}") String apiKey,
            @Valid @RequestBody OrderDTO orderDTO,
            BindingResult result
    ) {
        System.out.println("----- update order ----");
        Response<OrderDTO> response = new Response<>();
        if (result.hasErrors()) {
            result.getAllErrors().forEach(error -> response.addErrorMsgToResponse(error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(response);
        }
        OrderDTO entity = this.orderService.update(orderDTO);
        response.setData(entity);

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add(ApiUtil.HEADER_API_VERSION, apiVersion);
        headers.add(ApiUtil.HEADER_API_KEY, apiKey);

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @GetMapping()
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Route to get all orders ")
    public ResponseEntity<Response<List<OrderDTO>>> findAll(
            @RequestHeader(value = ApiUtil.HEADER_API_VERSION, defaultValue = "${api.version}") String apiVersion,
            @RequestHeader(value = ApiUtil.HEADER_API_KEY, defaultValue = "${api.key}") String apiKey
    ) {
        Response<List<OrderDTO>> response = new Response<>();

        response.setData(this.orderService.getAll());

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add(ApiUtil.HEADER_API_VERSION, apiVersion);
        headers.add(ApiUtil.HEADER_API_KEY, apiKey);

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @GetMapping(value = "/findBy")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @ApiOperation(value = "Route to find all orders by user Id")
    public ResponseEntity<Response<List<OrderDTO>>> findByUserId(
            @RequestHeader(value = ApiUtil.HEADER_API_VERSION, defaultValue = "${api.version}") String apiVersion,
            @RequestHeader(value = ApiUtil.HEADER_API_KEY, defaultValue = "${api.key}") String apiKey,
            @RequestParam(required = false) Long userId
    ) {
        Response<List<OrderDTO>> response = new Response<>();

        response.setData(this.orderService.findByUserId(userId));
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add(ApiUtil.HEADER_API_VERSION, apiVersion);
        headers.add(ApiUtil.HEADER_API_KEY, apiKey);

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

}
