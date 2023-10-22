package com.example.cms.order.service.port;

import com.example.cms.order.domain.Order;


import java.util.Optional;

public interface OrderRepository {

    Order save(Order order);

    boolean existsByOrdersId(String orderId);

    Optional<Order> findById(String orderId);

}
