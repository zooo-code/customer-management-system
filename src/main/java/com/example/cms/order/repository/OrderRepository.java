package com.example.cms.order.repository;

import com.example.cms.order.domain.Order;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order,String> {

    Order save(Order order);

    boolean existsByOrdersId(String orderId);

    Optional<Order> findById(String orderId);
}
