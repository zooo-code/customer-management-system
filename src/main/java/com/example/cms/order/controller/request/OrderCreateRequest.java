package com.example.cms.order.controller.request;

import com.example.cms.cart.domain.Cart;
import com.example.cms.member.domain.Member;
import com.example.cms.order.domain.Order;
import com.example.cms.order.domain.Payments;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderCreateRequest {

    private Payments payment;
    //private CartResponse cartResponse;
    private Long memberId;
    private Long cartId;
    private Integer orderPrice;


    @Builder
    public OrderCreateRequest(Payments payment, Long memberId, Long cartId, Integer orderPrice) {
        this.payment = payment;
        this.memberId = memberId;
        this.cartId = cartId;
        this.orderPrice = orderPrice;
    }

    public Order toOrder(Member member, Cart cart){
        return Order.builder()
                .member(member)
                .cart(cart)
                .payment(payment)
                .ordersPrice(orderPrice)
                .build();
    }
}
