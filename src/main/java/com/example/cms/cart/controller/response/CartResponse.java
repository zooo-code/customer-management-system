package com.example.cms.cart.controller.response;

import com.example.cms.cartitem.domain.CartItem;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
public class CartResponse {

    private final Integer count;
    private final Integer totalPrice;
    private final List<CartItem> cartItems;
    @Builder
    public CartResponse(Integer count, Integer totalPrice, List<CartItem> cartItems) {
        this.count = count;
        this.totalPrice = totalPrice;
        this.cartItems = cartItems;
    }
}
