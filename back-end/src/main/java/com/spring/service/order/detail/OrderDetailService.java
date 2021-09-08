package com.spring.service.order.detail;

import com.spring.dto.OrderDetailDTO;
import com.spring.model.Order;

import java.util.List;

public interface OrderDetailService {

    OrderDetailDTO create(OrderDetailDTO orderDetailDTO);

    OrderDetailDTO update(OrderDetailDTO orderDetailDTO);

    List<OrderDetailDTO> findByOrderId(Long orderId);
}
