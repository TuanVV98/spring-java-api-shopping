package com.spring.service.order;

import com.spring.dto.model.OrderDTO;
import com.spring.enumeration.OrderEnum;
import com.spring.model.Order;
import com.spring.repository.OrderRepository;
import com.spring.utils.mapper.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;
    private MapperUtil mapperUtil;

    @Autowired
    public OrderServiceImpl(
            OrderRepository orderRepository,
            MapperUtil mapperUtil
    ) {
        this.orderRepository = orderRepository;
        this.mapperUtil = mapperUtil;
    }

    @Override
    public OrderDTO create(OrderDTO orderDTO) {
        orderDTO.setStatus(OrderEnum.NEW);
        Order order = this.orderRepository.save(orderDTO.convertDTOToEntity());
        return order.convertEntityToDTO();
    }

    @Override
    public OrderDTO update(OrderDTO orderDTO) {
        Order order = this.orderRepository.save(orderDTO.convertDTOToEntity());
        return order.convertEntityToDTO();
    }

    @Override
    public List<OrderDTO> findByUserId(Long userId) {
        return mapperUtil.mapList(this.orderRepository.findByUserId(userId),OrderDTO.class);
    }

    @Override
    public List<OrderDTO> getAll() {
        List<OrderDTO> item = new ArrayList<>();
        this.orderRepository.findAll().forEach(t->item.add(t.convertEntityToDTO()));
        return item;
//        return mapperUtil.mapList(this.orderRepository.findAll(),OrderDTO.class);
    }
}
