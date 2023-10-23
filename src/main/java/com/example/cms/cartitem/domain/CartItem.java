package com.example.cms.cartitem.domain;

import com.example.cms.cart.domain.Cart;

import com.example.cms.item.domain.Item;
import lombok.Builder;
import lombok.Getter;


@Getter
public class CartItem {

    private final Long id;
    private final Cart cart;
    private final Item item;
    private final Integer count;
    private final Integer price;
    @Builder
    public CartItem(Long id, Cart cart, Item item, Integer count, Integer price) {
        this.id = id;
        this.cart = cart;
        this.item = item;
        this.count = count;
        this.price = price;
    }



    public static CartItem createCartItem(Cart cart, Item item, Integer count) {
        return CartItem.builder()
                .cart(cart)
                .item(item)
                .price(item.getCost())
                .count(count)
                .build();
    }
}
