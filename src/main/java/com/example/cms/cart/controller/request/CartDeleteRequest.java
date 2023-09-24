package com.example.cms.cart.controller.request;

import com.example.cms.cartitem.controller.request.CartItemCreateRequest;
import com.example.cms.cartitem.controller.request.CartItemDeleteRequest;
import lombok.Getter;

import java.util.List;

@Getter
public class CartDeleteRequest {

    private Long cartId;
    private List<CartItemDeleteRequest> cartItemRequests;


    public CartDeleteRequest(Long cartId, List<CartItemDeleteRequest> cartItemRequests) {
        this.cartId = cartId;
        this.cartItemRequests = cartItemRequests;
    }
}
