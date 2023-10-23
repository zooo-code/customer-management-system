package com.example.cms.order.domain;

import com.example.cms.member.domain.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderCreate {

    private EPayments payment;
    //private CartResponse cartResponse;
    private Long memberId;
    private Long cartId;
    private Integer orderPrice;

    private String mobile;
    @Builder
    public OrderCreate(EPayments payment, Long memberId, Long cartId, Integer orderPrice, String mobile) {
        this.payment = payment;
        this.memberId = memberId;
        this.cartId = cartId;
        this.orderPrice = orderPrice;
        this.mobile = mobile;
    }




    public Order toOrder(Member member, com.example.cms.cart.domain.Cart cart){
        return Order.builder()
                .member(member)
                .cart(cart)
                .payment(payment)
                .ordersPrice(orderPrice)
                .build();
    }
}
