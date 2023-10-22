package com.example.cms.order.domain;

import com.example.cms.cart.domain.Cart;
import com.example.cms.member.domain.Member;
import lombok.Builder;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
public class Order {

    private Long id;

    private String ordersId;

    private LocalDateTime cancelDate;

    private Integer ordersPrice;

    private EPayments payment;

    private Cart cart;

    private Member member;
    private LocalDateTime CreatedAt;

    @Builder
    public Order(Long id, String ordersId, LocalDateTime cancelDate, Integer ordersPrice, EPayments payment, Cart cart, Member member, LocalDateTime createdAt) {
        this.id = id;
        this.ordersId = ordersId;
        this.cancelDate = cancelDate;
        this.ordersPrice = ordersPrice;
        this.payment = payment;
        this.cart = cart;
        this.member = member;
        CreatedAt = createdAt;
    }




    public void setOrdersId(String ordersId){
        this.ordersId = ordersId;
    }

    public void cancel(LocalDateTime cancelDate){
        this.cancelDate = cancelDate;
    }
}
