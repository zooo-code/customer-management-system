package com.example.cms.cart.domain;

import com.example.cms.cartitem.domain.CartItem;


import com.example.cms.utils.common.service.port.ClockHolder;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Getter
public class Cart {

    private final Long id;
    private Integer count;
    private Integer totalPrice;
    private final List<CartItem> cartItems;
    private final Long createdAt;
    @Builder
    public Cart(Long id, Integer count, Integer totalPrice, List<CartItem> cartItems, Long createdAt) {
        this.id = id;
        this.count = count;
        this.totalPrice = totalPrice;
        this.cartItems = cartItems;
        this.createdAt = createdAt;
    }


    public static Cart cartCreate(ClockHolder clockHolder){
        List<CartItem> cartItems = new ArrayList<>();
        Integer countItem = 0;
        Integer totalPrice = 0;
        return Cart.builder()
                .cartItems(cartItems)
                .totalPrice(totalPrice)
                .count(countItem)
                .createdAt(clockHolder.millis())
                .build();
    }

    public void addCountCart(Integer count) {
        this.count += count;
    }
    public void addTotalPrice(Integer price) {
        this.totalPrice += price;
    }

    public void addCartItem(CartItem cartItem) {
        this.cartItems.add(cartItem);

    }
}
