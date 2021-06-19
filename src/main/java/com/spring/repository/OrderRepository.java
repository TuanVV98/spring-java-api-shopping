package com.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
