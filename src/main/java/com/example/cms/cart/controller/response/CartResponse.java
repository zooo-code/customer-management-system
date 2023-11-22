package com.example.cms.cart.controller.response;

import com.example.cms.cart.domain.Cart;
import com.example.cms.cartitem.controller.response.CartItemResponse;
import com.example.cms.cartitem.domain.CartItem;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class CartResponse {

    private final Integer count;
    private final Integer totalPrice;
    private final List<CartItemResponse> cartItemResponses;
    @Builder
    public CartResponse(Integer count, Integer totalPrice, List<CartItemResponse> cartItemResponses) {
        this.count = count;
        this.totalPrice = totalPrice;
        this.cartItemResponses = cartItemResponses;
    }




    public static CartResponse from(Cart cart){
        List<CartItemResponse> collect = cart.getCartItems().stream().map(CartItemResponse::from).toList();
        return CartResponse.builder()
                .count(cart.getCount())
                .cartItemResponses(collect)
                .totalPrice(cart.getTotalPrice())
                .build();
    }
}
