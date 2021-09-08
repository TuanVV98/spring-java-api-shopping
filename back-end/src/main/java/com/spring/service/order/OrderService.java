package com.spring.service.order;

import com.spring.dto.model.OrderDTO;

import java.util.List;

public interface OrderService {

    OrderDTO create(OrderDTO orderDTO);

    OrderDTO update(OrderDTO orderDTO);

    List<OrderDTO> findByUserId(Long userId);

    List<OrderDTO> getAll();

}
