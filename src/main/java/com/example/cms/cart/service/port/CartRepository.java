package com.example.cms.cart.service.port;

import com.example.cms.cart.domain.Cart;


import java.util.Optional;

public interface CartRepository {

    Optional<Cart> findById(Long id);

    Cart save(Cart cart);
}
