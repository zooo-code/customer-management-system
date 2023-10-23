package com.example.cms.cart.domain;

import com.example.cms.cartitem.domain.CartItem;
import com.example.cms.member.domain.Member;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Getter
public class Cart {

    private final Long id;
    private final Member member;
    private Integer count;
    private Integer totalPrice;
    private List<CartItem> cartItem = new ArrayList<>();
    private final LocalDateTime createdAt;
    @Builder
    public Cart(Long id, Member member, Integer count, Integer totalPrice, List<CartItem> cartItem, LocalDateTime createdAt) {
        this.id = id;
        this.member = member;
        this.count = count;
        this.totalPrice = totalPrice;
        this.cartItem = cartItem;
        this.createdAt = createdAt;
    }


    public static Cart cartCreate(){
        List<CartItem> cartItems = new ArrayList<>();
        Integer countItem = 0;
        Integer totalPrice = 0;
        return Cart.builder()
                .cartItem(cartItems)
                .totalPrice(totalPrice)
                .count(countItem)
                .build();
    }
    public void addCountCart(Integer count) {
        this.count += count;
    }
    public void addTotalPrice(Integer price) {
        this.totalPrice += price;
    }

    public void addCartItem(CartItem cartItem) {
        this.cartItem.add(cartItem);

    }
}
