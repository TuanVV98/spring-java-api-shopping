package com.spring.service.order.detail;

import com.spring.dto.OrderDetailDTO;
import com.spring.model.OrderDetail;
import com.spring.repository.OrderDetailRepository;
import com.spring.utils.mapper.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {
    private OrderDetailRepository orderDetailRepository;

    private MapperUtil mapperUtil;

    @Autowired
    public OrderDetailServiceImpl(
            OrderDetailRepository orderDetailRepository,
            MapperUtil mapperUtil
    ) {
        this.orderDetailRepository = orderDetailRepository;
        this.mapperUtil = mapperUtil;
    }

    @Override
    public OrderDetailDTO create(OrderDetailDTO orderDetailDTO) {
        OrderDetail orderDetail = this.orderDetailRepository.save(orderDetailDTO.convertDTOToEntity());
        return orderDetail.convertEntityToDTO();
    }

    @Override
    public OrderDetailDTO update(OrderDetailDTO orderDetailDTO) {
        OrderDetail orderDetail = this.orderDetailRepository.save(orderDetailDTO.convertDTOToEntity());
        return orderDetail.convertEntityToDTO();
    }

    @Override
    public List<OrderDetailDTO> findByOrderId(Long orderId) {
//        List<OrderDetailDTO> item = new ArrayList<>();
//        for (OrderDetail t : this.orderDetailRepository.findAllByOrderId(orderId)) {
//
//            item.add(t.convertEntityToDTO());
////            System.out.println("---" + item);
//        }
//        return mapperUtil.mapList(this.orderDetailRepository.findAllByOrderId(orderId), OrderDetailDTO.class);
        return this.orderDetailRepository.findAllByOrderId(orderId);
    }
}
