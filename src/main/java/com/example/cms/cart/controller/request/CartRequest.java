package com.example.cms.cart.controller.request;


import com.example.cms.cartitem.controller.request.CartItemCreateRequest;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Getter
public class CartRequest {

    private List<CartItemCreateRequest> cartItemRequests;

    private String phone;

    public CartRequest() {
    }
    @Builder
    public CartRequest(List<CartItemCreateRequest> cartItemRequests, String phone) {
        this.cartItemRequests = cartItemRequests;
        this.phone = phone;
    }
}
