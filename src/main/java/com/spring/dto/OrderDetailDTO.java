package com.spring.dto;

import com.spring.dto.model.OrderDTO;
import com.spring.model.OrderDetail;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailDTO {

    private Long id;

    private double price;

    private int quantity;

    private Date createdAt;

    private LocalDateTime updatedAt;

    private Long orderId;

    private Long productId;

    private String productName;

//    public OrderDetailDTO(Long id, double price, int quantity, Date createdAt, LocalDateTime updatedAt,
//                          Long orderId, Long productId, String productName) {
//        this.id = id;
//        this.price = price;
//        this.quantity = quantity;
//        this.createdAt = createdAt;
//        this.updatedAt = updatedAt;
//        this.orderId = orderId;
//        this.productId = productId;
//        this.productName = productName;
//    }

    public OrderDetail convertDTOToEntity() {
        return new ModelMapper().map(this, OrderDetail.class);
    }
}
