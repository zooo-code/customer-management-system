package com.example.cms.order.controller.port;

import com.example.cms.order.domain.Order;
import com.example.cms.order.domain.OrderCreate;

import java.util.List;

public interface OrderService {
    Order createOrder(OrderCreate request);
    List<Order> findByOrdersId(String orderId);

    void cancel(String orderId);
}
