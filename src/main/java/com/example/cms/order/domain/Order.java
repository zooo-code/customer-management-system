package com.example.cms.order.domain;

import com.example.cms.cart.domain.Cart;
import com.example.cms.member.domain.Member;
import com.example.cms.utils.common.service.port.ClockHolder;
import lombok.Builder;
import lombok.Getter;


@Getter
public class Order {

    private final Long id;

    private String ordersId;

    private Long cancelDate;

    private final Integer ordersPrice;

    private final EPayments payment;

    private final Cart cart;

    private final Member member;
    private final Long CreatedAt;

    @Builder
    public Order(Long id, String ordersId, Long cancelDate, Integer ordersPrice,
                 EPayments payment, Cart cart, Member member, Long createdAt) {
        this.id = id;
        this.ordersId = ordersId;
        this.cancelDate = cancelDate;
        this.ordersPrice = ordersPrice;
        this.payment = payment;
        this.cart = cart;
        this.member = member;
        this.CreatedAt = createdAt;
    }
    public static Order from(OrderCreate orderCreate, Member member, Cart cart, ClockHolder clockHolder){
        return Order.builder()
                .cart(cart)
                .member(member)
                .payment(orderCreate.getPayment())
                .ordersPrice(cart.getTotalPrice())
                .createdAt(clockHolder.millis())
                .build();
    }

    public void cancel(ClockHolder clockHolder){
        this.cancelDate = clockHolder.millis();
    }
}
