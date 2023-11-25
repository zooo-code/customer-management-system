package com.example.cms.order.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface OrderRepositoryJpa extends JpaRepository<OrderEntity,String> {



    boolean existsByOrdersId(String orderId);

    Optional<OrderEntity> findByOrdersId(String orderId);
}
