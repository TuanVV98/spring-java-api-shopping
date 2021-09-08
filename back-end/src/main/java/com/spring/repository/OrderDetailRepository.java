package com.spring.repository;

import com.spring.dto.OrderDetailDTO;
import com.spring.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {

    @Query(value = "SELECT new com.spring.dto.OrderDetailDTO(o.id, o.price, o.quantity, o.createdAt, "
            +"o.updatedAt,o.order.id,o.product.id , o.product.name)"
            + " FROM OrderDetail o "
            + "WHERE o.order.id =:orderId")
    List<OrderDetailDTO> findAllByOrderId(@Param(value = "orderId") Long orderId);
}
