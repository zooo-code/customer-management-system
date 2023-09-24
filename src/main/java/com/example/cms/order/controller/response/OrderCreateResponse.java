package com.example.cms.order.controller.response;

import com.example.cms.order.domain.Order;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderCreateResponse {

    private String orderId;

    @Builder
    public OrderCreateResponse(String orderId) {
        this.orderId = orderId;
    }

    public static OrderCreateResponse of(Order order){
        return OrderCreateResponse.builder()
                .orderId(order.getOrdersId())
                .build();
    }
}
