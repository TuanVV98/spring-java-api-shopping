package com.spring.service.orderDetails;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.dto.model.OrderDetailsDTO;
import com.spring.dto.model.ProductDTO;
import com.spring.entity.OrderDetails;
import com.spring.repository.OrderDetailRepository;
import com.spring.util.mappers.MapperUtil;

@Service
public class OrderDetailsServiceImpl implements OrderDetailsService {

	@Autowired
	private OrderDetailRepository orderDetailRepository;

	@Autowired
	private MapperUtil mapperUtil;

	public OrderDetailsServiceImpl(OrderDetailRepository orderDetailRepository, MapperUtil mapperUtil) {
		this.orderDetailRepository = orderDetailRepository;
		this.mapperUtil = mapperUtil;
	}

	@Override
	public OrderDetailsDTO save(OrderDetailsDTO orderDetailsDTO) {
		OrderDetails orderDetailsEntity = orderDetailsDTO.convertDTOToEntity();

		return this.orderDetailRepository.save(orderDetailsEntity).convertEntiyToDTO();
	}

	@Override
	public List<OrderDetailsDTO> findAll() {

		return null;
	}

	@Override
	public List<OrderDetailsDTO> findByOrderId(Long orderId) {
		List<OrderDetailsDTO> itemsDTO = new ArrayList<>();

		this.orderDetailRepository.findByOrderId(orderId).stream().forEach(t -> itemsDTO.add(t.convertEntiyToDTO()));
		return itemsDTO;
	}

}
