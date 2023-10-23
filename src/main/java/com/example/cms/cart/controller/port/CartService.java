package com.example.cms.cart.controller.port;

import com.example.cms.cart.controller.request.CartDeleteRequest;
import com.example.cms.cart.controller.request.CartRequest;
import com.example.cms.cart.domain.Cart;

public interface CartService {

    Cart CreateCart(CartRequest request);
    void deleteCartItem(CartDeleteRequest request);
}
