package com.example.cms.order.controller.port;

import com.example.cms.order.domain.Order;
import com.example.cms.order.domain.OrderCreateRequest;

import java.util.List;

public interface OrderService {
    Order createOrder(OrderCreateRequest request);
    List<Order> findByOrdersId(String orderId);

    void cancel(String orderId);
}
