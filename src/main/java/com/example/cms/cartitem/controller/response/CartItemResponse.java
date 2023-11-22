package com.example.cms.cartitem.controller.response;

import com.example.cms.cartitem.domain.CartItem;
import com.example.cms.item.controller.response.ItemResponse;
import com.example.cms.item.domain.Item;
import lombok.Builder;

public class CartItemResponse {

    private final Integer count;

    private final Integer price;

    private final ItemResponse itemResponse;

    @Builder
    public CartItemResponse(Integer count, Integer price, ItemResponse itemResponse) {
        this.count = count;
        this.price = price;
        this.itemResponse = itemResponse;
    }

    public static CartItemResponse from(CartItem cartItem){
        return CartItemResponse.builder()
                .itemResponse(ItemResponse.from(cartItem.getItem()))
                .count(cartItem.getCount())
                .price(cartItem.getPrice())
                .build();
    }
}
