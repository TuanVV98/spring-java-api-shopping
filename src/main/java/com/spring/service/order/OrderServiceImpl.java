package com.spring.service.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.dto.model.OrderDTO;
import com.spring.entity.Order;
import com.spring.repository.OrderRepository;
import com.spring.util.mappers.MapperUtil;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepo;
	
	@Autowired
	private MapperUtil mapperUtil;

	public OrderServiceImpl(
		OrderRepository orderRepo,
		MapperUtil mapperUtil
			
	) {
		this.orderRepo = orderRepo;
		this.mapperUtil = mapperUtil;
	}

	@Override
	public OrderDTO save(OrderDTO orderDTO) {
		Order orderEntity = orderDTO.convertDTOToEntity();
		
		return this.orderRepo.save(orderEntity).convertEntityToDTO();
	}

	
	@Override
	public List<OrderDTO> findAll() {
	
		List<Order> orderEntity = this.orderRepo.findAll();
		
		return this.mapperUtil.mapList(orderEntity, OrderDTO.class);
	}

}
