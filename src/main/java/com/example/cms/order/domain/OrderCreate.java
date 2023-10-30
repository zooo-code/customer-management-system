package com.example.cms.order.domain;

import com.example.cms.cart.domain.Cart;
import com.example.cms.member.domain.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderCreate {

    private EPayments payment;


    private Long cartId;

    private String mobile;
    @Builder
    public OrderCreate(EPayments payment, Long cartId, String mobile) {
        this.payment = payment;
        this.cartId = cartId;
        this.mobile = mobile;
    }


}
