package com.example.cms.order.controller.request;

import com.example.cms.cart.domain.Cart;
import com.example.cms.member.infrastructure.MemberEntity;
import com.example.cms.order.domain.EPayments;
import com.example.cms.order.domain.Order;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderCreateRequest {

    private EPayments payment;
    //private CartResponse cartResponse;
    private Long memberId;
    private Long cartId;
    private Integer orderPrice;


    @Builder
    public OrderCreateRequest(EPayments payment, Long memberId, Long cartId, Integer orderPrice) {
        this.payment = payment;
        this.memberId = memberId;
        this.cartId = cartId;
        this.orderPrice = orderPrice;
    }

    public Order toOrder(MemberEntity memberEntity, Cart cart){
        return Order.builder()
                .memberEntity(memberEntity)
                .cart(cart)
                .payment(payment)
                .ordersPrice(orderPrice)
                .build();
    }
}
