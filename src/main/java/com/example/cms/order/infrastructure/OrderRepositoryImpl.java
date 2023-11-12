package com.example.cms.order.infrastructure;


import com.example.cms.order.domain.Order;
import com.example.cms.order.service.port.OrderRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class OrderRepositoryImpl implements OrderRepository {

    private final OrderRepositoryJpa orderRepositoryJpa;

    public OrderRepositoryImpl(OrderRepositoryJpa orderRepositoryJpa) {
        this.orderRepositoryJpa = orderRepositoryJpa;
    }

    @Override
    public Order save(Order order) {
        return orderRepositoryJpa.save(OrderEntity.from(order)).toModel();
    }

    @Override
    public boolean existsByOrdersId(String orderId) {
        return false;
    }

    @Override
    public Optional<Order> findByOrdersId(String orderId) {
        return orderRepositoryJpa
                .findByOrdersId(orderId).map(OrderEntity::toModel);
    }


}
