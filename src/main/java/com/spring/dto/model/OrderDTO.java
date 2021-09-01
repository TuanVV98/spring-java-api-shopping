package com.spring.dto.model;

import com.spring.enumeration.OrderEnum;
import com.spring.model.Order;
import com.spring.model.User;
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
public class OrderDTO {

    private Long id;

    private double subTotal;

    private OrderEnum status;

    private String fullName;

    private String mobile;

    private String address;

    private Date createdAt;

    private LocalDateTime updatedAt;

    private Long userId;

    public Order convertDTOToEntity() {
        return new ModelMapper().map(this, Order.class);
    }

}
