package com.example.cms.cart.controller.request;


import com.example.cms.cartitem.controller.request.CartItemRequest;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class CartRequest {

    private List<CartItemRequest> cartItemRequests;


    @Builder
    public CartRequest(List<CartItemRequest> cartItemRequests) {
        this.cartItemRequests = cartItemRequests;
    }
}
