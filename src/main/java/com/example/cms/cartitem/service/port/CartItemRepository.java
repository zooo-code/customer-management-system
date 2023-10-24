package com.example.cms.cartitem.service.port;

import com.example.cms.cartitem.domain.CartItem;

public interface CartItemRepository {
    CartItem save(CartItem cartItem);
}
