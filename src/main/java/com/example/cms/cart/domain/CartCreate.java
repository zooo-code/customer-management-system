package com.example.cms.cart.domain;

import com.example.cms.cartitem.domain.CartItem;
import com.example.cms.member.domain.Member;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Getter
public class CartCreate {

    private final Long id;
    private final Member member;
    private final Integer count;
    private final Integer totalPrice;
    private List<CartItem> cartItem = new ArrayList<>();
    private final LocalDateTime createdAt;
    @Builder
    public CartCreate(Long id, Member member, Integer count, Integer totalPrice, List<CartItem> cartItem, LocalDateTime createdAt) {
        this.id = id;
        this.member = member;
        this.count = count;
        this.totalPrice = totalPrice;
        this.cartItem = cartItem;
        this.createdAt = createdAt;
    }
}
