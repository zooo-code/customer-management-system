package com.example.cms.cart.controller.port;

import com.example.cms.cart.controller.request.CartRequest;
import com.example.cms.cart.domain.Cart;

public interface CartService {

    Cart CreateCart(CartRequest request);
    void deleteCart(Long cartId);
}
