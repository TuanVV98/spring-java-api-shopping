package com.spring.controller.v1.order.details;

import com.spring.dto.OrderDetailDTO;
import com.spring.dto.model.OrderDTO;
import com.spring.dto.response.Response;
import com.spring.service.order.detail.OrderDetailService;
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
@RequestMapping(value = "/api/v1/order/details")
public class OrderDetailController {

    private OrderDetailService orderDetailService;

    @Autowired
    public OrderDetailController(OrderDetailService orderDetailService) {
        this.orderDetailService = orderDetailService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @ApiOperation(value = "Route to created  order details")
    public ResponseEntity<Response<OrderDetailDTO>> create(
            @RequestHeader(value = ApiUtil.HEADER_API_VERSION, defaultValue = "${api.version}") String apiVersion,
            @RequestHeader(value = ApiUtil.HEADER_API_KEY, defaultValue = "${api.key}") String apiKey,
            @Valid @RequestBody OrderDetailDTO orderDetailDTO,
            BindingResult result
    ) {
//        System.out.println("order id :"+orderDetailDTO.getOrderId());
//        System.out.println("price :"+orderDetailDTO.getPrice());
//        System.out.println("quantity :"+orderDetailDTO.getQuantity());
//        System.out.println("product id :"+orderDetailDTO.getProductId());
        Response<OrderDetailDTO> response = new Response<>();
        if (result.hasErrors()) {
            result.getAllErrors().forEach(error -> response.addErrorMsgToResponse(error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(response);
        }

        OrderDetailDTO entity = this.orderDetailService.create(orderDetailDTO);
        response.setData(entity);

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add(ApiUtil.HEADER_API_VERSION, apiVersion);
        headers.add(ApiUtil.HEADER_API_KEY, apiKey);
        return new ResponseEntity<>(response, headers, HttpStatus.CREATED);
    }

    @GetMapping(value = "/findBy")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @ApiOperation(value = "Route to find order details by order Id")
    public ResponseEntity<Response<List<OrderDetailDTO>>> findByOrderId(
            @RequestHeader(value = ApiUtil.HEADER_API_VERSION, defaultValue = "${api.version}") String apiVersion,
            @RequestHeader(value = ApiUtil.HEADER_API_KEY, defaultValue = "${api.key}") String apiKey,
            @RequestParam(required = false) Long orderId
    ) {

        Response<List<OrderDetailDTO>> response = new Response<>();
        response.setData(this.orderDetailService.findByOrderId(orderId));

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add(ApiUtil.HEADER_API_VERSION, apiVersion);
        headers.add(ApiUtil.HEADER_API_KEY, apiKey);

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }
}
